package com.qq.reader.module.bookstore.qnative.item;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RankInfoItem */
public class ad extends s {
    public ArrayList<b> a = new ArrayList();
    public b b;
    public a c;
    public a d;
    public a e;
    private boolean f;
    private boolean g;
    private boolean h;

    /* compiled from: RankInfoItem */
    public class a {
        final /* synthetic */ ad a;
        private int b;
        private int c;
        private int d;
        private int e;
        private int f;

        public a(ad adVar) {
            this.a = adVar;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }

        public int c() {
            return this.d;
        }

        public int d() {
            return this.e;
        }

        public int e() {
            return this.f;
        }
    }

    /* compiled from: RankInfoItem */
    public class b {
        public String a;
        public String b;
        public int c;
        public String d;
        public int e;
        public int f;
        final /* synthetic */ ad g;

        public b(ad adVar) {
            this.g = adVar;
        }

        public int a() {
            return this.e;
        }

        public int b() {
            return this.f;
        }
    }

    public void parseData(JSONObject jSONObject) {
        try {
            this.f = jSONObject.optBoolean("hasMticket");
            this.g = jSONObject.optBoolean("hasRecommend");
            this.h = jSONObject.optBoolean("hasReward");
            JSONArray optJSONArray = jSONObject.optJSONArray("fansRank");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                if (length > 0) {
                    if (this.a != null) {
                        this.a.clear();
                    }
                    for (int i = 0; i < length; i++) {
                        b bVar = new b(this);
                        bVar.a = optJSONArray.getJSONObject(i).optString(MessageKey.MSG_ICON);
                        bVar.b = optJSONArray.getJSONObject(i).optString("title");
                        bVar.c = optJSONArray.getJSONObject(i).optInt("num");
                        bVar.d = optJSONArray.getJSONObject(i).optString("nick");
                        bVar.f = optJSONArray.getJSONObject(i).optInt("uid");
                        bVar.e = optJSONArray.getJSONObject(i).optInt("titlepos");
                        this.a.add(bVar);
                    }
                }
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("me");
            if (optJSONObject != null) {
                this.b = new b(this);
                this.b.a = optJSONObject.optString(MessageKey.MSG_ICON);
                this.b.b = optJSONObject.optString("title");
                this.b.c = optJSONObject.optInt("num");
                this.b.d = optJSONObject.optString("nick");
                this.b.f = optJSONObject.optInt("uid");
                this.b.e = optJSONObject.optInt("titlepos");
            }
            if (c()) {
                optJSONObject = jSONObject.optJSONObject("rewardMap");
                this.c = new a(this);
                this.c.b = optJSONObject.optInt("frontnum");
                this.c.e = optJSONObject.optInt("lastpos");
                this.c.d = optJSONObject.optInt("nextnum");
                this.c.c = optJSONObject.optInt("num");
                this.c.f = optJSONObject.optInt("pos");
            }
            if (b()) {
                optJSONObject = jSONObject.optJSONObject("rTicketMap");
                this.d = new a(this);
                this.d.b = optJSONObject.optInt("frontnum");
                this.d.e = optJSONObject.optInt("lastpos");
                this.d.d = optJSONObject.optInt("nextnum");
                this.d.c = optJSONObject.optInt("num");
                this.d.f = optJSONObject.optInt("pos");
            }
            if (a()) {
                optJSONObject = jSONObject.optJSONObject("mTicketMap");
                this.e = new a(this);
                this.e.b = optJSONObject.optInt("frontnum");
                this.e.e = optJSONObject.optInt("lastpos");
                this.e.d = optJSONObject.optInt("nextnum");
                this.e.c = optJSONObject.optInt("num");
                this.e.f = optJSONObject.optInt("pos");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return this.f;
    }

    public boolean b() {
        return this.g;
    }

    public boolean c() {
        return this.h;
    }

    public int a(a aVar) {
        int e = aVar.e();
        int d = aVar.d();
        if (d == 0) {
            return R.drawable.rank_asend;
        }
        e -= d;
        if (e < 0) {
            return R.drawable.rank_asend;
        }
        if (e == 0) {
            return R.drawable.rank_nochange;
        }
        return e > 0 ? R.drawable.rank_descend : e;
    }

    public int b(a aVar) {
        if (aVar.e() == 1) {
            return aVar.b() - aVar.c();
        }
        return aVar.a() - aVar.b();
    }

    public int c(a aVar) {
        if (aVar.e() == 1) {
            return 100;
        }
        if (aVar.a() == 0) {
            return 0;
        }
        return (aVar.b() * 100) / aVar.a();
    }

    public static int a(b bVar) {
        if (bVar.a() >= -1 && bVar.a() <= 1) {
            return R.drawable.localstore_endpage_fanslevel_level01_bg;
        }
        if (bVar.a() == 2 || bVar.a() == 3) {
            return R.drawable.localstore_endpage_fanslevel_level23_bg;
        }
        if (bVar.a() >= 4 && bVar.a() <= 9) {
            return R.drawable.localstore_endpage_fanslevel_level49_bg;
        }
        if (bVar.a() == 10) {
            return R.drawable.localstore_endpage_fanslevel_level10_bg;
        }
        return R.drawable.localstore_endpage_fanslevel_level01_bg;
    }

    public boolean d() {
        int size = this.a.size();
        int b = this.b.b();
        for (int i = 0; i < size; i++) {
            if (((b) this.a.get(i)).b() == b) {
                return true;
            }
        }
        return false;
    }
}
