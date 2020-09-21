package com.qlabs.qlabs.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.qlabs.qlabs.word.model.entity.Word;
import com.qlabs.qlabs.word.service.WordDao;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {

    public abstract WordDao personDao();

    private static volatile WordDataBase INSTANCE;

    public static WordDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (WordDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDataBase.class,
                            "person_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
