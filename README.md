# Tlece Task App

Welcome to the README of this application built using MVVM architecture, Retrofit for network requests, Room Database for local storage, and Firebase Cloud Messaging (FCM) for push notifications.

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Setup Instructions](#setup-instructions)
5. [Usage](#usage)

## Overview

This Android application follows the MVVM (Model-View-ViewModel) architecture pattern, which separates the user interface logic from the business logic, making the codebase more maintainable and testable. Retrofit is used for making network requests to fetch data from a remote server, while Room Database is employed for local storage, allowing users to access content offline. Additionally, Firebase Cloud Messaging (FCM) is integrated to enable push notifications, enhancing user engagement and providing real-time updates.

## Features

- **MVVM Architecture**: Separation of concerns into three distinct layers - Model, View, and ViewModel.
- **Network Requests with Retrofit**: Utilization of Retrofit library to fetch JSON data from a remote URL.
- **Local Database with Room**: Implementation of Room Database for efficient storage and management of fetched information locally on the device.
- **Firebase Cloud Messaging (FCM)**: Integration of FCM to receive and display push notifications, ensuring timely delivery of updates and announcements.

## Technologies Used

- **Android SDK**: Development platform for building Android applications.
- **Kotlin**: Modern programming language used for Android app development.
- **MVVM Architecture**: Design pattern facilitating separation of concerns and modular code structure.
- **Retrofit**: Type-safe HTTP client for making network requests and handling RESTful APIs.
- **Room Database**: Persistence library providing an abstraction layer over SQLite for local data storage.
- **Firebase Cloud Messaging (FCM)**: Google's solution for delivering push notifications to Android devices.
- **Background Tasks with WorkManager**: Integration of WorkManager to perform background tasks.

## Setup Instructions

Follow these steps to set up and run the application locally on your development environment:

1. **Clone the Repository**: Open a terminal and run the following command to clone the repository to your local machine:
   ```bash
   git clone https://github.com/siamsaleh/TleceVideoApp.git
   ```

2. **Open in Android Studio**: Launch Android Studio and open the project by selecting the root directory of the cloned repository.

3. **Configure Firebase**: If Firebase Cloud Messaging (FCM) is not already set up for your project, follow the instructions provided by Firebase to add your app to the Firebase project and download the `google-services.json` configuration file. Place this file in the `app` directory of your project.

4. **Build and Run**: Build the project by selecting "Build" > "Make Project" from the menu bar. Then, run the app on an Android device or emulator by clicking the "Run" button or pressing Shift + F10.

## Usage

Once the app is installed and running on your device or emulator, you can perform the following actions:

1. **View Videos**: Navigate through the app's interface to browse available videos.
2. **Play Video**: Click on the thumbnail of a video to play it.
3. **Receive Notifications**: If enabled, receive push notifications for new content or updates via Firebase Cloud Messaging.
