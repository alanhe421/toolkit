package com.tencent.upload.task.impl;

import FileCloud.DirListRsp;
import FileCloud.FileDirInfo;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.a;
import com.tencent.upload.c.a.b;
import com.tencent.upload.c.c;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.Dentry;
import com.tencent.upload.task.ICmdListener;
import com.tencent.upload.task.ITaskRsp;
import java.util.ArrayList;
import java.util.Iterator;

public class DirListTask extends CommandTask {
    private DirListRsp mActionResponse = null;
    private String mContent;
    private final FileType mFileType;
    private IListener mListener = null;
    private int mNum;
    private boolean mOrder;
    private int mPattern;
    private boolean mPrefixSearch;
    private String mStartPath;

    public static final class CmdTaskRsp extends ITaskRsp {
        public String content;
        public long dirCount;
        public long fileCount;
        public boolean hasMore;
        public ArrayList<Dentry> inodes = new ArrayList();

        public CmdTaskRsp(DirListRsp dirListRsp) {
            this.ret = dirListRsp.result.ret;
            this.msg = dirListRsp.result.msg;
            this.content = dirListRsp.content;
            this.hasMore = dirListRsp.hasmore;
            this.dirCount = dirListRsp.dir_count;
            this.fileCount = dirListRsp.file_count;
            Iterator it = dirListRsp.infos.iterator();
            while (it.hasNext()) {
                this.inodes.add(new Dentry((FileDirInfo) it.next()));
            }
        }
    }

    public interface IListener extends ICmdListener<CmdTaskRsp> {
    }

    public DirListTask(FileType fileType, String str, String str2, int i, int i2, boolean z, String str3, IListener iListener) {
        super(iListener);
        this.mNum = i;
        this.mOrder = z;
        setBucket(str);
        this.mPattern = i2;
        this.mContent = str3;
        this.mListener = iListener;
        this.mFileType = fileType;
        this.mStartPath = str2;
        this.mPrefixSearch = false;
    }

    public FileType getFileType() {
        return this.mFileType;
    }

    public a getNetworkRequest() {
        if (!prefixSearchIsEnable()) {
            return new b(getAbsolutePath(this.mStartPath), this.mNum, this.mPattern, this.mOrder, this.mContent);
        }
        this.mOrder = false;
        return new b("CMD_FTN_SEARCH_NAME", getAbsolutePath(this.mStartPath), this.mNum, this.mPattern, this.mOrder, this.mContent);
    }

    public DirListRsp getResponse() {
        return this.mActionResponse;
    }

    public String getTag() {
        return "DirListTask";
    }

    public void onResponse(a aVar, c cVar) {
        this.mActionResponse = (DirListRsp) cVar.a();
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

    public boolean prefixSearchIsEnable() {
        return this.mPrefixSearch;
    }

    public void setPrefixSearch(boolean z) {
        this.mPrefixSearch = z;
    }
}
