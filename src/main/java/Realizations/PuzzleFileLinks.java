package Realizations;

import java.io.Serializable;
import java.util.List;

public class PuzzleFileLinks implements Serializable {
    List<String> fileNamesUns;



    List<String> fileNamesSolved;
    int cols, rows;

    public PuzzleFileLinks(List<String> fileNames,List<String> fileNamesSolved, int cols, int rows) {
        this.fileNamesUns = fileNames;
        this.fileNamesSolved = fileNamesSolved;
        this.cols = cols;
        this.rows = rows;
    }
    public List<String> getFileNamesSolved() {
        return fileNamesSolved;
    }
    public List<String> getFileNamesUns() {
        return fileNamesUns;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
