package boatgame.game;


public class Parachutist extends MovingSprite {
    /**
     * constructor - creates an new Parachutist object
     *
     * @param x     the x axis of the Parachutist
     * @param y     the y axis of the Parachutist
     * @param path  the path of the image of the Parachutist
     * @param speed the speed of the Parachutist. will be higher if the score is higher
     */
    public Parachutist(int x, int y, String path, int speed) {
        super(x, y, path);
        setSpeed(speed);
    }

    /**
     * updates the position of the Parachutist related the boat speed and Y position
     */
    @Override
    public void update() {
        setY(getY() + getSpeed());
    }


}
