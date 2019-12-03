package boatgame.game;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GameScreen extends JPanel implements GameProperties, KeyListener {

    /**
     * constructor - sets the listener and sets the screen.
     */
    public GameScreen() {
        this.addKeyListener(GameScreen.this);
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();
    }

    /**
     * casts the Graphics object to Graphics2D and calls OnDraw
     *
     * @param g the graphics object to draw on screen
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        onDraw(g2D);
    }

    /**
     * calls onKeyPressed (Adapter design pattern)
     *
     * @param keyEvent the key pressed
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        onKeyPressed(keyEvent);
    }

    /**
     * calls onKeyReleased (Adapter design pattern)
     *
     * @param keyEvent the key released
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        onKeyReleased(keyEvent);
    }

    /**
     * when pressing a key, do an action
     *
     * @param event the key pressed
     */
    protected abstract void onKeyPressed(KeyEvent event);

    /**
     * when releasing a pressed key, do an action
     *
     * @param event the key released
     */
    protected abstract void onKeyReleased(KeyEvent event);

    /**
     * draw 2d objects on the screen
     *
     * @param g2d Graphics2D object used to draw on screen
     */
    protected abstract void onDraw(Graphics2D g2d);

    /**
     * called because of KeyListener
     *
     * @param keyEvent key typed
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

}
