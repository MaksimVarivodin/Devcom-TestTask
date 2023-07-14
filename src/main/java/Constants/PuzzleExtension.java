package Constants;

import javafx.stage.FileChooser;

import java.io.File;

public enum PuzzleExtension {
    PUZZLE("Puzzle","*.puzzle");
    private final String name;
    private final String extension;

    PuzzleExtension( String name, String extension) {
        this.extension = extension;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getExtension(){
        return extension;
    }

    public static FileChooser getFileChooserForPuzzle(){
        FileChooser fileChooser = new FileChooser();

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if(userDirectory.canRead())
            fileChooser.setInitialDirectory(new File(userDirectory+ "\\Pictures"));
        fileChooser.setTitle("Save Puzzle...");
        fileChooser.setInitialFileName("newPuzzle");
        // Set extension filter
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(PuzzleExtension.PUZZLE.getName(), PuzzleExtension.PUZZLE.getExtension())
        );
        fileChooser.setSelectedExtensionFilter( fileChooser.getExtensionFilters().get(0));
        return fileChooser;
    }
}
