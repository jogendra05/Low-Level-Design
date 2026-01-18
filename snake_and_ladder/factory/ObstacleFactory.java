package snake_and_ladder.factory;

import snake_and_ladder.enums.ObstacleType;
import snake_and_ladder.models.Ladder;
import snake_and_ladder.models.Obstacle;
import snake_and_ladder.models.Snake;

public class ObstacleFactory {
    public static Obstacle createObstacle(ObstacleType obstacle, int up, int down){
        return switch(obstacle){
            case SNAKE -> new Snake(up, down);
            case LADDER -> new Ladder(up, down);
            default -> throw new IllegalArgumentException("Invalid Obstacle Type");
        };
    }
}
