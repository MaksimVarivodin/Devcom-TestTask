package Realizations;

import Interfaces.PuzzlePieceI;

public class ArrayRandomizer<valuesType extends PuzzlePieceI> {
    private valuesType[] array;

    public ArrayRandomizer(valuesType[] array) {
        this.array = array;
    }

    public valuesType[] randomize() {
        int[] indexes = new int[array.length];
        valuesType[] newArray =(valuesType[]) new Object[array.length];
        int size = array.length;
        for (int i = 0; i < array.length; i++)
            indexes[i] = i;
        for (int iter = 0; iter < array.length; iter++) {
            int random = (int) (Math.random() * size);
            int index_value = -1;
            int j=0 ;
            int i = 0;
            for(;   j   <   random  && i< size; i++)    {
                if (indexes[i]!= -1){
                    index_value = indexes[i];
                    j++;
                }
            }
            indexes[i] = -1;
            newArray[index_value] = array[iter];
            size--;
        }
        return newArray;
    }

}
