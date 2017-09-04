package com.qq.reader.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.view.EmptyView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RookieGiftPageActivity extends ReaderBaseActivity {
    private List<a> a;
    private int b;
    private View c;
    private TextView d;
    private TextView e;
    private EmptyView f;
    private ViewGroup g;
    private ReaderProtocolJSONTask h;

    private static final class a {
        public ImageView a;
        public TextView b;
        public View c;

        private a() {
        }

        public static a a(Activity activity, int i, int i2, int i3) {
            a aVar = new a();
            aVar.a = (ImageView) activity.findViewById(i);
            aVar.b = (TextView) activity.findViewById(i2);
            aVar.c = activity.findViewById(i3);
            return aVar;
        }
    }

    private static final class b {
        public String a;
        public String b;
        public String c;
        public long d;

        private b() {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.rookie_gift_page_activity_layout);
        this.b = getIntent().getIntExtra("PAGE_TYPE", 0);
        b();
        a();
    }

    protected void onResume() {
        super.onResume();
        i.a("event_F150", null, ReaderApplication.getApplicationContext());
    }

    private void a() {
        this.c.setVisibility(0);
        if (this.h != null) {
            g.a().b(this.h);
        }
        this.h = new ReaderProtocolJSONTask(new c(this) {
            final /* synthetic */ RookieGiftPageActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                if (this.a.h != null) {
                    List arrayList = new ArrayList();
                    try {
                        JSONArray optJSONArray = new JSONObject(str).optJSONArray("recBooks");
                        new JSAddToBookShelf(ReaderApplication.getApplicationImp().getApplicationContext()).addBooks(optJSONArray.toString());
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            b bVar = new b();
                            if (optJSONObject != null) {
                                bVar.b = optJSONObject.optString("qurl");
                                bVar.c = optJSONObject.optString("title");
                                bVar.a = optJSONObject.optString("cover");
                                bVar.d = optJSONObject.optLong("bid");
                            }
                            arrayList.add(bVar);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    this.a.mHandler.obtainMessage(65555, arrayList).sendToTarget();
                    this.a.h = null;
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.a.h != null) {
                    this.a.mHandler.obtainMessage(65555, null).sendToTarget();
                    this.a.h = null;
                }
            }
        });
        if (this.b == 0) {
            this.h.setUrl(e.dc);
        } else {
            this.h.setUrl(e.dc);
        }
        g.a().a(this.h);
    }

    private void b() {
        findViewById(R.id.profile_header_left_back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RookieGiftPageActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.d = (TextView) findViewById(R.id.profile_header_title);
        this.e = (TextView) findViewById(R.id.content_title);
        this.g = (ScrollView) findViewById(R.id.book_root_container);
        this.f = (EmptyView) findViewById(R.id.empty_view);
        if (this.b == 0) {
            this.d.setText(R.string.rookie_gift_page_title);
            this.e.setText(R.string.rookie_gift_page_content_title);
        } else {
            this.d.setText(R.string.rookie_gift_page_title);
            this.e.setText(R.string.rookie_gift_page_content_title);
        }
        this.c = findViewById(R.id.progress_container);
        this.a = new ArrayList();
        this.a.add(a.a(this, R.id.img_0x0, R.id.tv_0x0, R.id.click_layout0x0));
        this.a.add(a.a(this, R.id.img_1x1, R.id.tv_1x1, R.id.click_layout1x1));
        this.a.add(a.a(this, R.id.img_1x2, R.id.tv_1x2, R.id.click_layout1x2));
        this.a.add(a.a(this, R.id.img_1x3, R.id.tv_1x3, R.id.click_layout1x3));
        this.a.add(a.a(this, R.id.img_2x1, R.id.tv_2x1, R.id.click_layout2x1));
        this.a.add(a.a(this, R.id.img_2x2, R.id.tv_2x2, R.id.click_layout2x2));
        this.a.add(a.a(this, R.id.img_2x3, R.id.tv_2x3, R.id.click_layout2x3));
        this.a.add(a.a(this, R.id.img_3x1, R.id.tv_3x1, R.id.click_layout3x1));
        this.a.add(a.a(this, R.id.img_3x2, R.id.tv_3x2, R.id.click_layout3x2));
        this.a.add(a.a(this, R.id.img_3x3, R.id.tv_3x3, R.id.click_layout3x3));
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 65555:
                a(message.obj);
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private void a(Object obj) {
        this.c.setVisibility(8);
        if (!(obj instanceof List)) {
            c();
        } else if (((List) obj).size() < 10) {
            c();
        } else {
            this.f.setVisibility(8);
            for (int i = 0; i < ((List) obj).size(); i++) {
                b bVar = (b) ((List) obj).get(i);
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(bVar.d));
                i.a("event_F152", hashMap, ReaderApplication.getApplicationContext());
                a aVar = (a) this.a.get(i);
                com.qq.reader.common.imageloader.c.a(getContext()).a(bVar.a, aVar.a, com.qq.reader.common.imageloader.a.a().j());
                aVar.c.setTag(bVar);
                aVar.c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ RookieGiftPageActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Object tag = view.getTag();
                        if (tag instanceof b) {
                            try {
                                Map hashMap = new HashMap();
                                hashMap.put(s.ORIGIN, String.valueOf(((b) tag).d));
                                i.a("event_F151", hashMap, ReaderApplication.getApplicationContext());
                                if (TextUtils.isEmpty(((b) tag).b)) {
                                    o.a(this.a, ((b) tag).d + "", -1, -1, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                                } else {
                                    com.qq.reader.qurl.c.a(this.a, ((b) tag).b);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                aVar.b.setText(bVar.c);
            }
        }
    }

    private void c() {
        this.f.setVisibility(0);
        this.f.d(getResources().getString(R.string.empty_page_reload_content_reload));
        this.f.b(new OnClickListener(this) {
            final /* synthetic */ RookieGiftPageActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.h != null) {
            g.a().b(this.h);
        }
        this.h = null;
        this.mHandler.removeCallbacksAndMessages(null);
    }
}
