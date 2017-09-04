package com.qq.reader.module.comic.card;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.entity.e;
import com.qq.reader.module.comic.entity.f;
import com.qq.reader.module.game.card.view.HorizontalListView;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONObject;

public class ComicDetailClassifyHotCard extends ComicDetailPageBaseCard<List<f>> {
    private ListAdapter horizontalAdapter;

    public ComicDetailClassifyHotCard(b bVar, String str) {
        super(bVar, str);
    }

    public boolean isValid() {
        return (this.item == null || this.item.c == null || ((List) this.item.c).size() <= 0) ? false : true;
    }

    public int getResLayoutId() {
        return R.layout.comic_detail_item_classifybook_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = (e) new Gson().fromJson(jSONObject.toString(), new TypeToken<e<List<f>>>(this) {
            final /* synthetic */ ComicDetailClassifyHotCard a;

            {
                this.a = r1;
            }
        }.getType());
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.tv_comic_four_card_title_name)).setText(String.valueOf(this.item.a));
        ((TextView) ap.a(getRootView(), R.id.tv_comic_four_card_title_face)).setText(String.valueOf(this.item.b));
        HorizontalListView horizontalListView = (HorizontalListView) ap.a(getRootView(), R.id.hlv);
        horizontalListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ ComicDetailClassifyHotCard a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                o.b(this.a.getEvnetListener().getFromActivity(), ((f) ((List) this.a.item.c).get(i)).a, null, "4");
                i.a("event_F251", null, ReaderApplication.getApplicationImp());
            }
        });
        horizontalListView.setDividerWidth(ao.a(10.0f));
        if (this.horizontalAdapter == null) {
            this.horizontalAdapter = new BaseAdapter(this) {
                final /* synthetic */ ComicDetailClassifyHotCard a;

                {
                    this.a = r1;
                }

                public int getCount() {
                    return ((List) this.a.item.c).size();
                }

                public Object getItem(int i) {
                    return ((List) this.a.item.c).get(i);
                }

                public long getItemId(int i) {
                    return Long.parseLong(((f) ((List) this.a.item.c).get(i)).a);
                }

                public View getView(int i, View view, ViewGroup viewGroup) {
                    if (view == null) {
                        view = View.inflate(this.a.getEvnetListener().getFromActivity(), R.layout.comic_detail_classify_item, null);
                    }
                    ImageView imageView = (ImageView) ap.a(view, R.id.iv_comic_cover);
                    LayoutParams layoutParams = imageView.getLayoutParams();
                    c.a(this.a.getEvnetListener().getFromActivity()).a(ao.a(Long.parseLong(((f) ((List) this.a.item.c).get(i)).a), layoutParams.width, layoutParams.height), imageView, a.a().j());
                    ((TextView) ap.a(view, R.id.tv_comic_title)).setText(((f) ((List) this.a.item.c).get(i)).b);
                    return view;
                }
            };
            horizontalListView.setAdapter(this.horizontalAdapter);
        }
        i.a("event_F250", null, ReaderApplication.getApplicationImp());
    }
}
