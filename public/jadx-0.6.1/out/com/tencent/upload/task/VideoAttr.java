package com.tencent.upload.task;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class VideoAttr implements Parcelable {
    public static final Creator<VideoAttr> CREATOR = new Creator<VideoAttr>() {
        public final VideoAttr createFromParcel(Parcel parcel) {
            return new VideoAttr(parcel);
        }

        public final VideoAttr[] newArray(int i) {
            return new VideoAttr[i];
        }
    };
    public String coverUrl = "";
    public String desc = "";
    public boolean isCheck = true;
    public long timeLen = 0;
    public String title = "";

    public VideoAttr(Parcel parcel) {
        boolean z = true;
        this.title = parcel.readString();
        this.desc = parcel.readString();
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.isCheck = z;
        this.coverUrl = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.desc);
        parcel.writeInt(this.isCheck ? 1 : 0);
        parcel.writeString(this.coverUrl);
    }
}
