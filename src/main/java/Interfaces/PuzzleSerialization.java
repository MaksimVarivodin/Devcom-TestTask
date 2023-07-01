package Interfaces;

import java.io.Serializable;
import java.util.List;

public interface PuzzleSerialization extends Serializable  {
    String toString();
    void fromString(String s);

}
