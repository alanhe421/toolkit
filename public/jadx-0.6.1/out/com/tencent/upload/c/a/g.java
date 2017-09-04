package com.tencent.upload.c.a;

import FileCloud.FileUploadReq;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.c.b;
import com.tencent.upload.common.c;
import com.tencent.upload.task.data.UploadDataSource;

public final class g extends b {
    public long a = 0;
    private UploadDataSource b;
    private String c;
    private long d = 0;
    private long e = 0;
    private long f = 0;
    private boolean g = true;
    private byte[] h;

    public g(UploadDataSource uploadDataSource, String str, long j, long j2, boolean z) {
        super("CMD_FILE_UPLOAD");
        this.b = uploadDataSource;
        this.c = str;
        this.a = j;
        this.e = j2;
        this.d = this.b.getDataLength();
        this.f = this.d - this.a >= this.e ? this.e : this.d - this.a;
        this.g = z;
    }

    public final byte[] a() {
        Object a = super.a();
        if (a == null) {
            return a;
        }
        Object obj = new byte[((int) (((long) a.length) + this.f))];
        System.arraycopy(a, 0, obj, 0, a.length);
        if (this.h == null || this.h.length <= 0) {
            this.b.readData((long) ((int) this.a), (int) this.f, obj, a.length);
            return obj;
        }
        System.arraycopy(this.h, 0, obj, a.length, this.h.length);
        this.h = null;
        return obj;
    }

    public final boolean f() {
        return this.a + this.f >= this.d;
    }

    protected final JceStruct h() {
        JceStruct fileUploadReq = new FileUploadReq();
        fileUploadReq.session = this.c;
        fileUploadReq.offset = this.a;
        fileUploadReq.data = new byte[0];
        fileUploadReq.trailing_data = this.f;
        if (this.g) {
            byte[] bArr = new byte[((int) this.f)];
            this.b.readData(this.a, (int) this.f, bArr, 0);
            this.h = bArr;
            fileUploadReq.check_sum = c.a(fileUploadReq.data);
        }
        return fileUploadReq;
    }

    public final long k() {
        return this.f;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("taskId=").append(b()).append(" reqId=").append(c()).append(" cmd=").append(d()).append(" fileType=").append(j()).append(" session=").append(this.c).append(" fileSize=").append(this.d).append(" offset=").append(this.a).append(" dataSize=").append(this.f);
        return stringBuilder.toString();
    }
}
