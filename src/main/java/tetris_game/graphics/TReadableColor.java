package tetris_game.graphics;

import java.util.Random;

public enum TReadableColor {
    BLACK, RED, GREEN, BLUE, AQUA, YELLOW, ORANGE, PURPLE;

    private static TReadableColor[] colorByNumber = { BLACK, RED, GREEN, BLUE, AQUA, YELLOW, ORANGE, PURPLE, };

    public static TReadableColor getRandomColor() {
        int colorNumber = new Random().nextInt(colorByNumber.length);
        return colorByNumber[colorNumber];
    }
}
