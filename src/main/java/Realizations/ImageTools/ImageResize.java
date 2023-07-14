package Realizations.ImageTools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageResize {
    public static BufferedImage resize(BufferedImage image, int newWidth, int newHeight){
        Image tmp = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
}
