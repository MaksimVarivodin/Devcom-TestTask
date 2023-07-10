package test_task.form;

import Constants.Theme;
import Services.PuzzleService;
import Services.ThemeService;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.css.CssParser;
import javafx.css.Stylesheet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class Controller {
    PuzzleService puzzleService;
    @FXML
    private AnchorPane id_main_pane;
    @FXML
    private Menu file_id;
    @FXML
    private Menu themes;

    @FXML
    private AnchorPane id_canvas;
    @FXML
    void applyLightTheme(ActionEvent event) throws IOException, URISyntaxException {
        RadioMenuItem item= (RadioMenuItem) event.getSource();
        if (item.isSelected()){
            ThemeService.applyTheme(id_main_pane.getScene(), Theme.LIGHTTHEME, Controller.class);
            ObservableList<MenuItem> items = themes.getItems();
            for (MenuItem i : items)
                if ((RadioMenuItem) i != item)
                    ((RadioMenuItem) i).setSelected(false);
        }

        else ThemeService.applyDefaultTheme(id_main_pane.getScene(), Controller.class);
        id_canvas.getChildren().clear();
        id_canvas.getChildren().add(App.button);
    }

    @FXML
    void applyDarkTheme(ActionEvent event) {
        RadioMenuItem item= (RadioMenuItem) event.getSource();
        if (item.isSelected()){
            ThemeService.applyTheme(id_main_pane.getScene(), Theme.DARKTHEME, Controller.class);
            ObservableList<MenuItem> items = themes.getItems();
            for (MenuItem i : items)
                if ((RadioMenuItem) i != item)
                    ((RadioMenuItem) i).setSelected(false);
        }
        else ThemeService.applyDefaultTheme(id_main_pane.getScene(), Controller.class);
    }
    @FXML
    void applyBlackTheme(ActionEvent event) {
        RadioMenuItem item= (RadioMenuItem) event.getSource();
        if (item.isSelected()){
            ThemeService.applyTheme(id_main_pane.getScene(), Theme.BLACKTHEME, Controller.class);
            ObservableList<MenuItem> items = themes.getItems();
            for (MenuItem i : items)
                if ((RadioMenuItem) i != item)
                    ((RadioMenuItem) i).setSelected(false);

        }
        else ThemeService.applyDefaultTheme(id_main_pane.getScene(), Controller.class);
    }
}