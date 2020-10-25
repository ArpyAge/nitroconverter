package com.joopie.ffconverter.downloader.figure;

import com.joopie.ffconverter.FFConverter;
import com.joopie.ffconverter.HabboAssetSWF;
import com.joopie.ffconverter.downloader.IDownloader;
import com.joopie.ffconverter.downloader.figure.resources.Figuremap;
import com.joopie.ffconverter.downloader.figure.resources.FiguremapParser;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class FigureDownloader implements IDownloader {
    public static class FigureRunnableDownloader implements Runnable{

        private String className;
        private Consumer<HabboAssetSWF> callback;

        public FigureRunnableDownloader(Consumer<HabboAssetSWF> callback, String className) {
            this.callback = callback;
            this.className = className;
        }

        @Override
        public void run() {
            try {
                URL swfURL = new URL(FFConverter.getConfig().getValue("dynamic.download.url.figure").replace("%className%", this.className));
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

    public FigureDownloader(Consumer<HabboAssetSWF> callback) {
        this.callback = callback;
    }

    @Override
    public void prepareDownloader(List<Callable<Object>> downloadTasks) throws Exception {
        Figuremap figuremap = FiguremapParser.getFiguremap();
        if (figuremap != null) {
            List<String> itemClassNames = new ArrayList<>();

            for (Figuremap.Lib lib : figuremap.getLib()) {
                String className = lib.getId().split("\\*")[0];
                if (!types.containsKey(className)) {
                    downloadTasks.add(Executors.callable(new FigureDownloader.FigureRunnableDownloader(this.callback, className)));
                }

                types.put(className, lib.getPart().get(0).getType());
            }
        }
    }
}
