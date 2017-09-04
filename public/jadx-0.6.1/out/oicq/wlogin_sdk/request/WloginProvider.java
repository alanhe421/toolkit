package oicq.wlogin_sdk.request;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import oicq.wlogin_sdk.tools.util;

public class WloginProvider extends ContentProvider {
    private a a;
    private SQLiteDatabase b = null;
    private final String c = "wlogin_provider.db";
    private final int d = 4;
    private final String e = "rsa_pubkey";
    private String f;
    private final int g = 1;
    private Context h;
    private UriMatcher i = new UriMatcher(-1);
    private Uri j;

    private class a extends SQLiteOpenHelper {
        final /* synthetic */ WloginProvider a;

        public a(WloginProvider wloginProvider, Context context, String str, CursorFactory cursorFactory, int i) {
            this.a = wloginProvider;
            super(context, str, cursorFactory, i);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL(String.format("CREATE TABLE %s (id INTEGER PRIMARY KEY AUTOINCREMENT, appid INTEGER, subappid INTEGER, pubkey TEXT, pubkey_md5 TEXT)", new Object[]{"rsa_pubkey"}));
            } catch (Exception e) {
                util.printException(e, "");
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS rsa_pubkey");
            onCreate(sQLiteDatabase);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        switch (this.i.match(uri)) {
            case 1:
                int delete = this.b.delete("rsa_pubkey", str, strArr);
                this.h.getContentResolver().notifyChange(uri, null);
                return delete;
            default:
                throw new IllegalArgumentException("Unnown URI" + uri);
        }
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        if (this.i.match(uri) != 1) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        long insert = this.b.insert("rsa_pubkey", null, contentValues);
        if (insert > 0) {
            Uri withAppendedId = ContentUris.withAppendedId(this.j, insert);
            this.h.getContentResolver().notifyChange(withAppendedId, null);
            return withAppendedId;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    public boolean onCreate() {
        this.h = getContext();
        this.f = "oicq.wlogin_sdk.WloginProvider";
        this.j = Uri.parse("content://" + this.f + "/" + "rsa_pubkey");
        this.i.addURI(this.f, "rsa_pubkey", 1);
        util.LOGI("oncreated!");
        this.a = new a(this, this.h, "wlogin_provider.db", null, 4);
        this.b = this.a.getWritableDatabase();
        return this.b != null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        switch (this.i.match(uri)) {
            case 1:
                Cursor query = this.b.query("rsa_pubkey", strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(this.h.getContentResolver(), uri);
                return query;
            default:
                throw new IllegalArgumentException("Unnown URI" + uri);
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        switch (this.i.match(uri)) {
            case 1:
                return this.b.update("rsa_pubkey", contentValues, str, strArr);
            default:
                throw new IllegalArgumentException("Unnown URI" + uri);
        }
    }
}
