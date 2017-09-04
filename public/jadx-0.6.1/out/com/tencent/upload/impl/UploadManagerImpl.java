package com.tencent.upload.impl;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.Const.ServerEnv;
import com.tencent.upload.common.Global;
import com.tencent.upload.common.a.a;
import com.tencent.upload.impl.TaskManager.TaskType;
import com.tencent.upload.log.b;
import com.tencent.upload.network.b.f;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.ITask;
import com.tencent.upload.task.ITask.TaskState;
import com.tencent.upload.task.UploadTask;
import com.tencent.util.TimeFormatterUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UploadManagerImpl {
    private long mBackgroundIdleTimestamp = 0;
    private TaskManager mCommandTaskManager;
    private final FileType mFileType;
    private boolean mIsBackgroundMode = false;
    private Handler mMainHander = new Handler(Looper.getMainLooper());
    private String mPersistenceId;
    private ServerEnv mServerEnv;
    private f mSessionPool;
    private TaskManager mUploadTaskManager;
    private WakeLock mWakeLock = null;
    private WifiLock mWifiLock = null;

    public UploadManagerImpl(FileType fileType, String str) {
        this.mFileType = fileType;
        this.mServerEnv = ServerEnv.NORMAL;
        this.mPersistenceId = str;
        this.mSessionPool = new f(this.mFileType);
        this.mUploadTaskManager = new TaskManager(this.mPersistenceId, this.mFileType, TaskType.UPLOAD);
        this.mCommandTaskManager = new TaskManager(this.mPersistenceId, this.mFileType, TaskType.COMMON);
        changeServerEnv(this.mServerEnv);
    }

    private void acquireWakeLock() {
        int i = 1;
        String str = "upload_" + this.mPersistenceId;
        try {
            Context context = Global.context;
            if (this.mWakeLock == null) {
                this.mWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, str);
                this.mWakeLock.acquire();
                a.a(getTag(), "acquireWakeLock()");
            }
            if (this.mWifiLock == null) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (VERSION.SDK_INT >= 12) {
                    i = 3;
                }
                this.mWifiLock = wifiManager.createWifiLock(i, str);
                a.a(getTag(), "acquireWifiLock()");
            }
        } catch (Throwable e) {
            b.c(getTag(), "acquireWakeLock error.", e);
        }
    }

    private void changeServerEnv(ServerEnv serverEnv) {
        a.b(getTag(), "change server environment to:" + serverEnv.toString());
        this.mServerEnv = serverEnv;
        this.mSessionPool.a(this.mServerEnv);
        this.mUploadTaskManager.setServerEnv(serverEnv, this.mSessionPool);
        this.mCommandTaskManager.setServerEnv(serverEnv, this.mSessionPool);
    }

    private void checkBackgroundIdle() {
        this.mMainHander.postDelayed(new Runnable() {
            public void run() {
                if (UploadManagerImpl.this.mIsBackgroundMode) {
                    if (UploadManagerImpl.this.mUploadTaskManager.isBusy()) {
                        UploadManagerImpl.this.mBackgroundIdleTimestamp = SystemClock.elapsedRealtime();
                    } else if (SystemClock.elapsedRealtime() - UploadManagerImpl.this.mBackgroundIdleTimestamp >= TaskManager.IDLE_PROTECT_TIME) {
                        UploadManagerImpl.this.releaseWakeLock();
                        return;
                    }
                    UploadManagerImpl.this.checkBackgroundIdle();
                }
            }
        }, BuglyBroadcastRecevier.UPLOADLIMITED);
    }

    private String getTag() {
        return "UploadManagerImpl_" + this.mFileType;
    }

    private void releaseWakeLock() {
        if (this.mWakeLock != null && this.mWakeLock.isHeld()) {
            this.mWakeLock.release();
            this.mWakeLock = null;
            a.a(getTag(), "releaseWakeLock()");
        }
        if (this.mWifiLock != null && this.mWifiLock.isHeld()) {
            this.mWifiLock.release();
            this.mWifiLock = null;
            a.a(getTag(), "releaseWifiLock()");
        }
    }

    public static boolean uploadLog(String str, Date date, Date date2) {
        if (TextUtils.isEmpty(str) || date == null || date2 == null || date.getTime() > date2.getTime()) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(simpleDateFormat.format(date2));
            long time = simpleDateFormat.parse(simpleDateFormat.format(date)).getTime();
            long time2 = parse.getTime();
            for (long j = time; j <= time2; j += TimeFormatterUtils.ONE_DAY) {
                com.tencent.upload.b.a.b.a(str, new Date(j));
            }
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean cancel(int i) {
        ITask task = this.mUploadTaskManager.getTask(i);
        if (task == null || !(task instanceof UploadTask)) {
            return false;
        }
        UploadTask uploadTask = (UploadTask) task;
        boolean cancel = uploadTask.cancel();
        if (cancel) {
            this.mUploadTaskManager.removeTask(uploadTask);
        }
        return cancel;
    }

    public boolean clear() {
        List allTasks = this.mUploadTaskManager.getAllTasks();
        for (int size = allTasks.size() - 1; size >= 0; size--) {
            ITask iTask = (ITask) allTasks.get(size);
            if (iTask != null && (iTask instanceof UploadTask)) {
                ((UploadTask) iTask).cancel();
            }
        }
        this.mUploadTaskManager.removeAllTask();
        return true;
    }

    public void close() {
        releaseWakeLock();
        this.mUploadTaskManager.close();
        this.mCommandTaskManager.close();
        b.a();
    }

    public List<UploadTask> getUploadTasks() {
        List<ITask> allTasks = this.mUploadTaskManager.getAllTasks();
        List<UploadTask> arrayList = new ArrayList(allTasks.size());
        for (ITask iTask : allTasks) {
            if (iTask != null && (iTask instanceof UploadTask)) {
                arrayList.add((UploadTask) iTask);
            }
        }
        return arrayList;
    }

    public boolean pause(int i) {
        ITask task = this.mUploadTaskManager.getTask(i);
        return (task == null || !(task instanceof UploadTask)) ? false : ((UploadTask) task).pause();
    }

    public boolean pauseAll() {
        List allTasks = this.mUploadTaskManager.getAllTasks();
        for (int size = allTasks.size() - 1; size >= 0; size--) {
            ITask iTask = (ITask) allTasks.get(size);
            if (iTask != null && (iTask instanceof UploadTask)) {
                ((UploadTask) iTask).pause();
            }
        }
        return true;
    }

    public boolean resume(int i) {
        ITask task = this.mUploadTaskManager.getTask(i);
        if (task != null && (task instanceof UploadTask)) {
            UploadTask uploadTask = (UploadTask) task;
            if (uploadTask.getTaskState() == TaskState.PAUSE || uploadTask.getTaskState() == TaskState.FAILED) {
                uploadTask.resume();
                this.mUploadTaskManager.sendIfHasTask();
                return true;
            }
        }
        return false;
    }

    public boolean resumeAll() {
        for (ITask iTask : this.mUploadTaskManager.getAllTasks()) {
            if (iTask != null && (iTask instanceof UploadTask)) {
                UploadTask uploadTask = (UploadTask) iTask;
                if (uploadTask.getTaskState() == TaskState.PAUSE || uploadTask.getTaskState() == TaskState.FAILED) {
                    uploadTask.resume();
                }
            }
        }
        this.mUploadTaskManager.sendIfHasTask();
        return true;
    }

    public boolean sendCommand(CommandTask commandTask) {
        return this.mCommandTaskManager.sendAsync(commandTask);
    }

    public void setBackgroundMode(boolean z) {
        if (this.mIsBackgroundMode != z) {
            this.mIsBackgroundMode = z;
            a.b(getTag(), "setBackgroundMode " + this.mIsBackgroundMode);
            if (this.mIsBackgroundMode) {
                acquireWakeLock();
                this.mBackgroundIdleTimestamp = SystemClock.elapsedRealtime();
                checkBackgroundIdle();
                return;
            }
            releaseWakeLock();
            this.mBackgroundIdleTimestamp = 0;
        }
    }

    public void setServerEnv(ServerEnv serverEnv) {
        if (this.mServerEnv != serverEnv) {
            changeServerEnv(serverEnv);
        }
    }

    public boolean upload(UploadTask uploadTask) {
        return this.mUploadTaskManager.sendAsync(uploadTask);
    }
}
