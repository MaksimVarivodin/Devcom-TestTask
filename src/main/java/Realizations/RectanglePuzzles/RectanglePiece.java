package Realizations.RectanglePuzzles;

import Interfaces.EdgeI;
import Interfaces.PuzzlePieceI;
import Realizations.RotateImage;

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
        piece = RotateImage.rotate(piece, -180);

    }

    /**
     * Clockwise rotation
     */
    @Override
    public void rotateRight() {
        if (piece.getWidth() == piece.getHeight()) {
            piece = RotateImage.rotate(piece, 180);
        }
    }

    @Override
    public EdgeI[] getEdges() {
        return new EdgeI[0];
    }

    /**
     * Returns puzzlePiece image
     */
    @Override
    public BufferedImage getImage() {
        return piece;
    }
}
