package com.gb.who.person.service;

import android.app.Application;

import com.gb.who.database.PersonDataBase;
import com.gb.who.person.model.entity.Person;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class PersonRepository {

    private PersonDao personDao;

    public PersonRepository (Application application) {
        PersonDataBase personDataBase = PersonDataBase.getDatabase(application);
        personDao = personDataBase.personDao();
    }

    public Single<Long> insertPerson(Person person) {
       return personDao.insertPerson(person);
    }

    public Observable<List<Person>> getAllPersons() {
        return personDao.getAllPersons();
    }


}
