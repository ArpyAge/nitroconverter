package com.joopie.ffconverter.converters.util.spritesheet;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joopie.ffconverter.HabboAssetSWF;
import com.joopie.ffconverter.converters.IConverter;
import com.joopie.ffconverter.converters.util.spritesheet.models.SpritesheetJSON;
import com.jpexs.decompiler.flash.tags.SymbolClassTag;
import com.jpexs.decompiler.flash.tags.base.ImageTag;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by jospi on 23-4-2017.
 */
public class SpritesheetConverter implements IConverter{
    private static final String SPRITESHEET_META_APP = "FFConverter ~ Joopie";
    private static final String SPRITESHEET_META_VERSION = "1.0";
    private static final String SPRITESHEET_META_FORMAT = "RGBA8888";
    private static final float SPRITESHEET_META_SCALE = 1;

    private static final SpritesheetJSONMapper spritesheetJSONMapper = new SpritesheetJSONMapper();
    private static final TexturePacker.Settings texturePackerSettings = new TexturePacker.Settings();

    public static final Map<String, String> imageSource = new HashMap<>();
    public static SpritesheetJSON spritesheetJSON = new SpritesheetJSON();

    static {
        texturePackerSettings.format = Pixmap.Format.valueOf(SpritesheetConverter.SPRITESHEET_META_FORMAT);
        texturePackerSettings.scale = new float[] { SpritesheetConverter.SPRITESHEET_META_SCALE };
        texturePackerSettings.alias	= false;
        texturePackerSettings.maxWidth	= 4096;
        texturePackerSettings.maxHeight	= 4096;
    }

    private SpritesheetJSON.Rect getRectFromImageFile(File imageFile) throws IOException {
        BufferedImage image = ImageIO.read(imageFile);
        SpritesheetJSON.Rect rect = new SpritesheetJSON.Rect();
        rect.setW(image.getWidth());
        rect.setH(image.getHeight());
        return rect;
    }

    private SpritesheetJSON convertAtlas2JSON(File atlasFile, File atlasImageFile) throws Exception{
        FileHandle atlasFileHandle = new FileHandle(atlasFile.toString());
        TextureAtlas.TextureAtlasData textureAtlasData = new TextureAtlas.TextureAtlasData(atlasFileHandle, atlasFileHandle.parent(), false);

        SpritesheetJSON spritesheetJSON = spritesheetJSONMapper.mapTextureAtlasData(textureAtlasData);

        SpritesheetJSON.Metadata metadata = new SpritesheetJSON.Metadata();
        metadata.setApp(SpritesheetConverter.SPRITESHEET_META_APP);
        metadata.setFormat(SpritesheetConverter.SPRITESHEET_META_FORMAT);
        metadata.setImage(atlasImageFile.getName());
        metadata.setScale(String.valueOf(SpritesheetConverter.SPRITESHEET_META_SCALE));
        metadata.setVersion(SpritesheetConverter.SPRITESHEET_META_VERSION);
        metadata.setSize(this.getRectFromImageFile(atlasImageFile));
        spritesheetJSON.setMeta(metadata);

        return spritesheetJSON;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    @Override
    public void fromHabboAsset(HabboAssetSWF habboAssetSWF, File outputFolder, String type) throws Exception {
        TexturePacker texturePacker = new TexturePacker(texturePackerSettings);
        List<SymbolClassTag> tagList = habboAssetSWF.symbolTags();
        List<String> names = new ArrayList<>();
        List<Integer> tags = new ArrayList<>();
        for(SymbolClassTag tag : tagList)
        {
            names = tag.names;
            tags = tag.tags;
        }
        for (ImageTag imageTag : habboAssetSWF.getImageTags()) {
            if(tags.contains(imageTag.characterID)) {
                for (int i = 0; i < tags.size(); i++) {
                    if(tags.get(i) == imageTag.characterID) {
                        if((names.get(i).contains("_64_") && type.equals("furniture")) || (names.get(i).contains("_icon_") && type.equals("furniture")) || (names.get(i).contains("_h_") && (type.equals("figure") || type.equals("effect"))) || (names.get(i).contains("_64_") && type.equals("pet"))) {
                            //texturePacker.addImage(imageTag.getImageCached().getBufferedImage(), names.get(i) + ".png");
                            if(!names.get(i).equals(imageTag.getClassName())) {
                                imageSource.put(names.get(i).substring(habboAssetSWF.getDocumentClass().length() + 1), imageTag.getClassName().substring(habboAssetSWF.getDocumentClass().length() + 1));
                                if((imageTag.getClassName().contains("_32_") && type.equals("furniture"))|| (imageTag.getClassName().contains("_sh_") && (type.equals("figure") || type.equals("effect"))) || (imageTag.getClassName().contains("_32_") && type.equals("pet"))) {
                                    texturePacker.addImage(imageTag.getImageCached().getBufferedImage(), imageTag.getClassName() + ".png");
                                }
                            }
                        }
                    }
                }
            }
            if ((imageTag.getClassName().contains("_64_") && type.equals("furniture")) || (imageTag.getClassName().contains("_icon_") && type.equals("furniture")) || (imageTag.getClassName().contains("_h_") && (type.equals("figure") || type.equals("effect"))) || (imageTag.getClassName().contains("_64_") && type.equals("pet"))) {
                texturePacker.addImage(imageTag.getImageCached().getBufferedImage(), imageTag.getClassName() + ".png");
            }
        }

        texturePacker.pack(outputFolder, habboAssetSWF.getDocumentClass());

        File atlasFile = new File(outputFolder + "/" + habboAssetSWF.getDocumentClass() + ".atlas");
        File atlasImageFile = new File(outputFolder + "/" + habboAssetSWF.getDocumentClass() + ".png");
        this.spritesheetJSON = this.convertAtlas2JSON(atlasFile, atlasImageFile);

        //this.getObjectMapper().writeValue(new File(outputFolder + "/" + habboAssetSWF.getDocumentClass() + "_spritesheet.json"), this.spritesheetJSON);
    }
}
