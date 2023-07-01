package Realizations.RectanglePuzzles;

import Constants.RectangleEdgeType;
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
        if (piece.getWidth() == piece.getHeight()){
            piece = RotateImage.rotate(piece, 180);
        }
    }

    /**
     * Method that returns edges of a puzzle piece.
     * As we don't know how many edges there are, we return interface array.
     *
     * @return Array of edges of a puzzle piece
     */
    @Override
    public EdgeI[] getEdges() {
        EdgeI[] edges = new RectanglePieceEdge[]{
                new RectanglePieceEdge(RectangleEdgeType.LEFT),
                new RectanglePieceEdge(RectangleEdgeType.RIGHT),
                new RectanglePieceEdge(RectangleEdgeType.TOP),
                new RectanglePieceEdge(RectangleEdgeType.BOTTOM)};
        return edges;
    }

    /**
     * Returns puzzlePiece image
     */
    @Override
    public BufferedImage getImage() {
        return piece;
    }
}
