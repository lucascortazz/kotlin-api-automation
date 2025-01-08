# kotlin-api-automation

This is a Kotlin-based script that interacts with a RESTful API to fetch, filter, and display user data based on specific criteria. The script was developed as part of the automation challenge and utilizes the Ktor library for HTTP requests and JSON handling.

---

## Features

- Fetches user data from a public API ([JSONPlaceholder](https://jsonplaceholder.typicode.com/)).
- Filters users based on their city (e.g., "Gwenborough").
- Displays the filtered user data in a readable format.
- Handles errors gracefully (e.g., network issues, invalid API responses).

---

## Requirements

- Kotlin 1.8 or later
- Java 11 or later
- Gradle (Bundled with the project)

---

## Dependencies

The project uses the following dependencies:
- `io.ktor:ktor-client-core:2.3.5`
- `io.ktor:ktor-client-cio:2.3.5`
- `io.ktor:ktor-client-content-negotiation:2.3.5`
- `io.ktor:ktor-serialization-kotlinx-json:2.3.5`
- `org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1`

All dependencies are managed via Gradle.

---

## How to Run

### 1. Clone the Repository
git clone <repository-url>

cd <repository-name>

### 2. Run the code
./gradlew build

./gradlew run

