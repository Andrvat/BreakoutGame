package utilities;

import model.BrickType;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PathsDistributor {
    public static final HashMap<BrickType, String> brickImagesPaths = new LinkedHashMap<>() {{
       put(BrickType.YELLOW, "src/main/resources/yellowBrick.png");
       put(BrickType.GREEN, "src/main/resources/greenBrick.png");
       put(BrickType.ORANGE, "src/main/resources/orangeBrick.png");
       put(BrickType.RED, "src/main/resources/redBrick.png");
    }};

    public static String getPathToBallImageFromContentRoot() {
        return "src/main/resources/ball.png";
    }

    public static String getPathToBrickImageFromContentRoot(BrickType type) {
        return brickImagesPaths.get(type);
    }

    public static String getPathToRacketImageFromContentRoot() {
        return "src/main/resources/racket.png";
    }
}
