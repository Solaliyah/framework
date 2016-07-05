package framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import java.io.IOException;

import framework.base.Audio;
import framework.base.Music;
import framework.base.Sound;


/**
 * Created by SolarisD on 2016/03/19.
 */
public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;
    AudioAttributes audioAttributes;

    public AndroidAudio(Activity activity){
        Log.d("Creat","AndroidAudio");
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            this.soundPool  = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(20)
                    .build();
        }else{
                this.soundPool = new SoundPool(20,AudioManager.STREAM_MUSIC,0);
        }

    }

    @Override
    public Music newMusic(String fileName){
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
            return new AndroidMusic(assetDescriptor);
        }catch(IOException e){
            throw new RuntimeException("Couldn't load music '" + fileName + "'");
        }
    }

    @Override
    public Sound newSound(String fileName){
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
            int soundid = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundid);
        }catch(IOException e){
            throw new RuntimeException("Couldn't load sound '" + fileName + "'");
        }
    }
}
