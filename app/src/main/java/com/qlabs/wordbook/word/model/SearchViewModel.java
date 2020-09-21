package com.qlabs.qlabs.word.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.qlabs.qlabs.word.model.entity.WordAdapterEntity;
import com.qlabs.qlabs.word.service.WordRepository;
import com.qlabs.qlabs.word.transformer.WordTransformer;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    public MutableLiveData<List<WordAdapterEntity>> searchWordsLiveData = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public void searchWords(String searchText) {
        final WordTransformer wordTransformer = new WordTransformer();
        wordRepository.getWordsByName(searchText + "%")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(wordTransformer::transform)
                .subscribe(new Observer<List<WordAdapterEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<WordAdapterEntity> adapterEntities) {
                        searchWordsLiveData.setValue(adapterEntities);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public LiveData<List<WordAdapterEntity>> getSearchWordsLiveData() {
        return searchWordsLiveData;
    }
}