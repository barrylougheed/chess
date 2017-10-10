import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Main {

    public static void main(String[] args) {
        ChessProject myGUI = new ChessProject();
        myGUI.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        myGUI.pack();
        myGUI.setResizable(true);
        myGUI.setLocationRelativeTo( null );
        myGUI.setVisible(true);
    }
}
