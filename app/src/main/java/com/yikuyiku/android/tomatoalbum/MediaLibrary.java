package com.yikuyiku.android.tomatoalbum;


import java.util.ArrayList;
import java.util.HashMap;

public final class MediaLibrary {
    static private HashMap<String, ArrayList<MyItem>> mediaLibrary;

    static {
        mediaLibrary = new HashMap<>();
        mediaLibrary.put("Image", new ArrayList<MyItem>());
        mediaLibrary.put("Video", new ArrayList<MyItem>());
        mediaLibrary.put("Audio", new ArrayList<MyItem>());
    }

    static public void initMediaLibrary() {
        refreshLibrary("Image");
        refreshLibrary("Video");
        refreshLibrary("Audio");
    }

    static public void refreshLibrary(String mediaType) {
        ArrayList<String> mediaPaths = new ArrayList<>();
        switch (mediaType) {
            case "Image":
                mediaPaths = MediaSearcher.getAllImages();
                break;
            case "Video":
                mediaPaths = MediaSearcher.getAllVideos();
                break;
            case "Audio":
                mediaPaths = MediaSearcher.getAllAudios();
                break;
            default:
                break;
        }

        for (String path: mediaPaths ) {
            mediaLibrary.get(mediaType).add(
                    new MyItem(mediaType, path, false)
            );
        }
    }

    static public ArrayList<MyItem> getSystemImages() {
        return mediaLibrary.get("Image");
    }
}
