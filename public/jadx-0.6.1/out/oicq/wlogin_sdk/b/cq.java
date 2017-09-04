package oicq.wlogin_sdk.b;

import java.nio.ByteBuffer;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: tlv_t511 */
public class cq extends b {
    public cq() {
        this.h = 1297;
    }

    public byte[] a(List<String> list) {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.putShort((short) list.size());
        for (String str : list) {
            String str2;
            if (!(str2 == null || str2.length() == 0)) {
                byte b;
                int indexOf = str2.indexOf(40);
                int indexOf2 = str2.indexOf(41);
                if (indexOf != 0 || indexOf2 <= 0) {
                    b = (byte) 1;
                } else {
                    int intValue = Integer.valueOf(str2.substring(indexOf + 1, indexOf2)).intValue();
                    indexOf = (1048576 & intValue) > 0 ? 1 : 0;
                    if ((intValue & SigType.WLOGIN_PT4Token) > 0) {
                        intValue = 1;
                    } else {
                        intValue = 0;
                    }
                    if (indexOf != 0) {
                        b = (byte) 1;
                    } else {
                        b = (byte) 0;
                    }
                    if (intValue != 0) {
                        b = (byte) (b | 2);
                    }
                    str2 = str2.substring(indexOf2 + 1);
                }
                allocate.put(b);
                allocate.putShort((short) str2.length());
                allocate.put(str2.getBytes());
            }
        }
        b(this.h);
        c(allocate.array(), allocate.position());
        e();
        return b();
    }
}
