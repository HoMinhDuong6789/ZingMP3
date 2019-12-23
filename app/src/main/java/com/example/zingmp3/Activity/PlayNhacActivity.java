package com.example.zingmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.zingmp3.Adapter.ViewPagerPlaylistnhac;
import com.example.zingmp3.Fragment.Fragment_Dianhac;
import com.example.zingmp3.Fragment.Fragment_playdanhsachcacbaihat;
import com.example.zingmp3.Model.BaiHat;
import com.example.zingmp3.R;
import com.example.zingmp3.Service.MusicService;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.SimpleFormatter;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar skttime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgradom;
    ViewPager viewpagerplaynhac;
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistnhac adapternhac;
    Fragment_Dianhac fragment_dianhac;
    Fragment_playdanhsachcacbaihat fragment_playdanhsachcacbaihat;
    MediaPlayer mediaPlayer;

    //play notification
    private final int NOTIFICATION_ID = 001;
    private final String CHANNEL_ID = "TheNotification";
    private final String CHANNEL_NAME = "The Notification";
    private final String CHANNEL_DESC = "The Notification System for The App";
    String tenbaihat="";


    //
    int position =0;// vi tri cac ca khuc, de next, pre
    boolean repeat= false;
    boolean checkrandom= false;
    boolean next= false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1)!=null){
                    if(mangbaihat.size()>0){
                        fragment_dianhac.PlayNhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else{
                        handler.postDelayed(this, 300); //0.3s
                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                   mediaPlayer.pause();
                   imgplay.setImageResource(R.drawable.iconplay);
                   //MusicService.class
                }else{
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                    displayNotification();
                }
            }
        });




        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat ==false){
                    if(checkrandom==true){
                        checkrandom=false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgradom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else{
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgradom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkrandom ==false){
                    if(repeat==true){
                        repeat=false;
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                        imgradom.setImageResource(R.drawable.iconshuffled);
                    }
                    imgradom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else{
                    imgradom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });

        //seekbar su kien
        skttime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        //btn
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer!=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer =null;
                    }
                    if(position<(mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat==true){
                            if(position==0){
                                position= mangbaihat.size();

                            }
                            position-=1;
                        }
                        if(checkrandom==true){
                            Random random = new Random();
                            int index= random.nextInt(mangbaihat.size());
                            if(index == position){
                                position=index-1;
                            }
                            position = index;
                        }
                        if(position>(mangbaihat.size()-1)){
                            position =0;

                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dianhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());

                        UpdateTime();
                    }
                }
                // set su kien nguoi dung click nhieu qua
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1= new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },3000);
            }
        });


        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer!=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer =null;
                    }
                    if(position<(mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if(position<0){
                            position= mangbaihat.size()-1;
                        }
                        if(repeat==true){
                            position=1;
                        }
                        if(checkrandom==true){
                            Random random = new Random();
                            int index= random.nextInt(mangbaihat.size());
                            if(index == position){
                                position=index-1;
                            }
                            position = index;
                        }

                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dianhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());

                        UpdateTime();
                    }
                }
                // set su kien nguoi dung click nhieu qua
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1= new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },3000);
            }
        });



    }

    // ham tao thong bao toolbar
    private void displayNotification(){
       // Intent landingIntent= new Intent(this, activity_landing.class);
       //   PendingIntent landingPendingIntent = PendingIntent.getActivity(this, 0, landingIntent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(tenbaihat)
                .setContentText("Playing")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        //  builder.setContentIntent(landingPendingIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }


    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if(intent !=null){
            if(intent.hasExtra("cakhuc")){
                BaiHat baihat = intent.getParcelableExtra("cakhuc");
                //Toast.makeText(this, baihat.getTenbaihat(), Toast.LENGTH_SHORT).show();
                mangbaihat.add(baihat);
            }

            if(intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> baihatarraylist =intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatarraylist;
            /*for(int i=0; i<mangbaihat.size();i++ ){
                //Log.d("BBB", mangbaihat.get(i).getTenbaihat());
            }*/
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
                mediaPlayer.stop(); //khi dang hat thi dung phat, tat
                mangbaihat.clear(); // xoa danh sach phat
            }
        });

        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dianhac = new Fragment_Dianhac();
        fragment_playdanhsachcacbaihat = new Fragment_playdanhsachcacbaihat();
        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.Addfragnent(fragment_playdanhsachcacbaihat);
        adapternhac.Addfragnent(fragment_dianhac);
        viewpagerplaynhac.setAdapter(adapternhac);

       fragment_dianhac= (Fragment_Dianhac) adapternhac.getItem(1);

        if(mangbaihat.size()>0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            tenbaihat =mangbaihat.get(0).getTenbaihat();
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }



     class PlayMp3 extends AsyncTask<String, Void, String> {


         @Override
         protected String doInBackground(String... strings) {

             return strings[0];
         }

         @Override
         protected void onPostExecute(String baihat) {
             super.onPostExecute(baihat);

             try {
             mediaPlayer = new MediaPlayer();
             mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
             // thread online
             mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                 // ham nay dung de khi getfile tren server lau thi dung thread nay lai vaf reset
                 @Override
                 public void onCompletion(MediaPlayer mp) {
                     mediaPlayer.stop();
                     mediaPlayer.reset();
                 }
             });

                 mediaPlayer.setDataSource(baihat);
                 mediaPlayer.prepare();

             } catch (IOException e) {
                 e.printStackTrace();
             }
             mediaPlayer.start();
             TimeSong();
             UpdateTime();
         }

         // tao thu tu phat mot danh sach ca khuc nao do
     }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        skttime.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    skttime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next= true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next ==true){
                    if(mangbaihat.size()>0){
                        if(mediaPlayer.isPlaying() || mediaPlayer!=null){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer =null;
                        }
                        if(position<(mangbaihat.size())){
                            imgplay.setImageResource(R.drawable.iconpause);
                            position++;
                            if(repeat==true){
                                if(position==0){
                                    position= mangbaihat.size();

                                }
                                position-=1;
                            }
                            if(checkrandom==true){
                                Random random = new Random();
                                int index= random.nextInt(mangbaihat.size());
                                if(index == position){
                                    position=index-1;
                                }
                                position = index;
                            }
                            if(position>(mangbaihat.size()-1)){
                                position =0;

                            }
                            new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                            fragment_dianhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                            getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        }
                    }
                    // set su kien nguoi dung click nhieu qua
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1= new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    },3000);
                    next= false;
                    handler1.removeCallbacks(this);
                }else{
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000 );
    }


}
