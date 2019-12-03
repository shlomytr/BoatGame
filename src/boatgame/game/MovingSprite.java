package boatgame.game;

public abstract class MovingSprite extends Sprite {


    /**
     * constructor - creates an new MovingSprite object
     *
     * @param x    the x axis of the Sprite
     * @param y    the y axis of the Sprite
     * @param path the path of the image of the Sprite
     */
    public MovingSprite(int x, int y, String path) {
        super(x, y, path);
    }

    /**
     * updates the position of the MovingSprite related the sprite speed and position
     */
    public abstract void update();


}
