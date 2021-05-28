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

    public static final HashMap<String, String> gameElementsImagesPaths = new LinkedHashMap<>() {{
        put("ball", "src/main/resources/ball.png");
        put("racket", "src/main/resources/racket.png");
        put("gameBackground", "src/main/resources/background.jpg");
        put("menuBackground", "src/main/resources/menuBackground.jpg");
    }};

    public static String getPathToGameElementFromContentRoot(String elementName) {
        return gameElementsImagesPaths.get(elementName);
    }

    public static String getPathToBrickImageFromContentRoot(BrickType type) {
        return brickImagesPaths.get(type);
    }
}
