package Realizations.PuzzleSolver;


import Realizations.ArrayReducer;

/**
 * @LittleSGraphAlgorhythm is a class that performs
 */

public class LittleSGraphAlgorhythm {

    Double [][] graph;

    public LittleSGraphAlgorhythm(Double[][] graph) {
        this.graph = graph;
    }

    public GraphEdge[] calculateTraversal() {
        int vertices = graph.length;
        GraphEdge[] traversal = new GraphEdge[vertices];
        for (int i = 0; i < graph.length - 2; i++) {
            graphReduction();
            traversal[i] = getHamiltonianCycleEdge();
            vertices--;
            graph = ArrayReducer.<Double>reduce2D(graph, vertices, vertices + 1, traversal[i].verticeA);
            graph = ArrayReducer.<Double>reduce2D(graph, vertices, vertices, traversal[i].verticeB);
            graph[traversal[i].verticeA][traversal[i].verticeB] = Double.POSITIVE_INFINITY;
        }
        if (vertices == 2){
           // if (graph[0][1]== 0 && graph[1][0] == 0)



        }
        return traversal;
    }

    private void graphReduction() {

        for (int i = 0; i < graph.length; i++) {
            Double minY = Double.MAX_VALUE;
            Double minX = Double.MAX_VALUE;

            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] < minX)
                    minX = graph[i][j];
                if (graph[j][i] < minY)
                    minY = graph[j][i];
            }
            // if there are no zeros in a row
            if (minX > 0)
                for (int j = 0; j < graph.length; j++)
                    graph[i][j] -= minX;

            // if there are no zeros in a column
            if (minY > 0)
                for (int j = 0; j < graph.length; j++)
                    graph[j][i] -= minY;

        }
    }

    private GraphEdge getHamiltonianCycleEdge() {
        GraphEdge[] edges = new GraphEdge[graph.length];
        Double maxMinSum = Double.MIN_VALUE;
        Double[] minSum = new Double[graph.length];
        for (int c = 0; c < graph.length; c++) {

            Double minY = Double.MAX_VALUE;
            Double minX = 0.0;
            for (int i = 0; i < graph.length; i++) {
                minX = Double.MAX_VALUE;
                // searching for the minimum in the row
                for (int j = 0; j < graph.length; j++)
                    if (graph[i][j] < minX)
                        if (graph[i][j] != 0)
                            minX = graph[i][j];
                        else
                            edges[i] = new GraphEdge(i, j);
                // searching for the minimum in the column
                if (graph[i][c] < minY)
                    if (edges[i].verticeB != c)
                        minX = graph[i][c];
            }
            minSum[c] = minY + minX;
        }
        int index = 0;
        for (int i = 0; i < graph.length; i++)
            if (minSum[i] > maxMinSum) {
                maxMinSum = minSum[i];
                index = i;
            }
        return edges[index];
    }


}

