package com.tencent.upload.task.impl;

import FileCloud.FileMoveRsp;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.a;
import com.tencent.upload.c.a.e;
import com.tencent.upload.c.c;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.ICmdListener;
import com.tencent.upload.task.data.FileInfo;

public class FileCopyTask extends CommandTask {
    private FileMoveRsp mActionResponse = null;
    private boolean mDelSrc = false;
    private String mDstDir = "";
    private String mDstFileId;
    private final FileType mFileType;
    private IListener mListener = null;
    private String mSrcFileId;
    private String mSrcUrl = "";

    public interface IListener extends ICmdListener<FileInfo> {
    }

    public FileCopyTask(FileType fileType, String str, String str2, String str3, IListener iListener) {
        super(iListener);
        setBucket(str);
        this.mFileType = fileType;
        this.mListener = iListener;
        this.mSrcFileId = str2;
        this.mDstFileId = str3;
    }

    public FileType getFileType() {
        return this.mFileType;
    }

    public a getNetworkRequest() {
        return new e(this.mSrcUrl, this.mDstDir, this.mDelSrc, this.mFileType, getBucket(), this.mSrcFileId, this.mDstFileId);
    }

    public FileMoveRsp getResponse() {
        return this.mActionResponse;
    }

    public String getTag() {
        return "FileCopyTask";
    }

    public void onResponse(a aVar, c cVar) {
        this.mActionResponse = (FileMoveRsp) cVar.a();
        if (this.mActionResponse != null) {
            cVar.a = this.mActionResponse.result.ret;
            cVar.b = this.mActionResponse.result.msg;
            if (this.mListener != null) {
                if (this.mActionResponse.result.ret == 0) {
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.fileType = this.mFileType;
                    fileInfo.url = this.mActionResponse.url;
                    fileInfo.fileId = this.mActionResponse.fileid;
                    this.mListener.onSuccess(fileInfo);
                } else {
                    this.mListener.onFailure(this.mActionResponse.result.ret, this.mActionResponse.result.msg);
                }
            }
        }
        super.onResponse(aVar, cVar);
    }
}
