package com.example.zingmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.zingmp3.Adapter.DanhsachcacchudeAdapter;
import com.example.zingmp3.Model.ChuDe;
import com.example.zingmp3.R;
import com.example.zingmp3.Service.APIService;
import com.example.zingmp3.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcachudeActivity extends AppCompatActivity {

    RecyclerView recyclerViewtatcachude;
    Toolbar toolbartatcachude;
    DanhsachcacchudeAdapter danhsachcacchudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcachude);

        GetData();
        anhxa();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.GetAllChude();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
                //Log.d("BBB", mangchude.get(0).getTenchude());
                danhsachcacchudeAdapter = new DanhsachcacchudeAdapter(DanhsachtatcachudeActivity.this, mangchude);
                recyclerViewtatcachude.setLayoutManager(new GridLayoutManager(DanhsachtatcachudeActivity.this, 1));
                recyclerViewtatcachude.setAdapter(danhsachcacchudeAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        recyclerViewtatcachude =findViewById(R.id.recycleviewAllChude);
        toolbartatcachude= findViewById(R.id.toolbarallchude);
        setSupportActionBar(toolbartatcachude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbartatcachude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
