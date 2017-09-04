package com.tencent.widget;

import android.annotation.SuppressLint;
import android.view.ActionMode;
import android.view.ActionMode.Callback;

@SuppressLint({"NewApi"})
public interface AbsListView$MultiChoiceModeListener extends Callback {
    void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z);
}
