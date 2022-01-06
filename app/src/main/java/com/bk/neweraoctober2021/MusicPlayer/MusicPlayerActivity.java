package com.bk.neweraoctober2021.MusicPlayer;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MusicPlayerActivity extends AppCompatActivity {
    private ArrayList<Music> musicList = new ArrayList<>();
    private ListView musicListView;
    private MediaPlayer mediaPlayer;
    private TextView tvArtistName, tvMusicName;
    private ImageView imgPrevious, imgPlayPause, imgNext;
    private int currentMusicPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        getMusicListFromRaw();
        findViews();
        setListeners();
    }

    private void findViews(){
        musicListView = findViewById(R.id.listview);
        tvArtistName = findViewById(R.id.tvArtistName);
        tvMusicName = findViewById(R.id.tvMusicName);
        imgPrevious = findViewById(R.id.imgPrevious);
        imgPlayPause = findViewById(R.id.imgPlayPause);
        imgNext = findViewById(R.id.imgNext);
    }

    private void setListeners(){
        MusicListAdapter adapter = new MusicListAdapter(this, musicList);
        musicListView.setAdapter(adapter);

        musicListView.setOnItemClickListener((adapterView, view, i, l) -> {
            if(mediaPlayer != null && mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                mediaPlayer = null;
            }
            Music music = (Music) adapterView.getAdapter().getItem(i);
            currentMusicPosition = i;

            tvMusicName.setText(music.getMusicName());
            tvArtistName.setText(music.getArtistName());

            ((MusicListAdapter) musicListView.getAdapter()).setCurrentPlaying(i);

            mediaPlayer = MediaPlayer.create(this, music.getResourceID());
            mediaPlayer.start();
            imgPlayPause.setImageResource(R.drawable.icn_pause);
        });

        imgPlayPause.setOnClickListener(view -> {
            if(mediaPlayer != null){
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlayPause.setImageResource(R.drawable.icn_play);
                } else {
                    mediaPlayer.start();
                    imgPlayPause.setImageResource(R.drawable.icn_pause);
                }
            }
        });

        imgPrevious.setOnClickListener(view -> {
            if(mediaPlayer != null){
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlayPause.setImageResource(R.drawable.icn_play);
                }

                if(currentMusicPosition != 0){
                    currentMusicPosition--;
                    ((MusicListAdapter) musicListView.getAdapter()).setCurrentPlaying(currentMusicPosition);
                    Music music = (Music) musicListView.getAdapter().getItem(currentMusicPosition);
                    tvMusicName.setText(music.getMusicName());
                    tvArtistName.setText(music.getArtistName());
                    mediaPlayer = MediaPlayer.create(this, music.getResourceID());
                    mediaPlayer.start();
                    imgPlayPause.setImageResource(R.drawable.icn_pause);
                }
            }
        });

        imgNext.setOnClickListener(view -> {
            if(mediaPlayer != null){
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlayPause.setImageResource(R.drawable.icn_play);
                }

                if(currentMusicPosition != (musicList.size() - 1)){
                    currentMusicPosition++;
                    ((MusicListAdapter) musicListView.getAdapter()).setCurrentPlaying(currentMusicPosition);
                    Music music = (Music) musicListView.getAdapter().getItem(currentMusicPosition);
                    tvMusicName.setText(music.getMusicName());
                    tvArtistName.setText(music.getArtistName());
                    mediaPlayer = MediaPlayer.create(this, music.getResourceID());
                    mediaPlayer.start();
                    imgPlayPause.setImageResource(R.drawable.icn_pause);
                }
            }
        });
    }

    private void getMusicListFromRaw(){
        Field[] fields = R.raw.class.getFields();
        for (int i=0; i < fields.length; i++){
            Music music = new Music();

            try {
                music.setResourceID(fields[i].getInt(fields[i]));

                Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + music.getResourceID());
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(this, mediaPath);
                String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

                music.setMusicName(title);
                music.setArtistName(artist);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            musicList.add(music);
        }
    }
}