package Constants;

public enum Theme {
    LIGHTTHEME("LightTheme.css"),
    DARKTHEME("DarkTheme.css"),
    BLACKTHEME("BlackTheme.css"),
    DEFAULTTHEME(LIGHTTHEME.toString());

    private final String cssFileName;

    Theme(String cssFileName) {
        this.cssFileName = cssFileName;
    }

    @Override
    public String toString() {
        return cssFileName;
    }
}
