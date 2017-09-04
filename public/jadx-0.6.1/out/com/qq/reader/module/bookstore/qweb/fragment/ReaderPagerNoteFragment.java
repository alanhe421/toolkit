package com.qq.reader.module.bookstore.qweb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.NewChapterViewActivity.TabViewBookInfo;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.s;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookchapter.online.f;
import com.qq.reader.module.bookchapter.online.g;
import com.qq.reader.module.readpage.aa;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.readengine.model.b;
import com.qq.reader.view.af;
import com.qq.reader.view.ak;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

public class ReaderPagerNoteFragment extends BaseFragment implements a {
    private final int MENU_BOOKMARK_DEL = 1;
    private final int MENU_BOOKMARK_JUMP = 0;
    private final int MENU_BOOKMARK_share = 2;
    private boolean isInitedChapter = false;
    private long mBookPoint;
    private TextView mChapterParserMessage;
    private LinearLayout mChapterPbLiner;
    private TabViewBookInfo mCurBook;
    private b mCurrentSelectNote;
    private com.qq.reader.view.linearmenu.a mMenu;
    private View mOnlineChapterLoading;
    private g mOnlineHandle;
    private f mOnlineOperator;
    private OnlineTag mOnlineTag = null;
    private int mPositionChapter = 0;
    private a mRemarkListAdapter;
    protected ListView mRemarkListView;
    private View remarkEmptyView;
    protected View root;

    private class a extends BaseAdapter {
        final /* synthetic */ ReaderPagerNoteFragment a;
        private SparseArray<List<b>> b;
        private a c;

        public a(ReaderPagerNoteFragment readerPagerNoteFragment) {
            this.a = readerPagerNoteFragment;
            a();
        }

        public void a(a aVar) {
            this.c = aVar;
        }

        private void a() {
            if (this.b == null) {
                this.b = new SparseArray();
            }
            for (b bVar : IBook.mRemarksList) {
                int m = bVar.m();
                if (aa.m() != null && m > 0) {
                    bVar.e(aa.m().getChapterName(m));
                }
                List arrayList;
                if (this.b.indexOfKey(m) < 0) {
                    arrayList = new ArrayList();
                    arrayList.add(bVar);
                    this.b.put(m, arrayList);
                } else {
                    arrayList = (List) this.b.get(m);
                    arrayList.add(bVar);
                    this.b.put(m, arrayList);
                }
            }
        }

        public int getCount() {
            if (this.b == null) {
                return 0;
            }
            int size;
            synchronized (this.b) {
                size = this.b.size();
            }
            return size;
        }

        public Object getItem(int i) {
            if (i > getCount() || i < 0) {
                return null;
            }
            Object obj;
            synchronized (this.b) {
                obj = this.b.get(this.b.keyAt(i));
            }
            return obj;
        }

        public void a(b bVar) {
            synchronized (IBook.mRemarksList) {
                if (IBook.mRemarksList != null) {
                    for (Object obj : IBook.mRemarksList) {
                        if (obj.f() == bVar.f()) {
                            break;
                        }
                    }
                    Object obj2 = null;
                    if (obj2 != null) {
                        IBook.mRemarksList.remove(obj2);
                    }
                }
            }
            int m = bVar.m();
            List list = (List) this.b.get(m);
            list.remove(bVar);
            if (list.size() > 0) {
                this.b.put(m, list);
            } else {
                this.b.remove(m);
            }
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater from = LayoutInflater.from(this.a.getActivity());
            View inflate = from.inflate(R.layout.remarklistgroup, null);
            LinearLayout linearLayout = (LinearLayout) inflate;
            List<b> list = (List) getItem(i);
            TextView textView = (TextView) inflate.findViewById(R.id.title);
            if (list == null || list.size() <= 0) {
                textView.setText("第" + this.b.keyAt(i) + "章");
            } else {
                textView.setText(((b) list.get(0)).u());
            }
            for (final b bVar : list) {
                View inflate2 = from.inflate(R.layout.remarklistitem, null);
                TextView textView2 = (TextView) inflate2.findViewById(R.id.content);
                TextView textView3 = (TextView) inflate2.findViewById(R.id.remark);
                ((TextView) inflate2.findViewById(R.id.percentTime)).setText(ao.j(bVar.i() / 1000));
                CharSequence d = bVar.d();
                String e = bVar.e();
                if (!TextUtils.isEmpty(d)) {
                    d = d.replace("\n", "");
                }
                if (!TextUtils.isEmpty(e)) {
                    e = e.replace("\n", "");
                }
                textView2.setText(d);
                if (bVar.e().length() > 0) {
                    textView3.setVisibility(0);
                    textView3.setText("笔记：" + e);
                } else {
                    textView3.setText("");
                    textView3.setVisibility(8);
                }
                inflate2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        this.b.c.onClick(bVar);
                    }
                });
                inflate2.setOnLongClickListener(new OnLongClickListener(this) {
                    final /* synthetic */ a b;

                    public boolean onLongClick(View view) {
                        this.b.c.onLongClick(bVar);
                        return false;
                    }
                });
                linearLayout.addView(inflate2);
            }
            return linearLayout;
        }
    }

    public static ReaderPagerNoteFragment newInstance(TabViewBookInfo tabViewBookInfo, OnlineTag onlineTag, long j) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", tabViewBookInfo);
        bundle.putParcelable("onlinetag", onlineTag);
        bundle.putLong("bookpoint", j);
        ReaderPagerNoteFragment readerPagerNoteFragment = new ReaderPagerNoteFragment();
        readerPagerNoteFragment.setArguments(bundle);
        return readerPagerNoteFragment;
    }

    public static ReaderPagerNoteFragment newInstance(Bundle bundle) {
        ReaderPagerNoteFragment readerPagerNoteFragment = new ReaderPagerNoteFragment();
        if (bundle != null) {
            readerPagerNoteFragment.setArguments(bundle);
        }
        return readerPagerNoteFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActivity().setTheme(R.style.BookCoinChargeTheme);
        this.root = layoutInflater.inflate(R.layout.remarklist, null);
        this.mCurBook = (TabViewBookInfo) getArguments().getSerializable("resultBook");
        this.mOnlineTag = (OnlineTag) getArguments().getParcelable("resultOnlinetag");
        this.mBookPoint = getArguments().getLong("resultMarkP", -1);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return this.root;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        createRemarkListView();
    }

    private void createRemarkListView() {
        this.mRemarkListView = (ListView) this.root.findViewById(R.id.notelist);
        this.mRemarkListAdapter = new a(this);
        this.mRemarkListAdapter.a((a) this);
        this.mRemarkListView.setAdapter(this.mRemarkListAdapter);
        this.mRemarkListView.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ ReaderPagerNoteFragment a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                return false;
            }
        });
        this.remarkEmptyView = this.root.findViewById(R.id.empty_notelist);
        if (IBook.mRemarksList.size() == 0) {
            this.mRemarkListView.setVisibility(8);
            this.remarkEmptyView.setVisibility(0);
            return;
        }
        this.mRemarkListView.setVisibility(0);
        this.remarkEmptyView.setVisibility(8);
    }

    public void onClick(Object obj) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        try {
            b bVar = (b) obj;
            bundle.putLong(ReaderPagerChapterFragment.RESULT_BOOKMARK_POINT, Long.parseLong(bVar.g()));
            if (this.mOnlineTag != null) {
                this.mOnlineTag.g(bVar.m());
                this.mOnlineTag.a(bVar.n());
                bundle.putParcelable("resultOnlinetag", this.mOnlineTag);
            }
            intent.putExtras(bundle);
            getActivity().setResult(-1, intent);
            getActivity().finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLongClick(Object obj) {
        this.mCurrentSelectNote = (b) obj;
        getContextMenu().f_();
    }

    public com.qq.reader.view.linearmenu.a getContextMenu() {
        if (this.mMenu == null) {
            this.mMenu = new com.qq.reader.view.linearmenu.b(getActivity());
        }
        this.mMenu.i();
        this.mMenu.a(0, getResources().getString(R.string.bookmarklist_menu_jump), null);
        if (this.mCurBook.getReadType() == 1 || (this.mCurrentSelectNote != null && this.mCurrentSelectNote.q() > 0)) {
            this.mMenu.a(2, getResources().getString(R.string.bookmarklist_menu_share), null);
        }
        this.mMenu.a(1, getResources().getString(R.string.bookmarklist_menu_del), null);
        this.mMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ ReaderPagerNoteFragment a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                String str = null;
                switch (i) {
                    case 0:
                        this.a.onClick(this.a.mCurrentSelectNote);
                        return true;
                    case 1:
                        if (c.b()) {
                            str = d.R(ReaderApplication.getApplicationImp());
                        }
                        if (s.a().a(str, this.a.mCurrentSelectNote.f(), this.a.mCurrentSelectNote.q())) {
                            s.a().a(str, this.a.mCurrentSelectNote.q(), 0, System.currentTimeMillis(), false);
                            this.a.mRemarkListAdapter.a(this.a.mCurrentSelectNote);
                            this.a.mRemarkListAdapter.notifyDataSetChanged();
                        } else {
                            af.a(this.a.getActivity().getApplicationContext(), (CharSequence) "删除失败，请重试。", 1).a();
                        }
                        return true;
                    case 2:
                        int i2;
                        if (this.a.mCurrentSelectNote.e().length() > 0) {
                            i2 = 15;
                        } else {
                            i2 = 12;
                        }
                        new ak(this.a.getActivity(), this.a.mCurrentSelectNote, i2).f_();
                        i.a("event_B195", null, this.a.getActivity());
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
