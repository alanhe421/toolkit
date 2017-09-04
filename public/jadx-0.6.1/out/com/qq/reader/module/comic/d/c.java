package com.qq.reader.module.comic.d;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.DetailCommentCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.comic.card.ComicDetailAboutMoreCard;
import com.qq.reader.module.comic.card.ComicDetailAdvCard;
import com.qq.reader.module.comic.card.ComicDetailClassifyHotCard;
import com.qq.reader.module.comic.card.ComicDetailDirCard;
import com.qq.reader.module.comic.card.ComicDetailHeaderCard;
import com.qq.reader.module.comic.card.ComicDetailIntroduceCard;
import com.qq.reader.module.comic.card.ComicDetailOriginalBookCard;
import com.qq.reader.module.comic.card.ComicDetailPageBaseCard;
import com.qq.reader.module.comic.card.ComicDetailWatchingFocusCard;
import com.qq.reader.module.comic.entity.h;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfComicDetail */
public class c extends af {
    public int a;
    public String b;

    public c(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        this.a = jSONObject.optInt("code");
        if (this.a == 0) {
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            for (int i = 0; i < optJSONArray.length(); i++) {
                ComicDetailPageBaseCard comicDetailHeaderCard;
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                int optInt = optJSONObject.optInt("module");
                String str = "comic_module_" + i;
                switch (optInt) {
                    case 1:
                        comicDetailHeaderCard = new ComicDetailHeaderCard(this, str);
                        break;
                    case 2:
                        comicDetailHeaderCard = new ComicDetailWatchingFocusCard(this, str);
                        break;
                    case 3:
                        comicDetailHeaderCard = new ComicDetailIntroduceCard(this, str);
                        break;
                    case 4:
                        comicDetailHeaderCard = new ComicDetailDirCard(this, str);
                        break;
                    case 5:
                        comicDetailHeaderCard = new ComicDetailAdvCard(this, str);
                        break;
                    case 6:
                        comicDetailHeaderCard = new ComicDetailOriginalBookCard(this, str);
                        break;
                    case 7:
                        comicDetailHeaderCard = new ComicDetailClassifyHotCard(this, str);
                        break;
                    case 8:
                        comicDetailHeaderCard = new ComicDetailAboutMoreCard(this, str);
                        break;
                    case 9:
                        DetailCommentCard detailCommentCard = new DetailCommentCard(this, str);
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("data");
                        detailCommentCard.build(optJSONObject2);
                        if (detailCommentCard.fillData(optJSONObject2)) {
                            detailCommentCard.setEventListener(l());
                            this.k.add(detailCommentCard);
                            this.l.put(str, detailCommentCard);
                        }
                        comicDetailHeaderCard = null;
                        break;
                    default:
                        comicDetailHeaderCard = null;
                        break;
                }
                if (comicDetailHeaderCard != null && comicDetailHeaderCard.fillData(optJSONObject) && comicDetailHeaderCard.isValid()) {
                    comicDetailHeaderCard.setEventListener(l());
                    this.k.add(comicDetailHeaderCard);
                    this.l.put(str, comicDetailHeaderCard);
                }
            }
            return;
        }
        this.b = jSONObject.optString("message");
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public void a(b bVar) {
        super.a(bVar);
        if (bVar instanceof c) {
            this.a = ((c) bVar).a;
            this.b = ((c) bVar).b;
        }
    }

    public String a(Bundle bundle) {
        return e.dp + "?comicId=" + bundle.getString("KEY_COMIC_ID");
    }

    public boolean b() {
        return true;
    }

    public boolean a() {
        return false;
    }

    public h x() {
        for (a aVar : m()) {
            if (aVar instanceof ComicDetailHeaderCard) {
                return ((ComicDetailHeaderCard) aVar).getComicDetailInfo();
            }
        }
        return null;
    }
}
