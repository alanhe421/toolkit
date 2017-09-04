package format.epub.common.text.model;

import com.qq.reader.common.utils.ao;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* compiled from: ZLParagraphTableBlock */
public class f {
    int[] a;
    int[] b;
    int[] c;
    int[] d;
    byte[] e;
    int f;

    f(int i) {
        this.a = new int[i];
        this.b = new int[i];
        this.c = new int[i];
        this.d = new int[i];
        this.e = new byte[i];
    }

    public void a(DataOutputStream dataOutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ao.a(byteArrayOutputStream, 0);
        ao.a(byteArrayOutputStream, this.f);
        for (int i = 0; i < this.f; i++) {
            ao.a(byteArrayOutputStream, this.a[i]);
            ao.a(byteArrayOutputStream, this.b[i]);
            ao.a(byteArrayOutputStream, this.c[i]);
            ao.a(byteArrayOutputStream, this.d[i]);
            ao.a(byteArrayOutputStream, this.e[i]);
        }
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        int length = toByteArray.length;
        toByteArray[0] = (byte) ((length >> 24) & 255);
        toByteArray[1] = (byte) ((length >> 16) & 255);
        toByteArray[2] = (byte) ((length >> 8) & 255);
        toByteArray[3] = (byte) (length & 255);
        dataOutputStream.write(toByteArray);
    }

    public static f a(DataInputStream dataInputStream) throws IOException {
        byte[] bArr = new byte[dataInputStream.readInt()];
        dataInputStream.read(bArr);
        DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(bArr));
        int readInt = dataInputStream2.readInt();
        f fVar = new f(readInt);
        fVar.f = readInt;
        for (int i = 0; i < readInt; i++) {
            fVar.a[i] = dataInputStream2.readInt();
            fVar.b[i] = dataInputStream2.readInt();
            fVar.c[i] = dataInputStream2.readInt();
            fVar.d[i] = dataInputStream2.readInt();
            fVar.e[i] = (byte) dataInputStream2.readInt();
        }
        dataInputStream2.close();
        return fVar;
    }

    public e a(int i) {
        e eVar = new e();
        eVar.a = this.a[i];
        eVar.b = this.b[i];
        eVar.c = this.c[i];
        eVar.d = this.d[i];
        eVar.e = this.e[i];
        return eVar;
    }
}
