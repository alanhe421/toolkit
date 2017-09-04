package com.dynamicload.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.FrameLayout;
import com.dynamicload.Lib.DLPluginManager;

public class PluginViewHolderView extends FrameLayout {
    private int a;

    public PluginViewHolderView(Context context) {
        super(context);
        this.a = -1;
    }

    public PluginViewHolderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = -1;
        this.a = getId();
    }

    public PluginViewHolderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = -1;
        this.a = getId();
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = (Parcelable) sparseArray.get(this.a);
        if (parcelable != null && (parcelable instanceof Bundle)) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(DLPluginManager.getInstance().getPackage("com.qqreader.qqnews").classLoader);
            super.dispatchRestoreInstanceState(bundle.getSparseParcelableArray("pluginIns"));
        }
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        SparseArray sparseArray2 = new SparseArray();
        super.dispatchSaveInstanceState(sparseArray2);
        if (sparseArray2.size() > 0) {
            Bundle bundle = new Bundle(DLPluginManager.getInstance().getPackage("com.qqreader.qqnews").classLoader);
            bundle.putSparseParcelableArray("pluginIns", sparseArray2);
            sparseArray.put(this.a, bundle);
        }
    }
}
