package com.tencent.qalsdk.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.Socket;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.util.ByteArrayBuffer;
import qalsdk.n;

public class MsfSocketInputBuffer extends AbsSessionInputBuffer {
    private static final Class SOCKET_TIMEOUT_CLASS = SocketTimeoutExceptionClass();
    private int inputBufferSize = 0;
    public InputStream instream;
    private final Socket socket;

    private static Class SocketTimeoutExceptionClass() {
        try {
            return Class.forName("java.net.SocketTimeoutException");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static boolean isSocketTimeoutException(InterruptedIOException interruptedIOException) {
        if (SOCKET_TIMEOUT_CLASS != null) {
            return SOCKET_TIMEOUT_CLASS.isInstance(interruptedIOException);
        }
        return true;
    }

    public MsfSocketInputBuffer(Socket socket, int i, String str, int i2) throws IOException {
        int i3 = 1024;
        if (socket == null) {
            throw new IllegalArgumentException("Socket may not be null");
        }
        int receiveBufferSize;
        this.socket = socket;
        if (i < 0) {
            receiveBufferSize = socket.getReceiveBufferSize();
        } else {
            receiveBufferSize = i;
        }
        if (receiveBufferSize >= 1024) {
            i3 = receiveBufferSize;
        }
        InputStream inputStream = socket.getInputStream();
        if (!n.m.contains(inputStream.toString())) {
            n.m.add(inputStream.toString());
        }
        init(inputStream, i3, str, i2);
    }

    public boolean isDataAvailable(int i) throws IOException {
        boolean hasBufferedData = hasBufferedData();
        if (!hasBufferedData) {
            try {
                if (fillBuffer() == -1) {
                    throw new IOException("readData return -1");
                }
                hasBufferedData = hasBufferedData();
            } catch (InterruptedIOException e) {
                if (!isSocketTimeoutException(e)) {
                    throw e;
                }
            }
        }
        return hasBufferedData;
    }

    public void reset() {
        if (this.buffer != null) {
            this.buffer = new byte[this.inputBufferSize];
        }
        this.bufferpos = 0;
        this.bufferlen = 0;
        if (this.linebuffer != null) {
            this.linebuffer.clear();
        }
    }

    protected void init(InputStream inputStream, int i, String str, int i2) {
        boolean z = false;
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        } else if (i <= 0) {
            throw new IllegalArgumentException("Buffer size may not be negative or zero");
        } else {
            this.instream = inputStream;
            this.inputBufferSize = i;
            this.buffer = new byte[this.inputBufferSize];
            this.bufferpos = 0;
            this.bufferlen = 0;
            this.linebuffer = new ByteArrayBuffer(i);
            this.charset = str;
            if (this.charset.equalsIgnoreCase("US-ASCII") || this.charset.equalsIgnoreCase("ASCII")) {
                z = true;
            }
            this.ascii = z;
            this.maxLineLen = i2;
            this.metrics = new HttpTransportMetricsImpl();
        }
    }

    protected int fillBuffer() throws IOException {
        int i;
        if (this.bufferpos > 0) {
            i = this.bufferlen - this.bufferpos;
            if (i > 0) {
                System.arraycopy(this.buffer, this.bufferpos, this.buffer, 0, i);
            }
            this.bufferpos = 0;
            this.bufferlen = i;
        }
        int i2 = this.bufferlen;
        i = this.instream.read(this.buffer, i2, this.buffer.length - i2);
        if (i == -1) {
            return -1;
        }
        this.bufferlen = i2 + i;
        this.metrics.incrementBytesTransferred((long) i);
        return i;
    }
}
