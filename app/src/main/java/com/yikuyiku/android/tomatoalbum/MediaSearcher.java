package com.yikuyiku.android.tomatoalbum;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

public class MediaSearcher {
    public void getAllImages() {
        Cursor cursor = MyApplication.globalContentResolver().query(
                MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null,
                null
                );
        int ImageCount = cursor.getCount();
        Log.d("123223", String.valueOf(ImageCount));
    }
}
