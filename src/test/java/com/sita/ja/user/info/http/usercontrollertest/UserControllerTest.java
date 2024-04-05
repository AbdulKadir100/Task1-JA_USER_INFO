package com.sita.ja.user.info.http.usercontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sita.ja.user.info.http.controller.UserController;
import com.sita.ja.user.info.http.service.UserService;

public class UserControllerTest {

	@Test
	public void test_GetUserDetail_Success() {
		// Mock UserService
		UserService userService = mock(UserService.class);
		Properties properties = new Properties();
		properties.setProperty("testUser", "testWorkstation");

		when(userService.loadUserProperties()).thenReturn(properties);
		when(userService.buildJsonPayload("testUser", "testWorkstation")).thenReturn("dummyJson");

		// Mocking RestTemplate
		RestTemplate restTemplate = mock(RestTemplate.class);
		ResponseEntity<String> mockResponse = new ResponseEntity<>("Mock response body", HttpStatus.OK);
		when(restTemplate.postForEntity(eq("http://localhost:8081/api/users/userInfo"), any(HttpEntity.class),
				eq(String.class))).thenReturn(mockResponse);

		// Create UserController instance with mocked dependencies
		UserController controller = new UserController(restTemplate, userService);

		// Call the method under test
		ResponseEntity<String> responseEntity = controller.getUserDetail("testUser");

		// Verify the returned ResponseEntity
		assertSame(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Mock response body", responseEntity.getBody());

	}

	@Test
	public void testGetUserDetail_UserNotFound() {
		// Mock UserService
		UserService userService = mock(UserService.class);
		Properties properties = new Properties();
		when(userService.loadUserProperties()).thenReturn(properties);

		// Mock RestTemplate (not necessary for this test case)

		// Create UserController instance with mocked dependencies
		UserController controller = new UserController(null, userService);

		// Call the method under test
		ResponseEntity<String> responseEntity = controller.getUserDetail("nonexistentUser");

		// Verify the returned ResponseEntity
		assertSame(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	public void testGetUserDetail_ErrorReadingProperties() {
		// Mock UserService
		UserService userService = mock(UserService.class);
		when(userService.loadUserProperties()).thenReturn(null);

		// Mock RestTemplate (not necessary for this test case)

		// Create UserController instance with mocked dependencies
		UserController controller = new UserController(null, userService);

		// Call the method under test
		ResponseEntity<String> responseEntity = controller.getUserDetail("anyUser");

		// Verify the returned ResponseEntity
		assertSame(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		assertEquals("Error reading user properties", responseEntity.getBody());
	}

}
