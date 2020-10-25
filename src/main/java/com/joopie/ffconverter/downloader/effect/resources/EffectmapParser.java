package com.joopie.ffconverter.downloader.effect.resources;

import com.joopie.ffconverter.FFConverter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.net.URL;

public class EffectmapParser {
    public static Effectmap getEffectmap() throws JAXBException, IOException {
        return getEffectmap(new URL(FFConverter.getConfig().getValue("effectmap.url")));
    }

    public static Effectmap getEffectmap(URL sourceURL) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(Effectmap.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StreamSource src = new StreamSource(sourceURL.openStream());
        return ((Effectmap) unmarshaller.unmarshal(src));
    }

}
