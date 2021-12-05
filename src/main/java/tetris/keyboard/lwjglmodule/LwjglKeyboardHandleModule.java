package tetris.keyboard.lwjglmodule;

import org.lwjgl.input.Keyboard;
import tetris.keyboard.KeyboardHandleModule;
import tetris.main.ShiftDirection;

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
                    case Keyboard.KEY_ESCAPE :
                        wasEscPressed = true;
                        break;
                    case Keyboard.KEY_UP :
                        wasRotateRequested = true;
                        break;
                    case Keyboard.KEY_LEFT :
                        shiftDirection = ShiftDirection.LEFT;
                        break;
                    case Keyboard.KEY_RIGHT :
                        shiftDirection = ShiftDirection.RIGHT;
                        break;
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
