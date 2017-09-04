package com.qq.reader.module.audio.c;

import android.os.Bundle;
import com.qq.reader.module.audio.card.AudioZoneMoreBookListCard;
import com.qq.reader.module.bookstore.qnative.page.impl.ag;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfAudioClassifyBookList */
public class a extends ag {
    public a(Bundle bundle) {
        super(bundle);
    }

    protected void a(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("lbookList");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    com.qq.reader.module.bookstore.qnative.card.a audioZoneMoreBookListCard = new AudioZoneMoreBookListCard(this, String.valueOf(5));
                    audioZoneMoreBookListCard.fillData(optJSONObject);
                    audioZoneMoreBookListCard.setEventListener(l());
                    this.k.add(audioZoneMoreBookListCard);
                    this.l.put(audioZoneMoreBookListCard.getCardId(), audioZoneMoreBookListCard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }
}
