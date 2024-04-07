# JA_USER_INFO_HTTP

JA_USER_INFO_HTTP is a Spring Boot project aimed at providing user information via HTTP endpoints.

## Description

JA_USER_INFO_HTTP is built using Spring Boot and integrates with Spring Security for basic authentication and authorization. It exposes RESTful endpoints to retrieve user information securely.

## Prerequisites

Before running this project, ensure you have the following prerequisites installed:

- Java Development Kit (JDK) 17 or higher
- Apache Maven
- Spring Boot 3.2.1 or above
- Git (optional)


## Usage

Once the application is running, you can access the RESTful endpoints to retrieve user information. For example:

- **GET** `http://localhost:8080/api/userDetails?user={name}`

  Replace `{name}` with the actual username (e.g., admin, staff, etc.) to retrieve the corresponding user information from the properties file.

- This web service exposes the data to other running web services at:

  **POST** `http://localhost:8081/api/addUserInfo`

  The **GET** service will return status `200 OK` when data is successfully exposed or `Error 404` if the user is not found.

  Similarly, the **POST** service shows the exposed data retrieved from the first web service.

  # Security Configuration for JA_USER_INFO_HTTP

This Java class represents the security configuration for the JA_USER_INFO_HTTP Spring Boot project. It utilizes Spring Security to provide authentication and authorization features.

## Overview

The `SecurityConfig` class is responsible for configuring security settings within the JA_USER_INFO_HTTP project. It ensures that only authorized users can access the application's endpoints.

## UserDetails Creation

The `userDetailsService()` method creates a single user, named "user", with the password "user". This user is granted the role of "USER". It's essential to customize this method to match your application's user authentication requirements.

## Password Encoding

Passwords are securely hashed using the BCryptPasswordEncoder. This ensures that user passwords are stored securely in the system.

## Usage

To integrate this security configuration into your Spring Boot project, follow these steps:

1. **Include SecurityConfig**: Copy the `SecurityConfig` class into your project's source code.

2. **Dependency Management**: Ensure that your project's `pom.xml` or `build.gradle` file includes the necessary dependencies for Spring Security.

3. **Customization**: Customize the `userDetailsService()` method to match your application's user authentication requirements. You may need to integrate with your user database or user management system.

4. **Secure Endpoints**: Apply appropriate security annotations and configurations to your controllers and endpoints. Ensure that sensitive endpoints are protected and accessible only to authorized users.

5. **Testing**: Thoroughly test the security configuration to ensure that it behaves as expected. Test various authentication scenarios to validate user access controls.
