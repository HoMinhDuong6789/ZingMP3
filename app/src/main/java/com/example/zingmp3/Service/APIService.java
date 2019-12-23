package com.example.zingmp3.Service;

public class APIService {

    private static String base_url= "https://vayngaychochi.000webhostapp.com/server/";

    public static  DataService getService(){

        return  APIRetrofitClient.getClient(base_url).create(DataService.class);
        //Lỗi chi đâu Dương
        //click cai nu hen chua bắt đc, với lại  như vậy đó?
        //Ời để t fix cái lỗi crash trước hì
    }
}
