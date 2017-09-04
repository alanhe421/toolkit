package com.qq.reader.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager.e;
import android.widget.ImageView;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.widget.a;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.fragment.ReaderPagerBookmarkFragment;
import com.qq.reader.module.bookstore.qweb.fragment.ReaderPagerChapterFragment;
import com.qq.reader.module.bookstore.qweb.fragment.ReaderPagerNoteFragment;
import com.tencent.feedback.proguard.R;
import java.io.Serializable;

public class NewChapterViewActivity extends BaseWebTabActivity implements e {
    Bundle k;
    private TabViewBookInfo l;
    private long m = -1;
    private long n = 0;
    private OnlineTag o = null;

    public static class TabViewBookInfo implements Serializable {
        private static final long serialVersionUID = -5621171624812054611L;
        private String mBookName;
        private long mBookNetId;
        private String mBookPath;
        private int mEncoding;
        private int mFileCount;
        private boolean mIsPDF;
        private int mReadType;

        public TabViewBookInfo(int i, long j, String str, String str2, int i2, int i3, boolean z) {
            this.mReadType = i;
            this.mBookNetId = j;
            this.mBookPath = str;
            this.mBookName = str2;
            this.mEncoding = i2;
            this.mFileCount = i3;
            this.mIsPDF = z;
        }

        public int getReadType() {
            return this.mReadType;
        }

        public long getBookNetId() {
            return this.mBookNetId;
        }

        public String getBookPath() {
            return this.mBookPath;
        }

        public String getBookName() {
            return this.mBookName;
        }

        public int getFileCount() {
            return this.mFileCount;
        }

        public int getEncoding() {
            return this.mEncoding;
        }

        public boolean isPDF() {
            return this.mIsPDF;
        }
    }

    public void onCreate(Bundle bundle) {
        getIntent().putExtra("CREATE_BUNDLE", "CREATE_BUNDLE");
        this.k = getIntent().getExtras();
        if (this.k != null) {
            this.l = (TabViewBookInfo) this.k.getSerializable("resultBook");
            this.m = this.k.getLong("resultMarkP");
            this.o = (OnlineTag) this.k.getParcelable("resultOnlinetag");
            if (this.l == null && bundle != null) {
                this.l = (TabViewBookInfo) bundle.getSerializable("resultBook");
                this.m = bundle.getLong("resultMarkP");
                this.o = (OnlineTag) bundle.getParcelable("resultOnlinetag");
                this.k.putSerializable("resultBook", this.l);
                this.k.putLong("resultMarkP", this.m);
                this.k.putParcelable("resultOnlinetag", this.o);
            }
        }
        if (this.l == null) {
            finish();
        } else if (this.l.getReadType() == 1) {
            this.n = this.k.getLong("bookFileLength");
        }
        super.onCreate(bundle);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putSerializable("resultBook", this.l);
            bundle.putLong("resultMarkP", this.m);
            bundle.putParcelable("resultOnlinetag", this.o);
        }
    }

    protected void a(Bundle bundle) {
        this.g.add(0, new TabInfo(ReaderPagerChapterFragment.newInstance(this.k), null, "目录", null));
        this.g.add(1, new TabInfo(ReaderPagerBookmarkFragment.newInstance(this.k), null, "书签", null));
        this.g.add(2, new TabInfo(ReaderPagerNoteFragment.newInstance(this.k), null, "笔记", null));
        this.a.a(2, this.g);
        this.a.setTextColorResource(R.color.skin_set_common_textcolor);
        this.a.setIndicatorColorResource(R.color.skin_set_common_textcolor);
        this.a.setOnPageChangeListener(this);
        a.a((ImageView) this.d.findViewById(R.id.title_left), this);
    }

    protected String e() {
        return ao.k(this.l.getBookName());
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (i == 0) {
            i.a("event_B38", null, this);
        } else if (i == 1) {
            i.a("event_B7", null, this);
        } else if (i == 2) {
            i.a("event_B30", null, this);
        }
    }

    public void onPageScrollStateChanged(int i) {
    }
}
