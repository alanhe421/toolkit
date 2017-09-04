package com.qq.reader.qplugin.local;

import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.tencent.tinker.android.dx.instruction.Opcodes;

public class TingBookMark extends LocalMark {
    private static final long serialVersionUID = 1;
    private int mDrmFlag = 2;

    public TingBookMark(long j, String str) {
        super(8, String.valueOf(j), 0, str, false);
        this.mBookId = j;
    }

    public int getmDrmFlag() {
        return this.mDrmFlag;
    }

    public long getLasttime() {
        return this.mOperateTime;
    }

    public void setLasttime(long j) {
        this.mOperateTime = j;
    }

    public Mark setCoverUrl(String str) {
        this.mCoverUrl = ao.a(getBookId(), false, (int) Opcodes.OR_INT);
        return this;
    }

    public String getImageURI() {
        if (this.mCoverUrl == null || this.mCoverUrl.length() == 0) {
            this.mCoverUrl = ao.a(getBookId(), false, (int) Opcodes.OR_INT);
        }
        return this.mCoverUrl;
    }
}
