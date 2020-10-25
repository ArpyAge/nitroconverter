package com.joopie.ffconverter.converters.furniture;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joopie.ffconverter.HabboAssetSWF;
import com.joopie.ffconverter.converters.IConverter;
import com.joopie.ffconverter.converters.furniture.models.*;
import com.joopie.ffconverter.converters.util.spritesheet.SpritesheetConverter;
import com.joopie.ffconverter.converters.util.spritesheet.models.SpritesheetJSON;
import com.jpexs.decompiler.flash.tags.DefineBinaryDataTag;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * Created by jospi on 22-4-2017.
 */
public class FurnitureConverter implements IConverter {
    private static final FurnitureJSONMapper furnitureJSONMapper = new FurnitureJSONMapper();

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

    private AssetsXML getAssetsXML(HabboAssetSWF habboAssetSWF) throws Exception {
        byte[] binaryData = this.getBinaryData(habboAssetSWF, "assets", true);
        InputStream inputStream = new ByteArrayInputStream(binaryData);
        return (AssetsXML) this.getUnmashaller(AssetsXML.class).unmarshal(inputStream);
    }

    private LogicXML getLogicXML(HabboAssetSWF habboAssetSWF) throws Exception {
        byte[] binaryData = this.getBinaryData(habboAssetSWF, "logic", true);
        InputStream inputStream = new ByteArrayInputStream(binaryData);
        return (LogicXML) this.getUnmashaller(LogicXML.class).unmarshal(inputStream);
    }

    private IndexXML getIndexXML(HabboAssetSWF habboAssetSWF) throws Exception {
        byte[] binaryData = this.getBinaryData(habboAssetSWF, "index", false);
        InputStream inputStream = new ByteArrayInputStream(binaryData);
        return (IndexXML) this.getUnmashaller(IndexXML.class).unmarshal(inputStream);
    }

    private ManifestXML getManifestXML(HabboAssetSWF habboAssetSWF) throws Exception {
        byte[] binaryData = this.getBinaryData(habboAssetSWF, "manifest", false);
        InputStream inputStream = new ByteArrayInputStream(binaryData);
        return (ManifestXML) this.getUnmashaller(ManifestXML.class).unmarshal(inputStream);
    }

    private VisualizationXML getVisualizationXML(HabboAssetSWF habboAssetSWF) throws Exception {
        byte[] binaryData = this.getBinaryData(habboAssetSWF, "visualization", true);
        InputStream inputStream = new ByteArrayInputStream(binaryData);
        return (VisualizationXML) this.getUnmashaller(VisualizationXML.class).unmarshal(inputStream);
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    private FurnitureJSON convertXML2JSON(HabboAssetSWF habboAssetSWF) throws Exception {
        AssetsXML assetsXML = this.getAssetsXML(habboAssetSWF);
        LogicXML logicXML = this.getLogicXML(habboAssetSWF);
        IndexXML indexXML = this.getIndexXML(habboAssetSWF);
        ManifestXML manifestXML = this.getManifestXML(habboAssetSWF);
        VisualizationXML visualizationXML = this.getVisualizationXML(habboAssetSWF);

        return furnitureJSONMapper.mapXML(assetsXML, indexXML, logicXML, visualizationXML);
    }

    @Override
    public void fromHabboAsset(HabboAssetSWF habboAssetSWF, File outputFolder, String type) throws Exception {
        FurnitureJSON furnitureJSON = this.convertXML2JSON(habboAssetSWF);
        ObjectMapper objectMapper = new ObjectMapper();
        String spritesheetString = this.getObjectMapper().writeValueAsString(SpritesheetConverter.spritesheetJSON);
        JsonNode jsonNode = objectMapper.readTree(spritesheetString);

        furnitureJSON.setSpritesheet(jsonNode);
        SpritesheetConverter.spritesheetJSON = new SpritesheetJSON();
        //furnitureJSON.setSpritesheet(habboAssetSWF.getDocumentClass() + "_spritesheet.json");

        this.getObjectMapper().writeValue(new File(outputFolder + "/" + habboAssetSWF.getDocumentClass() + ".json"), furnitureJSON);
    }
}
