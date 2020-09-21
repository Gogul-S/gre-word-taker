package com.qlabs.wordbook.word.transformer;

import com.qlabs.wordbook.common.Transformer;
import com.qlabs.wordbook.word.model.entity.Word;
import com.qlabs.wordbook.word.model.entity.WordAdapterEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordTransformer implements Transformer<Word, WordAdapterEntity> {

    @Override
    public WordAdapterEntity transform(Word input) {
        return WordAdapterEntity.builder()
                .id(input.getId())
                .wordTitle(input.getWordTitle())
                .hint(input.getHint())
                .meaning(input.getMeaning())
                .build();
    }

    @Override
    public List<WordAdapterEntity> transform(Collection<Word> inputCollection) {
        List<WordAdapterEntity> adapterEntities = new ArrayList<>();
        if (inputCollection != null && !inputCollection.isEmpty()) {
            for (Word word : inputCollection) {
                adapterEntities.add(transform(word));
            }
        }
        return adapterEntities;
    }
}
