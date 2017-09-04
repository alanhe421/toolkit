package com.dynamicload.Lib;

import android.content.Context;
import android.view.View;

public abstract class DLBaseFragmenView {
    protected Context mContext;

    public abstract void applyTheme();

    public abstract void changeNetTipBar(boolean z);

    public abstract View getView();

    public abstract void onDestroy();

    public abstract void onPause();

    public abstract void onResume();

    public DLBaseFragmenView(Context context) {
        this.mContext = context;
    }

    public void onRetDotClick() {
    }

    public void doRefresh() {
    }
}
