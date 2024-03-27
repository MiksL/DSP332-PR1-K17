package com.k17.pr1k17;

public class GameNumber
{
    private byte value;
    private boolean isSelected;

    public GameNumber(byte value) {
        this.value = value;
        this.isSelected = false;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
