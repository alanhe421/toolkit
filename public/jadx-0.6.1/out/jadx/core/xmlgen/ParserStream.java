package jadx.core.xmlgen;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.jetbrains.annotations.NotNull;

public class ParserStream {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    protected static final Charset STRING_CHARSET_UTF16 = Charset.forName("UTF-16LE");
    protected static final Charset STRING_CHARSET_UTF8 = Charset.forName("UTF-8");
    private final InputStream input;
    private long readPos = 0;

    public ParserStream(@NotNull InputStream inputStream) {
        this.input = inputStream;
    }

    public long getPos() {
        return this.readPos;
    }

    public int readInt8() throws IOException {
        this.readPos++;
        return this.input.read();
    }

    public int readInt16() throws IOException {
        this.readPos += 2;
        return ((this.input.read() & 255) << 8) | (this.input.read() & 255);
    }

    public int readInt32() throws IOException {
        this.readPos += 4;
        InputStream in = this.input;
        int b1 = in.read();
        int b2 = in.read();
        return (((in.read() << 24) | ((in.read() & 255) << 16)) | ((b2 & 255) << 8)) | (b1 & 255);
    }

    public long readUInt32() throws IOException {
        return ((long) readInt32()) & 4294967295L;
    }

    public String readString16Fixed(int len) throws IOException {
        return new String(readInt8Array(len * 2), STRING_CHARSET_UTF16).trim();
    }

    public int[] readInt32Array(int count) throws IOException {
        if (count == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = readInt32();
        }
        return arr;
    }

    public byte[] readInt8Array(int count) throws IOException {
        if (count == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        this.readPos += (long) count;
        byte[] arr = new byte[count];
        int pos = this.input.read(arr, 0, count);
        while (pos < count) {
            int read = this.input.read(arr, pos, count - pos);
            if (read == -1) {
                throw new IOException("No data, can't read " + count + " bytes");
            }
            pos += read;
        }
        return arr;
    }

    public void skip(long count) throws IOException {
        this.readPos += count;
        long pos = this.input.skip(count);
        while (pos < count) {
            long skipped = this.input.skip(count - pos);
            if (skipped == -1) {
                throw new IOException("No data, can't skip " + count + " bytes");
            }
            pos += skipped;
        }
    }

    public void checkInt8(int expected, String error) throws IOException {
        int v = readInt8();
        if (v != expected) {
            throwException(error, expected, v);
        }
    }

    public void checkInt16(int expected, String error) throws IOException {
        int v = readInt16();
        if (v != expected) {
            throwException(error, expected, v);
        }
    }

    private void throwException(String error, int expected, int actual) throws IOException {
        throw new IOException(error + ", expected: 0x" + Integer.toHexString(expected) + ", actual: 0x" + Integer.toHexString(actual) + ", offset: 0x" + Long.toHexString(getPos()));
    }

    public void checkPos(long expectedOffset, String error) throws IOException {
        if (getPos() != expectedOffset) {
            throw new IOException(error + ", expected offset: 0x" + Long.toHexString(expectedOffset) + ", actual: 0x" + Long.toHexString(getPos()));
        }
    }

    public void skipToPos(long expectedOffset, String error) throws IOException {
        long pos = getPos();
        if (pos < expectedOffset) {
            skip(expectedOffset - pos);
        }
        checkPos(expectedOffset, error);
    }

    public void mark(int len) throws IOException {
        if (this.input.markSupported()) {
            this.input.mark(len);
            return;
        }
        throw new IOException("Mark not supported for input stream " + this.input.getClass());
    }

    public void reset() throws IOException {
        this.input.reset();
    }

    public String toString() {
        return "pos: 0x" + Long.toHexString(this.readPos);
    }
}
