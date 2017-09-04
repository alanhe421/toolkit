package com.tencent.upload.c.a;

import FileCloud.ObjectDeleteReq;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.c.b;

public final class i extends b {
    private String a;
    private int b;

    public i(String str, int i) {
        super("CMD_DIR_FILE_DELETE");
        this.a = str;
        this.b = i;
    }

    protected final JceStruct h() {
        JceStruct objectDeleteReq = new ObjectDeleteReq();
        objectDeleteReq.path = this.a;
        objectDeleteReq.type = this.b;
        objectDeleteReq.auth = i();
        return objectDeleteReq;
    }
}
