package com.qlabs.qlabs.common;

public class RecyclerViewEntity {
    private int viewPosition;
    private RecyclerViewClickHandler recyclerViewClickHandler;

    public int getViewPosition() {
        return viewPosition;
    }

    public void setViewPosition(int viewPosition) {
        this.viewPosition = viewPosition;
    }

    public RecyclerViewClickHandler getRecyclerViewClickHandler() {
        return recyclerViewClickHandler;
    }

    public void setRecyclerViewClickHandler(RecyclerViewClickHandler recyclerViewClickHandler) {
        this.recyclerViewClickHandler = recyclerViewClickHandler;
    }
}
