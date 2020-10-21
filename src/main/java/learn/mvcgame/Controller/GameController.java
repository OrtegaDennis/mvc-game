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

    @GetMapping("/move")
    public String getMove() {
        return moves.get(rand.nextInt(3));
    }

    @PutMapping("/move/{index}")
    public String getOptions(@PathVariable int index) {
        String computerMove = moves.get(rand.nextInt(3));
        String playerMove = moves.get(index);
        String outcome = getOutcome(computerMove, playerMove);
        return outcome;
    }


    private String getOutcome(String computerMove, String playerMove) {
        if (computerMove.equalsIgnoreCase(playerMove))
            return "Tie!!!";
        if (computerMove.equalsIgnoreCase("Rock") && playerMove.equalsIgnoreCase("Scissors")) {
            return "You lose!";
        }
        if (computerMove.equalsIgnoreCase("Scissors") && playerMove.equalsIgnoreCase("Paper")) {
            return "You lose!";
        }
        if (computerMove.equalsIgnoreCase("Paper") && playerMove.equalsIgnoreCase("Rock")) {
            return "You lose!";
        }
        return "Victory";
    }




}
