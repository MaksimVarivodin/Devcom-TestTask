package Realizations.PuzzleSolver;
import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;
import Realizations.ImageTools.ImageConversion;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;

import java.awt.image.BufferedImage;

public class PuzzleSolverGenerator {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    static public PuzzleSolver generate(PuzzleI puzzle){
        PuzzlePieceI [] pieces = puzzle.getPuzzlePieces();
        Mat[] matPieces = new Mat[puzzle.getPuzzlePieces().length];
        for (int i =0  ; i< puzzle.getPuzzlePieces().length; i++)
                matPieces[i] = ImageConversion.bufferedImageToMat(pieces[i].getImage());

        //***/////*/*/*/**/

        int[][] m = new int[0][0];



        return new PuzzleSolver(puzzle,m );
    }

}
