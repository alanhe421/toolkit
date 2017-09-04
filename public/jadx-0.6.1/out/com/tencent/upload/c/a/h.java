package com.tencent.upload.c.a;

import FileCloud.HandShakeReq;
import FileCloud.stEnvironment;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.b;
import com.tencent.upload.common.Global;

public final class h extends b {
    private boolean a;
    private String b;
    private FileType c;

    public h(FileType fileType, boolean z, String str) {
        super("CMD_HANDSHAKE");
        this.a = z;
        this.b = str;
        this.c = fileType;
    }

    protected final JceStruct h() {
        JceStruct handShakeReq = new HandShakeReq();
        handShakeReq.auth = i();
        handShakeReq.env = Global.getEnv();
        handShakeReq.type = b.b(this.c);
        handShakeReq.flag = 4;
        if (this.a) {
            handShakeReq.flag |= 1;
        }
        handShakeReq.last_update = this.b;
        return handShakeReq;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stEnvironment env = Global.getEnv();
        stringBuilder.append("taskId=").append(b()).append(" reqId=").append(c()).append(" cmd=").append(d()).append(" fileType=").append(j()).append(" redirect=").append(this.a).append(" last_update=").append(this.b).append(" deviceId=").append(env.device).append(" qua=").append(env.qua).append(" net=").append(env.net);
        return stringBuilder.toString();
    }
}
