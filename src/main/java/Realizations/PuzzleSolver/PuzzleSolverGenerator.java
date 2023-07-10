package Realizations.PuzzleSolver;
import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;
import Realizations.ImageTools.ImageConversion;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import static Realizations.PuzzleSolver.GraphGenerator.generateGraph;

public class PuzzleSolverGenerator {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    static public PuzzleSolver generate(PuzzleI puzzle) throws IllegalArgumentException, NullPointerException {
        PuzzlePieceI [] pieces = puzzle.getPuzzlePieces();
        Mat[] matPieces = new Mat[puzzle.getPuzzlePieces().length];
        for (int i =0  ; i< puzzle.getPuzzlePieces().length; i++)
                matPieces[i] = ImageConversion.bufferedImageToMat(pieces[i].getImage());
        Graph compatibilityGraph = new Graph(generateGraph(matPieces));
        //***/////*/*/*/**/
        Integer[] path = (Integer[]) compatibilityGraph.branchAndBoundPathMethod().toArray();

        return new PuzzleSolver(puzzle, path);
    }

}
