package com.quenice.reader.main.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.quenice.reader.R;
import com.quenice.reader.base.BaseActivity;
import com.quenice.reader.base.Callback;
import com.quenice.reader.common.http.helper.HttpHelper;
import com.quenice.reader.common.http.model.ZhihuDaily;
import com.quenice.reader.main.adapter.NewsListAdapter;

public class MainActivity extends BaseActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	private NewsListAdapter mNewListAdapter;
	private RecyclerView mRecyclerView;
	@Override
	protected void initVars() {
		super.initVars();
		mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_main;
	}

	@Override
	protected boolean hasToolbar() {
		return true;
	}

	@Override
	protected void initData() {
		super.initData();
		HttpHelper.getInstance().zhihuDailyListLatest(this, new Callback<ZhihuDaily>() {
			@Override
			public void onSuccess(ZhihuDaily data, String msg) {
				if(data == null) return;
				mNewListAdapter = new NewsListAdapter(MainActivity.this, data.getStories());
				mRecyclerView.setAdapter(mNewListAdapter);
				mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
			}

			@Override
			public void onFailure(int code, String msg) {

			}
		});
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
//		if (id == R.id.action_settings) {
//			return true;
//		}

		Log.e("MainActivity", "onOptionsItemSelected");

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_zhihu) {
			getToolbar().setTitle("知乎");
		} else if (id == R.id.nav_zhihu_daily) {
			getToolbar().setTitle("知乎日报");
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}
