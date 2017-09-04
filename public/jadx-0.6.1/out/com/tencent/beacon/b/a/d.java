package com.tencent.beacon.b.a;

import java.util.Locale;

/* compiled from: ProGuard */
public final class d {
    public static final String[][] a = new String[7][];

    static {
        String[][] strArr = a;
        String[] strArr2 = new String[2];
        strArr2[0] = "t_event";
        strArr2[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s int , %s int , %s int , %s int , %s blob)", new Object[]{"t_event", "_id", "_time", "_type", "_prority", "_length", "_datas"});
        strArr[0] = strArr2;
        strArr = a;
        strArr2 = new String[2];
        strArr2[0] = "t_count_event";
        strArr2[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s varchar(255) unique  , %s int , %s int , %s int , %s int , %s int , %s text)", new Object[]{"t_count_event", "_id", "_countid", "_prority", "_local", "_stime", "_utime", "_ctime", "_cparams"});
        strArr[1] = strArr2;
        strArr = a;
        strArr2 = new String[2];
        strArr2[0] = "t_strategy";
        strArr2[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s int unique , %s int , %s blob)", new Object[]{"t_strategy", "_id", "_key", "_ut", "_datas"});
        strArr[2] = strArr2;
        strArr = a;
        strArr2 = new String[2];
        strArr2[0] = "t_file";
        strArr2[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s text , %s int , %s int , %s text , %s text , %s text , %s text)", new Object[]{"t_file", "_id", "_n", "_ut", "_sz", "_ac", "_sa", "_t", "_p"});
        strArr[3] = strArr2;
        strArr = a;
        strArr2 = new String[2];
        strArr2[0] = "t_req_data";
        strArr2[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s text unique , %s int , %s int , %s blob)", new Object[]{"t_req_data", "_id", "_rid", "_time", "_cnt", "_datas"});
        strArr[4] = strArr2;
        strArr = a;
        String[] strArr3 = new String[2];
        strArr3[0] = "t_apple";
        strArr3[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s blob unique , %s int , %s int, %s int)", new Object[]{"t_apple", "_id", "_a", "_b", "_c", "_d"});
        strArr[5] = strArr3;
        strArr = a;
        strArr3 = new String[2];
        strArr3[0] = "t_conf";
        strArr3[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s text unique , %s text , %s int , %s int)", new Object[]{"t_conf", "_id", "_key", "_value", "_vdate", "_time"});
        strArr[6] = strArr3;
    }
}
