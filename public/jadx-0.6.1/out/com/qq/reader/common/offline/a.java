package com.qq.reader.common.offline;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.appconfig.a.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.d;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipException;

/* compiled from: OfflineAssetManager */
public class a {
    private static volatile a b;
    Handler a = new Handler(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            this.a.a(message);
        }
    };
    private Context c;
    private long d = 0;
    private long e = 0;
    private Map<Long, OfflineDownloadTask> f = new HashMap(1);
    private int g = 0;
    private int h = 0;
    private long i;
    private String j;
    private Map<Long, OfflineCheckVersionTask> k = new HashMap(1);
    private String l;
    private int m = 0;
    private a n;
    private Object o = new Object();

    /* compiled from: OfflineAssetManager */
    public interface a {
    }

    public a(Context context) {
        this.c = context;
    }

    public static a a(Context context) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    public void a(a aVar) {
        this.n = aVar;
    }

    public void a() {
        this.n = null;
    }

    public int b() {
        return this.m;
    }

    @Deprecated
    public void c() {
        if (!d()) {
            c.c(this.c, -1);
            h();
        } else if (com.qq.reader.common.c.a.N) {
            f.a("update", c.g(this.c));
            c.c(this.c, -1);
            h();
            com.qq.reader.common.c.a.N = false;
        } else if (c.o(this.c) + 1800000 < System.currentTimeMillis()) {
            this.a.sendEmptyMessage(90001);
        }
    }

    private boolean d() {
        Iterator it = e().iterator();
        while (it.hasNext()) {
            if (!e.a((String) it.next(), "88")) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<String> e() {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(com.qq.reader.common.c.a.cY);
        return arrayList;
    }

    private boolean a(long j) {
        if (j > c.n(this.c)) {
            return false;
        }
        return true;
    }

    private synchronized void f() {
        if (this.f.isEmpty()) {
            ReaderTask offlineDownloadTask = new OfflineDownloadTask(new d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(ReaderProtocolTask readerProtocolTask, InputStream inputStream, long j) {
                    Object obj;
                    Object obj2 = null;
                    this.a.m = 3;
                    boolean a = this.a.a(inputStream);
                    if (a) {
                        synchronized (this.a.o) {
                            try {
                                this.a.m = 1;
                                ao.f(com.qq.reader.common.c.a.cV, com.qq.reader.common.c.a.cZ + "/");
                                File file = new File(com.qq.reader.common.c.a.cY);
                                File file2 = new File(com.qq.reader.common.c.a.cZ);
                                if (!(file == null || file.exists()) || file.renameTo(new File(com.qq.reader.common.c.a.da))) {
                                    file2.renameTo(new File(com.qq.reader.common.c.a.cY));
                                    ao.c(new File(com.qq.reader.common.c.a.da));
                                }
                                if (this.a.d()) {
                                    this.a.m = 2;
                                    obj2 = 1;
                                } else {
                                    ao.c(new File(com.qq.reader.common.c.a.cY));
                                    c.c(this.a.c, -1);
                                }
                                this.a.f.clear();
                                obj = obj2;
                            } catch (ZipException e) {
                                e.printStackTrace();
                                this.a.f.clear();
                                obj = null;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                this.a.f.clear();
                                obj = null;
                            } catch (Throwable th) {
                                this.a.f.clear();
                            }
                            if (obj != null) {
                                File file3 = new File(com.qq.reader.common.c.a.cV);
                                if (file3.exists()) {
                                    file3.delete();
                                }
                                c.c(this.a.c, this.a.i);
                                c.d(this.a.c, System.currentTimeMillis());
                                com.qq.reader.appconfig.a.d.x(this.a.c, true);
                                this.a.m = 2;
                            }
                        }
                    } else {
                        obj = null;
                    }
                    if (obj == null || !a) {
                        this.a.f.clear();
                        if (this.a.g < 2) {
                            this.a.a.sendEmptyMessageDelayed(90002, 3000);
                            this.a.g = this.a.g + 1;
                        }
                    }
                }

                public void a(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.a.f.clear();
                    if (this.a.g < 2) {
                        this.a.a.sendEmptyMessageDelayed(90002, 3000);
                        this.a.g = this.a.g + 1;
                    }
                }
            });
            offlineDownloadTask.setTid(this.d);
            if (!(this.j == null || this.j.equals(""))) {
                offlineDownloadTask.setUrl(this.j);
            }
            offlineDownloadTask.setPriority(1);
            this.f.put(Long.valueOf(this.d), offlineDownloadTask);
            this.d++;
            g.a().a(offlineDownloadTask);
        }
    }

    private synchronized void g() {
        if (this.k.isEmpty()) {
            ReaderTask offlineCheckVersionTask = new OfflineCheckVersionTask(new d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(ReaderProtocolTask readerProtocolTask, InputStream inputStream, long j) {
                    this.a.a(inputStream, 10);
                    this.a.k.clear();
                }

                public void a(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.a.k.clear();
                    if (this.a.h < 2) {
                        this.a.a.sendEmptyMessageDelayed(90001, 3000);
                        this.a.h = this.a.h + 1;
                    }
                }
            });
            offlineCheckVersionTask.setTid(this.e);
            offlineCheckVersionTask.setPriority(1);
            this.k.put(Long.valueOf(this.e), offlineCheckVersionTask);
            this.e++;
            g.a().a(offlineCheckVersionTask);
        }
    }

    private boolean a(InputStream inputStream) {
        IOException e;
        FileOutputStream fileOutputStream;
        FileNotFoundException e2;
        Throwable th;
        String str = com.qq.reader.common.c.a.cV;
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            if (ab.a(file.getParentFile())) {
                file.createNewFile();
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            fileOutputStream = new FileOutputStream(str);
            try {
                byte[] bArr = new byte[4096];
                int i = 0;
                while (i != -1) {
                    i = inputStream.read(bArr, 0, bArr.length);
                    if (i != -1) {
                        fileOutputStream.write(bArr, 0, i);
                    }
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return true;
            } catch (FileNotFoundException e5) {
                e2 = e5;
                try {
                    e2.printStackTrace();
                    file.delete();
                    if (fileOutputStream != null) {
                        return false;
                    }
                    try {
                        fileOutputStream.close();
                        return false;
                    } catch (IOException e32) {
                        e32.printStackTrace();
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e322 = e6;
                e322.printStackTrace();
                file.delete();
                if (fileOutputStream != null) {
                    return false;
                }
                try {
                    fileOutputStream.close();
                    return false;
                } catch (IOException e3222) {
                    e3222.printStackTrace();
                    return false;
                }
            }
        } catch (FileNotFoundException e7) {
            e2 = e7;
            fileOutputStream = null;
            e2.printStackTrace();
            file.delete();
            if (fileOutputStream != null) {
                return false;
            }
            fileOutputStream.close();
            return false;
        } catch (IOException e8) {
            e3222 = e8;
            fileOutputStream = null;
            e3222.printStackTrace();
            file.delete();
            if (fileOutputStream != null) {
                return false;
            }
            fileOutputStream.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private void b(InputStream inputStream) {
        IOException iOException;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        long j = -1;
        String str = null;
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String substring = readLine.substring(0, readLine.indexOf(":"));
                readLine = readLine.substring(readLine.indexOf(":") + 1, readLine.length());
                if (substring.equalsIgnoreCase(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION)) {
                    j = Long.valueOf(readLine).longValue();
                }
                if (substring.equalsIgnoreCase("zipfile")) {
                    try {
                        this.l = readLine;
                    } catch (IOException e) {
                        IOException iOException2 = e;
                        str = readLine;
                        iOException = iOException2;
                    }
                } else {
                    readLine = str;
                }
                str = readLine;
            } catch (IOException e2) {
                iOException = e2;
            }
        }
        if (!a(j) && str != null) {
            this.i = j;
            this.j = str + "update" + j + ".zip";
            this.a.sendEmptyMessage(90002);
            return;
        }
        iOException.printStackTrace();
        if (!a(j)) {
        }
    }

    private void c(InputStream inputStream) {
    }

    private void a(InputStream inputStream, int i) {
        switch (i) {
            case 10:
                b(inputStream);
                return;
            case 11:
                c(inputStream);
                return;
            default:
                return;
        }
    }

    private void h() {
        if (!this.a.hasMessages(90001)) {
            this.a.sendEmptyMessage(90001);
        }
    }

    private void a(Message message) {
        switch (message.what) {
            case 90001:
                g();
                return;
            case 90002:
                f();
                return;
            case 90006:
                h();
                return;
            default:
                return;
        }
    }
}
