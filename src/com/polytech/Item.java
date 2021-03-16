package com.polytech;

public class Item {

    private int size;

    private boolean placed;

    public Item(int size) {
        this.size = size;
        placed = false;
    }

    @Override
    public String toString() {
        return "Item{" +
                "size=" + size +
                ", placed=" + placed +
                '}';
    }
}
