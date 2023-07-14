package Realizations.RectanglePuzzles;

import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;
import Realizations.ArrayRandomizer;
import Realizations.FileNameGenerator;
import Realizations.ImageTools.ImageCutter;
import Realizations.PuzzleFileLinks;
import Realizations.Serializer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RectanglePuzzleGenerator{
    /**
     * Cuts image into pieces. Height and width of pieces are determined by rows and columns.

     * @param image   image to cut
     * @param rows     number of rows
     * @param columns  number of columns
     * @return PuzzlePieceI[]  array of PuzzlePieces
     */

    public static PuzzlePieceI[] generatePieceArray(BufferedImage image, int rows, int columns) throws IOException {
        BufferedImage[] array = ImageCutter.cutImage(image, rows, columns);
        PuzzlePieceI [] result = new RectanglePiece[array.length];
        int iter = 0;
        for (BufferedImage img:
             array) {
            result[iter]= new RectanglePiece(img);
            iter++;
        }
        return result;
    }
    private static PuzzlePieceI[] randomRotate(PuzzlePieceI[] pieces){
        PuzzlePieceI [] result = pieces.clone();
        for (PuzzlePieceI piece:
                result) {
            for (int i = 0; i< Math.random() * 2; i++) {
                piece.rotateLeft();
            }
        }
        return result;
    }
    /**
     * Cuts image into pieces. Height and width of pieces are determined by rows and columns.
     *
     * @param image    image to cut
     * @param rows     number of rows
     * @param columns  number of columns
     * @return PuzzleI  Puzzle ready for use
     */

    public static PuzzleI generatePuzzle(BufferedImage image, int rows, int columns) throws IOException {
        PuzzlePieceI [] pieces = generatePieceArray(image, rows, columns);
        PuzzlePieceI[]randomRotated = randomRotate(RectanglePiece.copy( pieces));
        ArrayRandomizer randomizer = new ArrayRandomizer(randomRotated);
        randomRotated = randomizer.randomize();
        PuzzleI puzzle = new RectanglePiecePuzzle(randomRotated, pieces ,  columns, rows);
        return puzzle;
    }

    /**
     * Saves puzzle to directory -> path/puzzleName
     *
     * @param format     is the extension of puzzle pieces
     * @param path       is the directory where the directory of a puzzle will be created
     * @param puzzleName is the name of directory where puzzle will be saved
     */

    public static  void savePuzzle(PuzzleI puzzle, String format, String path, String puzzleName) throws IOException {
        File puzzleDir = new File(path +"\\"+  FilenameUtils.removeExtension(puzzleName) + "\\images" );
        if (puzzleDir.exists())
            FileUtils.deleteDirectory(puzzleDir);
        puzzleDir.mkdirs();
        List<String> fileNames = new ArrayList<>();
        for (PuzzlePieceI piece : puzzle.getPuzzlePieces()) {
            String fileName = FileNameGenerator.generateFileName(16);
            fileNames.add(RectanglePiece.savePuzzlePiece(piece, puzzleDir.getPath(), fileName, format));
        }
        List<String> fileNamesSolved = new ArrayList<>();
        for (PuzzlePieceI piece : puzzle.getSolvedPuzzlePieces()) {
            String fileName = FileNameGenerator.generateFileName(16);
            fileNamesSolved.add(RectanglePiece.savePuzzlePiece(piece, puzzleDir.getPath(), fileName, format));
        }
        PuzzleFileLinks links = new PuzzleFileLinks(fileNames,fileNamesSolved,  puzzle.getColumns(), puzzle.getRows());
        String data = Serializer.Serialize(links);
        File puzzleFile = new File(path + "\\" + FilenameUtils.removeExtension(puzzleName) +"\\" + puzzleName);
        if(puzzleFile.exists())
            puzzleFile.delete();
        puzzleFile.createNewFile();
        Files.writeString(puzzleFile.toPath(), data);
    }

    /**
     * Opens puzzle from path -> path/puzzleName
     * Also tries to get all images signed in .puzzle file
     *
     * @param path is the dir where are the pictures and .puzzle file
     * @return
     */

    public static PuzzleI openPuzzle(String path) throws IOException, ClassNotFoundException {
        String data = new String(Files.readAllBytes(Paths.get(path)));

        if (!FilenameUtils.getExtension(path).equals("puzzle")){
            throw new IOException("Not a .puzzle file");
        }
        PuzzleFileLinks links = Serializer.Deserialize(data);
        if (links.getFileNamesUns().size() != links.getCols() * links.getRows()) {
            throw new IOException("Wrong puzzle file");
        }
        PuzzlePieceI[] pieces = new PuzzlePieceI[links.getFileNamesUns().size()];
        PuzzlePieceI[] solved = new PuzzlePieceI[links.getFileNamesSolved().size()];
        int i = 0;
        for (String fileName : links.getFileNamesUns()) {
            pieces[i] = RectanglePiece.openPuzzlePiece(fileName);
            i++;
        }
        i = 0;
        for (String fileName : links.getFileNamesSolved()) {
            solved[i] = RectanglePiece.openPuzzlePiece(fileName);
            i++;
        }
        return new RectanglePiecePuzzle(pieces, solved, links.getCols(), links.getRows());

    }
}
