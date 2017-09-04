package com.tencent.upload.task.impl;

import FileCloud.stFileUploadReq;
import FileCloud.stFileUploadRsp;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.a.c;
import com.tencent.upload.common.a;
import com.tencent.upload.common.i;
import com.tencent.upload.log.b;
import com.tencent.upload.task.ITask.TaskState;
import com.tencent.upload.task.IUploadTaskListener;
import com.tencent.upload.task.UploadTask;
import com.tencent.upload.task.data.FileInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class FileUploadTask extends UploadTask {
    public static final Creator<FileUploadTask> CREATOR = new Creator<FileUploadTask>() {
        public final FileUploadTask createFromParcel(Parcel parcel) {
            return new FileUploadTask(parcel);
        }

        public final FileUploadTask[] newArray(int i) {
            return new FileUploadTask[i];
        }
    };
    private String mBindInfo;
    private String mDestFilePath;

    public FileUploadTask(Parcel parcel) {
        super(parcel);
        this.mDestFilePath = parcel.readString();
        this.mBindInfo = parcel.readString();
    }

    public FileUploadTask(String str, String str2, String str3, String str4, IUploadTaskListener iUploadTaskListener) {
        super(str2);
        setBucket(str);
        setUploadListener(iUploadTaskListener);
        this.mDestFilePath = str3;
        this.mBindInfo = str4;
        if (this.mBindInfo == null) {
            this.mBindInfo = "";
        }
    }

    public static String getFileSha1(File file) {
        FileInputStream fileInputStream;
        Throwable e;
        Throwable th;
        String str = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                byte[] bArr = new byte[65536];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                str = i.a(instance.digest());
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    b.c("FileUtils", "getFileSha1->NoSuchAlgorithmException###", e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (OutOfMemoryError e4) {
                e = e4;
                b.c("FileUtils", "getFileSha1->OutOfMemoryError###", e);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
                return str;
            }
        } catch (Exception e5) {
            e = e5;
            Object obj = str;
            b.c("FileUtils", "getFileSha1->NoSuchAlgorithmException###", e);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (OutOfMemoryError e6) {
            e = e6;
            fileInputStream = str;
            b.c("FileUtils", "getFileSha1->OutOfMemoryError###", e);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable e7) {
            fileInputStream = str;
            th = e7;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }

    protected int getBucketSize() {
        return a.a().a("upload_file_slice_size", 128) << 10;
    }

    protected c getControlRequest() {
        c controlRequest = super.getControlRequest();
        controlRequest.a = getAbsolutePath(this.mDestFilePath);
        controlRequest.a(4, new stFileUploadReq(this.mBindInfo, null));
        return controlRequest;
    }

    public FileType getFileType() {
        return FileType.File;
    }

    public String getTag() {
        return "FileUploadTask";
    }

    protected void onUploadError(int i, String str) {
        a.a.c(getTag(), "upload file failed! actionId=" + getTaskId() + " ret=" + i + " msg=" + str);
        if (this.mListener != null) {
            this.mListener.onUploadFailed(i, str);
        }
    }

    protected void onUploadProgress(long j, long j2) {
        if (this.mListener != null) {
            this.mListener.onUploadProgress(j, j2);
        }
    }

    protected void onUploadStateChange(TaskState taskState) {
        if (this.mListener != null) {
            this.mListener.onUploadStateChange(taskState);
        }
    }

    protected void onUploadSucceed(Object obj) {
        a.a.b(getTag(), "upload file succeed! actionId=" + getTaskId());
        if (this.mListener != null && obj != null && (obj instanceof byte[])) {
            stFileUploadRsp FileCloud_stFileUploadRsp = (stFileUploadRsp) com.tencent.upload.c.b.a((byte[]) obj, "RSP");
            FileInfo fileInfo = new FileInfo();
            if (FileCloud_stFileUploadRsp != null) {
                fileInfo.url = FileCloud_stFileUploadRsp.url;
                this.mUrl = fileInfo.url;
            }
            this.mListener.onUploadSucceed(fileInfo);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mDestFilePath);
        parcel.writeString(this.mBindInfo);
    }
}
