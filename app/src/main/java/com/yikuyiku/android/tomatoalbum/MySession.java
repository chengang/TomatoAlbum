package com.yikuyiku.android.tomatoalbum;


import java.util.ArrayList;
import java.util.HashMap;

public final class MySession {
    static private HashMap<String, ArrayList<MyItem>> mediaLibrary;

    static private String currentTab;
    static private int itemPosition;

    static {
        mediaLibrary = new HashMap<>();
        mediaLibrary.put("Image", new ArrayList<MyItem>());
        mediaLibrary.put("Video", new ArrayList<MyItem>());
        mediaLibrary.put("Audio", new ArrayList<MyItem>());
        currentTab = "Images";
    }

    static public void initMediaLibrary() {
        refreshLibrary("Image");
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

    static public void setItemPosition(int position) { itemPosition = position; }
    static public int getItemPosition() { return itemPosition; }
}
