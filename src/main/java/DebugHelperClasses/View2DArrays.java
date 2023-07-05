package DebugHelperClasses;


public class View2DArrays {
    static public<T>  String view(T[][]items){
        String output = "";
        for (int i = 0; i < items.length; i++){
            for (int j = 0; j < items[i].length; j++)
                output += items[i][j].toString() + "\t";
            output += "\n";
        }
        return output;
    }
}
