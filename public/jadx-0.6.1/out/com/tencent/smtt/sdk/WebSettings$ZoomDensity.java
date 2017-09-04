package com.tencent.smtt.sdk;

import com.tencent.tinker.android.dx.instruction.Opcodes;

public enum WebSettings$ZoomDensity {
    FAR(Opcodes.OR_INT),
    MEDIUM(100),
    CLOSE(75);
    
    int value;

    private WebSettings$ZoomDensity(int i) {
        this.value = i;
    }
}
