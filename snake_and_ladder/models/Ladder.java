package snake_and_ladder.models;

import snake_and_ladder.enums.ObstacleType;

public class Ladder extends Obstacle{
    public Ladder(int top, int bottom){
        super(bottom, top);
    }

    public ObstacleType getObstacleType(){
        return ObstacleType.LADDER;
    }
}
