package com.tencent.upload.task.impl;

import FileCloud.FileDirStatRsp;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.a;
import com.tencent.upload.c.a.j;
import com.tencent.upload.c.c;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.Dentry;
import com.tencent.upload.task.ICmdListener;
import com.tencent.upload.task.ITaskRsp;

public class ObjectStatTask extends CommandTask {
    private FileDirStatRsp mActionResponse = null;
    private final FileType mFileType;
    private IListener mListener = null;
    private String mPath;
    private int mType;

    public static final class CmdTaskRsp extends ITaskRsp {
        public Dentry inode;

        public CmdTaskRsp(FileDirStatRsp fileDirStatRsp) {
            this.ret = fileDirStatRsp.result.ret;
            this.msg = fileDirStatRsp.result.msg;
            this.inode = new Dentry(fileDirStatRsp.info);
        }
    }

    public interface IListener extends ICmdListener<CmdTaskRsp> {
    }

    public ObjectStatTask(FileType fileType, String str, String str2, int i, IListener iListener) {
        super(iListener);
        this.mPath = str2;
        this.mType = i;
        setBucket(str);
        this.mFileType = fileType;
        this.mListener = iListener;
    }

    public FileType getFileType() {
        return this.mFileType;
    }

    public a getNetworkRequest() {
        return new j(getAbsolutePath(this.mPath), this.mType);
    }

    public FileDirStatRsp getResponse() {
        return this.mActionResponse;
    }

    public String getTag() {
        return "ObjectStatTask";
    }

    public void onResponse(a aVar, c cVar) {
        this.mActionResponse = (FileDirStatRsp) cVar.a();
        if (this.mActionResponse != null) {
            cVar.a = this.mActionResponse.result.ret;
            cVar.b = this.mActionResponse.result.msg;
            if (this.mListener != null) {
                if (this.mActionResponse.result.ret == 0) {
                    this.mListener.onSuccess(new CmdTaskRsp(this.mActionResponse));
                } else {
                    this.mListener.onFailure(this.mActionResponse.result.ret, this.mActionResponse.result.msg);
                }
            }
        }
        super.onResponse(aVar, cVar);
    }
}
