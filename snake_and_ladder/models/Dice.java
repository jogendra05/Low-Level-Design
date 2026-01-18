package snake_and_ladder.models;

import java.util.Random;

public class Dice {
    private final int noOfDice;
    private Random random = new Random();
    public Dice(int noOfDice) {
        this.noOfDice = noOfDice;
    }
    
    public int roll(){
        int sum = 0;
        for (int i = 1; i <= noOfDice; i++){
            sum += random.nextInt(6)+1;
        }
        return sum;
    }
}