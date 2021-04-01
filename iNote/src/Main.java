import gui.CFrame;
import javax.swing.*;

/**
 * this class is center of program
 * in this class we make an object for gui of notPad
 * and set the frame visible at last
 */
public class Main {

    /**
     * main class of program
     */
    public static void main(String[] args) {
        CFrame frame = new CFrame("iNote");
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
