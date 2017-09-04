package com.tencent.upload.task;

import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.c;
import com.tencent.upload.impl.SessionManager;
import com.tencent.upload.network.b.a.a;

public interface ITask extends a {

    public interface TaskStateListener {
        void onTaskStateChanged(ITask iTask, TaskState taskState);
    }

    public enum TaskState {
        WAITING(0, "等待中"),
        CONNECTING(1, "连接中"),
        SENDING(2, "发送中"),
        PAUSE(3, "暂停"),
        CANCEL(4, "取消"),
        FAILED(5, "失败"),
        SUCCEED(6, "成功");
        
        private int code;
        private String desc;

        private TaskState(int i, String str) {
            this.code = i;
            this.desc = str;
        }

        public static TaskState getStateFromCode(int i) {
            switch (i) {
                case 0:
                    return WAITING;
                case 1:
                    return CONNECTING;
                case 2:
                    return SENDING;
                case 3:
                    return PAUSE;
                case 4:
                    return CANCEL;
                case 5:
                    return FAILED;
                case 6:
                    return SUCCEED;
                default:
                    return WAITING;
            }
        }

        public final int getCode() {
            return this.code;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String toString() {
            return "[" + this.code + "," + this.desc + "]";
        }
    }

    FileType getFileType();

    int getTaskId();

    TaskState getTaskState();

    void onCancel(int i);

    void onConnecting();

    void onError(int i, String str, boolean z);

    void onRequestSended(com.tencent.upload.c.a aVar);

    void onRequestTimeout(com.tencent.upload.c.a aVar);

    void onResponse(com.tencent.upload.c.a aVar, c cVar);

    boolean onSend(com.tencent.upload.network.b.a aVar);

    void run(SessionManager sessionManager, TaskStateListener taskStateListener);
}
