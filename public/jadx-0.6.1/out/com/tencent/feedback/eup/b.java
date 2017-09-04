package com.tencent.feedback.eup;

import android.content.Context;
import android.os.Environment;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.common.PlugInInfo;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.d;
import com.tencent.feedback.common.e;
import com.tencent.feedback.proguard.a;
import com.tencent.feedback.proguard.j;
import com.tencent.feedback.proguard.l;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: RQDSRC */
public class b {
    private StringBuilder a;
    private int b = 0;

    private void a(String str) {
        for (int i = 0; i < this.b; i++) {
            this.a.append('\t');
        }
        if (str != null) {
            this.a.append(str).append(": ");
        }
    }

    public b(StringBuilder stringBuilder, int i) {
        this.a = stringBuilder;
        this.b = i;
    }

    public static boolean a(Context context, e eVar) {
        e.b("rqdp{  EUPDAO.insertEUP() start}", new Object[0]);
        if (context == null || eVar == null) {
            e.c("rqdp{  EUPDAO.insertEUP() have null args}", new Object[0]);
            return false;
        }
        try {
            boolean z;
            l a = a(eVar);
            if (context == null || a == null) {
                e.a("rqdp{  AnalyticsDAO.insert() have null args}", new Object[0]);
                z = false;
            } else {
                List arrayList = new ArrayList();
                arrayList.add(a);
                z = l.a(context, arrayList);
            }
            if (z) {
                eVar.a(a.a());
                e.b("rqdp{  EUPDAO.insertEUP() end}", new Object[0]);
                return true;
            }
            e.b("rqdp{  EUPDAO.insertEUP() end}", new Object[0]);
            return false;
        } catch (Throwable th) {
            e.b("rqdp{  EUPDAO.insertEUP() end}", new Object[0]);
        }
    }

    public b a(boolean z, String str) {
        a(str);
        this.a.append(z ? 'T' : 'F').append('\n');
        return this;
    }

    public b a(byte b, String str) {
        a(str);
        this.a.append(b).append('\n');
        return this;
    }

    public b a(char c, String str) {
        a(str);
        this.a.append(c).append('\n');
        return this;
    }

    public b a(short s, String str) {
        a(str);
        this.a.append(s).append('\n');
        return this;
    }

    public b a(int i, String str) {
        a(str);
        this.a.append(i).append('\n');
        return this;
    }

    public b a(long j, String str) {
        a(str);
        this.a.append(j).append('\n');
        return this;
    }

    public b a(float f, String str) {
        a(str);
        this.a.append(f).append('\n');
        return this;
    }

    public b a(double d, String str) {
        a(str);
        this.a.append(d).append('\n');
        return this;
    }

    public static int a(Context context, List<e> list) {
        e.b("rqdp{  EUPDAO.deleteEupList() start}", new Object[0]);
        if (context == null) {
            e.c("rqdp{  deleteEupList() have null args!}", new Object[0]);
            return -1;
        } else if (list.size() <= 0) {
            return 0;
        } else {
            Long[] lArr = new Long[list.size()];
            for (int i = 0; i < list.size(); i++) {
                lArr[i] = Long.valueOf(((e) list.get(i)).a());
            }
            return l.a(context, lArr);
        }
    }

    public b a(String str, String str2) {
        a(str2);
        if (str == null) {
            this.a.append("null\n");
        } else {
            this.a.append(str).append('\n');
        }
        return this;
    }

    public b a(byte[] bArr, String str) {
        a(str);
        if (bArr == null) {
            this.a.append("null\n");
        } else if (bArr.length == 0) {
            this.a.append(bArr.length).append(", []\n");
        } else {
            this.a.append(bArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (byte a : bArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public static int b(Context context) {
        e.b("rqdp{  EUPDAO.querySum() start}", new Object[0]);
        if (context == null) {
            e.c("rqdp{  querySum() context is null arg}", new Object[0]);
            return -1;
        }
        return l.a(context, new int[]{2, 1}, -1, Long.MAX_VALUE, null);
    }

    public b a(short[] sArr, String str) {
        a(str);
        if (sArr == null) {
            this.a.append("null\n");
        } else if (sArr.length == 0) {
            this.a.append(sArr.length).append(", []\n");
        } else {
            this.a.append(sArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (short a : sArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(int[] iArr, String str) {
        a(str);
        if (iArr == null) {
            this.a.append("null\n");
        } else if (iArr.length == 0) {
            this.a.append(iArr.length).append(", []\n");
        } else {
            this.a.append(iArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (int a : iArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(long[] jArr, String str) {
        a(str);
        if (jArr == null) {
            this.a.append("null\n");
        } else if (jArr.length == 0) {
            this.a.append(jArr.length).append(", []\n");
        } else {
            this.a.append(jArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (long a : jArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public static boolean b(Context context, List<e> list) {
        e.b("rqdp{  EUPDAO.insertOrUpdateEupList() start}", new Object[0]);
        if (context == null || list == null || list.size() <= 0) {
            e.c("rqdp{  context == null ||| list == null || list.size() <= 0,pls check}", new Object[0]);
            return false;
        }
        try {
            List arrayList = new ArrayList();
            for (e a : list) {
                l a2 = a(a);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            boolean b = l.b(context, arrayList);
            e.b("rqdp{  EUPDAO.insertOrUpdateEupList() end}", new Object[0]);
            return b;
        } catch (Throwable th) {
            e.b("rqdp{  EUPDAO.insertOrUpdateEupList() end}", new Object[0]);
        }
    }

    protected static boolean a(Context context, e eVar, CrashStrategyBean crashStrategyBean) {
        if (crashStrategyBean == null || !crashStrategyBean.isStoreCrashSdcard()) {
            return false;
        }
        try {
            e.b("save eup logs", new Object[0]);
            c a = c.a(context);
            String b = a.b();
            String B = a.B();
            String q = eVar.q();
            Locale locale = Locale.US;
            String str = "#--------\npackage:%s\nversion:%s\nsdk:%s\nprocess:%s\ndate:%s\ntype:%s\nmessage:%s\nstack:\n%s\neupID:%s\n";
            Object[] objArr = new Object[9];
            objArr[0] = b;
            objArr[1] = B;
            objArr[2] = a.c();
            objArr[3] = q;
            Date date = new Date(eVar.i());
            objArr[4] = date == null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
            objArr[5] = eVar.e();
            objArr[6] = eVar.f();
            objArr[7] = eVar.h();
            objArr[8] = eVar.v();
            b = String.format(locale, str, objArr);
            if (crashStrategyBean.getStoreDirectoryPath() != null) {
                File file = new File(crashStrategyBean.getStoreDirectoryPath());
                if (!file.isFile()) {
                    file = file.getParentFile();
                }
                a.a(new File(file, "euplog.txt").getAbsolutePath(), b, crashStrategyBean.getCrashSdcardMaxSize());
                return true;
            } else if (!com.tencent.feedback.common.a.f(context)) {
                return false;
            } else {
                String str2 = "/euplog.txt";
                int crashSdcardMaxSize = crashStrategyBean.getCrashSdcardMaxSize();
                e.b("rqdp{  sv sd start}", new Object[0]);
                if (b != null && b.trim().length() > 0) {
                    if (Environment.getExternalStorageState().equals("mounted")) {
                        a.a(new File(Environment.getExternalStorageDirectory(), "/Tencent/" + com.tencent.feedback.common.a.b(context) + str2).getAbsolutePath(), b, crashSdcardMaxSize);
                    }
                    e.b("rqdp{  sv sd end}", new Object[0]);
                }
                return true;
            }
        } catch (Throwable th) {
            e.c("rqdp{  save error} %s", th.toString());
            if (e.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public b a(float[] fArr, String str) {
        a(str);
        if (fArr == null) {
            this.a.append("null\n");
        } else if (fArr.length == 0) {
            this.a.append(fArr.length).append(", []\n");
        } else {
            this.a.append(fArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (float a : fArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(double[] dArr, String str) {
        a(str);
        if (dArr == null) {
            this.a.append("null\n");
        } else if (dArr.length == 0) {
            this.a.append(dArr.length).append(", []\n");
        } else {
            this.a.append(dArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (double a : dArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    protected static l a(e eVar) {
        if (eVar == null) {
            return null;
        }
        try {
            int i;
            l lVar = new l(eVar.P() != (byte) 1 ? 1 : 2, 0, eVar.i(), a.a((Object) eVar));
            lVar.b(eVar.j());
            lVar.a(eVar.m());
            lVar.a(eVar.o());
            lVar.a(eVar.a());
            if (eVar.w()) {
                i = 1;
            } else {
                i = 0;
            }
            lVar.c(i);
            return lVar;
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public <K, V> b a(Map<K, V> map, String str) {
        a(str);
        if (map == null) {
            this.a.append("null\n");
        } else if (map.isEmpty()) {
            this.a.append(map.size()).append(", {}\n");
        } else {
            this.a.append(map.size()).append(", {\n");
            b bVar = new b(this.a, this.b + 1);
            b bVar2 = new b(this.a, this.b + 2);
            for (Entry entry : map.entrySet()) {
                bVar.a('(', null);
                bVar2.a(entry.getKey(), null);
                bVar2.a(entry.getValue(), null);
                bVar.a(')', null);
            }
            a('}', null);
        }
        return this;
    }

    private static void a(ArrayList<String> arrayList, Throwable th, int i, int i2, int i3) {
        while (arrayList != null && th != null && i <= i2 && arrayList.size() <= i3) {
            i++;
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    arrayList.add(stackTraceElement.toString());
                }
            }
            if (th.getCause() != null) {
                arrayList.add("cause by:");
                arrayList.add(th.getCause().getClass().getName() + ": " + th.getCause().getMessage());
                th = th.getCause();
            } else {
                return;
            }
        }
    }

    public <T> b a(T[] tArr, String str) {
        a(str);
        if (tArr == null) {
            this.a.append("null\n");
        } else if (tArr.length == 0) {
            this.a.append(tArr.length).append(", []\n");
        } else {
            this.a.append(tArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (Object a : tArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    protected static String a(Throwable th, CrashStrategyBean crashStrategyBean) {
        int i = 100;
        int i2 = 3;
        if (crashStrategyBean != null) {
            i2 = Math.max(3, crashStrategyBean.getMaxStackFrame());
            i = Math.max(100, crashStrategyBean.getMaxStackLine());
            e.b("change frame:%d  line:%d", Integer.valueOf(i2), Integer.valueOf(i));
        }
        ArrayList arrayList = new ArrayList();
        a(arrayList, th, 0, i2, i);
        if (arrayList.size() <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuilder.append((String) it.next()).append("\n");
        }
        return stringBuilder.toString();
    }

    public <T> b a(Collection<T> collection, String str) {
        if (collection != null) {
            return a(collection.toArray(), str);
        }
        a(str);
        this.a.append("null\t");
        return this;
    }

    public static List<e> a(Context context, int i, String str, int i2, String str2, int i3, int i4, int i5, int i6, long j, long j2, Boolean bool) {
        e.b("rqdp{  EUPDAO.queryEupRecent() start}", new Object[0]);
        if (context == null || i == 0 || ((j2 > 0 && j > j2) || (i4 > 0 && i3 > i4))) {
            e.c("rqdp{  context == null || limitNum == 0 || (timeEnd > 0 && timeStart > timeEnd) || (maxCount > 0 && miniCount > maxCount ,pls check}", new Object[0]);
            return null;
        }
        int i7 = "asc".equals(str) ? 1 : 2;
        int[] iArr = null;
        if (i2 == 2) {
            iArr = new int[]{2};
        } else if (i2 == 1) {
            iArr = new int[]{1};
        } else if (i2 < 0) {
            iArr = new int[]{1, 2};
        } else {
            e.c("rqdp{  queryEupRecent() seletedRecordType unaccepted}", new Object[0]);
        }
        int i8 = bool == null ? -1 : bool.booleanValue() ? 1 : 0;
        List a = l.a(context, iArr, -1, i7, -1, i, str2, i3, i4, i5, i6, j, j2, i8);
        if (a == null || a.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            l lVar = (l) it.next();
            try {
                Object b = a.b(lVar.b());
                if (b != null && e.class.isInstance(b)) {
                    e eVar = (e) e.class.cast(b);
                    eVar.a(lVar.a());
                    arrayList.add(eVar);
                    it.remove();
                }
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                e.d("rqdp{  query have error!}", new Object[0]);
            }
        }
        if (a.size() > 0) {
            e.b("rqdp{  there are error datas ,should be remove }" + a.size(), new Object[0]);
            Long[] lArr = new Long[a.size()];
            for (int i9 = 0; i9 < a.size(); i9++) {
                lArr[i9] = Long.valueOf(((l) a.get(i9)).a());
            }
            l.a(context, lArr);
        }
        e.b("rqdp{  EUPDAO.queryEupRecent() end}", new Object[0]);
        return arrayList;
    }

    public <T> b a(T t, String str) {
        if (t == null) {
            this.a.append("null\n");
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            a((String) t, str);
        } else if (t instanceof Map) {
            a((Map) t, str);
        } else if (t instanceof List) {
            a((List) t, str);
        } else if (t instanceof j) {
            a((j) t, str);
        } else if (t instanceof byte[]) {
            a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            a((boolean[]) t, str);
        } else if (t instanceof short[]) {
            a((short[]) t, str);
        } else if (t instanceof int[]) {
            a((int[]) t, str);
        } else if (t instanceof long[]) {
            a((long[]) t, str);
        } else if (t instanceof float[]) {
            a((float[]) t, str);
        } else if (t instanceof double[]) {
            a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            a((Object[]) t, str);
        } else {
            throw new com.tencent.feedback.proguard.b("write object error: unsupport type.");
        }
        return this;
    }

    public static boolean a(Context context) {
        if (a.c() < 0) {
            e.d("rqdp{  today fail?}", new Object[0]);
            new Date().getTime();
        }
        List a = g.a(context).a(context, 1);
        if (a == null || a.size() <= 0) {
            return false;
        }
        return true;
    }

    public static e a(Context context, String str, String str2, long j, Map<String, PlugInInfo> map, String str3, String str4, String str5, String str6, String str7, String str8, long j2, String str9, byte[] bArr) {
        e eVar = new e();
        eVar.i(str3);
        eVar.j(str4);
        eVar.b(j2 + j);
        if (str9 != null && str9.length() > Constants.ERRORCODE_UNKNOWN) {
            try {
                str9 = str9.substring(str9.length() - 10000, str9.length());
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        if (bArr != null && bArr.length > Constants.ERRORCODE_UNKNOWN) {
            try {
                byte[] bArr2 = new byte[Constants.ERRORCODE_UNKNOWN];
                int length = bArr2.length - 1;
                int length2 = bArr.length - 1;
                while (length >= 0 && length2 >= 0) {
                    bArr2[length] = bArr[length2];
                    length--;
                    length2--;
                }
                bArr = bArr2;
            } catch (Throwable th2) {
                if (!e.a(th2)) {
                    th2.printStackTrace();
                }
            }
        }
        eVar.k(str9);
        eVar.b(bArr);
        eVar.c(str5);
        if (str7 == null || str7.trim().length() == 0) {
            str7 = "empty message";
        } else if (str7.length() > 1000) {
            str7 = str7.substring(0, 1000);
        }
        eVar.b(str7);
        eVar.a(str6);
        if (str8 == null || str8.trim().length() == 0) {
            str8 = "empty stack";
        }
        eVar.d(str8);
        eVar.a(-1.0f);
        d a = d.a(context);
        eVar.c(d.h());
        eVar.e(a.j());
        eVar.d(d.f());
        eVar.i(d.m());
        eVar.j(d.n());
        e.b("avram:%d,avsd:%d,avrom:%d,avstack:%d,avheap:%d", Long.valueOf(eVar.F()), Long.valueOf(eVar.H()), Long.valueOf(eVar.G()), Long.valueOf(eVar.L()), Long.valueOf(eVar.M()));
        c a2 = c.a(context);
        eVar.f(a2.t());
        eVar.g(a2.s());
        eVar.h(a2.u());
        eVar.r(a2.B());
        eVar.s(a2.z());
        e.b("tram:%d,trom:%d,tsd:%d,v:%s,cn:%s", Long.valueOf(eVar.I()), Long.valueOf(eVar.J()), Long.valueOf(eVar.K()), eVar.N(), eVar.O());
        eVar.e(str);
        eVar.q(str2);
        eVar.a((Map) map);
        eVar.l(a.d());
        eVar.m(a.c("ro.build.fingerprint"));
        eVar.k(eVar.i() - a2.D());
        e.b("record id:%s", eVar.v());
        e.b("rom id %s", eVar.y());
        eVar.c(a2.F());
        eVar.d(a2.G());
        eVar.b(a2.H());
        eVar.c(a2.K());
        e.b("record user scene:%d", Integer.valueOf(eVar.R()));
        return eVar;
    }

    public b a(j jVar, String str) {
        a('{', str);
        if (jVar == null) {
            this.a.append('\t').append("null");
        } else {
            jVar.a(this.a, this.b + 1);
        }
        a('}', null);
        return this;
    }
}
