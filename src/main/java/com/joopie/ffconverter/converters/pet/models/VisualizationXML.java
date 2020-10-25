package com.joopie.ffconverter.converters.pet.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "visualizationData")
public class VisualizationXML {
    public static class Visualization {
        public static class Layer {
            private Integer id;
            private Integer alpha;
            private Integer x;
            private Integer y;
            private Double z;
            private String ink;
            private String tag;
            private Boolean ignoreMouse;

            @XmlAttribute
            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            @XmlAttribute
            public Integer getAlpha() {
                return alpha;
            }

            public void setAlpha(Integer alpha) {
                this.alpha = alpha;
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
            public Double getZ() {
                return z;
            }

            public void setZ(Double z) {
                this.z = z;
            }

            @XmlAttribute
            public String getInk() {
                return ink;
            }

            public void setInk(String ink) {
                this.ink = ink;
            }

            @XmlAttribute
            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            @XmlAttribute
            public Boolean isIgnoreMouse() {
                return ignoreMouse;
            }

            public void setIgnoreMouse(Boolean ignoreMouse) {
                this.ignoreMouse = ignoreMouse;
            }
        }

        public static class Direction {
            private Integer id;

            private List<Layer> layers;

            @XmlAttribute
            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            @XmlElement(name = "layer")
            public List<Layer> getLayers() {
                return layers;
            }

            public void setLayers(List<Layer> layers) {
                this.layers = layers;
            }
        }

        public static class Color {
            public static class ColorLayer {
                private Integer id;
                private String color;

                @XmlAttribute
                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                @XmlAttribute
                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }
            }

            private Integer id;

            private List<ColorLayer> layers;

            @XmlAttribute
            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            @XmlElement(name = "colorLayer")
            public List<ColorLayer> getLayers() {
                return layers;
            }

            public void setLayers(List<ColorLayer> layers) {
                this.layers = layers;
            }
        }

        public static class Animation {
            public static class AnimationLayer {
                public static class FrameSequence {
                    public static class Frame {
                        public static class Offset {
                            private Integer direction;
                            private Integer x;
                            private Integer y;

                            @XmlAttribute
                            public Integer getDirection() {
                                return direction;
                            }

                            public void setDirection(Integer direction) {
                                this.direction = direction;
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
                        }
                        private String id;
                        private Integer x;
                        private Integer y;
                        private Integer randomX;
                        private Integer randomY;

                        private List<Offset> offsets;

                        @XmlAttribute
                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
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
                        public Integer getRandomX() {
                            return randomX;
                        }

                        public void setRandomX(Integer randomX) {
                            this.randomX = randomX;
                        }

                        @XmlAttribute
                        public Integer getRandomY() {
                            return randomY;
                        }

                        public void setRandomY(Integer randomY) {
                            this.randomY = randomY;
                        }

                        @XmlElement(name = "offset")
                        @XmlElementWrapper(name = "offsets")
                        public List<Offset> getOffsets() {
                            return offsets;
                        }

                        public void setOffsets(List<Offset> offsets) {
                            this.offsets = offsets;
                        }
                    }

                    private Integer loopCount;
                    private Integer random;

                    private List<Frame> frames;
                    private List<Integer> frameNumbers;

                    @XmlAttribute
                    public Integer getLoopCount() {
                        return loopCount;
                    }

                    public void setLoopCount(Integer loopCount) {
                        this.loopCount = loopCount;
                    }

                    @XmlAttribute
                    public Integer getRandom() {
                        return random;
                    }

                    public void setRandom(Integer random) {
                        this.random = random;
                    }

                    @XmlElement(name = "frame")
                    public List<Frame> getFrames() {
                        return frames;
                    }

                    public void setFrames(List<Frame> frames) {
                        this.frames = frames;
                    }

                    @XmlElement(name= "frames")
                    public List<Integer> getFrameNumbers() {
                        return frameNumbers;
                    }

                    public void setFrameNumbers(List<Integer> frameNumbers) {
                        this.frameNumbers = frameNumbers;
                    }
                }

                private Integer id;
                private Integer loopCount;
                private Integer frameRepeat;
                private Integer random;
                private Integer randomStart;

                private List<FrameSequence> frameSequences;

                @XmlAttribute
                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                @XmlAttribute
                public Integer getLoopCount() {
                    return loopCount;
                }

                public void setLoopCount(Integer loopCount) {
                    this.loopCount = loopCount;
                }

                @XmlAttribute
                public Integer getFrameRepeat() {
                    return frameRepeat;
                }

                public void setFrameRepeat(Integer frameRepeat) {
                    this.frameRepeat = frameRepeat;
                }

                @XmlAttribute
                public Integer getRandom() {
                    return random;
                }

                public void setRandom(Integer random) {
                    this.random = random;
                }

                @XmlAttribute
                public Integer getRandomStart() {
                    return randomStart;
                }

                public void setRandomStart(Integer randomStart) {
                    this.randomStart = randomStart;
                }

                @XmlElement(name = "frameSequence")
                public List<FrameSequence> getFrameSequences() {
                    return frameSequences;
                }

                public void setFrameSequences(List<FrameSequence> frameSequences) {
                    this.frameSequences = frameSequences;
                }
            }

            private int id;
            private Integer transitionTo;
            private Integer transitionFrom;
            private String immediateChangeFrom;

            private List<AnimationLayer> layers;

            @XmlAttribute
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @XmlAttribute
            public Integer getTransitionTo() {
                return transitionTo;
            }

            public void setTransitionTo(Integer transitionTo) {
                this.transitionTo = transitionTo;
            }

            @XmlAttribute
            public Integer getTransitionFrom() {
                return transitionFrom;
            }

            public void setTransitionFrom(Integer transitionFrom) {
                this.transitionFrom = transitionFrom;
            }

            @XmlAttribute
            public String getImmediateChangeFrom() {
                return immediateChangeFrom;
            }

            public void setImmediateChangeFrom(String immediateChangeFrom) {
                this.immediateChangeFrom = immediateChangeFrom;
            }

            @XmlElement(name = "animationLayer")
            public List<AnimationLayer> getLayers() {
                return layers;
            }

            public void setLayers(List<AnimationLayer> layers) {
                this.layers = layers;
            }
        }

        public static class Posture {
            private String id;
            private Integer animationId;

            @XmlAttribute
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @XmlAttribute
            public Integer getAnimationId() {
                return animationId;
            }

            public void setAnimationId(Integer animationId) {
                this.animationId = animationId;
            }
        }

        public static class Gesture {
            private String id;
            private Integer animationId;

            @XmlAttribute
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @XmlAttribute
            public Integer getAnimationId() {
                return animationId;
            }

            public void setAnimationId(Integer animationId) {
                this.animationId = animationId;
            }
        }

        private Integer size;
        private Integer layerCount;
        private Integer angle;

        private List<Layer> layers;
        private List<Direction> directions;
        private List<Color> colors;
        private List<Animation> animations;
        private List<Posture> postures;
        private List<Gesture> gestures;

        @XmlAttribute
        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        @XmlAttribute
        public Integer getLayerCount() {
            return layerCount;
        }

        public void setLayerCount(Integer layerCount) {
            this.layerCount = layerCount;
        }

        @XmlAttribute
        public Integer getAngle() {
            return angle;
        }

        public void setAngle(Integer angle) {
            this.angle = angle;
        }

        @XmlElement(name = "layer")
        @XmlElementWrapper(name = "layers")
        public List<Layer> getLayers() {
            return layers;
        }

        public void setLayers(List<Layer> layers) {
            this.layers = layers;
        }

        @XmlElement(name = "direction")
        @XmlElementWrapper(name = "directions")
        public List<Direction> getDirections() {
            return directions;
        }

        public void setDirections(List<Direction> directions) {
            this.directions = directions;
        }

        @XmlElement(name = "color")
        @XmlElementWrapper(name = "colors")
        public List<Color> getColors() {
            return colors;
        }

        public void setColors(List<Color> colors) {
            this.colors = colors;
        }

        @XmlElement(name = "animation")
        @XmlElementWrapper(name = "animations")
        public List<Animation> getAnimations() {
            return animations;
        }

        public void setAnimations(List<Animation> animations) {
            this.animations = animations;
        }

        @XmlElement(name = "posture")
        @XmlElementWrapper(name = "postures")
        public List<Posture> getPostures() {
            return postures;
        }

        public void setPostures(List<Posture> postures) {
            this.postures = postures;
        }

        @XmlElement(name = "gesture")
        @XmlElementWrapper(name = "gestures")
        public List<Gesture> getGestures() {
            return gestures;
        }

        public void setGestures(List<Gesture> gestures) {
            this.gestures = gestures;
        }
    }

    private String type;

    private List<Visualization> visualizations;

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "visualization")
    @XmlElementWrapper(name = "graphics")
    public List<Visualization> getVisualizations() {
        return visualizations;
    }

    public void setVisualizations(List<Visualization> visualizations) {
        this.visualizations = visualizations;
    }
}
