package com.qq.reader.module.bookshelf;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.plugin.audiobook.MusicBookGroup;
import com.qq.reader.qplugin.local.TingBookMark;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: BookStandListAdapter */
public class h extends BaseAdapter {
    a a;
    b b;
    private Context c;
    private volatile ArrayList<Mark> d;
    private int e = 3;
    private final int f = Opcodes.INT_TO_CHAR;
    private int g;
    private int h;
    private final int i = 2;
    private final int j = 4;
    private final int k = 3;
    private final int l = 6;
    private HashMap<String, Drawable> m = new HashMap();
    private HashMap<String, Drawable> n = new HashMap();
    private WeakReference<Drawable> o;
    private WeakReference<Drawable> p;
    private boolean q = false;
    private boolean r = false;

    /* compiled from: BookStandListAdapter */
    public interface a {
        void onClickBook(int i);
    }

    /* compiled from: BookStandListAdapter */
    public interface b {
        boolean a(int i);
    }

    /* compiled from: BookStandListAdapter */
    private class c {
        ArrayList<Mark> a = new ArrayList();
        int b;
        boolean c;
        final /* synthetic */ h d;

        public c(h hVar, int i) {
            this.d = hVar;
            this.b = i;
        }

        c a(Mark mark) {
            this.a.add(mark);
            return this;
        }
    }

    public h(Context context, boolean z) {
        this.c = context;
        this.d = new ArrayList();
        this.q = z;
    }

    public void a(int i) {
        int minimumWidth = d().getMinimumWidth();
        NinePatchDrawable ninePatchDrawable = (NinePatchDrawable) this.c.getResources().getDrawable(R.drawable.bookcase_bg);
        Rect rect = new Rect();
        ninePatchDrawable.getPadding(rect);
        int i2 = (i - rect.right) - rect.left;
        this.g = (i2 - (minimumWidth / 3)) / minimumWidth;
        if (this.g < 3) {
            this.g = 3;
        } else if (this.g > 6) {
            this.g = 6;
        }
        minimumWidth = e().getMinimumWidth();
        this.h = (i2 - (minimumWidth / 3)) / minimumWidth;
        if (this.h < 2) {
            this.h = 2;
        } else if (this.h > 4) {
            this.h = 4;
        }
    }

    private Drawable d() {
        if (this.o != null && this.o.get() != null) {
            return (Drawable) this.o.get();
        }
        Drawable drawable = this.c.getResources().getDrawable(R.drawable.bookcase_book_nor_cover);
        if (drawable == null) {
            return null;
        }
        this.o = new WeakReference(drawable);
        return (Drawable) this.o.get();
    }

    private Drawable e() {
        if (this.p != null && this.p.get() != null) {
            return (Drawable) this.p.get();
        }
        Drawable drawable = this.c.getResources().getDrawable(R.drawable.bookcase_book_nor_cover_big);
        if (drawable == null) {
            return null;
        }
        this.p = new WeakReference(drawable);
        return (Drawable) this.p.get();
    }

    public void b(int i) {
        this.e = ((i - 30) - 25) / this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_142);
    }

    public void a(Mark[] markArr) {
        synchronized (this.d) {
            if (markArr != null) {
                for (int length = markArr.length - 1; length >= 0; length--) {
                    if (markArr[length] != null) {
                        this.d.add(markArr[length]);
                    }
                }
                Collections.sort(this.d, new Comparator<Mark>(this) {
                    final /* synthetic */ h a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ int compare(Object obj, Object obj2) {
                        return a((Mark) obj, (Mark) obj2);
                    }

                    public int a(Mark mark, Mark mark2) {
                        return (int) (mark2.getOperateTime() - mark.getOperateTime());
                    }
                });
            }
        }
    }

    public void a() {
        synchronized (this.d) {
            if (this.d != null && this.d.size() > 0) {
                this.d.clear();
            }
        }
    }

    public int b() {
        int size;
        synchronized (this.d) {
            size = ((this.d.size() - this.h) / this.g) + 1;
            if (((this.d.size() - this.h) % this.g) + 1 == 1) {
            } else {
                size++;
            }
        }
        return size;
    }

    public int c() {
        int size;
        synchronized (this.d) {
            size = this.d.size();
        }
        return size;
    }

    public int getCount() {
        int b = b();
        if (b < this.e) {
            return this.e;
        }
        return b;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        return 1;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public Object getItem(int i) {
        Object obj;
        synchronized (this.d) {
            if (this.d == null || i >= this.d.size() || i < 0) {
                obj = null;
            } else {
                obj = this.d.get(i);
            }
        }
        return obj;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View a(LinearLayout linearLayout, c cVar) {
        int i;
        if (cVar.c) {
            i = this.h;
        } else {
            i = this.g;
        }
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
        Iterator it = cVar.a.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            BookStandAdapterItem bookStandAdapterItem;
            Mark mark = (Mark) it.next();
            View childAt = linearLayout.getChildAt(i2);
            if (childAt == null) {
                if (cVar.c) {
                    childAt = (BookStandAdapterItem) LayoutInflater.from(this.c).inflate(R.layout.bookmarkstanditem_big, linearLayout, false);
                } else {
                    BookStandAdapterItem bookStandAdapterItem2 = (BookStandAdapterItem) LayoutInflater.from(this.c).inflate(R.layout.bookmarkstanditem, linearLayout, false);
                }
                childAt.setLayoutParams(layoutParams);
                childAt.a();
                linearLayout.addView(childAt);
                bookStandAdapterItem = childAt;
            } else {
                bookStandAdapterItem = (BookStandAdapterItem) childAt;
            }
            bookStandAdapterItem.setPosition(d(cVar.b) + i2);
            childAt = bookStandAdapterItem.getContentView();
            childAt.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    int intValue = ((Integer) view.getTag()).intValue();
                    if (this.a.a != null) {
                        this.a.a.onClickBook(intValue);
                    }
                }
            });
            if (!this.q) {
                childAt.setOnLongClickListener(new OnLongClickListener(this) {
                    final /* synthetic */ h a;

                    {
                        this.a = r1;
                    }

                    public boolean onLongClick(View view) {
                        int intValue = ((Integer) view.getTag()).intValue();
                        if (this.a.b != null) {
                            return this.a.b.a(intValue);
                        }
                        return false;
                    }
                });
            }
            if (mark instanceof MusicBookGroup) {
                bookStandAdapterItem.setDefaultCover(R.drawable.musicbook_cover);
                bookStandAdapterItem.setCoverName("");
                bookStandAdapterItem.setTagFlag(cVar.c, false, 0);
                if (!this.q) {
                    bookStandAdapterItem.setReaded(mark.isLastRead());
                }
                bookStandAdapterItem.setCoverDownload(false, 0, false);
                bookStandAdapterItem.setBookCoverCheckBoxStatus(2, null);
            } else if (mark instanceof TingBookMark) {
                bookStandAdapterItem.setDefaultCover(R.drawable.musicbook_cover);
                bookStandAdapterItem.setCoverName("");
                bookStandAdapterItem.setBookHasNewContent(mark.hasNewContent());
                bookStandAdapterItem.setTagFlag(cVar.c, false, 0);
                if (!this.q) {
                    bookStandAdapterItem.setReaded(mark.isLastRead());
                }
                bookStandAdapterItem.setCoverDownload(false, 0, false);
                bookStandAdapterItem.setBookCoverCheckBoxStatus(2, null);
            } else if (mark instanceof LocalMark) {
                if (!this.q) {
                    String percentStr = mark.getPercentStr();
                    if (percentStr.length() > 0) {
                        try {
                            Double.parseDouble(percentStr.substring(0, percentStr.length() - 1));
                        } catch (Exception e) {
                        }
                    } else if (mark.getFileLength() > mark.getStartPoint()) {
                        Math.min((((double) (mark.getStartPoint() + 1)) / ((double) mark.getFileLength())) * 100.0d, 100.0d);
                    }
                    bookStandAdapterItem.setReaded(mark.isLastRead());
                }
                bookStandAdapterItem.setCoverName(mark.getBookShortName());
                com.qq.reader.common.imageloader.c.a(this.c).a(mark.getImageURI(), bookStandAdapterItem.a, com.qq.reader.common.imageloader.a.a().j(), new e<String, com.bumptech.glide.load.resource.a.b>(this) {
                    final /* synthetic */ h b;

                    public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                        bookStandAdapterItem.setCoverName("");
                        return true;
                    }

                    public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                        return false;
                    }
                });
                bookStandAdapterItem.setCoverVisibility(true);
                bookStandAdapterItem.setStatusChange(null, false, cVar.c);
                bookStandAdapterItem.setBookCoverCheckBoxStatus(2, null);
                bookStandAdapterItem.setBookHasNewContent(mark.hasNewContent());
                bookStandAdapterItem.setTagFlag(cVar.c, false, 0);
                bookStandAdapterItem.setCoverDownload(false, 0, false);
            } else if (mark instanceof DownloadMark) {
                DownloadBookTask downloadTask = ((DownloadMark) mark).getDownloadTask();
                if (downloadTask != null) {
                    bookStandAdapterItem.setReaded(false);
                    bookStandAdapterItem.setCoverName(downloadTask.getName());
                    com.qq.reader.common.imageloader.c.a(this.c).a(mark.getImageURI(), bookStandAdapterItem.a, com.qq.reader.common.imageloader.a.a().j(), new e<String, com.bumptech.glide.load.resource.a.b>(this) {
                        final /* synthetic */ h b;

                        public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                            bookStandAdapterItem.setCoverName("");
                            return true;
                        }

                        public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                            return false;
                        }
                    });
                    bookStandAdapterItem.setCoverVisibility(true);
                    bookStandAdapterItem.setCoverDownload(true, downloadTask.getProgress(), downloadTask.getIsOnlyDownLoadIcon());
                    bookStandAdapterItem.setStatusChange(downloadTask.getState(), downloadTask.getIsOnlyDownLoadIcon(), cVar.c);
                    bookStandAdapterItem.setBookCoverCheckBoxStatus(2, null);
                    bookStandAdapterItem.setBookHasNewContent(false);
                    if (downloadTask.getIsOnlyDownLoadIcon()) {
                        bookStandAdapterItem.setTagFlag(cVar.c, true, 1);
                    } else {
                        bookStandAdapterItem.setTagFlag(cVar.c, false, 0);
                    }
                }
            }
            i2++;
        }
        while (i2 < i) {
            BookStandAdapterItem bookStandAdapterItem3;
            View childAt2 = linearLayout.getChildAt(i2);
            if (childAt2 == null) {
                if (cVar.c) {
                    bookStandAdapterItem3 = (BookStandAdapterItem) LayoutInflater.from(this.c).inflate(R.layout.bookmarkstanditem_big, linearLayout, false);
                } else {
                    bookStandAdapterItem3 = (BookStandAdapterItem) LayoutInflater.from(this.c).inflate(R.layout.bookmarkstanditem, linearLayout, false);
                }
                bookStandAdapterItem3.setLayoutParams(layoutParams);
                bookStandAdapterItem3.a();
                linearLayout.addView(bookStandAdapterItem3);
            } else {
                bookStandAdapterItem3 = (BookStandAdapterItem) childAt2;
            }
            bookStandAdapterItem3.setBookCoverCheckBoxStatus(2, null);
            bookStandAdapterItem3.setCoverDownload(false, 0, false);
            bookStandAdapterItem3.setPosition(-1);
            bookStandAdapterItem3.setStatusChange(TaskStateEnum.InstallCompleted, false, cVar.c);
            bookStandAdapterItem3.setReaded(false);
            bookStandAdapterItem3.setCoverName("");
            if (cVar.c) {
                bookStandAdapterItem3.setNorCove(e());
            } else {
                bookStandAdapterItem3.setNorCove(d());
            }
            bookStandAdapterItem3.setCoverVisibility(false);
            bookStandAdapterItem3.setBackgroundDrawable(null);
            bookStandAdapterItem3.setBookHasNewContent(false);
            bookStandAdapterItem3.setTagFlag(cVar.c, false, 0);
            i2++;
        }
        return linearLayout;
    }

    private c c(int i) {
        c cVar;
        synchronized (this.d) {
            cVar = new c(this, i);
            int i2;
            int i3;
            if (i == 0) {
                cVar.c = true;
                if (this.d != null && i < b()) {
                    i2 = 0;
                    i3 = 0;
                    while (i3 < this.d.size() && i2 < this.h) {
                        cVar.a((Mark) this.d.get(i3));
                        i3++;
                        i2++;
                    }
                }
            } else {
                cVar.c = false;
                i2 = d(i);
                if (this.d != null && i < b()) {
                    i3 = i2;
                    i2 = 0;
                    while (i3 < this.d.size() && i2 < this.g) {
                        cVar.a((Mark) this.d.get(i3));
                        i3++;
                        i2++;
                    }
                }
            }
        }
        return cVar;
    }

    private int d(int i) {
        if (i == 0) {
            return 0;
        }
        return (this.g * i) - (this.g - this.h);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout;
        int itemViewType = getItemViewType(i);
        if (view == null) {
            if (itemViewType == 0) {
                linearLayout = (LinearLayout) LayoutInflater.from(this.c).inflate(R.layout.new_liner_item, viewGroup, false);
                linearLayout.setBackgroundResource(R.drawable.bookcase_bg);
            } else {
                linearLayout = (LinearLayout) LayoutInflater.from(this.c).inflate(R.layout.new_liner_item, viewGroup, false);
                linearLayout.setBackgroundResource(R.drawable.bookcase_bg);
            }
        } else if (itemViewType == 0) {
            linearLayout = (LinearLayout) view;
        } else {
            linearLayout = (LinearLayout) view;
        }
        return a(linearLayout, c(i));
    }

    public void a(a aVar) {
        this.a = aVar;
    }
}
