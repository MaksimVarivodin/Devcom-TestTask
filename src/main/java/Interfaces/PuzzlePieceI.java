package Interfaces;


import java.awt.image.BufferedImage;

/**
 * @PuzzlePieceI is an interface to give access to actions with PuzzlePiece.
 * */
public interface PuzzlePieceI {
    /**
     * Anticlockwise rotation
     * */
    void rotateLeft();
    /**
     * Clockwise rotation
     * */
    void rotateRight();
    /**
     * Method that returns edges of a puzzle piece.
     * As we don't know how many edges there are, we return interface array.
     * @return Array of edges of a puzzle piece
     * */
    EdgeI[] getEdges();
    /**
     * Returns puzzlePiece image
     * */
    BufferedImage getImage();
}
