package learn.mvcgame.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@RestController
public class GameController {

    static List<String> moves = new ArrayList<>(Arrays.asList("Rock", "Paper", "Scissors"));
    Random rand = new Random();
    static String currentMove;
    static int numWins;
    static int numLosses;

    @GetMapping("/move")
    public String getMove() {
        return moves.get(rand.nextInt(3));
    }

    @GetMapping("/wins")
    public int getWins() {
        return numWins;
    }

    @GetMapping("/losses")
    public int getLosses() {
        return numWins;
    }

    @GetMapping("/status")
    public String getstatus() {
        return checkStatus();
    }

    @PutMapping("/move/{index}")
    public String getOptions(@PathVariable String index) {
        try {
            int num = Integer.parseInt(index);
            if (num > 2 || num < 0) {
                return "Invalid move! Loser! 0-2 pls.";
            }
            String computerMove = moves.get(rand.nextInt(3));
            String playerMove = moves.get(num);
            String outcome = getOutcome(computerMove, playerMove);
            String status = getstatus();
            return outcome + "\n" + status;
        } catch (NumberFormatException e) {
            return "Idk what you did. Please enter 0-2 weirdo!";
        }
    }


    private String getOutcome(String computerMove, String playerMove) {
        if (computerMove.equalsIgnoreCase(playerMove))
            return "Tie!!!";
        if (computerMove.equalsIgnoreCase("Rock") && playerMove.equalsIgnoreCase("Scissors")) {
            numLosses++;
            return "You lose! " + "The " + computerMove + " beats your " + playerMove + "!";
        }
        if (computerMove.equalsIgnoreCase("Scissors") && playerMove.equalsIgnoreCase("Paper")) {
            numLosses++;
            return "You lose! " + "The " + computerMove + " beats your " + playerMove + "!";
        }
        if (computerMove.equalsIgnoreCase("Paper") && playerMove.equalsIgnoreCase("Rock")) {
            numLosses++;
            return "You lose! " + "The " + computerMove + " beats your " + playerMove + "!";
        }
        numWins++;
        return "Victory! " + "Your " + playerMove + " beats " + computerMove + "!";
    }

    private String checkStatus() {
        if (numWins == 2) {
            numWins = 0;
            numLosses = 0;
            return "You won the whole game! 2 out of 3";
        }

        if (numLosses == 2) {
            numWins = 0;
            numLosses = 0;
            return "You lose the whole game! 2 out of 3";
        }
        return "Game goes on...";
    }
}
