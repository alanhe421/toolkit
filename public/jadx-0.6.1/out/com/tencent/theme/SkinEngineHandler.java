package com.tencent.theme;

public interface SkinEngineHandler {
    boolean onDecodeOOM(OutOfMemoryError outOfMemoryError, String str, boolean z);

    boolean onDecodeReturnNullBitmap(String str, boolean z);

    boolean onSecondDecodeOOM(OutOfMemoryError outOfMemoryError, String str, boolean z);
}
