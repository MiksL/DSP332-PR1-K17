package com.k17.pr1k17;

import java.util.LinkedList;

/*
 Record class to store the current game state at a given move
 Stores the total points, bank points and the game numbers
 Has a constructor to initialize the record and provides getters for the fields
 Used to pass the current game state to the heuristic evaluation function and the algorithms
 */
public record GameState(short totalPoints, short bankPoints, LinkedList<GameNumber> gameNumbers) {
}
