package com.capstone.oneday.sqlData.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//나중에 백엔드 서버가 개발되면,
//유저 정보를 추출해서 저장하기 위한 엔티티.
//일단 연결은 해두었으나, 적극적으로 사용하지 않음.

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String userName;

    @ColumnInfo
    public String passWord;

    @ColumnInfo(name="userGender")
    public String maleOrFemale;
}
