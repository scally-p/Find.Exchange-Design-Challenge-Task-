package io.capsella.find.exchange.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.capsella.find.exchange.R;
import io.capsella.find.exchange.adapter.AccountAdapter;
import io.capsella.find.exchange.utility.Constants;
import io.capsella.find.exchange.utility.HelperFunctions;
import io.capsella.find.exchange.utility.HidingScrollListener;

public class AccountsFragment extends Fragment {

    RecyclerView recyclerView;

    Typeface robotoMedium;
    Typeface robotoRegular;

    public static AccountsFragment newInstance() {
        return new AccountsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_accounts, container, false);

        robotoMedium = Typeface.createFromAsset(requireContext().getAssets(), "Roboto-Medium.ttf");
        robotoRegular = Typeface.createFromAsset(requireContext().getAssets(), "Roboto-Regular.ttf");

        initViews(rootView);

        return rootView;
    }

    private void initViews(View rootView) {

        recyclerView = rootView.findViewById(R.id.recycler_view);
        setData();
    }

    private void setData() {

        //Set Account Cards
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new AccountAdapter(requireContext(), HelperFunctions.getDummyAccountsData()));
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