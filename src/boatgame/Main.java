package boatgame;

import boatgame.game.GamePanel;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame gameFrame = new JFrame("Boat Game");
            gameFrame.add(new GamePanel());
            gameFrame.setResizable(false);
            gameFrame.pack();
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);
        });

    }
}
