package snake_game;

import java.util.ArrayList;

public class GameController extends Thread {

    public static int snakeDirection;
    private ArrayList<ArrayList<GameData>> squares;
    private SnakeCoordinates snakeHeadPosition;
    private int snakeSize = 3;
    private int score = 0;
    private long snakeSpeed = 50;
    private ArrayList<SnakeCoordinates> snakePosition = new ArrayList<>();
    private SnakeCoordinates prizePosition;

    GameController(SnakeCoordinates goalPosition) {
        squares = Window.Grid;
        snakeHeadPosition = new SnakeCoordinates(goalPosition.getX(), goalPosition.getY());
        snakeDirection = 1;

        SnakeCoordinates newSnakeHeadPosition =
                new SnakeCoordinates(snakeHeadPosition.getX(), snakeHeadPosition.getY());
        snakePosition.add(newSnakeHeadPosition);

        prizePosition = new SnakeCoordinates(Window.height - 1, Window.width - 1);
        createPrize(prizePosition);
    }

    public void run() {
        while (true) {
            moveInside(snakeDirection);
            checkSnakeCollision();
            moveOutside();
            refreshSnakeTail();
            pause();
        }
    }

    private void pause() {
        try {
            sleep(snakeSpeed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkSnakeCollision() {
        SnakeCoordinates criticalPosition = snakePosition.get(snakePosition.size() - 1);
        for (int i = 0; i <= snakePosition.size() - 2; i++) {
            boolean isSnakeBiteItself = criticalPosition.getX() ==
                    snakePosition.get(i).getX() && criticalPosition.getY() ==
                    snakePosition.get(i).getY();
            if (isSnakeBiteItself) {
                stopTheGame();
            }
        }

        boolean isGettingPrize = criticalPosition.getX() ==
                prizePosition.getY() && criticalPosition.getY() == prizePosition.getX();
        if (isGettingPrize) {
            score += 1;
            System.out.println("YOUR SCORE: " + score + "!");
            snakeSize += 1;
            prizePosition = getSnakePosition();
            createPrize(prizePosition);
        }
    }

    private void stopTheGame() {
        System.out.println("GAME OVER! \n");
        while (true) {
            pause();
        }
    }

    private void createPrize(SnakeCoordinates foodPositionIn) {
        squares.get(foodPositionIn.getX()).get(foodPositionIn.getY()).lightMeUp(1);
    }

    private SnakeCoordinates getSnakePosition() {
        SnakeCoordinates snakeCoordinates;
        int randomX = (int) (Math.random() * 19);
        int randomY = (int) (Math.random() * 19);
        snakeCoordinates = new SnakeCoordinates(randomX, randomY);
        for (int i = 0; i <= snakePosition.size() - 1; i++) {
            if (snakeCoordinates.getY() == snakePosition.get(i).getX() &&
                    snakeCoordinates.getX() == snakePosition.get(i).getY()) {
                randomX = (int) (Math.random() * 19);
                randomY = (int) (Math.random() * 19);
                snakeCoordinates = new SnakeCoordinates(randomX, randomY);
                i = 0;
            }
        }
        return snakeCoordinates;
    }

    private void moveInside(int direction) {
        switch (direction) {
            case KeyboardControl.BOTTOM:
                snakeHeadPosition.ChangeData(snakeHeadPosition.getX(), (snakeHeadPosition.getY() + 1) % 20);
                snakePosition.add(new SnakeCoordinates(snakeHeadPosition.getX(), snakeHeadPosition.getY()));
                break;
            case KeyboardControl.TOP:
                if (snakeHeadPosition.getY() - 1 < 0) {
                    snakeHeadPosition.ChangeData(snakeHeadPosition.getX(), 19);
                } else {
                    snakeHeadPosition.ChangeData(snakeHeadPosition.getX(), Math.abs(snakeHeadPosition.getY() - 1) % 20);
                }
                snakePosition.add(new SnakeCoordinates(snakeHeadPosition.getX(), snakeHeadPosition.getY()));
                break;
            case KeyboardControl.LEFT:
                if (snakeHeadPosition.getX() - 1 < 0) {
                    snakeHeadPosition.ChangeData(19, snakeHeadPosition.getY());
                } else {
                    snakeHeadPosition.ChangeData(Math.abs(snakeHeadPosition.getX() - 1) % 20, snakeHeadPosition.getY());
                }
                snakePosition.add(new SnakeCoordinates(snakeHeadPosition.getX(), snakeHeadPosition.getY()));

                break;
            case KeyboardControl.RIGHT:
                snakeHeadPosition.ChangeData(Math.abs(snakeHeadPosition.getX() + 1) % 20, snakeHeadPosition.getY());
                snakePosition.add(new SnakeCoordinates(snakeHeadPosition.getX(), snakeHeadPosition.getY()));
                break;
        }
    }

    private void moveOutside() {
        for (SnakeCoordinates t : snakePosition) {
            int y = t.getX();
            int x = t.getY();
            squares.get(x).get(y).lightMeUp(0);
        }
    }

    private void refreshSnakeTail() {
        int cmpt = snakeSize;
        for (int i = snakePosition.size() - 1; i >= 0; i--) {
            if (cmpt == 0) {
                SnakeCoordinates t = snakePosition.get(i);
                squares.get(t.getY()).get(t.getX()).lightMeUp(2);
            } else {
                cmpt--;
            }
        }
        cmpt = snakeSize;
        for (int i = snakePosition.size() - 1; i >= 0; i--) {
            if (cmpt == 0) {
                snakePosition.remove(i);
            } else {
                cmpt--;
            }
        }
    }
}
