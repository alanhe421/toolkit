package com.tencent.upload.c.a;

import FileCloud.FileMoveReq;
import android.text.TextUtils;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.b;

public final class e extends b {
    private String a;
    private String b;
    private boolean c;
    private String d;
    private String e;
    private FileType f;
    private String g;

    public e(String str, String str2, boolean z, FileType fileType, String str3, String str4, String str5) {
        super("CMD_FILE_MOVE");
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = str4;
        this.e = str5;
        this.f = fileType;
        this.g = str3;
    }

    protected final JceStruct h() {
        JceStruct fileMoveReq = new FileMoveReq();
        fileMoveReq.auth = i();
        fileMoveReq.dst_directory = this.b;
        fileMoveReq.delete_src = this.c ? 1 : 0;
        if (TextUtils.isEmpty(this.a)) {
            fileMoveReq.src_fileid = this.d;
            fileMoveReq.dst_fileid = this.e;
            fileMoveReq.bucket = this.g;
            fileMoveReq.type = b.b(this.f);
        } else {
            fileMoveReq.src_url = this.a;
        }
        return fileMoveReq;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("taskId=").append(b()).append(" reqId=").append(c()).append(" cmd=").append(d()).append(" fileType=").append(j()).append(" url=").append(this.a).append(" srcfileid=").append(this.d).append(" dstfileid=").append(this.e).append(" bucket=").append(this.g).append(" type=").append(this.f).append(" dir=").append(this.b).append(" delete=").append(this.c);
        return stringBuilder.toString();
    }
}
