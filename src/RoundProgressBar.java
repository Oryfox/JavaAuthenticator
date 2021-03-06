import javax.swing.*;
import java.awt.*;

public class RoundProgressBar extends JPanel {

    int progress;
    Color mainColor;

    public RoundProgressBar(Color mainColor) {
        this(0,mainColor);
    }

    public RoundProgressBar(int progress, Color mainColor) {
        this.progress = progress;
        this.mainColor = mainColor;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(30,30));
    }

    public void setProgress(int progress) {
        this.progress = progress;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(mainColor);
        if (this.getWidth() >= this.getHeight()) {
            g.fillArc(this.getWidth() / 2 - this.getHeight() / 2, 0, this.getHeight(), this.getHeight(), 90, (int) (progress * 3.6));
        } else {
            g.fillArc(0,this.getHeight() / 2 - this.getWidth() / 2, this.getWidth(), this.getWidth(), 0, (int) (progress * 3.6));
        }
    }
}
