package com.aoty.matinalnew.model;

import android.database.Cursor;

import com.aoty.matinalnew.dao.NewsDataHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class New extends BaseModel {

    private static final HashMap<Integer, New> CACHE = new HashMap<Integer, New>();

    private int nid;

    private String ntitle;

    private String nBrief;

    private String nImgUrl;

    private String nUrl;

    public New() {

    }

    public New(int nid, String ntitle, String nBrief, String nImgUrl,
               String nUrl) {
        super();
        this.nid = nid;
        this.ntitle = ntitle;
        this.nBrief = nBrief;
        this.nImgUrl = nImgUrl;
        this.nUrl = nUrl;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getnBrief() {
        return nBrief;
    }

    public void setnBrief(String nBrief) {
        this.nBrief = nBrief;
    }

    public String getnImgUrl() {
        return nImgUrl;
    }

    public void setnImgUrl(String nImgUrl) {
        this.nImgUrl = nImgUrl;
    }

    public String getnUrl() {
        return nUrl;
    }

    public void setnUrl(String nUrl) {
        this.nUrl = nUrl;
    }


    private static void addToCache(New n) {
        CACHE.put(n.getNid(), n);
    }

    private static New getFromCache(int nId) {
        return CACHE.get(nId);

    }


    public static New fromJson(String json) {

        return new Gson().fromJson(json, New.class);

    }


    public static New fromCursor(Cursor cursor) {
        int nId = cursor.getInt(cursor.getColumnIndex(NewsDataHelper.NewsDBInfo.ID));
        New n = getFromCache(nId);
        if (n != null) {

            return n;
        }
        n = new Gson().fromJson(cursor.getString(cursor.getColumnIndex(NewsDataHelper.NewsDBInfo.JSON)), New.class);
        addToCache(n);
        return n;
    }


    public static class NewsReturnData {
        public ArrayList<New> news;
        public int status;
    }

}
