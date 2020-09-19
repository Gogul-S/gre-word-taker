package com.gb.who.word.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gb.who.word.model.entity.WordAdapterEntity;
import com.gb.who.word.service.WordRepository;
import com.gb.who.word.transformer.WordTransformer;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WordListViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private MutableLiveData<List<WordAdapterEntity>> wordListLiveData = new MutableLiveData<>();

    public WordListViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<WordAdapterEntity>> getAllWords() {
        return wordListLiveData;
    }

    public void getWords() {
        final WordTransformer wordTransformer = new WordTransformer();
        wordRepository.getAllPersons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(wordTransformer::transform)
                .subscribe(new Observer<List<WordAdapterEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<WordAdapterEntity> words) {
                        wordListLiveData.setValue(words);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
