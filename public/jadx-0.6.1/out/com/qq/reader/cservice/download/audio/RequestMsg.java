package com.qq.reader.cservice.download.audio;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.f;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.ao;
import com.tencent.connect.common.Constants;
import java.util.HashMap;

public class RequestMsg implements Parcelable {
    public static final Creator<RequestMsg> CREATOR = new Creator<RequestMsg>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RequestMsg a(Parcel parcel) {
            return new RequestMsg(parcel);
        }

        public RequestMsg[] a(int i) {
            return new RequestMsg[i];
        }
    };
    public boolean a;
    public final boolean b;
    protected transient a c;
    private Context d;
    private String e;
    private Bundle f;
    private String g;
    private String h;
    private HashMap<String, String> i;
    private int j;
    private boolean k;
    private byte[] l;

    public RequestMsg() {
        this(null, null, true, 0);
    }

    public RequestMsg(Parcel parcel) {
        this(null, null, true, 0);
        a(parcel);
    }

    public RequestMsg(String str) {
        this(str, null, true, 0);
    }

    public RequestMsg(String str, String str2, boolean z, int i) {
        this.h = Constants.HTTP_POST;
        this.k = false;
        this.a = z;
        this.g = str;
        this.e = str2;
        this.d = ReaderApplication.getApplicationImp();
        this.i = new HashMap();
        if (1 == i) {
            this.b = true;
        } else if (2 == i) {
            this.b = false;
        } else if (i == 0) {
            this.b = false;
        } else {
            this.b = false;
        }
        f();
    }

    public void a(String str, String str2) {
        if (this.i == null) {
            this.i = new HashMap();
        }
        this.i.put(str, str2);
    }

    private void f() {
        Object obj = d.c() + "";
        if (obj.equals("0")) {
            obj = "";
        }
        String h = d.h(this.d);
        this.c = c.c();
        if (c.b()) {
            this.i.put("loginType", String.valueOf(this.c.d()));
            switch (this.c.d()) {
                case 1:
                case 2:
                case 10:
                case 50:
                    if (!(this.c instanceof com.qq.reader.common.login.b.d)) {
                        this.i.put("usid", this.c.a(this.d));
                        this.i.put("uid", this.c.c());
                        this.i.put("qqnum", this.c.c());
                        break;
                    }
                    String a = this.c.a(this.d);
                    this.i.put("skey", a);
                    this.i.put("ckey", d.a(a));
                    this.i.put("qqnum", this.c.c());
                    break;
            }
        }
        this.i.put("sid", obj);
        this.i.put("qimei", h);
        this.i.put("timi", d.z(this.d));
        this.i.put("nosid", "1");
        this.i.put("c_platform", "android");
        this.i.put("c_version", "qqreader_6.5.3.0888_android");
        this.i.put("mversion", f.a(this.d));
        this.i.put("ua", d.a());
        this.i.put("channel", ao.h(this.d));
        this.i.put("safekey", d.y(this.d));
        this.i.put("supportTS", "2");
    }

    public HashMap<String, String> a() {
        return this.i;
    }

    public String b() {
        return this.g;
    }

    public byte[] c() {
        return this.l;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.h;
    }

    public void a(String str) {
        this.h = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeInt(this.j);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        if (this.e == null) {
            this.e = "";
        }
        parcel.writeString(this.e);
        if (this.f != null) {
            parcel.writeInt(0);
            parcel.writeBundle(this.f);
        } else {
            parcel.writeInt(-1);
        }
        if (this.i != null) {
            parcel.writeInt(this.i.size());
            for (String str : this.i.keySet()) {
                parcel.writeString(str);
                parcel.writeString((String) this.i.get(str));
            }
        } else {
            parcel.writeInt(-1);
        }
        parcel.writeInt(this.a ? 1 : 0);
        if (!this.k) {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (this.l != null) {
            parcel.writeInt(this.l.length);
            parcel.writeByteArray(this.l);
            return;
        }
        parcel.writeInt(0);
    }

    public void a(Parcel parcel) {
        int i;
        boolean z = true;
        this.j = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.e = parcel.readString();
        try {
            if (parcel.readInt() == 0) {
                this.f = parcel.readBundle(getClass().getClassLoader());
            }
        } catch (Exception e) {
        }
        try {
            this.i = new HashMap();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                for (i = 0; i < readInt; i++) {
                    this.i.put(parcel.readString(), parcel.readString());
                }
            }
        } catch (Exception e2) {
        }
        this.a = parcel.readInt() == 1;
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.k = z;
        i = parcel.readInt();
        if (i > 0) {
            this.l = new byte[i];
            parcel.readByteArray(this.l);
        }
    }
}
