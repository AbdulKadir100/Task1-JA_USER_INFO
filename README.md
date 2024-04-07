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
