package com.vptech.fitness.app.game;

import android.view.View;

import com.vptech.fitness.app.fitness.PlayerFitness;

import java.util.Set;

/**
 * A representation of a player, which is synced with our database. Through a system of dirty
 * property tracking.
 * @author Aaron Vontell
 */
public class Player {

    /** Gamification aspects **/
    private int level;
    private int experienceTotal;
    private int experienceLevel;
    private int faction;

    /** Fitness aspects **/
    private PlayerFitness fitness;
    private long fitnessLastSynced;

    /** Tracking for syncing **/
    private Set<Property> dirtyProperties;

    /** User account properties **/
    private String username;

    /**
     * Creates a new player from scratch
     */
    public Player() {

    }

    /**
     * Creates a player from a username
     * @param username
     */
    public Player(String username) {

    }


    public void startSync(View toShowStart, View toShowEnd) {

    }

}
