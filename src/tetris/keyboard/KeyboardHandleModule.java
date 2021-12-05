package tetris.keyboard;

import tetris.main.ShiftDirection;

public interface KeyboardHandleModule {

    void update();
    boolean wasEscPressed();
    ShiftDirection getShiftDirection();
    boolean wasRotateRequested();
    boolean wasBoostRequested();
}
