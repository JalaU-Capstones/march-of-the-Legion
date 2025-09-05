package university.jala.legion.util;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Handles the display of a startup banner and sound.
 */
public class StartupPresenter {

    private static final int BANNER_DURATION_MS = 7000;

    /**
     * Displays the ASCII banner, plays the background sound, and pauses.
     */
    public void showBannerAndPlaySound() {
        printBanner();
        playSound();
        try {
            Thread.sleep(BANNER_DURATION_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Prints the ASCII banner to the console.
     */
    public void printBanner() {
        System.out.println("\n\n");
        System.out.println("███╗   ███╗ █████╗ ██████╗  ██████╗██╗  ██╗    ██████╗ ███████╗    ████████╗██╗  ██╗███████╗    ██╗     ███████╗ ██████╗ ██╗ ██████╗ ███╗   ██╗");
        System.out.println("████╗ ████║██╔══██╗██╔══██╗██╔════╝██║  ██║    ██╔══██╗██╔════╝    ╚══██╔══╝██║  ██║██╔════╝    ██║     ██╔════╝██╔════╝ ██║██╔═══██╗████╗  ██║");
        System.out.println("██╔████╔██║███████║██████╔╝██║     ███████║    ██║  ██║█████╗         ██║   ███████║█████╗      ██║     █████╗  ██║  ███╗██║██║   ██║██╔██╗ ██║");
        System.out.println("██║╚██╔╝██║██╔══██║██╔══██╗██║     ██╔══██║    ██║  ██║██╔══╝         ██║   ██╔══██║██╔══╝      ██║     ██╔══╝  ██║   ██║██║██║   ██║██║╚██╗██║");
        System.out.println("██║ ╚═╝ ██║██║  ██║██║  ██║╚██████╗██║  ██║    ██████╔╝██║            ██║   ██║  ██║███████╗    ███████╗███████╗╚██████╔╝██║╚██████╔╝██║ ╚████║");
        System.out.println("╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝    ╚═════╝ ╚═╝            ╚═╝   ╚═╝  ╚═╝╚══════╝    ╚══════╝╚══════╝ ╚═════╝ ╚═╝ ╚═════╝ ╚═╝  ╚═══╝");
        System.out.println("                                                                                 ");
        System.out.println("                           March of the Legion by CodeWithBotina.                           ");
        System.out.println("\n\n");
    }

    private void playSound() {
        try {
            InputStream audioSrc = getClass().getResourceAsStream("/background.wav");
            if (audioSrc == null) {
                return; // Gracefully handle missing sound file
            }
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            // Gracefully handle any other sound errors without crashing
        }
    }
}
