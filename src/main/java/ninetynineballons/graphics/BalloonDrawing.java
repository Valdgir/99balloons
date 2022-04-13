package ninetynineballons.graphics;

import java.awt.*;

public class BalloonDrawing {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 40;

    /**
     * the X coordinate
     */
    private final int x;

    /**
     * the Y coordinate
     */
    private int y;

    /**
     * the minimum Y coordinate for the balloon to be visible
     */
    private final int minY;

    private BalloonState state = BalloonState.HOLD;

    private final Color color;

    public BalloonDrawing(int x, int y, Color color, int minY) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.minY = minY;
    }

    public void paint(Graphics g) {

        if (y < minY && y > 0) {
            fly(g);

            if (y > 0 && state == BalloonState.HOLD) {
                // The balloon is still visible.
                g.setColor(color); // add new
                g.fillOval(x, y, WIDTH, HEIGHT);
            }

        } else if (y > 0) {
            // the balloon is not yet visible
            moveUp();
        }

    }

    /**
     * Checks whether the balloon is popped or flied out of the screen.
     *
     * @return true, if in the player's reach
     */
    public boolean isPlayable() {
        return !state.isPopped() && y > 0;
    }

    private void moveUp() {

        if (state == BalloonState.FLY) {
            y--;
            // fly no more
            state = BalloonState.HOLD;
        }

    }

    private void fly(Graphics g) {

        if (state == BalloonState.FLY || state == BalloonState.POPPED) {
            // remove previous drawing
            g.setColor(Color.WHITE);
            g.fillOval(x, y, WIDTH, HEIGHT);

            if (state == BalloonState.POPPED) {
                state = BalloonState.INVISIBLE;
            }

        }

        moveUp();
    }

    /**
     * Checks whether the balloon collides with the current mouse pointer location.
     *
     * @param poppedColor the popped balloon color
     * @return true, if is hit
     */
    public boolean isHit(Color poppedColor) {
        boolean hit;

        if (y < minY && y > 0 && (state == BalloonState.FLY || state == BalloonState.HOLD)) {
            // The balloon is classified as a hit when the pointed colour is the balloon colour.
            hit = color.equals(poppedColor);
        } else {
            // can't hit
            hit = false;
        }

        return hit;
    }

    public BalloonState getState() {
        return state;
    }

    public void setState(BalloonState state) {
        this.state = state;
    }

}
