package com.gb.who.person.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gb.who.person.model.entity.Person;
import com.gb.who.person.service.PersonRepository;

import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersonListViewModel extends AndroidViewModel {

    private PersonRepository personRepository;
    private MutableLiveData<List<Person>> personsListLiveData = new MutableLiveData<>();

    public PersonListViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
    }

    public LiveData<List<Person>> getAllPersons() {
        return personsListLiveData;
    }

    public void getPersons() {
        personRepository.getAllPersons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Person>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Person> people) {
                        personsListLiveData.setValue(people);
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
