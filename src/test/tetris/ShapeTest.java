package test.tetris;

import org.junit.Assert;
import org.junit.Test;
import tetris.main.Coord;
import tetris.main.RotationMode;
import tetris.main.Shape;
import tetris.main.ShiftDirection;

import java.util.ArrayList;
import java.util.List;

import static tetris.main.ShapeForm.T_FORM;

public class ShapeTest {

    @Test
    public void getShiftedCoords() {
        RotationMode mode = RotationMode.NORMAL;
        Coord coord = new Coord(10, 10);
        Shape shape = new Shape( coord, mode, T_FORM);

        List<Coord> expected = new ArrayList<>();
        Coord coord1 = new Coord(9, 10);
        Coord coord2 = new Coord(10, 10);
        Coord coord3 = new Coord(10, 9);
        Coord coord4 = new Coord(11, 10);

        expected.add(coord1);
        expected.add(coord2);
        expected.add(coord3);
        expected.add(coord4);

        Coord[] arr = shape.getShiftedCoords(ShiftDirection.LEFT);
        List<Coord> actual = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            actual.add(new Coord(arr[i].getX(), arr[i].getY()));
        }

        Assert.assertEquals(expected, actual);

    }
}