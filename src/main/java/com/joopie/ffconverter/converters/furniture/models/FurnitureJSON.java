package com.joopie.ffconverter.converters.furniture.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

/**
 * Created by jospi on 22-4-2017.
 */
public class FurnitureJSON {
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

    public static class Dimensions {
        private Double x;
        private Double y;
        private Double z;

        public Double getX() {
            return x;
        }

        public void setX(Double x) {
            this.x = x;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            this.y = y;
        }

        public Double getZ() {
            return z;
        }

        public void setZ(Double z) {
            this.z = z;
        }
    }

    public static class Action {
        private String link;
        private Integer startState;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Integer getStartState() {
            return startState;
        }

        public void setStartState(Integer startState) {
            this.startState = startState;
        }
    }

    public static class Visualization {
        public static class Layer {
            @JsonIgnore
            private Integer id;
            private Integer alpha;
            private Integer x;
            private Integer y;
            private Double z;
            private String ink;
            private String tag;
            private Boolean ignoreMouse;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getAlpha() {
                return alpha;
            }

            public void setAlpha(Integer alpha) {
                this.alpha = alpha;
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

            public Double getZ() {
                return z;
            }

            public void setZ(Double z) {
                this.z = z;
            }

            public String getInk() {
                return ink;
            }

            public void setInk(String ink) {
                this.ink = ink;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public Boolean isIgnoreMouse() {
                return ignoreMouse;
            }

            public void setIgnoreMouse(Boolean ignoreMouse) {
                this.ignoreMouse = ignoreMouse;
            }
        }

        public static class Direction {
            @JsonIgnore
            private Integer id;

            private Map<Integer, Layer> layers;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Map<Integer, Layer> getLayers() {
                return layers;
            }

            public void setLayers(Map<Integer, Layer> layers) {
                this.layers = layers;
            }
        }

        public static class Color {
            public static class ColorLayer {
                @JsonIgnore
                private Integer id;
                private Integer color;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public Integer getColor() {
                    return color;
                }

                public void setColor(Integer color) {
                    this.color = color;
                }
            }

            @JsonIgnore
            private Integer id;

            private Map<Integer, ColorLayer> layers;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Map<Integer, ColorLayer> getLayers() {
                return layers;
            }

            public void setLayers(Map<Integer, ColorLayer> layers) {
                this.layers = layers;
            }
        }

        public static class IconColor {
            public static class ColorLayer {
                @JsonIgnore
                private Integer id;
                private Integer color;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public Integer getColor() {
                    return color;
                }

                public void setColor(Integer color) {
                    this.color = color;
                }
            }

            @JsonIgnore
            private Integer id;

            private Map<Integer, ColorLayer> layers;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Map<Integer, ColorLayer> getLayers() {
                return layers;
            }

            public void setLayers(Map<Integer, ColorLayer> layers) {
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

                            public Integer getDirection() {
                                return direction;
                            }

                            public void setDirection(Integer direction) {
                                this.direction = direction;
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
                        }
                        private Integer id;
                        private Integer x;
                        private Integer y;
                        private Integer randomX;
                        private Integer randomY;

                        private List<Offset> offsets;

                        public Integer getId() {
                            return id;
                        }

                        public void setId(Integer id) {
                            this.id = id;
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

                        public Integer getRandomX() {
                            return randomX;
                        }

                        public void setRandomX(Integer randomX) {
                            this.randomX = randomX;
                        }

                        public Integer getRandomY() {
                            return randomY;
                        }

                        public void setRandomY(Integer randomY) {
                            this.randomY = randomY;
                        }

                        public List<Offset> getOffsets() {
                            return offsets;
                        }

                        public void setOffsets(List<Offset> offsets) {
                            this.offsets = offsets;
                        }
                    }
                    private Integer loopCount;
                    private Integer random;

                    private Map<Integer, Frame> frames;

                    public Map<Integer, Frame> getFrames() {
                        return frames;
                    }

                    public void setFrames(Map<Integer, Frame> frames) {
                        this.frames = frames;
                    }

                    public Integer getLoopCount() {
                        return loopCount;
                    }

                    public void setLoopCount(Integer loopCount) {
                        this.loopCount = loopCount;
                    }

                    public Integer getRandom() {
                        return random;
                    }

                    public void setRandom(Integer random) {
                        this.random = random;
                    }
                }
                @JsonIgnore
                private Integer id;
                private Integer loopCount;
                private Integer frameRepeat;
                private Integer random;

                private Map<Integer, FrameSequence> frameSequences;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public Integer getLoopCount() {
                    return loopCount;
                }

                public void setLoopCount(Integer loopCount) {
                    this.loopCount = loopCount;
                }

                public Integer getFrameRepeat() {
                    return frameRepeat;
                }

                public void setFrameRepeat(Integer frameRepeat) {
                    this.frameRepeat = frameRepeat;
                }

                public Integer getRandom() {
                    return random;
                }

                public void setRandom(Integer random) {
                    this.random = random;
                }

                public Map<Integer, FrameSequence> getFrameSequences() {
                    return frameSequences;
                }

                public void setFrameSequences(Map<Integer, FrameSequence> frameSequences) {
                    this.frameSequences = frameSequences;
                }
            }

            @JsonIgnore
            private int id;
            private Integer transitionTo;
            private Integer transitionFrom;
            private String immediateChangeFrom;

            private Map<Integer, AnimationLayer> layers;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Integer getTransitionTo() {
                return transitionTo;
            }

            public void setTransitionTo(Integer transitionTo) {
                this.transitionTo = transitionTo;
            }

            public Integer getTransitionFrom() {
                return transitionFrom;
            }

            public void setTransitionFrom(Integer transitionFrom) {
                this.transitionFrom = transitionFrom;
            }

            public String getImmediateChangeFrom() {
                return immediateChangeFrom;
            }

            public void setImmediateChangeFrom(String immediateChangeFrom) {
                this.immediateChangeFrom = immediateChangeFrom;
            }

            public Map<Integer, AnimationLayer> getLayers() {
                return layers;
            }

            public void setLayers(Map<Integer, AnimationLayer> layers) {
                this.layers = layers;
            }
        }

        public static class Posture {
            private String id;
            private Integer animationId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Integer getAnimationId() {
                return animationId;
            }

            public void setAnimationId(Integer animationId) {
                this.animationId = animationId;
            }
        }

        private Integer layerCount;
        private Integer angle;
        private Integer size;

        private Map<Integer, Layer> layers;
        private Map<Integer, Direction> directions;
        private Map<Integer, Color> colors;
        private Map<Integer, Animation> animations;
        private Map<String, Posture> postures;
        private Map<String, Gesture> gestures;

        public Integer getLayerCount() {
            return layerCount;
        }

        public void setLayerCount(Integer layerCount) {
            this.layerCount = layerCount;
        }

        public Integer getAngle() {
            return angle;
        }

        public void setAngle(Integer angle) {
            this.angle = angle;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Map<Integer, Layer> getLayers() {
            return layers;
        }

        public void setLayers(Map<Integer, Layer> layers) {
            this.layers = layers;
        }

        public Map<Integer, Direction> getDirections() {
            return directions;
        }

        public void setDirections(Map<Integer, Direction> directions) {
            this.directions = directions;
        }

        public Map<Integer, Color> getColors() {
            return colors;
        }

        public void setColors(Map<Integer, Color> colors) {
            this.colors = colors;
        }

        public Map<Integer, Animation> getAnimations() {
            return animations;
        }

        public void setAnimations(Map<Integer, Animation> animations) {
            this.animations = animations;
        }

        public Map<String, Posture> getPostures() {
            return postures;
        }

        public void setPostures(Map<String, Posture> postures) {
            this.postures = postures;
        }

        public Map<String, Gesture> getGestures() {
            return gestures;
        }

        public void setGestures(Map<String, Gesture> gestures) {
            this.gestures = gestures;
        }
    }

    private String type = "furniture";
    private String name;
    private String visualizationType;
    private String logicType;
    private String maskType;
    private String credits;

    private JsonNode spritesheet;

    private Dimensions dimensions;
    private Action action;
    private List<Integer> directions;
    private Map<String, Asset> assets;
    private List<Visualization> visualizations;

    public String getType() {
        return type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisualizationType() {
        return visualizationType;
    }

    public void setVisualizationType(String visualizationType) {
        this.visualizationType = visualizationType;
    }

    public String getLogicType() {
        return logicType;
    }

    public void setLogicType(String logicType) {
        this.logicType = logicType;
    }

    public String getMaskType() {
        return maskType;
    }

    public void setMaskType(String maskType) {
        this.maskType = maskType;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public JsonNode getSpritesheet() {
        return spritesheet;
    }

    public void setSpritesheet(JsonNode spritesheet) {
        this.spritesheet = spritesheet;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Integer> getDirections() {
        return directions;
    }

    public void setDirections(List<Integer> directions) {
        this.directions = directions;
    }

    public Map<String, Asset> getAssets() {
        return assets;
    }

    public void setAssets(Map<String, Asset> assets) {
        this.assets = assets;
    }

    public List<Visualization> getVisualizations() {
        return visualizations;
    }

    public void setVisualizations(List<Visualization> visualizations) {
        this.visualizations = visualizations;
    }
}
