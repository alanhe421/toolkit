package com.dynamicload.Lib;

public class DLException extends RuntimeException {
    public int errno;

    public DLException(String str, int i) {
        super(str);
        this.errno = i;
    }

    public DLException(String str) {
        super(str);
    }

    public String toString() {
        return "DLException: " + getMessage();
    }
}
