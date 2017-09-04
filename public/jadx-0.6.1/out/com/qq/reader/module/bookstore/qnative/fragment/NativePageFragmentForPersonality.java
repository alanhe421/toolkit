package com.qq.reader.module.bookstore.qnative.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.request.b.g;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.c.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.f;
import com.qq.reader.common.utils.o;
import com.qq.reader.liveshow.utils.m;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.card.impl.PersonalityBooksListBookcard.b;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.page.impl.au;
import com.qq.reader.view.RoundImageView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class NativePageFragmentForPersonality extends NativePageFragmentforOther {
    private static final int MIN_PHOTO_NUM = 12;
    private final ArrayList<Drawable> mDrawableList = new ArrayList();
    private View mHeaderView;
    private View mTitleBarView;
    private ImageView mTopImageView;
    private int unitImageNum;

    protected void init(View view) {
        super.init(view);
        this.mTitleBarView = ((NativeBookStoreTwoLevelActivity) getBaseActivity()).n();
        this.mTopImageView = ((NativeBookStoreTwoLevelActivity) getBaseActivity()).o();
        this.mTopImageView.setImageDrawable(new ColorDrawable(getResources().getColor(R.color.personal_books_photo_foreground)));
    }

    protected void onListViewInitialized() {
        if (this.mTopImageView != null) {
            this.mTopImageView.setVisibility(0);
        }
        addHeaderView();
        setOnScrollListener();
    }

    private void addHeaderView() {
        if (this.mHeaderView == null) {
            this.mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.column_time_headerview, null);
        }
        if (this.mXListView.getHeaderViewsCount() == 0) {
            this.mXListView.addHeaderView(this.mHeaderView);
        }
    }

    private void setOnScrollListener() {
        this.mXListView.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NativePageFragmentForPersonality a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                int color = this.a.getResources().getColor(R.color.skin_set_bookdetail_title_bar_startcolor);
                int color2 = this.a.getResources().getColor(R.color.skin_set_bookdetail_title_bar_endcolor);
                if (i == 0) {
                    if (Math.min(((double) Math.abs(this.a.mHeaderView.getTop())) / ((double) this.a.mHeaderView.getHeight()), 1.0d) == 1.0d) {
                        this.a.mTitleBarView.setBackgroundColor(color2);
                    } else {
                        this.a.mTitleBarView.setBackgroundColor(color);
                    }
                } else if (((ColorDrawable) this.a.mTitleBarView.getBackground()).getColor() == color) {
                    this.a.mTitleBarView.setBackgroundColor(color2);
                }
            }
        });
    }

    protected void onDataInitialized() {
        super.onDataInitialized();
        setDataForHeader();
        setTopImage();
    }

    public void loadDataByDelete() {
        if (this.mHoldPage != null) {
            this.mHoldPage.a(1000);
            d.b().a(getApplicationContext(), this.mHoldPage, this.mHandler, false);
            showLoadingPage();
            if (this.emptyView != null) {
                this.emptyView.setVisibility(8);
            }
        }
    }

    private void setDataForHeader() {
        if ((this.mHoldPage instanceof au) && this.mXListView != null && this.mHeaderView != null) {
            TextView textView = (TextView) this.mHeaderView.findViewById(R.id.tv_personal_books_day);
            TextView textView2 = (TextView) this.mHeaderView.findViewById(R.id.tv_personal_books_month);
            TextView textView3 = (TextView) this.mHeaderView.findViewById(R.id.tv_update_time_tips);
            TextView textView4 = (TextView) this.mHeaderView.findViewById(R.id.tv_personal_books_description);
            ImageView imageView = (RoundImageView) this.mHeaderView.findViewById(R.id.iv_personal_books_user_head);
            textView3.setMaxWidth((int) (((float) a.bU) - (2.0f * (getResources().getDimension(R.dimen.common_dp_17) + getResources().getDimension(R.dimen.common_left_right_margin)))));
            textView.setText(((au) this.mHoldPage).A());
            textView2.setText(((au) this.mHoldPage).z());
            textView3.setText(((au) this.mHoldPage).y());
            textView4.setText(((au) this.mHoldPage).x());
            if (c.b()) {
                imageView.setVisibility(0);
                com.qq.reader.common.imageloader.c.a((Fragment) this).a(c.c().b(), imageView, com.qq.reader.common.imageloader.a.a().b());
                return;
            }
            imageView.setVisibility(8);
        }
    }

    private void setTopImage() {
        int count = (this.mXListView.getCount() - this.mXListView.getHeaderViewsCount()) - this.mXListView.getFooterViewsCount();
        this.mDrawableList.clear();
        final g imageLoadingListener = getImageLoadingListener();
        this.unitImageNum = Math.min(count, 12);
        for (int headerViewsCount = this.mXListView.getHeaderViewsCount(); headerViewsCount < this.unitImageNum + this.mXListView.getHeaderViewsCount(); headerViewsCount++) {
            com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(((b) this.mXListView.getAdapter().getItem(headerViewsCount)).e()), new g<com.bumptech.glide.load.resource.a.b>(this) {
                final /* synthetic */ NativePageFragmentForPersonality b;

                public void a(com.bumptech.glide.load.resource.a.b bVar, com.bumptech.glide.request.a.c cVar) {
                    imageLoadingListener.a((Object) bVar, cVar);
                }
            }, com.qq.reader.common.imageloader.a.a().j());
        }
    }

    private g<com.bumptech.glide.load.resource.a.b> getImageLoadingListener() {
        return new g<com.bumptech.glide.load.resource.a.b>(this) {
            final /* synthetic */ NativePageFragmentForPersonality a;

            {
                this.a = r1;
            }

            public void a(com.bumptech.glide.load.resource.a.b bVar, com.bumptech.glide.request.a.c cVar) {
                if (bVar instanceof j) {
                    Bitmap b = ((j) bVar).b();
                    if (this.a.getActivity() != null && this.a.isAdded()) {
                        if (this.a.mDrawableList.size() < this.a.unitImageNum - 1) {
                            this.a.mDrawableList.add(new BitmapDrawable(this.a.getResources(), b));
                        } else if (this.a.mDrawableList.size() == this.a.unitImageNum - 1) {
                            this.a.mDrawableList.add(new BitmapDrawable(this.a.getResources(), b));
                            int i = a.bU;
                            int a = m.a(this.a.getActivity(), 164.0f);
                            this.a.mTopImageView.setBackgroundDrawable(new BitmapDrawable(f.a(this.a.mDrawableList, m.a(this.a.getActivity(), 68.0f), m.a(this.a.getActivity(), 91.0f), i, a)));
                        }
                    }
                }
            }
        };
    }

    public void reRefresh() {
        if (this.mPullDownView != null && this.mHoldPage != null) {
            this.mHoldPage.a(1001);
            tryObtainDataWithNet(true, true);
            configCanPullDownRefresh(false);
        }
    }

    public void notifyData() {
        if (((au) this.mHoldPage).B()) {
            ((ReaderBaseActivity) getActivity()).disableUseAnimation();
            o.b(getActivity());
            getActivity().finish();
            return;
        }
        super.notifyData();
    }
}
