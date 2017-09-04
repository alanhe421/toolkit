package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class FileControlReq extends JceStruct {
    static stAuth cache_auth;
    static byte[] cache_biz_req;
    static byte[] cache_data;
    static stEnvironment cache_env;
    static int cache_file_type;
    public stAuth auth = null;
    public byte[] biz_req = null;
    public String check_sum = "";
    public int check_type = 0;
    public int compress = 0;
    public byte[] data = null;
    public stEnvironment env = null;
    public long file_len = 0;
    public String file_name = "";
    public int file_type = 0;
    public String magic_context = "";
    public int preupload = 0;
    public String session = "";
    public long slice_size = 0;

    public FileControlReq(stAuth FileCloud_stAuth, String str, stEnvironment FileCloud_stEnvironment, int i, int i2, String str2, int i3, long j, long j2, int i4, String str3, String str4, byte[] bArr, byte[] bArr2) {
        this.auth = FileCloud_stAuth;
        this.session = str;
        this.env = FileCloud_stEnvironment;
        this.preupload = i;
        this.check_type = i2;
        this.check_sum = str2;
        this.file_type = i3;
        this.file_len = j;
        this.slice_size = j2;
        this.compress = i4;
        this.magic_context = str3;
        this.file_name = str4;
        this.data = bArr;
        this.biz_req = bArr2;
    }

    public final void readFrom(c cVar) {
        if (cache_auth == null) {
            cache_auth = new stAuth();
        }
        this.auth = (stAuth) cVar.a(cache_auth, 1, true);
        this.session = cVar.a(2, false);
        if (cache_env == null) {
            cache_env = new stEnvironment();
        }
        this.env = (stEnvironment) cVar.a(cache_env, 3, true);
        this.preupload = cVar.a(this.preupload, 4, false);
        this.check_type = cVar.a(this.check_type, 5, true);
        this.check_sum = cVar.a(6, true);
        this.file_type = cVar.a(this.file_type, 7, true);
        this.file_len = cVar.a(this.file_len, 8, true);
        this.slice_size = cVar.a(this.slice_size, 9, true);
        this.compress = cVar.a(this.compress, 10, false);
        this.magic_context = cVar.a(11, false);
        this.file_name = cVar.a(12, false);
        if (cache_data == null) {
            byte[] bArr = new byte[1];
            cache_data = bArr;
            bArr[0] = (byte) 0;
        }
        this.data = cVar.a(cache_data, 13, false);
        if (cache_biz_req == null) {
            bArr = new byte[1];
            cache_biz_req = bArr;
            bArr[0] = (byte) 0;
        }
        this.biz_req = cVar.a(cache_biz_req, 21, true);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.auth, 1);
        if (this.session != null) {
            dVar.a(this.session, 2);
        }
        dVar.a(this.env, 3);
        dVar.a(this.preupload, 4);
        dVar.a(this.check_type, 5);
        dVar.a(this.check_sum, 6);
        dVar.a(this.file_type, 7);
        dVar.a(this.file_len, 8);
        dVar.a(this.slice_size, 9);
        dVar.a(this.compress, 10);
        if (this.magic_context != null) {
            dVar.a(this.magic_context, 11);
        }
        if (this.file_name != null) {
            dVar.a(this.file_name, 12);
        }
        if (this.data != null) {
            dVar.a(this.data, 13);
        }
        dVar.a(this.biz_req, 21);
    }
}
