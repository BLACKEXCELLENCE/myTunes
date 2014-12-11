package BLL;

import java.util.Scanner;

/**
 *
 * @author Christopher, Mark, Martin & Rasmus
 */
public class SoundControlMenu extends Menu {

    AudioPlayer p;
     String fileA = "";

    /**
     *
     */
    public SoundControlMenu() {
        super("      Sound Controls",
                "|Play              |",
                "|Pause             |",
                "|Resume            |",
                "|Stop              |",
                "|Now playing       |");
          }

     /**
     * @param option
     * @void prints the SoundControlMenu
     */
    @Override
    protected void doAction(int option)  {
        switch (option) {
            case 0:
                if (p != null) {
                    p.stop();
                }
                break;
            case 1:
                Scanner scanner = new Scanner(System.in);
                App.Menu.getInstance().showSongs();
                System.out.println("Enter song title:");
                String chooseSong = scanner.nextLine();
                fileA = chooseSong;
                chooseSong = chooseSong.replace(" ", "");
                if (p != null) {
                    p.stop();
                }
                p = new AudioPlayer(chooseSong + ".mp3");

                if (p.isValid() == false) {
                    System.out.println("The audio player is not working -- " + p.getErrorMessage());
                } else {
                    p.startToPlay();
                }
                break;
            case 2:
                if (p != null) {
                    p.pause();
                }
                break;
            case 3:
                if (p != null) {
                    p.resume();
                }
                break;
            case 4:
                if (p != null) {
                    p.stop();
                }
                break;
            case 5:
                if (p == null) {
                    System.out.println("No audio player exist...");
                } else if (p.isPlaying()) {
                    System.out.println("Now playing " + fileA);
                } else {
                    System.out.println("NOT playing...");
                }
        }
    }

}
