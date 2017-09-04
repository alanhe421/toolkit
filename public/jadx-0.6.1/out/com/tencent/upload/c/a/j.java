package com.tencent.upload.c.a;

import FileCloud.FileDirStatReq;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.c.b;

public final class j extends b {
    private String a;
    private int b;

    public j(String str, int i) {
        super("CMD_DIR_FILE_STAT");
        this.a = str;
        this.b = i;
    }

    protected final JceStruct h() {
        JceStruct fileDirStatReq = new FileDirStatReq();
        fileDirStatReq.path = this.a;
        fileDirStatReq.type = this.b;
        fileDirStatReq.auth = i();
        return fileDirStatReq;
    }
}
