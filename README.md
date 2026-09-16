# FitCollectMobile

FitCollectMobile is the companion Android application for [FitCollectSmartwatch](https://github.com/emanueldias/FitCollectSmartwatch). It serves as a central hub for receiving, storing, and analyzing workout data collected from Wear OS devices, providing users with a detailed view of their physical activities.

## Features

- **Wear OS Synchronization**: Seamlessly receives workout logs from the smartwatch via the Wear OS Data Layer API.
- **Workout History**: A comprehensive list of all synchronized workout collections.
- **Detailed Analysis**: In-depth view of each workout session, including:
    - Activity type and duration.
    - Calories burned and distance covered.
    - Heart rate logs and time-stamped metrics.
- **Persistent Storage**: Saves workout data locally as structured JSON files for offline access and data portability.
- **Real-time Updates**: Automatically refreshes the UI when new data arrives from the watch.

## Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose (Material 3)](https://developer.android.com/compose)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Synchronization**: [Wear OS Data Layer API](https://developer.android.com/training/wearables/data-layer)
- **Serialization**: [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) for JSON processing.
- **Asynchronous Work**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & Flow.
- **Navigation**: Jetpack Compose Navigation.

## Project Structure

- `data/`:
    - `model/`: Data classes representing workout metrics (e.g., `WorkoutData`).
    - `repository/`: Handles data persistence using internal file storage.
- `service/`: Contains `WearDataListenerService`, which listens for incoming data events from the Wear OS network.
- `ui/`:
    - `features/`: Composable screens and ViewModels for workout listing and details.
    - `components/`: Reusable UI elements.
    - `theme/`: Material 3 design system configuration.

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/emanueldias/FitCollectMobile.git
   ```
2. **Open in Android Studio**:
   Import the project and wait for Gradle sync.
3. **Smartwatch Pairing**:
   Ensure your Wear OS device is paired with the phone and has [FitCollectSmartwatch](https://github.com/emanueldias/FitCollectSmartwatch) installed.
4. **Run**:
   Deploy to an Android smartphone (API 24+).

## Data Synchronization

The application utilizes the `WearableListenerService` to monitor the `/workout/` path on the Data Layer. When a workout session is completed on the watch, the data is serialized into JSON and sent to the mobile app, where it is automatically saved and indexed in the local repository.
