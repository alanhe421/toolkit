package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class FileUploadReq extends JceStruct {
    static byte[] cache_data;
    public String check_sum = "";
    public byte[] data = null;
    public long offset = 0;
    public String session = "";
    public long trailing_data = 0;

    public FileUploadReq(String str, long j, byte[] bArr, String str2, long j2) {
        this.session = str;
        this.offset = j;
        this.data = bArr;
        this.check_sum = str2;
        this.trailing_data = j2;
    }

    public final void readFrom(c cVar) {
        this.session = cVar.a(1, true);
        this.offset = cVar.a(this.offset, 2, true);
        if (cache_data == null) {
            byte[] bArr = new byte[1];
            cache_data = bArr;
            bArr[0] = (byte) 0;
        }
        this.data = cVar.a(cache_data, 3, true);
        this.check_sum = cVar.a(4, false);
        this.trailing_data = cVar.a(this.trailing_data, 5, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.session, 1);
        dVar.a(this.offset, 2);
        dVar.a(this.data, 3);
        if (this.check_sum != null) {
            dVar.a(this.check_sum, 4);
        }
        dVar.a(this.trailing_data, 5);
    }
}
