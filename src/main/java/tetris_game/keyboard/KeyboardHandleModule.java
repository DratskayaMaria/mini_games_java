package tetris_game.keyboard;

import tetris_game.main.ShiftDirection;

public interface KeyboardHandleModule {

    void update();
    boolean wasEscPressed();
    ShiftDirection getShiftDirection();
    boolean wasRotateRequested();
    boolean wasBoostRequested();
}
