package io.capsella.find.exchange.utility;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;
import java.util.ArrayList;

import io.capsella.find.exchange.R;
import io.capsella.find.exchange.model.Account;

public class HelperFunctions {

    public static DisplayMetrics getDisplayMetrics(Context context) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        return displayMetrics;
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToSp(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int spToPx(final Context context, final float sp) {
        return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static String getCurrentTimeStamp() {
        return (DateFormat.format("yyyy-MM-dd HH:mm:ss", new java.util.Date()).toString());
    }

    public static ArrayList<Account> getDummyAccountsData() {

        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, 1, "Multi-Currency", "£", R.drawable.account_logo_1, R.drawable.account_logo_3, "Account", "", "6725.21", 4, "6712.80", "0.0", false));
        accounts.add(new Account(1, 2, "GBP", "£", R.drawable.account_logo_2, 0, "Classic Account", "78128321 | 44-62-11", "6725.14", 0, "1697.32", "2500.00", false));
        accounts.add(new Account());

        return accounts;
    }

    public static String formatDecimal(String value) {

        double amount = Double.parseDouble(value);
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(amount);
    }
}