package com.quenice.reader.base.main.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.quenice.reader.R;
import com.quenice.reader.base.BaseActivity;
import com.quenice.reader.base.main.listener.ScrollToTopListener;
import com.quenice.reader.module.zhihudaily.ui.ZhihuDailyListFragment;

import butterknife.OnClick;

import static com.quenice.reader.R.id.content_main;

public class MainActivity extends BaseActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	private int currentId;
	private Fragment mCurrentFragment;

	@Override
	protected void initVars() {
		super.initVars();

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	protected void initData() {
		super.initData();
		switchNews(R.id.nav_zhihu_daily);
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_main;
	}

	@Override
	protected int initTitle() {
		return R.string.zhihudaily;
	}

	@Override
	protected boolean hasToolbar() {
		return true;
	}

	@OnClick(R.id.fab_up)
	public void onClickUp() {
		if (mCurrentFragment instanceof ScrollToTopListener)
			((ScrollToTopListener) mCurrentFragment).scrollToTop();
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
		switchNews(id);
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	/**
	 * 切换新闻
	 *
	 * @param id
	 */
	private void switchNews(int id) {
		if (currentId == id) return;
		Fragment fragment = null;
		if (id == R.id.nav_zhihu) {
			currentId = id;
			getToolbar().setTitle("知乎");
		} else {
			//default zhihudaily
			currentId = R.id.nav_zhihu_daily;
			getToolbar().setTitle("知乎日报");
			fragment = ZhihuDailyListFragment.getInstance();
		}

		if (fragment == null) return;
		Fragment oldFragment = getSupportFragmentManager().findFragmentById(content_main);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		if (oldFragment == null) {
			transaction.add(content_main, fragment);
		} else {
			transaction.replace(content_main, fragment);
		}
		mCurrentFragment = fragment;
		transaction.commit();
	}
}
