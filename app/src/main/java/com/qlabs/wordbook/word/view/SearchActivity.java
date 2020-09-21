package com.qlabs.wordbook.word.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.qlabs.wordbook.R;
import com.qlabs.wordbook.common.RecyclerViewClickHandler;
import com.qlabs.wordbook.common.RecyclerViewEntity;
import com.qlabs.wordbook.common.TextUtils;
import com.qlabs.wordbook.databinding.ActivitySearchBinding;
import com.qlabs.wordbook.word.adapter.WordListAdapter;
import com.qlabs.wordbook.word.model.SearchViewModel;
import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;

import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding searchBinding;
    private SearchViewModel searchViewModel;
    private WordListAdapter wordListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        searchBinding.etSeachText.requestFocus();
        initViewModel();
        observeViewModel();
        initListeners();
    }

    private void observeViewModel() {
        searchViewModel.getSearchWordsLiveData().observe(this, this::populateWords);
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
        searchBinding.rvWords.setAdapter(wordListAdapter);
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
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
    }

    private void initListeners() {
        searchBinding.toolBarSearch.setNavigationOnClickListener(v -> finish());
        searchBinding.etSeachText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = editable.toString();
                if (TextUtils.checkForNullAndEmpty(searchText)) {
                    searchViewModel.searchWords(searchText);
                } else {
                    wordListAdapter.setAdapterEntities(Collections.emptyList());
                }
            }
        });
    }
}
