package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.AuthorQAIncomeItemCard;
import com.qq.reader.module.bookstore.qnative.card.impl.AuthorQAIncomeTotalCard;
import com.qq.reader.module.bookstore.qnative.card.impl.AuthorQASettleBalacnUserInfoCard;
import com.qq.reader.module.bookstore.qnative.card.impl.AuthorQASettleBalanceItemCard;
import com.qq.reader.module.bookstore.qnative.card.impl.AuthorQASettleBalanceTotalCard;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforEmptyRefreshable;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.d.b;
import com.tencent.feedback.proguard.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerAuthorQAIncomeListPage */
public class t extends af {
    private static d a;

    public t(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(this.f);
        StringBuffer stringBuffer = new StringBuffer("");
        String string = this.f.getString("KEY_ACTIONTAG");
        String string2 = this.f.getString("AUTHORPAGE_KEY_AUTHORID");
        if ("0".equals(string)) {
            stringBuffer.append("nativepage/aqa/settlement/incomeDetail?authorId=" + string2);
        } else if ("1".equals(string)) {
            stringBuffer.append("nativepage/aqa/settlement/detail?authorId=" + string2);
        }
        return cVar.a(e.a, stringBuffer.toString());
    }

    public void b(JSONObject jSONObject) {
        int i = 0;
        x();
        String string = this.f.getString("KEY_ACTIONTAG");
        long j = this.f.getLong("KEY_PAGEINDEX", 1);
        a authorQAIncomeTotalCard;
        JSONArray optJSONArray;
        a authorQAIncomeItemCard;
        if ("0".equals(string)) {
            if (jSONObject.optDouble("totalAmount", 0.0d) > 0.0d) {
                if (j == 1) {
                    authorQAIncomeTotalCard = new AuthorQAIncomeTotalCard(this, "AuthorQAIncomeTotalCard");
                    authorQAIncomeTotalCard.setEventListener(l());
                    authorQAIncomeTotalCard.fillData(jSONObject);
                    this.k.add(authorQAIncomeTotalCard);
                }
                optJSONArray = jSONObject.optJSONArray("incomeDetail");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    while (i < optJSONArray.length()) {
                        authorQAIncomeItemCard = new AuthorQAIncomeItemCard(this, "AuthorQAIncomeItemCard");
                        authorQAIncomeItemCard.setEventListener(l());
                        authorQAIncomeItemCard.fillData(optJSONArray.optJSONObject(i));
                        this.k.add(authorQAIncomeItemCard);
                        i++;
                    }
                }
            }
        } else if ("1".equals(string)) {
            if (jSONObject.optDouble("totalAmount", 0.0d) > 0.0d) {
                if (j == 1) {
                    authorQAIncomeTotalCard = new AuthorQASettleBalanceTotalCard(this, "AuthorQASettleBalanceTotalCard");
                    authorQAIncomeTotalCard.setEventListener(l());
                    authorQAIncomeTotalCard.fillData(jSONObject);
                    this.k.add(authorQAIncomeTotalCard);
                    authorQAIncomeTotalCard = new AuthorQASettleBalacnUserInfoCard(this, "AuthorQASettleBalacnUserInfoCard");
                    authorQAIncomeTotalCard.setEventListener(l());
                    authorQAIncomeTotalCard.fillData(jSONObject);
                    this.k.add(authorQAIncomeTotalCard);
                }
                optJSONArray = jSONObject.optJSONArray("detail");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    while (i < optJSONArray.length()) {
                        authorQAIncomeItemCard = new AuthorQASettleBalanceItemCard(this, "AuthorQASettleBalanceItemCard");
                        authorQAIncomeItemCard.setEventListener(l());
                        authorQAIncomeItemCard.fillData(optJSONArray.optJSONObject(i));
                        this.k.add(authorQAIncomeItemCard);
                        i++;
                    }
                }
            } else {
                a authorQASettleBalacnUserInfoCard = new AuthorQASettleBalacnUserInfoCard(this, "AuthorQASettleBalacnUserInfoCard");
                authorQASettleBalacnUserInfoCard.setEventListener(l());
                authorQASettleBalacnUserInfoCard.fillData(jSONObject);
                this.k.add(authorQASettleBalacnUserInfoCard);
                MyFavorEmptyCard myFavorEmptyCard = new MyFavorEmptyCard(this, "MyFavorEmptyCard");
                myFavorEmptyCard.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.author_qa_list_no_data_tip));
                myFavorEmptyCard.setImage(R.drawable.empty03);
                myFavorEmptyCard.setEventListener(l());
                this.k.add(myFavorEmptyCard);
            }
        }
        this.o = jSONObject.optLong("pagestamp");
        this.q = jSONObject;
    }

    public boolean a() {
        return false;
    }

    private void x() {
        try {
            if (a == null) {
                JSONObject jSONObject = new JSONObject(y());
                a = new d();
                a.a(jSONObject);
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
        if (this.f != null) {
            String string = this.f.getString("KEY_ACTIONTAG");
            List g = a.g();
            for (int i = 0; i < g.size(); i++) {
                b bVar = (b) g.get(i);
                bVar.c = false;
                if (bVar != null && bVar.b.equals(string)) {
                    bVar.c = true;
                }
            }
        }
        this.n = a;
    }

    private String y() {
        InputStream open;
        Exception e;
        Throwable th;
        try {
            open = ReaderApplication.getApplicationImp().getResources().getAssets().open("qa_income_action_tags.txt");
            try {
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                String str = new String(bArr, "UTF-8");
                if (open == null) {
                    return str;
                }
                try {
                    open.close();
                    return str;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return str;
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            open = null;
            e.printStackTrace();
            if (open != null) {
                open.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            open = null;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    public Class c() {
        return NativePageFragmentforEmptyRefreshable.class;
    }
}
