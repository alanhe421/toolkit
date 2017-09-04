package com.tencent.upload.impl;

import android.os.Handler;
import android.os.Looper;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.Const.RetCode;
import com.tencent.upload.Const.ServerEnv;
import com.tencent.upload.common.Global;
import com.tencent.upload.common.e;
import com.tencent.upload.common.e.a;
import com.tencent.upload.impl.TaskManager.TaskType;
import com.tencent.upload.network.a.k;
import com.tencent.upload.network.b.f;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.ITask;
import com.tencent.upload.task.ITask.TaskState;
import com.tencent.upload.task.ITask.TaskStateListener;
import com.tencent.upload.task.UploadTask;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SessionManager implements a, TaskStateListener {
    private ITask mCurrTask;
    private final FileType mFileType;
    private Handler mHandler;
    private TaskProcessListener mListener;
    private boolean mNetworkConnected = false;
    private f mSessionPool;
    private TaskType mTaskType;

    public interface TaskProcessListener {
        void onTaskFinished(ITask iTask);

        void onTaskInfoChanged(ITask iTask);
    }

    public SessionManager(TaskProcessListener taskProcessListener, Looper looper, FileType fileType, TaskType taskType, ServerEnv serverEnv) {
        this.mListener = taskProcessListener;
        this.mTaskType = taskType;
        this.mFileType = fileType;
        this.mHandler = new Handler(looper);
        e.a().a((a) this);
    }

    private void doExecuteTask(ITask iTask) {
        com.tencent.upload.common.a.a.c(getTag(), "execute Task. taskId=" + iTask.getTaskId() + " type=" + iTask.getClass().getSimpleName());
        this.mCurrTask = iTask;
        if (iTask instanceof CommandTask) {
            ((CommandTask) iTask).setSessionManager(this, this);
        }
        getSession();
        this.mCurrTask.onConnecting();
        this.mCurrTask.run(this, this);
    }

    private void doSendRequest(com.tencent.upload.c.a aVar, com.tencent.upload.network.b.a.a aVar2) {
        com.tencent.upload.network.b.a usefulSession = getUsefulSession();
        if (usefulSession != null) {
            if (!usefulSession.a(aVar, aVar2) && aVar2 != null) {
                if (e.b(Global.context)) {
                    aVar2.onRequestError(aVar, RetCode.SESSION_STATE_INVALID.getCode(), RetCode.SESSION_STATE_INVALID.getDesc());
                } else {
                    aVar2.onRequestError(aVar, RetCode.NETWORK_NOT_AVAILABLE.getCode(), RetCode.NETWORK_NOT_AVAILABLE.getDesc());
                }
            }
        } else if (aVar2 == null) {
        } else {
            if (e.b(Global.context)) {
                int code = RetCode.NO_SESSION.getCode();
                String desc = RetCode.NO_SESSION.getDesc();
                if (this.mSessionPool.b() != 0) {
                    code = this.mSessionPool.b();
                    desc = this.mSessionPool.c();
                }
                aVar2.onRequestError(aVar, code, desc);
                return;
            }
            aVar2.onRequestError(aVar, RetCode.NETWORK_NOT_AVAILABLE.getCode(), RetCode.NETWORK_NOT_AVAILABLE.getDesc());
        }
    }

    private List<com.tencent.upload.network.b.a> getSession() {
        List<com.tencent.upload.network.b.a> arrayList = new ArrayList();
        synchronized (this) {
            Collection a = this.mSessionPool.a(this.mTaskType, (long) BuglyBroadcastRecevier.UPLOADLIMITED);
            if (a != null) {
                arrayList.addAll(a);
            }
        }
        return arrayList;
    }

    private String getTag() {
        return "SessionManager_" + this.mFileType;
    }

    private void resumeCurrTask() {
        if (this.mCurrTask != null && this.mCurrTask.getTaskState() != TaskState.SENDING && this.mCurrTask.getTaskState() != TaskState.FAILED && this.mCurrTask.getTaskState() != TaskState.CANCEL) {
            doExecuteTask(this.mCurrTask);
        }
    }

    public void close() {
        this.mSessionPool.a(this.mTaskType);
    }

    public String getConnectedIp() {
        com.tencent.upload.network.b.a a = this.mSessionPool.a();
        return a == null ? null : a.e();
    }

    public ITask getCurrTask() {
        return this.mCurrTask;
    }

    public com.tencent.upload.network.b.a getIdleSession() {
        List<com.tencent.upload.network.b.a> session = getSession();
        if (session == null) {
            return null;
        }
        for (com.tencent.upload.network.b.a aVar : session) {
            if (aVar.i()) {
                return aVar;
            }
        }
        return null;
    }

    public k getUploadRoute() {
        com.tencent.upload.network.b.a a = this.mSessionPool.a();
        if (a == null) {
            return null;
        }
        a.f();
        return a.c();
    }

    public com.tencent.upload.network.b.a getUsefulSession() {
        List<com.tencent.upload.network.b.a> session = getSession();
        if (session == null || session.size() <= 0) {
            return null;
        }
        for (com.tencent.upload.network.b.a aVar : session) {
            if (aVar.i()) {
                return aVar;
            }
        }
        return (com.tencent.upload.network.b.a) session.get(0);
    }

    public boolean isIdle() {
        return getIdleSession() != null;
    }

    public void onNetworkConnect(boolean z) {
        if (this.mNetworkConnected != z) {
            com.tencent.upload.common.a.a.c(getTag(), "network connect:" + this.mNetworkConnected + " " + z);
            this.mNetworkConnected = z;
            if (this.mNetworkConnected) {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        SessionManager.this.resumeCurrTask();
                    }
                });
            }
        }
    }

    public void onTaskStateChanged(final ITask iTask, final TaskState taskState) {
        if (iTask != null) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    if (taskState == TaskState.SUCCEED || taskState == TaskState.FAILED || taskState == TaskState.PAUSE || taskState == TaskState.CANCEL) {
                        if (SessionManager.this.mListener != null) {
                            SessionManager.this.mListener.onTaskFinished(iTask);
                        }
                        if (taskState == TaskState.SUCCEED && (iTask instanceof UploadTask)) {
                            UploadTask uploadTask = (UploadTask) iTask;
                            if (uploadTask.getRoute() != null) {
                                SessionManager.this.mSessionPool.a(uploadTask.getRoute());
                            }
                        }
                        if (SessionManager.this.mCurrTask == iTask) {
                            SessionManager.this.mCurrTask = null;
                        }
                    }
                }
            });
        }
    }

    public void releaseCurrTask() {
        this.mCurrTask = null;
    }

    public void saveTaskInfo(ITask iTask) {
        if (this.mListener != null) {
            this.mListener.onTaskInfoChanged(iTask);
        }
    }

    public void sendRequest(final com.tencent.upload.c.a aVar, final com.tencent.upload.network.b.a.a aVar2) {
        if (aVar != null) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    SessionManager.this.doSendRequest(aVar, aVar2);
                }
            });
        }
    }

    public boolean sendTask(final ITask iTask) {
        if (iTask == null || this.mCurrTask != null) {
            return false;
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                SessionManager.this.doExecuteTask(iTask);
            }
        });
        return true;
    }

    public void setSessionPool(f fVar) {
        this.mSessionPool = fVar;
    }
}
