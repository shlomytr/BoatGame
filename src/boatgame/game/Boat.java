package boatgame.game;

public class Boat extends Sprite {

    /**
     * constructor - creates an new Boat object
     *
     * @param x    the x axis of the Boat
     * @param y    the y axis of the Boat
     * @param path the path of the image of the Boat
     */
    public Boat(int x, int y, String path) {
        super(x, y, path);
    }

    /**
     * if possible, moves the boat to the left
     */
    public void moveLeft() {
        // if the boat is at the left edge, don't move it
        if (getX() < 0)
            return;
        accelerate();
        setX(getX() - getSpeed());

    }

    /**
     * if possible, moves the boat to the right
     */
    public void moveRight() {
        // if the boat is at the right edge, don't move it
        if (getX() > GAME_WIDTH - BOAT_SIZE)
            return;
        accelerate();
        setX(getX() + getSpeed());

    }

    /**
     * if the left or right key is pressed and not passing the maximum speed, increase the speed by 3
     */
    private void accelerate() {
        if (getSpeed() < MAX_SPEED)
            setSpeed(getSpeed() + 3);
    }
}
