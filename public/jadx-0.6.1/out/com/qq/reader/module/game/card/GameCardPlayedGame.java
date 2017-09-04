package com.qq.reader.module.game.card;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.game.card.view.HorizontalListView;
import com.qq.reader.module.game.data.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GameCardPlayedGame extends com.qq.reader.module.bookstore.qnative.card.a implements OnItemClickListener {
    private a adapter;
    private List<c> mygames;

    private class a extends BaseAdapter {
        final /* synthetic */ GameCardPlayedGame a;
        private Activity b;

        public a(GameCardPlayedGame gameCardPlayedGame, Activity activity) {
            this.a = gameCardPlayedGame;
            this.b = activity;
        }

        public int getCount() {
            return this.a.mygames == null ? 0 : this.a.mygames.size();
        }

        public Object getItem(int i) {
            return this.a.mygames == null ? null : (c) this.a.mygames.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(this.b, R.layout.game_my_game_history_item_layout, null);
            }
            if (this.a.mygames.size() > i) {
                a((c) this.a.mygames.get(i), view);
            }
            return view;
        }

        private void a(c cVar, View view) {
            com.qq.reader.common.imageloader.c.a(this.a.getEvnetListener().getFromActivity()).a(cVar.e(), (ImageView) ap.a(view, R.id.iv_game_icon), com.qq.reader.common.imageloader.a.a().r());
            ((TextView) ap.a(view, R.id.game_name)).setText(cVar.c());
        }
    }

    public GameCardPlayedGame(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.game_my_game_history_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray("games");
        if (this.mygames == null) {
            this.mygames = new ArrayList();
        }
        this.mygames.clear();
        for (int i = 0; i < optJSONArray.length(); i++) {
            c a = c.a(optJSONArray.optJSONObject(i));
            com.qq.reader.module.game.a.c(a);
            this.mygames.add(a);
        }
        return true;
    }

    public void attachView() {
        if (this.adapter == null) {
            this.adapter = new a(this, getEvnetListener().getFromActivity());
        }
        HorizontalListView horizontalListView = (HorizontalListView) ap.a(getRootView(), R.id.game_history_horizontal_list);
        ListAdapter adapter = horizontalListView.getAdapter();
        if (adapter == null || adapter != this.adapter) {
            horizontalListView.setAdapter(this.adapter);
        }
        if (this.mygames == null || this.mygames.size() <= 0) {
            getRootView().setVisibility(8);
        } else {
            this.adapter.notifyDataSetChanged();
            getRootView().setVisibility(0);
        }
        horizontalListView.setOnItemClickListener(this);
        ap.a(getRootView(), R.id.title_btn).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GameCardPlayedGame a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                com.qq.reader.module.game.a.b().a(true);
                o.v(this.a.getEvnetListener().getFromActivity(), null);
                i.a("event_A218", null, ReaderApplication.getApplicationImp());
            }
        });
        i.a("event_B236", null, ReaderApplication.getApplicationImp());
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        com.qq.reader.module.game.a.b().a(true);
        i.a("event_B237", null, ReaderApplication.getApplicationImp());
        if (this.mygames != null && this.mygames.size() > i) {
            c cVar = (c) this.mygames.get(i);
            if (cVar.g()) {
                String b = cVar.b();
                if (!TextUtils.isEmpty(b)) {
                    i.a("event_A232", null, ReaderApplication.getApplicationImp());
                    com.qq.reader.module.game.a.a(cVar.l() + "", "1");
                    if (com.qq.reader.common.login.c.b()) {
                        o.a(getEvnetListener().getFromActivity(), b, false, null);
                    } else {
                        getEvnetListener().doFunction(com.qq.reader.module.game.a.a(b));
                    }
                }
            } else if (com.qq.reader.common.utils.ao.a.a(getEvnetListener().getFromActivity(), cVar.f())) {
                com.qq.reader.common.utils.ao.a.a(getEvnetListener().getFromActivity(), cVar.f());
                i.a("event_A232", null, ReaderApplication.getApplicationImp());
                com.qq.reader.module.game.a.a(cVar.l() + "", "3");
            } else {
                String j2 = cVar.j();
                if (!TextUtils.isEmpty(j2)) {
                    if (com.qq.reader.qurl.c.b(j2)) {
                        try {
                            com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), j2);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    o.a(getEvnetListener().getFromActivity(), j2, false, null);
                }
            }
        }
    }
}
