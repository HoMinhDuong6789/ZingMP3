package com.example.zingmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.zingmp3.Model.BaiHat;
import com.example.zingmp3.R;

import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar skttime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgradom;
    ViewPager viewpagerplaynhac;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        init();
        Intent intent = getIntent();
        if(intent.hasExtra("cakhuc")){
            BaiHat baihat = intent.getParcelableExtra("cakhuc");
            //Toast.makeText(this, baihat.getTenbaihat(), Toast.LENGTH_SHORT).show();

        }

        if(intent.hasExtra("cacbaihat")){
            ArrayList<BaiHat> mangbaihat =intent.getParcelableArrayListExtra("cacbaihat");
            for(int i=0; i<mangbaihat.size();i++ ){
                Log.d("BBB", mangbaihat.get(i).getTenbaihat());

            }
        }
    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
        skttime =findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebtnplay);
        imgrepeat= findViewById(R.id.imagebtnrepeat);
        imgnext = findViewById(R.id.imagebtnnext);
        imgpre = findViewById(R.id.imagebtnpre);
        imgradom = findViewById(R.id.imagebtnsuffle);
        viewpagerplaynhac =findViewById(R.id.viewpapagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar() .setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbarplaynhac.setTitleTextColor(Color.WHITE);


    }
}
