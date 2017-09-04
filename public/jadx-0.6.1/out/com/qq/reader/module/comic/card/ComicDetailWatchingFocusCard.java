package com.qq.reader.module.comic.card;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bumptech.glide.request.b.j;
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
import com.qq.reader.module.comic.activity.NativeBookStoreComicDetailActivity;
import com.qq.reader.module.comic.entity.ComicWatchingFocusItem;
import com.qq.reader.module.comic.entity.e;
import com.qq.reader.module.comic.views.PhotoView;
import com.qq.reader.module.game.card.view.HorizontalListView;
import com.qq.reader.module.game.card.view.HorizontalListView.OnScrollStateChangedListener;
import com.qq.reader.module.game.card.view.HorizontalListView.OnScrollStateChangedListener.ScrollState;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONObject;

public class ComicDetailWatchingFocusCard extends ComicDetailPageBaseCard<List<ComicWatchingFocusItem>> {
    private ListAdapter horizontalAdapter;
    private boolean isFirstScroll = true;

    public ComicDetailWatchingFocusCard(b bVar, String str) {
        super(bVar, str);
    }

    public boolean isValid() {
        return (this.item == null || this.item.c == null || ((List) this.item.c).size() <= 0) ? false : true;
    }

    public int getResLayoutId() {
        return R.layout.comic_detail_watching_focus_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = (e) new Gson().fromJson(jSONObject.toString(), new TypeToken<e<List<ComicWatchingFocusItem>>>(this) {
            final /* synthetic */ ComicDetailWatchingFocusCard a;

            {
                this.a = r1;
            }
        }.getType());
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.item_title)).setText(String.valueOf(this.item.a));
        HorizontalListView horizontalListView = (HorizontalListView) ap.a(getRootView(), R.id.hlv);
        horizontalListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ ComicDetailWatchingFocusCard a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.getEvnetListener() != null) {
                    o.a(this.a.getEvnetListener().getFromActivity(), (List) this.a.item.c, i, ((PhotoView) view.findViewById(R.id.img)).getInfo());
                    i.a("event_F248", null, ReaderApplication.getApplicationImp());
                    if (this.a.getEvnetListener().getFromActivity() instanceof NativeBookStoreComicDetailActivity) {
                        ((NativeBookStoreComicDetailActivity) this.a.getEvnetListener().getFromActivity()).a(false);
                    }
                }
            }
        });
        horizontalListView.setOnScrollStateChangedListener(new OnScrollStateChangedListener(this) {
            final /* synthetic */ ComicDetailWatchingFocusCard a;

            {
                this.a = r1;
            }

            public void a(ScrollState scrollState) {
                if (scrollState == ScrollState.SCROLL_STATE_IDLE) {
                    this.a.onScrollStat();
                }
            }
        });
        horizontalListView.setDividerWidth(ao.a(12.0f));
        if (this.horizontalAdapter == null) {
            this.horizontalAdapter = new BaseAdapter(this) {
                final /* synthetic */ ComicDetailWatchingFocusCard a;

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
                    return (long) ((ComicWatchingFocusItem) ((List) this.a.item.c).get(i)).id;
                }

                public View getView(int i, View view, ViewGroup viewGroup) {
                    if (view == null) {
                        view = View.inflate(this.a.getEvnetListener().getFromActivity(), R.layout.comic_detail_watching_focus_img_layout, null);
                    }
                    final ImageView imageView = (ImageView) ap.a(view, R.id.img);
                    c.a(this.a.getEvnetListener().getFromActivity()).a(((ComicWatchingFocusItem) ((List) this.a.item.c).get(i)).smallUrl, imageView, a.a().j(), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
                        final /* synthetic */ AnonymousClass4 b;

                        public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                            imageView.setImageResource(R.drawable.comic_watch_focus_default_bg);
                            return false;
                        }

                        public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                            return false;
                        }
                    });
                    return view;
                }
            };
            horizontalListView.setAdapter(this.horizontalAdapter);
        }
    }

    private void onScrollStat() {
        if (this.isFirstScroll) {
            i.a("event_F248", null, ReaderApplication.getApplicationImp());
            this.isFirstScroll = false;
        }
    }
}
