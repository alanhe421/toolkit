package com.qrcomic.downloader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: QRComicByteArrayPool */
public class b {
    public static b a = new b(d);
    public static b b = new b(e);
    protected static final Comparator<byte[]> c = new Comparator<byte[]>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((byte[]) obj, (byte[]) obj2);
        }

        public int a(byte[] bArr, byte[] bArr2) {
            return bArr.length - bArr2.length;
        }
    };
    private static int d = 1048576;
    private static int e = 1048576;
    private List<byte[]> f = new LinkedList();
    private List<byte[]> g = new ArrayList(64);
    private int h = 0;
    private final int i;

    public b(int i) {
        this.i = i;
    }

    public synchronized byte[] a(int i) {
        byte[] bArr;
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            bArr = (byte[]) this.g.get(i2);
            if (bArr.length >= i) {
                this.h -= bArr.length;
                this.g.remove(i2);
                this.f.remove(bArr);
                break;
            }
        }
        bArr = new byte[i];
        return bArr;
    }

    public synchronized void a(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.i) {
                this.f.add(bArr);
                int binarySearch = Collections.binarySearch(this.g, bArr, c);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.g.add(binarySearch, bArr);
                this.h += bArr.length;
                a();
            }
        }
    }

    private synchronized void a() {
        while (this.h > this.i) {
            byte[] bArr = (byte[]) this.f.remove(0);
            this.g.remove(bArr);
            this.h -= bArr.length;
        }
    }
}
