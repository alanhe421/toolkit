package com.tencent.upload;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.Const.ServerEnv;
import com.tencent.upload.common.Global;
import com.tencent.upload.common.a.a;
import com.tencent.upload.impl.UploadManagerImpl;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.IUploadTaskListener;
import com.tencent.upload.task.UploadTask;
import com.tencent.upload.task.impl.PhotoUploadTask;
import java.util.Date;
import java.util.List;

public class UploadManager {
    private final String mAppId;
    private final FileType mFileType;
    private UploadManagerImpl mImpl;

    public UploadManager(Context context, String str, FileType fileType, String str2) {
        this.mAppId = str;
        this.mFileType = fileType;
        Global.init(context.getApplicationContext());
        this.mImpl = new UploadManagerImpl(fileType, str2);
    }

    private String getTag() {
        return "UploadManager_" + this.mFileType;
    }

    public static boolean uploadLog(String str, Date date, Date date2) {
        return UploadManagerImpl.uploadLog(str, date, date2);
    }

    public boolean cancel(int i) {
        return this.mImpl.cancel(i);
    }

    public boolean clear() {
        return this.mImpl.clear();
    }

    public void close() {
        this.mImpl.close();
    }

    public List<UploadTask> getUploadTasks() {
        return this.mImpl.getUploadTasks();
    }

    public boolean pause(int i) {
        return this.mImpl.pause(i);
    }

    public boolean pauseAll() {
        return this.mImpl.pauseAll();
    }

    public boolean resume(int i) {
        return this.mImpl.resume(i);
    }

    public boolean resumeAll() {
        return this.mImpl.resumeAll();
    }

    public boolean sendCommand(CommandTask commandTask) {
        if (commandTask == null) {
            return false;
        }
        commandTask.setAppid(this.mAppId);
        if (commandTask.checkTaskValidity(this.mFileType)) {
            return this.mImpl.sendCommand(commandTask);
        }
        a.d(getTag(), "add command task error. taskId=" + commandTask.getTaskId());
        return false;
    }

    public void setBackgroundMode(boolean z) {
        this.mImpl.setBackgroundMode(z);
    }

    public void setServerEnv(ServerEnv serverEnv) {
        this.mImpl.setServerEnv(serverEnv);
    }

    public boolean upload(UploadTask uploadTask) {
        if (uploadTask == null || !uploadTask.isDataSourceValid()) {
            return false;
        }
        uploadTask.setAppid(this.mAppId);
        if (uploadTask.checkTaskValidity(this.mFileType)) {
            a.b(getTag(), "add upload task success. taskId=" + uploadTask.getTaskId() + " path=" + uploadTask.getDataSource());
            return this.mImpl.upload(uploadTask);
        }
        a.d(getTag(), "add upload task error. taskId=" + uploadTask.getTaskId() + " path=" + uploadTask.getDataSource());
        return false;
    }

    public int uploadPhoto(String str, IUploadTaskListener iUploadTaskListener) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        UploadTask photoUploadTask = new PhotoUploadTask(str, iUploadTaskListener);
        return upload(photoUploadTask) ? photoUploadTask.getTaskId() : -1;
    }
}
