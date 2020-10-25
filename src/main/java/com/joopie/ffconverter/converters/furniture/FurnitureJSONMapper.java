package com.joopie.ffconverter.converters.furniture;

import com.joopie.ffconverter.converters.furniture.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.joopie.ffconverter.converters.util.spritesheet.SpritesheetConverter.imageSource;

/**
 * Created by jospi on 22-4-2017.
 */
public class FurnitureJSONMapper {

    public static final int VISUALIZATION_DEFAULT_SIZE = 64;

    public static final int VISUALIZATION_ICON_SIZE = 1;

    public FurnitureJSON mapXML(AssetsXML assetsXML, IndexXML indexXML, LogicXML logicXML, VisualizationXML visualizationXML) {
        FurnitureJSON result = new FurnitureJSON();
        this.mapAssetsXML(assetsXML, result);
        this.mapIndexXML(indexXML, result);
        this.mapLogicXML(logicXML, result);
        this.mapVisualizationXML(visualizationXML, result);
        return result;
    }

    private void mapAssetsXML(AssetsXML assetsXML, FurnitureJSON output) {
        Map<String, FurnitureJSON.Asset> assets = new HashMap<String, FurnitureJSON.Asset>();
        for (AssetsXML.Asset assetXML : assetsXML.getAssets()) {
            if(!assetXML.getName().contains("_32_")) {
                FurnitureJSON.Asset asset = new FurnitureJSON.Asset();
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

                assets.put(assetXML.getName(), asset);
            }
        }

        imageSource.clear();

        output.setAssets(assets);
    }

    private void mapIndexXML(IndexXML indexXML, FurnitureJSON output) {
        output.setName(indexXML.getType());
        output.setLogicType(indexXML.getLogic());
        output.setVisualizationType(indexXML.getVisualization());
    }

    private void mapLogicXML(LogicXML logicXML, FurnitureJSON output) {
        FurnitureJSON.Dimensions dimensions = new FurnitureJSON.Dimensions();
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
        if(logicXML.getCredits() != null) {
            output.setCredits(logicXML.getCredits().getValue());
        }
        output.setDirections(directions);
    }

    private Map<Integer, FurnitureJSON.Visualization.Layer> mapVisualizationLayerXML(List<VisualizationXML.Visualization.Layer> layersXML) {
        Map<Integer, FurnitureJSON.Visualization.Layer> layers = new HashMap<Integer, FurnitureJSON.Visualization.Layer>();
        for (VisualizationXML.Visualization.Layer layerXML : layersXML) {
            FurnitureJSON.Visualization.Layer layer = new FurnitureJSON.Visualization.Layer();
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

    private void mapVisualizationLayerXML(VisualizationXML.Visualization visXML, FurnitureJSON.Visualization output) {
        if (visXML.getLayers() != null && !visXML.getLayers().isEmpty()) {
            Map<Integer, FurnitureJSON.Visualization.Layer> layers = this.mapVisualizationLayerXML(visXML.getLayers());

            output.setLayers(layers);
        }
    }

    private void mapVisualizationDirectionXML(VisualizationXML.Visualization visXML, FurnitureJSON.Visualization output) {
        if (visXML.getDirections() != null && !visXML.getDirections().isEmpty()) {
            Map<Integer, FurnitureJSON.Visualization.Direction> directions = new HashMap<Integer, FurnitureJSON.Visualization.Direction>();
            for (VisualizationXML.Visualization.Direction directionXML : visXML.getDirections()) {
                FurnitureJSON.Visualization.Direction direction = new FurnitureJSON.Visualization.Direction();
                direction.setId(directionXML.getId());
                if (directionXML.getLayers() != null && !directionXML.getLayers().isEmpty()) {
                    Map<Integer, FurnitureJSON.Visualization.Layer> layers = this.mapVisualizationLayerXML(directionXML.getLayers());
                    direction.setLayers(layers);
                }
                directions.put(directionXML.getId(), direction);
            }

            if (!directions.isEmpty()) {
                output.setDirections(directions);
            }
        }
    }

    private Map<Integer, FurnitureJSON.Visualization.Color.ColorLayer> mapVisualizationColorLayerXML(VisualizationXML.Visualization.Color colorXML) {
        Map<Integer, FurnitureJSON.Visualization.Color.ColorLayer> colorLayers = new HashMap<Integer, FurnitureJSON.Visualization.Color.ColorLayer>();
        for (VisualizationXML.Visualization.Color.ColorLayer colorLayerXML : colorXML.getLayers()) {
            FurnitureJSON.Visualization.Color.ColorLayer colorLayer = new FurnitureJSON.Visualization.Color.ColorLayer();
            colorLayer.setId(colorLayerXML.getId());
            colorLayer.setColor(Integer.parseInt(colorLayerXML.getColor(), 16));

            colorLayers.put(colorLayerXML.getId(), colorLayer);
        }

        return colorLayers;
    }

    private void mapVisualizationColorXML(VisualizationXML.Visualization visXML, FurnitureJSON.Visualization output) {
        if (visXML.getColors() != null && !visXML.getColors().isEmpty()) {
            Map<Integer, FurnitureJSON.Visualization.Color> colors = new HashMap<Integer, FurnitureJSON.Visualization.Color>();
            for (VisualizationXML.Visualization.Color colorXML : visXML.getColors()) {
                if (colorXML.getLayers() != null && !colorXML.getLayers().isEmpty()) {
                    FurnitureJSON.Visualization.Color color = new FurnitureJSON.Visualization.Color();
                    color.setId(colorXML.getId());

                    Map<Integer, FurnitureJSON.Visualization.Color.ColorLayer> colorLayers = this.mapVisualizationColorLayerXML(colorXML);

                    color.setLayers(colorLayers);
                    colors.put(colorXML.getId(), color);
                }
            }

            if (!colors.isEmpty()) {
                output.setColors(colors);
            }
        }
    }

    private Map<Integer, FurnitureJSON.Visualization.Animation.AnimationLayer> mapVisualizationAnimationLayerXML(VisualizationXML.Visualization.Animation animationXML) {
        Map<Integer, FurnitureJSON.Visualization.Animation.AnimationLayer> animationLayers = new HashMap<Integer, FurnitureJSON.Visualization.Animation.AnimationLayer>();
        for (VisualizationXML.Visualization.Animation.AnimationLayer animationLayerXML : animationXML.getLayers()) {
            FurnitureJSON.Visualization.Animation.AnimationLayer animationLayer = new FurnitureJSON.Visualization.Animation.AnimationLayer();
            animationLayer.setId(animationLayerXML.getId());
            animationLayer.setFrameRepeat(animationLayerXML.getFrameRepeat());
            animationLayer.setLoopCount(animationLayerXML.getLoopCount());
            animationLayer.setRandom(animationLayerXML.getRandom());

            if(animationLayerXML.getFrameSequences() != null && !animationLayerXML.getFrameSequences().isEmpty()) {
                Map<Integer, FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence> frameSequences = this.mapVisualizationFrameSequenceXML(animationLayerXML);

                animationLayer.setFrameSequences(frameSequences);
                animationLayers.put(animationLayerXML.getId(), animationLayer);
            }
        }

        return animationLayers;
    }

    private Map<Integer, FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence> mapVisualizationFrameSequenceXML(VisualizationXML.Visualization.Animation.AnimationLayer animationLayerXML) {
        Map<Integer, FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence> frameSequences = new HashMap<Integer, FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence>();
        Integer frameSequenceCount = 0;
        for (VisualizationXML.Visualization.Animation.AnimationLayer.FrameSequence frameSequenceXML : animationLayerXML.getFrameSequences()) {
            FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence frameSequence = new FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence();

            if (frameSequenceXML.getFrames() != null && !frameSequenceXML.getFrames().isEmpty()) {
                Integer frameId = 0;
                Map<Integer, FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame> frames = new HashMap<Integer, FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame>();
                for (VisualizationXML.Visualization.Animation.AnimationLayer.FrameSequence.Frame frameXML : frameSequenceXML.getFrames()) {
                    FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame frame = new FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame();
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
                        List<FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame.Offset> offsets = new ArrayList<FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame.Offset>();
                        for (VisualizationXML.Visualization.Animation.AnimationLayer.FrameSequence.Frame.Offset offsetXML : frameXML.getOffsets()) {
                            FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame.Offset offset = new FurnitureJSON.Visualization.Animation.AnimationLayer.FrameSequence.Frame.Offset();
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

    private void mapVisualizationAnimationXML(VisualizationXML.Visualization visXML, FurnitureJSON.Visualization output) {
        if (visXML.getAnimations() != null && !visXML.getAnimations().isEmpty()) {
            Map<Integer, FurnitureJSON.Visualization.Animation> animations = new HashMap<Integer, FurnitureJSON.Visualization.Animation>();
            for (VisualizationXML.Visualization.Animation animationXML : visXML.getAnimations()) {
                if (animationXML.getLayers() != null && !animationXML.getLayers().isEmpty()) {
                    FurnitureJSON.Visualization.Animation animation = new FurnitureJSON.Visualization.Animation();
                    animation.setId(animationXML.getId());
                    animation.setTransitionTo(animationXML.getTransitionTo());
                    animation.setTransitionFrom(animationXML.getTransitionFrom());
                    animation.setImmediateChangeFrom(animationXML.getImmediateChangeFrom());

                    Map<Integer, FurnitureJSON.Visualization.Animation.AnimationLayer> animationLayers = this.mapVisualizationAnimationLayerXML(animationXML);

                    animation.setLayers(animationLayers);
                    animations.put(animationXML.getId(), animation);
                }
            }

            if (!animations.isEmpty()) {
                output.setAnimations(animations);
            }
        }
    }

    private void mapVisualizationPostureXML(VisualizationXML.Visualization visXML, FurnitureJSON.Visualization output) {
        if (visXML.getPostures() != null && !visXML.getPostures().isEmpty()) {
            Map<String, FurnitureJSON.Visualization.Posture> postures = new HashMap<String, FurnitureJSON.Visualization.Posture>();
            for (VisualizationXML.Visualization.Posture postureXML : visXML.getPostures()) {
                FurnitureJSON.Visualization.Posture posture = new FurnitureJSON.Visualization.Posture();
                posture.setId(postureXML.getId());
                posture.setAnimationId(postureXML.getAnimationId());
                postures.put(postureXML.getId(), posture);
            }

            if (!postures.isEmpty()) {
                output.setPostures(postures);
            }
        }
    }

    private void mapVisualizationGestureXML(VisualizationXML.Visualization visXML, FurnitureJSON.Visualization output) {
        if (visXML.getGestures() != null && !visXML.getGestures().isEmpty()) {
            Map<String, FurnitureJSON.Visualization.Gesture> gestures = new HashMap<String, FurnitureJSON.Visualization.Gesture>();
            for (VisualizationXML.Visualization.Gesture gestureXML : visXML.getGestures()) {
                FurnitureJSON.Visualization.Gesture gesture = new FurnitureJSON.Visualization.Gesture();
                gesture.setId(gestureXML.getId());
                gesture.setAnimationId(gestureXML.getAnimationId());
                gestures.put(gestureXML.getId(), gesture);
            }

            if (!gestures.isEmpty()) {
                output.setGestures(gestures);
            }
        }
    }

    public void mapVisualizationXML(VisualizationXML visualizationXML, FurnitureJSON output) {
        List<FurnitureJSON.Visualization> visualizations = new ArrayList<>();
        for (VisualizationXML.Visualization visXML : visualizationXML.getVisualizations()) {
            if (visXML.getSize() == VISUALIZATION_DEFAULT_SIZE || visXML.getSize() == VISUALIZATION_ICON_SIZE) {
                FurnitureJSON.Visualization visualization = new FurnitureJSON.Visualization();
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
