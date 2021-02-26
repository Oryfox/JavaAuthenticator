import javax.swing.*;
import java.io.IOException;

public class Authenticator {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Storage.loadKeys();
        try {
            Icons.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new MainFrame();
    }
}
