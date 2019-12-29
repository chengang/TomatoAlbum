package com.yikuyiku.android.tomatoalbum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.recyclerView.setAdapter(new MainRecyclerAdapter("Image"));
        holder.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MediaLibrary.refreshLibrary("Image");
                holder.recyclerView.getAdapter().notifyDataSetChanged();
                holder.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return STATIC.tabNames.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        SwipeRefreshLayout swipeRefreshLayout;

        public ViewHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.recycler_view);
            swipeRefreshLayout = view.findViewById(R.id.swipeContainer);
        }
    }
}
