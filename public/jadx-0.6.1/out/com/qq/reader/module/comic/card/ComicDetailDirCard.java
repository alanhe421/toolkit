package com.qq.reader.module.comic.card;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.activity.NativeBookStoreComicDirActivity;
import com.qq.reader.module.comic.entity.e;
import com.qq.reader.module.comic.entity.g;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ComicDetailDirCard extends ComicDetailPageBaseCard<g> {
    public ComicDetailDirCard(b bVar, String str) {
        super(bVar, str);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = (e) new Gson().fromJson(jSONObject.toString(), new TypeToken<e<g>>(this) {
            final /* synthetic */ ComicDetailDirCard a;

            {
                this.a = r1;
            }
        }.getType());
        return (this.item == null || this.item.c == null) ? false : true;
    }

    public void attachView() {
        View rootView = getRootView();
        rootView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ComicDetailDirCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                NativeBookStoreComicDirActivity.a(this.a.getEvnetListener().getFromActivity(), String.valueOf(((g) this.a.item.c).a), 0, 0);
                i.a("event_F249", null, ReaderApplication.getApplicationImp());
            }
        });
        ((TextView) rootView.findViewById(R.id.detail_dir_card_title)).setText(this.item.a);
        ((TextView) rootView.findViewById(R.id.detail_dir_update_info)).setText(((g) this.item.c).b);
        ((TextView) rootView.findViewById(R.id.detail_dir_card_update_time)).setText(((g) this.item.c).c);
    }

    public int getResLayoutId() {
        return R.layout.comic_detail_item_dir_layout;
    }
}
