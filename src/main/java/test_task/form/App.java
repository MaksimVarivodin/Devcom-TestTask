package test_task.form;

import Interfaces.PuzzleI;
import Realizations.SquarePuzzles.SquarePuzzleGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("puzzle.fxml"));
        Path path = Paths.get("C:\\Users\\ПК\\Pictures\\Fractals\\тт.png");
        InputStream stream = Files.newInputStream(path);
        BufferedImage img = ImageIO.read(stream);

        PuzzleI squarePiecePuzzle = SquarePuzzleGenerator.generatePuzzle(img, 4, 4);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}