package Realizations.SquarePuzzles;

import Interfaces.PuzzlePieceI;
import Realizations.RectanglePuzzles.RectanglePiece;
import Realizations.ImageTools.ImageRotate;

import java.awt.image.BufferedImage;

public class SquarePiece extends RectanglePiece implements PuzzlePieceI  {

    public SquarePiece(BufferedImage image) {
        super(image);
    }

    /**
     * Anticlockwise rotation
     */
    @Override
    public void rotateLeft() {
        if (piece.getWidth() == piece.getHeight()){
            piece = ImageRotate.rotate(piece, -90);
        }
    }

    /**
     * Clockwise rotation
     */
    @Override
    public void rotateRight() {
        if (piece.getWidth() == piece.getHeight()){
            piece = ImageRotate.rotate(piece, 90);
        }
    }


}
