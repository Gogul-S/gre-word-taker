package com.qlabs.wordbook.word.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.qlabs.wordbook.word.model.entity.Word;
import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;
import com.qlabs.wordbook.word.service.WordRepository;
import com.qlabs.wordbook.word.transformer.WordTransformer;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WordListViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private MutableLiveData<List<WordAdapterEntity>> wordListLiveData = new MutableLiveData<>();

    private Disposable searchObserverDisposable;

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
}
