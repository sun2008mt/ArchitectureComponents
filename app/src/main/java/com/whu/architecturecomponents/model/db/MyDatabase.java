package com.whu.architecturecomponents.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.whu.architecturecomponents.model.entity.User;

/**
 * Created by Administrator on 2018/1/24.
 */

@Database(entities = {User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
