package com.qlabs.wordbook.word.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qlabs.wordbook.R;
import com.qlabs.wordbook.common.RecyclerViewClickHandler;
import com.qlabs.wordbook.databinding.AdapterWordBinding;
import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;

import java.util.ArrayList;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private List<WordAdapterEntity> adapterEntities = new ArrayList<>();
    private RecyclerViewClickHandler recyclerViewClickHandler;

    public WordListAdapter(@NonNull List<WordAdapterEntity> adapterEntities, RecyclerViewClickHandler recyclerViewClickHandler) {
        this.recyclerViewClickHandler = recyclerViewClickHandler;
        setAdapterEntities(adapterEntities);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterWordBinding adapterWordBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_word, parent, false);
        return new WordViewHolder(adapterWordBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        WordAdapterEntity wordAdapterEntity = adapterEntities.get(position);
        wordAdapterEntity.setViewPosition(position);
        holder.bind(wordAdapterEntity);
    }

    @Override
    public int getItemCount() {
        return adapterEntities.size();
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


    public void setAdapterEntities(List<WordAdapterEntity> adapterEntities) {
        setClickHandler(adapterEntities);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new WordsDiffUtil(adapterEntities, this.adapterEntities));
        diffResult.dispatchUpdatesTo(this);
        this.adapterEntities = adapterEntities;
    }

    private void setClickHandler(List<WordAdapterEntity> adapterEntities) {
        if (adapterEntities != null && !adapterEntities.isEmpty()) {
            for (WordAdapterEntity adapterEntity : adapterEntities) {
                adapterEntity.setRecyclerViewClickHandler(recyclerViewClickHandler);
            }
        }
    }
}
