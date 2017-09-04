package oicq.wlogin_sdk.a;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import oicq.wlogin_sdk.b.a;
import oicq.wlogin_sdk.request.u;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: QuickRegGetAccount */
public class b extends c {
    public b() {
        this.b = 17;
    }

    public byte[] a(long j, int i, byte b, byte[] bArr, byte[] bArr2, byte b2, byte[] bArr3, byte[] bArr4, int i2, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10) {
        if (bArr10 == null || bArr10.length == 0) {
            util.LOGI("token is null", "");
            return null;
        }
        int[] iArr = new int[]{2, 3, 6, 10, 13, 14, 18, 19, 20, 23, 24, 32};
        ArrayList arrayList = new ArrayList();
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i5 < length) {
            Object obj = new byte[0];
            a aVar;
            switch (iArr[i5]) {
                case 2:
                    aVar = new a(2);
                    aVar.a(i);
                    obj = aVar.a();
                    break;
                case 3:
                    aVar = new a(3);
                    aVar.a(b2);
                    obj = aVar.a();
                    break;
                case 6:
                    aVar = new a(6);
                    aVar.a(bArr3, bArr3.length);
                    obj = aVar.a();
                    break;
                case 10:
                    aVar = new a(10);
                    aVar.a(bArr4, bArr4.length);
                    obj = aVar.a();
                    break;
                case 13:
                    aVar = new a(13);
                    aVar.a(i2);
                    obj = aVar.a();
                    break;
                case 14:
                    aVar = new a(14);
                    aVar.a(bArr5, bArr5.length);
                    obj = aVar.a();
                    break;
                case 18:
                    aVar = new a(18);
                    aVar.a(bArr6, bArr6.length);
                    obj = aVar.a();
                    break;
                case 19:
                    aVar = new a(19);
                    aVar.a(bArr7, bArr7.length);
                    obj = aVar.a();
                    break;
                case 20:
                    aVar = new a(20);
                    aVar.a(bArr8, bArr8.length);
                    obj = aVar.a();
                    break;
                case 23:
                    aVar = new a(23);
                    aVar.a((byte) 5);
                    obj = aVar.a();
                    break;
                case 24:
                    aVar = new a(24);
                    aVar.a(u.J, u.J.length);
                    obj = aVar.a();
                    break;
                case 32:
                    aVar = new a(32);
                    aVar.a(bArr9, bArr9.length);
                    obj = aVar.a();
                    break;
            }
            if (obj.length > 2) {
                i3++;
                i4 += obj.length;
                arrayList.add(obj);
            }
            int i6 = i4;
            i5++;
            i3 = i3;
            i4 = i6;
        }
        Object obj2 = new byte[(i4 + 1)];
        util.int8_to_buf(obj2, 0, i3);
        i5 = 1;
        for (i4 = 0; i4 < i3; i4++) {
            byte[] bArr11 = (byte[]) arrayList.get(i4);
            System.arraycopy(bArr11, 0, obj2, i5, bArr11.length);
            i5 += bArr11.length;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(bArr10.length);
            dataOutputStream.write(bArr10);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeLong(j);
            dataOutputStream.writeInt(i);
            dataOutputStream.writeByte(b);
            dataOutputStream.writeShort(bArr.length);
            dataOutputStream.write(bArr);
            byte[] encrypt = cryptor.encrypt(obj2, 0, obj2.length, bArr2);
            if (encrypt == null) {
                util.LOGI("encrypt failed", "");
                return null;
            }
            dataOutputStream.writeShort(encrypt.length);
            dataOutputStream.write(encrypt);
            encrypt = byteArrayOutputStream.toByteArray();
            dataOutputStream.close();
            byteArrayOutputStream.close();
            return a(encrypt);
        } catch (Exception e) {
            util.LOGI("getRequest failed " + e.getMessage(), "" + j);
            return null;
        }
    }
}
