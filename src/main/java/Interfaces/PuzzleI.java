package Interfaces;


import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @PuzzleI is an interface to give access to actions with PuzzlePieces array.
 * */
public interface PuzzleI {

    void setCurrent(int xCoordinate, int yCoordinate);
    int getCurrentX();
    int getCurrentY();
    /**
     * Swaps selected puzzle piece with left piece,
     * if selected is not on left edge of a puzzle.

     * */
    void moveLeft();


    /**
     * Swaps selected puzzle piece with right piece,
     * if selected is not on right edge of a puzzle.

     * */
    void moveRight();


    /**
     * Swaps selected puzzle piece with top piece,
     * if selected is not on top edge of a puzzle.
     *
     * */
    void moveUp();


    /**
     * Swaps selected puzzle piece with bottom piece,
     * if selected is not on bottom edge of a puzzle.
     *
     * */
    void moveDown();



    /**
     * Rotates selected puzzle piece anticlockwise.
     *
     * */
    void rotateLeft();


    /**
     * Rotates selected puzzle piece clockwise.
     *
     * */
    void rotateRight();


    /**
     * Returns array of PuzzlePieces
     * */
    PuzzlePieceI[] getPuzzlePieces();
    /**
     * Returns array of Solved PuzzlePieces
     * */
    PuzzlePieceI[] getSolvedPuzzlePieces();
    /**
     * Returns Array of BufferedImages
     * */
    BufferedImage[] getImages();

    /**
     * @return number of columns
     * */
    int getColumns();


    /**
     * @return number of rows
     * */
    int getRows();


    boolean isSolved();
}
