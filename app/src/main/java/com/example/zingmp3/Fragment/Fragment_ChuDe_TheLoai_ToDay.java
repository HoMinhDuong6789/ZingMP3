package com.example.zingmp3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.zingmp3.Activity.DanhsachbaihatActivity;
import com.example.zingmp3.Activity.DanhsachtatcachudeActivity;
import com.example.zingmp3.Activity.DanhsachtheloaitheochudeActivity;
import com.example.zingmp3.Model.ChuDe;
import com.example.zingmp3.Model.ChuDeTheLoaiTrongNgay;
import com.example.zingmp3.Model.TheLoai;
import com.example.zingmp3.R;
import com.example.zingmp3.Service.APIService;
import com.example.zingmp3.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchidetheloai;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtxemthemchidetheloai = view.findViewById(R.id.textviewxemthem);
        txtxemthemchidetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        getData();
        return view;
    }

    private void getData() {
        DataService dataService= APIService.getService();
            Call<ChuDeTheLoaiTrongNgay> callback= dataService.GetCategoryMusic();
            callback.enqueue(new Callback<ChuDeTheLoaiTrongNgay>() {
                @Override
                public void onResponse(Call<ChuDeTheLoaiTrongNgay> call, Response<ChuDeTheLoaiTrongNgay> response) {
                    ChuDeTheLoaiTrongNgay chuDeTheLoaiTrongNgay= response.body();

                    final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                    chuDeArrayList.addAll(chuDeTheLoaiTrongNgay.getChuDe());

                    final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                    theLoaiArrayList.addAll(chuDeTheLoaiTrongNgay.getTheLoai());

                    LinearLayout linearLayout = new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 250);
                    layout.setMargins(10, 20, 10, 30);
                    // hinh anh cua chu de
                    for (int i=0; i< (chuDeArrayList.size()); i++){
                        CardView cardView = new CardView(getActivity());
                        cardView.setRadius(10);
                        ImageView imageView = new ImageView(getActivity());
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        if (chuDeArrayList.get(i).getHinhchude()!=null){
                            Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhchude()).into(imageView);

                        }
                        cardView.setLayoutParams(layout);
                        cardView.addView(imageView);
                        linearLayout.addView(cardView);
                        final int finalI = i;
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                                intent.putExtra("chude", chuDeArrayList.get(finalI));
                                startActivity(intent);
                            }
                        });
                    }

                    //hinh anh the loai
                    for (int i=0; i< (chuDeArrayList.size()); i++){
                        CardView cardView = new CardView(getActivity());
                        cardView.setRadius(10);
                        ImageView imageView = new ImageView(getActivity());
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        if (theLoaiArrayList.get(i).getHinhtheloai()!=null){
                            Picasso.with(getActivity()).load(theLoaiArrayList.get(i).getHinhtheloai()).into(imageView);
                        }
                        cardView.setLayoutParams(layout);
                        cardView.addView(imageView);
                        linearLayout.addView(cardView);
                        // ta co the bat su kien tren cardview or imageview deu duoc
                        // thang i dang nam trong function trong the truy xuat duoc trong function bat su kien nen tao ra bien
                       /* final int finalI = i;
                        cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                                intent.putExtra("idtheloai", theLoaiArrayList.get(finalI).getIdtheloai());
                                startActivity(intent);
                            }
                        });*/
                    }
                    horizontalScrollView.addView(linearLayout);
                }

                @Override
                public void onFailure(Call<ChuDeTheLoaiTrongNgay> call, Throwable t) {

                }
            });

    }
}
