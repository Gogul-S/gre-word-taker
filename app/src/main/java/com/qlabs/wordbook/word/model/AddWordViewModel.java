package com.qlabs.qlabs.word.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.qlabs.qlabs.word.model.entity.Word;
import com.qlabs.qlabs.word.service.WordRepository;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddWordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;

    private MutableLiveData<Boolean> insertionResult = new MutableLiveData<>();

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

    public LiveData<Boolean> getInsertionResult() {
        return insertionResult;
    }
}