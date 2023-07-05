package Realizations.SquarePuzzles;


import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;
import Realizations.ArrayRandomizer;
import Realizations.ImageTools.ImageCutter;
import Realizations.RectanglePuzzles.RectanglePiecePuzzle;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class SquarePuzzleGenerator   {

    public static PuzzlePieceI[] generatePieceArray(BufferedImage image, int rows, int columns) throws IOException {
        BufferedImage[] array = ImageCutter.cutImage(image, rows, columns);
        PuzzlePieceI [] result = new SquarePiece[array.length];
        int iter = 0;
        for (BufferedImage img:
             array) {
            result[iter]= new SquarePiece(img);
            iter++;
        }
        for (PuzzlePieceI piece:
             result) {
            for (int i = 0; i< Math.random() * 4; i++) {
                piece.rotateLeft();
            }
        }
        return result;
    }

    public static PuzzleI generatePuzzle(BufferedImage image, int rows, int columns) throws IOException {
        PuzzlePieceI [] pieces = generatePieceArray(image, rows, columns);
        ArrayRandomizer randomizer = new ArrayRandomizer(pieces);
        pieces = randomizer.randomize();
        PuzzleI puzzle = new RectanglePiecePuzzle(pieces, columns, rows);
        return puzzle;
    }
}
