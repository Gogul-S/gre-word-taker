package com.qlabs.wordbook.word.service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.qlabs.wordbook.word.model.entity.Word;

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

    @Query("select * from word where id = :id")
    Single<Word> getWordById(int id);

    @Query("delete from word where id = :id")
    Single<Integer> deleteWordById(int id);

    @Update
    Single<Integer> updateWord(Word word);


}
