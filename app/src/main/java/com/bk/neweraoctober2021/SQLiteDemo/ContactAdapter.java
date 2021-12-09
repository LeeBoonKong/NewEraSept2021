package com.bk.neweraoctober2021.SQLiteDemo;

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

class ContactAdapter extends ArrayAdapter<Contact> {
    private ArrayList<Contact> data;
    private Context context;

    public ContactAdapter(Context context, ArrayList<Contact> data){
        super(context, R.layout.single_row_phone_number);
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
        Contact contact = data.get(position);
        View view;

        if (convertView == null){
            Holder holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.single_row_phone_number, null);
            holder.tvName = view.findViewById(R.id.tvName);
            holder.tvNumber = view.findViewById(R.id.tvNumber);

            holder.tvName.setText(contact.getName());
            holder.tvNumber.setText(contact.getNumber());
            view.setTag(holder);
        } else {
            Holder holder = (Holder) convertView.getTag();
            view = convertView;
            holder.tvName.setText(contact.getName());
            holder.tvNumber.setText(contact.getNumber());
        }

        return view;
    }

    public void updateData(ArrayList<Contact> newData){
        this.data.clear();
        this.data.addAll(newData);
        notifyDataSetChanged();
    }

    private static class Holder{
        TextView tvName, tvNumber;
    }
}
