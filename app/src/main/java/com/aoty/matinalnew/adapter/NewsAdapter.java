package com.aoty.matinalnew.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.aoty.matinalnew.R;
import com.aoty.matinalnew.date.ImageCacheManager;
import com.aoty.matinalnew.model.New;
import com.aoty.matinalnew.utils.DensityUtils;


/**
 * Created by storm on 14-3-26.
 */
public class NewsAdapter extends CursorAdapter {

    private static final int IMAGE_MAX_HEIGHT = 80;

    private LayoutInflater mLayoutInflater;

    private ListView mListView;

    private Drawable mDefaultImageDrawable;

    private Resources mResource;

    public NewsAdapter(Context context, ListView listView) {
        super(context, null, false);
        mResource = context.getResources();
        mLayoutInflater = LayoutInflater.from(context);
        mListView = listView;
        mDefaultImageDrawable = mResource.getDrawable(R.drawable.list_default_img);
    }

    @Override
    public New getItem(int position) {
        mCursor.moveToPosition(position);
        return New.fromCursor(mCursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return mLayoutInflater.inflate(R.layout.list_view_item_layout, null, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Holder holder = getHolder(view);
        if (holder.imageRequest != null) {
            holder.imageRequest.cancelRequest();
        }

        view.setEnabled(!mListView.isItemChecked(cursor.getPosition()
                + mListView.getHeaderViewsCount()));

        New n = New.fromCursor(cursor);
        holder.imageRequest = ImageCacheManager.loadImage(n.getnImgUrl(), ImageCacheManager
                .getImageListener(holder.image, mDefaultImageDrawable, mDefaultImageDrawable), 0, DensityUtils.dip2px(context, IMAGE_MAX_HEIGHT));
        holder.title.setText(n.getNtitle());
        holder.brief.setText(n.getnBrief());
    }

    private Holder getHolder(final View view) {
        Holder holder = (Holder) view.getTag();
        if (holder == null) {
            holder = new Holder(view);
            view.setTag(holder);
        }
        return holder;
    }

    static class Holder {
        public ImageView image;
        public TextView title;
        public TextView brief;
        public ImageLoader.ImageContainer imageRequest;

        public Holder(View view) {
            image = (ImageView) view.findViewById(R.id.list_item_img);
            title = (TextView) view.findViewById(R.id.list_item_text_title);
            brief = (TextView) view.findViewById(R.id.list_item_text_brief);
        }
    }
}
