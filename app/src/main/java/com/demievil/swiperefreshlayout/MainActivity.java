package com.demievil.swiperefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements MyRefreshOnLoadLayout.OnRefreshListener,MyRefreshOnLoadLayout.OnLoadListener {

    private MyRefreshOnLoadLayout mRefreshLayout;
    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;
    private ArrayList<String> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRefreshLayout = (MyRefreshOnLoadLayout) findViewById(R.id.swipe_container);
        mListView = (ListView) findViewById(R.id.list);
        mRefreshLayout.setFooterView(this, mListView, R.layout.my_refresh_onload_footer_view_one);    //listview_footer

        values = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            values.add("Item " + i);
        }
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(mArrayAdapter);

        mRefreshLayout.setColorSchemeResources(R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadListener(this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                values.add(0, "Swipe Down to Refresh " + values.size());
                mArrayAdapter.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                values.add("Swipe Up to Load More "+ values.size());
                mArrayAdapter.notifyDataSetChanged();
                mRefreshLayout.setLoading(false);
            }
        }, 2000);
    }
}
