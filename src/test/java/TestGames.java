import game_2048.Game2048;
import org.junit.Assert;
import snake_game.GameController;
import snake_game.KeyboardControl;
import snake_game.SnakeCoordinates;
import snake_game.Window;
import org.junit.Test;
import tetris.main.Coord;
import tetris.main.RotationMode;
import tetris.main.Shape;
import tetris.main.ShiftDirection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static tetris.main.RotationMode.FLIP_CCW;
import static tetris.main.RotationMode.INVERT;
import static tetris.main.ShapeForm.T_FORM;

public class TestGames {

    //змейка
    @Test
    public void checkSnakeCollision() {
        Window window = new Window();
        SnakeCoordinates initialPosition = new SnakeCoordinates(10, 10);
        GameController gameController = new GameController(initialPosition);

        gameController.snakeHeadPosition.ChangeData(
                gameController.snakeHeadPosition.getX(), (gameController.snakeHeadPosition.getY() + 1) % 20);
        gameController.snakePosition.add(new SnakeCoordinates(gameController.snakeHeadPosition.getX(),
                gameController.snakeHeadPosition.getY()));
        gameController.refreshSnakeTail();

        SnakeCoordinates criticalPosition = gameController.snakePosition.get(
                gameController.snakePosition.size() - 1);

        gameController.snakePosition.get(0).setX(criticalPosition.getX());
        gameController.snakePosition.get(0).setY(criticalPosition.getY());

        boolean isSnakeBiteItself = gameController.checkSnakeCollision();
        assertTrue(isSnakeBiteItself);
    }

    @Test
    public void checkNotSnakeCollision() {
        Window window = new Window();
        SnakeCoordinates initialPosition = new SnakeCoordinates(10, 10);
        GameController gameController = new GameController(initialPosition);

        gameController.snakeHeadPosition.ChangeData(
                gameController.snakeHeadPosition.getX(), (gameController.snakeHeadPosition.getY() + 1) % 20);
        gameController.snakePosition.add(new SnakeCoordinates(gameController.snakeHeadPosition.getX(),
                gameController.snakeHeadPosition.getY()));
        gameController.refreshSnakeTail();

        SnakeCoordinates criticalPosition = gameController.snakePosition.get(
                gameController.snakePosition.size() - 1);

        boolean isSnakeBiteItself = gameController.checkSnakeCollision();
        assertTrue(!isSnakeBiteItself);
    }

    @Test
    public void checkGettingPrize(){
        Window window = new Window();
        int x = 10;
        int y = 10;
        SnakeCoordinates initialPosition = new SnakeCoordinates(x, y);
        GameController gameController = new GameController(initialPosition);

        gameController.prizePosition.setX(x);
        gameController.prizePosition.setY(y);

        gameController.checkSnakeCollision();
        assertEquals(1, gameController.score);
    }

    @Test
    public void checkNotGettingPrize(){
        Window window = new Window();
        int x = 10;
        int y = 10;
        SnakeCoordinates initialPosition = new SnakeCoordinates(x, y);
        GameController gameController = new GameController(initialPosition);

        gameController.prizePosition.setX(x - 1);
        gameController.prizePosition.setY(y - 1);

        gameController.checkSnakeCollision();
        assertEquals(0, gameController.score);
    }

    @Test
    public void checkSnakeMovementBottom(){
        Window window = new Window();
        int x = 10;
        int y = 10;
        SnakeCoordinates initialPosition = new SnakeCoordinates(x, y);
        GameController gameController = new GameController(initialPosition);

        gameController.moveInside(KeyboardControl.BOTTOM);
        SnakeCoordinates snakeHeadPosition = gameController.snakePosition.get(
                gameController.snakePosition.size() - 1);
        assertEquals(x, snakeHeadPosition.getX());
        assertEquals( (y + 1) % 20, snakeHeadPosition.getY());
    }

    @Test
    public void checkSnakeMovementTop(){
        Window window = new Window();
        int x = 10;
        int y = 10;
        SnakeCoordinates initialPosition = new SnakeCoordinates(x, y);
        GameController gameController = new GameController(initialPosition);

        gameController.moveInside(KeyboardControl.TOP);
        SnakeCoordinates snakeHeadPosition = gameController.snakePosition.get(
                gameController.snakePosition.size() - 1);
        if (y - 1 < 0) {
            y = 19;
        } else {
            y = Math.abs(y - 1) % 20;
        }

        assertEquals(x, snakeHeadPosition.getX());
        assertEquals( y, snakeHeadPosition.getY());
    }

    @Test
    public void checkSnakeMovementLeft(){
        Window window = new Window();
        int x = 10;
        int y = 10;
        SnakeCoordinates initialPosition = new SnakeCoordinates(x, y);
        GameController gameController = new GameController(initialPosition);

        gameController.moveInside(KeyboardControl.LEFT);
        SnakeCoordinates snakeHeadPosition = gameController.snakePosition.get(
                gameController.snakePosition.size() - 1);

        if (x - 1 < 0) {
            x = 19;
        } else {
            x = Math.abs(x - 1) % 20;
        }

        assertEquals(x, snakeHeadPosition.getX());
        assertEquals( y, snakeHeadPosition.getY());
    }

    @Test
    public void checkSnakeMovementRight(){
        Window window = new Window();
        int x = 10;
        int y = 10;
        SnakeCoordinates initialPosition = new SnakeCoordinates(x, y);
        GameController gameController = new GameController(initialPosition);

        gameController.moveInside(KeyboardControl.RIGHT);
        SnakeCoordinates snakeHeadPosition = gameController.snakePosition.get(
                gameController.snakePosition.size() - 1);
        assertEquals((x + 1) % 20, snakeHeadPosition.getX());
        assertEquals( y, snakeHeadPosition.getY());
    }

    //2048
    private int getSizeTiles(Game2048 game)
    {
        int size = 0;
        int temp = game.side;
        for (int i = 0; i < temp; i++)
        {
            for (int j = 0; j < temp; j++)
            {
                if (game.tiles[i][j] != null)
                {
                    size++;
                }
            }
        }
        return size;
    }


    @Test
    public void movesAvailable() {
        Game2048 game = new Game2048();
        game.startGame();
        assertTrue(game.movesAvailable());
    }


    @Test
    public void startGame() {
        Game2048 game = new Game2048();
        game.startGame();
        assertEquals(getSizeTiles(game), 2);
    }

    @Test
    public void gameState()
    {
        Game2048 game = new Game2048();
        assertEquals(game.gamestate, Game2048.State.start);
        game.startGame();
        assertEquals(game.gamestate, Game2048.State.running);
    }

    @Test
    public void addRandomTile() {
        Game2048 game = new Game2048();
        game.startGame();
        int size = getSizeTiles(game);
        game.addRandomTile();
        assertEquals(size + 1, getSizeTiles(game));
    }

    //тетрис
    @Test
    public void generateFigure() {
        RotationMode mode = RotationMode.NORMAL;
        Coord coord = new Coord(10, 10);
        Shape shape = new Shape( coord, mode, T_FORM);

        List<Coord> expected = new ArrayList<>();
        Coord coord1 = new Coord(10, 10);
        Coord coord2 = new Coord(11, 10);
        Coord coord3 = new Coord(11, 9);
        Coord coord4 = new Coord(12, 10);

        expected.add(coord1);
        expected.add(coord2);
        expected.add(coord3);
        expected.add(coord4);

        Coord[] arr = shape.getForm().getMask().generateFigure(coord, mode);

        List<Coord> actual = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            actual.add(new Coord(arr[i].getX(), arr[i].getY()));
        }

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNextRotationFrom() {
        RotationMode currentRotation = FLIP_CCW;
        RotationMode expected = INVERT;
        RotationMode newRotation = RotationMode.getNextRotationFrom(currentRotation);

        Assert.assertEquals(expected, newRotation);

    }

    @Test
    public void getShiftedCoords() {
        RotationMode mode = RotationMode.NORMAL;
        Coord coord = new Coord(10, 10);
        Shape shape = new Shape( coord, mode, T_FORM);

        List<Coord> expected = new ArrayList<>();
        Coord coord1 = new Coord(9, 10);
        Coord coord2 = new Coord(10, 10);
        Coord coord3 = new Coord(10, 9);
        Coord coord4 = new Coord(11, 10);

        expected.add(coord1);
        expected.add(coord2);
        expected.add(coord3);
        expected.add(coord4);

        Coord[] arr = shape.getShiftedCoords(ShiftDirection.LEFT);
        List<Coord> actual = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            actual.add(new Coord(arr[i].getX(), arr[i].getY()));
        }

        Assert.assertEquals(expected, actual);

    }
}