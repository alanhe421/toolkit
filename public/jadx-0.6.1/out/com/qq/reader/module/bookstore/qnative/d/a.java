package com.qq.reader.module.bookstore.qnative.d;

import android.text.TextUtils;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;

/* compiled from: NativeClassifyUtil */
public class a {
    private static HashMap<String, Integer> a = new HashMap();

    static {
        a.put("都市", Integer.valueOf(R.drawable.classify_dushi));
        a.put("二次元", Integer.valueOf(R.drawable.classify_erciyuan));
        a.put("军事", Integer.valueOf(R.drawable.classify_junshi));
        a.put("科幻", Integer.valueOf(R.drawable.classify_kehuan));
        a.put("灵异", Integer.valueOf(R.drawable.classify_lingyi));
        a.put("历史", Integer.valueOf(R.drawable.classify_lishi));
        a.put("奇幻", Integer.valueOf(R.drawable.classify_qihuan));
        a.put("体育", Integer.valueOf(R.drawable.classify_tiyu));
        a.put("武侠", Integer.valueOf(R.drawable.classify_wuxia));
        a.put("仙侠", Integer.valueOf(R.drawable.classify_xianxia));
        a.put("玄幻", Integer.valueOf(R.drawable.classify_xuanhuan));
        a.put("游戏", Integer.valueOf(R.drawable.classify_youxi));
        a.put("现实", Integer.valueOf(R.drawable.classify_xianshi));
        a.put("古代言情", Integer.valueOf(R.drawable.classify_gudaiyanqing));
        a.put("科幻空间", Integer.valueOf(R.drawable.classify_kehuankongjian));
        a.put("浪漫青春", Integer.valueOf(R.drawable.classify_langmanqingchun));
        a.put("现代言情", Integer.valueOf(R.drawable.classify_xiandaiyanqing));
        a.put("仙侠奇缘", Integer.valueOf(R.drawable.classify_xianxiaqiyuan));
        a.put("玄幻言情", Integer.valueOf(R.drawable.classify_xuanhuanyanqing));
        a.put("悬疑灵异", Integer.valueOf(R.drawable.classify_xuanyilingyi));
        a.put("游戏竞技", Integer.valueOf(R.drawable.classify_youxijingji));
        a.put("成功励志", Integer.valueOf(R.drawable.classify_chenggonglizhi));
        a.put("经济", Integer.valueOf(R.drawable.classify_jingji));
        a.put("两性关系", Integer.valueOf(R.drawable.classify_liangxingguanxi));
        a.put("青春文学", Integer.valueOf(R.drawable.classify_qingchunwenxue));
        a.put("文学", Integer.valueOf(R.drawable.classify_wenxue));
        a.put("小说", Integer.valueOf(R.drawable.classify_xiaoshuo));
        a.put("传记", Integer.valueOf(R.drawable.classify_zhuanji));
    }

    public static int a(String str) {
        if (!TextUtils.isEmpty(str) && a.containsKey(str)) {
            return ((Integer) a.get(str)).intValue();
        }
        return -1;
    }
}
