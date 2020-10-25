package com.joopie.ffconverter.converters.pet.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "assets")
public class AssetsXML {
    public static class Asset {
        private String name;
        private String source;

        private Integer x;
        private Integer y;

        private Boolean flipH;

        private Boolean usesPalette;

        @XmlAttribute
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @XmlAttribute
        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        @XmlAttribute
        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        @XmlAttribute
        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        @XmlAttribute
        public Boolean isFlipH() {
            return flipH;
        }

        public void setFlipH(Boolean flipH) {
            this.flipH = flipH;
        }

        @XmlAttribute
        public Boolean getUsesPalette() {
            return usesPalette;
        }

        public void setUsesPalette(Boolean usesPalette) {
            this.usesPalette = usesPalette;
        }
    }

    public static class Palette {
        private Integer id;
        private String source;
        private String color1;
        private String color2;

        @XmlAttribute
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @XmlAttribute
        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        @XmlAttribute
        public String getColor1() {
            return color1;
        }

        public void setColor1(String color1) {
            this.color1 = color1;
        }

        @XmlAttribute
        public String getColor2() {
            return color2;
        }

        public void setColor2(String color2) {
            this.color2 = color2;
        }
    }

    private List<Asset> assets;
    private List<Palette> palettes;

    @XmlElement(name = "asset")
    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    @XmlElement(name = "palette")
    public List<Palette> getPalettes() {
        return palettes;
    }

    public void setPalettes(List<Palette> palettes) {
        this.palettes = palettes;
    }
}
