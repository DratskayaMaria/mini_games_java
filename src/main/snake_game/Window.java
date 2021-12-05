package main.snake_game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Window extends JFrame {

    private static final long serialVersionUID = -2542001418764869760L;
    public static ArrayList<ArrayList<GameData>> Grid;
    public static int width = 20;
    public static int height = 20;

    public Window() {
        Grid = new ArrayList<>();
        ArrayList<GameData> data;

        for (int i = 0; i < width; i++) {
            data = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                GameData gameData = new GameData(2);
                data.add(gameData);
            }
            Grid.add(data);
        }

        getContentPane().setLayout(new GridLayout(20, 20, 0, 0));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                getContentPane().add(Grid.get(i).get(j).square);
            }
        }

        SnakeCoordinates initialPosition = new SnakeCoordinates(10, 10);
        GameController gameController = new GameController(initialPosition);
        gameController.start();

        this.addKeyListener(new KeyboardControl());
    }
}
