package com.qlabs.qlabs.word.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.qlabs.qlabs.R;
import com.qlabs.qlabs.common.RecyclerViewClickHandler;
import com.qlabs.qlabs.common.RecyclerViewEntity;
import com.qlabs.qlabs.common.TextUtils;
import com.qlabs.qlabs.databinding.ActivitySearchBinding;
import com.qlabs.qlabs.word.adapter.WordListAdapter;
import com.qlabs.qlabs.word.model.SearchViewModel;
import com.qlabs.qlabs.word.model.entity.WordAdapterEntity;

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
        wordListAdapter = new WordListAdapter(adapterEntities, new RecyclerViewClickHandler() {
            @Override
            public void onItemClicked(RecyclerViewEntity recyclerViewEntity) {

            }

            @Override
            public void onItemLongClicked(RecyclerViewEntity recyclerViewEntity) {

            }
        });
        searchBinding.rvWords.setAdapter(wordListAdapter);
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
