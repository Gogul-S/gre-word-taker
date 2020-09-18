package com.gb.who.word.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gb.who.R;
import com.gb.who.databinding.ActivityAddWordBinding;
import com.gb.who.word.model.AddWordViewModel;
import com.gb.who.word.model.entity.Word;

public class AddWordActivity extends AppCompatActivity {

    private ActivityAddWordBinding addWordBinding;
    private AddWordViewModel addWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addWordBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_word);
        initListeners();
        initViewModel();
        observeViewModel();
    }

    private void observeViewModel() {
        addWordViewModel.getInsertionResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success != null && success) {
                    Toast.makeText(AddWordActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddWordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViewModel() {
        addWordViewModel = ViewModelProviders.of(this).get(AddWordViewModel.class);
    }

    private void initListeners() {
        addWordBinding.btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = addWordBinding.etWord.getText().toString();
                String meaning = addWordBinding.etMeaning.getText().toString();
                addWordViewModel.addWord(new Word(word,meaning));
            }
        });
    }
}