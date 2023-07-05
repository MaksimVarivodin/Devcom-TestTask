package Realizations.ImageTools;

import java.awt.image.BufferedImage;

public class ImageCutter {
    public static BufferedImage[] cutImage(BufferedImage image, int rows, int columns){
        int width = image.getHeight() / columns;
        int height = image.getWidth() / rows;
        BufferedImage []array = new BufferedImage[columns *rows];
        for (int y = 0; y < rows; y++)
            for (int x = 0; x < columns; x++)
                array[y * columns + x] = image.getSubimage(x * width, y * height, width, height);
        return array;
    }
}
