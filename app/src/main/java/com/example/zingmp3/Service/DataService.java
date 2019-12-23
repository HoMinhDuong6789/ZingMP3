package com.example.zingmp3.Service;

import com.example.zingmp3.Model.Album;
import com.example.zingmp3.Model.BaiHat;
import com.example.zingmp3.Model.ChuDe;
import com.example.zingmp3.Model.ChuDeTheLoaiTrongNgay;
import com.example.zingmp3.Model.Playlist;
import com.example.zingmp3.Model.QuangCao;
import com.example.zingmp3.Model.TheLoai;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    //API danhsachcacplaylist
    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>>GetDanhsachcacPlayList();


    //API danhsachcacchude
    @GET("danhsachcacchude.php")
    Call<List<ChuDe>>GetAllChude();


    @GET("tatcaalbum.php")
    Call<List<Album>>GetAllAlbum();



    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);
    //anitation dung de gan id quangcao khi nguoi dung clidk vao de thuc thuc truy van

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);
    //anitation dung de gan id playlist khi nguoi dung clidk vao de thuc thuc truy van

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoalbum(@Field("idalbum") String idalbum);

    //post idbaihat de tang like cho bai hat
    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> Updateluotthich(@Field("idbaihat") String idbaihat);



    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheloaitheochude(@Field("idchude") String idchude);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> GetSearchbaihat(@Field("tukhoa") String tukhoa);
}
