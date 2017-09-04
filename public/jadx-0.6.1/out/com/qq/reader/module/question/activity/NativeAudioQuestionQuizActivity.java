package com.qq.reader.module.question.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.TextView;
import com.qq.reader.common.login.a;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.AudioQuestionQuizTask;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.AudioQuizXlistFooter;
import com.qq.reader.module.readpage.q;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.c;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.view.pullupdownlist.XListView$a;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class NativeAudioQuestionQuizActivity extends NativeBookStoreConfigBaseActivity {
    private static final String b = NativeAudioQuestionQuizActivity.class.getSimpleName();
    boolean a;
    private XListView c = null;
    private InputMethodManager d = null;
    private boolean n = false;
    private int o = 0;
    private Bundle p;
    private String q;
    private int r;
    private long s;
    private TextView t;
    private c u;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.nativebookstore_audioquiz_layout);
        this.p = getIntent().getExtras();
        i();
        this.s = this.p.getLong("audio_authorid");
        a(this.p);
    }

    private void i() {
        this.i = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshlayout);
        a();
        this.c = (XListView) findViewById(R.id.list);
        this.c.setCrashTag(CustomArrayList.Class_NativePageAudioQuiz);
        this.c.setXListFooter(new AudioQuizXlistFooter(this));
        this.c.setXListViewListener(new XListView$a(this) {
            final /* synthetic */ NativeAudioQuestionQuizActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.mHandler.sendEmptyMessage(500005);
            }
        });
        this.c.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NativeAudioQuestionQuizActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (1 == i) {
                    View currentFocus = this.a.getCurrentFocus();
                    if (currentFocus != null) {
                        if (currentFocus.getApplicationWindowToken() != null) {
                            this.a.d.hideSoftInputFromWindow(currentFocus.getApplicationWindowToken(), 2);
                        }
                        currentFocus.clearFocus();
                    }
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }
        });
        this.g = this.c;
        this.d = (InputMethodManager) getSystemService("input_method");
        this.t = (TextView) findViewById(R.id.profile_header_title);
        CharSequence string = this.p.getString("LOCAL_STORE_IN_TITLE");
        if (TextUtils.isEmpty(string)) {
            string = "大神";
        }
        this.t.setText(getString(R.string.audio_quiz_title, new Object[]{string}));
        findViewById(R.id.profile_header_left_back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioQuestionQuizActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.profile_header_right_button);
        textView.setText(R.string.qa_god);
        textView.setVisibility(0);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioQuestionQuizActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.b(this.a, 0, 0, null);
            }
        });
    }

    public void a(int i, int i2) {
        this.c.smoothScrollToPositionFromTop(i, i2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean handleMessageImp(android.os.Message r9) {
        /*
        r8 = this;
        r2 = 1;
        r0 = r9.what;	 Catch:{ Throwable -> 0x003c }
        switch(r0) {
            case 400008: goto L_0x01aa;
            case 500000: goto L_0x003e;
            case 500001: goto L_0x003e;
            case 500005: goto L_0x0038;
            case 1100100: goto L_0x00f2;
            case 1100101: goto L_0x012e;
            case 1100105: goto L_0x000b;
            default: goto L_0x0006;
        };
    L_0x0006:
        r0 = super.handleMessageImp(r9);
    L_0x000a:
        return r0;
    L_0x000b:
        r0 = r8.d;	 Catch:{ Throwable -> 0x003c }
        if (r0 == 0) goto L_0x0006;
    L_0x000f:
        r0 = r8.d;	 Catch:{ Throwable -> 0x003c }
        r0 = r0.isActive();	 Catch:{ Throwable -> 0x003c }
        if (r0 == 0) goto L_0x0006;
    L_0x0017:
        r0 = r8.getCurrentFocus();	 Catch:{ Throwable -> 0x003c }
        if (r0 == 0) goto L_0x0006;
    L_0x001d:
        r0 = r8.getCurrentFocus();	 Catch:{ Throwable -> 0x003c }
        r0 = r0.getApplicationWindowToken();	 Catch:{ Throwable -> 0x003c }
        if (r0 == 0) goto L_0x0006;
    L_0x0027:
        r0 = r8.d;	 Catch:{ Exception -> 0x0036 }
        r1 = r8.getCurrentFocus();	 Catch:{ Exception -> 0x0036 }
        r1 = r1.getApplicationWindowToken();	 Catch:{ Exception -> 0x0036 }
        r2 = 2;
        r0.hideSoftInputFromWindow(r1, r2);	 Catch:{ Exception -> 0x0036 }
        goto L_0x0006;
    L_0x0036:
        r0 = move-exception;
        goto L_0x0006;
    L_0x0038:
        r8.q();	 Catch:{ Throwable -> 0x003c }
        goto L_0x0006;
    L_0x003c:
        r0 = move-exception;
        goto L_0x0006;
    L_0x003e:
        r0 = r8.i;	 Catch:{ Throwable -> 0x00cf }
        r1 = 0;
        r0.setRefreshing(r1);	 Catch:{ Throwable -> 0x00cf }
        r0 = r9.obj;	 Catch:{ Throwable -> 0x00cf }
        if (r0 == 0) goto L_0x0082;
    L_0x0048:
        r0 = r9.obj;	 Catch:{ Throwable -> 0x00cf }
        r0 = (com.qq.reader.module.question.a.c) r0;	 Catch:{ Throwable -> 0x00cf }
        r4 = r0.r();	 Catch:{ Throwable -> 0x00cf }
        r6 = 1;
        r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r1 != 0) goto L_0x00c9;
    L_0x0056:
        r1 = r8.j;	 Catch:{ Throwable -> 0x00cf }
        r1.a(r0);	 Catch:{ Throwable -> 0x00cf }
        r1 = r0.b;	 Catch:{ Throwable -> 0x00cf }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Throwable -> 0x00cf }
        if (r1 != 0) goto L_0x0077;
    L_0x0063:
        r1 = r8.t;	 Catch:{ Throwable -> 0x00cf }
        r3 = 2131296369; // 0x7f090071 float:1.8210653E38 double:1.053000317E-314;
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x00cf }
        r5 = 0;
        r0 = r0.b;	 Catch:{ Throwable -> 0x00cf }
        r4[r5] = r0;	 Catch:{ Throwable -> 0x00cf }
        r0 = r8.getString(r3, r4);	 Catch:{ Throwable -> 0x00cf }
        r1.setText(r0);	 Catch:{ Throwable -> 0x00cf }
    L_0x0077:
        r0 = "event_D185";
        r1 = 0;
        r3 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ Throwable -> 0x00cf }
        com.qq.reader.common.monitor.i.a(r0, r1, r3);	 Catch:{ Throwable -> 0x00cf }
    L_0x0082:
        r8.c();	 Catch:{ Throwable -> 0x00cf }
        r0 = 0;
        r8.n = r0;	 Catch:{ Throwable -> 0x00cf }
        r0 = r8.h;	 Catch:{ Throwable -> 0x00cf }
        if (r0 == 0) goto L_0x00c6;
    L_0x008c:
        r0 = r8.j;	 Catch:{ Throwable -> 0x00cf }
        r0 = (com.qq.reader.module.question.a.c) r0;	 Catch:{ Throwable -> 0x00cf }
        r0 = r0.d;	 Catch:{ Throwable -> 0x00cf }
        if (r0 != 0) goto L_0x00d8;
    L_0x0094:
        r0 = r8.c;	 Catch:{ Throwable -> 0x00cf }
        r0.g();	 Catch:{ Throwable -> 0x00cf }
        r0 = r8.c;	 Catch:{ Throwable -> 0x00cf }
        r0 = r0.getXListFooter();	 Catch:{ Throwable -> 0x00cf }
        r0 = (com.qq.reader.module.question.card.AudioQuizXlistFooter) r0;	 Catch:{ Throwable -> 0x00cf }
        r1 = r8.j;	 Catch:{ Throwable -> 0x00cf }
        r1 = (com.qq.reader.module.question.a.c) r1;	 Catch:{ Throwable -> 0x00cf }
        r1 = r1.e;	 Catch:{ Throwable -> 0x00cf }
        if (r1 != 0) goto L_0x00d1;
    L_0x00a9:
        r1 = "暂无回答";
        r0.setEmptyTitle(r1);	 Catch:{ Throwable -> 0x00cf }
    L_0x00af:
        r0 = r8.h;	 Catch:{ Throwable -> 0x00cf }
        r0 = r0.b();	 Catch:{ Throwable -> 0x00cf }
        if (r0 != 0) goto L_0x00bf;
    L_0x00b7:
        r0 = r8.c;	 Catch:{ Throwable -> 0x00cf }
        r0 = r0.getAdapter();	 Catch:{ Throwable -> 0x00cf }
        if (r0 != 0) goto L_0x00ec;
    L_0x00bf:
        r0 = r8.c;	 Catch:{ Throwable -> 0x00cf }
        r1 = r8.h;	 Catch:{ Throwable -> 0x00cf }
        r0.setAdapter(r1);	 Catch:{ Throwable -> 0x00cf }
    L_0x00c6:
        r0 = r2;
        goto L_0x000a;
    L_0x00c9:
        r1 = r8.j;	 Catch:{ Throwable -> 0x00cf }
        r1.addMore(r0);	 Catch:{ Throwable -> 0x00cf }
        goto L_0x0082;
    L_0x00cf:
        r0 = move-exception;
        goto L_0x00c6;
    L_0x00d1:
        r1 = "暂无回答，赶快提问吧";
        r0.setEmptyTitle(r1);	 Catch:{ Throwable -> 0x00cf }
        goto L_0x00af;
    L_0x00d8:
        r0 = r8.j;	 Catch:{ Throwable -> 0x00cf }
        r0 = r0.s();	 Catch:{ Throwable -> 0x00cf }
        if (r0 != 0) goto L_0x00e6;
    L_0x00e0:
        r0 = r8.c;	 Catch:{ Throwable -> 0x00cf }
        r0.c();	 Catch:{ Throwable -> 0x00cf }
        goto L_0x00af;
    L_0x00e6:
        r0 = r8.c;	 Catch:{ Throwable -> 0x00cf }
        r0.e();	 Catch:{ Throwable -> 0x00cf }
        goto L_0x00af;
    L_0x00ec:
        r0 = r8.h;	 Catch:{ Throwable -> 0x00cf }
        r0.notifyDataSetChanged();	 Catch:{ Throwable -> 0x00cf }
        goto L_0x00c6;
    L_0x00f2:
        r0 = r9.obj;	 Catch:{ Throwable -> 0x003c }
        r0 = r0 instanceof android.os.Bundle;	 Catch:{ Throwable -> 0x003c }
        if (r0 == 0) goto L_0x012b;
    L_0x00f8:
        r0 = r8.u;	 Catch:{ Throwable -> 0x003c }
        if (r0 != 0) goto L_0x010b;
    L_0x00fc:
        r0 = new com.qq.reader.view.c;	 Catch:{ Throwable -> 0x003c }
        r0.<init>(r8);	 Catch:{ Throwable -> 0x003c }
        r8.u = r0;	 Catch:{ Throwable -> 0x003c }
        r0 = r8.u;	 Catch:{ Throwable -> 0x003c }
        r1 = 2131296368; // 0x7f090070 float:1.821065E38 double:1.0530003165E-314;
        r0.b(r1);	 Catch:{ Throwable -> 0x003c }
    L_0x010b:
        r0 = r8.isFinishing();	 Catch:{ Exception -> 0x01c3 }
        if (r0 != 0) goto L_0x0116;
    L_0x0111:
        r0 = r8.u;	 Catch:{ Exception -> 0x01c3 }
        r0.f_();	 Catch:{ Exception -> 0x01c3 }
    L_0x0116:
        r0 = r9.obj;	 Catch:{ Throwable -> 0x003c }
        r0 = (android.os.Bundle) r0;	 Catch:{ Throwable -> 0x003c }
        r1 = "audio_content";
        r1 = r0.getString(r1);	 Catch:{ Throwable -> 0x003c }
        r3 = "audio_price";
        r0 = r0.getInt(r3);	 Catch:{ Throwable -> 0x003c }
        r8.a(r1, r0);	 Catch:{ Throwable -> 0x003c }
    L_0x012b:
        r0 = r2;
        goto L_0x000a;
    L_0x012e:
        r0 = r8.u;	 Catch:{ Exception -> 0x01c0 }
        if (r0 == 0) goto L_0x0137;
    L_0x0132:
        r0 = r8.u;	 Catch:{ Exception -> 0x01c0 }
        r0.dismiss();	 Catch:{ Exception -> 0x01c0 }
    L_0x0137:
        r0 = r9.arg1;	 Catch:{ Throwable -> 0x003c }
        switch(r0) {
            case -1022: goto L_0x01a4;
            case 0: goto L_0x015a;
            default: goto L_0x013c;
        };	 Catch:{ Throwable -> 0x003c }
    L_0x013c:
        r0 = r9.obj;	 Catch:{ Throwable -> 0x003c }
        if (r0 != 0) goto L_0x0149;
    L_0x0140:
        r0 = 2131296957; // 0x7f0902bd float:1.8211845E38 double:1.0530006075E-314;
        r0 = r8.getString(r0);	 Catch:{ Throwable -> 0x003c }
        r9.obj = r0;	 Catch:{ Throwable -> 0x003c }
    L_0x0149:
        r0 = r9.obj;	 Catch:{ Throwable -> 0x003c }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x003c }
        r1 = 0;
        r0 = com.qq.reader.view.af.a(r8, r0, r1);	 Catch:{ Throwable -> 0x003c }
        r0.a();	 Catch:{ Throwable -> 0x003c }
    L_0x0157:
        r0 = r2;
        goto L_0x000a;
    L_0x015a:
        r0 = 0;
        r8.r = r0;	 Catch:{ Throwable -> 0x003c }
        r0 = 0;
        r8.q = r0;	 Catch:{ Throwable -> 0x003c }
        r0 = "提问成功";
        r1 = 0;
        r0 = com.qq.reader.view.af.a(r8, r0, r1);	 Catch:{ Throwable -> 0x003c }
        r0.a();	 Catch:{ Throwable -> 0x003c }
        r0 = r9.obj;	 Catch:{ Throwable -> 0x003c }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x003c }
        com.qq.reader.common.utils.o.e(r8, r0);	 Catch:{ Throwable -> 0x003c }
        r0 = r8.j;	 Catch:{ Throwable -> 0x003c }
        r0 = r0.m();	 Catch:{ Throwable -> 0x003c }
        r0 = r0.size();	 Catch:{ Throwable -> 0x003c }
        if (r0 <= 0) goto L_0x01a0;
    L_0x0180:
        r0 = r8.j;	 Catch:{ Throwable -> 0x003c }
        r0 = r0.m();	 Catch:{ Throwable -> 0x003c }
        r1 = 0;
        r0 = r0.get(r1);	 Catch:{ Throwable -> 0x003c }
        r0 = r0 instanceof com.qq.reader.module.question.card.AudioQuestionQuizCard;	 Catch:{ Throwable -> 0x003c }
        if (r0 == 0) goto L_0x01a0;
    L_0x018f:
        r0 = r8.j;	 Catch:{ Throwable -> 0x003c }
        r0 = r0.m();	 Catch:{ Throwable -> 0x003c }
        r1 = 0;
        r0 = r0.get(r1);	 Catch:{ Throwable -> 0x003c }
        r0 = (com.qq.reader.module.question.card.AudioQuestionQuizCard) r0;	 Catch:{ Throwable -> 0x003c }
        r0.resetCard();	 Catch:{ Throwable -> 0x003c }
        goto L_0x0157;
    L_0x01a0:
        r8.e_();	 Catch:{ Throwable -> 0x003c }
        goto L_0x0157;
    L_0x01a4:
        r0 = 608; // 0x260 float:8.52E-43 double:3.004E-321;
        r8.showFragmentDialog(r0);	 Catch:{ Throwable -> 0x003c }
        goto L_0x0157;
    L_0x01aa:
        r0 = r8.q;	 Catch:{ Throwable -> 0x003c }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x003c }
        if (r0 != 0) goto L_0x01bd;
    L_0x01b2:
        r0 = r8.r;	 Catch:{ Throwable -> 0x003c }
        if (r0 <= 0) goto L_0x01bd;
    L_0x01b6:
        r0 = r8.q;	 Catch:{ Throwable -> 0x003c }
        r1 = r8.r;	 Catch:{ Throwable -> 0x003c }
        r8.a(r0, r1);	 Catch:{ Throwable -> 0x003c }
    L_0x01bd:
        r0 = r2;
        goto L_0x000a;
    L_0x01c0:
        r0 = move-exception;
        goto L_0x0137;
    L_0x01c3:
        r0 = move-exception;
        goto L_0x0116;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.question.activity.NativeAudioQuestionQuizActivity.handleMessageImp(android.os.Message):boolean");
    }

    public void e_() {
        if (this.i != null) {
            if (this.j != null) {
                this.j.a(1001);
            }
            a(false, true);
        }
    }

    protected void a(boolean z, boolean z2) {
        boolean a = d.b().a(getApplicationContext(), this.j, this.mHandler, z);
        if (!z2) {
            if (a) {
                j();
                c();
                return;
            }
            b();
        }
    }

    protected void b() {
        super.b();
    }

    private void a(Bundle bundle) {
        this.n = true;
        this.o = 1;
        try {
            this.j = e.a().a(bundle, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.j != null) {
            if (this.h == null) {
                this.h = new f(this);
            }
            this.h.a(this.j);
            this.c.setPullLoadEnable(true);
            this.c.setAdapter(this.h);
            a(false, false);
        }
    }

    private void q() {
        if (!this.n) {
            Bundle bundle = new Bundle(this.p);
            int i = this.o + 1;
            this.o = i;
            bundle.putInt("audio_pagestamp", i);
            b a = e.a().a(bundle, this);
            a.a(1001);
            d.b().a(getApplicationContext(), a, this.mHandler, true);
            this.n = true;
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    public void doFunction(final Bundle bundle) {
        if (!"audio_quiz_submit".equals(bundle.getString("audio_action"))) {
            return;
        }
        if (com.qq.reader.common.login.c.b()) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1100100;
            obtainMessage.obj = bundle;
            this.mHandler.sendMessage(obtainMessage);
            return;
        }
        this.mLoginNextTask = new a(this) {
            final /* synthetic */ NativeAudioQuestionQuizActivity b;

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.b.doFunction((Bundle) bundle.clone());
                        return;
                    case 2:
                    case 3:
                        this.b.mLoginNextTask = null;
                        return;
                    default:
                        return;
                }
            }
        };
        startLogin();
    }

    private void a(String str, int i) {
        if (!this.a) {
            this.a = true;
            this.q = str;
            this.r = i;
            g.a().a(new AudioQuestionQuizTask(str, i, this.s, new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ NativeAudioQuestionQuizActivity a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    Message obtainMessage = this.a.mHandler.obtainMessage(1100101);
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int optInt = jSONObject.optInt("code", -1);
                        obtainMessage.arg1 = optInt;
                        if (optInt == 0) {
                            obtainMessage.obj = jSONObject.optString("questionid");
                        } else {
                            obtainMessage.obj = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                        }
                    } catch (Exception e) {
                        com.qq.reader.common.monitor.debug.c.e(NativeAudioQuestionQuizActivity.b, e.getMessage());
                    }
                    this.a.mHandler.sendMessage(obtainMessage);
                    this.a.a = false;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    Message obtainMessage = this.a.mHandler.obtainMessage(1100101);
                    obtainMessage.obj = this.a.getString(R.string.net_not_available);
                    obtainMessage.arg1 = -1;
                    this.a.mHandler.sendMessage(obtainMessage);
                    this.a.a = false;
                }
            }));
        }
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        AlertDialog alertDialog = (AlertDialog) q.a(this, i, null);
        switch (i) {
            case 608:
                alertDialog.a(getString(R.string.audio_quiz_pay_needtocharge_message, new Object[]{this.r + ""}));
                alertDialog.a(R.string.audio_quiz_pay_needtocharge_positive, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioQuestionQuizActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.f();
                    }
                });
                alertDialog.a(-1, R.drawable.selector_orange_button);
                break;
        }
        return alertDialog;
    }

    public void f() {
        new JSPay(this).startCharge(this, this.r);
    }
}
