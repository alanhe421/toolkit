package com.qq.reader.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.feedback.proguard.R;

/* compiled from: ShareDialog */
class aj$b extends BaseAdapter {
    final /* synthetic */ aj a;

    aj$b(aj ajVar) {
        this.a = ajVar;
    }

    public int getCount() {
        if (WXApiManager.getInstance(aj.b(this.a)).isWXinstalled() && WXApiManager.getInstance(aj.b(this.a)).isWXsupportApi()) {
            return 6;
        }
        return 4;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        aj$c com_qq_reader_view_aj_c;
        if (view == null) {
            view = LayoutInflater.from(aj.b(this.a)).inflate(R.layout.sharedialog_item, null);
            com_qq_reader_view_aj_c = new aj$c(this.a, view);
            view.setTag(com_qq_reader_view_aj_c);
        } else {
            com_qq_reader_view_aj_c = (aj$c) view.getTag();
        }
        if (getCount() != 6) {
            switch (i) {
                case 0:
                    com_qq_reader_view_aj_c.a(aj.b(this.a).getString(R.string.qq));
                    com_qq_reader_view_aj_c.a((int) R.drawable.qq);
                    break;
                case 1:
                    com_qq_reader_view_aj_c.a(aj.b(this.a).getString(R.string.qzone));
                    com_qq_reader_view_aj_c.a((int) R.drawable.qzone);
                    break;
                case 2:
                    com_qq_reader_view_aj_c.a(aj.b(this.a).getString(R.string.sina_weibo));
                    com_qq_reader_view_aj_c.a((int) R.drawable.sina_weibo);
                    break;
                case 3:
                    com_qq_reader_view_aj_c.a(aj.b(this.a).getString(R.string.more));
                    com_qq_reader_view_aj_c.a((int) R.drawable.more);
                    break;
                default:
                    break;
            }
        }
        switch (i) {
            case 0:
                com_qq_reader_view_aj_c.a(aj.b(this.a).getString(R.string.wxcircle));
                com_qq_reader_view_aj_c.a((int) R.drawable.pengyouquan);
                break;
            case 1:
                com_qq_reader_view_aj_c.a(aj.b(this.a).getString(R.string.wxfriend));
                com_qq_reader_view_aj_c.a((int) R.drawable.weixin);
                break;
            case 2:
                com_qq_reader_view_aj_c.a(aj.b(this.a).getString(R.string.qq));
                com_qq_reader_view_aj_c.a((int) R.drawable.qq);
                break;
            case 3:
                com_qq_reader_view_aj_c.a(aj.b(this.a).getString(R.string.qzone));
                com_qq_reader_view_aj_c.a((int) R.drawable.qzone);
                break;
            case 4:
                com_qq_reader_view_aj_c.a(aj.b(this.a).getString(R.string.sina_weibo));
                com_qq_reader_view_aj_c.a((int) R.drawable.sina_weibo);
                break;
            case 5:
                com_qq_reader_view_aj_c.a(aj.b(this.a).getString(R.string.more));
                com_qq_reader_view_aj_c.a((int) R.drawable.more);
                break;
        }
        return view;
    }
}
