package com.tencent.qalsdk.util;

import com.qq.taf.jce.JceStruct;
import java.io.IOException;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbsSessionInputBuffer implements SessionInputBuffer {
    protected boolean ascii = true;
    protected byte[] buffer;
    protected int bufferlen;
    protected int bufferpos;
    protected String charset = "US-ASCII";
    protected ByteArrayBuffer linebuffer = null;
    protected int maxLineLen = -1;
    protected HttpTransportMetricsImpl metrics;

    protected abstract int fillBuffer() throws IOException;

    public boolean hasBufferedData() {
        return this.bufferpos < this.bufferlen;
    }

    public int read() throws IOException {
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.buffer;
        int i = this.bufferpos;
        this.bufferpos = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            return 0;
        }
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        int i3 = this.bufferlen - this.bufferpos;
        if (i3 <= i2) {
            i2 = i3;
        }
        System.arraycopy(this.buffer, this.bufferpos, bArr, i, i2);
        this.bufferpos += i2;
        return i2;
    }

    public int read(byte[] bArr) throws IOException {
        if (bArr == null) {
            return 0;
        }
        return read(bArr, 0, bArr.length);
    }

    private int locateLF() {
        for (int i = this.bufferpos; i < this.bufferlen; i++) {
            if (this.buffer[i] == (byte) 10) {
                return i;
            }
        }
        return -1;
    }

    public int readLine(CharArrayBuffer charArrayBuffer) throws IOException {
        if (charArrayBuffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        }
        this.linebuffer.clear();
        Object obj = 1;
        int i = 0;
        while (obj != null) {
            int locateLF = locateLF();
            if (locateLF == -1) {
                if (hasBufferedData()) {
                    this.linebuffer.append(this.buffer, this.bufferpos, this.bufferlen - this.bufferpos);
                    this.bufferpos = this.bufferlen;
                }
                i = fillBuffer();
                if (i == -1) {
                    obj = null;
                }
            } else if (this.linebuffer.isEmpty()) {
                return lineFromReadBuffer(charArrayBuffer, locateLF);
            } else {
                this.linebuffer.append(this.buffer, this.bufferpos, (locateLF + 1) - this.bufferpos);
                this.bufferpos = locateLF + 1;
                obj = null;
            }
            if (this.maxLineLen > 0 && this.linebuffer.length() >= this.maxLineLen) {
                throw new IOException("Maximum line length limit exceeded");
            }
        }
        if (i == -1 && this.linebuffer.isEmpty()) {
            return -1;
        }
        return lineFromLineBuffer(charArrayBuffer);
    }

    private int lineFromLineBuffer(CharArrayBuffer charArrayBuffer) throws IOException {
        int length = this.linebuffer.length();
        if (length > 0) {
            if (this.linebuffer.byteAt(length - 1) == 10) {
                length--;
                this.linebuffer.setLength(length);
            }
            if (length > 0 && this.linebuffer.byteAt(length - 1) == 13) {
                this.linebuffer.setLength(length - 1);
            }
        }
        length = this.linebuffer.length();
        if (this.ascii) {
            charArrayBuffer.append(this.linebuffer, 0, length);
        } else {
            charArrayBuffer.append(new String(this.linebuffer.buffer(), 0, length, this.charset));
        }
        return length;
    }

    private int lineFromReadBuffer(CharArrayBuffer charArrayBuffer, int i) throws IOException {
        int i2 = this.bufferpos;
        this.bufferpos = i + 1;
        if (i > i2 && this.buffer[i - 1] == JceStruct.SIMPLE_LIST) {
            i--;
        }
        int i3 = i - i2;
        if (this.ascii) {
            charArrayBuffer.append(this.buffer, i2, i3);
        } else {
            charArrayBuffer.append(new String(this.buffer, i2, i3, this.charset));
        }
        return i3;
    }

    public String readLine() throws IOException {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        if (readLine(charArrayBuffer) != -1) {
            return charArrayBuffer.toString();
        }
        return null;
    }

    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public int getBufferpos() {
        return this.bufferpos;
    }

    public int getBufferlen() {
        return this.bufferlen;
    }
}
