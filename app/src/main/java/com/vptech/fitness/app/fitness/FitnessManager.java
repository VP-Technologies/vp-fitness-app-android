package com.vptech.fitness.app.fitness;

import java.util.List;

/**
 * Handles and manages all aspects of the fitness data, such as keeping tracks of which
 * services are turned on, caching results, queuing requests, and returning results
 */
public class FitnessManager {

    // STATIC SINGLETON STUFF ----------------------------------------------------------------------

    private static FitnessManager manager = null;

    /**
     * Creates a connection to the Fitness Manager, and returns that manager
     * @return the resulting connection manager
     */
    public static FitnessManager connect() {

        if (manager != null) {
            return manager;
        } else {

            // Create the fitness manager
            manager = new FitnessManager();

            // Load up credentials and preferences for fitness information

            return manager;

        }

    }

    /**
     * A private constructor for correctly generating and establishing
     * all saved fitness data
     */
    private FitnessManager() {}


    // INSTANCE METHODS AND VARS -------------------------------------------------------------------

    // A list of fitness services being used by this user
    private List<PlayerFitness> services;

    /**
     * Asynchronously obtain the fitness results, based on the requested properties. When
     * finished, execute the fitness result
     * @param req A configuration which defines what kind of information is requested
     * @param res A callback which will accept and handle the new information
     */
    public void fitnessRequest(FitnessRequest req, FitnessResult res) {

        // Determine the requested parameters and options based on the request

        // Determine the PlayerFitness services that are needed to make the request

        // Load the corresponding certifications and authentications (if needed)

        // Start the asynchronous service

    }

    /**
     * Asynchronously obtain the activity results, based on the requested properties. When
     * finished, execute the activity result
     * @param req A configuration which defines what kind of information is requested
     * @param res A callback which will accept and handle the new information
     */
    public void activityRequest(FitnessRequest req, ActivityResult res) {

    }

}
