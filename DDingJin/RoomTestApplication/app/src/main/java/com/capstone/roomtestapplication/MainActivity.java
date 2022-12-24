package com.capstone.roomtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//https://www.youtube.com/watch?v=pG6OkJ3rSjg 9분20초
public class MainActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.TodoEdit);
        mResultTextView = findViewById(R.id.ResultText);

        //DB생성
        final AppDatabase appDatabase = Room.databaseBuilder(this, AppDatabase.class, "TodolistDB")
                .allowMainThreadQueries() //백그라운드 동작 허용
                .build(); //데이터베이스 객체 생성

        mResultTextView.setText(appDatabase.todoDao().getAll().toString());

        findViewById(R.id.AddButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appDatabase.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
                mResultTextView.setText(appDatabase.todoDao().getAll().toString());
            }
        });
    }
}