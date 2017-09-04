package com.qq.reader.common.utils.networkUtil;

import android.os.Message;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.imsdk.BaseConstants;
import com.tencent.util.WeakReferenceHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import rx.b.b;
import rx.e;
import rx.e.d;

/* compiled from: CheckNetWork */
public class a {
    private static a a;
    private final String b = "CheckNetWork";
    private final String[] c = new String[]{"www.qq.com", "android.reader.qq.com", "dwn.3g.qq.com", "wfqqreader.3g.qq.com"};
    private final String[] d = new String[]{"http://android.reader.qq.com", "http://dwn.3g.qq.com"};
    private final String e = (com.qq.reader.common.c.a.l + "checknetwork/checkdownload.q");
    private final String f = "http://dwn.3g.qq.com/ChapBatAuthWithPD?bookId=357735&scids=0&type=0&text_type=0&useindex=1&tafauth=1";
    private int g = 0;
    private int h = 0;
    private WeakReferenceHandler i;

    private a() {
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    public void a(WeakReferenceHandler weakReferenceHandler) {
        this.i = weakReferenceHandler;
        c();
    }

    public void b() {
        this.i = null;
    }

    private void c() {
        f.d("CheckNetWork", "==========网络检测开始==========");
        rx.a.a(new rx.a.a<String>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((e) obj);
            }

            public void a(e<? super String> eVar) {
                eVar.onNext(c.a());
                eVar.onNext(b.a());
                eVar.onNext(e.b());
                eVar.onCompleted();
                this.a.d();
            }
        }).b(d.b()).a(rx.a.b.a.a()).a(new b<String>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                this.a.b(str);
            }
        });
    }

    private void d() {
        if (this.i != null) {
            new g(null, new d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(String str) {
                }

                public void a(int i, String str) {
                    this.a.b("公网IP ：" + str);
                    this.a.e();
                }
            }).a();
        }
    }

    private void e() {
        if (this.i != null) {
            if (this.g >= this.c.length) {
                this.g = 0;
                f();
                return;
            }
            final String str = this.c[this.g];
            new f(str, new d(this) {
                final /* synthetic */ a b;

                public void a(String str) {
                    f.d("CheckNetWork", str);
                }

                public void a(int i, String str) {
                    this.b.b(str);
                    f.d("CheckNetWork", str);
                    this.b.a(str);
                }
            }).a();
        }
    }

    private void a(String str) {
        if (this.i != null) {
            new h(str, new d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(String str) {
                    String str2 = "目标IP：" + str;
                    this.a.b(str2);
                    f.d("CheckNetWork", str2);
                }

                public void a(int i, String str) {
                    this.a.b(str);
                    f.d("CheckNetWork", str);
                    this.a.g = this.a.g + 1;
                    this.a.e();
                }
            }).a();
        }
    }

    private void f() {
        if (this.i != null) {
            if (this.h >= this.d.length) {
                this.h = 0;
                g();
                return;
            }
            final String str = this.d[this.h];
            g.a().a(new CheckServerContctionTask(str, new c(this) {
                final /* synthetic */ a b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    f.d("CheckNetWork", "checkServer : " + str);
                    f.d("CheckNetWork", str);
                    this.b.h = this.b.h + 1;
                    this.b.f();
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    f.d("CheckNetWork", "checkServer : " + str);
                    f.d("CheckNetWork", exception.toString());
                    this.b.h = this.b.h + 1;
                    this.b.f();
                }
            }));
        }
    }

    private void g() {
        if (this.i != null) {
            ReaderTask readerDownloadTask = new ReaderDownloadTask(ReaderApplication.getApplicationImp(), this.e, "http://dwn.3g.qq.com/ChapBatAuthWithPD?bookId=357735&scids=0&type=0&text_type=0&useindex=1&tafauth=1");
            final long[] jArr = new long[]{0};
            readerDownloadTask.setListener(new com.qq.reader.common.readertask.ordinal.a(this) {
                final /* synthetic */ a b;

                public void a() {
                    jArr[0] = System.currentTimeMillis();
                    f.d("CheckNetWork", "checkDownload : http://dwn.3g.qq.com/ChapBatAuthWithPD?bookId=357735&scids=0&type=0&text_type=0&useindex=1&tafauth=1");
                }

                public void a(boolean z) {
                    Exception e;
                    Message obtain;
                    Throwable th;
                    FileInputStream fileInputStream = null;
                    f.d("CheckNetWork", "duration = " + (System.currentTimeMillis() - jArr[0]) + "ms");
                    String str = "0B";
                    if (z) {
                        File file = new File(this.b.e);
                        long j = 0;
                        FileInputStream fileInputStream2;
                        try {
                            if (file.exists()) {
                                fileInputStream2 = new FileInputStream(file);
                                try {
                                    j = (long) fileInputStream2.available();
                                } catch (Exception e2) {
                                    e = e2;
                                    try {
                                        e.printStackTrace();
                                        if (fileInputStream2 != null) {
                                            try {
                                                fileInputStream2.close();
                                            } catch (IOException e3) {
                                                e3.printStackTrace();
                                            }
                                        }
                                        if (file.exists()) {
                                            file.delete();
                                        }
                                        f.d("CheckNetWork", "fileSeize = " + this.b.a(j));
                                        f.d("CheckNetWork", z ? "ok" : "error");
                                        f.d("CheckNetWork", "==========网络检测结束==========");
                                        if (this.b.i != null) {
                                            obtain = Message.obtain(this.b.i);
                                            obtain.what = -10001;
                                            this.b.i.sendMessage(obtain);
                                        }
                                        this.b.i = null;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        fileInputStream = fileInputStream2;
                                        if (fileInputStream != null) {
                                            try {
                                                fileInputStream.close();
                                            } catch (IOException e4) {
                                                e4.printStackTrace();
                                            }
                                        }
                                        if (file.exists()) {
                                            file.delete();
                                        }
                                        throw th;
                                    }
                                }
                            }
                            fileInputStream2 = null;
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                            if (file.exists()) {
                                file.delete();
                            }
                        } catch (Exception e5) {
                            e = e5;
                            fileInputStream2 = null;
                            e.printStackTrace();
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            if (file.exists()) {
                                file.delete();
                            }
                            f.d("CheckNetWork", "fileSeize = " + this.b.a(j));
                            if (z) {
                            }
                            f.d("CheckNetWork", z ? "ok" : "error");
                            f.d("CheckNetWork", "==========网络检测结束==========");
                            if (this.b.i != null) {
                                obtain = Message.obtain(this.b.i);
                                obtain.what = -10001;
                                this.b.i.sendMessage(obtain);
                            }
                            this.b.i = null;
                        } catch (Throwable th3) {
                            th = th3;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (file.exists()) {
                                file.delete();
                            }
                            throw th;
                        }
                        f.d("CheckNetWork", "fileSeize = " + this.b.a(j));
                    }
                    if (z) {
                    }
                    f.d("CheckNetWork", z ? "ok" : "error");
                    f.d("CheckNetWork", "==========网络检测结束==========");
                    if (this.b.i != null) {
                        obtain = Message.obtain(this.b.i);
                        obtain.what = -10001;
                        this.b.i.sendMessage(obtain);
                    }
                    this.b.i = null;
                }
            });
            g.a().a(readerDownloadTask);
        }
    }

    private String a(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String str = "";
        str = "0B";
        if (j == 0) {
            return str;
        }
        if (j < 1024) {
            return decimalFormat.format((double) j) + "B";
        }
        if (j < BaseConstants.MEGA) {
            return decimalFormat.format(((double) j) / 1024.0d) + "KB";
        }
        if (j < 1073741824) {
            return decimalFormat.format(((double) j) / 1048576.0d) + "MB";
        }
        return decimalFormat.format(((double) j) / 1.073741824E9d) + "GB";
    }

    private void b(String str) {
        if (this.i != null) {
            Message obtain = Message.obtain(this.i);
            obtain.what = -10000;
            obtain.obj = str;
            this.i.sendMessage(obtain);
        }
    }
}
