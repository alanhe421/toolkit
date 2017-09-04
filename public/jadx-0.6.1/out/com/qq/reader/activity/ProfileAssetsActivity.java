package com.qq.reader.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.c;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.feed.mypreference.MyReadingGeneActivity;
import com.tencent.feedback.proguard.R;
import com.tencent.tesla.soload.SoLoadCore;

public class ProfileAssetsActivity extends ReaderBaseActivity implements OnItemClickListener {
    private Context a;
    private ListView b = null;
    private a c = null;
    private ImageView d = null;
    private TextView e = null;

    public class a extends BaseAdapter {
        final /* synthetic */ ProfileAssetsActivity a;
        private Context b;
        private String[] c = new String[]{"书城历史", "我的笔记", "我的收藏", "阅读偏好"};
        private int[] d = new int[]{R.drawable.asset_history, R.drawable.asset_note, R.drawable.asset_collection, R.drawable.asset_favor};

        public a(ProfileAssetsActivity profileAssetsActivity, Context context) {
            this.a = profileAssetsActivity;
            this.b = context;
        }

        public int getCount() {
            return this.c.length;
        }

        public Object getItem(int i) {
            return this.c[i];
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(R.layout.profile_assets_list_item, null);
            }
            ((ImageView) ap.a(view, R.id.profile_assets_list_item_icon)).setBackgroundResource(this.d[i]);
            ((TextView) ap.a(view, R.id.profile_assets_list_item_text)).setText(this.c[i]);
            return view;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = getApplicationContext();
        setContentView(R.layout.profile_assets_layout);
        this.b = (ListView) findViewById(R.id.profile_assets_list);
        this.c = new a(this, this.a);
        this.b.setAdapter(this.c);
        this.b.setOnItemClickListener(this);
        this.d = (ImageView) findViewById(R.id.profile_header_left_back);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ProfileAssetsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.e = (TextView) findViewById(R.id.profile_header_title);
        this.e.setText("阅读资产");
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        finish();
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.b.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount <= this.c.getCount()) {
            switch (headerViewsCount) {
                case 0:
                    c();
                    i.a("event_D29", null, getApplicationContext());
                    return;
                case 1:
                    d();
                    i.a("event_D8", null, getApplicationContext());
                    return;
                case 2:
                    b();
                    return;
                case 3:
                    i.a("event_D72", null, getApplicationContext());
                    a();
                    return;
                case 4:
                    i.a("event_D69", null, getApplicationContext());
                    return;
                default:
                    return;
            }
        }
    }

    private void a() {
        Intent intent = new Intent();
        intent.setClass(this, MyReadingGeneActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void b() {
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "myfocus");
        intent.putExtra("LOCAL_STORE_IN_TITLE", "我的收藏");
        intent.setClass(this, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void c() {
        Intent intent = new Intent();
        intent.setClass(this, OnlineHistoryActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    private void d() {
        Intent intent = new Intent();
        intent.setClass(this, OneBookNoteActivity.class);
        intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }
}
