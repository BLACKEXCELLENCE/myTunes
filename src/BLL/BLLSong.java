/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BESong;
import DALC.DalcSong;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Christopher, Mark, Martin & Rasmus
 */
public class BLLSong {

    DalcSong ds;
    ArrayList<BESong> m_songs;

    /**
     *
     */
    public BLLSong() {
        ds = DalcSong.getInstance();
        m_songs = null;
    }

    
     /**
     * 
     * @return all songs
     * @throws java.lang.Exception
     */
    public ArrayList<BESong> getAll() throws Exception {
        try {
            if (m_songs == null)
                m_songs = ds.getAll();
            return m_songs;
        } catch (SQLException ex) {
            throw new Exception("Kunne ikke hente alle sange..."); 
        }
        
        
    }
    
     /**
     * 
     * @param searchWord
     * @return all songs from a playlist
     * @throws java.lang.Exception
     */
    public ArrayList<BESong> getAllByPlaylist(int searchWord) throws Exception {
        try {
            if (m_songs == null)
                m_songs = ds.getAllByPlaylist(searchWord);
            return m_songs;
        } catch (SQLException ex) {
            throw new Exception("Kunne ikke hente alle sange..."); 
        }
    } 

     /**
     * 
     * @param aSong
     * @throws java.lang.Exception
     * @void create a new song
     */
    public void createSong(BESong aSong) throws Exception {
        try {
            ds.createSong(aSong);
            m_songs.add(aSong);
        } catch (SQLException ex) {
          throw new Exception("kunne ikke oprette en sangen " + aSong.getTitle());
        }
    }
    
     /**
     * 
     * @param aSong
     * @throws java.lang.Exception
     * @void delete a song
     */
        public void deleteSong(BESong aSong) throws Exception {
        try {
            ds.deleteSong(aSong);
        } catch (SQLException ex) {
          throw new Exception("kunne ikke oprette en sangen " + aSong.getId());
        }
    }
    
    /**
     * 
     * @param searchWord
     * @return search songs
     * @throws java.lang.Exception
     */
    public ArrayList<BESong> searchSongs(String searchWord) throws Exception {
        try {
            if (m_songs == null)
                m_songs = ds.searchSongs(searchWord);
            return m_songs;
        } catch (SQLException ex) {
            throw new Exception("Kunne ikke hente sang..."); 
        }
    }
}