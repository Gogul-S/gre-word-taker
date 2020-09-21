package com.qlabs.qlabs.word.service;

import android.app.Application;

import com.qlabs.qlabs.database.WordDataBase;
import com.qlabs.qlabs.word.model.entity.Word;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class WordRepository {

    private WordDao wordDao;

    public WordRepository(Application application) {
        WordDataBase wordDataBase = WordDataBase.getDatabase(application);
        wordDao = wordDataBase.personDao();
    }

    public Single<Long> insertPerson(Word word) {
        return wordDao.insertWord(word);
    }

    public Observable<List<Word>> getAllPersons() {
        return wordDao.getAllWords();
    }

    public Observable<List<Word>> getWordsByName(String searchText) {
        return wordDao.getWordsByName(searchText);
    }


}
