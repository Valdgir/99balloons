package ninetynineballons.graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ninetynineballons.logic.GameObjects;
import ninetynineballons.logic.MouseListenerImpl;

public class GameArtFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private GameObjects drawings;

    public GameArtFrame() {
        setTitle("99 Balloons");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initGameObjects() {
        Container panel = getContentPane();
        drawings = new GameObjects(getWidth(), getHeight());
        GameSummaryFrame gameSummary = new GameSummaryFrame(drawings, this);
        panel.addMouseListener(new MouseListenerImpl(panel, drawings, gameSummary));
        // start refreshing screen
        Timer timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        drawings.paintAll(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawings.tick();
        repaint();
    }
}
