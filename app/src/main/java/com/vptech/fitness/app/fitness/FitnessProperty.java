package com.vptech.fitness.app.fitness;

/**
 * A container for fitness data, defined by its name and data
 * @author Aaron Vontell
 */
public class FitnessProperty {

    private String name;
    private Object value;
    private String date;

    /** Some constants for referencing common names **/
    public static final String STEPS = "STEPS";
    public static final String MILES = "MILES";
    public static final String STAIRS = "STAIRS";
    public static final String HEART_RATE = "HEART_RATE";
    public static final String CALORIES_BURNED = "CALORIES_BURNED";
    public static final String CALORIES_CONSUMED = "CALORIES_CONSUMED";

    /**
     * Creates a fitness property with the given name and value
     * @param name The name of the property
     * @param value The value of the property
     * @param date A string representing a date that this property was read from
     */
    public FitnessProperty(String name, Object value, String date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
}
