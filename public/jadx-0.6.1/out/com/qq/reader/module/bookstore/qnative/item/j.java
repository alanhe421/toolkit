package com.qq.reader.module.bookstore.qnative.item;

import android.text.TextUtils;
import com.qq.reader.common.utils.ao;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* compiled from: ChapterItem */
public class j extends s {
    private String a;
    private String b;
    private int c;
    private int d;
    private String e;
    private int f;

    public void parseData(JSONObject jSONObject) {
        this.f = jSONObject.optInt("chapshowtag", 0);
        if (this.f != 1 && this.f != 2) {
            this.a = jSONObject.optString("chapsize");
            this.b = jSONObject.optString("lastChapterUpdateTime");
            this.c = jSONObject.optInt("finished");
            this.d = jSONObject.optInt("contentType");
            this.e = jSONObject.optString("lastcname");
        }
    }

    public String a() {
        if (this.f == 2) {
            return "(暂不支持)";
        }
        if (this.f == 1) {
            return "本书版权提供方：京东";
        }
        Object a = a(this.e);
        if (this.c == 0) {
            if (this.d == 2) {
                return "连载至 " + this.e;
            }
            if (TextUtils.isEmpty(a)) {
                return "连载至" + this.a + "章";
            }
            return "连载至" + a + "章";
        } else if (this.c == 1) {
            return "完结共" + this.a + "章";
        } else {
            return "节选共" + this.a + "章";
        }
    }

    public String b() {
        if (this.f == 0 && !ao.s(this.b)) {
            return "更新于" + this.b;
        }
        return null;
    }

    public boolean c() {
        return this.c != 0;
    }

    public int d() {
        return this.f;
    }

    private String a(String str) {
        if (str == null) {
            return "";
        }
        try {
            Matcher matcher = Pattern.compile("^第\\d+章").matcher(str);
            if (matcher.find()) {
                String group = matcher.group();
                return group.substring(1, group.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
