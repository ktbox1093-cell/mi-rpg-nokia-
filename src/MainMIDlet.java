package src;

import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Display;

public class MainMIDlet extends MIDlet {
    private GameCanvasExample canvas;

    public MainMIDlet() {
        canvas = new GameCanvasExample();
    }

    protected void startApp() {
        Display.getDisplay(this).setCurrent(canvas);
        canvas.start();
    }

    protected void pauseApp() {
        canvas.stop();
    }

    protected void destroyApp(boolean unconditional) {
        canvas.stop();
    }
}
