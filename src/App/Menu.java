/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import BE.*;
import BLL.*;
import java.util.Scanner;

/**
 *
 * @author Christopher, Mark, Martin & Rasmus
 */
public class Menu {

    boolean running = true;

    private static Menu m_instance;

    /**
     *
     * @return m_instance
     */
    static public Menu getInstance() {
        if (m_instance == null) {
            m_instance = new Menu();
        }
        return m_instance;
    }

    enum MenuItem {

        SONGS("|Songs             |"),
        PLAYLISTS("|Playlists         |"),
        PLAY_SONGS("|Play songs        |"),
        QUIT("|Quit              |");

        private final String description;

        MenuItem(String name) {
            this.description = name;
        }

        public String getDescription() {
            return description;
        }
    };

    /**
     *  Prints out the main menu.
     */
    private void show() {
        int count = 0;
        System.out.println("          MyTunes   ");
        System.out.println("        Version 1.0 ");
        System.out.println("   --------------------");
        for (MenuItem mi : MenuItem.values()) {
            count++;
            System.out.println("" + count + ". " + mi.getDescription());
        }
        int choice = 0;
        do {
            choice = new Scanner(System.in).nextInt();
            if (choice < 1 || choice > count) {
                System.out.println("Wrong input - try again...");
            } else {
                break;
            }

        } while (true);
        executeMenu(MenuItem.values()[choice - 1]);

    }

    /**
     * Prints out the song menu executes the menu
     */
    private void songMenu() {
        System.out.println("       Song Menu       ");
        System.out.println("1. |Show all songs    |");
        System.out.println("2. |Search songs      |");
        System.out.println("3. |Edit songs        |");
        System.out.println("0. |Return            |");
        int option;
        option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                showSongs();
                break;
            case 2:
                searchSongs();
                break;
            case 3:
                songEditMenu();
                break;
            case 0:
                show();
                break;
        }
    }

    /**
     * Prints out the song edit menu and executes the menu
     */
    private void songEditMenu() {
        System.out.println("     Edit Menu    ");
        System.out.println("1. |Create a song|");
        System.out.println("2. |Delete a song|");
        System.out.println("0. |Return|");
        int option;
        option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                createSong();
                break;
            case 2:
                deleteSong();
                break;
            case 0:
                songMenu();
                break;
        }
    }

    /**
     * Prints out the playlist menu and executes the menu
     */
    private void playlistMenu() {
        System.out.println("           Playlists Menu          ");
        System.out.println("1. |Show all playlists            |");
        System.out.println("2. |Show songs in a playlist      |");
        System.out.println("3. |Edit playlists                |");
        System.out.println("0. |Return                        |");
        int option;
        option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                showPlaylists();
                break;
            case 2:
                getAllByPlaylist();
                break;
            case 3:
                playlistEditMenu();
                break;
            case 0:
                show();
                break;
        }
    }

    /**
     * Prints out the playlist edit menu executes the menu
     */
    private void playlistEditMenu() {
        System.out.println("             Edit Menu             ");
        System.out.println("1. |Create a playlist             |");
        System.out.println("2. |Add song to a playlist        |");
        System.out.println("3. |Delete a playlist             |");
        System.out.println("4. |Delete a song from a playlist |");
        System.out.println("0. |Return                        |");
        int option;
        option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1:
                createPlaylist();
                break;
            case 2:
                addSongToPlaylist();
                break;
            case 3:
                deletePlaylist();
                break;
            case 4:
                deleteFromPlaylist();
                break;
            case 0:
                playlistMenu();
                break;
        }
    }

    /**
     * Executes the main menu
     */
    private void executeMenu(MenuItem value) {

        switch (value) {
            case SONGS:
                songMenu();
                break;
            case PLAYLISTS:
                playlistMenu();
                break;
            case PLAY_SONGS:
                soundControl();
                break;
            case QUIT:
                quit();
                break;
            default:
                break;
        }
    }

    /**
     * Prints out all songs
     */
    public void showSongs() {

        try {
            BLLSong ds = new BLLSong();
            System.out.println("All songs: ");
            for (BESong aSong : ds.getAll()) {
                System.out.println(aSong.getId() + ": " + aSong.getTitle() + ": " + aSong.getArtist() + " - Duration: " + aSong.getLength() + " - " + aSong.getCategoryId());
            }
            System.out.println("(" + ds.getAll().size() + " songs)");
            System.out.println("");
        } catch (Exception ex) {
            System.out.println("Could not get songs - " + ex.getMessage());
        }

    }

    /**
     *
     * Prints out all playlists
     */
    private void showPlaylists() {
        try {
            BLLPlaylist ds = new BLLPlaylist();
            System.out.println("Playlists: ");
            for (BEPlaylist aSong : ds.getAllPlaylists()) {
                System.out.println(aSong.getId() + ": " + aSong.getName());
            }
            System.out.println("(" + ds.getAllPlaylists().size() + " playlists)");
            System.out.println("");
        } catch (Exception ex) {
            System.out.println("Could not get playlists - " + ex.getMessage());
        }
    }

    /**
     * Prints out all songs in a playlist
     */
    private void getAllByPlaylist() {
        try {
            int searchWord;
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Choose one of your ");
            showPlaylists();
            searchWord = keyboard.nextInt();
            BLLSong ds = new BLLSong();
            System.out.println("Desired playlist: ");
            for (BESong aSong : ds.getAllByPlaylist(searchWord)) {
                System.out.println(aSong.getId() + ": " + aSong.getTitle() + ": " + aSong.getArtist());
            }
            System.out.println(" ");
        } catch (Exception ex) {
            System.out.println("Could not get songs - " + ex.getMessage());
        }
    }

    /**
     * Quits the application
     */
    private void quit() {
        running = false;
    }

    /**
     * Starts the application
     */
    public void run() {
        while (running) {
            show();
        }
    }

    /**
     * Create a new song
     */
    private void createSong() {
        try {
            String title;
            String artist;
            String categoryId;
            String filename;
            System.out.println("Enter title:        ");
            title = Keyboard.readString();
            System.out.println("Enter artist:       ");
            artist = Keyboard.readString();
            System.out.println("Enter category:     ");
            categoryId = Keyboard.readString();
            System.out.println("Enter file name:    ");
            filename = Keyboard.readString();

            BESong aSong = new BESong(0, title, artist, categoryId, filename);

            BLLSong ds = new BLLSong();
            ds.createSong(aSong);
        } catch (Exception ex) {
            System.out.println("Could not insert song - " + ex.getMessage());
        }
    }

    /**
     * Starts the sound control menu
     */
    static void soundControl() {
        new SoundControlMenu().run();
    }

    /**
     * Delete a song
     */
    private void deleteSong() {
        try {
            int id;
            System.out.println("Enter id:    ");
            id = Keyboard.readInt();
            BESong aSong = new BESong(id);
            BLLSong ds = new BLLSong();
            ds.deleteSong(aSong);

        } catch (Exception ex) {
            System.out.println("Could not delete song - " + ex.getMessage());
        }
    }

    /**
     * Search all songs
     */
    private void searchSongs() {
        try {
            String searchWord;
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter search word, either artist or title:  ");
            searchWord = keyboard.nextLine();
            BLLSong ds = new BLLSong();
            System.out.println("Searched song(s): ");
            for (BESong aSong : ds.searchSongs(searchWord)) {
                System.out.println(aSong.getId() + ": " + aSong.getTitle() + ": " + aSong.getArtist());
            }
            System.out.println(" ");
        } catch (Exception ex) {
            System.out.println("Could not get songs - " + ex.getMessage());
        }
    }

    /**
     * Delete a playlist
     */
    private void deletePlaylist() {
        try {
            int id;
            System.out.println("Enter id:    ");
            showPlaylists();
            id = Keyboard.readInt();

            BEPlaylist aPlaylist = new BEPlaylist(id);
            BLLPlaylist ds = new BLLPlaylist();
            ds.deletePlaylist(aPlaylist);

        } catch (Exception ex) {
            System.out.println("Could not delete playlist - " + ex.getMessage());
        }
    }

    /**
     * Create a playlist
     */
    private void createPlaylist() {
        try {
            String name;
            System.out.println("Enter name:    ");
            name = Keyboard.readString();

            BEPlaylist aPlaylist = new BEPlaylist(name);
            BLLPlaylist ds = new BLLPlaylist();
            ds.createPlaylist(aPlaylist);

        } catch (Exception ex) {
            System.out.println("Could not create playlist - " + ex.getMessage());
        }
    }

    /**
     * Delete a song from a playlist
     */
    private void deleteFromPlaylist() {
        try {
            int songid;
            int id;
            showPlaylists();
            System.out.println("Enter id of playlist to delete song from:   ");
            id = Keyboard.readInt();
            getAllByPlaylist();
            System.out.println("Enter id of song to delete:    ");
            songid = Keyboard.readInt();

            BEPlaylist aPlaylist = new BEPlaylist(id, songid);
            BLLPlaylist ds = new BLLPlaylist();
            ds.deleteFromPlaylist(aPlaylist);

        } catch (Exception ex) {
            System.out.println("Could not delete song from playlist - " + ex.getMessage());
        }
    }

    /**
     * Add a song to a playlist
     */
    private void addSongToPlaylist() {
        try {
            int playlistid;
            int songID;
            System.out.println("Enter Playlist ID:    ");
            showPlaylists();
            playlistid = Keyboard.readInt();
            System.out.println("Enter Song ID:   ");
            showSongs();
            songID = Keyboard.readInt();

            BEPlaylistSong aPlaylist = new BEPlaylistSong(playlistid, songID);
            BLLPlaylist ds = new BLLPlaylist();
            ds.addSongToPlaylist(aPlaylist);

        } catch (Exception ex) {
            System.out.println("Could not insert song into playlist - " + ex.getMessage());
        }
    }
}
