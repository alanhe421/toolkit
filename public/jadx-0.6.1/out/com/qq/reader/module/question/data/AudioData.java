package com.qq.reader.module.question.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import com.tencent.open.SocialConstants;
import java.io.File;
import org.json.JSONObject;

public class AudioData implements Parcelable {
    public static final Creator<AudioData> CREATOR = new Creator<AudioData>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public AudioData a(Parcel parcel) {
            return new AudioData(parcel);
        }

        public AudioData[] a(int i) {
            return new AudioData[i];
        }
    };
    private String a;
    private AskerData b;
    private AnswerData c;
    private int d = 0;

    public static class AnswerData implements Parcelable {
        public static final Creator<AnswerData> CREATOR = new Creator<AnswerData>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public AnswerData a(Parcel parcel) {
                return new AnswerData(parcel);
            }

            public AnswerData[] a(int i) {
                return new AnswerData[i];
            }
        };
        private long a;
        private long b;
        private long c;
        private long d;
        private String e;
        private String f;
        private int g;
        private int h;
        private int i;
        private long j;
        private File k;
        private String l;
        private String m;
        private String n;
        private long o;
        private int p;
        private int q;
        private int r;
        private int s;
        private int t;
        private int u;
        private String[] v;

        protected AnswerData(Parcel parcel) {
            this.b = parcel.readLong();
            this.a = parcel.readLong();
            this.c = parcel.readLong();
            this.d = parcel.readLong();
            this.e = parcel.readString();
            this.f = parcel.readString();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = parcel.readLong();
            this.l = parcel.readString();
            this.m = parcel.readString();
            this.n = parcel.readString();
            this.o = parcel.readLong();
            this.p = parcel.readInt();
            this.q = parcel.readInt();
            this.r = parcel.readInt();
            this.s = parcel.readInt();
            this.t = parcel.readInt();
            this.u = parcel.readInt();
        }

        public String a() {
            return this.l;
        }

        public boolean a(JSONObject jSONObject) {
            this.b = jSONObject.optLong("uid");
            this.a = jSONObject.optLong("authorId");
            this.e = jSONObject.optString(MessageKey.MSG_ICON).trim();
            this.m = jSONObject.optString("title").trim();
            this.g = jSONObject.optInt(OpenConstants.API_NAME_PAY);
            this.n = jSONObject.optString(MessageKey.MSG_CONTENT).trim();
            this.o = jSONObject.optLong("payTimeLimit");
            this.c = (long) jSONObject.optInt("audioDuration");
            this.f = jSONObject.optString("audioid").trim();
            this.p = jSONObject.optInt("listenCount");
            this.j = jSONObject.optLong("createTime");
            this.q = jSONObject.optInt("purchased");
            this.l = jSONObject.optString("authorName").trim();
            this.r = jSONObject.optInt("type");
            this.s = jSONObject.optInt("labelName");
            this.t = jSONObject.optInt("eavesDroppingCount");
            this.u = jSONObject.optInt("earnMoney");
            return true;
        }

        public int b() {
            return this.t;
        }

        public int c() {
            return this.u;
        }

        public String[] d() {
            return this.v;
        }

        public void a(String[] strArr) {
            this.v = strArr;
        }

        public AnswerData a(long j) {
            this.d = j;
            return this;
        }

        public int e() {
            return this.r;
        }

        public long f() {
            return this.a;
        }

        public String g() {
            return this.e;
        }

        public String h() {
            return this.f;
        }

        public AnswerData a(String str) {
            this.f = str;
            return this;
        }

        public int i() {
            return this.g;
        }

        public long j() {
            return this.c;
        }

        public AnswerData b(long j) {
            this.c = j;
            return this;
        }

        public String k() {
            return this.n;
        }

        public int l() {
            return this.p;
        }

        public AnswerData a(int i) {
            this.p = i;
            return this;
        }

        public long m() {
            return this.o;
        }

        public int n() {
            return this.q;
        }

        public AnswerData b(int i) {
            this.q = i;
            return this;
        }

        public String o() {
            return this.m;
        }

        public int p() {
            return this.s;
        }

        public AnswerData a(File file) {
            this.k = file;
            return this;
        }

        public File q() {
            return this.k;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.b);
            parcel.writeLong(this.a);
            parcel.writeLong(this.c);
            parcel.writeLong(this.d);
            parcel.writeString(this.e);
            parcel.writeString(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeLong(this.j);
            parcel.writeString(this.l);
            parcel.writeString(this.m);
            parcel.writeString(this.n);
            parcel.writeLong(this.o);
            parcel.writeInt(this.p);
            parcel.writeInt(this.q);
            parcel.writeInt(this.r);
            parcel.writeInt(this.s);
            parcel.writeInt(this.t);
            parcel.writeInt(this.u);
        }
    }

    public static class AskerData implements Parcelable {
        public static final Creator<AskerData> CREATOR = new Creator<AskerData>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public AskerData a(Parcel parcel) {
                return new AskerData(parcel);
            }

            public AskerData[] a(int i) {
                return new AskerData[i];
            }
        };
        private String a;
        private String b;
        private String c;
        private String d;
        private int e;
        private String f;
        private int g;
        private long h;
        private int i = -1;
        private String j;
        private long k;
        private String l;
        private int m;

        protected AskerData(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readInt();
            this.f = parcel.readString();
            this.g = parcel.readInt();
            this.h = parcel.readLong();
            this.i = parcel.readInt();
            this.j = parcel.readString();
            this.l = parcel.readString();
            this.m = parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeInt(this.e);
            parcel.writeString(this.f);
            parcel.writeInt(this.g);
            parcel.writeLong(this.h);
            parcel.writeInt(this.i);
            parcel.writeString(this.j);
            parcel.writeString(this.l);
            parcel.writeInt(this.m);
        }

        public boolean a(JSONObject jSONObject) throws Exception {
            this.a = jSONObject.optString("uid").trim();
            this.c = jSONObject.optString("id").trim();
            this.f = jSONObject.optString(MessageKey.MSG_ICON).trim();
            this.d = jSONObject.optString(MessageKey.MSG_CONTENT).trim();
            try {
                this.d = this.d.replaceAll("\\n", "");
                if (this.d.length() > 60) {
                    this.d = this.d.substring(0, 60).concat("…");
                }
            } catch (Exception e) {
            }
            this.e = jSONObject.optInt(OpenConstants.API_NAME_PAY);
            this.b = jSONObject.optString("name").trim();
            this.h = jSONObject.optLong("createTime");
            this.i = jSONObject.optInt("status");
            this.j = jSONObject.optString(SocialConstants.PARAM_SEND_MSG).trim();
            this.k = jSONObject.optLong("expiredTime");
            this.l = jSONObject.optString("qAuthorId");
            this.m = jSONObject.optInt("listenStatus");
            this.g = jSONObject.optInt("vipStatus", 0);
            return true;
        }

        public int a() {
            return this.g;
        }

        public long b() {
            return this.k;
        }

        public long c() {
            return this.h;
        }

        public String d() {
            return this.f;
        }

        public String e() {
            return this.a;
        }

        public String f() {
            return this.b;
        }

        public String g() {
            return this.c;
        }

        public String h() {
            return this.d;
        }

        public int i() {
            return this.e;
        }

        public int j() {
            return this.i;
        }

        public void a(int i) {
            this.i = i;
        }

        public String k() {
            return this.j;
        }

        public String l() {
            return this.l;
        }

        public int m() {
            return this.m;
        }

        public void b(int i) {
            this.m = i;
        }
    }

    protected AudioData(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (AskerData) parcel.readParcelable(AskerData.class.getClassLoader());
        this.c = (AnswerData) parcel.readParcelable(AnswerData.class.getClassLoader());
        this.d = parcel.readInt();
    }

    public void a(AskerData askerData) {
        this.b = askerData;
    }

    public void a(AnswerData answerData) {
        this.c = answerData;
    }

    public AskerData a() {
        return this.b;
    }

    public AnswerData b() {
        return this.c;
    }

    public void a(int i) {
        this.d = i;
    }

    public int c() {
        return this.d;
    }

    public void a(JSONObject jSONObject) throws Exception {
        JSONObject optJSONObject = jSONObject.optJSONObject("question");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("answer");
        AskerData askerData = new AskerData();
        askerData.a(optJSONObject);
        AnswerData answerData = new AnswerData();
        answerData.a(optJSONObject2);
        a(answerData);
        a(askerData);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeParcelable(this.b, i);
        parcel.writeParcelable(this.c, i);
        parcel.writeInt(this.d);
    }
}
