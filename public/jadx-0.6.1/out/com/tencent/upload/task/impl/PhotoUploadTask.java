package com.tencent.upload.task.impl;

import FileCloud.stPhotoUploadReq;
import FileCloud.stPhotoUploadRsp;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.a.c;
import com.tencent.upload.c.b;
import com.tencent.upload.common.a;
import com.tencent.upload.task.ITask.TaskState;
import com.tencent.upload.task.IUploadTaskListener;
import com.tencent.upload.task.UploadTask;
import com.tencent.upload.task.data.FileInfo;

public class PhotoUploadTask extends UploadTask {
    public static final Creator<PhotoUploadTask> CREATOR = new Creator<PhotoUploadTask>() {
        public final PhotoUploadTask createFromParcel(Parcel parcel) {
            return new PhotoUploadTask(parcel);
        }

        public final PhotoUploadTask[] newArray(int i) {
            return new PhotoUploadTask[i];
        }
    };
    private int mExpired;
    private String mFileId;

    public PhotoUploadTask(Parcel parcel) {
        super(parcel);
        setBucket(parcel.readString());
        this.mFileId = parcel.readString();
    }

    public PhotoUploadTask(String str) {
        super(str);
    }

    public PhotoUploadTask(String str, IUploadTaskListener iUploadTaskListener) {
        super(str);
        setUploadListener(iUploadTaskListener);
    }

    public PhotoUploadTask(byte[] bArr, IUploadTaskListener iUploadTaskListener) {
        super(bArr);
        setUploadListener(iUploadTaskListener);
    }

    protected int getBucketSize() {
        return a.a().a("upload_pic_slice_size", 32) << 10;
    }

    protected c getControlRequest() {
        c controlRequest = super.getControlRequest();
        JceStruct FileCloud_stPhotoUploadReq = new stPhotoUploadReq();
        FileCloud_stPhotoUploadReq.directory = "";
        FileCloud_stPhotoUploadReq.expired = (long) this.mExpired;
        FileCloud_stPhotoUploadReq.bind_info = "";
        FileCloud_stPhotoUploadReq.bucket = getBucket();
        FileCloud_stPhotoUploadReq.fileid = this.mFileId;
        controlRequest.a(1, FileCloud_stPhotoUploadReq);
        return controlRequest;
    }

    public FileType getFileType() {
        return FileType.Photo;
    }

    public String getTag() {
        return "PhotoUploadTask";
    }

    protected void onUploadError(int i, String str) {
        a.a.c(getTag(), "upload photo failed! actionId=" + getTaskId() + " ret=" + i + " msg=" + str);
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
        a.a.b(getTag(), "upload photo succeed! actionId=" + getTaskId());
        if (this.mListener != null && obj != null && (obj instanceof byte[])) {
            stPhotoUploadRsp FileCloud_stPhotoUploadRsp = (stPhotoUploadRsp) b.a((byte[]) obj, "RSP");
            FileInfo fileInfo = new FileInfo();
            if (FileCloud_stPhotoUploadRsp != null) {
                fileInfo.url = FileCloud_stPhotoUploadRsp.url;
                fileInfo.fileId = FileCloud_stPhotoUploadRsp.fileid;
                this.mUrl = fileInfo.url;
                if (FileCloud_stPhotoUploadRsp.stats != null) {
                    fileInfo.extendInfo.putAll(FileCloud_stPhotoUploadRsp.stats);
                }
            }
            this.mListener.onUploadSucceed(fileInfo);
        }
    }

    public void setExpired(int i) {
        this.mExpired = i;
    }

    public void setFileId(String str) {
        this.mFileId = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(getBucket());
        parcel.writeString(this.mFileId);
    }
}
