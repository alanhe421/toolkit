package com.qq.reader.common.emotion;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SystemEmoticonInfo */
public class i extends d {
    public static String e = "system_emoticons";
    public int c;
    public String d;

    public static List<d> a() {
        List<d> arrayList = new ArrayList();
        for (int i = 1; i <= 105; i++) {
            i iVar = new i();
            iVar.c = i;
            iVar.a = 1;
            iVar.d = String.format("[emot=default,%02d/]", new Object[]{Integer.valueOf(i)});
            arrayList.add(iVar);
        }
        return arrayList;
    }

    public Drawable a(Context context, float f) {
        return b.a(context, String.format(e + "/%02d.png", new Object[]{Integer.valueOf(this.c)}));
    }

    public Drawable b(Context context, float f) {
        return b.a(context, String.format(e + "/%02d.png", new Object[]{Integer.valueOf(this.c)}));
    }
}
