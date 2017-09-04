package com.qq.reader.module.bookstore.qnative;

import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.module.audio.c.c;
import com.qq.reader.module.bookstore.qnative.activity.NativeNewTabTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.NativeServerLimitTimeDiscountBuyPage;
import com.qq.reader.module.bookstore.qnative.page.impl.NativeServerTabPageOfEditorRecommend;
import com.qq.reader.module.bookstore.qnative.page.impl.NativeServerTodayReadPage;
import com.qq.reader.module.bookstore.qnative.page.impl.aa;
import com.qq.reader.module.bookstore.qnative.page.impl.ab;
import com.qq.reader.module.bookstore.qnative.page.impl.ac;
import com.qq.reader.module.bookstore.qnative.page.impl.ad;
import com.qq.reader.module.bookstore.qnative.page.impl.ae;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.bookstore.qnative.page.impl.ag;
import com.qq.reader.module.bookstore.qnative.page.impl.ai;
import com.qq.reader.module.bookstore.qnative.page.impl.aj;
import com.qq.reader.module.bookstore.qnative.page.impl.ak;
import com.qq.reader.module.bookstore.qnative.page.impl.al;
import com.qq.reader.module.bookstore.qnative.page.impl.an;
import com.qq.reader.module.bookstore.qnative.page.impl.ao;
import com.qq.reader.module.bookstore.qnative.page.impl.ap;
import com.qq.reader.module.bookstore.qnative.page.impl.aq;
import com.qq.reader.module.bookstore.qnative.page.impl.ar;
import com.qq.reader.module.bookstore.qnative.page.impl.as;
import com.qq.reader.module.bookstore.qnative.page.impl.at;
import com.qq.reader.module.bookstore.qnative.page.impl.au;
import com.qq.reader.module.bookstore.qnative.page.impl.av;
import com.qq.reader.module.bookstore.qnative.page.impl.aw;
import com.qq.reader.module.bookstore.qnative.page.impl.ax;
import com.qq.reader.module.bookstore.qnative.page.impl.ay;
import com.qq.reader.module.bookstore.qnative.page.impl.az;
import com.qq.reader.module.bookstore.qnative.page.impl.ba;
import com.qq.reader.module.bookstore.qnative.page.impl.bb;
import com.qq.reader.module.bookstore.qnative.page.impl.bc;
import com.qq.reader.module.bookstore.qnative.page.impl.bd;
import com.qq.reader.module.bookstore.qnative.page.impl.d;
import com.qq.reader.module.bookstore.qnative.page.impl.g;
import com.qq.reader.module.bookstore.qnative.page.impl.h;
import com.qq.reader.module.bookstore.qnative.page.impl.i;
import com.qq.reader.module.bookstore.qnative.page.impl.j;
import com.qq.reader.module.bookstore.qnative.page.impl.l;
import com.qq.reader.module.bookstore.qnative.page.impl.m;
import com.qq.reader.module.bookstore.qnative.page.impl.n;
import com.qq.reader.module.bookstore.qnative.page.impl.o;
import com.qq.reader.module.bookstore.qnative.page.impl.p;
import com.qq.reader.module.bookstore.qnative.page.impl.q;
import com.qq.reader.module.bookstore.qnative.page.impl.r;
import com.qq.reader.module.bookstore.qnative.page.impl.t;
import com.qq.reader.module.bookstore.qnative.page.impl.u;
import com.qq.reader.module.bookstore.qnative.page.impl.w;
import com.qq.reader.module.bookstore.qnative.page.impl.x;
import com.qq.reader.module.bookstore.qnative.page.impl.y;
import com.qq.reader.module.bookstore.qnative.page.impl.z;
import com.qq.reader.module.feed.data.impl.f;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PageManager */
public class e {
    private static e a;
    private final Map<String, b> b = new HashMap();
    private JSONObject c;
    private Set<String> d = new HashSet();
    private boolean e = true;

    public static e a() {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    a = new e();
                }
            }
        }
        return a;
    }

    private e() {
        c();
    }

    public b a(Bundle bundle, a aVar) {
        String a;
        String string = bundle.getString("KEY_JUMP_PAGENAME");
        bundle.getString("ABTEST_PARAM");
        CharSequence string2 = bundle.getString("KEY_ACTIONTAG");
        if (string == null || string.length() == 0) {
            a = c.a(bundle.getString("KEY_ACTION"));
        } else {
            a = string;
        }
        String string3 = bundle.getString(s.STATPARAM_KEY);
        b bVar = null;
        if ("Feed_FirstPage".equals(a)) {
            bVar = new f(bundle);
            bVar.b(aVar);
        } else if ("My_red_packet_list".equals(a)) {
            bVar = new com.qq.reader.module.redpacket.a.a(bundle);
            bVar.b(aVar);
        } else if ("Red_packet_rank_list".equals(a)) {
            bVar = new com.qq.reader.module.redpacket.a.b(bundle);
            bVar.b(aVar);
        } else if ("Author_qa_income_list".equals(a)) {
            bVar = new t(bundle);
            bVar.b(aVar);
        } else if ("Author_wx_info_bind".equals(a)) {
            bVar = new u(bundle);
            bVar.b(aVar);
        } else if ("discovery_comment_detail".equals(a)) {
            bVar = new ac(bundle);
            bVar.b(aVar);
        } else if ("PAGE_NAME_AUDIO_CATEGORY_LIST_PAGE".equals(a)) {
            bVar = new r(bundle);
            bVar.b(aVar);
        } else if ("user_center_subscribed_authors".equals(a)) {
            bVar = new ar(bundle);
            bVar.b(aVar);
        } else if ("userAllComment".equals(a)) {
            bVar = new q(bundle);
            bVar.b(aVar);
        } else if ("authorAllNews".equals(a)) {
            bVar = new com.qq.reader.module.bookstore.qnative.page.impl.s(bundle);
            bVar.b(aVar);
        } else if ("PAGE_NAME_DISCOUNT_BOOK_LIST".equals(a)) {
            bVar = new ab(bundle);
            bVar.b(aVar);
        } else if ("PAGE_NAME_REAL_TIME_REC_FREE_BOOK_LIST".equals(a)) {
            bVar = new ax(bundle);
            bVar.b(aVar);
        } else if ("selected_comment".equals(a)) {
            bVar = new ba(bundle);
            bVar.b(aVar);
        } else if ("myfocus".equals(a)) {
            bVar = new ae(bundle);
            bVar.b(aVar);
        } else if ("searchToolResult".equals(a)) {
            bVar = new az(bundle);
            bVar.b(aVar);
        } else if ("searchToolMore".equals(a)) {
            bVar = new ay(bundle);
            bVar.b(aVar);
        } else if ("topicpage".equals(a)) {
            bVar = new ad(bundle);
            bVar.b(aVar);
        } else if ("myfocus".equals(a)) {
            bVar = new ae(bundle);
            bVar.b(aVar);
        } else if ("classify".equals(a)) {
            bVar = new ag(bundle);
            bVar.b(aVar);
        } else if ("Audio_Classify_List".equals(a)) {
            bVar = new com.qq.reader.module.audio.c.a(bundle);
            bVar.b(aVar);
        } else if ("search_label".equals(a)) {
            bVar = new ak(bundle);
            bVar.b(aVar);
        } else if ("WellChosenBookStore".equals(a)) {
            bVar = new d(bundle);
            bVar.b(aVar);
        } else if ("Audio_Zone".equals(a)) {
            bVar = new c(bundle);
            bVar.b(aVar);
        } else if ("Audio_Zone_More_Book_List".equals(a)) {
            bVar = new com.qq.reader.module.audio.c.d(bundle);
            bVar.b(aVar);
        } else if ("BookLibCategory_boy".equals(a) || "BookLibCategory_girl".equals(a) || "BookLibCategory_publish".equals(a) || "BookLibCategory_comic".equals(a) || "BookLibCategory_audio".equals(a)) {
            bVar = new aq(bundle);
            bVar.b(aVar);
        } else if ("bookclubmain".equals(a)) {
            bVar = new x(bundle);
            bVar.b(aVar);
        } else if ("bookclubchapter".equals(a)) {
            bVar = new al(bundle);
            bVar.b(aVar);
        } else if ("bookclubreplylist".equals(a)) {
            bVar = new at(bundle);
            bVar.b(aVar);
        } else if ("bookclubdiscusslist".equals(a)) {
            bVar = new at(bundle);
            bVar.b(aVar);
        } else if ("bookclubhot".equals(a)) {
            bVar = new w(bundle);
            bVar.b(aVar);
        } else if ("bookclubreward".equals(a)) {
            bVar = new y(bundle);
            bVar.b(aVar);
        } else if ("bookclubreply".equals(a)) {
            bVar = new ao(bundle);
            bVar.b(aVar);
        } else if ("search".equals(a)) {
            bVar = new ap(bundle);
            bVar.b(aVar);
        } else if ("charge_bookcoin".equals(a)) {
            bVar = new com.qq.reader.module.bookstore.charge.c(bundle);
            bVar.b(aVar);
        } else if ("charge_openvip".equals(a)) {
            bVar = new com.qq.reader.module.bookstore.charge.d(bundle);
            bVar.b(aVar);
        } else if ("Personality_books".equals(a)) {
            bVar = new au(bundle);
            bVar.b(aVar);
        } else if ("Limit_time_discount_buy".equalsIgnoreCase(a)) {
            bVar = new NativeServerLimitTimeDiscountBuyPage(bundle);
            bVar.b(aVar);
        } else if ("today_read".equals(a)) {
            bVar = new NativeServerTodayReadPage(bundle);
            bVar.b(aVar);
        } else if ("publisher_and_author".equals(a)) {
            bVar = new an(bundle);
            bVar.b(aVar);
        } else if ("GoodWriter_MainPage".equals(a)) {
            if (b()) {
                b bVar2;
                r0 = (JSONArray) this.c.opt(a);
                if (r0 != null) {
                    bVar = new com.qq.reader.module.bookstore.qnative.page.impl.b(bundle, a);
                    bVar.a(r0.toString());
                    bVar.b(aVar);
                    bVar2 = bVar;
                } else {
                    bVar2 = null;
                }
                bVar = bVar2;
            }
        } else if ("UserCenterPage".equals(a)) {
            if (b()) {
                r0 = (JSONArray) this.c.opt(a);
                if (r0 != null) {
                    bVar = new n(bundle, a);
                    bVar.a(r0.toString());
                    bVar.b(aVar);
                }
            }
        } else if ("SearchToolMain".equals(a)) {
            if (b()) {
                r0 = (JSONArray) this.c.opt(a);
                if (r0 != null) {
                    bVar = new l(bundle, a);
                    bVar.a(r0.toString());
                    bVar.b(aVar);
                }
            }
        } else if ("DetailPage".equals(a)) {
            r6 = bundle.getLong("URL_BUILD_PERE_BOOK_ID");
            if (b()) {
                r0 = (JSONArray) this.c.opt(a);
                if (r0 != null) {
                    bVar = new com.qq.reader.module.bookstore.qnative.page.impl.f(bundle, a);
                    ((com.qq.reader.module.bookstore.qnative.page.impl.f) bVar).b(r6);
                    bVar.a(r0.toString());
                    bVar.b(aVar);
                }
            }
        } else if ("AudioBookDetailNormal".equals(a)) {
            r6 = bundle.getLong("URL_BUILD_PERE_BOOK_ID");
            if (b()) {
                r0 = (JSONArray) this.c.opt(a);
                if (r0 != null) {
                    bVar = new com.qq.reader.module.bookstore.qnative.page.impl.a(bundle, a);
                    ((com.qq.reader.module.bookstore.qnative.page.impl.a) bVar).c(String.valueOf(r6));
                    bVar.a(r0.toString());
                    bVar.b(aVar);
                }
            }
        } else if ("EndPage".equals(a)) {
            r6 = bundle.getLong("URL_BUILD_PERE_BOOK_ID");
            boolean z = bundle.getBoolean("LOCAL_STORE_KEY_IS_FINISH");
            if (b()) {
                r0 = (JSONArray) this.c.opt(a);
                if (r0 != null) {
                    bVar = new g(bundle, a);
                    ((g) bVar).b(r6);
                    ((g) bVar).a(z);
                    bVar.a(r0.toString());
                    bVar.b(aVar);
                }
            }
        } else if ("Find_HomePage".equals(a)) {
            bVar = new aj(bundle);
            bVar.b(aVar);
        } else if ("HallOfFamePage".equals(a)) {
            if (b()) {
                r0 = (JSONArray) this.c.opt(a);
                if (r0 != null) {
                    bVar = new i(bundle);
                    bVar.a(r0.toString());
                    bVar.b(aVar);
                }
            }
        } else if ("BookLibTopRank_boy".equals(a) || "BookLibTopRank_girl".equals(a) || "BookLibTopRank_publish".equals(a)) {
            bVar = new av(bundle);
            bVar.b(aVar);
        } else if ("BookLibTopRank_audio".equals(a)) {
            bVar = new com.qq.reader.module.audio.c.b(bundle);
            bVar.b(aVar);
        } else if ("Single_rankboard_Pub".equals(a) || "Single_rankboard_Boy".equals(a) || "Single_rankboard_Girl".equals(a)) {
            if (b()) {
                r0 = (JSONArray) this.c.opt(a);
                if (r0 != null) {
                    bVar = new m(bundle, a);
                    bVar.a(r0.toString());
                    bVar.b(aVar);
                }
            }
        } else if (a == null || a.length() <= 0 || !(this.d.contains(a) || a.equals("common_finish_books") || a.equals("common_boutique_zone") || a.equals("common_free_books") || a.equals("common_pay_month"))) {
            if ("adv_list".equals(a)) {
                bVar = new p(bundle);
                bVar.b(aVar);
            } else if ("feed_column_list_a".equals(a)) {
                bVar = new z(bundle);
                bVar.b(aVar);
            } else if ("feed_column_list_b".equals(a)) {
                bVar = new aa(bundle);
                bVar.b(aVar);
            } else if ("user_center_more_book".equals(a)) {
                bVar = new bb(bundle);
                bVar.b(aVar);
            } else if ("user_center_more_comment".equals(a)) {
                bVar = new bc(bundle);
                bVar.b(aVar);
            } else if ("user_center_more_interaction".equals(a)) {
                bVar = new bd(bundle);
                bVar.b(aVar);
            } else if ("author_question_answer".equals(a)) {
                bVar = new com.qq.reader.module.question.a.d(bundle);
                bVar.b(aVar);
            } else if ("user_qa_page".equals(a)) {
                bVar = new com.qq.reader.module.question.a.e(bundle);
                bVar.b(aVar);
            } else if ("user_qa_quiz".equals(a)) {
                bVar = new com.qq.reader.module.question.a.c(bundle);
                bVar.b(aVar);
            } else if ("user_qa_detail".equals(a)) {
                bVar = new com.qq.reader.module.question.a.b(bundle);
                bVar.b(aVar);
            } else if ("my_game".equals(a)) {
                bVar = new com.qq.reader.module.game.b.d(bundle);
                bVar.b(aVar);
            } else if ("native_game_center".equals(a)) {
                bVar = new com.qq.reader.module.game.b.c(bundle);
                bVar.b(aVar);
            } else if ("native_game_column".equals(a)) {
                bVar = new com.qq.reader.module.game.b.b(bundle);
                bVar.b(aVar);
            } else if ("native_game_category".equals(a)) {
                bVar = new com.qq.reader.module.game.b.a(bundle);
                bVar.b(aVar);
            } else if ("community_chosen_content".equals(a)) {
                bVar = new com.qq.reader.module.bookstore.qnative.page.impl.e(bundle);
                bVar.b(aVar);
            } else if ("rankboard_detail".equals(a)) {
                bVar = new aw(bundle);
                bVar.b(aVar);
            } else if ("obtain_welfare".equals(a)) {
                bVar = new j(bundle);
                bVar.b(aVar);
            } else if ("author_time_line".equals(a)) {
                bVar = new com.qq.reader.module.bookstore.qnative.page.impl.c(bundle);
                bVar.b(aVar);
            } else if ("virtual_recommend_page".equals(a)) {
                bVar = new NativeServerTabPageOfEditorRecommend(bundle);
                bVar.b(aVar);
            } else if ("virtual_recommend_three_level_page".equals(a)) {
                bVar = new ai(bundle);
                bVar.b(aVar);
            } else if ("voucher_detail".equals(a)) {
                bVar = new o(bundle);
                bVar.b(aVar);
            } else if ("page_name_comic_main".equals(a)) {
                bVar = new com.qq.reader.module.comic.d.a(bundle);
                bVar.b(aVar);
            } else if ("DetailComicPage".equals(a)) {
                bVar = new com.qq.reader.module.comic.d.c(bundle);
                bVar.b(aVar);
            } else if ("page_name_comic_update".equals(a)) {
                bVar = new com.qq.reader.module.comic.d.d(bundle);
                bVar.b(aVar);
            } else if ("page_name_comic_column".equals(a)) {
                bVar = new com.qq.reader.module.comic.d.b(bundle);
                bVar.b(aVar);
            } else if ("surrounding_all".equals(a)) {
                bVar = new as(bundle);
                bVar.b(aVar);
            } else {
                bVar = new af(bundle);
                bVar.b(aVar);
            }
        } else if (b()) {
            if (null == null) {
                if ((a.equals("common_finish_books") || a.equals("common_boutique_zone") || a.equals("common_free_books") || a.equals("common_pay_month")) && !TextUtils.isEmpty(string2)) {
                    if (aVar instanceof NativePageFragmentforOther) {
                        a = string2;
                        aVar = (NativeNewTabTwoLevelActivity) aVar.getFromActivity();
                    } else {
                        CharSequence charSequence = string2;
                    }
                }
                r0 = (JSONArray) this.c.opt(a);
                if (r0 != null) {
                    bVar = new h(bundle, a);
                    bVar.a(r0.toString());
                }
            }
            bVar.b(aVar);
        }
        if (bVar != null) {
            bVar.b(string3);
        }
        return bVar;
    }

    private boolean b() {
        if (this.c != null && this.c.length() != 0) {
            return true;
        }
        com.qq.reader.common.monitor.f.a("LocalStoreCore", "ERROR: please init jsonBookStoreidMaps First!");
        return false;
    }

    private void c() {
        if (ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().density <= 1.5f) {
            this.e = false;
        } else {
            this.e = true;
        }
        if (this.c == null || this.c.length() == 0) {
            InputStream inputStream = null;
            try {
                inputStream = ReaderApplication.getApplicationImp().getResources().getAssets().open("pageconfig.txt");
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                this.c = new JSONObject(new String(bArr, "UTF-8"));
                Iterator keys = this.c.keys();
                while (keys.hasNext()) {
                    this.d.add(keys.next());
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        com.qq.reader.common.monitor.f.a("LocalStoreCore", "initLocalBookStore IOException" + e.toString());
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                com.qq.reader.common.monitor.f.a("LocalStoreCore", "initLocalBookStore JSONException" + e2.toString());
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        com.qq.reader.common.monitor.f.a("LocalStoreCore", "initLocalBookStore IOException" + e3.toString());
                    }
                }
            } catch (IOException e32) {
                e32.printStackTrace();
                com.qq.reader.common.monitor.f.a("LocalStoreCore", "initLocalBookStore IOException" + e32.toString());
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                        com.qq.reader.common.monitor.f.a("LocalStoreCore", "initLocalBookStore IOException" + e322.toString());
                    }
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        com.qq.reader.common.monitor.f.a("LocalStoreCore", "initLocalBookStore IOException" + e4.toString());
                    }
                }
            }
        }
    }
}
