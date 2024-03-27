package com.k17.pr1k17;

import java.util.List;

/**
 * Represents a node in the tree of game states
 * Stores the total points, bank points, and the first and second summed values and the first index
 */
public final class TreeNode {
    private final byte totalPoints;
    private final byte bankPoints;
    private final byte firstIndex;
    private final byte firstSummedValue;
    private final byte secondSummedValue;
    private List<TreeNode> children; // List of children nodes - takes up no memory if not initialized

    /**
     * @param totalPoints the total points
     * @param bankPoints the bank points
     * @param firstIndex the first summed value index (2nd value is always firstIndex + 1)
     * @param firstSummedValue the first summed value
     * @param secondSummedValue the second summed value
     */
    public TreeNode(byte totalPoints, byte bankPoints, byte firstIndex, byte firstSummedValue, byte secondSummedValue)
    {
        this.totalPoints = totalPoints;
        this.bankPoints = bankPoints;
        this.firstIndex = firstIndex;
        this.firstSummedValue = firstSummedValue;
        this.secondSummedValue = secondSummedValue;
    }

    public byte getTotalPoints() {
        return totalPoints;
    }

    public byte getBankPoints() {
        return bankPoints;
    }

    public byte getFirstIndex() {
        return firstIndex;
    }

    public byte getFirstSummedValue() {
        return firstSummedValue;
    }

    public byte getSecondSummedValue() {
        return secondSummedValue;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}
