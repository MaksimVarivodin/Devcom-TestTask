package Services;

import Interfaces.PuzzleI;
import Realizations.PuzzleSolver.PuzzleSolver;
import Realizations.PuzzleSolver.PuzzleSolverGenerator;

public class PuzzleService {
    PuzzleI puzzle;
    PuzzleSolver solver;
    public PuzzleService(PuzzleI puzzle) {
        this.puzzle = puzzle;
        solver = PuzzleSolverGenerator.generate(puzzle);
    }


}
