package eu.hansolo.fx.timebuddy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public enum PropertyManager {
    INSTANCE;

    public static final String TIME_BUDDY_FX               = "timebuddyfx.properties";
    public static final String PROPERTY_DARK_MODE          = "dark_mode";
    public static final String PROPERTY_TWENTY_FOUR_HOURS  = "twenty_four_hours";
    public static final String PROPERTY_MARK_WEEKEND_HOURS = "mark_weekend_hours";
    public static final String PROPERTY_HOME_TIME_ZONE     = "home_time_zone";
    public static final String PROPERTY_TIME_ZONE_1        = "time_zone_1";
    public static final String PROPERTY_TIME_ZONE_2        = "time_zone_2";
    public static final String PROPERTY_TIME_ZONE_3        = "time_zone_3";
    public static final String PROPERTY_TIME_ZONE_4        = "time_zone_4";
    public static final String PROPERTY_TIME_ZONE_5        = "time_zone_5";
    public static final String PROPERTY_TIME_ZONE_6        = "time_zone_6";
    public static final String PROPERTY_TIME_ZONE_7        = "time_zone_7";
    public static final String PROPERTY_TIME_ZONE_8        = "time_zone_8";
    public static final String PROPERTY_TIME_ZONE_9        = "time_zone_9";
    public static final String PROPERTY_TIME_ZONE_10       = "time_zone_10";
    public static final String PROPERTY_TIME_ZONE_11       = "time_zone_11";
    public static final String PROPERTY_TIME_ZONE_12       = "time_zone_12";

    private             Properties properties;


    // ******************** Constructors **************************************
    PropertyManager() {
        properties = new Properties();
        // Load properties
        final String propertiesFilePath = new StringBuilder(Constants.HOME_FOLDER).append(TIME_BUDDY_FX).toString();

        // Create properties file if not exists
        Path path = Paths.get(propertiesFilePath);
        if (!Files.exists(path)) { createProperties(properties); }

        // Load properties file
        try (FileInputStream propertiesFile = new FileInputStream(propertiesFilePath)) {
            properties.loadFromXML(propertiesFile);
        } catch (IOException ex) {
            System.out.println("Error reading " + TIME_BUDDY_FX + " file. " + ex);
        }

        // If properties empty, fill with default values
        if (properties.isEmpty()) {
            createProperties(properties);
        } else {
            validateProperties();
        }
    }


    // ******************** Methods *******************************************
    public Properties getProperties() { return properties; }

    public Object get(final String KEY) { return properties.getOrDefault(KEY, ""); }
    public void set(final String KEY, final String VALUE) {
        properties.setProperty(KEY, VALUE);
        storeProperties();
    }

    public String getString(final String key) { return properties.getOrDefault(key, "").toString(); }
    public void setString(final String key, final String value) { properties.setProperty(key, value); }

    public double getDouble(final String key) { return getDouble(key, 0); }
    public double getDouble(final String key, final double defaultValue) { return Double.parseDouble(properties.getOrDefault(key, Double.toString(defaultValue)).toString()); }
    public void setDouble(final String key, final double value) { properties.setProperty(key, Double.toString(value)); }

    public float getFloat(final String key) { return getFloat(key, 0); }
    public float getFloat(final String key, final float defaultValue) { return Float.parseFloat(properties.getOrDefault(key, Float.toString(defaultValue)).toString()); }
    public void setFloat(final String key, final float value) { properties.setProperty(key, Float.toString(value)); }

    public int getInt(final String key) { return getInt(key, 0); }
    public int getInt(final String key, final int defaultValue) { return Integer.parseInt(properties.getOrDefault(key, Integer.toString(defaultValue)).toString()); }
    public void setInt(final String key, final int value) { properties.setProperty(key, Integer.toString(value)); }

    public long getLong(final String key) { return getLong(key, 0); }
    public long getLong(final String key, final long defaultValue) { return Long.parseLong(properties.getOrDefault(key, Long.toString(defaultValue)).toString()); }
    public void setLong(final String key, final long value) { properties.setProperty(key, Long.toString(value)); }

    public boolean getBoolean(final String key) { return getBoolean(key, false); }
    public boolean getBoolean(final String key, final boolean defaultValue) { return Boolean.parseBoolean(properties.getOrDefault(key, Boolean.toString(defaultValue)).toString()); }
    public void setBoolean(final String key, final boolean value) { properties.setProperty(key, Boolean.toString(value)); }

    public boolean hasKey(final String key) { return properties.containsKey(key); }

    public void storeProperties() {
        if (null == properties) { return; }
        final String propFilePath = new StringBuilder(Constants.HOME_FOLDER).append(TIME_BUDDY_FX).toString();
        try (OutputStream output = new FileOutputStream(propFilePath)) {
            properties.storeToXML(output, null, StandardCharsets.UTF_16);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    // ******************** Properties ****************************************
    private void validateProperties() {
        if (null == properties) { return; }
        boolean storeProperties = false;
        if (!properties.containsKey(PROPERTY_DARK_MODE))          { properties.put(PROPERTY_DARK_MODE, "false");              storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TWENTY_FOUR_HOURS))  { properties.put(PROPERTY_TWENTY_FOUR_HOURS, "true");       storeProperties = true; }
        if (!properties.containsKey(PROPERTY_MARK_WEEKEND_HOURS)) { properties.put(PROPERTY_MARK_WEEKEND_HOURS, "false");     storeProperties = true; }
        if (!properties.containsKey(PROPERTY_HOME_TIME_ZONE))     { properties.put(PROPERTY_HOME_TIME_ZONE, "Europe/Berlin"); storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_1))        { properties.put(PROPERTY_TIME_ZONE_1, "");                 storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_2))        { properties.put(PROPERTY_TIME_ZONE_2, "");                 storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_3))        { properties.put(PROPERTY_TIME_ZONE_3, "");                 storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_4))        { properties.put(PROPERTY_TIME_ZONE_4, "");                 storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_5))        { properties.put(PROPERTY_TIME_ZONE_5, "");                 storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_6))        { properties.put(PROPERTY_TIME_ZONE_6, "");                 storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_7))        { properties.put(PROPERTY_TIME_ZONE_7, "");                 storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_8))        { properties.put(PROPERTY_TIME_ZONE_8, "");                 storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_9))        { properties.put(PROPERTY_TIME_ZONE_9, "");                 storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_10))       { properties.put(PROPERTY_TIME_ZONE_10, "");                storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_11))       { properties.put(PROPERTY_TIME_ZONE_11, "");                storeProperties = true; }
        if (!properties.containsKey(PROPERTY_TIME_ZONE_12))       { properties.put(PROPERTY_TIME_ZONE_12, "");                storeProperties = true; }

        if (storeProperties) { storeProperties(); }
    }

    private void createProperties(Properties properties) {
        final String propFilePath = new StringBuilder(Constants.HOME_FOLDER).append(TIME_BUDDY_FX).toString();
        try (OutputStream output = new FileOutputStream(propFilePath)) {
            properties.put(PROPERTY_DARK_MODE, "false");
            properties.put(PROPERTY_TWENTY_FOUR_HOURS, "true");
            properties.put(PROPERTY_MARK_WEEKEND_HOURS, "false");
            properties.put(PROPERTY_HOME_TIME_ZONE, "Europe/Berlin");
            properties.put(PROPERTY_TIME_ZONE_1, "");
            properties.put(PROPERTY_TIME_ZONE_2, "");
            properties.put(PROPERTY_TIME_ZONE_3, "");
            properties.put(PROPERTY_TIME_ZONE_4, "");
            properties.put(PROPERTY_TIME_ZONE_5, "");
            properties.put(PROPERTY_TIME_ZONE_6, "");
            properties.put(PROPERTY_TIME_ZONE_7, "");
            properties.put(PROPERTY_TIME_ZONE_8, "");
            properties.put(PROPERTY_TIME_ZONE_9, "");
            properties.put(PROPERTY_TIME_ZONE_10, "");
            properties.put(PROPERTY_TIME_ZONE_11, "");
            properties.put(PROPERTY_TIME_ZONE_12, "");
            properties.storeToXML(output, null, StandardCharsets.UTF_16);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
