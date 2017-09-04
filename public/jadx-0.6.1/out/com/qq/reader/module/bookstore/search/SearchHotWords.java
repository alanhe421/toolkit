package com.qq.reader.module.bookstore.search;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchHotWords extends AbsSearchWords {
    public List<SearchHotWords> initDefault() {
        List<SearchHotWords> arrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            SearchHotWords searchHotWords = new SearchHotWords();
            searchHotWords.setKeyWord(i + " keyword");
            searchHotWords.mType = i;
            arrayList.add(searchHotWords);
        }
        return arrayList;
    }

    public SearchHotWords(String str) {
        setKeyWord(str);
    }

    public SearchHotWords(String str, int i) {
        super(str, i);
    }

    public SearchHotWords parseJson(JSONObject jSONObject) {
        setQurl(jSONObject.optString("qurl"));
        setKeyWord(jSONObject.optString("title"));
        try {
            this.mType = Integer.valueOf(jSONObject.optString("type")).intValue();
        } catch (Exception e) {
            this.mType = 0;
        }
        return this;
    }

    public static List<SearchHotWords> parseHotword(String str) throws JSONException {
        List<SearchHotWords> arrayList = new ArrayList();
        JSONArray optJSONArray = new JSONObject(str).optJSONArray("hotwords");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(new SearchHotWords().parseJson(optJSONArray.optJSONObject(i)));
            }
        }
        return arrayList;
    }
}
