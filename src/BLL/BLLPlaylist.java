/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEPlaylist;
import DALC.DalcPlaylist;
import DALC.DalcSong;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christopher, Mark, Martin & Rasmus
 */
public class BLLPlaylist {

    DalcPlaylist ds;
    ArrayList<BEPlaylist> m_songs;

    public BLLPlaylist() {
        ds = DalcPlaylist.getInstance();
        m_songs = null;
    }

    
     /**
     * 
     * @return all playlists
     */
    public ArrayList<BEPlaylist> getAllPlaylists() throws Exception {
        try {
            if (m_songs == null)
                m_songs = ds.getAllPlaylists();
            return m_songs;
        } catch (SQLException ex) {
            throw new Exception("Kunne ikke hente alle sange..."); 
        }  
    }
    
     /**
     * 
     * @void delete a playlist
     */
    public void deletePlaylist(BEPlaylist aPlaylist) throws Exception {
        try {
            ds.deletePlaylist(aPlaylist);
        } catch (SQLException ex) {
          throw new Exception("kunne ikke slette playlist " + aPlaylist.getId());
        }
    }
    
     /**
     * 
     * @void create a playlist
     */
    public void createPlaylist(BEPlaylist aPlaylist) throws Exception {
        try {
            ds.createPlaylist(aPlaylist);
        } catch (SQLException ex) {
          throw new Exception("kunne ikke oprette playlist " + aPlaylist.getName());
        }
    }
    
     /**
     * 
     * @void delete a song from a playlist
     */
    public void deleteFromPlaylist(BEPlaylist aPlaylist) throws Exception {
        try {
            ds.deleteFromPlaylist(aPlaylist);
        } catch (SQLException ex) {
          throw new Exception("kunne ikke slette sangen fra playlisten " + aPlaylist.getName());
        }
    }
    
    
}