package com.yikuyiku.android.tomatoalbum;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MediaSearcher {
    static public ArrayList<String> getAllImages() {
        return getAll(MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                , MediaStore.Images.Media._ID + " DESC"
                , MediaStore.Images.Media.DATA);
    }

    static public ArrayList<String> getAllVideos() {
        return getAll(MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                , MediaStore.Video.Media._ID + " DESC"
                , MediaStore.Video.Media.DATA);
    }

    static public ArrayList<String> getAllAudios() {
        return getAll(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                , MediaStore.Audio.Media._ID + " DESC"
                , MediaStore.Audio.Media.DATA);
    }

    static private ArrayList<String> getAll(Uri project, String sortOrder, String columnName) {
        Cursor cursor = MyApplication.globalContentResolver().query(
                project,
                null,
                null,
                null,
                sortOrder,
                null
        );
        cursor.moveToFirst();

        ArrayList<String> pathList = new ArrayList<>();
        int indexImagePath = cursor.getColumnIndexOrThrow(columnName);
        while(cursor.moveToNext()) {
            pathList.add(cursor.getString(indexImagePath));
        }
        cursor.close();
        return pathList;
    }

}
