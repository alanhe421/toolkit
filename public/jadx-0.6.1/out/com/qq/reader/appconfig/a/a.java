package com.qq.reader.appconfig.a;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.b;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.plugin.w;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/* compiled from: AccountSwitchHandler */
public class a {
    private static a a;
    private static String b = "";
    private Context c = ReaderApplication.getApplicationImp();
    private ArrayList<c> d = new ArrayList();

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    private a() {
    }

    private void e() {
        boolean ck = d.ck(ReaderApplication.getApplicationImp());
        com.qq.reader.common.c.a.bM = com.qq.reader.common.c.a.p + "qmessage.db";
        com.qq.reader.common.c.a.bP = com.qq.reader.common.c.a.p + "fas.db";
        com.qq.reader.common.c.a.bd = com.qq.reader.common.c.a.p + "reading.db";
        com.qq.reader.common.c.a.bf = com.qq.reader.common.c.a.s + "bkd/";
        com.qq.reader.common.c.a.be = ck ? com.qq.reader.common.c.a.r + "bkd/" : com.qq.reader.common.c.a.p + "bkd/";
        com.qq.reader.common.c.a.bx = ck ? com.qq.reader.common.c.a.n + "bkd/" : com.qq.reader.common.c.a.l + "bkd/";
        com.qq.reader.common.c.a.bg = com.qq.reader.common.c.a.be + "default" + ".db";
        com.qq.reader.common.c.a.as = ck ? com.qq.reader.common.c.a.n + "bkd/pdf" + ".db" : com.qq.reader.common.c.a.as;
        com.qq.reader.common.c.a.at = ck ? com.qq.reader.common.c.a.n + "bkd/wps" + ".db" : com.qq.reader.common.c.a.at;
        com.qq.reader.common.c.a.bK = com.qq.reader.common.c.a.p + "account.db";
        com.qq.reader.common.c.a.bL = com.qq.reader.common.c.a.p + "activateshelf.db";
        com.qq.reader.common.c.a.bQ = com.qq.reader.common.c.a.p + "newsign.q";
        com.qq.reader.common.c.a.bh = com.qq.reader.common.c.a.p + "tid.db";
        com.qq.reader.common.c.a.bi = com.qq.reader.common.c.a.p + "cha/";
        com.qq.reader.common.c.a.bm = com.qq.reader.common.c.a.p + "Online/";
        b.a = ck ? com.qq.reader.common.c.a.n + "Online/" : com.qq.reader.common.c.a.l + "Online/";
        b.b = ck ? com.qq.reader.common.c.a.r + "Online/" : com.qq.reader.common.c.a.l + "Online/";
        com.qq.reader.common.c.a.bn = ck ? com.qq.reader.common.c.a.r + "Online/online.db" : com.qq.reader.common.c.a.p + "Online/online.db";
        com.qq.reader.common.c.a.bo = com.qq.reader.common.c.a.p + "Cloud/cloudlist_big.db";
        com.qq.reader.common.c.a.bp = com.qq.reader.common.c.a.p + "Cloud/cloudlist.db";
        com.qq.reader.common.c.a.bq = com.qq.reader.common.c.a.p + "Cloud/cloudnote.db";
        com.qq.reader.common.c.a.br = com.qq.reader.common.c.a.p + "remarks.db";
        com.qq.reader.common.c.a.bs = com.qq.reader.common.c.a.p + "question.db";
        com.qq.reader.common.c.a.bt = com.qq.reader.common.c.a.l + "rookiegift.db";
        com.qq.reader.common.c.a.bu = com.qq.reader.common.c.a.p + "gamecoupon.db";
        com.qq.reader.common.c.a.aV = com.qq.reader.common.c.a.p + "moreinfo.db";
        com.qq.reader.common.c.a.bI = com.qq.reader.common.c.a.p + "cloudversion.db";
        com.qq.reader.common.c.a.bJ = com.qq.reader.common.c.a.p + "cloudversion_old.db";
        String str = ReaderApplication.getApplicationImp().getExternalCacheDir() + "/" + com.qq.reader.common.c.a.t;
        com.qq.reader.common.c.a.ae = str + "nativedata";
        com.qq.reader.common.c.a.ah = str + "feeddata";
        com.qq.reader.common.c.a.ai = str + "newfeeddata";
        com.qq.reader.common.c.a.aj = str + "failedtaskdata";
        com.qq.reader.common.c.a.ag = com.qq.reader.common.c.a.t + "selectedtag";
        com.qq.reader.common.c.a.aN = com.qq.reader.common.c.a.p + "Online/audioDownloadInfo.db";
        com.qq.reader.common.c.a.am = str + "audio/";
        com.qq.reader.common.c.a.al = str + "tempaudio/";
        com.qq.reader.common.c.a.aT = com.qq.reader.common.c.a.p + "booklimitfree.db";
        d.d();
    }

    public void b() {
        com.qq.reader.common.c.a.k = d.R(this.c);
        if (com.qq.reader.common.c.a.k == null) {
            com.qq.reader.common.c.a.k = "";
        }
        c.a("cloud", "USE UIN : " + com.qq.reader.common.c.a.k);
        String str = "";
        if (com.qq.reader.common.c.a.k != null && com.qq.reader.common.c.a.k.length() > 0) {
            str = com.qq.reader.common.c.a.k + "/";
        }
        com.qq.reader.common.c.a.p = com.qq.reader.common.c.a.l + str;
        com.qq.reader.common.c.a.r = com.qq.reader.common.c.a.n + str;
        com.qq.reader.common.c.a.s = str;
        com.qq.reader.common.c.a.t = str;
        com.qq.reader.common.c.a.q = com.qq.reader.common.c.a.p + "skin/";
        Object obj = null;
        if (new File(com.qq.reader.common.c.a.p).exists()) {
            obj = 1;
        }
        e();
        if (obj == null) {
            ao.m();
        }
        if (d.ck(ReaderApplication.getApplicationImp())) {
            if (!new File(b.b).exists()) {
                ao.e(b.a, b.b);
            }
            if (!new File(com.qq.reader.common.c.a.be).exists()) {
                ao.e(com.qq.reader.common.c.a.bx, com.qq.reader.common.c.a.be);
            }
        }
        if (!TextUtils.isEmpty(com.qq.reader.common.c.a.k)) {
            if (!new File(com.qq.reader.common.c.a.p + "plugin.db").exists()) {
                File file = new File(com.qq.reader.common.c.a.l + "plugin.db");
                File file2 = new File(com.qq.reader.common.c.a.p + "plugin.db");
                if (!file2.getParentFile().exists()) {
                    file2.mkdirs();
                }
                ao.a(file, file2);
            }
            com.qq.reader.common.c.a.cR = com.qq.reader.common.c.a.p + "PlugIn/TxtStyle/";
            com.qq.reader.common.c.a.ap = com.qq.reader.common.c.a.l + "plugin.db";
        }
        f();
        c.a("ACCOUNT", "BOOKS_ONLINE_HISTORY_PATH_NEW ==" + com.qq.reader.common.c.a.bn);
        c.a("ACCOUNT", "AUTO_BOOKMARK ==" + com.qq.reader.common.c.a.be);
        c.a("ACCOUNT", "UNLOGIN_AUTO_BOOKMARK_ROOT ==" + com.qq.reader.common.c.a.bx);
    }

    private synchronized void f() {
        if (!(com.qq.reader.common.c.a.k == null || com.qq.reader.common.c.a.k.equals(b))) {
            int size = this.d.size();
            for (int i = 0; i < size; i++) {
                c cVar = (c) this.d.get(i);
                if (cVar != null) {
                    cVar.a();
                }
            }
            this.d.clear();
            if (!TextUtils.isEmpty(b)) {
                w.b().c(ReaderApplication.getApplicationImp());
            }
            b = com.qq.reader.common.c.a.k;
        }
    }

    public synchronized void a(c cVar) {
        this.d.add(cVar);
    }

    public static void a(String str) {
        RandomAccessFile randomAccessFile;
        UnsupportedEncodingException e;
        Throwable th;
        FileNotFoundException e2;
        Exception e3;
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile = new RandomAccessFile(ao.c(com.qq.reader.common.c.a.Z), "rw");
            try {
                randomAccessFile.writeUTF(str);
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e4) {
                    }
                }
            } catch (UnsupportedEncodingException e5) {
                e = e5;
                try {
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e6) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile2 = randomAccessFile;
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e7) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e8) {
                e2 = e8;
                randomAccessFile2 = randomAccessFile;
                try {
                    e2.printStackTrace();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e9) {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    throw th;
                }
            } catch (Exception e10) {
                e3 = e10;
                randomAccessFile2 = randomAccessFile;
                e3.printStackTrace();
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e11) {
                    }
                }
            }
        } catch (UnsupportedEncodingException e12) {
            e = e12;
            randomAccessFile = null;
            e.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (FileNotFoundException e13) {
            e2 = e13;
            e2.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
        } catch (Exception e14) {
            e3 = e14;
            e3.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
        }
    }

    public static String c() {
        RandomAccessFile randomAccessFile;
        UnsupportedEncodingException e;
        Throwable th;
        FileNotFoundException e2;
        Exception e3;
        File b = ao.b(com.qq.reader.common.c.a.Z);
        if (b == null) {
            return "";
        }
        String str = "";
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile = new RandomAccessFile(b, "rw");
            try {
                str = randomAccessFile.readUTF();
                if (randomAccessFile == null) {
                    return str;
                }
                try {
                    randomAccessFile.close();
                    return str;
                } catch (IOException e4) {
                    return str;
                }
            } catch (UnsupportedEncodingException e5) {
                e = e5;
                try {
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        return str;
                    }
                    try {
                        randomAccessFile.close();
                        return str;
                    } catch (IOException e6) {
                        return str;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile2 = randomAccessFile;
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e7) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e8) {
                e2 = e8;
                randomAccessFile2 = randomAccessFile;
                try {
                    e2.printStackTrace();
                    if (randomAccessFile2 != null) {
                        return str;
                    }
                    try {
                        randomAccessFile2.close();
                        return str;
                    } catch (IOException e9) {
                        return str;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    throw th;
                }
            } catch (Exception e10) {
                e3 = e10;
                randomAccessFile2 = randomAccessFile;
                e3.printStackTrace();
                if (randomAccessFile2 != null) {
                    return str;
                }
                try {
                    randomAccessFile2.close();
                    return str;
                } catch (IOException e11) {
                    return str;
                }
            }
        } catch (UnsupportedEncodingException e12) {
            e = e12;
            randomAccessFile = null;
            e.printStackTrace();
            if (randomAccessFile != null) {
                return str;
            }
            randomAccessFile.close();
            return str;
        } catch (FileNotFoundException e13) {
            e2 = e13;
            e2.printStackTrace();
            if (randomAccessFile2 != null) {
                return str;
            }
            randomAccessFile2.close();
            return str;
        } catch (Exception e14) {
            e3 = e14;
            e3.printStackTrace();
            if (randomAccessFile2 != null) {
                return str;
            }
            randomAccessFile2.close();
            return str;
        }
    }

    public void d() {
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            c cVar = (c) this.d.get(i);
            if (cVar != null) {
                cVar.a();
            }
        }
        this.d.clear();
    }
}
