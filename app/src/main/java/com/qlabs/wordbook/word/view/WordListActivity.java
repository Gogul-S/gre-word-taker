package com.qlabs.wordbook.word.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;

import com.qlabs.wordbook.R;
import com.qlabs.wordbook.common.RecyclerViewClickHandler;
import com.qlabs.wordbook.databinding.ActivityListWordsBinding;
import com.qlabs.wordbook.word.WordConstants;
import com.qlabs.wordbook.word.adapter.PagedWordListAdapter;
import com.qlabs.wordbook.word.model.WordListViewModel;
import com.qlabs.wordbook.word.model.entity.Word;
import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;
import com.qlabs.wordbook.word.transformer.WordTransformer;

public class WordListActivity extends AppCompatActivity {

    private ActivityListWordsBinding listWordsBinding;
    private WordListViewModel wordListViewModel;
    private PagedWordListAdapter pagedWordListAdapter;

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
            if (words != null) {
                populateWords(words);
            }
        });
    }

    private void populateWords(PagedList<Word> adapterEntities) {
        if (pagedWordListAdapter == null) {
            initAdapter(adapterEntities);
        } else {
            pagedWordListAdapter.submitList(adapterEntities);
        }
    }

    private void initAdapter(PagedList<Word> adapterEntities) {
        pagedWordListAdapter = new PagedWordListAdapter(new WordTransformer(), new RecyclerViewClickHandler<WordAdapterEntity>() {
            @Override
            public void onItemClicked(View view, WordAdapterEntity recyclerViewEntity) {
                handleWordClick(view, recyclerViewEntity);
            }

            @Override
            public void onItemLongClicked(View view, WordAdapterEntity recyclerViewEntity) {

            }
        });
        listWordsBinding.rvWords.setAdapter(pagedWordListAdapter);
        pagedWordListAdapter.submitList(adapterEntities);
    }

    private void handleWordClick(View view, WordAdapterEntity wordAdapterEntity) {
        switch (view.getId()) {
            case R.id.clWordContainer:
                openWordDetailsActivity(wordAdapterEntity);
                break;
            case R.id.ivDeleteWord:
                wordListViewModel.deleteWordById(wordAdapterEntity.getId());
                break;
            case R.id.ivEditWord:
                openEditWordActivity(wordAdapterEntity);
                break;
        }
    }

    private void openEditWordActivity(WordAdapterEntity wordAdapterEntity) {
        Bundle args = new Bundle();
        args.putInt("mode", WordConstants.EDIT_MODE);
        args.putInt("wordId", wordAdapterEntity.getId());
        Intent editWord = new Intent(this, AddWordActivity.class);
        editWord.putExtras(args);
        startActivity(editWord);
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
