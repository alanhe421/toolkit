package com.qq.reader.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.liveshow.view.ReaderLiveLibCheckerActivity;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.CheckNetWorkActivity;
import com.qq.reader.activity.ClearCacheActivity;
import com.qq.reader.activity.DiscoveryCommentIndexActivity;
import com.qq.reader.activity.H5GameActivity;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.activity.NativeBookStoreSearchActivity;
import com.qq.reader.activity.NewLoginActivity;
import com.qq.reader.activity.ProfileAccountActivity;
import com.qq.reader.activity.ProfileLevelActivity;
import com.qq.reader.activity.QidianLoginActivity;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.activity.RookieGiftPageActivity;
import com.qq.reader.activity.UserCenterNewActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.activity.WebBrowserForFullScreenContents;
import com.qq.reader.activity.WebRadioBrowserForContents;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.b;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.charge.BookPayVipActivity;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.audio.activity.AudioPanicGiftsActivity;
import com.qq.reader.module.audio.activity.NativeAudioZoneActivity;
import com.qq.reader.module.bookchapter.online.OnlineChapterActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeAudioBookDetailActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeAudioBookPlayerActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeAuthorPageActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeAuthorWXBindActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBackRefreshTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigDetailActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigStackTabFragment;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreEndPageActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreRankBActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreSelectedCommentActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeLimitTimeDiscountBuyActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeLocalPopTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeNewTabTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeSearchResultsActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeSearchToolMainActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeSkinDetailActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeSkinManageActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeTTSPlayerActivity;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.y;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.fragment.RookieZoneFragment;
import com.qq.reader.module.bookstore.qweb.fragment.WebBrowserFragment;
import com.qq.reader.module.bookstore.search.AudioBookSearchParamCollection;
import com.qq.reader.module.bookstore.search.CommonBookSearchParamCollection;
import com.qq.reader.module.bookstore.search.SearchTabInfo.SearchActionTagLv3InitialDataModel;
import com.qq.reader.module.comic.activity.NativeBookLibraryActivity;
import com.qq.reader.module.comic.activity.NativeBookStoreComicDetailActivity;
import com.qq.reader.module.comic.activity.NativeBookStoreComicDownloadActivity;
import com.qq.reader.module.comic.activity.NativeBookStoreComicMainPageActivity;
import com.qq.reader.module.comic.activity.NativeBookStoreComicSectionPayActivity;
import com.qq.reader.module.comic.activity.NativeBookStoreComicWatchingFocusActivity;
import com.qq.reader.module.comic.activity.NativeComicFreeAreaActivity;
import com.qq.reader.module.comic.activity.NativeComicMonthlyAreaActivity;
import com.qq.reader.module.comic.entity.ComicShelfInfo;
import com.qq.reader.module.comic.entity.ComicWatchingFocusItem;
import com.qq.reader.module.comic.views.RectInfo;
import com.qq.reader.module.feed.activity.FamousAuthorActivity;
import com.qq.reader.module.feed.activity.FeedSearchOptionActivity;
import com.qq.reader.module.feed.mypreference.MyFeedPreferenceActivity;
import com.qq.reader.module.feed.mypreference.MyReadingGeneActivity;
import com.qq.reader.module.game.activity.NativeGameCenterMainActivity;
import com.qq.reader.module.question.activity.FamousAuthorSayActivity;
import com.qq.reader.module.question.activity.NativeAudioQuestionDetailActivity;
import com.qq.reader.module.question.activity.NativeAudioQuestionQuizActivity;
import com.qq.reader.module.question.activity.QAIntroductionEditActivity;
import com.qq.reader.module.redpacket.sendpacket.ReceiveRedPacketActivity;
import com.qq.reader.module.redpacket.sendpacket.RedpacketTypeSelectActivity;
import com.qq.reader.module.redpacket.singlebookpacket.RedPacketSingleBookActivity;
import com.qq.reader.module.redpacket.square.RedPacketSquareActivity;
import com.qq.reader.module.rookie.view.RookieGiftDialog;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.view.as;
import com.qq.reader.view.at;
import com.qq.reader.view.au;
import com.qq.reader.view.av;
import com.qq.reader.view.aw;
import com.tencent.av.sdk.AVAudioCtrl;
import com.tencent.feedback.proguard.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JumpActivityUtil */
public class o {
    public static String a(Context context, int i) {
        int i2;
        int aS = d.aS(context);
        if (aS == 0) {
            i2 = 3;
        } else {
            i2 = aS;
        }
        switch (i2) {
            case 1:
                switch (i) {
                    case 0:
                        return "BookLibCategory_boy";
                    case 1:
                        return "BookLibTopRank_boy";
                    case 2:
                        return "PayMonth_Boy";
                    case 3:
                        return "Limit_Free_Boy";
                    case 4:
                        return "Boutique_Zone_Boy";
                    case 5:
                        return "Special_Price_Boy";
                    case 6:
                        return "End_Book_Boy";
                    default:
                        return null;
                }
            case 2:
                switch (i) {
                    case 0:
                        return "BookLibCategory_girl";
                    case 1:
                        return "BookLibTopRank_girl";
                    case 2:
                        return "PayMonth_Girl";
                    case 3:
                        return "Limit_Free_Girl";
                    case 4:
                        return "Boutique_Zone_Girl";
                    case 5:
                        return "Special_Price_Girl";
                    case 6:
                        return "End_Book_Girl";
                    default:
                        return null;
                }
            case 3:
                switch (i) {
                    case 0:
                        return "BookLibCategory_publish";
                    case 1:
                        return "BookLibTopRank_publish";
                    case 2:
                        return "PayMonth_Publish";
                    case 3:
                        return "Limit_Free_Publish";
                    case 4:
                        return "Boutique_Zone_Publish";
                    case 5:
                        return "Publish_Special_Price";
                    case 6:
                        return "End_Book_Boy";
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

    public static void a(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, NewLoginActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void b(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent(activity, MainActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        intent.putExtra("fromjump", true);
        a(activity, intent, jumpActivityParameter);
    }

    public static void c(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("main_tab_tag", 0);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        intent.putExtra("fromjump", true);
        a(activity, intent, jumpActivityParameter);
    }

    public static void d(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("main_tab_tag", 1);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        intent.putExtra("fromjump", true);
        a(activity, intent, jumpActivityParameter);
    }

    public static void e(Activity activity, JumpActivityParameter jumpActivityParameter) {
        a(activity, jumpActivityParameter, null);
    }

    public static void a(Activity activity, JumpActivityParameter jumpActivityParameter, String str) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("main_tab_tag", 2);
        intent.putExtra(NativeBookStoreConfigStackTabFragment.INTETNT_CATEGORY_TYPE_KEY, str);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        intent.putExtra("fromjump", true);
        a(activity, intent, jumpActivityParameter);
    }

    public static void f(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("main_tab_tag", 3);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        intent.putExtra("fromjump", true);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, String str2, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            long longValue;
            try {
                longValue = Long.valueOf(str).longValue();
            } catch (Exception e) {
                longValue = 0;
            }
            if (longValue > 0) {
                Intent intent = new Intent();
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                intent.setClass(activity, NativeBookStoreConfigDetailActivity.class);
                intent.putExtra("KEY_JUMP_PAGENAME", "DetailPage");
                intent.putExtra("URL_BUILD_PERE_BOOK_ID", longValue);
                if (!TextUtils.isEmpty(str2)) {
                    intent.putExtra(s.STATPARAM_KEY, str2.toString());
                }
                c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                a(activity, intent, jumpActivityParameter);
            }
        }
    }

    public static void b(Activity activity, String str, String str2, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            long longValue;
            try {
                longValue = Long.valueOf(str).longValue();
            } catch (Exception e) {
                longValue = 0;
            }
            if (longValue > 0) {
                Intent intent = new Intent();
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                intent.setClass(activity, NativeAudioBookDetailActivity.class);
                intent.putExtra("KEY_JUMP_PAGENAME", "AudioBookDetailNormal");
                intent.putExtra("URL_BUILD_PERE_BOOK_ID", longValue);
                if (!TextUtils.isEmpty(str2)) {
                    intent.putExtra(s.STATPARAM_KEY, str2.toString());
                }
                c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                a(activity, intent, jumpActivityParameter);
            }
        }
    }

    public static void a(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str)) {
            str = "书评广场";
        }
        Intent intent = new Intent();
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        intent.putExtras(bundle);
        intent.setClass(activity, DiscoveryCommentIndexActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity) {
        if (activity != null) {
            new JSPay(activity).openVip();
        }
    }

    public static void a(Activity activity, int i, boolean z) {
        Intent intent = new Intent();
        intent.putExtra("open_type", 1);
        intent.putExtra("open_month", i);
        intent.putExtra("auto_pay", z);
        intent.setClass(activity, BookPayVipActivity.class);
        a(activity, intent, new JumpActivityParameter().a(20002));
    }

    public static void a(Activity activity, int i) {
        if (activity != null) {
            new JSPay(activity).startCharge(activity, i);
        }
    }

    public static void a(Activity activity, int i, String str) {
        if (activity != null) {
            new JSPay(activity).startCharge(activity, i, str);
        }
    }

    public static void a(Activity activity, long j, String str, JumpActivityParameter jumpActivityParameter) {
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "discovery_comment_detail");
        bundle.putLong("URL_BUILD_PERE_BOOK_ID", j);
        bundle.putString("PARA_TYPE_BOOK_NAME", str);
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        bundle.putInt("CTYPE", 4);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, Long l, String str, int i, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            Intent intent = new Intent();
            intent.putExtra("URL_BUILD_PERE_BOOK_ID", l);
            intent.putExtra("PARA_TYPE_BOOK_NAME", str);
            intent.putExtra("CTYPE", i);
            intent.putExtra("KEY_JUMP_PAGENAME", "bookclubmain");
            intent.putExtra("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubindex));
            intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
            c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            a(activity, intent, jumpActivityParameter);
        }
    }

    public static void a(Activity activity, long j, int i, String str, boolean z, boolean z2, boolean z3) {
        at atVar = new at(activity, j);
        atVar.a(i, str, z, z2, z3);
        if (atVar != null && !atVar.f()) {
            atVar.f_();
        }
    }

    public static as a(int i, Activity activity, long j, int i2, String str, boolean z) {
        as awVar;
        switch (i) {
            case 1:
                awVar = new aw(activity, j, i2, str, z);
                break;
            case 2:
                awVar = new av(activity, j, i2, str, z);
                break;
            default:
                awVar = new au(activity, j, i2, str, z);
                break;
        }
        if (awVar != null) {
            awVar.f_();
        }
        return awVar;
    }

    public static void a(Activity activity, Long l, JumpActivityParameter jumpActivityParameter) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("SHOWCOMMENTACTIVITY", true);
        bundle.putLong("URL_BUILD_PERE_BOOK_ID", l.longValue());
        bundle.putString("KEY_JUMP_PAGENAME", "bookclubmain");
        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubindex));
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void g(Activity activity, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            Intent intent = new Intent(activity, NativeSkinManageActivity.class);
            c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            a(activity, intent, jumpActivityParameter);
        }
    }

    public static void b(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        if (activity != null && str != null && str.length() != 0) {
            Intent intent = new Intent(activity, NativeSkinDetailActivity.class);
            intent.putExtra("com.qq.reader.WebContent", e.Y + "id=" + str);
            intent.putExtra("plugin_id", str);
            c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            a(activity, intent, jumpActivityParameter);
        }
    }

    public static void a(Activity activity, String str, String str2, String str3, String str4, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            Intent intent = new Intent(activity, NativeBookStoreSelectedCommentActivity.class);
            intent.putExtra("KEY_JUMP_PAGENAME", "selected_comment");
            intent.putExtra("TOPIC_ID", str);
            intent.putExtra(s.ALG, str4);
            intent.putExtra("itemid", str3);
            try {
                intent.putExtra("CTYPE", Integer.parseInt(str2));
            } catch (Exception e) {
                c.e("JumpActivity", e.getMessage());
            }
            intent.putExtra("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.selected_comments));
            c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            a(activity, intent, jumpActivityParameter);
        }
    }

    public static void a(Activity activity, Long l, String str, int i, String str2, int i2, int i3, boolean z, int i4, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            HashMap hashMap = (HashMap) jumpActivityParameter.a();
            Bundle bundle = new Bundle();
            bundle.putLong("URL_BUILD_PERE_BOOK_ID", l.longValue());
            bundle.putString("COMMENT_ID", str);
            bundle.putString("PARA_TYPE_COMMENT_UID", str2);
            bundle.putString("KEY_JUMP_PAGENAME", "bookclubreply");
            if (hashMap != null) {
                bundle.putString(s.ALG, (String) hashMap.get(s.ALG));
                bundle.putString("itemid", (String) hashMap.get("itemid"));
            }
            bundle.putInt("floor_index", i2);
            bundle.putInt("floor_next", i3);
            bundle.putInt("from", i4);
            bundle.putBoolean("lcoate", z);
            bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubdetail));
            bundle.putInt("CTYPE", i);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
            c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            a(activity, intent, jumpActivityParameter);
        }
    }

    public static void a(Activity activity, String str, String str2, int i, String str3, String str4, JumpActivityParameter jumpActivityParameter) {
        a(activity, str, str2, i, str3, str4, null, jumpActivityParameter);
    }

    public static void b(Activity activity, String str, String str2, String str3, String str4, JumpActivityParameter jumpActivityParameter) {
        a(activity, str, str2, -1, str3, str4, null, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, String str2, JumpActivityParameter jumpActivityParameter) {
        a(activity, str, str2, -1, null, null, null, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, String str2, String str3, ArrayList<SearchActionTagLv3InitialDataModel> arrayList, JumpActivityParameter jumpActivityParameter) {
        a(activity, str, str2, -1, null, str3, arrayList, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, String str2, int i, String str3, String str4, ArrayList<SearchActionTagLv3InitialDataModel> arrayList, JumpActivityParameter jumpActivityParameter) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("KEY_ACTIONTAG", str);
        } else if ("comicCat".equalsIgnoreCase(str3) || "audioCat".equalsIgnoreCase(str3)) {
            bundle.putString("KEY_ACTIONTAG", ",-1,-1,-1,-1,-1");
        } else {
            bundle.putString("KEY_ACTIONTAG", ",-1,-1,-1,-1,6");
        }
        if (TextUtils.isEmpty(str2)) {
            bundle.putString("KEY_ACTIONID", "0");
        } else {
            bundle.putString("KEY_ACTIONID", str2);
        }
        if (!TextUtils.isEmpty(str4)) {
            bundle.putString("LOCAL_STORE_IN_TITLE", str4);
        }
        if (i >= 0) {
            bundle.putInt("URL_BUILD_PERE_CLASSIFY_AREA", i);
        }
        if ("audioCat".equalsIgnoreCase(str3)) {
            bundle.putString("KEY_JUMP_PAGENAME", "Audio_Classify_List");
        } else {
            bundle.putString("KEY_JUMP_PAGENAME", "classify");
        }
        if (arrayList != null && arrayList.size() > 0) {
            bundle.putSerializable("PARAM_CLASSIFY_MUST_SELECTED_DATA", arrayList);
        }
        if ("comicCat".equalsIgnoreCase(str3) || "audioCat".equalsIgnoreCase(str3)) {
            bundle.putString("KEY_ACTION", str3);
        }
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void b(Activity activity, String str, String str2, JumpActivityParameter jumpActivityParameter) {
        int i = 3;
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        bundle.putString("KEY_JUMP_PAGENAME", a((Context) activity, 1));
        if (TextUtils.isEmpty(str)) {
            str = "排行榜";
        }
        Map hashMap = new HashMap();
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        if ("1".equals(str2) || "2".equals(str2) || "3".equals(str2) || "4".equals(str2) || "5".equals(str2)) {
            bundle.putString("URL_BUILD_PERE_RANK", str2);
            hashMap.put(s.ORIGIN, String.valueOf(Integer.valueOf(str2).intValue() - 1));
        } else {
            int aS = d.aS(activity);
            if (aS == 1 || aS == 2 || aS == 3) {
                i = aS;
            }
            hashMap.put(s.ORIGIN, String.valueOf(i - 1));
            bundle.putString("URL_BUILD_PERE_RANK", String.valueOf(i));
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(s.ORIGIN, "102424");
            bundle.putString(s.STATPARAM_KEY, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bundle.putString("ABTEST_PARAM", "1");
        intent.setClass(activity, NativeBookStoreRankBActivity.class);
        hashMap.put("defaultPre", bundle.getString("URL_BUILD_PERE_RANK"));
        hashMap.put("rankboard", "abtest_B");
        intent.putExtras(bundle);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
        i.a("event_B225", hashMap, activity);
    }

    public static void a(Activity activity, String str) {
        Intent intent = new Intent(activity, RedpacketTypeSelectActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        intent.putExtra("bid", str);
        a(activity, intent, null);
    }

    public static void a(Activity activity, long j, int i) {
        Intent intent = new Intent(activity, ReceiveRedPacketActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("rid", j);
        bundle.putInt("rtype", i);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

    public static void c(Activity activity, String str, String str2, JumpActivityParameter jumpActivityParameter) {
        String str3;
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        if ("1".equals(str2)) {
            str3 = "Single_rankboard_Boy";
        } else if ("2".equals(str2)) {
            str3 = "Single_rankboard_Girl";
        } else {
            str3 = "Single_rankboard_Pub";
        }
        bundle.putString("KEY_JUMP_PAGENAME", str3);
        if (TextUtils.isEmpty(str)) {
            str = "排行榜";
        }
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        if ("1".equals(str2) || "2".equals(str2) || "3".equals(str2)) {
            bundle.putString("URL_BUILD_PERE_RANK", str2);
        } else {
            int aS = d.aS(activity);
            if (!(aS == 1 || aS == 2 || aS == 3)) {
                aS = 3;
            }
            bundle.putString("URL_BUILD_PERE_RANK", String.valueOf(aS));
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(s.ORIGIN, "102424");
            bundle.putString(s.STATPARAM_KEY, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        intent.putExtras(bundle);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, String str2, String str3, JumpActivityParameter jumpActivityParameter) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str)) {
            str = "排行榜";
        }
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        bundle.putString("KEY_ACTION", "rank");
        if (TextUtils.isEmpty(str2)) {
            bundle.putString("KEY_ACTIONID", "0");
        } else {
            bundle.putString("KEY_ACTIONID", str2);
        }
        bundle.putString("KEY_ACTIONTAG", str3);
        bundle.putBoolean("PARA_TYPE_BOOLEAN", true);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void b(Activity activity, String str, String str2, String str3, JumpActivityParameter jumpActivityParameter) {
        int i = 3;
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str)) {
            str = "排行榜";
        }
        bundle.putString("KEY_JUMP_PAGENAME", a((Context) activity, 1));
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        bundle.putString("KEY_ACTION", "rank");
        if (TextUtils.isEmpty(str2)) {
            bundle.putString("KEY_ACTIONID", "0");
        } else {
            bundle.putString("KEY_ACTIONID", str2);
        }
        if (TextUtils.isEmpty(str3) || !("boy".equals(str3) || "girl".equals(str3) || "pub".equals(str3))) {
            int aS = d.aS(activity);
            if (aS == 1 || aS == 2 || aS == 3) {
                i = aS;
            }
            bundle.putString("URL_BUILD_PERE_RANK", String.valueOf(i));
        } else if ("boy".equals(str3)) {
            bundle.putString("URL_BUILD_PERE_RANK", String.valueOf(1));
        } else if ("girl".equals(str3)) {
            bundle.putString("URL_BUILD_PERE_RANK", String.valueOf(2));
        } else if ("pub".equals(str3)) {
            bundle.putString("URL_BUILD_PERE_RANK", String.valueOf(3));
        }
        bundle.putBoolean("PARA_TYPE_BOOLEAN", true);
        Intent intent = new Intent();
        bundle.putString("ABTEST_PARAM", "1");
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreRankBActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, int i, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Serializable arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put("book_url", e.d(ReaderApplication.getApplicationImp().getApplicationContext()));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("book_url", e.e(ReaderApplication.getApplicationImp().getApplicationContext()));
        arrayList.add(new TabInfo(WebBrowserFragment.class, "", "成长等级", hashMap));
        arrayList.add(new TabInfo(WebBrowserFragment.class, "", "VIP等级", hashMap2));
        Bundle bundle = new Bundle();
        bundle.putSerializable("tablist", arrayList);
        bundle.putString("title", "我的等级");
        if (i == 0) {
            bundle.putInt("select", 0);
        } else {
            bundle.putInt("select", 1);
        }
        intent.putExtra("info", bundle);
        intent.setClass(activity, ProfileLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, int i, int i2, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Serializable arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put("book_url", e.d(ReaderApplication.getApplicationImp().getApplicationContext()) + "&autoReceive=" + i2);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("book_url", e.e(ReaderApplication.getApplicationImp().getApplicationContext()));
        arrayList.add(new TabInfo(WebBrowserFragment.class, "", "成长等级", hashMap));
        arrayList.add(new TabInfo(WebBrowserFragment.class, "", "VIP等级", hashMap2));
        Bundle bundle = new Bundle();
        bundle.putSerializable("tablist", arrayList);
        bundle.putString("title", "我的等级");
        bundle.putInt("select", 0);
        intent.putExtra("info", bundle);
        intent.setClass(activity, ProfileLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void h(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, WebBrowserForContents.class);
        intent.putExtra("com.qq.reader.WebContent", e.aB + e.b((Context) activity));
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void i(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, WebBrowserForContents.class);
        intent.putExtra("com.qq.reader.WebContent", e.g(activity));
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void d(Activity activity, String str, String str2, JumpActivityParameter jumpActivityParameter) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str)) {
            str = "专题";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "0";
        }
        Intent intent = new Intent();
        bundle.putString("KEY_ACTIONTAG", str2);
        int aV = d.aV(activity);
        if (aV == 4 || aV == 1) {
            bundle.putString("KEY_ACTIONID", "1");
        } else if (aV == 5 || aV == 2) {
            bundle.putString("KEY_ACTIONID", "2");
        } else {
            bundle.putString("KEY_ACTIONID", "0");
        }
        bundle.putString("KEY_JUMP_PAGENAME", "topicpage");
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void c(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, WebBrowserForContents.class);
        intent.putExtra("com.qq.reader.WebContent", "/topicV2.html?tid=" + str);
        intent.putExtra("com.qq.reader.WebContent_collect", true);
        intent.putExtra("com.qq.reader.WebContent_share", true);
        intent.putExtra("com.qq.reader.Need_record_history", true);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, boolean z, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getString(R.string.audio_zone));
        bundle.putBoolean("need_start_main", z);
        bundle.putString("KEY_JUMP_PAGENAME", "Audio_Zone");
        intent.putExtras(bundle);
        intent.setClass(activity, NativeAudioZoneActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void j(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getString(R.string.listen_zone));
        bundle.putString("KEY_JUMP_PAGENAME", "Listen_Book");
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void k(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, AudioPanicGiftsActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, String str2, String str3, String str4, String str5, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("LOCAL_STORE_IN_TITLE", str5);
        bundle.putString("KEY_ACTIONID", str);
        bundle.putString("KEY_ACTIONTAG", str3);
        bundle.putString("KEY_ACTION", str2);
        bundle.putString("URL_BUILD_PERE_ACTION_FLAG", str4);
        bundle.putString("KEY_JUMP_PAGENAME", "Audio_Zone_More_Book_List");
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void l(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        String a = a((Context) activity, 2);
        bundle.putString("KEY_JUMP_PAGENAME", "common_pay_month");
        bundle.putString("KEY_ACTIONTAG", a);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(s.ORIGIN, "102437");
            bundle.putString(s.STATPARAM_KEY, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        intent.putExtras(bundle);
        intent.setClass(activity, NativeNewTabTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle2 = new Bundle();
        String a = a((Context) activity, 4);
        bundle2.putString("KEY_JUMP_PAGENAME", "common_boutique_zone");
        bundle2.putString("KEY_ACTIONTAG", a);
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                bundle2.putString(str, bundle.getString(str));
            }
        }
        Bundle bundle3 = new Bundle();
        bundle3.putString("Boutique_Zone_Boy", "10038");
        bundle3.putString("Boutique_Zone_Girl", "10042");
        bundle3.putString("Boutique_Zone_Publish", "10046");
        bundle2.putString("bidsincid", bundle3.getString(a));
        bundle2.putString("cidincate", "cids");
        intent.putExtras(bundle2);
        intent.setClass(activity, NativeNewTabTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void b(Activity activity, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle2 = new Bundle();
        String a = a((Context) activity, 3);
        bundle2.putString("KEY_JUMP_PAGENAME", "common_free_books");
        bundle2.putString("KEY_ACTIONTAG", a);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(s.ORIGIN, "102437");
            bundle2.putString(s.STATPARAM_KEY, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                bundle2.putString(str, bundle.getString(str));
            }
        }
        Bundle bundle3 = new Bundle();
        bundle3.putString("Limit_Free_Boy", "10368");
        bundle3.putString("Limit_Free_Girl", "10376");
        bundle3.putString("Limit_Free_Publish", "10362");
        bundle2.putString("bidsincid", bundle3.getString(a));
        bundle2.putString("cidincate", "lmtcids");
        intent.putExtras(bundle2);
        intent.setClass(activity, NativeNewTabTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, int i, JumpActivityParameter jumpActivityParameter) {
        b(activity, 1, i, jumpActivityParameter);
    }

    public static void b(Activity activity, int i, int i2, JumpActivityParameter jumpActivityParameter) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        bundle.putInt("LOCAL_STORE_IN_TAB_INDEX", i);
        bundle.putInt("CURRENT_ITEM", i2);
        bundle.putInt("KEY_PAGEINDEX", 0);
        intent.putExtras(bundle);
        intent.setClass(activity, FamousAuthorSayActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(a aVar, String str) {
        if (!TextUtils.isEmpty(str)) {
            y yVar = new y();
            yVar.a(str);
            yVar.a(aVar);
        }
    }

    public static void m(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtra("com.qq.reader.WebContent", e.Z);
        intent.setClass(activity, WebBrowserForContents.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void b(Activity activity, String str) {
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void n(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        bundle.putString("LOCAL_STORE_IN_TITLE", "清理缓存");
        intent.putExtras(bundle);
        intent.setClass(activity, ClearCacheActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, int i, int i2, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            Intent intent = null;
            Mark b = com.qq.reader.common.db.handle.i.c().b(str, false);
            if (b != null && b.getType() == 3) {
                intent = new Intent(activity, MainActivity.class);
            }
            Parcelable a = v.b().a(str);
            if (a != null && (a instanceof OnlineTag)) {
                intent = new Intent(activity, ReaderPageActivity.class);
                if (i > 0) {
                    a.c(i);
                    a.a(0);
                    if (i2 >= 0) {
                        a.a((long) i2);
                    }
                }
                a.a(false);
                intent.putExtra("com.qq.reader.OnlineTag.web.chapter", true);
                intent.putExtra("com.qq.reader.OnlineTag", a);
            }
            if (intent == null) {
                if (i < 1) {
                    i = 1;
                }
                if (i2 < 0) {
                    i2 = 0;
                }
                intent = new Intent(activity, ReaderPageActivity.class);
                intent.putExtra("book_chapterid", i);
                intent.putExtra("book_chapter_offset", i2);
            }
            intent.putExtra("com.qq.reader.fromonline", true);
            intent.putExtra("filepath", str);
            c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            a(activity, intent, jumpActivityParameter);
        }
    }

    public static void d(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_ACTION", "classic");
        bundle.putString("KEY_ACTIONID", d.aX(activity));
        bundle.putString("KEY_JUMP_PAGENAME", "WellChosenBookStore");
        if (TextUtils.isEmpty(str)) {
            str = "经典";
        }
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        i.a("event_F78", null, ReaderApplication.getApplicationImp());
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, boolean z, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        if (z) {
            intent.putExtra("com.qq.reader.WebContent_encode", str);
        } else {
            intent.putExtra("com.qq.reader.WebContent", str);
        }
        intent.setClass(activity, H5GameActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void o(Activity activity, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            a(activity, new Intent(activity, MyReadingGeneActivity.class), jumpActivityParameter);
        }
    }

    public static void b(Activity activity, boolean z, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            Intent intent = new Intent(activity, MyFeedPreferenceActivity.class);
            intent.putExtra("isRookie", z);
            a(activity, intent, jumpActivityParameter);
        }
    }

    public static void p(Activity activity, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            a(activity, new Intent(activity, ProfileAccountActivity.class), jumpActivityParameter);
        }
    }

    public static void e(Activity activity, String str, String str2, JumpActivityParameter jumpActivityParameter) {
        try {
            Intent intent = new Intent(activity, NativeBookStoreEndPageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putLong("KEY_PAGEINDEX", 0);
            bundle.putString("KEY_JUMP_PAGENAME", "bookclubreplylist");
            bundle.putString("LOCAL_STORE_IN_TITLE", "全部回复");
            bundle.putString("topiccomments_tid", str);
            bundle.putInt("CTYPE", Integer.parseInt(str2));
            bundle.putInt("function_type", 0);
            intent.putExtras(bundle);
            a(activity, intent, jumpActivityParameter);
        } catch (Exception e) {
            c.e("JumpActivityUtil.goTopicComment", e.getMessage());
        }
    }

    public static void f(Activity activity, String str, String str2, JumpActivityParameter jumpActivityParameter) {
        try {
            Intent intent = new Intent(activity, NativeBookStoreEndPageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putLong("KEY_PAGEINDEX", 0);
            bundle.putString("KEY_JUMP_PAGENAME", "bookclubdiscusslist");
            bundle.putString("LOCAL_STORE_IN_TITLE", "全部讨论");
            bundle.putString("topiccomments_tid", str);
            bundle.putInt("CTYPE", Integer.parseInt(str2));
            bundle.putInt("function_type", 0);
            intent.putExtras(bundle);
            a(activity, intent, jumpActivityParameter);
        } catch (Exception e) {
            c.e("JumpActivityUtil.goTopicDiscuss", e.getMessage());
        }
    }

    public static void a(Activity activity, String str, String str2, int i, int i2, JumpActivityParameter jumpActivityParameter) {
        try {
            Intent intent = new Intent(activity, NativeBookStoreEndPageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("KEY_JUMP_PAGENAME", "bookclubchapter");
            bundle.putInt("CTYPE", 0);
            bundle.putString("LOCAL_STORE_IN_TITLE", "章评详情");
            bundle.putString("PARA_TYPE_BOOK_TITLE", "章评详情");
            bundle.putLong("URL_BUILD_PERE_BOOK_ID", Long.valueOf(str).longValue());
            bundle.putInt("URL_BUILD_PERE_CHAPTER_ID", Integer.valueOf(str2).intValue());
            bundle.putBoolean("LOCAL_STORE_KEY_IS_FINISH", true);
            bundle.putInt("function_type", 0);
            bundle.putInt("floor_index", i);
            bundle.putInt("floor_next", i2);
            intent.putExtras(bundle);
            a(activity, intent, jumpActivityParameter);
        } catch (Exception e) {
            c.e("JumpActivityUtil.goTopicComment", e.getMessage());
        }
    }

    public static void q(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "searchToolMore");
        intent.putExtra("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getString(R.string.search_tool_more_title));
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        intent.putExtra("PARA_TYPE_SWIPE_REFRESH_ENABLE", true);
        intent.putExtra("NATIVE_LISTVIEW_DIVIDOR_RES", R.drawable.localstore_card_divider_line_drawable);
        intent.putExtra("NATIVE_LISTVIEW_DIVIDOR_HEIGHT", 1);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void r(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "SearchToolMain");
        intent.putExtra("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getString(R.string.search_tool));
        intent.setClass(activity, NativeSearchToolMainActivity.class);
        intent.putExtra("PARA_TYPE_SWIPE_REFRESH_ENABLE", false);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void s(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "HallOfFamePage");
        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getString(R.string.search_tool_option_title));
        intent.putExtras(bundle);
        intent.setClass(activity, FeedSearchOptionActivity.class);
        intent.putExtra("PARA_TYPE_SWIPE_REFRESH_ENABLE", true);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void c(Activity activity, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        bundle.putString("KEY_JUMP_PAGENAME", "HallOfFamePage");
        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getString(R.string.search_tool_option_title));
        intent.putExtras(bundle);
        intent.setClass(activity, FeedSearchOptionActivity.class);
        intent.putExtra("PARA_TYPE_SWIPE_REFRESH_ENABLE", true);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, String str2, boolean z, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "searchToolResult");
        intent.putExtra("search_option", str2);
        intent.putExtra("search_option_text", str);
        intent.putExtra("LOCAL_STORE_IN_TITLE", "搜索结果");
        intent.setClass(activity, NativeSearchResultsActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        intent.putExtra("PARA_TYPE_SWIPE_REFRESH_ENABLE", true);
        intent.putExtra("PARA_TYPE_BOOLEAN", z);
        intent.putExtra("search_result_order", 1);
        intent.putExtra("NATIVE_LISTVIEW_DIVIDOR_RES", R.drawable.feed_listview_divider);
        intent.putExtra("NATIVE_LISTVIEW_DIVIDOR_HEIGHT", activity.getResources().getDimensionPixelOffset(R.dimen.search__tool_result_divider_div_height));
        a(activity, intent, jumpActivityParameter);
    }

    public static void e(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        try {
            Intent intent = new Intent();
            intent.putExtra("filepath", str);
            intent.setClass(activity, NativeAudioBookPlayerActivity.class);
            intent.addFlags(SigType.WLOGIN_QRPUSH);
            a(activity, intent, jumpActivityParameter);
        } catch (Exception e) {
            c.e("JumpActivityUtil.goPlayerActivity", e.getMessage());
        }
    }

    public static void f(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Parcelable onlineTag = new OnlineTag(str, "", System.currentTimeMillis());
        onlineTag.c(1);
        onlineTag.j(2);
        onlineTag.k("mp3");
        intent.putExtra("com.qq.reader.OnlineTag", onlineTag);
        intent.setClass(activity, OnlineChapterActivity.class);
        a(activity, intent, jumpActivityParameter);
    }

    public static void b(Activity activity, String str, int i, JumpActivityParameter jumpActivityParameter) {
        a(activity, str, i, null, jumpActivityParameter);
    }

    public static void c(Activity activity, String str, String str2, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        try {
            Intent intent = new Intent();
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.putExtra("filepath", str);
            if (!TextUtils.isEmpty(str2)) {
                intent.putExtra("bookname", str2);
            }
            intent.setClass(activity, NativeAudioBookPlayerActivity.class);
            intent.addFlags(SigType.WLOGIN_QRPUSH);
            a(activity, intent, jumpActivityParameter);
        } catch (Exception e) {
            c.e("JumpActivityUtil.goPlayerActivity", e.getMessage());
        }
    }

    public static void a(Activity activity, String str, int i, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        try {
            Intent intent = new Intent();
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.putExtra("filepath", str);
            intent.putExtra("book_chapterid", i);
            intent.setClass(activity, NativeAudioBookPlayerActivity.class);
            intent.setFlags(SigType.WLOGIN_QRPUSH);
            a(activity, intent, jumpActivityParameter);
        } catch (Exception e) {
            c.e("JumpActivityUtil.goPlayerActivity", e.getMessage());
        }
    }

    public static void g(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "RecommendPage");
        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getString(R.string.detail_reader_read_more));
        bundle.putString("KEY_ACTIONID", str);
        bundle.putString("KEY_ACTION", "recommendbooks");
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void h(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, WebBrowserForContents.class);
        if (str.contains("topicV2.html")) {
            intent.putExtra("com.qq.reader.Need_record_history", true);
        }
        intent.putExtra("com.qq.reader.WebContent", str);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void i(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, WebBrowserForFullScreenContents.class);
        if (str.contains("topicV2.html")) {
            intent.putExtra("com.qq.reader.Need_record_history", true);
        }
        intent.putExtra("com.qq.reader.WebContent", str);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void c(Activity activity, String str) {
        b(activity, str, "0");
    }

    public static void d(Activity activity, String str) {
        a(activity, str, "0");
    }

    public static void a(Activity activity, String str, String str2) {
        a(activity, str, str2, "");
    }

    public static void a(Activity activity, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("searchkey", str);
        bundle.putString("searchhint", str3);
        bundle.putSerializable("searchParamSearchMode", new AudioBookSearchParamCollection());
        bundle.putString("from", str2);
        Intent intent = new Intent();
        intent.setClass(activity, NativeBookStoreSearchActivity.class);
        intent.putExtras(bundle);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, null);
    }

    public static void b(Activity activity, String str, String str2) {
        b(activity, str, str2, "");
    }

    public static void b(Activity activity, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("searchkey", str);
        bundle.putString("searchhint", str3);
        bundle.putSerializable("searchParamSearchMode", new CommonBookSearchParamCollection());
        bundle.putString("from", str2);
        Intent intent = new Intent();
        intent.setClass(activity, NativeBookStoreSearchActivity.class);
        intent.putExtras(bundle);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, null);
    }

    private static void a(Activity activity, Intent intent, JumpActivityParameter jumpActivityParameter) {
        if (jumpActivityParameter == null) {
            jumpActivityParameter = new JumpActivityParameter();
        }
        intent.setFlags(jumpActivityParameter.b());
        if (!TextUtils.isEmpty(jumpActivityParameter.d())) {
            intent.putExtra("URL_DATA_QURL", jumpActivityParameter.d());
        }
        if (jumpActivityParameter.c() != -1) {
            activity.startActivityForResult(intent, jumpActivityParameter.c());
        } else {
            activity.startActivity(intent);
        }
    }

    public static void t(Activity activity, JumpActivityParameter jumpActivityParameter) {
        a(activity, new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:com.qq.reader")), jumpActivityParameter);
    }

    public static void c(Activity activity, String str, String str2, String str3, String str4, JumpActivityParameter jumpActivityParameter) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str4)) {
            bundle.putString("KEY_ACTIONTAG", ",-1,-1,-1,-1,6");
        } else {
            bundle.putString("KEY_ACTIONTAG", str4);
        }
        if (TextUtils.isEmpty(str3)) {
            bundle.putString("KEY_ACTIONID", "0");
        } else {
            bundle.putString("KEY_ACTIONID", str3);
        }
        bundle.putString("KEY_JUMP_PAGENAME", "search_label");
        if ("comicCat".equalsIgnoreCase(str2) || "comicTag".equalsIgnoreCase(str2)) {
            bundle.putString("KEY_ACTION", str2);
        }
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("LOCAL_STORE_IN_TITLE", str);
        }
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void c(Activity activity, String str, int i, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "publisher_and_author");
        intent.putExtra("searchkey", str);
        intent.putExtra("needDirect", 0);
        intent.putExtra("searchType", i);
        intent.putExtra("LOCAL_STORE_IN_TITLE", str);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void c(Activity activity, String str, String str2, String str3, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "GoodWriter_MainPage");
        intent.putExtra("AUTHORPAGE_KEY_AUTHORID", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("AUTHORPAGE_KEY_AUTHOR_NAME", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            intent.putExtra("AUTHORPAGE_KEY_AVATAR_URL", str3);
        }
        if (b.l) {
            intent.putExtra("NATIVE_BG_COLOR_Resource", R.color.concept_divider_bg);
        }
        intent.setClass(activity, NativeAuthorPageActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void j(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "UserCenterPage");
        intent.putExtra("userId", str);
        intent.setClass(activity, UserCenterNewActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void d(Activity activity, String str, String str2, String str3, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "UserCenterPage");
        intent.putExtra("userId", str);
        intent.putExtra("userNickName", str2);
        intent.putExtra("userIconUrl", str3);
        intent.setClass(activity, UserCenterNewActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(a aVar, String str, String str2) {
        Bundle bundle;
        if (!TextUtils.isEmpty(str) && !"0".equals(str)) {
            bundle = new Bundle();
            bundle.putString("KEY_ACTION", "sameauthorallbooks");
            bundle.putString("KEY_ACTIONID", str);
            bundle.putString("LOCAL_STORE_IN_TITLE", "全部作品");
            new com.qq.reader.module.bookstore.qnative.c(bundle).a(aVar);
        } else if (!TextUtils.isEmpty(str2) && !"0".equals(str2)) {
            bundle = new Bundle();
            bundle.putString("KEY_ACTION", "sameauthorbooks");
            bundle.putString("KEY_ACTIONID", str2);
            bundle.putString("LOCAL_STORE_IN_TITLE", "全部作品");
            new com.qq.reader.module.bookstore.qnative.c(bundle).a(aVar);
        }
    }

    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent(context, NativeBookStoreTwoLevelActivity.class);
        Bundle bundle;
        if (!TextUtils.isEmpty(str) && !"0".equals(str)) {
            bundle = new Bundle();
            bundle.putString("KEY_ACTION", "sameauthorallbooks");
            bundle.putString("KEY_ACTIONID", str);
            bundle.putString("LOCAL_STORE_IN_TITLE", "全部作品");
            intent.putExtras(bundle);
        } else if (!(TextUtils.isEmpty(str2) || "0".equals(str2))) {
            bundle = new Bundle();
            bundle.putString("KEY_ACTION", "sameauthorbooks");
            bundle.putString("KEY_ACTIONID", str2);
            bundle.putString("LOCAL_STORE_IN_TITLE", "全部作品");
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void c(Activity activity, String str, String str2, String str3) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "user_qa_page");
        bundle.putString("LOCAL_STORE_IN_TITLE", str3);
        bundle.putString("URL_BUILD_PERE_UID", str);
        bundle.putString("URL_BUILD_PERE_QA_TYPE", String.valueOf(str2));
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, null);
    }

    public static void g(Activity activity, String str, String str2, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent(activity, NativeBookStoreTwoLevelActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", str);
        bundle.putString("LOCAL_STORE_IN_TITLE", str2);
        intent.putExtras(bundle);
        a(activity, intent, jumpActivityParameter);
    }

    public static void d(Activity activity, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent(activity, NativeBookStoreTwoLevelActivity.class);
        intent.putExtras(bundle);
        a(activity, intent, jumpActivityParameter);
    }

    public static void b(Activity activity, int i, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("PAGE_STATUS_INIT_KEY", i);
        bundle.putString("KEY_JUMP_PAGENAME", "author_question_answer");
        bundle.putString("LOCAL_STORE_IN_TITLE", "我的问答主页");
        bundle.putBoolean("GO_TWOLEVEL_ACT_USE_CACHE", false);
        bundle.putInt(NativeBackRefreshTwoLevelActivity.k, 1007);
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBackRefreshTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void e(Activity activity, String str) {
        a(activity, str, false);
    }

    public static void a(Activity activity, String str, boolean z) {
        a(activity, str, z, 2, 20);
    }

    public static void a(Activity activity, String str, boolean z, int i, int i2) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("audio_questionid", str);
        bundle.putString("KEY_JUMP_PAGENAME", "user_qa_detail");
        bundle.putString("LOCAL_STORE_IN_TITLE", "回答详情");
        bundle.putInt("audio_auto_play", z ? 1 : 0);
        bundle.putInt("floor_index", i);
        bundle.putInt("floor_next", i2);
        intent.putExtras(bundle);
        intent.setClass(activity, NativeAudioQuestionDetailActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        JumpActivityParameter jumpActivityParameter = new JumpActivityParameter();
        jumpActivityParameter.a(1006);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, long j) {
        a(activity, j, null);
    }

    public static void a(Activity activity, long j, String str) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putLong("audio_authorid", j);
        bundle.putString("KEY_JUMP_PAGENAME", "user_qa_quiz");
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        intent.putExtras(bundle);
        intent.setClass(activity, NativeAudioQuestionQuizActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, null);
    }

    public static void a(Activity activity, String str, int i, String str2, int i2, String str3) {
        Intent intent = new Intent(activity, QAIntroductionEditActivity.class);
        intent.putExtra("userIcon", str);
        intent.putExtra("userLevel", i);
        intent.putExtra("userIntro", str2);
        intent.putExtra("price", i2);
        intent.putExtra("authorId", str3);
        JumpActivityParameter jumpActivityParameter = new JumpActivityParameter();
        jumpActivityParameter.a(1004);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, boolean z, String str, String str2, String str3) {
        a(activity, z, str, str, str2, str3);
    }

    public static void a(Activity activity, boolean z, String str, String str2, String str3, String str4) {
        if (z) {
            try {
                com.qq.reader.qurl.c.a(activity, String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{str2, str4, str3}), null);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        d(activity, str, str4, str3, null);
    }

    public static void u(Activity activity, JumpActivityParameter jumpActivityParameter) {
        a(activity, e.cM, false, jumpActivityParameter);
    }

    public static void v(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "my_game");
        bundle.putString("LOCAL_STORE_IN_TITLE", "我的游戏");
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void w(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent(activity, NativeGameCenterMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "native_game_center");
        intent.putExtras(bundle);
        a(activity, intent, jumpActivityParameter);
    }

    public static void b(Activity activity, JumpActivityParameter jumpActivityParameter, String str) {
        Intent intent = new Intent(activity, NativeBookStoreTwoLevelActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "native_game_column");
        bundle.putString("CID", str);
        intent.putExtras(bundle);
        a(activity, intent, jumpActivityParameter);
    }

    public static void c(Activity activity, JumpActivityParameter jumpActivityParameter, String str) {
        Intent intent = new Intent(activity, NativeBookStoreTwoLevelActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "native_game_category");
        bundle.putString("KEY_ACTIONID", "10001");
        bundle.putString("KEY_ACTION", "game");
        bundle.putString("KEY_ACTIONTAG", str);
        intent.putExtras(bundle);
        a(activity, intent, jumpActivityParameter);
    }

    public static void x(Activity activity, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            a(activity, new Intent(activity, CheckNetWorkActivity.class), jumpActivityParameter);
        }
    }

    public static void a(Activity activity, String str, JumpActivityParameter jumpActivityParameter, String str2) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("pushTime", str2);
        }
        bundle.putString("KEY_JUMP_PAGENAME", "community_chosen_content");
        if (TextUtils.isEmpty(str)) {
            str = "社区精选";
        }
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void b(Activity activity, int i) {
        if (i != 0) {
            com.liveshow.a.a().a(activity);
            com.liveshow.a.a().a(activity, i);
        }
    }

    public static void c(Activity activity, int i) {
        Intent intent = new Intent(activity, ReaderLiveLibCheckerActivity.class);
        intent.putExtra("room", i);
        a(activity, intent, null);
    }

    public static void f(Activity activity, String str) {
        Intent intent = new Intent();
        intent.setClass(activity, NativeAuthorWXBindActivity.class);
        intent.putExtra("AUTHORPAGE_KEY_AUTHORID", str);
        intent.putExtra("KEY_JUMP_PAGENAME", "Author_wx_info_bind");
        intent.putExtra("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.wx_charge));
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        JumpActivityParameter jumpActivityParameter = new JumpActivityParameter();
        jumpActivityParameter.a(1007);
        a(activity, intent, jumpActivityParameter);
    }

    public static void g(Activity activity, String str) {
        Intent intent = new Intent();
        intent.setClass(activity, NativeNewTabTwoLevelActivity.class);
        intent.putExtra("AUTHORPAGE_KEY_AUTHORID", str);
        intent.putExtra("KEY_ACTIONTAG", "0");
        intent.putExtra("KEY_JUMP_PAGENAME", "Author_qa_income_list");
        intent.putExtra("NATIVE_LISTVIEW_DIVIDOR_RES", R.drawable.localstore_linecolor_divider);
        intent.putExtra("NATIVE_LISTVIEW_DIVIDOR_HEIGHT", 1);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, null);
    }

    public static void y(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtras(new Bundle());
        intent.setClass(activity, QidianLoginActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void c(Activity activity, int i, int i2, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (i2 < 1 || i2 > 2) {
            i2 = 1;
        }
        bundle.putString("KEY_JUMP_PAGENAME", "Red_packet_rank_list");
        bundle.putString("KEY_ACTIONTAG", String.valueOf(i2));
        bundle.putInt("RANK_TYPE", i);
        bundle.putInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_BG_RES_ID", R.drawable.red_packet_activity_title_bar_bg);
        bundle.putInt("NATIVE_LISTVIEW_PULLDOWN_SCHEME_COLOR", ReaderApplication.getApplicationImp().getResources().getColor(R.color.red_packet_pulldown_scheme_color));
        bundle.putInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_SELECTED_TEXTCOLOR", ReaderApplication.getApplicationImp().getResources().getColor(R.color.red_packet_activity_selected_textcolor));
        bundle.putInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_UNSELECTED_TEXTCOLOR", ReaderApplication.getApplicationImp().getResources().getColor(R.color.red_packet_activity_unselected_textcolor));
        bundle.putInt("NEW_TAB_TWO_LEVEL_LOADING_DRAWABLE_RES_ID", R.drawable.red_packet_progress_loading);
        bundle.putInt("NATIVE_FRAGMENT_LOADING_PROGRESSBAR_INDETEMINATE_DRAWABLE_RES_ID", R.drawable.red_packet_progress_loading);
        intent.putExtras(bundle);
        intent.setClass(activity, NativeNewTabTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void z(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "My_red_packet_list");
        bundle.putString("KEY_ACTIONTAG", "received");
        bundle.putInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_BG_RES_ID", R.drawable.red_packet_activity_title_bar_bg);
        bundle.putInt("NATIVE_LISTVIEW_PULLDOWN_SCHEME_COLOR", ReaderApplication.getApplicationImp().getResources().getColor(R.color.red_packet_pulldown_scheme_color));
        bundle.putInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_SELECTED_TEXTCOLOR", ReaderApplication.getApplicationImp().getResources().getColor(R.color.red_packet_activity_selected_textcolor));
        bundle.putInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_UNSELECTED_TEXTCOLOR", ReaderApplication.getApplicationImp().getResources().getColor(R.color.red_packet_activity_unselected_textcolor));
        bundle.putInt("NEW_TAB_TWO_LEVEL_LOADING_DRAWABLE_RES_ID", R.drawable.red_packet_progress_loading);
        bundle.putInt("NATIVE_LISTVIEW_FOOTER_LOADING_PROGRESSBAR_INDETEMINATE_DRAWABLE_RES_ID", R.drawable.red_packet_progress_loading);
        bundle.putInt("NATIVE_FRAGMENT_LOADING_PROGRESSBAR_INDETEMINATE_DRAWABLE_RES_ID", R.drawable.red_packet_progress_loading);
        intent.putExtras(bundle);
        intent.setClass(activity, NativeNewTabTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void A(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtras(new Bundle());
        intent.setClass(activity, RedPacketSquareActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, String str, long j, long j2, boolean z, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.putExtras(new Bundle());
        intent.putExtra("bookName", str);
        intent.putExtra("bookId", j);
        intent.putExtra("cbookId", j2);
        intent.putExtra("isFromReadPage", z);
        intent.setClass(activity, RedPacketSingleBookActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, int i, JumpActivityParameter jumpActivityParameter, String str) {
        c(activity, i, jumpActivityParameter);
        if (!TextUtils.isEmpty(str)) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, str);
            i.a("event_A265", hashMap, ReaderApplication.getApplicationImp());
        }
    }

    public static void c(Activity activity, int i, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Serializable arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        String str = "?qimei=" + d.h(activity) + "&timi=" + d.z(ReaderApplication.getApplicationImp()) + "&skey=" + com.qq.reader.common.login.c.c().a(ReaderApplication.getApplicationImp());
        hashMap.put("book_url", e.da + str);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("book_url", e.db + str);
        arrayList.add(new TabInfo(RookieZoneFragment.class, "", "专享福利", hashMap));
        arrayList.add(new TabInfo(RookieZoneFragment.class, "", "每日推荐", hashMap2));
        Bundle bundle = new Bundle();
        bundle.putSerializable("tablist", arrayList);
        bundle.putString("title", "新手专区");
        if (i == 0) {
            bundle.putInt("select", 0);
        } else {
            bundle.putInt("select", 1);
        }
        intent.putExtra("info", bundle);
        intent.setClass(activity, ProfileLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void B(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "obtain_welfare");
        bundle.putString("LOCAL_STORE_IN_TITLE", "领福利");
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void C(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "author_time_line");
        intent.putExtras(bundle);
        intent.setClass(activity, FamousAuthorActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        i.a("event_F65", null, ReaderApplication.getApplicationImp());
        a(activity, intent, jumpActivityParameter);
    }

    public static void D(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "virtual_recommend_page");
        bundle.putString("KEY_ACTIONTAG", "editorrec");
        intent.putExtras(bundle);
        intent.setClass(activity, NativeNewTabTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
        Map hashMap = new HashMap();
        hashMap.put("sex", d.aS(ReaderApplication.getApplicationImp()) + "");
        i.a("event_F140", hashMap, ReaderApplication.getApplicationImp());
    }

    public static void a(Activity activity, String str, long j) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "virtual_recommend_three_level_page");
        bundle.putString("LOCAL_STORE_IN_TITLE", str);
        bundle.putLong("_id", j);
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, null);
    }

    public static void b(Activity activity, long j, String str, JumpActivityParameter jumpActivityParameter) {
        if (activity != null) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("KEY_JUMP_PAGENAME", "surrounding_all");
            bundle.putLong("URL_BUILD_PERE_BOOK_ID", j);
            bundle.putString("LOCAL_STORE_IN_TITLE", str);
            intent.putExtras(bundle);
            intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
            c.a(R.anim.slide_in_right, R.anim.slide_out_left);
            a(activity, intent, jumpActivityParameter);
        }
    }

    public static void E(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "voucher_detail");
        bundle.putBoolean("GO_TWOLEVEL_ACT_USE_CACHE", false);
        bundle.putString("LOCAL_STORE_IN_TITLE", "抵扣券详情");
        intent.putExtras(bundle);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void d(Activity activity, String str, String str2, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle2 = new Bundle();
        bundle2.putString("KEY_JUMP_PAGENAME", str);
        bundle2.putString("LOCAL_STORE_IN_TITLE", str2);
        if (bundle != null) {
            for (String str3 : bundle.keySet()) {
                bundle2.putString(str3, bundle.getString(str3));
            }
        }
        intent.putExtras(bundle2);
        intent.setClass(activity, NativeBookStoreTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void e(Activity activity, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        Map hashMap = new HashMap();
        hashMap.put("sex", d.aS(ReaderApplication.getApplicationImp()) + "");
        i.a("event_F84", hashMap, ReaderApplication.getApplicationImp());
        Intent intent = new Intent();
        Bundle bundle2 = new Bundle();
        String a = a((Context) activity, 5);
        bundle2.putString("KEY_JUMP_PAGENAME", a);
        ArrayList arrayList = new ArrayList();
        arrayList.add("Special_Price_Boy");
        arrayList.add("Special_Price_Girl");
        arrayList.add("Publish_Special_Price");
        bundle2.putStringArrayList("pagelist", arrayList);
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                bundle2.putString(str, bundle.getString(str));
            }
        }
        Object bundle3 = new Bundle();
        bundle3.putString("Special_Price_Boy", ReaderApplication.getApplicationImp().getString(R.string.special_offer_boy));
        bundle3.putString("Special_Price_Girl", ReaderApplication.getApplicationImp().getString(R.string.special_offer_girl));
        bundle3.putString("Publish_Special_Price", ReaderApplication.getApplicationImp().getString(R.string.special_offer_publish));
        Bundle bundle4 = new Bundle();
        bundle4.putString("Special_Price_Boy", "10348");
        bundle4.putString("Special_Price_Girl", "10355");
        bundle4.putString("Publish_Special_Price", "10343");
        bundle2.putParcelable("titleInfo", bundle3);
        bundle2.putString("LOCAL_STORE_IN_TITLE", bundle3.getString(a));
        bundle2.putString("bidsincid", bundle4.getString(a));
        bundle2.putString("cidincate", "lmtcids");
        intent.putExtras(bundle2);
        intent.setClass(activity, NativeLocalPopTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void f(Activity activity, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle2 = new Bundle();
        String a = a((Context) activity, 6);
        bundle2.putString("KEY_JUMP_PAGENAME", "common_finish_books");
        bundle2.putString("KEY_ACTIONTAG", a);
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                bundle2.putString(str, bundle.getString(str));
            }
        }
        Bundle bundle3 = new Bundle();
        bundle3.putString("End_Book_Boy", "10331");
        bundle3.putString("End_Book_Girl", "10337");
        bundle2.putString("bidsincid", bundle3.getString(a));
        bundle2.putString("cidincate", "cids");
        intent.putExtras(bundle2);
        intent.setClass(activity, NativeNewTabTwoLevelActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void h(Activity activity, String str, String str2, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "Limit_time_discount_buy");
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("BUNDLE_PARAM_LIMIT_TIME_DISCOUNT_BUY_START_TIME", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("bids", str2);
        }
        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.limit_time_discount_buy_title));
        intent.putExtras(bundle);
        intent.setClass(activity, NativeLimitTimeDiscountBuyActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void b(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, MyFeedPreferenceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("fromFeedAction", true);
        bundle.putString("PREFERENCE_ACTIVITY_EXTRA_BTN_SAVE_TEXT", ReaderApplication.getApplicationImp().getResources().getString(R.string.book_list_preference_change_btn_text));
        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.book_list_preference_change_title));
        bundle.putString("newuser", "1");
        bundle.putBoolean("shouldHideOtherSexGene", true);
        i.a("event_F191", null, ReaderApplication.getApplicationImp());
        bundle.putString("PREFERENCE_ACTIVITY_EXTRA_NEXT_QURL", "uniteqqreader://nativepage/infostream/individualbooklist?fromgene=1&needGeneInfo=0");
        intent.putExtras(bundle);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        activity.startActivity(intent);
    }

    public static void a(Activity activity, boolean z) {
        Intent intent = new Intent();
        intent.setClass(activity, MyFeedPreferenceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("PREFERENCE_ACTIVITY_EXTRA_BTN_SAVE_TEXT", ReaderApplication.getApplicationImp().getResources().getString(R.string.book_list_preference_change_btn_text));
        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.book_list_preference_change_title));
        bundle.putString("newuser", "1");
        bundle.putBoolean("shouldHideOtherSexGene", true);
        i.a("event_F191", null, ReaderApplication.getApplicationImp());
        if (z) {
            bundle.putString("PREFERENCE_ACTIVITY_EXTRA_NEXT_QURL", "uniteqqreader://nativepage/infostream/individualbooklist?needGeneInfo=0");
        }
        intent.putExtras(bundle);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        activity.startActivity(intent);
    }

    public static void k(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, WebRadioBrowserForContents.class);
        intent.putExtra("popmenu", str);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void c(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, RookieGiftPageActivity.class);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, null);
    }

    public static void d(Activity activity, int i) {
        new RookieGiftDialog(activity, i).b();
    }

    public static void a(Activity activity, Bundle bundle, int i, int i2, ComicShelfInfo comicShelfInfo) {
        Intent intent = new Intent(activity, NativeBookStoreComicSectionPayActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("key_pay_comic_shelf_info", comicShelfInfo);
        JumpActivityParameter jumpActivityParameter = new JumpActivityParameter();
        jumpActivityParameter.a(i);
        a(activity, intent, jumpActivityParameter);
        if (i2 == 1) {
            activity.overridePendingTransition(R.anim.slide_in_right, 0);
        } else {
            activity.overridePendingTransition(R.anim.slide_in_bottom, 0);
        }
    }

    public static void g(Activity activity, Bundle bundle, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, NativeBookStoreComicMainPageActivity.class);
        bundle.putString("KEY_JUMP_PAGENAME", "page_name_comic_main");
        bundle.putInt("comic_main_type", 0);
        intent.putExtras(bundle);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, ComicShelfInfo comicShelfInfo, int i, JumpActivityParameter jumpActivityParameter, boolean z) {
        Intent intent = new Intent();
        if (z) {
            intent.putExtra("REQUEST_CODE", 4101);
            intent.putExtra("RESPONSE_CODE", AVAudioCtrl.AUDIO_CODEC_TYPE_SILK);
            if (jumpActivityParameter == null) {
                jumpActivityParameter = new JumpActivityParameter();
                jumpActivityParameter.a(4101);
            }
        }
        intent.setClass(activity, NativeBookStoreComicDownloadActivity.class);
        intent.putExtra("KEY_COMIC_SHELF_INFO", comicShelfInfo);
        intent.putExtra("KEY_COMIC_ID", comicShelfInfo.a());
        intent.putExtra("KEY_SECTION_ID", i);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void l(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        b(activity, str, jumpActivityParameter, "5");
    }

    public static void b(Activity activity, String str, JumpActivityParameter jumpActivityParameter, String str2) {
        Intent intent = new Intent();
        intent.setClass(activity, NativeBookStoreComicDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_COMIC_ID", str);
        bundle.putString("KEY_COMIC_NAME", "书籍详情");
        bundle.putString("KEY_COMIC_ORIGIN", str2);
        intent.putExtra("KEY_JUMP_PAGENAME", "DetailComicPage");
        intent.putExtras(bundle);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, List<ComicWatchingFocusItem> list, int i, RectInfo rectInfo) {
        NativeBookStoreComicWatchingFocusActivity.a(activity, list, i, rectInfo);
        c.a(R.anim.alpha_in, R.anim.alpha_out);
    }

    public static void i(Activity activity, String str, String str2, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent(activity, NativeBookStoreTwoLevelActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "page_name_comic_update");
        bundle.putString("KEY_ACTIONID", str);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("KEY_ACTIONTAG", str2);
        }
        intent.putExtra("NATIVE_LISTVIEW_FOOTVIEW_BG_COLOR", ReaderApplication.getApplicationImp().getResources().getColor(R.color.concept_divider_bg));
        intent.putExtras(bundle);
        a(activity, intent, jumpActivityParameter);
    }

    public static void m(Activity activity, String str, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent(activity, NativeBookStoreTwoLevelActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "page_name_comic_column");
        bundle.putString("KEY_ACTIONID", str);
        bundle.putString("columnId", str);
        intent.putExtras(bundle);
        intent.putExtra("NATIVE_LISTVIEW_FOOTVIEW_BG_COLOR", ReaderApplication.getApplicationImp().getResources().getColor(R.color.concept_divider_bg));
        a(activity, intent, jumpActivityParameter);
    }

    public static void F(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, NativeComicFreeAreaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "page_name_comic_main");
        bundle.putInt("comic_main_type", 1);
        intent.putExtras(bundle);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void G(Activity activity, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, NativeComicMonthlyAreaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_JUMP_PAGENAME", "page_name_comic_main");
        bundle.putInt("comic_main_type", 2);
        intent.putExtras(bundle);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        a(activity, intent, jumpActivityParameter);
    }

    public static void h(Activity activity, String str) {
        NativeBookLibraryActivity.a(activity, str);
    }

    public static void a(Context context, String str, String str2, int i, String str3, boolean z) {
        com.qq.reader.module.comic.a.a().a(context, str, str2, i, str3, z, 0);
    }

    public static void d(Activity activity, String str, int i, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, NativeTTSPlayerActivity.class);
        intent.putExtra("bookrealid", str);
        intent.putExtra("book_chapterid", i);
        c.a(R.anim.slide_in_top, 0);
        a(activity, intent, jumpActivityParameter);
    }

    public static void a(Activity activity, OnlineTag onlineTag, JumpActivityParameter jumpActivityParameter) {
        Intent intent = new Intent();
        intent.setClass(activity, NativeTTSPlayerActivity.class);
        if (onlineTag != null) {
            intent.putExtra("com.qq.reader.OnlineTag", onlineTag);
            intent.putExtra("bookrealid", onlineTag.k());
        }
        c.a(R.anim.slide_in_top, 0);
        a(activity, intent, jumpActivityParameter);
    }
}
