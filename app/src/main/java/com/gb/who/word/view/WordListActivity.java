package com.gb.who.word.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gb.who.R;
import com.gb.who.databinding.ActivityListWordsBinding;
import com.gb.who.word.model.WordListViewModel;
import com.gb.who.word.model.entity.Word;

import java.util.List;

public class WordListActivity extends AppCompatActivity {

    private ActivityListWordsBinding listWordsBinding;
    private WordListViewModel wordListViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listWordsBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_words);
        initViewModel();
        observeViewModel();
        initListeners();
        wordListViewModel.getWords();
    }

    private void initListeners() {
        listWordsBinding.btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WordListActivity.this, AddWordActivity.class));
            }
        });
    }

    private void observeViewModel() {
        wordListViewModel.getAllPersons().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> people) {
                if (people != null && !people.isEmpty()) {
                    StringBuilder personNames = new StringBuilder();
                    for (Word word : people) {
                        personNames.append("\n\n").append(word.getWordTitle()).append(":").append(word.getMeaning());
                    }
                    listWordsBinding.tvWords.setText(personNames);
                }
            }
        });
    }

    private void initViewModel() {
        wordListViewModel = ViewModelProviders.of(this).get(WordListViewModel.class);
    }
}
