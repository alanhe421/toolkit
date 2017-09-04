package com.qq.reader.common.utils;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: TitleBarTabInfoGenerator */
public class an {
    private static an a;

    /* compiled from: TitleBarTabInfoGenerator */
    private class a {
        final /* synthetic */ an a;
        private ArrayList<a> b;
        private ArrayList<b> c;

        /* compiled from: TitleBarTabInfoGenerator */
        public class a {
            public String a = "";
            public boolean b = false;
            public String c = "";
            public String d = "0";
            final /* synthetic */ a e;

            public a(a aVar) {
                this.e = aVar;
            }
        }

        /* compiled from: TitleBarTabInfoGenerator */
        public class b {
            public String a = "";
            public String b = "";
            public boolean c = false;
            final /* synthetic */ a d;

            public b(a aVar) {
                this.d = aVar;
            }
        }

        private a(an anVar) {
            this.a = anVar;
        }

        public a a(String str, String str2, String str3, boolean z) {
            a aVar = new a(this);
            aVar.c = str2;
            aVar.b = z;
            aVar.a = str;
            aVar.d = str3;
            if (this.b == null) {
                this.b = new ArrayList();
            }
            this.b.add(aVar);
            return this;
        }

        public a a(String str, String str2, boolean z) {
            b bVar = new b(this);
            bVar.c = z;
            bVar.a = str;
            bVar.b = str2;
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(bVar);
            return this;
        }

        public String a() {
            int i = 0;
            try {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                JSONArray jSONArray2 = new JSONArray();
                int i2 = 0;
                while (this.b != null && i2 < this.b.size()) {
                    JSONObject jSONObject2 = new JSONObject();
                    a aVar = (a) this.b.get(i2);
                    jSONObject2.put("isSelected", aVar.b);
                    jSONObject2.put("actionTag", aVar.d);
                    jSONObject2.put("title", aVar.a);
                    jSONObject2.put("actionId", aVar.c);
                    jSONArray.put(jSONObject2);
                    i2++;
                }
                jSONObject.put("actionIdList", jSONArray);
                while (this.c != null && i < this.c.size()) {
                    JSONObject jSONObject3 = new JSONObject();
                    b bVar = (b) this.c.get(i);
                    jSONObject3.put("isSelected", bVar.c);
                    jSONObject3.put("actionTag", bVar.b);
                    jSONObject3.put("title", bVar.a);
                    jSONArray2.put(jSONObject3);
                    i++;
                }
                jSONObject.put("actionTagList", jSONArray2);
                return jSONObject.toString();
            } catch (Exception e) {
                c.e("Error", e.getMessage());
                return null;
            }
        }
    }

    public static an a() {
        if (a == null) {
            a = new an();
        }
        return a;
    }

    public String a(int i) {
        return a(i, null);
    }

    public String a(int i, Bundle bundle) {
        switch (i) {
            case 1:
                return new a().a("男生专题", "1", "0", true).a("女生专题", "2", "0", false).a("出版专题", "0", "0", false).a("最新", "0", true).a("经典", "1", false).a();
            case 2:
                return new a().a("", "0", "", true).a("我领取的", "received", true).a("我发出的", "sent", false).a();
            case 3:
                return new a().a("", "0", "", true).a("周榜", "2", false).a("总榜", "1", false).a();
            case 4:
                return new a().a(ReaderApplication.getApplicationImp().getResources().getString(R.string.limit_time_discount_buy_title), "1", "0", true).a("08:00", "0", true).a("14:00", "1", false).a("20:00", "2", false).a("24:00", "3", false).a("08:00", "4", false).a();
            case 5:
                return new a().a("", "0", "", true).a("推荐", "editorrec", true).a("主编", "editorList", false).a();
            case 6:
                return new a().a("", "0", "", true).a("男生", "End_Book_Boy", true).a("女生", "End_Book_Girl", false).a();
            case 7:
                return new a().a("", "0", "", true).a("男生", "Limit_Free_Boy", true).a("出版", "Limit_Free_Publish", false).a("女生", "Limit_Free_Girl", false).a();
            case 8:
                return new a().a("", "0", "", true).a("男生", "Boutique_Zone_Boy", true).a("出版", "Boutique_Zone_Publish", false).a("女生", "Boutique_Zone_Girl", false).a();
            case 9:
                return new a().a("", "0", "", true).a("男生", "PayMonth_Boy", true).a("出版", "PayMonth_Publish", false).a("女生", "PayMonth_Girl", false).a();
            default:
                return "";
        }
    }
}
