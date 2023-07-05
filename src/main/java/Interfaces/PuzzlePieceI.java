package Interfaces;


import java.awt.image.BufferedImage;
import java.util.Arrays;

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
     * Returns puzzlePiece image
     * */
    BufferedImage getImage();


}
