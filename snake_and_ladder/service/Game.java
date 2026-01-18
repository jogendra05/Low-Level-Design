package snake_and_ladder.service;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

import snake_and_ladder.enums.ObstacleType;
import snake_and_ladder.factory.ObstacleFactory;
import snake_and_ladder.models.Board;
import snake_and_ladder.models.Dice;
import snake_and_ladder.models.Obstacle;
import snake_and_ladder.models.Player;

public class Game {
    private final int noOfSnakes;
    private final int noOfLadders;
    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;

    public Game(int noOfSnakes, int noOfLadders, int size, int noOfDice) {
        this.noOfSnakes = noOfSnakes;
        this.noOfLadders = noOfLadders;
        this.board = new Board(size);
        this.players = new ArrayDeque<>();
        this.dice = new Dice(noOfDice);
        initBoardObstacle();
    }

    private void initBoardObstacle() {
        generateObstacle(noOfLadders, ObstacleType.LADDER);
        generateObstacle(noOfSnakes, ObstacleType.SNAKE);
    }

    private void generateObstacle(int count, ObstacleType type) {
        Random random = new Random();
        int size = board.getSize();

        while (count > 0) {
            int src = random.nextInt(size) + 1;
            int dest = random.nextInt(size) + 1;

            if (src == dest) continue;

            if (type == ObstacleType.SNAKE && src < dest) {
                int temp = src;
                src = dest;
                dest = temp;
            }

            if (type == ObstacleType.LADDER && src > dest) {
                int temp = src;
                src = dest;
                dest = temp;
            }

            if (src <= 1 || dest <= 1 || src == size || dest == size) continue;

            Obstacle obstacle = ObstacleFactory.createObstacle(type, src, dest);
            if (board.addObstacle(obstacle)) count--;
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        board.printBoard(players);

        while (players.size() > 1) {
            Player curPlayer = players.poll();
            System.out.println("-----------------------------------");

            int diceRoll = dice.roll();
            System.out.println(curPlayer.getName() + " rolled " + diceRoll);

            int newPosition = board.getNewPosition(curPlayer, diceRoll);

            if (newPosition == curPlayer.getPosition()) {
                players.offer(curPlayer);
                continue;
            }

            curPlayer.setPosition(newPosition);

            if (newPosition == board.getSize()) {
                System.out.println(curPlayer.getName() + " has won the game!");
            } else {
                players.offer(curPlayer);
            }

            board.printBoard(players);
        }
    }
}
