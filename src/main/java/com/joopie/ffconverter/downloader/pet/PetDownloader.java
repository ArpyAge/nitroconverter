package com.joopie.ffconverter.downloader.pet;

import com.joopie.ffconverter.FFConverter;
import com.joopie.ffconverter.HabboAssetSWF;
import com.joopie.ffconverter.downloader.IDownloader;
import com.joopie.ffconverter.downloader.pet.resources.ExternalVars;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class PetDownloader implements IDownloader {
    public static class PetRunnableDownloader implements Runnable{

        private int revision;
        private String className;
        private Consumer<HabboAssetSWF> callback;

        public PetRunnableDownloader(Consumer<HabboAssetSWF> callback, String className) {
            this.callback = callback;
            this.className = className;
        }

        @Override
        public void run() {
            try {
                URL swfURL = new URL(FFConverter.getConfig().getValue("dynamic.download.url.pet").replace("%className%", this.className));
                HabboAssetSWF habboAssetSWF = new HabboAssetSWF(swfURL.openStream());
                this.callback.accept(habboAssetSWF);
                //habboAssetSWF.dispose();
                habboAssetSWF = null;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Consumer<HabboAssetSWF> callback;

    public PetDownloader(Consumer<HabboAssetSWF> callback) {
        this.callback = callback;
    }

    //https://images.habbo.com/dcr/hof_furni/45508/ads_calip_cola.swf

    /*@Override
    public void prepareDownloader(List<Callable<Object>> downloadTasks) throws Exception {
        downloadTasks.add(Executors.callable(new FurnitureRunnableDownloader(this.callback, 45508, "ads_calip_cola")));
    }*/

    @Override
    public void prepareDownloader(List<Callable<Object>> downloadTasks) throws Exception {
        File outputFolderPet = new File(FFConverter.getConfig().getValue("output.folder.pet"));
        ExternalVars externalVars = new ExternalVars();
        String pets = externalVars.getValue("pet.configuration");
        if (!pets.equals("")) {
            List<String> itemClassNames = new ArrayList<>();
            String[] petNames = pets.split(",");

            for (String pet : petNames) {
                File assetOuputFolder = new File(outputFolderPet + "/" + pet);
                if (assetOuputFolder.isDirectory()) {
                    continue;
                }
                if (!itemClassNames.contains(pet)) {
                    downloadTasks.add(Executors.callable(new PetDownloader.PetRunnableDownloader(this.callback, pet)));
                }

                itemClassNames.add(pet);
            }
        }
    }
}