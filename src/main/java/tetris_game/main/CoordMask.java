package tetris_game.main;

public enum CoordMask {
    /* Кирпичик [][][][] */
    I_FORM(
            (initalCoord, rotation) -> {
                Coord[] ret = new Coord[4];

                switch (rotation){
                    case NORMAL:
                    case INVERT:
                        ret[0] = initalCoord;
                        ret[1] = new Coord(initalCoord.x , initalCoord.y - 1);
                        ret[2] = new Coord(initalCoord.x, initalCoord.y - 2);
                        ret[3] = new Coord(initalCoord.x, initalCoord.y - 3);
                        break;
                    case FLIP_CCW:
                    case FLIP_CW:
                        ret[0] = initalCoord;
                        ret[1] = new Coord(initalCoord.x + 1, initalCoord.y);
                        ret[2] = new Coord(initalCoord.x + 2, initalCoord.y);
                        ret[3] = new Coord(initalCoord.x + 3, initalCoord.y);
                        break;
                    default:
                        ErrorCatcher.wrongParameter("rotation", "CoordMask");
                }

                return ret;
            }
    ),
    /* Кирпичик  []
     *           [][][]
     */
    J_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initalCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                            ret[0] = new Coord(initalCoord.x + 1 , initalCoord.y);
                            ret[1] = new Coord(initalCoord.x + 1, initalCoord.y - 1);
                            ret[2] = new Coord(initalCoord.x + 1, initalCoord.y - 2);
                            ret[3] = new Coord(initalCoord.x, initalCoord.y - 2);
                            break;
                        case INVERT:
                            ret[0] = new Coord(initalCoord.x + 1 , initalCoord.y);
                            ret[1] = initalCoord;
                            ret[2] = new Coord(initalCoord.x, initalCoord.y - 1);
                            ret[3] = new Coord(initalCoord.x, initalCoord.y - 2);
                            break;
                        case FLIP_CCW:
                            ret[0] = initalCoord;
                            ret[1] = new Coord(initalCoord.x + 1, initalCoord.y);
                            ret[2] = new Coord(initalCoord.x + 2, initalCoord.y);
                            ret[3] = new Coord(initalCoord.x + 2, initalCoord.y - 1);
                            break;
                        case FLIP_CW:
                            ret[0] = initalCoord;
                            ret[1] = new Coord(initalCoord.x, initalCoord.y - 1);
                            ret[2] = new Coord(initalCoord.x + 1, initalCoord.y - 1);
                            ret[3] = new Coord(initalCoord.x + 2, initalCoord.y - 1);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /* Кирпичик     []
     *          [][][]
     */
    L_FORM(
            (initialCoord, rotation) -> {
                Coord[] ret = new Coord[4];

                switch (rotation){
                    case NORMAL:
                        ret[0] = initialCoord;
                        ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                        ret[2] = new Coord(initialCoord.x, initialCoord.y - 2);
                        ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                        break;
                    case INVERT:
                        ret[0] = initialCoord;
                        ret[1] = new Coord(initialCoord.x + 1, initialCoord.y);
                        ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                        ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                        break;
                    case FLIP_CCW:
                        ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                        ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                        ret[2] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                        ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                        break;
                    case FLIP_CW:
                        ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                        ret[1] = initialCoord;
                        ret[2] = new Coord(initialCoord.x + 1, initialCoord.y);
                        ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                        break;
                    default:
                        ErrorCatcher.wrongParameter("rotation", "CoordMask");
                }

                return ret;
            }
    ),
    /* Кирпичик [][]
     *          [][]
     */
    O_FORM(
            (initialCoord, rotation) -> {
                Coord[] ret = new Coord[4];

                ret[0] = initialCoord;
                ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                ret[3] = new Coord(initialCoord.x + 1, initialCoord.y);

                return ret;
            }
    ),
    /* Кирпичик   [][]
     *          [][]
     */
    S_FORM(
            (initialCoord, rotation) -> {
                Coord[] ret = new Coord[4];

                switch (rotation){
                    case NORMAL:
                    case INVERT:
                        ret[0] = new Coord(initialCoord.x , initialCoord.y - 1);
                        ret[1] = new Coord(initialCoord.x + 1 , initialCoord.y - 1);
                        ret[2] = new Coord(initialCoord.x + 1, initialCoord.y);
                        ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                        break;
                    case FLIP_CCW:
                    case FLIP_CW:
                        ret[0] = initialCoord;
                        ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                        ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                        ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                        break;
                    default:
                        ErrorCatcher.wrongParameter("rotation", "CoordMask");
                }

                return ret;
            }
    ),
    /* Кирпичик [][]
     *            [][]
     */
    Z_FORM(
            (initialCoord, rotation) -> {
                Coord[] ret = new Coord[4];

                switch (rotation){
                    case NORMAL:
                    case INVERT:
                        ret[0] = initialCoord;
                        ret[1] = new Coord(initialCoord.x + 1 , initialCoord.y);
                        ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                        ret[3] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                        break;
                    case FLIP_CCW:
                    case FLIP_CW:
                        ret[0] = new Coord(initialCoord.x + 1, initialCoord.y);
                        ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                        ret[2] = new Coord(initialCoord.x, initialCoord.y - 1);
                        ret[3] = new Coord(initialCoord.x, initialCoord.y - 2);
                        break;
                    default:
                        ErrorCatcher.wrongParameter("rotation", "CoordMask");
                }

                return ret;
            }
    ),
    /* Кирпичик [][][]
     *            []
     */
    T_FORM(
            (initialCoord, rotation) -> {
                Coord[] ret = new Coord[4];

                switch (rotation){
                    case NORMAL:
                        ret[0] = initialCoord;
                        ret[1] = new Coord(initialCoord.x + 1, initialCoord.y);
                        ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                        ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                        break;
                    case INVERT:
                        ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                        ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                        ret[2] = new Coord(initialCoord.x + 1, initialCoord.y);
                        ret[3] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                        break;
                    case FLIP_CCW:
                        ret[0] = initialCoord;;
                        ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                        ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                        ret[3] = new Coord(initialCoord.x, initialCoord.y - 2);
                        break;
                    case FLIP_CW:
                        ret[0] = new Coord(initialCoord.x + 1, initialCoord.y);
                        ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                        ret[2] = new Coord(initialCoord.x, initialCoord.y - 1);
                        ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                        break;
                    default:
                        ErrorCatcher.wrongParameter("rotation", "CoordMask");
                }

                return ret;
            }
    );


    private interface GenerationDelegate{
        Coord[] generateFigure(Coord initialCoord,  RotationMode rotation);
    }
    private GenerationDelegate forms;

    CoordMask(GenerationDelegate forms){
        this.forms = forms;
    }
    public Coord[] generateFigure(Coord initialCoord, RotationMode rotation){
        return this.forms.generateFigure(initialCoord, rotation);
    }
}
