package com.gb.who.word.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.gb.who.R;
import com.gb.who.common.RecyclerViewClickHandler;
import com.gb.who.common.RecyclerViewEntity;
import com.gb.who.databinding.ActivityListWordsBinding;
import com.gb.who.word.adapter.WordListAdapter;
import com.gb.who.word.model.WordListViewModel;
import com.gb.who.word.model.entity.WordAdapterEntity;

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
        listWordsBinding.flSearchWords.setOnClickListener(v-> startActivity(new Intent(WordListActivity.this, SearchActivity.class)));
    }

    private void observeViewModel() {
        wordListViewModel.getAllWords().observe(this, words -> {
            if (words != null && !words.isEmpty()) {
                populateWords(words);
            }
        });
    }

    private void populateWords(List<WordAdapterEntity> adapterEntities) {
        if(wordListAdapter == null) {
            initAdapter(adapterEntities);
        } else {
            wordListAdapter.setAdapterEntities(adapterEntities);
        }
    }

    private void initAdapter(List<WordAdapterEntity> adapterEntities) {
        wordListAdapter = new WordListAdapter(adapterEntities, new RecyclerViewClickHandler() {
            @Override
            public void onItemClicked(RecyclerViewEntity recyclerViewEntity) {

            }

            @Override
            public void onItemLongClicked(RecyclerViewEntity recyclerViewEntity) {

            }
        });
        listWordsBinding.rvWords.setAdapter(wordListAdapter);
    }

    private void initViewModel() {
        wordListViewModel = ViewModelProviders.of(this).get(WordListViewModel.class);
    }
}
