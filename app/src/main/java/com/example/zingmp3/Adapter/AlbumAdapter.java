package com.example.zingmp3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.Model.Album;
import com.example.zingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> mangalbum;

    public AlbumAdapter(Context context, ArrayList<Album> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album= mangalbum.get(position);
        holder.txttencasialbum.setText(album.getTencasi());
        holder.txttenalbum.setText(album.getTenalbum());
        Picasso.with(context).load(album.getHinhalbum()).into(holder.imghinhalbum);

    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
         ImageView imghinhalbum;
         TextView txttenalbum, txttencasialbum;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             imghinhalbum = itemView.findViewById(R.id.imageviewalbum);
             txttenalbum = itemView.findViewById(R.id.textviewtenalbum);
             txttencasialbum = itemView.findViewById(R.id.textviewtencasialbum);



         }


     }



}
