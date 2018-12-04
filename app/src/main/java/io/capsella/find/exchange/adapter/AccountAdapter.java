package io.capsella.find.exchange.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.capsella.find.exchange.R;
import io.capsella.find.exchange.model.Account;
import io.capsella.find.exchange.utility.HelperFunctions;

public class AccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Account> itemArrayList;

    private Typeface robotoBold;
    private Typeface robotoMedium;
    private Typeface robotoRegular;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ACCOUNT_ADD = 1;
    private static final int TYPE_ACCOUNT_MULTI_CURRENCY = 2;
    private static final int TYPE_ACCOUNT_NORMAL = 3;

    public AccountAdapter(Context context, ArrayList<Account> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
        robotoBold = Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
        robotoMedium = Typeface.createFromAsset(context.getAssets(), "Roboto-Medium.ttf");
        robotoRegular = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mainLayout;
        TextView totalCardAmountLabel;
        TextView totalCardAmountTxt;
        TextView spentAmountTxt;
        TextView myAccountsTxt;

        private HeaderViewHolder(View itemView) {
            super(itemView);
            this.mainLayout = itemView.findViewById(R.id.main_layout);
            this.totalCardAmountLabel = itemView.findViewById(R.id.total_card_amount_label);
            this.totalCardAmountTxt = itemView.findViewById(R.id.total_card_amount_txt);
            this.spentAmountTxt = itemView.findViewById(R.id.spent_amount);
            this.myAccountsTxt = itemView.findViewById(R.id.my_accounts);
        }
    }

    private class AddNewAccountViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mainLayout;
        TextView addNewAccount;

        private AddNewAccountViewHolder(View itemView) {
            super(itemView);
            this.mainLayout = itemView.findViewById(R.id.main_layout);
            this.addNewAccount = itemView.findViewById(R.id.add_new_account);
        }
    }

    private class AccountMultiCurrencyViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mainLayout;
        TextView currencyLabelTxt;
        ImageView accountLogo;
        ImageView accountIcon;
        TextView accountNameTxt;
        TextView totalAmountTxt;
        TextView noOfCurrenciesTxt;
        TextView availableAmountTxt;

        private AccountMultiCurrencyViewHolder(View itemView) {
            super(itemView);
            this.mainLayout = itemView.findViewById(R.id.main_layout);
            this.currencyLabelTxt = itemView.findViewById(R.id.currency_label);
            this.accountLogo = itemView.findViewById(R.id.account_logo);
            this.accountIcon = itemView.findViewById(R.id.account_icon);
            this.accountNameTxt = itemView.findViewById(R.id.account_name);
            this.totalAmountTxt = itemView.findViewById(R.id.total_amount);
            this.noOfCurrenciesTxt = itemView.findViewById(R.id.no_of_currencies);
            this.availableAmountTxt = itemView.findViewById(R.id.available_amount);
        }
    }

    private class AccountNormalViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mainLayout;
        TextView currencyLabelTxt;
        ImageView accountLogo;
        TextView accountNameTxt;
        TextView accountNameSubTxt;
        TextView totalAmountTxt;
        TextView availableAmountTxt;
        TextView overdraftAmountTxt;
        TextView noTransactionsTxt;

        private AccountNormalViewHolder(View itemView) {
            super(itemView);
            this.mainLayout = itemView.findViewById(R.id.main_layout);
            this.currencyLabelTxt = itemView.findViewById(R.id.currency_label);
            this.accountLogo = itemView.findViewById(R.id.account_logo);
            this.accountNameTxt = itemView.findViewById(R.id.account_name);
            this.accountNameSubTxt = itemView.findViewById(R.id.account_name_sub);
            this.totalAmountTxt = itemView.findViewById(R.id.total_amount);
            this.availableAmountTxt = itemView.findViewById(R.id.available_amount);
            this.overdraftAmountTxt = itemView.findViewById(R.id.overdraft_amount);
            this.noTransactionsTxt = itemView.findViewById(R.id.no_transactions_txt);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (itemArrayList.get(position).getAccountType() == TYPE_HEADER) {
            return TYPE_HEADER;
        } else if (itemArrayList.get(position).getAccountType() == TYPE_ACCOUNT_ADD) {
            return TYPE_ACCOUNT_ADD;
        } else if (itemArrayList.get(position).getAccountType() == TYPE_ACCOUNT_MULTI_CURRENCY) {
            return TYPE_ACCOUNT_MULTI_CURRENCY;
        } else {
            return TYPE_ACCOUNT_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_account_header, parent, false);
            return new HeaderViewHolder(v);
        } else if (viewType == TYPE_ACCOUNT_ADD) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_account_add_item, parent, false);
            return new AddNewAccountViewHolder(v);
        } else if (viewType == TYPE_ACCOUNT_MULTI_CURRENCY) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_account_multi_currency_item, parent, false);
            return new AccountMultiCurrencyViewHolder(v);
        } else if (viewType == TYPE_ACCOUNT_NORMAL) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_account_normal_item, parent, false);
            return new AccountNormalViewHolder(v);
        }
        return null;
    }

    private Account getItem(int position) {
        return itemArrayList.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Account account = getItem(position);

        if (holder instanceof HeaderViewHolder) {

            final HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            initHeader(viewHolder);

        } else if (holder instanceof AddNewAccountViewHolder) {

            final AddNewAccountViewHolder viewHolder = (AddNewAccountViewHolder) holder;
            initAddAccount(viewHolder);

        } else if (holder instanceof AccountMultiCurrencyViewHolder) {

            final AccountMultiCurrencyViewHolder viewHolder = (AccountMultiCurrencyViewHolder) holder;
            initMultiCurrencyAccount(viewHolder, account);

        } else if (holder instanceof AccountNormalViewHolder) {

            final AccountNormalViewHolder viewHolder = (AccountNormalViewHolder) holder;
            initNormalAccount(viewHolder, account);
        }
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    private void initHeader(HeaderViewHolder viewHolder) {

        viewHolder.totalCardAmountLabel.setTypeface(robotoMedium);
        viewHolder.totalCardAmountTxt.setTypeface(robotoMedium);
        viewHolder.spentAmountTxt.setTypeface(robotoRegular);
        viewHolder.myAccountsTxt.setTypeface(robotoMedium);

        //Set Header
        int cardsCount = 2;
        double totalCardAmount = 9566.15;
        double spentAmount = 1322.47;
        String currency = "£";

        viewHolder.totalCardAmountLabel.setText(context.getResources().getString(R.string.total_card_amount_label, String.valueOf(cardsCount)));
        viewHolder.totalCardAmountTxt.setText(getSizeSpannableText(currency + HelperFunctions.formatDecimal(String.valueOf(totalCardAmount))));
        viewHolder.spentAmountTxt.setText(getFullSpannableText(context.getResources().getString(R.string.spent_amount, currency + HelperFunctions.formatDecimal(String.valueOf(spentAmount))), 1));

        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initAddAccount(AddNewAccountViewHolder viewHolder) {
        viewHolder.addNewAccount.setTypeface(robotoMedium);
        viewHolder.addNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initMultiCurrencyAccount(AccountMultiCurrencyViewHolder viewHolder, Account account) {

        viewHolder.currencyLabelTxt.setText(account.getCurrencyLabel());
        viewHolder.accountLogo.setImageResource(account.getAccountLogo());
        viewHolder.accountIcon.setImageResource(account.getAccountIcon());
        viewHolder.accountNameTxt.setText(account.getAccountName());
        viewHolder.totalAmountTxt.setText(account.getCurrency() + " " + HelperFunctions.formatDecimal(account.getTotalAmount()));
        getSizeSpannableText(viewHolder.totalAmountTxt);
        viewHolder.noOfCurrenciesTxt.setText(context.getString(R.string.no_of_currencies, String.valueOf(account.getNoOfCurrencies())));
        viewHolder.availableAmountTxt.setText(getFullSpannableText(context.getString(R.string.available_amount, account.getCurrency() + HelperFunctions.formatDecimal(account.getAvailableAmount())), 2));

        viewHolder.currencyLabelTxt.setTypeface(robotoBold);
        viewHolder.accountNameTxt.setTypeface(robotoMedium);
        viewHolder.totalAmountTxt.setTypeface(robotoRegular, Typeface.BOLD);
        viewHolder.availableAmountTxt.setTypeface(robotoRegular);

        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initNormalAccount(AccountNormalViewHolder viewHolder, Account account) {

        viewHolder.currencyLabelTxt.setText(account.getCurrencyLabel());
        viewHolder.accountLogo.setImageResource(account.getAccountLogo());
        viewHolder.accountNameTxt.setText(account.getAccountName());
        viewHolder.accountNameSubTxt.setText(account.getAccountNameSubTxt());
        viewHolder.totalAmountTxt.setText(account.getCurrency() + " " + HelperFunctions.formatDecimal(account.getTotalAmount()));
        getSizeSpannableText(viewHolder.totalAmountTxt);
        viewHolder.availableAmountTxt.setText(getFullSpannableText(context.getString(R.string.available_amount, account.getCurrency() + HelperFunctions.formatDecimal(account.getAvailableAmount())), 2));
        viewHolder.overdraftAmountTxt.setText(getFullSpannableText(context.getString(R.string.overdraft_amount, account.getCurrency() + HelperFunctions.formatDecimal(account.getOverdraftAmount())), 2));

        viewHolder.currencyLabelTxt.setTypeface(robotoBold);
        viewHolder.accountNameTxt.setTypeface(robotoMedium);
        viewHolder.accountNameSubTxt.setTypeface(robotoMedium);
        viewHolder.totalAmountTxt.setTypeface(robotoRegular, Typeface.BOLD);
        viewHolder.availableAmountTxt.setTypeface(robotoRegular);
        viewHolder.overdraftAmountTxt.setTypeface(robotoRegular);
        viewHolder.noTransactionsTxt.setTypeface(robotoMedium);

        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getSizeSpannableText(TextView textView) {

        SpannableString value = new SpannableString(textView.getText().toString());

        value.setSpan(new RelativeSizeSpan(0.7f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        value.setSpan(new RelativeSizeSpan(0.7f), value.length() - 2, value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(value);
    }

    private SpannableString getSizeSpannableText(String text) {

        SpannableString spannableString = new SpannableString(text);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.7f);
        spannableString.setSpan(sizeSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    private SpannableString getFullSpannableText(String text, int type) {

        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.color_6));
        StyleSpan styleSpan = new StyleSpan(android.graphics.Typeface.BOLD);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.7f);

        switch (type) {
            case 1:
                spannableString.setSpan(foregroundColorSpan, 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                spannableString.setSpan(styleSpan, 8, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                spannableString.setSpan(sizeSpan, 8, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case 2:
                spannableString.setSpan(foregroundColorSpan, 0, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                spannableString.setSpan(styleSpan, 10, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                spannableString.setSpan(sizeSpan, 10, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
        }

        return spannableString;
    }
}