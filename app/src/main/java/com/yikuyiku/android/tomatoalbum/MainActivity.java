package com.yikuyiku.android.tomatoalbum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeContainer;
    RecyclerView recyclerView;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(stringList);
        recyclerView.setAdapter(mainRecyclerAdapter);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadImages();
            }
        });
    }

    private void loadImages() {
        stringList.add("image1");
        stringList.add("image2");
        stringList.add("pic1");
        stringList.add("pic2");
        stringList.add("photo1");
        stringList.add("photo2");
        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(stringList);
        recyclerView.setAdapter(mainRecyclerAdapter);
        Toast.makeText(getApplicationContext(),"Loaded Images", Toast.LENGTH_LONG).show();
        swipeContainer.setRefreshing(false);
    }
}
