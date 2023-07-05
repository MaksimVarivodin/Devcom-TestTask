package Realizations.RectanglePuzzles;

import Interfaces.PuzzlePieceI;
import Realizations.ImageTools.ImageRotate;

import java.awt.image.BufferedImage;

public class RectanglePiece implements PuzzlePieceI {

    protected BufferedImage piece;

    public RectanglePiece(BufferedImage image) {
        piece = image;
    }

    public RectanglePiece(PuzzlePieceI piece) {
        this(piece.getImage());
    }

    /**
     * Anticlockwise rotation
     */
    @Override
    public void rotateLeft() {
        piece = ImageRotate.rotate(piece, -180);

    }

    /**
     * Clockwise rotation
     */
    @Override
    public void rotateRight() {
        if (piece.getWidth() == piece.getHeight()) {
            piece = ImageRotate.rotate(piece, 180);
        }
    }

    /**
     * Returns puzzlePiece image
     */
    @Override
    public BufferedImage getImage() {
        return piece;
    }
}
