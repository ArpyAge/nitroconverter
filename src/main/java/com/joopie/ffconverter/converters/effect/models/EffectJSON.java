package com.joopie.ffconverter.converters.effect.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

public class EffectJSON {
    public static class Asset {
        @JsonIgnore
        private String name;

        private Integer x;
        private Integer y;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

    public static class Alias {
        @JsonIgnore
        private String name;

        private String link;
        private Integer fliph;
        private Integer flipv;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Integer getFliph() {
            return fliph;
        }

        public void setFliph(Integer fliph) {
            this.fliph = fliph;
        }

        public Integer getFlipv() {
            return flipv;
        }

        public void setFlipv(Integer flipv) {
            this.flipv = flipv;
        }
    }

    public static class Animation {
        public static class DirectionOffset {
            private Integer offset;

            public Integer getOffset() {
                return offset;
            }

            public void setOffset(Integer offset) {
                this.offset = offset;
            }
        }

        public static class Shadow {
            private String id;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAlign() {
                return align;
            }

            public void setAlign(String align) {
                this.align = align;
            }

            public String getBlend() {
                return blend;
            }

            public void setBlend(String blend) {
                this.blend = blend;
            }

            public Integer getInk() {
                return ink;
            }

            public void setInk(Integer ink) {
                this.ink = ink;
            }

            public String getBase() {
                return base;
            }

            public void setBase(String base) {
                this.base = base;
            }
        }

        public static class Remove {
            private String id;

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

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public Integer getDx() {
                    return dx;
                }

                public void setDx(Integer dx) {
                    this.dx = dx;
                }

                public Integer getDy() {
                    return dy;
                }

                public void setDy(Integer dy) {
                    this.dy = dy;
                }

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

            private List<EffectJSON.Animation.Sprite.Direction> directionList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMember() {
                return member;
            }

            public void setMember(String member) {
                this.member = member;
            }

            public Integer getDirections() {
                return directions;
            }

            public void setDirections(Integer directions) {
                this.directions = directions;
            }

            public Integer getStaticY() {
                return staticY;
            }

            public void setStaticY(Integer staticY) {
                this.staticY = staticY;
            }

            public Integer getInk() {
                return ink;
            }

            public void setInk(Integer ink) {
                this.ink = ink;
            }

            public List<EffectJSON.Animation.Sprite.Direction> getDirectionList() {
                return directionList;
            }

            public void setDirectionList(List<EffectJSON.Animation.Sprite.Direction> directionList) {
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

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public Integer getFrame() {
                    return frame;
                }

                public void setFrame(Integer frame) {
                    this.frame = frame;
                }

                public Integer getDx() {
                    return dx;
                }

                public void setDx(Integer dx) {
                    this.dx = dx;
                }

                public Integer getDy() {
                    return dy;
                }

                public void setDy(Integer dy) {
                    this.dy = dy;
                }

                public Integer getDz() {
                    return dz;
                }

                public void setDz(Integer dz) {
                    this.dz = dz;
                }

                public Integer getDd() {
                    return dd;
                }

                public void setDd(Integer dd) {
                    this.dd = dd;
                }

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

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

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

                private List<EffectJSON.Animation.Frame.Bodypart.Item> items;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public Integer getFrame() {
                    return frame;
                }

                public void setFrame(Integer frame) {
                    this.frame = frame;
                }

                public Integer getDx() {
                    return dx;
                }

                public void setDx(Integer dx) {
                    this.dx = dx;
                }

                public Integer getDy() {
                    return dy;
                }

                public void setDy(Integer dy) {
                    this.dy = dy;
                }

                public Integer getDz() {
                    return dz;
                }

                public void setDz(Integer dz) {
                    this.dz = dz;
                }

                public Integer getDd() {
                    return dd;
                }

                public void setDd(Integer dd) {
                    this.dd = dd;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getBase() {
                    return base;
                }

                public void setBase(String base) {
                    this.base = base;
                }

                public List<EffectJSON.Animation.Frame.Bodypart.Item> getItems() {
                    return items;
                }

                public void setItems(List<EffectJSON.Animation.Frame.Bodypart.Item> items) {
                    this.items = items;
                }
            }

            private List<EffectJSON.Animation.Frame.Fx> fxs;
            private List<EffectJSON.Animation.Frame.Bodypart> bodyparts;

            public List<EffectJSON.Animation.Frame.Fx> getFxs() {
                return fxs;
            }

            public void setFxs(List<EffectJSON.Animation.Frame.Fx> fxs) {
                this.fxs = fxs;
            }

            public List<EffectJSON.Animation.Frame.Bodypart> getBodyparts() {
                return bodyparts;
            }

            public void setBodyparts(List<EffectJSON.Animation.Frame.Bodypart> bodyparts) {
                this.bodyparts = bodyparts;
            }
        }

        public static class Avatar {
            private Integer ink;
            private String foreground;
            private String background;

            public Integer getInk() {
                return ink;
            }

            public void setInk(Integer ink) {
                this.ink = ink;
            }

            public String getForeground() {
                return foreground;
            }

            public void setForeground(String foreground) {
                this.foreground = foreground;
            }

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

            private List<EffectJSON.Animation.Frame> frames;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOverride() {
                return override;
            }

            public void setOverride(String override) {
                this.override = override;
            }

            public List<EffectJSON.Animation.Frame> getFrames() {
                return frames;
            }

            public void setFrames(List<EffectJSON.Animation.Frame> frames) {
                this.frames = frames;
            }
        }

        private String name;
        private String desc;
        private boolean resetOnToggle;

        private List<EffectJSON.Animation.DirectionOffset> directions;
        private List<EffectJSON.Animation.Shadow> shadows;
        private List<EffectJSON.Animation.Add> adds;
        private List<EffectJSON.Animation.Remove> removes;
        private List<EffectJSON.Animation.Sprite> sprites;
        private List<EffectJSON.Animation.Frame> frames;
        private List<EffectJSON.Animation.Avatar> avatars;
        private List<EffectJSON.Animation.Override> overrides;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public boolean getResetOnToggle() {
            return resetOnToggle;
        }

        public void setResetOnToggle(boolean resetOnToggle) {
            this.resetOnToggle = resetOnToggle;
        }

        public List<EffectJSON.Animation.DirectionOffset> getDirections() {
            return directions;
        }

        public void setDirections(List<EffectJSON.Animation.DirectionOffset> directions) {
            this.directions = directions;
        }

        public List<EffectJSON.Animation.Shadow> getShadows() {
            return shadows;
        }

        public void setShadows(List<EffectJSON.Animation.Shadow> shadows) {
            this.shadows = shadows;
        }

        public List<EffectJSON.Animation.Add> getAdds() {
            return adds;
        }

        public void setAdds(List<EffectJSON.Animation.Add> adds) {
            this.adds = adds;
        }

        public List<EffectJSON.Animation.Remove> getRemoves() {
            return removes;
        }

        public void setRemoves(List<EffectJSON.Animation.Remove> removes) {
            this.removes = removes;
        }

        public List<EffectJSON.Animation.Sprite> getSprites() {
            return sprites;
        }

        public void setSprites(List<EffectJSON.Animation.Sprite> sprites) {
            this.sprites = sprites;
        }

        public List<EffectJSON.Animation.Frame> getFrames() {
            return frames;
        }

        public void setFrames(List<EffectJSON.Animation.Frame> frames) {
            this.frames = frames;
        }

        public List<EffectJSON.Animation.Avatar> getAvatars() {
            return avatars;
        }

        public void setAvatars(List<EffectJSON.Animation.Avatar> avatars) {
            this.avatars = avatars;
        }

        public List<EffectJSON.Animation.Override> getOverrides() {
            return overrides;
        }

        public void setOverrides(List<EffectJSON.Animation.Override> overrides) {
            this.overrides = overrides;
        }
    }

    private String type = "effect";
    private String name;

    private JsonNode spritesheet;

    private Map<String, EffectJSON.Asset> assets;
    private Map<String, EffectJSON.Alias> aliases;
    private Map<String, EffectJSON.Animation> animations;

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

    public Map<String, EffectJSON.Asset> getAssets() {
        return assets;
    }

    public void setAssets(Map<String, EffectJSON.Asset> assets) {
        this.assets = assets;
    }

    public Map<String, EffectJSON.Alias> getAliases() {
        return aliases;
    }

    public void setAliases(Map<String, EffectJSON.Alias> aliases) {
        this.aliases = aliases;
    }

    public Map<String, EffectJSON.Animation> getAnimations() {
        return animations;
    }

    public void setAnimations(Map<String, EffectJSON.Animation> animations) {
        this.animations = animations;
    }

    public JsonNode getSpritesheet() {
        return spritesheet;
    }

    public void setSpritesheet(JsonNode spritesheet) {
        this.spritesheet = spritesheet;
    }
}
