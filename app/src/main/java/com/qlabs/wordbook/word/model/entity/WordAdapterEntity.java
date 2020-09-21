package com.qlabs.qlabs.word.model.entity;

import com.qlabs.qlabs.common.RecyclerViewEntity;

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
