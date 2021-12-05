package test.tetris;

import org.junit.Assert;
import org.junit.Test;

import tetris.main.*;

import java.util.ArrayList;
import java.util.List;

import static tetris.main.ShapeForm.T_FORM;

public class CoordMaskTest {

    @Test
    public void generateFigure() {
        RotationMode mode = RotationMode.NORMAL;
        Coord coord = new Coord(10, 10);
        Shape shape = new Shape( coord, mode, T_FORM);

        List<Coord> expected = new ArrayList<>();
        Coord coord1 = new Coord(10, 10);
        Coord coord2 = new Coord(11, 10);
        Coord coord3 = new Coord(11, 9);
        Coord coord4 = new Coord(12, 10);

        expected.add(coord1);
        expected.add(coord2);
        expected.add(coord3);
        expected.add(coord4);

        Coord[] arr = shape.getForm().getMask().generateFigure(coord, mode);

        List<Coord> actual = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            actual.add(new Coord(arr[i].getX(), arr[i].getY()));
        }

        Assert.assertEquals(expected, actual);
    }
}