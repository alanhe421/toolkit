package com.sina.weibo.sdk.auth;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sina.weibo.sdk.b.k;
import com.tencent.connect.common.Constants;

public class AuthInfo implements Parcelable {
    public static final Creator<AuthInfo> CREATOR = new Creator<AuthInfo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public AuthInfo a(Parcel parcel) {
            return new AuthInfo(parcel);
        }

        public AuthInfo[] a(int i) {
            return new AuthInfo[i];
        }
    };
    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";

    public AuthInfo(Context context, String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = context.getPackageName();
        this.e = k.a(context, this.d);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public Bundle f() {
        Bundle bundle = new Bundle();
        bundle.putString("appKey", this.a);
        bundle.putString("redirectUri", this.b);
        bundle.putString(Constants.PARAM_SCOPE, this.c);
        bundle.putString("packagename", this.d);
        bundle.putString("key_hash", this.e);
        return bundle;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
    }

    protected AuthInfo(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
    }
}
