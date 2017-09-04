package com.tencent.theme;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;

public class ColorStateListPreloadIntercepter15 extends SparseArray<ColorStateList> {
    SparseArray<Integer> mKeyToResourcesId;
    SparseArray<ColorStateList> mOldPreloadCache;
    SkinEngine mSkinEngine;

    public ColorStateListPreloadIntercepter15(SkinEngine skinEngine, Resources resources, SparseArray<ColorStateList> sparseArray, Class cls, int i) {
        this.mSkinEngine = skinEngine;
        this.mOldPreloadCache = sparseArray;
        this.mKeyToResourcesId = new SparseArray(cls.getDeclaredFields().length + 10);
        long uptimeMillis = SystemClock.uptimeMillis();
        TypedValue typedValue = new TypedValue();
        while (true) {
            try {
                resources.getValue(i, typedValue, true);
                if (typedValue.type < 28 || typedValue.type > 31) {
                    if (typedValue.string.toString().endsWith(".xml")) {
                        this.mKeyToResourcesId.put((typedValue.assetCookie << 24) | typedValue.data, Integer.valueOf(i));
                    }
                    i++;
                } else {
                    i++;
                }
            } catch (NotFoundException e) {
                if (SkinEngine.DEBUG) {
                    Log.d(SkinEngine.TAG, "int ColorStateListPreloadIntercepter cost: " + (SystemClock.uptimeMillis() - uptimeMillis));
                    return;
                }
                return;
            }
        }
    }

    public ColorStateList get(int i) {
        Integer num = (Integer) this.mKeyToResourcesId.get(i);
        if (num == null) {
            return (ColorStateList) this.mOldPreloadCache.get(i);
        }
        return this.mSkinEngine.loadColorStateList(num.intValue());
    }
}
