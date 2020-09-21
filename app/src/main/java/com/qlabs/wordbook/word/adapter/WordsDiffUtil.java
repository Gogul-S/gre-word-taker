package com.qlabs.wordbook.word.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;

import java.util.List;

public class WordsDiffUtil extends DiffUtil.Callback {

    private List<WordAdapterEntity> oldList;
    private List<WordAdapterEntity> newList;

    public WordsDiffUtil(List<WordAdapterEntity> newList, List<WordAdapterEntity> oldList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        WordAdapterEntity oldItem = oldList.get(oldItemPosition);
        WordAdapterEntity newItem = newList.get(newItemPosition);
        return (oldItem.getWordTitle().equals(newItem.getWordTitle())
                && newItem.getHint().equals(oldItem.getHint()));
    }
}
