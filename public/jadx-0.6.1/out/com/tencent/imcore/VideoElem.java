package com.tencent.imcore;

public class VideoElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public VideoElem() {
        this(internalJNI.new_VideoElem(), true);
    }

    protected VideoElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(VideoElem videoElem) {
        return videoElem == null ? 0 : videoElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_VideoElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getBusiness_id() {
        return internalJNI.VideoElem_business_id_get(this.swigCPtr, this);
    }

    public byte[] getImageId() {
        return internalJNI.VideoElem_imageId_get(this.swigCPtr, this);
    }

    public int getImage_download_flag() {
        return internalJNI.VideoElem_image_download_flag_get(this.swigCPtr, this);
    }

    public long getImage_height() {
        return internalJNI.VideoElem_image_height_get(this.swigCPtr, this);
    }

    public byte[] getImage_path() {
        return internalJNI.VideoElem_image_path_get(this.swigCPtr, this);
    }

    public long getImage_size() {
        return internalJNI.VideoElem_image_size_get(this.swigCPtr, this);
    }

    public byte[] getImage_type() {
        return internalJNI.VideoElem_image_type_get(this.swigCPtr, this);
    }

    public StrVec getImage_urls() {
        long VideoElem_image_urls_get = internalJNI.VideoElem_image_urls_get(this.swigCPtr, this);
        return VideoElem_image_urls_get == 0 ? null : new StrVec(VideoElem_image_urls_get, false);
    }

    public long getImage_width() {
        return internalJNI.VideoElem_image_width_get(this.swigCPtr, this);
    }

    public long getTaskid() {
        return internalJNI.VideoElem_taskid_get(this.swigCPtr, this);
    }

    public byte[] getVideoId() {
        return internalJNI.VideoElem_videoId_get(this.swigCPtr, this);
    }

    public int getVideo_download_flag() {
        return internalJNI.VideoElem_video_download_flag_get(this.swigCPtr, this);
    }

    public long getVideo_duration() {
        return internalJNI.VideoElem_video_duration_get(this.swigCPtr, this);
    }

    public byte[] getVideo_path() {
        return internalJNI.VideoElem_video_path_get(this.swigCPtr, this);
    }

    public long getVideo_size() {
        return internalJNI.VideoElem_video_size_get(this.swigCPtr, this);
    }

    public byte[] getVideo_type() {
        return internalJNI.VideoElem_video_type_get(this.swigCPtr, this);
    }

    public StrVec getVideo_urls() {
        long VideoElem_video_urls_get = internalJNI.VideoElem_video_urls_get(this.swigCPtr, this);
        return VideoElem_video_urls_get == 0 ? null : new StrVec(VideoElem_video_urls_get, false);
    }

    public void setBusiness_id(int i) {
        internalJNI.VideoElem_business_id_set(this.swigCPtr, this, i);
    }

    public void setImageId(byte[] bArr) {
        internalJNI.VideoElem_imageId_set(this.swigCPtr, this, bArr);
    }

    public void setImage_download_flag(int i) {
        internalJNI.VideoElem_image_download_flag_set(this.swigCPtr, this, i);
    }

    public void setImage_height(long j) {
        internalJNI.VideoElem_image_height_set(this.swigCPtr, this, j);
    }

    public void setImage_path(byte[] bArr) {
        internalJNI.VideoElem_image_path_set(this.swigCPtr, this, bArr);
    }

    public void setImage_size(long j) {
        internalJNI.VideoElem_image_size_set(this.swigCPtr, this, j);
    }

    public void setImage_type(byte[] bArr) {
        internalJNI.VideoElem_image_type_set(this.swigCPtr, this, bArr);
    }

    public void setImage_urls(StrVec strVec) {
        internalJNI.VideoElem_image_urls_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }

    public void setImage_width(long j) {
        internalJNI.VideoElem_image_width_set(this.swigCPtr, this, j);
    }

    public void setTaskid(long j) {
        internalJNI.VideoElem_taskid_set(this.swigCPtr, this, j);
    }

    public void setVideoId(byte[] bArr) {
        internalJNI.VideoElem_videoId_set(this.swigCPtr, this, bArr);
    }

    public void setVideo_download_flag(int i) {
        internalJNI.VideoElem_video_download_flag_set(this.swigCPtr, this, i);
    }

    public void setVideo_duration(long j) {
        internalJNI.VideoElem_video_duration_set(this.swigCPtr, this, j);
    }

    public void setVideo_path(byte[] bArr) {
        internalJNI.VideoElem_video_path_set(this.swigCPtr, this, bArr);
    }

    public void setVideo_size(long j) {
        internalJNI.VideoElem_video_size_set(this.swigCPtr, this, j);
    }

    public void setVideo_type(byte[] bArr) {
        internalJNI.VideoElem_video_type_set(this.swigCPtr, this, bArr);
    }

    public void setVideo_urls(StrVec strVec) {
        internalJNI.VideoElem_video_urls_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }
}
