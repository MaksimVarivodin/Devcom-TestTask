package Realizations.SquarePuzzles;

import Interfaces.PuzzlePieceI;
import Realizations.RectanglePuzzles.RectanglePiece;
import Realizations.ImageTools.ImageRotate;

import java.awt.image.BufferedImage;

public class SquarePiece extends RectanglePiece implements PuzzlePieceI {

    public SquarePiece(BufferedImage image) {
        super(image);
    }

    /**
     * Anticlockwise rotation
     */
    @Override
    public void rotateLeft() {
        piece = ImageRotate.rotate(piece, -90);

    }

    /**
     * Clockwise rotation
     */
    @Override
    public void rotateRight() {
        piece = ImageRotate.rotate(piece, 90);

    }

    @Override
    public void setImage(BufferedImage image) {
        piece = image;
    }


}
