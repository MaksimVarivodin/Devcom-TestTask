package Interfaces;


import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @PuzzleI is an interface to give access to actions with PuzzlePieces array.
 * */
public interface PuzzleI {

    void setCurrent(int xCoordinate, int yCoordinate);
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
     * Saves puzzle to directory -> path/puzzleName
     * @param path is the directory where the directory of a puzzle will be created
     * @param format is the extension of puzzle pieces
     * @param puzzleName is the name of directory where puzzle will be saved
     * */
    void savePuzzle(String format, String path, String puzzleName) throws IOException;
    /**
     * Opens puzzle from path -> path/puzzleName
     * Also tries to get all images signed in .puzzle file
     * @param path is the dir where are the pictures and .puzzle file
     * @param puzzleName is the name of the file with the links to images on puzzle(their location on the grid)
     * */
    void openPuzzle(String path, String puzzleName) throws IOException, ClassNotFoundException;

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
     * @return number of columns
     * */
    int getColumns();


    /**
     * @return number of rows
     * */
    int getRows();
}
