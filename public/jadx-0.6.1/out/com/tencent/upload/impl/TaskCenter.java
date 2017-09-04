package com.tencent.upload.impl;

import FileCloud.FileUploadRsp;
import android.text.TextUtils;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.c.c;
import com.tencent.upload.task.UploadTask;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskCenter {
    private static Map<String, UploadTask> mUploadTasks = new ConcurrentHashMap();

    public static void handleResponse(c cVar) {
        if (cVar != null && cVar.a() != null) {
            JceStruct a = cVar.a();
            if (a instanceof FileUploadRsp) {
                CharSequence charSequence = ((FileUploadRsp) a).session;
                if (!TextUtils.isEmpty(charSequence)) {
                    UploadTask uploadTask = (UploadTask) mUploadTasks.get(charSequence);
                    if (uploadTask != null) {
                        uploadTask.onResponse(null, cVar);
                    }
                }
            }
        }
    }

    public static void registTask(String str, UploadTask uploadTask) {
        if (uploadTask != null && str != null && !mUploadTasks.containsKey(str)) {
            mUploadTasks.put(str, uploadTask);
        }
    }

    public static void unregistTask(String str) {
        if (str != null) {
            mUploadTasks.remove(str);
        }
    }
}
