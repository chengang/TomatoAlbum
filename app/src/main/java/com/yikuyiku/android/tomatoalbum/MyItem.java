package com.yikuyiku.android.tomatoalbum;

public class MyItem {
    private String type;
    private String uri;
    private boolean isSelected;

    MyItem(String itemType, String itemUri, boolean itemIsSelected) {
        type = itemType;
        uri = itemUri;
        isSelected = itemIsSelected;
    }

    public String getType() {
        return type;
    }

    public String getUri() {
        return uri;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean itemIsSelected) {
        isSelected = itemIsSelected;
    }
}
