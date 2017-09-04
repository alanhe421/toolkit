package com.tencent.upload.task;

public interface ICmdListener<T> {
    void onFailure(int i, String str);

    void onSuccess(T t);
}
