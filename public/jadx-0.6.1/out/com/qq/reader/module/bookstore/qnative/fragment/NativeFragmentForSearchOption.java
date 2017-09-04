package com.qq.reader.module.bookstore.qnative.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.ao.d;
import com.qq.reader.module.bookstore.qnative.model.c;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.feed.mypreference.StickyGridHeadersGridView;
import com.qq.reader.module.feed.mypreference.e;
import com.qq.reader.view.af;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONObject;

public class NativeFragmentForSearchOption extends BaseFragment {
    private Bundle args;
    private String fileData;
    private c infoModel;
    private String jsonData;
    private b mDelegateAdapter;
    private int mGridItem_height;
    private StickyGridHeadersGridView mGridView;
    private a mOptionChangeListener;
    private RelativeLayout mRlContent;
    private View mRootView;
    private String mTabName;
    private HashMap<String, ArrayList<String>> mValueMap = new HashMap();

    public interface a {
        void a(String str, String str2);
    }

    private class b extends BaseAdapter implements e {
        final /* synthetic */ NativeFragmentForSearchOption a;
        private c b;

        public /* synthetic */ Object getItem(int i) {
            return b(i);
        }

        public b(NativeFragmentForSearchOption nativeFragmentForSearchOption, c cVar) {
            this.a = nativeFragmentForSearchOption;
            this.b = cVar;
        }

        public int a(int i) {
            try {
                return ((com.qq.reader.module.bookstore.qnative.model.c.a) this.b.c.b.get(i)).b;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        public int a() {
            int i;
            Exception e;
            try {
                i = this.b.c.a;
                try {
                    if (this.a.mRlContent != null) {
                        if (i > 0) {
                            this.a.mRlContent.setPadding(0, 0, 0, 0);
                        } else {
                            this.a.mRlContent.setPadding(0, this.a.getResources().getDimensionPixelOffset(R.dimen.search_tool_option_fragment_header_empty_padding_top), 0, 0);
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return i;
                }
            } catch (Exception e3) {
                Exception exception = e3;
                i = 0;
                e = exception;
                e.printStackTrace();
                return i;
            }
            return i;
        }

        public View a(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(this.a.getActivity(), R.layout.search_option_header_ui, null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_increasement);
            if (i == 0) {
                linearLayout.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
            }
            TextView textView = (TextView) view.findViewById(R.id.tv_header_text);
            textView.setText(((com.qq.reader.module.bookstore.qnative.model.c.a) this.b.c.b.get(i)).a);
            if (TextUtils.isEmpty(textView.getText().toString())) {
                view.setVisibility(4);
            }
            return view;
        }

        public int getCount() {
            int i = 0;
            try {
                i = this.b.a.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return i;
        }

        public c.c b(int i) {
            return (c.c) this.b.a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(this.a.getActivity(), R.layout.search_option_grid_layout, null);
                view.setLayoutParams(new LayoutParams(-1, this.a.mGridItem_height));
            }
            final CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_select);
            final c.c b = b(i);
            checkBox.setText(b.a);
            checkBox.setChecked(this.a.itemIsChecked(b));
            if (checkBox.isChecked()) {
                checkBox.setTextColor(this.a.getActivity().getResources().getColor(R.color.text_color_c301));
            } else if (this.a.couldCheck(b) || this.a.isSingleSelected(b)) {
                checkBox.setTextColor(this.a.getActivity().getResources().getColor(R.color.search_option_grid_textcolor));
            } else {
                checkBox.setTextColor(this.a.getActivity().getResources().getColor(R.color.search_option_disable_textcolor));
            }
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b c;

                public void onClick(View view) {
                    boolean z = !checkBox.isChecked();
                    if (!z || this.c.a.couldCheck(b) || this.c.a.isSingleSelected(b)) {
                        if (z) {
                            if (this.c.a.isSingleSelected(b)) {
                                this.c.a.removeAllValueInSameColumn(b);
                            }
                            this.c.a.addCheckedItem(b);
                        } else {
                            this.c.a.removeCheckedItem(b);
                        }
                        this.c.a.mOptionChangeListener.a(this.c.a.getColumnName(b), this.c.a.generateOptionText(b));
                        checkBox.setChecked(z);
                        this.c.notifyDataSetChanged();
                        return;
                    }
                    af.a(this.c.a.getActivity(), "最多可选择" + this.c.a.getMaxSelection(b) + "个" + this.c.a.mTabName, 0).a();
                }
            });
            return view;
        }
    }

    public void setFragmentArgs(Bundle bundle) {
        this.args = bundle;
        initInfoModel();
        initValueMap();
    }

    public void setInitialValues(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("&");
            Set keySet = this.mValueMap.keySet();
            int i = 0;
            while (split != null && i < split.length) {
                String str2 = split[i];
                int indexOf = str2.indexOf("=");
                if (indexOf >= 0) {
                    String substring = str2.substring(0, indexOf);
                    if (!TextUtils.isEmpty(substring) && keySet.contains(substring) && indexOf + 1 < str2.length()) {
                        String[] split2 = str2.substring(indexOf + 1).split(",");
                        indexOf = 0;
                        while (split2 != null && indexOf < split2.length) {
                            String str3 = split2[indexOf];
                            if (!str3.equals(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE) && valuesContainInInfoModel(substring, str3)) {
                                ArrayList arrayList = (ArrayList) this.mValueMap.get(substring);
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                arrayList.add(str3);
                                this.mValueMap.put(substring, arrayList);
                                if (this.mOptionChangeListener != null) {
                                    this.mOptionChangeListener.a(substring, getOptionText(substring));
                                }
                            }
                            indexOf++;
                        }
                    }
                }
                i++;
            }
        }
    }

    public void setOptionChangeListner(a aVar) {
        this.mOptionChangeListener = aVar;
    }

    public void clearAllValueAndUpdate(String str) {
        try {
            ArrayList arrayList = (ArrayList) this.mValueMap.get(str);
            if (arrayList != null) {
                arrayList.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mDelegateAdapter != null) {
            this.mDelegateAdapter.notifyDataSetChanged();
        }
        if (this.mOptionChangeListener != null) {
            this.mOptionChangeListener.a(str, "");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mGridItem_height = getResources().getDimensionPixelOffset(R.dimen.search_option_grid_height);
        this.mRootView = View.inflate(getActivity(), R.layout.search_option_fragment_ui, null);
        this.mRlContent = (RelativeLayout) this.mRootView.findViewById(R.id.rl_content_bg);
        return this.mRootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mGridView = (StickyGridHeadersGridView) this.mRootView.findViewById(R.id.gridview);
        this.mGridView.setOverScrollMode(2);
        initGridView();
    }

    private String getOptionTextByValue(int i, String str) {
        int i2 = 0;
        while (i2 < this.infoModel.a.size()) {
            try {
                c.c cVar = (c.c) this.infoModel.a.get(i2);
                if (cVar.c == i && cVar.b.contains(str)) {
                    return cVar.a;
                }
                i2++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private int getSecIdByColumn(String str) {
        int i = 0;
        while (i < this.infoModel.b.c.size()) {
            try {
                String str2 = (String) this.infoModel.b.c.get(i);
                if (str2 != null && str != null && str2.equals(str)) {
                    return i;
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private String getOptionText(String str) {
        ArrayList arrayList = (ArrayList) this.mValueMap.get(str);
        String str2 = "";
        int i = 0;
        while (arrayList != null && i < arrayList.size()) {
            str2 = str2 + getOptionTextByValue(getSecIdByColumn(str), (String) arrayList.get(i));
            if (i != arrayList.size() - 1) {
                str2 = str2 + "、";
            }
            i++;
        }
        return str2;
    }

    private String generateOptionText(c.c cVar) {
        String str;
        Exception exception;
        String str2 = "";
        try {
            ArrayList arrayList = (ArrayList) this.mValueMap.get(getColumnName(cVar));
            int i = 0;
            while (arrayList != null && i < arrayList.size()) {
                String str3 = str2 + getOptionTextByValue(cVar.c, (String) arrayList.get(i));
                try {
                    if (i != arrayList.size() - 1) {
                        str3 = str3 + "、";
                    }
                    i++;
                    str2 = str3;
                } catch (Exception e) {
                    Exception exception2 = e;
                    str = str3;
                    exception = exception2;
                }
            }
            return str2;
        } catch (Exception e2) {
            exception = e2;
            str = str2;
            exception.printStackTrace();
            return str;
        }
    }

    public String generateSearchParam() {
        String str;
        Exception e;
        String str2 = "";
        try {
            Iterator it = this.mValueMap.keySet().iterator();
            while (it.hasNext()) {
                str = (String) it.next();
                ArrayList arrayList = (ArrayList) this.mValueMap.get(str);
                if (arrayList == null || arrayList.size() == 0) {
                    str = str2 + str + "=-1";
                } else {
                    str2 = str2 + str + "=";
                    int i = 0;
                    while (i < arrayList.size()) {
                        str = str2 + ((String) arrayList.get(i));
                        if (i != arrayList.size() - 1) {
                            str = str + ",";
                        }
                        i++;
                        str2 = str;
                    }
                    str = str2;
                }
                try {
                    if (it.hasNext()) {
                        str = str + "&";
                    }
                    str2 = str;
                } catch (Exception e2) {
                    e = e2;
                }
            }
            return str2;
        } catch (Exception e3) {
            e = e3;
            str = str2;
        }
        e.printStackTrace();
        return str;
    }

    private void initValueMap() {
        for (int i = 0; i < this.infoModel.b.b.size() && this.infoModel.b.b.size() == this.infoModel.b.c.size(); i++) {
            this.mValueMap.put((String) this.infoModel.b.c.get(i), new ArrayList(((Integer) this.infoModel.b.b.get(i)).intValue()));
        }
    }

    private void initInfoModel() {
        this.jsonData = this.args.getString("data");
        if (this.fileData == null) {
            try {
                JSONObject jSONObject = new JSONObject(this.jsonData);
                this.mTabName = jSONObject.optString("name");
                this.fileData = d.a(jSONObject.optString("file_name"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.infoModel == null) {
            this.infoModel = new c();
            this.infoModel.a(this.fileData);
        }
    }

    private void initGridView() {
        this.mDelegateAdapter = new b(this, this.infoModel);
        this.mGridView.setAdapter(this.mDelegateAdapter);
        this.mGridView.setNumColumns(3);
        this.mGridView.setAreHeadersSticky(false);
    }

    public void onPreLoad() {
    }

    public void onLoading() {
    }

    public void onLoadFinished() {
    }

    private void removeAllValueInSameColumn(c.c cVar) {
        try {
            ArrayList arrayList = (ArrayList) this.mValueMap.get(this.infoModel.b.c.get(cVar.c));
            if (arrayList != null) {
                arrayList.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isSingleSelected(c.c cVar) {
        try {
            if (((Integer) this.infoModel.b.b.get(cVar.c)).intValue() == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean itemIsChecked(c.c cVar) {
        try {
            ArrayList arrayList = (ArrayList) this.mValueMap.get(this.infoModel.b.c.get(cVar.c));
            if (arrayList != null) {
                for (int i = 0; i < cVar.b.size(); i++) {
                    if (arrayList.contains((String) cVar.b.get(i))) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void addCheckedItem(c.c cVar) {
        try {
            ArrayList arrayList = (ArrayList) this.mValueMap.get(getColumnName(cVar));
            if (arrayList != null) {
                for (int i = 0; i < cVar.b.size(); i++) {
                    arrayList.add(cVar.b.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean valuesContainInInfoModel(String str, String str2) {
        int i = 0;
        while (i < this.infoModel.a.size()) {
            try {
                c.c cVar = (c.c) this.infoModel.a.get(i);
                if (cVar.c == getSecIdByColumn(str) && cVar.b.contains(str2)) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void removeCheckedItem(c.c cVar) {
        try {
            ArrayList arrayList = (ArrayList) this.mValueMap.get(getColumnName(cVar));
            if (arrayList != null) {
                for (int i = 0; i < cVar.b.size(); i++) {
                    arrayList.remove(cVar.b.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean couldCheck(c.c cVar) {
        try {
            return ((ArrayList) this.mValueMap.get(getColumnName(cVar))).size() + 1 <= getMaxSelection(cVar);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private String getColumnName(c.c cVar) {
        try {
            return (String) this.infoModel.b.c.get(cVar.c);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getMaxSelection(c.c cVar) {
        try {
            return ((Integer) this.infoModel.b.b.get(cVar.c)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
