package test_task.form;

import Constants.ImageExtensions;
import Constants.PuzzleExtension;
import Constants.Theme;
import Services.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Controller {
    PuzzleService puzzleService;

    int[][] rotations;
    String fileName;
    String absolutePath;
    @FXML
    public AnchorPane id_main_pane;
    @FXML
    private Menu themes;
    @FXML
    public VBox id_canvas;
    @FXML
    public Slider id_columns;

    @FXML
    public Slider id_rows;

    private Button currentButton;
    @FXML
    Label currentX;

    @FXML
    Label currentY;
    @FXML
    private Label id_not_picked;


    @FXML
    void openPuzzleFromImage(ActionEvent event) throws IOException {

        FileChooser fileChooser = ImageExtensions.getFileChooserForImages();
        // Show open file dialog
        File imageFile = fileChooser.showOpenDialog(id_main_pane.getScene().getWindow());
        if (imageFile != null) {
            puzzleService = PuzzleServiceGenerator.generateFromImage(imageFile.getAbsolutePath(), (int) id_rows.getValue(), (int) id_columns.getValue(), (int) id_canvas.getWidth(), (int) id_canvas.getHeight(), 5);
            changeRowColumnCount();
            rotations = new int[puzzleService.getPuzzle().getRows()][puzzleService.getPuzzle().getColumns()];
            id_not_picked.setVisible(false);
        } else {
            id_not_picked.setVisible(true);
        }

    }

    @FXML
    void openPuzzle(ActionEvent event) throws IOException, ClassNotFoundException {
        FileChooser fileChooser = PuzzleExtension.getFileChooserForPuzzle();
        File puzzle = fileChooser.showOpenDialog(id_main_pane.getScene().getWindow());
        if (puzzle != null) {
            puzzleService = PuzzleServiceGenerator.openPuzzle(puzzle.getAbsolutePath(), (int) id_canvas.getWidth(), (int) id_canvas.getHeight(), 5);
            changeRowColumnCount();
            rotations = new int[puzzleService.getPuzzle().getRows()][puzzleService.getPuzzle().getColumns()];
            id_columns.setValue(puzzleService.getPuzzle().getColumns());
            id_rows.setValue(puzzleService.getPuzzle().getRows());
            id_not_picked.setVisible(false);
        } else
            id_not_picked.setVisible(true);
    }


    @FXML
    void saveAs(ActionEvent event) throws IOException {
        FileChooser chooser = PuzzleExtension.getFileChooserForPuzzle();
        File file = chooser.showSaveDialog(id_main_pane.getScene().getWindow());

        if (puzzleService != null && file != null && file.getName().length() > 0) {
            PuzzleServiceGenerator.savePuzzle(puzzleService, file.getParent(), file.getName());
        }
    }

    @FXML
    void save(ActionEvent event) throws IOException {
        if (fileName != null && fileName.length() > 0) {
            File file = new File(absolutePath);
            PuzzleServiceGenerator.savePuzzle(puzzleService, file.getParent(), file.getName());
        } else if (puzzleService != null)
            saveAs(event);
    }

    @FXML
    void solve(ActionEvent event) {
        DialogueService.getDialogue("Not working yet", "Error!").showAndWait();
    }

    @FXML
    void checkIsSolved(ActionEvent event) {
        if (puzzleService != null) {
            if (puzzleService.checkIsSolved()) {
                DialogueService.getDialogue("Congratulations!", "You solved the puzzle").showAndWait();
            } else {
                DialogueService.getDialogue("Sorry!", "You didn't solve the puzzle").showAndWait();
            }
        }
    }

    @FXML
    void closePuzzle(ActionEvent event) throws IOException {
        if (fileName != null && fileName.length() > 0) {

            File file = new File(absolutePath);
            PuzzleServiceGenerator.savePuzzle(puzzleService, file.getParent(), file.getName());

        } else if (puzzleService != null){
            save(event);

        }
        puzzleService = null;
        rotations = null;
        absolutePath = "";
        fileName = "";
        currentX.setText("-1");
        currentY.setText("-1");
        id_canvas.getChildren().clear();
        id_not_picked.setVisible(true);
    }

    @FXML
    void quitApp(ActionEvent event) throws IOException {
        closePuzzle(event);
        Platform.exit();

    }

    @FXML
    void applyLightTheme(ActionEvent event) throws IOException, URISyntaxException {
        if (ThemeService.checkIsSelected(event)) {
            ThemeService.applyTheme(id_main_pane.getScene(), Theme.LIGHTTHEME, Controller.class);
            ThemeService.uncheckNotSelected(event, (RadioMenuItem) event.getSource(), themes.getItems());

        } else ThemeService.applyDefaultTheme(id_main_pane.getScene(), Controller.class);
    }

    @FXML
    void applyDarkTheme(ActionEvent event) {
        if (ThemeService.checkIsSelected(event)) {
            ThemeService.applyTheme(id_main_pane.getScene(), Theme.DARKTHEME, Controller.class);
            ThemeService.uncheckNotSelected(event, (RadioMenuItem) event.getSource(), themes.getItems());
        } else ThemeService.applyDefaultTheme(id_main_pane.getScene(), Controller.class);
    }

    @FXML
    void applyBlackTheme(ActionEvent event) {
        if (ThemeService.checkIsSelected(event)) {
            ThemeService.applyTheme(id_main_pane.getScene(), Theme.BLACKTHEME, Controller.class);
            ThemeService.uncheckNotSelected(event, (RadioMenuItem) event.getSource(), themes.getItems());
        } else ThemeService.applyDefaultTheme(id_main_pane.getScene(), Controller.class);
    }

    @FXML
    void currentTip(ActionEvent event) {
        DialogueService.getDialogue("To understand where is selected puzzle piece\n" +
                "there are X, Y labels in the bottom-right corner", "Selected piece TIP").showAndWait();
    }

    @FXML
    void difficultyTip(ActionEvent event) {
        DialogueService.getDialogue("To select difficulty(rows X cols)\n" +
                "before u open Image, select difficulty on the sliders.", "Difficulty Tip").showAndWait();
    }

    @FXML
    void movementTip(ActionEvent event) {
        DialogueService.getDialogue("Click on a piece to move it\n" +
                "A - left\n" +
                "W - up\n" +
                "S - down\n"
                + "D - right", "Movement Tip").showAndWait();
    }

    @FXML
    void rotationTip(ActionEvent event) {
        DialogueService.getDialogue("Click on a piece to rotate it and use buttons\n" +
                "in bottom-left corner to change rotation", "Rotation Tip").showAndWait();
    }

    @FXML
    void author(ActionEvent event) {
        DialogueService.getDialogue("Author: VARIVODIN Maksym", "OMGGGG 3:13!!! neeed to sleep T_T").showAndWait();
    }

    @FXML
    void donate(ActionEvent event) {
        DialogueService.getDialogue("Best donation - give me a job", "I'm so poor and hungry...").showAndWait();
    }

    @FXML
    void rotateLeft(ActionEvent event) {
        if (currentButton != null && rotations != null && puzzleService != null) {
            int Y = Integer.parseInt(currentY.getText()) - 1;
            int X = Integer.parseInt(currentX.getText()) - 1;
            if (currentButton.getMinHeight() == currentButton.getMinWidth()) {
                rotations[Y][X] -= rotations[Y][X] != -360 ? 90 : -270;
            } else {
                rotations[Y][X] -= rotations[Y][X] != -360 ? 180 : -180;
            }
            currentButton.getGraphic().setRotate(rotations[Y][X]);
            puzzleService.rotateLeft();
        }
    }

    @FXML
    void rotateRight(ActionEvent event) {
        if (currentButton != null && rotations != null && puzzleService != null) {
            int Y = Integer.parseInt(currentY.getText()) - 1;
            int X = Integer.parseInt(currentX.getText()) - 1;
            if (currentButton.getMinHeight() == currentButton.getMinWidth()) {
                rotations[Y][X] += rotations[Y][X] != 360 ? 90 : -270;
            } else {
                rotations[Y][X] += rotations[Y][X] != 360 ? 180 : -180;
            }
            currentButton.getGraphic().setRotate(rotations[Y][X]);
            puzzleService.rotateRight();
        }
    }


    public void changeRowColumnCount() throws IOException {
        id_canvas.getChildren().clear();
        id_canvas.setAlignment(Pos.CENTER);
        if (puzzleService != null) {
            HBox[] array = puzzleService.getHBoxes();
            applyActionToButtons(array);
            id_canvas.getChildren().addAll(array);
        }

    }

    public void findCurrentButton() {
        ObservableList<Node> boxes = id_canvas.getChildren();
        int indexX = 0;
        int indexY = 0;
        boolean buttonFound = false;
        for (; indexY < boxes.size(); indexY++) {
            HBox box = (HBox) boxes.get(indexY);
            ObservableList<Node> buttons = box.getChildren();
            for (; indexX < buttons.size(); indexX++) {
                Button button = (Button) buttons.get(indexX);
                if (button.equals(currentButton)) {
                    buttonFound = true;
                    break;
                }
            }
            if (buttonFound) break;
            indexX = 0;

        }
        puzzleService.setCoordinates(indexX, indexY);
    }

    private HBox findLineByCoordinateY(int y) {
        ObservableList<Node> boxes = id_canvas.getChildren();
        return (HBox) boxes.get(y);
    }

    private HBox swapButtonsInLine(int x1, int x2, int y) {
        HBox line = findLineByCoordinateY(y);
        Button a = ButtonService.buttonFromButton((Button) line.getChildren().get(x1));
        Button b = ButtonService.buttonFromButton((Button) line.getChildren().get(x2));
        a.setOnAction(getButtonHandler());
        b.setOnAction(getButtonHandler());
        line.getChildren().set(x1, b);
        line.getChildren().set(x2, a);
        return line;
    }

    private void swapButtonsInRows(int y1, int y2, int x) {
        HBox line1 = findLineByCoordinateY(y1);
        HBox line2 = findLineByCoordinateY(y2);
        Button a = ButtonService.buttonFromButton((Button) line1.getChildren().get(x));
        Button b = ButtonService.buttonFromButton((Button) line2.getChildren().get(x));
        a.setOnAction(getButtonHandler());
        b.setOnAction(getButtonHandler());
        line1.getChildren().set(x, b);
        line2.getChildren().set(x, a);
        id_canvas.getChildren().set(y1, line1);
        id_canvas.getChildren().set(y2, line2);
    }

    public void moveCurrentButtonLeft() {
        if (puzzleService != null && currentButton != null) {
            int Y = Integer.parseInt(currentY.getText()) - 1;
            int X = Integer.parseInt(currentX.getText()) - 1;
            if (X > 0 && Y >= 0 && Y < puzzleService.getPuzzle().getRows()) {
                swapButtonsInLine(X - 1, X, Y);
                puzzleService.moveLeft();
                currentX.setText(Integer.toString(X));
            }

        }
    }

    public void moveCurrentButtonRight() {
        if (puzzleService != null && currentButton != null) {
            int Y = Integer.parseInt(currentY.getText()) - 1;
            int X = Integer.parseInt(currentX.getText()) - 1;
            if (X < puzzleService.getPuzzle().getColumns() - 1 && Y >= 0 && Y < puzzleService.getPuzzle().getRows()) {
                swapButtonsInLine(X, X + 1, Y);
                puzzleService.moveRight();
                currentX.setText(Integer.toString(X + 2));
            }
        }
    }

    public void moveCurrentButtonUp() {
        if (puzzleService != null && currentButton != null) {
            int X = Integer.parseInt(currentX.getText()) - 1;
            int Y = Integer.parseInt(currentY.getText()) - 1;
            if (Y > 0 && X >= 0 && X < puzzleService.getPuzzle().getColumns()) {
                swapButtonsInRows(Y - 1, Y, X);
                puzzleService.moveUp();
                currentY.setText(Integer.toString(Y));
            }
        }
    }

    public void moveCurrentButtonDown() {
        if (puzzleService != null && currentButton != null) {
            int X = Integer.parseInt(currentX.getText()) - 1;
            int Y = Integer.parseInt(currentY.getText()) - 1;
            if (Y < puzzleService.getPuzzle().getRows() - 1 && X >= 0 && X < puzzleService.getPuzzle().getColumns()) {
                swapButtonsInRows(Y, Y + 1, X);
                puzzleService.moveDown();
                currentY.setText(Integer.toString(Y + 2));
            }
        }
    }

    private void applyActionToButtons(HBox[] array) {
        for (HBox box : array) {
            ObservableList<Node> buttons = box.getChildren();
            for (Node button : buttons) {
                ((Button) button).setOnAction(getButtonHandler());
            }
        }
    }


    public EventHandler<ActionEvent> getButtonHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentButton = (Button) event.getSource();
                findCurrentButton();
                currentX.setText(Integer.toString(puzzleService.getCurrentX() + 1));
                currentY.setText(Integer.toString(puzzleService.getCurrentY() + 1));
            }
        };
    }

    public ChangeListener<Number> getResizeEventListener() {
        return new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    if (puzzleService != null) {
                        puzzleService.setBoxHeight((int) id_canvas.getHeight());
                        puzzleService.setBoxWidth((int) id_canvas.getWidth());
                        changeRowColumnCount();
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }


}