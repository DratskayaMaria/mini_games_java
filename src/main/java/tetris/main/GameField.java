package tetris.main;

import tetris.graphics.TReadableColor;
import java.util.Random;
import static tetris.main.Constants.*;

public class GameField {

    private TReadableColor[][] theField;
    private int[] countFilledCellsInLine;
    private Shape shape;

    int score = 0;

    public GameField(){
        spawnNewFigure();

        theField = new TReadableColor[COUNT_CELLS_X][COUNT_CELLS_Y+OFFSET_TOP];
        countFilledCellsInLine = new int[COUNT_CELLS_Y+OFFSET_TOP];

        for(int y = 0; y < COUNT_CELLS_Y + OFFSET_TOP; y++){
            for(int x = 0; x < COUNT_CELLS_X; x++){
                theField[x][y] = EMPTINESS_COLOR;
            }
        }
    }


    private void spawnNewFigure(){
       int randomX = new Random().nextInt(COUNT_CELLS_X - MAX_FIGURE_WIDTH);
       this.shape = new Shape(new Coord(randomX, COUNT_CELLS_Y + OFFSET_TOP - 1));
    }


    public boolean isEmpty(int x, int y){
        return (theField[x][y].equals(EMPTINESS_COLOR));
    }
    public TReadableColor getColor(int x, int y) {
        return theField[x][y];
    }
    public Shape getFigure() {
        return shape;
    }

    public void tryShiftFigure(ShiftDirection shiftDirection) {
        Coord[] shiftedCoords = shape.getShiftedCoords(shiftDirection);

        boolean canShift = true;

        for(Coord coord: shiftedCoords) {
            if((coord.y < 0 || coord.y >= COUNT_CELLS_Y+OFFSET_TOP)
             ||(coord.x < 0 || coord.x >= COUNT_CELLS_X)
             || ! isEmpty(coord.x, coord.y)){
                canShift = false;
            }
        }

        if(canShift){
            shape.shift(shiftDirection);
        }
    }

    public void tryRotateFigure() {
        Coord[] rotatedCoords = shape.getRotatedCoords();

        boolean canRotate = true;

        for(Coord coord: rotatedCoords) {
            if((coord.y<0 || coord.y >= COUNT_CELLS_Y + OFFSET_TOP)
                    ||(coord.x<0 || coord.x >= COUNT_CELLS_X)
                    ||! isEmpty(coord.x, coord.y)){
                canRotate = false;
            }
        }

        if(canRotate){
            shape.rotate();
        }
    }


    public void letFallDown() {
        Coord[] fallenCoords = shape.getFallenCoords();

        boolean canFall = true;

        for(Coord coord: fallenCoords) {
            if((coord.y < 0 || coord.y >= COUNT_CELLS_Y+OFFSET_TOP)
                    ||(coord.x < 0 || coord.x >= COUNT_CELLS_X)
                    ||! isEmpty(coord.x, coord.y)){
                canFall = false;
            }
        }

        if(canFall) {
            shape.fall();
        } else {
            Coord[] figureCoords = shape.getCoords();


            boolean haveToShiftLinesDown = false;

            for(Coord coord: figureCoords) {
                theField[coord.x][coord.y] = shape.getColor();

                countFilledCellsInLine[coord.y]++;
                haveToShiftLinesDown = tryDestroyLine(coord.y) || haveToShiftLinesDown;
            }

            if(haveToShiftLinesDown) {
                shiftLinesDown();
                score++;
                System.out.println("Your Score: " + score);
            }

            spawnNewFigure();
        }
    }


    private void shiftLinesDown() {
        int fallTo = -1;

        for(int y = 0; y < COUNT_CELLS_Y; y++){
            if(fallTo == -1){
                if(countFilledCellsInLine[y] == 0) fallTo = y;
            }else {
                if(countFilledCellsInLine[y] != 0){

                    for(int x = 0; x < COUNT_CELLS_X; x++){
                        theField[x][fallTo] = theField[x][y];
                        theField[x][y] = EMPTINESS_COLOR;
                    }

                    countFilledCellsInLine[fallTo] = countFilledCellsInLine[y];
                    countFilledCellsInLine[y] = 0;
                    fallTo++;
                }
            }
        }
    }


    private boolean tryDestroyLine(int y) {
        if(countFilledCellsInLine[y] < COUNT_CELLS_X){
            return false;
        }

        for(int x = 0; x < COUNT_CELLS_X; x++){
            theField[x][y] = EMPTINESS_COLOR;
        }

        countFilledCellsInLine[y] = 0;
        return true;
    }


    public boolean isOverfilled(){
        boolean ret = false;

        for(int i = 0; i < OFFSET_TOP; i++){
            if(countFilledCellsInLine[COUNT_CELLS_Y + i] != 0) {
                ret = true;
                System.out.println("GAME OVER");
                System.out.println("YOUR FINAL SCORE: " + score);
            }
        }

        return ret;
    }

}
