package com.qq.reader.module.bookstore.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchTabInfo {
    List<a> a = new ArrayList();

    public static class SearchActionTagLv3InitialDataModel implements Serializable {
        public boolean itemShouldInvisible = false;
        public int[] selectedItemIds;
        public int selectedSubId;
    }

    public static class a {
        boolean a;
        List<c> b = new ArrayList();
        public int c;
        public String d;
        public int e;
        public int f;

        public String toString() {
            return "SearchId title = " + this.d;
        }
    }

    public static class b {
        public int a;
        public String b;
        public int c;

        public String toString() {
            return "SearchActionTagLv3{id=" + this.a + ", tips='" + this.b + '\'' + ", subId=" + this.c + '}';
        }
    }

    public static class c {
        public int a = -1;
        public int b;
        public boolean c;
        public int d;
        public String e;
        public int f;
        public boolean g = false;
        public int h;
        public List<b> i = new ArrayList();
    }
}
