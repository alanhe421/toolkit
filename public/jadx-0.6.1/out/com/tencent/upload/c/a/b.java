package com.tencent.upload.c.a;

import FileCloud.DirListReq;
import com.qq.taf.jce.JceStruct;

public final class b extends com.tencent.upload.c.b {
    private String a;
    private int b;
    private int c;
    private boolean d;
    private String e;

    public b(String str, int i, int i2, boolean z, String str2) {
        this("CMD_DIR_LIST", str, i, i2, z, str2);
    }

    public b(String str, String str2, int i, int i2, boolean z, String str3) {
        super(str);
        this.a = str2;
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = str3;
    }

    protected final JceStruct h() {
        JceStruct dirListReq = new DirListReq();
        dirListReq.auth = i();
        dirListReq.startpath = this.a;
        dirListReq.num = (long) this.b;
        dirListReq.pattern = this.c;
        dirListReq.order = this.d;
        dirListReq.content = this.e;
        return dirListReq;
    }
}
