package com.gb.who.person.service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gb.who.person.model.entity.Person;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Long> insertPerson(Person person);

    @Query("SELECT * FROM person")
    Observable<List<Person>> getAllPersons();


}
