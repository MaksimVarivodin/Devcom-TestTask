package Realizations.PuzzleSolver;

import Realizations.ArrayReducer;

import java.util.*;

class Graph {


    private static final Double ELEMENT_DIAGONAL = Double.POSITIVE_INFINITY;

    private Double[][] matrix;
    private Integer[] vi;
    private Integer[] vj;

    Graph(Double[][] matrix) throws IllegalArgumentException, NullPointerException {
        checkMatrix();
        this.matrix = matrix;
        fillVertices(this.matrix.length);

    }

    public List<Integer> branchAndBoundPathMethod() {
        List<Integer> result = new ArrayList<>();
        Integer expectedPathSize = matrix.length;


        while (this.matrix.length > 0 && result.size() != expectedPathSize) {
            Double[] di = getMinimumFromColumnRow(this.matrix, false);
            this.matrix = diffMatrix(this.matrix, di, false);

            Double[] dj = getMinimumFromColumnRow(this.matrix, true);
            this.matrix = diffMatrix(this.matrix, dj, true);

            Pare<Integer> maxZero = getMaximumZeroPare(this.matrix);


            if (!result.contains(vi))
                result.add(this.vi[maxZero.i]);

            if (!result.contains(vj))
                result.add(this.vj[maxZero.j]);

            this.matrix[maxZero.j][maxZero.i] =  ELEMENT_DIAGONAL;

            // reducing data
            ArrayReducer.reduce2D(matrix, this.matrix.length -1, this.matrix.length, maxZero.i);
            ArrayReducer.reduce2D(matrix, this.matrix.length, this.matrix.length, maxZero.j);
            // reducing vertices arrays
            ArrayReducer.reduce1D(vi, maxZero.i);
            ArrayReducer.reduce1D(vj, maxZero.j);
        }

        if (!result.isEmpty()) {
            result.add(result.get(0));
        }
        return result;

    }

    private Pare getMaximumZeroPare(Double[][] matrix) {
        Pare<Integer> result = new Pare(0, 0);
        Double max = 0.0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                Double value = matrix[i][j];
                if (value == 0) {
                    Pare<Double> pare = getHorizontalVerticalMinimum(matrix, i, j);
                    if (pare.i + pare.j > max) {
                        max = pare.i + pare.j;
                        result = new Pare(i, j);
                    }

                }
            }
        }
        return result;
    }

    private Pare getHorizontalVerticalMinimum(Double[][] matrix, int i, int j) {
        Double x = Double.MAX_VALUE;
        Double y = Double.MAX_VALUE;


        for (int count = 0; count < matrix.length; count++) {
            // searching for vertical minimum
            if (count != i && matrix[count][j] != ELEMENT_DIAGONAL && matrix[count][j] < y)
                y = matrix[count][j];
            // searching for horizontal minimum
            if (count != j && matrix[i][count] != ELEMENT_DIAGONAL && matrix[i][count] < x)
                x = matrix[i][count];
        }
        x = x == Double.MAX_VALUE ? 0 : x;
        y = y == Double.MAX_VALUE ? 0 : y;
        return new Pare(x, y);
    }


    private Double[] getMinimumFromColumnRow(Double[][] matrix, boolean row) {
        Double[] d = new Double[matrix.length];
        Arrays.fill(d, Double.MAX_VALUE);
        int count = 0;
        for (Double[] array : matrix) {
            for (Double value : array) {
                if (value != ELEMENT_DIAGONAL && d[count] > value)
                    d[count] = value;
                if (row) count++;
            }
            count = row ? 0 : count + 1;
        }
        return d;
    }


    private Double[][] diffMatrix(Double[][] matrix, Double[] d, boolean row) {
        for (int i = 0; i < matrix.length; i++)

            for (int j = 0; j < matrix[i].length; j++)

                if (matrix[i][j] != ELEMENT_DIAGONAL)

                    if (row)
                        matrix[i][j] -= d[j];
                    else
                        matrix[i][j] -= d[i];

        return matrix;
    }


    private void fillVertices(int size) {
        vi = new Integer[size];
        vj = new Integer[size];
        for (int v = 1; v <= size; v++)
            vi[v - 1] = v;
        System.arraycopy(vi, 0, vj, 0, vi.length);

    }



    private void checkMatrix() throws IllegalArgumentException, NullPointerException {
        if (matrix != null) {
            for (Double[] array : this.matrix) {
                if (array.length != matrix.length) {
                    throw new IllegalArgumentException("Matrix is not square");
                }
            }
        } else {
            throw new NullPointerException("Matrix was null.");
        }
    }

    class Pare<T extends Number & Comparable> {
        private final T i;
        private final T j;

        Pare(T i, T j) {
            this.i = i;
            this.j = j;
        }
    }
}
