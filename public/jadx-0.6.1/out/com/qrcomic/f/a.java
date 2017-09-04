package com.qrcomic.f;

import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: ByteArrayPool */
public class a {
    protected static final Comparator<byte[]> a = new Comparator<byte[]>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((byte[]) obj, (byte[]) obj2);
        }

        public int a(byte[] bArr, byte[] bArr2) {
            return bArr.length - bArr2.length;
        }
    };
    private static a b = new a(ShareConstants.MD5_FILE_BUF_LENGTH);
    private static a c;
    private List<byte[]> d = new LinkedList();
    private List<byte[]> e = new ArrayList(64);
    private int f = 0;
    private final int g;

    public static a a() {
        return b;
    }

    public static a b() {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a(1024000);
                }
            }
        }
        return c;
    }

    public a(int i) {
        this.g = i;
    }

    public synchronized byte[] a(int i) {
        byte[] bArr;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            bArr = (byte[]) this.e.get(i2);
            if (bArr.length >= i) {
                this.f -= bArr.length;
                this.e.remove(i2);
                this.d.remove(bArr);
                break;
            }
        }
        bArr = new byte[i];
        return bArr;
    }

    public synchronized void a(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.g) {
                this.d.add(bArr);
                int binarySearch = Collections.binarySearch(this.e, bArr, a);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.e.add(binarySearch, bArr);
                this.f += bArr.length;
                c();
            }
        }
    }

    private synchronized void c() {
        while (this.f > this.g) {
            byte[] bArr = (byte[]) this.d.remove(0);
            this.e.remove(bArr);
            this.f -= bArr.length;
        }
    }
}
