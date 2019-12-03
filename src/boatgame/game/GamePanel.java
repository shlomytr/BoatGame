package boatgame.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends GameScreen implements Runnable {

    private Random random = new Random();
    private int life = 3, score;
    private boolean isRunning = false;
    private boolean isNotPaused = true;
    private Thread gameThread;
    private Sprite background = new Sprite(0, 0, BACKGROUND_PATH);
    private Sprite sea = new Sprite(0, SEA_LEVEL, SEA_PATH);
    private Boat boat = new Boat(BOAT_INIT_X, BOAT_INIT_Y, BOAT_PATH);
    private Plane plane = new Plane(GAME_WIDTH, 35, PLANE_PATH);
    private ArrayList<Parachutist> parachutists = new ArrayList<>();

    /**
     * constructor -  sets the game's window size
     */
    public GamePanel() {
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    }

    /**
     * starts the game's thread
     */
    @Override
    public void addNotify() {
        super.addNotify();
        if (gameThread == null) {
            gameThread = new Thread(GamePanel.this);
        }
        gameThread.start();
    }

    /**
     * @param event makes an action related to the presses of the user
     */
    @Override
    protected void onKeyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            // if left was pressed move the boat left
            case KeyEvent.VK_LEFT:
                if (isNotPaused)
                    boat.moveLeft();
                break;
            // if right was pressed move the boat right
            case KeyEvent.VK_RIGHT:
                if (isNotPaused)
                    boat.moveRight();
                break;
            // if space or P was pressed, pause or unpause the game
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_P:
                isNotPaused = !isNotPaused;
                repaint();
                break;
            default:
                break;
        }

    }

    /**
     * when releasing the arrow key, set the speed at 0
     *
     * @param event the key released
     */
    @Override
    protected void onKeyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_RIGHT)
            boat.setSpeed(0);
    }

    /**
     * draw on the screen the relevant sprite
     *
     * @param g2d Graphics2D object used to draw on screen
     */
    @Override
    protected void onDraw(Graphics2D g2d) {
        // draw the background, the boat and the plane
        background.draw(g2d);
        sea.draw(g2d);
        boat.draw(g2d);
        plane.draw(g2d);
        // draw all the parachutists
        for (Parachutist parachutist : parachutists)
            parachutist.draw(g2d);
        // makes the font bigger and bold
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g2d.setColor(Color.RED);
        // put the lives of the player on the screen
        g2d.drawString("Lives: " + life, 10, 20);
        // change the color to black and put the score on the screen
        g2d.setColor(Color.BLACK);
        g2d.drawString("Score: " + score, GAME_WIDTH - 120, 20);
        // if the game is not over and the user press the pause keys, puts a "game paused" message on the screen
        if (!isNotPaused && isRunning)
            g2d.drawString("Game paused. Please press SPACE or P to continue", 300, 30);
        //when the game is over, put a relevant message on the screen
        if (!isRunning)
            g2d.drawString("GAME OVER", GAME_WIDTH / 2 - 50, GAME_HEIGHT / 2 - 25);
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            // sleep so that the thread will get CPU time
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // while the game is active
            while (isNotPaused && isRunning) {
                // checks how much time is to make the next move
                long startTime = System.currentTimeMillis();
                updateGame();
                repaint();
                long endTime = System.currentTimeMillis() - startTime;
                long waitTime = (MILLISECOND / FPS) - endTime / MILLISECOND;

                //sleep the time needed to the game to continue
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * updates the game -
     */
    private void updateGame() {
        plane.update();
        deployParachutist();
        for (int i = 0; i < parachutists.size(); i++) {
            // for each parachutist that is on the screen, update where it is and check for collisions
            Parachutist parachutist = parachutists.get(i);
            parachutist.update();
            checkParachutistCollide(parachutist);
        }
        // when the plane got to the end of the screen, returns its position to the start with a random speed
        if (plane.getX() < -145) {
            plane.setX(GAME_WIDTH);
            plane.initializeSpeed();
        }
    }

    /**
     * checks if a parachutist collides with the boat and add 10 points or the water and remove one life
     *
     * @param parachutist the parachutist that is being checked
     */
    private void checkParachutistCollide(Parachutist parachutist) {
        // if the parachutist got to the water remove one life
        if (parachutist.getY() > 420) {
            life--;
            // if the lives are more than 0, remove the parachutist
            if (life > 0)
                parachutists.remove(parachutist);
            else
                // end the game
                isRunning = false;
        }
        // if the player manages to catch the parachutist, remove it and add 10 points to the score
        if (parachutist.getBound().intersects(boat.getBound())) {
            parachutists.remove(parachutist);
            score += 10;
        }
    }

    /**
     * if there are less than 3 parachutists, deploy one randomly with random increased speed related to ths score
     */
    private void deployParachutist() {
        if (parachutists.size() < 3 && plane.getX() < GAME_WIDTH - 85 && plane.getX() > 10) {
            if (random.nextInt(150) == random.nextInt(150)) {
                parachutists.add(new Parachutist(plane.getX(), plane.getY() + 100, PARACHUTIST_PATH, random.nextInt(score / 50 + 1) + 1));
            }
        }
    }


}
