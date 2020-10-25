package com.joopie.ffconverter.converters.figure.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public class FigureJSON {
    public static class Asset {
        @JsonIgnore
        private String name;

        private String source;
        private Integer x;
        private Integer y;
        private Boolean flipH;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        public Boolean getFlipH() {
            return flipH;
        }

        public void setFlipH(Boolean flipH) {
            this.flipH = flipH;
        }
    }

    private String type = "figure";
    private String name;

    private JsonNode spritesheet;

    private Map<String, FigureJSON.Asset> assets;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, FigureJSON.Asset> getAssets() {
        return assets;
    }

    public void setAssets(Map<String, FigureJSON.Asset> assets) {
        this.assets = assets;
    }

    public JsonNode getSpritesheet() {
        return spritesheet;
    }

    public void setSpritesheet(JsonNode spritesheet) {
        this.spritesheet = spritesheet;
    }
}
