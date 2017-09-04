package com.qq.reader.module.question.record;

public class AudioException extends Exception {
    public static final int ERROR_UNKNOW = 2;
    public static final int FILE_ERROR = 1;
    public static final int IO_ERROR = 0;
    public int state = 0;

    public AudioException(int i, Throwable th) {
        super(th);
        this.state = i;
    }
}
