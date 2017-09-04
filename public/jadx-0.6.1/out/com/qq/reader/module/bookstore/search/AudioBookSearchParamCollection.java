package com.qq.reader.module.bookstore.search;

import com.qq.reader.appconfig.e;
import java.util.Map;

public class AudioBookSearchParamCollection implements ISearchParamCollection {
    public boolean needShowSearchTabView() {
        return false;
    }

    public int getSearchType() {
        return 2;
    }

    public String getHotWordProtocolURL() {
        return e.aR + "newaudio=1&";
    }

    public String getDefaultHotWordFilePath() {
        return "search/audio_only_search_hot_word_json.txt";
    }

    public String getHotWordSaveConfigPerfeName() {
        return "AUDIO_HOT_WORD";
    }

    public String getSearchAssociateProtocolURL() {
        return e.ct;
    }

    public String getSearchHistoryTableName() {
        return "audiosearchhistory";
    }

    public String getSearchProtocolURL() {
        return e.cu;
    }

    public void submitStaticsParam(Map<String, String> map) {
        if (map != null) {
            map.put("type", "1");
        }
    }
}
