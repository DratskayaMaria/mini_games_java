package main.snake_game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardControl extends KeyAdapter {

    public static final int BOTTOM = 4;
    public static final int TOP = 3;
    public static final int LEFT = 2;
    public static final int RIGHT = 1;

    public static final int BOTTOM_KEY = 40;
    public static final int TOP_KEY = 38;
    public static final int LEFT_KEY = 37;
    public static final int RIGHT_KEY = 39;

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case BOTTOM_KEY:
                if (GameController.snakeDirection != TOP)
                    GameController.snakeDirection = BOTTOM;
                break;
            case TOP_KEY:
                if (GameController.snakeDirection != BOTTOM)
                    GameController.snakeDirection = TOP;
                break;
            case LEFT_KEY:
                if (GameController.snakeDirection != RIGHT)
                    GameController.snakeDirection = LEFT;
                break;
            case RIGHT_KEY:
                if (GameController.snakeDirection != LEFT)
                    GameController.snakeDirection = RIGHT;
                break;
            default:
                break;
        }
    }

}
