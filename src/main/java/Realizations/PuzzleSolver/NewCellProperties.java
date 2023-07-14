package Realizations.PuzzleSolver;

public class NewCellProperties {
    int index;
    int indexInList;
    int angle;
    int difference;

    public NewCellProperties(int index, int angle, int difference, int indexInList) {
        this.index = index;
        this.angle = angle;
        this.difference = difference;
        this.indexInList = indexInList;
    }

    public NewCellProperties() {
    }
}
