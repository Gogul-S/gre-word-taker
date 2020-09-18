package com.gb.who.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gb.who.person.model.entity.Person;
import com.gb.who.person.service.PersonDao;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class PersonDataBase extends RoomDatabase {

    public abstract PersonDao personDao();

    private static volatile PersonDataBase INSTANCE;

    public static PersonDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (PersonDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PersonDataBase.class,
                            "person_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
