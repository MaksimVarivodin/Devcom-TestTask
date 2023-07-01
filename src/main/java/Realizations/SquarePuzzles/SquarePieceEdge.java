package Realizations.SquarePuzzles;

import Constants.RectangleEdgeType;
import Interfaces.EdgeI;
import Interfaces.PuzzlePieceI;
import Realizations.RectanglePuzzles.RectanglePieceEdge;

public class SquarePieceEdge extends RectanglePieceEdge implements EdgeI {

    /**
     * RectanglePieceEdge constructor
     * @param rectangleEdgeType is the type of the edge of a puzzle piece,
     *                 it is strongly recommended to use RectangleEdgeType enumeration
     * */
    public SquarePieceEdge(RectangleEdgeType rectangleEdgeType) {
        super(rectangleEdgeType);
    }
}
