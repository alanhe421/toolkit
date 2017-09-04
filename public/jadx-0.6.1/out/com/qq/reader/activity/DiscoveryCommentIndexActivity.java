package com.qq.reader.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.DiscoveryCommentZoneTask;
import com.qq.reader.common.utils.o;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.view.EmptyView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DiscoveryCommentIndexActivity extends ReaderBaseActivity implements OnItemClickListener {
    ArrayList<a> a = new ArrayList();
    protected Bundle b = null;
    private ListView c = null;
    private FrameLayout d = null;
    private View e = null;
    private com.qq.reader.module.dicovery.a f = null;
    private ImageView g = null;
    private TextView h = null;
    private TextView i = null;
    private TextView j = null;
    private TextView k = null;
    private TextView l = null;
    private TextView m = null;
    private TextView n = null;
    private View o;
    private String p = "";

    public class a {
        public String a;
        public long b;
        public long c;
        final /* synthetic */ DiscoveryCommentIndexActivity d;

        public a(DiscoveryCommentIndexActivity discoveryCommentIndexActivity) {
            this.d = discoveryCommentIndexActivity;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getIntent().getExtras();
        setContentView(R.layout.discovery_comment_layout);
        this.c = (ListView) findViewById(R.id.discovery_comment_layout_list);
        c();
        b();
        this.f = new com.qq.reader.module.dicovery.a(this, getHandler());
        this.c.setAdapter(this.f);
        this.c.setOnItemClickListener(this);
        this.g = (ImageView) findViewById(R.id.profile_header_left_back);
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DiscoveryCommentIndexActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.p = this.b.getString("LOCAL_STORE_IN_TITLE");
        this.h = (TextView) findViewById(R.id.profile_header_title);
        this.h.setText(this.p);
        setStatPageName("commentsquarepage");
    }

    public void a() {
        if (this.f.getCount() > 0) {
            if (this.d.getChildCount() > 0) {
                this.d.removeAllViews();
            }
        } else if (this.d.getChildCount() < 1) {
            this.d.addView(this.e);
        }
    }

    private void c() {
        this.o = d();
        this.c.addHeaderView(this.o);
    }

    private View d() {
        String[] strArr = new String[]{"书荒求助", "原创交流", "大神沙龙"};
        for (String str : strArr) {
            a aVar = new a(this);
            aVar.a = str;
            aVar.b = 0;
            aVar.c = 0;
            this.a.add(aVar);
        }
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.discovery_comment_layout_header, null);
        View findViewById = inflate.findViewById(R.id.item_1);
        ((ImageView) findViewById.findViewById(R.id.discovery_comment_layout_header_item_icon)).setImageResource(R.drawable.discovery_comment_header_item_1_image);
        this.i = (TextView) findViewById.findViewById(R.id.discovery_comment_layout_header_item_name);
        this.i.setText(strArr[0]);
        this.l = (TextView) findViewById.findViewById(R.id.discovery_comment_layout_header_item_amount);
        findViewById.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DiscoveryCommentIndexActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                a aVar = (a) this.a.a.get(0);
                o.a(this.a, aVar.b, aVar.a, new JumpActivityParameter());
                i.a("event_C88", null, ReaderApplication.getApplicationImp());
            }
        });
        View findViewById2 = inflate.findViewById(R.id.item_2);
        ((ImageView) findViewById2.findViewById(R.id.discovery_comment_layout_header_item_icon)).setImageResource(R.drawable.discovery_comment_header_item_2_image);
        this.j = (TextView) findViewById2.findViewById(R.id.discovery_comment_layout_header_item_name);
        this.j.setText(strArr[1]);
        this.m = (TextView) findViewById2.findViewById(R.id.discovery_comment_layout_header_item_amount);
        findViewById2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DiscoveryCommentIndexActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                a aVar = (a) this.a.a.get(1);
                o.a(this.a, aVar.b, aVar.a, new JumpActivityParameter());
                j.a(89, 2);
                i.a("event_C90", null, ReaderApplication.getApplicationImp());
            }
        });
        findViewById2 = inflate.findViewById(R.id.item_3);
        ((ImageView) findViewById2.findViewById(R.id.discovery_comment_layout_header_item_icon)).setImageResource(R.drawable.discovery_comment_header_item_3_image);
        this.k = (TextView) findViewById2.findViewById(R.id.discovery_comment_layout_header_item_name);
        this.k.setText(strArr[2]);
        this.n = (TextView) findViewById2.findViewById(R.id.discovery_comment_layout_header_item_amount);
        findViewById2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DiscoveryCommentIndexActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                a aVar = (a) this.a.a.get(2);
                o.a(this.a, aVar.b, aVar.a, new JumpActivityParameter());
                j.a(88, 2);
                i.a("event_C89", null, ReaderApplication.getApplicationImp());
            }
        });
        e();
        return inflate;
    }

    private void e() {
        if (this.a != null && this.a.size() > 2) {
            this.i.setText("" + ((a) this.a.get(0)).a);
            this.j.setText("" + ((a) this.a.get(1)).a);
            this.k.setText("" + ((a) this.a.get(2)).a);
            this.l.setText("" + ((a) this.a.get(0)).c);
            this.m.setText("" + ((a) this.a.get(1)).c);
            this.n.setText("" + ((a) this.a.get(2)).c);
        }
    }

    protected void onDestroy() {
        getHandler().removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        a(false);
        a();
    }

    protected void onPause() {
        super.onPause();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        finish();
        return true;
    }

    private void a(boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (z) {
                ArrayList a = this.f.a();
                if (a != null && a.size() > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        stringBuffer.append(String.valueOf((Long) it.next()));
                        stringBuffer.append(",");
                    }
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    jSONObject.put("bids", stringBuffer.toString());
                }
            }
            g.a().a(new DiscoveryCommentZoneTask(new c(this) {
                final /* synthetic */ DiscoveryCommentIndexActivity a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        if (this.a.getHandler() != null) {
                            JSONObject jSONObject = new JSONObject(str);
                            Message obtainMessage = this.a.getHandler().obtainMessage();
                            obtainMessage.what = 9000001;
                            obtainMessage.obj = jSONObject;
                            obtainMessage.sendToTarget();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                }
            }, jSONObject.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 9000001:
                try {
                    JSONObject jSONObject = (JSONObject) message.obj;
                    JSONArray optJSONArray = jSONObject.optJSONArray("commonzonelist");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        this.a.clear();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            a aVar = new a(this);
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            aVar.a = jSONObject2.optString("title");
                            aVar.c = jSONObject2.optLong("commentcount");
                            aVar.b = jSONObject2.optLong("bid");
                            this.a.add(aVar);
                        }
                        e();
                    }
                    final JSONArray optJSONArray2 = jSONObject.optJSONArray("zonelist");
                    if (optJSONArray2 != null) {
                        g.a().a(new ReaderIOTask() {
                            public void run() {
                                super.run();
                                for (int i = 0; i < optJSONArray2.length(); i++) {
                                    JSONObject jSONObject = null;
                                    try {
                                        jSONObject = optJSONArray2.getJSONObject(i);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    int optInt = jSONObject.optInt("commentcount");
                                    DiscoveryCommentIndexActivity.this.f.a(jSONObject.optLong("bid"), optInt);
                                }
                                DiscoveryCommentIndexActivity.this.mHandler.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass6 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        DiscoveryCommentIndexActivity.this.f.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                        break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    break;
                }
                break;
            case 9000003:
                this.f.notifyDataSetChanged();
                a();
                a(true);
                break;
        }
        return super.handleMessageImp(message);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.c.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount < this.f.getCount()) {
            com.qq.reader.module.dicovery.a.a aVar = (com.qq.reader.module.dicovery.a.a) this.f.getItem(headerViewsCount);
            Mark mark = aVar.a;
            final long bookId = mark.getBookId();
            final int i2 = aVar.b + aVar.c;
            g.a().a(new ReaderShortTask() {
                public void run() {
                    DiscoveryCommentIndexActivity.this.f.a(bookId, i2, 0);
                }
            });
            aVar.c = 0;
            aVar.b = 0;
            this.f.notifyDataSetChanged();
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(mark.getType()));
            i.a("event_F338", hashMap, ReaderApplication.getApplicationContext());
            if (mark.getType() == 9) {
                headerViewsCount = 9;
            } else {
                headerViewsCount = 0;
            }
            o.a((Activity) this, Long.valueOf(mark.getBookId()), mark.getBookName(), headerViewsCount, new JumpActivityParameter());
        }
    }

    public void b() {
        if (this.c.getFooterViewsCount() == 0) {
            this.d = new FrameLayout(this);
            View emptyView = new EmptyView(this);
            emptyView.a(3).c("去精选找好书").a("和读同1本书的小伙伴，讨论吐槽吧").b(R.drawable.empty09).a(true).a(new OnClickListener(this) {
                final /* synthetic */ DiscoveryCommentIndexActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(com.qq.reader.common.c.a.co);
                    this.a.getApplicationContext().sendBroadcast(intent);
                    this.a.finish();
                }
            });
            this.e = emptyView;
            this.c.addFooterView(this.d);
        }
    }
}
