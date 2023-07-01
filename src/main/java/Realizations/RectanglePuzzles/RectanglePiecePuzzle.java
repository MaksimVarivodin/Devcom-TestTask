package Realizations.RectanglePuzzles;

import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;
import Realizations.FileNameGenerator;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class RectanglePiecePuzzle implements PuzzleI {
    PuzzlePieceI[] pieces;
    int width;
    int height;

    public RectanglePiecePuzzle(PuzzlePieceI[] pieces, int cols, int rows) {
        this.pieces = pieces;
        this.width = cols;
        this.height = rows;
    }

    /**
     * Swaps selected puzzle piece with left piece,
     * if selected is not on left edge of a puzzle.
     *
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     */
    @Override
    public void moveLeft(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= 1 && xCoordinate < width && yCoordinate >= 0 && yCoordinate < height){
            PuzzlePieceI buffer  = new RectanglePiece(pieces[yCoordinate * width + xCoordinate - 1]);
            pieces[yCoordinate * width + xCoordinate - 1] = pieces[yCoordinate * width + xCoordinate];
            pieces[yCoordinate * width + xCoordinate] = buffer;
        }
    }

    /**
     * Swaps selected puzzle piece with right piece,
     * if selected is not on right edge of a puzzle.
     *
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     */
    @Override
    public void moveRight(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= 0 && xCoordinate < width-1 && yCoordinate >= 0 && yCoordinate < height){
            PuzzlePieceI buffer  = pieces[yCoordinate * width + xCoordinate + 1];
            pieces[yCoordinate * width + xCoordinate + 1] = pieces[yCoordinate * width + xCoordinate];
            pieces[yCoordinate * width + xCoordinate] = buffer;
        }
    }

    /**
     * Swaps selected puzzle piece with top piece,
     * if selected is not on top edge of a puzzle.
     *
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     */
    @Override
    public void moveUp(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= 0 && xCoordinate < width && yCoordinate >= 1 && yCoordinate < height){
            PuzzlePieceI buffer  = pieces[(yCoordinate -1) * width + xCoordinate];
            pieces[(yCoordinate - 1) * width + xCoordinate] = pieces[yCoordinate * width + xCoordinate];
            pieces[yCoordinate * width + xCoordinate] = buffer;
        }
    }

    /**
     * Swaps selected puzzle piece with bottom piece,
     * if selected is not on bottom edge of a puzzle.
     *
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     */
    @Override
    public void moveDown(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= 0 && xCoordinate < width && yCoordinate >= 0 && yCoordinate < height -1){
            PuzzlePieceI buffer  = pieces[(yCoordinate +1) * width + xCoordinate];
            pieces[(yCoordinate + 1) * width + xCoordinate] = pieces[yCoordinate * width + xCoordinate];
            pieces[yCoordinate * width + xCoordinate] = buffer;
        }
    }

    /**
     * Saves puzzle to directory -> path/puzzleName
     *
     * @param format     is the extension of puzzle pieces
     * @param path       is the directory where the directory of a puzzle will be created
     * @param puzzleName is the name of directory where puzzle will be saved
     */
    @Override
    public void save(String format, String path, String puzzleName) throws IOException {
        File puzzleDir = new File(path +"/"+  puzzleName);
        puzzleDir.mkdirs();
        for (PuzzlePieceI piece : pieces) {
            String fileName = FileNameGenerator.generateFileName(16);
            ImageIO.write(piece.getImage(), format, new File(puzzleDir.getPath() + "/" + fileName + "." + format));
        }
    }

    /**
     * Rotates selected puzzle piece anticlockwise.
     *
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     */
    @Override
    public void rotateLeft(int xCoordinate, int yCoordinate) {
        pieces[yCoordinate * width + xCoordinate].rotateLeft();
    }

    /**
     * Rotates selected puzzle piece clockwise.
     *
     * @param xCoordinate is the row
     * @param yCoordinate is the column
     */
    @Override
    public void rotateRight(int xCoordinate, int yCoordinate) {
        pieces[yCoordinate * width + xCoordinate].rotateRight();
    }
}
