package Realizations.RectanglePuzzles;

import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;
import Realizations.ArrayRandomizer;
import Realizations.ImageTools.ImageCutter;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class RectanglePuzzleGenerator{
    /**
     * Cuts image into pieces. Height and width of pieces are determined by rows and columns.

     * @param image   image to cut
     * @param rows     number of rows
     * @param columns  number of columns
     * @return PuzzlePieceI[]  array of PuzzlePieces
     */

    public static PuzzlePieceI[] generatePieceArray(BufferedImage image, int rows, int columns) throws IOException {
        BufferedImage[] array = ImageCutter.cutImage(image, rows, columns);
        PuzzlePieceI [] result = new RectanglePiece[array.length];
        int iter = 0;
        for (BufferedImage img:
             array) {
            result[iter]= new RectanglePiece(img);
            iter++;
        }
        for (PuzzlePieceI piece:
             result) {
            for (int i = 0; i< Math.random() * 2; i++) {
                piece.rotateLeft();
            }
        }
        return result;
    }

    /**
     * Cuts image into pieces. Height and width of pieces are determined by rows and columns.
     *
     * @param image    image to cut
     * @param rows     number of rows
     * @param columns  number of columns
     * @return PuzzleI  Puzzle ready for use
     */

    public static PuzzleI generatePuzzle(BufferedImage image, int rows, int columns) throws IOException {
        PuzzlePieceI [] pieces = generatePieceArray(image, rows, columns);
        ArrayRandomizer randomizer = new ArrayRandomizer(pieces);
        pieces = randomizer.randomize();
        PuzzleI puzzle = new RectanglePiecePuzzle(pieces, columns, rows);
        return puzzle;
    }
}
