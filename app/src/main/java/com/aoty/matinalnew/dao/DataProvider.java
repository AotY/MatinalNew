package com.aoty.matinalnew.dao;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import com.aoty.matinalnew.app.MyApplication;

/**
 * Created by AotY on 2014/12/22.
 */
public class DataProvider extends ContentProvider {

    private static final String TAG = "TAG";

    static final Object DBLock = new Object();

    public static final String AUTHORITY = "com.aoty.matinalnew.dao.DataProvider";

    public static final String SCHEME = "content://";

    // messages
    public static final String PATH_FEEDS = "/news";

    public static final Uri FEEDS_CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH_FEEDS);

    private static final int NEWS = 0;

    /*
     * MIME type definitions
     */
    public static final String NEW_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.aoty.matinal.new";

    private static final UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, "news", NEWS);
    }

    private static DBHelper mDBHelper;

    public static DBHelper getDBHelper() {
        if (mDBHelper == null) {
            mDBHelper = new DBHelper(MyApplication.getContext());
        }
        return mDBHelper;
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        synchronized (DBLock) { //加锁
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            String table = matchTable(uri);
            //设置查询的表名
            queryBuilder.setTables(table);
            SQLiteDatabase db = getDBHelper().getReadableDatabase();
            Cursor cursor = queryBuilder.query(db,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case NEWS:
                return NEW_CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        synchronized (DBLock) {
            String table = matchTable(uri);
            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            long rowId = 0;
            db.beginTransaction();
            try {
                rowId = db.insert(table, null, values);
                db.setTransactionSuccessful();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            } finally {
                db.endTransaction();
            }
            if (rowId > 0) {
                Uri returnUri = ContentUris.withAppendedId(uri, rowId);
                //通知指定URI数据已改变
                getContext().getContentResolver().notifyChange(uri, null);
                return returnUri;
            }
            throw new SQLException("Failed to insert row into " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        synchronized (DBLock) {
            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            int count = 0;
            String table = matchTable(uri);
            db.beginTransaction(); //开启事务
            try {
                count = db.delete(table, selection, selectionArgs);
                db.setTransactionSuccessful(); //标记事务成功
            } finally {
                db.endTransaction();//结束事务
            }
            //通知指定URI数据已改变
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        synchronized (DBLock) {
            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            int count;
            String table = matchTable(uri);
            db.beginTransaction(); //开启事务
            try {
                count = db.update(table, values, selection, selectionArgs);
                db.setTransactionSuccessful(); //事务可以嵌套，通过setTransactionSuccessful标记, 当所有操作都成功时，事务才提交，否则回滚。
            } finally {
                db.endTransaction();//结束事务
            }
            //通知指定URI数据已改变
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
    }

    private String matchTable(Uri uri) {
        String table = null;
        switch (sUriMatcher.match(uri)) {
            case NEWS:
                table = NewsDataHelper.NewsDBInfo.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return table;
    }
}
