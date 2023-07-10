package test_task.form;

import Constants.Theme;
import Interfaces.PuzzleI;
import Realizations.SquarePuzzles.SquarePuzzleGenerator;
import Services.ButtonService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App extends Application {


    public static Button button;
    @Override
    public void start(Stage stage) throws IOException {

        Path path = Paths.get("C:\\Users\\ПК\\Pictures\\Fractals\\тт.png");
        InputStream stream = Files.newInputStream(path);
        BufferedImage img = ImageIO.read(stream);
        BufferedImage image = ButtonService.createButton(img, 100, 100);
        button = new Button();
        button.contentDisplayProperty().setValue(ContentDisplay.TOP);
        ImageView view = new ImageView( javafx.embed.swing.SwingFXUtils.toFXImage(image, null));
        button.setGraphic(view);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("puzzle.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        URL resource = this.getClass().getClassLoader().getResource(Theme.LIGHTTHEME.toString());
        if (resource!= null){
            scene.getStylesheets().clear();
            scene.getStylesheets().add(resource.toExternalForm());
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}