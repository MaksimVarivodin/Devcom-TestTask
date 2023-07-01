package Realizations.SquarePuzzles;

import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;
import Realizations.RectanglePuzzles.RectanglePiecePuzzle;

public class SquarePiecePuzzle extends RectanglePiecePuzzle implements PuzzleI {
    public SquarePiecePuzzle(PuzzlePieceI[] pieces, int columns, int rows) {
        super(pieces, columns, rows);
    }
}
