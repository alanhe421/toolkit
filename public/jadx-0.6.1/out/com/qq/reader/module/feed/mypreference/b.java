package com.qq.reader.module.feed.mypreference;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PreferenceItems */
public class b {
    public static List<a> a = null;
    private static boolean g = false;
    public List<a> b = new ArrayList();
    public List<a> c = new ArrayList();
    public List<a> d = new ArrayList();
    public List<a> e = new ArrayList();
    public LinkedHashMap<Integer, Integer> f = new LinkedHashMap();
    private boolean h;
    private int i;

    /* compiled from: PreferenceItems */
    public static class a {
        public String a;
        public long b;
        public int c;
        public String d;
        public boolean e = false;

        a(String str, long j, int i, String str2) {
            this.a = str;
            this.b = j;
            this.c = i;
            this.d = str2;
        }

        public String a() {
            return String.valueOf(this.b);
        }
    }

    public static void a(List<a> list, List<a> list2, List<a> list3, String str) {
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("tags");
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("name");
                    long optLong = optJSONObject.optLong("id");
                    int optInt = optJSONObject.optInt("sex");
                    a aVar = new a(optString, optLong, optInt, optJSONObject.optString(SocialConstants.PARAM_URL));
                    if (optInt == 1) {
                        list.add(aVar);
                    } else if (optInt == 2) {
                        list2.add(aVar);
                    } else if (optInt == 3) {
                        list3.add(aVar);
                    } else {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a() {
        try {
            String b = a.a().b();
            if (b == null) {
                a.a().a(null);
                return;
            }
            Collection arrayList = new ArrayList();
            Collection arrayList2 = new ArrayList();
            Collection arrayList3 = new ArrayList();
            a(arrayList, arrayList2, arrayList3, b);
            a = new ArrayList();
            a.addAll(arrayList);
            a.addAll(arrayList2);
            a.addAll(arrayList3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static a a(String str) {
        if (a == null || a.size() == 0) {
            a();
        }
        int i = 0;
        while (i < a.size()) {
            try {
                a aVar = (a) a.get(i);
                if (aVar.a().equals(str)) {
                    return aVar;
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String b(String str) {
        if (a == null || a.size() == 0) {
            a();
        }
        return a(str).d;
    }

    public b(Context context, List<Long> list, boolean z) {
        this.h = z;
        if (this.b == null || this.b.size() == 0) {
            g();
        } else if (this.i != d.aS(ReaderApplication.getApplicationImp())) {
            h();
        }
        a((List) list);
    }

    public void a(List<Long> list) {
        int i = 0;
        while (this.b != null && i < this.b.size()) {
            boolean z;
            if (a(list, ((a) this.b.get(i)).b)) {
                z = true;
            } else {
                z = false;
            }
            ((a) this.b.get(i)).e = z;
            i++;
        }
    }

    public a a(int i) {
        return (a) this.b.get(i);
    }

    public int b() {
        return this.b.size();
    }

    private boolean a(List<Long> list, long j) {
        if (list != null) {
            return list.contains(Long.valueOf(j));
        }
        return false;
    }

    public ArrayList<String> c() {
        ArrayList<String> arrayList = new ArrayList();
        int i = 0;
        while (this.b != null && i < this.b.size()) {
            if (((a) this.b.get(i)).e) {
                arrayList.add(((a) this.b.get(i)).a());
            }
            i++;
        }
        return arrayList;
    }

    public ArrayList<String> d() {
        return c();
    }

    public int e() {
        int i = 0;
        int i2 = 0;
        while (this.b != null && i < this.b.size()) {
            if (((a) this.b.get(i)).e) {
                i2++;
            }
            i++;
        }
        return i2;
    }

    private void g() {
        try {
            String b = a.a().b();
            if (b == null) {
                a.a().a(null);
                return;
            }
            a(this.c, this.d, this.e, b);
            h();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedHashMap<Integer, Integer> f() {
        return this.f;
    }

    private void h() {
        this.i = d.aS(ReaderApplication.getApplicationImp());
        this.f.clear();
        this.b = new ArrayList();
        if (this.i == 1) {
            this.f.put(Integer.valueOf(1), Integer.valueOf(this.c.size()));
            this.f.put(Integer.valueOf(3), Integer.valueOf(this.e.size()));
            if (!this.h) {
                this.f.put(Integer.valueOf(2), Integer.valueOf(this.d.size()));
            }
            this.b.addAll(this.c);
            this.b.addAll(this.e);
            if (!this.h) {
                this.b.addAll(this.d);
            }
        } else if (this.i == 2) {
            this.f.put(Integer.valueOf(2), Integer.valueOf(this.d.size()));
            this.f.put(Integer.valueOf(3), Integer.valueOf(this.e.size()));
            if (!this.h) {
                this.f.put(Integer.valueOf(1), Integer.valueOf(this.c.size()));
            }
            this.b.addAll(this.d);
            this.b.addAll(this.e);
            this.b.addAll(this.c);
        } else {
            this.f.put(Integer.valueOf(3), Integer.valueOf(this.e.size()));
            this.f.put(Integer.valueOf(1), Integer.valueOf(this.c.size()));
            this.f.put(Integer.valueOf(2), Integer.valueOf(this.d.size()));
            this.b.addAll(this.e);
            this.b.addAll(this.c);
            this.b.addAll(this.d);
        }
    }
}
