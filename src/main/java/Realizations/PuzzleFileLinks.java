package Realizations;

import java.io.Serializable;
import java.util.List;

public class PuzzleFileLinks implements Serializable {
    List<String> fileNames;
    int cols, rows;

    public PuzzleFileLinks(List<String> fileNames, int cols, int rows) {
        this.fileNames = fileNames;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
