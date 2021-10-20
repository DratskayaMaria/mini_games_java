package snake_game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Window window = new Window();

        window.setTitle("Snake");
        window.setSize(300, 300);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
