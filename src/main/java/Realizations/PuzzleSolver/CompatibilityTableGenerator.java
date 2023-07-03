package Realizations.PuzzleSolver;

import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;




public class CompatibilityTableGenerator {
    public static CompatibilityTable getCompatibilityTable(PuzzleI puzzle) {
        int length = puzzle.getPuzzlePieces().length;
        Compatibility[][] table = new Compatibility[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (i== j)
                    table[i][j] = new Compatibility(0);
                else
                table[i][j] = PuzzlePieceI.compatibility(puzzle.getPuzzlePieces()[i], puzzle.getPuzzlePieces()[j]);
                table[j][i] = table[i][j];
            }
        }
        return new CompatibilityTable(table);
    }

}
