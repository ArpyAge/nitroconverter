package com.joopie.ffconverter.converters.pet;

import com.joopie.ffconverter.HabboAssetSWF;
import com.joopie.ffconverter.converters.pet.models.PetJSON;
import com.joopie.ffconverter.converters.pet.models.AssetsXML;
import com.joopie.ffconverter.converters.pet.models.IndexXML;
import com.joopie.ffconverter.converters.pet.models.LogicXML;
import com.joopie.ffconverter.converters.pet.models.VisualizationXML;
import com.joopie.ffconverter.converters.util.RGB;
import com.jpexs.decompiler.flash.tags.DefineBinaryDataTag;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.joopie.ffconverter.converters.util.spritesheet.SpritesheetConverter.imageSource;

public class PetJSONMapper {

    private List<RGB> paletteColors;

    public static final int VISUALIZATION_DEFAULT_SIZE = 64;

    public static final int VISUALIZATION_ICON_SIZE = 1;

    public PetJSON mapXML(HabboAssetSWF habboAssetSWF, AssetsXML assetsXML, IndexXML indexXML, LogicXML logicXML, VisualizationXML visualizationXML) {
        PetJSON result = new PetJSON();
        this.mapAssetsXML(habboAssetSWF, assetsXML, result);
        this.mapIndexXML(indexXML, result);
        this.mapLogicXML(logicXML, result);
        this.mapVisualizationXML(visualizationXML, result);
        return result;
    }

    private void mapAssetsXML(HabboAssetSWF habboAssetSWF, AssetsXML assetsXML, PetJSON output) {
        Map<String, PetJSON.Asset> assets = new HashMap<String, PetJSON.Asset>();
        for (AssetsXML.Asset assetXML : assetsXML.getAssets()) {
            if(!assetXML.getName().contains("_32_")) {
                PetJSON.Asset asset = new PetJSON.Asset();
                asset.setName(assetXML.getName());
                asset.setSource(assetXML.getSource());
                if(imageSource.containsKey(assetXML.getSource())) {
                    asset.setSource(imageSource.get(assetXML.getSource()));
                }
                if(imageSource.containsKey(assetXML.getName())) {
                    asset.setSource(imageSource.get(assetXML.getName()));
                }
                asset.setX(assetXML.getX());
                asset.setY(assetXML.getY());
                asset.setFlipH(assetXML.isFlipH());
                asset.setUsesPalette(assetXML.getUsesPalette());

                assets.put(assetXML.getName(), asset);
            }
        }

        List<PetJSON.Palette> palettes = new ArrayList<PetJSON.Palette>();
        if (assetsXML.getPalettes() != null && !assetsXML.getPalettes().isEmpty()) {
            for (AssetsXML.Palette paletteXML : assetsXML.getPalettes()) {
                PetJSON.Palette palette = new PetJSON.Palette();
                palette.setId(paletteXML.getId());
                palette.setSource(paletteXML.getSource());
                palette.setColor1(paletteXML.getColor1());
                palette.setColor2(paletteXML.getColor2());

                this.getPalette(habboAssetSWF, paletteXML.getSource());

                List<List<Integer>> RGB = new ArrayList<>();
                int counter = 1;
                for(RGB rgb : this.paletteColors) {
                    List<Integer> rgbs = new ArrayList<>();
                    rgbs.add(rgb.getR());
                    rgbs.add(rgb.getG());
                    rgbs.add(rgb.getB());
                    RGB.add(rgbs);
                }
                palette.setRGB(RGB);

                palettes.add(palette);
            }
        }

        output.setPalettes(palettes);
        output.setAssets(assets);
    }

    private byte[] getBinaryData(HabboAssetSWF habboAssetSWF, String type, boolean documentNameTwice) {
        String binaryName = habboAssetSWF.getFullClassName(type, documentNameTwice);
        DefineBinaryDataTag tag = habboAssetSWF.getBinaryTagByName(binaryName);
        if(tag == null) {
            binaryName = habboAssetSWF.getFullClassName(type, documentNameTwice, true);
            tag = habboAssetSWF.getBinaryTagByName(binaryName);
        }
        if(tag == null) {
            return null;
        }
        return tag.binaryData.getRangeData();
    }

    private void getPalette(HabboAssetSWF habboAssetSWF, String paletteName) {
        byte[] binaryData = this.getBinaryData(habboAssetSWF, paletteName, false);
        if(binaryData != null) {
            InputStream inputStream = new ByteArrayInputStream(binaryData);
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            this.paletteColors = new ArrayList<>();

            try {
                int R = 0;
                int G = 0;
                int B = 0;
                int counter = 1;
                while (dataInputStream.available() > 0) {
                    if (counter == 1) {
                        R = dataInputStream.readUnsignedByte();
                    } else if (counter == 2) {
                        G = dataInputStream.readUnsignedByte();
                    } else if (counter == 3) {
                        B = dataInputStream.readUnsignedByte();
                        this.paletteColors.add(new RGB(R, G, B));
                        counter = 0;
                    }
                    counter++;
                }
            } catch (Exception e) {

            }
        }

    }

    private void mapIndexXML(IndexXML indexXML, PetJSON output) {
        output.setName(indexXML.getType());
        output.setLogicType(indexXML.getLogic());
        output.setVisualizationType(indexXML.getVisualization());
    }

    private void mapLogicXML(LogicXML logicXML, PetJSON output) {
        PetJSON.Dimensions dimensions = new PetJSON.Dimensions();
        dimensions.setX(logicXML.getModel().getDimensions().getX());
        dimensions.setY(logicXML.getModel().getDimensions().getY());
        dimensions.setZ(logicXML.getModel().getDimensions().getZ());
        output.setDimensions(dimensions);

        List<Integer> directions = new ArrayList<Integer>();
        if(logicXML.getModel().getDirections() == null) {
            directions.add(0);
        }
        else {
            for (LogicXML.Model.Direction directionXML : logicXML.getModel().getDirections()) {
                directions.add(directionXML.getId());
            }
        }
        if(logicXML.getMask() != null) {
            output.setMaskType(logicXML.getMask().getType());
        }
        output.setDirections(directions);
    }

    private Map<Integer, PetJSON.Visualization.Layer> mapVisualizationLayerXML(List<VisualizationXML.Visualization.Layer> layersXML) {
        Map<Integer, PetJSON.Visualization.Layer> layers = new HashMap<Integer, PetJSON.Visualization.Layer>();
        for (VisualizationXML.Visualization.Layer layerXML : layersXML) {
            PetJSON.Visualization.Layer layer = new PetJSON.Visualization.Layer();
            layer.setId(layerXML.getId());
            layer.setAlpha(layerXML.getAlpha());
            layer.setInk(layerXML.getInk());
            layer.setTag(layerXML.getTag());
            layer.setX(layerXML.getX());
            layer.setY(layerXML.getY());
            layer.setZ(layerXML.getZ());
            layer.setIgnoreMouse(layerXML.isIgnoreMouse());
            layers.put(layerXML.getId(), layer);
        }

        return layers;
    }

    private void mapVisualizationLayerXML(VisualizationXML.Visualization visXML, PetJSON.Visualization output) {
        if (visXML.getLayers() != null && !visXML.getLayers().isEmpty()) {
            Map<Integer, PetJSON.Visualization.Layer> layers = this.mapVisualizationLayerXML(visXML.getLayers());

            output.setLayers(layers);
        }
    }

    private void mapVisualizationDirectionXML(VisualizationXML.Visualization visXML, PetJSON.Visualization output) {
        if (visXML.getDirections() != null && !visXML.getDirections().isEmpty()) {
            Map<Integer, PetJSON.Visualization.Direction> directions = new HashMap<Integer, PetJSON.Visualization.Direction>();
            for (VisualizationXML.Visualization.Direction directionXML : visXML.getDirections()) {
                PetJSON.Visualization.Direction direction = new PetJSON.Visualization.Direction();
                direction.setId(directionXML.getId());
                if (directionXML.getLayers() != null && !directionXML.getLayers().isEmpty()) {
                    Map<Integer, PetJSON.Visualization.Layer> layers = this.mapVisualizationLayerXML(directionXML.getLayers());
                    direction.setLayers(layers);
                }
                directions.put(directionXML.getId(), direction);
            }

            if (!directions.isEmpty()) {
                output.setDirections(directions);
            }
        }
    }

    private Map<Integer, PetJSON.Visualization.Color.ColorLayer> mapVisualizationColorLayerXML(VisualizationXML.Visualization.Color colorXML) {
        Map<Integer, PetJSON.Visualization.Color.ColorLayer> colorLayers = new HashMap<Integer, PetJSON.Visualization.Color.ColorLayer>();
        for (VisualizationXML.Visualization.Color.ColorLayer colorLayerXML : colorXML.getLayers()) {
            PetJSON.Visualization.Color.ColorLayer colorLayer = new PetJSON.Visualization.Color.ColorLayer();
            colorLayer.setId(colorLayerXML.getId());
            colorLayer.setColor(Integer.parseInt(colorLayerXML.getColor(), 16));

            colorLayers.put(colorLayerXML.getId(), colorLayer);
        }

        return colorLayers;
    }

    private void mapVisualizationColorXML(VisualizationXML.Visualization visXML, PetJSON.Visualization output) {
        if (visXML.getColors() != null && !visXML.getColors().isEmpty()) {
            Map<Integer, PetJSON.Visualization.Color> colors = new HashMap<Integer, PetJSON.Visualization.Color>();
            for (VisualizationXML.Visualization.Color colorXML : visXML.getColors()) {
                if (colorXML.getLayers() != null && !colorXML.getLayers().isEmpty()) {
                    PetJSON.Visualization.Color color = new PetJSON.Visualization.Color();
                    color.setId(colorXML.getId());

                    Map<Integer, PetJSON.Visualization.Color.ColorLayer> colorLayers = this.mapVisualizationColorLayerXML(colorXML);

                    color.setLayers(colorLayers);
                    colors.put(colorXML.getId(), color);
                }
            }

            if (!colors.isEmpty()) {
                output.setColors(colors);
            }
        }
    }

    private Map<Integer, PetJSON.Visualization.Animation.AnimationLayer> mapVisualizationAnimationLayerXML(VisualizationXML.Visualization.Animation animationXML) {
        Map<Integer, PetJSON.Visualization.Animation.AnimationLayer> animationLayers = new HashMap<Integer, PetJSON.Visualization.Animation.AnimationLayer>();
        for (VisualizationXML.Visualization.Animation.AnimationLayer animationLayerXML : animationXML.getLayers()) {
            PetJSON.Visualization.Animation.AnimationLayer animationLayer = new PetJSON.Visualization.Animation.AnimationLayer();
            animationLayer.setId(animationLayerXML.getId());
            animationLayer.setFrameRepeat(animationLayerXML.getFrameRepeat());
            animationLayer.setLoopCount(animationLayerXML.getLoopCount());
            animationLayer.setRandom(animationLayerXML.getRandom());

            if(animationLayerXML.getFrameSequences() != null && !animationLayerXML.getFrameSequences().isEmpty()) {
                Map<Integer, PetJSON.Visualization.Animation.AnimationLayer.FrameSequence> frameSequences = this.mapVisualizationFrameSequenceXML(animationLayerXML);

                animationLayer.setFrameSequences(frameSequences);
                animationLayers.put(animationLayerXML.getId(), animationLayer);
            }
        }

        return animationLayers;
    }

    private Map<Integer, PetJSON.Visualization.Animation.AnimationLayer.FrameSequence> mapVisualizationFrameSequenceXML(VisualizationXML.Visualization.Animation.AnimationLayer animationLayerXML) {
        Map<Integer, PetJSON.Visualization.Animation.AnimationLayer.FrameSequence> frameSequences = new HashMap<Integer, PetJSON.Visualization.Animation.AnimationLayer.FrameSequence>();
        Integer frameSequenceCount = 0;
        for (VisualizationXML.Visualization.Animation.AnimationLayer.FrameSequence frameSequenceXML : animationLayerXML.getFrameSequences()) {
            PetJSON.Visualization.Animation.AnimationLayer.FrameSequence frameSequence = new PetJSON.Visualization.Animation.AnimationLayer.FrameSequence();

            if (frameSequenceXML.getFrames() != null && !frameSequenceXML.getFrames().isEmpty()) {
                Integer frameId = 0;
                Map<Integer, PetJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame> frames = new HashMap<Integer, PetJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame>();
                for (VisualizationXML.Visualization.Animation.AnimationLayer.FrameSequence.Frame frameXML : frameSequenceXML.getFrames()) {
                    PetJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame frame = new PetJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame();
                    frame.setX(frameXML.getX());
                    frame.setY(frameXML.getY());
                    frame.setRandomX(frameXML.getRandomX());
                    frame.setRandomY(frameXML.getRandomY());
                    if(frameXML.getId().equals("NaN")) {
                        frame.setId(0);
                    }
                    else {
                        frame.setId(Integer.parseInt(frameXML.getId()));
                    }
                    if (frameXML.getOffsets() != null && !frameXML.getOffsets().isEmpty()) {
                        List<PetJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame.Offset> offsets = new ArrayList<PetJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame.Offset>();
                        for (VisualizationXML.Visualization.Animation.AnimationLayer.FrameSequence.Frame.Offset offsetXML : frameXML.getOffsets()) {
                            PetJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame.Offset offset = new PetJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame.Offset();
                            offset.setDirection(offsetXML.getDirection());
                            offset.setX(offsetXML.getX());
                            offset.setY(offsetXML.getY());
                            offsets.add(offset);
                        }
                        frame.setOffsets(offsets);
                    }
                    frames.put(frameId, frame);
                    frameId++;
                }
                frameSequence.setLoopCount(frameSequenceXML.getLoopCount());
                frameSequence.setRandom(frameSequenceXML.getRandom());
                frameSequence.setFrames(frames);
                frameSequences.put(frameSequenceCount, frameSequence);
            }
            frameSequenceCount++;
        }

        return frameSequences;
    }

    private void mapVisualizationAnimationXML(VisualizationXML.Visualization visXML, PetJSON.Visualization output) {
        if (visXML.getAnimations() != null && !visXML.getAnimations().isEmpty()) {
            Map<Integer, PetJSON.Visualization.Animation> animations = new HashMap<Integer, PetJSON.Visualization.Animation>();
            for (VisualizationXML.Visualization.Animation animationXML : visXML.getAnimations()) {
                if (animationXML.getLayers() != null && !animationXML.getLayers().isEmpty()) {
                    PetJSON.Visualization.Animation animation = new PetJSON.Visualization.Animation();
                    animation.setId(animationXML.getId());
                    animation.setTransitionTo(animationXML.getTransitionTo());
                    animation.setTransitionFrom(animationXML.getTransitionFrom());
                    animation.setImmediateChangeFrom(animationXML.getImmediateChangeFrom());

                    Map<Integer, PetJSON.Visualization.Animation.AnimationLayer> animationLayers = this.mapVisualizationAnimationLayerXML(animationXML);

                    animation.setLayers(animationLayers);
                    animations.put(animationXML.getId(), animation);
                }
            }

            if (!animations.isEmpty()) {
                output.setAnimations(animations);
            }
        }
    }

    private void mapVisualizationPostureXML(VisualizationXML.Visualization visXML, PetJSON.Visualization output) {
        if (visXML.getPostures() != null && !visXML.getPostures().isEmpty()) {
            Map<String, PetJSON.Visualization.Posture> postures = new HashMap<String, PetJSON.Visualization.Posture>();
            for (VisualizationXML.Visualization.Posture postureXML : visXML.getPostures()) {
                PetJSON.Visualization.Posture posture = new PetJSON.Visualization.Posture();
                posture.setId(postureXML.getId());
                posture.setAnimationId(postureXML.getAnimationId());
                postures.put(postureXML.getId(), posture);
            }

            if (!postures.isEmpty()) {
                output.setPostures(postures);
            }
        }
    }

    private void mapVisualizationGestureXML(VisualizationXML.Visualization visXML, PetJSON.Visualization output) {
        if (visXML.getGestures() != null && !visXML.getGestures().isEmpty()) {
            Map<String, PetJSON.Visualization.Gesture> gestures = new HashMap<String, PetJSON.Visualization.Gesture>();
            for (VisualizationXML.Visualization.Gesture gestureXML : visXML.getGestures()) {
                PetJSON.Visualization.Gesture gesture = new PetJSON.Visualization.Gesture();
                gesture.setId(gestureXML.getId());
                gesture.setAnimationId(gestureXML.getAnimationId());
                gestures.put(gestureXML.getId(), gesture);
            }

            if (!gestures.isEmpty()) {
                output.setGestures(gestures);
            }
        }
    }

    public void mapVisualizationXML(VisualizationXML visualizationXML, PetJSON output) {
        List<PetJSON.Visualization> visualizations = new ArrayList<>();
        for (VisualizationXML.Visualization visXML : visualizationXML.getVisualizations()) {
            if (visXML.getSize() == VISUALIZATION_DEFAULT_SIZE || visXML.getSize() == VISUALIZATION_ICON_SIZE) {
                PetJSON.Visualization visualization = new PetJSON.Visualization();
                visualization.setAngle(visXML.getAngle());
                visualization.setLayerCount(visXML.getLayerCount());
                visualization.setSize(visXML.getSize());

                this.mapVisualizationLayerXML(visXML, visualization);
                this.mapVisualizationDirectionXML(visXML, visualization);
                this.mapVisualizationColorXML(visXML, visualization);
                this.mapVisualizationAnimationXML(visXML, visualization);
                this.mapVisualizationPostureXML(visXML, visualization);
                this.mapVisualizationGestureXML(visXML, visualization);

                visualizations.add(visualization);
            }
        }

        output.setVisualizations(visualizations);
    }
}
