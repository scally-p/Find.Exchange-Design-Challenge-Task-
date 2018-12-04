package io.capsella.find.exchange.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.capsella.find.exchange.R;
import io.capsella.find.exchange.adapter.AccountAdapter;
import io.capsella.find.exchange.model.Account;
import io.capsella.find.exchange.utility.Constants;
import io.capsella.find.exchange.utility.HelperFunctions;
import io.capsella.find.exchange.utility.HidingScrollListener;

public class CardFragment extends Fragment {

    Toolbar toolbar;
    ActionBar actionBar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RecyclerView recyclerView;
    TextView addAccount;

    Typeface robotoBold;
    Typeface robotoMedium;
    Typeface robotoRegular;

    public static CardFragment newInstance() {
        return new CardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);

        robotoBold = Typeface.createFromAsset(requireContext().getAssets(), "Roboto-Bold.ttf");
        robotoMedium = Typeface.createFromAsset(requireContext().getAssets(), "Roboto-Medium.ttf");
        robotoRegular = Typeface.createFromAsset(requireContext().getAssets(), "Roboto-Regular.ttf");

        initViews(rootView);

        return rootView;
    }

    private void initViews(View rootView) {

        toolbar = rootView.findViewById(R.id.toolbar);
        collapsingToolbarLayout = rootView.findViewById(R.id.collapsing_toolbar_layout);
        actionBar = ((AppCompatActivity) requireContext()).getSupportActionBar();
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.my_accounts));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.black));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.black));
        collapsingToolbarLayout.setCollapsedTitleTypeface(robotoBold);
        collapsingToolbarLayout.setExpandedTitleTypeface(robotoBold);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        addAccount = rootView.findViewById(R.id.add_account);
        addAccount.setTypeface(robotoRegular);

        setData();
    }

    private void setData() {

        ArrayList<Account> accounts = HelperFunctions.getDummyAccountsData();
        accounts.remove(0);

        //Set Account Cards
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new AccountAdapter(requireContext(), accounts));
        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                requireContext().sendBroadcast(new Intent(Constants.Broadcast_TOGGLE_VIEW_VISIBILITY_BROADCAST).putExtra(Constants.STATE, Constants.HIDE));
            }

            @Override
            public void onShow() {
                requireContext().sendBroadcast(new Intent(Constants.Broadcast_TOGGLE_VIEW_VISIBILITY_BROADCAST).putExtra(Constants.STATE, Constants.SHOW));
            }
        });
    }
}