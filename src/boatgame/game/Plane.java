package boatgame.game;

import java.util.Random;

public class Plane extends MovingSprite {

    private Random random = new Random();

    /**
     * constructor - creates an new Plane object
     *
     * @param x    the x axis of the Plane
     * @param y    the y axis of the Plane
     * @param path the path of the image of the Plane
     */
    public Plane(int x, int y, String path) {
        super(x, y, path);
        setSpeed(3);
    }

    /**
     * updates the position of the Plane related the boat speed and X position
     */
    public void update() {
        setX(getX() - getSpeed());
    }

    /**
     * when the plane starts the new loop, sets its speed between 3-6
     */
    public void initializeSpeed() {
        setSpeed(random.nextInt(4) + 3);
    }


}
