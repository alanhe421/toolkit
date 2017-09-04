package com.qq.reader.module.bookstore.qnative.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.qq.reader.view.web.n.a;
import java.util.ArrayList;

public class NativeLocalPopTwoLevelActivity extends NativeBookStoreTwoLevelActivity {
    private ArrayList<String> k;
    private Bundle w;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.k = this.r.getStringArrayList("pagelist");
        this.l = this.r.getString("LOCAL_STORE_IN_TITLE");
        this.w = (Bundle) this.r.getParcelable("titleInfo");
        String string = this.r.getString("KEY_JUMP_PAGENAME");
        if (this.k != null) {
            this.q = this.k.indexOf(string);
            if (this.q < 0) {
                this.q = 0;
            }
        }
    }

    protected void onResume() {
        super.onResume();
        String string = this.r.getString("KEY_JUMP_PAGENAME");
        if ("PayMonth_Boy".equals(string) || "PayMonth_Girl".equals(string) || "PayMonth_Publish".equals(string)) {
            this.m.q();
        }
    }

    protected void m() {
        super.m();
        p();
        int i = 0;
        while (this.k != null && i < this.k.size()) {
            this.o.a(i, this.w.getString((String) this.k.get(i)), null);
            i++;
        }
        this.o.b(this.q);
        this.o.a((a) this);
        this.p.setOnClickListener(this.n);
        this.h.setOnClickListener(this.n);
    }

    public boolean b(int i, Bundle bundle) {
        this.q = i;
        this.o.b(this.q);
        String str = (String) this.k.get(i);
        this.h.setText(this.w.getString(str));
        this.r.putString("KEY_JUMP_PAGENAME", str);
        this.g.clear();
        c();
        this.m.a(1001);
        c(this.r);
        return false;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 20002) {
            return;
        }
        if (i2 == 0) {
            String string = this.r.getString("KEY_JUMP_PAGENAME");
            if ("PayMonth_Boy".equals(string) || "PayMonth_Girl".equals(string) || "PayMonth_Publish".equals(string)) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                    final /* synthetic */ NativeLocalPopTwoLevelActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.m.q();
                    }
                }, 2000);
            }
        } else if (i2 == 5) {
            startLogin();
        }
    }
}
