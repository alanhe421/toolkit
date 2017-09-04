package jadx.core.utils;

import jadx.api.IJadxArgs;

public class StringUtils {
    private final boolean escapeUnicode;

    public StringUtils(IJadxArgs args) {
        this.escapeUnicode = args.escapeUnicode();
    }

    public String unescapeString(String str) {
        int len = str.length();
        if (len == 0) {
            return "\"\"";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; i++) {
            processChar(str.charAt(i) & 65535, res);
        }
        return '\"' + res.toString() + '\"';
    }

    public String unescapeChar(char ch) {
        if (ch == '\'') {
            return "'\\''";
        }
        StringBuilder res = new StringBuilder();
        processChar(ch, res);
        return '\'' + res.toString() + '\'';
    }

    private void processChar(int c, StringBuilder res) {
        switch (c) {
            case 8:
                res.append("\\b");
                return;
            case 9:
                res.append("\\t");
                return;
            case 10:
                res.append("\\n");
                return;
            case 12:
                res.append("\\f");
                return;
            case 13:
                res.append("\\r");
                return;
            case 34:
                res.append("\\\"");
                return;
            case 39:
                res.append('\'');
                return;
            case 92:
                res.append("\\\\");
                return;
            default:
                if (c < 32 || (c >= 127 && this.escapeUnicode)) {
                    res.append("\\u").append(String.format("%04x", new Object[]{Integer.valueOf(c)}));
                    return;
                }
                res.append((char) c);
                return;
        }
    }

    public static String escape(String str) {
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            switch (c) {
                case ' ':
                case '$':
                case ',':
                case '.':
                case '/':
                case ';':
                case '<':
                    sb.append('_');
                    break;
                case '*':
                case '>':
                case '?':
                case ']':
                    break;
                case '[':
                    sb.append('A');
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    public static String escapeXML(String str) {
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            switch (c) {
                case '\"':
                    sb.append("&quot;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    public static String escapeResValue(String str) {
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            switch (c) {
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\"':
                    sb.append("&quot;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    public static String escapeResStrValue(String str) {
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            switch (c) {
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\"':
                    sb.append("\\\"");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\'':
                    sb.append("\\'");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }
}
