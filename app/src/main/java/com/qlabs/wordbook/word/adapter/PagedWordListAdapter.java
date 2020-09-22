package com.qlabs.wordbook.word.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qlabs.wordbook.databinding.AdapterWordBinding;
import com.qlabs.wordbook.word.model.entity.Word;
import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;

public class PagedWordListAdapter extends PagedListAdapter<Word, PagedWordListAdapter.WordViewHolder> {

    protected PagedWordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback) {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {

    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {

        private AdapterWordBinding adapterWordBinding;

        public WordViewHolder(@NonNull AdapterWordBinding wordBinding) {
            super(wordBinding.getRoot());
            this.adapterWordBinding = wordBinding;
        }

        public void bind(WordAdapterEntity wordAdapterEntity) {
            adapterWordBinding.setWordEntity(wordAdapterEntity);
            adapterWordBinding.executePendingBindings();
        }
    }

    private static DiffUtil.ItemCallback<Word> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Word>() {
                @Override
                public boolean areItemsTheSame(Word oldWord, Word newWord) {
                    return oldWord.getId() == newWord.getId();
                }

                @Override
                public boolean areContentsTheSame(Word oldWord,
                                                  Word newWord) {
                    return (oldWord.getWordTitle().equals(newWord.getWordTitle())
                            && oldWord.getHint().equals(newWord.getHint()));
                }
            };
}
