import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Icons {
    static BufferedImage backIcon;
    static BufferedImage editIcon;
    static BufferedImage deleteIcon;
    static BufferedImage qrIcon;
    static BufferedImage copyIcon;

    public static void load() throws IOException {
        backIcon = ImageIO.read(KeyItem.class.getResourceAsStream("icons/backArrow.png"));
        editIcon = ImageIO.read(KeyItem.class.getResourceAsStream("icons/editPencil.png"));
        deleteIcon = ImageIO.read(KeyItem.class.getResourceAsStream("icons/deleteTrash.png"));
        qrIcon = ImageIO.read(KeyItem.class.getResourceAsStream("icons/qrCode.png"));
        copyIcon = ImageIO.read(KeyItem.class.getResourceAsStream("icons/copy.png"));
    }
}
