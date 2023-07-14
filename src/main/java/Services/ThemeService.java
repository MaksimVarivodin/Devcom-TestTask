package Services;

import Constants.Theme;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;

import java.net.URL;

public class ThemeService {
    public static void applyTheme(Scene scene, Theme theme, Class contrlrClass) {
        URL resource = contrlrClass.getClassLoader().getResource(theme.toString());
        if (resource!= null){
            scene.getStylesheets().clear();
            scene.getStylesheets().add(resource.toExternalForm());
        }
    }
    public static void applyDefaultTheme(Scene scene, Class contrlrClass){
        applyTheme(scene, Theme.LIGHTTHEME, contrlrClass);
    }
    public static boolean checkIsSelected(ActionEvent event){
        RadioMenuItem item= (RadioMenuItem) event.getSource();
        return item.isSelected();
    }
    public static void uncheckNotSelected(ActionEvent event, RadioMenuItem item, ObservableList<MenuItem> items){
        for (MenuItem i : items)
            if ( i != item)
                ((RadioMenuItem) i).setSelected(false);
    }
}
