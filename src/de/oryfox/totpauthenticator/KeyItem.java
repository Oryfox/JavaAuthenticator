package de.oryfox.totpauthenticator;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class KeyItem extends JPanel implements Comparable<KeyItem> {

    String title; //Platform or whatever
    String account ; //Username or E-Mail
    String key; //Secret

    JLabel titleLabel;
    JLabel accountLabel;
    JLabel verificationCodeLabel;

    JPanel backButton;
    JPanel editButton;
    JPanel removeButton;
    JPanel copyButton;

    JTextField titleField;
    JTextField accountField;
    JTextField secretField;

    boolean editMode = false;

    public KeyItem(String title, String account, String key) {
        this.title = title;
        this.account = account;
        this.key = key;

        this.setOpaque(false);

        this.setLayout(new GridLayout(0,1));
        this.setPreferredSize(new Dimension(0,80));
        this.setBorder(BorderFactory.createEmptyBorder(5,15,5,15));

        {
            titleLabel = new JLabel(title);
            titleLabel.setFont(MainFrame.font);
            this.add(titleLabel);

            accountLabel = new JLabel(account);
            accountLabel.setFont(MainFrame.font);
            this.add(accountLabel);

            verificationCodeLabel = new JLabel(Generator.getVerificationCode(key));
            verificationCodeLabel.setFont(MainFrame.font);
            this.add(verificationCodeLabel);
        } //Labels

        {
            titleField = new JTextField();
            titleField.setFont(MainFrame.font);
            new GhostText(titleField,"Title");
            titleField.setForeground(Color.BLACK);

            accountField = new JTextField();
            accountField.setFont(MainFrame.font);
            new GhostText(accountField,"Account");
            accountField.setForeground(Color.BLACK);

            secretField = new JTextField();
            secretField.setFont(MainFrame.font);
            new GhostText(secretField,"Secret");
            secretField.setForeground(Color.BLACK);

            titleField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyChar() == '\n' && (!titleField.getText().equals("") && !accountField.getText().equals("") && !secretField.getText().equals("") && !titleField.getText().equals("Title") && !accountField.getText().equals("Account") && !secretField.getText().equals("Secret"))) {
                        changeEditMode();
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
                    if (e.getKeyChar() == '\n' && (!titleField.getText().equals("") && !accountField.getText().equals("") && !secretField.getText().equals("") && !titleField.getText().equals("Title") && !accountField.getText().equals("Account") && !secretField.getText().equals("Secret"))) {
                        changeEditMode();
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
                    if (e.getKeyChar() == '\n' && (!titleField.getText().equals("") && !accountField.getText().equals("") && !secretField.getText().equals("") && !titleField.getText().equals("Title") && !accountField.getText().equals("Account") && !secretField.getText().equals("Secret"))) {
                        changeEditMode();
                        Storage.saveKeys();
                    }
                }
            });
        } //Fields

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showButtons();
            }
        });
    }

    public KeyItem() {
        editMode = true;
        this.setOpaque(false);

        this.setLayout(new GridLayout(0,1));
        this.setPreferredSize(new Dimension(0,80));
        this.setBorder(BorderFactory.createEmptyBorder(5,15,5,15));

        {
            titleLabel = new JLabel();
            titleLabel.setFont(MainFrame.font);

            accountLabel = new JLabel();
            accountLabel.setFont(MainFrame.font);

            verificationCodeLabel = new JLabel();
            verificationCodeLabel.setFont(MainFrame.font);
        } //Labels

        {
            titleField = new JTextField();
            titleField.setFont(MainFrame.font);
            this.add(titleField);
            new GhostText(titleField,"Title");

            accountField = new JTextField();
            accountField.setFont(MainFrame.font);
            this.add(accountField);
            new GhostText(accountField,"Account");

            secretField = new JTextField();
            secretField.setFont(MainFrame.font);
            this.add(secretField);
            new GhostText(secretField,"Secret");

            titleField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyChar() == '\n' && (!titleField.getText().equals("") && !accountField.getText().equals("") && !secretField.getText().equals("") && !titleField.getText().equals("Title") && !accountField.getText().equals("Account") && !secretField.getText().equals("Secret"))) {
                        changeEditMode();
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
                    if (e.getKeyChar() == '\n' && (!titleField.getText().equals("") && !accountField.getText().equals("") && !secretField.getText().equals("") && !titleField.getText().equals("Title") && !accountField.getText().equals("Account") && !secretField.getText().equals("Secret"))) {
                        changeEditMode();
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
                    if (e.getKeyChar() == '\n' && (!titleField.getText().equals("") && !accountField.getText().equals("") && !secretField.getText().equals("") && !titleField.getText().equals("Title") && !accountField.getText().equals("Account") && !secretField.getText().equals("Secret"))) {
                        changeEditMode();
                        Storage.saveKeys();
                    }
                }
            });
        } //TextFields

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showButtons();
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

    public void changeEditMode() {
        this.editMode = !this.editMode;
        if (editMode) {
            this.remove(titleLabel);
            this.remove(accountLabel);
            this.remove(verificationCodeLabel);

            titleField.setText(titleLabel.getText());
            accountField.setText(accountLabel.getText());
            secretField.setText(key);

            this.add(titleField);
            this.add(accountField);
            this.add(secretField);
        } else {
            this.remove(titleField);
            this.remove(accountField);
            this.remove(secretField);

            titleLabel.setText(title = titleField.getText());
            accountLabel.setText(account = accountField.getText());
            verificationCodeLabel.setText(Generator.getVerificationCode(key = secretField.getText()));

            this.add(titleLabel);
            this.add(accountLabel);
            this.add(verificationCodeLabel);
        }
        this.updateUI();
    }

    public void showButtons() {
        if (!editMode) {
            ((GridLayout)this.getLayout()).setRows(1);
            ((GridLayout)this.getLayout()).setColumns(0);
            this.remove(titleLabel);
            this.remove(accountLabel);
            this.remove(verificationCodeLabel);

            this.add(backButton = ActionButton.back(this));
            this.add(copyButton = ActionButton.copy(this));
            this.add(editButton = ActionButton.edit(this));
            this.add(removeButton = ActionButton.remove(this));

            this.updateUI();
        }
    }

    public void hideButtons() {
        ((GridLayout)this.getLayout()).setColumns(1);
        ((GridLayout)this.getLayout()).setRows(0);
        this.remove(backButton);
        this.remove(copyButton);
        this.remove(editButton);
        this.remove(removeButton);

        this.add(titleLabel);
        this.add(accountLabel);
        this.add(verificationCodeLabel);

        this.updateUI();
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public static class ActionButton {

        public static JPanel back(KeyItem parent) {
            final boolean[] hover = new boolean[]{false};
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (hover[0]) g.setColor(OryColors.RED.darker());
                    else g.setColor(OryColors.RED);
                    if (this.getWidth() >= this.getHeight()) {
                        ((Graphics2D)g).fill(new RoundRectangle2D.Double(this.getWidth() / 2 - this.getHeight() / 2,0,this.getHeight(),this.getHeight(),25,25));
                        g.drawImage(Icons.backIcon.getScaledInstance(this.getHeight(), this.getHeight(), Image.SCALE_SMOOTH),this.getWidth() / 2 - this.getHeight() / 2,0,null);
                    } else {
                        ((Graphics2D)g).fill(new RoundRectangle2D.Double(0, this.getHeight() / 2 - this.getWidth() / 2, this.getWidth(), this.getWidth(), 25,25));
                        g.drawImage(Icons.backIcon.getScaledInstance(this.getWidth(), this.getWidth(), Image.SCALE_SMOOTH),0, this.getHeight() / 2 - this.getWidth() / 2,null);
                    }
                }
            };
            panel.setOpaque(false);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parent.hideButtons();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    hover[0] = true;
                    panel.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover[0] = false;
                    panel.repaint();
                }
            });

            return panel;
        }

        public static JPanel edit(KeyItem parent) {
            final boolean[] hover = new boolean[]{false};
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (hover[0]) g.setColor(OryColors.RED.darker());
                    else g.setColor(OryColors.RED);
                    if (this.getWidth() >= this.getHeight()) {
                        ((Graphics2D)g).fill(new RoundRectangle2D.Double(this.getWidth() / 2 - this.getHeight() / 2,0,this.getHeight(),this.getHeight(),25,25));
                        g.drawImage(Icons.editIcon.getScaledInstance(this.getHeight(), this.getHeight(), Image.SCALE_SMOOTH),this.getWidth() / 2 - this.getHeight() / 2,0,null);
                    } else {
                        ((Graphics2D)g).fill(new RoundRectangle2D.Double(0, this.getHeight() / 2 - this.getWidth() / 2, this.getWidth(), this.getWidth(), 25,25));
                        g.drawImage(Icons.editIcon.getScaledInstance(this.getWidth(), this.getWidth(), Image.SCALE_SMOOTH),0, this.getHeight() / 2 - this.getWidth() / 2,null);
                    }
                }
            };
            panel.setOpaque(false);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parent.hideButtons();
                    parent.changeEditMode();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    hover[0] = true;
                    panel.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover[0] = false;
                    panel.repaint();
                }
            });

            return panel;
        }

        public static JPanel remove(KeyItem parent) {
            final boolean[] hover = new boolean[]{false};
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (hover[0]) g.setColor(OryColors.RED.darker());
                    else g.setColor(OryColors.RED);
                    if (this.getWidth() >= this.getHeight()) {
                        ((Graphics2D)g).fill(new RoundRectangle2D.Double(this.getWidth() / 2 - this.getHeight() / 2,0,this.getHeight(),this.getHeight(),25,25));
                        g.drawImage(Icons.deleteIcon.getScaledInstance(this.getHeight(), this.getHeight(), Image.SCALE_SMOOTH),this.getWidth() / 2 - this.getHeight() / 2,0,null);
                    } else {
                        ((Graphics2D)g).fill(new RoundRectangle2D.Double(0, this.getHeight() / 2 - this.getWidth() / 2, this.getWidth(), this.getWidth(), 25,25));
                        g.drawImage(Icons.deleteIcon.getScaledInstance(this.getWidth(), this.getWidth(), Image.SCALE_SMOOTH),0, this.getHeight() / 2 - this.getWidth() / 2,null);
                    }
                }
            };
            panel.setOpaque(false);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int reply = JOptionPane.showConfirmDialog(parent,"Are you sure you want to delete this entry? \n This cannot be undone!", "Permanent Deletion" , JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        MainFrame.codesPanel.remove(parent);
                        MainFrame.codesPanel.updateUI();
                        Storage.keys.remove(parent);
                        Storage.saveKeys();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    hover[0] = true;
                    panel.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover[0] = false;
                    panel.repaint();
                }
            });

            return panel;
        }

        public static JPanel copy(KeyItem parent) {
            final boolean[] hover = new boolean[]{false};
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (hover[0]) g.setColor(OryColors.RED.darker());
                    else g.setColor(OryColors.RED);
                    if (this.getWidth() >= this.getHeight()) {
                        ((Graphics2D)g).fill(new RoundRectangle2D.Double(this.getWidth() / 2 - this.getHeight() / 2,0,this.getHeight(),this.getHeight(),25,25));
                        g.drawImage(Icons.copyIcon.getScaledInstance(this.getHeight(), this.getHeight(), Image.SCALE_SMOOTH),this.getWidth() / 2 - this.getHeight() / 2,0,null);
                    } else {
                        ((Graphics2D)g).fill(new RoundRectangle2D.Double(0, this.getHeight() / 2 - this.getWidth() / 2, this.getWidth(), this.getWidth(), 25,25));
                        g.drawImage(Icons.copyIcon.getScaledInstance(this.getWidth(), this.getWidth(), Image.SCALE_SMOOTH),0, this.getHeight() / 2 - this.getWidth() / 2,null);
                    }
                }
            };
            panel.setOpaque(false);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parent.hideButtons();
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(Generator.getVerificationCode(parent.key)),null);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    hover[0] = true;
                    panel.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover[0] = false;
                    panel.repaint();
                }
            });

            return panel;
        }
    }
}
