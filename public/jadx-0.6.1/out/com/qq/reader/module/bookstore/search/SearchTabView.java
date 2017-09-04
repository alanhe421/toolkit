package com.qq.reader.module.bookstore.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.search.SearchTabInfo.a;
import com.qq.reader.module.bookstore.search.SearchTabInfo.b;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchTabView extends AbsSearchTabView {
    private Integer q;
    private Integer r;

    public SearchTabView(Context context) {
        super(context);
    }

    protected OnClickListener b(final int i) {
        return new OnClickListener(this) {
            final /* synthetic */ SearchTabView b;

            public void onClick(View view) {
                try {
                    this.b.f(i);
                    this.b.i(i);
                    if (this.b.l[i].getCompoundDrawables()[2] != null) {
                        this.b.l[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.search_tab_tv_drawable_selected_selector, 0);
                    }
                    PopupWindow popupWindow = (PopupWindow) this.b.a.get(i);
                    if (this.b.g != null) {
                        int i = popupWindow != null ? popupWindow.isShowing() ? 1 : 2 : 0;
                        this.b.g.a(i, i);
                    }
                    this.b.e(i);
                } catch (Exception e) {
                }
            }
        };
    }

    protected OnClickListener c(final int i) {
        return new OnClickListener(this) {
            final /* synthetic */ SearchTabView b;

            public void onClick(View view) {
                try {
                    this.b.q = Integer.valueOf(0);
                    this.b.r = Integer.valueOf(1);
                    a aVar = (a) view.getTag(R.string.obj_tag);
                    if (this.b.g != null) {
                        List arrayList = new ArrayList();
                        if (!(aVar == null || aVar.f == -1)) {
                            b bVar = new b();
                            bVar.a = aVar.e;
                            bVar.c = aVar.f;
                            bVar.b = aVar.d;
                            ((List) this.b.n.get(Integer.valueOf(i))).clear();
                            ((List) this.b.n.get(Integer.valueOf(i))).add(bVar);
                        }
                        arrayList.addAll(this.b.getSelectedData());
                        this.b.g.a(this.b.a(arrayList));
                    }
                    this.b.l[1].setSelected(true);
                    this.b.l[1].setTextColor(this.b.getResources().getColor(R.color.search_tab_txt_selected_color));
                    this.b.f(i);
                    this.b.i(i);
                    this.b.e(2);
                    PopupWindow popupWindow = (PopupWindow) this.b.a.get(i);
                    if (this.b.g != null) {
                        int i = popupWindow != null ? popupWindow.isShowing() ? 1 : 2 : 0;
                        this.b.g.a(i, i);
                    }
                    this.b.a();
                    Map hashMap;
                    if (this.b.getInfoType() == 4) {
                        hashMap = new HashMap();
                        hashMap.put("order", this.b.l[1].getText().toString());
                        i.a("event_F302", hashMap, ReaderApplication.getApplicationImp());
                    } else if (this.b.getInfoType() == 5) {
                        hashMap = new HashMap();
                        hashMap.put("order", this.b.l[1].getText().toString());
                        i.a("event_B287", hashMap, ReaderApplication.getApplicationImp());
                    }
                } catch (Exception e) {
                }
            }
        };
    }

    protected void a(int i, boolean z) {
        List<com.qq.reader.widget.b> list = (List) this.c.get(i);
        if (list != null) {
            for (com.qq.reader.widget.b bVar : list) {
                if (z) {
                    bVar.c();
                } else {
                    bVar.e();
                }
            }
        }
    }

    protected OnClickListener d(final int i) {
        return new OnClickListener(this) {
            final /* synthetic */ SearchTabView b;

            public void onClick(View view) {
                try {
                    this.b.f(i);
                    this.b.i(i);
                    this.b.e(2);
                    PopupWindow popupWindow = (PopupWindow) this.b.a.get(i);
                    if (this.b.g != null) {
                        int i = popupWindow != null ? popupWindow.isShowing() ? 1 : 2 : 0;
                        this.b.g.a(i, i);
                    }
                } catch (Exception e) {
                }
            }
        };
    }

    public SearchTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected Integer getExceptId() {
        return this.q;
    }

    private void l(int i) {
        ((List) this.n.get(Integer.valueOf(i))).clear();
        for (com.qq.reader.widget.b bVar : (List) this.c.get(i)) {
            bVar.f();
            ((List) this.n.get(Integer.valueOf(i))).addAll(bVar.b());
        }
    }

    public OnClickListener j(final int i) {
        return new OnClickListener(this) {
            final /* synthetic */ SearchTabView b;

            public void onClick(View view) {
                try {
                    this.b.l(i);
                    this.b.i(i);
                    List selectedData = this.b.getSelectedData();
                    if (this.b.g != null) {
                        this.b.g.a(this.b.a(selectedData));
                    }
                    this.b.e(i);
                    if (this.b.getInfoType() == 5) {
                        i.a("event_B292", null, ReaderApplication.getApplicationImp());
                    }
                } catch (Exception e) {
                }
            }
        };
    }

    public SearchTabView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void e(int i) {
        Object obj = null;
        try {
            if (((List) this.n.get(Integer.valueOf(i))).size() > 0) {
                obj = 1;
            }
            this.l[i].setTextColor(obj != null ? getResources().getColor(R.color.search_tab_txt_selected_color) : getResources().getColor(R.color.search_tab_txt_def_color));
            if (this.l[i].getCompoundDrawables()[2] != null) {
                this.l[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, obj != null ? R.drawable.search_tab_tv_drawable_selected_selector : R.drawable.search_tab_tv_drawable_selector, 0);
            }
        } catch (Exception e) {
        }
    }

    public String a(List<b> list) {
        String str;
        int length;
        int i = 0;
        String[] strArr = new String[]{"", WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ};
        String str2 = "";
        int i2 = 0;
        while (i2 < list.size()) {
            switch (((b) list.get(i2)).c) {
                case 0:
                    strArr[0] = strArr[0] + ((b) list.get(i2)).a + ":";
                    str = str2;
                    break;
                case 1:
                    strArr[1] = String.valueOf(((b) list.get(i2)).a);
                    str = str2;
                    break;
                case 2:
                    strArr[2] = String.valueOf(((b) list.get(i2)).a);
                    str = str2;
                    break;
                case 3:
                    strArr[3] = String.valueOf(((b) list.get(i2)).a);
                    str = str2;
                    break;
                case 4:
                    strArr[4] = String.valueOf(((b) list.get(i2)).a);
                    str = str2;
                    break;
                case 5:
                    strArr[5] = String.valueOf(((b) list.get(i2)).a);
                    str = str2;
                    break;
                case 6:
                    str = str2 + ((b) list.get(i2)).a + ",";
                    break;
                default:
                    str = str2;
                    break;
            }
            i2++;
            str2 = str;
        }
        String str3 = strArr[0];
        if (strArr[0].length() > 0) {
            length = strArr[0].length() - 1;
        } else {
            length = 0;
        }
        strArr[0] = str3.substring(0, length);
        StringBuffer stringBuffer = new StringBuffer();
        for (length = 0; length < strArr.length; length++) {
            stringBuffer.append(strArr[length]);
            if (length != strArr.length - 1) {
                stringBuffer.append(",");
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            str = "actionId";
            if (str2.length() > 0) {
                i = str2.length() - 1;
            }
            jSONObject.put(str, str2.substring(0, i));
            jSONObject.put("actionTag", stringBuffer.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public String getSearchParas() {
        return a(getSelectedData());
    }

    protected void g(int i) {
        if (i == 0) {
            this.q = Integer.valueOf(1);
        }
    }

    public void k(int i) {
        if (i == 0 && this.r.intValue() == 1) {
            this.m[1].performClick();
        }
        this.l[i].setSelected(false);
    }

    private void a() {
        this.l[0].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.search_tab_tv_drawable_selector, 0);
        this.l[0].setSelected(false);
        this.l[0].setText(((a) this.o.a.get(0)).d);
        for (TextView selected : (List) this.b.get(0)) {
            selected.setSelected(false);
        }
        ((List) this.n.get(Integer.valueOf(0))).clear();
        this.l[0].setTextColor(getResources().getColor(R.color.search_tab_txt_def_color));
    }

    protected void h(int i) {
        this.q = Integer.valueOf(1);
        this.r = Integer.valueOf(0);
        this.l[0].setTextColor(getResources().getColor(R.color.search_tab_txt_selected_color));
        this.l[1].setTextColor(getResources().getColor(R.color.search_tab_txt_def_color));
        this.l[0].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.search_tab_tv_drawable_selected_selector, 0);
    }
}
