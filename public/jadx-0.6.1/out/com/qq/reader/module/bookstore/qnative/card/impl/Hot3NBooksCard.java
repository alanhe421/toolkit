package com.qq.reader.module.bookstore.qnative.card.impl;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.GetHotDiscountBookTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Hot3NBooksCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private static final String BUTTON_TEXT_BUY = "抢购";
    private static final String BUTTON_TEXT_FREE = "免费领";
    protected static final String JSON_KEY_DISCOUNT = "discount";
    private static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private int[] itemLayoutIds = new int[]{R.id.book_item_1, R.id.book_item_2, R.id.book_item_3};
    private int mDiscount = 0;

    private class a extends g {
        public boolean a;
        public String b;
        final /* synthetic */ Hot3NBooksCard c;

        private a(Hot3NBooksCard hot3NBooksCard) {
            this.c = hot3NBooksCard;
        }

        public void parseData(JSONObject jSONObject) {
            boolean z = true;
            super.parseData(jSONObject);
            if (jSONObject.optInt("discountChecked") != 1) {
                z = false;
            }
            this.a = z;
            this.b = jSONObject.optString("get_qurl");
        }
    }

    public Hot3NBooksCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.hot_3n_books_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        List itemList = getItemList();
        if (itemList != null) {
            itemList.clear();
        } else {
            itemList = new ArrayList();
        }
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString(JSON_KEY_PROMOTION_NAME);
        this.mDiscount = jSONObject.optInt(JSON_KEY_DISCOUNT, 0);
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            s aVar = new a();
            aVar.parseData(optJSONObject);
            addItem(aVar);
            i++;
        }
        if (itemList == null || itemList.size() < this.mDispaly) {
            return false;
        }
        return true;
    }

    public void attachView() {
        int i = 0;
        ((CardTitle) ap.a(getRootView(), R.id.card_title)).setCardTitle(0, this.mShowTitle, this.mPromotionName, null);
        List itemList = getItemList();
        while (itemList != null && i < itemList.size() && i < this.itemLayoutIds.length) {
            fillSingleBookInfo(ap.a(getRootView(), this.itemLayoutIds[i]), (a) itemList.get(i));
            i++;
        }
        showStatics();
    }

    private void fillSingleBookInfo(final View view, final a aVar) {
        if (aVar != null) {
            ImageView imageView = (ImageView) ap.a(view, R.id.img_book_cover_mask);
            c.a(getEvnetListener().getFromActivity()).a(ao.g(aVar.m()), (ImageView) ap.a(view, R.id.img_book_cover), com.qq.reader.common.imageloader.a.a().j());
            ap.a(view, R.id.fl_book_image).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ Hot3NBooksCard b;

                public void onClick(View view) {
                    if (aVar != null) {
                        o.a(this.b.getEvnetListener().getFromActivity(), String.valueOf(aVar.m()), aVar.mStatParamString, null, null);
                        this.b.clickStatics();
                    }
                }
            });
            ((TextView) ap.a(view, R.id.tv_title)).setText(aVar.n());
            TextView textView = (TextView) ap.a(view, R.id.tv_get_book);
            if (aVar.a) {
                textView.setText("已领取");
                textView.setEnabled(false);
            } else {
                textView.setText(getTextButtonText());
            }
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ Hot3NBooksCard c;

                public void onClick(View view) {
                    if (aVar != null) {
                        try {
                            this.c.clickStatics();
                            this.c.getDiscountBook(aVar, view);
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                        }
                    }
                }
            });
        }
    }

    private void showStatics() {
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void runOnMainThread(Runnable runnable) {
        getEvnetListener().getFromActivity().runOnUiThread(runnable);
    }

    private void getDiscountBook(final a aVar, final View view) {
        if (com.qq.reader.common.login.c.b()) {
            doGetDiscountBook(aVar, view);
            return;
        }
        com.qq.reader.common.login.a anonymousClass3 = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ Hot3NBooksCard c;

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.c.doGetDiscountBook(aVar, view);
                        return;
                    default:
                        return;
                }
            }
        };
        Activity fromActivity = getEvnetListener().getFromActivity();
        if (fromActivity instanceof ReaderBaseActivity) {
            ReaderBaseActivity readerBaseActivity = (ReaderBaseActivity) fromActivity;
            readerBaseActivity.setLoginNextTask(anonymousClass3);
            readerBaseActivity.startLogin();
        }
    }

    private void doGetDiscountBook(final a aVar, View view) {
        final TextView textView = (TextView) ap.a(view, R.id.tv_get_book);
        com.qq.reader.common.readertask.g.a().a(new GetHotDiscountBookTask(String.valueOf(aVar.m()), this.mCardId, new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ Hot3NBooksCard c;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    if (new JSONObject(str).optInt("code", -1) != 0) {
                        this.c.runOnMainThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                textView.setText(this.a.c.getTextButtonText());
                                textView.setEnabled(true);
                                af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.author_edit_fenda_server_error), 0).a();
                            }
                        });
                    } else if (Hot3NBooksCard.BUTTON_TEXT_FREE.equals(this.c.getTextButtonText())) {
                        new JSAddToBookShelf(this.c.getEvnetListener().getFromActivity()).addByIdWithCallBack(String.valueOf(aVar.m()), "true", new com.qq.reader.common.web.js.JSAddToBookShelf.a(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void a() {
                                this.a.c.runOnMainThread(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        aVar.a = true;
                                        textView.setText("已领取");
                                        textView.setEnabled(false);
                                        o.a(this.a.a.c.getEvnetListener().getFromActivity(), String.valueOf(aVar.m()), null, null, null);
                                    }
                                });
                            }

                            public void b() {
                                this.a.c.runOnMainThread(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        textView.setText(this.a.a.c.getTextButtonText());
                                        textView.setEnabled(true);
                                        af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.author_edit_fenda_server_error), 0).a();
                                    }
                                });
                            }
                        });
                    } else {
                        this.c.runOnMainThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                aVar.a = true;
                                textView.setText("已领取");
                                textView.setEnabled(false);
                                o.a(this.a.c.getEvnetListener().getFromActivity(), String.valueOf(aVar.m()), null, null, null);
                            }
                        });
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.c.runOnMainThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        textView.setText(this.a.c.getTextButtonText());
                        textView.setEnabled(true);
                        af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.net_not_available), 0).a();
                    }
                });
            }
        }));
        textView.setText(getBuyLoadingText());
        textView.setEnabled(true);
    }

    public boolean reSaveDataBuild(JSONObject jSONObject) throws JSONException {
        jSONObject.put("expireTime", System.currentTimeMillis() / 1000);
        return true;
    }

    private String getBuyLoadingText() {
        String str = "抢购中...";
        try {
            if (this.mDiscount <= 0) {
                return "领取中...";
            }
            return str;
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
            return str;
        }
    }

    private String getTextButtonText() {
        String str = BUTTON_TEXT_BUY;
        try {
            if (this.mDiscount <= 0) {
                return BUTTON_TEXT_FREE;
            }
            return str;
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
            return str;
        }
    }

    public boolean isExpired() {
        return true;
    }
}
