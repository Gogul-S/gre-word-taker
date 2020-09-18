package com.gb.who.person.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gb.who.R;
import com.gb.who.databinding.ActivityAddPersonBinding;
import com.gb.who.person.model.AddPersonViewModel;
import com.gb.who.person.model.entity.Person;

public class AddPersonalityActivity extends AppCompatActivity {

    private ActivityAddPersonBinding addPersonalityBinding;
    private AddPersonViewModel addPersonViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPersonalityBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_person);
        initListeners();
        initViewModel();
        observeViewModel();
    }

    private void observeViewModel() {
        addPersonViewModel.getInsertionResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success != null && success) {
                    Toast.makeText(AddPersonalityActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddPersonalityActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViewModel() {
        addPersonViewModel = ViewModelProviders.of(this).get(AddPersonViewModel.class);
    }

    private void initListeners() {
        addPersonalityBinding.btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personName = addPersonalityBinding.etPersonName.getText().toString();
                addPersonViewModel.insertPerson(new Person(personName));
            }
        });
    }
}