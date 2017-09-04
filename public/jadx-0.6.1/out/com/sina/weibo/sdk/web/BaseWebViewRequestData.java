package com.sina.weibo.sdk.web;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sina.weibo.sdk.auth.AuthInfo;

public class BaseWebViewRequestData implements Parcelable {
    public static final Creator<BaseWebViewRequestData> CREATOR = new Creator<BaseWebViewRequestData>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public BaseWebViewRequestData a(Parcel parcel) {
            return new BaseWebViewRequestData(parcel);
        }

        public BaseWebViewRequestData[] a(int i) {
            return new BaseWebViewRequestData[i];
        }
    };
    private String a;
    private AuthInfo b;
    private WebRequestType c;
    private String d;
    private String e;
    private int f = 0;

    public String a() {
        return this.d;
    }

    public BaseWebViewRequestData(AuthInfo authInfo, WebRequestType webRequestType, String str, int i, String str2, String str3) {
        this.d = str;
        this.b = authInfo;
        this.c = webRequestType;
        this.e = str2;
        this.a = str3;
        this.f = i;
    }

    public String b() {
        return this.e;
    }

    public String c() {
        return this.a;
    }

    public AuthInfo d() {
        return this.b;
    }

    public WebRequestType e() {
        return this.c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeParcelable(this.b, i);
        parcel.writeInt(this.c == null ? -1 : this.c.ordinal());
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
    }

    protected BaseWebViewRequestData(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (AuthInfo) parcel.readParcelable(AuthInfo.class.getClassLoader());
        int readInt = parcel.readInt();
        this.c = readInt == -1 ? null : WebRequestType.values()[readInt];
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readInt();
    }
}
