package com.example.zingmp3.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {

@SerializedName("idplaylist")
@Expose
private String idplaylist;

@SerializedName("ten")
@Expose
private String ten;


@SerializedName("hinhanh")
@Expose
private String hinhanh;


@SerializedName("hinhicon")
@Expose
private String hinhicon;

public String getIdplaylist() {
return idplaylist;
}

public void setIdplaylist(String idplaylist) {
this.idplaylist = idplaylist;
}

public String getTen() {
return ten;
}

public void setTen(String ten) {
this.ten = ten;
}

public String getHinhanh() {
return hinhanh;
}

public void setHinhanh(String hinhanh) {
this.hinhanh = hinhanh;
}

public String getHinhicon() {
return hinhicon;
}

public void setHinhicon(String hinhicon) {
this.hinhicon = hinhicon;
}

}