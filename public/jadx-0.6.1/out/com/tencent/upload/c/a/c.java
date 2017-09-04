package com.tencent.upload.c.a;

import FileCloud.FileControlReq;
import FileCloud.stPhotoUploadReq;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.c.b;
import com.tencent.upload.common.Global;
import com.tencent.upload.task.data.UploadDataSource;

public final class c extends b {
    public String a;
    private long b;
    private long c;
    private String d;
    private int e;
    private String f;
    private byte[] g;
    private long h;
    private String i;
    private int j;
    private JceStruct k;

    public c(String str, int i, String str2, String str3, long j, long j2) {
        super("CMD_FILE_CONTROL");
        this.d = str;
        this.e = i;
        this.f = str2;
        this.b = j;
        this.c = j2;
        this.a = str3;
    }

    public final void a(int i, JceStruct jceStruct) {
        this.j = i;
        this.k = jceStruct;
    }

    public final void a(UploadDataSource uploadDataSource, long j) {
        if (uploadDataSource != null) {
            try {
                long dataLength = uploadDataSource.getDataLength();
                if (dataLength < j) {
                    j = dataLength;
                }
                this.h = (long) ((int) j);
                byte[] bArr = new byte[((int) this.h)];
                uploadDataSource.readData(0, (int) this.h, bArr, 0);
                this.g = bArr;
            } catch (Throwable e) {
                com.tencent.upload.log.b.c("FileUploadRequest", "fill first slice file data error!", e);
            }
        }
    }

    public final void a(String str) {
        this.i = str;
    }

    protected final JceStruct h() {
        JceStruct fileControlReq = new FileControlReq();
        fileControlReq.auth = i();
        fileControlReq.env = Global.getEnv();
        fileControlReq.session = this.d;
        fileControlReq.preupload = 0;
        fileControlReq.check_type = this.e;
        fileControlReq.check_sum = this.f;
        fileControlReq.file_len = this.b;
        fileControlReq.slice_size = this.c;
        fileControlReq.file_name = this.a;
        fileControlReq.magic_context = this.i;
        fileControlReq.data = this.g;
        fileControlReq.file_type = this.j;
        fileControlReq.biz_req = b.a(this.k);
        return fileControlReq;
    }

    public final long k() {
        return this.h;
    }

    public final String toString() {
        String str = "";
        if (this.k != null && (this.k instanceof stPhotoUploadReq)) {
            str = ((stPhotoUploadReq) this.k).fileid;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("taskId=").append(b()).append(" reqId=").append(c()).append(" cmd=").append(d()).append(" fileType=").append(j()).append(" session=").append(this.d).append(" fileId=").append(str).append(" sha=").append(this.f).append(" fileLength=").append(this.b).append(" firstSliceData=").append(this.h);
        return stringBuilder.toString();
    }
}
