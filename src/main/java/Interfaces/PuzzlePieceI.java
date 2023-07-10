package Interfaces;


import java.awt.image.BufferedImage;
import java.io.IOException;

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

    /**
     * saves puzzlePiece image
     * */
    void savePuzzlePiece(String path, String fileName, String format) throws IOException;
    /**
     * opens image and creates PuzzlePiece
     * */
    PuzzlePieceI openPuzzlePiece(String path) throws IOException;
}
