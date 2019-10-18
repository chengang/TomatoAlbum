package com.yikuyiku.android.tomatoalbum;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private List<String> mediaUriList;
    private Context context;

    public MainRecyclerAdapter() {}

    public void setDataSet(List<String> allMediaUriList) { mediaUriList = allMediaUriList; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.height = APPCONF.getGridItemHeightDefault();
        holder.imageView.setLayoutParams(params);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                String imageUri = mediaUriList.get(pos);
                //holder.itemView.setSelected(true);
                Toast.makeText(view.getContext(), imageUri, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int pos = holder.getAdapterPosition();
                if (holder.checkBox.getVisibility() != View.VISIBLE) {
                    holder.checkBox.setVisibility(View.VISIBLE);
                } else {
                    holder.checkBox.setVisibility(View.INVISIBLE);
                }
                return true; // shake or not, eat the long touch event or not
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        String imgurl = mediaUriList.get(position);
        Glide.with(context).load(imgurl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
                params.height = (int) (APPCONF.getGridItemWidth() / ((float) resource.getIntrinsicWidth() / (float)  resource.getIntrinsicHeight()));
                holder.imageView.setLayoutParams(params);
                return false;
            }
        }).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mediaUriList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.recycler_item_image);
            checkBox = (CheckBox) view.findViewById(R.id.recycler_item_checkbox);
        }
    }
}
