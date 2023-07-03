package Realizations.Edges;

import Interfaces.EdgeI;

public class RectanglePieceEdge implements EdgeI {
    private int[] edgeArray;

    public RectanglePieceEdge(int[] edgeArray) {
        this.edgeArray = edgeArray;
    }

    /**
     * Method that returns RGB array of edge of a puzzle piece
     *
     * @return RGB-colors array
     */
    @Override
    public int[] getRGBArray() {
        return edgeArray;
    }
}
