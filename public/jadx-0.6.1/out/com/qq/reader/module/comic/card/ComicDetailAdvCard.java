package com.qq.reader.module.comic.card;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.entity.d;
import com.qq.reader.module.comic.entity.e;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONObject;

public class ComicDetailAdvCard extends ComicDetailPageBaseCard<List<d>> {
    public ComicDetailAdvCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.comic_detail_item_advs_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = (e) new Gson().fromJson(jSONObject.toString(), new TypeToken<e<List<d>>>(this) {
            final /* synthetic */ ComicDetailAdvCard a;

            {
                this.a = r1;
            }
        }.getType());
        return (this.item == null || this.item.c == null || ((List) this.item.c).isEmpty()) ? false : true;
    }

    public void attachView() {
        View rootView = getRootView();
        final d dVar = (d) ((List) this.item.c).get(0);
        c.a(getEvnetListener().getFromActivity()).a(dVar.a(), (ImageView) rootView.findViewById(R.id.iv_comic_detail_adv), a.a().j());
        rootView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ComicDetailAdvCard b;

            public void onClick(View view) {
                String b = dVar.b();
                if (com.qq.reader.qurl.c.a(b)) {
                    try {
                        com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), b);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                i.a("event_F256", null, ReaderApplication.getApplicationImp());
            }
        });
        i.a("event_F255", null, ReaderApplication.getApplicationImp());
    }
}
