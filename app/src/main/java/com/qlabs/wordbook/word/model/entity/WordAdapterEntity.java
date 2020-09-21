package com.qlabs.wordbook.word.model.entity;

import com.qlabs.wordbook.common.RecyclerViewEntity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class WordAdapterEntity extends RecyclerViewEntity {
    private int id;
    private String wordTitle;
    private String hint;
    private String meaning;
}
