package Realizations;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RotateImage {
    public static BufferedImage rotate(BufferedImage image, int degrees) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, image.getType());
        Graphics2D graphics = newImage.createGraphics();
        graphics.rotate(Math.toRadians(degrees), width / 2, height / 2);
        graphics.drawImage(image, 0, 0, null);
        return newImage;
    }
}
