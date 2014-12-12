package DALC;

import BE.BESong;
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
public class DalcSong {

    Connection mConnection;

    private static DalcSong m_instance;

    /**
     *
     * @return
     */
    static public DalcSong getInstance() {
        if (m_instance == null) {
            m_instance = new DalcSong();
        }
        return m_instance;
    }

    /**
     *
     */
    public DalcSong() {
        try {
            mConnection = DBConnection.getConnection();
        } catch (SQLServerException ex) {
        }
    }

    /**
     * @return all the songs
     * @throws java.sql.SQLException
     */
    public ArrayList<BESong> getAll() throws SQLException {
        ArrayList<BESong> result = new ArrayList<BESong>();

        Statement stm = DBConnection.getConnection().createStatement();
        stm.execute("select * from song inner join Artist on Song.artistID = artist.id inner join Category on Category.ID = song.CategoryID");
        ResultSet res = stm.getResultSet();
        while (res.next()) {
            int id = res.getInt("ID");
            String title = res.getString("Title");
            String artist = res.getString("Name");
            String categoryID = res.getString("Category");
            String filename = res.getString("FileName");

            result.add(new BESong(id, title, artist, categoryID, filename));
        }

        return result;
    }

    /**
     *
     * @param id
     * @return the song with the id as [id]
     * @throws java.sql.SQLException
     */
    public BESong getById(int id) throws SQLException {
        for (BESong aSong : getAll()) {
            if (aSong.getId() == id) {
                return aSong;
            }
        }
        return null;
    }

    /**
     * @param searchWord
     * @return all the songs at the playlist [pl]
     * @throws java.sql.SQLException
     */
    public ArrayList<BESong> getAllByPlaylist(int searchWord) throws SQLException {
        ArrayList<BESong> result = new ArrayList<BESong>();

        Statement stm = DBConnection.getConnection().createStatement();
        stm.execute("select * from Song inner join PlayListSong on PlayListSong.SongID = Song.ID inner join Artist on Artist.ID = Song.artistID where PlayListID = " + searchWord);
        ResultSet res = stm.getResultSet();
        while (res.next()) {
            int id = res.getInt("ID");
            String title = res.getString("Title");
            String artist = res.getString("Name");

            result.add(new BESong(id, title, artist));
        }

        return result;
    }

    /**
     * @param s
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException
     * @throws java.sql.SQLException
     * @void create a song
     */
    public void createSong(BESong s) throws SQLServerException, SQLException {
        String sql = "insert into Song values (?,?,?,?)";

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, s.getTitle());
        ps.setString(2, s.getArtist());
        ps.setString(3, s.getCategoryId());
        ps.setString(4, s.getFilename());

        ps.executeUpdate();
    }

    /**
     * @param s
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException
     * @throws java.sql.SQLException
     * @void delete a song
     */
    public void deleteSong(BESong s) throws SQLServerException, SQLException {
        String sql = ("delete from PlayListSong where songid = ? delete from Song where ID = ?");

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, s.getId());
        ps.setInt(2, s.getId());

        ps.executeUpdate();
    }

    /**
     * @param searchWord
     * @return search all songs
     * @throws java.sql.SQLException
     */
    public ArrayList<BESong> searchSongs(String searchWord) throws SQLException {
        ArrayList<BESong> result = new ArrayList<BESong>();

        Statement stm = DBConnection.getConnection().createStatement();
        stm.execute("select * from Song inner join Artist on Artist.ID = Song.ArtistID "
                + "where Name like '%" + searchWord + "%' or Title like '%" + searchWord + "%'");
        ResultSet res = stm.getResultSet();
        while (res.next()) {
            int id = res.getInt("ID");
            String title = res.getString("Title");
            String artist = res.getString("Name");

            result.add(new BESong(id, title, artist));
        }

        return result;
    }
}
