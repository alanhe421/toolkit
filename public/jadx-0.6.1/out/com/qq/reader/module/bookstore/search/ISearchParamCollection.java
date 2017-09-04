package com.qq.reader.module.bookstore.search;

import java.io.Serializable;
import java.util.Map;

public interface ISearchParamCollection extends Serializable {
    String getDefaultHotWordFilePath();

    String getHotWordProtocolURL();

    String getHotWordSaveConfigPerfeName();

    String getSearchAssociateProtocolURL();

    String getSearchHistoryTableName();

    String getSearchProtocolURL();

    int getSearchType();

    boolean needShowSearchTabView();

    void submitStaticsParam(Map<String, String> map);
}
