package oicq.wlogin_sdk.request;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import oicq.wlogin_sdk.sharemem.WloginLoginInfo;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: account_sig_info_map */
public class d {
    private static final Object f = new Object();
    private static final Object g = new Object();
    private static b h = null;
    private static b i = null;
    Context a;
    WloginLastLoginInfo b = new WloginLastLoginInfo();
    TreeMap<Long, WloginAllSigInfo> c = new TreeMap();
    TreeMap<String, UinInfo> d = new TreeMap();
    public int e;

    public d(Context context) {
        this.a = context;
        this.d = a(this.a, "name_file");
        if (this.d == null) {
            this.d = new TreeMap();
        }
    }

    public synchronized int a(long j, long j2, byte[][] bArr, long j3, long j4, long j5, long j6, long j7, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[][] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10, byte[] bArr11, byte[] bArr12, byte[] bArr13, byte[] bArr14, byte[] bArr15, byte[] bArr16, byte[] bArr17, byte[] bArr18, byte[][] bArr19, long[] jArr, int i) {
        int i2;
        util.LOGI("start put_siginfo", "" + j);
        i2 = 0;
        if (this.a != null) {
            int a;
            synchronized (f) {
                TreeMap treeMap;
                Object obj;
                Object obj2;
                Object obj3;
                Object obj4;
                Object obj5;
                byte[] bArr20;
                util.LOGI("put_siginfo load file", "" + j);
                TreeMap a2 = a(this.a, "tk_file");
                if (a2 == null) {
                    treeMap = new TreeMap();
                } else {
                    treeMap = a2;
                }
                WloginAllSigInfo wloginAllSigInfo = (WloginAllSigInfo) this.c.get(Long.valueOf(j));
                if (wloginAllSigInfo == null) {
                    wloginAllSigInfo = (WloginAllSigInfo) treeMap.get(Long.valueOf(j));
                    if (wloginAllSigInfo == null) {
                        wloginAllSigInfo = new WloginAllSigInfo();
                    }
                }
                util.LOGI("mainSigMap 0x" + Integer.toHexString(this.e), "" + j);
                wloginAllSigInfo.mainSigMap = this.e;
                Object obj6 = new byte[0];
                Object obj7 = new byte[0];
                Object obj8 = new byte[0];
                Object obj9 = new byte[0];
                Object obj10 = new byte[0];
                byte[] bArr21 = new byte[0];
                byte[] bArr22 = new byte[0];
                WloginSigInfo wloginSigInfo = (WloginSigInfo) wloginAllSigInfo._tk_map.get(Long.valueOf(j2));
                if (wloginSigInfo != null) {
                    Object obj11;
                    if (wloginSigInfo._en_A1 != null) {
                        Object obj12 = (byte[]) wloginSigInfo._en_A1.clone();
                        if (wloginSigInfo._noPicSig != null) {
                            obj7 = (byte[]) wloginSigInfo._noPicSig.clone();
                            obj6 = obj12;
                        } else {
                            obj6 = obj12;
                        }
                    }
                    if (wloginSigInfo._G != null) {
                        obj8 = (byte[]) wloginSigInfo._G.clone();
                    }
                    if (wloginSigInfo._dpwd != null) {
                        obj9 = (byte[]) wloginSigInfo._dpwd.clone();
                    }
                    if (wloginSigInfo._randseed != null) {
                        obj11 = (byte[]) wloginSigInfo._randseed.clone();
                    } else {
                        obj11 = obj10;
                    }
                    bArr22 = wloginSigInfo._psKey;
                    obj = obj11;
                    obj2 = obj9;
                    obj3 = obj8;
                    obj4 = obj7;
                    obj5 = obj6;
                    bArr21 = wloginSigInfo._pt4Token;
                    bArr20 = bArr22;
                } else {
                    bArr20 = bArr21;
                    obj = obj10;
                    obj2 = obj9;
                    obj3 = obj8;
                    obj4 = obj7;
                    obj5 = obj6;
                    bArr21 = bArr22;
                }
                if (bArr19[6] != null && bArr19[6].length > 2) {
                    Entry entry;
                    String str;
                    Map hashMap = new HashMap();
                    Map hashMap2 = new HashMap();
                    Map hashMap3 = new HashMap();
                    Map hashMap4 = new HashMap();
                    Ticket.parsePsBuf(bArr20, j5, hashMap, hashMap2);
                    Iterator it = hashMap2.entrySet().iterator();
                    while (it.hasNext()) {
                        entry = (Entry) it.next();
                        str = (String) entry.getKey();
                        if (Ticket.isPskeyExpired(((Long) entry.getValue()).longValue())) {
                            it.remove();
                            hashMap.remove(str);
                        }
                    }
                    it = hashMap4.entrySet().iterator();
                    while (it.hasNext()) {
                        entry = (Entry) it.next();
                        str = (String) entry.getKey();
                        if (Ticket.isPt4TokenExpired(((Long) entry.getValue()).longValue())) {
                            it.remove();
                            hashMap3.remove(str);
                        }
                    }
                    Ticket.parsePsBuf(bArr21, j5, hashMap3, hashMap4);
                    Ticket.parseSvrPs(bArr19[6], j5, hashMap, hashMap2, hashMap3, hashMap4);
                    try {
                        bArr19[6] = Ticket.packPsBuf(hashMap, j5, hashMap2);
                        bArr19[12] = Ticket.packPsBuf(hashMap3, j5, hashMap4);
                    } catch (BufferOverflowException e) {
                        util.LOGI("map size " + hashMap.size() + "is too large", "" + j);
                        a(Long.valueOf(j));
                        i2 = -1023;
                    }
                }
                wloginAllSigInfo.put_simpleinfo(j, bArr2, bArr3, bArr4, bArr5, bArr6);
                wloginAllSigInfo.put_siginfo(j3, j4, j5, j6, j7, bArr7, bArr8, bArr9, bArr10, bArr11, bArr12, bArr13, bArr14, bArr15, bArr16, bArr17, bArr18, bArr19, jArr, i);
                wloginAllSigInfo.put_siginfo(j2, bArr, j5);
                treeMap.put(Long.valueOf(j), wloginAllSigInfo.get_clone());
                a = a(treeMap, "tk_file");
                if (a != 0) {
                    wloginSigInfo = (WloginSigInfo) wloginAllSigInfo._tk_map.get(Long.valueOf(j2));
                    if (wloginSigInfo != null) {
                        wloginSigInfo._en_A1 = (byte[]) obj5.clone();
                        wloginSigInfo._noPicSig = (byte[]) obj4.clone();
                        wloginSigInfo._G = (byte[]) obj3.clone();
                        wloginSigInfo._dpwd = (byte[]) obj2.clone();
                        wloginSigInfo._randseed = (byte[]) obj.clone();
                    }
                }
                this.c.put(Long.valueOf(j), wloginAllSigInfo.get_clone());
            }
            i2 = a;
        }
        return i2;
    }

    public synchronized int a(long j, long j2) {
        int i = 0;
        synchronized (this) {
            util.LOGI("start clear_da2 " + j2, "" + j);
            if (this.a != null) {
                synchronized (f) {
                    TreeMap a = a(this.a, "tk_file");
                    if (a != null) {
                        WloginAllSigInfo wloginAllSigInfo = (WloginAllSigInfo) a.get(Long.valueOf(j));
                        if (wloginAllSigInfo == null) {
                            i = -1;
                        } else {
                            util.LOGI("clear_da2 clear DA2 in file", "" + j);
                            wloginAllSigInfo.put_da2(j2, new byte[0]);
                            i = a(a, "tk_file");
                            this.c = a;
                        }
                    }
                }
            }
            util.LOGI("end clear_da2 ret " + i, "" + j);
        }
        return i;
    }

    public synchronized void b(long j, long j2) {
        util.LOGI("clear_pskey " + j2, "" + j);
        WloginAllSigInfo wloginAllSigInfo = (WloginAllSigInfo) this.c.get(Long.valueOf(j));
        if (wloginAllSigInfo != null) {
            WloginSigInfo wloginSigInfo = (WloginSigInfo) wloginAllSigInfo._tk_map.get(Long.valueOf(j2));
            if (wloginSigInfo != null) {
                wloginSigInfo._pt4Token = new byte[0];
                wloginSigInfo._psKey = new byte[0];
                wloginSigInfo.cacheTickets = null;
                wloginSigInfo.cacheUpdateStamp = 0;
                wloginAllSigInfo._tk_map.put(Long.valueOf(j), wloginSigInfo);
                if (this.a != null) {
                    synchronized (f) {
                        TreeMap a = a(this.a, "tk_file");
                        if (a != null) {
                            a.put(Long.valueOf(j), wloginAllSigInfo);
                            a(a, "tk_file");
                            this.c = a;
                        }
                    }
                }
            }
        }
    }

    public synchronized int a(long j, long j2, byte[] bArr) {
        int i;
        WloginAllSigInfo wloginAllSigInfo = (WloginAllSigInfo) this.c.get(Long.valueOf(j));
        if (wloginAllSigInfo == null) {
            i = -1;
        } else {
            Object obj;
            Object obj2 = new byte[0];
            WloginSigInfo wloginSigInfo = (WloginSigInfo) wloginAllSigInfo._tk_map.get(Long.valueOf(j2));
            if (wloginSigInfo == null || wloginSigInfo._randseed == null) {
                obj = obj2;
            } else {
                obj = (byte[]) wloginSigInfo._randseed.clone();
            }
            int put_randseed = wloginAllSigInfo.put_randseed(j2, bArr);
            if (this.a != null) {
                synchronized (f) {
                    TreeMap a = a(this.a, "tk_file");
                    if (a != null) {
                        a.put(Long.valueOf(j), wloginAllSigInfo.get_clone());
                        int a2 = a(a, "tk_file");
                        if (a2 != 0) {
                            WloginSigInfo wloginSigInfo2 = (WloginSigInfo) wloginAllSigInfo._tk_map.get(Long.valueOf(j2));
                            if (wloginSigInfo2 != null) {
                                wloginSigInfo2._randseed = (byte[]) obj.clone();
                            }
                        }
                        i = a2;
                    } else {
                        i = put_randseed;
                    }
                }
            } else {
                i = put_randseed;
            }
        }
        return i;
    }

    public synchronized int a(long j, long j2, long j3, long j4, byte[] bArr, byte[] bArr2) {
        int i;
        WloginAllSigInfo wloginAllSigInfo = (WloginAllSigInfo) this.c.get(Long.valueOf(j));
        if (wloginAllSigInfo == null) {
            i = -1;
        } else {
            i = wloginAllSigInfo.put_siginfo(j2, j3, j4, bArr, bArr2);
            if (this.a != null) {
                synchronized (f) {
                    TreeMap a = a(this.a, "tk_file");
                    if (a == null) {
                        a = new TreeMap();
                    }
                    a.put(Long.valueOf(j), wloginAllSigInfo.get_clone());
                    a(a, "tk_file");
                }
            }
        }
        return i;
    }

    public synchronized WloginAllSigInfo a(long j) {
        WloginAllSigInfo wloginAllSigInfo;
        wloginAllSigInfo = (WloginAllSigInfo) this.c.get(Long.valueOf(j));
        if (wloginAllSigInfo != null) {
            util.LOGI("get_all_siginfo got in mem", "");
        } else if (this.a == null) {
            wloginAllSigInfo = null;
        } else {
            TreeMap a = a(this.a, "tk_file");
            if (a == null) {
                wloginAllSigInfo = null;
            } else {
                wloginAllSigInfo = (WloginAllSigInfo) a.get(Long.valueOf(j));
                if (wloginAllSigInfo == null) {
                    wloginAllSigInfo = null;
                } else {
                    util.LOGI("got in file", "");
                    this.c.put(Long.valueOf(j), wloginAllSigInfo);
                }
            }
        }
        return wloginAllSigInfo;
    }

    public synchronized void a() {
        util.LOGI("refresh_all_siginfo ...", "");
        this.c = a(this.a, "tk_file");
        if (this.c == null) {
            this.c = new TreeMap();
        }
    }

    public synchronized WloginSigInfo c(long j, long j2) {
        WloginSigInfo wloginSigInfo;
        WloginAllSigInfo a = a(j);
        if (a == null) {
            wloginSigInfo = null;
        } else {
            util.LOGI("get_siginfo get WloginAllSigInfo " + a._tk_map.size() + " " + a._tk_map, j + "");
            wloginSigInfo = (WloginSigInfo) a._tk_map.get(Long.valueOf(j2));
            if (wloginSigInfo == null) {
                wloginSigInfo = null;
            } else {
                util.LOGI("get_siginfo get WloginSigInfo sdkappid " + j2 + " " + wloginSigInfo, j + "");
            }
        }
        return wloginSigInfo;
    }

    public synchronized WloginSimpleInfo b(long j) {
        WloginSimpleInfo wloginSimpleInfo;
        util.LOGD("get_simpleinfo", "uin=" + j);
        WloginAllSigInfo a = a(j);
        if (a == null) {
            wloginSimpleInfo = null;
        } else {
            wloginSimpleInfo = a._useInfo.get_clone();
        }
        return wloginSimpleInfo;
    }

    public synchronized void a(Long l) {
        this.c.remove(l);
        if (this.a != null) {
            synchronized (f) {
                TreeMap a = a(this.a, "tk_file");
                if (a == null) {
                } else {
                    a.remove(l);
                    a(a, "tk_file");
                }
            }
        }
    }

    public synchronized void a(Long l, Long l2) {
        util.LOGI("clear_sig uin=" + l, "");
        WloginAllSigInfo wloginAllSigInfo = (WloginAllSigInfo) this.c.get(l);
        if (wloginAllSigInfo != null) {
            wloginAllSigInfo._tk_map.remove(l2);
            util.LOGI("uin " + l + " appid " + l2 + " sig has been cleared");
        }
        if (this.a != null) {
            synchronized (f) {
                TreeMap a = a(this.a, "tk_file");
                if (a == null) {
                } else {
                    wloginAllSigInfo = (WloginAllSigInfo) a.get(l);
                    if (wloginAllSigInfo == null) {
                    } else {
                        wloginAllSigInfo._tk_map.remove(l2);
                        a(a, "tk_file");
                    }
                }
            }
        }
    }

    public synchronized void a(String str, Long l, boolean z) {
        util.LOGI("put account " + str + " uin=" + l, "" + l);
        String b = b(l);
        if (b != null) {
            this.d.remove(b);
        }
        UinInfo uinInfo = new UinInfo(l, z);
        this.d.put(str, uinInfo);
        if (this.a != null) {
            synchronized (g) {
                TreeMap a = a(this.a, "name_file");
                if (a == null) {
                    a = new TreeMap();
                }
                if (b != null) {
                    a.remove(b);
                }
                a.put(str, uinInfo);
                a(a, "name_file");
            }
        }
    }

    public synchronized void a(String str) {
        this.d.remove(str);
        if (this.a != null) {
            synchronized (g) {
                TreeMap a = a(this.a, "name_file");
                if (a == null) {
                    a = new TreeMap();
                }
                a.remove(str);
                a(a, "name_file");
            }
        }
    }

    public synchronized UinInfo a(String str, boolean z) {
        UinInfo uinInfo;
        if (z) {
            uinInfo = (UinInfo) this.d.get(str);
            if (uinInfo != null) {
                util.LOGI("mem got_account name: " + str + " uin: " + uinInfo._uin + ", " + uinInfo.getHasPassword(), "");
            }
        }
        if (this.a == null) {
            uinInfo = null;
        } else {
            TreeMap a = a(this.a, "name_file");
            if (a == null) {
                uinInfo = null;
            } else {
                uinInfo = (UinInfo) a.get(str);
                if (uinInfo == null) {
                    uinInfo = null;
                } else {
                    this.d.put(str, uinInfo);
                    util.LOGI("file got_account name: " + str + " uin: " + uinInfo._uin + ", " + uinInfo.getHasPassword(), "");
                }
            }
        }
        return uinInfo;
    }

    public synchronized String b(Long l) {
        String str;
        for (String str2 : this.d.keySet()) {
            UinInfo uinInfo = (UinInfo) this.d.get(str2);
            if (uinInfo != null && uinInfo._uin.equals(l)) {
                break;
            }
        }
        str2 = null;
        return str2;
    }

    public synchronized List<WloginLoginInfo> a(boolean z) {
        List<WloginLoginInfo> list;
        List<WloginLoginInfo> arrayList = new ArrayList();
        if (this.a != null) {
            TreeMap a = a(this.a, "tk_file");
            if (a == null) {
                list = arrayList;
            } else {
                for (Long l : a.keySet()) {
                    WloginAllSigInfo wloginAllSigInfo = (WloginAllSigInfo) this.c.get(l);
                    if (wloginAllSigInfo == null) {
                        wloginAllSigInfo = (WloginAllSigInfo) a.get(l);
                        if (wloginAllSigInfo != null) {
                            this.c.put(l, wloginAllSigInfo);
                        } else {
                            continue;
                        }
                    }
                    WloginAllSigInfo wloginAllSigInfo2 = wloginAllSigInfo;
                    for (Long l2 : wloginAllSigInfo2._tk_map.keySet()) {
                        WloginSigInfo wloginSigInfo = (WloginSigInfo) wloginAllSigInfo2._tk_map.get(l2);
                        if (wloginSigInfo != null) {
                            int i;
                            String b = b(l);
                            if (b == null) {
                                b = String.valueOf(l);
                            }
                            if (wloginAllSigInfo2._useInfo._img_url == null) {
                                wloginAllSigInfo2._useInfo._img_url = new byte[0];
                            }
                            long longValue = l.longValue();
                            long longValue2 = l2.longValue();
                            String str = new String(wloginAllSigInfo2._useInfo._img_url);
                            long j = wloginSigInfo._create_time;
                            if (z) {
                                i = WloginLoginInfo.TYPE_LOACL;
                            } else {
                                i = WloginLoginInfo.TYPE_REMOTE;
                            }
                            arrayList.add(new WloginLoginInfo(b, longValue, longValue2, str, j, i, wloginSigInfo._login_bitmap));
                        }
                    }
                    continue;
                }
            }
        }
        list = arrayList;
        return list;
    }

    public synchronized void b(String str) {
        this.d.remove(str);
        util.LOGI("clear_account " + str, "");
        if (this.a != null) {
            synchronized (g) {
                TreeMap a = a(this.a, "name_file");
                if (a == null) {
                } else {
                    a.remove(str);
                    a(a, "name_file");
                }
            }
        }
    }

    public synchronized int a(TreeMap treeMap, String str) {
        int i;
        i = 0;
        if ("tk_file".equals(str) || "name_file".equals(str)) {
            i = b(treeMap, str);
        }
        return i;
    }

    public synchronized int b(TreeMap treeMap, String str) {
        int a;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(treeMap);
            objectOutputStream.flush();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            a = a(this.a, str, cryptor.encrypt(toByteArray, 0, toByteArray.length, u.B));
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (Throwable th) {
            util.LOGI("saveTKTreeMap failed " + th.getStackTrace().toString(), "");
            util.printThrowable(th, "");
            a = -1022;
        }
        return a;
    }

    public static int a(byte[] bArr, int i, byte[] bArr2) {
        for (int i2 = i; i2 < (bArr.length - bArr2.length) - i; i2++) {
            int i3;
            for (i3 = 0; i3 < bArr2.length; i3++) {
                if (bArr[i2 + i3] != bArr2[i3]) {
                    Object obj = null;
                    break;
                }
            }
            i3 = 1;
            if (obj != null) {
                return i2;
            }
        }
        return -1;
    }

    public static TreeMap a(Context context, String str) {
        util.LOGI("loadTKTreeMap sigfile " + str, "");
        byte[] b = b(context, str);
        if (b != null) {
            util.LOGI("loadTKTreeMap len:" + b.length + " at " + u.l(), "");
            try {
                byte[] decrypt = cryptor.decrypt(b, 0, b.length, u.B);
                TreeMap treeMap;
                if (decrypt != null) {
                    InputStream byteArrayInputStream = new ByteArrayInputStream(decrypt);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    treeMap = (TreeMap) objectInputStream.readObject();
                    objectInputStream.close();
                    byteArrayInputStream.close();
                    if (treeMap != null) {
                        util.LOGI("loadTKTreeMap tree size: " + treeMap.size(), "");
                        if ("tk_file".equals(str)) {
                            for (Object next : treeMap.keySet()) {
                                util.LOGI(next + " allsig: " + ((WloginAllSigInfo) treeMap.get(next))._tk_map, "" + next);
                            }
                            return treeMap;
                        }
                        for (Object next2 : treeMap.keySet()) {
                            UinInfo uinInfo = (UinInfo) treeMap.get(next2);
                            if (uinInfo != null) {
                                util.LOGI(next2 + " is uin: " + uinInfo._uin, "");
                            }
                        }
                        return treeMap;
                    }
                    util.LOGI("tree is null", "");
                    return null;
                }
                Key secretKeySpec = new SecretKeySpec(u.B, "DESede");
                Cipher instance = Cipher.getInstance("DESede");
                instance.init(2, secretKeySpec);
                InputStream byteArrayInputStream2 = new ByteArrayInputStream(b);
                ObjectInputStream objectInputStream2 = new ObjectInputStream(new CipherInputStream(byteArrayInputStream2, instance));
                treeMap = (TreeMap) objectInputStream2.readObject();
                if (treeMap != null) {
                    objectInputStream2.close();
                    byteArrayInputStream2.close();
                    return treeMap;
                }
            } catch (Throwable th) {
                util.printThrowable(th, "");
            }
        } else {
            util.LOGI("dbdata is null", "");
        }
        return c(context, str);
    }

    private static TreeMap c(Context context, String str) {
        ObjectInputStream objectInputStream;
        TreeMap treeMap;
        Throwable th;
        byte[] bArr;
        FileInputStream openFileInput;
        int read;
        Cipher instance;
        ObjectInputStream objectInputStream2;
        ObjectInputStream objectInputStream3;
        byte[] bArr2;
        int length;
        FileInputStream openFileInput2;
        FileOutputStream openFileOutput;
        int read2;
        int i;
        byte[] bytes;
        Key secretKeySpec;
        try {
            secretKeySpec = new SecretKeySpec(u.B, "DESede");
            Cipher instance2 = Cipher.getInstance("DESede");
            instance2.init(2, secretKeySpec);
            objectInputStream = new ObjectInputStream(new CipherInputStream(context.openFileInput(str), instance2));
            try {
                treeMap = (TreeMap) objectInputStream.readObject();
            } catch (Throwable th2) {
                th = th2;
                if (!(th instanceof FileNotFoundException)) {
                    try {
                        bArr = new byte[256];
                        openFileInput = context.openFileInput(str);
                        while (true) {
                            read = openFileInput.read(bArr, 0, bArr.length);
                            if (read > 0) {
                                break;
                            }
                            util.LOGI(util.buf_to_string(bArr, read));
                        }
                        openFileInput.close();
                    } catch (Exception e) {
                    }
                }
                try {
                    secretKeySpec = new SecretKeySpec(new String("%4;7t>;28<fc.5*6").getBytes(), "DESede");
                    instance = Cipher.getInstance("DESede");
                    instance.init(2, secretKeySpec);
                    objectInputStream2 = new ObjectInputStream(new CipherInputStream(context.openFileInput(str), instance));
                    try {
                        treeMap = (TreeMap) objectInputStream2.readObject();
                        objectInputStream = objectInputStream2;
                    } catch (Throwable th3) {
                        objectInputStream3 = objectInputStream2;
                        try {
                            bArr2 = new byte[256];
                            length = bArr2.length - 40;
                            openFileInput2 = context.openFileInput(str);
                            openFileOutput = context.openFileOutput("tmp_tk_file", 0);
                            while (true) {
                                read2 = openFileInput2.read(bArr2, 40, length);
                                if (read2 > 0) {
                                    break;
                                }
                                for (i = 40 + read2; i < bArr2.length; i++) {
                                    bArr2[i] = (byte) 0;
                                }
                                i = 0;
                                while (i >= 0) {
                                    bytes = new String("WloginAllSigInfo").getBytes();
                                    i = a(bArr2, i, bytes);
                                    if (i < 0) {
                                        break;
                                    }
                                    i += bytes.length;
                                    if (i + 8 <= bArr2.length && bArr2[i + 0] == (byte) -127 && bArr2[i + 1] == (byte) 64 && bArr2[i + 2] == (byte) 1 && bArr2[i + 3] == (byte) 111 && bArr2[i + 4] == (byte) -111 && bArr2[i + 5] == (byte) -44 && bArr2[i + 6] == (byte) 26 && bArr2[i + 7] == (byte) -101) {
                                        bArr2[i + 0] = (byte) 0;
                                        bArr2[i + 1] = (byte) 0;
                                        bArr2[i + 2] = (byte) 0;
                                        bArr2[i + 3] = (byte) 0;
                                        bArr2[i + 4] = (byte) 0;
                                        bArr2[i + 5] = (byte) 0;
                                        bArr2[i + 6] = (byte) 0;
                                        bArr2[i + 7] = (byte) 1;
                                    }
                                }
                                i = 0;
                                while (i >= 0) {
                                    bytes = new String("WloginSigInfo").getBytes();
                                    i = a(bArr2, i, bytes);
                                    if (i < 0) {
                                        break;
                                    }
                                    i += bytes.length;
                                    if (i + 8 <= bArr2.length && bArr2[i + 0] == (byte) 0 && bArr2[i + 1] == (byte) 0 && bArr2[i + 2] == (byte) 0 && bArr2[i + 3] == (byte) 0 && bArr2[i + 4] == (byte) 0 && bArr2[i + 5] == (byte) 0 && bArr2[i + 6] == (byte) 0 && bArr2[i + 7] == (byte) 0) {
                                        bArr2[i + 0] = (byte) 0;
                                        bArr2[i + 1] = (byte) 0;
                                        bArr2[i + 2] = (byte) 0;
                                        bArr2[i + 3] = (byte) 0;
                                        bArr2[i + 4] = (byte) 0;
                                        bArr2[i + 5] = (byte) 0;
                                        bArr2[i + 6] = (byte) 0;
                                        bArr2[i + 7] = (byte) 1;
                                    }
                                }
                                i = 0;
                                while (i >= 0) {
                                    bytes = new String("WloginSimpleInfo").getBytes();
                                    i = a(bArr2, i, bytes);
                                    if (i < 0) {
                                        break;
                                    }
                                    i += bytes.length;
                                    if (i + 8 <= bArr2.length && bArr2[i + 0] == (byte) 57 && bArr2[i + 1] == (byte) -69 && bArr2[i + 2] == (byte) -84 && bArr2[i + 3] == (byte) 110 && bArr2[i + 4] == (byte) -46 && bArr2[i + 5] == (byte) 98 && bArr2[i + 6] == (byte) -31 && bArr2[i + 7] == (byte) -113) {
                                        bArr2[i + 0] = (byte) 0;
                                        bArr2[i + 1] = (byte) 0;
                                        bArr2[i + 2] = (byte) 0;
                                        bArr2[i + 3] = (byte) 0;
                                        bArr2[i + 4] = (byte) 0;
                                        bArr2[i + 5] = (byte) 0;
                                        bArr2[i + 6] = (byte) 0;
                                        bArr2[i + 7] = (byte) 1;
                                    }
                                }
                                i = 0;
                                while (i >= 0) {
                                    bytes = new String("UinInfo").getBytes();
                                    i = a(bArr2, i, bytes);
                                    if (i < 0) {
                                        break;
                                    }
                                    i += bytes.length;
                                    if (i + 8 <= bArr2.length && bArr2[i + 0] == (byte) -118 && bArr2[i + 1] == (byte) -23 && bArr2[i + 2] == Byte.MIN_VALUE && bArr2[i + 3] == (byte) -19 && bArr2[i + 4] == (byte) -26 && bArr2[i + 5] == (byte) 99 && bArr2[i + 6] == (byte) 41 && bArr2[i + 7] == (byte) 14) {
                                        bArr2[i + 0] = (byte) 0;
                                        bArr2[i + 1] = (byte) 0;
                                        bArr2[i + 2] = (byte) 0;
                                        bArr2[i + 3] = (byte) 0;
                                        bArr2[i + 4] = (byte) 0;
                                        bArr2[i + 5] = (byte) 0;
                                        bArr2[i + 6] = (byte) 0;
                                        bArr2[i + 7] = (byte) 1;
                                    }
                                }
                                openFileOutput.write(bArr2, 40, read2);
                                if (read2 > 40) {
                                    System.arraycopy(bArr2, read2, bArr2, 0, 40);
                                }
                            }
                            openFileInput2.close();
                            openFileOutput.close();
                            objectInputStream = new ObjectInputStream(context.openFileInput("tmp_tk_file"));
                            try {
                                treeMap = (TreeMap) objectInputStream.readObject();
                            } catch (Exception e2) {
                                objectInputStream3 = objectInputStream;
                                objectInputStream = objectInputStream3;
                                treeMap = null;
                                context.deleteFile("tmp_tk_file");
                                if (treeMap == null) {
                                    return treeMap;
                                }
                                try {
                                    objectInputStream.close();
                                    return treeMap;
                                } catch (Exception e3) {
                                    return null;
                                }
                            }
                        } catch (Exception e4) {
                        }
                        context.deleteFile("tmp_tk_file");
                        if (treeMap == null) {
                            return treeMap;
                        }
                        objectInputStream.close();
                        return treeMap;
                    }
                } catch (Throwable th4) {
                    objectInputStream3 = objectInputStream;
                    bArr2 = new byte[256];
                    length = bArr2.length - 40;
                    openFileInput2 = context.openFileInput(str);
                    openFileOutput = context.openFileOutput("tmp_tk_file", 0);
                    while (true) {
                        read2 = openFileInput2.read(bArr2, 40, length);
                        if (read2 > 0) {
                            break;
                        }
                        for (i = 40 + read2; i < bArr2.length; i++) {
                            bArr2[i] = (byte) 0;
                        }
                        i = 0;
                        while (i >= 0) {
                            bytes = new String("WloginAllSigInfo").getBytes();
                            i = a(bArr2, i, bytes);
                            if (i < 0) {
                                break;
                            }
                            i += bytes.length;
                            bArr2[i + 0] = (byte) 0;
                            bArr2[i + 1] = (byte) 0;
                            bArr2[i + 2] = (byte) 0;
                            bArr2[i + 3] = (byte) 0;
                            bArr2[i + 4] = (byte) 0;
                            bArr2[i + 5] = (byte) 0;
                            bArr2[i + 6] = (byte) 0;
                            bArr2[i + 7] = (byte) 1;
                        }
                        i = 0;
                        while (i >= 0) {
                            bytes = new String("WloginSigInfo").getBytes();
                            i = a(bArr2, i, bytes);
                            if (i < 0) {
                                break;
                            }
                            i += bytes.length;
                            bArr2[i + 0] = (byte) 0;
                            bArr2[i + 1] = (byte) 0;
                            bArr2[i + 2] = (byte) 0;
                            bArr2[i + 3] = (byte) 0;
                            bArr2[i + 4] = (byte) 0;
                            bArr2[i + 5] = (byte) 0;
                            bArr2[i + 6] = (byte) 0;
                            bArr2[i + 7] = (byte) 1;
                        }
                        i = 0;
                        while (i >= 0) {
                            bytes = new String("WloginSimpleInfo").getBytes();
                            i = a(bArr2, i, bytes);
                            if (i < 0) {
                                break;
                            }
                            i += bytes.length;
                            bArr2[i + 0] = (byte) 0;
                            bArr2[i + 1] = (byte) 0;
                            bArr2[i + 2] = (byte) 0;
                            bArr2[i + 3] = (byte) 0;
                            bArr2[i + 4] = (byte) 0;
                            bArr2[i + 5] = (byte) 0;
                            bArr2[i + 6] = (byte) 0;
                            bArr2[i + 7] = (byte) 1;
                        }
                        i = 0;
                        while (i >= 0) {
                            bytes = new String("UinInfo").getBytes();
                            i = a(bArr2, i, bytes);
                            if (i < 0) {
                                break;
                            }
                            i += bytes.length;
                            bArr2[i + 0] = (byte) 0;
                            bArr2[i + 1] = (byte) 0;
                            bArr2[i + 2] = (byte) 0;
                            bArr2[i + 3] = (byte) 0;
                            bArr2[i + 4] = (byte) 0;
                            bArr2[i + 5] = (byte) 0;
                            bArr2[i + 6] = (byte) 0;
                            bArr2[i + 7] = (byte) 1;
                        }
                        openFileOutput.write(bArr2, 40, read2);
                        if (read2 > 40) {
                            System.arraycopy(bArr2, read2, bArr2, 0, 40);
                        }
                    }
                    openFileInput2.close();
                    openFileOutput.close();
                    objectInputStream = new ObjectInputStream(context.openFileInput("tmp_tk_file"));
                    treeMap = (TreeMap) objectInputStream.readObject();
                    context.deleteFile("tmp_tk_file");
                    if (treeMap == null) {
                        return treeMap;
                    }
                    objectInputStream.close();
                    return treeMap;
                }
                if (treeMap == null) {
                    return treeMap;
                }
                objectInputStream.close();
                return treeMap;
            }
        } catch (Throwable th5) {
            th = th5;
            objectInputStream = null;
            if (th instanceof FileNotFoundException) {
                bArr = new byte[256];
                openFileInput = context.openFileInput(str);
                while (true) {
                    read = openFileInput.read(bArr, 0, bArr.length);
                    if (read > 0) {
                        break;
                    }
                    util.LOGI(util.buf_to_string(bArr, read));
                }
                openFileInput.close();
            }
            secretKeySpec = new SecretKeySpec(new String("%4;7t>;28<fc.5*6").getBytes(), "DESede");
            instance = Cipher.getInstance("DESede");
            instance.init(2, secretKeySpec);
            objectInputStream2 = new ObjectInputStream(new CipherInputStream(context.openFileInput(str), instance));
            treeMap = (TreeMap) objectInputStream2.readObject();
            objectInputStream = objectInputStream2;
            if (treeMap == null) {
                return treeMap;
            }
            objectInputStream.close();
            return treeMap;
        }
        if (treeMap == null) {
            return treeMap;
        }
        objectInputStream.close();
        return treeMap;
    }

    private static void c(String str) {
        util.LOGI("file " + str + " last update stample " + new File(str).lastModified(), "");
    }

    public static int a(Context context, String str, byte[] bArr) {
        SQLiteDatabase writableDatabase;
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Cursor cursor;
        Throwable th;
        SQLiteDatabase sQLiteDatabase2;
        Throwable th2;
        Cursor cursor2 = null;
        String str2 = "";
        try {
            if (str.equals("tk_file")) {
                if (h == null) {
                    h = new b(context, str, null, 1);
                }
                writableDatabase = h.getWritableDatabase();
            } else {
                if (i == null) {
                    i = new b(context, str, null, 1);
                }
                writableDatabase = i.getWritableDatabase();
            }
            try {
                c(writableDatabase.getPath());
                writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + str + " (ID INTEGER PRIMARY KEY, " + str + " BLOB);");
                Cursor query = writableDatabase.query(str, new String[]{"ID"}, "ID=0", null, null, null, null);
                try {
                    if (query.getCount() == 0) {
                        writableDatabase.execSQL("insert into " + str + " (ID, " + str + ") values (?,?);", new Object[]{Integer.valueOf(0), new byte[1]});
                    }
                    writableDatabase.execSQL("update " + str + " set " + str + " =? where ID=0", new Object[]{bArr});
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    if (writableDatabase != null && true == writableDatabase.isOpen()) {
                        util.LOGI("write_to_db db closed", "");
                        writableDatabase.close();
                    }
                    return 0;
                } catch (Exception e) {
                    sQLiteDatabase = writableDatabase;
                    exception = e;
                    cursor = query;
                    try {
                        if (str.equals("tk_file")) {
                            i = null;
                        } else {
                            h = null;
                        }
                        util.printException(exception, "");
                        util.LOGI("save ticket to file failed", "");
                        cursor.close();
                        return sQLiteDatabase != null ? -1022 : -1022;
                    } catch (Throwable th3) {
                        th = th3;
                        Cursor cursor3 = cursor;
                        sQLiteDatabase2 = sQLiteDatabase;
                        cursor2 = cursor3;
                        cursor2.close();
                        util.LOGI("write_to_db db closed", "");
                        sQLiteDatabase2.close();
                        throw th;
                    }
                } catch (Throwable th4) {
                    cursor2 = query;
                    th2 = th4;
                    sQLiteDatabase2 = writableDatabase;
                    th = th2;
                    cursor2.close();
                    util.LOGI("write_to_db db closed", "");
                    sQLiteDatabase2.close();
                    throw th;
                }
            } catch (Exception e2) {
                Exception exception2 = e2;
                cursor = null;
                sQLiteDatabase = writableDatabase;
                exception = exception2;
                if (str.equals("tk_file")) {
                    h = null;
                } else {
                    i = null;
                }
                util.printException(exception, "");
                util.LOGI("save ticket to file failed", "");
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                if (sQLiteDatabase != null && true == sQLiteDatabase.isOpen()) {
                    util.LOGI("write_to_db db closed", "");
                    sQLiteDatabase.close();
                    return -1022;
                }
            } catch (Throwable th42) {
                th2 = th42;
                sQLiteDatabase2 = writableDatabase;
                th = th2;
                if (!(cursor2 == null || cursor2.isClosed())) {
                    cursor2.close();
                }
                if (sQLiteDatabase2 != null && true == sQLiteDatabase2.isOpen()) {
                    util.LOGI("write_to_db db closed", "");
                    sQLiteDatabase2.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            exception = e3;
            cursor = null;
            if (str.equals("tk_file")) {
                h = null;
            } else {
                i = null;
            }
            util.printException(exception, "");
            util.LOGI("save ticket to file failed", "");
            cursor.close();
            if (sQLiteDatabase != null) {
            }
        } catch (Throwable th5) {
            th = th5;
            sQLiteDatabase2 = null;
            cursor2.close();
            util.LOGI("write_to_db db closed", "");
            sQLiteDatabase2.close();
            throw th;
        }
    }

    public static byte[] b(Context context, String str) {
        SQLiteDatabase readableDatabase;
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        String str2 = "";
        Boolean valueOf = Boolean.valueOf(false);
        try {
            Cursor rawQuery;
            if (str.equals("tk_file")) {
                if (h == null) {
                    h = new b(context, str, null, 1);
                }
                readableDatabase = h.getReadableDatabase();
            } else {
                if (i == null) {
                    i = new b(context, str, null, 1);
                }
                readableDatabase = i.getReadableDatabase();
            }
            try {
                c(readableDatabase.getPath());
                rawQuery = readableDatabase.rawQuery("select count(*) from sqlite_master where type ='table' and name ='" + str + "' ", null);
            } catch (Exception e) {
                sQLiteDatabase = readableDatabase;
                exception = e;
                cursor = null;
                try {
                    if (str.equals("tk_file")) {
                        h = null;
                    } else {
                        i = null;
                    }
                    util.printException(exception, "");
                    cursor.close();
                    util.LOGI("get_from_db db closed", "");
                    sQLiteDatabase.close();
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = cursor;
                    cursor2.close();
                    util.LOGI("get_from_db db closed", "");
                    sQLiteDatabase.close();
                    throw th;
                }
            } catch (Throwable th3) {
                sQLiteDatabase = readableDatabase;
                th = th3;
                cursor2.close();
                util.LOGI("get_from_db db closed", "");
                sQLiteDatabase.close();
                throw th;
            }
            try {
                if (rawQuery.moveToNext() && rawQuery.getInt(0) > 0) {
                    valueOf = Boolean.valueOf(true);
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                if (valueOf.booleanValue()) {
                    String str3 = str;
                    Cursor query = readableDatabase.query(str3, new String[]{str}, "ID=0", null, null, null, null);
                    if (query == null) {
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        if (readableDatabase != null && true == readableDatabase.isOpen()) {
                            util.LOGI("get_from_db db closed", "");
                            readableDatabase.close();
                        }
                        return null;
                    }
                    try {
                        if (query.moveToFirst()) {
                            byte[] blob = query.getBlob(0);
                            query.close();
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            if (readableDatabase != null && true == readableDatabase.isOpen()) {
                                util.LOGI("get_from_db db closed", "");
                                readableDatabase.close();
                            }
                            return blob;
                        }
                        query.close();
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        if (readableDatabase != null && true == readableDatabase.isOpen()) {
                            util.LOGI("get_from_db db closed", "");
                            readableDatabase.close();
                        }
                        return null;
                    } catch (Exception e2) {
                        Exception exception2 = e2;
                        cursor = query;
                        sQLiteDatabase = readableDatabase;
                        exception = exception2;
                        if (str.equals("tk_file")) {
                            h = null;
                        } else {
                            i = null;
                        }
                        util.printException(exception, "");
                        cursor.close();
                        util.LOGI("get_from_db db closed", "");
                        sQLiteDatabase.close();
                        return null;
                    } catch (Throwable th32) {
                        cursor2 = query;
                        sQLiteDatabase = readableDatabase;
                        th = th32;
                        cursor2.close();
                        util.LOGI("get_from_db db closed", "");
                        sQLiteDatabase.close();
                        throw th;
                    }
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                if (readableDatabase != null && true == readableDatabase.isOpen()) {
                    util.LOGI("get_from_db db closed", "");
                    readableDatabase.close();
                }
                return null;
            } catch (Exception e22) {
                sQLiteDatabase = readableDatabase;
                exception = e22;
                cursor = rawQuery;
                if (str.equals("tk_file")) {
                    i = null;
                } else {
                    h = null;
                }
                util.printException(exception, "");
                cursor.close();
                util.LOGI("get_from_db db closed", "");
                sQLiteDatabase.close();
                return null;
            } catch (Throwable th322) {
                cursor2 = rawQuery;
                sQLiteDatabase = readableDatabase;
                th = th322;
                cursor2.close();
                util.LOGI("get_from_db db closed", "");
                sQLiteDatabase.close();
                throw th;
            }
        } catch (Exception e3) {
            exception = e3;
            cursor = null;
            sQLiteDatabase = null;
            if (str.equals("tk_file")) {
                h = null;
            } else {
                i = null;
            }
            util.printException(exception, "");
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
            if (sQLiteDatabase != null && true == sQLiteDatabase.isOpen()) {
                util.LOGI("get_from_db db closed", "");
                sQLiteDatabase.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            sQLiteDatabase = null;
            if (!(cursor2 == null || cursor2.isClosed())) {
                cursor2.close();
            }
            if (sQLiteDatabase != null && true == sQLiteDatabase.isOpen()) {
                util.LOGI("get_from_db db closed", "");
                sQLiteDatabase.close();
            }
            throw th;
        }
    }
}
