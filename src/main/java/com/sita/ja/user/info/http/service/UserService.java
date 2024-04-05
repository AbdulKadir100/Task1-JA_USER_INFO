package com.sita.ja.user.info.http.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@EnableAutoConfiguration
@Configuration
@PropertySource("classpath:users.properties")
public class UserService {

	private static final String USER_PROPERTIES_FILE = "users.properties";

	// Method to load user properties from a properties file
	public Properties loadUserProperties() {

		Properties properties = new Properties();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(USER_PROPERTIES_FILE)) {
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new RuntimeException("Unable to load " + USER_PROPERTIES_FILE + ". File not found.");
			}
		} catch (IOException e) {
			// Handle IOException if unable to read the properties file
			throw new RuntimeException("Error reading " + USER_PROPERTIES_FILE + ": " + e.getMessage(), e);
		}
		return properties;
	}

	// Method to build a JSON payload using user and workstation details
	public String buildJsonPayload(String user, String workstation) {
		return "{\"user\": \"" + user + "\", \"workstation\": \"" + workstation
				+ "\", \"status\": \"Success\", \"message\": \"user exist in database and has access to given workstation\"}";
	}

}
