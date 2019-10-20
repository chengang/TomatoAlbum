package com.yikuyiku.android.tomatoalbum;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MediaSearcher {
    static public ArrayList<String> getAllImages() {
        Cursor cursor = MyApplication.globalContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Images.Media._ID + " DESC",
                null
                );
        cursor.moveToFirst();

        ArrayList<String> imagePathList = new ArrayList<>();
        int indexImagePath = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        while(cursor.moveToNext()) {
            imagePathList.add(cursor.getString(indexImagePath));
        }
        cursor.close();
        return imagePathList;
    }
}
