package com.tencent.upload.task;

import com.tencent.upload.task.ITask.TaskState;
import com.tencent.upload.task.data.FileInfo;

public interface IUploadTaskListener {
    void onUploadFailed(int i, String str);

    void onUploadProgress(long j, long j2);

    void onUploadStateChange(TaskState taskState);

    void onUploadSucceed(FileInfo fileInfo);
}
