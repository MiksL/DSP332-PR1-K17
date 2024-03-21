package com.k17.pr1k17;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HeuristicEvaluation {
    // TODO: Implement heuristic evaluation function - used in both algorithms
    // Game data that will be passed to the algorithms:
    // 1. Current game point tallies - totalPoints and bankPoints - MainController.java
    // 2. Current board state - gameNumbers LinkedList - MainController.java
    // Algorithm calls HeuristicEvaluation.java for heuristic evaluation, same evaluation function for both algorithms


    // Map will contain the index of the first number to be summed and the heuristic score of the numbers to be summed
    public Map<Short,Short> evaluate(GameState gameState)
    {
        LinkedList<GameNumber> gameNumbers = gameState.gameNumbers();
        short totalPoints = gameState.totalPoints();
        short bankPoints = gameState.bankPoints();

        Map<Short, Short> points = new HashMap<>();

        // Depending on the number of moves left, the heuristic evaluation will be different

        // Go through all the numbers and look at the remaining moves, both point tallies and then evaluate the board
        // Evaluation function WILL NOT take into account the shortest paths, since they all have the same end condition -> 1 number left
        // First player is the maximizer -> attempting to reach an even number
        // Second player is the minimizer -> attempting to reach an odd number

        for(GameNumber gameNumber : gameNumbers)
        {
            short number = gameNumber.getValue();
        }

        return points;
    }

}
