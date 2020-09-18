package com.gb.who.person.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gb.who.R;
import com.gb.who.databinding.ActivityListPersonsBinding;
import com.gb.who.person.model.PersonListViewModel;
import com.gb.who.person.model.entity.Person;

import java.util.List;

public class PersonListActivity extends AppCompatActivity {

    private ActivityListPersonsBinding listPersonsBinding;
    private PersonListViewModel personListViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listPersonsBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_persons);
        initViewModel();
        observeViewModel();
        initListeners();
        personListViewModel.getPersons();
    }

    private void initListeners() {
        listPersonsBinding.btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonListActivity.this, AddPersonalityActivity.class));
            }
        });
    }

    private void observeViewModel() {
        personListViewModel.getAllPersons().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                if (people != null && !people.isEmpty()) {
                    StringBuilder personNames = new StringBuilder();
                    for (Person person : people) {
                        personNames.append("\n\n").append(person.getName());
                    }
                    listPersonsBinding.tvWords.setText(personNames);
                }
            }
        });
    }

    private void initViewModel() {
        personListViewModel = ViewModelProviders.of(this).get(PersonListViewModel.class);
    }
}
