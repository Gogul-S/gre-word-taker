package com.qlabs.wordbook.word.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.qlabs.wordbook.R;
import com.qlabs.wordbook.common.RecyclerViewClickHandler;
import com.qlabs.wordbook.common.Transformer;
import com.qlabs.wordbook.databinding.AdapterWordBinding;
import com.qlabs.wordbook.word.model.entity.Word;
import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;
import com.qlabs.wordbook.word.transformer.WordTransformer;

public class PagedWordListAdapter extends PagedListAdapter<Word, WordListAdapter.WordViewHolder> {

    private WordTransformer wordTransformer;
    private RecyclerViewClickHandler recyclerViewClickHandler;

    public PagedWordListAdapter(WordTransformer transformer, RecyclerViewClickHandler recyclerViewClickHandler) {
        super(DIFF_CALLBACK);
        this.wordTransformer = transformer;
        this.recyclerViewClickHandler = recyclerViewClickHandler;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterWordBinding adapterWordBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_word, parent, false);
        return new WordListAdapter.WordViewHolder(adapterWordBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        Word word = getItem(position);
        if (word != null) {
            WordAdapterEntity adapterEntity = wordTransformer.transform(word);
            adapterEntity.setRecyclerViewClickHandler(recyclerViewClickHandler);
            adapterEntity.setViewPosition(position);
            holder.bind(adapterEntity);
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
