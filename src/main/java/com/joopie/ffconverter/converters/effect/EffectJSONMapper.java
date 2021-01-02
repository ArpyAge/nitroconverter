package com.joopie.ffconverter.converters.effect;

import com.joopie.ffconverter.HabboAssetSWF;
import com.joopie.ffconverter.converters.effect.models.AnimationXML;
import com.joopie.ffconverter.converters.effect.models.EffectJSON;
import com.joopie.ffconverter.converters.effect.models.ManifestXML;
import com.joopie.ffconverter.downloader.effect.EffectDownloader;
import com.jpexs.decompiler.flash.tags.base.ImageTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.joopie.ffconverter.converters.util.spritesheet.SpritesheetConverter.imageSource;

public class EffectJSONMapper {

    public static final String MUST_START_WITH = "h_";

    public EffectJSON mapXML(HabboAssetSWF habboAssetSWF, ManifestXML manifestXML, AnimationXML animationXML) {
        String name = habboAssetSWF.getDocumentClass();
        EffectJSON result = new EffectJSON();
        result.setName(name);
        result.setType(EffectDownloader.types.get(name));
        this.mapManifestXML(habboAssetSWF, manifestXML, result);
        this.mapAnimationXML(animationXML, result);
        return result;
    }

    private void mapManifestXML(HabboAssetSWF habboAssetSWF, ManifestXML manifestXML, EffectJSON output) {
        Map<String, EffectJSON.Asset> assets = new HashMap<String, EffectJSON.Asset>();
        for (ManifestXML.Library.Asset assetXML : manifestXML.getLibrary().getAssets()) {
            if(assetXML.getName().startsWith(MUST_START_WITH)) {

                EffectJSON.Asset asset = new EffectJSON.Asset();
                asset.setName(assetXML.getName());
                if(assetXML.getParam() != null && !assetXML.getParam().getValue().isEmpty()) {
                    asset.setX(Integer.parseInt(assetXML.getParam().getValue().split(",")[0]));
                    asset.setY(Integer.parseInt(assetXML.getParam().getValue().split(",")[1]));
                }
                if(imageSource.containsKey(assetXML.getName())) {
                    asset.setSource(imageSource.get(assetXML.getName()));
                }

                assets.put(assetXML.getName(), asset);
            }
        }

        output.setAssets(assets);

        if((manifestXML.getLibrary().getAliases() != null && !manifestXML.getLibrary().getAliases().isEmpty() && manifestXML.getLibrary().getAliases().size() > 0) || (imageSource != null && !imageSource.isEmpty() && imageSource.size() > 0)) {
            Map<String, EffectJSON.Alias> aliases = new HashMap<String, EffectJSON.Alias>();
            if(manifestXML.getLibrary().getAliases() != null && !manifestXML.getLibrary().getAliases().isEmpty() && manifestXML.getLibrary().getAliases().size() > 0) {
                for (ManifestXML.Library.Alias aliasXML : manifestXML.getLibrary().getAliases()) {
                    if (aliasXML.getName().startsWith(MUST_START_WITH)) {
                        EffectJSON.Alias alias = new EffectJSON.Alias();
                        alias.setName(aliasXML.getName());
                        alias.setLink(aliasXML.getLink());
                        alias.setFliph(aliasXML.getFliph());
                        alias.setFlipv(aliasXML.getFlipv());

                        aliases.put(aliasXML.getName(), alias);
                    }
                }
            }
            /*
            if(imageSource != null && !imageSource.isEmpty() && imageSource.size() > 0) {
                for (Map.Entry<String, String> source : imageSource.entrySet()) {
                    EffectJSON.Alias alias = new EffectJSON.Alias();
                    alias.setName(source.getKey());
                    alias.setLink(source.getValue());
                    aliases.put(source.getKey(), alias);
                }
            }
            */
            output.setAliases(aliases);
        }
    }

    private void mapAnimationXML(AnimationXML animationXML, EffectJSON output) {
        Map<String, EffectJSON.Animation> animations = new HashMap<String, EffectJSON.Animation>();
        EffectJSON.Animation animation = new EffectJSON.Animation();
        animation.setName(animationXML.getName());
        animation.setDesc(animationXML.getDesc());
        animation.setResetOnToggle(animationXML.getResetOnToggle());
        List<EffectJSON.Animation.Frame> frames = new ArrayList<EffectJSON.Animation.Frame>();
        List<EffectJSON.Animation.Avatar> avatars = new ArrayList<EffectJSON.Animation.Avatar>();
        List<EffectJSON.Animation.DirectionOffset> directions = new ArrayList<EffectJSON.Animation.DirectionOffset>();
        List<EffectJSON.Animation.Shadow> shadows = new ArrayList<EffectJSON.Animation.Shadow>();
        List<EffectJSON.Animation.Add> adds = new ArrayList<EffectJSON.Animation.Add>();
        List<EffectJSON.Animation.Remove> removes = new ArrayList<EffectJSON.Animation.Remove>();
        List<EffectJSON.Animation.Sprite> sprites = new ArrayList<EffectJSON.Animation.Sprite>();
        List<EffectJSON.Animation.Override> overrides = new ArrayList<EffectJSON.Animation.Override>();
        if(animationXML.getFrames() != null && animationXML.getFrames().size() > 0) {
            for (AnimationXML.Frame frameXML : animationXML.getFrames()) {
                List<EffectJSON.Animation.Frame.Fx> fxs = new ArrayList<EffectJSON.Animation.Frame.Fx>();
                List<EffectJSON.Animation.Frame.Bodypart> bodyparts = new ArrayList<EffectJSON.Animation.Frame.Bodypart>();
                EffectJSON.Animation.Frame frame = new EffectJSON.Animation.Frame();
                if(frameXML.getFxs() != null && frameXML.getFxs().size() > 0) {
                    for (AnimationXML.Frame.Fx fxXML : frameXML.getFxs()) {
                        EffectJSON.Animation.Frame.Fx fx = new EffectJSON.Animation.Frame.Fx();
                        fx.setAction(fxXML.getAction());
                        fx.setDx(fxXML.getDx());
                        fx.setDy(fxXML.getDy());
                        fx.setDz(fxXML.getDz());
                        fx.setDd(fxXML.getDd());
                        fx.setFrame(fxXML.getFrame());
                        fx.setId(fxXML.getId());

                        fxs.add(fx);
                    }
                }
                if(frameXML.getBodyparts() != null && frameXML.getBodyparts().size() > 0) {
                    for (AnimationXML.Frame.Bodypart bodypartXML : frameXML.getBodyparts()) {
                        List<EffectJSON.Animation.Frame.Bodypart.Item> items = new ArrayList<EffectJSON.Animation.Frame.Bodypart.Item>();
                        EffectJSON.Animation.Frame.Bodypart bodypart = new EffectJSON.Animation.Frame.Bodypart();
                        if(bodypartXML.getItems() != null && bodypartXML.getItems().size() > 0) {
                            for (AnimationXML.Frame.Bodypart.Item itemXML : bodypartXML.getItems()) {
                                EffectJSON.Animation.Frame.Bodypart.Item item = new EffectJSON.Animation.Frame.Bodypart.Item();
                                item.setId(itemXML.getId());
                                item.setBase(itemXML.getBase());

                                items.add(item);
                            }
                        }
                        bodypart.setAction(bodypartXML.getAction());
                        bodypart.setDx(bodypartXML.getDx());
                        bodypart.setDy(bodypartXML.getDy());
                        bodypart.setDz(bodypartXML.getDz());
                        bodypart.setDd(bodypartXML.getDd());
                        bodypart.setFrame(bodypartXML.getFrame());
                        bodypart.setId(bodypartXML.getId());
                        bodypart.setBase(bodypartXML.getBase());
                        bodypart.setItems(items);

                        bodyparts.add(bodypart);
                    }
                }
                if(frameXML.getRepeats() != null) frame.setRepeats(frameXML.getRepeats());
                frame.setFxs(fxs);
                frame.setBodyparts(bodyparts);
                frames.add(frame);
            }
        }
        if(animationXML.getAvatars() != null && animationXML.getAvatars().size() > 0) {
            for (AnimationXML.Avatar avatarXML : animationXML.getAvatars()) {
                EffectJSON.Animation.Avatar avatar = new EffectJSON.Animation.Avatar();
                avatar.setBackground(avatarXML.getBackground());
                avatar.setForeground(avatarXML.getForeground());
                avatar.setInk(avatarXML.getInk());

                avatars.add(avatar);
            }
        }
        if(animationXML.getDirections() != null && animationXML.getDirections().size() > 0) {
            for (AnimationXML.DirectionOffset directionXML : animationXML.getDirections()) {
                EffectJSON.Animation.DirectionOffset direction = new EffectJSON.Animation.DirectionOffset();
                direction.setOffset(directionXML.getOffset());

                directions.add(direction);
            }
        }
        if(animationXML.getShadows() != null && animationXML.getShadows().size() > 0) {
            for (AnimationXML.Shadow shadowXML : animationXML.getShadows()) {
                EffectJSON.Animation.Shadow shadow = new EffectJSON.Animation.Shadow();
                shadow.setId(shadowXML.getId());

                shadows.add(shadow);
            }
        }
        if(animationXML.getAdds() != null && animationXML.getAdds().size() > 0) {
            for (AnimationXML.Add addXML : animationXML.getAdds()) {
                EffectJSON.Animation.Add add = new EffectJSON.Animation.Add();
                add.setId(addXML.getId());
                add.setAlign(addXML.getAlign());
                add.setBlend(addXML.getBlend());
                add.setInk(addXML.getInk());
                add.setBase(addXML.getBase());

                adds.add(add);
            }
        }
        if(animationXML.getRemoves() != null && animationXML.getRemoves().size() > 0) {
            for (AnimationXML.Remove removeXML : animationXML.getRemoves()) {
                EffectJSON.Animation.Remove remove = new EffectJSON.Animation.Remove();
                remove.setId(removeXML.getId());

                removes.add(remove);
            }
        }
        if(animationXML.getSprites() != null && animationXML.getSprites().size() > 0) {
            for (AnimationXML.Sprite spriteXML : animationXML.getSprites()) {
                EffectJSON.Animation.Sprite sprite = new EffectJSON.Animation.Sprite();
                List<EffectJSON.Animation.Sprite.Direction> directions2 = new ArrayList<EffectJSON.Animation.Sprite.Direction>();
                if(spriteXML.getDirectionList() != null && spriteXML.getDirectionList().size() > 0) {
                    for (AnimationXML.Sprite.Direction directionXML : spriteXML.getDirectionList()) {
                        EffectJSON.Animation.Sprite.Direction direction = new EffectJSON.Animation.Sprite.Direction();
                        direction.setId(directionXML.getId());
                        direction.setDx(directionXML.getDx());
                        direction.setDy(directionXML.getDy());
                        direction.setDz(directionXML.getDz());

                        directions2.add(direction);
                    }
                }
                sprite.setDirectionList(directions2);
                sprite.setDirections(spriteXML.getDirections());
                sprite.setId(spriteXML.getId());
                sprite.setInk(spriteXML.getInk());
                sprite.setMember(spriteXML.getMember());
                sprite.setStaticY(spriteXML.getStaticY());
                sprites.add(sprite);
            }
        }
        if(animationXML.getOverrides() != null && animationXML.getOverrides().size() > 0) {
            for (AnimationXML.Override overrideXML : animationXML.getOverrides()) {
                EffectJSON.Animation.Override override = new EffectJSON.Animation.Override();
                override.setName(overrideXML.getName());
                override.setOverride(overrideXML.getOverride());
                if (overrideXML.getFrames() != null && overrideXML.getFrames().size() > 0) {
                    List<EffectJSON.Animation.Frame> overrideFrames = new ArrayList<EffectJSON.Animation.Frame>();
                    for (AnimationXML.Frame frameXML : overrideXML.getFrames()) {
                        List<EffectJSON.Animation.Frame.Fx> fxs = new ArrayList<EffectJSON.Animation.Frame.Fx>();
                        List<EffectJSON.Animation.Frame.Bodypart> bodyparts = new ArrayList<EffectJSON.Animation.Frame.Bodypart>();
                        EffectJSON.Animation.Frame frame = new EffectJSON.Animation.Frame();
                        if (frameXML.getFxs() != null && frameXML.getFxs().size() > 0) {
                            for (AnimationXML.Frame.Fx fxXML : frameXML.getFxs()) {
                                EffectJSON.Animation.Frame.Fx fx = new EffectJSON.Animation.Frame.Fx();
                                fx.setAction(fxXML.getAction());
                                fx.setDx(fxXML.getDx());
                                fx.setDy(fxXML.getDy());
                                fx.setDz(fxXML.getDx());
                                fx.setDd(fxXML.getDd());
                                fx.setFrame(fxXML.getFrame());
                                fx.setId(fxXML.getId());

                                fxs.add(fx);
                            }
                        }
                        if (frameXML.getBodyparts() != null && frameXML.getBodyparts().size() > 0) {
                            for (AnimationXML.Frame.Bodypart bodypartXML : frameXML.getBodyparts()) {
                                List<EffectJSON.Animation.Frame.Bodypart.Item> items = new ArrayList<EffectJSON.Animation.Frame.Bodypart.Item>();
                                EffectJSON.Animation.Frame.Bodypart bodypart = new EffectJSON.Animation.Frame.Bodypart();
                                if (bodypartXML.getItems() != null && bodypartXML.getItems().size() > 0) {
                                    for (AnimationXML.Frame.Bodypart.Item itemXML : bodypartXML.getItems()) {
                                        EffectJSON.Animation.Frame.Bodypart.Item item = new EffectJSON.Animation.Frame.Bodypart.Item();
                                        item.setId(itemXML.getId());
                                        item.setBase(itemXML.getBase());

                                        items.add(item);
                                    }
                                }
                                bodypart.setAction(bodypartXML.getAction());
                                bodypart.setDx(bodypartXML.getDx());
                                bodypart.setDy(bodypartXML.getDy());
                                bodypart.setDz(bodypartXML.getDz());
                                bodypart.setDd(bodypartXML.getDd());
                                bodypart.setFrame(bodypartXML.getFrame());
                                bodypart.setId(bodypartXML.getId());
                                bodypart.setBase(bodypartXML.getBase());
                                bodypart.setItems(items);

                                bodyparts.add(bodypart);
                            }
                        }
                        frame.setFxs(fxs);
                        frame.setBodyparts(bodyparts);
                        overrideFrames.add(frame);
                    }
                    override.setFrames(overrideFrames);
                    overrides.add(override);
                }
            }
        }
        animation.setFrames(frames);
        animation.setShadows(shadows);
        animation.setAdds(adds);
        animation.setDirections(directions);
        animation.setAvatars(avatars);
        animation.setRemoves(removes);
        animation.setSprites(sprites);
        animation.setOverrides(overrides);
        animations.put(output.getName(), animation);

        output.setAnimations(animations);
    }
}