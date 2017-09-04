package com.tencent.av.opengl.ui;

import java.util.Comparator;

class GLViewGroup$SortComparator implements Comparator<GLView> {
    final /* synthetic */ GLViewGroup this$0;

    GLViewGroup$SortComparator(GLViewGroup gLViewGroup) {
        this.this$0 = gLViewGroup;
    }

    public int compare(GLView gLView, GLView gLView2) {
        if (gLView.getZOrder() >= gLView2.getZOrder()) {
            return 1;
        }
        return -1;
    }
}
