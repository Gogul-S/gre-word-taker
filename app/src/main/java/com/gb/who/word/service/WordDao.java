package com.gb.who.word.service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gb.who.word.model.entity.Word;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Long> insertWord(Word word);

    @Query("SELECT * FROM Word")
    Observable<List<Word>> getAllWords();

    @Query("select * from word where wordTitle like :searchText")
    Observable<List<Word>> getWordsByName(String searchText);


}
