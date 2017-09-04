package com.tencent.upload.task.impl;

import FileCloud.DirCreateRsp;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.a;
import com.tencent.upload.c.c;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.ICmdListener;
import com.tencent.upload.task.ITaskRsp;

public class DirCreateTask extends CommandTask {
    private DirCreateRsp mActionResponse = null;
    private String mBizAttr;
    private final FileType mFileType;
    private IListener mListener = null;
    private String mPath;

    public static final class CmdTaskRsp extends ITaskRsp {
        public String accessUrl;
        public long ctime;
        public String path;

        public CmdTaskRsp(DirCreateRsp dirCreateRsp) {
            this.ret = dirCreateRsp.result.ret;
            this.msg = dirCreateRsp.result.msg;
            this.path = dirCreateRsp.path;
            this.ctime = dirCreateRsp.ctime;
            this.accessUrl = dirCreateRsp.access_url;
        }
    }

    public interface IListener extends ICmdListener<CmdTaskRsp> {
    }

    public DirCreateTask(FileType fileType, String str, String str2, String str3, IListener iListener) {
        super(iListener);
        this.mPath = str2;
        setBucket(str);
        this.mBizAttr = str3;
        this.mListener = iListener;
        this.mFileType = fileType;
    }

    public FileType getFileType() {
        return this.mFileType;
    }

    public a getNetworkRequest() {
        return new com.tencent.upload.c.a.a(getAbsolutePath(this.mPath), this.mBizAttr);
    }

    public DirCreateRsp getResponse() {
        return this.mActionResponse;
    }

    public String getTag() {
        return "DirCreateTask";
    }

    public void onResponse(a aVar, c cVar) {
        this.mActionResponse = (DirCreateRsp) cVar.a();
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
