package com.bk.neweraoctober2021.MusicPlayer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bk.neweraoctober2021.R;

import java.util.ArrayList;

class MusicListAdapter extends ArrayAdapter<Music> {

    private ArrayList<Music> data;
    private Context context;
    public static int currentlyPlaying = -1;

    public MusicListAdapter(Context context, ArrayList<Music> data){
        super(context, R.layout.music_listview_item);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Music getItem(int position) {
        return data.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Music music = data.get(position);
        View view;

        if (convertView == null){
            Holder holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.music_listview_item, null);
            holder.tvMusicName = view.findViewById(R.id.tvMusicName);
            holder.tvArtistName = view.findViewById(R.id.tvArtistName);
            holder.itemLinearLayout = view.findViewById(R.id.itemLinearLayout);

            holder.tvMusicName.setText(music.getMusicName());
            holder.tvArtistName.setText(music.getArtistName());

            if(position == currentlyPlaying){
                holder.itemLinearLayout.setBackgroundColor(Color.parseColor("#6ee4ff"));
            } else {
                holder.itemLinearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            }

            view.setTag(holder);
        } else {
            Holder holder = (Holder) convertView.getTag();
            view = convertView;
            holder.tvMusicName.setText(music.getMusicName());
            holder.tvArtistName.setText(music.getArtistName());

            if(position == currentlyPlaying){
                holder.itemLinearLayout.setBackgroundColor(Color.parseColor("#6ee4ff"));
            } else {
                holder.itemLinearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }

        return view;
    }

    public ArrayList<Music> getData(){
        return data;
    }

    public void setCurrentPlaying(int currentlyPlaying){
        this.currentlyPlaying = currentlyPlaying;
        notifyDataSetChanged();
    }

    private static class Holder{
        LinearLayout itemLinearLayout;
        TextView tvMusicName, tvArtistName;
    }
}
