package Constants;

import javafx.stage.FileChooser;

import java.io.File;

public enum ImageExtensions {
    Monochrome_Bitmap("Monochrome Bitmap", new String[]{"*.bmp", "*.dib"}),
    SxTeenColorBitmap("16 Color Bitmap", new String[]{"*.bmp", "*.dib"}),
    TwoFiveSixColorBitmap("256 Color Bitmap", new String[]{"*.bmp", "*.dib"}),
    JPEG("JPEG", new String[]{"*.jpg", "*.jpeg", "*.jpe", "*.jfif"}),
    GIF ("GIF", new String[]{"*.gif"}),
    TIFF("TIFF", new String[]{"*.tif", "*.tiff"}),
    PNG("PNG", new String[]{"*.png"}),
    HEIC("HEIC", new String[]{"*.heic", "*.hif"}),
    ALL_FORMATS("All Images", new String[]{"*.bmp", "*.dib", "*.jpg", "*.jpeg", "*.jpe", "*.jfif", "*.gif", "*.tif", "*.tiff", "*.png", "*.heic", "*.hif"}),;
    ImageExtensions(String stringName, String[] extensions) {
        this.stringName = stringName;
        this.extensions = extensions;
    }

    private final String stringName;
    private final String[] extensions;
    public String extensionName(){
        return stringName;
    }
    public String[] getExtensions(){
        return extensions;
    }
    public static FileChooser getFileChooserForImages(){
        FileChooser fileChooser = new FileChooser();

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if(userDirectory.canRead())
            fileChooser.setInitialDirectory(new File(userDirectory+ "\\Pictures"));
        fileChooser.setTitle("Select Image...");

        // Set extension filter
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(ImageExtensions.ALL_FORMATS.extensionName(), ImageExtensions.ALL_FORMATS.getExtensions()),
                new FileChooser.ExtensionFilter(ImageExtensions.JPEG.extensionName(), ImageExtensions.JPEG.getExtensions()),
                new FileChooser.ExtensionFilter(ImageExtensions.PNG.extensionName(), ImageExtensions.PNG.getExtensions()),
                new FileChooser.ExtensionFilter(ImageExtensions.GIF.extensionName(), ImageExtensions.GIF.getExtensions()),
                new FileChooser.ExtensionFilter(ImageExtensions.TIFF.extensionName(), ImageExtensions.TIFF.getExtensions()),
                new FileChooser.ExtensionFilter(ImageExtensions.HEIC.extensionName(), ImageExtensions.HEIC.getExtensions()),
                new FileChooser.ExtensionFilter(ImageExtensions.Monochrome_Bitmap.extensionName(), ImageExtensions.Monochrome_Bitmap.getExtensions()),
                new FileChooser.ExtensionFilter(ImageExtensions.SxTeenColorBitmap.extensionName(), ImageExtensions.SxTeenColorBitmap.getExtensions()),
                new FileChooser.ExtensionFilter(ImageExtensions.TwoFiveSixColorBitmap.extensionName(), ImageExtensions.TwoFiveSixColorBitmap.getExtensions())
        );
        return fileChooser;
    }
}
