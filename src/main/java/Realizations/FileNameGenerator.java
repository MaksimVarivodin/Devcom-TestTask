package Realizations;

import java.util.Random;

public class FileNameGenerator {
    public static String generateFileName(int nameLength) {
        return  (new Random())
                .ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(nameLength)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

}
