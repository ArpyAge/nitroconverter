package com.joopie.ffconverter.downloader.figure.resources;

import com.joopie.ffconverter.FFConverter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.net.URL;

public class FiguremapParser {
    public static Figuremap getFiguremap() throws JAXBException, IOException {
        return getFiguremap(new URL(FFConverter.getConfig().getValue("figuremap.url")));
    }

    public static Figuremap getFiguremap(URL sourceURL) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(Figuremap.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StreamSource src = new StreamSource(sourceURL.openStream());
        return ((Figuremap) unmarshaller.unmarshal(src));
    }

}
