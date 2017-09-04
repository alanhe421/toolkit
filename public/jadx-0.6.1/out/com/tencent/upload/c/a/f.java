package com.tencent.upload.c.a;

import FileCloud.FileStatReq;
import android.text.TextUtils;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.b;

public final class f extends b {
    private String a;
    private String b;
    private FileType c;
    private String d;

    public f(String str, String str2, FileType fileType, String str3) {
        super("CMD_FILE_STAT");
        this.a = str;
        this.b = str2;
        this.c = fileType;
        this.d = str3;
    }

    protected final JceStruct h() {
        JceStruct fileStatReq = new FileStatReq();
        fileStatReq.auth = i();
        if (TextUtils.isEmpty(this.a)) {
            fileStatReq.fileid = this.b;
            fileStatReq.bucket = this.d;
            fileStatReq.type = b.b(this.c);
        } else {
            fileStatReq.url = this.a;
        }
        return fileStatReq;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("taskId=").append(b()).append(" reqId=").append(c()).append(" cmd=").append(d()).append(" fileType=").append(j()).append(" url=").append(this.a).append(" fileid=").append(this.b).append(" bucket=").append(this.d).append(" type=").append(this.c);
        return stringBuilder.toString();
    }
}
