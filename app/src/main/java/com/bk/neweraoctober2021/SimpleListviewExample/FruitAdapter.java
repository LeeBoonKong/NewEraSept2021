package com.bk.neweraoctober2021.SimpleListviewExample;

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

public class FruitAdapter extends ArrayAdapter<String> {
    private ArrayList<String> data;
    private Context context;

    public FruitAdapter(Context context, ArrayList<String> data){
        super(context, R.layout.single_row_fruit);
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
            view = LayoutInflater.from(context).inflate(R.layout.single_row_fruit, null);
            holder.tvFruit = view.findViewById(R.id.tvFruit);
            holder.tvFruit.setText(fruitName);
            view.setTag(holder);
        } else {
            Holder holder = (Holder) convertView.getTag();
            view = convertView;
            holder.tvFruit.setText(fruitName);
        }

        return view;
    }

    private static class Holder{
        TextView tvFruit;
    }
}
