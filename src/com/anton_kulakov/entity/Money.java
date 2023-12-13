package com.anton_kulakov.entity;

public class Money extends Entity {
    private boolean isSpent = false;

    public void setSpent(boolean spent) {
        isSpent = spent;
    }
    public boolean getSpent() {
        return isSpent;
    }
}
