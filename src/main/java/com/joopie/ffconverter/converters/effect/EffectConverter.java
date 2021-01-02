package com.joopie.ffconverter.converters.effect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joopie.ffconverter.HabboAssetSWF;
import com.joopie.ffconverter.converters.IConverter;
import com.joopie.ffconverter.converters.effect.models.AnimationXML;
import com.joopie.ffconverter.converters.effect.models.EffectJSON;
import com.joopie.ffconverter.converters.effect.models.ManifestXML;
import com.joopie.ffconverter.converters.util.spritesheet.SpritesheetConverter;
import com.joopie.ffconverter.converters.util.spritesheet.models.SpritesheetJSON;
import com.jpexs.decompiler.flash.tags.DefineBinaryDataTag;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public class EffectConverter implements IConverter {
    private static final EffectJSONMapper effectJSONMapper = new EffectJSONMapper();

    private Unmarshaller getUnmashaller(Class clazz) throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return jaxbContext.createUnmarshaller();
    }

    private byte[] getBinaryData(HabboAssetSWF habboAssetSWF, String type, boolean documentNameTwice) {
        String binaryName = habboAssetSWF.getFullClassName(type, documentNameTwice);
        DefineBinaryDataTag tag = habboAssetSWF.getBinaryTagByName(binaryName);
        if(tag == null) {
            binaryName = habboAssetSWF.getFullClassName(type, documentNameTwice, true);
            tag = habboAssetSWF.getBinaryTagByName(binaryName);
        }
        return tag.binaryData.getRangeData();
    }

    private ManifestXML getManifestXML(HabboAssetSWF habboAssetSWF) throws Exception {
        byte[] binaryData = this.getBinaryData(habboAssetSWF, "manifest", false);
        InputStream inputStream = new ByteArrayInputStream(binaryData);
        return (ManifestXML) this.getUnmashaller(ManifestXML.class).unmarshal(inputStream);
    }

    private AnimationXML getAnimationXML(HabboAssetSWF habboAssetSWF) throws Exception {
        byte[] binaryData = this.getBinaryData(habboAssetSWF, "animation", false);
        InputStream inputStream = new ByteArrayInputStream(binaryData);
        return (AnimationXML) this.getUnmashaller(AnimationXML.class).unmarshal(inputStream);
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    private EffectJSON convertXML2JSON(HabboAssetSWF habboAssetSWF) throws Exception {
        ManifestXML manifestXML = this.getManifestXML(habboAssetSWF);
        AnimationXML animationXML = this.getAnimationXML(habboAssetSWF);

        return effectJSONMapper.mapXML(habboAssetSWF, manifestXML, animationXML);
    }

    @Override
    public void fromHabboAsset(HabboAssetSWF habboAssetSWF, File outputFolder, String type) throws Exception {
        EffectJSON effectJSON = this.convertXML2JSON(habboAssetSWF);
        ObjectMapper objectMapper = new ObjectMapper();
        String spritesheetString = this.getObjectMapper().writeValueAsString(SpritesheetConverter.spritesheetJSON);
        JsonNode jsonNode = objectMapper.readTree(spritesheetString);

        effectJSON.setSpritesheet(jsonNode);
        SpritesheetConverter.spritesheetJSON = new SpritesheetJSON();
        //effectJSON.setSpritesheet(habboAssetSWF.getDocumentClass() + "_spritesheet.json");

        this.getObjectMapper().writeValue(new File(outputFolder + "/" + habboAssetSWF.getDocumentClass() + ".json"), effectJSON);
    }
}

