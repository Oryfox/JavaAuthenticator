import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;

public class KeyItem extends JPanel implements Comparable<KeyItem> {

    String title; //Platform or whatever
    String account ; //Username or E-Mail
    String key; //Secret

    JLabel verificationCodeLabel;

    static Font font = new Font("Arial", Font.PLAIN, 16);

    boolean editMode = false;

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

    public KeyItem() {
        KeyItem self = this;
        editMode = true;
        this.setOpaque(false);

        this.setLayout(new GridLayout(0,1));
        this.setPreferredSize(new Dimension(0,80));
        this.setBorder(BorderFactory.createEmptyBorder(5,15,5,15));

        JTextField titleField = new JTextField();
        titleField.setFont(font);
        this.add(titleField);

        JTextField accountField = new JTextField();
        accountField.setFont(font);
        this.add(accountField);

        JTextField secretField = new JTextField();
        secretField.setFont(font);
        this.add(secretField);

        titleField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                title = titleField.getText();
                if (e.getKeyChar() == '\n' && (!titleField.getText().equals("") && !accountField.getText().equals("") && !secretField.getText().equals(""))) {
                    self.remove(titleField);
                    self.remove(accountField);
                    self.remove(secretField);

                    JLabel titleLabel = new JLabel(title);
                    titleLabel.setFont(font);
                    self.add(titleLabel);

                    JLabel accountLabel = new JLabel(account);
                    accountLabel.setFont(font);
                    self.add(accountLabel);

                    verificationCodeLabel = new JLabel(Generator.getVerificationCode(key));
                    verificationCodeLabel.setFont(font);
                    self.add(verificationCodeLabel);
                    editMode = false;
                    Storage.keys.add(self);
                    Storage.saveKeys();
                }
            }
        });
        accountField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                account = accountField.getText();
                if (e.getKeyChar() == '\n' && (!titleField.getText().equals("") && !accountField.getText().equals("") && !secretField.getText().equals(""))) {
                    self.remove(titleField);
                    self.remove(accountField);
                    self.remove(secretField);

                    JLabel titleLabel = new JLabel(title);
                    titleLabel.setFont(font);
                    self.add(titleLabel);

                    JLabel accountLabel = new JLabel(account);
                    accountLabel.setFont(font);
                    self.add(accountLabel);

                    verificationCodeLabel = new JLabel(Generator.getVerificationCode(key));
                    verificationCodeLabel.setFont(font);
                    self.add(verificationCodeLabel);
                    editMode = false;
                    Storage.keys.add(self);
                    Storage.saveKeys();
                }
            }
        });
        secretField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                key = secretField.getText();
                if (e.getKeyChar() == '\n' && (!titleField.getText().equals("") && !accountField.getText().equals("") && !secretField.getText().equals(""))) {
                    self.remove(titleField);
                    self.remove(accountField);
                    self.remove(secretField);

                    JLabel titleLabel = new JLabel(title);
                    titleLabel.setFont(font);
                    self.add(titleLabel);

                    JLabel accountLabel = new JLabel(account);
                    accountLabel.setFont(font);
                    self.add(accountLabel);

                    verificationCodeLabel = new JLabel(Generator.getVerificationCode(key));
                    verificationCodeLabel.setFont(font);
                    self.add(verificationCodeLabel);
                    editMode = false;
                    Storage.keys.add(self);
                    Storage.saveKeys();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(OryColors.PURPLE);
        ((Graphics2D)g).fill(new RoundRectangle2D.Double(0,0,this.getWidth(),this.getHeight(),25,25));
    }

    @Override
    public int compareTo(KeyItem o) {
        return this.title.compareTo(o.title);
    }

    public void updateVerificationCode() {
        if (!editMode) verificationCodeLabel.setText(Generator.getVerificationCode(key));
    }
}
