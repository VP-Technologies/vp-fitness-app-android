package com.vptech.fitness.app.fitness;

import java.util.Date;

/**
 * An interface for of all fitness aspects of a player (steps taken, miles traveled, heart
 * rate, calories burned, etc). <b>NOTE THAT ALL CALLS MADE TO IMPLEMENTING INTERFACES SHOULD
 * BE DONE ASYNCHRONOUSLY!</b>
 *
 * Not all services provide functionality to obtain the information required by this interface. In
 * these cases, the method will throw a <code>FitnessFeatureNotFoundException</code>
 *
 * @author Aaron Vontell
 */
public interface PlayerFitness {

    /**
     * Returns the name of the service being used to get the fitness information
     * @return the name of the service being used to get the fitness information
     */
    String getServiceName();

    /**
     * Returns the number of steps taken during the current day
     * @return the number of steps taken during the current day
     */
    int getNumSteps();

    /**
     * Returns the number of steps taken during the given date (which must be in the past)
     * @param date The date to query for information
     * @return the number of steps taken during the given date
     */
    int getNumSteps(Date date);

    /**
     * Returns the number of miles traveled during the current day
     * @return the number of miles traveled during the current day
     */
    float getMilesTraveled();

    /**
     * Returns the number of miles traveled during the given date (which must be in the past)
     * @param date The date to query for information
     * @return the number of miles traveled during the given date
     */
    float getMilesTraveled(Date date);

    /**
     * Returns the number of calories burned during the current day
     * @return the number of calories burned during the current day
     */
    int caloriesBurned();

    /**
     * Returns the number of calories burned during the given date (which must be in the past)
     * @param date The date to query for information
     * @return the number of calories burned during the given date
     */
    int caloriesBurned(Date date);

    /**
     * Returns the number of calories consumed during the current day
     * @return the number of calories consumed during the current day
     */
    int caloriesConsumed();

    /**
     * Returns the number of calories consumed during the given date (which must be in the past)
     * @param date The date to query for information
     * @return the number of calories consumed during the given date
     */
    int caloriesConsumed(Date date);

    // TODO: Heart rate?

}
