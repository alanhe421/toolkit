package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.internal.c;

/* compiled from: Headers */
public final class r {
    private final String[] a;

    /* compiled from: Headers */
    public static final class a {
        final List<String> a = new ArrayList(20);

        a a(String str) {
            int indexOf = str.indexOf(":", 1);
            if (indexOf != -1) {
                return b(str.substring(0, indexOf), str.substring(indexOf + 1));
            }
            if (str.startsWith(":")) {
                return b("", str.substring(1));
            }
            return b("", str);
        }

        public a a(String str, String str2) {
            d(str, str2);
            return b(str, str2);
        }

        a b(String str, String str2) {
            this.a.add(str);
            this.a.add(str2.trim());
            return this;
        }

        public a b(String str) {
            int i = 0;
            while (i < this.a.size()) {
                if (str.equalsIgnoreCase((String) this.a.get(i))) {
                    this.a.remove(i);
                    this.a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public a c(String str, String str2) {
            d(str, str2);
            b(str);
            b(str, str2);
            return this;
        }

        private void d(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            } else if (str.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            } else {
                int i;
                char charAt;
                int length = str.length();
                for (i = 0; i < length; i++) {
                    charAt = str.charAt(i);
                    if (charAt <= ' ' || charAt >= '') {
                        throw new IllegalArgumentException(c.a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
                    }
                }
                if (str2 == null) {
                    throw new NullPointerException("value == null");
                }
                length = str2.length();
                i = 0;
                while (i < length) {
                    charAt = str2.charAt(i);
                    if ((charAt > '\u001f' || charAt == '\t') && charAt < '') {
                        i++;
                    } else {
                        throw new IllegalArgumentException(c.a("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt), Integer.valueOf(i), str, str2));
                    }
                }
            }
        }

        public r a() {
            return new r(this);
        }
    }

    r(a aVar) {
        this.a = (String[]) aVar.a.toArray(new String[aVar.a.size()]);
    }

    private r(String[] strArr) {
        this.a = strArr;
    }

    public String a(String str) {
        return a(this.a, str);
    }

    public int a() {
        return this.a.length / 2;
    }

    public String a(int i) {
        return this.a[i * 2];
    }

    public String b(int i) {
        return this.a[(i * 2) + 1];
    }

    public List<String> b(String str) {
        int a = a();
        List list = null;
        for (int i = 0; i < a; i++) {
            if (str.equalsIgnoreCase(a(i))) {
                if (list == null) {
                    list = new ArrayList(2);
                }
                list.add(b(i));
            }
        }
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    public a b() {
        a aVar = new a();
        Collections.addAll(aVar.a, this.a);
        return aVar;
    }

    public boolean equals(Object obj) {
        return (obj instanceof r) && Arrays.equals(((r) obj).a, this.a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int a = a();
        for (int i = 0; i < a; i++) {
            stringBuilder.append(a(i)).append(": ").append(b(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    private static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public static r a(Map<String, String> map) {
        if (map == null) {
            throw new NullPointerException("headers == null");
        }
        String[] strArr = new String[(map.size() * 2)];
        int i = 0;
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            String trim = ((String) entry.getKey()).trim();
            String trim2 = ((String) entry.getValue()).trim();
            if (trim.length() != 0 && trim.indexOf(0) == -1 && trim2.indexOf(0) == -1) {
                strArr[i] = trim;
                strArr[i + 1] = trim2;
                i += 2;
            } else {
                throw new IllegalArgumentException("Unexpected header: " + trim + ": " + trim2);
            }
        }
        return new r(strArr);
    }
}
