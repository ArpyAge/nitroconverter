package com.joopie.ffconverter.downloader.effect;

import com.joopie.ffconverter.FFConverter;
import com.joopie.ffconverter.HabboAssetSWF;
import com.joopie.ffconverter.downloader.IDownloader;
import com.joopie.ffconverter.downloader.effect.resources.Effectmap;
import com.joopie.ffconverter.downloader.effect.resources.EffectmapParser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class EffectDownloader  implements IDownloader {
    public static class EffectRunnableDownloader implements Runnable{

        private String className;
        private Consumer<HabboAssetSWF> callback;

        public EffectRunnableDownloader(Consumer<HabboAssetSWF> callback, String className) {
            this.callback = callback;
            this.className = className;
        }

        @Override
        public void run() {
            try {
                URL swfURL = new URL(FFConverter.getConfig().getValue("dynamic.download.url.effect").replace("%className%", this.className));
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

    public static Map<String, String> types = new HashMap<String, String>();

    private Consumer<HabboAssetSWF> callback;

    public EffectDownloader(Consumer<HabboAssetSWF> callback) {
        this.callback = callback;
    }

    @Override
    public void prepareDownloader(List<Callable<Object>> downloadTasks) throws Exception {
        File outputFolderEffect = new File(FFConverter.getConfig().getValue("output.folder.effect"));
        Effectmap effectmap = EffectmapParser.getEffectmap();
        if (effectmap != null) {
            for (Effectmap.Effect effect : effectmap.getEffect()) {
                String className = effect.getLib();
                File assetOuputFolder = new File(outputFolderEffect + "/" + className);
                if (assetOuputFolder.isDirectory()) {
                    continue;
                }
                if (!types.containsKey(className)) {
                    downloadTasks.add(Executors.callable(new EffectDownloader.EffectRunnableDownloader(this.callback, className)));
                }

                types.put(className, effect.getType());
            }
        }
    }
}
