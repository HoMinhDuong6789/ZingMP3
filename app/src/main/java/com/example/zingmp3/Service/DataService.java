package com.example.zingmp3.Service;

import com.example.zingmp3.Model.Album;
import com.example.zingmp3.Model.BaiHat;
import com.example.zingmp3.Model.ChuDeTheLoaiTrongNgay;
import com.example.zingmp3.Model.Playlist;
import com.example.zingmp3.Model.QuangCao;
import com.example.zingmp3.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
// interface dung de gui len nhung phuong thuc tuong tac voi phia server,
    // va sau khi server ket noi roi thi no se tra du lieu ve cho thang nay


    @GET("songbanner.php")
    Call<List<QuangCao>>GetDataBanner();


    @GET("playlistforcurrentday.php")
    Call<List<Playlist>>GetPlayListCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<ChuDeTheLoaiTrongNgay>GetCategoryMusic();

    @GET("album.php")
    Call<List<Album>>  GetAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

}
