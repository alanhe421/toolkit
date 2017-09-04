package com.tencent.tesla.soload;

import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.util.LinkedHashMap;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

class MyZipFile implements MyZipConstants {
    static final int GPBF_DATA_DESCRIPTOR_FLAG = 8;
    static final int GPBF_UTF8_FLAG = 2048;
    public static final int OPEN_DELETE = 4;
    public static final int OPEN_READ = 1;
    private MyZipEntry desentry;
    private final String fileName;
    private File fileToDeleteOnClose;
    private String libname;
    private final LinkedHashMap<String, MyZipEntry> mEntries;
    private RandomAccessFile mRaf;

    static class RAFStream extends InputStream {
        long mLength;
        long mOffset;
        RandomAccessFile mSharedRaf;

        public RAFStream(RandomAccessFile randomAccessFile, long j) throws IOException {
            this.mSharedRaf = randomAccessFile;
            this.mOffset = j;
            this.mLength = randomAccessFile.length();
        }

        public int available() throws IOException {
            return this.mOffset < this.mLength ? 1 : 0;
        }

        public int read() throws IOException {
            return Streams.readSingleByte(this);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            synchronized (this.mSharedRaf) {
                this.mSharedRaf.seek(this.mOffset);
                if (((long) i2) > this.mLength - this.mOffset) {
                    i2 = (int) (this.mLength - this.mOffset);
                }
                int read = this.mSharedRaf.read(bArr, i, i2);
                if (read > 0) {
                    this.mOffset += (long) read;
                    return read;
                }
                return -1;
            }
        }

        public long skip(long j) throws IOException {
            if (j > this.mLength - this.mOffset) {
                j = this.mLength - this.mOffset;
            }
            this.mOffset += j;
            return j;
        }
    }

    static class ZipInflaterInputStream extends InflaterInputStream {
        long bytesRead = 0;
        MyZipEntry entry;

        public ZipInflaterInputStream(InputStream inputStream, Inflater inflater, int i, MyZipEntry myZipEntry) {
            super(inputStream, inflater, i);
            this.entry = myZipEntry;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.bytesRead += (long) read;
            }
            return read;
        }

        public int available() throws IOException {
            return super.available() == 0 ? 0 : (int) (this.entry.getSize() - this.bytesRead);
        }
    }

    public MyZipFile(File file, String str) throws ZipException, IOException {
        this(file, 1, str);
    }

    public MyZipFile(File file, int i, String str) throws IOException {
        this.desentry = null;
        this.mEntries = new LinkedHashMap();
        this.libname = str;
        this.fileName = file.getPath();
        if (i == 1 || i == 5) {
            if ((i & 4) != 0) {
                this.fileToDeleteOnClose = file;
            } else {
                this.fileToDeleteOnClose = null;
            }
            this.mRaf = new RandomAccessFile(this.fileName, "r");
            readCentralDir();
            return;
        }
        throw new IllegalArgumentException();
    }

    public MyZipFile(String str, String str2) throws IOException {
        this(new File(str), 1, str2);
    }

    private void checkNotClosed() {
        if (this.mRaf == null) {
            throw new IllegalStateException("Zip file closed");
        }
    }

    public MyZipEntry getEntry(String str) {
        checkNotClosed();
        if (str == null) {
            throw new NullPointerException();
        }
        MyZipEntry myZipEntry = (MyZipEntry) this.mEntries.get(str);
        if (myZipEntry == null) {
            return (MyZipEntry) this.mEntries.get(new StringBuilder(String.valueOf(str)).append("/").toString());
        }
        return myZipEntry;
    }

    public InputStream getInputStream(MyZipEntry myZipEntry) throws IOException {
        MyZipEntry entry = getEntry(myZipEntry.getName());
        if (entry == null) {
            return null;
        }
        RandomAccessFile randomAccessFile = this.mRaf;
        synchronized (randomAccessFile) {
            InputStream rAFStream = new RAFStream(randomAccessFile, entry.mLocalHeaderRelOffset + 28);
            DataInputStream dataInputStream = new DataInputStream(rAFStream);
            short reverseBytes = Short.reverseBytes(dataInputStream.readShort());
            dataInputStream.close();
            rAFStream.skip((long) (entry.nameLength + reverseBytes));
            rAFStream.mLength = rAFStream.mOffset + entry.compressedSize;
            if (entry.compressionMethod == 8) {
                InputStream zipInflaterInputStream = new ZipInflaterInputStream(rAFStream, new Inflater(true), Math.max(1024, (int) Math.min(entry.getSize(), 65535)), entry);
                return zipInflaterInputStream;
            }
            return rAFStream;
        }
    }

    public String getName() {
        return this.fileName;
    }

    public MyZipEntry getDesEntry() {
        return this.desentry;
    }

    private void readCentralDir() throws IOException {
        long j = 0;
        long length = this.mRaf.length() - 22;
        if (length < 0) {
            throw new ZipException("too short to be Zip");
        }
        long j2 = length - 65536;
        if (j2 >= 0) {
            j = j2;
        }
        j2 = length;
        do {
            this.mRaf.seek(j2);
            if (Integer.reverseBytes(this.mRaf.readInt()) == 101010256) {
                byte[] bArr = new byte[18];
                this.mRaf.readFully(bArr);
                BufferIterator it = HeapBufferIterator.iterator(bArr, 0, bArr.length, ByteOrder.LITTLE_ENDIAN);
                short readShort = it.readShort();
                short readShort2 = it.readShort();
                short readShort3 = it.readShort();
                short readShort4 = it.readShort();
                it.skip(4);
                int readInt = it.readInt();
                if (readShort3 == readShort4 && readShort == (short) 0 && readShort2 == (short) 0) {
                    InputStream bufferedInputStream = new BufferedInputStream(new RAFStream(this.mRaf, (long) readInt), 4096);
                    byte[] bArr2 = new byte[46];
                    short s = (short) 0;
                    while (s < readShort3) {
                        MyZipEntry myZipEntry = new MyZipEntry(bArr2, bufferedInputStream);
                        this.mEntries.put(myZipEntry.getName(), myZipEntry);
                        if (myZipEntry.getName().equals(this.libname)) {
                            this.desentry = myZipEntry;
                            return;
                        } else {
                            myZipEntry.getName().contains(ShareConstants.SO_PATH);
                            s++;
                        }
                    }
                    return;
                }
                throw new ZipException("spanned archives not supported");
            }
            j2--;
        } while (j2 >= j);
        throw new ZipException("EOCD not found; not a Zip archive?");
    }
}
