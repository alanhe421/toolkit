package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.ClipboardManager;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.af;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.feedback.proguard.R;
import java.io.Serializable;
import org.json.JSONObject;

public class AuthorWXInfoBindCard extends a {
    private CheckBox cb_confirm_wx_info;
    private CheckBox cb_read_contract;
    private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener(this) {
        final /* synthetic */ AuthorWXInfoBindCard a;

        {
            this.a = r1;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.a.updateBindButtonState();
        }
    };
    private WXBasicInfoItem item;
    private String labourContractTitle;
    private String topTip;
    private TextView tv_bind_wx;

    public static class WXBasicInfoItem extends s implements Serializable {
        private static final String KEY_ADDRESS = "contactAddress";
        private static final String KEY_GENDER = "authorSex";
        private static final String KEY_ID_NUMBER = "identityCard";
        private static final String KEY_LABOUR_CONTRACT_URL = "serviceContractUrl";
        private static final String KEY_LABOUR_CONTRACT_VERSION = "serviceContractVersion";
        private static final String KEY_NAME = "authorName";
        public String accessToken;
        public String address;
        public String authorId;
        public String avatarUrl;
        public String gender;
        public String id_number;
        public String labourContractUrl;
        public String labourContractVersion;
        public String nickName;
        public String openId;
        public String realname;
        public String refreshToken;
        public String scope;
        public String unionId;

        public void parseData(JSONObject jSONObject) {
            this.realname = jSONObject.optString(KEY_NAME);
            this.gender = jSONObject.optString(KEY_GENDER);
            this.id_number = jSONObject.optString(KEY_ID_NUMBER);
            this.address = jSONObject.optString(KEY_ADDRESS);
            this.labourContractUrl = jSONObject.optString(KEY_LABOUR_CONTRACT_URL);
            this.labourContractVersion = jSONObject.optString(KEY_LABOUR_CONTRACT_VERSION);
        }
    }

    public AuthorWXInfoBindCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_wx_info_bind_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = new WXBasicInfoItem();
        this.item.parseData(jSONObject);
        this.topTip = jSONObject.optString("intro");
        this.labourContractTitle = jSONObject.optString("serviceContractTitle");
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_name);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_gender);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.tv_address);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.tv_id_number);
        TextView textView5 = (TextView) ap.a(getRootView(), R.id.tv_qqreader_contract);
        TextView textView6 = (TextView) ap.a(getRootView(), R.id.tv_copy);
        TextView textView7 = (TextView) ap.a(getRootView(), R.id.tv_wx_bind_tip);
        TextView textView8 = (TextView) ap.a(getRootView(), R.id.tv_contract_part_b_info_tip);
        if (!TextUtils.isEmpty(this.topTip)) {
            textView7.setText(this.topTip);
        }
        this.cb_confirm_wx_info = (CheckBox) ap.a(getRootView(), R.id.cb_author_wx_bind_info_correct);
        this.cb_read_contract = (CheckBox) ap.a(getRootView(), R.id.cb_author_wx_bind_contract);
        this.tv_bind_wx = (TextView) ap.a(getRootView(), R.id.tv_bind_wx);
        if (this.item != null) {
            fillContent(textView, "真实姓名：", this.item.realname);
            fillContent(textView2, "性别：", this.item.gender);
            fillContent(textView3, "地址：", this.item.address);
            fillContent(textView4, "身份证号码：", this.item.id_number);
        }
        this.cb_confirm_wx_info.setOnCheckedChangeListener(this.checkedChangeListener);
        this.cb_read_contract.setOnCheckedChangeListener(this.checkedChangeListener);
        textView6.setOnClickListener(new c(this) {
            final /* synthetic */ AuthorWXInfoBindCard a;

            {
                this.a = r1;
            }

            public void a(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) ReaderApplication.getApplicationImp().getSystemService("clipboard");
                if (clipboardManager != null) {
                    clipboardManager.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.qqreader_wx_name));
                    af.a(this.a.getEvnetListener().getFromActivity(), ReaderApplication.getApplicationImp().getResources().getString(R.string.author_wx_bind_info_copy_success), 0).a();
                }
            }
        });
        this.tv_bind_wx.setOnClickListener(new c(this) {
            final /* synthetic */ AuthorWXInfoBindCard a;

            {
                this.a = r1;
            }

            public void a(View view) {
                if (!this.a.isInfoEnough()) {
                    af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.author_qa_income_wx_bind_info_not_enough), 0).a();
                } else if (WXApiManager.getInstance(ReaderApplication.getApplicationImp()).isWXinstalled()) {
                    Intent intent = new Intent();
                    intent.setAction("BROADCAST_ACTION_WX_BIND_START");
                    intent.putExtra("info", this.a.item);
                    ReaderApplication.getApplicationImp().sendBroadcast(intent);
                } else {
                    af.a(ReaderApplication.getApplicationImp(), (CharSequence) "请先安装微信客户端", 0).a();
                }
            }
        });
        if (!TextUtils.isEmpty(this.labourContractTitle)) {
            textView5.setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.qq_reader_labour_contract), new Object[]{this.labourContractTitle}));
            textView8.setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.author_wx_bind_confirm_info_tip), new Object[]{this.labourContractTitle}));
        }
        textView5.setOnClickListener(new c(this) {
            final /* synthetic */ AuthorWXInfoBindCard a;

            {
                this.a = r1;
            }

            public void a(View view) {
                if (this.a.item != null && !TextUtils.isEmpty(this.a.item.labourContractUrl)) {
                    try {
                        com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), e.Q + this.a.item.labourContractUrl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private boolean isInfoEnough() {
        if (this.item == null || TextUtils.isEmpty(this.item.address) || TextUtils.isEmpty(this.item.realname) || TextUtils.isEmpty(this.item.gender) || TextUtils.isEmpty(this.item.id_number)) {
            return false;
        }
        return true;
    }

    private void updateBindButtonState() {
        if (this.cb_confirm_wx_info.isChecked() && this.cb_read_contract.isChecked()) {
            this.tv_bind_wx.setEnabled(true);
        } else {
            this.tv_bind_wx.setEnabled(false);
        }
    }

    private void fillContent(TextView textView, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            textView.setText(str + str2);
        }
    }
}
