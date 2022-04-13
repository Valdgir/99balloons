package ninetynineballons.logic;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import ninetynineballons.graphics.GameSummaryFrame;

public class MouseListenerImpl implements MouseListener {
    private static final Logger LOG = Logger.getLogger(MouseListenerImpl.class.getName());

    private final GameObjects gameObjects;

    private final GameSummaryFrame gameSummary;

    /**
     * the panel with the balloons
     */
    private final Container panel;

    public MouseListenerImpl(Container panel, GameObjects gameObjects, GameSummaryFrame gameSummary) {
        this.gameObjects = gameObjects;
        this.gameSummary = gameSummary;
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        try {

            if (gameObjects.popBalloon()) {
                Score score = gameObjects.getScore();
                gameSummary.updateWithScore(score);
                gameSummary.displaySummary(score.isGameOver());
            }

        } catch (AWTException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (panel.equals(e.getSource())) {
            // Use the crosshair cursor for popping up the balloons.
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
            panel.setCursor(cursor);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // User had left the panel.
        Cursor cursor = Cursor.getDefaultCursor();
        panel.setCursor(cursor);
    }


}
