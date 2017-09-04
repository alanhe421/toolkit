package com.qq.reader.common.utils;

import com.qq.reader.common.monitor.debug.c;

/* compiled from: CategoryIdConverter */
public class i {
    public static String a() {
        return "cids";
    }

    public static long a(String str) {
        try {
            switch (Integer.parseInt(str)) {
                case 13100:
                    return 10384;
                case 13200:
                    return 10396;
                case 13400:
                    return 10392;
                case 13700:
                    return 10404;
                case 14100:
                    return 10400;
                case 14300:
                    return 10388;
                case 14400:
                    return 10408;
                case 14500:
                    return 10412;
                case 20001:
                    return 10472;
                case 20005:
                    return 10480;
                case 20010:
                    return 10487;
                case 20014:
                    return 10494;
                case 20019:
                    return 10507;
                case 20028:
                    return 10515;
                case 20032:
                    return 10523;
                case 20037:
                    return 10534;
                case 20042:
                    return 10542;
                case 20050:
                    return 10558;
                case 20054:
                    return 10529;
                case 20059:
                    return 10501;
                case 20065:
                    return 10550;
                case 30001:
                    return 10430;
                case 30008:
                    return 10437;
                case 30013:
                    return 10423;
                case 30020:
                    return 10416;
                case 30031:
                    return 10444;
                case 30036:
                    return 10465;
                case 30042:
                    return 10458;
                case 30050:
                    return 10451;
            }
        } catch (Exception e) {
            c.e("Error", e.getMessage());
        }
        return 0;
    }

    public static String b(String str) {
        try {
            switch (Integer.parseInt(str)) {
                case 13100:
                    return "Novel_Category_Publish";
                case 13200:
                    return "Economy_Category_Publish";
                case 13400:
                    return "Self_Help_Category_Publish";
                case 13700:
                    return "Bisexual_Category_Publish";
                case 14100:
                    return "Literature_Category_Publish";
                case 14300:
                    return "Youth_Literature_Category_Publish";
                case 14400:
                    return "History_Category_Publish";
                case 14500:
                    return "Biography_Category_Publish";
                case 20001:
                    return "XuanHuan_Category_Boy";
                case 20005:
                    return "QiHuan_Category_Boy";
                case 20010:
                    return "WuXia_Category_Boy";
                case 20014:
                    return "XianXia_Category_Boy";
                case 20019:
                    return "City_Category_Boy";
                case 20028:
                    return "History_Boy";
                case 20032:
                    return "War_Boy";
                case 20037:
                    return "Spiritual_Boy";
                case 20042:
                    return "Science_Boy";
                case 20050:
                    return "Game_Boy";
                case 20054:
                    return "Sport_Boy";
                case 20059:
                    return "ErCiYuan_Category_Boy";
                case 20065:
                    return "Job_Boy";
                case 30001:
                    return "Fantasy_Affection";
                case 30008:
                    return "XianXia_QiYuan";
                case 30013:
                    return "Ancient_Affection";
                case 30020:
                    return "Modern_Affection";
                case 30031:
                    return "Romance_Youth";
                case 30036:
                    return "Suspense_Spiritual";
                case 30042:
                    return "Science_Space";
                case 30050:
                    return "Game_Athletics";
            }
        } catch (Exception e) {
            c.e("Error", e.getMessage());
        }
        return null;
    }
}
