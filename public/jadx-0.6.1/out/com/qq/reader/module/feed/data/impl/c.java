package com.qq.reader.module.feed.data.impl;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.FeedEditorRecommend4BoyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.FeedEditorRecommend4GirlCard;
import com.qq.reader.module.bookstore.qnative.card.impl.FeedVirtualRecommendCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.Feed3HorBooksGroupCard;
import com.qq.reader.module.feed.card.Feed3VerBooksGroupCard;
import com.qq.reader.module.feed.card.Feed4HorBooksGroupCard;
import com.qq.reader.module.feed.card.FeedAdvCard;
import com.qq.reader.module.feed.card.FeedAuthorRecCard;
import com.qq.reader.module.feed.card.FeedBookGroupCard;
import com.qq.reader.module.feed.card.FeedBookRankCard;
import com.qq.reader.module.feed.card.FeedBookReviewCard;
import com.qq.reader.module.feed.card.FeedBookReviewOfficialCard;
import com.qq.reader.module.feed.card.FeedColumn3VerListCard;
import com.qq.reader.module.feed.card.FeedColumnPersonalOneCard;
import com.qq.reader.module.feed.card.FeedColumnPersonalRecommendCard;
import com.qq.reader.module.feed.card.FeedColumnPersonalTwoCard;
import com.qq.reader.module.feed.card.FeedInteractiveTopicCard;
import com.qq.reader.module.feed.card.FeedListenBookCard;
import com.qq.reader.module.feed.card.FeedQuestionCard;
import com.qq.reader.module.feed.card.FeedRecommendACard;
import com.qq.reader.module.feed.card.FeedSingleBookCard;
import com.qq.reader.module.feed.card.FeedSingleBookWithIntroCard;
import com.qq.reader.module.feed.card.FeedTodayFlashSaleCard;
import com.qq.reader.module.feed.card.FeedTopicCard;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FeedCardBuilder */
public class c {
    public static List<a> a(b bVar, JSONArray jSONArray) {
        List<a> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt("moudle", -1);
                int optInt2 = optJSONObject.optInt("style");
                if (optInt == -1) {
                    optInt = optJSONObject.optInt("bmodule");
                    optInt2 = optJSONObject.optInt("bstyle");
                }
                if (!"-404".equalsIgnoreCase(optJSONObject.optString("id"))) {
                    FeedBaseCard feedSingleBookCard;
                    FeedBaseCard feedBookReviewCard;
                    FeedBaseCard feedBaseCard = null;
                    switch (optInt) {
                        case 0:
                            switch (optInt2) {
                                case 0:
                                    feedSingleBookCard = new FeedSingleBookCard(bVar, "");
                                    break;
                                case 1:
                                    feedSingleBookCard = new FeedAdvCard(bVar, "");
                                    break;
                                case 2:
                                    feedSingleBookCard = new FeedTopicCard(bVar, "", 1);
                                    break;
                                case 9:
                                    feedSingleBookCard = new FeedSingleBookCard(bVar, "onlytitle");
                                    break;
                                case 17:
                                    feedSingleBookCard = new FeedSingleBookWithIntroCard(bVar, "");
                                    break;
                                case 18:
                                    feedSingleBookCard = new FeedSingleBookWithIntroCard(bVar, "onlytitle");
                                    break;
                                default:
                                    feedSingleBookCard = null;
                                    break;
                            }
                            feedBaseCard = feedSingleBookCard;
                            break;
                        case 1:
                            switch (optInt2) {
                                case 1:
                                    feedBaseCard = new FeedAdvCard(bVar, "");
                                    break;
                                case 2:
                                    feedBaseCard = new FeedTopicCard(bVar, "", 1);
                                    break;
                                case 3:
                                    feedBaseCard = new FeedTopicCard(bVar, "", 2);
                                    break;
                                case 4:
                                    feedBaseCard = new FeedTopicCard(bVar, "", 3);
                                    break;
                                case 5:
                                    feedBaseCard = new FeedBookGroupCard(bVar, "");
                                    break;
                                case 6:
                                    feedBaseCard = new FeedBookRankCard(bVar, "");
                                    break;
                                case 7:
                                    feedBaseCard = new FeedAuthorRecCard(bVar, "");
                                    break;
                                default:
                                    break;
                            }
                        case 2:
                            switch (optInt2) {
                                case 1:
                                    feedBaseCard = new FeedAdvCard(bVar, "");
                                    break;
                                case 2:
                                    feedBaseCard = new FeedTopicCard(bVar, "", 1);
                                    break;
                                case 3:
                                    feedBaseCard = new FeedTopicCard(bVar, "", 2);
                                    break;
                                case 4:
                                    feedBaseCard = new FeedTopicCard(bVar, "", 3);
                                    break;
                                case 5:
                                    feedBaseCard = new FeedBookGroupCard(bVar, "");
                                    break;
                                default:
                                    break;
                            }
                        case 3:
                            switch (optInt2) {
                                case 8:
                                    feedBookReviewCard = new FeedBookReviewCard(bVar, "");
                                    break;
                                case 10:
                                    feedBookReviewCard = new FeedBookReviewOfficialCard(bVar, "");
                                    break;
                                case 12:
                                    feedBookReviewCard = new FeedInteractiveTopicCard(bVar, "");
                                    break;
                            }
                        case 4:
                            feedBookReviewCard = null;
                            break;
                        case 5:
                            switch (optInt2) {
                                case 0:
                                    feedBookReviewCard = new FeedListenBookCard(bVar, "");
                                    break;
                                case 2:
                                    feedBookReviewCard = new FeedTopicCard(bVar, "", 1);
                                    ((FeedTopicCard) feedBookReviewCard).setListen(true);
                                    break;
                                default:
                                    feedBookReviewCard = null;
                                    break;
                            }
                            feedBaseCard = feedBookReviewCard;
                            break;
                        case 6:
                            switch (optInt2) {
                                case 13:
                                    feedBaseCard = new Feed3HorBooksGroupCard(bVar, "");
                                    break;
                                case 14:
                                    feedBaseCard = new Feed3VerBooksGroupCard(bVar, "");
                                    break;
                                case 15:
                                    feedBaseCard = new FeedRecommendACard(bVar, "");
                                    break;
                                case 16:
                                    feedBaseCard = new Feed4HorBooksGroupCard(bVar, "");
                                    break;
                                default:
                                    break;
                            }
                        case 1001:
                            switch (optInt2) {
                                case 13:
                                    feedBaseCard = new Feed3HorBooksGroupCard(bVar, "");
                                    break;
                                case 14:
                                    feedBaseCard = new Feed3VerBooksGroupCard(bVar, "");
                                    break;
                                case 16:
                                    feedBaseCard = new Feed4HorBooksGroupCard(bVar, "");
                                    break;
                                default:
                                    break;
                            }
                        case 1002:
                            switch (optInt2) {
                                case 13:
                                    feedBaseCard = new Feed3HorBooksGroupCard(bVar, "");
                                    break;
                                case 14:
                                    feedBaseCard = new Feed3VerBooksGroupCard(bVar, "");
                                    break;
                                case 16:
                                    feedBaseCard = new Feed4HorBooksGroupCard(bVar, "");
                                    break;
                                default:
                                    break;
                            }
                        case 1003:
                            switch (optInt2) {
                                case 13:
                                    feedBaseCard = new Feed3HorBooksGroupCard(bVar, "");
                                    break;
                                case 14:
                                    feedBaseCard = new Feed3VerBooksGroupCard(bVar, "");
                                    break;
                                case 16:
                                    feedBaseCard = new Feed4HorBooksGroupCard(bVar, "");
                                    break;
                                default:
                                    break;
                            }
                        case 1004:
                            switch (optInt2) {
                                case 1004:
                                    feedBaseCard = new FeedQuestionCard(bVar, "normalList");
                                    break;
                                default:
                                    break;
                            }
                    }
                    switch (optInt2) {
                        case 1:
                            feedSingleBookCard = new FeedAdvCard(bVar, "");
                            break;
                        case 2:
                            feedSingleBookCard = new FeedTopicCard(bVar, "", 1);
                            break;
                        default:
                            feedSingleBookCard = feedBookReviewCard;
                            break;
                    }
                    feedBaseCard = feedSingleBookCard;
                    if (feedBaseCard != null) {
                        feedBaseCard.setDataStatus(1001);
                        feedBaseCard.setIndex(i);
                        feedBaseCard.fillData(optJSONObject);
                        arrayList.add(feedBaseCard);
                    } else {
                        continue;
                    }
                }
            }
        }
        return arrayList;
    }

    public static ArrayList<FeedBaseCard> a(b bVar, JSONObject jSONObject, com.qq.reader.module.bookstore.qnative.c.a aVar) {
        ArrayList<FeedBaseCard> arrayList = new ArrayList();
        if (jSONObject != null) {
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("list");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        FeedBaseCard feedColumn3VerListCard;
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        switch (optJSONObject.optInt("style")) {
                            case 2:
                                feedColumn3VerListCard = new FeedColumn3VerListCard(bVar, "");
                                break;
                            case 4:
                                feedColumn3VerListCard = new FeedEditorRecommend4BoyCard(bVar, "", true);
                                break;
                            case 5:
                                feedColumn3VerListCard = new FeedColumnPersonalOneCard(bVar, "");
                                break;
                            case 6:
                                feedColumn3VerListCard = new FeedColumnPersonalTwoCard(bVar, "");
                                break;
                            case 7:
                                feedColumn3VerListCard = new FeedColumnPersonalRecommendCard(bVar, "");
                                break;
                            case 8:
                                feedColumn3VerListCard = new FeedEditorRecommend4GirlCard(bVar, "", true);
                                break;
                            case 9:
                                feedColumn3VerListCard = new FeedVirtualRecommendCard(bVar, "");
                                break;
                            case 13:
                                feedColumn3VerListCard = new FeedTodayFlashSaleCard(bVar, "");
                                break;
                            default:
                                feedColumn3VerListCard = null;
                                break;
                        }
                        if (feedColumn3VerListCard != null) {
                            feedColumn3VerListCard.setCardId(i + "");
                            feedColumn3VerListCard.setEventListener(aVar);
                            feedColumn3VerListCard.setDataStatus(1001);
                            if (feedColumn3VerListCard.parseData(optJSONObject)) {
                                arrayList.add(feedColumn3VerListCard);
                            }
                        }
                    }
                    return arrayList;
                }
            } catch (Exception e) {
                if (arrayList.size() > 0) {
                    return arrayList;
                }
                return null;
            }
        }
        return null;
    }
}
