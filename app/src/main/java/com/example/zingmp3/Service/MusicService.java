package com.example.zingmp3.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.zingmp3.R;

public class MusicService extends Service {

    MediaPlayer player;
    @Nullable


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //player=MediaPlayer.create(MainActivity.this, Settings.System.DEFAULT_RINGTONE_URI);
        //player=MediaPlayer.create(MusicService.this, R.raw.ifyouletmego);
        player.start();
        player.setLooping(true);
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        super.onDestroy();
    }
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        player.stop();
        player.release();
        Intent broadcastIntent= new Intent(this,MusicBroadcastReceiver.class);
        sendBroadcast(broadcastIntent);
    }

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
