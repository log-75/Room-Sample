package com.example.android.room;

import android.arch.persistence.room.RoomDatabase;
import com.example.aliyyyyreza.room.DAO;

@android.arch.persistence.room.Database(entities = {UserModel.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract DAO getDAO();
}
