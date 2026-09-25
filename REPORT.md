# Lab 1 Git Race -- Project Report

## What I specified

Before writing any code, I thoroughly analyzed the stater repository structure
to understand the existing funcionality in order to decide what I wanted to implement.

I decided to implement a new feature that allows users to view the greetings history.
Some of the key specifications for this feature include:
- The history should be displayed in a table format, with each row representing a greeting.
- Non-blanck greetings should be displayed in the table, while blank greetings should be ignored.
- The main view must dinamically display the recent request log using Thymeleaf.

## What I changed

- **Created files:**
  - `src/main/kotlin/service/HistoryService.kt`: Service class managing the in-memory log entries.
  - `src/main/kotlin/controller/HistoryApiController.kt`: REST controller serving history data as JSON at `/api/history`.
  - `src/test/kotlin/controller/HistoryApiControllerTest.kt`: Unit tests for the history REST API.
  - `src/test/kotlin/service/HistoryServiceTest.kt`: Unit tests verifying thread safety and state handling of `HistoryService`.

- **Modified files:**
  - `src/main/kotlin/controller/HelloController.kt`: Injected `HistoryService` to store logs on form POST submissions and supply existing logs to the Thymeleaf model on GET requests.
  - `src/main/resources/templates/welcome.html`: Integrated a responsive Thymeleaf card displaying history logs conditionally and providing an API link.
  - `src/test/kotlin/controller/HelloControllerUnitTests.kt` & `HelloControllerMVCTests.kt`: Updated tests to include dependencies for `HistoryService`.
  - `src/test/kotlin/IntegrationTest.kt`: End-to-end testing to verify form submissions register in history and persist across navigation.

## Technical decisions

- **Form POST & PRG Pattern:** The starter originally used a single `GET` endpoint for greetings. I added a `POST /` endpoint to handle submissions when updating history, combined with the Post/Redirect/Get (PRG) pattern to prevent duplicate log entries upon refreshing the browser.
- **Thread Safety:** I rejected the Gemini's suggestion of a standard non-thread-safe `ArrayList`. Instead, I chose a thread-safe synchronized collection to prevent data corruption under concurrent HTTP requests so that the system could be able to grow and scale in an hypothetical situation.

## How I verified

I run the full check and build using Gradle:
./gradlew check
./gradlew bootRun

**Failures**: Upon the first execution of './gradlew check', several syntax errors occurred due to my lack of familiarity with Kotlin syntax. After correcting these errors and updateing existing test all chacks passed successfully.

**Manual Testing**: Tested form submissions manually at http://localhost:8080/, verifying that new names appeared in the Thymeleaf history section, and verified JSON output at http://localhost:8080/api/history.

## AI disclosure

- **Tools / skills:** Gemini (Google)
- **Purpose:** Assisting with Thymeleaf HTML layout for the history component and reviewing Spring Boot/Kotlin controller interaction.
- **Representative prompts:**
  - "How to display a list of strings in Thymeleaf?"
  - "Is it necessary to add an independent controller for the history API or would you integrate it into the existing controller?"
- **Affected files/sections:** src/main/resources/templates/welcome.html (history card section) and HelloController.kt.
- **Validation steps:** Executed `./gradlew check` to verify unit and integration tests, fixed Kotlin syntax errors, and manually verified UI and JSON API endpoints via `./gradlew bootRun`.
- **Citations:** None. No external code or documentation pages were directly adapted.
- **Human-reviewed:** I reviewed all generated code carefully. I rejected the suggested standard Array/List structure in favor of a thread-safe concurrent collection, ensuring that HTTP request handlers can safely mutate state under potential concurrent load.

