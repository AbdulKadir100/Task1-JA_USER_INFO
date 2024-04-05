package com.sita.ja.user.info.http.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sita.ja.user.info.http.service.UserService;

import java.util.Properties;

@RestController
@RequestMapping("/api")
public class UserController {

	private RestTemplate restTemplate;
	private UserService userService;

	public UserController(RestTemplate restTemplate, UserService userService) {
		this.restTemplate = restTemplate;
		this.userService = userService;
	}

	@GetMapping("/userDetails")
	public ResponseEntity<String> getUserDetail(@RequestParam String user) {

		// Load user properties using UserService
		Properties properties = userService.loadUserProperties();

		// Check if properties are loaded successfully
		if (properties == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading user properties");
		}

		// Get the workstation associated with the provided user
		String workstation = properties.getProperty(user);

		// Check if the workstation is found
		if (workstation == null) {
			return ResponseEntity.notFound().build();
		}

		// Build JSON payload using UserService
		String jasonPayload = userService.buildJsonPayload(user, workstation);

		// Set headers for the HTTP request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create an HTTP entity with the JSON payload and headers
		HttpEntity<String> request = new HttpEntity<>(jasonPayload, headers);

		// Make a POST request to another service using RestTemplate
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/api/users/addUserInfo",
				request, String.class);

		// Return the response received from the other service
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}

}
