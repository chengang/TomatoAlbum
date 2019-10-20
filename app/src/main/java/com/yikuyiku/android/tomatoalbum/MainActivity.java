package com.yikuyiku.android.tomatoalbum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 21387;

    SwipeRefreshLayout swipeContainer;
    MainRecyclerAdapter mainRecyclerAdapter;
    StaggeredGridLayoutManager layoutManager;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (
                        (grantResults.length == 1)
                                && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // PERMISSION GRANTED
                } else {
                    finish();
                }
                break;
            default:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( PackageManager.PERMISSION_GRANTED !=
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        ) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new StaggeredGridLayoutManager(APPCONF.getGridSpanCount(), StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                layoutManager.invalidateSpanAssignments();
//            }
//        });
        mainRecyclerAdapter = new MainRecyclerAdapter();
        MySession.setSystemImages(MediaSearcher.getAllImages());
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
        MySession.setSystemImages(MediaSearcher.getAllImages());
        mainRecyclerAdapter.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(),"Loaded Images", Toast.LENGTH_LONG).show();
        swipeContainer.setRefreshing(false);
    }
}
