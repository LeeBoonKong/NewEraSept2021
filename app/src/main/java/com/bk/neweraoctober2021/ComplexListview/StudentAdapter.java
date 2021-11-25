package com.bk.neweraoctober2021.ComplexListview;

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

class StudentAdapter extends ArrayAdapter<Student> {
    private ArrayList<Student> data;
    private Context context;

    public StudentAdapter(Context context, ArrayList<Student> data){
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
        Student student = data.get(position);
        View view;

        if (convertView == null){
            Holder holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.single_row_student, null);
            holder.tvName = view.findViewById(R.id.tvName);
            holder.tvMatric = view.findViewById(R.id.tvMatric);
            holder.tvCourse = view.findViewById(R.id.tvCourse);

            holder.tvName.setText(student.getName());
            holder.tvMatric.setText(student.getMatric());
            holder.tvCourse.setText(student.getCourse());

            view.setTag(holder);
        } else {
            Holder holder = (Holder) convertView.getTag();
            view = convertView;
            holder.tvName.setText(student.getName());
            holder.tvMatric.setText(student.getMatric());
            holder.tvCourse.setText(student.getCourse());
        }

        return view;
    }

    public ArrayList<Student> getData(){
        return data;
    }

    private static class Holder{
        TextView tvName, tvMatric, tvCourse;
    }
}
