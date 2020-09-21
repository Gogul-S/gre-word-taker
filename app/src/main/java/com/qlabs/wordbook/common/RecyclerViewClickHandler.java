package com.qlabs.wordbook.common;

import android.view.View;

public interface RecyclerViewClickHandler<T extends RecyclerViewEntity> {
    void onItemClicked(View view, T recyclerViewEntity);
    void onItemLongClicked(View view, T recyclerViewEntity);
}
