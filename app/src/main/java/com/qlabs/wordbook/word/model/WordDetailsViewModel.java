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

public class WordDetailsViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private MutableLiveData<Word> wordLiveData = new MutableLiveData<>();

    public WordDetailsViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<Word> getWordLiveData() {
        return wordLiveData;
    }

    public void getWordbyId(int id) {
        wordRepository.getWordById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Word>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Word word) {
                        wordLiveData.setValue(word);
                    }

                    @Override
                    public void onError(Throwable e) {
                        wordLiveData.setValue(null);
                    }
                });
    }
}
