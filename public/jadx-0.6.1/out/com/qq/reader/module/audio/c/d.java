package com.qq.reader.module.audio.c;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.audio.fragment.NativeAudioZoneMoreListFragment;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.page.impl.af;

/* compiled from: NativeServerPageOfAudioZoneMoreBookList */
public class d extends af {
    public d(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return new c(bundle).a(e.a, "listDispatch?");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void a(org.json.JSONObject r7) {
        /*
        r6 = this;
        r1 = 1;
        r0 = 0;
        r2 = "lbookList";
        r3 = r7.optJSONArray(r2);	 Catch:{ Exception -> 0x005c }
        r2 = "actionId";
        r4 = r7.optInt(r2);	 Catch:{ Exception -> 0x005c }
        r2 = "actionFlag";
        r5 = r7.optInt(r2);	 Catch:{ Exception -> 0x005c }
        r2 = 4;
        switch(r4) {
            case -1: goto L_0x0056;
            case 11040: goto L_0x001c;
            case 11041: goto L_0x0054;
            case 11043: goto L_0x0052;
            default: goto L_0x001b;
        };	 Catch:{ Exception -> 0x005c }
    L_0x001b:
        r1 = r2;
    L_0x001c:
        if (r3 == 0) goto L_0x0060;
    L_0x001e:
        r2 = r3.length();	 Catch:{ Exception -> 0x005c }
        if (r2 <= 0) goto L_0x0060;
    L_0x0024:
        r2 = r3.length();	 Catch:{ Exception -> 0x005c }
        if (r0 >= r2) goto L_0x0060;
    L_0x002a:
        r2 = r3.optJSONObject(r0);	 Catch:{ Exception -> 0x005c }
        r4 = new com.qq.reader.module.audio.card.AudioZoneMoreBookListCard;	 Catch:{ Exception -> 0x005c }
        r5 = java.lang.String.valueOf(r1);	 Catch:{ Exception -> 0x005c }
        r4.<init>(r6, r5);	 Catch:{ Exception -> 0x005c }
        r4.fillData(r2);	 Catch:{ Exception -> 0x005c }
        r2 = r6.l();	 Catch:{ Exception -> 0x005c }
        r4.setEventListener(r2);	 Catch:{ Exception -> 0x005c }
        r2 = r6.k;	 Catch:{ Exception -> 0x005c }
        r2.add(r4);	 Catch:{ Exception -> 0x005c }
        r2 = r6.l;	 Catch:{ Exception -> 0x005c }
        r5 = r4.getCardId();	 Catch:{ Exception -> 0x005c }
        r2.put(r5, r4);	 Catch:{ Exception -> 0x005c }
        r0 = r0 + 1;
        goto L_0x0024;
    L_0x0052:
        r1 = 2;
        goto L_0x001c;
    L_0x0054:
        r1 = 3;
        goto L_0x001c;
    L_0x0056:
        switch(r5) {
            case 1: goto L_0x005a;
            case 2: goto L_0x001c;
            default: goto L_0x0059;
        };
    L_0x0059:
        goto L_0x001b;
    L_0x005a:
        r1 = r0;
        goto L_0x001c;
    L_0x005c:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0060:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.audio.c.d.a(org.json.JSONObject):void");
    }

    public boolean a() {
        return false;
    }

    public Class c() {
        return NativeAudioZoneMoreListFragment.class;
    }
}
