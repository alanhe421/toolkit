package com.tencent;

import com.tencent.IMFunc.RequestListener;
import com.tencent.connect.common.Constants;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Map.Entry;

final class bg implements Runnable {
    private /* synthetic */ HttpURLConnection a;
    private /* synthetic */ byte[] b;
    private /* synthetic */ Map c;
    private /* synthetic */ RequestListener d;

    bg(HttpURLConnection httpURLConnection, byte[] bArr, Map map, RequestListener requestListener) {
        this.a = httpURLConnection;
        this.b = bArr;
        this.c = map;
        this.d = requestListener;
    }

    public final void run() {
        try {
            this.a.setRequestMethod(Constants.HTTP_POST);
            this.a.setDoOutput(true);
            this.a.setRequestProperty("Content-Length", String.valueOf(this.b.length));
            for (Entry entry : this.c.entrySet()) {
                this.a.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            this.a.getOutputStream().write(this.b);
            InputStream bufferedInputStream = new BufferedInputStream(this.a.getInputStream());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(10240);
            byte[] bArr = new byte[10240];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read < 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            if (this.d != null) {
                this.d.onSuccess(toByteArray);
            }
            this.a.disconnect();
        } catch (Throwable th) {
            this.a.disconnect();
        }
    }
}
