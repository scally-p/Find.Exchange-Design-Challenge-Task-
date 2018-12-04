package io.capsella.find.exchange.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.capsella.find.exchange.R;
import io.capsella.find.exchange.adapter.AccountAdapter;
import io.capsella.find.exchange.utility.HelperFunctions;

public class AccountsFragment extends Fragment {

    TextView totalCardAmountLabel;
    TextView totalCardAmountTxt;
    TextView spentAmountTxt;
    TextView myAccountsTxt;
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

        totalCardAmountLabel = rootView.findViewById(R.id.total_card_amount_label);
        totalCardAmountTxt = rootView.findViewById(R.id.total_card_amount_txt);
        spentAmountTxt = rootView.findViewById(R.id.spent_amount);
        myAccountsTxt = rootView.findViewById(R.id.my_accounts);
        recyclerView = rootView.findViewById(R.id.recycler_view);

        totalCardAmountLabel.setTypeface(robotoMedium);
        totalCardAmountTxt.setTypeface(robotoMedium);
        spentAmountTxt.setTypeface(robotoRegular);
        myAccountsTxt.setTypeface(robotoMedium);

        setData();
    }

    private void setData() {

        //Set Header
        int cardsCount = 2;
        double totalCardAmount = 9566.15;
        double spentAmount = 1322.47;
        String currency = "£";

        totalCardAmountLabel.setText(getString(R.string.total_card_amount_label, String.valueOf(cardsCount)));
        totalCardAmountTxt.setText(getSizeSpannableText(currency + HelperFunctions.formatDecimal(String.valueOf(totalCardAmount))));
        spentAmountTxt.setText(getFullSpannableText(getString(R.string.spent_amount, currency + HelperFunctions.formatDecimal(String.valueOf(spentAmount)))));

        //Set Account Cards
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new AccountAdapter(requireContext(), HelperFunctions.getDummyAccountsData()));
    }

    private SpannableString getSizeSpannableText(String text) {

        SpannableString spannableString = new SpannableString(text);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.7f);
        spannableString.setSpan(sizeSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    private SpannableString getFullSpannableText(String text) {

        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.color_6));
        StyleSpan styleSpan = new StyleSpan(android.graphics.Typeface.BOLD);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.7f);
        spannableString.setSpan(foregroundColorSpan, 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(styleSpan, 8, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(sizeSpan, 8, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}