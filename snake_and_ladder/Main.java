package snake_and_ladder;

import java.util.Scanner;

import snake_and_ladder.models.Player;
import snake_and_ladder.service.Game;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the board size : ");
        int boardSize = scanner.nextInt();

        System.out.print("Enter the number of snakes : ");
        int noOfSnakes = scanner.nextInt();

        System.out.print("Enter the number of ladders : ");
        int noOfLadders = scanner.nextInt();

        System.out.print("Enter the number of players : ");
        int noOfPlayers = scanner.nextInt();

        System.out.print("Enter the number of Dice : ");
        int noOfDice = scanner.nextInt();

        Game game = new Game(noOfSnakes, noOfLadders, boardSize, noOfDice);

        for (int i = 0; i < noOfPlayers; i++){
            System.out.print("Enter the name of player " + (i + 1) + " : ");
            Player player = new Player(scanner.next());
            game.addPlayer(player);
        }

        game.startGame();
    }
}
