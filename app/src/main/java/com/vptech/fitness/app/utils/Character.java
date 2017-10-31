package com.vptech.fitness.app.utils;

/**
 * Created by Neil on 8/23/2017.
 */

public class Character {

    private int health;
    private int speed;
    private int attack;
    private int level;

    public Character(int health, int speed, int attack, int level) {
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
