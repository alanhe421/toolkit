package com.qq.reader.module.bookstore.qweb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.activity.NewChapterViewActivity.TabViewBookInfo;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.k;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.QueryChapterBuyInfoTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.audio.AudioAuthCheckTask;
import com.qq.reader.cservice.download.audio.AuthCheckTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.audio.loader.QueryAudioChapterBuyInfoTask;
import com.qq.reader.module.bookchapter.b;
import com.qq.reader.module.bookchapter.c;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.f;
import com.qq.reader.module.bookchapter.online.g;
import com.qq.reader.module.bookchapter.online.h;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.a;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

public class ReaderPagerChapterFragment extends BaseFragment {
    public static final String RESULT_BOOKMARK_FREE = "resultChapterFree";
    public static final String RESULT_BOOKMARK_POINT = "resultBookmark";
    private final int MENU_BOOKMARK_download = 3;
    private final int MENU_BOOKMARK_redownload = 2;
    private String buyRecordCache;
    private View chapterEmptyView;
    private boolean hasGotBuyRecord = false;
    private boolean isInitedChapter = false;
    private long mBookPoint;
    private b mChapterAdapter;
    protected ListView mChapterListView;
    private TextView mChapterParserMessage;
    private LinearLayout mChapterPbLiner;
    private TabViewBookInfo mCurBook;
    private int mCurrentSelectPosition;
    private a mMenu;
    private View mOnlineChapterLoading;
    private g mOnlineHandle;
    private f mOnlineOperator;
    private OnlineTag mOnlineTag = null;
    private int mPositionChapter = 0;
    protected View root;
    private ArrayList<Mark> tempMarkList = new ArrayList();
    private int tempMarkSize = 30;

    public static ReaderPagerChapterFragment newInstance(TabViewBookInfo tabViewBookInfo, OnlineTag onlineTag, long j) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", tabViewBookInfo);
        bundle.putParcelable("onlinetag", onlineTag);
        bundle.putLong("bookpoint", j);
        ReaderPagerChapterFragment readerPagerChapterFragment = new ReaderPagerChapterFragment();
        readerPagerChapterFragment.setArguments(bundle);
        return readerPagerChapterFragment;
    }

    public static ReaderPagerChapterFragment newInstance(Bundle bundle) {
        ReaderPagerChapterFragment readerPagerChapterFragment = new ReaderPagerChapterFragment();
        if (bundle != null) {
            readerPagerChapterFragment.setArguments(new Bundle(bundle));
        }
        return readerPagerChapterFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActivity().setTheme(R.style.BookCoinChargeTheme);
        this.root = layoutInflater.inflate(R.layout.chapterlist, null);
        this.mCurBook = (TabViewBookInfo) getArguments().getSerializable("resultBook");
        this.mOnlineTag = (OnlineTag) getArguments().getParcelable("resultOnlinetag");
        this.mBookPoint = getArguments().getLong("resultMarkP", -1);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return this.root;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        createChapterListView();
        if (this.mOnlineTag != null) {
            com.qq.reader.common.readertask.g.a().a(new ReaderIOTask() {
                public void run() {
                    super.run();
                    ReaderPagerChapterFragment.this.pullBuyRecord(ReaderPagerChapterFragment.this.mOnlineTag.k(), ReaderPagerChapterFragment.this.mOnlineTag.F());
                }
            });
        }
    }

    private void createChapterListView() {
        this.mChapterListView = (ListView) this.root.findViewById(R.id.online_chapter_list);
        this.mChapterPbLiner = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.chapterlist_loading, this.mChapterListView, false);
        this.mChapterParserMessage = (TextView) this.mChapterPbLiner.findViewById(R.id.chapter_parser_message);
        this.mOnlineChapterLoading = this.root.findViewById(R.id.chapter_loading);
        if (this.mCurBook.getReadType() == 0) {
            this.mChapterAdapter = new com.qq.reader.module.bookchapter.a.b();
            this.mChapterListView.addHeaderView(this.mChapterPbLiner);
            this.mOnlineChapterLoading.setVisibility(8);
        } else {
            this.mChapterAdapter = new h();
            this.mOnlineChapterLoading.setVisibility(0);
            this.mChapterListView.addFooterView(this.mChapterPbLiner);
        }
        this.mChapterListView.setAdapter(this.mChapterAdapter);
        this.mChapterListView.removeFooterView(this.mChapterPbLiner);
        this.mChapterListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ ReaderPagerChapterFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (view != this.a.mChapterPbLiner) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    if (i >= this.a.mChapterAdapter.getCount()) {
                        i = this.a.mChapterAdapter.getCount() - 1;
                    }
                    if (this.a.mCurBook.getReadType() == 1) {
                        this.a.onlineChapterItemClick(i);
                        return;
                    }
                    bundle.putLong(ReaderPagerChapterFragment.RESULT_BOOKMARK_POINT, ((Mark) this.a.mChapterAdapter.getItem(i)).getStartPoint());
                    bundle.putBoolean(ReaderPagerChapterFragment.RESULT_BOOKMARK_FREE, ((Mark) this.a.mChapterAdapter.getItem(i)).isFree());
                    intent.putExtras(bundle);
                    this.a.getActivity().setResult(-1, intent);
                    this.a.getActivity().finish();
                }
            }
        });
        if (this.mCurBook.getReadType() == 1) {
            this.mChapterListView.setOnItemLongClickListener(new OnItemLongClickListener(this) {
                final /* synthetic */ ReaderPagerChapterFragment a;

                {
                    this.a = r1;
                }

                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                    this.a.mCurrentSelectPosition = i;
                    this.a.getContextMenu().f_();
                    return true;
                }
            });
        }
        this.chapterEmptyView = this.root.findViewById(R.id.online_chapter_empyt_layout);
        this.chapterEmptyView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReaderPagerChapterFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.chapterEmptyView.setVisibility(8);
                this.a.mOnlineChapterLoading.setVisibility(0);
                this.a.registerChapterHandler();
            }
        });
        this.mChapterListView.setVisibility(8);
        this.chapterEmptyView.setVisibility(8);
        if (this.mCurBook.getReadType() == 0) {
            getHandler().sendEmptyMessage(403);
            this.isInitedChapter = true;
            return;
        }
        this.chapterEmptyView.setVisibility(8);
        this.mOnlineChapterLoading.setVisibility(0);
        registerChapterHandler();
    }

    private void onlineChapterItemClick(int i) {
        OnlineChapter onlineChapter = (OnlineChapter) this.mChapterAdapter.getItem(i);
        this.mOnlineTag.b(onlineChapter.getChapterName()).a(0).g(onlineChapter.getChapterId());
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("resultOnlinetag", this.mOnlineTag);
        intent.putExtras(bundle);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }

    protected boolean handleMessageImp(Message message) {
        Collection d;
        switch (message.what) {
            case 300:
                if (this.tempMarkList.size() > 0) {
                    this.mChapterAdapter.a(this.tempMarkList);
                    this.mChapterAdapter.notifyDataSetChanged();
                    this.tempMarkList.clear();
                }
                this.mChapterPbLiner.setVisibility(8);
                this.mChapterListView.removeHeaderView(this.mChapterPbLiner);
                if (c.a().e() != null) {
                    this.mPositionChapter = (int) this.mChapterAdapter.a(this.mBookPoint);
                    this.mChapterListView.setSelection(this.mPositionChapter);
                    break;
                }
                af.a(getApplicationContext(), (CharSequence) "没有找到适合的章节目录。", 0).a();
                break;
            case 301:
                Mark mark = (Mark) message.obj;
                this.mChapterParserMessage.setText(" 读取目录中... " + ao.a(c.a().f()));
                this.tempMarkList.add(mark);
                if (this.tempMarkList.size() >= this.tempMarkSize) {
                    this.mChapterAdapter.a(this.tempMarkList);
                    this.tempMarkList.clear();
                    this.mChapterAdapter.notifyDataSetChanged();
                }
                if (this.mChapterListView.getVisibility() != 0) {
                    this.mChapterListView.setVisibility(0);
                    break;
                }
                break;
            case 302:
                this.mChapterPbLiner.setVisibility(8);
                this.mChapterListView.removeHeaderView(this.mChapterPbLiner);
                this.mChapterAdapter.a();
                this.mChapterAdapter.notifyDataSetChanged();
                if (this.mChapterListView.getVisibility() == 0) {
                    this.mChapterListView.setVisibility(8);
                    break;
                }
                break;
            case 303:
                this.mChapterParserMessage.setText(" 读取目录中... " + ao.a(c.a().f()));
                d = c.a().d();
                if (d != null && d.size() > 0) {
                    this.mChapterAdapter.a(d);
                    this.mChapterAdapter.notifyDataSetChanged();
                }
                this.tempMarkList.clear();
                if (this.mChapterListView.getVisibility() != 0) {
                    this.mChapterListView.setVisibility(0);
                    break;
                }
                break;
            case 400:
                this.mPositionChapter = (int) this.mChapterAdapter.a(this.mBookPoint);
                this.mChapterListView.setSelection(this.mPositionChapter);
                break;
            case 402:
                if (this.mChapterAdapter != null) {
                    this.mChapterAdapter.notifyDataSetChanged();
                    break;
                }
                break;
            case 403:
                Mark[] markArr;
                if (this.mCurBook != null) {
                    Mark[] a = i.c().a(this.mCurBook.getBookPath());
                    c.a().a(a);
                    markArr = a;
                } else {
                    markArr = null;
                }
                if (markArr == null || markArr.length <= 0) {
                    if (!chapterPaser()) {
                        getHandler().sendEmptyMessage(303);
                        break;
                    }
                    this.mChapterListView.setVisibility(8);
                    break;
                }
                this.mChapterPbLiner.setVisibility(8);
                this.mChapterListView.removeHeaderView(this.mChapterPbLiner);
                for (Object a2 : markArr) {
                    this.mChapterAdapter.a(a2);
                }
                this.mChapterListView.setVisibility(0);
                this.mPositionChapter = (int) this.mChapterAdapter.a(this.mBookPoint);
                this.mChapterListView.setSelection(this.mPositionChapter);
                break;
                break;
            case 21000:
                try {
                    this.mOnlineOperator = (f) message.obj;
                    d = this.mOnlineOperator.e();
                    if (message.arg1 == 1) {
                        this.mChapterParserMessage.setText("正在获取最新章节信息...");
                        this.mChapterListView.addFooterView(this.mChapterPbLiner);
                    } else {
                        this.mChapterListView.removeFooterView(this.mChapterPbLiner);
                    }
                    if (!this.isInitedChapter) {
                        this.isInitedChapter = true;
                        this.mOnlineChapterLoading.setVisibility(8);
                        if (d == null || d.size() == 0) {
                            this.mChapterListView.setVisibility(8);
                            this.chapterEmptyView.setVisibility(0);
                        } else {
                            this.mPositionChapter = this.mOnlineTag.g() - 1;
                            this.mChapterListView.setVisibility(0);
                            this.chapterEmptyView.setVisibility(8);
                            this.mChapterAdapter.a(this.mPositionChapter);
                            this.mChapterListView.setSelection(this.mPositionChapter);
                            this.mChapterAdapter.a(d);
                            this.mChapterAdapter.notifyDataSetChanged();
                        }
                        if (this.hasGotBuyRecord && this.mOnlineTag != null) {
                            applyBuyRecordCache(this.mOnlineTag.k(), this.mOnlineTag.F());
                        }
                    } else if (d != null && d.size() > 0 && message.arg2 == 2) {
                        this.mChapterAdapter.a(d);
                        this.mChapterAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case 21001:
                this.mChapterListView.removeFooterView(this.mChapterPbLiner);
                this.mOnlineChapterLoading.setVisibility(8);
                if (!this.isInitedChapter) {
                    this.mChapterListView.setVisibility(8);
                    this.chapterEmptyView.setVisibility(0);
                }
                unregisterChapterHandler();
                return true;
            case 21011:
                ArrayList arrayList = (ArrayList) message.obj;
                if (this.mChapterAdapter != null) {
                    this.mChapterAdapter.a(arrayList);
                    this.mChapterAdapter.notifyDataSetChanged();
                }
                return true;
            case 21101:
                if (this.mChapterAdapter != null) {
                    this.mChapterAdapter.a(true);
                    this.mChapterAdapter.notifyDataSetChanged();
                }
                return true;
        }
        return super.handleMessageImp(message);
    }

    private void applyBuyRecordCache(String str, int i) {
        int i2 = -1;
        if (this.mOnlineHandle != null) {
            i2 = this.mOnlineHandle.b();
        }
        Object obj = this.buyRecordCache;
        if ((i == 1 && r0 == 2) || (i == 2 && r0 == 3)) {
            if (!TextUtils.isEmpty(obj)) {
                solveBuyRecordOnAdapter(obj, str, true);
            }
        } else if (((i == 1 && r0 == 1) || (i == 2 && r0 == 2)) && !TextUtils.isEmpty(obj)) {
            solveBuyRecordOnAdapter(obj, str, false);
        }
    }

    private void solveBuyRecordOnAdapter(String str, String str2, boolean z) {
        Message obtain;
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt("retCode") == 0) {
                    List a = ao.a(jSONObject.optString("cids"));
                    if (a != null) {
                        k.a(getApplicationContext()).a(str2, a);
                        a = k.a(getApplicationContext()).a(str2);
                        obtain = Message.obtain();
                        obtain.what = 21011;
                        obtain.obj = a;
                        this.mHandler.sendMessage(obtain);
                    }
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("Err", e.getMessage());
            }
        } else if (new JSONObject(str).optInt("code") == 1) {
            ArrayList arrayList = new ArrayList();
            obtain = Message.obtain();
            obtain.what = 21101;
            obtain.obj = arrayList;
            this.mHandler.sendMessage(obtain);
        }
    }

    private void pullBuyRecord(final String str, int i) {
        int i2 = -1;
        if (this.mOnlineHandle != null) {
            i2 = this.mOnlineHandle.b();
        }
        ReaderTask queryChapterBuyInfoTask;
        if (i == 1 && r0 == 2) {
            queryChapterBuyInfoTask = new QueryChapterBuyInfoTask(str);
            queryChapterBuyInfoTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderPagerChapterFragment b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.solveBuyRecordOnAdapter(str, str, true);
                    this.b.buyRecordCache = str;
                    this.b.hasGotBuyRecord = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    com.qq.reader.common.monitor.debug.c.e("Err", exception.getMessage());
                }
            });
            com.qq.reader.common.readertask.g.a().a(queryChapterBuyInfoTask);
        } else if (i == 1 && r0 == 1) {
            com.qq.reader.common.readertask.g.a().a(new AuthCheckTask(Long.parseLong(this.mOnlineTag.k()), new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderPagerChapterFragment b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.solveBuyRecordOnAdapter(str, str, false);
                    this.b.buyRecordCache = str;
                    this.b.hasGotBuyRecord = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.b.mHandler.sendEmptyMessage(10000505);
                }
            }));
        } else if (i == 2 && r0 == 3) {
            queryChapterBuyInfoTask = new QueryAudioChapterBuyInfoTask(str);
            queryChapterBuyInfoTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderPagerChapterFragment b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.solveBuyRecordOnAdapter(str, str, true);
                    this.b.buyRecordCache = str;
                    this.b.hasGotBuyRecord = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                }
            });
            com.qq.reader.common.readertask.g.a().a(queryChapterBuyInfoTask);
        } else if (i == 2 && r0 == 2) {
            com.qq.reader.common.readertask.g.a().a(new AudioAuthCheckTask(Long.parseLong(this.mOnlineTag.k()), new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ ReaderPagerChapterFragment b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.solveBuyRecordOnAdapter(str, str, false);
                    this.b.buyRecordCache = str;
                    this.b.hasGotBuyRecord = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.b.mHandler.sendEmptyMessage(10000505);
                }
            }));
        }
    }

    private boolean chapterPaser() {
        boolean z = false;
        c.a().a(new c.a(this) {
            final /* synthetic */ ReaderPagerChapterFragment a;

            {
                this.a = r1;
            }

            public void a(int i, Mark mark) {
                this.a.getHandler().obtainMessage(i, mark).sendToTarget();
            }
        });
        if (c.a().c()) {
            return false;
        }
        String bookPath = this.mCurBook.getBookPath();
        if (!(bookPath == null || bookPath.indexOf("/Download/Books/") == -1)) {
            z = true;
        }
        c.a().a(this.mCurBook.getEncoding(), this.mCurBook.getBookPath(), this.mCurBook.getBookName(), z);
        this.mBookPoint = -1;
        return true;
    }

    private void registerChapterHandler() {
        this.mOnlineHandle = new g(getApplicationContext(), this.mOnlineTag);
        this.mOnlineHandle.a(getHandler());
        this.mOnlineHandle.a(true);
    }

    private void unregisterChapterHandler() {
        if (this.mOnlineHandle != null) {
            this.mOnlineHandle.d();
            this.mOnlineHandle = null;
        }
    }

    public a getContextMenu() {
        if (this.mMenu == null) {
            this.mMenu = new com.qq.reader.view.linearmenu.b(getActivity());
        }
        this.mMenu.i();
        if (this.mCurrentSelectPosition >= this.mChapterAdapter.getCount()) {
            this.mCurrentSelectPosition = this.mChapterAdapter.getCount() - 1;
        }
        OnlineChapter onlineChapter = (OnlineChapter) this.mChapterAdapter.getItem(this.mCurrentSelectPosition);
        String a = v.a("" + onlineChapter.getBookId(), onlineChapter.getChapterId());
        if (a != null) {
            if (new File(a).exists()) {
                Bundle bundle = new Bundle();
                bundle.putString("chapterpath", a);
                this.mMenu.a(2, getResources().getString(R.string.chapterlist_menu_redownload), bundle);
            } else {
                this.mMenu.a(3, getResources().getString(R.string.chapterlist_menu_download), null);
            }
        }
        this.mMenu.a(new a.b(this) {
            final /* synthetic */ ReaderPagerChapterFragment a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                Intent intent;
                Bundle bundle2;
                switch (i) {
                    case 2:
                        String string = bundle.getString("chapterpath");
                        if (string != null) {
                            ao.a(new File(string));
                            intent = new Intent();
                            bundle2 = new Bundle();
                            if (this.a.mCurrentSelectPosition >= this.a.mChapterAdapter.getCount()) {
                                this.a.mCurrentSelectPosition = this.a.mChapterAdapter.getCount() - 1;
                            }
                            if (this.a.mCurBook.getReadType() == 1) {
                                this.a.onlineChapterItemClick(this.a.mCurrentSelectPosition);
                            } else {
                                bundle2.putLong(ReaderPagerChapterFragment.RESULT_BOOKMARK_POINT, ((Mark) this.a.mChapterAdapter.getItem(this.a.mCurrentSelectPosition)).getStartPoint());
                                bundle2.putBoolean(ReaderPagerChapterFragment.RESULT_BOOKMARK_FREE, ((Mark) this.a.mChapterAdapter.getItem(this.a.mCurrentSelectPosition)).isFree());
                            }
                            intent.putExtras(bundle2);
                            this.a.getActivity().setResult(-1, intent);
                            this.a.getActivity().finish();
                        }
                        return true;
                    case 3:
                        intent = new Intent();
                        bundle2 = new Bundle();
                        if (this.a.mCurrentSelectPosition >= this.a.mChapterAdapter.getCount()) {
                            this.a.mCurrentSelectPosition = this.a.mChapterAdapter.getCount() - 1;
                        }
                        if (this.a.mCurBook.getReadType() == 1) {
                            this.a.onlineChapterItemClick(this.a.mCurrentSelectPosition);
                        } else {
                            bundle2.putLong(ReaderPagerChapterFragment.RESULT_BOOKMARK_POINT, ((Mark) this.a.mChapterAdapter.getItem(this.a.mCurrentSelectPosition)).getStartPoint());
                            bundle2.putBoolean(ReaderPagerChapterFragment.RESULT_BOOKMARK_FREE, ((Mark) this.a.mChapterAdapter.getItem(this.a.mCurrentSelectPosition)).isFree());
                        }
                        intent.putExtras(bundle2);
                        this.a.getActivity().setResult(-1, intent);
                        this.a.getActivity().finish();
                        return true;
                    default:
                        return false;
                }
            }
        });
        return this.mMenu;
    }

    public void onPreLoad() {
    }

    public void onLoading() {
    }

    public void onLoadFinished() {
    }
}
