package io.capsella.find.exchange.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;

import io.capsella.find.exchange.R;
import io.capsella.find.exchange.fragment.AccountsFragment;
import io.capsella.find.exchange.fragment.CardFragment;
import io.capsella.find.exchange.fragment.MarketFragment;
import io.capsella.find.exchange.fragment.MoreFragment;
import io.capsella.find.exchange.utility.Constants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    SpaceNavigationView spaceNavigationView;
    ViewPager viewPager;
    View spaceNavigationViewShadow;

    ViewPagerAdapter viewPagerAdapter;
    private ArrayList<Fragment> fragments;
    ToggleViewVisibilityBroadcastReceiver toggleViewVisibilityBroadcastReceiver;

    Typeface robotoMedium;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        robotoMedium = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");
        toggleViewVisibilityBroadcastReceiver = new ToggleViewVisibilityBroadcastReceiver();

        initViews(savedInstanceState);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(toggleViewVisibilityBroadcastReceiver, new IntentFilter(Constants.Broadcast_TOGGLE_VIEW_VISIBILITY_BROADCAST));
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(toggleViewVisibilityBroadcastReceiver);
    }

    private void initViews(Bundle savedInstanceState) {

        //ViewPager
        viewPager = findViewById(R.id.view_pager);
        spaceNavigationViewShadow = findViewById(R.id.space_navigation_view_shadow);

        //SpaceNavigationView
        spaceNavigationView = findViewById(R.id.space_navigation_view);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem(getResources().getString(R.string.accounts), R.drawable.space_navigation_view_icon_selector));
        spaceNavigationView.addSpaceItem(new SpaceItem(getResources().getString(R.string.card), R.drawable.menu_card));
        spaceNavigationView.addSpaceItem(new SpaceItem(getResources().getString(R.string.market), R.drawable.menu_market));
        spaceNavigationView.addSpaceItem(new SpaceItem(getResources().getString(R.string.more), R.drawable.menu_more));
        spaceNavigationView.shouldShowFullBadgeText(true);
        spaceNavigationView.setCentreButtonIconColorFilterEnabled(false);
        spaceNavigationView.setFont(robotoMedium);

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Log.d("onCentreButtonClick ", "onCentreButtonClick");
                spaceNavigationView.shouldShowFullBadgeText(true);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Log.d("onItemClick ", "" + itemIndex + " " + itemName);
                viewPager.setCurrentItem(itemIndex, true);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Log.d("onItemReselected ", "" + itemIndex + " " + itemName);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                spaceNavigationView.changeCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        fragments = new ArrayList<>(4);

        AccountsFragment accountsFragment = AccountsFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.accounts));
        accountsFragment.setArguments(bundle);

        CardFragment cardFragment = CardFragment.newInstance();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.card));
        cardFragment.setArguments(bundle);

        MarketFragment marketFragment = MarketFragment.newInstance();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.market));
        marketFragment.setArguments(bundle);

        MoreFragment moreFragment = MoreFragment.newInstance();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.more));
        moreFragment.setArguments(bundle);

        fragments.add(accountsFragment);
        fragments.add(cardFragment);
        fragments.add(marketFragment);
        fragments.add(moreFragment);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void hideViews() {
//        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

//        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
//        int fabBottomMargin = lp.bottomMargin;
//        mFabButton.animate().translationY(mFabButton.getHeight() + fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();

        spaceNavigationViewShadow.setVisibility(View.GONE);
        spaceNavigationView.animate().translationY(spaceNavigationView.getHeight()).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    private void showViews() {
//        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));

        spaceNavigationViewShadow.setVisibility(View.VISIBLE);
        spaceNavigationView.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> data;

        public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

    private class ToggleViewVisibilityBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(Constants.STATE, Constants.HIDE);

            switch (state) {
                case Constants.HIDE:
                    hideViews();
                    break;
                case Constants.SHOW:
                    showViews();
                    break;
            }
        }
    }
}
