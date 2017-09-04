package com.tencent.upload.task.impl;

import FileCloud.FileDeleteRsp;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.a;
import com.tencent.upload.c.a.d;
import com.tencent.upload.c.c;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.ICmdListener;

public class FileDeleteTask extends CommandTask {
    private FileDeleteRsp mActionResponse = null;
    private String mFileId;
    private final FileType mFileType;
    private IListener mListener = null;

    public interface IListener extends ICmdListener<Void> {
    }

    public FileDeleteTask(String str, FileType fileType, String str2, IListener iListener) {
        super(iListener);
        setBucket(str2);
        this.mFileId = str;
        this.mFileType = fileType;
        this.mListener = iListener;
    }

    public FileType getFileType() {
        return this.mFileType;
    }

    protected a getNetworkRequest() {
        return new d(this.mUrl, this.mFileId, this.mFileType, getBucket());
    }

    public FileDeleteRsp getResponse() {
        return this.mActionResponse;
    }

    public String getTag() {
        return "FileDeleteTask";
    }

    public void onResponse(a aVar, c cVar) {
        this.mActionResponse = (FileDeleteRsp) cVar.a();
        if (this.mActionResponse != null) {
            cVar.a = this.mActionResponse.result.ret;
            cVar.b = this.mActionResponse.result.msg;
            if (this.mListener != null) {
                if (this.mActionResponse.result.ret == 0) {
                    this.mListener.onSuccess(null);
                } else {
                    this.mListener.onFailure(this.mActionResponse.result.ret, this.mActionResponse.result.msg);
                }
            }
        }
        super.onResponse(aVar, cVar);
    }
}
