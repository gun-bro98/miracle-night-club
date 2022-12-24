package com.capstone.oneday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.capstone.oneday.sqlData.Database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB 생성
        appDatabase = AppDatabase.getDBInstance(this);

        //main thread에서 DB접근 지양, 그러므로 thread 사용하기
        class InsertRunnable implements Runnable {
            @Override
            public void run() {

            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }
}