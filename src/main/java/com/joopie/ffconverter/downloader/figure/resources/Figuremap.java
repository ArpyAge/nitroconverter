package com.joopie.ffconverter.downloader.figure.resources;

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2015.08.11 at 03:58:23 AM NDT
//

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "lib"
})
@XmlRootElement(name = "map")
public class Figuremap {

    @XmlElement(required = true)
    private List<com.joopie.ffconverter.downloader.figure.resources.Figuremap.Lib> lib;

    /**
     * Gets the value of the Lib property.
     *
     * @return possible object is
     * {@link com.joopie.ffconverter.downloader.figure.resources.Figuremap.Lib }
     */
    public List<com.joopie.ffconverter.downloader.figure.resources.Figuremap.Lib> getLib() {
        return this.lib;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "part"
    })
    public static class Lib {

        @XmlAttribute(name = "id")
        String id;
        @XmlAttribute(name = "revision")
        String revision;

        @XmlElement(required = true)
        private List<com.joopie.ffconverter.downloader.figure.resources.Figuremap.Lib.Part> part;

        public List<com.joopie.ffconverter.downloader.figure.resources.Figuremap.Lib.Part> getPart() {
            if (part == null) {
                part = new ArrayList<>();
            }
            return this.part;
        }

        /**
         * Gets the value of the id property.
         *
         * @return possible object is
         * {@link Integer }
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         *
         * @param value allowed object is
         *              {@link Integer }
         */
        public void setId(String value) {
            this.id = value;
        }

        /**
         * Gets the value of the revision property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getRevision() {
            return revision;
        }

        /**
         * Sets the value of the revision property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRevision(String value) {
            this.revision = value;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
        })
        public static class Part {

            @XmlAttribute(name = "id")
            String id;
            @XmlAttribute(name = "type")
            String type;

            /**
             * Gets the value of the id property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getId() {
                return id;
            }

            /**
             * Sets the value of the id property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setId(String value) {
                this.id = value;
            }

            /**
             * Gets the value of the type property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setType(String value) {
                this.type = value;
            }


        }

    }

}
