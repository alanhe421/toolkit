package com.qq.reader.module.findhome.b;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.qq.reader.module.findhome.a.b;
import com.qq.reader.module.findhome.a.c;
import com.qq.reader.module.findhome.base.a;
import com.tencent.feedback.proguard.R;

/* compiled from: FindHomeRecyclerVHFactory */
public class d {
    public int a(a aVar) {
        return 0;
    }

    public int a(b bVar) {
        return 4;
    }

    public int a(c cVar) {
        return 1;
    }

    public int a(com.qq.reader.module.findhome.a.a aVar) {
        return 2;
    }

    public int a(com.qq.reader.module.findhome.a.d dVar) {
        return 3;
    }

    public com.qq.reader.module.findhome.base.b a(int i, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        switch (i) {
            case 0:
                return new com.qq.reader.module.findhome.base.b(layoutInflater.inflate(R.layout.findhome_expand_basecard, viewGroup, false));
            case 1:
                return new com.qq.reader.module.findhome.base.b(layoutInflater.inflate(R.layout.findhome_expand_comic, viewGroup, false));
            case 2:
                return new com.qq.reader.module.findhome.c.a(layoutInflater.inflate(R.layout.findhome_expand_audiobook, viewGroup, false));
            case 3:
                return new com.qq.reader.module.findhome.c.c(layoutInflater.inflate(R.layout.findhome_expand_live, viewGroup, false));
            case 4:
                return new com.qq.reader.module.findhome.c.b(layoutInflater.inflate(R.layout.findhome_expand_basecard, viewGroup, false));
            default:
                return null;
        }
    }
}
