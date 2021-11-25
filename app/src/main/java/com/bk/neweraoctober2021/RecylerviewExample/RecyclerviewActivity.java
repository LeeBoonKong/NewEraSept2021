package com.bk.neweraoctober2021.RecylerviewExample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bk.neweraoctober2021.ComplexListview.Student;
import com.bk.neweraoctober2021.R;

import java.util.ArrayList;

public class RecyclerviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
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
        recyclerView = findViewById(R.id.recylerview);
    }

    private void setListeners(){
        RecyclerStudentAdapter adapter = new RecyclerStudentAdapter(this, studentList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}