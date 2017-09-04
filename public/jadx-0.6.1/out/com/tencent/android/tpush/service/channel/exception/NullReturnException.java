package com.tencent.android.tpush.service.channel.exception;

/* compiled from: ProGuard */
public class NullReturnException extends Exception {
    private static final long serialVersionUID = -2623309261327598087L;
    private int statusCode = -1;

    public NullReturnException(String str) {
        super(str);
    }

    public NullReturnException(String str, Exception exception) {
        super(str, exception);
    }
}
