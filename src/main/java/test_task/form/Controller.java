package test_task.form;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import tempPackage.PuzzleSolver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Picture Files", "*.bmp", "*.dib", "*.jpg", "*.jpeg", "*.jpe", "*.jfif", "*.gif", "*.tif", "*.tiff", "*.png", "*.ico", "*.heic", "*.heif", "*.webp")
                ,new FileChooser.ExtensionFilter("JPEG", "*.jpg", "*.jpeg", "*.jpe", "*.jfif")
                ,new FileChooser.ExtensionFilter("GIF", "*.gif")
                ,new FileChooser.ExtensionFilter("TIFF", "*.tif", "*.tiff")
                ,new FileChooser.ExtensionFilter("PNG", "*.png")
                ,new FileChooser.ExtensionFilter("ICO", "*.ico")
                ,new FileChooser.ExtensionFilter("HEIC", "*.heic", "*.heif")
                ,new FileChooser.ExtensionFilter("WEBP", "*.webp")
                ,new FileChooser.ExtensionFilter("Bitmap Files", "*.bmp", "*.dib")

        );
        fileChooser.setInitialDirectory(new File("C:\\Users\\ПК\\Pictures"));
        File selectedFile = fileChooser.showOpenDialog(welcomeText.getScene().getWindow());
        String path = new File(".").getCanonicalPath();
        if (selectedFile != null){
            int puzzleWidth = 4;
            int puzzleHeight = 4;
            int pieceSize = 200;
            PuzzleSolver puzzleSolver = new PuzzleSolver(puzzleWidth, puzzleHeight, pieceSize);
            if (puzzleSolver.solvePuzzle(selectedFile.getPath())) {
                BufferedImage solution = puzzleSolver.getFinalImage();
                System.out.println("Решение пазла:");
                File output = new File("output.png");
                ImageIO.write(solution, "png", output);
            }
        }

    }
}