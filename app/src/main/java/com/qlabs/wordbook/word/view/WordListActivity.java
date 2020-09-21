package com.qlabs.wordbook.word.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.qlabs.wordbook.R;
import com.qlabs.wordbook.common.RecyclerViewClickHandler;
import com.qlabs.wordbook.common.RecyclerViewEntity;
import com.qlabs.wordbook.databinding.ActivityListWordsBinding;
import com.qlabs.wordbook.word.adapter.WordListAdapter;
import com.qlabs.wordbook.word.model.WordListViewModel;
import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;

import java.util.List;

public class WordListActivity extends AppCompatActivity {

    private ActivityListWordsBinding listWordsBinding;
    private WordListViewModel wordListViewModel;
    private WordListAdapter wordListAdapter;

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
        listWordsBinding.btnAddPerson.setOnClickListener(view -> startActivity(new Intent(WordListActivity.this, AddWordActivity.class)));
        listWordsBinding.flSearchWords.setOnClickListener(v -> startActivity(new Intent(WordListActivity.this, SearchActivity.class)));
    }

    private void observeViewModel() {
        wordListViewModel.getAllWords().observe(this, words -> {
            if (words != null && !words.isEmpty()) {
                populateWords(words);
            }
        });
    }

    private void populateWords(List<WordAdapterEntity> adapterEntities) {
        if (wordListAdapter == null) {
            initAdapter(adapterEntities);
        } else {
            wordListAdapter.setAdapterEntities(adapterEntities);
        }
    }

    private void initAdapter(List<WordAdapterEntity> adapterEntities) {
        wordListAdapter = new WordListAdapter(adapterEntities, new RecyclerViewClickHandler<WordAdapterEntity>() {
            @Override
            public void onItemClicked(View view, WordAdapterEntity wordAdapterEntity) {
                handleWordClick(view, wordAdapterEntity);
            }

            @Override
            public void onItemLongClicked(View view, WordAdapterEntity wordAdapterEntity) {

            }
        });
        listWordsBinding.rvWords.setAdapter(wordListAdapter);
    }

    private void handleWordClick(View view, WordAdapterEntity wordAdapterEntity) {
        switch (view.getId()) {
            case R.id.clWordContainer:
                openWordDetailsActivity(wordAdapterEntity);
                break;
        }
    }

    private void openWordDetailsActivity(WordAdapterEntity wordAdapterEntity) {
        Bundle args = new Bundle();
        args.putInt("wordId", wordAdapterEntity.getId());
        Intent intent = new Intent(this, WordDetailsActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }

    private void initViewModel() {
        wordListViewModel = ViewModelProviders.of(this).get(WordListViewModel.class);
    }
}
