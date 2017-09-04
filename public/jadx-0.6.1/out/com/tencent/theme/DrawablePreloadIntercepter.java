package com.tencent.theme;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable.ConstantState;
import android.util.LongSparseArray;

@TargetApi(16)
public class DrawablePreloadIntercepter extends LongSparseArray<ConstantState> {
    private int mIndex;
    private DrawableLoader mLoader;

    public DrawablePreloadIntercepter(int i, DrawableLoader drawableLoader) {
        this.mIndex = i;
        this.mLoader = drawableLoader;
    }

    public ConstantState get(long j) {
        return this.mLoader.get(this.mIndex, j);
    }

    public void put(long j, ConstantState constantState) {
        this.mLoader.mOldPreloadCache[this.mIndex].put(j, constantState);
    }

    public int size() {
        return this.mLoader.mOldPreloadCache[this.mIndex].size();
    }

    public ConstantState valueAt(int i) {
        return (ConstantState) this.mLoader.mOldPreloadCache[this.mIndex].valueAt(i);
    }
}
