package com.qq.reader.activity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.view.GuideShadowView;
import com.qq.reader.view.k;
import com.qq.reader.view.web.n;
import com.qq.reader.view.web.n.a;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebRadioBrowserForContents extends WebBrowserForContents {
    protected OnClickListener j = null;
    private n s;
    private ImageView t;
    private GuideShadowView u;
    private View v;
    private TextView w;
    private k x;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.w = (TextView) findViewById(R.id.profile_header_title);
        this.v = findViewById(R.id.common_titler);
        this.t = (ImageView) findViewById(R.id.profile_header_title_sort);
        A();
    }

    protected void c() {
    }

    protected void A() {
        if (this.s == null) {
            this.s = new n(this, R.layout.webpage_popup_menu);
            this.s.a(new OnCancelListener(this) {
                final /* synthetic */ WebRadioBrowserForContents a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.t.setImageResource(R.drawable.bookstore_title_arrow_white);
                    if (this.a.u != null) {
                        ((ViewGroup) this.a.getWindow().getDecorView()).removeView(this.a.u);
                    }
                }
            });
            this.s.a((a) this);
            I();
            this.j = new OnClickListener(this) {
                final /* synthetic */ WebRadioBrowserForContents a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.G();
                }
            };
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            a(extras);
        }
    }

    private void a(Bundle bundle) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("popmenu"));
            JSONArray optJSONArray = jSONObject.optJSONArray("taglist");
            int optInt = jSONObject.optInt("select");
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                CharSequence optString = optJSONObject.optString("title");
                String optString2 = optJSONObject.optString(SocialConstants.PARAM_URL);
                Bundle bundle2 = new Bundle();
                bundle2.putString(SocialConstants.PARAM_URL, optString2);
                bundle2.putString("name", optString);
                this.s.a(i, optString, bundle2);
                if (optInt == i) {
                    this.s.b(i);
                    this.w.setText(optString);
                    this.k.b(optString2);
                }
            }
            this.t.setOnClickListener(this.j);
            this.w.setOnClickListener(this.j);
            H();
        } catch (Exception e) {
            c.e("MultiChoiceWeb", e.getMessage());
            finish();
        }
    }

    private void G() {
        if (this.s != null) {
            if (this.s.f()) {
                this.s.cancel();
            } else {
                this.s.c().a((int) R.id.readpage_topbar_popup);
                this.s.a(true);
            }
            H();
        }
    }

    private void H() {
        this.t.setVisibility(0);
        if (this.s.f()) {
            this.t.setImageResource(R.drawable.bookstore_title_arrow_up_white);
        } else {
            this.t.setImageResource(R.drawable.bookstore_title_arrow_white);
        }
    }

    public boolean b(int i, Bundle bundle) {
        if (bundle != null) {
            this.s.b(i);
            this.w.setText(bundle.getString("name"));
            this.k.b(bundle.getString(SocialConstants.PARAM_URL));
            this.t.setVisibility(0);
            H();
            this.k.clearHistory();
        }
        return true;
    }

    protected void d(String str) {
    }

    @TargetApi(8)
    private void I() {
        if (this.s != null) {
            this.s.a(new OnShowListener(this) {
                final /* synthetic */ WebRadioBrowserForContents a;

                {
                    this.a = r1;
                }

                public void onShow(DialogInterface dialogInterface) {
                    if (!d.n) {
                        if (this.a.u == null) {
                            this.a.u = new GuideShadowView(this.a);
                        }
                        this.a.u.setHighLightRect(this.a.B());
                        ((ViewGroup) this.a.getWindow().getDecorView()).addView(this.a.u);
                    }
                }
            });
        }
    }

    public k B() {
        if (this.x == null) {
            View view = this.v;
            r1 = new int[4];
            view.getLocationOnScreen(r1);
            r1[2] = r1[0] + view.getWidth();
            r1[3] = view.getHeight() + r1[1];
            this.x = new k();
            this.x.a = new Rect(r1[0], r1[1], r1[2], r1[3]);
            this.x.b = 1;
        }
        return this.x;
    }
}
