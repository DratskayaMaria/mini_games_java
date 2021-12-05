package main.tetris.keyboard;

import main.tetris.main.ShiftDirection;

public interface KeyboardHandleModule {

    void update();
    boolean wasEscPressed();
    ShiftDirection getShiftDirection();
    boolean wasRotateRequested();
    boolean wasBoostRequested();
}
