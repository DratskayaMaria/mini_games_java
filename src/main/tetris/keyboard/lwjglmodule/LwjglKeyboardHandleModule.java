package main.tetris.keyboard.lwjglmodule;

import org.lwjgl.input.Keyboard;
import main.tetris.keyboard.KeyboardHandleModule;
import main.tetris.main.ShiftDirection;

public class LwjglKeyboardHandleModule implements KeyboardHandleModule {

    private boolean wasEscPressed;
    private boolean wasRotateRequested;
    private ShiftDirection shiftDirection;

    @Override
    public void update() {
        resetValues();

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch (Keyboard.getEventKey()) {
                    case Keyboard.KEY_ESCAPE -> wasEscPressed = true;
                    case Keyboard.KEY_UP -> wasRotateRequested = true;
                    case Keyboard.KEY_LEFT -> shiftDirection = ShiftDirection.LEFT;
                    case Keyboard.KEY_RIGHT -> shiftDirection = ShiftDirection.RIGHT;
                }
            }
        }
    }


    private void resetValues() {
        wasEscPressed = false;
        wasRotateRequested = false;
        shiftDirection = ShiftDirection.AWAITING;
    }

    @Override
    public boolean wasEscPressed() {
        return wasEscPressed;
    }

    @Override
    public ShiftDirection getShiftDirection() {
        return shiftDirection;
    }

    @Override
    public boolean wasRotateRequested() {
        return wasRotateRequested;
    }

    @Override
    public boolean wasBoostRequested() {
        return Keyboard.isKeyDown(Keyboard.KEY_DOWN);
    }

}
