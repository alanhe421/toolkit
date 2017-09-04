package com.qq.reader.module.question.loader;

import com.qq.reader.module.question.data.AudioData;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.ByteBuffer;

/* compiled from: RecordDataUploadUtil */
public class d {

    /* compiled from: RecordDataUploadUtil */
    public interface a {
        void a(AudioData audioData);

        void a(AudioData audioData, long j);

        void a(AudioData audioData, long j, int i);

        void a(AudioData audioData, long j, long j2);

        void b(AudioData audioData);

        void b(AudioData audioData, long j);
    }

    /* compiled from: RecordDataUploadUtil */
    static class b {
        public final long a;
        public final long b;
        public final long c;
        public final long d;

        public b(long j) {
            this.a = 2882377846L;
            this.b = 1007;
            this.c = 0;
            this.d = j;
        }

        public b(ByteBuffer byteBuffer) {
            this.a = ((long) byteBuffer.getInt()) & 4294967295L;
            this.b = ((long) byteBuffer.getInt()) & 4294967295L;
            this.c = ((long) byteBuffer.getInt()) & 4294967295L;
            this.d = ((long) byteBuffer.getInt()) & 4294967295L;
        }

        public void a(ByteBuffer byteBuffer) {
            byteBuffer.putInt((int) (this.a & -1));
            byteBuffer.putInt((int) (this.b & -1));
            byteBuffer.putInt((int) (this.c & -1));
            byteBuffer.putInt((int) (this.d & -1));
        }
    }

    /* compiled from: RecordDataUploadUtil */
    static class c {
        public final int a = 304;
        public final byte[] b;
        public final int c;
        public final byte[] d;
        public final long e;
        public final long f;
        public final long g;
        public final long h;
        public final long i;

        public c(byte[] bArr, byte[] bArr2, long j, long j2, long j3, long j4, long j5) {
            this.b = bArr;
            this.c = 20;
            this.d = bArr2;
            this.e = j;
            this.f = j2;
            this.g = j3;
            this.h = j4;
            this.i = j5;
        }

        public void a(ByteBuffer byteBuffer) {
            byte[] bArr;
            Object obj;
            int i;
            byteBuffer.putShort((short) (this.a & 65535));
            if (this.b.length == this.a) {
                bArr = this.b;
            } else {
                obj = new byte[this.a];
                if (this.b.length > this.a) {
                    i = this.a;
                } else {
                    i = this.b.length;
                }
                System.arraycopy(this.b, 0, obj, 0, i);
                while (i < this.a) {
                    obj[i] = null;
                    i++;
                }
                Object obj2 = obj;
            }
            byteBuffer.put(bArr, 0, this.a);
            byteBuffer.putShort((short) (this.c & 65535));
            if (this.d.length == this.c) {
                bArr = this.d;
            } else {
                obj = new byte[this.c];
                i = this.d.length > this.c ? this.c : this.d.length;
                System.arraycopy(this.d, 0, obj, 0, i);
                while (i < this.c) {
                    obj[i] = null;
                    i++;
                }
                obj2 = obj;
            }
            byteBuffer.put(bArr, 0, this.c);
            byteBuffer.putInt((int) (this.e & -1));
            byteBuffer.putInt((int) (this.f & -1));
            byteBuffer.putInt((int) (this.g & -1));
            byteBuffer.putInt((int) (this.h & -1));
            byteBuffer.putInt((int) (this.i & -1));
        }
    }

    /* compiled from: RecordDataUploadUtil */
    static class d {
        public final int a;
        public final long b;
        public final long c;

        public d(ByteBuffer byteBuffer) {
            this.a = byteBuffer.get() & 65535;
            this.b = ((long) byteBuffer.getInt()) & 4294967295L;
            this.c = ((long) byteBuffer.getInt()) & 4294967295L;
        }
    }

    public static long a(byte[] bArr, byte[] bArr2, String str, long j, RandomAccessFile randomAccessFile, long j2, int i) throws MalformedURLException, ProtocolException, FileNotFoundException, IOException {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream2;
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream2;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            c cVar = new c(bArr, bArr2, j, j2, (long) i, 0, 0);
            b bVar = new b((long) (i + 348));
            ByteBuffer allocate = ByteBuffer.allocate(i + 364);
            bVar.a(allocate);
            cVar.a(allocate);
            randomAccessFile.seek(j2);
            byte[] bArr3 = new byte[i];
            randomAccessFile.read(bArr3);
            allocate.put(bArr3);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setConnectTimeout(20000);
                httpURLConnection2.setReadTimeout(60000);
                httpURLConnection2.setRequestMethod(Constants.HTTP_POST);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setDoOutput(true);
                OutputStream outputStream2 = httpURLConnection2.getOutputStream();
                try {
                    outputStream2.write(allocate.array());
                    outputStream2.flush();
                    httpURLConnection2.connect();
                    inputStream = httpURLConnection2.getInputStream();
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                    } catch (Throwable th2) {
                        inputStream2 = inputStream;
                        outputStream = outputStream2;
                        httpURLConnection = httpURLConnection2;
                        th = th2;
                        byteArrayOutputStream2 = null;
                        if (outputStream != null) {
                            outputStream.flush();
                            outputStream.close();
                        }
                        if (byteArrayOutputStream2 != null) {
                            byteArrayOutputStream2.flush();
                            byteArrayOutputStream2.close();
                        }
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th22) {
                    inputStream2 = null;
                    outputStream = outputStream2;
                    httpURLConnection = httpURLConnection2;
                    th = th22;
                    byteArrayOutputStream2 = null;
                    if (outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.flush();
                        byteArrayOutputStream2.close();
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
                try {
                    byte[] bArr4 = new byte[25];
                    while (true) {
                        int read = inputStream.read(bArr4);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr4, 0, read);
                    }
                    ByteBuffer wrap = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
                    b bVar2 = new b(wrap);
                    d dVar = new d(wrap);
                    if (bVar2.b != 0) {
                        j = -1;
                    } else if (dVar.a != 1) {
                        j = dVar.b;
                    }
                    if (outputStream2 != null) {
                        outputStream2.flush();
                        outputStream2.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    com.qq.reader.common.monitor.debug.c.a("upoad", "doUploadUnit t = " + (System.currentTimeMillis() - currentTimeMillis) + ", endOffset = " + j + ", data_len = " + i);
                    return j;
                } catch (Throwable th222) {
                    Throwable th3 = th222;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    inputStream2 = inputStream;
                    outputStream = outputStream2;
                    httpURLConnection = httpURLConnection2;
                    th = th3;
                    if (outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.flush();
                        byteArrayOutputStream2.close();
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2222) {
                inputStream2 = null;
                outputStream = null;
                httpURLConnection = httpURLConnection2;
                th = th2222;
                byteArrayOutputStream2 = null;
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.flush();
                    byteArrayOutputStream2.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream2 = null;
            inputStream2 = null;
            outputStream = null;
            httpURLConnection = null;
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.flush();
                byteArrayOutputStream2.close();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
