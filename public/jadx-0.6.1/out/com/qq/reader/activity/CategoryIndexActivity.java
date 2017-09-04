package com.qq.reader.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.module.bookshelf.a.a.c;
import com.qq.reader.module.bookshelf.a.a.d;
import com.qq.reader.module.bookshelf.a.a.f;
import com.qq.reader.view.AlertDialog.a;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.a.b;
import com.qq.reader.view.linearmenu.e;
import com.qq.reader.view.metro.MetroItem;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Iterator;

public class CategoryIndexActivity extends ReaderBaseActivity implements OnItemClickListener, OnItemLongClickListener {
    private ListView a = null;
    private d b = null;
    private c c = null;
    private final int d = 309;
    private final int e = 310;
    private final int f = 10;
    private final int g = 11;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void a() {
        setContentView(R.layout.category_index_list);
        ((TextView) findViewById(R.id.profile_header_title)).setText(R.string.category_index_titler);
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CategoryIndexActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.a = (ListView) findViewById(R.id.category_index_list);
        this.a.setOnItemClickListener(this);
        this.a.setOnItemLongClickListener(this);
        c();
        this.c = new c(this, this.mHandler);
        this.b = new d(getApplicationContext(), this.mHandler, b());
        this.a.setAdapter(this.b);
    }

    private ArrayList<MetroItem> b() {
        ArrayList c = this.c.c();
        ArrayList<MetroItem> arrayList = new ArrayList();
        arrayList.add(new MetroItem(10001, "书城正版", this.c.a()));
        arrayList.add(new MetroItem(Constants.CODE_LOGIC_REGISTER_IN_PROCESS, "本地导入", this.c.b()));
        Iterator it = c.iterator();
        while (it.hasNext()) {
            MetroItem metroItem = (MetroItem) it.next();
            if (!(metroItem.getDisplayName().equals(BookShelfFragment.CATEGORY_ALL) || metroItem.getDisplayName().equals("在线"))) {
                arrayList.add(new MetroItem(metroItem.getId(), metroItem.getDisplayName(), this.c.a(metroItem.getId())));
            }
        }
        return arrayList;
    }

    private void c() {
        if (this.a != null && this.a.getFooterViewsCount() == 0) {
            RelativeLayout relativeLayout = (RelativeLayout) ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.category_list_footer, null);
            TextView textView = (TextView) relativeLayout.findViewById(R.id.category_index_list_footer_btn);
            textView.setText(R.string.build_new_category);
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CategoryIndexActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.showFragmentDialog(309);
                    j.a(14, 0);
                }
            });
            this.a.addFooterView(relativeLayout);
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.a.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount < this.b.getCount()) {
            MetroItem metroItem = (MetroItem) this.b.getItem(headerViewsCount);
            if (metroItem != null) {
                int id = metroItem.getId();
                if (id == 10001 || id == Constants.CODE_LOGIC_REGISTER_IN_PROCESS || id == i.b) {
                    af.a(getApplicationContext(), (CharSequence) "系统默认分组不支持修改", 0).a();
                } else {
                    e eVar = new e(this);
                    CharSequence spannableStringBuilder = new SpannableStringBuilder();
                    spannableStringBuilder.append(metroItem.getName() + "(共" + metroItem.getCount() + "本图书)");
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(-7829368), metroItem.getName().length(), spannableStringBuilder.length(), 18);
                    spannableStringBuilder.setSpan(new AbsoluteSizeSpan(11, true), metroItem.getName().length(), spannableStringBuilder.length(), 18);
                    eVar.a(spannableStringBuilder);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(String.valueOf(10), metroItem);
                    eVar.a(10, getResources().getString(R.string.manager_catgory_one_rename), bundle);
                    bundle = new Bundle();
                    bundle.putSerializable(String.valueOf(11), metroItem);
                    eVar.a(11, getResources().getString(R.string.manager_catgory_one_remove), bundle);
                    eVar.a(new b(this) {
                        final /* synthetic */ CategoryIndexActivity a;

                        {
                            this.a = r1;
                        }

                        public boolean a(int i, Bundle bundle) {
                            j.a(15, 0);
                            return this.a.a(i, bundle);
                        }
                    });
                    eVar.f_();
                }
            }
        }
        return true;
    }

    private boolean a(int i, Bundle bundle) {
        switch (i) {
            case 10:
                showFragmentDialog(310, bundle);
                return true;
            case 11:
                a(bundle).f_();
                return true;
            default:
                return false;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.a.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount < this.b.getCount()) {
            MetroItem metroItem = (MetroItem) this.b.getItem(headerViewsCount);
            if (metroItem != null) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), CategoryBooksActivity.class);
                com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                intent.putExtra("category_books_mode", 10101);
                intent.putExtra("category_id", metroItem.getId());
                intent.putExtra("category_name", metroItem.getName());
                startActivity(intent);
            }
        }
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        Dialog a;
        switch (i) {
            case 309:
                View inflate = LayoutInflater.from(this).inflate(R.layout.category_dialog_add_or_rename, null);
                final EditText editText = (EditText) inflate.findViewById(R.id.category_add);
                a = new a(this).a((int) R.string.txt_catgory_tile_add).a(inflate).a((int) R.string.button_catgory_next, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ CategoryIndexActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        String trim = editText.getText().toString().trim();
                        dialogInterface.dismiss();
                        if (!this.b.c.a(trim)) {
                        }
                    }
                }).b((int) R.string.button_catgory_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ CategoryIndexActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                a.a(false);
                a.getWindow().setSoftInputMode(16);
                break;
            case 310:
                final MetroItem metroItem = (MetroItem) bundle.get(String.valueOf(10));
                View inflate2 = LayoutInflater.from(this).inflate(R.layout.category_dialog_add_or_rename, null);
                final EditText editText2 = (EditText) inflate2.findViewById(R.id.category_add);
                editText2.setText(metroItem.getDisplayName());
                editText2.setSelection(0, metroItem.getName().length());
                a = new a(this).a((int) R.string.txt_catgory_tile_rename).a(inflate2).a((int) R.string.button_catgory_commit, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ CategoryIndexActivity c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.c.c.a(metroItem.getDisplayName(), editText2.getText().toString().trim());
                        dialogInterface.dismiss();
                    }
                }).b((int) R.string.button_catgory_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ CategoryIndexActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                a.getWindow().setSoftInputMode(16);
                break;
            default:
                a = null;
                break;
        }
        return a != null ? a : super.createDialog(i, bundle);
    }

    protected void onResume() {
        super.onResume();
        this.b.a(b());
        this.b.notifyDataSetInvalidated();
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 20001:
                af.a(getApplicationContext(), (String) message.obj, 0).a();
                return true;
            case 20002:
                this.b.a(b());
                this.b.notifyDataSetInvalidated();
                return true;
            case 20005:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), CategoryBooksActivity.class);
                com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                intent.putExtra("category_books_mode", 10102);
                intent.putExtra("category_id", i.a);
                intent.putExtra("category_name", BookShelfFragment.CATEGORY_ALL);
                startActivity(intent);
                return true;
            case 20006:
                this.b.a(b());
                this.b.notifyDataSetInvalidated();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    private f a(Bundle bundle) {
        f fVar = new f(this, (MetroItem) bundle.get(String.valueOf(11)));
        fVar.a(new f.a(this) {
            final /* synthetic */ CategoryIndexActivity a;

            {
                this.a = r1;
            }

            public void a(MetroItem metroItem) {
                this.a.c.b(metroItem.getName());
                j.a(67, 0);
            }
        });
        return fVar;
    }
}
