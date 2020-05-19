package eu.mrndesign.matned.searchEngine.data.jFrame;

import javax.swing.*;

public class BaseSwingScreen {

        protected JFrame frame;

        public void show() {
            frame.setVisible(true);
        }

        public void hide() {
            frame.setVisible(false);
        }

}
