package org.apache.commons.compress.archivers.zip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipException;

/* compiled from: ExtraFieldUtils */
public class c {
    private static final Map<ZipShort, Class<?>> a = new ConcurrentHashMap();

    /* compiled from: ExtraFieldUtils */
    public static final class a {
        public static final a a = new a(0);
        public static final a b = new a(1);
        public static final a c = new a(2);
        private final int d;

        private a(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }
    }

    static {
        a(b.class);
        a(X5455_ExtendedTimestamp.class);
        a(X7875_NewUnix.class);
        a(f.class);
        a(j.class);
        a(i.class);
        a(m.class);
    }

    public static void a(Class<?> cls) {
        try {
            a.put(((r) cls.newInstance()).getHeaderId(), cls);
        } catch (ClassCastException e) {
            throw new RuntimeException(cls + " doesn't implement ZipExtraField");
        } catch (InstantiationException e2) {
            throw new RuntimeException(cls + " is not a concrete class");
        } catch (IllegalAccessException e3) {
            throw new RuntimeException(cls + "'s no-arg constructor is not public");
        }
    }

    public static r a(ZipShort zipShort) throws InstantiationException, IllegalAccessException {
        Class cls = (Class) a.get(zipShort);
        if (cls != null) {
            return (r) cls.newInstance();
        }
        r lVar = new l();
        lVar.a(zipShort);
        return lVar;
    }

    public static r[] a(byte[] bArr, boolean z, a aVar) throws ZipException {
        List arrayList = new ArrayList();
        int i = 0;
        while (i <= bArr.length - 4) {
            ZipShort zipShort = new ZipShort(bArr, i);
            int value = new ZipShort(bArr, i + 2).getValue();
            if ((i + 4) + value > bArr.length) {
                switch (aVar.a()) {
                    case 0:
                        throw new ZipException("bad extra field starting at " + i + ".  Block length of " + value + " bytes exceeds remaining" + " data of " + ((bArr.length - i) - 4) + " bytes.");
                    case 1:
                        break;
                    case 2:
                        k kVar = new k();
                        if (z) {
                            kVar.parseFromLocalFileData(bArr, i, bArr.length - i);
                        } else {
                            kVar.parseFromCentralDirectoryData(bArr, i, bArr.length - i);
                        }
                        arrayList.add(kVar);
                        break;
                    default:
                        throw new ZipException("unknown UnparseableExtraField key: " + aVar.a());
                }
                return (r[]) arrayList.toArray(new r[arrayList.size()]);
            }
            try {
                r a = a(zipShort);
                if (z) {
                    a.parseFromLocalFileData(bArr, i + 4, value);
                } else {
                    a.parseFromCentralDirectoryData(bArr, i + 4, value);
                }
                arrayList.add(a);
                i += value + 4;
            } catch (InstantiationException e) {
                throw new ZipException(e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new ZipException(e2.getMessage());
            }
        }
        return (r[]) arrayList.toArray(new r[arrayList.size()]);
    }

    public static byte[] a(r[] rVarArr) {
        int i;
        int i2 = (rVarArr.length <= 0 || !(rVarArr[rVarArr.length - 1] instanceof k)) ? 0 : 1;
        int length = i2 != 0 ? rVarArr.length - 1 : rVarArr.length;
        int i3 = length * 4;
        for (r localFileDataLength : rVarArr) {
            i3 += localFileDataLength.getLocalFileDataLength().getValue();
        }
        Object obj = new byte[i3];
        i3 = 0;
        for (i = 0; i < length; i++) {
            System.arraycopy(rVarArr[i].getHeaderId().getBytes(), 0, obj, i3, 2);
            System.arraycopy(rVarArr[i].getLocalFileDataLength().getBytes(), 0, obj, i3 + 2, 2);
            Object localFileDataData = rVarArr[i].getLocalFileDataData();
            System.arraycopy(localFileDataData, 0, obj, i3 + 4, localFileDataData.length);
            i3 += localFileDataData.length + 4;
        }
        if (i2 != 0) {
            Object localFileDataData2 = rVarArr[rVarArr.length - 1].getLocalFileDataData();
            System.arraycopy(localFileDataData2, 0, obj, i3, localFileDataData2.length);
        }
        return obj;
    }

    public static byte[] b(r[] rVarArr) {
        int i;
        int i2 = (rVarArr.length <= 0 || !(rVarArr[rVarArr.length - 1] instanceof k)) ? 0 : 1;
        int length = i2 != 0 ? rVarArr.length - 1 : rVarArr.length;
        int i3 = length * 4;
        for (r centralDirectoryLength : rVarArr) {
            i3 += centralDirectoryLength.getCentralDirectoryLength().getValue();
        }
        Object obj = new byte[i3];
        i3 = 0;
        for (i = 0; i < length; i++) {
            System.arraycopy(rVarArr[i].getHeaderId().getBytes(), 0, obj, i3, 2);
            System.arraycopy(rVarArr[i].getCentralDirectoryLength().getBytes(), 0, obj, i3 + 2, 2);
            Object centralDirectoryData = rVarArr[i].getCentralDirectoryData();
            System.arraycopy(centralDirectoryData, 0, obj, i3 + 4, centralDirectoryData.length);
            i3 += centralDirectoryData.length + 4;
        }
        if (i2 != 0) {
            Object centralDirectoryData2 = rVarArr[rVarArr.length - 1].getCentralDirectoryData();
            System.arraycopy(centralDirectoryData2, 0, obj, i3, centralDirectoryData2.length);
        }
        return obj;
    }
}
