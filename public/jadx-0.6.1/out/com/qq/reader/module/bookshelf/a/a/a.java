package com.qq.reader.module.bookshelf.a.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookshelf.i;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

/* compiled from: CategoryBooksAdapter */
public class a extends i {
    private ArrayList<Mark> o = new ArrayList();
    private int p = 10101;

    public a(Context context) {
        super(context, false);
    }

    public void a(ArrayList<Mark> arrayList) {
        this.o = arrayList;
    }

    public void b(int i) {
        this.p = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.category_detail_list_item, null);
            b bVar2 = new b(view);
            view.setTag(bVar2);
            bVar = bVar2;
        } else {
            bVar = (b) view.getTag();
        }
        Mark mark = (Mark) getItem(i);
        if (mark != null) {
            boolean z;
            if (mark.getIsFinish() == 1) {
                z = true;
            } else {
                z = false;
            }
            String str = "";
            str = mark.getLastUpdateChapter();
            bVar.b(mark.getBookShortName());
            bVar.a(mark.getBookShortName());
            bVar.b(mark.getType());
            if (z || str.length() <= 0) {
                bVar.c(mark.getAuthor());
            } else {
                bVar.c(str);
            }
            if (this.p == 10101) {
                bVar.b(false);
            } else {
                bVar.b(true);
                bVar.a(this.o.contains(mark));
            }
            bVar.a(mark.getPrivateProperty());
            bVar.a();
            final String bookName = mark.getBookName();
            c.a(this.a).a(mark.getImageURI(), bVar.a, com.qq.reader.common.imageloader.a.a().j(), new e<String, b>(this) {
                final /* synthetic */ a c;

                public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                    bVar.d(bookName);
                    return true;
                }

                public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                    return false;
                }
            });
        }
        return view;
    }
}
