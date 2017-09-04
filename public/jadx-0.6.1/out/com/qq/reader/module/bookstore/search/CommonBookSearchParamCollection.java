package com.qq.reader.module.bookstore.search;

import com.qq.reader.appconfig.e;
import java.util.Map;

public class CommonBookSearchParamCollection implements ISearchParamCollection {
    public boolean needShowSearchTabView() {
        return true;
    }

    public int getSearchType() {
        return 1;
    }

    public String getHotWordProtocolURL() {
        return e.aQ;
    }

    public String getDefaultHotWordFilePath() {
        return "search/search_hot_word_json.txt";
    }

    public String getHotWordSaveConfigPerfeName() {
        return "HOT_WORD";
    }

    public String getSearchAssociateProtocolURL() {
        return e.cq;
    }

    public String getSearchHistoryTableName() {
        return "searchhistory";
    }

    public String getSearchProtocolURL() {
        return e.cs;
    }

    public void submitStaticsParam(Map<String, String> map) {
        if (map != null) {
            map.put("type", "0");
        }
    }
}
