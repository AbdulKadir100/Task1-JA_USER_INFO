package com.sita.service2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sita.service2.payload.ApiResponse;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.sita.service2.payload.UserDto;

@RestController
@RequestMapping("/api")
public class UserInfoController {

	@PostMapping("/addUserInfo")
	public ResponseEntity<ApiResponse<UserDto>> addUserInfo(@RequestBody UserDto userDto) {

		// Create a custom response object
		ApiResponse<UserDto> response = new ApiResponse<>();
		response.setMessage("User information added successfully");
		response.setUserDto(userDto);

		// Return the custom response with a success status code
		return ResponseEntity.ok(response);
	}

}
