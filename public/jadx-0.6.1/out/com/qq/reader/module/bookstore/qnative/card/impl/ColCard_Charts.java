package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ColCard_Charts extends a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PUSH_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    c[] mBindActionArray;
    c[] mBindActionArrayFromConfig;
    c[] mBindActionArrayFromServer;

    public ColCard_Charts(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_col_1;
    }

    public void build(JSONObject jSONObject) {
        super.build(jSONObject);
        JSONArray optJSONArray = jSONObject.optJSONArray("bottomButton");
        if (optJSONArray != null) {
            this.mBindActionArrayFromConfig = new c[optJSONArray.length()];
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                String optString = optJSONObject.optString("action");
                String optString2 = optJSONObject.optString("actionId");
                String optString3 = optJSONObject.optString("actionTag");
                String optString4 = optJSONObject.optString("controllerTitle");
                this.mBindActionArrayFromConfig[i] = new c(null);
                Bundle a = this.mBindActionArrayFromConfig[i].a();
                a.putString("KEY_ACTION", optString);
                a.putString("KEY_ACTIONID", optString2);
                a.putString("KEY_ACTIONTAG", optString3);
                a.putString("LOCAL_STORE_IN_TITLE", optString4);
                a.putBoolean("PARA_TYPE_BOOLEAN", true);
            }
        }
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            final g gVar;
            ((CardTitle) ap.a(getRootView(), R.id.title_layout)).setCardTitle(37, this.mShowTitle, this.mPromotionName, null);
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            cardMoreView.setText("查看更多");
            if (this.mMoreAction != null) {
                cardMoreView.setVisibility(0);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Charts a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Bundle a = this.a.mMoreAction.a().a();
                        try {
                            JSONObject jSONObject = new JSONObject();
                            int aS = d.aS(ReaderApplication.getApplicationImp());
                            if (aS == 0) {
                                aS = 3;
                            }
                            switch (aS) {
                                case 1:
                                    jSONObject.put(s.ORIGIN, "1782");
                                    break;
                                case 2:
                                    jSONObject.put(s.ORIGIN, "1783");
                                    break;
                                case 3:
                                    jSONObject.put(s.ORIGIN, "1784");
                                    break;
                            }
                            a.putString(s.STATPARAM_KEY, jSONObject.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        this.a.mMoreAction.a(this.a.getEvnetListener());
                        if ("人气榜".equals(this.a.mShowTitle)) {
                            i.a("event_C96", null, ReaderApplication.getApplicationImp());
                        }
                    }
                });
            } else {
                cardMoreView.setVisibility(8);
            }
            BookInfo4Chat bookInfo4Chat = (BookInfo4Chat) ap.a(getRootView(), R.id.body_layout);
            if (getItemList().size() > 0) {
                gVar = (g) getItemList().get(0);
                bookInfo4Chat.setBookInfo(gVar, this.mType);
                bookInfo4Chat.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Charts b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            gVar.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                bookInfo4Chat.setVisibility(8);
            }
            BookInfo4Chat_Simple bookInfo4Chat_Simple = (BookInfo4Chat_Simple) ap.a(getRootView(), R.id.localstore_book_0_body);
            if (getItemList().size() > 1) {
                gVar = (g) getItemList().get(1);
                bookInfo4Chat_Simple.setBookInfo(R.drawable.localstore_icon_bookinfo_chatmidle, "2", gVar, this.mType);
                bookInfo4Chat_Simple.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Charts b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            gVar.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                bookInfo4Chat_Simple.setVisibility(8);
            }
            bookInfo4Chat_Simple = (BookInfo4Chat_Simple) ap.a(getRootView(), R.id.localstore_book_1_body);
            if (getItemList().size() > 2) {
                gVar = (g) getItemList().get(2);
                bookInfo4Chat_Simple.setBookInfo(R.drawable.localstore_icon_bookinfo_chatmidle, "3", gVar, this.mType);
                bookInfo4Chat_Simple.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Charts b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            gVar.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                bookInfo4Chat_Simple.setVisibility(8);
            }
            bookInfo4Chat_Simple = (BookInfo4Chat_Simple) ap.a(getRootView(), R.id.localstore_book_2_body);
            if (getItemList().size() > 3) {
                gVar = (g) getItemList().get(3);
                bookInfo4Chat_Simple.setBookInfo(R.drawable.localstore_icon_bookinfo_chatbottom, "4", gVar, this.mType);
                bookInfo4Chat_Simple.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Charts b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            gVar.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                bookInfo4Chat_Simple.setVisibility(8);
            }
            bookInfo4Chat_Simple = (BookInfo4Chat_Simple) ap.a(getRootView(), R.id.localstore_book_3_body);
            if (getItemList().size() > 4) {
                gVar = (g) getItemList().get(4);
                bookInfo4Chat_Simple.setBookInfo(R.drawable.localstore_icon_bookinfo_chatbottom, "5", gVar, this.mType);
                bookInfo4Chat_Simple.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Charts b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            gVar.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                bookInfo4Chat_Simple.setVisibility(8);
            }
            if (this.mBindActionArrayFromServer != null) {
                this.mBindActionArray = this.mBindActionArrayFromServer;
            } else if (this.mBindActionArrayFromConfig != null) {
                this.mBindActionArray = this.mBindActionArrayFromConfig;
            } else {
                this.mBindActionArray = null;
            }
            View a = ap.a(getRootView(), R.id.localstore_charts_button_layout);
            if (this.mBindActionArray == null) {
                a.setVisibility(8);
                return;
            }
            a.setVisibility(0);
            TextView textView = (TextView) ap.a(getRootView(), R.id.localstore_charts_button_1);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.localstore_charts_button_2);
            ((TextView) ap.a(getRootView(), R.id.localstore_charts_button_0)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ColCard_Charts a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.mBindActionArray != null && this.a.mBindActionArray.length > 0) {
                        c cVar = this.a.mBindActionArray[0];
                        if (cVar != null) {
                            cVar.a(this.a.getEvnetListener());
                        }
                    }
                }
            });
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ColCard_Charts a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.mBindActionArray != null && this.a.mBindActionArray.length > 1) {
                        c cVar = this.a.mBindActionArray[1];
                        if (cVar != null) {
                            cVar.a(this.a.getEvnetListener());
                        }
                    }
                }
            });
            textView2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ColCard_Charts a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.mBindActionArray != null && this.a.mBindActionArray.length > 2) {
                        c cVar = this.a.mBindActionArray[2];
                        if (cVar != null) {
                            cVar.a(this.a.getEvnetListener());
                        }
                    }
                }
            });
        }
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        getItemList().clear();
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString(JSON_KEY_PUSH_NAME);
        JSONArray optJSONArray = jSONObject.optJSONArray("bottomButton");
        if (optJSONArray != null) {
            this.mBindActionArrayFromServer = new c[optJSONArray.length()];
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                String string = jSONObject2.getString("action");
                String string2 = jSONObject2.getString("actionId");
                String string3 = jSONObject2.getString("actionTag");
                String string4 = jSONObject2.getString("controllerTitle");
                this.mBindActionArrayFromServer[i2] = new c(null);
                Bundle a = this.mBindActionArrayFromServer[i2].a();
                a.putString("KEY_ACTION", string);
                a.putString("KEY_ACTIONID", string2);
                a.putString("KEY_ACTIONTAG", string3);
                a.putString("LOCAL_STORE_IN_TITLE", string4);
                a.putBoolean("PARA_TYPE_BOOLEAN", true);
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray(JSON_KEY_BOOKLIST);
        if (optJSONArray2 == null) {
            return false;
        }
        int length = optJSONArray2.length();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            jSONObject2 = optJSONArray2.getJSONObject(i);
            s gVar = new g();
            gVar.parseData(jSONObject2);
            addItem(gVar);
            i++;
        }
        return true;
    }
}
