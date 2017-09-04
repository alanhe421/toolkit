package com.qq.reader.module.bookstore.qweb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.activity.NewChapterViewActivity.TabViewBookInfo;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.framework.mark.UserMark;
import com.qq.reader.module.bookchapter.a;
import com.qq.reader.module.bookchapter.online.f;
import com.qq.reader.module.bookchapter.online.g;
import com.qq.reader.readengine.fileparse.d;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.b;
import com.tencent.feedback.proguard.R;
import java.util.List;

public class ReaderPagerBookmarkFragment extends BaseFragment implements a {
    private final int MENU_BOOKMARK_DEL = 1;
    private final int MENU_BOOKMARK_JUMP = 0;
    private View bookmarkEmptyView;
    private boolean isInitedChapter = false;
    private a mBookMarkListAdapter;
    protected ListView mBookMarkListView;
    private long mBookPoint;
    private TextView mChapterParserMessage;
    private LinearLayout mChapterPbLiner;
    private TabViewBookInfo mCurBook;
    private Mark mCurrentSelectMark;
    private long mFileLength = 0;
    private com.qq.reader.view.linearmenu.a mMenu;
    private View mOnlineChapterLoading;
    private g mOnlineHandle;
    private f mOnlineOperator;
    private OnlineTag mOnlineTag = null;
    private int mPositionChapter = 0;
    private int mPositionMark = 0;
    protected View root;

    public static ReaderPagerBookmarkFragment newInstance(TabViewBookInfo tabViewBookInfo, OnlineTag onlineTag, long j) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", tabViewBookInfo);
        bundle.putParcelable("onlinetag", onlineTag);
        bundle.putLong("bookpoint", j);
        ReaderPagerBookmarkFragment readerPagerBookmarkFragment = new ReaderPagerBookmarkFragment();
        readerPagerBookmarkFragment.setArguments(bundle);
        return readerPagerBookmarkFragment;
    }

    public static ReaderPagerBookmarkFragment newInstance(Bundle bundle) {
        ReaderPagerBookmarkFragment readerPagerBookmarkFragment = new ReaderPagerBookmarkFragment();
        if (bundle != null) {
            readerPagerBookmarkFragment.setArguments(bundle);
        }
        return readerPagerBookmarkFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActivity().setTheme(R.style.BookCoinChargeTheme);
        this.root = layoutInflater.inflate(R.layout.bookmarklist, null);
        this.mCurBook = (TabViewBookInfo) getArguments().getSerializable("resultBook");
        this.mOnlineTag = (OnlineTag) getArguments().getParcelable("resultOnlinetag");
        this.mBookPoint = getArguments().getLong("resultMarkP", -1);
        if (this.mCurBook.getReadType() == 1) {
            this.mFileLength = getArguments().getLong("bookFileLength");
        }
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return this.root;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        createBookmarkListView();
    }

    private void createBookmarkListView() {
        this.mBookMarkListView = (ListView) this.root.findViewById(R.id.bookmarklist);
        this.mBookMarkListView.setFastScrollEnabled(true);
        this.mBookMarkListAdapter = new a(getContext());
        this.mBookMarkListAdapter.a((a) this);
        this.mBookMarkListView.setAdapter(this.mBookMarkListAdapter);
        this.mBookMarkListView.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ ReaderPagerBookmarkFragment a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                return false;
            }
        });
        this.mBookMarkListView.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ ReaderPagerBookmarkFragment a;

            {
                this.a = r1;
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i3 != 0) {
                    this.a.mPositionMark = i;
                }
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }
        });
        this.bookmarkEmptyView = this.root.findViewById(R.id.empty_bookmarklist);
        if (this.mCurBook != null) {
            List<Mark> a = i.c().a(this.mCurBook.getBookNetId(), this.mCurBook.getBookPath());
            if (a == null || a.size() <= 0) {
                this.mBookMarkListView.setVisibility(8);
                this.bookmarkEmptyView.setVisibility(0);
                return;
            }
            if (this.mCurBook.getReadType() == 1) {
                for (Mark mark : a) {
                    int chapterId = ((UserMark) mark).getChapterId();
                    long chapterOffset = ((UserMark) mark).getChapterOffset();
                    com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                    gVar.a(chapterId, chapterOffset);
                    double a2 = d.a(gVar, this.mCurBook.getFileCount(), this.mFileLength);
                    mark.setPercentStr(String.format("%.2f%%", new Object[]{Double.valueOf(a2 * 100.0d)}));
                }
            }
            this.mBookMarkListAdapter.a((List) a);
            this.mBookMarkListView.setVisibility(0);
            this.bookmarkEmptyView.setVisibility(8);
        }
    }

    public void onPreLoad() {
    }

    public void onLoading() {
    }

    public void onLoadFinished() {
    }

    public void onLongClick(Object obj) {
        this.mCurrentSelectMark = (Mark) obj;
        getContextMenu().f_();
    }

    public void onClick(Object obj) {
        Mark mark = (Mark) obj;
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putLong(ReaderPagerChapterFragment.RESULT_BOOKMARK_POINT, mark.getStartPoint());
        if (this.mOnlineTag != null) {
            this.mOnlineTag.g(((UserMark) mark).getChapterId());
            this.mOnlineTag.a(((UserMark) mark).getChapterOffset());
            bundle.putParcelable("resultOnlinetag", this.mOnlineTag);
        }
        intent.putExtras(bundle);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }

    public com.qq.reader.view.linearmenu.a getContextMenu() {
        if (this.mMenu == null) {
            this.mMenu = new b(getActivity());
        }
        this.mMenu.i();
        this.mMenu.a(0, getResources().getString(R.string.bookmarklist_menu_jump), null);
        this.mMenu.a(1, getResources().getString(R.string.bookmarklist_menu_del), null);
        this.mMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ ReaderPagerBookmarkFragment a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                switch (i) {
                    case 0:
                        this.a.onClick(this.a.mCurrentSelectMark);
                        com.qq.reader.common.monitor.i.a("event_B8", null, this.a.getActivity());
                        return true;
                    case 1:
                        Mark mark = (UserMark) this.a.mCurrentSelectMark;
                        if (i.c().c((UserMark) mark)) {
                            this.a.mBookMarkListAdapter.a(mark);
                            this.a.mBookMarkListAdapter.notifyDataSetChanged();
                        } else {
                            af.a(this.a.getActivity().getApplicationContext(), (CharSequence) "删除失败，请重试。", 1).a();
                        }
                        com.qq.reader.common.monitor.i.a("event_B8", null, this.a.getActivity());
                        return true;
                    default:
                        return false;
                }
            }
        });
        return this.mMenu;
    }
}
