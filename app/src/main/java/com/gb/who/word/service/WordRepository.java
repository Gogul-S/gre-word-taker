package com.gb.who.word.service;

import android.app.Application;

import com.gb.who.database.WordDataBase;
import com.gb.who.word.model.entity.Word;

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
