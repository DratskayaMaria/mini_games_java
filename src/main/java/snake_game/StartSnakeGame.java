package snake_game;

import javax.swing.*;


public class StartSnakeGame {

    static Window window;
    public static void start() {
        window = new Window();
        window.setTitle("Snake");
        window.setSize(900, 700);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
