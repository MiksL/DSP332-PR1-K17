package com.k17.pr1k17;

import java.util.List;

/**
 * Represents a node in the tree of game states
 * Stores the total points, bank points, and the first and second summed values and the first index
 */
public final class TreeNode {
    private final short totalPoints;
    private final short bankPoints;
    private final short firstIndex;
    private final short firstSummedValue;
    private final short secondSummedValue;
    private List<TreeNode> children; // List of children nodes - takes up no memory if not initialized

    /**
     * @param totalPoints the total points
     * @param bankPoints the bank points
     * @param firstIndex the first summed value index (2nd value is always firstIndex + 1)
     * @param firstSummedValue the first summed value
     * @param secondSummedValue the second summed value
     */
    public TreeNode(short totalPoints, short bankPoints, short firstIndex, short firstSummedValue, short secondSummedValue)
    {
        this.totalPoints = totalPoints;
        this.bankPoints = bankPoints;
        this.firstIndex = firstIndex;
        this.firstSummedValue = firstSummedValue;
        this.secondSummedValue = secondSummedValue;
    }

    public short getTotalPoints() {
        return totalPoints;
    }

    public short getBankPoints() {
        return bankPoints;
    }

    public short getFirstIndex() {
        return firstIndex;
    }

    public short getFirstSummedValue() {
        return firstSummedValue;
    }

    public short getSecondSummedValue() {
        return secondSummedValue;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}
