package com.example.zingmp3.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHat {

@SerializedName("idbaihat")
@Expose
private String idbaihat;
@SerializedName("idalbum")
@Expose
private String idalbum;
@SerializedName("idtheloai")
@Expose
private String idtheloai;
@SerializedName("idplaylist")
@Expose
private String idplaylist;
@SerializedName("tenbaihat")
@Expose
private String tenbaihat;
@SerializedName("hinhbaihat")
@Expose
private String hinhbaihat;
@SerializedName("casi")
@Expose
private String casi;
@SerializedName("linkbaihat")
@Expose
private String linkbaihat;
@SerializedName("luotthich")
@Expose
private String luotthich;

public String getIdbaihat() {
return idbaihat;
}

public void setIdbaihat(String idbaihat) {
this.idbaihat = idbaihat;
}

public String getIdalbum() {
return idalbum;
}

public void setIdalbum(String idalbum) {
this.idalbum = idalbum;
}

public String getIdtheloai() {
return idtheloai;
}

public void setIdtheloai(String idtheloai) {
this.idtheloai = idtheloai;
}

public String getIdplaylist() {
return idplaylist;
}

public void setIdplaylist(String idplaylist) {
this.idplaylist = idplaylist;
}

public String getTenbaihat() {
return tenbaihat;
}

public void setTenbaihat(String tenbaihat) {
this.tenbaihat = tenbaihat;
}

public String getHinhbaihat() {
return hinhbaihat;
}

public void setHinhbaihat(String hinhbaihat) {
this.hinhbaihat = hinhbaihat;
}

public String getCasi() {
return casi;
}

public void setCasi(String casi) {
this.casi = casi;
}

public String getLinkbaihat() {
return linkbaihat;
}

public void setLinkbaihat(String linkbaihat) {
this.linkbaihat = linkbaihat;
}

public String getLuotthich() {
return luotthich;
}

public void setLuotthich(String luotthich) {
this.luotthich = luotthich;
}

}