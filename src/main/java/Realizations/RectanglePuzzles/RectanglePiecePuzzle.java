package Realizations.RectanglePuzzles;

import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;
import Realizations.FileNameGenerator;
import Realizations.PuzzleFileLinks;
import Realizations.Serializer;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RectanglePiecePuzzle implements PuzzleI {
    PuzzlePieceI[] pieces;
    int columns;
    int rows;

    int currentPieceX;
    int currentPieceY;

    public RectanglePiecePuzzle(PuzzlePieceI[] pieces, int cols, int rows) {
        this.pieces = pieces;
        this.columns = cols;
        this.rows = rows;
        this.currentPieceX = 0;
        this.currentPieceY = 0;
    }
    public void setCurrent(int currentPieceX, int currentPieceY){
        this.currentPieceX = currentPieceX;
        this.currentPieceY = currentPieceY;
    }
    /**
     * Swaps selected puzzle piece with left piece,
     * if selected is not on left edge of a puzzle.
     *
     */
    @Override
    public void moveLeft() {
        if (currentPieceX >= 1 && currentPieceX < columns && currentPieceY >= 0 && currentPieceY < rows){
            PuzzlePieceI buffer  = new RectanglePiece(pieces[currentPieceY * columns + currentPieceX - 1]);
            pieces[currentPieceY * columns + currentPieceX - 1] = pieces[currentPieceY * columns + currentPieceX];
            pieces[currentPieceY * columns + currentPieceX] = buffer;
        }
    }

    /**
     * Swaps selected puzzle piece with right piece,
     * if selected is not on right edge of a puzzle.
     *
     */
    @Override
    public void moveRight() {
        if (currentPieceX >= 0 && currentPieceX < columns -1 && currentPieceY >= 0 && currentPieceY < rows){
            PuzzlePieceI buffer  = pieces[currentPieceY * columns + currentPieceX + 1];
            pieces[currentPieceY * columns + currentPieceX + 1] = pieces[currentPieceY * columns + currentPieceX];
            pieces[currentPieceY * columns + currentPieceX] = buffer;
        }
    }

    /**
     * Swaps selected puzzle piece with top piece,
     * if selected is not on top edge of a puzzle.
     *
     */
    @Override
    public void moveUp() {
        if (currentPieceX >= 0 && currentPieceX < columns && currentPieceY >= 1 && currentPieceY < rows){
            PuzzlePieceI buffer  = pieces[(currentPieceY -1) * columns + currentPieceX];
            pieces[(currentPieceY - 1) * columns + currentPieceX] = pieces[currentPieceY * columns + currentPieceX];
            pieces[currentPieceY * columns + currentPieceX] = buffer;
        }
    }

    /**
     * Swaps selected puzzle piece with bottom piece,
     * if selected is not on bottom edge of a puzzle.
     *
     */
    @Override
    public void moveDown() {
        if (currentPieceX >= 0 && currentPieceX < columns && currentPieceY >= 0 && currentPieceY < rows -1){
            PuzzlePieceI buffer  = pieces[(currentPieceY +1) * columns + currentPieceX];
            pieces[(currentPieceY + 1) * columns + currentPieceX] = pieces[currentPieceY * columns + currentPieceX];
            pieces[currentPieceY * columns + currentPieceX] = buffer;
        }
    }

    /**
     * Saves puzzle to directory -> path/puzzleName
     *
     * @param format     is the extension of puzzle pieces
     * @param path       is the directory where the directory of a puzzle will be created
     * @param puzzleName is the name of directory where puzzle will be saved
     */
    @Override
    public void savePuzzle(String format, String path, String puzzleName) throws IOException {
        File puzzleDir = new File(path +"/"+  puzzleName + "/images" );
        puzzleDir.mkdirs();
        List<String> fileNames = new ArrayList<>();
        for (PuzzlePieceI piece : pieces) {
            String fileName = FileNameGenerator.generateFileName(16);
            fileNames.add(fileName);
            File imageFile = new File(puzzleDir.getPath() + "/" + fileName + "." + format);
            ImageIO.write(piece.getImage(), format, imageFile);

        }
        String data = Serializer.Serialize(new PuzzleFileLinks(fileNames, columns, rows));
        File puzzleFile = new File(path + "/" + puzzleName +"/" + puzzleName + ".puzzle");
        try(PrintWriter writer = new PrintWriter(puzzleFile)){
            writer.println(data);
        }
    }

    /**
     * Opens puzzle from path -> path/puzzleName
     * Also tries to get all images signed in .puzzle file
     *
     * @param path       is the dir where are the pictures and .puzzle file
     * @param puzzleName is the name of the file with the links to images on puzzle(their location on the grid), also it must have extension ".puzzle"
     */
    @Override
    public void openPuzzle(String path, String puzzleName) throws IOException, ClassNotFoundException {
        String data = new String(Files.readAllBytes(Paths.get(path + "/"+ puzzleName)));

        if (!FilenameUtils.getExtension(puzzleName).equals("puzzle")){
            throw new IOException("Not a .puzzle file");
        }
        PuzzleFileLinks links = Serializer.Deserialize(data);
        if (links.getFileNames().size() != links.getCols() * links.getRows()) {
            throw new IOException("Wrong puzzle file");
        }
        PuzzlePieceI[] pieces = new PuzzlePieceI[links.getFileNames().size()];
        int i = 0;
        for (String fileName : links.getFileNames()) {
            BufferedImage img = ImageIO.read(new File(fileName));
            pieces[i] = new RectanglePiece(img);
            i++;
        }
        this.columns = links.getCols();
        this.rows = links.getRows();
        this.pieces = pieces;
    }

    /**
     * Rotates selected puzzle piece anticlockwise.
     *
     */
    @Override
    public void rotateLeft() {
        pieces[currentPieceY * columns + currentPieceX].rotateLeft();
    }

    /**
     * Rotates selected puzzle piece clockwise.
     *
     */
    @Override
    public void rotateRight() {
        pieces[currentPieceY * columns + currentPieceX].rotateRight();
    }

    /**
     * Returns array of PuzzlePieces
     */
    @Override
    public PuzzlePieceI[] getPuzzlePieces() {
        return this.pieces;
    }


    /**
     * @return number of columns
     */
    @Override
    public int getColumns() {
        return  this.columns;
    }

    /**
     * @return number of rows
     */
    @Override
    public int getRows() {
        return this.rows;
    }
}
