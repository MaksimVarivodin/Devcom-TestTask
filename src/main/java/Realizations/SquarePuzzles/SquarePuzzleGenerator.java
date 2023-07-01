package Realizations.SquarePuzzles;

import Realizations.RectanglePuzzles.RectanglePuzzleGenerator;
import Interfaces.PuzzlePieceI;
import Realizations.ImageCutter;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SquarePuzzleGenerator extends RectanglePuzzleGenerator {
    @Override
    public PuzzlePieceI[] generatePieceArray(BufferedImage image, int rows, int columns) throws IOException {
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
}
