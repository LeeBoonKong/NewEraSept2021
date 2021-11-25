package com.bk.neweraoctober2021.RecylerviewExample;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bk.neweraoctober2021.ComplexListview.Student;
import com.bk.neweraoctober2021.R;

import java.util.ArrayList;

class RecyclerStudentAdapter extends RecyclerView.Adapter<RecyclerStudentAdapter.Holder> {
    private ArrayList<Student> dataList;
    private Context context;

    public RecyclerStudentAdapter(Context context, ArrayList<Student> data){
        this.dataList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_student, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerStudentAdapter.Holder holder, int position) {
        Student student = dataList.get(position);

        holder.tvName.setText(student.getName());
        holder.tvMatric.setText(student.getMatric());
        holder.tvCourse.setText(student.getCourse());

        holder.tvName.setOnClickListener(view -> showDialog("Name", student.getName()));
        holder.tvMatric.setOnClickListener(view -> showDialog("Matric", student.getMatric()));
        holder.tvCourse.setOnClickListener(view -> showDialog("Course", student.getCourse()));
        holder.root.setOnClickListener(view -> showDialog("Root", "Clicked on the root of " + student.getName()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private void showDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle(title)
                .setMessage(message)
                .setNeutralButton("OK", null)
                .show();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private TextView tvName, tvMatric, tvCourse;
        private LinearLayout root;

        public Holder(View view){
            super(view);

            root = view.findViewById(R.id.root);
            tvName = view.findViewById(R.id.tvName);
            tvMatric = view.findViewById(R.id.tvMatric);
            tvCourse = view.findViewById(R.id.tvCourse);
        }
    }
}
