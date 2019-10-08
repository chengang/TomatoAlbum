package com.yikuyiku.android.tomatoalbum;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private List<String> stringList;
    private Context context;

    public MainRecyclerAdapter() {}

    public void setDataSet(List<String> stringList1) { stringList = stringList1; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ViewGroup.LayoutParams imageLayoutParams = holder.imageView.getLayoutParams();
        imageLayoutParams.height = 200;
        holder.imageView.setLayoutParams(imageLayoutParams);
        String imgurl = stringList.get(position);
        Glide.with(context).load(imgurl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (holder.imageView == null) {
                    return false;
                }
                ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
                Log.d("123(%d)", String.valueOf(holder.imageView.getWidth()));
                int vw = holder.imageView.getWidth() - holder.imageView.getPaddingLeft() - holder.imageView.getPaddingRight();
                int vh = (int) ((float)vw/(float) 1.78);
                params.height = vh + holder.imageView.getPaddingTop() + holder.imageView.getPaddingBottom();
                holder.imageView.setLayoutParams(params);
                return false;
            }
        }).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.recycler_item_image);
        }
    }
}
