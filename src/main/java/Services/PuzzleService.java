package Services;

import Interfaces.PuzzleI;
import Interfaces.PuzzlePieceI;
import Realizations.PuzzleSolver.PuzzleSolver;
import Realizations.RectanglePuzzles.RectanglePuzzleGenerator;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PuzzleService {
    PuzzleI puzzle;
    PuzzleSolver solver;
    int boxWidth;
    int boxHeight;
    int spacing;
    double scale;

    public PuzzleService(PuzzleI puzzle, int width, int height, int spacing) {
        this.puzzle = puzzle;
        solver = new PuzzleSolver(puzzle);
        this.boxWidth = width;
        this.boxHeight = height;
        this.spacing = spacing;
        calculateScale();
    }

    private double calculateScale() {
        if (puzzle.getPuzzlePieces()[0].getImage().getWidth() == puzzle.getPuzzlePieces()[0].getImage().getHeight()){
            scale = boxWidth < boxHeight ? boxWidth / (double) puzzle.getColumns() : boxHeight / (double) puzzle.getRows();
            scale /= boxWidth < boxHeight ? (double) puzzle.getPuzzlePieces()[0].getImage().getWidth() : (double) puzzle.getPuzzlePieces()[0].getImage().getHeight();
        }
        else{
            int imageWidth  = puzzle.getPuzzlePieces()[0].getImage().getWidth();
            int imageHeight = puzzle.getPuzzlePieces()[0].getImage().getHeight();
            scale = boxWidth < boxHeight ? boxWidth / (double)puzzle.getColumns() : boxHeight / (double)puzzle.getRows();
            scale /= boxWidth < boxHeight? (double) imageWidth : (double) imageHeight;
        }
        return scale;
    }

    private double getButtonHeight() {
        return scale * (double) puzzle.getPuzzlePieces()[0].getImage().getHeight() - 2 *spacing;
    }

    private double getButtonWidth() {
        return scale * (double) puzzle.getPuzzlePieces()[0].getImage().getWidth() - 2* spacing;
    }

    public void setBoxWidth(int boxWidth) {
        this.boxWidth = boxWidth;
        calculateScale();
    }

    public void setBoxHeight(int boxHeight) {
        this.boxHeight = boxHeight;
        calculateScale();
    }

    public Button[] getButtons() throws NullPointerException {
        if (puzzle.getPuzzlePieces() != null) {
            PuzzlePieceI[] array = puzzle.getPuzzlePieces();
            Button[] result = new Button[array.length];

            for (int i = 0; i < puzzle.getPuzzlePieces().length; i++)
                result[i] = ButtonService.puzzlePieceToButton(array[i], (int) getButtonWidth(), (int) getButtonHeight());
            return result;
        } else
            throw new NullPointerException("Array of pieces was null.");
    }

    public HBox[] getHBoxes() {
        Button[] buttons = getButtons();
        HBox[] result = new HBox[puzzle.getRows()];
        for (int i = 0; i < result.length; i++) {
            result[i] = new HBox(spacing);
            result[i].setAlignment(Pos.CENTER);
            for (int j = 0; j < puzzle.getColumns(); j++)
                result[i].getChildren().add(buttons[i * puzzle.getColumns() + j]);
        }
        return result;
    }

    public HBox[] getSolvedHBoxes() {
        BufferedImage[][] images = solver.solve();
        HBox[] result = new HBox[images.length];
        for (int i = 0; i < images.length; i++) {
            result[i].setAlignment(Pos.CENTER);
            result[i] = new HBox(spacing);
            for (int j = 0; j < images[0].length; j++) {
                result[i].getChildren().add(ButtonService.createButton(images[i][j], (int) getButtonWidth(), (int) getButtonHeight()));
            }
        }
        return result;
    }

    public void setCoordinates(int x, int y) {
        puzzle.setCurrent(x, y);
    }

    public int getCurrentX() {
        return puzzle.getCurrentX();
    }

    public int getCurrentY() {
        return puzzle.getCurrentY();
    }

    public PuzzleI getPuzzle() {
        return puzzle;
    }


    public boolean checkIsSolved(){
        return puzzle.isSolved();
    }

    public void rotateLeft(){
        puzzle.rotateLeft();
    }

    public void rotateRight(){
        puzzle.rotateRight();
    }
    public void moveLeft(){
        puzzle.moveLeft();
    }
    public void moveRight(){
        puzzle.moveRight();
    }
    public void moveUp(){
        puzzle.moveUp();
    }
    public void moveDown(){
        puzzle.moveDown();
    }
}
