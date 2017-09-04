package com.qq.reader.plugin.tts;

import com.qq.reader.plugin.tts.model.a;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/* compiled from: SentenceParser */
public abstract class i {
    protected int a;
    private Pattern b = Pattern.compile("([\\r|\\n| ]+)");
    private Pattern c = Pattern.compile("([\\r|\\n| |　| | ]+)");

    protected abstract void a(a aVar, f fVar);

    protected boolean a(CharSequence charSequence, boolean z) {
        if (z) {
            return this.c.matcher(charSequence).find();
        }
        return this.b.matcher(charSequence).find();
    }

    protected String a(String str, String str2) {
        int i = 0;
        try {
            byte[] bytes = str.getBytes(str2);
            int length = str.length() - 1;
            while (i <= length && (str.charAt(i) <= ' ' || str.charAt(i) == ' ')) {
                i++;
            }
            if (i == 0) {
                return str;
            }
            length = str.substring(0, i).getBytes(str2).length;
            return new String(bytes, length, bytes.length - length, str2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
