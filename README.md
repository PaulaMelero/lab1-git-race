# Lab 1 Git Race

Individual starter for Web Engineering 2026–27. Stack matches the group project: **Java 25 LTS**, **Kotlin 2.4.0**, **Spring Boot 4.1.0**, **Gradle 9.6.0**, **Bootstrap 5.3.8**.

The assignment, AI rules, and deadline are in [`docs/GUIDE.md`](docs/GUIDE.md). Fill [`REPORT.md`](REPORT.md) before you submit. Delivery is the Moodle zip only (`docs/GUIDE.md`).

## Increment

Added an greeting request history feature. It records non-blank greeting interactions (name and timestamp), displays recent entries dynamically on the web UI via Thymeleaf, and exposes the history data through a dedicated REST API endpoint at `/api/history`.

## Run

Java 25 is required (`./gradlew` uses the wrapper). GitHub Codespaces is optional (`docs/GUIDE.md`). Clone this course repository; you do not fork it to submit.

```bash
git clone https://github.com/UNIZAR-30246-WebEngineering/lab1-git-race.git
cd lab1-git-race
./gradlew check
./gradlew bootRun
```

- UI: <http://localhost:8080>
- JSON: <http://localhost:8080/api/hello>
- Health: <http://localhost:8080/actuator/health>
- JSON History: http://localhost:8080/api/history

```bash
./gradlew test
./gradlew test --tests "HelloControllerUnitTests"
```

## Layout

```
src/main/kotlin/HelloWorld.kt          # class Application
src/main/kotlin/service/HistoryService.kt
src/main/kotlin/controller/HelloController.kt
src/main/kotlin/controller/HistoryApiController.kt
src/main/resources/templates/welcome.html
src/test/kotlin/controller/HelloControllerUnitTests.kt
src/test/kotlin/controller/HelloControllerMVCTests.kt
src/tst/kotlin/controller/HistoryApiControllerTest.kt
src/test/kotlin/controller/HistoryServiceTest.kt
src/test/kotlin/IntegrationTest.kt
```

## License

MIT — see `LICENSE`.
