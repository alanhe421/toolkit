package com.tencent.theme;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.SystemClock;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.TypedValue;

@TargetApi(16)
public class ColorStateListPreloadIntercepter extends LongSparseArray {
    LongSparseArray<Integer> mKeyToResourcesId;
    LongSparseArray<ColorStateList> mOldPreloadCache;
    SkinEngine mSkinEngine;

    public ColorStateListPreloadIntercepter(SkinEngine skinEngine, Resources resources, LongSparseArray longSparseArray, Class cls, int i) {
        this.mSkinEngine = skinEngine;
        this.mOldPreloadCache = longSparseArray;
        this.mKeyToResourcesId = new LongSparseArray(cls.getDeclaredFields().length + 10);
        long uptimeMillis = SystemClock.uptimeMillis();
        TypedValue typedValue = new TypedValue();
        while (true) {
            try {
                resources.getValue(i, typedValue, true);
                if (typedValue.type < 28 || typedValue.type > 31) {
                    if (typedValue.string.toString().endsWith(".xml")) {
                        this.mKeyToResourcesId.put((((long) typedValue.assetCookie) << 32) | ((long) typedValue.data), Integer.valueOf(i));
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

    public Object get(long j) {
        Integer num = (Integer) this.mKeyToResourcesId.get(j);
        if (num == null) {
            return this.mOldPreloadCache.get(j);
        }
        return this.mSkinEngine.loadColorStateList(num.intValue());
    }
}
