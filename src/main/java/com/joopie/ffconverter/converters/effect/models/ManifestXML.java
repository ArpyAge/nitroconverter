package com.joopie.ffconverter.converters.effect.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "manifest")
public class ManifestXML {
    public static class Library {
        public static class Asset {
            public static class Param {
                private String key;
                private String value;

                @XmlAttribute
                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                @XmlAttribute
                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
            private String name;
            private String mimeType;

            private com.joopie.ffconverter.converters.effect.models.ManifestXML.Library.Asset.Param param;

            @XmlAttribute
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @XmlAttribute
            public String getMimeType() {
                return mimeType;
            }

            public void setMimeType(String mimeType) {
                this.mimeType = mimeType;
            }

            @XmlElement(name = "param")
            public com.joopie.ffconverter.converters.effect.models.ManifestXML.Library.Asset.Param getParam() {
                return param;
            }

            public void setParam(com.joopie.ffconverter.converters.effect.models.ManifestXML.Library.Asset.Param param) {
                this.param = param;
            }
        }

        public static class Alias {
            private String name;
            private String link;
            private Integer fliph;
            private Integer flipv;

            @XmlAttribute
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @XmlAttribute
            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            @XmlAttribute
            public Integer getFliph() {
                return fliph;
            }

            public void setFliph(Integer fliph) {
                this.fliph = fliph;
            }

            @XmlAttribute
            public Integer getFlipv() {
                return flipv;
            }

            public void setFlipv(Integer flipv) {
                this.flipv = flipv;
            }

        }

        private String name;
        private String version;

        private List<com.joopie.ffconverter.converters.effect.models.ManifestXML.Library.Asset> assets;
        private List<com.joopie.ffconverter.converters.effect.models.ManifestXML.Library.Alias> aliases;

        @XmlAttribute
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @XmlAttribute
        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @XmlElement(name = "asset")
        @XmlElementWrapper(name = "assets")
        public List<com.joopie.ffconverter.converters.effect.models.ManifestXML.Library.Asset> getAssets() {
            return assets;
        }

        public void setAssets(List<com.joopie.ffconverter.converters.effect.models.ManifestXML.Library.Asset> assets) {
            this.assets = assets;
        }

        @XmlElement(name = "alias")
        @XmlElementWrapper(name = "aliases")
        public List<com.joopie.ffconverter.converters.effect.models.ManifestXML.Library.Alias> getAliases() {
            return aliases;
        }

        public void setAliases(List<com.joopie.ffconverter.converters.effect.models.ManifestXML.Library.Alias> aliases) {
            this.aliases = aliases;
        }
    }

    private com.joopie.ffconverter.converters.effect.models.ManifestXML.Library library;

    @XmlElement
    public com.joopie.ffconverter.converters.effect.models.ManifestXML.Library getLibrary() {
        return library;
    }

    public void setLibrary(com.joopie.ffconverter.converters.effect.models.ManifestXML.Library library) {
        this.library = library;
    }
}
