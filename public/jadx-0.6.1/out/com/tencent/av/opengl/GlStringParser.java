package com.tencent.av.opengl;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class GlStringParser {
    private Map<String, String> mMap;
    private char mend;
    private char mkey;

    public GlStringParser() {
        this.mkey = '\r';
        this.mend = '\t';
        this.mkey = '\r';
        this.mend = '\t';
        this.mMap = new HashMap();
    }

    public GlStringParser(char c, char c2) {
        this.mkey = '\r';
        this.mend = '\t';
        this.mkey = c;
        this.mend = c2;
        this.mMap = new HashMap();
    }

    public Map<String, String> getMap() {
        return this.mMap;
    }

    public GlStringParser(String str) {
        this.mkey = '\r';
        this.mend = '\t';
        this.mMap = new HashMap();
        unflatten(str);
    }

    public GlStringParser(Map<String, String> map) {
        this.mkey = '\r';
        this.mend = '\t';
        this.mMap = map;
    }

    public String flatten() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.mMap.keySet()) {
            stringBuilder.append(str);
            stringBuilder.append(this.mkey);
            stringBuilder.append((String) this.mMap.get(str));
            stringBuilder.append(this.mend);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public void unflatten(String str) {
        if (str != null) {
            this.mMap.clear();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mend);
            StringTokenizer stringTokenizer = new StringTokenizer(str, stringBuilder.toString());
            while (stringTokenizer.hasMoreElements()) {
                String nextToken = stringTokenizer.nextToken();
                int indexOf = nextToken.indexOf(this.mkey);
                if (indexOf != -1) {
                    this.mMap.put(nextToken.substring(0, indexOf), nextToken.substring(indexOf + 1));
                }
            }
        }
    }

    public void remove(String str) {
        this.mMap.remove(str);
    }

    public void set(String str, String str2) {
        if (str != null && str.indexOf(this.mend) == -1 && str.indexOf(this.mkey) == -1 && str2 != null && str2.indexOf(this.mkey) == -1 && str2.indexOf(this.mend) == -1) {
            this.mMap.put(str, str2);
        }
    }

    public void set(String str, int i) {
        this.mMap.put(str, Integer.toString(i));
    }

    public String get(String str) {
        return (String) this.mMap.get(str);
    }

    public int getInt(String str) {
        String str2 = (String) this.mMap.get(str);
        if (str2 != null) {
            return Integer.parseInt(str2);
        }
        return 0;
    }

    public Boolean getBoolean(String str) {
        String str2 = (String) this.mMap.get(str);
        if (str2 == null || (!str2.equals("true") && !str2.equals("false"))) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(str2);
    }
}
