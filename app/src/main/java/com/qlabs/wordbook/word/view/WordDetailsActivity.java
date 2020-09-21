package com.qlabs.wordbook.word.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.qlabs.wordbook.R;
import com.qlabs.wordbook.databinding.ActivityWordDetailsBinding;

public class WordDetailsActivity extends AppCompatActivity {

    private ActivityWordDetailsBinding wordDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_word_details);
        initListeners();
    }

    private void initListeners() {
        wordDetailsBinding.toolbarWordDetails.setNavigationOnClickListener(v -> finish());
    }
}