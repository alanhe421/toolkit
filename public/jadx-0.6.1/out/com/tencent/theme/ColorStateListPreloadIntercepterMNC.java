package com.tencent.theme;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.util.LongSparseArray;

@TargetApi(16)
class ColorStateListPreloadIntercepterMNC extends ColorStateListPreloadIntercepter {
    public ColorStateListPreloadIntercepterMNC(SkinEngine skinEngine, Resources resources, LongSparseArray longSparseArray, Class cls, int i) {
        super(skinEngine, resources, longSparseArray, cls, i);
    }

    public Object get(long j) {
        Integer num = (Integer) this.mKeyToResourcesId.get(j);
        if (num == null) {
            return this.mOldPreloadCache.get(j);
        }
        SkinnableColorStateList loadColorStateList = this.mSkinEngine.loadColorStateList(num.intValue());
        if (loadColorStateList != null) {
            return loadColorStateList.mmFactory;
        }
        return null;
    }
}
