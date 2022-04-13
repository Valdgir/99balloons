
package ninetynineballons.graphics;

import javax.swing.*;

public class ShowGameFrame {

    public static void showGameFrame() {
        GameArtFrame artFrame = new GameArtFrame();
        artFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        artFrame.setSize(200, 400);
        artFrame.initGameObjects();
        artFrame.setVisible(true);
    }

}
