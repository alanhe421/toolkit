package com.qq.reader.module.bookstore.qnative.card.impl;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.UserCenterSignTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.networkUtil.e;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.utils.t;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class UserCenterInfoCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private String mCommitContent;
    private int mGender;
    private int mGrowLevel;
    private int mIsGetGift;
    private int mIsOwn;
    private int mIsPayMonth;
    private t mNMC;
    private long mPraiseNum;
    private long mReadTime;
    private String mSign;
    private TextView mSignTv;
    private long mUpgradeValue;
    private a mUserCenterSignDialog;
    private String mUserIconUrl;
    private int mVipLevel;

    class a extends BaseDialog {
        Activity a;
        EditText b;
        TextView c;
        TextView d;
        final /* synthetic */ UserCenterInfoCard e;

        public void dismiss() {
            super.dismiss();
        }

        public a(final UserCenterInfoCard userCenterInfoCard, Activity activity, String str) {
            this.e = userCenterInfoCard;
            this.a = activity;
            if (this.f == null) {
                a(this.a, null, R.layout.user_center_sign_dialog_layout, 1, true);
            }
            this.f.setCanceledOnTouchOutside(false);
            this.f.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ a b;

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.b.e.mNMC != null) {
                        this.b.e.mNMC.a();
                    }
                }
            });
            this.b = (EditText) this.f.findViewById(R.id.user_center_sign_et);
            this.c = (TextView) this.f.findViewById(R.id.user_center_sign_commit);
            this.d = (TextView) this.f.findViewById(R.id.user_center_sign_cancel);
            if (!(TextUtils.isEmpty(str) || str.equals("编辑个性签名"))) {
                this.b.setText(str);
                this.b.setSelection(str.length());
            }
            this.b.setOnFocusChangeListener(new OnFocusChangeListener(this) {
                final /* synthetic */ a b;

                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        this.b.f.getWindow().setSoftInputMode(5);
                    }
                }
            });
            this.b.addTextChangedListener(new TextWatcher(this) {
                final /* synthetic */ a b;

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    Object trim = this.b.b.getText().toString().trim();
                    this.b.e.mCommitContent = trim;
                    if (!TextUtils.isEmpty(trim)) {
                        this.b.c.setEnabled(true);
                        if (trim.length() >= 30) {
                            this.b.e.showToast("已达字数上限");
                        }
                    }
                }
            });
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (e.a(this.b.e.getEvnetListener().getFromActivity().getApplicationContext())) {
                        this.b.e.mCommitContent = this.b.b.getText().toString().trim();
                        g.a().a(new UserCenterSignTask(new c(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                if (!TextUtils.isEmpty(str)) {
                                    try {
                                        JSONObject jSONObject = new JSONObject(str);
                                        int optInt = jSONObject.optInt("code");
                                        final String optString = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                                        if (optInt == 0) {
                                            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                                final /* synthetic */ AnonymousClass2 a;

                                                {
                                                    this.a = r1;
                                                }

                                                public void run() {
                                                    if (this.a.a.b.e.mUserCenterSignDialog != null) {
                                                        this.a.a.b.e.mUserCenterSignDialog.cancel();
                                                    }
                                                    this.a.a.b.e.showToast("提交完成");
                                                    if (TextUtils.isEmpty(this.a.a.b.e.mCommitContent)) {
                                                        this.a.a.b.e.mSignTv.setText("编辑个性签名");
                                                    } else {
                                                        this.a.a.b.e.mSignTv.setText(this.a.a.b.e.mCommitContent);
                                                    }
                                                }
                                            });
                                        } else {
                                            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                                final /* synthetic */ AnonymousClass2 b;

                                                public void run() {
                                                    this.b.a.b.e.showToast(optString);
                                                }
                                            });
                                        }
                                    } catch (Exception e) {
                                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass2 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                this.a.a.b.e.showToast("出错啦，请稍后重试");
                                            }
                                        });
                                    }
                                }
                            }

                            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass2 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.a.b.e.showToast("出错啦，请稍后重试");
                                    }
                                });
                            }
                        }, this.b.e.mCommitContent));
                        return;
                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.e.showToast("网络异常，请稍后重试");
                        }
                    });
                }
            });
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    ((InputMethodManager) this.b.a.getSystemService("input_method")).hideSoftInputFromWindow(this.b.f.getCurrentFocus().getWindowToken(), 0);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.cancel();
                        }
                    }, 200);
                }
            });
            userCenterInfoCard.mNMC = new t(this.a, true);
            userCenterInfoCard.mNMC.c(true);
        }
    }

    public UserCenterInfoCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_info_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mIsPayMonth = jSONObject.optInt("vipStatus", 0);
        this.mVipLevel = jSONObject.optInt("vipLevel");
        this.mGrowLevel = jSONObject.optInt("growLevel");
        this.mPraiseNum = jSONObject.optLong("praiseNum");
        this.mReadTime = jSONObject.optLong("readTime");
        this.mUpgradeValue = jSONObject.optLong("upgradeValue");
        this.mGender = jSONObject.optInt("gender");
        this.mIsGetGift = jSONObject.optInt("isGetGift");
        this.mIsOwn = jSONObject.optInt("isOwn");
        this.mUserIconUrl = jSONObject.optString("userIcon");
        this.mSign = jSONObject.optString("sign");
        return true;
    }

    protected void parseEmpty() {
    }

    public void attachView() {
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.user_center_user_icon);
        this.mSignTv = (TextView) ap.a(getRootView(), R.id.user_center_sign);
        this.mSignTv.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterInfoCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.mIsOwn != 0) {
                    i.a("event_D126", null, ReaderApplication.getApplicationImp());
                    if (this.a.mUserCenterSignDialog == null) {
                        this.a.mUserCenterSignDialog = new a(this.a, this.a.getEvnetListener().getFromActivity(), this.a.mSignTv.getText().toString());
                    }
                    this.a.mUserCenterSignDialog.f_();
                }
            }
        });
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.user_center_month_img);
        TextView textView = (TextView) ap.a(getRootView(), R.id.user_center_vip_level_tv);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.user_center_grow_level_tv);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.user_center_read_time_hour_count_tv);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.user_center_read_time_hour_tv);
        TextView textView5 = (TextView) ap.a(getRootView(), R.id.user_center_read_time_minute_count_tv);
        TextView textView6 = (TextView) ap.a(getRootView(), R.id.user_center_read_time_minute_tv);
        TextView textView7 = (TextView) ap.a(getRootView(), R.id.user_center_praise_num_tv);
        TextView textView8 = (TextView) ap.a(getRootView(), R.id.user_center_upgrade_value_tv);
        ImageView imageView3 = (ImageView) ap.a(getRootView(), R.id.user_center_gender_icon);
        ImageView imageView4 = (ImageView) ap.a(getRootView(), R.id.user_center_get_gift_arrow_iv);
        TextView textView9 = (TextView) ap.a(getRootView(), R.id.user_center_get_gift_tv);
        textView9.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterInfoCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_D125", null, ReaderApplication.getApplicationImp());
                o.a(this.a.getEvnetListener().getFromActivity(), com.qq.reader.common.login.c.c().e(), 1, null);
            }
        });
        View a = ap.a(getRootView(), R.id.user_center_my_grade_rl);
        View a2 = ap.a(getRootView(), R.id.user_center_my_grade_divider);
        a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterInfoCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_D128", null, ReaderApplication.getApplicationImp());
                o.a(this.a.getEvnetListener().getFromActivity(), com.qq.reader.common.login.c.c().e(), null);
            }
        });
        if (this.mGender == 0) {
            imageView3.setImageResource(R.drawable.user_center_male_icon);
        } else if (this.mGender == 1) {
            imageView3.setImageResource(R.drawable.user_center_female_icon);
        } else {
            imageView3.setVisibility(8);
        }
        ao.a(this.mIsPayMonth, imageView2, true);
        Map hashMap;
        if (this.mIsOwn == 1) {
            hashMap = new HashMap();
            hashMap.put("isOwn", this.mIsOwn + "");
            i.a("event_D123", hashMap, ReaderApplication.getApplicationImp());
            i.a("event_D127", null, ReaderApplication.getApplicationImp());
            a.setVisibility(0);
            a2.setVisibility(0);
        } else {
            a.setVisibility(8);
            a2.setVisibility(8);
            hashMap = new HashMap();
            hashMap.put("isOwn", this.mIsOwn + "");
            i.a("event_D123", hashMap, ReaderApplication.getApplicationImp());
        }
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.mUserIconUrl, imageView, com.qq.reader.common.imageloader.a.a().p());
        imageView2.setVisibility(0);
        textView.setText("VIP" + this.mVipLevel);
        textView.setVisibility(0);
        textView2.setText("LV" + this.mGrowLevel);
        textView2.setVisibility(0);
        textView7.setText(this.mPraiseNum + "");
        if (this.mReadTime == 0) {
            textView3.setVisibility(8);
            textView4.setVisibility(8);
            textView5.setVisibility(0);
            textView6.setVisibility(0);
            textView5.setText("0");
            textView6.setText("分钟");
        } else {
            long j = ((this.mReadTime / 1000) / 60) / 60;
            long j2 = (this.mReadTime / 1000) / 60;
            if (j < 1) {
                textView3.setVisibility(8);
                textView4.setVisibility(8);
                textView5.setVisibility(0);
                textView6.setVisibility(0);
                textView5.setText(j2 + "");
                textView6.setText("分钟");
            } else if (j > 9999) {
                textView3.setVisibility(0);
                textView4.setVisibility(0);
                if (j % 24 == 0) {
                    textView5.setVisibility(8);
                    textView6.setVisibility(8);
                    textView3.setText((j / 24) + "");
                    textView4.setText("天");
                } else {
                    textView5.setVisibility(0);
                    textView6.setVisibility(0);
                    textView3.setText((j / 24) + "");
                    textView4.setText("天");
                    textView5.setText((j % 24) + "");
                    textView6.setText("小时");
                }
            } else {
                textView3.setVisibility(0);
                textView4.setVisibility(0);
                if (j2 % 60 == 0) {
                    textView5.setVisibility(8);
                    textView6.setVisibility(8);
                    textView3.setText((j2 / 60) + "");
                    textView4.setText("小时");
                } else {
                    textView5.setVisibility(0);
                    textView6.setVisibility(0);
                    textView3.setText((j2 / 60) + "");
                    textView4.setText("小时");
                    textView5.setText((j2 % 60) + "");
                    textView6.setText("分钟");
                }
            }
        }
        textView8.setText("再有" + this.mUpgradeValue + "成长值升级");
        if (this.mIsGetGift == 1) {
            textView9.setVisibility(8);
            imageView4.setVisibility(0);
        } else {
            textView9.setVisibility(0);
            i.a("event_D124", null, ReaderApplication.getApplicationImp());
            imageView4.setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.mSign)) {
            this.mSignTv.setVisibility(0);
            this.mSignTv.setText(this.mSign);
        } else if (this.mIsOwn == 0) {
            this.mSignTv.setVisibility(4);
        } else {
            this.mSignTv.setVisibility(0);
            this.mSignTv.setText("编辑个性签名");
        }
    }

    private void showToast(String str) {
        af.a(getEvnetListener().getFromActivity().getApplicationContext(), (CharSequence) str, 0).a();
    }
}
