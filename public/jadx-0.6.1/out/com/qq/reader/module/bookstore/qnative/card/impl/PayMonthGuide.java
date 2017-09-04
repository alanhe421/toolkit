package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.ProfileNetTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.search.SearchTabInfo.SearchActionTagLv3InitialDataModel;
import com.qq.reader.widget.UserCircleImageView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PayMonthGuide extends a implements OnClickListener {
    private static final String TAG = "PayMonthGuide";
    private c mBindActionJump;
    private c mBindActionLogin;
    private Context mContext;
    private boolean notShowBottom;

    public PayMonthGuide(b bVar, String str) {
        super(bVar, str);
        this.mDataState = 1001;
        this.mBindActionJump = new c(null);
        Bundle a = this.mBindActionJump.a();
        a.putInt("function_type", 0);
        a.putString("KEY_JUMP_PAGENAME", "webpage");
        a.putString("com.qq.reader.WebContent", "");
        this.mBindActionLogin = new c(null);
        this.mBindActionLogin.a().putInt("function_type", 3);
        this.mContext = ReaderApplication.getApplicationImp();
    }

    public PayMonthGuide(b bVar, String str, boolean z) {
        this(bVar, str);
        this.notShowBottom = z;
    }

    public boolean isExpired() {
        return false;
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_fullwidthbtn;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }

    private View getProfileLayout() {
        return ap.a(getRootView(), R.id.profile_layout);
    }

    private TextView getFullWidthButton() {
        return (TextView) ap.a(getRootView(), R.id.fullwidth_button);
    }

    private UserCircleImageView getAvatar() {
        UserCircleImageView userCircleImageView = (UserCircleImageView) ap.a(getRootView(), R.id.profile_avatar);
        userCircleImageView.setBorderColor(ReaderApplication.getApplicationContext().getResources().getColor(R.color.pay_month_guide_user_circle_img_border));
        userCircleImageView.setBorderWidth(1);
        return userCircleImageView;
    }

    private ImageView getBookVipimg() {
        return (ImageView) ap.a(getRootView(), R.id.profile_vip_img);
    }

    private TextView getName() {
        return (TextView) ap.a(getRootView(), R.id.profile_name);
    }

    private TextView getBookVipInfo() {
        return (TextView) ap.a(getRootView(), R.id.profile_vipinfo);
    }

    private View getMonthvipIntroLayout() {
        return ap.a(getRootView(), R.id.monthvip_intro_layout);
    }

    private View getMonthvipActivityLayout() {
        return ap.a(getRootView(), R.id.monthvip_activity_layout);
    }

    private View getMonthvipUserRedTip() {
        return ap.a(getRootView(), R.id.monthvip_activity_redtip);
    }

    private View getNoMonthvipUserRedTip() {
        return ap.a(getRootView(), R.id.monthvip_activity_redtip);
    }

    public void attachView() {
        initViews();
        getFullWidthButton().setOnClickListener(this);
        if (isLogin()) {
            getProfileData();
        }
    }

    public void refresh() {
        f.d(TAG, "refresh ");
        if (isLogin()) {
            getProfileData();
        }
        super.refresh();
    }

    private void initViews() {
        if (getProfileLayout() != null) {
            getFullWidthButton().setEnabled(true);
            TextView textView = (TextView) ap.a(getRootView(), R.id.monthvip_privilege_text);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.monthvip_activity_text);
            TextView textView3 = (TextView) ap.a(getRootView(), R.id.tv_pay_guide_classify);
            View a = ap.a(getRootView(), R.id.monthvip_activity_left_divider);
            if (isLogin()) {
                com.qq.reader.common.login.b.a loginUser = getLoginUser();
                try {
                    getName().setText(loginUser.a());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String b = loginUser.b();
                getAvatar().setBorderWidth(1);
                getAvatar().setBorderColor(this.mContext.getResources().getColor(R.color.avatar_circle_mask_color));
                setAvatarImage(getAvatar(), b, "", new OnClickListener(this) {
                    final /* synthetic */ PayMonthGuide a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (!this.a.isLogin()) {
                            this.a.mBindActionLogin.a(this.a.getEvnetListener());
                        }
                    }
                });
                getProfileLayout().setVisibility(0);
                getFullWidthButton().setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.paymonth_openvip_textcolor));
                if (loginUser.j(ReaderApplication.getApplicationImp())) {
                    getBookVipInfo().setText("已开通");
                    getFullWidthButton().setText("开通包月");
                    if (loginUser.c(ReaderApplication.getApplicationImp().getApplicationContext()) == 1) {
                        getBookVipInfo().setText("已开通");
                        getFullWidthButton().setText("开通包月");
                        getFullWidthButton().setBackgroundResource(R.drawable.open_vip_disable);
                        getFullWidthButton().setVisibility(8);
                        getFullWidthButton().setEnabled(false);
                    } else if (loginUser.c(ReaderApplication.getApplicationImp().getApplicationContext()) == 2) {
                        getFullWidthButton().setText("续费包月");
                        getBookVipInfo().setText("" + loginUser.h(ReaderApplication.getApplicationImp()) + "到期");
                        getFullWidthButton().setBackgroundDrawable(ao.a(ReaderApplication.getApplicationImp(), (int) R.drawable.open_vip_normal, (int) R.drawable.open_vip_press));
                    }
                    getBookVipimg().setVisibility(0);
                    ao.a(loginUser.k(ReaderApplication.getApplicationImp()), getBookVipimg(), true);
                    if (com.qq.reader.cservice.adv.c.a(false)) {
                        getMonthvipUserRedTip().setVisibility(0);
                    }
                    textView2.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.paymonth_vip_activity_entrance_text));
                    textView3.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.paymonth_vip_classify_entrance_text));
                    getMonthvipIntroLayout().setVisibility(8);
                    a.setVisibility(8);
                } else {
                    getBookVipInfo().setText("开通包月，尊享特权");
                    getBookVipimg().setVisibility(0);
                    getBookVipimg().setImageResource(R.drawable.bookvip_none);
                    getFullWidthButton().setText("开通包月");
                    getFullWidthButton().setBackgroundDrawable(ao.a(ReaderApplication.getApplicationImp(), (int) R.drawable.open_vip_normal, (int) R.drawable.open_vip_press));
                    if (com.qq.reader.cservice.adv.c.a(false)) {
                        getNoMonthvipUserRedTip().setVisibility(0);
                    }
                    textView2.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.paymonth_nonvip_activity_entrance_text));
                    textView3.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.paymonth_nonvip_classify_entrance_text));
                    textView.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.paymonth_nonvip_privilege_entrance_text));
                    getMonthvipIntroLayout().setVisibility(0);
                    a.setVisibility(0);
                }
            } else {
                getProfileLayout().setVisibility(0);
                getName().setText("未登录");
                getBookVipInfo().setText("开通包月，尊享特权");
                getBookVipimg().setVisibility(8);
                getFullWidthButton().setText("登录");
                getFullWidthButton().setBackgroundDrawable(ao.a(ReaderApplication.getApplicationImp(), (int) R.drawable.login_normal, (int) R.drawable.login_press));
                getFullWidthButton().setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.paymonth_login_textcolor));
                if (com.qq.reader.cservice.adv.c.a(false)) {
                    getNoMonthvipUserRedTip().setVisibility(0);
                }
                textView2.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.paymonth_nonvip_activity_entrance_text));
                textView3.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.paymonth_nonvip_classify_entrance_text));
                textView.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.paymonth_nonvip_privilege_entrance_text));
                getMonthvipIntroLayout().setVisibility(0);
                a.setVisibility(0);
            }
            if (this.notShowBottom) {
                ap.a(getRootView(), R.id.monthvip_to_activate_layout).setVisibility(8);
                ap.a(getRootView(), R.id.middle_divider).setVisibility(0);
                return;
            }
            View a2 = ap.a(getRootView(), R.id.ll_guide_classify_entrance);
            if (a2 != null) {
                a2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ PayMonthGuide a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        i.a("event_C292", null, ReaderApplication.getApplicationImp());
                        String access$100 = this.a.getCategoryId();
                        ArrayList arrayList = new ArrayList();
                        SearchActionTagLv3InitialDataModel searchActionTagLv3InitialDataModel = new SearchActionTagLv3InitialDataModel();
                        searchActionTagLv3InitialDataModel.selectedSubId = 2;
                        searchActionTagLv3InitialDataModel.selectedItemIds = new int[]{1};
                        searchActionTagLv3InitialDataModel.itemShouldInvisible = false;
                        arrayList.add(searchActionTagLv3InitialDataModel);
                        String access$200 = this.a.getCategoryTitle();
                        o.a(this.a.getEvnetListener().getFromActivity(), ",-1,1,-1,-1,6", access$100, access$200, arrayList, null);
                    }
                });
            }
            getMonthvipIntroLayout().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ PayMonthGuide a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.toVip();
                }
            });
            getMonthvipActivityLayout().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ PayMonthGuide a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.toActivityIntro();
                }
            });
        }
    }

    private String getCategoryTitle() {
        b bindPage = getBindPage();
        if (bindPage != null) {
            if ("PayMonth_Boy".equals(bindPage.i)) {
                return "男生";
            }
            if ("PayMonth_Girl".equals(bindPage.i)) {
                return "女生";
            }
            if ("PayMonth_Publish".equals(bindPage.i)) {
                return "出版";
            }
        }
        return "";
    }

    private String getCategoryId() {
        b bindPage = getBindPage();
        if (bindPage != null) {
            if ("PayMonth_Boy".equals(bindPage.i)) {
                return "1";
            }
            if ("PayMonth_Girl".equals(bindPage.i)) {
                return "2";
            }
            if ("PayMonth_Publish".equals(bindPage.i)) {
                return "3";
            }
        }
        return String.valueOf(d.aS(ReaderApplication.getApplicationImp()));
    }

    public void onClick(View view) {
        if (isLogin()) {
            Bundle bundle = new Bundle();
            bundle.putString("KEY_ACTION", "detail_2_openvip");
            if (getEvnetListener() != null) {
                getEvnetListener().doFunction(bundle);
            }
            i.a("event_C92", null, ReaderApplication.getApplicationImp());
            return;
        }
        this.mBindActionLogin.a(getEvnetListener());
    }

    private void toVip() {
        this.mBindActionJump.a().putString("com.qq.reader.WebContent", e.aB + e.b(ReaderApplication.getApplicationImp()));
        this.mBindActionJump.a(getEvnetListener());
        i.a("event_C91", null, ReaderApplication.getApplicationImp());
    }

    private void toActivityIntro() {
        this.mBindActionJump.a().putString("com.qq.reader.WebContent", "/monthActList.html?" + e.b(ReaderApplication.getApplicationImp()));
        this.mBindActionJump.a(getEvnetListener());
        cancelRedTip();
        i.a("event_C143", null, ReaderApplication.getApplicationImp());
    }

    public boolean isNeedCacheOnDisk() {
        return false;
    }

    public void getProfileData() {
        f.d(TAG, "getProfileData ");
        g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ PayMonthGuide a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject != null) {
                        com.qq.reader.common.login.b.a.a(com.qq.reader.common.login.c.c(), jSONObject);
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass5 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.initViews();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        }));
    }

    public boolean selfPrepareData() {
        if (isLogin()) {
            getProfileData();
        }
        return true;
    }

    private void cancelRedTip() {
        View monthvipUserRedTip = getMonthvipUserRedTip();
        View noMonthvipUserRedTip = getNoMonthvipUserRedTip();
        if (monthvipUserRedTip != null && monthvipUserRedTip.getVisibility() == 0) {
            monthvipUserRedTip.setVisibility(8);
        }
        if (noMonthvipUserRedTip != null && noMonthvipUserRedTip.getVisibility() == 0) {
            noMonthvipUserRedTip.setVisibility(8);
        }
        com.qq.reader.cservice.adv.c.a(true);
    }

    private String getMonthVipActivityAdvTitle() {
        String e;
        List c = com.qq.reader.cservice.adv.b.a(ReaderApplication.getApplicationImp()).c("102597");
        if (c != null && c.size() > 0) {
            com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) c.get(0);
            if (aVar != null) {
                e = aVar.e();
                return (e != null || e.equalsIgnoreCase("")) ? null : e;
            }
        }
        e = null;
        if (e != null) {
        }
    }
}
