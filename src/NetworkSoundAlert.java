import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import javax.sound.sampled.*;

public class NetworkSoundAlert {
    public static void main(String[] args) {
        String soundFilePath = "sound.wav";

        while (true) {
            // Checking network connection
            if (!isInternetAvailable()) {
                System.out.println("Network not found! Playing sound...");

                // Playing sound through Realtek(R) Audio
                playSoundThroughRealtek(soundFilePath);
            } else {
                System.out.println("Network found.");
            }

            // Waiting before the next check (60 seconds)
            try {
                Thread.sleep(60000); // 60000 milliseconds = 60 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to check internet connectivity
    public static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("8.8.8.8");
            boolean reachable = address.isReachable(5000); // Trying to reach within 5 seconds
            System.out.println("Network check result: " + (reachable ? "Network available" : "Network unavailable"));
            return reachable;
        } catch (IOException e) {
            System.out.println("Error while checking network.");
            e.printStackTrace();
            return false;
        }
    }

    // Method to play sound through Realtek(R) Audio device
    public static void playSoundThroughRealtek(String soundFilePath) {
        try {
            // Checking if the file exists
            File soundFile = new File(soundFilePath);
            if (!soundFile.exists()) {
                System.out.println("File not found: " + soundFilePath);
                return;
            }

            // Finding a suitable audio device (excluding "Port" devices)
            Mixer.Info[] mixers = AudioSystem.getMixerInfo();
            Mixer targetMixer = null;

            for (Mixer.Info mixerInfo : mixers) {
                System.out.println("Found device: " + mixerInfo.getName());
                if (!mixerInfo.getName().contains("Port") && mixerInfo.getName().contains("Realtek")) {
                    targetMixer = AudioSystem.getMixer(mixerInfo);
                    System.out.println("Using device: " + mixerInfo.getName());
                    break;
                }
            }

            if (targetMixer == null) {
                System.out.println("Suitable Realtek(R) Audio device not found.");
                return;
            }

            // Loading audio stream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Getting Clip from the selected device (Mixer)
            Clip clip = (Clip) targetMixer.getLine(new DataLine.Info(Clip.class, audioStream.getFormat()));
            clip.open(audioStream);
            clip.start();

            // Printing status
            System.out.println("Sound is playing through Realtek(R) Audio device...");

            // Waiting for the sound to finish playing
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            clip.close();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio file format.");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("Audio device problem.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error opening the file.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}