package com.example.zingmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zingmp3.Adapter.DanhsachbaihatAdapter;
import com.example.zingmp3.Model.Album;
import com.example.zingmp3.Model.BaiHat;
import com.example.zingmp3.Model.Playlist;
import com.example.zingmp3.Model.QuangCao;
import com.example.zingmp3.Model.TheLoai;
import com.example.zingmp3.R;
import com.example.zingmp3.Service.APIService;
import com.example.zingmp3.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class DanhsachbaihatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imgdanhsachcakhuc;
    ArrayList<BaiHat> mangbaihat;
    QuangCao quangCao;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    Playlist playlist;
    TheLoai theLoai;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);

        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        DataIntent();
        anhxa();
        init();
        if(quangCao !=null && !quangCao.getTenbaihat().equals("")){
            setValueInView(quangCao.getTenbaihat(), quangCao.getHinhbaihat());
            getDataQuangCao(quangCao.getId());
        }

        if(playlist != null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(), playlist.getHinhanh());
            getDataPlayList(playlist.getIdplaylist());
        }


        if(theLoai != null && !theLoai.getTentheloai().equals("")){
            setValueInView(theLoai.getTentheloai(), theLoai.getHinhtheloai());
            getDataTheLoai(theLoai.getIdtheloai());
        }

        if(album != null && !album.getTenalbum().equals("")){
            setValueInView(album.getTenalbum(), album.getHinhalbum());
            getDataAlbum(album.getIdalbum());
        }

    }

    private void getDataAlbum(String idalbum) {
          DataService dataService= APIService.getService();
          Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheoalbum(idalbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                //Log.d("BBBBBBBBBBBB", mangbaihat.get(0).getIdtheloai());
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this
                        , mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void getDataTheLoai(String idtheloai) {
        DataService dataService= APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                Log.d("BBBBBBBBBBBB", mangbaihat.get(0).getIdtheloai());
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this
                        , mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlayList(String idplaylist) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                //Log.d("BBBBBBBBBBBB", mangbaihat.get(0).getTenbaihat());
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this
                        , mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();


            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void getDataQuangCao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                //Log.d("BBBBBBBBBBBB", mangbaihat.get(0).getTenbaihat());
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this
                , mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try{
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsachbaihat = findViewById(R.id.recycleviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
            }
            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }

            if(intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }

            if(intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
                Toast.makeText(this, album.getTenalbum(), Toast.LENGTH_SHORT).show();
            }

        }

    }
    private  void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", mangbaihat);
                startActivity(intent);
            }
        });
    }
}
