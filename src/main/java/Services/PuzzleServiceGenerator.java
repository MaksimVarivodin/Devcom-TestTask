package Services;

import Interfaces.PuzzleI;
import Realizations.RectanglePuzzles.RectanglePuzzleGenerator;
import Realizations.SquarePuzzles.SquarePuzzleGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PuzzleServiceGenerator {

    static public PuzzleService generateFromImage(String fileName, int rows, int columns, int width, int height, int spacing) throws IOException {
        Path path = Paths.get(fileName);
        InputStream stream = Files.newInputStream(path);
        BufferedImage img = ImageIO.read(stream);
        if (img.getHeight()/rows == img.getWidth()/columns)
            return new PuzzleService(SquarePuzzleGenerator.generatePuzzle(img, rows, columns), width, height, spacing);
        else
            return new PuzzleService(RectanglePuzzleGenerator.generatePuzzle(img, rows, columns), width, height, spacing);

    }
    static public PuzzleService openPuzzle(String path, int width, int height, int spacing) throws IOException, ClassNotFoundException {
        PuzzleI puzzle = RectanglePuzzleGenerator.openPuzzle(path);
        return new PuzzleService(puzzle, width, height, spacing);

    }
    static public void savePuzzle(PuzzleService puzzle, String path, String puzzleName) throws IOException {
        RectanglePuzzleGenerator.savePuzzle(puzzle.getPuzzle(),  ".PNG", path, puzzleName);
    }

}
