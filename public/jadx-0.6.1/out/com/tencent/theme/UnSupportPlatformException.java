package com.tencent.theme;

public class UnSupportPlatformException extends Exception {
    public UnSupportPlatformException(String str, Throwable th) {
        super(str, th);
    }

    public UnSupportPlatformException(String str) {
        super(str);
    }

    public UnSupportPlatformException(Throwable th) {
        super(th);
    }
}
