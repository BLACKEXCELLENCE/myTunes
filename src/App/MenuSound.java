package App;

import java.util.Scanner;

/**
 * Abstract class implementing the basic functionality of a console based menu
 * class. A menu can be created by sub-classing this class and implement the
 * abstract method doAction(option).
 *
 * The constructor of the sub-class must invoke the super-class constructor by
 * the instruction
 *
 * super(header, "menuoption1", "menuoption2");
 *
 * Note, that this instruction must be the first instruction of the sub-class
 * constructor.
 *
 */
public abstract class MenuSound {
	// value used to exit the menu.
    // the value can be changed by the sub-class constructor.

    /**
     *
     */
    protected int EXIT_OPTION = 0;
    // The menu header text
    private String header;
    // The list of menu options texts.
    private final String[] menuItems;

    /**
     * Abstract method stating what should be done, when a menu option is
     * selected. The method must be overridden by the sub-class.
     *
     * @param option the menu option that has been selected.
     */
    protected abstract void doAction(int option);

    /**
     * Creates an instance of the class with the given header text and menu
     * options.
     *
     * @param header The header text of the menu.
     * @param menuItems The list of menu items texts.
     */
    public MenuSound(String header, String... menuItems) {
        this.header = header;
        this.menuItems = menuItems;
    }

    /**
     * Executes the menu until the EXIT_OPTION has been selected.
     */
    public void run() {
        int option = EXIT_OPTION;
        do {
            showMenu();
            option = getOption();
            doAction(option);

        } while (option != EXIT_OPTION);

    }

    /**
     * Returns a valid menu-option input from the keyboard. The method continues
     * prompting for an option value, until a valid option has been input.
     *
     * @return A valid menu option.
     */
    private int getOption() {
        int option = EXIT_OPTION;

        boolean done = false;
        while (!done) {
            System.out.print("\nSelect option: ");
            try {
                option = new Scanner(System.in).nextInt();
                if ((option >= 1 && option <= menuItems.length) || option == EXIT_OPTION) {
                    done = true;
                } else {
                    System.out.println("Please enter a valid option.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a number.");
            }
        }

        return option;
    }

    /**
     * Prints out a console menu with a header text and a list of menu options.
     * The menu options will be assigned the numbers from 1 to the number of
     * options in the menu.
     */
    private void showMenu() {
        System.out.println();
        System.out.println(header);
        System.out.println("   --------------------");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println(String.format("" + (i + 1) + ": " + menuItems[i]));
        }
        System.out.println(String.format("%2d) %s", EXIT_OPTION, "Return"));
    }

    /**
     * Waits until the 'enter' key is pressed.
     */
    protected void pause() {
        System.out.print("Press <enter> to continue...");
        new Scanner(System.in).nextLine();
    }

    /**
     *
     * @param idx
     * @param menuText
     */
    protected void setMenuText(int idx, String menuText) {
        menuItems[idx - 1] = menuText;
    }

    /**
     *
     * @param newHeader
     */
    protected void setHeader(String newHeader) {
        header = newHeader;
    }

}
