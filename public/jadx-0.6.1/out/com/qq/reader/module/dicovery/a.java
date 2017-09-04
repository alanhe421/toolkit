package com.qq.reader.module.dicovery;

import android.content.Context;
import android.os.Handler;
import android.support.v4.util.e;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ap;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: DiscoveryCommentBookListAdapter */
public class a extends BaseAdapter {
    List<a> a = null;
    e<a> b = null;
    HashMap<Long, com.qq.reader.common.db.handle.e.a> c = null;
    Context d = null;
    Handler e = null;

    /* compiled from: DiscoveryCommentBookListAdapter */
    public class a {
        public Mark a;
        public int b;
        public int c;
        final /* synthetic */ a d;

        public a(a aVar) {
            this.d = aVar;
        }
    }

    public a(Context context, Handler handler) {
        this.d = context;
        this.a = new ArrayList();
        this.b = new e();
        this.e = handler;
        b();
        g.a().a(new DiscoveryCommentBookListAdapter$1(this));
    }

    private void b() {
        for (Mark mark : i.c().o()) {
            if (mark.getSynBook() == 1) {
                a aVar = new a(this);
                aVar.a = mark;
                aVar.b = 0;
                aVar.c = 0;
                this.a.add(aVar);
                this.b.b(mark.getBookId(), aVar);
            }
        }
    }

    private void c() {
        this.c = com.qq.reader.common.db.handle.e.a().b();
        for (Mark mark : i.c().o()) {
            com.qq.reader.common.db.handle.e.a aVar;
            long bookId = mark.getBookId();
            com.qq.reader.common.db.handle.e.a aVar2 = (com.qq.reader.common.db.handle.e.a) this.c.get(Long.valueOf(bookId));
            if (aVar2 == null) {
                com.qq.reader.common.db.handle.e.a().a(String.valueOf(bookId), 0, 0);
                aVar2 = new com.qq.reader.common.db.handle.e.a();
                aVar2.a = bookId;
                aVar2.b = 0;
                aVar2.c = 0;
                this.c.put(Long.valueOf(bookId), aVar2);
                aVar = aVar2;
            } else {
                aVar = aVar2;
            }
            a aVar3 = (a) this.b.a(bookId);
            if (aVar3 != null) {
                aVar3.a = mark;
                aVar3.b = aVar.b;
                aVar3.c = aVar.c;
            }
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.d).inflate(R.layout.discovery_comment_list_item, null);
        }
        a aVar = (a) getItem(i);
        if (aVar != null) {
            TextView textView = (TextView) ap.a(view, R.id.discovery_comment_list_item_commentamout);
            if (aVar.c <= 0) {
                textView.setText("");
            } else {
                textView.setText("新增: " + aVar.c);
            }
            Mark mark = aVar.a;
            if (mark != null) {
                c.a(this.d).a(mark.getImageURI(), (ImageView) ap.a(view, R.id.discovery_comment_list_item_bookcover), com.qq.reader.common.imageloader.a.a().j());
                ((TextView) ap.a(view, R.id.discovery_comment_list_item_bookname)).setText(mark.getBookShortName());
                ImageView imageView = (ImageView) ap.a(view, R.id.comment_book_type_icon);
                if (mark.getType() == 9) {
                    imageView.setImageResource(R.drawable.comic_book_icon);
                    imageView.setVisibility(0);
                } else {
                    imageView.setVisibility(4);
                }
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(mark.getType()));
                com.qq.reader.common.monitor.i.a("event_F337", hashMap, ReaderApplication.getApplicationContext());
            }
        }
        return view;
    }

    public ArrayList<Long> a() {
        ArrayList<Long> arrayList = new ArrayList();
        for (a aVar : this.a) {
            if (aVar != null) {
                arrayList.add(Long.valueOf(aVar.a.getBookId()));
            }
        }
        return arrayList;
    }

    public void a(long j, int i) {
        try {
            com.qq.reader.common.db.handle.e.a aVar = (com.qq.reader.common.db.handle.e.a) this.c.get(Long.valueOf(j));
            for (a aVar2 : this.a) {
                if (aVar2 != null && aVar2.a.getBookId() == j) {
                    if (aVar.b > 0) {
                        aVar2.c = aVar != null ? i - aVar.b : 0;
                    } else {
                        aVar2.c = 0;
                    }
                    if (aVar.b <= 0) {
                        aVar2.b = i;
                        aVar.b = i;
                    }
                    com.qq.reader.common.db.handle.e.a().a(String.valueOf(j), aVar.b, aVar2.c);
                }
            }
        } catch (Exception e) {
        }
    }

    public void a(long j, int i, int i2) {
        com.qq.reader.common.db.handle.e.a().a(String.valueOf(j), i, i2);
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }
}
