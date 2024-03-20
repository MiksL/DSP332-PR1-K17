package com.k17.pr1k17;

public class GameNumber
{
    private short value;
    private boolean isSelected;

    public GameNumber(short value) {
        this.value = value;
        this.isSelected = false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
