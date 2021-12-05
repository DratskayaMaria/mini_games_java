package main.snake_game;

import java.awt.*;
import java.util.ArrayList;

public class GameData {

    public SquarePanel square;
    private ArrayList<Color> colors = new ArrayList<>();
    private int color;

    public GameData(int col) {
        //snake
        colors.add(Color.GREEN);
        //prize
        colors.add(Color.RED);
        //background
        colors.add(Color.WHITE);
        color = col;
        square = new SquarePanel(colors.get(color));
    }

    public void lightMeUp(int col) {
        square.ChangeColor(colors.get(col));
    }
}
