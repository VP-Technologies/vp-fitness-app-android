package com.vptech.fitness.app.fitness;

import java.util.Date;

/**
 * A service which is used to obtain information from the Google Fit services on Android
 * @author Aaron Vontell
 */
public class GoogleFitPlayerFitness implements PlayerFitness {

    private final String SERVICE_NAME = "Google Fit";

    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override
    public int getNumSteps() {
        return 0;
    }

    @Override
    public int getNumSteps(Date date) {
        return 0;
    }

    @Override
    public float getMilesTraveled() {
        return 0;
    }

    @Override
    public float getMilesTraveled(Date date) {
        return 0;
    }

    @Override
    public int caloriesBurned() {
        return 0;
    }

    @Override
    public int caloriesBurned(Date date) {
        return 0;
    }

    @Override
    public int caloriesConsumed() {
        return 0;
    }

    @Override
    public int caloriesConsumed(Date date) {
        return 0;
    }


}
