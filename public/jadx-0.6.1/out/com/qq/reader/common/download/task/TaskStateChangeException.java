package com.qq.reader.common.download.task;

public class TaskStateChangeException extends Exception {
    private static final long serialVersionUID = 1;
    private n context;

    public TaskStateChangeException(String str, Throwable th) {
        super(str, th);
    }

    public TaskStateChangeException(String str) {
        super(str);
    }

    public TaskStateChangeException(String str, n nVar) {
        super(str);
        this.context = nVar;
    }

    public TaskStateChangeException(Throwable th) {
        super(th);
    }

    public n getContext() {
        return this.context;
    }

    public void setContext(n nVar) {
        this.context = nVar;
    }
}
