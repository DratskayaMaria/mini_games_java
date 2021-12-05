package test.tetris;

import org.junit.Assert;
import org.junit.Test;
import tetris.main.RotationMode;

import static tetris.main.RotationMode.*;

public class RotationModeTest {

    @Test
    public void getNextRotationFrom() {
        RotationMode currentRotation = FLIP_CCW;
        RotationMode expected = INVERT;
        RotationMode newRotation = RotationMode.getNextRotationFrom(currentRotation);

        Assert.assertEquals(expected, newRotation);

    }
}