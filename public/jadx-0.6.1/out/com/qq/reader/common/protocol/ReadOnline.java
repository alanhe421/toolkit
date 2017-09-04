package com.qq.reader.common.protocol;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.google.gson.Gson;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.charge.voucher.a.a;
import com.qq.reader.common.charge.voucher.a.b;
import com.qq.reader.common.db.handle.h;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookchapter.online.i;
import com.tencent.android.tpush.common.Constants;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReadOnline {

    public static class ReadOnlineFile implements Serializable {
        private static final long serialVersionUID = -6641116127777064030L;
        private int chapterId;
        private String chapterName = "";
        private File destFile;

        public File getDestFile() {
            return this.destFile;
        }

        public void setDestFile(File file) {
            this.destFile = file;
        }

        public int getChapterId() {
            return this.chapterId;
        }

        public void setChapterId(int i) {
            this.chapterId = i;
        }

        public String getChapterName() {
            return this.chapterName;
        }

        public void setChapterName(String str) {
            this.chapterName = str;
        }
    }

    public static class ReadOnlineResult implements Parcelable {
        public static final Creator<ReadOnlineResult> CREATOR = new Creator<ReadOnlineResult>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public ReadOnlineResult a(Parcel parcel) {
                return new ReadOnlineResult(parcel);
            }

            public ReadOnlineResult[] a(int i) {
                return new ReadOnlineResult[i];
            }
        };
        private int a;
        private int b;
        private String c = "";
        private String d = "";
        private String e = "";
        private String f = "";
        private String g = "";
        private int h;
        private String i;
        private int j;
        private int k;
        private String l;
        private final a m = new a();
        private String n;
        private int o = -1;
        private int p;
        private String q;
        private String r;
        private int s;
        private int t;
        private int u;
        private String v;
        private List<ReadOnlineFile> w = new ArrayList();
        private List<Integer> x = new ArrayList();

        public boolean a() {
            if (this.b == -8 || this.b == -9) {
                return true;
            }
            return false;
        }

        public boolean b() {
            if (this.b == -7 || this.b == -8) {
                return true;
            }
            return false;
        }

        public boolean c() {
            if (this.b == -7 || this.b == -8 || this.b == -9 || this.b == -6) {
                return true;
            }
            return false;
        }

        public boolean d() {
            if (this.b == -6 || this.b == -7) {
                return true;
            }
            return false;
        }

        public boolean e() {
            if (this.b == -6 || this.b == -7 || this.b == -8 || this.b == -9) {
                return true;
            }
            return false;
        }

        public boolean f() {
            if (this.b == -5 || this.b == -6 || this.b == -7 || this.b == -9 || this.b == -8) {
                return true;
            }
            return false;
        }

        public int g() {
            return this.j;
        }

        public void a(int i) {
            this.j = i;
        }

        public boolean h() {
            return this.h != DLConstants.LOAD_ERR_ILLEGAL_DEPENDENCY;
        }

        public void b(int i) {
            this.h = i;
        }

        public String i() {
            return this.i;
        }

        public void a(String str) {
            this.i = str;
        }

        public int j() {
            return this.k;
        }

        public void c(int i) {
            this.k = i;
        }

        public int k() {
            return this.a;
        }

        public void d(int i) {
            this.a = i;
        }

        public int l() {
            return this.p;
        }

        public void e(int i) {
            this.p = i;
        }

        public String m() {
            return this.q;
        }

        public void b(String str) {
            this.q = str;
        }

        public int n() {
            return this.s;
        }

        public void f(int i) {
            this.s = i;
        }

        public int o() {
            return this.t;
        }

        public void g(int i) {
            this.t = i;
        }

        public int p() {
            return this.u;
        }

        public String q() {
            return "限时租书 " + this.t + "书币" + this.u + "天>";
        }

        public void h(int i) {
            this.u = i;
        }

        public String r() {
            return this.r;
        }

        public void c(String str) {
            this.r = str;
        }

        public int s() {
            return this.b;
        }

        public void i(int i) {
            this.b = i;
        }

        public String t() {
            return this.d;
        }

        public void d(String str) {
            this.d = str;
        }

        public String u() {
            return this.e;
        }

        public void e(String str) {
            if (str != null) {
                this.e = str;
            }
        }

        public String v() {
            return this.f;
        }

        public void f(String str) {
            if (str != null) {
                this.f = str;
            }
        }

        public String w() {
            return this.g;
        }

        public void a(String str, String str2) {
            String substring = str.substring(str.indexOf("?") + 1);
            String str3 = "&origin=" + str2;
            if (this.b == -6 || this.b == -7) {
                this.g = e.af + substring + str3;
            } else {
                this.g = e.ah + substring + str3;
            }
        }

        public void a(ReadOnlineFile readOnlineFile) {
            this.w.add(readOnlineFile);
        }

        public List<ReadOnlineFile> x() {
            return this.w;
        }

        public List<Integer> y() {
            return this.x;
        }

        public void a(List<Integer> list) {
            this.x = list;
        }

        public int z() {
            return this.m.b;
        }

        public void j(int i) {
            this.m.b = i;
        }

        public int A() {
            return this.m.c;
        }

        public void k(int i) {
            this.m.c = i;
        }

        public int B() {
            return this.m.d;
        }

        public void l(int i) {
            this.m.d = i;
        }

        public void a(b bVar) {
            this.m.g = bVar;
        }

        public String C() {
            return this.v;
        }

        public void g(String str) {
            this.v = str;
        }

        public String D() {
            return this.m.e;
        }

        public void h(String str) {
            this.m.e = str;
        }

        public a E() {
            return this.m;
        }

        public String F() {
            return this.l;
        }

        public void i(String str) {
            this.l = str;
        }

        public String G() {
            return this.n;
        }

        public void j(String str) {
            this.n = str;
        }

        public int H() {
            return this.o;
        }

        public void m(int i) {
            this.o = i;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.b);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
            parcel.writeString(this.f);
            parcel.writeInt(this.h);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
            parcel.writeString(this.l);
        }

        public ReadOnlineResult(Parcel parcel) {
            this.b = parcel.readInt();
            this.d = parcel.readString();
            this.e = parcel.readString();
            this.f = parcel.readString();
            this.h = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
            this.l = parcel.readString();
        }
    }

    private static boolean a(int i) {
        if (i == -6 || i == -7) {
            return true;
        }
        return false;
    }

    private static boolean b(int i) {
        if (i == -8 || i == -9) {
            return true;
        }
        return false;
    }

    public static synchronized ReadOnlineResult a(InputStream inputStream, OnlineTag onlineTag) throws Exception {
        ReadOnlineResult readOnlineResult;
        synchronized (ReadOnline.class) {
            File file;
            List<File> a = ao.a(inputStream, onlineTag.c());
            ReadOnlineResult readOnlineResult2 = new ReadOnlineResult();
            for (File file2 : a) {
                if (file2 != null && file2.getName().equals("info.txt")) {
                    file = file2;
                    break;
                }
            }
            file = null;
            if (file == null || !file.exists()) {
                throw new FileNotFoundException(file.getPath() + " is not exist");
            }
            a.remove(file);
            InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            String a2 = com.qq.reader.common.conn.http.b.a(bufferedInputStream);
            bufferedInputStream.close();
            JSONArray jSONArray = new JSONArray(a2);
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            int optInt = jSONObject.optInt("isVip");
            int optInt2 = jSONObject.optInt("code");
            String optString = jSONObject.optString("book_id");
            long optLong = jSONObject.optLong("limitfreeend_time", 0);
            int optInt3 = jSONObject.optInt("dicountbagid", -1);
            String optString2 = jSONObject.optString("dicountbagdesc");
            int optInt4 = jSONObject.optInt("isRent", 0);
            int optInt5 = jSONObject.optInt("rentPrice", 0);
            int optInt6 = jSONObject.optInt("rentDateRange", 0);
            CharSequence optString3 = jSONObject.optString("openvipdesc");
            CharSequence optString4 = jSONObject.optString("onepricedesc");
            if (optInt3 > 0) {
                readOnlineResult2.e(optInt3);
                readOnlineResult2.b(optString2);
            }
            if (optInt4 > 0) {
                readOnlineResult2.f(optInt4);
                readOnlineResult2.g(optInt5);
                readOnlineResult2.h(optInt6);
            }
            if (!TextUtils.isEmpty(optString3)) {
                readOnlineResult2.c((String) optString3);
            }
            if (!TextUtils.isEmpty(optString4)) {
                readOnlineResult2.g((String) optString4);
            }
            readOnlineResult2.d(optInt);
            if (optInt2 != 0) {
                readOnlineResult2.i(optInt2);
                readOnlineResult2.e(jSONObject.optString("message"));
                readOnlineResult = readOnlineResult2;
            } else {
                optInt6 = jSONArray.length();
                if (optInt6 <= 1) {
                    throw new FileNotFoundException("json data is empty");
                }
                i iVar = new i(optString);
                String str = "";
                onlineTag.d(jSONObject.optInt("book_max_chapter"));
                int optInt7 = jSONObject.optInt("discount", 100);
                String optString5 = jSONObject.optString("disMsg", "");
                optInt2 = jSONObject.optInt("balance", 0);
                int optInt8 = jSONObject.optInt("balance_free", 0);
                readOnlineResult2.j(optInt2);
                readOnlineResult2.k(optInt8);
                JSONObject optJSONObject = jSONObject.optJSONObject("voucher");
                optInt = 0;
                if (optJSONObject != null) {
                    b bVar = (b) new Gson().fromJson(optJSONObject.toString(), b.class);
                    readOnlineResult2.l(bVar.b);
                    readOnlineResult2.a(bVar);
                    optInt = bVar.b;
                }
                readOnlineResult2.j(com.qq.reader.common.charge.voucher.b.a(optInt2, optInt8, optInt));
                str = jSONObject.optString("firstsavemsg");
                if (!ao.s(str)) {
                    readOnlineResult2.h(str);
                }
                int F = onlineTag.F();
                for (optInt5 = 1; optInt5 < optInt6; optInt5++) {
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(optInt5);
                    int optInt9 = jSONObject2.optInt("code");
                    optInt2 = jSONObject2.optInt("chapter_id");
                    if (F == 2) {
                        optInt4 = jSONObject2.optInt("chapter_uuid");
                    } else {
                        optInt4 = optInt2;
                    }
                    int optInt10 = jSONObject2.optInt("paycheckmode", -1);
                    if (optInt4 == onlineTag.s()) {
                        readOnlineResult2.i(optInt9);
                        if (optInt9 != 0) {
                            int optInt11;
                            for (File file3 : a) {
                                if (file3 != null && file3.getName().equals("preview.txt")) {
                                    break;
                                }
                            }
                            File file32 = null;
                            if (file32 != null) {
                                readOnlineResult2.f(com.qq.reader.common.conn.http.b.b(new FileInputStream(file32)));
                            }
                            a2 = jSONObject2.optString("chapter_title");
                            if (!"".equalsIgnoreCase(a2)) {
                                readOnlineResult2.d(a2);
                            }
                            boolean a3 = a(optInt9);
                            boolean b = b(optInt9);
                            if (a3) {
                                optInt11 = jSONObject.optInt("bookprice", 0);
                                optInt8 = (optInt11 * optInt7) / 100;
                                optInt2 = jSONObject.optInt("ltimedisprice", 0);
                                if (optInt2 == 0 || optInt2 >= optInt8) {
                                    optInt2 = optInt8;
                                    optString = optString5;
                                } else {
                                    optString = jSONObject.optString("ltimedismsg", "");
                                }
                                optString5 = optString;
                                optInt8 = optInt2;
                            } else if (b) {
                                optInt2 = jSONObject2.optInt("chaptermprice", 0);
                                optInt11 = optInt2 / 100;
                                optInt8 = (optInt2 * optInt7) / Constants.ERRORCODE_UNKNOWN;
                            } else {
                                optInt11 = 0;
                                optInt8 = 0;
                            }
                            readOnlineResult2.a(optInt11);
                            readOnlineResult2.c(optInt8);
                            optInt2 = jSONObject2.optInt("autopaycode", 0);
                            if (optInt8 > readOnlineResult2.E().a()) {
                                optInt2 = DLConstants.LOAD_ERR_ILLEGAL_DEPENDENCY;
                            }
                            readOnlineResult2.b(optInt2);
                            if (optInt2 == -108) {
                                readOnlineResult2.a(jSONObject2.optString("autopaymsg"));
                            }
                            if (optInt8 != optInt11) {
                                readOnlineResult2.i(optString5);
                            }
                        }
                        if (!(optInt9 == 0 || optInt9 == -9 || optInt9 == -8)) {
                            if (optInt9 == -6 || optInt9 == -7) {
                                readOnlineResult2.a(jSONObject2.optString("buy_chapter_url"), onlineTag.y());
                            } else {
                                readOnlineResult2.e(jSONObject2.getString("message"));
                            }
                        }
                    }
                    if (optInt9 == 0) {
                        if (a.size() > 0) {
                            ReadOnlineFile a4 = a(a, onlineTag.k(), optInt4);
                            if (a4 != null) {
                                readOnlineResult2.a(a4);
                            }
                            if (h.a(optInt10)) {
                                iVar.b(optInt10);
                                iVar.a(optLong);
                                iVar.a(optInt4);
                            }
                        } else {
                            throw new FileNotFoundException(file.getPath() + " is not exist");
                        }
                    }
                }
                h.b().a(iVar);
                file.delete();
                readOnlineResult = readOnlineResult2;
            }
        }
        return readOnlineResult;
    }

    private static ReadOnlineFile a(List<File> list, String str, int i) {
        String str2 = str + "_" + i + "";
        for (File file : list) {
            if (file != null && file.getName().startsWith(str2)) {
                ReadOnlineFile readOnlineFile = new ReadOnlineFile();
                readOnlineFile.setDestFile(file);
                readOnlineFile.setChapterId(i);
                return readOnlineFile;
            }
        }
        return null;
    }
}
