package com.qq.reader.module.question.loader;

import android.content.Context;
import android.os.Handler;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderNetTask;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.question.data.AudioData;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;

public class RecordDownloadTask extends ReaderNetTask {
    public static final String TASKNAME = "ReaderDownloadTask";
    private int downloadTime = 0;
    private String downloadfilePath;
    private Context mContext;
    private String mRecodeId;
    private a mStrongRefListener;
    private String[] urlGroup = null;

    public static class a implements com.qq.reader.common.readertask.ordinal.a {
        private b a;
        private String b;
        private AudioData c;
        private WeakReference<Handler> d;

        public a(b bVar, String str, AudioData audioData, Handler handler) {
            if (bVar == null || audioData == null || str == null) {
                throw new RuntimeException("RecordDataCache&&vAnswerId&&AudioData不能为空");
            }
            this.a = bVar;
            this.b = str;
            this.c = audioData;
            this.d = new WeakReference(handler);
        }

        public void a() {
        }

        public void a(boolean z) {
            Handler handler = (Handler) this.d.get();
            if (handler != null) {
                if (z) {
                    File a = this.a.a(this.b);
                    if (a == null || !a.exists()) {
                        c.a(handler, this.c, 11000005);
                        return;
                    }
                    this.c.b().a(a);
                    c.a(handler, false, this.c);
                    return;
                }
                this.c.b().a(null);
                c.a(handler, this.c, 11000005);
            }
        }
    }

    public RecordDownloadTask(Context context, String str, String[] strArr) {
        setPriority(3);
        this.mRecodeId = str;
        this.mContext = context;
        this.downloadfilePath = f.b().a(str);
        this.urlGroup = strArr;
        this.mUrl = this.urlGroup[0];
        this.downloadTime = 0;
    }

    public void setListener(a aVar) {
        this.mStrongRefListener = aVar;
    }

    public void run() {
        File file;
        Exception e;
        InputStream inputStream;
        Throwable th;
        File file2;
        Object obj;
        Object obj2;
        Object obj3;
        File file3 = null;
        InputStream inputStream2 = null;
        OutputStream outputStream = null;
        InputStream inputStream3;
        OutputStream bufferedOutputStream;
        try {
            if (this.downloadfilePath == null || this.downloadfilePath.length() == 0 || this.mUrl == null || this.mUrl.length() == 0 || !this.mUrl.toLowerCase().startsWith("http://")) {
                if (file3 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (file3 != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                if (file3 != null && file3.exists()) {
                    file3.delete();
                }
                if (file3 != null && file3.exists()) {
                    file3.delete();
                }
                if (ao.e(this.mContext)) {
                    this.downloadTime++;
                    if (this.downloadTime < this.urlGroup.length) {
                        this.mUrl = this.urlGroup[this.downloadTime];
                        run();
                        return;
                    }
                }
                if (this.mStrongRefListener != null) {
                    this.mStrongRefListener.a(false);
                    return;
                }
                return;
            }
            file = new File(this.downloadfilePath);
            try {
                if (!file.exists() || file.length() <= 0) {
                    File file4 = new File(this.downloadfilePath + ".temp");
                    try {
                        if (file4.exists()) {
                            file4.delete();
                        }
                        HttpURLConnection a = b.a(this.mUrl, null, this.mContext);
                        if (a == null) {
                            throw new IOException("Connection cannot be established to : " + this.mUrl);
                        }
                        byte[] bArr = new byte[20480];
                        inputStream3 = a.getInputStream();
                        try {
                            ab.c(file4);
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file4));
                            while (inputStream3 != null) {
                                try {
                                    int read = inputStream3.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    bufferedOutputStream.write(bArr, 0, read);
                                } catch (Exception e3) {
                                    e = e3;
                                    file3 = file4;
                                    outputStream = bufferedOutputStream;
                                    inputStream = inputStream3;
                                } catch (Throwable th2) {
                                    th = th2;
                                    file3 = file4;
                                }
                            }
                            bufferedOutputStream.flush();
                            file4.renameTo(file);
                            if (!file.exists() || file.length() == 0) {
                                if (inputStream3 != null) {
                                    try {
                                        inputStream3.close();
                                    } catch (IOException e222) {
                                        e222.printStackTrace();
                                    }
                                }
                                if (bufferedOutputStream != null) {
                                    try {
                                        bufferedOutputStream.close();
                                    } catch (IOException e2222) {
                                        e2222.printStackTrace();
                                    }
                                }
                                if (file4 != null && file4.exists()) {
                                    file4.delete();
                                }
                                if (file != null && file.exists()) {
                                    file.delete();
                                }
                                if (ao.e(this.mContext)) {
                                    this.downloadTime++;
                                    if (this.downloadTime < this.urlGroup.length) {
                                        this.mUrl = this.urlGroup[this.downloadTime];
                                        run();
                                        return;
                                    }
                                }
                                if (this.mStrongRefListener != null) {
                                    this.mStrongRefListener.a(false);
                                    return;
                                }
                                return;
                            }
                            f.b().a(this.mRecodeId, new FileInputStream(file), null);
                            if (inputStream3 != null) {
                                try {
                                    inputStream3.close();
                                } catch (IOException e22222) {
                                    e22222.printStackTrace();
                                }
                            }
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (IOException e222222) {
                                    e222222.printStackTrace();
                                }
                            }
                            if (file4 != null && file4.exists()) {
                                file4.delete();
                            }
                            if (file != null && file.exists()) {
                                file.delete();
                            }
                            if (this.mStrongRefListener != null) {
                                this.mStrongRefListener.a(true);
                                return;
                            }
                            return;
                        } catch (Exception e4) {
                            e = e4;
                            inputStream = inputStream3;
                            file2 = file3;
                            file3 = file4;
                            obj = file2;
                            if (file != null) {
                                try {
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    inputStream3 = inputStream;
                                    bufferedOutputStream = outputStream;
                                    if (inputStream3 != null) {
                                        try {
                                            inputStream3.close();
                                        } catch (IOException e5) {
                                            e5.printStackTrace();
                                        }
                                    }
                                    if (bufferedOutputStream != null) {
                                        try {
                                            bufferedOutputStream.close();
                                        } catch (IOException e52) {
                                            e52.printStackTrace();
                                        }
                                    }
                                    file3.delete();
                                    file.delete();
                                    if (ao.e(this.mContext)) {
                                        this.downloadTime++;
                                        if (this.downloadTime < this.urlGroup.length) {
                                            this.mUrl = this.urlGroup[this.downloadTime];
                                            run();
                                            return;
                                        }
                                    }
                                    if (this.mStrongRefListener != null) {
                                        this.mStrongRefListener.a(false);
                                    }
                                    throw th;
                                }
                            }
                            f.a("net", "ReaderDownloadTask get Icon Failed" + e.toString());
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e2222222) {
                                    e2222222.printStackTrace();
                                }
                            }
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e22222222) {
                                    e22222222.printStackTrace();
                                }
                            }
                            file3.delete();
                            file.delete();
                            if (ao.e(this.mContext)) {
                                this.downloadTime++;
                                if (this.downloadTime < this.urlGroup.length) {
                                    this.mUrl = this.urlGroup[this.downloadTime];
                                    run();
                                    return;
                                }
                            }
                            if (this.mStrongRefListener == null) {
                                this.mStrongRefListener.a(false);
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            obj2 = file3;
                            file3 = file4;
                            if (inputStream3 != null) {
                                inputStream3.close();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            file3.delete();
                            file.delete();
                            if (ao.e(this.mContext)) {
                                this.downloadTime++;
                                if (this.downloadTime < this.urlGroup.length) {
                                    this.mUrl = this.urlGroup[this.downloadTime];
                                    run();
                                    return;
                                }
                            }
                            if (this.mStrongRefListener != null) {
                                this.mStrongRefListener.a(false);
                            }
                            throw th;
                        }
                    } catch (Exception e6) {
                        e = e6;
                        inputStream = file3;
                        file2 = file3;
                        file3 = file4;
                        outputStream = file2;
                        if (file != null) {
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                        f.a("net", "ReaderDownloadTask get Icon Failed" + e.toString());
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        file3.delete();
                        file.delete();
                        if (ao.e(this.mContext)) {
                            this.downloadTime++;
                            if (this.downloadTime < this.urlGroup.length) {
                                this.mUrl = this.urlGroup[this.downloadTime];
                                run();
                                return;
                            }
                        }
                        if (this.mStrongRefListener == null) {
                            this.mStrongRefListener.a(false);
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        obj2 = file3;
                        obj3 = file3;
                        file3 = file4;
                        if (inputStream3 != null) {
                            inputStream3.close();
                        }
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        file3.delete();
                        file.delete();
                        if (ao.e(this.mContext)) {
                            this.downloadTime++;
                            if (this.downloadTime < this.urlGroup.length) {
                                this.mUrl = this.urlGroup[this.downloadTime];
                                run();
                                return;
                            }
                        }
                        if (this.mStrongRefListener != null) {
                            this.mStrongRefListener.a(false);
                        }
                        throw th;
                    }
                }
                if (file3 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e222222222) {
                        e222222222.printStackTrace();
                    }
                }
                if (file3 != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e2222222222) {
                        e2222222222.printStackTrace();
                    }
                }
                if (file3 != null && file3.exists()) {
                    file3.delete();
                }
                if (file != null && file.exists()) {
                    file.delete();
                }
                if (this.mStrongRefListener != null) {
                    this.mStrongRefListener.a(true);
                }
            } catch (Exception e7) {
                e = e7;
                obj = file3;
                obj2 = file3;
                if (file != null) {
                    if (file.exists()) {
                        file.delete();
                    }
                }
                f.a("net", "ReaderDownloadTask get Icon Failed" + e.toString());
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (file3 != null && file3.exists()) {
                    file3.delete();
                }
                if (file != null && file.exists()) {
                    file.delete();
                }
                if (ao.e(this.mContext)) {
                    this.downloadTime++;
                    if (this.downloadTime < this.urlGroup.length) {
                        this.mUrl = this.urlGroup[this.downloadTime];
                        run();
                        return;
                    }
                }
                if (this.mStrongRefListener == null) {
                    this.mStrongRefListener.a(false);
                }
            } catch (Throwable th6) {
                th = th6;
                obj2 = file3;
                obj3 = file3;
                if (inputStream3 != null) {
                    inputStream3.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (file3 != null && file3.exists()) {
                    file3.delete();
                }
                if (file != null && file.exists()) {
                    file.delete();
                }
                if (ao.e(this.mContext)) {
                    this.downloadTime++;
                    if (this.downloadTime < this.urlGroup.length) {
                        this.mUrl = this.urlGroup[this.downloadTime];
                        run();
                        return;
                    }
                }
                if (this.mStrongRefListener != null) {
                    this.mStrongRefListener.a(false);
                }
                throw th;
            }
        } catch (Exception e8) {
            e = e8;
            file = file3;
            obj = file3;
            obj2 = file3;
            if (file != null) {
                if (file.exists()) {
                    file.delete();
                }
            }
            f.a("net", "ReaderDownloadTask get Icon Failed" + e.toString());
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            file3.delete();
            file.delete();
            if (ao.e(this.mContext)) {
                this.downloadTime++;
                if (this.downloadTime < this.urlGroup.length) {
                    this.mUrl = this.urlGroup[this.downloadTime];
                    run();
                    return;
                }
            }
            if (this.mStrongRefListener == null) {
                this.mStrongRefListener.a(false);
            }
        } catch (Throwable th7) {
            th = th7;
            file = file3;
            bufferedOutputStream = file3;
            inputStream3 = file3;
            if (inputStream3 != null) {
                inputStream3.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            file3.delete();
            file.delete();
            if (ao.e(this.mContext)) {
                this.downloadTime++;
                if (this.downloadTime < this.urlGroup.length) {
                    this.mUrl = this.urlGroup[this.downloadTime];
                    run();
                    return;
                }
            }
            if (this.mStrongRefListener != null) {
                this.mStrongRefListener.a(false);
            }
            throw th;
        }
    }
}
