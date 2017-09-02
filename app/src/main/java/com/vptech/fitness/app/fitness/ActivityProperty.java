package com.vptech.fitness.app.fitness;

/**
 * Represents some fitness activity
 * @author Aaron Vontell
 */
public class ActivityProperty {

    private String name;
    private int intensity;
    private int duration;
    private String date;

    /**
     * Creates a fitness activity with the given name, intensity, and duration
     * @param name The name of this activity (i.e. Bike)
     * @param intensity An integer representing the intensity of the activity (-1 if N/A)
     * @param duration The number of seconds that this activity occurred over
     * @param date The date at which this activity occurred
     */
    public ActivityProperty(String name, int intensity, int duration, String date) {
        this.name = name;
        this.intensity = intensity;
        this.duration = duration;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getIntensity() {
        return intensity;
    }

    public int getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }
}
