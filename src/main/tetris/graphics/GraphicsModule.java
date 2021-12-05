package main.tetris.graphics;

import main.tetris.main.GameField;

public interface GraphicsModule {

    void draw(GameField field);
    boolean isCloseRequested();
    void destroy();
    void sync(int fps);
}
