package Realizations.PuzzleSolver;

public class GraphEdge {
    int verticeA;
    int verticeB;

    public GraphEdge(int verticeA, int verticeB) {
        this.verticeA = verticeA;
        this.verticeB = verticeB;
    }

    public int getVerticeA() {
        return verticeA;
    }

    public int getVerticeB() {
        return verticeB;
    }
}
