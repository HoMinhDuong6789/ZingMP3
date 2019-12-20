package com.example.zingmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.zingmp3.Adapter.AllAlbumAdapter;
import com.example.zingmp3.Model.Album;
import com.example.zingmp3.R;
import com.example.zingmp3.Service.APIService;
import com.example.zingmp3.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaAlbumActivity extends AppCompatActivity {

    RecyclerView recyclerViewAllAlbum;
    Toolbar toolbaralbum;
    AllAlbumAdapter allAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatca_album);
        anhxa();
        GetData();
    }

    private void GetData() {
        DataService dataService= APIService.getService();
        Call<List<Album>> callback= dataService.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangalbum= (ArrayList<Album>) response.body();
                //Log.d("BBBB", mangalbum.get(0).getTenalbum());
                allAlbumAdapter = new AllAlbumAdapter(DanhsachtatcaAlbumActivity.this, mangalbum);
                recyclerViewAllAlbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaAlbumActivity.this, 2));
                recyclerViewAllAlbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        recyclerViewAllAlbum = findViewById(R.id.recyclerviewAllalbum);
        toolbaralbum = findViewById(R.id.toolbarAllalbum);
        setSupportActionBar(toolbaralbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbaralbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
