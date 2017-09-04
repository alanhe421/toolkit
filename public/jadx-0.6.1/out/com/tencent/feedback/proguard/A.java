package com.tencent.feedback.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import com.tencent.upload.log.trace.TracerConfig;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* compiled from: RQDSRC */
public class a {
    protected HashMap<String, HashMap<String, byte[]>> a = new HashMap();
    protected String b;
    h c;
    private HashMap<String, Object> d;

    public static int a(Context context, p[] pVarArr) {
        if (context == null || pVarArr == null || pVarArr.length <= 0) {
            return -1;
        }
        Object arrayList = new ArrayList(pVarArr.length);
        for (Object obj : pVarArr) {
            byte[] a = a(obj);
            if (a != null) {
                l lVar = new l(7, 0, 0, a);
                lVar.a(obj.a());
                arrayList.add(lVar);
            }
        }
        if (arrayList.size() > 0) {
            return l.b(context, arrayList) ? arrayList.size() : -1;
        } else {
            return 0;
        }
    }

    a() {
        HashMap hashMap = new HashMap();
        this.d = new HashMap();
        this.b = "GBK";
        this.c = new h();
    }

    public static byte[] a(String str, int i) {
        e.a("rqdp{  LogcatManager.getLogDatas() start + count:}" + i, new Object[0]);
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        arrayList.add("logcat");
        arrayList.add("-d");
        arrayList.add("-v");
        arrayList.add("time");
        if (str != null && str.length() > 0) {
            arrayList.add("-s");
            arrayList.add(str);
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        com.tencent.feedback.eup.a aVar = new com.tencent.feedback.eup.a(i);
        a(strArr, aVar);
        if (aVar.size() <= 0) {
            return null;
        }
        e.a("rqdp{  get log success in list size:}" + aVar.size(), new Object[0]);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = aVar.iterator();
            while (it.hasNext()) {
                stringBuffer.append(((String) it.next()) + "\n");
            }
            aVar.clear();
            byte[] bytes = stringBuffer.toString().getBytes("utf-8");
            stringBuffer.setLength(0);
            e.a("rqdp{  LogcatManager.getLogDatas() end}", new Object[0]);
            return bytes;
        } catch (Throwable th) {
            e.a("rqdp{  LogcatManager.getLogDatas() end}", new Object[0]);
        }
    }

    public static Long[] a(LinkedHashMap<Long, Long> linkedHashMap, long j) {
        if (linkedHashMap == null || linkedHashMap.size() <= 0 || j <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = linkedHashMap.keySet().iterator();
        long j2 = 0;
        while (it.hasNext() && j2 < j) {
            long longValue = ((Long) it.next()).longValue();
            long longValue2 = ((Long) linkedHashMap.get(Long.valueOf(longValue))).longValue();
            if (j2 + longValue2 <= j) {
                arrayList.add(Long.valueOf(longValue));
                longValue2 += j2;
            } else {
                longValue2 = j2;
            }
            j2 = longValue2;
        }
        return arrayList.size() > 0 ? (Long[]) arrayList.toArray(new Long[1]) : null;
    }

    public static Map<String, String> b() {
        Map allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : allStackTraces.entrySet()) {
                stringBuilder.setLength(0);
                for (StackTraceElement stackTraceElement : (StackTraceElement[]) entry.getValue()) {
                    stringBuilder.append(stackTraceElement.toString()).append("\n");
                }
                hashMap.put(((Thread) entry.getKey()).getName(), stringBuilder.toString());
            }
        } catch (Throwable th) {
            e.d("add all thread error", new Object[0]);
            if (!e.a(th)) {
                th.printStackTrace();
            }
        }
        return hashMap;
    }

    public static synchronized int a(Context context, q[] qVarArr) {
        int i = 0;
        synchronized (a.class) {
            if (!(context == null || qVarArr == null)) {
                if (qVarArr.length > 0) {
                    List arrayList = new ArrayList(qVarArr.length);
                    for (Object obj : qVarArr) {
                        byte[] a = a(obj);
                        if (a == null) {
                            e.c("rqdp{ getSerData error }", new Object[0]);
                        } else {
                            l lVar = new l(9, 0, obj.a(), a);
                            lVar.a(obj.c());
                            arrayList.add(lVar);
                        }
                    }
                    if (arrayList.size() > 0 && l.a(context, arrayList)) {
                        i = arrayList.size();
                    }
                }
            }
            e.c("rqdp{  args error}", new Object[0]);
        }
        return i;
    }

    public void a(String str) {
        this.b = str;
    }

    public static List<p> a(Context context) {
        if (context == null) {
            return null;
        }
        List<l> a = l.a(context, new int[]{7}, -1, -1, Long.MAX_VALUE, 5, null, -1, -1, -1, -1, -1, Long.MAX_VALUE);
        if (a == null || a.size() <= 0) {
            return null;
        }
        List<p> arrayList = new ArrayList(a.size());
        for (l lVar : a) {
            try {
                p pVar = (p) p.class.cast(b(lVar.b()));
                pVar.a(lVar.a());
                arrayList.add(pVar);
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                e.d("rqdp{  netconsume error }%s", th.toString());
            }
        }
        return arrayList;
    }

    public static synchronized int b(Context context, q[] qVarArr) {
        int i = 0;
        synchronized (a.class) {
            if (!(context == null || qVarArr == null)) {
                if (qVarArr.length > 0) {
                    List arrayList = new ArrayList(qVarArr.length);
                    for (q qVar : qVarArr) {
                        if (qVar.d() >= 0) {
                            arrayList.add(Long.valueOf(qVar.d()));
                        }
                    }
                    if (arrayList.size() > 0) {
                        i = l.a(context, (Long[]) arrayList.toArray(new Long[0]));
                    }
                }
            }
            e.c("rqdp{  args error}", new Object[0]);
        }
        return i;
    }

    public static boolean a(String[] strArr, com.tencent.feedback.eup.a<String> aVar) {
        if (strArr == null || strArr.length <= 0) {
            return false;
        }
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(strArr);
            if (aVar != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    aVar.add(readLine);
                }
                bufferedReader.close();
            } else {
                process.waitFor();
            }
            if (process != null) {
                try {
                    process.getOutputStream().close();
                } catch (Throwable e) {
                    if (!e.a(e)) {
                        e.printStackTrace();
                    }
                }
                try {
                    process.getInputStream().close();
                } catch (Throwable e2) {
                    if (!e.a(e2)) {
                        e2.printStackTrace();
                    }
                }
                try {
                    process.getErrorStream().close();
                } catch (Throwable e22) {
                    if (!e.a(e22)) {
                        e22.printStackTrace();
                    }
                }
            }
            return true;
        } catch (Throwable th) {
            if (process != null) {
                try {
                    process.getOutputStream().close();
                } catch (Throwable th2) {
                    if (!e.a(th2)) {
                        th2.printStackTrace();
                    }
                }
                try {
                    process.getInputStream().close();
                } catch (Throwable th22) {
                    if (!e.a(th22)) {
                        th22.printStackTrace();
                    }
                }
                try {
                    process.getErrorStream().close();
                } catch (Throwable th222) {
                    if (!e.a(th222)) {
                        th222.printStackTrace();
                    }
                }
            }
        }
    }

    public static byte[] a(Object obj) {
        ObjectOutputStream objectOutputStream;
        Throwable e;
        Throwable th;
        byte[] bArr = null;
        e.b("rqdp{  en obj 2 bytes}", new Object[0]);
        if (obj == null || !Serializable.class.isInstance(obj)) {
            e.c("rqdp{  not serial obj}", new Object[0]);
        } else {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream.writeObject(obj);
                    objectOutputStream.flush();
                    bArr = byteArrayOutputStream.toByteArray();
                    try {
                        objectOutputStream.close();
                    } catch (Throwable e2) {
                        if (!e.a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e22) {
                        if (!e.a(e22)) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    e22 = th2;
                    try {
                        if (!e.a(e22)) {
                            e22.printStackTrace();
                        }
                        e.d(e22.getMessage(), new Object[0]);
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (Throwable e222) {
                                if (!e.a(e222)) {
                                    e222.printStackTrace();
                                }
                            }
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e2222) {
                            if (!e.a(e2222)) {
                                e2222.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th3) {
                        th = th3;
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (Throwable e22222) {
                                if (!e.a(e22222)) {
                                    e22222.printStackTrace();
                                }
                            }
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e222222) {
                            if (!e.a(e222222)) {
                                e222222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable e2222222) {
                objectOutputStream = bArr;
                th = e2222222;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                byteArrayOutputStream.close();
                throw th;
            }
        }
        return bArr;
    }

    public static String a(ArrayList<String> arrayList) {
        int i;
        StringBuffer stringBuffer = new StringBuffer();
        for (i = 0; i < arrayList.size(); i++) {
            Object obj = (String) arrayList.get(i);
            if (obj.equals("java.lang.Integer") || obj.equals("int")) {
                obj = "int32";
            } else if (obj.equals("java.lang.Boolean") || obj.equals("boolean")) {
                obj = "bool";
            } else if (obj.equals("java.lang.Byte") || obj.equals("byte")) {
                obj = "char";
            } else if (obj.equals("java.lang.Double") || obj.equals("double")) {
                obj = "double";
            } else if (obj.equals("java.lang.Float") || obj.equals("float")) {
                obj = "float";
            } else if (obj.equals("java.lang.Long") || obj.equals("long")) {
                obj = "int64";
            } else if (obj.equals("java.lang.Short") || obj.equals("short")) {
                obj = "short";
            } else if (obj.equals("java.lang.Character")) {
                throw new IllegalArgumentException("can not support java.lang.Character");
            } else if (obj.equals("java.lang.String")) {
                obj = "string";
            } else if (obj.equals("java.util.List")) {
                obj = "list";
            } else if (obj.equals("java.util.Map")) {
                obj = "map";
            }
            arrayList.set(i, obj);
        }
        Collections.reverse(arrayList);
        for (i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (str.equals("list")) {
                arrayList.set(i - 1, "<" + ((String) arrayList.get(i - 1)));
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            } else if (str.equals("map")) {
                arrayList.set(i - 1, "<" + ((String) arrayList.get(i - 1)) + ",");
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            } else if (str.equals("Array")) {
                arrayList.set(i - 1, "<" + ((String) arrayList.get(i - 1)));
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            }
        }
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
        }
        return stringBuffer.toString();
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            i iVar = new i();
            iVar.a(this.b);
            iVar.a((Object) t, 0);
            Object a = k.a(iVar.a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            a(arrayList, (Object) t);
            hashMap.put(a(arrayList), a);
            this.d.remove(str);
            this.a.put(str, hashMap);
        }
    }

    public static int b(Context context, p[] pVarArr) {
        if (context == null) {
            return -1;
        }
        if (pVarArr == null) {
            return l.a(context, new int[]{7}, -1, Long.MAX_VALUE);
        }
        ArrayList arrayList = new ArrayList();
        for (p pVar : pVarArr) {
            if (pVar.a() >= 0) {
                arrayList.add(Long.valueOf(pVar.a()));
            }
        }
        if (arrayList.size() > 0) {
            return l.a(context, (Long[]) arrayList.toArray(new Long[0]));
        }
        return 0;
    }

    public static boolean a(Context context, z zVar) {
        Throwable th;
        Throwable th2;
        n nVar = null;
        if (context == null || zVar == null) {
            e.c("rqdp{  context == null || bean == null}", new Object[0]);
            return false;
        }
        n nVar2;
        SQLiteDatabase writableDatabase;
        try {
            nVar2 = new n(context);
            try {
                writableDatabase = nVar2.getWritableDatabase();
                if (writableDatabase == null) {
                    try {
                        e.d("get db fail!,return ", new Object[0]);
                        if (writableDatabase != null && writableDatabase.isOpen()) {
                            writableDatabase.close();
                        }
                        nVar2.close();
                        return false;
                    } catch (Throwable th3) {
                        th2 = th3;
                        if (writableDatabase != null && writableDatabase.isOpen()) {
                            writableDatabase.close();
                        }
                        if (nVar2 != null) {
                            nVar2.close();
                        }
                        throw th2;
                    }
                }
                ContentValues contentValues;
                if (zVar != null) {
                    contentValues = new ContentValues();
                    if (zVar.a() >= 0) {
                        contentValues.put("_id", Long.valueOf(zVar.a()));
                    }
                    contentValues.put("_key", Integer.valueOf(zVar.b()));
                    contentValues.put("_datas", zVar.c());
                    contentValues.put("_ut", Long.valueOf(zVar.d()));
                }
                if (contentValues != null) {
                    long replace = writableDatabase.replace("strategy", "_id", contentValues);
                    if (replace < 0) {
                        e.c("rqdp{  insert failure! return}", new Object[0]);
                        if (writableDatabase != null && writableDatabase.isOpen()) {
                            writableDatabase.close();
                        }
                        nVar2.close();
                        return false;
                    }
                    zVar.a(replace);
                    e.b("rqdp{  update strategy} %d rqdp{  true}", Integer.valueOf(zVar.b()));
                    if (writableDatabase != null && writableDatabase.isOpen()) {
                        writableDatabase.close();
                    }
                    nVar2.close();
                    return true;
                }
                if (writableDatabase != null && writableDatabase.isOpen()) {
                    writableDatabase.close();
                }
                nVar2.close();
                return false;
            } catch (Throwable th4) {
                th2 = th4;
                writableDatabase = null;
                writableDatabase.close();
                if (nVar2 != null) {
                    nVar2.close();
                }
                throw th2;
            }
        } catch (Throwable th5) {
            th2 = th5;
            writableDatabase = null;
            nVar2 = null;
            writableDatabase.close();
            if (nVar2 != null) {
                nVar2.close();
            }
            throw th2;
        }
    }

    public static synchronized q[] a(Context context, String str) {
        q[] qVarArr;
        synchronized (a.class) {
            if (context == null || str == null) {
                e.c("rqdp{  args error}", new Object[0]);
                qVarArr = null;
            } else {
                List<l> a = l.a(context, new int[]{9}, -1, -1, TracerConfig.LOG_FLUSH_DURATION, -1, str, -1, -1, -1, -1, -1, Long.MAX_VALUE);
                if (a == null || a.size() <= 0) {
                    qVarArr = null;
                } else {
                    ArrayList arrayList = new ArrayList(a.size());
                    for (l lVar : a) {
                        Object b = b(lVar.b());
                        if (b != null && q.class.isInstance(b)) {
                            q qVar = (q) q.class.cast(b);
                            qVar.b(lVar.a());
                            arrayList.add(qVar);
                        }
                    }
                    if (arrayList.size() > 0) {
                        qVarArr = (q[]) arrayList.toArray(new q[0]);
                    } else {
                        qVarArr = null;
                    }
                }
            }
        }
        return qVarArr;
    }

    public static Object b(byte[] bArr) {
        ObjectInputStream objectInputStream;
        Object readObject;
        Throwable e;
        Throwable th;
        ObjectInputStream objectInputStream2 = null;
        e.b("rqdp{  de byte 2 obj}", new Object[0]);
        if (bArr != null && bArr.length >= 0) {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                try {
                    readObject = objectInputStream.readObject();
                    try {
                        objectInputStream.close();
                    } catch (Throwable e2) {
                        if (!e.a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable e22) {
                        if (!e.a(e22)) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    e22 = th2;
                    try {
                        if (!e.a(e22)) {
                            e22.printStackTrace();
                        }
                        e.d(e22.getMessage(), new Object[0]);
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (Throwable e222) {
                                if (!e.a(e222)) {
                                    e222.printStackTrace();
                                }
                            }
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable e2222) {
                            if (!e.a(e2222)) {
                                e2222.printStackTrace();
                            }
                        }
                        return readObject;
                    } catch (Throwable th3) {
                        th = th3;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (Throwable e22222) {
                                if (!e.a(e22222)) {
                                    e22222.printStackTrace();
                                }
                            }
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable e222222) {
                            if (!e.a(e222222)) {
                                e222222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable e2222222) {
                objectInputStream = objectInputStream2;
                th = e2222222;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                byteArrayInputStream.close();
                throw th;
            }
        }
        return readObject;
    }

    public static synchronized int b(Context context, String str) {
        int i = 0;
        synchronized (a.class) {
            if (context == null || str == null) {
                e.c("rqdp{  args error}", new Object[0]);
            } else {
                i = l.a(context, new int[]{9}, -1, Long.MAX_VALUE, str);
            }
        }
        return i;
    }

    public static void a(Context context, int i, byte[] bArr) {
        if (bArr != null) {
            z zVar = new z();
            zVar.a(i);
            zVar.a(bArr);
            zVar.b(new Date().getTime());
            a(context, zVar);
        }
    }

    public static byte[] a(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        e.b("rqdp{  enD:} %d %d", Integer.valueOf(bArr.length), Integer.valueOf(i));
        if (i == 1) {
            try {
                H g = new G();
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                e.d("rqdp{  err enD: }%s", th.toString());
                return null;
            }
        }
        g = i == 3 ? new F() : null;
        if (g == null) {
            return null;
        }
        g.a(str);
        return g.b(bArr);
    }

    public static z a(Context context, int i) {
        n nVar;
        SQLiteDatabase sQLiteDatabase;
        n nVar2;
        Throwable th;
        Cursor cursor;
        Cursor cursor2;
        SQLiteDatabase sQLiteDatabase2 = null;
        if (context == null) {
            e.c("rqdp{  context == null}", new Object[0]);
            return null;
        }
        try {
            nVar = new n(context);
            try {
                SQLiteDatabase writableDatabase = nVar.getWritableDatabase();
                if (writableDatabase == null) {
                    try {
                        e.c("rqdp{  getWritableDatabase fail!}", new Object[0]);
                        if (writableDatabase != null && writableDatabase.isOpen()) {
                            writableDatabase.close();
                        }
                        nVar.close();
                        return null;
                    } catch (Throwable th2) {
                        cursor2 = null;
                        sQLiteDatabase2 = writableDatabase;
                        th = th2;
                        cursor2.close();
                        sQLiteDatabase2.close();
                        if (nVar != null) {
                            nVar.close();
                        }
                        throw th;
                    }
                }
                cursor2 = writableDatabase.query("strategy", null, String.format(Locale.US, " %s = %d ", new Object[]{"_key", Integer.valueOf(i)}), null, null, null, null);
                if (cursor2 != null) {
                    try {
                        if (cursor2.moveToNext()) {
                            z zVar;
                            if (cursor2 == null || cursor2.isBeforeFirst() || cursor2.isAfterLast()) {
                                zVar = null;
                            } else {
                                e.b("rqdp{  parse bean}", new Object[0]);
                                zVar = new z();
                                zVar.a(cursor2.getLong(cursor2.getColumnIndex("_id")));
                                zVar.a(cursor2.getInt(cursor2.getColumnIndex("_key")));
                                zVar.b(cursor2.getLong(cursor2.getColumnIndex("_ut")));
                                zVar.a(cursor2.getBlob(cursor2.getColumnIndex("_datas")));
                            }
                            if (zVar != null) {
                                e.b("rqdp{  read strategy key:}%d", Integer.valueOf(zVar.b()));
                                if (!(cursor2 == null || cursor2.isClosed())) {
                                    cursor2.close();
                                }
                                if (writableDatabase != null && writableDatabase.isOpen()) {
                                    writableDatabase.close();
                                }
                                nVar.close();
                                return zVar;
                            }
                        }
                    } catch (Throwable th22) {
                        sQLiteDatabase2 = writableDatabase;
                        th = th22;
                        if (!(cursor2 == null || cursor2.isClosed())) {
                            cursor2.close();
                        }
                        if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                            sQLiteDatabase2.close();
                        }
                        if (nVar != null) {
                            nVar.close();
                        }
                        throw th;
                    }
                }
                if (!(cursor2 == null || cursor2.isClosed())) {
                    cursor2.close();
                }
                if (writableDatabase != null && writableDatabase.isOpen()) {
                    writableDatabase.close();
                }
                nVar.close();
                return null;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = null;
                cursor2.close();
                sQLiteDatabase2.close();
                if (nVar != null) {
                    nVar.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            cursor2 = null;
            nVar = null;
            cursor2.close();
            sQLiteDatabase2.close();
            if (nVar != null) {
                nVar.close();
            }
            throw th;
        }
    }

    public static byte[] b(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        if (i == 1) {
            try {
                H g = new G();
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                e.d("rqdp{  err unD:} %s", th.toString());
                return null;
            }
        }
        g = i == 3 ? new F() : null;
        if (g == null) {
            return null;
        }
        g.a(str);
        return g.a(bArr);
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        e.b("rqdp{  zp:} %s rqdp{  len:} %s", Integer.valueOf(i), Integer.valueOf(bArr.length));
        try {
            C a = B.a(i);
            if (a == null) {
                return null;
            }
            return a.a(bArr);
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            e.d("rqdp{  err zp :} %s", th.toString());
            return null;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        e.b("rqdp{  unzp:} %s rqdp{  len:} %s", Integer.valueOf(i), Integer.valueOf(bArr.length));
        try {
            C a = B.a(i);
            if (a == null) {
                return null;
            }
            return a.b(bArr);
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            e.d("rqdp{  err unzp}" + th.toString(), new Object[0]);
            return null;
        }
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            } else if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a((ArrayList) arrayList, Array.get(obj, 0));
            } else {
                arrayList.add("Array");
                arrayList.add("?");
            }
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a((ArrayList) arrayList, list.get(0));
            } else {
                arrayList.add("?");
            }
        } else if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a((ArrayList) arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
        } else {
            arrayList.add(obj.getClass().getName());
        }
    }

    public static byte[] a(j jVar) {
        try {
            i iVar = new i();
            iVar.a("utf-8");
            jVar.a(iVar);
            return iVar.b();
        } catch (Throwable th) {
            if (!e.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static int b(Context context, int i) {
        n nVar;
        Throwable th;
        Throwable th2;
        SQLiteDatabase sQLiteDatabase = null;
        if (context == null) {
            e.c("rqdp{  context == null ||key < -1}", new Object[0]);
            return 0;
        }
        try {
            nVar = new n(context);
            try {
                sQLiteDatabase = nVar.getWritableDatabase();
                if (sQLiteDatabase == null) {
                    e.d("get db fail!,return ", new Object[0]);
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    nVar.close();
                    return 0;
                }
                e.b("rqdp{  delete Strategy key} %d rqdp{  , num} %d", Integer.valueOf(510), Integer.valueOf(sQLiteDatabase.delete("strategy", String.format("%s = %d", new Object[]{"_key", Integer.valueOf(510)}), null)));
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
                nVar.close();
                return r1;
            } catch (Throwable th3) {
                th = th3;
                try {
                    if (!e.a(th)) {
                        th.printStackTrace();
                    }
                    e.d("rqdp{  Error strategy delete!} %s", th.toString());
                    sQLiteDatabase.close();
                    if (nVar != null) {
                        return 0;
                    }
                    nVar.close();
                    return 0;
                } catch (Throwable th4) {
                    th2 = th4;
                    sQLiteDatabase.close();
                    if (nVar != null) {
                        nVar.close();
                    }
                    throw th2;
                }
            }
        } catch (Throwable th5) {
            th2 = th5;
            nVar = null;
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
            if (nVar != null) {
                nVar.close();
            }
            throw th2;
        }
    }

    public static N a(int i, c cVar, byte[] bArr, byte b, byte b2, String str) {
        e.b("rqdp{  en2Req }", new Object[0]);
        if (cVar == null) {
            e.d("rqdp{  error no com info!}", new Object[0]);
            return null;
        }
        try {
            N n = new N();
            synchronized (cVar) {
                n.a = cVar.a();
                n.b = cVar.n();
                n.c = cVar.b();
                n.d = cVar.B();
                n.e = "";
                n.f = cVar.c();
                n.g = i;
                n.h = bArr == null ? "".getBytes() : bArr;
                n.i = cVar.e();
                n.j = cVar.f();
                n.k = new HashMap();
                n.k.put("productIdentify", cVar.k().trim().length() > 0 ? cVar.k() : cVar.l());
            }
            if (bArr != null) {
                n.h = a(n.h, b, b2, str);
                if (n.h == null) {
                    e.d("reqPkg sbuffer error!", new Object[0]);
                    return null;
                }
            }
            return n;
        } catch (Throwable th) {
            if (e.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public byte[] a() {
        i iVar = new i(0);
        iVar.a(this.b);
        iVar.a(this.a, 0);
        return k.a(iVar.a());
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
        this.c.a(this.b);
        Map hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.a = this.c.a(hashMap, 0, false);
    }

    public static byte[] a(byte[] bArr, int i, int i2, String str) {
        byte[] bArr2 = null;
        if (bArr != null) {
            try {
                bArr2 = a(a(bArr, i), i2, str);
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return bArr2;
    }

    public static byte[] b(byte[] bArr, int i, int i2, String str) {
        try {
            return b(b(bArr, 1, str), 2);
        } catch (Throwable e) {
            if (!e.a(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static long c() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            return simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime();
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    private static String e(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(toHexString);
        }
        return stringBuffer.toString().toUpperCase();
    }

    public static String c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr);
            return e(instance.digest());
        } catch (Throwable e) {
            if (!e.a(e)) {
                e.printStackTrace();
            }
            e.d(e.getMessage(), new Object[0]);
            return null;
        }
    }

    public static String b(String str) {
        FileInputStream fileInputStream;
        Throwable e;
        Throwable th;
        String str2 = null;
        if (!(str == null || str.length() == 0)) {
            File file = new File(str);
            if (file.exists() && file.canRead()) {
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        MessageDigest instance = MessageDigest.getInstance("SHA-1");
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            instance.update(bArr, 0, read);
                        }
                        str2 = e(instance.digest());
                        try {
                            fileInputStream.close();
                        } catch (Throwable e2) {
                            if (!e.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        try {
                            if (!e.a(e2)) {
                                e2.printStackTrace();
                            }
                            e.d(e2.getMessage(), new Object[0]);
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable e22) {
                                    if (!e.a(e22)) {
                                        e22.printStackTrace();
                                    }
                                }
                            }
                            return str2;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable e222) {
                                    if (!e.a(e222)) {
                                        e222.printStackTrace();
                                    }
                                }
                            }
                            throw th;
                        }
                    } catch (NoSuchAlgorithmException e4) {
                        e222 = e4;
                        if (!e.a(e222)) {
                            e222.printStackTrace();
                        }
                        e.d(e222.getMessage(), new Object[0]);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e2222) {
                                if (!e.a(e2222)) {
                                    e2222.printStackTrace();
                                }
                            }
                        }
                        return str2;
                    }
                } catch (IOException e5) {
                    e2222 = e5;
                    Object obj = str2;
                    if (e.a(e2222)) {
                        e2222.printStackTrace();
                    }
                    e.d(e2222.getMessage(), new Object[0]);
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return str2;
                } catch (NoSuchAlgorithmException e6) {
                    e2222 = e6;
                    fileInputStream = str2;
                    if (e.a(e2222)) {
                        e2222.printStackTrace();
                    }
                    e.d(e2222.getMessage(), new Object[0]);
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return str2;
                } catch (Throwable e22222) {
                    fileInputStream = str2;
                    th = e22222;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
        }
        return str2;
    }

    public static boolean a(File file, File file2, int i) {
        FileInputStream fileInputStream;
        Throwable e;
        FileInputStream fileInputStream2 = null;
        e.b("rqdp{  ZF start}", new Object[0]);
        if (file == null || file2 == null || file.equals(file2)) {
            e.c("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        } else if (file.exists() && file.canRead()) {
            try {
                if (!(file2.getParentFile() == null || file2.getParentFile().exists())) {
                    file2.getParentFile().mkdirs();
                }
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                if (!file2.exists() || !file2.canRead()) {
                    return false;
                }
                ZipOutputStream zipOutputStream;
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
                        try {
                            zipOutputStream.setMethod(8);
                            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                            byte[] bArr = new byte[5000];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                zipOutputStream.write(bArr, 0, read);
                            }
                            zipOutputStream.flush();
                            zipOutputStream.closeEntry();
                            try {
                                fileInputStream.close();
                            } catch (Throwable e2) {
                                if (!e.a(e2)) {
                                    e2.printStackTrace();
                                }
                            }
                            try {
                                zipOutputStream.close();
                            } catch (Throwable e22) {
                                if (!e.a(e22)) {
                                    e22.printStackTrace();
                                }
                            }
                            e.b("rqdp{  ZF end}", new Object[0]);
                            return true;
                        } catch (Throwable th) {
                            e22 = th;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (zipOutputStream != null) {
                                zipOutputStream.close();
                            }
                            e.b("rqdp{  ZF end}", new Object[0]);
                            throw e22;
                        }
                    } catch (Throwable th2) {
                        e22 = th2;
                        zipOutputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (zipOutputStream != null) {
                            zipOutputStream.close();
                        }
                        e.b("rqdp{  ZF end}", new Object[0]);
                        throw e22;
                    }
                } catch (Throwable th3) {
                    e22 = th3;
                    zipOutputStream = null;
                    fileInputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    e.b("rqdp{  ZF end}", new Object[0]);
                    throw e22;
                }
            } catch (Throwable e222) {
                if (e.a(e222)) {
                    return false;
                }
                e222.printStackTrace();
                return false;
            }
        } else {
            e.c("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        }
    }

    public static ArrayList<String> a(String[] strArr) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        ArrayList<String> arrayList = new ArrayList();
        BufferedReader bufferedReader3;
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    arrayList.add(readLine);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            bufferedReader3 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            while (true) {
                try {
                    String readLine2 = bufferedReader3.readLine();
                    if (readLine2 != null) {
                        arrayList.add(readLine2);
                    } else {
                        try {
                            break;
                        } catch (Throwable e) {
                            if (!e.a(e)) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            bufferedReader.close();
            try {
                bufferedReader3.close();
                return arrayList;
            } catch (Throwable e2) {
                if (e.a(e2)) {
                    return arrayList;
                }
                e2.printStackTrace();
                return arrayList;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }

    public static String c(String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        List a = a(new String[]{"/system/bin/sh", "-c", "getprop " + str});
        if (a == null || a.size() <= 0) {
            return "fail";
        }
        return (String) a.get(0);
    }

    public static String d() {
        try {
            return UUID.randomUUID().toString();
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static void a(String str, String str2, int i) {
        e.b("rqdp{  sv sd start} %s", str);
        if (str2 != null && str2.trim().length() > 0) {
            File file = new File(str);
            FileOutputStream fileOutputStream;
            try {
                if (!file.exists()) {
                    if (file.getParentFile() != null) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                }
                fileOutputStream = null;
                if (file.length() >= ((long) i)) {
                    fileOutputStream = new FileOutputStream(file, false);
                } else {
                    fileOutputStream = new FileOutputStream(file, true);
                }
                fileOutputStream.write(str2.getBytes("UTF-8"));
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
            }
            e.b("rqdp{  sv sd end}", new Object[0]);
        }
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            Writer stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (!(e.a(th2) || e.a(th2))) {
                th2.printStackTrace();
            }
            return "fail";
        }
    }

    public static Q d(byte[] bArr) {
        try {
            Q q = new Q();
            h hVar = new h(bArr);
            hVar.a("utf-8");
            q.a(hVar);
            return q;
        } catch (Throwable th) {
            if (!e.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
