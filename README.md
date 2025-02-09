# Network Sound Alert

## Overview

`Network Sound Alert` is a Java-based application that continuously monitors your internet connection and plays a specific sound through the **Realtek(R) Audio** device when the network becomes unavailable. This can be useful for users who want a loud alert in case of a network outage, especially in environments where reliable connectivity is critical.

## Features

- Monitors internet connectivity by attempting to reach Google's DNS server (`8.8.8.8`).
- Plays a custom sound file through the **Realtek(R) Audio** device when the network is not available.
- Continues checking the network status every 60 seconds and plays the alert if necessary.

## Requirements

- Java 8 or later.
- A sound file in `.wav` format (included in the project as `sound.wav`).
- A **Realtek(R) Audio** device installed on the system for sound output.
- An active internet connection to verify connectivity.

## Installation and Setup

1. **Clone the repository** to your local machine:

   ```bash
   git clone https://github.com/yourusername/NetworkSoundAlert.git
2. ```bash
   cd NetworkSoundAlert   
3. **Place your sound file** in the project’s root directory. The default file name is `aaaa-za-donbass.wav`. You can change the file name in the code if necessary.

4. **Build the project**:

   If you're using a build tool like Maven or Gradle, build the project accordingly. Otherwise, you can compile the project using the following command:

   ```bash
   javac -d out/production/NetworkSoundAlert src/NetworkSoundAlert.java   