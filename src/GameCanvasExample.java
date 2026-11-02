
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

public class GameCanvasExample extends GameCanvas implements Runnable {
    private boolean running;
    private Thread gameThread;
    
    // Posición inicial del jugador en el centro de la pantalla (320x240)
    private int playerX = 150;
    private int playerY = 110;
    private int playerSpeed = 3;

    public GameCanvasExample() {
        super(true); // Habilitar doble búfer para evitar parpadeos (screen tearing)
    }

    public void start() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stop() {
        running = false;
        try {
            if (gameThread != null) {
                gameThread.join();
            }
        } catch (InterruptedException e) {}
    }

    public void run() {
        Graphics g = getGraphics();
        while (running) {
            long startTime = System.currentTimeMillis();

            updateGame();
            renderGame(g);

            long elapsed = System.currentTimeMillis() - startTime;
            long delay = 30 - elapsed; // Mantener ~33 FPS estables
            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {}
            }
        }
    }

    private void updateGame() {
        int keystate = getKeyStates();

        // Control mediante D-pad o teclado numérico del E72
        if ((keystate & UP_PRESSED) != 0) {
            playerY -= playerSpeed;
            if (playerY < 0) playerY = 0;
        }
        if ((keystate & DOWN_PRESSED) != 0) {
            playerY += playerSpeed;
            if (playerY > getHeight() - 12) playerY = getHeight() - 12;
        }
        if ((keystate & LEFT_PRESSED) != 0) {
            playerX -= playerSpeed;
            if (playerX < 0) playerX = 0;
        }
        if ((keystate & RIGHT_PRESSED) != 0) {
            playerX += playerSpeed;
            if (playerX > getWidth() - 12) playerX = getWidth() - 12;
        }
    }

    private void renderGame(Graphics g) {
        // 1. Limpiar pantalla (Fondo negro de estilo retro)
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, getWidth(), getHeight());

        // 2. Dibujar interfaz / HUD básico o texto de estado
        g.setColor(255, 255, 255);
        g.drawString("RPG Nivel 1000 - E72", 5, 5, Graphics.TOP | Graphics.LEFT);

        // 3. Dibujar al jugador (Representado temporalmente como un rectángulo verde)
        g.setColor(0, 255, 0);
        g.fillRect(playerX, playerY, 12, 12);

        // Volcar búfer gráfico a la pantalla del Nokia
        flushGraphics();
    }
}
