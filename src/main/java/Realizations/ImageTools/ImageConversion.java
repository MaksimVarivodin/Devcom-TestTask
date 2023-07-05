package Realizations.ImageTools;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ImageConversion {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    static public Mat bufferedImageToMat(BufferedImage image){
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
}
