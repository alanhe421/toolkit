package com.tencent.av.opengl.ui;

import java.util.Collections;

class GLViewGroup$1 implements GLView$OnZOrderChangedListener {
    final /* synthetic */ GLViewGroup this$0;

    GLViewGroup$1(GLViewGroup gLViewGroup) {
        this.this$0 = gLViewGroup;
    }

    public void OnZOrderChanged(GLView gLView, int i, int i2) {
        Collections.sort(GLViewGroup.access$000(this.this$0), GLViewGroup.access$100(this.this$0));
    }
}
