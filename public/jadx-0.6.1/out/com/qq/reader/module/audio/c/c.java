package com.qq.reader.module.audio.c;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.audio.card.AudioZoneBookDiscountCard;
import com.qq.reader.module.audio.card.AudioZoneBookHorCard;
import com.qq.reader.module.audio.card.AudioZoneBookRecCard;
import com.qq.reader.module.audio.card.AudioZoneBookVerCard;
import com.qq.reader.module.audio.card.AudioZoneBottomJumpCard;
import com.qq.reader.module.audio.card.AudioZoneEntranceCard;
import com.qq.reader.module.audio.card.AudioZoneListenTimeCard;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.tencent.android.tpush.common.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfAudioZone */
public class c extends af {
    public c(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return new com.qq.reader.module.bookstore.qnative.c(bundle).a(e.a, "audio/home?");
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            String string = jSONObject.getString("type");
            String toLowerCase = string.toLowerCase();
            a audioZoneEntranceCard;
            if ("quickEntry".equalsIgnoreCase(toLowerCase)) {
                audioZoneEntranceCard = new AudioZoneEntranceCard(this, string);
                audioZoneEntranceCard.fillData(jSONObject2.optJSONArray(string));
                audioZoneEntranceCard.setEventListener(l());
                this.k.add(audioZoneEntranceCard);
                this.l.put(audioZoneEntranceCard.getCardId(), audioZoneEntranceCard);
            } else if (Constants.FLAG_ACTIVITY_NAME.equalsIgnoreCase(toLowerCase)) {
                audioZoneEntranceCard = new AudioZoneListenTimeCard(this, string);
                audioZoneEntranceCard.fillData(jSONObject2.optJSONObject(string));
                audioZoneEntranceCard.setEventListener(l());
                this.k.add(audioZoneEntranceCard);
                this.l.put(audioZoneEntranceCard.getCardId(), audioZoneEntranceCard);
            } else if ("list".equalsIgnoreCase(toLowerCase)) {
                JSONArray optJSONArray = jSONObject2.optJSONArray(string);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        int optInt = optJSONObject.optInt("style");
                        switch (optInt) {
                            case 0:
                                audioZoneEntranceCard = new AudioZoneBottomJumpCard(this, String.valueOf(optInt));
                                break;
                            case 1:
                            case 5:
                                audioZoneEntranceCard = new AudioZoneBookHorCard(this, String.valueOf(optInt));
                                break;
                            case 2:
                                audioZoneEntranceCard = new AudioZoneBookRecCard(this, String.valueOf(optInt));
                                break;
                            case 3:
                                audioZoneEntranceCard = new AudioZoneBookDiscountCard(this, String.valueOf(optInt));
                                break;
                            case 4:
                            case 6:
                                audioZoneEntranceCard = new AudioZoneBookVerCard(this, String.valueOf(optInt));
                                break;
                            default:
                                audioZoneEntranceCard = new AudioZoneBookHorCard(this, String.valueOf(optInt));
                                break;
                        }
                        audioZoneEntranceCard.fillData(optJSONObject);
                        audioZoneEntranceCard.setEventListener(l());
                        this.k.add(audioZoneEntranceCard);
                        this.l.put(audioZoneEntranceCard.getCardId(), audioZoneEntranceCard);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public Class c() {
        return NativePageFragmentforOther.class;
    }
}
