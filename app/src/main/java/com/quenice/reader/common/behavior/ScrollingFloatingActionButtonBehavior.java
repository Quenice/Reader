package com.quenice.reader.common.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiubb on 2017/2/16.
 */

public class ScrollingFloatingActionButtonBehavior extends FloatingActionButton.Behavior {
	public ScrollingFloatingActionButtonBehavior(Context context, AttributeSet attrs) {
		super();
	}

	@Override
	public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
	                                   final View directTargetChild, final View target, final int nestedScrollAxes) {
		// Ensure we react to vertical scrolling
		return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
	}

	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
		if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
			child.hide();
		} else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
			child.show();
		}
	}
}
