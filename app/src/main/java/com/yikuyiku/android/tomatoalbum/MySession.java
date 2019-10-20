package com.yikuyiku.android.tomatoalbum;


import java.util.ArrayList;
import java.util.HashMap;

public final class MySession {
    static private ArrayList<MyItem> systemImages;

    static {
        systemImages = new ArrayList<>();
    }

    static public void setSystemImages(ArrayList<String> systemImageUris) {
        for (String imageUri: systemImageUris ) {
            systemImages.add(
                    new MyItem("image", imageUri, false)
            );
        }
    }

    static public ArrayList<MyItem> getSystemImages() {
        return systemImages;
    }
}
