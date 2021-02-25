import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.concurrent.atomic.AtomicInteger;

public class MainFrame extends JFrame {

    private JPanel basePanel;

    public MainFrame() {
        super("Java Authenticator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300,500);

        basePanel = new JPanel(new GridBagLayout());
        basePanel.setBackground(Color.white);

        addGB(basePanel, topPanel(),0,0.1,new Insets(10,10,5,10));
        addGB(basePanel, codesPanel(),1,0.9,new Insets(5,10,10,10));

        this.add(basePanel);

        this.setVisible(true);
    }

    private JPanel topPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(OryColors.YELLOW);
                ((Graphics2D)g).fill(new RoundRectangle2D.Double(0,0,this.getWidth(),this.getHeight(),25,25));
            }
        };
        panel.setOpaque(false);

        AtomicInteger startRemaining = new AtomicInteger(Time.getRemainingTime());
        JLabel label = new JLabel("Remaining time: " + startRemaining);
        label.setFont(new Font("Helvetica", Font.PLAIN, 18));

        new Thread(() -> {
            while (startRemaining.get() >= 1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                label.setText("Remaining time: " + startRemaining.decrementAndGet());
                if (startRemaining.get() == 30) {
                    for (KeyItem item : Storage.keys) item.updateVerificationCode();
                }
                if (startRemaining.get() == 1) {
                    startRemaining.set(31);
                }
            }
        }).start();

        panel.add(label);

        return panel;
    }

    private JScrollPane codesPanel() {
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.setOpaque(false);
        ((GridLayout)panel.getLayout()).setVgap(10);

        for (KeyItem item : Storage.keys) panel.add(item);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));

        return scrollPane;
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
}
