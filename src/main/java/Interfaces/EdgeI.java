package Interfaces;


public interface EdgeI {
    /**
     * Method that returns RGB array of edge of a puzzle piece
     * @return RGB-colors array
     * */
    int[] getRGBArray();

    /**
     * Static method that compares two edges
     * @throws NullPointerException if edges are null or if their arrays are null or not of equal length
     * @return true if edges are equal, else false
     * */
    static int compatibility(EdgeI e1, EdgeI e2)throws NullPointerException{
        int [] r1 = e1.getRGBArray();
        int [] r2 = e2.getRGBArray();
        if (e1 == null || e2 == null)
            throw new NullPointerException("Edges were null");
        if ( r1== null ||  r2 != null ||  r1.length != r2.length)
            throw new NullPointerException("Edges-arrays error");
        int count = 0;
        for (int i = 0; i < r1.length; i++) {
            if (r1[i] == r2[i])
                count++;
        }
        return count;
    }
}
