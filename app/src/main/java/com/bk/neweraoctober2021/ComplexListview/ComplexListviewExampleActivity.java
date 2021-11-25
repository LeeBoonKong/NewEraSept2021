package com.bk.neweraoctober2021.ComplexListview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bk.neweraoctober2021.R;

import java.util.ArrayList;

public class ComplexListviewExampleActivity extends AppCompatActivity {
    private ArrayList<Student> studentList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_listview_example);
        initArraylist();
        findViews();
        setListeners();
    }

    private void initArraylist(){
        Student student1 = new Student();
        student1.setName("John");
        student1.setMatric("DIT12345");
        student1.setCourse("Software Engineering");
        studentList.add(student1);

        Student student2 = new Student();
        student2.setName("Justin");
        student2.setMatric("DIC223344");
        student2.setCourse("Cyberscurity");
        studentList.add(student2);
    }

    private void findViews(){
        listView = findViewById(R.id.listview);
    }

    private void setListeners(){
        StudentAdapter adapter = new StudentAdapter(this, studentList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StudentAdapter studentAdapter = (StudentAdapter) listView.getAdapter();
                Student student = studentAdapter.getData().get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(ComplexListviewExampleActivity.this);
                builder.setTitle(student.getName())
                        .setMessage(student.getCourse())
                        .setNeutralButton("OK", null)
                        .show();
            }
        });
    }
}