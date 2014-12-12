/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Christopher, Mark, Martin & Rasmus
 */
public class BEPlaylistSong {

    private int playlistid;
    private int songid;

    /**
     *
     * @param playlistid
     * @param songid
     */
    public BEPlaylistSong(int playlistid, int songid) {
        this.playlistid = playlistid;
        this.songid = songid;
    }

    /**
     * @return the playlistid
     */
    public int getPlaylistid() {
        return playlistid;
    }

    /**
     * @param playlistid the playlistid to set
     */
    public void setPlaylistid(int playlistid) {
        this.playlistid = playlistid;
    }

    /**
     * @return the songid
     */
    public int getSongid() {
        return songid;
    }

    /**
     * @param songid the songid to set
     */
    public void setSongid(int songid) {
        this.songid = songid;
    }
}
