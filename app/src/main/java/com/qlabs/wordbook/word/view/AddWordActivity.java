package com.qlabs.wordbook.word.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.qlabs.wordbook.R;
import com.qlabs.wordbook.common.TextUtils;
import com.qlabs.wordbook.databinding.ActivityAddWordBinding;
import com.qlabs.wordbook.word.WordConstants;
import com.qlabs.wordbook.word.model.AddWordViewModel;
import com.qlabs.wordbook.word.model.entity.Word;

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
        populateDataBasedOnMode();
        addWordBinding.etWord.requestFocus();
    }

    private void populateDataBasedOnMode() {
        int mode = getIntent().getIntExtra("mode", 0);
        addWordViewModel.setMode(mode);
        if (mode == WordConstants.EDIT_MODE) {
            populateDataForEdit();
        }
    }

    private void populateDataForEdit() {
        int wordId = getIntent().getIntExtra("wordId", -1);
        if (wordId != -1) {
            addWordViewModel.getWordById(wordId);
        } else {
            closeWithError();
        }
    }

    private void closeWithError() {
        Toast.makeText(AddWordActivity.this, R.string.something_went_wrong, Toast.LENGTH_LONG).show();
        finish();
    }

    private void observeViewModel() {
        addWordViewModel.getInsertionResult().observe(this, success -> {
            if (success != null && success) {
                Toast.makeText(AddWordActivity.this, getString(R.string.success), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(AddWordActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
            }
        });

        addWordViewModel.getEditWordLiveData().observe(this, this::populateWord);
    }

    private void populateWord(Word word) {
        if (word != null) {
            addWordBinding.etWord.setText(word.getWordTitle());
            addWordBinding.etHint.setText(word.getHint());
            addWordBinding.etMeaning.setText(word.getMeaning());
        } else {
            closeWithError();
        }
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
        if (addWordViewModel.getMode() == WordConstants.EDIT_MODE) {
            addWordViewModel.updateWord(word);
        } else {
            if (word == null) return;
            addWordViewModel.addWord(word);
        }
    }

    private Word getWord() {
        String wordTitle = addWordBinding.etWord.getText().toString();
        if (!TextUtils.checkForNullAndEmpty(wordTitle)) {
            addWordBinding.tilWord.setError(getString(R.string.enter_a_word));
            return null;
        }
        addWordBinding.tilWord.setError(null);
        String hint = addWordBinding.etHint.getText().toString();
        if (!TextUtils.checkForNullAndEmpty(hint)) {
            addWordBinding.tilHint.setError(getString(R.string.enter_hint));
            return null;
        }
        addWordBinding.tilHint.setError(null);
        String meaning = addWordBinding.etMeaning.getText().toString();
        if (!TextUtils.checkForNullAndEmpty(meaning)) {
            addWordBinding.tilMeaning.setError(getString(R.string.enter_meaning_for_word));
            return null;
        }
        addWordBinding.tilMeaning.setError(null);
        Word word;
        if (addWordViewModel.getMode() == WordConstants.EDIT_MODE) {
            word = getUpdatedWord(wordTitle, hint, meaning);
        } else {
            word = new Word(wordTitle.trim(), meaning.trim(), hint.trim());
        }
        return word;
    }

    private Word getUpdatedWord(String wordTitle, String hint, String meaning) {
        Word word;
        word = addWordViewModel.getEditWord();
        if (word == null) {
            closeWithError();
        } else {
            word.setWordTitle(wordTitle);
            word.setHint(hint);
            word.setMeaning(meaning);
        }
        return word;
    }
}