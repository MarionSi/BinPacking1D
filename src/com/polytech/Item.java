package com.polytech;

public class Item {

    private int size;
    private boolean placed;
    private Bin bin = null;

    public Item(int size) {
        this.size = size;
        placed = false;
    }

    public int getSize() {
        return size;
    }

    public void placedInBin(Bin bin) {
        placed = true;
        this.bin = bin;
    }

    public boolean isPlaced() {
        return placed;
    }

    @Override
    public String toString() {
        return "Item{" +
                "size=" + size +
                ", placed=" + placed +
                '}';
    }
}
