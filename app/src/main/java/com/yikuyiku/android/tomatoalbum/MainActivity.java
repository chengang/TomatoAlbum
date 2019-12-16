package com.yikuyiku.android.tomatoalbum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 21387;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (
                    // PERMISSION NOT GRANTED
                       (grantResults.length != 1)
                       || (grantResults[0] != PackageManager.PERMISSION_GRANTED)
                ) {
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

        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(new ViewPager2Adapter());

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int pos) {
                tab.setText(STATIC.tabNames[pos]);
            }
        });
        tabLayoutMediator.attach();
        MySession.setSystemImages(MediaSearcher.getAllImages());
    }
}
