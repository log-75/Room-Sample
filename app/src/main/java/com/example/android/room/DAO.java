package com.example.aliyyyyreza.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.android.room.UserModel;

@Dao
public interface DAO {

    @Query("select * from UserModel where id =:id")
    UserModel getUser(int id);

    @Insert
    void setUser(UserModel user);
}
