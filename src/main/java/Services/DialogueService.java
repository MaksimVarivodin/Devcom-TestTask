package Services;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class DialogueService {
    public static Dialog<String> getDialogue(String message, String title) {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle(title);
        dialog.setContentText(message);
        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);
        return dialog;

    }
}
