# TodosNative

TodosNative is a simple Android application for managing a list of to-do items. 
The app is built using Kotlin, Jetpack Compose, and Dagger Hilt for dependency injection.

## Features

- View a list of to-do items
- View details of a to-do item
- Mark to-do items as completed

## Getting Started

### Prerequisites

- Android Studio Ladybug | 2024.2.1 Patch 3
- Kotlin 1.8 or higher
- Gradle 7.0 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/todosnative.git
    ```
2. Open the project in Android Studio.
3. Build the project to download dependencies and set up the environment.

### Running the App

1. Connect an Android device or start an emulator.
2. Click the "Run" button in Android Studio or use the following command:
    ```sh
    ./gradlew installDebug
    ```

## Project Structure

- `app/src/main/java/com/sample/todosnative/`: Contains the main application code.
- `app/src/main/res/`: Contains the resource files (layouts, strings, etc.).
- `app/src/main/AndroidManifest.xml`: The manifest file for the application.

## Dependencies

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.