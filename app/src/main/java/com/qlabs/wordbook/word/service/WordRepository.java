package com.qlabs.wordbook.word.service;

import android.app.Application;

import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;

import com.qlabs.wordbook.database.WordDataBase;
import com.qlabs.wordbook.word.model.entity.Word;

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

    public Observable<PagedList<Word>> getAllPersons() {
        return new RxPagedListBuilder<>(wordDao.getAllWords(), 10).buildObservable();
    }

    public Observable<List<Word>> getWordsByName(String searchText) {
        return wordDao.getWordsByName(searchText);
    }

    public Single<Word> getWordById(int id) {
        return wordDao.getWordById(id);
    }

    public Single<Integer> deleteWordById(int id) {
        return wordDao.deleteWordById(id);
    }

    public Single<Integer> updateWord(Word word) {
        return wordDao.updateWord(word);
    }


}
