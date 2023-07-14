package Realizations.RectanglePuzzles;

import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;
import Realizations.ImageTools.ImageEquality;

import java.awt.image.BufferedImage;

public class RectanglePiecePuzzle implements PuzzleI {
    PuzzlePieceI[] unsolvedPieces;
    PuzzlePieceI[] solvedPieces;
    int columns;
    int rows;

    int currentPieceX;
    int currentPieceY;


    public RectanglePiecePuzzle(PuzzlePieceI[] pieces,PuzzlePieceI[] solvedPieces, int cols, int rows) {
        this.unsolvedPieces = pieces;
        this.solvedPieces = solvedPieces;
        this.columns = cols;
        this.rows = rows;
        this.currentPieceX = 0;
        this.currentPieceY = 0;
    }
    public void setCurrent(int currentPieceX, int currentPieceY){
        this.currentPieceX = currentPieceX;
        this.currentPieceY = currentPieceY;
    }

    public PuzzlePieceI[] getSolvedPieces() {
        return solvedPieces;
    }
    public boolean isSolved(){
        for (int i = 0; i < solvedPieces.length; i++) {
            if(!ImageEquality.equal(unsolvedPieces[i].getImage(),solvedPieces[i].getImage()))
                return false;
        }
        return true;
    }
    @Override
    public int getCurrentX() {
        return currentPieceX;
    }

    @Override
    public int getCurrentY() {
        return currentPieceY;
    }

    /**
     * Swaps selected puzzle piece with left piece,
     * if selected is not on left edge of a puzzle.
     *
     */
    @Override
    public void moveLeft() {
        if (currentPieceX >= 1 && currentPieceX < columns && currentPieceY >= 0 && currentPieceY < rows){
            PuzzlePieceI buffer  = new RectanglePiece(unsolvedPieces[currentPieceY * columns + currentPieceX - 1]);
            unsolvedPieces[currentPieceY * columns + currentPieceX - 1] = unsolvedPieces[currentPieceY * columns + currentPieceX];
            unsolvedPieces[currentPieceY * columns + currentPieceX] = buffer;

        }
    }

    /**
     * Swaps selected puzzle piece with right piece,
     * if selected is not on right edge of a puzzle.
     *
     */
    @Override
    public void moveRight() {
        if (currentPieceX >= 0 && currentPieceX < columns -1 && currentPieceY >= 0 && currentPieceY < rows){
            PuzzlePieceI buffer  = unsolvedPieces[currentPieceY * columns + currentPieceX + 1];
            unsolvedPieces[currentPieceY * columns + currentPieceX + 1] = unsolvedPieces[currentPieceY * columns + currentPieceX];
            unsolvedPieces[currentPieceY * columns + currentPieceX] = buffer;
        }
    }

    /**
     * Swaps selected puzzle piece with top piece,
     * if selected is not on top edge of a puzzle.
     *
     */
    @Override
    public void moveUp() {
        if (currentPieceX >= 0 && currentPieceX < columns && currentPieceY >= 1 && currentPieceY < rows){
            PuzzlePieceI buffer  = unsolvedPieces[(currentPieceY -1) * columns + currentPieceX];
            unsolvedPieces[(currentPieceY - 1) * columns + currentPieceX] = unsolvedPieces[currentPieceY * columns + currentPieceX];
            unsolvedPieces[currentPieceY * columns + currentPieceX] = buffer;
        }
    }

    /**
     * Swaps selected puzzle piece with bottom piece,
     * if selected is not on bottom edge of a puzzle.
     *
     */
    @Override
    public void moveDown() {
        if (currentPieceX >= 0 && currentPieceX < columns && currentPieceY >= 0 && currentPieceY < rows -1){
            PuzzlePieceI buffer  = unsolvedPieces[(currentPieceY +1) * columns + currentPieceX];
            unsolvedPieces[(currentPieceY + 1) * columns + currentPieceX] = unsolvedPieces[currentPieceY * columns + currentPieceX];
            unsolvedPieces[currentPieceY * columns + currentPieceX] = buffer;
        }
    }



    /**
     * Rotates selected puzzle piece anticlockwise.
     *
     */
    @Override
    public void rotateLeft() {
        unsolvedPieces[currentPieceY * columns + currentPieceX].rotateLeft();
    }

    /**
     * Rotates selected puzzle piece clockwise.
     *
     */
    @Override
    public void rotateRight() {
        unsolvedPieces[currentPieceY * columns + currentPieceX].rotateRight();
    }

    /**
     * Returns array of PuzzlePieces
     */
    @Override
    public PuzzlePieceI[] getPuzzlePieces() {
        return this.unsolvedPieces;
    }

    /**
     * Returns array of Solved PuzzlePieces
     */
    @Override
    public PuzzlePieceI[] getSolvedPuzzlePieces() {
        return this.solvedPieces;
    }

    /**
     * Returns Array of BufferedImages
     */
    @Override
    public BufferedImage[] getImages() {
        BufferedImage[] images = new BufferedImage[this.unsolvedPieces.length];
        for (int i = 0; i < unsolvedPieces.length; i++)
            images[i] = unsolvedPieces[i].getImage();
        return images;
    }


    /**
     * @return number of columns
     */
    @Override
    public int getColumns() {
        return  this.columns;
    }

    /**
     * @return number of rows
     */
    @Override
    public int getRows() {
        return this.rows;
    }
}
