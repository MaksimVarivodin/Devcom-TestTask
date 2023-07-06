package Realizations;

public class ArrayReducer  {
    static public<E> E[][] reduce2D(E[][] array, int newRows, int newColumns, int deleteIndex ) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NullPointerException{
        if (array == null || array.length == 0)
            throw new NullPointerException();
        if (deleteIndex >= array.length || deleteIndex < 0)
            throw new ArrayIndexOutOfBoundsException(deleteIndex);
        if(newRows <= 0 || newColumns <= 0)
            throw new IllegalArgumentException();

        E[][] newArray = (E[][])new Object[newRows][newColumns];
        if (newRows < array.length)
            newArray = reduce1D(array, deleteIndex);
        else if (newColumns < array.length)
            for (int i = 0; i < array.length; i++)
                newArray[i] = reduce1D(array[i], deleteIndex);

        return newArray;
    }
    static public<E> E[] reduce1D(E[] array, int deleteIndex)throws ArrayIndexOutOfBoundsException, NullPointerException{

        if (array == null || array.length == 0)
            throw new NullPointerException();
        if (deleteIndex >= array.length || deleteIndex < 0)
            throw new ArrayIndexOutOfBoundsException(deleteIndex);

        E[] newArray = (E[])new Object[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, deleteIndex);
        System.arraycopy(array, deleteIndex+1, newArray, deleteIndex, array.length - deleteIndex -1);

        return newArray;
    }

}
