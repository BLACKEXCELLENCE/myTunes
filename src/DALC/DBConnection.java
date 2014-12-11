/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DALC;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Christopher
 */
public class DBConnection {
    
    private static Connection m_connection = null;
    
    /**
     *
     * @return
     * @throws SQLServerException
     */
    public static Connection getConnection() throws SQLServerException
    {
        if (m_connection == null) // first time
        {
            SQLServerDataSource ds = new SQLServerDataSource();
        
            ds.setApplicationName("jdbc:sqlserver://");
            //ds.setServerName("10.211.55.8");
            ds.setServerName("localhost");
            ds.setInstanceName("SQLEXPRESS");
            ds.setDatabaseName("myTunesV2");
            ds.setPortNumber(1433);
            ds.setUser("java");
            ds.setPassword("1234");
            m_connection = ds.getConnection();
        }
        return m_connection;

    }
}
