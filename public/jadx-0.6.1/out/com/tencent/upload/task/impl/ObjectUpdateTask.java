package com.tencent.upload.task.impl;

import FileCloud.FileDirUpdateRsp;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.a;
import com.tencent.upload.c.a.k;
import com.tencent.upload.c.c;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.ICmdListener;
import com.tencent.upload.task.ITaskRsp;
import com.tencent.upload.task.VideoAttr;

public class ObjectUpdateTask extends CommandTask {
    private FileDirUpdateRsp mActionResponse;
    private String mBizAttr;
    private final FileType mFileType;
    private IListener mListener;
    private int mModFlag;
    private String mPath;
    private int mType;
    private VideoAttr mVideoAttr;

    public static final class CmdTaskRsp extends ITaskRsp {
        public CmdTaskRsp(FileDirUpdateRsp fileDirUpdateRsp) {
            this.ret = fileDirUpdateRsp.result.ret;
            this.msg = fileDirUpdateRsp.result.msg;
        }
    }

    public interface IListener extends ICmdListener<CmdTaskRsp> {
    }

    public ObjectUpdateTask(FileType fileType, String str, String str2, int i, int i2, String str3, VideoAttr videoAttr, IListener iListener) {
        super(iListener);
        this.mListener = null;
        this.mActionResponse = null;
        this.mPath = str2;
        this.mType = i;
        this.mBizAttr = str3;
        setBucket(str);
        this.mModFlag = i2;
        this.mListener = iListener;
        this.mFileType = fileType;
        this.mVideoAttr = videoAttr;
    }

    public ObjectUpdateTask(FileType fileType, String str, String str2, int i, String str3, IListener iListener) {
        this(fileType, str, str2, i, 1, str3, null, iListener);
    }

    public FileType getFileType() {
        return this.mFileType;
    }

    public a getNetworkRequest() {
        return new k(getAbsolutePath(this.mPath), this.mType, this.mModFlag, this.mBizAttr, this.mVideoAttr);
    }

    public FileDirUpdateRsp getResponse() {
        return this.mActionResponse;
    }

    public String getTag() {
        return "ObjectUpdateTask";
    }

    public void onResponse(a aVar, c cVar) {
        this.mActionResponse = (FileDirUpdateRsp) cVar.a();
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
