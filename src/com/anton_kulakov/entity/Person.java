package com.anton_kulakov.entity;

abstract public class Person extends Entity {
    private int speed;
    private int hp;

    public Person(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    abstract void makeMove();
}
