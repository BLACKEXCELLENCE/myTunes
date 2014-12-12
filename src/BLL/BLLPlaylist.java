/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEPlaylist;
import BE.BEPlaylistSong;
import DALC.DalcPlaylist;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Christopher, Mark, Martin & Rasmus
 */
public class BLLPlaylist {

    DalcPlaylist ds;
    ArrayList<BEPlaylist> m_songs;

    /**
     *
     */
    public BLLPlaylist() {
        ds = DalcPlaylist.getInstance();
        m_songs = null;
    }

    /**
     *
     * @return all playlists
     * @throws java.lang.Exception
     */
    public ArrayList<BEPlaylist> getAllPlaylists() throws Exception {
        try {
            if (m_songs == null) {
                m_songs = ds.getAllPlaylists();
            }
            return m_songs;
        } catch (SQLException ex) {
            throw new Exception("Could not get songs...");
        }
    }

    /**
     *
     * @param aPlaylist
     * @throws java.lang.Exception
     * @void delete a playlist
     */
    public void deletePlaylist(BEPlaylist aPlaylist) throws Exception {
        try {
            ds.deletePlaylist(aPlaylist);
        } catch (SQLException ex) {
            throw new Exception("Could not get playlist " + aPlaylist.getId());
        }
    }

    /**
     *
     * @param aPlaylist
     * @throws java.lang.Exception
     * @void create a playlist
     */
    public void createPlaylist(BEPlaylist aPlaylist) throws Exception {
        try {
            ds.createPlaylist(aPlaylist);
        } catch (SQLException ex) {
            throw new Exception("Could not create playlist" + aPlaylist.getName());
        }
    }

    /**
     *
     * @param aPlaylist
     * @throws java.lang.Exception
     * @void delete a song from a playlist
     */
    public void deleteFromPlaylist(BEPlaylist aPlaylist) throws Exception {
        try {
            ds.deleteFromPlaylist(aPlaylist);
        } catch (SQLException ex) {
            throw new Exception("Could not delete the song from the playlist" + aPlaylist.getName());
        }
    }

    /**
     *
     * @param aPlaylist
     * @throws java.lang.Exception
     * @void add a song to a playlist
     */
    public void addSongToPlaylist(BEPlaylistSong aPlaylist) throws Exception {
        try {
            ds.addSongToPlaylist(aPlaylist);
        } catch (SQLException ex) {
            throw new Exception("Could not insert the song into the playlist" + aPlaylist.getSongid());
        }
    }

}
