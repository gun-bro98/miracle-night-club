package com.capstone.oneday.sqlData.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.capstone.oneday.sqlData.DAO.UserDAO;
import com.capstone.oneday.sqlData.Model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDAO userDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getDBInstance(Context context) { //Singleton (getter, setter)
        //INSTANCE가 null이면 초기화
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_NAME")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}
