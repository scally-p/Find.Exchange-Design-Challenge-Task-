package io.capsella.find.exchange.model;

public class Account {

    int id;
    int accountType;
    String currencyLabel;
    String currency;
    int accountLogo;
    int accountIcon;
    String accountName;
    String accountNameSubTxt;
    String availableAmount;
    String totalAmount;
    int noOfCurrencies;
    String overdraftAmount;
    boolean hasTransactions;

    public Account() {
    }

    public Account(int id, int accountType, String currencyLabel, String currency, int accountLogo, int accountIcon, String accountName, String accountNameSubTxt, String totalAmount, int noOfCurrencies, String availableAmount, String overdraftAmount, boolean hasTransactions) {
        this.id = id;
        this.accountType = accountType;
        this.currencyLabel = currencyLabel;
        this.currency = currency;
        this.accountLogo = accountLogo;
        this.accountIcon = accountIcon;
        this.accountName = accountName;
        this.accountNameSubTxt = accountNameSubTxt;
        this.totalAmount = totalAmount;
        this.noOfCurrencies = noOfCurrencies;
        this.availableAmount = availableAmount;
        this.overdraftAmount = overdraftAmount;
        this.hasTransactions = hasTransactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getCurrencyLabel() {
        return currencyLabel;
    }

    public void setCurrencyLabel(String currencyLabel) {
        this.currencyLabel = currencyLabel;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAccountLogo() {
        return accountLogo;
    }

    public void setAccountLogo(int accountLogo) {
        this.accountLogo = accountLogo;
    }

    public int getAccountIcon() {
        return accountIcon;
    }

    public void setAccountIcon(int accountIcon) {
        this.accountIcon = accountIcon;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNameSubTxt() {
        return accountNameSubTxt;
    }

    public void setAccountNameSubTxt(String accountNameSubTxt) {
        this.accountNameSubTxt = accountNameSubTxt;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getNoOfCurrencies() {
        return noOfCurrencies;
    }

    public void setNoOfCurrencies(int noOfCurrencies) {
        this.noOfCurrencies = noOfCurrencies;
    }

    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(String overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    public boolean isHasTransactions() {
        return hasTransactions;
    }

    public void setHasTransactions(boolean hasTransactions) {
        this.hasTransactions = hasTransactions;
    }
}
