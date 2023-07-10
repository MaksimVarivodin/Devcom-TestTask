package Realizations.RectanglePuzzles;

import Interfaces.PuzzlePieceI;
import Realizations.ImageTools.ImageRotate;

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
        if (piece.getWidth() == piece.getHeight()) {
            piece = ImageRotate.rotate(piece, 180);
        }
    }

    /**
     * Returns puzzlePiece image
     */
    @Override
    public BufferedImage getImage() {
        return piece;
    }

    /**
     * @param path
     */
    @Override
    public void savePuzzlePiece(String path, String fileName, String format) throws IOException {
        File imageFile = new File(path + "/" + fileName + "." + format);
        ImageIO.write(piece, format, imageFile);
    }

    /**
     * opens image and creates PuzzlePiece
     *
     * @param fileName
     */
    @Override
    public PuzzlePieceI openPuzzlePiece(String fileName) throws IOException {
        return new RectanglePiece(ImageIO.read(new File(fileName)));
    }


}
