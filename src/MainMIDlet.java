import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

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

