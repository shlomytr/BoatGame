package boatgame.game;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite implements GameProperties {

    private int x, y, speed;
    protected final Image image;

    /**
     * constructor - creates an new Sprite object
     *
     * @param x    the x axis of the Sprite
     * @param y    the y axis of the Sprite
     * @param path the path of the image of the Sprite
     */
    public Sprite(int x, int y, String path) {
        this.x = x;
        this.y = y;
        speed = 0;
        image = new ImageIcon(getClass().getResource(path)).getImage();
    }

    /**
     * getter for x axis
     *
     * @return the x axis
     */
    public int getX() {
        return x;
    }

    /**
     * setter for x axis
     *
     * @param x the new x position for the Sprite
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter for y axis
     *
     * @return the y axis
     */
    public int getY() {
        return y;
    }

    /**
     * setter for y axis
     *
     * @param y the new y position for the Sprite
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * getter for the speed
     *
     * @return the speed of the Sprite
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * setter for the speed
     *
     * @param speed the new speed of the Sprite
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * draws the Sprite using Graphics2D object
     *
     * @param g2D the Graphics2D object
     */
    protected void draw(Graphics2D g2D) {
        g2D.drawImage(this.image, getX(), getY(), null);
    }

    /**
     * @return a Rectangle with the same place of the Sprite
     */
    public Rectangle getBound() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
}


