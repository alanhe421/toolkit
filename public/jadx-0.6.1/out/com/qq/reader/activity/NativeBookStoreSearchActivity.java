package com.qq.reader.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.BookReleaseAlertTask;
import com.qq.reader.common.readertask.protocol.SearchHotWordsTask;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.utils.t;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.ap;
import com.qq.reader.module.bookstore.search.AbsSearchWords;
import com.qq.reader.module.bookstore.search.CommonBookSearchParamCollection;
import com.qq.reader.module.bookstore.search.DropDownEditText;
import com.qq.reader.module.bookstore.search.ISearchParamCollection;
import com.qq.reader.module.bookstore.search.SearchData;
import com.qq.reader.module.bookstore.search.SearchHistory;
import com.qq.reader.module.bookstore.search.SearchHotWords;
import com.qq.reader.module.bookstore.search.SearchTabView;
import com.qq.reader.module.bookstore.search.SearchXListFooter;
import com.qq.reader.module.bookstore.search.card.SearchBaseCard;
import com.qq.reader.module.bookstore.search.d;
import com.qq.reader.module.bookstore.search.g;
import com.qq.reader.module.bookstore.search.h;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.plugin.audiobook.MusicActivity;
import com.qq.reader.plugin.audiobook.MusicBookGroup;
import com.qq.reader.qplugin.local.TingBookMark;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.ah;
import com.qq.reader.view.animation.e;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.view.r;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class NativeBookStoreSearchActivity extends NativeBookStoreConfigBaseActivity implements OnClickListener {
    private static String B = "{\"actionTag\":\",-1,-1,-1,-1,0\",\"actionId\":\"\"}";
    private SearchTabView A;
    private List<String> C = new ArrayList();
    private e D;
    private View E;
    private Bundle F;
    private View G;
    private ISearchParamCollection H = new CommonBookSearchParamCollection();
    private int I = -1;
    private DropDownEditText J;
    private d K;
    private g L;
    private a M;
    private com.qq.reader.module.bookstore.search.e N;
    private boolean O = true;
    private View P;
    private long Q;
    private boolean R = false;
    private List<SearchHotWords> S;
    SearchKeywordAssociateTask a;
    int b = 1;
    String c = null;
    private final String d = NativeBookStoreSearchActivity.class.getSimpleName();
    private XListView n = null;
    private InputMethodManager o = null;
    private boolean p = false;
    private String q;
    private boolean r = false;
    private View s;
    private View t;
    private View u;
    private TextView v;
    private ImageView w;
    private BaseDialog x;
    private View y;
    private int z = 0;

    private static class SearchKeywordAssociateTask extends ReaderProtocolJSONTask {
        WeakReference<a> handlerRef;
        private boolean isCancel;
        private ISearchParamCollection mSearchParamCollection = new CommonBookSearchParamCollection();

        public void setCancel(boolean z) {
            this.isCancel = z;
        }

        public SearchKeywordAssociateTask(a aVar, final String str, ISearchParamCollection iSearchParamCollection) {
            ISearchParamCollection a = ao.a(iSearchParamCollection);
            this.mSearchParamCollection = a;
            this.handlerRef = new WeakReference(aVar);
            registerNetTaskListener(new c(this) {
                final /* synthetic */ SearchKeywordAssociateTask b;

                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onConnectionRecieveData(com.qq.reader.common.readertask.ordinal.ReaderProtocolTask r6, java.lang.String r7, long r8) {
                    /*
                    r5 = this;
                    r0 = new java.lang.StringBuilder;
                    r0.<init>();
                    r1 = "搜索返回值:    ";
                    r0 = r0.append(r1);
                    r0 = r0.append(r7);
                    r0 = r0.toString();
                    com.qq.reader.activity.NativeBookStoreSearchActivity.b(r0);
                    r0 = r5.b;
                    r0 = r0.isCancel;
                    if (r0 == 0) goto L_0x0020;
                L_0x001f:
                    return;
                L_0x0020:
                    r1 = new java.util.ArrayList;
                    r1.<init>();
                    r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x00fa }
                    r0.<init>(r7);	 Catch:{ Exception -> 0x00fa }
                    r2 = "key";
                    r3 = "";
                    r2 = r0.optString(r2, r3);	 Catch:{ Exception -> 0x00fa }
                    r3 = "免费";
                    r3 = r2.equals(r3);	 Catch:{ Exception -> 0x00fa }
                    if (r3 != 0) goto L_0x004f;
                L_0x003d:
                    r3 = "mianfei";
                    r3 = r2.equals(r3);	 Catch:{ Exception -> 0x00fa }
                    if (r3 != 0) goto L_0x004f;
                L_0x0046:
                    r3 = "mf";
                    r3 = r2.equals(r3);	 Catch:{ Exception -> 0x00fa }
                    if (r3 == 0) goto L_0x0067;
                L_0x004f:
                    r3 = new com.qq.reader.module.bookstore.search.SearchData;	 Catch:{ Exception -> 0x00fa }
                    r3.<init>();	 Catch:{ Exception -> 0x00fa }
                    r4 = "免费";
                    r3.setKeyWord(r4);	 Catch:{ Exception -> 0x00fa }
                    r4 = "uniteqqreader://nativepage/discover/todayfree";
                    r3.setQurl(r4);	 Catch:{ Exception -> 0x00fa }
                    r4 = 10;
                    r3.mType = r4;	 Catch:{ Exception -> 0x00fa }
                    r1.add(r3);	 Catch:{ Exception -> 0x00fa }
                L_0x0067:
                    r3 = "包月";
                    r3 = r2.equals(r3);	 Catch:{ Exception -> 0x00fa }
                    if (r3 != 0) goto L_0x0082;
                L_0x0070:
                    r3 = "baoyue";
                    r3 = r2.equals(r3);	 Catch:{ Exception -> 0x00fa }
                    if (r3 != 0) goto L_0x0082;
                L_0x0079:
                    r3 = "by";
                    r3 = r2.equals(r3);	 Catch:{ Exception -> 0x00fa }
                    if (r3 == 0) goto L_0x009a;
                L_0x0082:
                    r3 = new com.qq.reader.module.bookstore.search.SearchData;	 Catch:{ Exception -> 0x00fa }
                    r3.<init>();	 Catch:{ Exception -> 0x00fa }
                    r4 = 11;
                    r3.mType = r4;	 Catch:{ Exception -> 0x00fa }
                    r4 = "uniteqqreader://nativepage/discover/vipzone";
                    r3.setQurl(r4);	 Catch:{ Exception -> 0x00fa }
                    r4 = "包月";
                    r3.setKeyWord(r4);	 Catch:{ Exception -> 0x00fa }
                    r1.add(r3);	 Catch:{ Exception -> 0x00fa }
                L_0x009a:
                    r3 = "听书";
                    r3 = r2.equals(r3);	 Catch:{ Exception -> 0x00fa }
                    if (r3 != 0) goto L_0x00b5;
                L_0x00a3:
                    r3 = "tingshu";
                    r3 = r2.equals(r3);	 Catch:{ Exception -> 0x00fa }
                    if (r3 != 0) goto L_0x00b5;
                L_0x00ac:
                    r3 = "ts";
                    r2 = r2.equals(r3);	 Catch:{ Exception -> 0x00fa }
                    if (r2 == 0) goto L_0x00cd;
                L_0x00b5:
                    r2 = new com.qq.reader.module.bookstore.search.SearchData;	 Catch:{ Exception -> 0x00fa }
                    r2.<init>();	 Catch:{ Exception -> 0x00fa }
                    r3 = 12;
                    r2.mType = r3;	 Catch:{ Exception -> 0x00fa }
                    r3 = "听书";
                    r2.setKeyWord(r3);	 Catch:{ Exception -> 0x00fa }
                    r3 = "uniteqqreader://nativepage/discover/listenzone";
                    r2.setQurl(r3);	 Catch:{ Exception -> 0x00fa }
                    r1.add(r2);	 Catch:{ Exception -> 0x00fa }
                L_0x00cd:
                    r2 = "matchList";
                    r2 = r0.optJSONArray(r2);	 Catch:{ Exception -> 0x00fa }
                    r0 = 0;
                L_0x00d5:
                    if (r2 == 0) goto L_0x00ef;
                L_0x00d7:
                    r3 = r2.length();	 Catch:{ Exception -> 0x00fa }
                    if (r0 >= r3) goto L_0x00ef;
                L_0x00dd:
                    r3 = new com.qq.reader.module.bookstore.search.SearchData;	 Catch:{ Exception -> 0x00fa }
                    r3.<init>();	 Catch:{ Exception -> 0x00fa }
                    r4 = r2.optJSONObject(r0);	 Catch:{ Exception -> 0x00fa }
                    r3.parseJson(r4);	 Catch:{ Exception -> 0x00fa }
                    r1.add(r3);	 Catch:{ Exception -> 0x00fa }
                    r0 = r0 + 1;
                    goto L_0x00d5;
                L_0x00ef:
                    r0 = r5.b;
                    r0 = r0.mSearchParamCollection;
                    r5.a(r1, r0);
                    goto L_0x001f;
                L_0x00fa:
                    r0 = move-exception;
                    r0 = "parse data fail";
                    com.qq.reader.activity.NativeBookStoreSearchActivity.b(r0);	 Catch:{ all -> 0x010f }
                    r1.clear();	 Catch:{ all -> 0x010f }
                    r0 = r5.b;
                    r0 = r0.mSearchParamCollection;
                    r5.a(r1, r0);
                    goto L_0x001f;
                L_0x010f:
                    r0 = move-exception;
                    r2 = r5.b;
                    r2 = r2.mSearchParamCollection;
                    r5.a(r1, r2);
                    throw r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.activity.NativeBookStoreSearchActivity.SearchKeywordAssociateTask.1.onConnectionRecieveData(com.qq.reader.common.readertask.ordinal.ReaderProtocolTask, java.lang.String, long):void");
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    NativeBookStoreSearchActivity.b("搜索返回值: error   " + exception.toString());
                    if (!this.b.isCancel) {
                        a(new ArrayList(), this.b.mSearchParamCollection);
                    }
                }

                private void a(List<SearchData> list, ISearchParamCollection iSearchParamCollection) {
                    int i = 0;
                    try {
                        ISearchParamCollection a = ao.a(iSearchParamCollection);
                        a aVar = (a) this.b.handlerRef.get();
                        if (aVar != null) {
                            List<SearchData> arrayList = new ArrayList();
                            List<Mark> g = i.c().g();
                            for (int i2 = 0; i2 < g.size(); i2++) {
                                ((Mark) g.get(i2)).generatePinyin();
                            }
                            ArrayList arrayList2 = new ArrayList();
                            SearchData searchData = new SearchData();
                            searchData.mTag = arrayList2;
                            searchData.mType = 14;
                            for (Mark mark : g) {
                                int i3;
                                String pinyinBookName = mark.getPinyinBookName();
                                String trim = mark.getBookName().trim();
                                if (trim.lastIndexOf(".") > 0) {
                                    trim = trim.substring(0, trim.lastIndexOf("."));
                                }
                                if (trim.startsWith(str) || pinyinBookName.startsWith(str)) {
                                    SearchData searchData2;
                                    if (a.getSearchType() != 2) {
                                        searchData2 = new SearchData();
                                        if (mark.getType() == 8) {
                                            searchData2.mType = 15;
                                        } else {
                                            searchData2.mType = 13;
                                        }
                                        searchData2.setKeyWord(trim);
                                        searchData2.mTag = mark;
                                        if (i == 0) {
                                            arrayList.add(searchData2);
                                        } else {
                                            arrayList2.add(0, searchData2);
                                        }
                                        i3 = i + 1;
                                    } else if (mark.getType() == 8) {
                                        searchData2 = new SearchData();
                                        searchData2.mType = 15;
                                        searchData2.setKeyWord(trim);
                                        searchData2.mTag = mark;
                                        if (i == 0) {
                                            arrayList.add(searchData2);
                                        } else {
                                            arrayList2.add(0, searchData2);
                                        }
                                        i3 = i + 1;
                                    }
                                    i = i3;
                                }
                                i3 = i;
                                i = i3;
                            }
                            if (arrayList2.size() > 0) {
                                searchData.setKeyWord(String.format(ReaderApplication.getApplicationImp().getString(R.string.search_hint_folder_text), new Object[]{Integer.valueOf(arrayList2.size() + 1)}));
                                arrayList.add(searchData);
                            }
                            arrayList.addAll(list);
                            for (SearchData searchData3 : arrayList) {
                                Map hashMap;
                                Map hashMap2;
                                switch (searchData3.mType) {
                                    case 1:
                                    case 9:
                                        hashMap = new HashMap();
                                        hashMap.put(s.ORIGIN, "3");
                                        hashMap.put("keyword", str);
                                        NativeBookStoreSearchActivity.b(hashMap, searchData3);
                                        if (this.b.mSearchParamCollection != null) {
                                            this.b.mSearchParamCollection.submitStaticsParam(hashMap);
                                        }
                                        com.qq.reader.common.monitor.i.a("event_C269", hashMap, ReaderApplication.getApplicationImp());
                                        StatisticsManager.a().a("event_C269", hashMap);
                                        hashMap2 = new HashMap();
                                        hashMap2.put("type", "3");
                                        com.qq.reader.common.monitor.i.a("event_C128", hashMap2, ReaderApplication.getApplicationImp());
                                        StatisticsManager.a().a("event_C128", hashMap2);
                                        break;
                                    case 2:
                                        hashMap2 = new HashMap();
                                        hashMap2.put("type", "4");
                                        com.qq.reader.common.monitor.i.a("event_C128", hashMap2, ReaderApplication.getApplicationImp());
                                        StatisticsManager.a().a("event_C128", hashMap2);
                                        break;
                                    case 3:
                                    case 4:
                                        hashMap = new HashMap();
                                        hashMap.put(s.ORIGIN, "4");
                                        hashMap.put("keyword", str);
                                        NativeBookStoreSearchActivity.b(hashMap, searchData3);
                                        if (this.b.mSearchParamCollection != null) {
                                            this.b.mSearchParamCollection.submitStaticsParam(hashMap);
                                        }
                                        com.qq.reader.common.monitor.i.a("event_C269", hashMap, ReaderApplication.getApplicationImp());
                                        StatisticsManager.a().a("event_C269", hashMap);
                                        hashMap = new HashMap();
                                        hashMap.put("type", searchData3.mType == 3 ? "1" : "2");
                                        com.qq.reader.common.monitor.i.a("event_C128", hashMap, ReaderApplication.getApplicationImp());
                                        StatisticsManager.a().a("event_C128", hashMap);
                                        break;
                                    case 8:
                                        hashMap = new HashMap();
                                        hashMap.put(s.ORIGIN, "5");
                                        hashMap.put("keyword", str);
                                        NativeBookStoreSearchActivity.b(hashMap, searchData3);
                                        if (this.b.mSearchParamCollection != null) {
                                            this.b.mSearchParamCollection.submitStaticsParam(hashMap);
                                        }
                                        com.qq.reader.common.monitor.i.a("event_C269", hashMap, ReaderApplication.getApplicationImp());
                                        StatisticsManager.a().a("event_C269", hashMap);
                                        break;
                                    case 10:
                                    case 11:
                                    case 12:
                                        hashMap = new HashMap();
                                        hashMap.put(s.ORIGIN, "4");
                                        hashMap.put("keyword", str);
                                        NativeBookStoreSearchActivity.b(hashMap, searchData3);
                                        if (this.b.mSearchParamCollection != null) {
                                            this.b.mSearchParamCollection.submitStaticsParam(hashMap);
                                        }
                                        com.qq.reader.common.monitor.i.a("event_C269", hashMap, ReaderApplication.getApplicationImp());
                                        StatisticsManager.a().a("event_C269", hashMap);
                                        hashMap2 = new HashMap();
                                        hashMap2.put("type", "1");
                                        com.qq.reader.common.monitor.i.a("event_C128", hashMap2, ReaderApplication.getApplicationImp());
                                        StatisticsManager.a().a("event_C128", hashMap2);
                                        break;
                                    case 13:
                                        hashMap = new HashMap();
                                        hashMap.put(s.ORIGIN, "2");
                                        hashMap.put("keyword", str);
                                        NativeBookStoreSearchActivity.b(hashMap, searchData3);
                                        if (this.b.mSearchParamCollection != null) {
                                            this.b.mSearchParamCollection.submitStaticsParam(hashMap);
                                        }
                                        com.qq.reader.common.monitor.i.a("event_C269", hashMap, ReaderApplication.getApplicationImp());
                                        StatisticsManager.a().a("event_C269", hashMap);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (!this.b.isCancel) {
                                Message obtainMessage = aVar.obtainMessage(1);
                                obtainMessage.obj = arrayList;
                                Bundle bundle = new Bundle();
                                bundle.putString("SEARCH_KEY", str);
                                obtainMessage.setData(bundle);
                                aVar.sendMessage(obtainMessage);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a.getSearchAssociateProtocolURL());
            try {
                stringBuilder.append("key=").append(URLEncoder.encode(str, "utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            setUrl(stringBuilder.toString());
        }
    }

    public static class a extends Handler {
        WeakReference<NativeBookStoreSearchActivity> a;

        a(NativeBookStoreSearchActivity nativeBookStoreSearchActivity) {
            this.a = new WeakReference(nativeBookStoreSearchActivity);
        }

        public void handleMessage(Message message) {
            NativeBookStoreSearchActivity nativeBookStoreSearchActivity = (NativeBookStoreSearchActivity) this.a.get();
            if (nativeBookStoreSearchActivity != null && !nativeBookStoreSearchActivity.isFinishing()) {
                switch (message.what) {
                    case 1:
                        try {
                            String string = message.getData().getString("SEARCH_KEY");
                            String str = string == null ? "" : string;
                            if (!nativeBookStoreSearchActivity.K.a() && str.equals(nativeBookStoreSearchActivity.J.getText().toString().trim())) {
                                nativeBookStoreSearchActivity.K.a((ArrayList) message.obj);
                                nativeBookStoreSearchActivity.K.a(str);
                                nativeBookStoreSearchActivity.K.notifyDataSetChanged();
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    case 2:
                        try {
                            if (!nativeBookStoreSearchActivity.isFinishing()) {
                                nativeBookStoreSearchActivity.J.c();
                            }
                        } catch (Exception e2) {
                        }
                        nativeBookStoreSearchActivity.K.b();
                        nativeBookStoreSearchActivity.K.notifyDataSetChanged();
                        return;
                    case 3:
                        nativeBookStoreSearchActivity.a((String) message.obj);
                        return;
                    case 4:
                        if (message.obj instanceof ArrayList) {
                            nativeBookStoreSearchActivity.S = (List) message.obj;
                            return;
                        }
                        return;
                    case 5:
                        nativeBookStoreSearchActivity.x();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.nativebookstore_search_layout);
        i();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeMessages(506);
        this.M.removeCallbacksAndMessages(null);
        this.L.a(this.H);
        if (!isFinishing()) {
            this.J.c();
            this.A.a((Activity) this);
        }
        ao.e.a(this.J.getWindowToken(), (Context) this);
    }

    private void i() {
        a();
        q();
        t();
    }

    private void q() {
        this.E = findViewById(R.id.search_result_content);
        this.y = findViewById(R.id.search_bottom_container);
        this.v = (TextView) this.y.findViewById(R.id.search_pageNO);
        this.w = (ImageView) this.E.findViewById(R.id.img_search_tab_view_shadow);
        this.t = this.y.findViewById(R.id.search_bottom_recommoncontainer);
        this.u = this.y.findViewById(R.id.search_nothing);
        this.s = this.y.findViewById(R.id.search_recommon_words);
        this.s.setOnClickListener(null);
        this.u.setOnClickListener(this);
        this.n = (XListView) findViewById(R.id.search_result_list);
        this.n.setCrashTag(CustomArrayList.Class_SearchActivity);
        this.n.setXListFooter(new SearchXListFooter(this));
        this.D = new com.qq.reader.view.animation.e.a(1).a(this.y).a(ao.a(80.0f)).a();
        this.D.a(new OnScrollListener(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (this.a.y.getVisibility() == 0) {
                    Adapter adapter = absListView.getAdapter();
                    if (adapter != null) {
                        SearchBaseCard searchBaseCard;
                        if (i < 0) {
                            searchBaseCard = (SearchBaseCard) adapter.getItem(0);
                        } else if (i >= adapter.getCount()) {
                            searchBaseCard = (SearchBaseCard) adapter.getItem(adapter.getCount() - 1);
                        } else {
                            searchBaseCard = (SearchBaseCard) adapter.getItem(i);
                        }
                        if (searchBaseCard != null && this.a.I != searchBaseCard.getPageNo()) {
                            this.a.I = searchBaseCard.getPageNo();
                            this.a.v.setText((this.a.I + 1) + " / " + ((ap) this.a.j).d);
                        }
                    }
                }
            }
        });
        this.n.setOnScrollListener(this.D);
        this.n.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int headerViewsCount = i - ((ListView) adapterView).getHeaderViewsCount();
                if (headerViewsCount < this.a.h.getCount() && headerViewsCount >= 0) {
                    ((SearchBaseCard) this.a.h.b(headerViewsCount)).doClickedCard();
                }
            }
        });
        this.n.setXListViewListener(new com.qq.reader.view.pullupdownlist.XListView.a(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.mHandler.sendEmptyMessage(500005);
            }
        });
        this.g = this.n;
        this.o = (InputMethodManager) getSystemService("input_method");
        this.A = (SearchTabView) findViewById(R.id.search_tab_view);
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.A.a(h.a(null, "search/default_search_tab_info.txt"));
                this.a.A.setSearchTabListener(new com.qq.reader.module.bookstore.search.a(this) {
                    final /* synthetic */ AnonymousClass13 a;

                    {
                        this.a = r1;
                    }

                    public void a(int i, int i2) {
                        com.qq.reader.common.monitor.debug.c.e("onTitleClicked", "index = " + i + " popupStates = " + i2);
                        if (i2 == 1 || i == 1) {
                            Map hashMap = new HashMap();
                            hashMap.put("exact", String.valueOf(((ap) this.a.a.j).e));
                            hashMap.put(s.ORIGIN, String.valueOf(i));
                            if (this.a.a.H != null) {
                                this.a.a.H.submitStaticsParam(hashMap);
                            }
                            com.qq.reader.common.monitor.i.a("event_B168", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_B168", hashMap);
                        }
                    }

                    public void a_(String str) {
                        this.a.a.a(this.a.a.q, str);
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, str);
                        com.qq.reader.common.monitor.i.a("event_B169", hashMap, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("event_B169", hashMap);
                    }
                });
            }
        }, 500);
        this.G = findViewById(R.id.loading_none_layout);
    }

    protected void b() {
        super.b();
        this.y.setVisibility(8);
        this.G.setVisibility(8);
    }

    private void b(String str, String str2) {
        this.p = true;
        this.q = str;
        Bundle bundle = new Bundle();
        if (B.equals(str2)) {
            bundle.putInt("searchstate", 0);
        } else {
            bundle.putInt("searchstate", 1);
        }
        bundle.putString("KEY_JUMP_PAGENAME", "search");
        bundle.putString("searchkey", URLEncoder.encode(str));
        bundle.putSerializable("searchParamSearchMode", this.H);
        bundle.putInt("searchpageNO", this.z);
        bundle.putString("searchParams", str2);
        b(bundle);
    }

    private void b(Bundle bundle) {
        this.F = bundle;
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
            this.n.setPullLoadEnable(true);
            this.n.setAdapter(this.h);
            a(false, false);
        }
    }

    private void r() {
        if (!this.p) {
            Bundle bundle = new Bundle(this.F);
            int i = this.z + 1;
            this.z = i;
            bundle.putInt("searchpageNO", i);
            bundle.putInt("nextstart", ((ap) this.j).v);
            b a = com.qq.reader.module.bookstore.qnative.e.a().a(bundle, this);
            a.a(1001);
            com.qq.reader.module.bookstore.qnative.d.b().a(getApplicationContext(), a, this.mHandler, true);
            this.p = true;
        }
    }

    protected ah a(Bundle bundle) {
        final ah ahVar = new ah(this, bundle.getString("bookname"));
        ahVar.a(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreSearchActivity b;

            public void onClick(View view) {
                com.qq.reader.common.monitor.i.a("event_B158", null, ReaderApplication.getApplicationImp());
                final String g = ahVar.g();
                final String h = ahVar.h();
                if (TextUtils.isEmpty(g)) {
                    af.a(this.b, (CharSequence) "书名不能为空", 0).a();
                } else if (com.qq.reader.common.login.c.b()) {
                    Message obtainMessage = this.b.mHandler.obtainMessage();
                    obtainMessage.what = 507;
                    Bundle bundle = new Bundle();
                    bundle.putString("bookname", g);
                    bundle.putString("authorname", h);
                    obtainMessage.obj = bundle;
                    this.b.mHandler.sendMessage(obtainMessage);
                } else {
                    this.b.setLoginNextTask(new com.qq.reader.common.login.a(this) {
                        final /* synthetic */ AnonymousClass14 c;

                        public void a(int i) {
                            switch (i) {
                                case 1:
                                    Message obtainMessage = this.c.b.mHandler.obtainMessage();
                                    obtainMessage.what = 507;
                                    Bundle bundle = new Bundle();
                                    bundle.putString("bookname", g);
                                    bundle.putString("authorname", h);
                                    obtainMessage.obj = bundle;
                                    this.c.b.mHandler.sendMessage(obtainMessage);
                                    return;
                                default:
                                    return;
                            }
                        }
                    });
                    this.b.startLogin();
                }
            }
        });
        ahVar.b(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreSearchActivity b;

            public void onClick(View view) {
                ahVar.dismiss();
            }
        });
        ahVar.a(new r(this) {
            final /* synthetic */ NativeBookStoreSearchActivity b;

            public t a() {
                return ahVar.c();
            }

            public void onDismiss(DialogInterface dialogInterface) {
                super.onDismiss(dialogInterface);
                if (this.b.o.isActive()) {
                    this.b.o.toggleSoftInput(1, 2);
                }
            }
        });
        return ahVar;
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 506:
                f();
                break;
            case 507:
                Bundle bundle = (Bundle) message.obj;
                com.qq.reader.common.readertask.g.a().a(new BookReleaseAlertTask(bundle.getString("bookname"), bundle.getString("authorname"), new c(this) {
                    final /* synthetic */ NativeBookStoreSearchActivity a;

                    {
                        this.a = r1;
                    }

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        String str2;
                        int i;
                        String string = ReaderApplication.getApplicationImp().getResources().getString(R.string.search_alert_success);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int optInt = jSONObject.optInt("code");
                            if (optInt != 0) {
                                string = jSONObject.optString("errmsg");
                            }
                            int i2 = optInt;
                            str2 = string;
                            i = i2;
                        } catch (Exception e) {
                            i = -1;
                            str2 = ReaderApplication.getApplicationImp().getResources().getString(R.string.net_not_available);
                        }
                        this.a.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass17 c;

                            public void run() {
                                try {
                                    af.a(ReaderApplication.getApplicationImp(), str2, 0).a();
                                    if (i == 0 && this.c.a.x != null) {
                                        this.c.a.x.dismiss();
                                    }
                                } catch (Throwable th) {
                                    com.qq.reader.common.monitor.debug.c.e("NativeBookStoreSearchActivity", th.getMessage());
                                }
                            }
                        });
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                        this.a.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass17 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                af.a(ReaderApplication.getApplicationImp(), (int) R.string.net_not_available, 0).a();
                            }
                        });
                    }
                }));
                return true;
            case 500000:
            case 500001:
                try {
                    if (message.obj != null) {
                        b bVar = (ap) message.obj;
                        s();
                        if (bVar.r() == 0) {
                            this.j.a(bVar);
                            if (!(bVar.t == null || this.A == null)) {
                                this.A.a(bVar.t);
                            }
                            if (bVar.m().size() > 0) {
                                if (bVar.c == 0) {
                                    a(((ap) this.j).w, ((ap) this.j).a, ((ap) this.j).e);
                                } else {
                                    this.y.setVisibility(0);
                                }
                            }
                            Map hashMap = new HashMap();
                            hashMap.put("exact", String.valueOf(bVar.e));
                            com.qq.reader.common.monitor.i.a("event_B162", hashMap, ReaderApplication.getApplicationImp());
                        } else {
                            this.j.addMore(bVar);
                        }
                    }
                    c();
                    this.p = false;
                    if (this.h != null) {
                        if (this.j.m().size() == 0) {
                            if (((ap) this.j).c == 0) {
                                this.G.setVisibility(0);
                                Map hashMap2 = new HashMap();
                                if (this.H != null) {
                                    this.H.submitStaticsParam(hashMap2);
                                }
                                com.qq.reader.common.monitor.i.a("event_B211", hashMap2, ReaderApplication.getApplicationImp());
                                StatisticsManager.a().a("event_B211", hashMap2);
                                this.n.setVisibility(8);
                                this.A.setVisibility(8);
                            } else {
                                this.n.g();
                            }
                        } else if (this.j.s()) {
                            this.r = false;
                            this.n.e();
                        } else {
                            this.r = true;
                            this.n.c();
                        }
                        if (this.h.b() || this.n.getAdapter() == null) {
                            this.n.setAdapter(this.h);
                        } else {
                            this.h.notifyDataSetChanged();
                        }
                    }
                } catch (Exception e) {
                }
                return true;
            case 500005:
                r();
                break;
        }
        return super.handleMessageImp(message);
    }

    protected void d() {
        this.p = false;
        if (this.n.getVisibility() != 0 || this.n.getAdapter().getCount() <= 1) {
            this.e.setVisibility(8);
            this.f.setVisibility(0);
            this.G.setVisibility(8);
        } else if (this.n.getFooterViewsCount() > 0) {
            this.n.d();
        }
    }

    private void s() {
        if (this.H != null && !this.H.needShowSearchTabView()) {
            this.A.setVisibility(8);
            LayoutParams layoutParams = (LayoutParams) this.n.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            this.n.setLayoutParams(layoutParams);
            this.w.setVisibility(8);
        }
    }

    protected void c() {
        super.c();
        this.A.setVisibility(0);
        s();
    }

    private void a(int i, ArrayList<String> arrayList, int i2) {
        this.y.setVisibility(0);
        this.D.a();
        this.I = 1;
        this.v.setText("1 / " + ((ap) this.j).d);
        if (i == 0) {
            this.t.setVisibility(8);
            this.D.a(ao.a(0.0f));
        } else if (i == 1) {
            this.t.setVisibility(0);
            this.s.setVisibility(8);
            this.u.setVisibility(0);
            this.D.a(ao.a(50.0f));
        } else if (i == 2) {
            Map hashMap = new HashMap();
            hashMap.put("exact", String.valueOf(i2));
            com.qq.reader.common.monitor.i.a("event_C169", hashMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_C169", hashMap);
            this.t.setVisibility(0);
            this.s.setVisibility(0);
            this.u.setVisibility(8);
            try {
                this.s.setVisibility(0);
                LinearLayout linearLayout = (LinearLayout) this.s.findViewById(R.id.row1);
                LinearLayout linearLayout2 = (LinearLayout) this.s.findViewById(R.id.row2);
                linearLayout.removeAllViews();
                linearLayout2.removeAllViews();
                linearLayout2.setVisibility(8);
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                while (i4 < arrayList.size()) {
                    int i6;
                    JSONObject jSONObject = new JSONObject((String) arrayList.get(i4));
                    final String optString = jSONObject.optString("keyword");
                    final String optString2 = jSONObject.optString("id");
                    final int optInt = jSONObject.optInt("type", 0);
                    View textView = new TextView(this);
                    textView.setText(optString);
                    textView.setTextColor(getResources().getColor(R.color.text_color_c301));
                    textView.setTextSize(((float) getResources().getDimensionPixelSize(R.dimen.text_size_class_3)) / getResources().getDisplayMetrics().density);
                    textView.setSingleLine();
                    textView.setEllipsize(TruncateAt.END);
                    textView.setTextColor(getResources().getColorStateList(R.color.localstore_loading_failed_textcolor_selector));
                    ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins((int) (16.0f * com.qq.reader.common.c.a.bZ), 0, 0, 0);
                    textView.setLayoutParams(layoutParams);
                    textView.setTag(Integer.valueOf(i4));
                    final int i7 = i2;
                    textView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ NativeBookStoreSearchActivity e;

                        public void onClick(View view) {
                            Map hashMap = new HashMap();
                            hashMap.put("exact", String.valueOf(i7));
                            Intent intent = new Intent();
                            switch (optInt) {
                                case 2:
                                    intent.putExtra("KEY_ACTIONID", optString2);
                                    intent.putExtra("KEY_ACTIONTAG", "-1,-1,6");
                                    intent.putExtra("KEY_JUMP_PAGENAME", "classify");
                                    intent.setClass(this.e, NativeBookStoreTwoLevelActivity.class);
                                    com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                                    this.e.startActivity(intent);
                                    hashMap.put(s.ORIGIN, "0");
                                    com.qq.reader.common.monitor.i.a("event_C170", hashMap, this.e.getContext());
                                    StatisticsManager.a().a("event_C170", hashMap);
                                    return;
                                case 3:
                                    intent.putExtra("KEY_ACTIONID", optString2);
                                    intent.putExtra("LOCAL_STORE_IN_TITLE", optString);
                                    intent.putExtra("KEY_JUMP_PAGENAME", "search_label");
                                    intent.setClass(this.e, NativeBookStoreTwoLevelActivity.class);
                                    com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                                    this.e.startActivity(intent);
                                    hashMap.put(s.ORIGIN, "1");
                                    com.qq.reader.common.monitor.i.a("event_C170", hashMap, this.e.getContext());
                                    StatisticsManager.a().a("event_C170", hashMap);
                                    return;
                                default:
                                    switch (optInt) {
                                        case 1:
                                            com.qq.reader.common.monitor.i.a("event_C237", hashMap, this.e.getContext());
                                            StatisticsManager.a().a("event_C237", hashMap);
                                            break;
                                        case 23:
                                            com.qq.reader.common.monitor.i.a("event_C238", hashMap, this.e.getContext());
                                            StatisticsManager.a().a("event_C238", hashMap);
                                            break;
                                        case 101:
                                            com.qq.reader.common.monitor.i.a("event_C239", hashMap, this.e.getContext());
                                            StatisticsManager.a().a("event_C239", hashMap);
                                            break;
                                    }
                                    this.e.a(((TextView) view).getText().toString(), NativeBookStoreSearchActivity.B);
                                    return;
                            }
                        }
                    });
                    i7 = i5 + optString.length();
                    if (i7 > 14 || i3 >= 3) {
                        linearLayout2.setVisibility(0);
                        linearLayout2.addView(textView);
                        this.D.a(ao.a(80.0f));
                        i6 = i3;
                    } else {
                        i6 = i3 + 1;
                        linearLayout.addView(textView);
                        this.D.a(ao.a(50.0f));
                    }
                    i4++;
                    i3 = i6;
                    i5 = i7;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String e() {
        return B;
    }

    public void doFunction(Bundle bundle) {
        super.doFunction(bundle);
        if ("PARA_TYPE_REFRESH".equals(bundle.getString("function_type")) && this.h != null) {
            this.mHandler.sendEmptyMessageDelayed(10000508, 100);
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    public void f() {
        this.J.setEnabled(true);
        this.J.setFocusable(true);
        this.J.requestFocus();
        ao.e.a(this.J, (Context) this);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            a(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void a(MotionEvent motionEvent) {
        try {
            int[] iArr = new int[]{0, 0};
            this.J.getLocationInWindow(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            int width = this.J.getWidth() + i;
            int height = this.J.getHeight() + i2;
            if (motionEvent.getRawX() < ((float) i) || motionEvent.getRawX() > ((float) width) || motionEvent.getY() < ((float) i2) || motionEvent.getRawY() > ((float) height)) {
                ao.e.a(this.J.getWindowToken(), (Context) this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void t() {
        this.H = (ISearchParamCollection) getIntent().getSerializableExtra("searchParamSearchMode");
        this.H = ao.a(this.H);
        this.J = (DropDownEditText) findViewById(R.id.searchBar);
        this.P = findViewById(R.id.clearTextBtn);
        this.P.setOnClickListener(this);
        this.K = new d(this);
        this.J.setAdapter(this.K);
        this.L = g.a(ReaderApplication.getApplicationImp());
        this.J.setHint("请输入书名或者作者名");
        this.J.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if ((i3 == 1 || i2 == 0) && System.currentTimeMillis() - this.a.Q > 500) {
                    this.a.O = true;
                }
            }

            public void afterTextChanged(Editable editable) {
                try {
                    com.qq.reader.common.monitor.debug.c.e(this.a.d, "search bar input call " + editable.toString());
                    if (editable.toString().length() <= 0 || !TextUtils.isEmpty(editable.toString().trim())) {
                        String trim = editable.toString().trim();
                        this.a.K.a(trim);
                        this.a.K.a(this.a.H);
                        if (trim.length() == 0) {
                            this.a.w();
                        } else if (this.a.O) {
                            SearchData searchData = new SearchData();
                            searchData.mType = 0;
                            searchData.setKeyWord(trim);
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(searchData);
                            this.a.K.b(arrayList);
                            this.a.K.notifyDataSetChanged();
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, "1");
                            if (this.a.H != null) {
                                this.a.H.submitStaticsParam(hashMap);
                            }
                            com.qq.reader.common.monitor.i.a("event_C269", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_C269", hashMap);
                            this.a.M.removeMessages(3);
                            Message obtainMessage = this.a.M.obtainMessage(3);
                            obtainMessage.obj = trim;
                            this.a.M.sendMessageDelayed(obtainMessage, 100);
                        }
                        this.a.v();
                        return;
                    }
                    this.a.u();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.J.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.w();
                this.a.A.a(this.a);
            }
        });
        this.J.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (!z) {
                }
            }
        });
        this.J.setDropDownVerticalOffset((int) getResources().getDimension(R.dimen.common_dp_10));
        this.M = new a(this);
        this.N = new com.qq.reader.module.bookstore.search.e();
        this.N.a((ViewGroup) findViewById(R.id.search_default_page), this, this.H);
        a(getIntent());
        this.J.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    AbsSearchWords absSearchWords = (AbsSearchWords) this.a.K.getItem(i);
                    String trim = this.a.J.getText().toString().trim();
                    if (absSearchWords != null) {
                        Map hashMap;
                        if (absSearchWords.mType != 14) {
                            ao.e.a(this.a.J.getWindowToken(), this.a);
                            this.a.O = false;
                            this.a.Q = System.currentTimeMillis();
                        }
                        boolean z = absSearchWords instanceof SearchHistory;
                        if (z && absSearchWords.mType != 16) {
                            hashMap = new HashMap();
                            if (this.a.H != null) {
                                this.a.H.submitStaticsParam(hashMap);
                            }
                            com.qq.reader.common.monitor.i.a("event_C265", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_C265", hashMap);
                        }
                        String word;
                        switch (absSearchWords.mType) {
                            case 0:
                                if (i == 0 && !z) {
                                    hashMap = new HashMap();
                                    hashMap.put(s.ORIGIN, "1");
                                    hashMap.put("keyword", trim);
                                    NativeBookStoreSearchActivity.b(hashMap, (SearchData) absSearchWords);
                                    if (this.a.H != null) {
                                        this.a.H.submitStaticsParam(hashMap);
                                    }
                                    com.qq.reader.common.monitor.i.a("event_C270", hashMap, ReaderApplication.getApplicationImp());
                                    StatisticsManager.a().a("event_C270", hashMap);
                                    Map hashMap2 = new HashMap();
                                    hashMap2.put(s.ORIGIN, "3");
                                    hashMap2.put("keyword", trim);
                                    if (this.a.H != null) {
                                        this.a.H.submitStaticsParam(hashMap2);
                                    }
                                    com.qq.reader.common.monitor.i.a("event_C268", hashMap2, ReaderApplication.getApplicationImp());
                                    StatisticsManager.a().a("event_C268", hashMap2);
                                }
                                this.a.C.clear();
                                com.qq.reader.qurl.c.a(this.a, absSearchWords.getQurl());
                                break;
                            case 1:
                            case 9:
                                com.qq.reader.qurl.c.a(this.a, absSearchWords.getQurl());
                                if (!z) {
                                    hashMap = new HashMap();
                                    hashMap.put(s.ORIGIN, "3");
                                    hashMap.put("keyword", trim);
                                    NativeBookStoreSearchActivity.b(hashMap, (SearchData) absSearchWords);
                                    if (this.a.H != null) {
                                        this.a.H.submitStaticsParam(hashMap);
                                    }
                                    com.qq.reader.common.monitor.i.a("event_C270", hashMap, ReaderApplication.getApplicationImp());
                                    StatisticsManager.a().a("event_C270", hashMap);
                                    break;
                                }
                                break;
                            case 3:
                            case 4:
                                com.qq.reader.qurl.c.a(this.a, absSearchWords.getQurl());
                                if (!z) {
                                    hashMap = new HashMap();
                                    hashMap.put(s.ORIGIN, "4");
                                    hashMap.put("keyword", trim);
                                    NativeBookStoreSearchActivity.b(hashMap, (SearchData) absSearchWords);
                                    if (this.a.H != null) {
                                        this.a.H.submitStaticsParam(hashMap);
                                    }
                                    com.qq.reader.common.monitor.i.a("event_C270", hashMap, ReaderApplication.getApplicationImp());
                                    StatisticsManager.a().a("event_C270", hashMap);
                                    break;
                                }
                                break;
                            case 5:
                                if (!z) {
                                    com.qq.reader.qurl.c.a(this.a, absSearchWords.getQurl());
                                    break;
                                }
                                word = absSearchWords.getWord();
                                for (Mark mark : i.c().g()) {
                                    trim = mark.getBookName().trim();
                                    if (trim.lastIndexOf(".") > 0) {
                                        trim = trim.substring(0, trim.lastIndexOf("."));
                                    }
                                    if (trim.equals(word) && mark.getType() != 8) {
                                        this.a.a(mark);
                                        break;
                                    }
                                }
                                break;
                            case 6:
                                if (!z) {
                                    hashMap = new HashMap();
                                    hashMap.put(s.ORIGIN, Constants.VIA_SHARE_TYPE_INFO);
                                    hashMap.put("keyword", trim);
                                    NativeBookStoreSearchActivity.b(hashMap, (SearchData) absSearchWords);
                                    if (this.a.H != null) {
                                        this.a.H.submitStaticsParam(hashMap);
                                    }
                                    com.qq.reader.common.monitor.i.a("event_C270", hashMap, ReaderApplication.getApplicationImp());
                                    StatisticsManager.a().a("event_C270", hashMap);
                                    com.qq.reader.qurl.c.a(this.a, absSearchWords.getQurl());
                                    break;
                                }
                                Object obj;
                                String word2 = absSearchWords.getWord();
                                for (Mark mark2 : i.c().g()) {
                                    word = mark2.getBookName().trim();
                                    if (word.lastIndexOf(".") > 0) {
                                        word = word.substring(0, word.lastIndexOf("."));
                                    }
                                    if (word.equals(word2) && mark2.getType() == 8) {
                                        this.a.a(mark2);
                                        obj = 1;
                                        if (obj == null) {
                                            com.qq.reader.qurl.c.a(this.a, absSearchWords.getQurl());
                                            break;
                                        }
                                    }
                                }
                                obj = null;
                                if (obj == null) {
                                    com.qq.reader.qurl.c.a(this.a, absSearchWords.getQurl());
                                }
                                break;
                            case 8:
                                Object hashMap3 = new HashMap();
                                hashMap3.put("key", absSearchWords.getKeyWord());
                                hashMap3.put(s.ORIGIN, "909");
                                hashMap3.put("keyword", trim);
                                com.qq.reader.qurl.c.a(this.a, absSearchWords.getQurl(), new JumpActivityParameter().a(hashMap3));
                                if (!z) {
                                    hashMap = new HashMap();
                                    hashMap.put(s.ORIGIN, "5");
                                    hashMap.put("keyword", trim);
                                    NativeBookStoreSearchActivity.b(hashMap, (SearchData) absSearchWords);
                                    if (this.a.H != null) {
                                        this.a.H.submitStaticsParam(hashMap);
                                    }
                                    com.qq.reader.common.monitor.i.a("event_C270", hashMap, ReaderApplication.getApplicationImp());
                                    StatisticsManager.a().a("event_C270", hashMap);
                                    break;
                                }
                                break;
                            case 10:
                            case 11:
                            case 12:
                                if (!z) {
                                    hashMap = new HashMap();
                                    hashMap.put(s.ORIGIN, "4");
                                    hashMap.put("keyword", trim);
                                    NativeBookStoreSearchActivity.b(hashMap, (SearchData) absSearchWords);
                                    if (this.a.H != null) {
                                        this.a.H.submitStaticsParam(hashMap);
                                    }
                                    com.qq.reader.common.monitor.i.a("event_C270", hashMap, ReaderApplication.getApplicationImp());
                                    StatisticsManager.a().a("event_C270", hashMap);
                                }
                                com.qq.reader.qurl.c.a(this.a, absSearchWords.getQurl());
                                break;
                            case 13:
                            case 15:
                                if (absSearchWords.mTag != null) {
                                    if (!z) {
                                        hashMap = new HashMap();
                                        hashMap.put(s.ORIGIN, "2");
                                        hashMap.put("keyword", trim);
                                        NativeBookStoreSearchActivity.b(hashMap, (SearchData) absSearchWords);
                                        if (this.a.H != null) {
                                            this.a.H.submitStaticsParam(hashMap);
                                        }
                                        com.qq.reader.common.monitor.i.a("event_C270", hashMap, ReaderApplication.getApplicationImp());
                                        StatisticsManager.a().a("event_C270", hashMap);
                                    }
                                    this.a.a((Mark) absSearchWords.mTag);
                                    break;
                                }
                                return;
                            case 14:
                                if (absSearchWords.mTag instanceof ArrayList) {
                                    com.qq.reader.common.monitor.f.d(CustomArrayList.Class_SearchActivity, "folder clicked");
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.addAll(this.a.K.c());
                                    ArrayList arrayList2 = (ArrayList) absSearchWords.mTag;
                                    arrayList.subList(0, 1).clear();
                                    arrayList.remove(1);
                                    Iterator it = arrayList2.iterator();
                                    while (it.hasNext()) {
                                        arrayList.add(1, (SearchData) it.next());
                                    }
                                    Message obtainMessage = this.a.M.obtainMessage(1);
                                    obtainMessage.obj = arrayList;
                                    Bundle bundle = new Bundle();
                                    bundle.putString("SEARCH_KEY", this.a.K.d());
                                    obtainMessage.setData(bundle);
                                    this.a.M.sendMessageDelayed(obtainMessage, 100);
                                    com.qq.reader.common.monitor.i.a("event_C130", null, ReaderApplication.getApplicationImp());
                                    StatisticsManager.a().a("event_C130", null);
                                    break;
                                }
                                break;
                            case 16:
                                this.a.L.b(this.a.H);
                                this.a.K.b(new ArrayList());
                                this.a.K.notifyDataSetChanged();
                                break;
                            default:
                                com.qq.reader.qurl.c.a(this.a, absSearchWords.getQurl());
                                break;
                        }
                        if (absSearchWords.mType != 14 && absSearchWords.mType != 16) {
                            SearchHistory a;
                            if (absSearchWords instanceof SearchData) {
                                a = this.a.a((SearchData) absSearchWords);
                            } else if (absSearchWords instanceof SearchHistory) {
                                SearchHistory searchHistory = new SearchHistory(System.currentTimeMillis(), absSearchWords.getKeyWord(), absSearchWords.getType());
                                searchHistory.setQurl(absSearchWords.getQurl());
                                searchHistory.setId(((SearchHistory) absSearchWords).getId());
                                a = searchHistory;
                            } else {
                                a = null;
                            }
                            if (a != null) {
                                this.a.L.a(a, this.a.H);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.searchBtn).setOnClickListener(this);
        findViewById(R.id.websearch_header_back).setOnClickListener(this);
        this.M.sendEmptyMessage(5);
        this.J.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                switch (i) {
                    case 0:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        ao.e.a(this.a.J.getWindowToken(), this.a);
                        this.a.a(this.a.J.getText().toString(), NativeBookStoreSearchActivity.B);
                        CharSequence trim = this.a.J.getText().toString().trim();
                        if (!TextUtils.isEmpty(trim)) {
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, "2");
                            hashMap.put("keyword", trim);
                            if (this.a.H != null) {
                                this.a.H.submitStaticsParam(hashMap);
                            }
                            com.qq.reader.common.monitor.i.a("event_C268", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_C268", hashMap);
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.y();
            }
        }, 500);
        v();
    }

    private void u() {
        if (!this.J.getText().toString().equals("")) {
            this.J.setText("");
        }
        this.J.requestFocus();
        ao.e.a(this.J, (Context) this);
    }

    private void v() {
        if (this.P != null) {
            if (this.J.getText().toString().length() > 0) {
                this.P.setVisibility(0);
            } else {
                this.P.setVisibility(4);
            }
        }
    }

    private boolean w() {
        ArrayList c = this.L.c(this.H);
        if (this.J.getText().toString().length() == 0) {
            if (c.size() > 0) {
                this.K.b(c);
                this.K.notifyDataSetChanged();
                Map hashMap = new HashMap();
                if (this.H != null) {
                    this.H.submitStaticsParam(hashMap);
                }
                com.qq.reader.common.monitor.i.a("event_C264", hashMap, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C264", hashMap);
                return true;
            }
            this.K.b(new ArrayList());
            this.K.notifyDataSetChanged();
            y();
        }
        return false;
    }

    private void a(String str) {
        b(str + "   ------ 查询 关键词");
        if (this.a != null) {
            this.a.setCancel(true);
            com.qq.reader.common.readertask.g.a().b(this.a);
        }
        this.a = new SearchKeywordAssociateTask(this.M, str, this.H);
        com.qq.reader.common.readertask.g.a().a(this.a);
    }

    public void a(String str, String str2) {
        String str3;
        if (str == null || "".equals(str)) {
            str3 = this.c;
        } else {
            str3 = str.trim();
        }
        this.A.a((Activity) this);
        this.z = 0;
        ao.e.a(this.J.getWindowToken(), (Context) this);
        this.O = false;
        this.Q = System.currentTimeMillis();
        this.R = true;
        if (str3 == null || str3.length() <= 0 || getResources().getString(R.string.feed_titlebar_hint).equals(str3)) {
            af.a((Context) this, (CharSequence) "请先输入搜索关键词", 0).a();
            return;
        }
        if (this.C.contains(str3)) {
            this.C.remove(str3);
        }
        this.C.add(str3);
        this.N.a();
        this.E.setVisibility(0);
        s();
        b(str3, str2);
        this.M.removeMessages(3);
        if (this.a != null) {
            this.a.setCancel(true);
            com.qq.reader.common.readertask.g.a().b(this.a);
        }
        if (!this.J.getText().toString().equals(str3)) {
            this.J.setText(String.valueOf(str3));
            Selection.setSelection(this.J.getText(), this.J.getText().length());
        }
        this.L.a(new SearchHistory(System.currentTimeMillis(), str3, 0), this.H);
        if (this.J.d() && !isFinishing()) {
            this.J.c();
        }
        if (this.S != null) {
            this.N.a(this.S);
            this.S = null;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchBtn:
                this.C.clear();
                a(this.J.getText().toString(), B);
                if (!TextUtils.isEmpty(this.J.getText().toString().trim())) {
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "1");
                    hashMap.put("keyword", this.J.getText().toString().trim());
                    if (this.H != null) {
                        this.H.submitStaticsParam(hashMap);
                    }
                    com.qq.reader.common.monitor.i.a("event_C268", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_C268", hashMap);
                    return;
                }
                return;
            case R.id.clearTextBtn:
                u();
                return;
            case R.id.websearch_header_back:
                if (this.J.d() && !isFinishing()) {
                    this.J.c();
                    y();
                }
                this.A.a((Activity) this);
                this.C.clear();
                if (this.R) {
                    this.N.b();
                    this.E.setVisibility(8);
                    this.R = false;
                    y();
                    return;
                }
                finish();
                return;
            case R.id.search_nothing:
                Bundle bundle = new Bundle();
                bundle.putString("bookname", this.q);
                this.x = a(bundle);
                try {
                    if (!isFinishing()) {
                        this.x.f_();
                    }
                    com.qq.reader.common.monitor.i.a("event_B157", null, ReaderApplication.getApplicationImp());
                    return;
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("NativeBookStoreSearchActivity", e.getMessage());
                    return;
                }
            default:
                return;
        }
    }

    private static void b(Object obj) {
        com.qq.reader.common.monitor.debug.c.e("PART_TWO", String.valueOf(obj));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBackPressed() {
        /*
        r2 = this;
        r0 = r2.J;
        r0 = r0.d();
        if (r0 == 0) goto L_0x0017;
    L_0x0008:
        r0 = r2.isFinishing();
        if (r0 != 0) goto L_0x0017;
    L_0x000e:
        r0 = r2.J;
        r0.c();
        r2.y();
    L_0x0016:
        return;
    L_0x0017:
        r0 = r2.A;
        r0 = r0.a(r2);
        if (r0 != 0) goto L_0x0016;
    L_0x001f:
        r0 = r2.C;
        r0 = r0.size();
        r1 = 2;
        if (r0 < r1) goto L_0x004b;
    L_0x0028:
        r0 = r2.C;
        r1 = r2.C;
        r1 = r1.size();
        r1 = r1 + -1;
        r0.remove(r1);
        r0 = r2.C;
        r1 = r2.C;
        r1 = r1.size();
        r1 = r1 + -1;
        r0 = r0.remove(r1);
        r0 = (java.lang.String) r0;
        r1 = B;
        r2.a(r0, r1);
        goto L_0x0016;
    L_0x004b:
        r0 = r2.C;
        r0.clear();
        r0 = r2.b;
        r1 = 1;
        if (r0 != r1) goto L_0x006c;
    L_0x0055:
        r0 = r2.R;
        if (r0 == 0) goto L_0x0070;
    L_0x0059:
        r0 = r2.N;
        r0.b();
        r0 = r2.E;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = 0;
        r2.R = r0;
        r2.y();
        goto L_0x0016;
    L_0x006c:
        r0 = r2.b;
        if (r0 != 0) goto L_0x0070;
    L_0x0070:
        super.onBackPressed();
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.activity.NativeBookStoreSearchActivity.onBackPressed():void");
    }

    private SearchHistory a(SearchData searchData) {
        if (searchData == null) {
            return null;
        }
        int type = searchData.getType();
        Mark mark;
        if (searchData.mType == 13) {
            int i;
            mark = (Mark) searchData.mTag;
            if (mark == null || mark.getType() == 8) {
                i = type;
            } else {
                i = 5;
            }
            type = i;
        } else if (searchData.mType == 15) {
            mark = (Mark) searchData.mTag;
            if (mark != null && mark.getType() == 8) {
                type = 6;
            }
        }
        SearchHistory searchHistory = new SearchHistory(System.currentTimeMillis(), searchData.getKeyWord(), type);
        searchHistory.setQurl(searchData.getQurl());
        return searchHistory;
    }

    private void a(final Mark mark) {
        if (mark instanceof MusicBookGroup) {
            Intent intent = new Intent();
            intent.setClass(this, MusicActivity.class);
            startActivity(intent);
        } else if (mark instanceof TingBookMark) {
            o.e((Activity) this, mark.getId(), null);
        } else if (mark instanceof ComicBookMark) {
            ComicBookMark comicBookMark = (ComicBookMark) mark;
            OnlineTag a = v.b().a(String.valueOf(comicBookMark.getBookId()));
            if (a != null && a.w() == 0 && mark.getIsFinish() == 1) {
                a.h(1);
                v.b().b(a);
            }
            com.qq.reader.module.comic.a.a().a((Context) this, comicBookMark);
        } else if (mark instanceof LocalMark) {
            String id = mark.getId();
            Intent intent2 = new Intent();
            Bundle bundle = new Bundle();
            if (new File(id).exists() && 4 != mark.getType()) {
                bundle.putString("filepath", mark.getId());
                bundle.putString("filename", mark.getBookName());
                bundle.putString("fileauthor", mark.getAuthor());
                bundle.putInt("fileencode", mark.getEncoding());
                intent2.putExtras(bundle);
                com.qq.reader.b.a(intent2, this);
            } else if (4 == mark.getType()) {
                OnlineTag a2 = v.b().a(mark.getId());
                if (a2 != null && a2.w() == 0 && mark.getIsFinish() == 1) {
                    a2.h(1);
                    v.b().b(a2);
                }
                bundle.putString("filepath", mark.getId());
                bundle.putString("filename", mark.getBookName());
                intent2.putExtras(bundle);
                intent2.putExtra("com.qq.reader.OnlineTag", a2);
                intent2.putExtra("com.qq.reader.fromonline", true);
                com.qq.reader.b.a(intent2, this);
            }
        } else if (!(mark instanceof DownloadMark)) {
            af.a(getApplicationContext(), (CharSequence) "该状态暂不支持。", 1).a();
        } else if (com.qq.reader.common.login.c.b()) {
            a((DownloadMark) mark);
        } else {
            this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeBookStoreSearchActivity b;

                public void a(int i) {
                    this.b.a((DownloadMark) mark);
                }
            };
            startLogin();
        }
    }

    private void x() {
        com.qq.reader.common.readertask.g.a().a(new SearchHotWordsTask(new c(this) {
            final /* synthetic */ NativeBookStoreSearchActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    NativeBookStoreSearchActivity.b("热词返回值:   " + str);
                    List parseHotword = SearchHotWords.parseHotword(str);
                    if (parseHotword.size() > 0) {
                        com.qq.reader.appconfig.a.d.a(ReaderApplication.getApplicationImp(), parseHotword, this.a.H);
                        Message obtainMessage = this.a.M.obtainMessage(4);
                        obtainMessage.obj = parseHotword;
                        this.a.M.sendMessage(obtainMessage);
                    }
                } catch (Exception e) {
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        }, this.H));
    }

    private void a(DownloadMark downloadMark) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (downloadMark != null) {
            bundle.putString("filepath", downloadMark.getId());
            bundle.putString("filename", downloadMark.getBookName());
            bundle.putString("fileauthor", downloadMark.getAuthor());
            bundle.putBoolean("detailpage_trial_read", true);
            bundle.putString("fileid", String.valueOf(downloadMark.getBookId()));
            intent.putExtras(bundle);
            com.qq.reader.b.a(intent, this);
        }
    }

    private void y() {
        if (!this.R || this.J.d()) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, com.qq.reader.appconfig.a.d.aS(ReaderApplication.getApplicationImp()) + "");
            if (this.H != null) {
                this.H.submitStaticsParam(hashMap);
            }
            com.qq.reader.common.monitor.i.a("event_C258", hashMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_C258", hashMap);
            if (this.N.c()) {
                com.qq.reader.common.monitor.i.a("event_C266", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C266", null);
            }
        }
    }

    private void a(final Intent intent) {
        Bundle extras = intent.getExtras();
        String str = null;
        if (extras != null) {
            str = extras.getString("searchkey");
            this.c = extras.getString("searchhint");
        }
        if (TextUtils.isEmpty(str)) {
            u();
            this.mHandler.sendEmptyMessageDelayed(506, 998);
        } else {
            this.Q = System.currentTimeMillis();
            this.O = false;
            String str2 = "";
            Object trim = str.trim();
            if (trim.length() > 20) {
                trim = trim.substring(0, 20);
            }
            this.J.setText(trim);
            this.J.setSelection(trim.length());
            a(str.trim(), B);
            this.b = intent.getIntExtra("searchbackstate", 0);
        }
        if (!TextUtils.isEmpty(this.c)) {
            this.J.setHint(this.c);
        }
        this.mHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ NativeBookStoreSearchActivity b;

            public void run() {
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, intent.getStringExtra("from"));
                if (this.b.H != null) {
                    this.b.H.submitStaticsParam(hashMap);
                }
                com.qq.reader.common.monitor.i.a("event_search", hashMap, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_search", hashMap);
            }
        }, 500);
    }

    private static void b(Map<String, String> map, SearchData searchData) {
        map.put("id", String.valueOf(searchData.id));
        map.put("algo_info", String.valueOf(searchData.mStatePara.a));
        map.put("origin_server", String.valueOf(searchData.mStatePara.c));
        map.put(Constants.PARAM_PLATFORM, String.valueOf(searchData.mStatePara.b));
        map.put("qurl", String.valueOf(searchData.mStatePara.d));
    }
}
