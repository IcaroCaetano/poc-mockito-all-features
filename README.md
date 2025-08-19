# Mockito Proof of Concept (POC)

This project is a **Proof of Concept (POC)** demonstrating the capabilities of **Mockito** using **Java 17** and **Maven**.

## Overview

Mockito is one of the most widely used mocking frameworks for unit testing in Java. It allows developers to:

- Create mock objects for dependencies.
- Stub method calls to return predefined results.
- Verify interactions between objects.
- Use argument matchers for flexible testing.
- Perform partial mocking (spies).
- Mock static methods and final classes (with `mockito-inline`).
- Capture arguments for deeper assertions.
- Use annotations to reduce boilerplate setup.

This POC showcases all these features in simple examples.

---

## Features Demonstrated

### 1. **Basic Mocking**

- Create mock objects with `Mockito.mock()`.
- Stub methods to return custom responses.

### 2. **Annotations Support**

- Use `@Mock`, `@InjectMocks`, and `@Captor` annotations.
- Initialize mocks using `MockitoAnnotations.openMocks(this)`.

### 3. **Stubbing Methods**

- Define return values for specific method calls using `when(...).thenReturn(...)`.
- Throw exceptions using `thenThrow(...)`.

### 4. **Verification**

- Verify interactions with mocks using `verify(...)`.
- Ensure no unexpected interactions with `verifyNoMoreInteractions(...)`.

### 5. **Argument Matchers**

- Use `any()`, `eq()`, and other matchers.
- Combine matchers with specific values.

### 6. **Spies (Partial Mocks)**

- Wrap real objects with `Mockito.spy()`.
- Override selected behavior while keeping original logic.

### 7. **Argument Captors**

- Capture arguments passed to methods with `@Captor` or `ArgumentCaptor.forClass()`.

### 8. **Mocking Static Methods**

- Use `mockito-inline` to mock static methods.
- Demonstrate usage with `mockStatic(...)`.

### 9. **Mocking Final Classes and Methods**

- Enable mocking of final classes with `mockito-inline`.

### 10. **Behavior Verification with Timeouts**

- Verify method calls within a specific time limit using `verify(..., timeout(...))`.

### 11. **BDD Style Testing**

- Use `given(...)` and `then(...)` for Behavior Driven Development (BDD) style tests.

### 12. **Deep Stubs**

- Use deep stubs for chained method calls (e.g., `when(mock.getSubObject().getValue())`).

### 13. **Strictness and Unnecessary Stubs Detection**

- Configure Mockito to detect unused stubs and enforce strict interaction checks.

---

## Tech Stack

- **Java 17**
- **Maven**
- **JUnit 5**
- **Mockito Core**
- **Mockito Inline**

---

## How to Run

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd mockito-poc
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the tests:

   ```bash
   mvn test
   ```

---

## References

- [Mockito Official Site](https://site.mockito.org/)
- [Mockito GitHub](https://github.com/mockito/mockito)
- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)

