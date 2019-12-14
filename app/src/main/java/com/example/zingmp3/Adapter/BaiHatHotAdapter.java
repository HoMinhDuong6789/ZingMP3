package com.example.zingmp3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.Model.BaiHat;
import com.example.zingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //  dung de gan layout, va xac dinh nhung view se xuat hien tren layout nay
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihathot, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // dung de gan du lieu vao
        BaiHat baiHat= baiHatArrayList.get(position);
        holder.txtCasi.setText(baiHat.getCasi());
        holder.txtTen.setText(baiHat.getTenbaihat());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(holder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtTen, txtCasi;
            ImageView imgHinh, imgLuotthich;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.textviewtenbaihathot);
            txtCasi= itemView.findViewById(R.id.textviewcasibaihathot);
            imgHinh=itemView.findViewById(R.id.imageviewbaihathot);
            imgLuotthich = itemView.findViewById(R.id.imageviewluotthich);

        }
    }
}
