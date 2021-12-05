package tetris.main;

import tetris.graphics.TReadableColor;
import java.util.Random;

public enum ShapeForm {

    I_FORM (CoordMask.I_FORM, TReadableColor.BLUE),
    J_FORM (CoordMask.J_FORM, TReadableColor.ORANGE),
    L_FORM (CoordMask.L_FORM, TReadableColor.YELLOW),
    O_FORM (CoordMask.O_FORM, TReadableColor.RED),
    S_FORM (CoordMask.S_FORM, TReadableColor.AQUA),
    Z_FORM (CoordMask.Z_FORM, TReadableColor.PURPLE),
    T_FORM (CoordMask.T_FORM, TReadableColor.GREEN);

    private CoordMask mask;
    private TReadableColor color;

    private static final ShapeForm[] formByNumber = {I_FORM, J_FORM, L_FORM, O_FORM, S_FORM, Z_FORM, T_FORM,};

    ShapeForm(CoordMask mask, TReadableColor color){
        this.mask = mask;
        this.color = color;
    }

    public CoordMask getMask(){
        return this.mask;
    }
    public TReadableColor getColor(){
        return this.color;
    }

    public static ShapeForm getRandomForm() {
        int formNumber = new Random().nextInt(formByNumber.length);
        return formByNumber[formNumber];
    }

}
