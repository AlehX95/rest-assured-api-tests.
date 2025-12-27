# REST Assured API Automation Framework

This project is a REST API automation framework built with **Java, TestNG, and REST Assured**, designed to demonstrate professional API testing practices, including **CRUD operations, POJO serialization, Data-Driven Testing, and clean architecture**.

The framework uses **JSONPlaceholder** as a mock API to practice real-world API testing scenarios.

---

##  Technologies Used

- Java
- REST Assured
- TestNG
- Maven
- Hamcrest Matchers
- JSONPlaceholder (Mock API)

---

##  Features Covered

- Full CRUD API testing (GET, POST, PUT, PATCH, DELETE)
- POJO-based request bodies (automatic serialization)
- PayloadBuilder for dynamic and negative payloads
- Data Providers for data-driven testing
- Positive and negative test scenarios
- Clean separation of concerns
- Scalable and maintainable test structure

---

##  Project Structure
src
├── main
│ └── java
│ └── com.alex.api
│ ├── base
│ │ └── BaseRequest.java
│ ├── models
│ │ ├── UserModel.java
│ │ ├── AddressModel.java
│ │ ├── PostModel.java
│ │ └── PatchPostModel.java
│ └── payload
│ └── PostPayloadBuilder.java
│
└── test
└── java
└── com.alex.api
├── dataprovider
│ ├── PostDataProvider.java
│ ├── PostPojoDataProvider.java
│ ├── PutPojoDataProvider.java
│ └── PatchPojoDataProvider.java
└── test
├── GetUserTest.java
├── PostUserTest.java
├── PutUserTest.java
├── PatchUserTest.java
└── DeleteUserTest.java


---

##  API Coverage

| HTTP Method | Endpoint  | Description |
|------------|-----------|-------------|
| GET        | /users    | Retrieve users |
| POST       | /posts    | Create post |
| PUT        | /posts/{id} | Full update |
| PATCH      | /posts/{id} | Partial update |
| DELETE     | /posts/{id} | Delete resource |

---

##  Testing Approach

- **POJO Models** are used to represent request bodies.
- REST Assured automatically serializes POJOs into JSON.
- **DataProviders** are used to execute the same test with multiple datasets.
- **PayloadBuilder** is used for:
  - dynamic payload generation
  - negative test scenarios
  - malformed JSON tests

---

## Data-Driven Testing

This framework uses TestNG DataProviders to execute the same test multiple times with different data sets.

Example:
- Creating multiple posts using a single test method
- Updating users with different data using PUT
- Partial updates using PATCH with POJO models

This approach reduces code duplication and improves scalability.

##  Prerequisites

To run this project, you need:

- Java 11 or higher
- Maven 3.8+
- Git
- Internet connection (JSONPlaceholder is a public API)


##  How to Run Tests

Run all tests:
```bash
mvn clean test

## Run a specific test class

```bash
mvn -Dtest=PostUserTest test

## Run a specific method

mvn -Dtest=PostUserTest#createPost_WithPojo_ShouldReturn201 test






