package com.aoty.matinalnew.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;

import com.aoty.matinalnew.model.Category;
import com.aoty.matinalnew.model.New;
import com.aoty.matinalnew.utils.database.Column;
import com.aoty.matinalnew.utils.database.SQLiteTable;

import java.util.ArrayList;

/**
 * Created by AotY on 2014/12/24.
 */
public class NewsDataHelper extends BaseDataHelper {
    private Category mCategory;

    public NewsDataHelper(Context context, Category category) {
        super(context);
        mCategory = category;
    }

    @Override
    protected Uri getContentUri() {
        return DataProvider.FEEDS_CONTENT_URI;
    }

    private ContentValues getContentValues(New n) {
        ContentValues values = new ContentValues();
        values.put(NewsDBInfo.ID, n.getNid());
        values.put(NewsDBInfo.CATEGORY, mCategory.ordinal());
        values.put(NewsDBInfo.JSON, n.toJson());
        return values;
    }

    public New query(long id) {
        New n = null;
        Cursor cursor = query(null, NewsDBInfo.CATEGORY + "=?" + " AND " + NewsDBInfo.ID + "= ?",
                new String[]{
                        String.valueOf(mCategory.ordinal()), String.valueOf(id)
                }, null);
        if (cursor.moveToFirst()) {
            n = New.fromCursor(cursor);
        }
        cursor.close();
        return n;
    }

    public void bulkInsert(ArrayList<New> news) {
        ArrayList<ContentValues> contentValues = new ArrayList<ContentValues>();
        for (New n : news) {
            ContentValues values = getContentValues(n);
            contentValues.add(values);
        }
        ContentValues[] valueArray = new ContentValues[contentValues.size()];
        bulkInsert(contentValues.toArray(valueArray));
    }

    public int deleteAll() {
        synchronized (DataProvider.DBLock) {
            DBHelper mDBHelper = DataProvider.getDBHelper();
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            int row = db.delete(NewsDBInfo.TABLE_NAME, NewsDBInfo.CATEGORY + "=?", new String[]{
                    String.valueOf(mCategory.ordinal())
            });
            return row;
        }
    }

    public CursorLoader getCursorLoader() {
//        return new CursorLoader(getContext(), getContentUri(), null, NewsDBInfo.CATEGORY + "=?",
//                new String[]{
//                        String.valueOf(mCategory.ordinal())
//                }, NewsDBInfo._ID + " DESC");
        return new CursorLoader(getContext(), getContentUri(), null, NewsDBInfo.CATEGORY + "=?",
                new String[]{
                        String.valueOf(mCategory.ordinal())
                }, NewsDBInfo._ID + " ASC");
    }


    public static final class NewsDBInfo implements BaseColumns {
        private NewsDBInfo() {
        }

        public static final String TABLE_NAME = "news";

        public static final String ID = "id";

        public static final String CATEGORY = "category";

        public static final String JSON = "json";

        public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
                .addColumn(ID, Column.DataType.INTEGER)
                .addColumn(CATEGORY, Column.DataType.INTEGER).addColumn(JSON, Column.DataType.TEXT);
    }
}
