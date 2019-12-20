package com.example.zingmp3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.Activity.DanhsachtatcaAlbumActivity;
import com.example.zingmp3.Adapter.AlbumAdapter;
import com.example.zingmp3.Model.Album;
import com.example.zingmp3.R;
import com.example.zingmp3.Service.APIService;
import com.example.zingmp3.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album extends Fragment {

    View view;
    RecyclerView recyclerViewAlbum;
    TextView txtxemthemalbum;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);
        recyclerViewAlbum = view.findViewById(R.id.recycleviewAlbum);
        txtxemthemalbum = view.findViewById(R.id.textviewxemthemAlbum);
        txtxemthemalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), DanhsachtatcaAlbumActivity.class);
                startActivity(intent);

            }
        });
        getData();
        return view;
    }

    private void getData() {
        DataService dataService= APIService.getService();
        Call<List<Album>> callback= dataService.GetAlbumHot();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArraylist = (ArrayList<Album>) response.body();
                albumAdapter= new AlbumAdapter(getActivity(), albumArraylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewAlbum.setLayoutManager(linearLayoutManager);
                recyclerViewAlbum.setAdapter(albumAdapter);

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });

    }
}
