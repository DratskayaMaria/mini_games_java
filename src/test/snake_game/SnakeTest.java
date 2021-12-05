package test.snake_game;

import main.snake_game.GameController;
import main.snake_game.KeyboardControl;
import main.snake_game.SnakeCoordinates;
import main.snake_game.Window;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeTest {

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
}