package com.aoty.matinalnew.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by AotY on 2014/12/22.
 */
public abstract class BaseDataHelper {

    private Context mContext;

    /*
        构造函数，传入content
    */
    public BaseDataHelper(Context context) {

        this.mContext = context;
    }


    public Context getContext() {
        return mContext;
    }


    protected abstract Uri getContentUri();


    public void notifyChange() {

        mContext.getContentResolver().notifyChange(getContentUri(), null);

    }

    /*
     查询
     */
    protected final Cursor query(Uri uri, String[] projection, String selection,
                                 String[] selectionArgs, String sortOrder) {
        return mContext.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }


    protected final Cursor query(String[] projection, String selection,
                                 String[] selectionArgs, String sortOrder) {
        return mContext.getContentResolver().query(getContentUri(), projection, selection, selectionArgs, sortOrder);
    }



    /*
    插入(Insert)
     */

    protected final Uri insert(ContentValues values) {
        return mContext.getContentResolver().insert(getContentUri(), values);
    }


    protected final int bulkInsert(ContentValues[] valueses) {
        return mContext.getContentResolver().bulkInsert(getContentUri(), valueses);
    }


    /*
    更新(Update)
     */
    protected final int update(ContentValues values, String selection,
                               String[] selectionArgs) {
        return mContext.getContentResolver().update(getContentUri(), values, selection, selectionArgs);
    }


    /*
    删除
     */
    protected final int delete(Uri uri, String selection, String[] selectionArgs) {
        return mContext.getContentResolver().delete(getContentUri(), selection, selectionArgs);
    }


    protected final Cursor getList(String[] projection, String selection, String[] selectionArgs,
                                   String sortOrder) {
        return mContext.getContentResolver().query(getContentUri(), projection, selection,
                selectionArgs, sortOrder);
    }


    /*
   CursorLoader监听数据源，当数据改变的时候，将新的数据发布到UI上。
   CursorLoader还有一个更为强劲的功能，就是在接到数据变更的通知时会重新query一次，
   这样就保证了Cursor的数据始终与数据库同步。配合LoaderManager，
   使用CursorLoader是目前在Activity&Fragment中异步读取ContentProvider的最佳方案。
    */
    public CursorLoader getCursorLoader(Context context) {
        return getCursorLoader(context, null, null, null, null);
    }

    protected final CursorLoader getCursorLoader(Context context, String[] projection,
                                                 String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(context, getContentUri(), projection, selection, selectionArgs,
                sortOrder);
    }


}
