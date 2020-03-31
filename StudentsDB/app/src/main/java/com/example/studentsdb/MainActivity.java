package com.example.studentsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Data.DataBaseHandler;
import Human.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
        Student student1 = new Student("Math", "Ivanov", "Vasya", 4.1);
        Student student2 = new Student("Phys", "Sidorov", "Kolya", 3.2);
        Student student3 = new Student("Eng", "Blue", "Charlie", 4.6);
        Student student4 = new Student("Cult", "Chaplin", "Claus", 2.6);
        Student student5 = new Student("Chem", "Kim", "Yen", 5.0);

        dataBaseHandler.addStudent(student1);
        dataBaseHandler.addStudent(student2);
        dataBaseHandler.addStudent(student3);
        dataBaseHandler.addStudent(student4);
        dataBaseHandler.addStudent(student5);

        Log.d("Student_Check", "Count of students: " + dataBaseHandler.getStudentsCount());

        List<Student> students = dataBaseHandler.getAllStudents();
        for (Student student : students) {
            Log.d("Student_Check", "Add Student: ID: " + student.getId()
                    + ", DEPARTMENT: " + student.getDepartment()
                    + ", FAMILY: " + student.getFamily()
                    + ", NAME: " + student.getName()
                    + ", AVERAGE_CHECK: " + student.getAverageCheck()
            );
            dataBaseHandler.deleteStudent(student); //clear DB
        }
        Log.d("Student_Check", "Count of students after clear: " + dataBaseHandler.getStudentsCount());


    }
}
