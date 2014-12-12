package DALC;

import BE.BEPlaylist;
import BE.BEPlaylistSong;
import static DALC.DBConnection.getConnection;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Christopher, Mark, Martin & Rasmus
 */
public class DalcPlaylist {
    
    Connection mConnection;
    
    private static DalcPlaylist m_instance;
    
    /**
     *
     * @return
     */
    static public DalcPlaylist getInstance()
    {
        if (m_instance == null)
            m_instance = new DalcPlaylist();
        return m_instance;
    }
    
    private DalcPlaylist()
    {
        try {
            mConnection = DBConnection.getConnection();
        } catch (SQLServerException ex) {
        }
    }
    /**
     * 
     * @return all the playlists
     * @throws java.sql.SQLException
     */
    public ArrayList<BEPlaylist> getAllPlaylists() throws SQLException
    {   
        ArrayList<BEPlaylist> result = new ArrayList<BEPlaylist>();
      
            Statement stm = DBConnection.getConnection().createStatement();
            stm.execute("Select * From Playlist");
            ResultSet res = stm.getResultSet();
            while (res.next())
            {
             int id = res.getInt("ID");
             String name = res.getString("Name");
              result.add(new BEPlaylist(id, name));
            }
      
        return result;
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<BEPlaylist> getAllPlaylists2() throws SQLException
    { 
        Connection c = getConnection();
        try {
            Statement stm = c.createStatement();
            boolean status = stm.execute("select * from Playlist");
            if (status == false) {
                return null;
            }
            ResultSet res = stm.getResultSet();
 
            while (res.next()) {
                System.out.println(res.getString("Name"));
 
                int id = res.getInt("ID");
                Statement stm2 = c.createStatement();
                boolean status2 = stm2.execute("select * from Song inner join PlaylistSong on PlaylistSong.songID = Song.ID "
                        + "inner join Artist on Song.artistID = Artist.ID " +
                            "where playlistID = " + id);
                if (status2 == false) {
                    return null;
                }
 
                ResultSet res2 = stm2.getResultSet();
                DalcSong.getInstance().getAll();
            }
 
        } catch (SQLException ex) {
            System.out.println("Kunne ikke hente playlists.." + ex.getMessage());
            return null;
        }
        return null;
    }
    /**
     * 
     * @param id
     * @return the song with the id as [id] 
     * @throws java.sql.SQLException 
     */
    public BEPlaylist getById(int id)  throws SQLException
    { 
      for (BEPlaylist aSong : getAllPlaylists())
          if (aSong.getId() == id) return aSong; 
      return null;
    }
    
    /**
     * 
     * @param pl
     * @return all the songs at the playlist [pl] 
     */
    public ArrayList<BEPlaylist> getAllByPlaylist(BEPlaylist pl)
    {   return null;
    }   
    
    /**
     * 
     * @param s
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException
     * @throws java.sql.SQLException
     * @void delete a playlist
     */
    public void deletePlaylist(BEPlaylist s) throws SQLServerException, SQLException
    {   
        String sql = ("delete from PlayListSong where playlistid = ? delete from playlist where ID = ?");
        
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, s.getId());
        ps.setInt(2, s.getId());
 
        ps.executeUpdate();
    }
    
    /**
     * 
     * @param s
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException
     * @throws java.sql.SQLException
     * @void create a playlist
     */
    public void createPlaylist(BEPlaylist s) throws SQLServerException, SQLException
    {   
        String sql = ("insert into playlist (name) values (?)"); 
        
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, s.getName());
 
        ps.executeUpdate();
    }
    
    /**
     * 
     * @param s
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException
     * @throws java.sql.SQLException
     * @void delete a song from a playlist
     */
        public void deleteFromPlaylist(BEPlaylist s) throws SQLServerException, SQLException
    {   
        String sql = ("delete from PlaylistSong where playlistID = ? and SongID = ?"); 
        
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, s.getId());
        ps.setInt(2, s.getSongid());
 
        ps.executeUpdate();
    }
        
     /**
     * 
     * @param s
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException
     * @throws java.sql.SQLException
     * @void add a song to a playlist
     */
        public void addSongToPlaylist(BEPlaylistSong s) throws SQLServerException, SQLException
    {   
        String sql = ("INSERT INTO PlayListSong (PlayListID, SongID, SeqNo)"
                + "values (?, ?, (SELECT ISNULL(Max(SeqNo),0)+1 FROM [dbo].[PlayListSong] WHERE PlayListID=?))"); 
        
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, s.getPlaylistid());
        ps.setInt(2, s.getSongid());
        ps.setInt(3, s.getPlaylistid());
 
        ps.executeUpdate();
    }
}