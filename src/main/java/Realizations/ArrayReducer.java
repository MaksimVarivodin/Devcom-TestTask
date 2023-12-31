package Realizations;

import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayReducer  {
    static public<E extends Number & Comparable<E> & ConstantDesc & Constable> E[][] reduce2D(E[][] array, int newRows, int newColumns, int deleteIndex ) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NullPointerException{
        if (array == null || array.length == 0)
            throw new NullPointerException();
        if (deleteIndex >= array.length || deleteIndex < 0)
            throw new ArrayIndexOutOfBoundsException(deleteIndex);
        if(newRows <= 0 || newColumns <= 0)
            throw new IllegalArgumentException();

        E[][] newArray = (E[][]) new Number[newRows][newColumns];
        if (newRows < array.length)
            newArray =  reduce2DRows(array, deleteIndex);
        else if (newColumns < array.length)
            for (int i = 0; i < array.length; i++)
                newArray[i] = reduce1D(array[i], deleteIndex);

        return (E[][])newArray;
    }
    static private <  E extends Number & Comparable<E> & ConstantDesc & Constable> E[][] reduce2DRows(E[][] array,  int deleteIndex ) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NullPointerException{
        if (array == null || array.length == 0)
            throw new NullPointerException();
        if (deleteIndex >= array.length || deleteIndex < 0)
            throw new ArrayIndexOutOfBoundsException(deleteIndex);
        E[][] newArray = (E[][]) new Number[array.length -1][];
        for (int i = 0; i < deleteIndex; i++) {
            newArray[i] =  array[i];
        }
        for (int i = deleteIndex+1; i < array.length; i++) {
            newArray[i] =   array[i-1];
        }
        return newArray;
    }
    static public<E extends Number & Comparable<E> & ConstantDesc & Constable> E[] reduce1D(E[] array, int deleteIndex)throws ArrayIndexOutOfBoundsException, NullPointerException{

        if (array == null || array.length == 0)
            throw new NullPointerException();
        if (deleteIndex >= array.length || deleteIndex < 0)
            throw new ArrayIndexOutOfBoundsException(deleteIndex);


        E[] newArray = (E[]) new Number[array.length -1];

        System.arraycopy(array, 0, newArray, 0, deleteIndex);
        System.arraycopy(array, deleteIndex+1, newArray, deleteIndex, array.length - deleteIndex -1);

        return newArray;
    }

}
