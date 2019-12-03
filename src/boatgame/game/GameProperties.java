package boatgame.game;

/**
 * Properties for the game
 */
public interface GameProperties {

    int GAME_WIDTH = 1080, GAME_HEIGHT = 720;
    int BOAT_INIT_X = GAME_WIDTH / 2 - 125, BOAT_INIT_Y = GAME_HEIGHT - 250;
    int SEA_LEVEL = 496, BOAT_SIZE = 224;
    String BACKGROUND_PATH = "/boatgame/game/asset/images/background.png";
    String SEA_PATH = "/boatgame/game/asset/images/sea.png";
    String BOAT_PATH = "/boatgame/game/asset/images/boat.png";
    String PLANE_PATH = "/boatgame/game/asset/images/plane.png";
    String PARACHUTIST_PATH = "/boatgame/game/asset/images/parachutist.png";

    int MAX_SPEED = 12;
    long MILLISECOND = 1000L;
    int FPS = 60;

}
