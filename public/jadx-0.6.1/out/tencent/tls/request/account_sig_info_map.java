package tencent.tls.request;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import tencent.tls.platform.TLSUserInfo;
import tencent.tls.report.QLog;
import tencent.tls.request.TinyInfo.UserType;
import tencent.tls.tools.cryptor;
import tencent.tls.tools.util;

public class account_sig_info_map {
    public static final String TLS_ACCOUNT_TABLE = "name_tree";
    public static final String TLS_TICKET_TABLE = "tk_tree";
    private static final Object db_lock = new Object();
    private static TkDBHelper tlsDBHelper = null;
    public static final String tls_sdk_DBNAME = "tls_sdk.db";
    Context _context;
    TreeMap<String, TinyInfo> _name_map = new TreeMap();
    TreeMap<Long, AllSigInfo> _uin_map = new TreeMap();

    public account_sig_info_map(Context context) {
        this._context = context;
    }

    public synchronized int put_siginfo(long j, long j2, byte[] bArr, byte[] bArr2, long j3, long j4, long j5, ArrayList<Ticket> arrayList, int i) {
        int i2;
        i2 = 0;
        if (this._context != null) {
            int refreshTKTreeMap;
            synchronized (db_lock) {
                TreeMap treeMap;
                AllSigInfo allSigInfo;
                byte[] bArr3;
                byte[] bArr4;
                SigInfo sigInfo;
                byte[] bArr5;
                byte[] bArr6;
                QLog.i("before put_siginfo", j);
                TreeMap loadTKTreeMap = loadTKTreeMap(this._context, TLS_TICKET_TABLE);
                if (loadTKTreeMap == null) {
                    treeMap = new TreeMap();
                } else {
                    treeMap = loadTKTreeMap;
                }
                AllSigInfo allSigInfo2 = (AllSigInfo) this._uin_map.get(Long.valueOf(j));
                if (allSigInfo2 == null) {
                    allSigInfo2 = (AllSigInfo) treeMap.get(Long.valueOf(j));
                    if (allSigInfo2 == null) {
                        allSigInfo = new AllSigInfo();
                        bArr3 = new byte[0];
                        bArr4 = new byte[0];
                        sigInfo = (SigInfo) allSigInfo._tk_map.get(Long.valueOf(j2));
                        if (!(sigInfo == null || sigInfo._en_A1 == null)) {
                            bArr3 = (byte[]) sigInfo._en_A1.clone();
                            if (sigInfo._noPicSig != null) {
                                bArr5 = (byte[]) sigInfo._noPicSig.clone();
                                bArr6 = bArr3;
                                allSigInfo.put_siginfo(j3, j4, j5, arrayList, i);
                                if (bArr != null && bArr.length > 0) {
                                    allSigInfo.put_siginfo(j2, bArr, bArr2, j5);
                                }
                                treeMap.put(Long.valueOf(j), allSigInfo);
                                refreshTKTreeMap = refreshTKTreeMap(treeMap, TLS_TICKET_TABLE);
                                if (refreshTKTreeMap != 0) {
                                    sigInfo = (SigInfo) allSigInfo._tk_map.get(Long.valueOf(j2));
                                    if (sigInfo != null) {
                                        sigInfo._en_A1 = bArr6;
                                        sigInfo._noPicSig = bArr5;
                                    }
                                }
                                this._uin_map.put(Long.valueOf(j), allSigInfo);
                                QLog.i("after put_siginfo", j);
                            }
                        }
                        bArr5 = bArr4;
                        bArr6 = bArr3;
                        allSigInfo.put_siginfo(j3, j4, j5, arrayList, i);
                        allSigInfo.put_siginfo(j2, bArr, bArr2, j5);
                        treeMap.put(Long.valueOf(j), allSigInfo);
                        refreshTKTreeMap = refreshTKTreeMap(treeMap, TLS_TICKET_TABLE);
                        if (refreshTKTreeMap != 0) {
                            sigInfo = (SigInfo) allSigInfo._tk_map.get(Long.valueOf(j2));
                            if (sigInfo != null) {
                                sigInfo._en_A1 = bArr6;
                                sigInfo._noPicSig = bArr5;
                            }
                        }
                        this._uin_map.put(Long.valueOf(j), allSigInfo);
                        QLog.i("after put_siginfo", j);
                    }
                }
                allSigInfo = allSigInfo2;
                bArr3 = new byte[0];
                bArr4 = new byte[0];
                sigInfo = (SigInfo) allSigInfo._tk_map.get(Long.valueOf(j2));
                bArr3 = (byte[]) sigInfo._en_A1.clone();
                if (sigInfo._noPicSig != null) {
                    bArr5 = (byte[]) sigInfo._noPicSig.clone();
                    bArr6 = bArr3;
                    allSigInfo.put_siginfo(j3, j4, j5, arrayList, i);
                    allSigInfo.put_siginfo(j2, bArr, bArr2, j5);
                    treeMap.put(Long.valueOf(j), allSigInfo);
                    refreshTKTreeMap = refreshTKTreeMap(treeMap, TLS_TICKET_TABLE);
                    if (refreshTKTreeMap != 0) {
                        sigInfo = (SigInfo) allSigInfo._tk_map.get(Long.valueOf(j2));
                        if (sigInfo != null) {
                            sigInfo._en_A1 = bArr6;
                            sigInfo._noPicSig = bArr5;
                        }
                    }
                    this._uin_map.put(Long.valueOf(j), allSigInfo);
                    QLog.i("after put_siginfo", j);
                }
                bArr5 = bArr4;
                bArr6 = bArr3;
                allSigInfo.put_siginfo(j3, j4, j5, arrayList, i);
                allSigInfo.put_siginfo(j2, bArr, bArr2, j5);
                treeMap.put(Long.valueOf(j), allSigInfo);
                refreshTKTreeMap = refreshTKTreeMap(treeMap, TLS_TICKET_TABLE);
                if (refreshTKTreeMap != 0) {
                    sigInfo = (SigInfo) allSigInfo._tk_map.get(Long.valueOf(j2));
                    if (sigInfo != null) {
                        sigInfo._en_A1 = bArr6;
                        sigInfo._noPicSig = bArr5;
                    }
                }
                this._uin_map.put(Long.valueOf(j), allSigInfo);
                QLog.i("after put_siginfo", j);
            }
            i2 = refreshTKTreeMap;
        }
        return i2;
    }

    public synchronized AllSigInfo get_all_siginfo(long j) {
        AllSigInfo allSigInfo;
        QLog.i("get_all_siginfo", j);
        allSigInfo = (AllSigInfo) this._uin_map.get(Long.valueOf(j));
        if (allSigInfo == null) {
            TreeMap loadTKTreeMap = loadTKTreeMap(this._context, TLS_TICKET_TABLE);
            if (loadTKTreeMap == null) {
                allSigInfo = null;
            } else {
                allSigInfo = (AllSigInfo) loadTKTreeMap.get(Long.valueOf(j));
                if (allSigInfo == null) {
                    allSigInfo = null;
                } else {
                    this._uin_map.put(Long.valueOf(j), allSigInfo);
                    allSigInfo = allSigInfo.clone();
                }
            }
        }
        return allSigInfo;
    }

    public synchronized void refresh_all_siginfo() {
        QLog.i("refresh_all_siginfo...");
        this._uin_map = loadTKTreeMap(this._context, TLS_ACCOUNT_TABLE);
        if (this._uin_map == null) {
            this._uin_map = new TreeMap();
        }
    }

    public synchronized SigInfo get_siginfo(long j, long j2) {
        SigInfo sigInfo;
        QLog.i("get_siginfo", j);
        AllSigInfo allSigInfo = get_all_siginfo(j);
        if (allSigInfo == null) {
            sigInfo = null;
        } else {
            sigInfo = (SigInfo) allSigInfo._tk_map.get(Long.valueOf(j2));
            if (sigInfo == null) {
                sigInfo = null;
            }
        }
        return sigInfo;
    }

    public synchronized void clear_sig(long j, long j2) {
        QLog.i("clear_sig sdkAppid=" + j2, j);
        this._uin_map.remove(Long.valueOf(j));
        if (this._context != null) {
            synchronized (db_lock) {
                TreeMap loadTKTreeMap = loadTKTreeMap(this._context, TLS_TICKET_TABLE);
                if (loadTKTreeMap == null) {
                } else {
                    loadTKTreeMap.remove(Long.valueOf(j));
                    refreshTKTreeMap(loadTKTreeMap, TLS_TICKET_TABLE);
                }
            }
        }
    }

    public synchronized void remove_account(String str) {
        QLog.i("remove_account " + str);
        TinyInfo tinyInfo = getTinyInfo(str);
        if (tinyInfo != null) {
            TinyInfo tinyInfo2;
            Set<String> keySet = this._name_map.keySet();
            List<String> arrayList = new ArrayList();
            for (String str2 : keySet) {
                tinyInfo2 = (TinyInfo) this._name_map.get(str2);
                if (tinyInfo2 != null && tinyInfo2._tinyid == tinyInfo._tinyid) {
                    arrayList.add(str2);
                }
            }
            for (String str22 : arrayList) {
                this._name_map.remove(str22);
            }
            arrayList.clear();
            if (this._context != null) {
                synchronized (db_lock) {
                    TreeMap treeMap;
                    TreeMap loadTKTreeMap = loadTKTreeMap(this._context, TLS_ACCOUNT_TABLE);
                    if (loadTKTreeMap == null) {
                        treeMap = new TreeMap();
                    } else {
                        treeMap = loadTKTreeMap;
                    }
                    for (String str222 : treeMap.keySet()) {
                        tinyInfo2 = (TinyInfo) treeMap.get(str222);
                        if (tinyInfo2 != null && tinyInfo2._tinyid == tinyInfo._tinyid) {
                            arrayList.add(str222);
                        }
                    }
                    for (String str2222 : arrayList) {
                        treeMap.remove(str2222);
                    }
                    refreshTKTreeMap(treeMap, TLS_ACCOUNT_TABLE);
                }
            }
        }
    }

    public synchronized void putTinyInfo(String str, String str2, long j, UserType userType) {
        QLog.i("putTinyInfo " + str + " userType " + userType + " tinyid " + j);
        TinyInfo tinyInfo = getTinyInfo(str);
        if (tinyInfo != null) {
            tinyInfo._tinyid = j;
        } else {
            QLog.i("new tinyInfo");
            tinyInfo = new TinyInfo(req_global._acc_type, str2, j, userType);
        }
        tinyInfo.userType = userType;
        if (userType == UserType.USER_TYPE_GUEST) {
            tinyInfo._userid = str2;
            this._name_map.put(str, tinyInfo);
        } else {
            tinyInfo._userid = str2;
            this._name_map.put(str, tinyInfo);
        }
        if (!str.equals(str2)) {
            this._name_map.put(str2, tinyInfo);
        }
        if (this._context != null) {
            synchronized (db_lock) {
                TreeMap loadTKTreeMap = loadTKTreeMap(this._context, TLS_ACCOUNT_TABLE);
                if (loadTKTreeMap == null) {
                    loadTKTreeMap = new TreeMap();
                }
                loadTKTreeMap.put(str, tinyInfo);
                refreshTKTreeMap(loadTKTreeMap, TLS_ACCOUNT_TABLE);
            }
        }
    }

    public synchronized TinyInfo getTinyInfo(String str) {
        TinyInfo tinyInfo;
        QLog.i("getTinyInfo " + util.getLineInfo(3) + util.getLineInfo(4));
        tinyInfo = (TinyInfo) this._name_map.get(str);
        if (tinyInfo == null) {
            if (this._context == null) {
                tinyInfo = null;
            } else {
                TreeMap loadTKTreeMap = loadTKTreeMap(this._context, TLS_ACCOUNT_TABLE);
                if (loadTKTreeMap == null) {
                    tinyInfo = null;
                } else {
                    tinyInfo = (TinyInfo) loadTKTreeMap.get(str);
                    if (tinyInfo == null) {
                        tinyInfo = null;
                    } else {
                        this._name_map.put(str, tinyInfo);
                    }
                }
            }
        }
        return tinyInfo;
    }

    private synchronized TinyInfo getTinyInfo(long j) {
        TinyInfo tinyInfo;
        for (TinyInfo tinyInfo2 : this._name_map.values()) {
            if (tinyInfo2._tinyid == j) {
                break;
            }
        }
        if (this._context == null) {
            tinyInfo2 = null;
        } else {
            TreeMap loadTKTreeMap = loadTKTreeMap(this._context, TLS_ACCOUNT_TABLE);
            if (loadTKTreeMap == null) {
                tinyInfo2 = null;
            } else {
                for (TinyInfo tinyInfo22 : loadTKTreeMap.values()) {
                    if (tinyInfo22._tinyid == j) {
                        this._name_map.put(tinyInfo22._userid, tinyInfo22);
                        break;
                    }
                }
                tinyInfo22 = null;
            }
        }
        return tinyInfo22;
    }

    public synchronized List<TLSUserInfo> get_all_logined_account() {
        List<TLSUserInfo> list;
        List<TLSUserInfo> arrayList = new ArrayList();
        if (this._context != null) {
            TreeMap loadTKTreeMap = loadTKTreeMap(this._context, TLS_TICKET_TABLE);
            if (loadTKTreeMap == null) {
                list = arrayList;
            } else {
                for (Long longValue : loadTKTreeMap.keySet()) {
                    long longValue2 = longValue.longValue();
                    AllSigInfo allSigInfo = (AllSigInfo) this._uin_map.get(Long.valueOf(longValue2));
                    if (allSigInfo == null) {
                        allSigInfo = (AllSigInfo) loadTKTreeMap.get(Long.valueOf(longValue2));
                        if (allSigInfo != null) {
                            this._uin_map.put(Long.valueOf(longValue2), allSigInfo);
                        } else {
                            continue;
                        }
                    }
                    AllSigInfo allSigInfo2 = allSigInfo;
                    TinyInfo tinyInfo = getTinyInfo(longValue2);
                    String str = null;
                    if (tinyInfo != null) {
                        str = tinyInfo._userid;
                    }
                    for (Long longValue3 : allSigInfo2._tk_map.keySet()) {
                        long longValue4 = longValue3.longValue();
                        if (longValue4 == req_global.sdkappid) {
                            SigInfo sigInfo = (SigInfo) allSigInfo2._tk_map.get(Long.valueOf(longValue4));
                            if (sigInfo == null) {
                                continue;
                            } else if (str == null) {
                                clear_sig(longValue2, longValue4);
                            } else {
                                longValue4 = sigInfo._A1_create_time;
                                if (longValue4 <= 0) {
                                    longValue4 = sigInfo._TLS_create_time;
                                }
                                arrayList.add(new TLSUserInfo(tinyInfo._acc_type, str, longValue2, longValue4, tinyInfo.userType));
                            }
                        }
                    }
                    continue;
                }
            }
        }
        list = arrayList;
        return list;
    }

    public synchronized int refreshTKTreeMap(TreeMap<?, ?> treeMap, String str) {
        int i;
        i = 0;
        if (TLS_TICKET_TABLE.equals(str) || TLS_ACCOUNT_TABLE.equals(str)) {
            i = saveTKTreeMap(treeMap, str);
        }
        return i;
    }

    public synchronized int saveTKTreeMap(TreeMap<?, ?> treeMap, String str) {
        int write_to_db;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(treeMap);
            objectOutputStream.flush();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            write_to_db = write_to_db(this._context, str, cryptor.encrypt(toByteArray, 0, toByteArray.length, req_global._IMEI_KEY));
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (Throwable th) {
            QLog.e(th);
            write_to_db = -1022;
        }
        return write_to_db;
    }

    public static TreeMap<?, ?> loadTKTreeMap(Context context, String str) {
        Throwable th;
        byte[] bArr = get_from_db(context, str);
        if (bArr == null) {
            return null;
        }
        QLog.i("get_from_db len:" + bArr.length);
        TreeMap<?, ?> treeMap;
        try {
            bArr = cryptor.decrypt(bArr, 0, bArr.length, req_global._IMEI_KEY);
            if (bArr == null) {
                return null;
            }
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
            treeMap = (TreeMap) objectInputStream.readObject();
            if (treeMap == null) {
                return null;
            }
            try {
                objectInputStream.close();
                return treeMap;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            treeMap = null;
            th = th4;
            QLog.e(th);
            return treeMap;
        }
    }

    public static int write_to_db(Context context, String str, byte[] bArr) {
        TkDBHelper tkDBHelper = null;
        String str2 = "";
        try {
            if (tlsDBHelper == null) {
                tlsDBHelper = new TkDBHelper(context, tls_sdk_DBNAME, null, 1);
            }
            SQLiteDatabase writableDatabase = tlsDBHelper.getWritableDatabase();
            try {
                writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + str + " (ID INTEGER PRIMARY KEY, " + str + " BLOB);");
                try {
                    String str3 = str;
                    Cursor query = writableDatabase.query(str3, new String[]{"ID"}, "ID=0", null, null, null, null);
                    if (query == null || !query.moveToFirst()) {
                        try {
                            writableDatabase.execSQL("insert into " + str + " (ID, " + str + ") values (?,?);", new Object[]{Integer.valueOf(0), new byte[1]});
                        } catch (Throwable e) {
                            QLog.e(e);
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return -1022;
                        }
                    }
                    try {
                        writableDatabase.execSQL("update " + str + " set " + str + " =? where ID=0", new Object[]{bArr});
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        return 0;
                    } catch (Throwable e2) {
                        tlsDBHelper = tkDBHelper;
                        QLog.e(e2);
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        return -1022;
                    }
                } catch (Throwable e22) {
                    QLog.e(e22);
                    if (!(tkDBHelper == null || tkDBHelper.isClosed())) {
                        tkDBHelper.close();
                    }
                    return -1022;
                }
            } catch (Throwable e222) {
                tlsDBHelper = tkDBHelper;
                QLog.e(e222);
                return -1022;
            }
        } catch (Throwable e2222) {
            tlsDBHelper = tkDBHelper;
            QLog.e(e2222);
            return -1022;
        }
    }

    public static byte[] get_from_db(Context context, String str) {
        Throwable e;
        String str2 = "";
        Boolean valueOf = Boolean.valueOf(false);
        try {
            if (tlsDBHelper == null) {
                tlsDBHelper = new TkDBHelper(context, tls_sdk_DBNAME, null, 1);
            }
            SQLiteDatabase writableDatabase = tlsDBHelper.getWritableDatabase();
            Cursor query;
            try {
                Cursor rawQuery = writableDatabase.rawQuery("select count(*) from sqlite_master where type ='table' and name ='" + str + "' ", null);
                try {
                    if (rawQuery.moveToNext() && rawQuery.getInt(0) > 0) {
                        valueOf = Boolean.valueOf(true);
                    }
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                    if (!valueOf.booleanValue()) {
                        return null;
                    }
                    String str3 = str;
                    query = writableDatabase.query(str3, new String[]{str}, "ID=0", null, null, null, null);
                    if (query == null || !query.moveToFirst()) {
                        return null;
                    }
                    byte[] blob = query.getBlob(0);
                    query.close();
                    return blob;
                } catch (SQLException e2) {
                    e = e2;
                    query = rawQuery;
                    tlsDBHelper = null;
                    QLog.e(e);
                    query.close();
                    return null;
                }
            } catch (SQLException e3) {
                e = e3;
                query = null;
                tlsDBHelper = null;
                QLog.e(e);
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                return null;
            }
        } catch (Throwable e4) {
            tlsDBHelper = null;
            QLog.e(e4);
            return null;
        }
    }
}
