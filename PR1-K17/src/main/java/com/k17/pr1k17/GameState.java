package com.k17.pr1k17;

import java.util.LinkedList;

/**
 * Record class to store the current game state at a given move
 * <br>
 * Stores the current state of the game by storing the total points, bank points, and the game numbers
 * @param totalPoints
 * @param bankPoints
 * @param gameNumbers
 */
public record GameState(short totalPoints, short bankPoints, LinkedList<GameNumber> gameNumbers) {
}
