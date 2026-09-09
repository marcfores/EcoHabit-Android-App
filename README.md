# EcoHabit: Sustainable Habit Tracker

An Android application developed in Java designed to help users build and track eco-friendly daily habits. 

Created as a final project for the *Applications and Usability* course, the app focuses on delivering a clean, intuitive, and engaging User Experience (UX) while maintaining a robust underlying architecture.

## Key Features & UX/UI Design

*   **Local Data Persistence:** Utilizes `SharedPreferences` to save user habits offline without requiring an internet connection.
*   **Dynamic Habit Management:** Users can add custom habits via floating action buttons (FAB) and modal dialogues, check them as completed, or delete them.
*   **Interactive UI:** Implements smooth declarative animations (`ObjectAnimator`) when checking habits, creating a rewarding experience.
*   **Forgiving Interactions:** Features a `Snackbar` undo mechanism when a habit is deleted, adhering to usability best practices.
*   **Pre-loaded Content:** Includes default sustainable habits (e.g., reducing plastic, shorter showers) to guide new users.

## Technical Architecture

The application is structured using a modular **Model-View-Adapter (MVA)** pattern to ensure clean, scalable, and maintainable code:

*   **`Habit.java` (Model):** Represents the data structure (name and completion status).
*   **`HabitAdapter.java` (Adapter):** Acts as the bridge between data and the UI, managing the `RecyclerView` and handling state changes and local persistence updates.
*   **`MainActivity.java` (View):** Serves as the main entry point, managing the core UI components and handling the floating action button interactions.

## Repository Structure
*   `/app`: Main application source code (`src/main/java`), resources (XML layouts, drawable icons), and `build.gradle` configuration.
*   `/doc`: Contains the final academic report detailing the development methodology and UX considerations.

## Tech Stack
*   **Platform:** Android Studio
*   **Language:** Java
*   **Core Components:** `RecyclerView`, `SharedPreferences`, `AlertDialog`, `Snackbar`, `ObjectAnimator`.
