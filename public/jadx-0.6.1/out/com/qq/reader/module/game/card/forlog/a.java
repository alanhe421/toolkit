package com.qq.reader.module.game.card.forlog;

import android.app.Activity;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.game.card.view.GameOpenBtn;
import com.qq.reader.module.game.card.view.c;
import java.util.HashMap;
import java.util.Map;

/* compiled from: GameHorizontalCardPresenterForRDM */
public class a extends c {
    private String g;
    private int h;

    public a(com.qq.reader.module.bookstore.qnative.c.a aVar, String str, int i) {
        super(aVar);
        this.g = str;
        this.h = i;
    }

    protected GameOpenBtn a() {
        return super.a();
    }

    protected boolean a(Activity activity, com.qq.reader.module.game.data.c cVar) {
        com.qq.reader.common.monitor.debug.c.e("Game", "category item click cId is " + this.g + "   user location is " + this.h);
        Map hashMap;
        switch (this.h) {
            case 0:
                com.qq.reader.module.game.a.b().a(true);
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, this.g);
                i.a("event_A224", hashMap, ReaderApplication.getApplicationImp());
                break;
            case 1:
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, this.g);
                i.a("event_A235", hashMap, ReaderApplication.getApplicationImp());
                break;
            case 2:
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, this.g);
                i.a("event_A229", hashMap, ReaderApplication.getApplicationImp());
                break;
            case 3:
                i.a("event_A214", null, ReaderApplication.getApplicationImp());
                break;
        }
        return super.a(activity, cVar);
    }

    protected boolean b(Activity activity, com.qq.reader.module.game.data.c cVar) {
        com.qq.reader.common.monitor.debug.c.e("Game", "category ok button click cId is " + this.g + "   user location is " + this.h);
        Map hashMap;
        switch (this.h) {
            case 0:
                com.qq.reader.module.game.a.b().a(true);
                if (cVar.g() || (!cVar.g() && cVar.d() == 5)) {
                    hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, this.g);
                    i.a("event_A223", hashMap, ReaderApplication.getApplicationImp());
                    i.a("event_A232", null, ReaderApplication.getApplicationImp());
                    break;
                }
            case 1:
                if (cVar.g() || (!cVar.g() && cVar.d() == 5)) {
                    hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, this.g);
                    i.a("event_A234", hashMap, ReaderApplication.getApplicationImp());
                    i.a("event_A232", null, ReaderApplication.getApplicationImp());
                    break;
                }
            case 2:
                if (cVar.g() || (!cVar.g() && cVar.d() == 5)) {
                    hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, this.g);
                    i.a("event_A228", hashMap, ReaderApplication.getApplicationImp());
                    i.a("event_A232", null, ReaderApplication.getApplicationImp());
                    break;
                }
            case 3:
                if (cVar.g() || (!cVar.g() && cVar.d() == 5)) {
                    i.a("event_A232", null, ReaderApplication.getApplicationImp());
                    i.a("event_A215", null, ReaderApplication.getApplicationImp());
                    break;
                }
        }
        return super.b(activity, cVar);
    }
}
