package qalsdk;

import com.tencent.qalsdk.util.AbsSessionInputBuffer;
import java.io.IOException;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.util.ByteArrayBuffer;

/* compiled from: MsfHttpSocketBuffer */
public class l extends AbsSessionInputBuffer {
    public l() {
        this.linebuffer = new ByteArrayBuffer(1024);
        this.charset = "US-ASCII";
        boolean z = this.charset.equalsIgnoreCase("US-ASCII") || this.charset.equalsIgnoreCase("ASCII");
        this.ascii = z;
        this.maxLineLen = -1;
        this.metrics = new HttpTransportMetricsImpl();
    }

    public void a(byte[] bArr) {
        if (this.bufferlen == 0 || this.bufferpos >= this.bufferlen) {
            this.buffer = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
        } else {
            int i = this.bufferlen - this.bufferpos;
            Object obj = new byte[(bArr.length + i)];
            System.arraycopy(this.buffer, this.bufferpos, obj, 0, i);
            System.arraycopy(bArr, 0, obj, i, bArr.length);
            this.buffer = obj;
        }
        this.bufferpos = 0;
        this.bufferlen = this.buffer.length;
        if (this.linebuffer != null) {
            this.linebuffer.clear();
        }
        this.metrics.incrementBytesTransferred((long) bArr.length);
    }

    public boolean isDataAvailable(int i) {
        return hasBufferedData();
    }

    protected int fillBuffer() throws IOException {
        return -1;
    }
}
