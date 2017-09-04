package com.tencent.imcore;

public class ImageElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ImageElem() {
        this(internalJNI.new_ImageElem(), true);
    }

    protected ImageElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ImageElem imageElem) {
        return imageElem == null ? 0 : imageElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ImageElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getFileid() {
        return internalJNI.ImageElem_fileid_get(this.swigCPtr, this);
    }

    public int getFormat() {
        return internalJNI.ImageElem_format_get(this.swigCPtr, this);
    }

    public String getLarge_url() {
        return internalJNI.ImageElem_large_url_get(this.swigCPtr, this);
    }

    public int getLevel() {
        return internalJNI.ImageElem_level_get(this.swigCPtr, this);
    }

    public String getOrig_url() {
        return internalJNI.ImageElem_orig_url_get(this.swigCPtr, this);
    }

    public int getPic_height() {
        return internalJNI.ImageElem_pic_height_get(this.swigCPtr, this);
    }

    public int getPic_large_height() {
        return internalJNI.ImageElem_pic_large_height_get(this.swigCPtr, this);
    }

    public int getPic_large_size() {
        return internalJNI.ImageElem_pic_large_size_get(this.swigCPtr, this);
    }

    public int getPic_large_width() {
        return internalJNI.ImageElem_pic_large_width_get(this.swigCPtr, this);
    }

    public int getPic_size() {
        return internalJNI.ImageElem_pic_size_get(this.swigCPtr, this);
    }

    public int getPic_thumb_height() {
        return internalJNI.ImageElem_pic_thumb_height_get(this.swigCPtr, this);
    }

    public int getPic_thumb_size() {
        return internalJNI.ImageElem_pic_thumb_size_get(this.swigCPtr, this);
    }

    public int getPic_thumb_width() {
        return internalJNI.ImageElem_pic_thumb_width_get(this.swigCPtr, this);
    }

    public int getPic_width() {
        return internalJNI.ImageElem_pic_width_get(this.swigCPtr, this);
    }

    public int getTaskid() {
        return internalJNI.ImageElem_taskid_get(this.swigCPtr, this);
    }

    public String getThumb_url() {
        return internalJNI.ImageElem_thumb_url_get(this.swigCPtr, this);
    }

    public void setFileid(String str) {
        internalJNI.ImageElem_fileid_set(this.swigCPtr, this, str);
    }

    public void setFormat(int i) {
        internalJNI.ImageElem_format_set(this.swigCPtr, this, i);
    }

    public void setLarge_url(String str) {
        internalJNI.ImageElem_large_url_set(this.swigCPtr, this, str);
    }

    public void setLevel(int i) {
        internalJNI.ImageElem_level_set(this.swigCPtr, this, i);
    }

    public void setOrig_url(String str) {
        internalJNI.ImageElem_orig_url_set(this.swigCPtr, this, str);
    }

    public void setPic_height(int i) {
        internalJNI.ImageElem_pic_height_set(this.swigCPtr, this, i);
    }

    public void setPic_large_height(int i) {
        internalJNI.ImageElem_pic_large_height_set(this.swigCPtr, this, i);
    }

    public void setPic_large_size(int i) {
        internalJNI.ImageElem_pic_large_size_set(this.swigCPtr, this, i);
    }

    public void setPic_large_width(int i) {
        internalJNI.ImageElem_pic_large_width_set(this.swigCPtr, this, i);
    }

    public void setPic_size(int i) {
        internalJNI.ImageElem_pic_size_set(this.swigCPtr, this, i);
    }

    public void setPic_thumb_height(int i) {
        internalJNI.ImageElem_pic_thumb_height_set(this.swigCPtr, this, i);
    }

    public void setPic_thumb_size(int i) {
        internalJNI.ImageElem_pic_thumb_size_set(this.swigCPtr, this, i);
    }

    public void setPic_thumb_width(int i) {
        internalJNI.ImageElem_pic_thumb_width_set(this.swigCPtr, this, i);
    }

    public void setPic_width(int i) {
        internalJNI.ImageElem_pic_width_set(this.swigCPtr, this, i);
    }

    public void setTaskid(int i) {
        internalJNI.ImageElem_taskid_set(this.swigCPtr, this, i);
    }

    public void setThumb_url(String str) {
        internalJNI.ImageElem_thumb_url_set(this.swigCPtr, this, str);
    }
}
