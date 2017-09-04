package com.qq.reader.module.bookstore.qnative.storage.task;

import com.qq.reader.common.readertask.ordinal.ReaderShortTask;

public class BaseNativeDataTask extends ReaderShortTask {
    private static final long serialVersionUID = 1;
    private boolean isUseCache = true;

    public boolean isUseCache() {
        return this.isUseCache;
    }

    public void setIsUseCache(boolean z) {
        this.isUseCache = z;
    }
}
