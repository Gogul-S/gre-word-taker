package com.qlabs.wordbook.word.model.entity;

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
    @ColumnInfo(name = "hint")
    private String hint;

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

    @NonNull
    public String getHint() {
        return hint;
    }

    public void setHint(@NonNull String hint) {
        this.hint = hint;
    }

    public Word(@NonNull String wordTitle, @NonNull String meaning, @NonNull String hint) {
        this.wordTitle = wordTitle;
        this.meaning = meaning;
        this.hint = hint;
    }
}
