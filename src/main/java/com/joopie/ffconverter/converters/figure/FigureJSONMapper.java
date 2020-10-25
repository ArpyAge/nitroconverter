package com.joopie.ffconverter.converters.figure;

import com.joopie.ffconverter.FFConverter;
import com.joopie.ffconverter.HabboAssetSWF;
import com.joopie.ffconverter.converters.figure.models.FigureJSON;
import com.joopie.ffconverter.converters.figure.models.*;
import com.joopie.ffconverter.downloader.figure.FigureDownloader;
import com.jpexs.decompiler.flash.tags.base.ImageTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.joopie.ffconverter.converters.util.spritesheet.SpritesheetConverter.imageSource;

public class FigureJSONMapper {

    public static final String MUST_START_WITH = "h_";

    public FigureJSON mapXML(HabboAssetSWF habboAssetSWF, ManifestXML manifestXML) {
        String name = habboAssetSWF.getDocumentClass();
        FigureJSON result = new FigureJSON();
        result.setName(name);
        result.setType(FigureDownloader.types.get(name));
        this.mapManifestXML(habboAssetSWF, manifestXML, result);
        return result;
    }

    private void mapManifestXML(HabboAssetSWF habboAssetSWF, ManifestXML manifestXML, FigureJSON output) {

        Map<String, List<Integer>> assetRotations = new HashMap<String, List<Integer>>();
        Map<String, FigureJSON.Asset> assets = new HashMap<String, FigureJSON.Asset>();
        for (ManifestXML.Library.Asset assetXML : manifestXML.getLibrary().getAssets()) {
            if(assetXML.getName().startsWith(MUST_START_WITH)) {
                boolean hasImage = false;
                for (ImageTag imageTag : habboAssetSWF.getImageTags()) {
                    if(imageTag.getClassName().contains(assetXML.getName())) {
                        hasImage = true;
                    }
                }
                if(hasImage || !FFConverter.getConfig().getBoolean("figure.skip.non-existing.asset.images")) {
                    FigureJSON.Asset asset = new FigureJSON.Asset();
                    asset.setName(assetXML.getName());
                    asset.setX(Integer.parseInt(assetXML.getParam().getValue().split(",")[0]));
                    asset.setY(Integer.parseInt(assetXML.getParam().getValue().split(",")[1]));
                    if(imageSource.containsKey(assetXML.getName())) {
                        asset.setSource(imageSource.get(assetXML.getName()));
                    }
                    assets.put(assetXML.getName(), asset);
                    if (FFConverter.getConfig().getBoolean("figure.rotation.enabled")) {
                        String[] names = assetXML.getName().split("_");
                        if (this.isInteger(names[4])) {
                            String firstName = names[0] + "_" + names[1] + "_" + names[2] + "_" + names[3] + "_%ROTATION%_" + names[5];
                            Integer rotation = Integer.parseInt(names[4]);
                            if (rotation >= 0 && rotation < 8) {
                                if (assetRotations.containsKey(firstName)) {
                                    assetRotations.get(firstName).add(rotation);
                                } else {
                                    List<Integer> rotations = new ArrayList<Integer>();
                                    rotations.add(rotation);
                                    assetRotations.put(firstName, rotations);
                                }
                            }
                        }
                    }
                }
                else {
                    System.out.println("Image "+ assetXML.getName() +" did not decompile for some reason");
                }
            }
        }

        if (FFConverter.getConfig().getBoolean("figure.rotation.enabled")) {
            for (String name : assetRotations.keySet()) {
                for (Integer i = 0; i < 8; i++) {
                    boolean rotationExists = false;
                    for (Integer rotation : assetRotations.get(name)) {
                        if (!rotationExists && i == rotation) {
                            rotationExists = true;
                        }
                    }
                    if (!rotationExists) {
                        if (assets.containsKey(name.replace("%ROTATION%", this.oppositeRotation(i) + ""))) {
                            FigureJSON.Asset asset = new FigureJSON.Asset();
                            asset.setName(name.replace("%ROTATION%", i + ""));
                            asset.setX(assets.get(name.replace("%ROTATION%", this.oppositeRotation(i) + "")).getX());
                            asset.setY(assets.get(name.replace("%ROTATION%", this.oppositeRotation(i) + "")).getY());
                            asset.setSource(name.replace("%ROTATION%", this.oppositeRotation(i) + ""));
                            asset.setFlipH(true);

                            assets.put(name.replace("%ROTATION%", i + ""), asset);
                        }
                    }
                }
            }
        }

        output.setAssets(assets);
    }

    private Integer oppositeRotation(Integer rotation) {
        if(rotation == 0) { return 6; }
        if(rotation == 1) { return 5; }
        if(rotation == 2) { return 4; }
        if(rotation == 3) { return 7; }
        if(rotation == 4) { return 2; }
        if(rotation == 5) { return 1; }
        if(rotation == 6) { return 0; }
        if(rotation == 7) { return 3; }
        return 0;
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
