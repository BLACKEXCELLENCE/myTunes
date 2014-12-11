/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import java.sql.SQLException;

/**
 *
 * @author Christopher, Mark, Martin & Rasmus
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        new Menu().run();
        //new TestApp01().testProperties();
    }

}
