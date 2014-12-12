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
public class BEPlaylist {

    private int id;
    private String name;
    private int songid;
    private int playlistid;

    /**
     *
     * @param id
     * @param name
     */
    public BEPlaylist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @param id
     */
    public BEPlaylist(int id) {
        this.id = id;
    }

    /**
     *
     * @param name
     */
    public BEPlaylist(String name) {
        this.name = name;
    }

    /**
     *
     * @param id
     * @param songid
     */
    public BEPlaylist(int id, int songid) {
        this.id = id;
        this.songid = songid;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

}
