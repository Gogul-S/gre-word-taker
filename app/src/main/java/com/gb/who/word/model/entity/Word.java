package com.gb.who.word.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "wordTitle")
    private String wordTitle;

    @NonNull
    @ColumnInfo(name = "meaning")
    private String meaning;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getWordTitle() {
        return wordTitle;
    }

    public void setWordTitle(@NonNull String name) {
        this.wordTitle = name;
    }

    @NonNull
    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(@NonNull String meaning) {
        this.meaning = meaning;
    }

    public Word(@NonNull String wordTitle,@NonNull String meaning) {
        this.wordTitle = wordTitle;
        this.meaning = meaning;
    }
}
