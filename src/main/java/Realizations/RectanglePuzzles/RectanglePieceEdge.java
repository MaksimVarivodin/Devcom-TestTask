package Realizations.RectanglePuzzles;

import Constants.RectangleEdgeType;
import Interfaces.EdgeI;
import Interfaces.PuzzlePieceI;

public class RectanglePieceEdge implements EdgeI {
    private RectangleEdgeType rectangleEdgeType;
    /**
     * RectanglePieceEdge constructor
     * @param rectangleEdgeType is the type of the edge of a puzzle piece,
     *                 it is strongly recommended to use RectangleEdgeType enumeration
     * */
    public RectanglePieceEdge(RectangleEdgeType rectangleEdgeType) {
        this.rectangleEdgeType = rectangleEdgeType;
    }

    /**
     * Method that returns RGB array of edge of a puzzle piece
     *
     * @return RGB-colors array
     */
    @Override
    public int[] getRGBArray(PuzzlePieceI puzzlePiece) {

        int[] edgeArray = null;
        switch (rectangleEdgeType) {
            case LEFT, RIGHT:
                edgeArray = new int[puzzlePiece.getImage().getHeight()];
                break;
            case TOP, BOTTOM:
                edgeArray = new int[puzzlePiece.getImage()  .getWidth()];
                break;
            default:
                return null;
        }

        int i = 0;

        switch (rectangleEdgeType) {
            case LEFT:
                for (; i < edgeArray.length; i++)
                    edgeArray[i] = puzzlePiece.getImage().getRGB(0, i);
                break;
            case RIGHT:
                for (; i < edgeArray.length; i++)
                    edgeArray[i] = puzzlePiece.getImage().getRGB(puzzlePiece.getImage().getWidth() - 1, i);
                break;
            case TOP:
                for (; i < edgeArray.length; i++)
                    edgeArray[i] = puzzlePiece.getImage().getRGB(i, 0);
                break;
            case BOTTOM:
                for (; i < edgeArray.length; i++)
                    edgeArray[i] = puzzlePiece.getImage().getRGB(i, puzzlePiece.getImage().getHeight() - 1);
                break;
            default:
                return null;
        }
        return edgeArray;

    }
}
