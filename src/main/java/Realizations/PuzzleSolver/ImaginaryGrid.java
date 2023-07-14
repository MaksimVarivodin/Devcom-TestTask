package Realizations.PuzzleSolver;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImaginaryGrid {
    private Boolean[][] filled;
    private ImaginaryCell[] array;
    private List<Integer> actualIndexesList;
    private BufferedImage[] source;
    private int columns;
    private int rows;

    public ImaginaryGrid(BufferedImage[] source, int initialPictureXSize, int initialPictureYSize)throws NullPointerException {
        this.source = source;
        columns = calculateColumns(initialPictureXSize);
        rows = calculateRows(initialPictureYSize);
        filled = new Boolean[columns][rows];
        array = new ImaginaryCell[columns * rows];
        actualIndexesList = new ArrayList<>();
        for (int i = 0; i < columns * rows; i++) {
            actualIndexesList.add(i);
        }
    }

    private int calculateColumns(int pictureXSize)throws NullPointerException {
        if (source != null) {
            int picWidth = source[0].getWidth();
            return pictureXSize / picWidth;
        } else throw new NullPointerException("Picture array was null.");
    }

    private int calculateRows(int pictureYSize) throws NullPointerException{
        if (source != null) {
            int picHeight = source[0].getHeight();
            return pictureYSize / picHeight;
        } else throw new NullPointerException("Picture array was null.");
    }

    private boolean movementLeftPossible()throws NullPointerException {
        if (filled != null)
            for (int i = 0; i < rows; i++)
                if (filled[i][0])
                    return false;
                else
                    throw new NullPointerException("Filled array was null.");
        return true;
    }

    private boolean movementRightPossible() throws NullPointerException{
        if (filled != null)
            for (int i = 0; i < rows; i++)
                if (filled[i][columns - 1])
                    return false;
                else
                    throw new NullPointerException("Filled array was null.");
        return true;
    }

    private boolean movementUpPossible()throws NullPointerException {
        if (filled != null)
            for (int i = 0; i < columns; i++)
                if (filled[0][i])
                    return false;
                else
                    throw new NullPointerException("Filled array was null.");
        return true;
    }

    private boolean movementDownPossible()throws NullPointerException {
        if (filled != null)
            for (int i = 0; i < columns; i++)
                if (filled[rows - 1][i])
                    return false;
                else
                    throw new NullPointerException("Filled array was null.");
        return true;
    }

    private int checkNewCellXInBounds() throws NullPointerException{
        if (array != null) {
            ImaginaryCell temp = getLast();
            if (temp.X > columns || temp.X < -1)
                throw new IllegalArgumentException("X coordinate is out of bounds.");
            else if ((temp.X == columns && !movementLeftPossible())
                    || temp.X == -1 && !movementRightPossible())
                throw new IllegalArgumentException("Can't move.");
            else
                // if one element is out of bounds on the left, we need to move it to the right
                if (temp.X == -1)
                    return 1;
                    // if one element is out of bounds on the right, we need to move it to the left
                else if (temp.X == columns)
                    return -1;
                    // otherwise we don't need to move
                else
                    return 0;

        } else
            throw new NullPointerException("Cell array was null.");
    }

    private int checkNewCellYInBounds() throws NullPointerException{
        if (array != null) {
            ImaginaryCell temp = getLast();
            if (temp.Y > rows || temp.Y < -1)
                throw new IllegalArgumentException("Y coordinate is out of bounds.");
            else if ((temp.Y == rows && !movementUpPossible())
                    || temp.Y == -1 && !movementDownPossible())
                throw new IllegalArgumentException("Can't move.");
            else
                // if one element is out of bounds on the top, we need to move it to the bottom
                if (temp.Y == -1)
                    return 1;
                    // if one element is out of bounds on the bottom, we need to move it to the top
                else if (temp.Y == rows)
                    return -1;
                    // otherwise we don't need to move
                else
                    return 0;
        } else
            throw new NullPointerException("Cell array was null.");
    }

    private ImaginaryCell getLast() throws NullPointerException {
        if (array != null)
            return array[array.length - 1];
        else
            throw new NullPointerException("Cell array was null.");

    }
    private boolean setEdgesToCheck() throws ArrayIndexOutOfBoundsException{
        ImaginaryCell temp = getLast();
        if (temp.Y < -1 || temp.Y > columns)
            throw new ArrayIndexOutOfBoundsException("Y coordinate is out of bounds.");
        if (temp.X < -1 || temp.X > rows)
            throw new ArrayIndexOutOfBoundsException("X coordinate is out of bounds.");
        /*
        * Here we see two cases, when the current cell
        * is on the edge of the grid, and not.
        *
        * */
        if (temp.Y > 0 && temp.Y< rows -1){
            if (!filled[temp.Y-1][temp.X] && !(temp.Y -1 == 0 &&(temp.X > 0 || temp.X < columns -1)))
                array[array.length - 1].top = true;
            if (!filled[temp.Y+1][temp.X] && !(temp.Y + 1 == rows- 1 && (temp.X > 0 || temp.X < columns -1)))
                array[array.length - 1].bottom = true;
        } else {
            if (temp.Y == 0 && movementDownPossible())
                array[array.length - 1].top = true;
            if (temp.Y == rows - 1 && movementUpPossible())
                array[array.length - 1].bottom = true;
        }
        if (temp.X > 0 && temp.X < columns -1){
            if (!filled[temp.Y][temp.X-1] && !(temp.X - 1 == 0 && (temp.Y > 0 || temp.Y < rows -1)))
                array[array.length - 1].left = true;
            if (!filled[temp.Y][temp.X+1] && !(temp.X + 1 == columns- 1 && (temp.Y > 0 || temp.Y < rows -1)))
                array[array.length - 1].right = true;
        } else {
            if (temp.X == 0 && movementRightPossible())
                array[array.length - 1].left = true;
            if (temp.X == columns - 1 && movementLeftPossible())
                array[array.length - 1].right = true;
        }
        ImaginaryCell temp2= getLast();
        // At least one is left to fill
        return temp2.top|| temp2.bottom || temp2.left || temp2.right;
    }
    private void moveGridByXY(int x, int y) throws NullPointerException{
        if (array != null){
            for (int i = 0; i < rows * columns; i++) {
                array[i].X += x;
                array[i].Y += y;
            }
        }
        else throw new NullPointerException("Cell array was null.");
    }
    private int[] getTopColor(BufferedImage source){
        int[] topColor = new int[source.getWidth()];
        for (int i = 0; i < source.getWidth(); i++)
            topColor[i] = source.getRGB(i, 0);
        return topColor;
    }
    private int[] getBottomColor(BufferedImage source){
        int[] bottomColor = new int[source.getWidth()];
        for (int i = 0; i < source.getWidth(); i++)
            bottomColor[i] = source.getRGB(i, source.getHeight() - 1);
        return bottomColor;
    }
    private int[] getLeftColor(BufferedImage source){
        int[] leftColor = new int[source.getHeight()];
        for (int i = 0; i < source.getHeight(); i++)
            leftColor[i] = source.getRGB(0, i);
        return leftColor;
    }
    private int[] getRightColor(BufferedImage source){
        int[] rightColor = new int[source.getHeight()];
        for (int i = 0; i < source.getHeight(); i++)
            rightColor[i] = source.getRGB(source.getWidth() - 1, i);
        return rightColor;
    }
    private int[][] getSidesColors(int topLength, int index){
        int[][] sidesColors = null;
        BufferedImage temp = this.source[index];
        if (temp.getHeight()!= temp.getHeight()){
            if (temp.getWidth() == topLength){
                sidesColors = new int[][]{
                        getTopColor(temp),
                        getBottomColor(temp)};
            }
            else if (temp.getHeight() == topLength){
                sidesColors = new int[][]{
                        getLeftColor(temp),
                        getRightColor(temp)};
            }
        }else{
            sidesColors = new int[][]{
                    getTopColor(temp),
                    getLeftColor(temp),
                    getRightColor(temp),
                    getBottomColor(temp)};
        }
        return sidesColors;

    }
    private int getColorDifference(int []a, int [] b){
        int count = 0;
        if (a.length != b.length)
            throw new IllegalArgumentException("Arrays have different length.");
        for (int i = 0; i < a.length; i++) {
            if(a[i]!= b[i])
            {
                count++;
            }
        }
        return count;
    }
    private NewCellProperties compareSidesWithCell(int[] top, int arrayIndex){
        int angle = 90;
        int rotationCount = 4;
        int[][] sides = getSidesColors(top.length, arrayIndex);

        if(this.source[arrayIndex].getHeight()!= this.source[arrayIndex].getHeight()){
            angle+=90;
            rotationCount-=2;
        }
        int bestDifference = Integer.MAX_VALUE;
        int bestAngle = 0;
        int tempAngle = 0;
        for (int i = 0; i < rotationCount; i++) {
            int difference = getColorDifference(sides[i], top);
            if (difference < bestDifference) {
                bestDifference = difference;
                bestAngle = tempAngle;
            }
            tempAngle+=angle;
        }
        return new NewCellProperties(arrayIndex, bestAngle, bestDifference, 0);
    }
    private NewCellProperties compareSidesWithList(int[] side){
        ImaginaryCell temp = getLast();
        if(temp!= null){            
            int iter = 0;
            int indexInList = 0;
            int minValue = Integer.MAX_VALUE;
            NewCellProperties[] possibleCells = new NewCellProperties[actualIndexesList.size()];
            for (int index:
                 actualIndexesList) {
                possibleCells[iter] = compareSidesWithCell(side, index);
                if (possibleCells[iter].difference < minValue){
                    minValue = possibleCells[iter].difference;
                    indexInList = iter;
                    possibleCells[iter].indexInList = iter;
                }
                iter++;                
            }
            actualIndexesList.remove(indexInList);
            return possibleCells[indexInList];
        }
        return new NewCellProperties();
    }
    private int countAvailableSides(){
        int count = 0;
        ImaginaryCell temp = getLast();
        if(temp != null){
            count += temp.top ? 1 : 0;
            count += temp.bottom ? 1 : 0;
            count += temp.left ? 1 : 0;
            count += temp.right ? 1 : 0;
        }
        return count;
    }
    private NewCellProperties compareEdges(){
        ImaginaryCell temp = getLast();
        if(temp == null)
            return new NewCellProperties();
        else {
            NewCellProperties result = new NewCellProperties();
            int counter = 0;
            NewCellProperties[] results = new NewCellProperties[countAvailableSides()];

            if (temp.top){
                results[counter] =  compareSidesWithList(getTopColor(this.source[temp.actualArrayIndex]));
                counter++;
            }
            if (temp.bottom){
                results[counter] =  compareSidesWithList(getBottomColor(this.source[temp.actualArrayIndex]));
                counter++;
            }
            if (temp.left){
                results[counter] =  compareSidesWithList(getLeftColor(this.source[temp.actualArrayIndex]));
                counter++;
            }
            if (temp.right) {
                results[counter] =  compareSidesWithList(getRightColor(this.source[temp.actualArrayIndex]));
            }

            return result;
        }
    }

    private void dropNewPiece(){
        setEdgesToCheck();
        NewCellProperties temp = compareEdges();


    }

}
