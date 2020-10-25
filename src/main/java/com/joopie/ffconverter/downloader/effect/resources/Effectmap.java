package com.joopie.ffconverter.downloader.effect.resources;

import javax.xml.bind.annotation.*;
import java.util.List;

@SuppressWarnings("ALL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "effect"
})
@XmlRootElement(name = "map")
public class Effectmap {

    @XmlElement(required = true)
    private List<com.joopie.ffconverter.downloader.effect.resources.Effectmap.Effect> effect;

    /**
     * Gets the value of the Lib property.
     *
     * @return possible object is
     * {@link com.joopie.ffconverter.downloader.effect.resources.Figuremap.Lib }
     */
    public List<com.joopie.ffconverter.downloader.effect.resources.Effectmap.Effect> getEffect() {
        return this.effect;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Effect {

        @XmlAttribute(name = "id")
        String id;
        @XmlAttribute(name = "lib")
        String lib;
        @XmlAttribute(name = "type")
        String type;
        @XmlAttribute(name = "revision")
        String revision;

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
         * Gets the value of the lib property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getLib() {
            return lib;
        }

        /**
         * Sets the value of the lib property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLib(String value) {
            this.lib = value;
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

    }

}