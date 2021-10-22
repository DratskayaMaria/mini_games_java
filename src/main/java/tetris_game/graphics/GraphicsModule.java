package tetris_game.graphics;

import tetris_game.main.GameField;

public interface GraphicsModule {

    void draw(GameField field);
    boolean isCloseRequested();
    void destroy();
    void sync(int fps);
}
