import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class KeyItem extends JPanel implements Comparable {

    String title; //Platform or whatever
    String account ; //Username or E-Mail
    String key; //Secret

    JLabel verificationCodeLabel;

    static Font font = new Font("Arial", Font.PLAIN, 16);

    public KeyItem(String title, String account, String key) {
        this.title = title;
        this.account = account;
        this.key = key;

        this.setOpaque(false);

        this.setLayout(new GridLayout(0,1));
        this.setPreferredSize(new Dimension(0,80));
        this.setBorder(BorderFactory.createEmptyBorder(5,15,5,15));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(font);
        this.add(titleLabel);

        JLabel accountLabel = new JLabel(account);
        accountLabel.setFont(font);
        this.add(accountLabel);

        verificationCodeLabel = new JLabel(Generator.getVerificationCode(key));
        verificationCodeLabel.setFont(font);
        this.add(verificationCodeLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(OryColors.PURPLE);
        ((Graphics2D)g).fill(new RoundRectangle2D.Double(0,0,this.getWidth(),this.getHeight(),25,25));
    }

    @Override
    public int compareTo(Object o) {
        return this.title.compareTo(((KeyItem)o).title);
    }

    public void updateVerificationCode() {
        verificationCodeLabel.setText(Generator.getVerificationCode(key));
    }
}
