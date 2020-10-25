package com.joopie.ffconverter.downloader.pet.resources;

import com.joopie.ffconverter.FFConverter;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public class ExternalVars {

    public boolean isLoading = false;

    private final Properties properties;

    public ExternalVars() throws IOException {
        this.isLoading = true;
        this.properties = new Properties();
        URL sourceURL = new URL(FFConverter.getConfig().getValue("external_vars.url"));
        StreamSource src = new StreamSource(sourceURL.openStream());
        this.properties.load(src.getInputStream());
        this.isLoading = false;
    }

    public String getValue(String key)
    {
        if (this.isLoading)
            return "";

        if (!this.properties.containsKey(key)) {
            System.out.println("[CONFIG] Key not found: " + key);
        }
        return this.properties.getProperty(key, "");
    }
}
