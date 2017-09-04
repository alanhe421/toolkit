package com.tencent.widget;

import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;

public class AdapterView$AdapterContextMenuInfo implements ContextMenuInfo {
    public long id;
    public int position;
    public View targetView;

    public AdapterView$AdapterContextMenuInfo(View view, int i, long j) {
        this.targetView = view;
        this.position = i;
        this.id = j;
    }
}
