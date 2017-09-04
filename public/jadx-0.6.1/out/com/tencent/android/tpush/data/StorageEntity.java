package com.tencent.android.tpush.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: ProGuard */
public class StorageEntity implements Parcelable {
    public static final Creator CREATOR = new b();
    public String a = "";
    public int b = -1;
    public boolean c;
    public String d;
    public int e;
    public float f;
    public long g;

    public StorageEntity(Parcel parcel) {
        a(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeByte(this.c ? (byte) 1 : (byte) 0);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeFloat(this.f);
        parcel.writeLong(this.g);
    }

    private void a(Parcel parcel) {
        boolean z = true;
        this.a = parcel.readString();
        this.b = parcel.readInt();
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.c = z;
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readFloat();
        this.g = parcel.readLong();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("StorageEntity[key:").append(this.a).append(",type:").append(this.b).append(",strValue:").append(this.d).append(",boolValue:").append(this.c).append(",intValue").append(this.e).append(",floatValue:").append(this.f).append(",longValue:").append(this.g).append("]");
        return stringBuilder.toString();
    }
}
