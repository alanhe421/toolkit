package com.qrcomic.f;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.qrcomic.util.g;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: TimeTraceUtil */
public class e {
    public String a = "TimeTraceUtil";
    public ConcurrentHashMap<String, String> b = new ConcurrentHashMap();
    private long c = SystemClock.elapsedRealtime();

    public void a(String str, boolean z) {
        a(str, "~~startTime", z);
    }

    public void a(String str, String str2, boolean z) {
        if (this.b != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.b.put(str, str2 + "," + String.valueOf(elapsedRealtime) + "," + (z ? "true" : "false"));
            long a = a(str2, elapsedRealtime);
            String format = String.format("TimerOutput: %s to %s cost=%d", new Object[]{str2, str, Long.valueOf(a)});
            if (g.a()) {
                g.b(this.a, g.d, format);
            } else {
                Log.i(this.a, format);
            }
        }
    }

    public HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        synchronized (hashMap) {
            if (this.b != null) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - this.c;
                for (Entry entry : this.b.entrySet()) {
                    String str = (String) entry.getKey();
                    String[] split = ((String) entry.getValue()).split(",");
                    if (split != null && split.length >= 3) {
                        String str2 = split[0];
                        Object obj = split[1];
                        if (!Boolean.parseBoolean(split[2])) {
                            if (TextUtils.isDigitsOnly(obj)) {
                                elapsedRealtime = a(str2, Long.parseLong(obj));
                            }
                        }
                    }
                    if (elapsedRealtime < 0) {
                        elapsedRealtime = 0;
                    }
                    hashMap.put(str, String.valueOf(elapsedRealtime));
                }
            }
        }
        return hashMap;
    }

    private long a(String str, long j) {
        if (!TextUtils.isEmpty(str) && this.b.containsKey(str)) {
            String str2 = (String) this.b.get(str);
            if (!TextUtils.isEmpty(str2)) {
                String[] split = str2.split(",");
                if (split.length >= 2 && TextUtils.isDigitsOnly(split[1])) {
                    return j - Long.parseLong(split[1]);
                }
            }
        } else if ("~~startTime".equals(str)) {
            return j - this.c;
        }
        return 0;
    }

    public String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        HashMap a = a();
        synchronized (a) {
            if (a != null) {
                str = "";
                str = "";
                for (Entry entry : a.entrySet()) {
                    String str2 = (String) entry.getKey();
                    str = (String) entry.getValue();
                    if (!(TextUtils.isEmpty(str2) || TextUtils.isEmpty(str))) {
                        stringBuilder.append(str2 + ":" + str + "ms,");
                    }
                }
            }
            str = stringBuilder.toString();
        }
        return str;
    }
}
