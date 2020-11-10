package com.joopie.ffconverter.converters.effect.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "animation")
public class AnimationXML {
    public static class DirectionOffset {
        private Integer offset;

        @XmlAttribute
        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }
    }

    public static class Shadow {
        private String id;

        @XmlAttribute
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class Add {
        private String id;
        private String align;
        private String blend;
        private Integer ink;
        private String base;

        @XmlAttribute
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @XmlAttribute
        public String getAlign() {
            return align;
        }

        public void setAlign(String align) {
            this.align = align;
        }

        @XmlAttribute
        public String getBlend() {
            return blend;
        }

        public void setBlend(String blend) {
            this.blend = blend;
        }

        @XmlAttribute
        public Integer getInk() {
            return ink;
        }

        public void setInk(Integer ink) {
            this.ink = ink;
        }

        @XmlAttribute
        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }
    }

    public static class Remove {
        private String id;

        @XmlAttribute
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class Sprite {
        public static class Direction {
            private Integer id;
            private Integer dx;
            private Integer dy;
            private Integer dz;

            @XmlAttribute
            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            @XmlAttribute
            public Integer getDx() {
                return dx;
            }

            public void setDx(Integer dx) {
                this.dx = dx;
            }

            @XmlAttribute
            public Integer getDy() {
                return dy;
            }

            public void setDy(Integer dy) {
                this.dy = dy;
            }

            @XmlAttribute
            public Integer getDz() {
                return dz;
            }

            public void setDz(Integer dz) {
                this.dz = dz;
            }
        }

        private String id;
        private String member;
        private Integer directions;
        private Integer staticY;
        private Integer ink;

        private List<AnimationXML.Sprite.Direction> directionList;

        @XmlAttribute
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @XmlAttribute
        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        @XmlAttribute
        public Integer getDirections() {
            return directions;
        }

        public void setDirections(Integer directions) {
            this.directions = directions;
        }

        @XmlAttribute
        public Integer getStaticY() {
            return staticY;
        }

        public void setStaticY(Integer staticY) {
            this.staticY = staticY;
        }

        @XmlAttribute
        public Integer getInk() {
            return ink;
        }

        public void setInk(Integer ink) {
            this.ink = ink;
        }

        @XmlElement(name = "direction")
        public List<AnimationXML.Sprite.Direction> getDirectionList() {
            return directionList;
        }

        public void setDirectionList(List<AnimationXML.Sprite.Direction> directionList) {
            this.directionList = directionList;
        }
    }

    public static class Frame {
        public static class Fx {
            private String id;
            private Integer frame;
            private Integer dx;
            private Integer dy;
            private Integer dz;
            private Integer dd;
            private String action;

            @XmlAttribute
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @XmlAttribute
            public Integer getFrame() {
                return frame;
            }

            public void setFrame(Integer frame) {
                this.frame = frame;
            }

            @XmlAttribute
            public Integer getDx() {
                return dx;
            }

            public void setDx(Integer dx) {
                this.dx = dx;
            }

            @XmlAttribute
            public Integer getDy() {
                return dy;
            }

            public void setDy(Integer dy) {
                this.dy = dy;
            }

            @XmlAttribute
            public Integer getDz() {
                return dz;
            }

            public void setDz(Integer dz) {
                this.dz = dz;
            }

            @XmlAttribute
            public Integer getDd() {
                return dd;
            }

            public void setDd(Integer dd) {
                this.dd = dd;
            }

            @XmlAttribute
            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }
        }

        public static class Bodypart {
            public static class Item {
                private String id;
                private String base;

                @XmlAttribute
                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                @XmlAttribute
                public String getBase() {
                    return base;
                }

                public void setBase(String base) {
                    this.base = base;
                }
            }
            private String id;
            private Integer frame;
            private Integer dx;
            private Integer dy;
            private Integer dz;
            private Integer dd;
            private String action;
            private String base;

            private List<AnimationXML.Frame.Bodypart.Item> items;

            @XmlAttribute
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @XmlAttribute
            public Integer getFrame() {
                return frame;
            }

            public void setFrame(Integer frame) {
                this.frame = frame;
            }

            @XmlAttribute
            public Integer getDx() {
                return dx;
            }

            public void setDx(Integer dx) {
                this.dx = dx;
            }

            @XmlAttribute
            public Integer getDy() {
                return dy;
            }

            public void setDy(Integer dy) {
                this.dy = dy;
            }

            @XmlAttribute
            public Integer getDz() {
                return dz;
            }

            public void setDz(Integer dz) {
                this.dz = dz;
            }

            @XmlAttribute
            public Integer getDd() {
                return dd;
            }

            public void setDd(Integer dd) {
                this.dd = dd;
            }

            @XmlAttribute
            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            @XmlAttribute
            public String getBase() {
                return base;
            }

            public void setBase(String base) {
                this.base = base;
            }

            @XmlElement(name = "item")
            public List<AnimationXML.Frame.Bodypart.Item> getItems() {
                return items;
            }

            public void setItems(List<AnimationXML.Frame.Bodypart.Item> items) {
                this.items = items;
            }
        }

        private List<AnimationXML.Frame.Fx> fxs;
        private List<AnimationXML.Frame.Bodypart> bodyparts;

        @XmlElement(name = "fx")
        public List<AnimationXML.Frame.Fx> getFxs() {
            return fxs;
        }

        public void setFxs(List<AnimationXML.Frame.Fx> fxs) {
            this.fxs = fxs;
        }

        @XmlElement(name = "bodypart")
        public List<AnimationXML.Frame.Bodypart> getBodyparts() {
            return bodyparts;
        }

        public void setBodyparts(List<AnimationXML.Frame.Bodypart> bodyparts) {
            this.bodyparts = bodyparts;
        }
    }

    public static class Avatar {
        private Integer ink;
        private String foreground;
        private String background;

        @XmlAttribute
        public Integer getInk() {
            return ink;
        }

        public void setInk(Integer ink) {
            this.ink = ink;
        }

        @XmlAttribute
        public String getForeground() {
            return foreground;
        }

        public void setForeground(String foreground) {
            this.foreground = foreground;
        }

        @XmlAttribute
        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }
    }

    public static class Override {
        private String name;
        private String override;

        private List<AnimationXML.Frame> frames;

        @XmlAttribute
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @XmlAttribute
        public String getOverride() {
            return override;
        }

        public void setOverride(String override) {
            this.override = override;
        }

        @XmlElement(name = "frame")
        public List<AnimationXML.Frame> getFrames() {
            return frames;
        }

        public void setFrames(List<AnimationXML.Frame> frames) {
            this.frames = frames;
        }
    }

    private String name;
    private String desc;
    private boolean resetOnToggle;

    private List<AnimationXML.DirectionOffset> directions;
    private List<AnimationXML.Shadow> shadows;
    private List<AnimationXML.Add> adds;
    private List<AnimationXML.Remove> removes;
    private List<AnimationXML.Sprite> sprites;
    private List<AnimationXML.Frame> frames;
    private List<AnimationXML.Avatar> avatars;
    private List<AnimationXML.Override> overrides;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @XmlAttribute
    public boolean getResetOnToggle() {
        return resetOnToggle;
    }

    public void setResetOnToggle(boolean resetOnToggle) {
        this.resetOnToggle = resetOnToggle;
    }

    @XmlElement(name = "direction")
    public List<AnimationXML.DirectionOffset> getDirections() {
        return directions;
    }

    public void setDirections(List<AnimationXML.DirectionOffset> directions) {
        this.directions = directions;
    }

    @XmlElement(name = "shadow")
    public List<AnimationXML.Shadow> getShadows() {
        return shadows;
    }

    public void setShadows(List<AnimationXML.Shadow> shadows) {
        this.shadows = shadows;
    }

    @XmlElement(name = "add")
    public List<AnimationXML.Add> getAdds() {
        return adds;
    }

    public void setAdds(List<AnimationXML.Add> adds) {
        this.adds = adds;
    }

    @XmlElement(name = "remove")
    public List<AnimationXML.Remove> getRemoves() {
        return removes;
    }

    public void setRemoves(List<AnimationXML.Remove> removes) {
        this.removes = removes;
    }

    @XmlElement(name = "sprite")
    public List<AnimationXML.Sprite> getSprites() {
        return sprites;
    }

    public void setSprites(List<AnimationXML.Sprite> sprites) {
        this.sprites = sprites;
    }

    @XmlElement(name = "frame")
    public List<AnimationXML.Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<AnimationXML.Frame> frames) {
        this.frames = frames;
    }

    @XmlElement(name = "avatar")
    public List<AnimationXML.Avatar> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<AnimationXML.Avatar> avatars) {
        this.avatars = avatars;
    }

    @XmlElement(name = "override")
    public List<AnimationXML.Override> getOverrides() {
        return overrides;
    }

    public void setOverrides(List<AnimationXML.Override> overrides) {
        this.overrides = overrides;
    }
}
