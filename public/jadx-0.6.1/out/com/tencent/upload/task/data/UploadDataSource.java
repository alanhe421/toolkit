package com.tencent.upload.task.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.upload.common.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class UploadDataSource implements Parcelable {

    public static class ByteDataSource extends UploadDataSource {
        public static final Creator<ByteDataSource> CREATOR = new Creator<ByteDataSource>() {
            public final ByteDataSource createFromParcel(Parcel parcel) {
                return new ByteDataSource(parcel);
            }

            public final ByteDataSource[] newArray(int i) {
                return new ByteDataSource[i];
            }
        };
        private byte[] mData;

        public ByteDataSource(Parcel parcel) {
            int readLong = (int) parcel.readLong();
            if (readLong < 0) {
                readLong = 0;
            }
            this.mData = new byte[readLong];
            parcel.readByteArray(this.mData);
        }

        public ByteDataSource(byte[] bArr) {
            this.mData = bArr;
        }

        public String calcSha1() {
            return c.a(this.mData);
        }

        public int describeContents() {
            return 0;
        }

        public boolean exists() {
            return this.mData != null;
        }

        public long getDataLength() {
            return this.mData != null ? (long) this.mData.length : 0;
        }

        public long readData(long j, int i, byte[] bArr, int i2) {
            System.arraycopy(this.mData, (int) j, bArr, i2, i);
            return (long) i;
        }

        public String toString() {
            return "[Byte:,Size:" + getDataLength() + "]";
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(getDataLength());
            parcel.writeByteArray(this.mData);
        }
    }

    public static class FileDataSource extends UploadDataSource {
        public static final Creator<FileDataSource> CREATOR = new Creator<FileDataSource>() {
            public final FileDataSource createFromParcel(Parcel parcel) {
                return new FileDataSource(parcel);
            }

            public final FileDataSource[] newArray(int i) {
                return new FileDataSource[i];
            }
        };
        private File mFile;
        private String mFilePath;
        private FileInputStream sIns;
        private long sfileOffset;

        public FileDataSource(Parcel parcel) {
            this.mFilePath = parcel.readString();
            this.mFile = new File(this.mFilePath);
        }

        public FileDataSource(String str) {
            this.mFilePath = str;
            this.mFile = new File(str);
        }

        public String calcSha1() {
            return c.a(this.mFile);
        }

        public int describeContents() {
            return 0;
        }

        public boolean exists() {
            return this.mFile.exists();
        }

        public long getDataLength() {
            return this.mFile.exists() ? this.mFile.length() : 0;
        }

        public String getFileName() {
            return this.mFile.getName();
        }

        public long readData(long j, int i, byte[] bArr, int i2) {
            long read;
            synchronized (this) {
                try {
                    InputStream fileInputStream;
                    if (this.sIns == null || this.sfileOffset != j) {
                        try {
                            if (this.sIns != null) {
                                this.sIns.close();
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        fileInputStream = new FileInputStream(this.mFile);
                        this.sIns = fileInputStream;
                        fileInputStream.skip(j);
                        this.sfileOffset = ((long) i) + j;
                    } else {
                        fileInputStream = this.sIns;
                        this.sfileOffset += (long) i;
                    }
                    read = (long) fileInputStream.read(bArr, i2, i);
                    try {
                        if (this.sfileOffset >= this.mFile.length() && this.sIns != null) {
                            this.sIns.close();
                            this.sIns = null;
                            this.sfileOffset = 0;
                        }
                    } catch (Exception e) {
                    }
                } catch (IOException e2) {
                    try {
                        if (this.sIns != null) {
                            this.sIns.close();
                            this.sIns = null;
                            this.sfileOffset = 0;
                        }
                    } catch (Exception e3) {
                    }
                    throw e2;
                } catch (Throwable th2) {
                    try {
                        if (this.sfileOffset >= this.mFile.length() && this.sIns != null) {
                            this.sIns.close();
                            this.sIns = null;
                            this.sfileOffset = 0;
                        }
                    } catch (Exception e4) {
                    }
                }
            }
            return read;
        }

        public String toString() {
            return "[File:" + this.mFilePath + ",Size:" + getDataLength() + "]";
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mFilePath);
        }
    }

    public abstract String calcSha1();

    public abstract boolean exists();

    public abstract long getDataLength();

    public boolean isValid() {
        return getDataLength() > 0;
    }

    public abstract long readData(long j, int i, byte[] bArr, int i2);
}
