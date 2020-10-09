package com.qlabs.wordbook.word.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.qlabs.wordbook.R;
import com.qlabs.wordbook.databinding.ActivityWordDetailsBinding;
import com.qlabs.wordbook.word.WordConstants;
import com.qlabs.wordbook.word.model.WordDetailsViewModel;
import com.qlabs.wordbook.word.model.entity.Word;
import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;

public class WordDetailsActivity extends AppCompatActivity {

    private ActivityWordDetailsBinding wordDetailsBinding;
    private WordDetailsViewModel wordDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_word_details);
        initListeners();
        initViewModel();
        observeViewModel();
        getWordDetails();
    }

    private void getWordDetails() {
        int wordId = getIntent().getIntExtra("wordId", -1);
        if (wordId != -1) {
            wordDetailsViewModel.setWordId(wordId);
            wordDetailsViewModel.getWordbyId();
        } else {
            Toast.makeText(WordDetailsActivity.this, R.string.something_went_wrong, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void observeViewModel() {
        wordDetailsViewModel.getWordLiveData().observe(this, word -> {
            if (word != null) {
                setWordDetails(word);
            } else {
                Toast.makeText(WordDetailsActivity.this, R.string.something_went_wrong, Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void setWordDetails(Word word) {
        wordDetailsBinding.toolbarWordDetails.setTitle(word.getWordTitle().toUpperCase());
        wordDetailsBinding.tvWordTitle.setText(word.getWordTitle());
        wordDetailsBinding.tvQuickHint.setText(word.getHint());
        wordDetailsBinding.tvMeaning.setText(word.getMeaning());
    }

    private void initViewModel() {
        wordDetailsViewModel = ViewModelProviders.of(this).get(WordDetailsViewModel.class);
    }

    private void initListeners() {
        wordDetailsBinding.toolbarWordDetails.setNavigationOnClickListener(v -> finish());
        wordDetailsBinding.toolbarWordDetails.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        openEditWordActivity();
                }
                return false;
            }
        });
    }

    private void openEditWordActivity() {
        Bundle args = new Bundle();
        args.putInt("mode", WordConstants.EDIT_MODE);
        args.putInt("wordId", wordDetailsViewModel.getWordId());
        Intent editWord = new Intent(this, AddWordActivity.class);
        editWord.putExtras(args);
        startActivity(editWord);
    }
}