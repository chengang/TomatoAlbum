package com.yikuyiku.android.tomatoalbum;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_staggered_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(APPCONF.getGridSpanCount(), StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        holder.recyclerView.setLayoutManager(layoutManager);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(STATIC.tabNames[position]);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView textView;
        public ViewHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.recycler_view);
            textView = view.findViewById(R.id.test_text);
        }
    }
}
