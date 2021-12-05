package main.tetris.graphics.lwjglmodule;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;
import main.tetris.graphics.GraphicsModule;
import main.tetris.graphics.TReadableColor;
import main.tetris.main.*;

import static org.lwjgl.opengl.GL11.*;
import static main.tetris.main.Constants.*;

public class LwjglGraphicsModule implements GraphicsModule {

    public LwjglGraphicsModule() {
        initOpengl();
    }

    private void initOpengl() {
        try {

            Display.setDisplayMode(new DisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
            Display.setTitle(Constants.SCREEN_NAME);
            Display.create();
        } catch (LWJGLException e) {
            ErrorCatcher.graphicsFailure(e);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Constants.SCREEN_WIDTH,0, Constants.SCREEN_HEIGHT,1,-1);
        glMatrixMode(GL_MODELVIEW);

        glEnable(GL_TEXTURE_2D);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glClearColor(1,1,1,1);
    }

    private void drawCell(int x, int y, Color color) {
        glColor3ub(color.getRedByte(), color.getGreenByte(), color.getBlueByte());

        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(x,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,0);
        glVertex2f(x+ Constants.CELL_SIZE,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,1);
        glVertex2f(x+ Constants.CELL_SIZE, y);
        glTexCoord2f(0,1);
        glVertex2f(x, y);
        glEnd();
    }

    @Override
    public void draw(GameField field) {
        glClear(GL_COLOR_BUFFER_BIT);

        for(int x = 0; x < COUNT_CELLS_X; x++){
            for(int y = 0; y < COUNT_CELLS_Y; y++){
                TReadableColor color = field.getColor(x,y);
                drawCell(x*CELL_SIZE, y*CELL_SIZE, convertColor(color));
            }
        }

        Shape shape = field.getFigure();
        TReadableColor color = shape.getColor();
        for(Coord coord : shape.getCoords()){
            drawCell(coord.x * CELL_SIZE, coord.y * CELL_SIZE, convertColor(color));
        }

        Display.update();
    }


    private Color convertColor(TReadableColor color) {
        return switch (color) {
            case RED -> new Color(ReadableColor.RED);
            case GREEN -> new Color(ReadableColor.GREEN);
            case BLUE -> new Color(ReadableColor.BLUE);
            case AQUA -> new Color(ReadableColor.CYAN);
            case YELLOW -> new Color(ReadableColor.YELLOW);
            case ORANGE -> new Color(ReadableColor.ORANGE);
            case PURPLE -> new Color(ReadableColor.PURPLE);
            case BLACK -> new Color(ReadableColor.BLACK);
        };
    }


    @Override
    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    @Override
    public void destroy() {
        Display.destroy();
    }

    @Override
    public void sync(int fps) {
        Display.sync(fps);
    }
}
