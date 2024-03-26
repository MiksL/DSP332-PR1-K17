package com.k17.pr1k17;

/**
    Generates the game tree up to an arbitrary depth restriction
    Uses delta encoding to store the tree structures for memory efficiency
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
 */
public class TreeGenerator
{

}
