package Realizations.ImageTools;

import java.awt.image.BufferedImage;

public class ImageEquality {
    public static boolean equal (BufferedImage image1, BufferedImage image2) {
        // The images must be the same size.
        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
            return false;
        }

        int width  = image1.getWidth();
        int height = image1.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }
}
