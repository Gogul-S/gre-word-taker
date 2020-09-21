package com.qlabs.qlabs.word.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.qlabs.qlabs.R;
import com.qlabs.qlabs.common.TextUtils;
import com.qlabs.qlabs.databinding.ActivityAddWordBinding;
import com.qlabs.qlabs.word.model.AddWordViewModel;
import com.qlabs.qlabs.word.model.entity.Word;

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
        addWordBinding.btnAddPerson.setOnClickListener(view -> {
            addWord();
        });

        addWordBinding.addWordsToolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }

    private void addWord() {
        Word word = getWord();
        if (word == null) return;
        addWordViewModel.addWord(word);
    }

    private Word getWord() {
        String wordTitle = addWordBinding.etWord.getText().toString();
        if(!TextUtils.checkForNullAndEmpty(wordTitle)) {
            addWordBinding.tilWord.setError(getString(R.string.enter_a_word));
            return null;
        }
        addWordBinding.tilWord.setError(null);
        String hint = addWordBinding.etHint.getText().toString();
        if(!TextUtils.checkForNullAndEmpty(hint)) {
            addWordBinding.tilHint.setError(getString(R.string.enter_hint));
            return null;
        }
        addWordBinding.tilHint.setError(null);
        String meaning = addWordBinding.etMeaning.getText().toString();
        if(!TextUtils.checkForNullAndEmpty(meaning)) {
            addWordBinding.tilMeaning.setError(getString(R.string.enter_meaning_for_word));
            return null;
        }
        addWordBinding.tilMeaning.setError(null);
        Word word = new Word(wordTitle,meaning,hint);
        return word;
    }
}