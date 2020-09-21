package com.qlabs.qlabs.word.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qlabs.qlabs.R;
import com.qlabs.qlabs.common.RecyclerViewClickHandler;
import com.qlabs.qlabs.databinding.AdapterWordBinding;
import com.qlabs.qlabs.word.model.entity.WordAdapterEntity;

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
        holder.bind(adapterEntities.get(position));
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
