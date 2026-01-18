package snake_and_ladder.models;

import snake_and_ladder.enums.ObstacleType;

public abstract class Obstacle {
    protected int src;
    protected int dist;

    Obstacle(int src, int dist) {
        this.src = src;
        this.dist = dist;
    }

    public int getSrc() {
        return src;
    }

    public int getDest() {
        return dist;
    }

    public int movePlayer(){
        return dist;
    }

    public abstract ObstacleType getObstacleType();
}
