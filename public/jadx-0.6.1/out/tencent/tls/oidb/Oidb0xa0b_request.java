package tencent.tls.oidb;

import com.tencent.mobileqq.pb.ByteStringMicro;
import java.nio.ByteBuffer;
import tencent.tls.oidb.OidbHead.OIDBHead;
import tencent.tls.oidb.cmd0xa0b.Oidb0Xa0b.ReqBody;

public class Oidb0xa0b_request {
    public static final int CMD = 2571;
    public static final byte ETX_C = (byte) 41;
    public static final int OPENACCOUNT = 1;
    public static final int ROLE = 6269;
    public static final int SELFACCOUNT = 2;
    public static final byte STX_C = (byte) 40;
    protected int dwBodyLen;
    protected int dwHeadLen;
    protected int service_type;

    public Oidb0xa0b_request(int i) {
        this.service_type = i;
    }

    public int getCmd() {
        return CMD;
    }

    public byte[] get_request(long j, int i, String str, String str2, String str3) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        OIDBHead oIDBHead = new OIDBHead();
        oIDBHead.uint32_command.set(CMD);
        oIDBHead.uint32_service_type.set(this.service_type);
        ReqBody reqBody = new ReqBody();
        reqBody.uint32_sdkappid.set((int) j);
        reqBody.str_appidat3rd.set(str2);
        reqBody.str_identifier.set(str);
        reqBody.bytes_usersig.set(ByteStringMicro.copyFrom(str3.getBytes()));
        reqBody.uint32_accounttype.set(i);
        this.dwHeadLen = oIDBHead.getSerializedSize();
        this.dwBodyLen = reqBody.getSerializedSize();
        ByteBuffer allocate = ByteBuffer.allocate(((this.dwHeadLen + 9) + this.dwBodyLen) + 1);
        allocate.put((byte) 40);
        allocate.putInt(this.dwHeadLen);
        allocate.putInt(this.dwBodyLen);
        allocate.put(oIDBHead.toByteArray());
        allocate.put(reqBody.toByteArray());
        allocate.put((byte) 41);
        return allocate.array();
    }
}
