import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URI;

public class MainFrame extends JFrame {

    JPanel basePanel;
    static JPanel codesPanel;
    JScrollPane aboutScrollPane;

    boolean showingAbout = false;

    public MainFrame() {
        super("Java Authenticator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 600);
        this.setJMenuBar(createJMenuBar());
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("icons/lock.png")));

        basePanel = new JPanel(new GridBagLayout());
        basePanel.setBackground(Color.white);

        addGB(basePanel, topPanel(), 0, 0.1, new Insets(10, 10, 5, 10));
        addGB(basePanel, codesPanel(), 1, 0.9, new Insets(5, 10, 10, 10));

        this.add(basePanel);

        this.setVisible(true);
        if (Authenticator.demo)
            JOptionPane.showMessageDialog(this, "You see demo keys \n These are not real codes or accounts", "Demo", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel topPanel() {
        JPanel panel = new JPanel(new GridLayout(1,0)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(OryColors.YELLOW);
                ((Graphics2D) g).fill(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 25, 25));
            }
        };
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Remaining time: " + Time.getRemainingTime());
        label.setFont(new Font("Helvetica", Font.PLAIN, 18));

        RoundProgressBar progressBar = new RoundProgressBar(OryColors.PURPLE);

        new Thread(() -> {
            while (true) {
                try {
                    //noinspection BusyWait
                    Thread.sleep(1000); //While program works this loop will never be cancelled so noinspection here
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int time = Time.getRemainingTime();
                label.setText("Remaining time: " + time);
                progressBar.setProgress((int)(((double) time / 30) * 100));
                if (time == 30)
                    for (KeyItem item : Storage.keys)
                        item.updateVerificationCode();
            }
        }).start();

        panel.add(label);
        panel.add(progressBar);

        return panel;
    }

    private JScrollPane codesPanel() {
        codesPanel = new JPanel(new GridLayout(0, 1));
        codesPanel.setOpaque(false);
        ((GridLayout) codesPanel.getLayout()).setVgap(10);

        for (KeyItem item : Storage.keys) codesPanel.add(item);

        JScrollPane scrollPane = new JScrollPane(codesPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        return scrollPane;
    }

    private JMenuBar createJMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem addNewSecretItem = new JMenuItem("Add empty secret");
        addNewSecretItem.addActionListener(e -> {
            KeyItem newItem = new KeyItem();
            Storage.keys.add(newItem);
            codesPanel.add(newItem, 0);
            codesPanel.updateUI();
        });
        fileMenu.add(addNewSecretItem);

        JMenuItem addNewItemFromQR = new JMenuItem("Add from QR code");
        addNewItemFromQR.addActionListener(e -> {
            FileDialog dialog = new FileDialog(this);
            dialog.setMultipleMode(false);
            dialog.setVisible(true);
            try {
                KeyItem newItem = QRCodeProcessing.getKeyItemFrom(ImageIO.read(dialog.getFiles()[0]));
                Storage.keys.add(newItem);
                codesPanel.add(newItem, 0);
                codesPanel.updateUI();
                Storage.saveKeys();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        fileMenu.add(addNewItemFromQR);

        JMenuItem takeScreenShotAndAdd = new JMenuItem("Add from QR Code Screenshot");
        takeScreenShotAndAdd.addActionListener(e -> {
            try {
                KeyItem newItem = QRCodeProcessing.getKeyItemFrom(new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())));
                Storage.keys.add(newItem);
                codesPanel.add(newItem, 0);
                codesPanel.updateUI();
                Storage.saveKeys();
            } catch (AWTException awtException) {
                awtException.printStackTrace();
            }
        });
        fileMenu.add(takeScreenShotAndAdd);

        fileMenu.add(new JSeparator());

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(quitItem);

        JMenu aboutMenu = new JMenu("About");

        JMenuItem aboutItem = new JMenuItem("About Authenticator");
        aboutItem.addActionListener(e -> toggleAbout());
        aboutMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        return menuBar;
    }

    private void toggleAbout() {
        showingAbout = !showingAbout;
        if (showingAbout) {
            aboutScrollPane = new JScrollPane();
            aboutScrollPane.getVerticalScrollBar().setUnitIncrement(5);
            aboutScrollPane.setBorder(BorderFactory.createEmptyBorder());
            aboutScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

            JPanel dependencyHolder = new JPanel(new GridLayout(0, 1));
            dependencyHolder.setBackground(Color.WHITE);

            RoundedButton closeAboutButton = new RoundedButton("Back", e -> this.toggleAbout(), OryColors.YELLOW);
            dependencyHolder.add(closeAboutButton);

            dependencyHolder.add(new DependencyItem("apache/commons-codec", "https://github.com/apache/commons-codec"));
            dependencyHolder.add(new DependencyItem("stleary/JSON-java", "https://github.com/stleary/JSON-java"));
            dependencyHolder.add(new DependencyItem("samdjstevens/java-totp", "https://github.com/samdjstevens/java-totp"));
            dependencyHolder.add(new DependencyItem("Akaya Telivigala Font", "https://fonts.google.com/specimen/Akaya+Telivigala"));
            dependencyHolder.add(new DependencyItem("zxing/zxing", "https://github.com/zxing/zxing"));

            aboutScrollPane.setViewportView(dependencyHolder);

            this.remove(basePanel);
            this.add(aboutScrollPane);
        } else {
            this.remove(aboutScrollPane);
            this.add(basePanel);
        }
        this.validate();
        this.repaint();
    }

    private static void addGB(JComponent parent, Component component, int gridy, double weighty, Insets insets) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = 1;
        constraints.weightx = 0.5;
        constraints.weighty = weighty;
        constraints.insets = insets;
        parent.add(component, constraints);
    }

    public static class DependencyItem extends JPanel {

        public DependencyItem(String title, String url) {
            super(new GridLayout(0, 1));
            this.setOpaque(false);
            this.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 15));
            this.setPreferredSize(new Dimension(0,120));

            JLabel titleLabel = new JLabel(title);
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, MainFrame.class.getResourceAsStream("fonts/AkayaTelivigala-Regular.ttf"));
                titleLabel.setFont(font.deriveFont(Font.PLAIN, 26));
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            this.add(titleLabel);

            this.add(new RoundedButton("Open in web", e -> {
                try {
                    Desktop.getDesktop().browse(URI.create(url));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }, OryColors.BLUE, 22));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(OryColors.PURPLE);
            ((Graphics2D) g).fill(new RoundRectangle2D.Double(10, 10, this.getWidth() - 20, this.getHeight() - 20, 25, 25));
        }
    }
}
