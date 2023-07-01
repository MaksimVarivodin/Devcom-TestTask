package Interfaces;


import java.io.IOException;

/**
 * @PuzzleI is an interface to give access to actions with PuzzlePieces array.
 * */
public interface PuzzleI {


    /**
     * Swaps selected puzzle piece with left piece,
     * if selected is not on left edge of a puzzle.
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     * */
    void moveLeft(int xCoordinate, int yCoordinate);


    /**
     * Swaps selected puzzle piece with right piece,
     * if selected is not on right edge of a puzzle.
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     * */
    void moveRight(int xCoordinate, int yCoordinate);


    /**
     * Swaps selected puzzle piece with top piece,
     * if selected is not on top edge of a puzzle.
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     * */
    void moveUp(int xCoordinate, int yCoordinate);


    /**
     * Swaps selected puzzle piece with bottom piece,
     * if selected is not on bottom edge of a puzzle.
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     * */
    void moveDown(int xCoordinate, int yCoordinate);


    /**
     * Saves puzzle to directory -> path/puzzleName
     * @param path is the directory where the directory of a puzzle will be created
     * @param format is the extension of puzzle pieces
     * @param puzzleName is the name of directory where puzzle will be saved
     * */
    void save(String format, String path, String puzzleName) throws IOException;


    /**
     * Rotates selected puzzle piece anticlockwise.
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     * */
    void rotateLeft(int xCoordinate, int yCoordinate);


    /**
     * Rotates selected puzzle piece clockwise.
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     * */
    void rotateRight(int xCoordinate, int yCoordinate);


}
