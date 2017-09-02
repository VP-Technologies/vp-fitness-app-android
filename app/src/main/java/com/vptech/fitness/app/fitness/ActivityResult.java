package com.vptech.fitness.app.fitness;

import java.util.List;

/**
 * Interface for receiving activity results
 * @author Aaron Vontell
 */
public interface ActivityResult {

    /**
     * Callback to call when an activity request is finished
     * @param activities A list of activities as results
     */
    void onResult(List<ActivityProperty> activities);

}
