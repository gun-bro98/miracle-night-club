package com.capstone.oneday.sqlData.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.capstone.oneday.sqlData.Model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM user")
    List<User> getAllUserList();

    @Query("SELECT * FROM user WHERE userName = :name")
    List<User> getUserByName(String name);

    @Insert
    void insertUser(User user);

    @Delete
    void userDelete(User user);

    @Update
    void userUpdate(User user);

}
