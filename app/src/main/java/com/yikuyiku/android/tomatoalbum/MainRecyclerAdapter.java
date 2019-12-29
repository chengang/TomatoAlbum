package com.yikuyiku.android.tomatoalbum;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private Context context;
    private String mediaType;

    public MainRecyclerAdapter(String mediaType) {
        this.mediaType = mediaType;
    }

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
                switch (mediaType) {
                    case "Image":
                        Intent intent = new Intent(context, ImageFullscreenActivity.class);
                        intent.putExtra("mediaType", "Image");
                        intent.putExtra("pos", holder.getAdapterPosition());
                        context.startActivity(intent);
                        break;
                    case "Video":
                        Log.d("123223", "Video: "+holder.getAdapterPosition());
                        break;
                    case "audio":
                        Log.d("123223", "Audio: "+holder.getAdapterPosition());
                        break;
                    default:
                        break;
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int pos = holder.getAdapterPosition();
                if (holder.checkBox.getVisibility() != View.VISIBLE) {
                    holder.checkBox.setVisibility(View.VISIBLE);
                    MediaLibrary.getItems(mediaType).get(pos).setSelected(true);
                } else {
                    holder.checkBox.setVisibility(View.INVISIBLE);
                    MediaLibrary.getItems(mediaType).get(pos).setSelected(false);
                }
                return true; // shake or not, eat the long touch event or not
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        String imgUri = MediaLibrary.getItems(this.mediaType).get(position).getUri();
        Glide.with(context).load(imgUri).listener(new RequestListener<Drawable>() {
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
        return MediaLibrary.getItems(this.mediaType).size();
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
