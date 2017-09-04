package com.qq.reader.module.bookstore.search.card;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SearchCardBuilder */
public class b {
    public static List<SearchBaseCard> a(com.qq.reader.module.bookstore.qnative.page.b bVar, JSONArray jSONArray, String str) {
        List<SearchBaseCard> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            SearchBaseCard searchBaseCard = null;
            if (optJSONObject != null) {
                switch (optJSONObject.optInt("cardtype", -1)) {
                    case 0:
                        searchBaseCard = new SearchSingleBookCard(bVar, "", "skey");
                        break;
                    case 1:
                        searchBaseCard = new SearchTagClassifyDirectzoneCard(bVar, "", 0);
                        break;
                    case 2:
                        searchBaseCard = new SearchTagClassifyDirectzoneCard(bVar, "", 1);
                        break;
                    case 3:
                        searchBaseCard = new SearchTagClassifyDirectzoneCard(bVar, "", 2);
                        break;
                    case 4:
                        searchBaseCard = new SearchAuthorDirectzoneCard(bVar, "");
                        break;
                    case 5:
                        searchBaseCard = new SearchPublisherDirectzoneCard(bVar, "");
                        break;
                    case 6:
                        searchBaseCard = new SearchTopicCard(bVar, "", 3);
                        break;
                    case 7:
                        searchBaseCard = new SearchTagClassifyDirectzoneCard(bVar, "", 3);
                        break;
                    case 8:
                        searchBaseCard = new SearchZoneDirectzoneCard(bVar, "");
                        break;
                    case 9:
                        searchBaseCard = new SearchAuthorRecCard(bVar, "");
                        break;
                    case 10:
                        searchBaseCard = new SearchAudioSingleBookCard(bVar, "", "skey");
                        break;
                }
                if (searchBaseCard != null) {
                    searchBaseCard.setSearchKey(str);
                    searchBaseCard.fillData(optJSONObject);
                    arrayList.add(searchBaseCard);
                }
            }
        }
        return arrayList;
    }
}
