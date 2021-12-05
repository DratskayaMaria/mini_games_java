package main.snake_game;

import main.main.MainGames;

import java.util.ArrayList;

public class GameController extends Thread {

    public static int snakeDirection;
    public ArrayList<ArrayList<GameData>> squares;
    public SnakeCoordinates snakeHeadPosition;
    public int snakeSize = 3;
    public int score = 0;
    public long snakeSpeed = 50;
    public ArrayList<SnakeCoordinates> snakePosition = new ArrayList<>();
    public SnakeCoordinates prizePosition;

    public GameController(SnakeCoordinates goalPosition) {
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
        boolean isSnakeBiteItself = false;
        while (!isSnakeBiteItself) {
            moveInside(snakeDirection);
            isSnakeBiteItself = checkSnakeCollision();
            moveOutside();
            refreshSnakeTail();
            pause();
        }
        StartSnakeGame.window.dispose();
        MainGames mainGames = new MainGames("Игры");
        mainGames.setLocationRelativeTo(null);
        mainGames.setVisible(true);
        interrupt();
    }

    public void pause() {
        try {
            sleep(snakeSpeed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkSnakeCollision() {
        SnakeCoordinates criticalPosition = snakePosition.get(snakePosition.size() - 1);
        boolean isSnakeBiteItself = false;
        for (int i = 0; i <= snakePosition.size() - 2; i++) {
            isSnakeBiteItself = criticalPosition.getX() ==
                    snakePosition.get(i).getX() && criticalPosition.getY() ==
                    snakePosition.get(i).getY();
            if (isSnakeBiteItself) {
                return isSnakeBiteItself;
            }
        }

        boolean isGettingPrize = criticalPosition.getX() ==
                prizePosition.getY() && criticalPosition.getY() == prizePosition.getX();
        if (isGettingPrize) {
            score += 1;
            System.out.println("YOUR SCORE: " + score + "!");
            snakeSize += 1;
            prizePosition = getPrizePosition();
            createPrize(prizePosition);
        }

        return isSnakeBiteItself;
    }

    public void stopTheGame() {
        System.out.println("GAME OVER! \n");
        while (true){
            pause();
        }
    }

    public void createPrize(SnakeCoordinates foodPositionIn) {
        squares.get(foodPositionIn.getX()).get(foodPositionIn.getY()).lightMeUp(1);
    }

    public SnakeCoordinates getPrizePosition() {
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

    public void moveInside(int direction) {
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

    public void moveOutside() {
        for (SnakeCoordinates t : snakePosition) {
            int y = t.getX();
            int x = t.getY();
            squares.get(x).get(y).lightMeUp(0);
        }
    }

    public void refreshSnakeTail() {
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
