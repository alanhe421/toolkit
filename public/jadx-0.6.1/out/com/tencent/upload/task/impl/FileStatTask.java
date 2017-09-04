package com.tencent.upload.task.impl;

import FileCloud.FileStatRsp;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.a;
import com.tencent.upload.c.a.f;
import com.tencent.upload.c.b;
import com.tencent.upload.c.c;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.ICmdListener;
import com.tencent.upload.task.data.FileInfo;

public class FileStatTask extends CommandTask {
    private FileStatRsp mActionResponse = null;
    private String mFileId;
    private final FileType mFileType;
    private IListener mListener = null;

    public interface IListener extends ICmdListener<FileInfo> {
    }

    public FileStatTask(String str, FileType fileType, String str2, IListener iListener) {
        super(iListener);
        setBucket(str2);
        this.mFileId = str;
        this.mFileType = fileType;
        this.mListener = iListener;
    }

    private FileInfo actionRsp2ActoinRet() {
        if (this.mActionResponse.result.ret != 0 || this.mActionResponse.stats == null) {
            return null;
        }
        FileInfo fileInfo = new FileInfo();
        try {
            fileInfo.fileType = b.b(this.mActionResponse.stats.get("file_type") != null ? Integer.parseInt((String) this.mActionResponse.stats.get("file_type")) : 0);
            fileInfo.url = (String) this.mActionResponse.stats.get("file_url");
            fileInfo.extendInfo.putAll(this.mActionResponse.stats);
            return fileInfo;
        } catch (Throwable e) {
            com.tencent.upload.log.b.c(getTag(), "parser filestat response error.", e);
            return null;
        }
    }

    public FileType getFileType() {
        return this.mFileType;
    }

    public a getNetworkRequest() {
        return new f(this.mUrl, this.mFileId, this.mFileType, getBucket());
    }

    public FileStatRsp getResponse() {
        return this.mActionResponse;
    }

    public String getTag() {
        return "FileStatTask";
    }

    public void onResponse(a aVar, c cVar) {
        this.mActionResponse = (FileStatRsp) cVar.a();
        if (this.mActionResponse != null) {
            cVar.a = this.mActionResponse.result.ret;
            cVar.b = this.mActionResponse.result.msg;
            if (this.mListener != null) {
                FileInfo actionRsp2ActoinRet = actionRsp2ActoinRet();
                if (actionRsp2ActoinRet != null) {
                    this.mListener.onSuccess(actionRsp2ActoinRet);
                } else {
                    this.mListener.onFailure(this.mActionResponse.result.ret, this.mActionResponse.result.msg);
                }
            }
        }
        super.onResponse(aVar, cVar);
    }
}
