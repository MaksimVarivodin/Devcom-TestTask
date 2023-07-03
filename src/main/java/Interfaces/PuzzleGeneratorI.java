package Interfaces;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface PuzzleGeneratorI {


    /**
     * Cuts image into pieces. Height and width of pieces is determined by rows and columns.
     *
     * @param image   - image to cut
     * @param rows    - number of rows
     * @param columns - number of columns
     * @return PuzzlePieceI[] - array of PuzzlePieces
     */
    PuzzlePieceI[] generatePieceArray(BufferedImage image, int rows, int columns) throws IOException;


    /**
     * Cuts image into pieces. Height and width of pieces is determined by rows and columns.
     *
     * @param image   - image to cut
     * @param rows    - number of rows
     * @param columns - number of columns
     * @return PuzzleI - Puzzle ready for use
     */
    PuzzleI generatePuzzle(BufferedImage image, int rows, int columns) throws IOException;
}


