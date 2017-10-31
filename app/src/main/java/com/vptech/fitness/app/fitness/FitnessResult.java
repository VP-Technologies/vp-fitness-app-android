package com.vptech.fitness.app.fitness;

import java.util.List;

/**
 * Interface for receiving fitness results
 * @author Aaron Vontell
 */
public interface FitnessResult {

    /**
     * Callback to cal when a fitness request is finished
     * @param properties A list of properties as results
     */
    void onResult(List<FitnessProperty> properties);

}
