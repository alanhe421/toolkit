package com.tencent.mm.sdk.b;

import com.tencent.mm.a.a;

public final class e {
    private final a r;
    private d<String, String> s;

    public final String b(String str) {
        try {
            if (str.startsWith("!")) {
                if (this.s.a(str)) {
                    return (String) this.s.get(str);
                }
                String substring = str.substring(1);
                try {
                    String[] split = substring.split("@");
                    if (split.length > 1) {
                        String str2 = split[0];
                        int intValue = Integer.valueOf(split[0]).intValue();
                        String substring2 = substring.substring(str2.length() + 1, (str2.length() + 1) + intValue);
                        String str3 = this.r.a(substring2) + substring.substring(intValue + (str2.length() + 1));
                        this.s.put(str, str3);
                        return str3;
                    }
                    str = substring;
                } catch (Exception e) {
                    str = substring;
                    Exception exception = e;
                    exception.printStackTrace();
                    str = "[td]" + str;
                    return str;
                }
            }
        } catch (Exception e2) {
            exception = e2;
            exception.printStackTrace();
            str = "[td]" + str;
            return str;
        }
        return str;
    }
}
