package com.gb.who.person.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gb.who.person.model.entity.Person;
import com.gb.who.person.service.PersonRepository;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddPersonViewModel extends AndroidViewModel {

    private PersonRepository personRepository;

    private MutableLiveData<Boolean> insertionResult = new MutableLiveData<>();

    public AddPersonViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
    }

    public void insertPerson(Person person) {
        personRepository.insertPerson(person).
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
