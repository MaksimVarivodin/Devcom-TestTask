package Realizations;

import Interfaces.PuzzlePieceI;

public class ArrayRandomizer<valuesType extends PuzzlePieceI> {
    private valuesType[] array;

    public ArrayRandomizer(valuesType[] array) {
        this.array = array;
    }

    public valuesType[] randomize() {
        int[] indexes = new int[array.length];
        valuesType[] newArray = (valuesType[]) new PuzzlePieceI[array.length];
        int size = array.length;
        for (int i = 0; i < array.length; i++)
            indexes[i] = i;
        for (int iter = 0; iter < array.length; iter++) {
            int random = (int) (Math.random() * (size));
            int j = 0;
            int i = 1;
            while(i< indexes.length &&(j < random || indexes[i - 1] == -1)){
                if (indexes[i -1] != -1)
                    j++;
                i++;
            }

            newArray[indexes[i -1]] = array[iter];
            indexes[i -1] = -1;
            size--;
        }
        return newArray;
    }

}
