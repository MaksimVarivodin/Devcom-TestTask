package Realizations.Edges;

import Interfaces.EdgeI;

import java.awt.image.BufferedImage;


/**
 * @RectangleEdgesGenerator is a class to generate edges of a puzzle piece
 */
public class RectangleEdgesGenerator {

    /**
     * Method that generates EdgeI[] of a rectangle piece.
     *
     * @param image  is the image of a puzzle piece
     * @return array of EdgeI with rgb int values
     */
    public static EdgeI[] generateEdges(BufferedImage image) {
        int i = 0;
        int[] rgbEdgeArray  = new int[image.getHeight()];
        EdgeI[] array = new RectanglePieceEdge[4];
        // creating left edge
        for (; i < rgbEdgeArray.length; i++)
            rgbEdgeArray[i] = image.getRGB(0, i);
        array[0] = new RectanglePieceEdge(rgbEdgeArray);

        // creating right edge
        for (; i < rgbEdgeArray.length; i++)
            rgbEdgeArray[i] = image.getRGB(image.getWidth() - 1, i);
        array[1] = new RectanglePieceEdge(rgbEdgeArray);

        rgbEdgeArray = new int[image.getWidth()];

        // creating top edge
        for (; i < rgbEdgeArray.length; i++)
            rgbEdgeArray[i] = image.getRGB(i, 0);
        array[2] = new RectanglePieceEdge(rgbEdgeArray);

        // creating bottom edge
        for (; i < rgbEdgeArray.length; i++)
            rgbEdgeArray[i] = image.getRGB(i, image.getHeight() - 1);
        array[3] = new RectanglePieceEdge(rgbEdgeArray);

        return array;
    }

}
