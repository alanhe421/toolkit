package com.tencent.av;

import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import java.nio.ByteBuffer;

public class NetworkUtil {
    private static final String tag = "av.NetworkUtil";

    public static byte[] formReq(String str, int i, int i2, String str2, byte[] bArr) {
        IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(str);
        if (msfUserInfo == null) {
            msfUserInfo = new IMMsfUserInfo();
        }
        short nextInt = (short) IMMsfCoreProxy.get().random.nextInt();
        short s = (short) i;
        long tinyid = msfUserInfo.getTinyid();
        byte[] bytes = "".getBytes();
        if (str2 != null) {
            bytes = str2.getBytes();
        }
        byte length = (byte) bytes.length;
        ByteBuffer allocate = ByteBuffer.allocate((((bytes.length + 11) + 4) + 10) + bArr.length);
        allocate.putShort((short) 0).putShort((short) 0).putShort(nextInt).putInt(0);
        allocate.putShort(s).putLong(tinyid).put(length).put(bytes).putInt(i2);
        allocate.put(bArr);
        allocate.flip();
        return allocate.array();
    }

    public static byte[] formReq(String str, int i, int i2, String str2, byte[] bArr, byte[] bArr2) {
        IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(str);
        if (msfUserInfo == null) {
            msfUserInfo = new IMMsfUserInfo();
        }
        short nextInt = (short) IMMsfCoreProxy.get().random.nextInt();
        short s = (short) i;
        long tinyid = msfUserInfo.getTinyid();
        byte[] bytes = "".getBytes();
        if (str2 != null) {
            bytes = str2.getBytes();
        }
        byte length = (byte) bytes.length;
        int length2 = (bytes.length + 11) + 4;
        int length3 = bArr.length;
        int length4 = bArr2.length;
        ByteBuffer allocate = ByteBuffer.allocate(((((((length2 + 10) + 1) + 4) + 4) + length3) + length4) + 1);
        allocate.putShort((short) 0).putShort((short) 0).putShort(nextInt).putInt(0);
        allocate.putShort(s).putLong(tinyid).put(length).put(bytes).putInt(i2);
        allocate.put((byte) 40).putInt(length3).putInt(length4).put(bArr).put(bArr2).put((byte) 41);
        allocate.flip();
        return allocate.array();
    }

    public static byte[] parseRsp(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.getShort();
        wrap.getShort();
        wrap.getShort();
        wrap.getInt();
        wrap.getShort();
        wrap.getInt();
        short s = wrap.getShort();
        if (s > wrap.remaining()) {
            return null;
        }
        wrap.get(new byte[s], 0, s);
        wrap.get();
        int i = wrap.getInt();
        int i2 = wrap.getInt();
        if (i > wrap.remaining()) {
            return null;
        }
        wrap.get(new byte[i], 0, i);
        if (i2 > wrap.remaining()) {
            return null;
        }
        byte[] bArr2 = new byte[i2];
        wrap.get(bArr2, 0, i2);
        return bArr2;
    }
}
