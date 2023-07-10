package Services;

import Constants.Theme;
import javafx.scene.Scene;

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
        URL resource = contrlrClass.getClassLoader().getResource(Theme.DEFAULTTHEME.toString());
        if (resource!= null){
            scene.getStylesheets().clear();
            scene.getStylesheets().add(resource.toExternalForm());
        }
    }
}
