package com.tencent.upload.c.a;

import FileCloud.DirCreateReq;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.c.b;

public final class a extends b {
    private String a;
    private String b;

    public a(String str, String str2) {
        super("CMD_DIR_CREATE");
        this.a = str;
        this.b = str2;
    }

    protected final JceStruct h() {
        JceStruct dirCreateReq = new DirCreateReq();
        dirCreateReq.path = this.a;
        dirCreateReq.biz_attr = this.b;
        dirCreateReq.auth = i();
        return dirCreateReq;
    }
}
