package com.realty.drake.bidjepeyim.models;

import org.parceler.Parcel;

/**
 * Created by drake on 8/8/18
 */

@Parcel
public class Statistic {
    String ministry;
    double credit, expense, balance;

    public Statistic() {}//For firebase

    public Statistic(String ministry,
                     double credit,
                     double expense,
                     double balance) {
        this.ministry = ministry;
        this.credit = credit;
        this.expense = expense;
        this.balance = balance;
    }

    public String getMinistry() {
        return ministry;
    }

    public void setMinistry(String ministry) {
        this.ministry = ministry;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
