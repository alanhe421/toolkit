package com.qq.reader.cservice.download.app;

import android.content.Context;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.readertask.ordinal.ReaderNetTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.r;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import qalsdk.n;

public class ReaderDownloadAppTask extends ReaderNetTask {
    protected static final int BLOCK = 20480;
    private static final int CONNECTION_TIMEOUT = 30000;
    private static final int READ_TIMEOUT = 30000;
    public static final String TASKNAME = "ReaderDownloadAppTask";
    public static boolean isDownloadSuccess = false;
    public static boolean isDownloading = false;
    private static final long serialVersionUID = 20150625;
    private long currentSize = 0;
    private String downloadfilePath;
    private boolean hasRelease = false;
    private Context mContext;
    private a mListenerRef;
    private int progress = 0;
    private RandomAccessFile randomAccessFile;
    private long totalSize = 0;

    public ReaderDownloadAppTask(Context context, String str, String str2) {
        setPriority(2);
        this.mContext = context;
        this.downloadfilePath = str;
        this.mUrl = str2;
        setFailedType(1);
    }

    public void setListener(a aVar) {
        this.mListenerRef = aVar;
    }

    private void setProgress(int i) {
        this.progress = i;
    }

    public String getDownloadfilePath() {
        return this.downloadfilePath;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r15 = this;
        r0 = 0;
        r1 = 1;
        r2 = 0;
        r14 = 100;
        r3 = r15.downloadfilePath;
        if (r3 == 0) goto L_0x003b;
    L_0x0009:
        r3 = r15.downloadfilePath;
        r3 = r3.length();
        if (r3 == 0) goto L_0x003b;
    L_0x0011:
        r3 = r15.mUrl;
        if (r3 == 0) goto L_0x003b;
    L_0x0015:
        r3 = r15.mUrl;
        r3 = r3.length();
        if (r3 == 0) goto L_0x003b;
    L_0x001d:
        r3 = r15.mUrl;
        r3 = r3.toLowerCase();
        r4 = "http://";
        r3 = r3.startsWith(r4);
        if (r3 != 0) goto L_0x003c;
    L_0x002c:
        r3 = r15.mUrl;
        r3 = r3.toLowerCase();
        r4 = "https://";
        r3 = r3.startsWith(r4);
        if (r3 != 0) goto L_0x003c;
    L_0x003b:
        return;
    L_0x003c:
        r3 = 0;
        r4 = 1;
        isDownloading = r4;	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r4 = r15.prepareRandomAccessFile();	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r15.randomAccessFile = r4;	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r4 = r15.getSize();	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r6 = 0;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 <= 0) goto L_0x007d;
    L_0x0050:
        r4 = r15.currentSize;	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r6 = r15.getSize();	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 < 0) goto L_0x007d;
    L_0x005a:
        r1 = 100;
        r15.setProgress(r1);	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r15.releaseResources(r3);	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r1 = 1;
        r3 = 0;
        r15.onDone(r1, r3);	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r15.releaseResources(r0);
        if (r0 == 0) goto L_0x006f;
    L_0x006c:
        r0.disconnect();
    L_0x006f:
        r1 = r15.mListenerRef;
        r1.a(r14);
        if (r0 != 0) goto L_0x0079;
    L_0x0076:
        r0 = "";
    L_0x0079:
        r15.onDone(r2, r0);
        goto L_0x003b;
    L_0x007d:
        r3 = new java.net.URL;	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r4 = r15.mUrl;	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r3.<init>(r4);	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r3 = r15.prepareConnection(r3);	 Catch:{ Exception -> 0x017f, all -> 0x015e }
        r4 = r3.getInputStream();	 Catch:{ Exception -> 0x0184, all -> 0x0177 }
        if (r4 != 0) goto L_0x00a4;
    L_0x008e:
        r15.releaseResources(r4);
        if (r3 == 0) goto L_0x0096;
    L_0x0093:
        r3.disconnect();
    L_0x0096:
        r1 = r15.mListenerRef;
        r1.a(r14);
        if (r0 != 0) goto L_0x00a0;
    L_0x009d:
        r0 = "";
    L_0x00a0:
        r15.onDone(r2, r0);
        goto L_0x003b;
    L_0x00a4:
        r5 = 20480; // 0x5000 float:2.8699E-41 double:1.01185E-319;
        r8 = new byte[r5];	 Catch:{ Exception -> 0x00ed }
        r6 = r2;
        r7 = r2;
        r5 = r2;
    L_0x00ab:
        r9 = r15.isDisconnected();	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        if (r9 != 0) goto L_0x00c4;
    L_0x00b1:
        if (r4 == 0) goto L_0x00c4;
    L_0x00b3:
        r9 = java.lang.Thread.currentThread();	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r9 = r9.isInterrupted();	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        if (r9 != 0) goto L_0x00c4;
    L_0x00bd:
        r7 = r4.read(r8);	 Catch:{ IOException -> 0x00e9 }
        r9 = -1;
        if (r7 != r9) goto L_0x0124;
    L_0x00c4:
        r6 = r15.isDisconnected();	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        if (r6 == 0) goto L_0x00ce;
    L_0x00ca:
        r6 = r15.progress;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        if (r6 >= r14) goto L_0x00ce;
    L_0x00ce:
        r5 = r15.progress;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        if (r5 != r14) goto L_0x015b;
    L_0x00d2:
        r15.releaseResources(r4);
        if (r3 == 0) goto L_0x00da;
    L_0x00d7:
        r3.disconnect();
    L_0x00da:
        r2 = r15.mListenerRef;
        r2.a(r14);
        if (r0 != 0) goto L_0x00e4;
    L_0x00e1:
        r0 = "";
    L_0x00e4:
        r15.onDone(r1, r0);
        goto L_0x003b;
    L_0x00e9:
        r9 = move-exception;
        if (r7 < r1) goto L_0x010c;
    L_0x00ec:
        throw r9;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
    L_0x00ed:
        r1 = move-exception;
    L_0x00ee:
        r15.markFailReason(r1);	 Catch:{ all -> 0x017a }
        r0 = r1.getMessage();	 Catch:{ all -> 0x017a }
        r15.releaseResources(r4);
        if (r3 == 0) goto L_0x00fd;
    L_0x00fa:
        r3.disconnect();
    L_0x00fd:
        r1 = r15.mListenerRef;
        r1.a(r14);
        if (r0 != 0) goto L_0x0107;
    L_0x0104:
        r0 = "";
    L_0x0107:
        r15.onDone(r2, r0);
        goto L_0x003b;
    L_0x010c:
        r7 = r7 + 1;
        r4.close();	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r3.disconnect();	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r9 = new java.net.URL;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r10 = r15.mUrl;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r9.<init>(r10);	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r3 = r15.prepareConnection(r9);	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r4 = r3.getInputStream();	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        goto L_0x00ab;
    L_0x0124:
        r9 = r15.randomAccessFile;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r10 = 0;
        r9.write(r8, r10, r7);	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r7 = r15.randomAccessFile;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r10 = r7.length();	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r15.currentSize = r10;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r12 = 100;
        r10 = r10 * r12;
        r12 = r15.totalSize;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r10 = r10 / r12;
        r7 = (int) r10;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r15.progress = r7;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r7 = r15.mListenerRef;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        if (r7 == 0) goto L_0x014c;
    L_0x013f:
        r7 = r15.progress;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        if (r6 >= r7) goto L_0x014c;
    L_0x0143:
        r6 = r15.progress;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r7 = r15.mListenerRef;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r9 = r15.progress;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        r7.a(r9);	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
    L_0x014c:
        r7 = r15.progress;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        if (r7 >= r14) goto L_0x0153;
    L_0x0150:
        r7 = r2;
        goto L_0x00ab;
    L_0x0153:
        r7 = r15.progress;	 Catch:{ Exception -> 0x00ed, all -> 0x017c }
        if (r7 != r14) goto L_0x0188;
    L_0x0157:
        r7 = r2;
        r5 = r1;
        goto L_0x00ab;
    L_0x015b:
        r1 = r2;
        goto L_0x00d2;
    L_0x015e:
        r1 = move-exception;
        r3 = r0;
        r4 = r0;
    L_0x0161:
        r15.releaseResources(r4);
        if (r3 == 0) goto L_0x0169;
    L_0x0166:
        r3.disconnect();
    L_0x0169:
        r3 = r15.mListenerRef;
        r3.a(r14);
        if (r0 != 0) goto L_0x0173;
    L_0x0170:
        r0 = "";
    L_0x0173:
        r15.onDone(r2, r0);
        throw r1;
    L_0x0177:
        r1 = move-exception;
        r4 = r0;
        goto L_0x0161;
    L_0x017a:
        r1 = move-exception;
        goto L_0x0161;
    L_0x017c:
        r1 = move-exception;
        r2 = r5;
        goto L_0x0161;
    L_0x017f:
        r1 = move-exception;
        r3 = r0;
        r4 = r0;
        goto L_0x00ee;
    L_0x0184:
        r1 = move-exception;
        r4 = r0;
        goto L_0x00ee;
    L_0x0188:
        r7 = r2;
        goto L_0x00ab;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.cservice.download.app.ReaderDownloadAppTask.run():void");
    }

    private void onDone(boolean z, String str) {
        isDownloading = false;
        if (z) {
            File file = new File(getTempFilePath());
            File file2 = new File(this.downloadfilePath);
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
            isDownloadSuccess = true;
        } else {
            isDownloadSuccess = false;
        }
        if (this.mListenerRef != null) {
            this.mListenerRef.a(z, str);
        }
    }

    private boolean isDisconnected() {
        return false;
    }

    private String getTempFilePath() {
        return this.downloadfilePath + ".temp";
    }

    private long getSize() {
        return this.totalSize;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.io.RandomAccessFile prepareRandomAccessFile() throws java.io.IOException {
        /*
        r4 = this;
        r0 = new java.io.File;
        r1 = r4.getTempFilePath();
        r0.<init>(r1);
        r1 = r0.getParentFile();
        if (r1 != 0) goto L_0x002b;
    L_0x000f:
        r0 = new java.io.IOException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "file's directory is invalid: ";
        r1 = r1.append(r2);
        r2 = r4.downloadfilePath;
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x002b:
        r1 = r0.getParentFile();
        r1 = com.qq.reader.common.utils.ab.a(r1);
        if (r1 != 0) goto L_0x0053;
    L_0x0035:
        r1 = new java.io.IOException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "cannot create directory:";
        r2 = r2.append(r3);
        r0 = r0.getParent();
        r0 = r2.append(r0);
        r0 = r0.toString();
        r1.<init>(r0);
        throw r1;
    L_0x0053:
        r1 = r0.getParentFile();
        r1 = r1.isDirectory();
        if (r1 != 0) goto L_0x0079;
    L_0x005d:
        r0 = new java.io.IOException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "file's directory is a file, not a directory: ";
        r1 = r1.append(r2);
        r2 = r4.downloadfilePath;
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0079:
        r1 = r0.exists();
        if (r1 != 0) goto L_0x0085;
    L_0x007f:
        r0 = r0.createNewFile();	 Catch:{ IOException -> 0x009d }
        if (r0 != 0) goto L_0x0085;
    L_0x0085:
        r0 = new java.io.RandomAccessFile;
        r1 = r4.getTempFilePath();
        r2 = "rw";
        r0.<init>(r1, r2);
        r2 = r0.length();
        r4.currentSize = r2;
        r2 = r4.currentSize;
        r0.seek(r2);
        return r0;
    L_0x009d:
        r1 = move-exception;
        r1 = new java.io.IOException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "cannot create file:";
        r2 = r2.append(r3);
        r0 = r0.getAbsolutePath();
        r0 = r2.append(r0);
        r0 = r0.toString();
        r1.<init>(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.cservice.download.app.ReaderDownloadAppTask.prepareRandomAccessFile():java.io.RandomAccessFile");
    }

    private HttpURLConnection prepareConnection(URL url) throws IOException {
        HttpURLConnection a = b.a(url, this.mContext);
        if (a == null) {
            throw new IOException("Connection cannot be established to : " + url.toString());
        }
        a.setConnectTimeout(n.f);
        a.setReadTimeout(n.f);
        if (this.currentSize > 0) {
            a.setRequestProperty("Range", "bytes=" + String.valueOf(this.currentSize) + "-");
        }
        int responseCode = a.getResponseCode();
        String contentType;
        switch (responseCode) {
            case 200:
            case 206:
                contentType = a.getContentType();
                if (contentType == null || (contentType.indexOf("text/vnd.wap.wml") == -1 && contentType.indexOf("application/vnd.wap.wmlc") == -1)) {
                    contentType = a.getHeaderField("Content-Range");
                    if (contentType != null) {
                        int indexOf = contentType.indexOf(47);
                        if (-1 != indexOf && indexOf < contentType.length() - 1) {
                            this.totalSize = (long) Integer.parseInt(contentType.substring(indexOf + 1));
                        }
                    } else {
                        contentType = a.getHeaderField("Content-Length");
                        if (contentType != null) {
                            this.totalSize = (long) Integer.parseInt(contentType);
                        }
                    }
                    if (this.currentSize <= 0 || a.getHeaderField("Content-Range") != null) {
                        return a;
                    }
                    handleUnrangeableDownload();
                    if (a != null) {
                        a.disconnect();
                    }
                    return prepareConnection(url);
                }
                if (a != null) {
                    a.disconnect();
                }
                return prepareConnection(url);
            case 301:
            case 302:
                contentType = a.getHeaderField("Location");
                if (a != null) {
                    a.disconnect();
                }
                if (contentType != null) {
                    return prepareConnection(new URL(contentType));
                }
                throw new IOException("HTTP 302 not return url  ");
            case ErrorCode.INFO_CAN_LOAD_TBS /*406*/:
                handleUnrangeableDownload();
                if (a != null) {
                    a.disconnect();
                }
                return prepareConnection(url);
            default:
                handleUnrangeableDownload();
                if (a != null) {
                    a.disconnect();
                }
                throw new IOException("HTTP Response Code: " + responseCode);
        }
    }

    private void handleUnrangeableDownload() throws IOException {
        if (this.randomAccessFile != null) {
            this.randomAccessFile.close();
        }
        if (ao.a(new File(getTempFilePath()))) {
            this.randomAccessFile = prepareRandomAccessFile();
            this.currentSize = 0;
            return;
        }
        throw new IOException("File cannot be deleted: " + getTempFilePath());
    }

    private void releaseResources(InputStream inputStream) {
        if (!this.hasRelease) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            try {
                if (this.randomAccessFile != null) {
                    this.randomAccessFile.close();
                    this.randomAccessFile = null;
                    this.hasRelease = true;
                }
            } catch (IOException e2) {
            } finally {
                this.randomAccessFile = null;
            }
        }
    }

    private void markFailReason(Exception exception) {
        if (!(exception instanceof MalformedURLException)) {
            if (exception instanceof IOException) {
                synchronized (this) {
                    try {
                        wait(1000);
                    } catch (InterruptedException e) {
                    }
                }
                if (!isDisconnected() && !(exception instanceof SocketTimeoutException) && !r.c(this.totalSize - this.currentSize)) {
                }
            } else if (!(exception instanceof FileNotFoundException)) {
            }
        }
    }
}
