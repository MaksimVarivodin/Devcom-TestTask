package test_task.devcom_test_task;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Bitmap Files", "*.bmp", "*.dib")
                ,new FileChooser.ExtensionFilter("JPEG", "*.jpg", "*.jpeg", "*.jpe", "*.jfif")
                ,new FileChooser.ExtensionFilter("GIF", "*.gif")
                ,new FileChooser.ExtensionFilter("TIFF", "*.tif", "*.tiff")
                ,new FileChooser.ExtensionFilter("PNG", "*.png")
                ,new FileChooser.ExtensionFilter("ICO", "*.ico")
                ,new FileChooser.ExtensionFilter("HEIC", "*.heic", "*.heif")
                ,new FileChooser.ExtensionFilter("WEBP", "*.webp")
                ,new FileChooser.ExtensionFilter("All Picture Files", "*.bmp", "*.dib", "*.jpg", "*.jpeg", "*.jpe", "*.jfif", "*.gif", "*.tif", "*.tiff", "*.png", "*.ico", "*.heic", "*.heif", "*.webp")

        );
        File selectedFile = fileChooser.showOpenDialog(welcomeText.getScene().getWindow());

    }
}