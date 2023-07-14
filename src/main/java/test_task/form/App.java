package test_task.form;

import Constants.Theme;
import Interfaces.PuzzleI;
import Realizations.SquarePuzzles.SquarePuzzleGenerator;
import Services.ButtonService;
import Services.PuzzleServiceGenerator;
import Services.ThemeService;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("puzzle.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Controller fxmlController = ((Controller) fxmlLoader.getController());

        fxmlController.id_canvas.widthProperty().addListener(fxmlController.getResizeEventListener());
        fxmlController.id_canvas.heightProperty().addListener(fxmlController.getResizeEventListener());
        ThemeService.applyDefaultTheme(scene, Controller.class);
        stage.setTitle("Puzzle");

        // setting movement Controls here:

        scene.setOnKeyReleased(e->{
            switch (e.getCode()){
                case A:
                    fxmlController.moveCurrentButtonLeft();
                    break;
                case D:
                    fxmlController.moveCurrentButtonRight();
                    break;
                case W:
                    fxmlController.moveCurrentButtonUp();
                    break;
                case S:
                    fxmlController.moveCurrentButtonDown();
                    break;
            }
        });

        stage.setScene(scene);




        stage.show();
        double heightDifference = stage.getHeight() - fxmlController.id_main_pane.getMinHeight();
        stage.setMinHeight(fxmlController.id_main_pane.getMinHeight()+ heightDifference);
        stage.setMinWidth(fxmlController.id_main_pane.getMinWidth());

    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}