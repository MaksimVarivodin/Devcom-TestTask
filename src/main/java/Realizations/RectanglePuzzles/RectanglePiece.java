package Realizations.RectanglePuzzles;

import Interfaces.PuzzlePieceI;
import Realizations.ImageTools.ImageRotate;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RectanglePiece implements PuzzlePieceI {

    protected BufferedImage piece;

    public RectanglePiece(BufferedImage image) {
        piece = image;
    }

    public RectanglePiece(PuzzlePieceI piece) {
        this(piece.getImage());
    }

    /**
     * Anticlockwise rotation
     */
    @Override
    public void rotateLeft() {
        piece = ImageRotate.rotate(piece, -180);

    }

    /**
     * Clockwise rotation
     */
    @Override
    public void rotateRight() {
        piece = ImageRotate.rotate(piece, 180);
    }

    /**
     * Returns puzzlePiece image
     */
    @Override
    public BufferedImage getImage() {
        return piece;
    }

    @Override
    public void setImage(BufferedImage image) {
        piece = image;
    }

    /**
     * @param path
     * @return
     */

    public static String savePuzzlePiece(PuzzlePieceI piece, String path, String fileName, String format) {
        String imagePath = path + "\\" + fileName + format;
        File imageFile = new File(imagePath);
        boolean written = false;
        String ext = FilenameUtils.getExtension(format);
        try {
            imageFile.createNewFile();
            imageFile.setWritable(true);
            written = ImageIO.write(piece.getImage(), ext, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!written)
            return null;
        return imagePath;
    }

    /**
     * opens image and creates PuzzlePiece
     *
     * @param fileName
     */

    public static PuzzlePieceI openPuzzlePiece(String fileName) throws IOException {
        return new RectanglePiece(ImageIO.read(new File(fileName)));
    }

    public static PuzzlePieceI copy(PuzzlePieceI piece){
        return new RectanglePiece(piece.getImage());
    }
    public static PuzzlePieceI[] copy(PuzzlePieceI[] pieces){
        PuzzlePieceI[] result = new RectanglePiece[pieces.length];
        for (int i = 0; i < pieces.length; i++)
            result[i] = copy(pieces[i]);
        return result;
    }

}
