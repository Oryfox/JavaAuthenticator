package de.oryfox.totpauthenticator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Icons {
    static BufferedImage backIcon;
    static BufferedImage editIcon;
    static BufferedImage deleteIcon;
    static BufferedImage copyIcon;

    public static void load() throws IOException {
        backIcon = ImageIO.read(KeyItem.class.getClassLoader().getResourceAsStream("icons/backArrow.png"));
        editIcon = ImageIO.read(KeyItem.class.getClassLoader().getResourceAsStream("icons/editPencil.png"));
        deleteIcon = ImageIO.read(KeyItem.class.getClassLoader().getResourceAsStream("icons/deleteTrash.png"));
        copyIcon = ImageIO.read(KeyItem.class.getClassLoader().getResourceAsStream("icons/copy.png"));
    }
}
