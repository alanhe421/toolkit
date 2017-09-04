package com.qq.reader.module.bookchapter.online;

import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.common.charge.voucher.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.b.b;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.plugin.audiobook.model.MusicChapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OnlineBookOperator */
public class f {
    private d a = null;
    private OnlineTag b = null;

    public f(OnlineTag onlineTag) {
        this.b = onlineTag;
        this.a = new d();
    }

    public void a() {
        RandomAccessFile randomAccessFile;
        Throwable th;
        RandomAccessFile randomAccessFile2 = null;
        synchronized (f.class) {
            File file = new File(this.b.e());
            if (file.exists() && file.length() > 0) {
                try {
                    randomAccessFile = new RandomAccessFile(file, "r");
                    if (randomAccessFile != null) {
                        try {
                            if (randomAccessFile.length() != 0) {
                                byte[] bArr = new byte[((int) randomAccessFile.length())];
                                randomAccessFile.read(bArr);
                                e(new String(bArr));
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e) {
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e3) {
                                }
                            }
                            return;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            randomAccessFile2 = randomAccessFile;
                            th = th3;
                            if (randomAccessFile2 != null) {
                                try {
                                    randomAccessFile2.close();
                                } catch (IOException e4) {
                                }
                            }
                            throw th;
                        }
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    } else {
                        randomAccessFile2 = randomAccessFile;
                    }
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e5) {
                        }
                    }
                    return;
                } catch (Exception e6) {
                    randomAccessFile = null;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    return;
                } catch (Throwable th4) {
                    th = th4;
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    throw th;
                }
            }
            return;
        }
    }

    public void b() {
        Throwable th;
        RandomAccessFile randomAccessFile = null;
        if (this.b != null) {
            synchronized (f.class) {
                if (z()) {
                    RandomAccessFile randomAccessFile2;
                    try {
                        randomAccessFile2 = new RandomAccessFile(this.b.e(), "r");
                        if (randomAccessFile2 != null) {
                            try {
                                if (randomAccessFile2.length() != 0) {
                                    byte[] bArr = new byte[((int) randomAccessFile2.length())];
                                    randomAccessFile2.read(bArr);
                                    String str = new String(bArr);
                                    if (this.b == null || this.b.F() != 2) {
                                        b(str);
                                    } else {
                                        a(str);
                                    }
                                    if (randomAccessFile2 != null) {
                                        try {
                                            randomAccessFile2.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            } catch (Exception e2) {
                                if (randomAccessFile2 != null) {
                                    try {
                                        randomAccessFile2.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                randomAccessFile = randomAccessFile2;
                                th = th3;
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        } else {
                            randomAccessFile = randomAccessFile2;
                        }
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                        return;
                    } catch (Exception e5) {
                        randomAccessFile2 = null;
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        throw th;
                    }
                }
            }
        }
    }

    public boolean c() throws Throwable {
        int i = 0;
        try {
            JSONArray optJSONArray;
            List arrayList = new ArrayList(this.a.j());
            SparseArray sparseArray = new SparseArray(this.a.j());
            JSONObject jSONObject = new JSONObject(ao.a(new FileInputStream(new File(this.b.d()))));
            if (jSONObject != null) {
                optJSONArray = jSONObject.optJSONArray("index");
            } else {
                optJSONArray = null;
            }
            Set hashSet = new HashSet();
            int i2 = 0;
            int i3 = 0;
            while (optJSONArray != null && i2 < optJSONArray.length()) {
                OnlineChapter a = a(optJSONArray.optJSONObject(i2));
                if (hashSet.add(a)) {
                    if (a != null && a.getPrice() > 0.0f) {
                        i3++;
                        i = (int) (((float) i) + a.getPrice());
                    }
                    arrayList.add(a);
                    sparseArray.append((int) a.getUUID(), Integer.valueOf(a.getChapterId()));
                }
                i2++;
            }
            hashSet.clear();
            this.a.b(arrayList);
            this.a.a(sparseArray);
            a(arrayList, this.a.f());
            if (i3 > 0) {
                this.a.j(i / i3);
            }
        } catch (Exception e) {
            c.e("Error", e.getMessage());
        }
        return true;
    }

    public boolean d() throws Throwable {
        Throwable th;
        int i = 0;
        b bVar = null;
        try {
            List arrayList = new ArrayList(this.a.j());
            SparseArray sparseArray = new SparseArray(this.a.j());
            b bVar2 = new b(new BufferedReader(new FileReader(this.b.d()), 65536));
            try {
                Set hashSet = new HashSet();
                int i2 = 0;
                while (true) {
                    String[] a = bVar2.a();
                    if (a == null) {
                        break;
                    }
                    OnlineChapter a2 = a(a);
                    if (hashSet.add(a2)) {
                        if (a2 != null && a2.getPrice() > 0.0f) {
                            i2++;
                            i = (int) (((float) i) + a2.getPrice());
                        }
                        arrayList.add(a2);
                        sparseArray.append((int) a2.getUUID(), Integer.valueOf(a2.getChapterId()));
                    }
                }
                hashSet.clear();
                this.a.b(arrayList);
                this.a.a(sparseArray);
                a(arrayList, this.a.f());
                if (i2 > 0) {
                    this.a.j(i / i2);
                }
                if (bVar2 != null) {
                    try {
                        bVar2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
                bVar = bVar2;
                if (bVar != null) {
                    try {
                        bVar.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (bVar != null) {
                bVar.close();
            }
            throw th;
        }
    }

    private OnlineChapter a(JSONObject jSONObject) {
        return b(jSONObject);
    }

    private OnlineChapter a(String[] strArr) {
        return b(strArr);
    }

    private OnlineChapter b(JSONObject jSONObject) {
        int i = 1;
        int i2 = 0;
        OnlineChapter musicChapter = new MusicChapter();
        if (jSONObject == null) {
            return musicChapter;
        }
        int optInt = jSONObject.optInt("chapterIndex", 0);
        musicChapter.setChapterId(optInt);
        musicChapter.setAcid(jSONObject.optLong("acid", 0));
        musicChapter.setChapterName(ao.a(optInt, jSONObject.optString("audioChapterName")));
        musicChapter.setDuration(jSONObject.optLong("duration"));
        musicChapter.setSize((float) jSONObject.optDouble("size"));
        int optInt2 = jSONObject.optInt("price", 0);
        int optInt3 = jSONObject.optInt("sort", -1);
        JSONObject optJSONObject = jSONObject.optJSONObject("xmlyExtra");
        optInt = optJSONObject != null ? optJSONObject.optInt("canFreeAudition", -1) == 1 ? 1 : 0 : 0;
        if (this.a.h() != 1) {
            if (this.a.h() != 2 || this.a.z() <= 0) {
                if ((optInt3 < 0 || optInt3 >= this.a.k()) && r0 == 0) {
                    i = 0;
                    i2 = this.a.r();
                }
            } else if ((optInt3 < 0 || optInt3 >= this.a.k()) && r0 == 0) {
                i = 0;
                i2 = optInt2;
            }
        }
        musicChapter.setPrice((float) i2);
        musicChapter.setIsFree(i);
        musicChapter.setUUID(jSONObject.optLong("acid", 0));
        musicChapter.setBookId(jSONObject.optLong("adid", 0));
        musicChapter.setBookName(this.b.b());
        return musicChapter;
    }

    private OnlineChapter b(String[] strArr) {
        int i = 1;
        OnlineChapter onlineChapter = new OnlineChapter();
        int parseInt = Integer.parseInt(strArr[0]);
        onlineChapter.setChapterId(parseInt);
        onlineChapter.setChapterName(strArr[1]);
        onlineChapter.setWords(Long.parseLong(strArr[2]));
        int parseInt2 = Integer.parseInt(strArr[3]);
        if (this.a.z() <= 0 ? parseInt2 == 0 : parseInt <= this.a.k()) {
            i = 0;
        }
        onlineChapter.setWprice(parseInt2);
        float f = (float) parseInt2;
        if (strArr.length > 4) {
            float parseFloat = Float.parseFloat(strArr[5]);
            if (parseFloat > 0.0f) {
                f = parseFloat / 100.0f;
            }
        }
        onlineChapter.setPrice(f);
        onlineChapter.setIsFree(i);
        onlineChapter.setBookId(Long.parseLong(this.b.k()));
        onlineChapter.setBookName(this.b.b());
        if (strArr.length > 6) {
            onlineChapter.setChapterMD5(strArr[6]);
        }
        if (strArr.length > 7) {
            onlineChapter.setUUID(Long.parseLong(strArr[7]));
        }
        return onlineChapter;
    }

    private int e(String str) throws JSONException {
        return d(new JSONObject(str));
    }

    private int f(String str) throws JSONException {
        return c(new JSONObject(str));
    }

    private int c(JSONObject jSONObject) throws JSONException {
        boolean z = true;
        int d = d(jSONObject);
        if (jSONObject != null) {
            this.a.q(jSONObject.optString("songer"));
            this.a.s(jSONObject.optInt("status", 1));
            this.a.t(jSONObject.optInt("checklevel", 0));
            this.a.g(jSONObject.optInt("xmlydiscountprice", 0));
            this.a.e(jSONObject.optLong("publisher", 0));
            JSONObject optJSONObject = jSONObject.optJSONObject("detailDes");
            if (optJSONObject != null) {
                this.a.z(optJSONObject.optString("firstL"));
                this.a.A(optJSONObject.optString("first"));
                this.a.B(optJSONObject.optString("second"));
                d dVar = this.a;
                if (optJSONObject.optInt("needOpenVip", 0) <= 0) {
                    z = false;
                }
                dVar.a(z);
            }
        }
        return d;
    }

    private int d(JSONObject jSONObject) throws JSONException {
        this.a.a(jSONObject.optInt("packageSize"));
        this.a.h(jSONObject.optInt("bookdiscount", 100));
        this.a.m(jSONObject.optString("eventmsg", ""));
        this.a.a(jSONObject.optLong(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION));
        this.a.a(this.b.k());
        this.a.e(jSONObject.optString("bookid"));
        this.a.f(jSONObject.optString("lastupdatechaptername"));
        this.a.b(jSONObject.optString("title", ""));
        this.a.q(jSONObject.optString("author", ""));
        this.a.b(Long.parseLong(jSONObject.optString("updateDate")));
        int optInt = jSONObject.optInt("newdownloadType", Integer.MIN_VALUE);
        if (optInt != Integer.MIN_VALUE) {
            this.a.b(optInt);
        } else {
            this.a.b(jSONObject.optInt("downloadType"));
        }
        int optInt2 = jSONObject.optInt("totalChapters");
        this.a.c(optInt2);
        d dVar = this.a;
        if (jSONObject.optInt("startChargeChapterSort", -1) >= 0) {
            optInt = jSONObject.optInt("startChargeChapterSort", 0);
        } else {
            optInt = jSONObject.optInt("maxfreechapter");
        }
        dVar.d(optInt);
        this.a.c(jSONObject.optString("downloadUrl"));
        this.a.k(jSONObject.optInt("price", 0));
        this.a.f(jSONObject.optInt("unitprice", 0));
        this.a.g(jSONObject.optString("discountEndtime"));
        this.a.h(jSONObject.optString("ltimedisperiod"));
        this.a.m(jSONObject.optInt("isfinished", 0));
        this.a.n(jSONObject.optString("format", "txt"));
        this.a.o(jSONObject.optString("bookfrom", ""));
        this.a.p(jSONObject.optString("copyright", ""));
        this.a.n(jSONObject.optInt("drm", 0));
        this.a.p(jSONObject.optInt("commentcount", 0));
        this.a.s(jSONObject.optString("publishtime"));
        this.a.t(jSONObject.optString("publisher"));
        this.a.r(jSONObject.optString("categoryName"));
        this.a.u(jSONObject.optString("isbn"));
        this.a.o(jSONObject.optInt("wordscount"));
        this.a.v(jSONObject.optString("authoricon"));
        this.a.w(jSONObject.optString("authorid"));
        this.a.q(jSONObject.optInt("ltimedisprice", 0));
        this.a.x(jSONObject.optString("ltimedismsg", ""));
        this.a.d(jSONObject.optString("songer"));
        this.a.c(jSONObject.optLong("adid", 0));
        this.a.i(jSONObject.optString("intro"));
        this.a.j(jSONObject.optString("mediakey"));
        this.a.a(jSONObject.optBoolean("needOpenVip"));
        long optLong = jSONObject.optLong("discountbagendtime", 0) * 1000;
        String optString = jSONObject.optString("discountbagdesc");
        this.a.d(optLong);
        this.a.k(optString);
        this.a.l(jSONObject.optString("categoryInfoV4SlaveId"));
        this.a.y(jSONObject.optString("purchaseTicketIntro"));
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("purchaseTickets");
            if (optJSONArray != null) {
                this.a.a((ArrayList) new Gson().fromJson(optJSONArray.toString(), new TypeToken<ArrayList<d>>(this) {
                    final /* synthetic */ f a;

                    {
                        this.a = r1;
                    }
                }.getType()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (optLong > 0 && optLong > System.currentTimeMillis()) {
            this.a.h(0);
            this.a.m(optString);
        }
        this.a.l(this.a.K());
        this.a.b(jSONObject.optBoolean("payinfo", false));
        this.a.e(jSONObject.optInt("allAudioChapters", 0));
        this.a.i(jSONObject.optInt("estimatedTrackCount", 0));
        return optInt2;
    }

    public boolean a(String str) {
        if (this.b == null) {
            return false;
        }
        try {
            f(str);
            c();
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean b(String str) {
        if (this.b == null) {
            return false;
        }
        b bVar = null;
        try {
            e(str);
            d();
            if (bVar != null) {
                try {
                    bVar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } catch (Throwable th) {
            if (bVar != null) {
                try {
                    bVar.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int c(java.lang.String r22) {
        /*
        r21 = this;
        r0 = r21;
        r2 = r0.b;
        if (r2 != 0) goto L_0x0008;
    L_0x0006:
        r2 = -1;
    L_0x0007:
        return r2;
    L_0x0008:
        r3 = 0;
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0043 }
        r0 = r22;
        r2.<init>(r0);	 Catch:{ Exception -> 0x0043 }
        r4 = "retCode";
        r4 = r2.optInt(r4);	 Catch:{ Exception -> 0x0043 }
        switch(r4) {
            case 0: goto L_0x0022;
            case 1: goto L_0x004e;
            case 2: goto L_0x0076;
            default: goto L_0x001a;
        };
    L_0x001a:
        r2 = r3;
    L_0x001b:
        if (r2 == 0) goto L_0x0020;
    L_0x001d:
        r2.close();	 Catch:{ IOException -> 0x01e1 }
    L_0x0020:
        r2 = r4;
        goto L_0x0007;
    L_0x0022:
        r0 = r21;
        r5 = r0.b;	 Catch:{ Exception -> 0x0043 }
        if (r5 == 0) goto L_0x003d;
    L_0x0028:
        r0 = r21;
        r5 = r0.b;	 Catch:{ Exception -> 0x0043 }
        r5 = r5.F();	 Catch:{ Exception -> 0x0043 }
        r6 = 2;
        if (r5 != r6) goto L_0x003d;
    L_0x0033:
        r0 = r21;
        r0.c(r2);	 Catch:{ Exception -> 0x0043 }
    L_0x0038:
        r21.d(r22);	 Catch:{ Exception -> 0x0043 }
        r2 = r3;
        goto L_0x001b;
    L_0x003d:
        r0 = r21;
        r0.d(r2);	 Catch:{ Exception -> 0x0043 }
        goto L_0x0038;
    L_0x0043:
        r2 = move-exception;
    L_0x0044:
        r2.printStackTrace();	 Catch:{ all -> 0x006f }
        if (r3 == 0) goto L_0x004c;
    L_0x0049:
        r3.close();	 Catch:{ IOException -> 0x01e7 }
    L_0x004c:
        r2 = -1;
        goto L_0x0007;
    L_0x004e:
        r0 = r21;
        r5 = r0.b;	 Catch:{ Exception -> 0x0043 }
        if (r5 == 0) goto L_0x0069;
    L_0x0054:
        r0 = r21;
        r5 = r0.b;	 Catch:{ Exception -> 0x0043 }
        r5 = r5.F();	 Catch:{ Exception -> 0x0043 }
        r6 = 2;
        if (r5 != r6) goto L_0x0069;
    L_0x005f:
        r0 = r21;
        r0.c(r2);	 Catch:{ Exception -> 0x0043 }
    L_0x0064:
        r21.d(r22);	 Catch:{ Exception -> 0x0043 }
        r2 = r3;
        goto L_0x001b;
    L_0x0069:
        r0 = r21;
        r0.d(r2);	 Catch:{ Exception -> 0x0043 }
        goto L_0x0064;
    L_0x006f:
        r2 = move-exception;
    L_0x0070:
        if (r3 == 0) goto L_0x0075;
    L_0x0072:
        r3.close();	 Catch:{ IOException -> 0x01ed }
    L_0x0075:
        throw r2;
    L_0x0076:
        r0 = r21;
        r5 = r0.b;	 Catch:{ Exception -> 0x0043 }
        if (r5 == 0) goto L_0x0187;
    L_0x007c:
        r0 = r21;
        r5 = r0.b;	 Catch:{ Exception -> 0x0043 }
        r5 = r5.F();	 Catch:{ Exception -> 0x0043 }
        r6 = 2;
        if (r5 != r6) goto L_0x0187;
    L_0x0087:
        r0 = r21;
        r0.c(r2);	 Catch:{ Exception -> 0x0043 }
    L_0x008c:
        r21.d(r22);	 Catch:{ Exception -> 0x0043 }
        r5 = "updateChapters";
        r6 = r2.optJSONArray(r5);	 Catch:{ Exception -> 0x0043 }
        r7 = r6.length();	 Catch:{ Exception -> 0x0043 }
        if (r7 <= 0) goto L_0x001a;
    L_0x009c:
        r8 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0043 }
        r8.<init>(r7);	 Catch:{ Exception -> 0x0043 }
        r9 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0043 }
        r9.<init>(r7);	 Catch:{ Exception -> 0x0043 }
        r2 = 0;
        r5 = r2;
    L_0x00a8:
        if (r5 >= r7) goto L_0x018e;
    L_0x00aa:
        r2 = r6.get(r5);	 Catch:{ Exception -> 0x0043 }
        r2 = (org.json.JSONObject) r2;	 Catch:{ Exception -> 0x0043 }
        r0 = r21;
        r2 = r0.e(r2);	 Catch:{ Exception -> 0x0043 }
        r9.add(r2);	 Catch:{ Exception -> 0x0043 }
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0043 }
        r10.<init>();	 Catch:{ Exception -> 0x0043 }
        r11 = "";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x0043 }
        r11 = r2.getChapterId();	 Catch:{ Exception -> 0x0043 }
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x0043 }
        r10 = r10.toString();	 Catch:{ Exception -> 0x0043 }
        r11 = r2.getChapterName();	 Catch:{ Exception -> 0x0043 }
        r12 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0043 }
        r12.<init>();	 Catch:{ Exception -> 0x0043 }
        r13 = "";
        r12 = r12.append(r13);	 Catch:{ Exception -> 0x0043 }
        r14 = r2.getWords();	 Catch:{ Exception -> 0x0043 }
        r12 = r12.append(r14);	 Catch:{ Exception -> 0x0043 }
        r12 = r12.toString();	 Catch:{ Exception -> 0x0043 }
        r13 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0043 }
        r13.<init>();	 Catch:{ Exception -> 0x0043 }
        r14 = "";
        r13 = r13.append(r14);	 Catch:{ Exception -> 0x0043 }
        r14 = r2.getWprice();	 Catch:{ Exception -> 0x0043 }
        r13 = r13.append(r14);	 Catch:{ Exception -> 0x0043 }
        r13 = r13.toString();	 Catch:{ Exception -> 0x0043 }
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0043 }
        r14.<init>();	 Catch:{ Exception -> 0x0043 }
        r15 = "";
        r14 = r14.append(r15);	 Catch:{ Exception -> 0x0043 }
        r16 = r2.getFeeWords();	 Catch:{ Exception -> 0x0043 }
        r0 = r16;
        r14 = r14.append(r0);	 Catch:{ Exception -> 0x0043 }
        r14 = r14.toString();	 Catch:{ Exception -> 0x0043 }
        r15 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0043 }
        r15.<init>();	 Catch:{ Exception -> 0x0043 }
        r16 = "";
        r15 = r15.append(r16);	 Catch:{ Exception -> 0x0043 }
        r16 = r2.getPrice();	 Catch:{ Exception -> 0x0043 }
        r17 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r16 = r16 * r17;
        r0 = r16;
        r0 = (int) r0;	 Catch:{ Exception -> 0x0043 }
        r16 = r0;
        r15 = r15.append(r16);	 Catch:{ Exception -> 0x0043 }
        r15 = r15.toString();	 Catch:{ Exception -> 0x0043 }
        r16 = r2.getChapterMD5();	 Catch:{ Exception -> 0x0043 }
        r17 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0043 }
        r17.<init>();	 Catch:{ Exception -> 0x0043 }
        r18 = "";
        r17 = r17.append(r18);	 Catch:{ Exception -> 0x0043 }
        r18 = r2.getUUID();	 Catch:{ Exception -> 0x0043 }
        r2 = r17.append(r18);	 Catch:{ Exception -> 0x0043 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0043 }
        r17 = 8;
        r0 = r17;
        r0 = new java.lang.String[r0];	 Catch:{ Exception -> 0x0043 }
        r17 = r0;
        r18 = 0;
        r17[r18] = r10;	 Catch:{ Exception -> 0x0043 }
        r10 = 1;
        r17[r10] = r11;	 Catch:{ Exception -> 0x0043 }
        r10 = 2;
        r17[r10] = r12;	 Catch:{ Exception -> 0x0043 }
        r10 = 3;
        r17[r10] = r13;	 Catch:{ Exception -> 0x0043 }
        r10 = 4;
        r17[r10] = r14;	 Catch:{ Exception -> 0x0043 }
        r10 = 5;
        r17[r10] = r15;	 Catch:{ Exception -> 0x0043 }
        r10 = 6;
        r17[r10] = r16;	 Catch:{ Exception -> 0x0043 }
        r10 = 7;
        r17[r10] = r2;	 Catch:{ Exception -> 0x0043 }
        r0 = r17;
        r8.add(r0);	 Catch:{ Exception -> 0x0043 }
        r2 = r5 + 1;
        r5 = r2;
        goto L_0x00a8;
    L_0x0187:
        r0 = r21;
        r0.d(r2);	 Catch:{ Exception -> 0x0043 }
        goto L_0x008c;
    L_0x018e:
        r2 = new java.io.File;	 Catch:{ Exception -> 0x0043 }
        r0 = r21;
        r5 = r0.b;	 Catch:{ Exception -> 0x0043 }
        r5 = r5.d();	 Catch:{ Exception -> 0x0043 }
        r2.<init>(r5);	 Catch:{ Exception -> 0x0043 }
        r5 = r2.exists();	 Catch:{ Exception -> 0x0043 }
        if (r5 == 0) goto L_0x0203;
    L_0x01a1:
        r6 = new java.io.FileWriter;	 Catch:{ Exception -> 0x0043 }
        r5 = 1;
        r6.<init>(r2, r5);	 Catch:{ Exception -> 0x0043 }
        r5 = new com.qq.reader.common.utils.b.c;	 Catch:{ Exception -> 0x0043 }
        r2 = new java.io.BufferedWriter;	 Catch:{ Exception -> 0x0043 }
        r7 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2.<init>(r6, r7);	 Catch:{ Exception -> 0x0043 }
        r6 = 44;
        r7 = 0;
        r10 = 0;
        r5.<init>(r2, r6, r7, r10);	 Catch:{ Exception -> 0x0043 }
        r5.a(r8);	 Catch:{ Exception -> 0x01ff, all -> 0x01f3 }
        r2 = r5;
    L_0x01bb:
        r0 = r21;
        r3 = r0.a;	 Catch:{ Exception -> 0x01d9, all -> 0x01f7 }
        r3.c(r9);	 Catch:{ Exception -> 0x01d9, all -> 0x01f7 }
        r0 = r21;
        r3 = r0.a;	 Catch:{ Exception -> 0x01d9, all -> 0x01f7 }
        r3 = r3.b();	 Catch:{ Exception -> 0x01d9, all -> 0x01f7 }
        r0 = r21;
        r5 = r0.a;	 Catch:{ Exception -> 0x01d9, all -> 0x01f7 }
        r5 = r5.f();	 Catch:{ Exception -> 0x01d9, all -> 0x01f7 }
        r0 = r21;
        r0.a(r3, r5);	 Catch:{ Exception -> 0x01d9, all -> 0x01f7 }
        goto L_0x001b;
    L_0x01d9:
        r3 = move-exception;
        r20 = r3;
        r3 = r2;
        r2 = r20;
        goto L_0x0044;
    L_0x01e1:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0020;
    L_0x01e7:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x004c;
    L_0x01ed:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0075;
    L_0x01f3:
        r2 = move-exception;
        r3 = r5;
        goto L_0x0070;
    L_0x01f7:
        r3 = move-exception;
        r20 = r3;
        r3 = r2;
        r2 = r20;
        goto L_0x0070;
    L_0x01ff:
        r2 = move-exception;
        r3 = r5;
        goto L_0x0044;
    L_0x0203:
        r2 = r3;
        goto L_0x01bb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.bookchapter.online.f.c(java.lang.String):int");
    }

    private OnlineChapter e(JSONObject jSONObject) throws JSONException {
        if (this.b.F() == 2) {
            return f(jSONObject);
        }
        return g(jSONObject);
    }

    private OnlineChapter f(JSONObject jSONObject) throws JSONException {
        OnlineChapter musicChapter = new MusicChapter();
        musicChapter.setChapterId(jSONObject.getInt("seq"));
        musicChapter.setChapterName(jSONObject.getString("title"));
        int optInt = jSONObject.optInt("price", 0);
        musicChapter.setWprice(optInt);
        float f = (float) optInt;
        float parseFloat = Float.parseFloat(jSONObject.optString("fprice", "0"));
        if (parseFloat > 0.0f) {
            f = parseFloat / 100.0f;
        }
        if (this.a.h() == 1) {
            f = 0.0f;
        }
        musicChapter.setPrice(f);
        musicChapter.setSize((float) jSONObject.optDouble("size"));
        musicChapter.setDuration(jSONObject.optLong("duration"));
        musicChapter.setUUID(jSONObject.optLong("uuid", 0));
        musicChapter.setChapterMD5(jSONObject.optString("cmd5", "0"));
        musicChapter.setBookId(Long.parseLong(this.b.k()));
        musicChapter.setBookName(this.b.b());
        return musicChapter;
    }

    private OnlineChapter g(JSONObject jSONObject) throws JSONException {
        OnlineChapter onlineChapter = new OnlineChapter();
        onlineChapter.setChapterId(jSONObject.getInt("seq"));
        onlineChapter.setChapterName(jSONObject.getString("title"));
        int optInt = jSONObject.optInt("price", 0);
        onlineChapter.setWprice(optInt);
        float f = (float) optInt;
        float parseFloat = Float.parseFloat(jSONObject.optString("fprice", "0"));
        if (parseFloat > 0.0f) {
            f = parseFloat / 100.0f;
        }
        onlineChapter.setPrice(f);
        onlineChapter.setWords(jSONObject.getLong("words"));
        onlineChapter.setFeeWords((long) jSONObject.optInt("feewords", 0));
        onlineChapter.setChapterMD5(jSONObject.optString("cmd5", "0"));
        onlineChapter.setBookId(Long.parseLong(this.b.k()));
        onlineChapter.setUUID(jSONObject.optLong("uuid", 0));
        onlineChapter.setBookName(this.b.b());
        return onlineChapter;
    }

    private void a(List<OnlineChapter> list, int i) {
        List arrayList = new ArrayList();
        j jVar;
        int i2;
        if (list.size() <= i) {
            jVar = new j();
            for (i2 = 0; i2 < list.size(); i2++) {
                jVar.a((OnlineChapter) list.get(i2));
            }
            arrayList.add(jVar);
        } else {
            jVar = new j();
            int i3 = 0;
            for (i2 = 0; i2 < list.size(); i2++) {
                if (i3 < i) {
                    jVar.a((OnlineChapter) list.get(i2));
                    i3++;
                } else {
                    if (jVar.a() != 0) {
                        arrayList.add(jVar);
                        jVar = new j();
                    }
                    jVar.a((OnlineChapter) list.get(i2));
                    i3 = 1;
                }
            }
            if (jVar.a() != 0) {
                arrayList.add(jVar);
            }
        }
        this.a.a(arrayList);
    }

    public void d(String str) {
        IOException e;
        Throwable th;
        RandomAccessFile randomAccessFile = null;
        synchronized (f.class) {
            if (!(this.b == null || i() == -1)) {
                File file = new File(this.b.e());
                if (file.exists() && file.length() > 0) {
                    file.delete();
                }
                try {
                    if (ab.a(file.getParentFile())) {
                        file.createNewFile();
                    }
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                    try {
                        randomAccessFile2.write(str.getBytes());
                        randomAccessFile2.close();
                        RandomAccessFile randomAccessFile3 = null;
                        if (null != null) {
                            try {
                                randomAccessFile3.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        randomAccessFile = randomAccessFile2;
                        try {
                            e2.printStackTrace();
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        randomAccessFile = randomAccessFile2;
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e22 = e5;
                    e22.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                }
            }
        }
    }

    public List<OnlineChapter> e() {
        return this.a.b();
    }

    public List<j> f() {
        return this.a.a();
    }

    private boolean z() {
        String d = this.b.d();
        String e = this.b.e();
        File file = new File(d);
        File file2 = new File(e);
        if (!file.exists() || !file2.exists() || file.length() <= 0 || file2.length() <= 0) {
            return false;
        }
        return true;
    }

    public String g() {
        return this.b.k();
    }

    public List<Object> h() {
        return this.a.m();
    }

    public long i() {
        return this.a.g();
    }

    public int j() {
        return this.a.h();
    }

    public int k() {
        return this.a.f();
    }

    public int l() {
        return this.a.y();
    }

    public long m() {
        return this.a.i();
    }

    public boolean n() {
        return this.a.v();
    }

    public String o() {
        return this.a.u();
    }

    public int p() {
        return this.a.z();
    }

    public int q() {
        return this.a.A();
    }

    public String r() {
        return this.a.B();
    }

    public int s() {
        int x = this.a.x();
        if (x != 0 || t() || u()) {
            return x;
        }
        return 100;
    }

    public boolean t() {
        if (this.a.x() != 0) {
            return false;
        }
        if (System.currentTimeMillis() < ao.h(this.a.n()).longValue()) {
            return true;
        }
        return false;
    }

    public boolean u() {
        if (this.a.t() > System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    public int v() {
        return this.a.K();
    }

    public String w() {
        return this.a.L();
    }

    public String x() {
        return this.a.n();
    }

    public d y() {
        return this.a;
    }
}
