package Interfaces;


import Realizations.PuzzleSolver.Compatibility;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * @PuzzlePieceI is an interface to give access to actions with PuzzlePiece.
 * */
public interface PuzzlePieceI {
    /**
     * Anticlockwise rotation
     * */
    void rotateLeft();
    /**
     * Clockwise rotation
     * */
    void rotateRight();
    EdgeI[] getEdges();
    /**
     * Returns puzzlePiece image
     * */
    BufferedImage getImage();
    static Compatibility compatibility(PuzzlePieceI piece1, PuzzlePieceI piece2)throws NullPointerException{
        if (piece1 == null || piece2 == null )
            throw new NullPointerException("Pieces are null");
        if( piece1.getEdges().length != piece2.getEdges().length)
            throw new NullPointerException("Edges are not equal");

        int[] values = new int[piece1.getEdges().length * piece2.getEdges().length];

        for (int i =0 ; i< piece1.getEdges().length; i++)
            for (int j =0 ; j< piece2.getEdges().length; j++)
                if (piece1.getEdges()[i].getRGBArray().length == piece2.getEdges()[j].getRGBArray().length)
                    values[i] = EdgeI.compatibility(piece1.getEdges()[i], piece2.getEdges()[j]);

        int max = Arrays.stream(values).max().getAsInt();

        return new Compatibility(max);
    }
}
