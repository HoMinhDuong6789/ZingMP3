package com.example.zingmp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.Activity.PlayNhacActivity;
import com.example.zingmp3.Model.BaiHat;
import com.example.zingmp3.R;
import com.example.zingmp3.Service.APIService;
import com.example.zingmp3.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihathot;

    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> mangbaihathot) {
        this.context = context;
        this.mangbaihathot = mangbaihathot;
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
        BaiHat baiHat= mangbaihathot.get(position);
        holder.txtCasi.setText(baiHat.getCasi());
        holder.txtTen.setText(baiHat.getTenbaihat());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(holder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return mangbaihathot.size();
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



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", mangbaihathot.get(getPosition()));
                    context.startActivity(intent);
                }
            });


            imgLuotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(context,mangbaihathot.get(getPosition()).getLuotthich(), Toast.LENGTH_SHORT).show();
                    imgLuotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.Updateluotthich(mangbaihathot.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("succesful")){
                                Toast.makeText(context, "Da thich", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLuotthich.setEnabled(false);
                }
            });
        }
    }
}
