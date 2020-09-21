package com.qlabs.wordbook.word.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.qlabs.wordbook.word.model.entity.Word;
import com.qlabs.wordbook.word.service.WordRepository;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddWordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;

    private MutableLiveData<Boolean> insertionResult = new MutableLiveData<>();
    private MutableLiveData<Word> editWordLiveData = new MutableLiveData<>();

    private Word editWord;
    private int mode;

    public AddWordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public void addWord(Word word) {
        wordRepository.insertPerson(word).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Long aLong) {
                        insertionResult.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        insertionResult.setValue(false);
                    }
                });

    }

    public void getWordById(int id) {
        wordRepository.getWordById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Word>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Word word) {
                        editWord = word;
                        editWordLiveData.setValue(word);
                    }

                    @Override
                    public void onError(Throwable e) {
                        editWordLiveData.setValue(null);
                    }
                });
    }

    public LiveData<Boolean> getInsertionResult() {
        return insertionResult;
    }

    public LiveData<Word> getEditWordLiveData() {
        return editWordLiveData;
    }

    public void updateWord(Word word) {
        wordRepository.updateWord(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        insertionResult.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        insertionResult.setValue(false);
                    }
                });
    }

    public Word getEditWord() {
        return editWord;
    }

    public void setEditWord(Word editWord) {
        this.editWord = editWord;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
