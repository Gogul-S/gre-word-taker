package com.qlabs.wordbook.word.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;
import com.qlabs.wordbook.word.service.WordRepository;
import com.qlabs.wordbook.word.transformer.WordTransformer;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    public MutableLiveData<List<WordAdapterEntity>> searchWordsLiveData = new MutableLiveData<>();
    private Disposable searchObserverDisposable;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public void searchWords(String searchText) {
        final WordTransformer wordTransformer = new WordTransformer();
        disposeObserver();
        wordRepository.getWordsByName(searchText + "%")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(wordTransformer::transform)
                .subscribe(new Observer<List<WordAdapterEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        searchObserverDisposable = d;
                    }

                    @Override
                    public void onNext(List<WordAdapterEntity> wordAdapterEntities) {
                        searchWordsLiveData.setValue(wordAdapterEntities);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposeObserver();
    }

    public void disposeObserver() {
        if(searchObserverDisposable != null && !searchObserverDisposable.isDisposed()) {
            searchObserverDisposable.dispose();
        }
    }

    public void deleteWordById(int id) {
        wordRepository.deleteWordById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public LiveData<List<WordAdapterEntity>> getSearchWordsLiveData() {
        return searchWordsLiveData;
    }
}
