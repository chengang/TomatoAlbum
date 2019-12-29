package com.yikuyiku.android.tomatoalbum;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullscreenFlipperAdapter extends BaseAdapter {
    private Context context;
    private String mediaType;

    public FullscreenFlipperAdapter(Context context, String mediaType) {
        this.context = context;
        this.mediaType = mediaType;
    }

    @Override
    public int getCount() {
        return MediaLibrary.getItems(this.mediaType).size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = null;
        if(null == view) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view = imageView;
        } else {
            imageView = (ImageView) view;
        }
        Glide.with(context).load(MediaLibrary.getItems(this.mediaType).get(i).getUri()).into(imageView);
        return imageView;
    }
}
