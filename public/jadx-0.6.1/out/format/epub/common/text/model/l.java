package format.epub.common.text.model;

import com.dynamicload.Lib.DLConstants;
import com.tencent.connect.common.Constants;
import format.epub.common.c.a.g;
import format.epub.common.image.ZLImageMap;
import format.epub.common.text.model.a.b;
import format.epub.common.text.model.a.d;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ZLTextPlainModel */
public class l implements i {
    protected final String a;
    protected final String b;
    protected int[] c;
    protected int[] d;
    protected int[] e;
    protected int[] f;
    protected byte[] g;
    protected int h;
    protected final b i;
    protected final ZLImageMap j;
    protected final Map<String, String> k = new HashMap();
    int l;
    private ArrayList<g> m;

    /* compiled from: ZLTextPlainModel */
    public final class a implements format.epub.common.text.model.j.a {
        int a;
        int b;
        final /* synthetic */ l c;
        private int d;
        private int e;
        private byte f;
        private char[] g;
        private int h;
        private int i;
        private byte j;
        private boolean k;
        private byte l;
        private String m;
        private d n;
        private n o;
        private d p;
        private short q;

        public a(l lVar, int i) {
            this.c = lVar;
            this.e = lVar.i.c(i).c;
            this.a = lVar.i.c(i).a;
            this.b = lVar.i.c(i).b;
        }

        public void a(int i) {
            if (i < this.c.h) {
                this.d = 0;
                this.e = this.c.i.c(i).c;
                this.a = this.c.i.c(i).a;
                this.b = this.c.i.c(i).b;
            }
        }

        public byte a() {
            return this.f;
        }

        public char[] b() {
            return this.g;
        }

        public int c() {
            return this.h;
        }

        public int d() {
            return this.i;
        }

        public byte e() {
            return this.j;
        }

        public boolean f() {
            return this.k;
        }

        public byte g() {
            return this.l;
        }

        public String h() {
            return this.m;
        }

        public d i() {
            return this.n;
        }

        public n j() {
            return this.o;
        }

        public short k() {
            return this.q;
        }

        public boolean l() {
            return this.d < this.e;
        }

        public void m() {
            char[] a;
            int i;
            byte b;
            int i2 = this.b;
            char[] a2 = this.c.i.a(this.a);
            if (i2 == a2.length) {
                b bVar = this.c.i;
                i2 = this.a + 1;
                this.a = i2;
                a2 = bVar.a(i2);
                i2 = 0;
            }
            short s = (short) a2[i2];
            byte b2 = (byte) s;
            if (b2 == (byte) 0) {
                bVar = this.c.i;
                i2 = this.a + 1;
                this.a = i2;
                a = bVar.a(i2);
                i = 0;
                i2 = (short) a[0];
                b = (byte) i2;
            } else {
                byte b3 = b2;
                a = a2;
                b = b3;
                short s2 = s;
                i = i2;
                short s3 = s2;
            }
            byte b4 = this.f;
            this.f = b;
            i++;
            int i3;
            short s4;
            String str;
            int i4;
            String str2;
            switch (b) {
                case (byte) 1:
                    i3 = i + 1;
                    i2 = a[i] << 16;
                    i = i3 + 1;
                    this.i = a[i3] + i2;
                    this.g = a;
                    this.h = i;
                    i += this.i;
                    break;
                case (byte) 2:
                    byte b5;
                    int i5;
                    String str3;
                    boolean z;
                    String str4;
                    int i6;
                    boolean z2;
                    String str5;
                    boolean z3;
                    i3 = i + 1;
                    s4 = (short) a[i];
                    int i7 = i3 + 1;
                    short s5 = (short) a[i3];
                    String str6 = new String(a, i7, s5);
                    String[] split = str6.split("\\|");
                    str6.indexOf(DLConstants.DEPENDENCY_PACKAGE_DIV);
                    String str7 = "";
                    String str8 = "";
                    if (split.length == 1) {
                        b5 = (byte) 0;
                        i5 = 0;
                        str3 = null;
                        z = false;
                        str4 = null;
                        i6 = 3;
                        z2 = false;
                        str = str8;
                        str5 = str6;
                        str8 = null;
                    } else {
                        String str9 = split[0];
                        str6 = null;
                        byte b6 = (byte) 0;
                        int i8 = 0;
                        str5 = null;
                        z3 = false;
                        boolean z4 = false;
                        String str10 = null;
                        i4 = 3;
                        String str11 = str8;
                        for (int i9 = 1; i9 < split.length; i9++) {
                            str3 = split[i9];
                            String substring = str3.substring(0, 1);
                            if (substring.equals("0")) {
                                str11 = str3.substring(1);
                                z3 = true;
                            } else if (substring.equals("1")) {
                                z4 = true;
                            } else if (substring.equals("2")) {
                                i4 = Integer.parseInt(str3.substring(1));
                            } else if (substring.equals("3")) {
                                str10 = str3.substring(1);
                            } else if (substring.equals("4")) {
                                try {
                                    i8 = Integer.valueOf(str3.substring(1)).intValue();
                                } catch (Exception e) {
                                }
                            } else if (substring.equals("5")) {
                                try {
                                    b6 = Byte.valueOf(str3.substring(1)).byteValue();
                                } catch (Exception e2) {
                                }
                            } else if (substring.equals(Constants.VIA_SHARE_TYPE_INFO)) {
                                str6 = str3.substring(1);
                            } else if (substring.equals("7")) {
                                str5 = str3.substring(1);
                            }
                        }
                        str8 = str6;
                        i5 = i8;
                        str3 = str5;
                        z = z3;
                        str4 = str10;
                        i6 = i4;
                        z2 = z4;
                        str5 = str9;
                        str = str11;
                        b5 = b6;
                    }
                    int i10 = i7 + s5;
                    z3 = false;
                    boolean z5 = false;
                    if (b4 == (byte) 3 && this.j == (byte) 10 && this.k) {
                        z3 = true;
                    }
                    if (b4 == (byte) 3 && this.j == (byte) 38 && this.k) {
                        z5 = true;
                    }
                    if (!(str4 == null && str8 == null)) {
                        z3 = false;
                    }
                    this.n = new d(this.c.j, this.c.k, str5, s4, z3, str, z5, z2);
                    this.n.g = i6;
                    this.n.h = str4;
                    this.n.i = str8;
                    this.n.a(z);
                    this.n.a(i5);
                    this.n.a(b5);
                    this.n.a(str3);
                    i = i10;
                    break;
                case (byte) 3:
                    i2 = i + 1;
                    s = (short) a[i];
                    this.j = (byte) s;
                    this.k = (s & 256) == 256;
                    this.l = (byte) (s >> 9);
                    if (this.l == (byte) 0) {
                        i = i2;
                        break;
                    }
                    i3 = i2 + 1;
                    s3 = (short) a[i2];
                    this.m = new String(a, i3, s3);
                    i = i3 + s3;
                    break;
                case (byte) 5:
                    i2 = i + 1;
                    this.q = (short) a[i];
                    i = i2;
                    break;
                case (byte) 6:
                case (byte) 9:
                    n aVar;
                    format.epub.common.c.a.g.a aVar2;
                    int i11;
                    s3 = (short) ((i2 >> 8) & 255);
                    if (b == (byte) 6) {
                        aVar = new format.epub.common.text.model.a.a(s3);
                    } else {
                        aVar = new b();
                    }
                    i2 = i + 1;
                    s4 = (short) a[i];
                    i = i2 + 1;
                    s3 = (short) a[i2];
                    int i12 = i + 1;
                    short s6 = (short) a[i];
                    i = i12 + 1;
                    short s7 = (short) a[i12];
                    int i13 = i + 1;
                    i4 = a[i];
                    i = i13 + 1;
                    char c = a[i13];
                    aVar.e(i4);
                    aVar.h(c);
                    int i14 = (s7 & 255) | ((((s3 & 255) << 16) | ((s4 & 255) << 24)) | ((s6 & 255) << 8));
                    i2 = i;
                    for (i = 0; i < 12; i++) {
                        if (n.a(i14, i)) {
                            i12 = i2 + 1;
                            s6 = (short) a[i2];
                            i2 = i12 + 1;
                            aVar.a(i, s6, (byte) a[i12]);
                        }
                    }
                    if (n.a(i14, 12) || n.a(i14, 16)) {
                        i = i2 + 1;
                        s3 = (short) a[i2];
                        if (n.a(i14, 12)) {
                            aVar.c((byte) (s3 & 255));
                        }
                        if (n.a(i14, 16)) {
                            aVar.c((byte) ((s3 >> 8) & 255));
                        }
                        i2 = i;
                    }
                    if (n.a(i14, 13)) {
                        i = i2 + 1;
                        s3 = (short) a[i2];
                        aVar.c(new String(a, i, s3));
                        i2 = s3 + i;
                    }
                    if (n.a(i14, 14)) {
                        i = i2 + 1;
                        s3 = (short) a[i2];
                        aVar.a((byte) (s3 & 255), (byte) ((s3 >> 8) & 255));
                        i2 = i;
                    }
                    if (n.a(i14, 15)) {
                        i = i2 + 1;
                        s3 = (short) a[i2];
                        aVar.d(new String(a, i, s3));
                        i2 = s3 + i;
                    }
                    if (n.a(i14, 22)) {
                        i = i2 + 1;
                        s3 = (short) a[i2];
                        aVar.e(new String(a, i, s3));
                        i2 = s3 + i;
                    }
                    if (n.a(i14, 24)) {
                        i = i2 + 1;
                        s3 = (short) a[i2];
                        str2 = new String(a, i, s3);
                        format.epub.common.b.b b7 = format.epub.common.b.b.b(str2);
                        if (b7 != null) {
                            aVar.a(format.epub.common.image.d.a().a(new format.epub.common.image.a("image/auto", b7)));
                        }
                        aVar.f(str2);
                        i2 = s3 + i;
                    }
                    if (n.a(i14, 23)) {
                        i = i2 + 1;
                        aVar.a((byte) a[i2]);
                        i2 = i;
                    }
                    if (n.a(i14, 26)) {
                        i = i2 + 1;
                        aVar.b((byte) a[i2]);
                        i2 = i;
                    }
                    format.epub.common.c.a.g.a[] aVarArr = new format.epub.common.c.a.g.a[]{new format.epub.common.c.a.g.a(), new format.epub.common.c.a.g.a(), new format.epub.common.c.a.g.a(), new format.epub.common.c.a.g.a()};
                    if (n.a(i14, 18)) {
                        aVar2 = aVarArr[0];
                        i11 = i2 + 1;
                        aVar2.a = (byte) a[i2];
                        i2 = i11 + 1;
                        aVar2.b = (short) a[i11];
                        i11 = i2 + 1;
                        aVar2.c = (byte) a[i2];
                        i2 = i11 + 1;
                        aVar2.d = (short) a[i11];
                        i11 = i2 + 1;
                        aVar2.e = (byte) a[i2];
                        i2 = i11 + 1;
                        s6 = (short) a[i11];
                        aVar2.f = new String(a, i2, s6);
                        i2 += s6;
                    }
                    if (n.a(i14, 19)) {
                        aVar2 = aVarArr[1];
                        i11 = i2 + 1;
                        aVar2.a = (byte) a[i2];
                        i2 = i11 + 1;
                        aVar2.b = (short) a[i11];
                        i11 = i2 + 1;
                        aVar2.c = (byte) a[i2];
                        i2 = i11 + 1;
                        aVar2.d = (short) a[i11];
                        i11 = i2 + 1;
                        aVar2.e = (byte) a[i2];
                        i2 = i11 + 1;
                        s6 = (short) a[i11];
                        aVar2.f = new String(a, i2, s6);
                        i2 += s6;
                    }
                    if (n.a(i14, 20)) {
                        aVar2 = aVarArr[2];
                        i11 = i2 + 1;
                        aVar2.a = (byte) a[i2];
                        i2 = i11 + 1;
                        aVar2.b = (short) a[i11];
                        i11 = i2 + 1;
                        aVar2.c = (byte) a[i2];
                        i2 = i11 + 1;
                        aVar2.d = (short) a[i11];
                        i11 = i2 + 1;
                        aVar2.e = (byte) a[i2];
                        i2 = i11 + 1;
                        s6 = (short) a[i11];
                        aVar2.f = new String(a, i2, s6);
                        i2 += s6;
                    }
                    if (n.a(i14, 21)) {
                        aVar2 = aVarArr[3];
                        i11 = i2 + 1;
                        aVar2.a = (byte) a[i2];
                        i2 = i11 + 1;
                        aVar2.b = (short) a[i11];
                        i11 = i2 + 1;
                        aVar2.c = (byte) a[i2];
                        i2 = i11 + 1;
                        aVar2.d = (short) a[i11];
                        i11 = i2 + 1;
                        aVar2.e = (byte) a[i2];
                        i2 = i11 + 1;
                        s6 = (short) a[i11];
                        aVar2.f = new String(a, i2, s6);
                        i2 += s6;
                    }
                    if (n.a(i14, 25)) {
                        g.b bVar2 = new g.b();
                        i12 = i2 + 1;
                        bVar2.a = (short) a[i2];
                        i2 = i12 + 1;
                        bVar2.b = (byte) a[i12];
                        i12 = i2 + 1;
                        bVar2.c = (short) a[i2];
                        i2 = i12 + 1;
                        bVar2.d = (byte) a[i12];
                        i12 = i2 + 1;
                        bVar2.e = (short) a[i2];
                        i2 = i12 + 1;
                        bVar2.f = (byte) a[i12];
                        i12 = i2 + 1;
                        s3 = (short) a[i2];
                        bVar2.g = new String(a, i12, s3);
                        i2 = s3 + i12;
                        aVar.a(bVar2);
                    }
                    aVar.a(aVarArr);
                    this.o = aVar;
                    break;
                case (byte) 7:
                    i2 = i;
                    break;
                case (byte) 11:
                    this.p = new d();
                    s4 = (short) a[i];
                    i++;
                    for (short s8 = (short) 0; s8 < s4; s8 = (short) (s8 + 1)) {
                        i2 = i + 1;
                        s = (short) a[i];
                        str2 = new String(a, i2, s);
                        i2 += s;
                        i = i2 + 1;
                        s3 = (short) a[i2];
                        str = new String(a, i, s3);
                        i += s3;
                        this.p.a(str2, str);
                    }
                    break;
            }
            i = i2;
            this.d++;
            this.b = i;
        }
    }

    protected l(String str, String str2, int i, int i2, String str3, String str4, ZLImageMap zLImageMap, int i3) {
        this.a = str;
        this.b = str2;
        this.l = i;
        this.i = new a(i2, i, str3, str4, i3);
        this.j = zLImageMap;
    }

    public final String a() {
        return this.a;
    }

    public final List<g> c() {
        return this.m != null ? this.m : Collections.emptyList();
    }

    public final int b() {
        return this.h;
    }

    public final j a(int i) {
        byte b = this.i.c(i).e;
        return b == (byte) 0 ? new k(this, i) : new m(b, this, i);
    }

    public final int b(int i) {
        int i2 = 0;
        try {
            return this.i.c(Math.max(Math.min(i, this.h - 1), 0)).d;
        } catch (Exception e) {
            return i2;
        }
    }

    private int a(int i, int i2) {
        int i3 = 0;
        int i4 = i - 1;
        while (i3 <= i4) {
            int i5 = (i3 + i4) >>> 1;
            int i6 = this.i.c(i5).d;
            if (i6 > i2) {
                i4 = i5 - 1;
            } else if (i6 >= i2) {
                return i5;
            } else {
                i3 = i5 + 1;
            }
        }
        return (-i3) - 1;
    }

    public final int c(int i) {
        int a = a(this.h, i);
        return a >= 0 ? a : Math.min((-a) - 1, this.h - 1);
    }

    public void d() {
        this.i.e();
    }

    public void a(DataInputStream dataInputStream) {
        System.currentTimeMillis();
        try {
            dataInputStream.readInt();
            this.h = dataInputStream.readInt();
            int readInt = dataInputStream.readInt();
            for (int i = 0; i < readInt; i++) {
                int readInt2 = dataInputStream.readInt();
                byte[] bArr = new byte[readInt2];
                dataInputStream.read(bArr, 0, readInt2);
                String str = new String(bArr, "UTF-8");
                int lastIndexOf = str.lastIndexOf(58);
                if (lastIndexOf != -1) {
                    this.k.put(format.epub.common.b.a.a(str.substring(lastIndexOf + 1)), str);
                } else {
                    format.epub.common.b.b b = format.epub.common.b.b.b(str);
                    if (b != null) {
                        String d = b.d();
                        this.j.put(d, new format.epub.common.image.a("image/auto", b));
                        this.k.put(d, str);
                    }
                }
            }
            this.i.d(dataInputStream.readInt());
            this.i.e(dataInputStream.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(DataOutputStream dataOutputStream) {
        this.i.b();
        this.i.d();
        try {
            dataOutputStream.writeInt(0);
            dataOutputStream.writeInt(this.h);
            Object[] toArray = this.j.values().toArray();
            dataOutputStream.writeInt(toArray.length);
            for (Object obj : toArray) {
                byte[] bytes = ((format.epub.common.image.b) obj).b().getBytes("UTF-8");
                dataOutputStream.writeInt(bytes.length);
                dataOutputStream.write(bytes);
            }
            dataOutputStream.writeInt(this.i.a());
            dataOutputStream.writeInt(this.i.f());
        } catch (Exception e) {
        }
    }
}
