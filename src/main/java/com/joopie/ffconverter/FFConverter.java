package com.joopie.ffconverter;

import java.io.File;

import com.joopie.ffconverter.configuration.ConfigurationManager;
import com.joopie.ffconverter.converters.IConverter;
import com.joopie.ffconverter.converters.effect.EffectConverter;
import com.joopie.ffconverter.converters.figure.FigureConverter;
import com.joopie.ffconverter.converters.furniture.FurnitureConverter;
import com.joopie.ffconverter.converters.pet.PetConverter;
import com.joopie.ffconverter.converters.util.spritesheet.SpritesheetConverter;
import com.joopie.ffconverter.downloader.Downloader;
import com.joopie.ffconverter.downloader.effect.EffectDownloader;
import com.joopie.ffconverter.downloader.figure.FigureDownloader;
import com.joopie.ffconverter.downloader.furniture.FurnitureDownloader;
import com.joopie.ffconverter.downloader.pet.PetDownloader;
import com.joopie.ffconverter.logging.Logging;

/**
 * Created by jospi on 10-4-2017.
 */
public class FFConverter {
    private static ConfigurationManager config;
    private static Logging logging;

    public static void main(String[] args) throws Exception {
        FFConverter.config = new ConfigurationManager("config.ini");
        FFConverter.logging = new Logging();

        File outputFolderFurniture = new File(FFConverter.getConfig().getValue("output.folder.furniture"));
        if (!outputFolderFurniture.isDirectory()) {
            outputFolderFurniture.mkdirs();
        }

        File outputFolderFigure = new File(FFConverter.getConfig().getValue("output.folder.figure"));
        if (!outputFolderFigure.isDirectory()) {
            outputFolderFigure.mkdirs();
        }

        File outputFolderEffect = new File(FFConverter.getConfig().getValue("output.folder.effect"));
        if (!outputFolderEffect.isDirectory()) {
            outputFolderEffect.mkdirs();
        }

        File outputFolderPet = new File(FFConverter.getConfig().getValue("output.folder.pet"));
        if (!outputFolderPet.isDirectory()) {
            outputFolderPet.mkdirs();
        }

        IConverter furnitureConverter = new FurnitureConverter();
        IConverter figureConverter = new FigureConverter();
        IConverter effectConverter = new EffectConverter();
        IConverter petConverter = new PetConverter();
        IConverter spritesheetConverter = new SpritesheetConverter();

        Downloader downloader = new Downloader();

        if (FFConverter.getConfig().getBoolean("convert.furniture")) {
            downloader.addDownloader(new FurnitureDownloader(habboAssetSWF -> {
                System.out.println("Attempt parsing furniture: " + habboAssetSWF.getDocumentClass());

                try {
                    File assetOuputFolder = new File(outputFolderFurniture + "/" + habboAssetSWF.getDocumentClass());
                    if (!assetOuputFolder.isDirectory()) {
                        assetOuputFolder.mkdirs();
                    } else if (assetOuputFolder.list().length > 0) {
                        System.out.println("Furniture already exists or the directory is not empty!");

                        return;
                    }

                    spritesheetConverter.fromHabboAsset(habboAssetSWF, assetOuputFolder, "furniture");
                    furnitureConverter.fromHabboAsset(habboAssetSWF, assetOuputFolder, "furniture");
                } catch (Exception e) {
                    e.printStackTrace();
                    FFConverter.getLogging().logFurniture("Furniture error: "+ habboAssetSWF.getDocumentClass());
                }
            }));
        }

        if (FFConverter.getConfig().getBoolean("convert.figure")) {
            downloader.addDownloader(new FigureDownloader(habboAssetSWF -> {
                System.out.println("Attempt parsing figure: " + habboAssetSWF.getDocumentClass());

                try {
                    File assetOuputFolder = new File(outputFolderFigure + "/" + habboAssetSWF.getDocumentClass());
                    if (!assetOuputFolder.isDirectory()) {
                        assetOuputFolder.mkdirs();
                    } else if (assetOuputFolder.list().length > 0) {
                        System.out.println("Figure already exists or the directory is not empty!");

                        return;
                    }

                    spritesheetConverter.fromHabboAsset(habboAssetSWF, assetOuputFolder, "figure");
                    figureConverter.fromHabboAsset(habboAssetSWF, assetOuputFolder, "figure");
                } catch (Exception e) {
                    e.printStackTrace();
                    FFConverter.getLogging().logFigure("Figure error: "+ habboAssetSWF.getDocumentClass());
                }
            }));
        }

        if (FFConverter.getConfig().getBoolean("convert.effect")) {
            downloader.addDownloader(new EffectDownloader(habboAssetSWF -> {
                System.out.println("Attempt parsing figure: " + habboAssetSWF.getDocumentClass());

                try {
                    File assetOuputFolder = new File(outputFolderEffect + "/" + habboAssetSWF.getDocumentClass());
                    if (!assetOuputFolder.isDirectory()) {
                        assetOuputFolder.mkdirs();
                    } else if (assetOuputFolder.list().length > 0) {
                        System.out.println("Figure already exists or the directory is not empty!");

                        return;
                    }

                    if(habboAssetSWF.getImageTags().size() > 0) {
                        spritesheetConverter.fromHabboAsset(habboAssetSWF, assetOuputFolder, "effect");
                    }
                    effectConverter.fromHabboAsset(habboAssetSWF, assetOuputFolder, "effect");
                } catch (Exception e) {
                    e.printStackTrace();
                    FFConverter.getLogging().logEffect("Effect error: "+ habboAssetSWF.getDocumentClass());
                }
            }));
        }

        if (FFConverter.getConfig().getBoolean("convert.pet")) {
            downloader.addDownloader(new PetDownloader(habboAssetSWF -> {
                System.out.println("Attempt parsing pet: " + habboAssetSWF.getDocumentClass());

                try {
                    File assetOuputFolder = new File(outputFolderPet + "/" + habboAssetSWF.getDocumentClass());
                    if (!assetOuputFolder.isDirectory()) {
                        assetOuputFolder.mkdirs();
                    } else if (assetOuputFolder.list().length > 0) {
                        System.out.println("Pet already exists or the directory is not empty!");

                        return;
                    }

                    if(habboAssetSWF.getImageTags().size() > 0) {
                        spritesheetConverter.fromHabboAsset(habboAssetSWF, assetOuputFolder, "pet");
                    }
                    petConverter.fromHabboAsset(habboAssetSWF, assetOuputFolder, "pet");
                } catch (Exception e) {
                    e.printStackTrace();
                    FFConverter.getLogging().logPet("Pet error: "+ habboAssetSWF.getDocumentClass());
                }
            }));
        }

        downloader.download();
        downloader = null;
    }

    public static ConfigurationManager getConfig()
    {
        return config;
    }

    public static Logging getLogging()
    {
        return logging;
    }
}