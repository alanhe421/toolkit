package com.qq.reader.module.bookstore.qnative.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import com.qq.reader.common.login.a.e;
import com.qq.reader.common.login.define.a;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.GetBindWXUserInfoTask;
import com.qq.reader.common.readertask.protocol.SubmitAuthorWXInfoTask;
import com.qq.reader.common.readertask.protocol.WeixinLoginTask;
import com.qq.reader.module.bookstore.qnative.card.impl.AuthorWXInfoBindCard.WXBasicInfoItem;
import com.qq.reader.view.af;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.connect.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeAuthorWXBindActivity extends NativeBookStoreTwoLevelActivity {
    private WXBasicInfoItem k;
    private String w;
    private final int x = 1;
    private final int y = 2;
    private BroadcastReceiver z = new BroadcastReceiver(this) {
        final /* synthetic */ NativeAuthorWXBindActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.qq.reader.wxlogin.code".equals(action)) {
                action = intent.getStringExtra("status");
                if (action.equalsIgnoreCase("success")) {
                    this.a.showPorgress("绑定中...");
                    action = intent.getStringExtra("code");
                    if (action != null) {
                        ReaderTask weixinLoginTask = new WeixinLoginTask(action);
                        weixinLoginTask.registerNetTaskListener(new c(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                if (this.a.a.a(str)) {
                                    try {
                                        JSONObject jSONObject = new JSONObject(str);
                                        String optString = jSONObject.optString(Constants.PARAM_ACCESS_TOKEN);
                                        String optString2 = jSONObject.optString("openid");
                                        String optString3 = jSONObject.optString("unionid");
                                        String optString4 = jSONObject.optString(Constants.PARAM_SCOPE);
                                        String optString5 = jSONObject.optString("refresh_token");
                                        if (this.a.a.k != null) {
                                            this.a.a.k.openId = optString2;
                                            this.a.a.k.unionId = optString3;
                                            this.a.a.k.accessToken = optString;
                                            this.a.a.k.scope = optString4;
                                            this.a.a.k.refreshToken = optString5;
                                            this.a.a.a(this.a.a.k);
                                        }
                                    } catch (JSONException e) {
                                        com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                                        this.a.a.x();
                                    }
                                }
                            }

                            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                                this.a.a.x();
                            }
                        });
                        g.a().a(weixinLoginTask);
                    }
                } else if (action.equalsIgnoreCase("cancel") || action.equalsIgnoreCase("error")) {
                    this.a.x();
                }
            } else if ("BROADCAST_ACTION_WX_BIND_START".equals(action)) {
                this.a.k = (WXBasicInfoItem) intent.getSerializableExtra("info");
                if (com.qq.reader.common.login.c.b() && com.qq.reader.common.login.c.c().d() == 2) {
                    this.a.k.accessToken = a.d(this.a);
                    this.a.k.avatarUrl = a.g(this.a);
                    this.a.k.openId = a.j(this.a);
                    this.a.k.unionId = a.m(this.a);
                    this.a.k.refreshToken = a.i(this.a);
                    this.a.k.nickName = a.f(this.a);
                    this.a.k.scope = a.n(this.a);
                    this.a.b(this.a.k);
                    return;
                }
                this.a.z();
            }
        }
    };

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                af.a((Context) this, (CharSequence) "绑定成功", 0).a();
                return true;
            case 2:
                af.a((Context) this, (CharSequence) "绑定取消", 0).a();
                return true;
            default:
                return super.handleMessage(message);
        }
    }

    private void x() {
        progressCancel();
        setResult(0);
        this.mHandler.sendEmptyMessage(2);
        finish();
    }

    private void y() {
        progressCancel();
        setResult(-1);
        this.mHandler.sendEmptyMessage(1);
        finish();
    }

    public void onStop() {
        super.onStop();
        progressCancel();
    }

    private void z() {
        if (WXApiManager.getInstance(this).isWXinstalled()) {
            showPorgress("正在登录");
            e.a((Context) this).a((Activity) this, null);
            return;
        }
        af.a((Context) this, (CharSequence) "请先安装微信客户端", 0).a();
    }

    private boolean a(String str) {
        try {
            if (new JSONObject(str).optInt("errcode") > 40000) {
                return false;
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void a(final WXBasicInfoItem wXBasicInfoItem) {
        if (wXBasicInfoItem != null) {
            ReaderTask getBindWXUserInfoTask = new GetBindWXUserInfoTask(wXBasicInfoItem);
            getBindWXUserInfoTask.registerNetTaskListener(new c(this) {
                final /* synthetic */ NativeAuthorWXBindActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String optString = jSONObject.optString("nickname");
                        String optString2 = jSONObject.optString("avatar");
                        if (wXBasicInfoItem != null) {
                            wXBasicInfoItem.nickName = optString;
                            wXBasicInfoItem.avatarUrl = optString2;
                        }
                        this.b.b(wXBasicInfoItem);
                    } catch (JSONException e) {
                        com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                        this.b.x();
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    com.qq.reader.common.monitor.debug.c.e("Error", exception.getMessage());
                    exception.printStackTrace();
                    this.b.x();
                }
            });
            getBindWXUserInfoTask.setPriority(4);
            g.a().a(getBindWXUserInfoTask);
        }
    }

    private void b(WXBasicInfoItem wXBasicInfoItem) {
        if (wXBasicInfoItem != null) {
            wXBasicInfoItem.authorId = this.w;
        }
        g.a().a(new SubmitAuthorWXInfoTask(new c(this) {
            final /* synthetic */ NativeAuthorWXBindActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    if (new JSONObject(str).optInt("code") == 0) {
                        this.a.y();
                    } else {
                        this.a.x();
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                    this.a.y();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                com.qq.reader.common.monitor.debug.c.e("Error", exception.getMessage());
                this.a.x();
            }
        }, wXBasicInfoItem));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.w = getIntent().getStringExtra("AUTHORPAGE_KEY_AUTHORID");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qq.reader.wxlogin.code");
        intentFilter.addAction("BROADCAST_ACTION_WX_BIND_START");
        registerReceiver(this.z, intentFilter);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.z);
    }
}
