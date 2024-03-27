package com.k17.pr1k17;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Generates the game tree up to an arbitrary depth restriction
 * Uses delta encoding to store the tree structures for memory efficiency
 */
/*
    Delta encoding used in the game tree generation takes more processing when decoding
    Algorithms have to traverse through the tree and decode the delta encoding to get their GameState
    less memory is used for storing the tree itself - can store larger trees / use less memory
    Each state is represented using:
    1. The total points
    2. The bank points
    3. The index (n) of the first number to be summed. 2nd number index is always n+1
    4. The first and second numbers that were summed

    Maximum depth that can ever be achieved in the game is 25, including the initial GameState
    24 * 23 * 22 * ... * 1 = 2.5 * 10^23 = 250 quintillion board states
    Let's assume 1 board state - 1KB of memory - worst case scenario
    Memory needed to store the entire tree = 250 billion GB = 250 million TB

    Depth of 3 -
    24 * 23 * 22 = 12144 board states
    Memory needed to store the entire tree = 12.144 MB

    Depth of 5
    24 * 23 * 22 * 21 * 20 = 12144 * 21 * 20 = 5080320 board states
    Memory needed to store the entire tree = 5.08 GB
 */
public class TreeGenerator
{
    private short maximumDepth = 3;
    private final GameState initialGameState;

    /**
     * ONLY USE THIS IF YOU WANT TO OVERWRITE DEFAULT MAXIMUM DEPTH
     * <br>
     * CONSIDER USING CONSTRUCTOR WITHOUT maximumDepth PARAMETER
     * <br>
     * DEFAULT MAXIMUM DEPTH IS 3
     * @param initialState the initial game state at a given move that the tree will be generated from
     * @param maximumDepth the maximum depth of the tree
     */
    public TreeGenerator(GameState initialState, short maximumDepth)
    {
        this.initialGameState = initialState;
        this.maximumDepth = maximumDepth;
    }

    /**
     * @param initialState the initial game state at a given move that the tree will be generated from
     */
    public TreeGenerator(GameState initialState)
    {
        this.initialGameState = initialState;
    }

    /**
     *
     * Recursively generates the game tree up to the specified maximum depth given the initial game state
     * @param state the initial game state
     * @param depth the maximum depth of the tree
      */
    public List<TreeNode> generateTree(GameState state, int depth) {
        // Generate the initial children
        return generateInitialChildren(state, depth);

        // TODO: Recursive method calling to generate children to the given depth while applying GameState changes
    }

    /**
     * Generates the initial children of the game tree based on the initial GameState
     * @param state
     * @param depth
     * @return a list of the initial children of the game tree - always n-1 children where n is the number of game numbers
     */
    private List<TreeNode> generateInitialChildren(GameState state, int depth) {
        List<TreeNode> nextStates = new ArrayList<>();
        LinkedList<GameNumber> gameNumbers = state.gameNumbers();

        for (int i = 0; i < gameNumbers.size() - 1; i++) {
            short firstSummedValue = gameNumbers.get(i).getValue();
            short secondSummedValue = gameNumbers.get(i + 1).getValue();

            // In the initial states, the total points and bank points change based on the first two number sum
            Pair<Short, Short> points = calculatePoints(firstSummedValue, secondSummedValue, state.totalPoints(), state.bankPoints());

            TreeNode nextState = new TreeNode(points.getKey(), points.getValue(), (short)i, firstSummedValue, secondSummedValue);
            nextStates.add(nextState);
        }

        return nextStates;
    }

    /**
     * Generates descendants of the initial children based on the changes in the game state
     * @param state
     * @param depth
     * @return a list of the children of the game tree
     */
    private List<TreeNode> generateAllChildren(GameState state, int depth) {
        // TODO: Implement generateAllChildren method
        /* Similar to generateInitialChildren, but it also takes into account the changes in the game state from the parent node
        And applies them to the current GameState - therefore the children will be correctly generated
         */

        return null;
    }

    /**
     * Calculates the point changes based on the sum of the first two numbers and returns the new total points and bank points as a Pair
     * Key - total points, Value - bank points
     * @param firstSummedValue
     * @param secondSummedValue
     * @param totalPoints
     * @param bankPoints
     * @return
     */
    private Pair<Short, Short> calculatePoints(short firstSummedValue, short secondSummedValue, short totalPoints, short bankPoints) {
        short sum = (short) (firstSummedValue + secondSummedValue);
        if (sum > 7) {
            totalPoints += 1;
        } else if (sum < 7) {
            totalPoints -= 1;
        } else {
            bankPoints += 1;
        }
        return new Pair<>(totalPoints, bankPoints);
    }
}
