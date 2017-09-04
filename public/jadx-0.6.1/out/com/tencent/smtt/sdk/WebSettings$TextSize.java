package com.tencent.smtt.sdk;

import com.tencent.tinker.android.dx.instruction.Opcodes;

public enum WebSettings$TextSize {
    SMALLEST(50),
    SMALLER(75),
    NORMAL(100),
    LARGER(Opcodes.NEG_LONG),
    LARGEST(Opcodes.OR_INT);
    
    int value;

    private WebSettings$TextSize(int i) {
        this.value = i;
    }
}
