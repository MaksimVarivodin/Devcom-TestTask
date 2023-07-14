package Services;

import Interfaces.PuzzlePieceI;
import Realizations.ImageTools.ImageEquality;
import Realizations.ImageTools.ImageResize;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class ButtonService {
    static public Button createButton(BufferedImage image, int width, int height){
        BufferedImage temp = ImageResize.resize(image, width, height);
        Button newButton = new Button("");
        newButton.setMinSize(width, height);
        newButton.setMaxSize(width, height);
        newButton.setPrefSize(width, height);
        ImageView imageView = new ImageView(SwingFXUtils.toFXImage(temp, null));
        newButton.setGraphic(imageView);
        return newButton;
    }
    static public Button puzzlePieceToButton(PuzzlePieceI piece, int width, int height){
        return createButton(piece.getImage(), width, height);
    }
    static public Button buttonFromButton(Button button){
        Button newButton = new Button("");
        newButton.setMinSize(button.getWidth(), button.getHeight());
        newButton.setMaxSize(button.getWidth(), button.getHeight());
        newButton.setPrefSize(button.getWidth(), button.getHeight());
        newButton.setGraphic(button.getGraphic());
        return newButton;
    }
}
