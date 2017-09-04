package com.qq.reader.module.question.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.emotion.ReplyView;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.AddAudioReplyTask;
import com.qq.reader.common.readertask.protocol.AudioQuestionRefuseTask;
import com.qq.reader.common.readertask.protocol.AudioQuestionReportTask;
import com.qq.reader.common.readertask.protocol.DelAudioReplyTask;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.utils.t;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.view.AudioControllerPanel;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.record.AudioMediaManager;
import com.qq.reader.module.question.record.PlayException;
import com.qq.reader.module.question.record.RecordException;
import com.qq.reader.module.readpage.q;
import com.qq.reader.plugin.audiobook.core.e;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.aa;
import com.qq.reader.view.af;
import com.qq.reader.view.c;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.view.pullupdownlist.XListView$a;
import com.qq.reader.view.r;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeAudioQuestionDetailActivity extends NativeBookStoreConfigBaseActivity {
    private static final String b = NativeAudioQuestionDetailActivity.class.getSimpleName();
    private FrameLayout A;
    private ReplyView B;
    private a C;
    private boolean D;
    public b a = null;
    private XListView c = null;
    private InputMethodManager d = null;
    private boolean n = false;
    private Bundle o;
    private Bundle p;
    private AudioControllerPanel q = null;
    private RelativeLayout r;
    private AudioMediaManager s;
    private AudioData t;
    private String u;
    private c v;
    private AlertDialog w;
    private int x = -1;
    private int y;
    private boolean z = true;

    private class a implements com.qq.reader.common.emotion.ReplyView.a {
        final /* synthetic */ NativeAudioQuestionDetailActivity a;
        private Bundle b;
        private Bundle c;
        private int d;

        private a(NativeAudioQuestionDetailActivity nativeAudioQuestionDetailActivity) {
            this.a = nativeAudioQuestionDetailActivity;
        }

        public void a(Bundle bundle) {
            this.c = bundle;
        }

        public void b(Bundle bundle) {
            this.b = bundle;
        }

        public void a(int i) {
            this.d = i;
        }

        public void c() {
            this.b = this.c;
            this.d = 0;
        }

        public boolean a() {
            if (com.qq.reader.common.login.c.b()) {
                String c = com.qq.reader.common.login.c.c().c();
                AudioData audioData = ((com.qq.reader.module.question.a.b) this.a.j).d;
                if (audioData != null && (c.equals(audioData.a().e()) || com.qq.reader.common.login.c.c().l(ReaderApplication.getApplicationImp()) == audioData.b().f() || audioData.a().m() != 0)) {
                    return true;
                }
                af.a(ReaderApplication.getApplicationImp(), R.string.audio_question_detail_notheard, 0).a();
                return false;
            }
            this.a.startLogin();
            return false;
        }

        public void a(CharSequence charSequence) {
        }

        public void b(CharSequence charSequence) {
            this.a.t();
            if (this.a.getCurrentFocus() != null) {
                IBinder windowToken = this.a.getCurrentFocus().getWindowToken();
                if (windowToken != null) {
                    this.a.d.hideSoftInputFromWindow(windowToken, 1);
                } else {
                    com.qq.reader.common.monitor.debug.c.e(NativeAudioQuestionDetailActivity.b, "windowToken -> null");
                }
            }
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            boolean u = this.a.j.u();
            if (this.a.j instanceof com.qq.reader.module.question.a.b) {
                Bundle bundle = new Bundle();
                bundle.putString("CONTENT", charSequence.toString());
                bundle.putString("PARA_TYPE_COMMENT_UID", this.b.getString("PARA_TYPE_COMMENT_UID"));
                bundle.putLong("fakereplyid", timeInMillis);
                ((com.qq.reader.module.question.a.b) this.a.j).c(bundle);
                if (u) {
                    this.a.c.c();
                }
                this.a.s();
                this.a.a(charSequence == null ? "" : charSequence.toString(), this.b, this.d, timeInMillis);
            }
        }

        public void b() {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.nativebookstore_audioquestion_layout);
        this.o = getIntent().getExtras();
        i();
        if (this.o != null) {
            this.u = this.o.getString("audio_questionid");
            b(this.o);
        }
    }

    private void i() {
        this.r = (RelativeLayout) findViewById(R.id.root);
        this.i = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshlayout);
        a();
        this.c = (XListView) findViewById(R.id.list);
        this.c.setCrashTag(CustomArrayList.Class_NativePageAudioQuestionDetail);
        this.c.setXListViewListener(new XListView$a(this) {
            final /* synthetic */ NativeAudioQuestionDetailActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.mHandler.sendEmptyMessage(500005);
            }
        });
        this.g = this.c;
        this.d = (InputMethodManager) getSystemService("input_method");
        ((TextView) findViewById(R.id.profile_header_title)).setText(this.o.getString("LOCAL_STORE_IN_TITLE"));
        findViewById(R.id.profile_header_left_back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioQuestionDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.B = (ReplyView) findViewById(R.id.reply_layout);
        this.A = (FrameLayout) findViewById(R.id.main_mask);
        this.A.setVisibility(4);
        this.A.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ NativeAudioQuestionDetailActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.d.hideSoftInputFromWindow(this.a.B.getWindowToken(), 2);
                this.a.mHandler.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass19 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.t();
                        this.a.a.C.c();
                    }
                }, 200);
                return true;
            }
        });
        this.B.setParentLayout(this.r);
        this.B.setMask(this.A);
        this.C = new a();
        this.B.setReplyActionListener(this.C);
    }

    private Bundle q() {
        Bundle bundle = new Bundle();
        bundle.putString("QID", this.u);
        bundle.putInt("CTYPE", 8);
        bundle.putBoolean(s.ORIGIN, true);
        return bundle;
    }

    private void a(final Bundle bundle, int i) {
        int i2 = bundle.getInt("REPLY_FROM");
        boolean z = bundle.getBoolean("SHOWKEYBOARD", false);
        this.B.setFrom(i2);
        if (this.B != null && this.B.getVisibility() == 8) {
            this.B.setVisibility(0);
        }
        String string = bundle.getString(BookClubReplyCard.REPLY_USER_NAME);
        a(string);
        if (bundle.containsKey("PARA_TYPE_REPLY_CARD_POSITION")) {
            this.B.d();
            this.d.showSoftInput(getCurrentFocus(), 0);
            this.mHandler.postDelayed(new Runnable(this) {
                final /* synthetic */ NativeAudioQuestionDetailActivity b;

                public void run() {
                    int[] iArr = new int[2];
                    this.b.B.getLocationInWindow(iArr);
                    this.b.c.smoothScrollBy(bundle.getInt("PARA_TYPE_REPLY_CARD_POSITION") - iArr[1], 300);
                }
            }, 500);
        }
        if (z) {
            this.mHandler.postDelayed(new Runnable(this) {
                final /* synthetic */ NativeAudioQuestionDetailActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.B.d();
                    this.a.d.showSoftInput(this.a.getCurrentFocus(), 0);
                }
            }, 200);
        }
        z = bundle.getBoolean(s.ORIGIN, false);
        this.C.b(bundle);
        this.C.a(i);
        if (!z) {
            r();
        } else if (string == null && VERSION.SDK_INT >= 21) {
            this.B.setParentLayout(this.r);
        }
    }

    private void a(String str) {
        this.B.setText("");
        if (com.qq.reader.common.login.c.b()) {
            String c = com.qq.reader.common.login.c.c().c();
            AudioData audioData = ((com.qq.reader.module.question.a.b) this.j).d;
            if (audioData == null || !(c.equals(audioData.a().e()) || com.qq.reader.common.login.c.c().l(ReaderApplication.getApplicationImp()) == audioData.b().f() || audioData.a().m() != 0)) {
                this.B.setHint("请听后再评论");
                return;
            } else if (TextUtils.isEmpty(str)) {
                this.B.setHint("写点什么吧");
                return;
            } else {
                this.B.setHint("回复" + str + "：");
                return;
            }
        }
        this.B.setHint("请听后再评论");
    }

    private void r() {
        if (VERSION.SDK_INT >= 21) {
            this.B.setParentLayout(this.r);
        }
        this.B.getInputFocus();
        this.B.c();
    }

    private void a(String str, Bundle bundle, int i, long j) {
        if (bundle != null) {
            String string = bundle.getString(BookClubReplyCard.BID);
            String string2 = bundle.getString(BookClubReplyCard.REPLY_UID);
            ReaderTask addAudioReplyTask = new AddAudioReplyTask(this.u, string, i, bundle.getString(BookClubReplyCard.REPLY_ID), string2, str, j, 8);
            addAudioReplyTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ NativeAudioQuestionDetailActivity a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    com.qq.reader.common.monitor.debug.c.e(NativeAudioQuestionDetailActivity.b, str);
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int optInt = jSONObject.optInt("code");
                        Message obtainMessage = this.a.mHandler.obtainMessage();
                        obtainMessage.obj = jSONObject;
                        switch (optInt) {
                            case -100:
                                this.a.mHandler.sendEmptyMessage(111);
                                return;
                            case 0:
                                obtainMessage.what = 6000004;
                                this.a.mHandler.sendMessage(obtainMessage);
                                return;
                            default:
                                obtainMessage.what = 6000005;
                                this.a.mHandler.sendMessage(obtainMessage);
                                return;
                        }
                    } catch (JSONException e) {
                        this.a.a(readerProtocolTask);
                    }
                    this.a.a(readerProtocolTask);
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    i.a("event_C62", false, 0, 0, null, true, false, ReaderApplication.getApplicationImp().getApplicationContext());
                    this.a.a(readerProtocolTask);
                }
            });
            g.a().a(addAudioReplyTask);
        }
    }

    private void a(ReaderProtocolTask readerProtocolTask) {
        Object jSONObject;
        try {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("message", "回复失败");
                jSONObject.put("signal", b(readerProtocolTask.getUrl()));
            } catch (Exception e) {
            }
        } catch (Exception e2) {
            jSONObject = null;
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 6000005;
        obtainMessage.obj = jSONObject;
        this.mHandler.sendMessage(obtainMessage);
    }

    private void a(Bundle bundle) {
        ReaderTask delAudioReplyTask = new DelAudioReplyTask(this.u, bundle.getString(BookClubReplyCard.BID), bundle.getString(BookClubReplyCard.REPLY_ID), bundle.getInt("CTYPE"));
        delAudioReplyTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeAudioQuestionDetailActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    switch (new JSONObject(str).optInt("code")) {
                        case -100:
                            this.a.mHandler.sendEmptyMessage(111);
                            return;
                        case 0:
                            this.a.mHandler.sendEmptyMessage(6000007);
                            return;
                        default:
                            return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                e.printStackTrace();
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        });
        g.a().a(delAudioReplyTask);
    }

    private String b(String str) {
        Matcher matcher = Pattern.compile("\\bsignal=\\b\\d*").matcher(str);
        if (matcher.find()) {
            return str.substring("signal=".length() + matcher.start(), matcher.end());
        }
        return null;
    }

    protected boolean handleMessageImp(Message message) {
        Bundle bundle;
        JSONObject jSONObject;
        switch (message.what) {
            case 111:
                af.a(getApplicationContext(), "登录态失效，请重新登录", 0).a();
                bundle = new Bundle();
                bundle.putInt("function_type", 3);
                doFunction(bundle);
                return true;
            case 506:
                try {
                    if (this.d != null) {
                        break;
                    }
                } catch (Exception e) {
                    break;
                }
                break;
            case 500000:
            case 500001:
                try {
                    this.i.setRefreshing(false);
                    if (message.obj != null) {
                        com.qq.reader.module.question.a.b bVar = (com.qq.reader.module.question.a.b) message.obj;
                        c();
                        if (this.h != null) {
                            if ("nextpage".equals(bVar.J)) {
                                if (bVar.C >= ((com.qq.reader.module.question.a.b) this.j).G) {
                                    if (bVar.s()) {
                                        this.c.e();
                                    } else {
                                        this.c.c();
                                    }
                                } else if (bVar.D < 0 && !bVar.s()) {
                                    ((com.qq.reader.module.question.a.b) this.j).F = 2;
                                }
                                this.j.addMore(bVar);
                                this.o.putInt("floor_index", ((com.qq.reader.module.question.a.b) this.j).F);
                                this.o.putInt("floor_next", ((com.qq.reader.module.question.a.b) this.j).D);
                                ((com.qq.reader.module.question.a.b) this.j).y();
                            } else {
                                AudioMediaManager.a().g();
                                this.j.a(bVar);
                                try {
                                    long l;
                                    boolean z;
                                    if (bVar.b == 0) {
                                        l = com.qq.reader.common.login.c.c().l(ReaderApplication.getApplicationImp());
                                        if (bVar.d.b() == null || l != bVar.d.b().f()) {
                                            w();
                                            ((com.qq.reader.module.question.a.b) this.j).x();
                                            if (!this.j.u()) {
                                                this.c.g();
                                            } else if (this.j.s()) {
                                                this.c.e();
                                            } else {
                                                this.c.c();
                                            }
                                            if (((com.qq.reader.module.question.a.b) this.j).C > 2) {
                                                if (this.z) {
                                                    z = true;
                                                } else {
                                                    z = false;
                                                }
                                                this.z = z;
                                            } else {
                                                this.z = false;
                                            }
                                        } else {
                                            x();
                                            Map hashMap = new HashMap();
                                            hashMap.put(s.ORIGIN, "0");
                                            i.a("event_D161", hashMap, ReaderApplication.getApplicationImp());
                                            ((com.qq.reader.module.question.a.b) this.j).x();
                                            if (!this.j.u()) {
                                                this.c.g();
                                            } else if (this.j.s()) {
                                                this.c.c();
                                            } else {
                                                this.c.e();
                                            }
                                            if (((com.qq.reader.module.question.a.b) this.j).C > 2) {
                                                this.z = false;
                                            } else {
                                                if (this.z) {
                                                    z = false;
                                                } else {
                                                    z = true;
                                                }
                                                this.z = z;
                                            }
                                        }
                                    } else {
                                        if (bVar.b == 1) {
                                            Bundle q = q();
                                            this.C.a(q);
                                            a(q, 0);
                                            w();
                                            Map hashMap2 = new HashMap();
                                            hashMap2.put(s.ORIGIN, "1");
                                            l = com.qq.reader.common.login.c.c().l(ReaderApplication.getApplicationImp());
                                            if (bVar.d.b() == null || l != bVar.d.b().f()) {
                                                hashMap2.put("isown", "0");
                                            } else {
                                                hashMap2.put("isown", "1");
                                            }
                                            i.a("event_D161", hashMap2, ReaderApplication.getApplicationImp());
                                        } else {
                                            w();
                                        }
                                        ((com.qq.reader.module.question.a.b) this.j).x();
                                        if (!this.j.u()) {
                                            this.c.g();
                                        } else if (this.j.s()) {
                                            this.c.e();
                                        } else {
                                            this.c.c();
                                        }
                                        if (((com.qq.reader.module.question.a.b) this.j).C > 2) {
                                            if (this.z) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            this.z = z;
                                        } else {
                                            this.z = false;
                                        }
                                    }
                                } catch (Exception e2) {
                                    com.qq.reader.common.monitor.debug.c.e("NativeAudioQuestion", e2.getMessage());
                                }
                            }
                            s();
                            if (this.z) {
                                this.mHandler.postDelayed(new Runnable(this) {
                                    final /* synthetic */ NativeAudioQuestionDetailActivity a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.c.setSelection(3);
                                    }
                                }, 200);
                                this.z = false;
                            }
                        }
                    }
                } catch (Exception e22) {
                    com.qq.reader.common.monitor.debug.c.e("NativeAudioQuestionDetail", e22.getMessage());
                }
                this.n = false;
                return true;
            case 500004:
                p();
                if (this.a == null) {
                    d();
                } else if (this.n) {
                    if (!((com.qq.reader.module.question.a.b) this.a).H) {
                        this.c.d();
                    } else if (getFromActivity() != null) {
                        af.a(getFromActivity(), "网络异常，请稍后重试", 0).a();
                    }
                }
                this.n = false;
                return true;
            case 500005:
                f();
                break;
            case 1100102:
                switch (message.arg1) {
                    case 0:
                        af.a(this, "提问已拒绝", 0).a();
                        b(this.o);
                        break;
                    default:
                        if (message.obj == null) {
                            message.obj = getString(R.string.net_not_available);
                        }
                        af.a(this, message.obj.toString(), 0).a();
                        break;
                }
                return true;
            case 1100103:
                switch (message.arg1) {
                    case 0:
                        af.a(this, "举报成功", 0).a();
                        b(this.o);
                        break;
                    default:
                        if (message.obj == null) {
                            message.obj = getString(R.string.net_not_available);
                        }
                        af.a(this, message.obj.toString(), 0).a();
                        break;
                }
                return true;
            case 1100201:
                if (this.v == null) {
                    this.v = new c(this);
                }
                try {
                    if (!isFinishing()) {
                        this.v.b(R.string.audio_quiz_upload);
                        this.v.f_();
                    }
                } catch (Exception e3) {
                }
                return true;
            case 1100202:
                return true;
            case 1100203:
                return true;
            case 1100204:
                if (this.v != null) {
                    try {
                        this.v.dismiss();
                    } catch (Exception e4) {
                    }
                }
                af.a(getContext(), R.string.author_edit_fenda_server_error, 1).a();
                return true;
            case 1100205:
                bundle = new Bundle(this.o);
                bundle.putInt("audio_answered", 1);
                b(bundle);
                if (this.v != null) {
                    try {
                        this.v.dismiss();
                    } catch (Exception e5) {
                    }
                }
                return true;
            case 1100206:
                if (this.v != null) {
                    try {
                        this.v.dismiss();
                    } catch (Exception e6) {
                    }
                }
                af.a(getContext(), R.string.net_not_available, 1).a();
                return true;
            case 1100401:
                this.q.setTimelong(message.arg1);
                com.qq.reader.common.monitor.debug.c.e(b, "onInitExistAudio duration is " + message.arg1);
                return true;
            case 1100402:
                com.qq.reader.common.monitor.debug.c.e(b, "onRecordError");
                getWindow().clearFlags(128);
                this.q.setControllerState(0);
                z();
                return true;
            case 1100403:
                com.qq.reader.common.monitor.debug.c.e(b, "onPlayingPercent is " + message.arg1);
                this.q.setProcessByPercent(((float) message.arg1) / 1000.0f);
                this.q.setTimelong(message.arg2);
                return true;
            case 1100404:
                com.qq.reader.common.monitor.debug.c.e(b, "onRecoding is " + message.arg1);
                this.q.setProgress(message.arg1);
                this.q.setTimelong(message.arg1);
                return true;
            case 1100405:
                com.qq.reader.common.monitor.debug.c.e(b, "onStartRecord");
                getFromActivity().sendBroadcast(new Intent(e.f));
                getWindow().addFlags(128);
                this.q.setMaxProgress(message.arg1);
                this.q.setControllerState(2);
                return true;
            case 1100406:
                com.qq.reader.common.monitor.debug.c.e(b, "onStopRecord");
                getWindow().clearFlags(128);
                this.q.setControllerState(1);
                this.q.setTimelong(message.arg1);
                if (message.arg1 <= 1) {
                    if (message.arg2 == 1) {
                        af.a(this, R.string.audio_controller_toast_tooshort, 0).a();
                    }
                    this.s.e();
                }
                return true;
            case 1100407:
                getFromActivity().sendBroadcast(new Intent(e.f));
                getWindow().addFlags(128);
                this.q.setControllerState(3);
                return true;
            case 1100408:
                com.qq.reader.common.monitor.debug.c.e(b, "onStopPlay");
                getWindow().clearFlags(128);
                this.q.setControllerState(1);
                this.q.setTimelong(message.arg1);
                return true;
            case 1100409:
                com.qq.reader.common.monitor.debug.c.e(b, "onDeletedFile true");
                this.q.setControllerState(0);
                return true;
            case 6000004:
                af.a(getApplicationContext(), "回复成功", 0).a();
                jSONObject = (JSONObject) message.obj;
                JSONObject optJSONObject = jSONObject.optJSONObject("reply");
                if (this.j instanceof com.qq.reader.module.question.a.b) {
                    try {
                        com.qq.reader.module.question.a.b bVar2 = (com.qq.reader.module.question.a.b) this.j;
                        bVar2.d(com.qq.reader.module.question.a.b.b(Long.valueOf(jSONObject.optString("signal")).longValue()));
                        if (bVar2.d(optJSONObject)) {
                            ((com.qq.reader.module.question.a.b) this.j).G = optJSONObject.optInt("index");
                            s();
                        }
                    } catch (Exception e222) {
                        e222.printStackTrace();
                    }
                }
                return true;
            case 6000005:
                if (message.obj != null) {
                    try {
                        jSONObject = (JSONObject) message.obj;
                        af.a(getApplicationContext(), jSONObject.optString("message"), 0).a();
                        String b = com.qq.reader.module.question.a.b.b(Long.valueOf(jSONObject.optString("signal")).longValue());
                        if (this.j instanceof com.qq.reader.module.question.a.b) {
                            ((com.qq.reader.module.question.a.b) this.j).d(b);
                            s();
                        }
                    } catch (Exception e2222) {
                        e2222.printStackTrace();
                    }
                }
                return true;
            case 6000006:
                af.a(ReaderApplication.getApplicationImp(), "回复太快了，休息会重试", 0).a();
                s();
                return true;
            case 6000008:
                af.a(getApplicationContext(), "操作失败，请稍后重试", 0).a();
                return true;
        }
        return super.handleMessageImp(message);
    }

    public void e_() {
        if (this.i != null) {
            if (this.j != null) {
                this.j.a(1001);
            }
            a(false, true);
        }
    }

    private void s() {
        ((com.qq.reader.module.question.a.b) this.j).x();
        ((com.qq.reader.module.question.a.b) this.j).z();
        ((com.qq.reader.module.question.a.b) this.j).B();
        if (this.h.b() || this.c.getAdapter() == null) {
            this.c.setAdapter(this.h);
        } else {
            this.h.notifyDataSetChanged();
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
        u();
    }

    protected void d() {
        if (this.i != null) {
            this.i.setRefreshing(false);
        }
        this.e.setVisibility(8);
        this.f.setVisibility(0);
    }

    private void b(Bundle bundle) {
        this.n = true;
        try {
            this.j = com.qq.reader.module.bookstore.qnative.e.a().a(bundle, this);
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
        w();
    }

    protected void f() {
        if (!this.n) {
            if (this.p == null) {
                this.p = new Bundle(this.o);
            }
            this.p.putLong("KEY_PAGEINDEX", -1);
            this.p.putString("URL_BUILD_PERE_SIGNAL", "nextpage");
            if (this.p.containsKey("URL_DATA_QURL")) {
                this.p.putString("URL_DATA_QURL", "");
            }
            c(this.p);
            this.a = com.qq.reader.module.bookstore.qnative.e.a().a(this.p, this);
            this.a.a(1001);
            d.b().a(getApplicationContext(), this.a, this.mHandler, false);
            this.n = true;
        }
    }

    private void c(Bundle bundle) {
        if (this.x >= 2) {
            bundle.putInt("floor_index", this.x);
            bundle.putInt("floor_next", this.y);
            bundle.putBoolean("page_replyloadpre", true);
            this.x = -1;
            return;
        }
        bundle.putInt("floor_index", ((com.qq.reader.module.question.a.b) this.j).A() + 1);
        bundle.putInt("floor_next", 20);
        bundle.putBoolean("page_replyloadpre", false);
    }

    private void t() {
        if (this.B != null) {
            this.B.setHasSendState(true);
            this.B.b();
            a(null);
        }
        if (this.A != null) {
            this.A.setVisibility(4);
        }
    }

    private void u() {
        if (this.B != null && this.B.getVisibility() == 0) {
            this.B.setVisibility(8);
            this.B.b();
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    public void doFunction(Bundle bundle) {
        String string = bundle.getString("audio_action");
        if ("audio_detail_refuse".equals(string)) {
            try {
                if (!isFinishing()) {
                    a((int) com.qq.reader.common.download.task.f.INSUFFICIENT_MEMORY).f_();
                    ao.e.a(this.r, this);
                }
            } catch (Exception e) {
            }
        } else if ("audio_detail_report".equals(string)) {
            try {
                if (!isFinishing()) {
                    a((int) com.qq.reader.common.download.task.f.USER_CANCELLED).f_();
                }
            } catch (Exception e2) {
            }
        } else if ("audio_detail_listen".equals(string)) {
            try {
                ((com.qq.reader.module.question.a.b) this.j).d.a().b(2);
            } catch (Exception e3) {
                com.qq.reader.common.monitor.debug.c.e(b, e3.getMessage());
            }
            a(null);
        }
        int i = bundle.getInt("function_type");
        if (i == 6) {
            this.x = bundle.getInt("floor_index", -1);
            this.y = bundle.getInt("floor_next", -1);
            this.mHandler.sendEmptyMessage(500005);
        } else if (i == 4) {
            switch (bundle.getInt(BookClubReplyCard.REPLY_STATUS)) {
                case 1:
                    if (this.C.a()) {
                        a(bundle, 1);
                        return;
                    }
                    return;
                case 2:
                case 3:
                case 7:
                    d(bundle).f_();
                    return;
                case 4:
                    a(bundle, 0);
                    return;
                default:
                    return;
            }
        } else if (i == 3) {
            startLogin();
            setLoginNextTask(new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeAudioQuestionDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.a.a(null);
                            return;
                        case 2:
                        case 3:
                            this.a.setLoginNextTask(null);
                            return;
                        default:
                            return;
                    }
                }
            });
        }
    }

    private com.qq.reader.view.linearmenu.b d(Bundle bundle) {
        final com.qq.reader.view.linearmenu.b bVar = new com.qq.reader.view.linearmenu.b(this);
        bVar.a(1, "回复", bundle);
        int i = bundle.getInt(BookClubReplyCard.REPLY_STATUS);
        this.o.getString("KEY_JUMP_PAGENAME");
        switch (i) {
            case 2:
                bVar.a(0, "删除", bundle);
                break;
        }
        bVar.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ NativeAudioQuestionDetailActivity b;

            public boolean a(int i, Bundle bundle) {
                bVar.cancel();
                switch (i) {
                    case 0:
                        this.b.showFragmentDialog(609, bundle);
                        return true;
                    case 1:
                        bundle.putBoolean("SHOWKEYBOARD", true);
                        this.b.a(bundle, 1);
                        return true;
                    default:
                        return false;
                }
            }
        });
        bVar.a(new OnCancelListener(this) {
            final /* synthetic */ NativeAudioQuestionDetailActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getWindow().closeAllPanels();
            }
        });
        return bVar;
    }

    protected Dialog createDialog(int i, final Bundle bundle) {
        AlertDialog alertDialog = (AlertDialog) q.a(this, i, null);
        switch (i) {
            case 100:
                alertDialog.a(getString(R.string.audio_controller_dialog_rerecord));
                alertDialog.a(R.string.audio_controller_dialog_rerecord_submit, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioQuestionDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.s.e();
                    }
                });
                alertDialog.b(R.string.readeralert_cancel, null);
                return alertDialog;
            case 609:
                return new com.qq.reader.view.AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a(R.string.bookstand_menu_del).b(R.string.bookclub_reply_delete_msg).a(R.string.bookclub_reply_delete, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioQuestionDetailActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.a(bundle);
                        if (this.b.j instanceof com.qq.reader.module.question.a.b) {
                            ((com.qq.reader.module.question.a.b) this.b.j).d(bundle.getString(BookClubReplyCard.REPLY_ID));
                            bundle.getString("COMMENT_ID");
                            if (this.b.j.u()) {
                                this.b.c.g();
                            }
                            this.b.s();
                        }
                    }
                }).b(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioQuestionDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
            default:
                return alertDialog;
        }
    }

    public BaseDialog a(int i) {
        switch (i) {
            case com.qq.reader.common.download.task.f.INSUFFICIENT_MEMORY /*901*/:
                BaseDialog aaVar = new aa(this);
                aaVar.g().setSoftInputMode(32);
                final View view = (EditText) LayoutInflater.from(this).inflate(R.layout.audio_dialog_refuese_content, aaVar.h(), false);
                view.addTextChangedListener(new TextWatcher(this) {
                    final /* synthetic */ NativeAudioQuestionDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void afterTextChanged(Editable editable) {
                        if (editable.toString().length() >= 50) {
                            af.a(this.a.getFromActivity(), R.string.audio_quiz_maxinput_toast, 0).a();
                        }
                    }
                });
                view.requestFocus();
                aaVar.a(false);
                aaVar.a(view);
                aaVar.b(R.string.audio_question_refusetitle);
                aaVar.a(R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioQuestionDetailActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        g.a().a(new AudioQuestionRefuseTask(view.getText().toString(), this.b.u, new com.qq.reader.common.readertask.ordinal.c(this) {
                            final /* synthetic */ AnonymousClass8 a;

                            {
                                this.a = r1;
                            }

                            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                Message obtainMessage = this.a.b.mHandler.obtainMessage(1100102);
                                try {
                                    JSONObject jSONObject = new JSONObject(str);
                                    obtainMessage.arg1 = jSONObject.optInt("code", -1);
                                    obtainMessage.obj = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                                } catch (Exception e) {
                                    com.qq.reader.common.monitor.debug.c.e(NativeAudioQuestionDetailActivity.b, e.getMessage());
                                }
                                this.a.b.mHandler.sendMessage(obtainMessage);
                            }

                            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                                Message obtainMessage = this.a.b.mHandler.obtainMessage(1100102);
                                obtainMessage.obj = this.a.b.getString(R.string.net_not_available);
                                obtainMessage.arg1 = -1;
                                this.a.b.mHandler.sendMessage(obtainMessage);
                            }
                        }));
                        dialogInterface.dismiss();
                    }
                });
                aaVar.b(R.string.readeralert_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioQuestionDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                aaVar.a(new OnShowListener(this) {
                    final /* synthetic */ NativeAudioQuestionDetailActivity b;

                    public void onShow(DialogInterface dialogInterface) {
                        view.requestFocus();
                        ((InputMethodManager) this.b.getSystemService("input_method")).showSoftInput(view, 0);
                    }
                });
                aaVar.a(new r(this) {
                    final /* synthetic */ NativeAudioQuestionDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public t a() {
                        return null;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                        super.onDismiss(dialogInterface);
                        InputMethodManager inputMethodManager = (InputMethodManager) this.a.getSystemService("input_method");
                        if (this.a.getCurrentFocus() != null && this.a.getCurrentFocus().getWindowToken() != null) {
                            inputMethodManager.hideSoftInputFromWindow(this.a.getCurrentFocus().getWindowToken(), 2);
                        }
                    }
                });
                return aaVar;
            case com.qq.reader.common.download.task.f.USER_CANCELLED /*902*/:
                final BaseDialog cVar = new com.qq.reader.view.linearmenu.c(this);
                cVar.c(R.string.audio_question_reporttitle);
                cVar.a(4, "辱骂和人身攻击", null);
                cVar.a(2, "广告及垃圾信息", null);
                cVar.a(3, "反动", null);
                cVar.a(new com.qq.reader.view.linearmenu.a.b(this) {
                    final /* synthetic */ NativeAudioQuestionDetailActivity b;

                    public boolean a(int i, Bundle bundle) {
                        int i2;
                        switch (i) {
                            case 2:
                                i2 = 2;
                                break;
                            case 3:
                                i2 = 3;
                                break;
                            case 4:
                                i2 = 1;
                                break;
                            default:
                                i2 = 1;
                                break;
                        }
                        g.a().a(new AudioQuestionReportTask(i2, this.b.u, new com.qq.reader.common.readertask.ordinal.c(this) {
                            final /* synthetic */ AnonymousClass13 a;

                            {
                                this.a = r1;
                            }

                            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                Message obtainMessage = this.a.b.mHandler.obtainMessage(1100103);
                                try {
                                    JSONObject jSONObject = new JSONObject(str);
                                    obtainMessage.arg1 = jSONObject.optInt("code", -1);
                                    obtainMessage.obj = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                                } catch (Exception e) {
                                    com.qq.reader.common.monitor.debug.c.e(NativeAudioQuestionDetailActivity.b, e.getMessage());
                                }
                                this.a.b.mHandler.sendMessage(obtainMessage);
                            }

                            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                                Message obtainMessage = this.a.b.mHandler.obtainMessage(1100103);
                                obtainMessage.obj = this.a.b.getString(R.string.net_not_available);
                                obtainMessage.arg1 = -1;
                                this.a.b.mHandler.sendMessage(obtainMessage);
                            }
                        }));
                        cVar.dismiss();
                        return true;
                    }
                });
                cVar.f_();
                return cVar;
            default:
                return null;
        }
    }

    public void finish() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("AUDIO_DATA", ((com.qq.reader.module.question.a.b) this.j).d);
        setResult(-1, new Intent().putExtras(bundle));
        super.finish();
    }

    protected void onPause() {
        getFromActivity().getWindow().clearFlags(128);
        if (this.s != null) {
            this.s.c();
            this.s.g();
        }
        AudioMediaManager.a().g();
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.B.a();
        AudioMediaManager.a().a(this.t);
    }

    private AudioControllerPanel v() {
        if (this.q == null) {
            this.q = new AudioControllerPanel(this);
            this.q.setOrientation(1);
            this.q.setGravity(17);
            this.q.setOnButtonClickListener(new com.qq.reader.module.question.card.view.AudioControllerPanel.a(this) {
                final /* synthetic */ NativeAudioQuestionDetailActivity a;

                {
                    this.a = r1;
                }

                public void onClick(int i) {
                    switch (i) {
                        case 0:
                            try {
                                this.a.s.a(60);
                                return;
                            } catch (PlayException e) {
                                switch (e.state) {
                                    case 0:
                                        af.a(this.a.getContext(), "播放失败", 0).a();
                                        return;
                                    case 1:
                                        af.a(this.a.getContext(), "播放失败,请检查文件是否损坏", 0).a();
                                        return;
                                    case 2:
                                        af.a(this.a.getContext(), "未知错误", 0).a();
                                        return;
                                    default:
                                        return;
                                }
                            } catch (RecordException e2) {
                                switch (e2.state) {
                                    case 0:
                                        this.a.z();
                                        return;
                                    case 1:
                                        af.a(this.a.getContext(), "录音失败,请检查sd是否已满", 0).a();
                                        return;
                                    case 2:
                                        af.a(this.a.getContext(), "未知错误", 0).a();
                                        return;
                                    default:
                                        return;
                                }
                            } catch (Exception e3) {
                                af.a(this.a.getContext(), "未知错误", 0).a();
                                AudioMediaManager.a(ReaderApplication.getApplicationImp(), false);
                                return;
                            }
                        case 1:
                            this.a.s.d();
                            return;
                        case 2:
                            try {
                                if (!this.a.isFinishing()) {
                                    this.a.createDialog(100, null).show();
                                    return;
                                }
                                return;
                            } catch (Exception e4) {
                                return;
                            }
                        default:
                            return;
                    }
                }
            });
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, ao.a(300.0f));
            layoutParams.addRule(12);
            this.r.addView(this.q, layoutParams);
            this.t = ((com.qq.reader.module.question.a.b) this.j).d;
            this.s = AudioMediaManager.a();
            this.s.a(this.t, this.mHandler);
            if (this.s.h() == AudioMediaManager.i) {
                this.q.setControllerState(1);
            } else {
                this.q.setControllerState(0);
            }
        }
        return this.q;
    }

    private void w() {
        if (this.q != null) {
            this.q.setVisibility(8);
        }
    }

    private void x() {
        v().setVisibility(0);
        y();
    }

    private void y() {
        if (VERSION.SDK_INT >= 23 && android.support.v4.app.a.a(this, "android.permission.RECORD_AUDIO") != 0 && !this.D) {
            this.D = true;
            android.support.v4.app.a.a(this, new String[]{"android.permission.RECORD_AUDIO"}, 111);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (strArr.length > 0 && i == 111) {
            for (String a : strArr) {
                if (android.support.v4.app.a.a(this, a) != 0) {
                    AlertDialog a2 = new com.qq.reader.view.AlertDialog.a(this).a("权限获取失败").b("需授予以下权限才可以正常使用QQ阅读：\n录音").a("授予权限", new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ NativeAudioQuestionDetailActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            o.t(this.a, null);
                            this.a.finish();
                        }
                    }).b("退出页面", new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ NativeAudioQuestionDetailActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.finish();
                        }
                    }).a();
                    a2.setCanceledOnTouchOutside(false);
                    a2.setCancelable(false);
                    a2.setOnKeyListener(new OnKeyListener(this) {
                        final /* synthetic */ NativeAudioQuestionDetailActivity a;

                        {
                            this.a = r1;
                        }

                        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                            if (i == 84 || i == 4) {
                                return true;
                            }
                            return false;
                        }
                    });
                    a2.b(51);
                    a2.show();
                }
            }
        }
        this.D = false;
    }

    private void z() {
        try {
            if (this.w == null) {
                this.w = new com.qq.reader.view.AlertDialog.a(this).a("录音失败").b(ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_controller_dialog_permission_tips)).a(ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_controller_dialog_permission_ok), new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioQuestionDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!this.a.isFinishing()) {
                            AudioMediaManager.a().e();
                        }
                    }
                }).a();
            }
            if (!isFinishing()) {
                this.w.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
