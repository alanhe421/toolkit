package format.epub.common.c.a;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import format.epub.common.text.model.c;
import format.epub.common.text.model.n;
import format.epub.common.utils.o;
import format.epub.options.ZLBoolean3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/* compiled from: StyleSheetTable */
public class g {
    private static final Pattern e = Pattern.compile("(#([a-f]|[A-F]|[0-9]){3}(([a-f]|[A-F]|[0-9]){3})?\\b|(rgb\\([0-9\\s]+,[0-9\\s]+,[0-9\\s]+\\))|(rgba\\([0-9\\s]+,[0-9\\s]+,[0-9\\s]+,[0-9.\\s]+\\)))");
    private static String f;
    public Map<String, String> a = new HashMap();
    private Map<b, n> b = new HashMap();
    private Map<b, Boolean> c = new HashMap();
    private Map<b, Boolean> d = new HashMap();

    /* compiled from: StyleSheetTable */
    public static class a {
        public byte a = c.a;
        public short b = (short) 0;
        public byte c = (byte) 0;
        public short d;
        public byte e = (byte) 2;
        public String f = "#ff000000";

        public boolean a() {
            if ((this.b <= (short) 0 || this.a == c.a) && this.d == (short) 0) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: StyleSheetTable */
    public static class b {
        public short a;
        public byte b = (byte) 0;
        public short c;
        public byte d = (byte) 0;
        public short e;
        public byte f = (byte) 0;
        public String g = "";
        public int h;
        public int i;
        public int j;
        public int k;
    }

    public void a(b bVar, Map<String, String> map) {
        if (bVar != null && !map.isEmpty()) {
            Log.e("StyleSheetTable", bVar.toString());
            Log.e("StyleSheetTable", map.toString());
            Log.e("StyleSheetTable", "---------------------------------------");
            this.b.put(bVar, a((Map) map, (n) this.b.get(bVar)));
            String a = a((Map) map, "page-break-before");
            if ("always".equals(a) || "left".equals(a) || "right".equals(a)) {
                this.c.put(bVar, Boolean.valueOf(true));
            } else if ("avoid".equals(a)) {
                this.c.put(bVar, Boolean.valueOf(false));
            }
            a = a((Map) map, "page-break-after");
            if ("always".equals(a) || "left".equals(a) || "right".equals(a)) {
                this.d.put(bVar, Boolean.valueOf(true));
            } else if ("avoid".equals(a)) {
                this.d.put(bVar, Boolean.valueOf(false));
            }
        }
    }

    public static boolean a(String str, short[] sArr, byte[] bArr) {
        try {
            if ("0".equals(str)) {
                bArr[0] = (byte) 0;
                sArr[0] = (short) 0;
                return true;
            } else if (o.a(str, "%")) {
                bArr[0] = (byte) 5;
                sArr[0] = (short) ((int) Double.parseDouble(str.substring(0, str.length() - 1)));
                return true;
            } else if (o.a(str, "rem")) {
                bArr[0] = (byte) 3;
                sArr[0] = (short) ((int) (Double.parseDouble(str.substring(0, str.length() - 3)) * 100.0d));
                return true;
            } else if (o.a(str, "em")) {
                bArr[0] = (byte) 2;
                sArr[0] = (short) ((int) (Double.parseDouble(str.substring(0, str.length() - 2)) * 100.0d));
                return true;
            } else if (o.a(str, "ex")) {
                bArr[0] = (byte) 4;
                sArr[0] = (short) ((int) (Double.parseDouble(str.substring(0, str.length() - 2)) * 100.0d));
                return true;
            } else if (o.a(str, "px")) {
                bArr[0] = (byte) 0;
                sArr[0] = (short) ((int) Math.round(Double.parseDouble(str.substring(0, str.length() - 2))));
                return true;
            } else if (!o.a(str, "pt")) {
                return false;
            } else {
                bArr[0] = (byte) 1;
                sArr[0] = (short) ((int) Math.round(Double.parseDouble(str.substring(0, str.length() - 2))));
                return true;
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("StyleSheetTable", e.getMessage());
            sArr[0] = (short) 0;
            return true;
        }
    }

    public static boolean a(n nVar, int i, String str) {
        short[] sArr = new short[]{(short) 0};
        byte[] bArr = new byte[]{(byte) 0};
        if (!a(str, sArr, bArr)) {
            return false;
        }
        nVar.a(i, sArr[0], bArr[0]);
        return true;
    }

    private static void a(n nVar, int i, Map map, String str) {
        String a = a(map, str);
        if (a != null && a.length() > 0) {
            a(nVar, i, a);
        }
    }

    public ZLBoolean3 a(String str, String str2) {
        Boolean bool = (Boolean) this.c.get(new b(str, str2));
        if (bool != null) {
            return ZLBoolean3.b3Value(bool.booleanValue());
        }
        bool = (Boolean) this.c.get(new b("", str2));
        if (bool != null) {
            return ZLBoolean3.b3Value(bool.booleanValue());
        }
        bool = (Boolean) this.c.get(new b(str, ""));
        if (bool != null) {
            return ZLBoolean3.b3Value(bool.booleanValue());
        }
        return ZLBoolean3.B3_UNDEFINED;
    }

    public ZLBoolean3 b(String str, String str2) {
        Boolean bool = (Boolean) this.d.get(new b(str, str2));
        if (bool != null) {
            return ZLBoolean3.b3Value(bool.booleanValue());
        }
        bool = (Boolean) this.d.get(new b("", str2));
        if (bool != null) {
            return ZLBoolean3.b3Value(bool.booleanValue());
        }
        bool = (Boolean) this.d.get(new b(str, ""));
        if (bool != null) {
            return ZLBoolean3.b3Value(bool.booleanValue());
        }
        return ZLBoolean3.B3_UNDEFINED;
    }

    public n c(String str, String str2) {
        return (n) this.b.get(new b(str, str2));
    }

    public List<Entry<b, n>> d(String str, String str2) {
        b bVar = new b(str, str2);
        List<Entry<b, n>> arrayList = new ArrayList();
        for (Entry entry : this.b.entrySet()) {
            if (bVar.equals(entry.getKey())) {
                arrayList.add(entry);
            }
        }
        return arrayList;
    }

    public static String a(Map map, String str) {
        String str2 = (String) map.get(str);
        if (str2 == null) {
            str2 = "";
        }
        return str2.trim();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static format.epub.common.text.model.n a(java.util.Map r10, format.epub.common.text.model.n r11) {
        /*
        r9 = 4;
        r8 = 3;
        r7 = 2;
        r1 = 1;
        r2 = 0;
        if (r11 != 0) goto L_0x0010;
    L_0x0007:
        r11 = new format.epub.common.text.model.n;
        r11.<init>(r2);
        r0 = 6;
        r11.e(r0);
    L_0x0010:
        r0 = "text-align";
        r0 = a(r10, r0);
        r3 = "justify";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x0106;
    L_0x0020:
        r11.c(r9);
    L_0x0023:
        r0 = "text-decoration";
        r0 = a(r10, r0);
        r3 = "underline";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x0130;
    L_0x0033:
        r11.a(r9, r1);
    L_0x0036:
        r0 = "text-shadow";
        r0 = a(r10, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x004c;
    L_0x0043:
        r0 = b(r0);
        if (r0 == 0) goto L_0x004c;
    L_0x0049:
        r11.a(r0);
    L_0x004c:
        r0 = "font-weight";
        r3 = a(r10, r0);
        if (r3 == 0) goto L_0x0072;
    L_0x0055:
        r0 = r3.length();
        if (r0 <= 0) goto L_0x0072;
    L_0x005b:
        r0 = -1;
        r4 = "bold";
        r4 = r3.equals(r4);
        if (r4 == 0) goto L_0x0153;
    L_0x0065:
        r0 = 700; // 0x2bc float:9.81E-43 double:3.46E-321;
    L_0x0067:
        r3 = -1;
        if (r0 == r3) goto L_0x0072;
    L_0x006a:
        r3 = 600; // 0x258 float:8.41E-43 double:2.964E-321;
        if (r0 < r3) goto L_0x0179;
    L_0x006e:
        r0 = r1;
    L_0x006f:
        r11.a(r1, r0);
    L_0x0072:
        r0 = "font-style";
        r0 = a(r10, r0);
        if (r0 == 0) goto L_0x0097;
    L_0x007b:
        r3 = r0.length();
        if (r3 <= 0) goto L_0x0097;
    L_0x0081:
        r3 = "italic";
        r3 = r0.equals(r3);
        if (r3 != 0) goto L_0x0093;
    L_0x008a:
        r3 = "oblique";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x017c;
    L_0x0093:
        r0 = r1;
    L_0x0094:
        r11.a(r7, r0);
    L_0x0097:
        r0 = "font-variant";
        r0 = a(r10, r0);
        if (r0 == 0) goto L_0x00b2;
    L_0x00a0:
        r3 = r0.length();
        if (r3 <= 0) goto L_0x00b2;
    L_0x00a6:
        r3 = 16;
        r4 = "small-caps";
        r0 = r0.equals(r4);
        r11.a(r3, r0);
    L_0x00b2:
        r0 = "font-family";
        r0 = a(r10, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x018b;
    L_0x00bf:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r3 = ",";
        r5 = r0.split(r3);
        r0 = r5.length;
        if (r0 < r7) goto L_0x017f;
    L_0x00ce:
        r3 = r2;
    L_0x00cf:
        r0 = r5.length;
        if (r3 >= r0) goto L_0x0184;
    L_0x00d2:
        r0 = r5[r3];
        r6 = "\"";
        r6 = r0.startsWith(r6);
        if (r6 == 0) goto L_0x00e1;
    L_0x00dd:
        r0 = r0.substring(r1);
    L_0x00e1:
        r6 = "\"";
        r6 = r0.endsWith(r6);
        if (r6 == 0) goto L_0x00f4;
    L_0x00ea:
        r6 = r0.length();
        r6 = r6 + -1;
        r0 = r0.substring(r2, r6);
    L_0x00f4:
        r4.append(r0);
        r0 = r5.length;
        r0 = r0 + -1;
        if (r3 >= r0) goto L_0x0102;
    L_0x00fc:
        r0 = ",";
        r4.append(r0);
    L_0x0102:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x00cf;
    L_0x0106:
        r3 = "left";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x0114;
    L_0x010f:
        r11.c(r1);
        goto L_0x0023;
    L_0x0114:
        r3 = "right";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x0122;
    L_0x011d:
        r11.c(r7);
        goto L_0x0023;
    L_0x0122:
        r3 = "center";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x0023;
    L_0x012b:
        r11.c(r8);
        goto L_0x0023;
    L_0x0130:
        r3 = "line-through";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x0140;
    L_0x0139:
        r0 = 8;
        r11.a(r0, r1);
        goto L_0x0036;
    L_0x0140:
        r3 = "none";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x0036;
    L_0x0149:
        r11.a(r9, r2);
        r0 = 8;
        r11.a(r0, r2);
        goto L_0x0036;
    L_0x0153:
        r4 = "normal";
        r4 = r3.equals(r4);
        if (r4 == 0) goto L_0x0160;
    L_0x015c:
        r0 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        goto L_0x0067;
    L_0x0160:
        r4 = "bolder";
        r4 = r3.equals(r4);
        if (r4 != 0) goto L_0x0067;
    L_0x0169:
        r4 = "lighter";
        r4 = r3.equals(r4);
        if (r4 != 0) goto L_0x0067;
    L_0x0172:
        r0 = -1;
        r0 = format.epub.common.utils.o.a(r3, r0);
        goto L_0x0067;
    L_0x0179:
        r0 = r2;
        goto L_0x006f;
    L_0x017c:
        r0 = r2;
        goto L_0x0094;
    L_0x017f:
        r0 = r5[r2];
        r4.append(r0);
    L_0x0184:
        r0 = r4.toString();
        r11.c(r0);
    L_0x018b:
        r0 = "color";
        r0 = a(r10, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x019b;
    L_0x0198:
        r11.d(r0);
    L_0x019b:
        r0 = "font-size";
        r0 = a(r10, r0);
        if (r0 == 0) goto L_0x01cc;
    L_0x01a4:
        r3 = r0.length();
        if (r3 <= 0) goto L_0x01cc;
    L_0x01aa:
        r3 = 100;
        r4 = 5;
        r5 = new short[r1];
        r5[r2] = r3;
        r3 = new byte[r4];
        r4 = "xx-small";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0233;
    L_0x01bc:
        r0 = 58;
        r5[r2] = r0;
        r0 = r1;
    L_0x01c1:
        if (r0 == 0) goto L_0x01cc;
    L_0x01c3:
        r0 = 9;
        r4 = r5[r2];
        r3 = r3[r2];
        r11.a(r0, r4, r3);
    L_0x01cc:
        r0 = r10.keySet();
        r3 = r0.iterator();
    L_0x01d4:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x03dd;
    L_0x01da:
        r0 = r3.next();
        r0 = (java.lang.String) r0;
        r4 = "margin";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x02e5;
    L_0x01e9:
        r0 = "margin";
        r0 = a(r10, r0);
        if (r0 == 0) goto L_0x01d4;
    L_0x01f2:
        r4 = r0.length();
        if (r4 <= 0) goto L_0x01d4;
    L_0x01f8:
        r4 = " ";
        r4 = format.epub.common.utils.o.a(r0, r4, r1);
        r0 = r4.size();
        if (r0 <= 0) goto L_0x020c;
    L_0x0205:
        r0 = r4.size();
        switch(r0) {
            case 1: goto L_0x02ce;
            case 2: goto L_0x02d5;
            case 3: goto L_0x02dc;
            default: goto L_0x020c;
        };
    L_0x020c:
        r5 = 5;
        r0 = r4.get(r2);
        r0 = (java.lang.String) r0;
        a(r11, r5, r0);
        r0 = r4.get(r1);
        r0 = (java.lang.String) r0;
        a(r11, r8, r0);
        r5 = 6;
        r0 = r4.get(r7);
        r0 = (java.lang.String) r0;
        a(r11, r5, r0);
        r0 = r4.get(r8);
        r0 = (java.lang.String) r0;
        a(r11, r7, r0);
        goto L_0x01d4;
    L_0x0233:
        r4 = "x-small";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0242;
    L_0x023c:
        r0 = 69;
        r5[r2] = r0;
        r0 = r1;
        goto L_0x01c1;
    L_0x0242:
        r4 = "small";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0252;
    L_0x024b:
        r0 = 83;
        r5[r2] = r0;
        r0 = r1;
        goto L_0x01c1;
    L_0x0252:
        r4 = "medium";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0262;
    L_0x025b:
        r0 = 100;
        r5[r2] = r0;
        r0 = r1;
        goto L_0x01c1;
    L_0x0262:
        r4 = "large";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0272;
    L_0x026b:
        r0 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r5[r2] = r0;
        r0 = r1;
        goto L_0x01c1;
    L_0x0272:
        r4 = "x-large";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0282;
    L_0x027b:
        r0 = 144; // 0x90 float:2.02E-43 double:7.1E-322;
        r5[r2] = r0;
        r0 = r1;
        goto L_0x01c1;
    L_0x0282:
        r4 = "xx-large";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0292;
    L_0x028b:
        r0 = 173; // 0xad float:2.42E-43 double:8.55E-322;
        r5[r2] = r0;
        r0 = r1;
        goto L_0x01c1;
    L_0x0292:
        r4 = "inherit";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x02a3;
    L_0x029b:
        r0 = 32;
        r11.a(r0, r1);
        r0 = r2;
        goto L_0x01c1;
    L_0x02a3:
        r4 = "smaller";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x02b4;
    L_0x02ac:
        r0 = 64;
        r11.a(r0, r1);
        r0 = r2;
        goto L_0x01c1;
    L_0x02b4:
        r4 = "larger";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x02c5;
    L_0x02bd:
        r0 = -128; // 0xffffffffffffff80 float:NaN double:NaN;
        r11.a(r0, r1);
        r0 = r2;
        goto L_0x01c1;
    L_0x02c5:
        r0 = a(r0, r5, r3);
        if (r0 != 0) goto L_0x07f3;
    L_0x02cb:
        r0 = r2;
        goto L_0x01c1;
    L_0x02ce:
        r0 = r4.get(r2);
        r4.add(r0);
    L_0x02d5:
        r0 = r4.get(r2);
        r4.add(r0);
    L_0x02dc:
        r0 = r4.get(r1);
        r4.add(r0);
        goto L_0x020c;
    L_0x02e5:
        r4 = "padding";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0350;
    L_0x02ee:
        r0 = "padding";
        r0 = a(r10, r0);
        if (r0 == 0) goto L_0x01d4;
    L_0x02f7:
        r4 = r0.length();
        if (r4 <= 0) goto L_0x01d4;
    L_0x02fd:
        r4 = " ";
        r4 = format.epub.common.utils.o.a(r0, r4, r1);
        r0 = r4.size();
        if (r0 <= 0) goto L_0x0311;
    L_0x030a:
        r0 = r4.size();
        switch(r0) {
            case 1: goto L_0x033a;
            case 2: goto L_0x0341;
            case 3: goto L_0x0348;
            default: goto L_0x0311;
        };
    L_0x0311:
        r5 = 7;
        r0 = r4.get(r2);
        r0 = (java.lang.String) r0;
        a(r11, r5, r0);
        r0 = r4.get(r1);
        r0 = (java.lang.String) r0;
        a(r11, r1, r0);
        r5 = 8;
        r0 = r4.get(r7);
        r0 = (java.lang.String) r0;
        a(r11, r5, r0);
        r0 = r4.get(r8);
        r0 = (java.lang.String) r0;
        a(r11, r2, r0);
        goto L_0x01d4;
    L_0x033a:
        r0 = r4.get(r2);
        r4.add(r0);
    L_0x0341:
        r0 = r4.get(r2);
        r4.add(r0);
    L_0x0348:
        r0 = r4.get(r1);
        r4.add(r0);
        goto L_0x0311;
    L_0x0350:
        r4 = "margin-left";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0361;
    L_0x0359:
        r0 = "margin-left";
        a(r11, r7, r10, r0);
        goto L_0x01d4;
    L_0x0361:
        r4 = "margin-right";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0372;
    L_0x036a:
        r0 = "margin-right";
        a(r11, r8, r10, r0);
        goto L_0x01d4;
    L_0x0372:
        r4 = "padding-left";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0383;
    L_0x037b:
        r0 = "padding-left";
        a(r11, r2, r10, r0);
        goto L_0x01d4;
    L_0x0383:
        r4 = "padding-right";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0394;
    L_0x038c:
        r0 = "padding-right";
        a(r11, r1, r10, r0);
        goto L_0x01d4;
    L_0x0394:
        r4 = "margin-top";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x03a6;
    L_0x039d:
        r0 = 5;
        r4 = "margin-top";
        a(r11, r0, r10, r4);
        goto L_0x01d4;
    L_0x03a6:
        r4 = "padding-top";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x03b8;
    L_0x03af:
        r0 = 7;
        r4 = "padding-top";
        a(r11, r0, r10, r4);
        goto L_0x01d4;
    L_0x03b8:
        r4 = "margin-bottom";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x03ca;
    L_0x03c1:
        r0 = 6;
        r4 = "margin-bottom";
        a(r11, r0, r10, r4);
        goto L_0x01d4;
    L_0x03ca:
        r4 = "padding-bottom";
        r0 = r4.equals(r0);
        if (r0 == 0) goto L_0x01d4;
    L_0x03d3:
        r0 = 8;
        r4 = "padding-bottom";
        a(r11, r0, r10, r4);
        goto L_0x01d4;
    L_0x03dd:
        r0 = "text-indent";
        a(r11, r9, r10, r0);
        r0 = 11;
        r3 = "width";
        a(r11, r0, r10, r3);
        r0 = "vertical-align";
        r3 = a(r10, r0);
        if (r3 == 0) goto L_0x0442;
    L_0x03f4:
        r0 = r3.length();
        if (r0 <= 0) goto L_0x0442;
    L_0x03fa:
        r0 = 9;
        r4 = new java.lang.String[r0];
        r0 = "sub";
        r4[r2] = r0;
        r0 = "super";
        r4[r1] = r0;
        r0 = "top";
        r4[r7] = r0;
        r0 = "text-top";
        r4[r8] = r0;
        r0 = "middle";
        r4[r9] = r0;
        r0 = 5;
        r5 = "bottom";
        r4[r0] = r5;
        r0 = 6;
        r5 = "text-bottom";
        r4[r0] = r5;
        r0 = 7;
        r5 = "initial";
        r4[r0] = r5;
        r0 = 8;
        r5 = "inherit";
        r4[r0] = r5;
        r0 = r4.length;
        r0 = r0 + -1;
    L_0x0433:
        if (r0 < 0) goto L_0x043d;
    L_0x0435:
        r5 = r4[r0];
        r5 = r3.equals(r5);
        if (r5 == 0) goto L_0x050f;
    L_0x043d:
        if (r0 < 0) goto L_0x0513;
    L_0x043f:
        r11.c(r0);
    L_0x0442:
        r0 = "qrfullpage";
        r0 = a(r10, r0);
        if (r0 == 0) goto L_0x045d;
    L_0x044b:
        r3 = r0.length();
        if (r3 <= 0) goto L_0x045d;
    L_0x0451:
        r3 = "1";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x051a;
    L_0x045a:
        r11.a(r1);
    L_0x045d:
        r0 = "qrbleed";
        r0 = a(r10, r0);
        if (r0 == 0) goto L_0x04ab;
    L_0x0466:
        r3 = r0.length();
        if (r3 <= 0) goto L_0x04ab;
    L_0x046c:
        r3 = " ";
        r0 = format.epub.common.utils.o.a(r0, r3, r1);
        r3 = r0.size();
        if (r3 <= 0) goto L_0x04ab;
    L_0x0479:
        r3 = "left";
        r3 = r0.contains(r3);
        if (r3 == 0) goto L_0x0487;
    L_0x0482:
        r3 = 8;
        r11.b(r3);
    L_0x0487:
        r3 = "right";
        r3 = r0.contains(r3);
        if (r3 == 0) goto L_0x0493;
    L_0x0490:
        r11.b(r7);
    L_0x0493:
        r3 = "top";
        r3 = r0.contains(r3);
        if (r3 == 0) goto L_0x049f;
    L_0x049c:
        r11.b(r1);
    L_0x049f:
        r3 = "bottom";
        r0 = r0.contains(r3);
        if (r0 == 0) goto L_0x04ab;
    L_0x04a8:
        r11.b(r9);
    L_0x04ab:
        r0 = "display";
        r0 = a(r10, r0);
        r0 = format.epub.common.c.a.i.a(r0);
        r11.d(r0);
        r3 = new format.epub.common.c.a.g.a[r9];
        r0 = new format.epub.common.c.a.g$a;
        r0.<init>();
        r3[r2] = r0;
        r0 = new format.epub.common.c.a.g$a;
        r0.<init>();
        r3[r1] = r0;
        r0 = new format.epub.common.c.a.g$a;
        r0.<init>();
        r3[r7] = r0;
        r0 = new format.epub.common.c.a.g$a;
        r0.<init>();
        r3[r8] = r0;
        r0 = "border";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x051f;
    L_0x04e4:
        r4 = r3[r2];
        a(r0, r4, r2);
        r0 = r1;
    L_0x04ea:
        if (r0 >= r9) goto L_0x051f;
    L_0x04ec:
        r4 = r3[r0];
        r5 = r3[r2];
        r5 = r5.a;
        r4.a = r5;
        r4 = r3[r0];
        r5 = r3[r2];
        r5 = r5.b;
        r4.b = r5;
        r4 = r3[r0];
        r5 = r3[r2];
        r5 = r5.c;
        r4.c = r5;
        r4 = r3[r0];
        r5 = r3[r2];
        r5 = r5.f;
        r4.f = r5;
        r0 = r0 + 1;
        goto L_0x04ea;
    L_0x050f:
        r0 = r0 + -1;
        goto L_0x0433;
    L_0x0513:
        r0 = 10;
        a(r11, r0, r3);
        goto L_0x0442;
    L_0x051a:
        r11.a(r2);
        goto L_0x045d;
    L_0x051f:
        r0 = "border-style";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x056c;
    L_0x052c:
        r4 = " ";
        r4 = format.epub.common.utils.o.a(r0, r4, r1);
        r0 = r4.size();
        if (r0 <= 0) goto L_0x0540;
    L_0x0539:
        r0 = r4.size();
        switch(r0) {
            case 1: goto L_0x05df;
            case 2: goto L_0x05e6;
            case 3: goto L_0x05ed;
            default: goto L_0x0540;
        };
    L_0x0540:
        r0 = r4.get(r2);
        r0 = (java.lang.String) r0;
        r5 = r3[r2];
        a(r0, r5, r1);
        r0 = r4.get(r1);
        r0 = (java.lang.String) r0;
        r5 = r3[r1];
        a(r0, r5, r1);
        r0 = r4.get(r7);
        r0 = (java.lang.String) r0;
        r5 = r3[r7];
        a(r0, r5, r1);
        r0 = r4.get(r8);
        r0 = (java.lang.String) r0;
        r4 = r3[r8];
        a(r0, r4, r1);
    L_0x056c:
        r0 = "border-width";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x05b9;
    L_0x0579:
        r4 = " ";
        r4 = format.epub.common.utils.o.a(r0, r4, r1);
        r0 = r4.size();
        if (r0 <= 0) goto L_0x058d;
    L_0x0586:
        r0 = r4.size();
        switch(r0) {
            case 1: goto L_0x05f6;
            case 2: goto L_0x05fd;
            case 3: goto L_0x0604;
            default: goto L_0x058d;
        };
    L_0x058d:
        r0 = r4.get(r2);
        r0 = (java.lang.String) r0;
        r5 = r3[r2];
        a(r0, r5, r1);
        r0 = r4.get(r1);
        r0 = (java.lang.String) r0;
        r5 = r3[r1];
        a(r0, r5, r1);
        r0 = r4.get(r7);
        r0 = (java.lang.String) r0;
        r5 = r3[r7];
        a(r0, r5, r1);
        r0 = r4.get(r8);
        r0 = (java.lang.String) r0;
        r4 = r3[r8];
        a(r0, r4, r1);
    L_0x05b9:
        r0 = "border-color";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x0645;
    L_0x05c6:
        r4 = e;
        r0 = r4.matcher(r0);
        r4 = new java.util.ArrayList;
        r4.<init>();
    L_0x05d1:
        r5 = r0.find();
        if (r5 == 0) goto L_0x060c;
    L_0x05d7:
        r5 = r0.group();
        r4.add(r5);
        goto L_0x05d1;
    L_0x05df:
        r0 = r4.get(r2);
        r4.add(r0);
    L_0x05e6:
        r0 = r4.get(r2);
        r4.add(r0);
    L_0x05ed:
        r0 = r4.get(r1);
        r4.add(r0);
        goto L_0x0540;
    L_0x05f6:
        r0 = r4.get(r2);
        r4.add(r0);
    L_0x05fd:
        r0 = r4.get(r2);
        r4.add(r0);
    L_0x0604:
        r0 = r4.get(r1);
        r4.add(r0);
        goto L_0x058d;
    L_0x060c:
        r0 = r4.size();
        if (r0 <= 0) goto L_0x0619;
    L_0x0612:
        r0 = r4.size();
        switch(r0) {
            case 1: goto L_0x07ad;
            case 2: goto L_0x07b4;
            case 3: goto L_0x07bb;
            default: goto L_0x0619;
        };
    L_0x0619:
        r0 = r4.get(r2);
        r0 = (java.lang.String) r0;
        r5 = r3[r2];
        a(r0, r5, r1);
        r0 = r4.get(r1);
        r0 = (java.lang.String) r0;
        r5 = r3[r1];
        a(r0, r5, r1);
        r0 = r4.get(r7);
        r0 = (java.lang.String) r0;
        r5 = r3[r7];
        a(r0, r5, r1);
        r0 = r4.get(r8);
        r0 = (java.lang.String) r0;
        r4 = r3[r8];
        a(r0, r4, r1);
    L_0x0645:
        r0 = "border-top";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x0657;
    L_0x0652:
        r4 = r3[r2];
        a(r0, r4, r2);
    L_0x0657:
        r0 = "border-right";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x0669;
    L_0x0664:
        r4 = r3[r1];
        a(r0, r4, r2);
    L_0x0669:
        r0 = "border-bottom";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x067b;
    L_0x0676:
        r4 = r3[r7];
        a(r0, r4, r2);
    L_0x067b:
        r0 = "border-left";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x068d;
    L_0x0688:
        r4 = r3[r8];
        a(r0, r4, r2);
    L_0x068d:
        r0 = "border-radius";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x069d;
    L_0x069a:
        a(r0, r3);
    L_0x069d:
        r0 = "border-top-color";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x06af;
    L_0x06aa:
        r4 = r3[r2];
        a(r0, r4, r1);
    L_0x06af:
        r0 = "border-right-color";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x06c1;
    L_0x06bc:
        r4 = r3[r1];
        a(r0, r4, r1);
    L_0x06c1:
        r0 = "border-bottom-color";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x06d3;
    L_0x06ce:
        r4 = r3[r7];
        a(r0, r4, r1);
    L_0x06d3:
        r0 = "border-left-color";
        r0 = a(r10, r0);
        r4 = android.text.TextUtils.isEmpty(r0);
        if (r4 != 0) goto L_0x06e5;
    L_0x06e0:
        r4 = r3[r8];
        a(r0, r4, r1);
    L_0x06e5:
        r11.a(r3);
        r0 = "background";
        r0 = a(r10, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x06f8;
    L_0x06f5:
        r11.e(r0);
    L_0x06f8:
        r0 = "background-color";
        r0 = a(r10, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x0708;
    L_0x0705:
        r11.e(r0);
    L_0x0708:
        r0 = "background-image";
        r0 = a(r10, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x0742;
    L_0x0715:
        r3 = "url(";
        r3 = r0.startsWith(r3);
        if (r3 == 0) goto L_0x0742;
    L_0x071e:
        r3 = r0.length();
        r3 = r3 + -1;
        r0 = r0.substring(r9, r3);
        r0 = format.epub.common.utils.d.b(r0);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = f;
        r3 = r3.append(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        r11.f(r0);
    L_0x0742:
        r0 = "float";
        r0 = a(r10, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x075b;
    L_0x074f:
        r3 = "left";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x07c4;
    L_0x0758:
        r11.a(r1);
    L_0x075b:
        r0 = "width";
        r0 = a(r10, r0);
        r11.a(r0);
        r0 = "height";
        r0 = a(r10, r0);
        r11.b(r0);
        r0 = "list-style";
        r0 = a(r10, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x0783;
    L_0x077c:
        r0 = c(r0);
        r11.g(r0);
    L_0x0783:
        r0 = "list-style-image";
        r0 = a(r10, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x0793;
    L_0x0790:
        r11.g(r0);
    L_0x0793:
        r0 = "clear";
        r0 = a(r10, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x07ac;
    L_0x07a0:
        r3 = "left";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x07d5;
    L_0x07a9:
        r11.b(r1);
    L_0x07ac:
        return r11;
    L_0x07ad:
        r0 = r4.get(r2);
        r4.add(r0);
    L_0x07b4:
        r0 = r4.get(r2);
        r4.add(r0);
    L_0x07bb:
        r0 = r4.get(r1);
        r4.add(r0);
        goto L_0x0619;
    L_0x07c4:
        r3 = "right";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x07d1;
    L_0x07cd:
        r11.a(r7);
        goto L_0x075b;
    L_0x07d1:
        r11.a(r2);
        goto L_0x075b;
    L_0x07d5:
        r1 = "right";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x07e2;
    L_0x07de:
        r11.b(r7);
        goto L_0x07ac;
    L_0x07e2:
        r1 = "both";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x07ef;
    L_0x07eb:
        r11.b(r8);
        goto L_0x07ac;
    L_0x07ef:
        r11.b(r2);
        goto L_0x07ac;
    L_0x07f3:
        r0 = r1;
        goto L_0x01c1;
        */
        throw new UnsupportedOperationException("Method not decompiled: format.epub.common.c.a.g.a(java.util.Map, format.epub.common.text.model.n):format.epub.common.text.model.n");
    }

    private static void a(String str, a aVar, boolean z) {
        String[] split = z ? new String[]{str} : str.split(" ");
        byte b = aVar.a;
        short s = aVar.b;
        byte b2 = aVar.c;
        String str2 = aVar.f;
        short[] sArr = new short[]{s};
        byte[] bArr = new byte[]{b2};
        int length = split.length;
        int i = 0;
        while (i < length) {
            short s2;
            byte b3;
            byte b4;
            String str3 = split[i];
            byte b5;
            if (str3.equals("none")) {
                b5 = b2;
                s2 = s;
                b3 = c.a;
                b4 = b5;
            } else if (str3.equals("solid")) {
                b5 = b2;
                s2 = s;
                b3 = c.b;
                b4 = b5;
            } else if (str3.equals("dotted")) {
                b5 = b2;
                s2 = s;
                b3 = c.c;
                b4 = b5;
            } else if (str3.equals("dashed")) {
                b5 = b2;
                s2 = s;
                b3 = c.d;
                b4 = b5;
            } else if (str3.equals("double")) {
                b5 = b2;
                s2 = s;
                b3 = c.e;
                b4 = b5;
            } else if (str3.startsWith("#") || str3.startsWith("rgb")) {
                str2 = str3;
                b4 = b2;
                s2 = s;
                b3 = b;
            } else if (str3.equals("thin")) {
                s2 = (short) 5;
                b3 = b;
                b4 = (byte) 0;
            } else if (str3.equals("medium")) {
                s2 = (short) 10;
                b3 = b;
                b4 = (byte) 0;
            } else if (str3.equals("thick")) {
                s2 = (short) 15;
                b3 = b;
                b4 = (byte) 0;
            } else {
                sArr[0] = aVar.b;
                bArr[0] = aVar.c;
                if (a(str3, sArr, bArr)) {
                    s2 = sArr[0];
                    b4 = bArr[0];
                    b3 = b;
                } else if (TextUtils.isEmpty(str3)) {
                    b4 = b2;
                    s2 = s;
                    b3 = b;
                } else {
                    try {
                        Color.parseColor(str3);
                        str2 = str3;
                        b4 = b2;
                        s2 = s;
                        b3 = b;
                    } catch (Exception e) {
                        b4 = b2;
                        s2 = s;
                        b3 = b;
                    }
                }
            }
            i++;
            b = b3;
            s = s2;
            b2 = b4;
        }
        aVar.a = b;
        aVar.b = s;
        aVar.c = b2;
        aVar.f = str2;
    }

    private static b b(String str) {
        b bVar = null;
        String[] split = str.split(" ");
        if (split.length >= 2) {
            bVar = new b();
            short s = bVar.a;
            byte b = bVar.b;
            short[] sArr = new short[]{s};
            byte[] bArr = new byte[]{b};
            if (a(split[0], sArr, bArr)) {
                bVar.a = sArr[0];
                bVar.b = bArr[0];
            }
            if (a(split[1], sArr, bArr)) {
                bVar.c = sArr[0];
                bVar.d = bArr[0];
            }
            if (split.length >= 3) {
                String str2 = split[2];
                if (str2.startsWith("#")) {
                    bVar.g = str2;
                } else if (a(str2, sArr, bArr)) {
                    bVar.e = sArr[0];
                    bVar.f = bArr[0];
                }
            }
            if (split.length == 4) {
                bVar.g = split[3];
            }
        }
        return bVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r9, format.epub.common.c.a.g.a[] r10) {
        /*
        r8 = 3;
        r7 = 2;
        r6 = 1;
        r5 = 0;
        r0 = " ";
        r1 = format.epub.common.utils.o.a(r9, r0, r6);
        r0 = r1.size();
        if (r0 <= 0) goto L_0x0018;
    L_0x0011:
        r0 = r1.size();
        switch(r0) {
            case 1: goto L_0x0081;
            case 2: goto L_0x0088;
            case 3: goto L_0x008f;
            default: goto L_0x0018;
        };
    L_0x0018:
        r2 = new short[r6];
        r2[r5] = r5;
        r3 = new byte[r6];
        r3[r5] = r5;
        r0 = r1.get(r5);
        r0 = (java.lang.String) r0;
        r0 = a(r0, r2, r3);
        if (r0 == 0) goto L_0x0038;
    L_0x002c:
        r0 = r10[r5];
        r4 = r2[r5];
        r0.d = r4;
        r0 = r10[r5];
        r4 = r3[r5];
        r0.e = r4;
    L_0x0038:
        r0 = r1.get(r6);
        r0 = (java.lang.String) r0;
        r0 = a(r0, r2, r3);
        if (r0 == 0) goto L_0x0050;
    L_0x0044:
        r0 = r10[r6];
        r4 = r2[r5];
        r0.d = r4;
        r0 = r10[r6];
        r4 = r3[r5];
        r0.e = r4;
    L_0x0050:
        r0 = r1.get(r7);
        r0 = (java.lang.String) r0;
        r0 = a(r0, r2, r3);
        if (r0 == 0) goto L_0x0068;
    L_0x005c:
        r0 = r10[r7];
        r4 = r2[r5];
        r0.d = r4;
        r0 = r10[r7];
        r4 = r3[r5];
        r0.e = r4;
    L_0x0068:
        r0 = r1.get(r8);
        r0 = (java.lang.String) r0;
        r0 = a(r0, r2, r3);
        if (r0 == 0) goto L_0x0080;
    L_0x0074:
        r0 = r10[r8];
        r1 = r2[r5];
        r0.d = r1;
        r0 = r10[r8];
        r1 = r3[r5];
        r0.e = r1;
    L_0x0080:
        return;
    L_0x0081:
        r0 = r1.get(r5);
        r1.add(r0);
    L_0x0088:
        r0 = r1.get(r5);
        r1.add(r0);
    L_0x008f:
        r0 = r1.get(r6);
        r1.add(r0);
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: format.epub.common.c.a.g.a(java.lang.String, format.epub.common.c.a.g$a[]):void");
    }

    public void a() {
        this.b.clear();
        this.c.clear();
        this.d.clear();
    }

    public static void a(String str) {
        f = str;
    }

    private static int c(String str) {
        if ("square".equals(str)) {
            return 2;
        }
        if ("none".equals(str)) {
            return 1;
        }
        if ("disc".equals(str)) {
            return 4;
        }
        if ("circle".equals(str)) {
            return 8;
        }
        if ("decimal".equals(str)) {
            return 16;
        }
        if ("decimal-leading-zero".equals(str)) {
            return 32;
        }
        if ("lower-roman".equals(str)) {
            return 64;
        }
        return 4;
    }
}
