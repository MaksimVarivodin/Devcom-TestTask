package Realizations.PuzzleSolver;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * @LittleMatrixGenerator is a class that creates shortest distances matrix of (pieces.length X pieces.length) size.
 * In our case, as distances we use difference between contours of pieces.
 * The smaller the value is, the more similar the picture contours are.
 * */
public class LittleGraphGenerator {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static Double[][] generateGraph(Mat[] matPieces) {
        Double[][] matrix = new Double[matPieces.length][matPieces.length];
        matPieces = prepareForContourSearch(matPieces);
        ArrayList<List<MatOfPoint>> contourList = new ArrayList<>();
        for (Mat piece:
             matPieces) {
            contourList.add(findContour(piece));
        }
        for (int i = 0; i < matPieces.length; i++){
            for (int j = 0; j <= i; j++)
                if (i == j)
                    matrix[i][j] = Double.POSITIVE_INFINITY;
                else{
                    matrix[i][j] = findMinDifference(contourList.get(i), contourList.get(j));
                    matrix[j][i] = matrix[i][j];
                }
        }
        return matrix;
    }
    static private Mat[] prepareForContourSearch(Mat[] matPieces){
        for (Mat piece : matPieces) {
            // Convert to grayscale
            Imgproc.cvtColor(piece, piece, Imgproc.COLOR_BGR2GRAY);
            // Apply edge detection
            Imgproc.Canny(piece, piece, 100, 200);
        }
        return matPieces;
    }
    static private List<MatOfPoint> findContour(Mat piece) {
        List<MatOfPoint> contour = new ArrayList<>();
        Imgproc.findContours(piece, contour, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        return contour;
    }
    static private Double findMinDifference(List<MatOfPoint> contours1, List<MatOfPoint> contours2) {
        // Compare contours
        Double minDifference = Double.MAX_VALUE;
        for (MatOfPoint contour1 : contours1)
            for (MatOfPoint contour2 : contours2) {
                Double difference = Imgproc.matchShapes(contour1, contour2, Imgproc.CV_CONTOURS_MATCH_I1, 0);
                if (difference < minDifference)
                    minDifference = difference;
            }
        return minDifference;

    }

}
