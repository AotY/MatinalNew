package com.aoty.matinalnew.model;

/**
 * Created by AotY on 2014/12/24.
 */
public enum Category {

    headline("headline"), sport("sport"), technology("technology"), economic("economic"), entertainment("entertainment"), guiyang("guiyang");

    private String mDisplayName;

    Category(String displayName) {
        mDisplayName = displayName;
    }

    public String getDisplayName() {
        return mDisplayName;
    }
}
