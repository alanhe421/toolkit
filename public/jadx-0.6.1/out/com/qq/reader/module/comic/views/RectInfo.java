package com.qq.reader.module.comic.views;

import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.widget.ImageView.ScaleType;

public class RectInfo implements Parcelable {
    public static final Creator<RectInfo> CREATOR = new Creator<RectInfo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RectInfo a(Parcel parcel) {
            return new RectInfo(parcel);
        }

        public RectInfo[] a(int i) {
            return new RectInfo[i];
        }
    };
    public RectF a = new RectF();
    public RectF b = new RectF();
    public RectF c = new RectF();
    public RectF d = new RectF();
    public PointF e = new PointF();
    public float f;
    public float g;
    ScaleType h;

    public RectInfo(RectF rectF, RectF rectF2, RectF rectF3, RectF rectF4, PointF pointF, float f, float f2, ScaleType scaleType) {
        this.a.set(rectF);
        this.b.set(rectF2);
        this.c.set(rectF3);
        this.d.set(rectF4);
        this.e.set(pointF);
        this.f = f;
        this.g = f2;
        this.h = scaleType;
    }

    protected RectInfo(Parcel parcel) {
        this.a = (RectF) parcel.readParcelable(RectF.class.getClassLoader());
        this.b = (RectF) parcel.readParcelable(RectF.class.getClassLoader());
        this.c = (RectF) parcel.readParcelable(RectF.class.getClassLoader());
        this.d = (RectF) parcel.readParcelable(RectF.class.getClassLoader());
        this.e = (PointF) parcel.readParcelable(PointF.class.getClassLoader());
        this.f = parcel.readFloat();
        this.g = parcel.readFloat();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeParcelable(this.b, i);
        parcel.writeParcelable(this.c, i);
        parcel.writeParcelable(this.d, i);
        parcel.writeParcelable(this.e, i);
        parcel.writeFloat(this.f);
        parcel.writeFloat(this.g);
    }

    public RectInfo a() {
        return new RectInfo(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
    }
}
