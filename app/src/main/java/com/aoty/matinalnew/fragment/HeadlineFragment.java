package com.aoty.matinalnew.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.aoty.matinalnew.NewDetailActivity;
import com.aoty.matinalnew.R;
import com.aoty.matinalnew.adapter.NewsAdapter;
import com.aoty.matinalnew.app.MyApplication;
import com.aoty.matinalnew.dao.NewsDataHelper;
import com.aoty.matinalnew.date.GsonRequest;
import com.aoty.matinalnew.model.Category;
import com.aoty.matinalnew.model.New;
import com.aoty.matinalnew.utils.TaskUtils;
import com.aoty.matinalnew.utils.ToastUtils;
import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;

import java.util.ArrayList;

/**
 * Created by AotY on 2014/12/25.
 */
public class HeadlineFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    private SwipeRefreshLayout mSwipwLayout;

    private ListView listView;

//    private MenuItem mRefreshItem;

    private Category mCategory;

    private String mGetNewUrl;
    private NewsDataHelper mDataHelper;
    private NewsAdapter mAdapter;

    private Activity mActivity;

    private String mPage;

//    private SliderLayout mDemoSlider;


    public HeadlineFragment(Category category, String getNewUrl) {
        this.mCategory = category;
        this.mGetNewUrl = getNewUrl;

    }
//    public static NewFragment newInstance(Category category) {
//        NewFragment fragment = new NewFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(EXTRA_CATEGORY, category.name());
//        fragment.setArguments(bundle);
//        return getInstance();
//    }

    //    public abstract NewFragment getInstance();
//    public abstract Category getCategory();
//
//    public abstract String getNewUrl();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.headline_frag_layout, null, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        init();
    }


    private void init() {
        initView();
//        parseArgument();
        mDataHelper = new NewsDataHelper(MyApplication.getContext(), mCategory);
        mAdapter = new NewsAdapter(getActivity(), listView);
        AnimationAdapter animationAdapter = new SwingLeftInAnimationAdapter(mAdapter);
        animationAdapter.setAbsListView(listView);
        listView.setAdapter(animationAdapter);
        listView.setOnItemClickListener(this);
        mSwipwLayout.setOnRefreshListener(this);
        mSwipwLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        getLoaderManager().initLoader(0, null, this);
//        initData();
        loadFirst();
    }

    private void initData() {
//        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Hannibal",R.drawable.hannibal);
//        file_maps.put("Big Bang Theory",R.drawable.bigbang);
//        file_maps.put("House of Cards",R.drawable.house);
//        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);
//
//        for(String name : file_maps.keySet()){
//            TextSliderView textSliderView = new TextSliderView(mActivity);
//            // initialize a SliderLayout
//            textSliderView
//                    .description(name)
//                    .image(file_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit)
//                    .setOnSliderClickListener(this);

        //add your extra information
//            textSliderView.getBundle()
//                    .putString("extra",name);
//
//            mDemoSlider.addSlider(textSliderView);
//        }
//        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
//        mDemoSlider.setDuration(4000);
//        mDemoSlider.setCustomIndicator((PagerIndicator) mActivity.findViewById(R.id.headline_custom_indicator));
    }

    private void initView() {
        mSwipwLayout = (SwipeRefreshLayout) mActivity.findViewById(R.id.headlie_swipe_container);
        listView = (ListView) mActivity.findViewById(R.id.heahline_list_view);
//        mDemoSlider = (SliderLayout)mActivity.findViewById(R.id.headline_slider);

    }

    private void parseArgument() {
//        Bundle bundle = getArguments();
//        mCategory = Category.valueOf(bundle.getString(EXTRA_CATEGORY));
    }

    private void loadFirst() {
        mPage = "0";
        loadData(mPage);
    }

    private void loadData(String next) {
        if (!mSwipwLayout.isRefreshing() && ("0".equals(next))) {
            setRefreshing(true);
        }
        executeRequest(new GsonRequest(mGetNewUrl, New.NewsReturnData.class, responseListener(),
                errorListener()));
    }

    private void setRefreshing(boolean refreshing) {
        mSwipwLayout.setRefreshing(refreshing);
//        if (mRefreshItem == null)
//            return;
//        if (refreshing)
//            mRefreshItem.setActionView(R.layout.actionbar_refresh_progress);
//        else
//            mRefreshItem.setActionView(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        mRefreshItem = menu.findItem(R.id.action_refresh);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.action_refresh) {
//            loadFirst();
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    private Response.Listener<New.NewsReturnData> responseListener() {
        final boolean isRefreshFromTop = ("0".equals(mPage));
        return new Response.Listener<New.NewsReturnData>() {
            @Override
            public void onResponse(final New.NewsReturnData response) {
                TaskUtils.executeAsyncTask(new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object... params) {
                        if (isRefreshFromTop) {
                            mDataHelper.deleteAll();
                        }
                        if (response.status == 1 && response.news.size() != 0) {
                            ArrayList<New> news = response.news;
                            mDataHelper.bulkInsert(news);
                            return null;
                        }
//                        ToastUtils.showShort("加载数据失败");
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        super.onPostExecute(o);
                        setRefreshing(false);
                    }
                });
            }
        };
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.showShort("加载数据失败");
                setRefreshing(false);
            }
        };
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mDataHelper.getCursorLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.changeCursor(data);
        if (data != null && data.getCount() == 0) {
            loadFirst();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);
    }

    @Override
    public void onRefresh() {
        loadFirst();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String newUrl = mAdapter.getItem(position).getnUrl();
        Intent intent = new Intent(getActivity(), NewDetailActivity.class);
        intent.putExtra(NewDetailActivity.NEW_URL, newUrl);
        startActivity(intent);
    }

}
