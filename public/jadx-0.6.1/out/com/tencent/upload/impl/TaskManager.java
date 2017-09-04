package com.tencent.upload.impl;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.Const.ServerEnv;
import com.tencent.upload.common.Global;
import com.tencent.upload.common.a.a;
import com.tencent.upload.impl.SessionManager.TaskProcessListener;
import com.tencent.upload.network.b.f;
import com.tencent.upload.task.ITask;
import com.tencent.upload.task.ITask.TaskState;
import com.tencent.upload.task.UploadTask;
import com.tencent.upload.task.storage.StorageHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskManager implements TaskProcessListener {
    public static final long IDLE_PROTECT_TIME = 300000;
    private boolean isBusy;
    private final FileType mFileType;
    private Handler mHandler;
    private long mIdleTimestamp;
    private String mName;
    private String mPersistenceId;
    private ServerEnv mServerEnv;
    private SessionManager mSessionManager;
    private f mSessionPool;
    private StorageHelper mStorageHelper;
    private List<ITask> mTaskList;
    private TaskType mTaskType;
    private HandlerThread mThread;

    public enum TaskType {
        COMMON(0, "COMM"),
        UPLOAD(1, "UPLOAD");
        
        private int code;
        private String desc;

        private TaskType(int i, String str) {
            this.code = i;
            this.desc = str;
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

    public TaskManager(String str, FileType fileType, TaskType taskType) {
        this(str, fileType, taskType, ServerEnv.NORMAL);
    }

    public TaskManager(String str, FileType fileType, TaskType taskType, ServerEnv serverEnv) {
        this.mTaskList = new ArrayList();
        this.isBusy = false;
        this.mIdleTimestamp = System.currentTimeMillis();
        this.mFileType = fileType;
        this.mPersistenceId = str;
        this.mName = this.mPersistenceId + "_" + taskType.getDesc();
        this.mTaskType = taskType;
        this.mServerEnv = serverEnv;
        initTaskManager();
    }

    private String getTag() {
        return "TaskManager_" + this.mFileType;
    }

    private ITask getTask() {
        if (this.mTaskList.size() <= 0) {
            return null;
        }
        for (ITask iTask : this.mTaskList) {
            if (iTask.getTaskState() == TaskState.WAITING) {
                return iTask;
            }
        }
        return null;
    }

    private void initTaskManager() {
        this.mThread = new HandlerThread("thread_" + this.mName);
        this.mThread.start();
        this.mHandler = new Handler(this.mThread.getLooper());
        this.mSessionManager = new SessionManager(this, this.mThread.getLooper(), this.mFileType, this.mTaskType, this.mServerEnv);
        if (!TextUtils.isEmpty(this.mPersistenceId)) {
            this.mStorageHelper = new StorageHelper(Global.context, this.mName);
            Collection loadUploadTasks = this.mStorageHelper.loadUploadTasks();
            if (loadUploadTasks != null) {
                this.mTaskList.addAll(loadUploadTasks);
            }
        }
    }

    private void next() {
        ITask task = getTask();
        if (task == null) {
            this.isBusy = false;
            this.mIdleTimestamp = System.currentTimeMillis();
            return;
        }
        this.isBusy = true;
        this.mSessionManager.sendTask(task);
    }

    private void processTask(ITask iTask) {
        long currentTimeMillis = System.currentTimeMillis() - this.mIdleTimestamp;
        if (!isBusy() && currentTimeMillis > IDLE_PROTECT_TIME) {
            a.c(getTag(), "has been idle for " + (currentTimeMillis / 1000) + " seconds, need reconnect to server. taskType=" + this.mTaskType);
            this.mSessionPool.a(this.mTaskType);
        }
        this.mTaskList.add(iTask);
        if (this.mStorageHelper != null) {
            this.mStorageHelper.saveUploadTasks(this.mTaskList);
        }
        next();
    }

    private void setSessionPool(f fVar) {
        this.mSessionPool = fVar;
        if (this.mSessionManager != null) {
            this.mSessionManager.setSessionPool(this.mSessionPool);
        }
    }

    public void close() {
        if (this.mSessionManager != null) {
            this.mSessionManager.close();
        }
    }

    public List<ITask> getAllTasks() {
        return new ArrayList(this.mTaskList);
    }

    public Looper getLooper() {
        return this.mHandler.getLooper();
    }

    public ITask getTask(int i) {
        for (ITask iTask : new ArrayList(this.mTaskList)) {
            if (i == iTask.getTaskId()) {
                return iTask;
            }
        }
        return null;
    }

    public boolean isBusy() {
        if (this.isBusy) {
            return true;
        }
        for (ITask taskState : new ArrayList(this.mTaskList)) {
            if (taskState.getTaskState() == TaskState.WAITING) {
                return true;
            }
        }
        return false;
    }

    public void onTaskFinished(ITask iTask) {
        if (!(iTask instanceof UploadTask)) {
            this.mTaskList.remove(iTask);
        } else if ((iTask.getTaskState() == TaskState.SUCCEED || iTask.getTaskState() == TaskState.CANCEL) && this.mTaskList.remove(iTask) && this.mStorageHelper != null) {
            this.mStorageHelper.saveUploadTasks(this.mTaskList);
        }
        if (iTask == this.mSessionManager.getCurrTask()) {
            this.mSessionManager.releaseCurrTask();
            next();
        }
    }

    public void onTaskInfoChanged(ITask iTask) {
        if (this.mStorageHelper != null) {
            this.mStorageHelper.saveUploadTasks(this.mTaskList);
        }
    }

    public void removeAllTask() {
        this.mHandler.post(new Runnable() {
            public void run() {
                TaskManager.this.mTaskList.clear();
                if (TaskManager.this.mStorageHelper != null) {
                    TaskManager.this.mStorageHelper.saveUploadTasks(TaskManager.this.mTaskList);
                }
            }
        });
    }

    public void removeTask(final ITask iTask) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (TaskManager.this.mTaskList.remove(iTask) && TaskManager.this.mStorageHelper != null) {
                    TaskManager.this.mStorageHelper.saveUploadTasks(TaskManager.this.mTaskList);
                }
            }
        });
    }

    public boolean sendAsync(final ITask iTask) {
        return iTask == null ? false : this.mHandler.post(new Runnable() {
            public void run() {
                TaskManager.this.processTask(iTask);
            }
        });
    }

    public void sendIfHasTask() {
        if (!this.isBusy) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    TaskManager.this.next();
                }
            });
        }
    }

    public void setServerEnv(ServerEnv serverEnv, f fVar) {
        this.mServerEnv = serverEnv;
        setSessionPool(fVar);
        if (this.mSessionManager != null) {
            this.mSessionManager.close();
        }
        this.mSessionManager = new SessionManager(this, this.mThread.getLooper(), this.mFileType, this.mTaskType, this.mServerEnv);
        this.mSessionManager.setSessionPool(this.mSessionPool);
    }
}
