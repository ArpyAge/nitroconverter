package com.joopie.ffconverter.converters.pet.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "objectData")
public class LogicXML {
    public static class Model {
        public static class Dimensions {
            private Double x;
            private Double y;
            private Double z;

            @XmlAttribute
            public Double getX() {
                return x;
            }

            public void setX(Double x) {
                this.x = x;
            }

            @XmlAttribute
            public Double getY() {
                return y;
            }

            public void setY(Double y) {
                this.y = y;
            }

            @XmlAttribute
            public Double getZ() {
                return z;
            }

            public void setZ(Double z) {
                this.z = z;
            }
        }

        public static class Direction {
            private Integer id;

            @XmlAttribute
            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }
        }

        private Dimensions dimensions;
        private List<Direction> directions;

        @XmlElement
        public Dimensions getDimensions() {
            return dimensions;
        }

        public void setDimensions(Dimensions dimensions) {
            this.dimensions = dimensions;
        }

        @XmlElement(name = "direction")
        @XmlElementWrapper(name = "directions")
        public List<Direction> getDirections() {
            return directions;
        }

        public void setDirections(List<Direction> directions) {
            this.directions = directions;
        }
    }

    public static class Mask {
        private String type;

        @XmlAttribute
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    private String type;
    private Model model;
    private Mask mask;

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @XmlElement
    public Mask getMask() {
        return mask;
    }

    public void setMask(Mask mask) {
        this.mask = mask;
    }
}