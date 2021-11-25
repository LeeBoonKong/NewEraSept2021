package com.bk.neweraoctober2021.NetworkingExample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bk.neweraoctober2021.R;

import java.util.ArrayList;

class UsernameAdapter extends ArrayAdapter<String> {
    private ArrayList<String> data;
    private Context context;

    public UsernameAdapter(Context context, ArrayList<String> data){
        super(context, R.layout.single_row_string);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String fruitName = data.get(position);
        View view;

        if (convertView == null){
            Holder holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.single_row_string, null);
            holder.tvName = view.findViewById(R.id.tvString);
            holder.tvName.setText(fruitName);
            view.setTag(holder);
        } else {
            Holder holder = (Holder) convertView.getTag();
            view = convertView;
            holder.tvName.setText(fruitName);
        }

        return view;
    }

    private static class Holder{
        TextView tvName;
    }
}
