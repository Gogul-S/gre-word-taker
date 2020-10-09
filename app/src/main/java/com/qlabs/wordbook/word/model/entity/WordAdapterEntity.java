package com.qlabs.wordbook.word.model.entity;

import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.qlabs.wordbook.R;
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

    @BindingAdapter("wordPreview")
    public static void wordPreview(TextView textView, WordAdapterEntity wordAdapterEntity) {
        textView.setBackgroundDrawable(ContextCompat.getDrawable(textView.getContext(), getDrawable(wordAdapterEntity)));
        textView.setText(String.format("%s", wordAdapterEntity.getWordTitle().toUpperCase().charAt(0)));
    }

    public static int getDrawable(WordAdapterEntity wordAdapterEntity) {
        int l = wordAdapterEntity.getViewPosition();
        switch (l % 4) {
            case 0:
                return R.drawable.background_blue_solid_circle;
            case 1:
                return R.drawable.background_green_solid_circle;
            case 2:
                return R.drawable.background_orange_solid_circle;
            case 3:
                return R.drawable.background_red_solid_circle;
            default:
                return R.drawable.background_grey_solid_circle;
        }

    }
}
