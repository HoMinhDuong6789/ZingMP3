package com.example.zingmp3.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zingmp3.R;

public class Fragment_TrangChu  extends Fragment {
    View view; // dung de gan layout vao phan fragment, va co the lay layout nay tuong tac voi fragment luon

    @Nullable
    @Override
    // ham nay co chuc nang gan view cho fragment lam
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        return view;

    }
}
