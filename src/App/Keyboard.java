/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Christopher, Mark, Martin & Rasmus
 */
public class Keyboard {

    /**
     *
     * @return
     */
    public static String readString() {
        String res = new Scanner(System.in).nextLine();
        return res;
    }

    /**
     *
     * @return
     */
    public static int readInt() {
        int res = new Scanner(System.in).nextInt();
        return res;
    }

    /**
     *
     * @param promptText
     * @return
     */
    public static int readInt(String promptText) {
        System.out.print(promptText);
        int res = new Scanner(System.in).nextInt();
        return res;
    }

    /**
     *
     * @param promptText
     * @return
     */
    public static int promptForInt(String promptText) {
        String res = JOptionPane.showInputDialog(promptText);
        return Integer.parseInt(res);
    }

}
