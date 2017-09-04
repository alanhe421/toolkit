package org.apache.a.a;

import java.util.Hashtable;
import java.util.Vector;
import java.util.zip.ZipException;

/* compiled from: ExtraFieldUtils */
public class b {
    static Class a;
    static Class b;
    private static Hashtable c = new Hashtable();

    static {
        Class a;
        if (a == null) {
            a = a("org.apache.a.a.a");
            a = a;
        } else {
            a = a;
        }
        a(a);
        if (b == null) {
            a = a("org.apache.a.a.c");
            b = a;
        } else {
            a = b;
        }
        a(a);
    }

    static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static void a(Class cls) {
        try {
            c.put(((f) cls.newInstance()).a(), cls);
        } catch (ClassCastException e) {
            throw new RuntimeException(new StringBuffer().append(cls).append(" doesn't implement ZipExtraField").toString());
        } catch (InstantiationException e2) {
            throw new RuntimeException(new StringBuffer().append(cls).append(" is not a concrete class").toString());
        } catch (IllegalAccessException e3) {
            throw new RuntimeException(new StringBuffer().append(cls).append("'s no-arg constructor is not public").toString());
        }
    }

    public static f a(j jVar) throws InstantiationException, IllegalAccessException {
        Class cls = (Class) c.get(jVar);
        if (cls != null) {
            return (f) cls.newInstance();
        }
        f dVar = new d();
        dVar.a(jVar);
        return dVar;
    }

    public static f[] a(byte[] bArr) throws ZipException {
        Vector vector = new Vector();
        int i = 0;
        while (i <= bArr.length - 4) {
            j jVar = new j(bArr, i);
            int b = new j(bArr, i + 2).b();
            if ((i + 4) + b > bArr.length) {
                throw new ZipException(new StringBuffer().append("data starting at ").append(i).append(" is in unknown format").toString());
            }
            try {
                f a = a(jVar);
                a.a(bArr, i + 4, b);
                vector.addElement(a);
                i += b + 4;
            } catch (InstantiationException e) {
                throw new ZipException(e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new ZipException(e2.getMessage());
            }
        }
        if (i != bArr.length) {
            throw new ZipException(new StringBuffer().append("data starting at ").append(i).append(" is in unknown format").toString());
        }
        f[] fVarArr = new f[vector.size()];
        vector.copyInto(fVarArr);
        return fVarArr;
    }

    public static byte[] a(f[] fVarArr) {
        int i;
        int length = fVarArr.length * 4;
        for (f b : fVarArr) {
            length += b.b().b();
        }
        Object obj = new byte[length];
        length = 0;
        for (i = 0; i < fVarArr.length; i++) {
            System.arraycopy(fVarArr[i].a().a(), 0, obj, length, 2);
            System.arraycopy(fVarArr[i].b().a(), 0, obj, length + 2, 2);
            Object d = fVarArr[i].d();
            System.arraycopy(d, 0, obj, length + 4, d.length);
            length += d.length + 4;
        }
        return obj;
    }

    public static byte[] b(f[] fVarArr) {
        int i;
        int length = fVarArr.length * 4;
        for (f c : fVarArr) {
            length += c.c().b();
        }
        Object obj = new byte[length];
        length = 0;
        for (i = 0; i < fVarArr.length; i++) {
            System.arraycopy(fVarArr[i].a().a(), 0, obj, length, 2);
            System.arraycopy(fVarArr[i].c().a(), 0, obj, length + 2, 2);
            Object e = fVarArr[i].e();
            System.arraycopy(e, 0, obj, length + 4, e.length);
            length += e.length + 4;
        }
        return obj;
    }
}
