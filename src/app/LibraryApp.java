package app;

public class LibraryApp {
    public static void main(String[] args) {
        final String appName = "Biblioteka v 1.6";
        System.out.println(appName);
        //System.out.println(Option.EXIT.getValue());
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.controlLoop();
    }
}
