package com.joopie.ffconverter.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    /**
     * Flag for when the ConfigurationManager has fully loaded.
     */
    public boolean loaded = false;

    /**
     * Flag for when the ConfigurationManager is still loading.
     * The configurationmanager is loaded in two parts,
     * first the config.ini is read.
     * After that the rest of the configuration is read from the database.
     */
    public boolean isLoading = false;

    /**
     * Our configurations stored in this object.
     */
    private final Properties properties;

    private final String configurationPath;

    public ConfigurationManager(String configurationPath) throws Exception
    {
        this.properties = new Properties();
        this.configurationPath = configurationPath;
        this.reload();
    }

    /**
     * Reloads the settings from the config file.
     * @throws Exception
     */
    public void reload()
    {
        this.isLoading = true;
        this.properties.clear();

        InputStream input = null;

        try
        {
            File f = new File(this.configurationPath);
            input = new FileInputStream(f);
            this.properties.load(input);

        }
        catch (IOException ex)
        {
            System.out.println("[CRITICAL] FAILED TO LOAD CONFIG FILE! (" + this.configurationPath + ")");
            ex.printStackTrace();
        }
        finally
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        this.isLoading = false;
    }

    /**
     * Gets the string value for a specific key.
     * @param key The key to find the value for.
     * @return The string value for the key. Returns an empty string if not found.
     */
    public String getValue(String key)
    {
        return this.getValue(key, "");
    }

    /**
     * Gets the string value for a specific key.
     * @param key The key to find the value for.
     * @param defaultValue The value that will be returned when the key is not found.
     * @return The string value for the key. Returns defaultValue when not found.
     */
    public String getValue(String key, String defaultValue)
    {
        if (this.isLoading)
            return defaultValue;

        if (!this.properties.containsKey(key)) {
            System.out.println("[CONFIG] Key not found: " + key);
        }
        return this.properties.getProperty(key, defaultValue);
    }

    /**
     * Gets the boolean value for a specific key.
     * @param key The key to find the value for.
     * @return The boolean value for the key. Returns false if not found.
     */
    public boolean getBoolean(String key)
    {
        return this.getBoolean(key, false);
    }

    /**
     * Gets the boolean value for a specific key.
     * @param key The key to find the value for.
     * @param defaultValue The value that will be returned when the key is not found.
     * @return The boolean value for the key. Returns defaultValue when not found.
     */
    public boolean getBoolean(String key, boolean defaultValue)
    {
        if (this.isLoading)
            return defaultValue;

        try
        {
            return (this.getValue(key, "0").equals("1")) || (this.getValue(key, "false").equals("true"));
        }
        catch (Exception e)
        {
            System.out.println("Failed to parse key " + key + " with value " + this.getValue(key) + " to type boolean.");
        }
        return defaultValue;
    }

    /**
     * Gets the int value for a specific key.
     * @param key The key to find the value for.
     * @return The int value for the key. Returns 0 if not found.
     */
    public int getInt(String key)
    {
        return this.getInt(key, 0);
    }

    /**
     * Gets the int value for a specific key.
     * @param key The key to find the value for.
     * @param defaultValue The value that will be returned when the key is not found.
     * @return The int value for the key. Returns defaultValue when not found.
     */
    public int getInt(String key, Integer defaultValue)
    {
        if (this.isLoading)
            return defaultValue;

        try
        {
            return Integer.parseInt(this.getValue(key, defaultValue.toString()));
        } catch (Exception e)
        {
            System.out.println("Failed to parse key " + key + " with value " + this.getValue(key) + " to type integer.");
        }
        return defaultValue;
    }

    /**
     * Gets the double value for a specific key.
     * @param key The key to find the value for.
     * @return The double value for the key. Returns 0 if not found.
     */
    public double getDouble(String key)
    {
        return this.getDouble(key, 0.0);
    }

    /**
     * Gets the double value for a specific key.
     * @param key The key to find the value for.
     * @param defaultValue The value that will be returned when the key is not found.
     * @return The double value for the key. Returns defaultValue when not found.
     */
    public double getDouble(String key, Double defaultValue)
    {
        if (this.isLoading)
            return defaultValue;

        try
        {
            return Double.parseDouble(this.getValue(key, defaultValue.toString()));
        }
        catch (Exception e)
        {
            System.out.println("Failed to parse key " + key + " with value " + this.getValue(key) + " to type double.");
        }

        return defaultValue;
    }
}
