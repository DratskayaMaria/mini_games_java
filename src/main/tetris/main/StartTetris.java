package main.tetris.main;

import main.tetris.graphics.GraphicsModule;
import main.tetris.graphics.lwjglmodule.LwjglGraphicsModule;
import main.tetris.keyboard.KeyboardHandleModule;
import main.tetris.keyboard.lwjglmodule.LwjglKeyboardHandleModule;

import static main.tetris.main.Constants.BOOST_MULTIPLIER;
import static main.tetris.main.Constants.FPS;
import static main.tetris.main.Constants.FRAMES_PER_MOVE;

public class StartTetris {

    private static GraphicsModule graphicsModule;
    private static KeyboardHandleModule keyboardModule;
    private static GameField gameField;
    private static ShiftDirection shiftDirection;

    private static boolean endOfGame;
    private static boolean isRotateRequested;
    private static boolean isBoostRequested;

    private static int loopNumber;

    public static boolean start() {
        initFields();

        while(!endOfGame){
            input();
            logic();

            graphicsModule.draw(gameField);
            graphicsModule.sync(FPS);
        }
        graphicsModule.destroy();
        return endOfGame;
    }


    private static void initFields() {
        loopNumber = 0;
        endOfGame = false;
        shiftDirection = ShiftDirection.AWAITING;
        isRotateRequested = false;
        graphicsModule = new LwjglGraphicsModule();
        keyboardModule = new LwjglKeyboardHandleModule();
        gameField = new GameField();
    }

    private static void input(){

        keyboardModule.update();
        shiftDirection = keyboardModule.getShiftDirection();
        isRotateRequested = keyboardModule.wasRotateRequested();
        isBoostRequested = keyboardModule.wasBoostRequested();
        endOfGame = endOfGame || keyboardModule.wasEscPressed() || graphicsModule.isCloseRequested();
    }

    private static void logic(){
       if(shiftDirection != ShiftDirection.AWAITING){

           gameField.tryShiftFigure(shiftDirection);
           shiftDirection = ShiftDirection.AWAITING;
       }

       if(isRotateRequested){

           gameField.tryRotateFigure();
           isRotateRequested = false;
       }

       if( (loopNumber % (FRAMES_PER_MOVE / (isBoostRequested ? BOOST_MULTIPLIER : 1)) ) == 0) gameField.letFallDown();

       loopNumber = (loopNumber + 1) % (FRAMES_PER_MOVE);
       endOfGame = endOfGame || gameField.isOverfilled();

    }

}
