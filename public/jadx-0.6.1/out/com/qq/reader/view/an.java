package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.tencent.feedback.proguard.R;

/* compiled from: StyleDialog */
public class an extends BaseDialog {
    af a;
    Context b;
    private GridView c;
    private BaseAdapter d;
    private b e;
    private c i;
    private Handler j = new Handler(this) {
        final /* synthetic */ an a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 60001:
                    if (d.u < 3) {
                        d.T(this.a.b);
                        this.a.a = af.a(this.a.b.getApplicationContext(), "长按进行编辑", 0);
                        this.a.a.a();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    /* compiled from: StyleDialog */
    class a extends BaseAdapter {
        final /* synthetic */ an a;
        private int[] b = new int[]{R.drawable.style01, R.drawable.style07, R.drawable.style05, R.drawable.style04, R.drawable.style02, R.drawable.style03, R.drawable.style06, R.drawable.color_btn_empty};
        private String[] c = new String[]{"羊皮纸", "水墨江南", "护眼模式", "华灯初上", "粉红回忆", "白色磨砂", "咖啡时光", "自定义"};
        private Context d;

        public a(an anVar, Context context) {
            this.a = anVar;
            this.d = context;
        }

        public int getCount() {
            return this.b.length;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) d.L(this.d);
        }

        public boolean a(int i) {
            return ((long) i) == getItemId(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate;
            ImageView imageView;
            if (i < this.b.length - 1) {
                inflate = LayoutInflater.from(this.a.f.getContext()).inflate(R.layout.styleitem, null);
                imageView = (ImageView) inflate.findViewById(R.id.styleImage);
                imageView.setBackgroundResource(this.b[i]);
                imageView.setScaleType(ScaleType.FIT_XY);
                if (a(i)) {
                    imageView.setImageResource(R.drawable.color_btn_selected);
                } else {
                    imageView.setImageDrawable(null);
                }
            } else {
                View inflate2 = LayoutInflater.from(this.a.f.getContext()).inflate(R.layout.styleitemlast, null);
                imageView = (ImageView) inflate2.findViewById(R.id.styleImage);
                StyleItemView styleItemView = (StyleItemView) inflate2.findViewById(R.id.itemview);
                imageView.setScaleType(ScaleType.FIT_XY);
                if (a(i)) {
                    imageView.setImageResource(R.drawable.color_btn_selected);
                    inflate = inflate2;
                } else {
                    imageView.setImageDrawable(null);
                    inflate = inflate2;
                }
            }
            ((TextView) inflate.findViewById(R.id.styleImage_info)).setText(this.c[i]);
            return inflate;
        }
    }

    /* compiled from: StyleDialog */
    public interface c {
        void a();
    }

    public void a(b bVar) {
        this.e = bVar;
    }

    public void a(c cVar) {
        this.i = cVar;
    }

    public an(Activity activity) {
        this.b = activity;
        if (this.f == null) {
            a(activity, null, R.layout.styledialog, true, false, true);
            this.c = (GridView) this.f.findViewById(R.id.styleDialog);
            this.d = new a(this, this.f.getContext());
            this.c.setAdapter(this.d);
            this.c.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ an a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (!(this.a.e == null || this.a.i == null)) {
                        if (i != 8 || d.m) {
                            this.a.e.e(i);
                        } else {
                            this.a.i.a();
                        }
                        this.a.d.notifyDataSetChanged();
                    }
                    if (i == 8 && d.m) {
                        this.a.j.sendEmptyMessage(60001);
                    }
                }
            });
            this.c.setOnItemLongClickListener(new OnItemLongClickListener(this) {
                final /* synthetic */ an a;

                {
                    this.a = r1;
                }

                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (this.a.i == null || i != 8 || !d.m) {
                        return false;
                    }
                    this.a.e.e(i);
                    this.a.i.a();
                    return true;
                }
            });
            this.f.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ an a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.a.a != null) {
                        this.a.a.b();
                    }
                }
            });
        }
    }
}
