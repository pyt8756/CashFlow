package com.pyt.cashflow.bean;

/**
 * Created by pengyutao on 2017/7/27.
 */

public class ProfessionBean {

    /**
     * title : Airline Pilot
     * salary : 9500
     * taxes : 2350
     * mortgage : 143000
     * mortgagePayment : 1330
     * car : 15000
     * carPayment : 300
     * credit : 22000
     * creditPayment : 660
     * retail : 1000
     * retailPayment : 50
     * other : 2210
     * perChild : 480
     * savings : 400
     * v1 : 1
     * v2 : 1
     * school : 12000
     * schoolPayment : 60
     */

//    {
//            "v1": "1"
//    },

    private String title;//职业
    private String salary;//薪水
    private String taxes;//税金
    private String mortgage;//住房抵押贷款
    private String mortgagePayment;//住房抵押贷款/房租支出
    private String car;//购车贷款
    private String carPayment;//购车贷款支出
    private String credit;//信用卡
    private String creditPayment;//信用卡支出
    private String retail;//额外负债
    private String retailPayment;//额外负债支出
    private String other;//其他支出
    private String perChild;//每个孩子支出
    private String savings;//储蓄
    private String v1;//
    private String v2;//
    private String school;//教育贷款
    private String schoolPayment;//教育贷款支出

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public String getMortgage() {
        return mortgage;
    }

    public void setMortgage(String mortgage) {
        this.mortgage = mortgage;
    }

    public String getMortgagePayment() {
        return mortgagePayment;
    }

    public void setMortgagePayment(String mortgagePayment) {
        this.mortgagePayment = mortgagePayment;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCarPayment() {
        return carPayment;
    }

    public void setCarPayment(String carPayment) {
        this.carPayment = carPayment;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(String creditPayment) {
        this.creditPayment = creditPayment;
    }

    public String getRetail() {
        return retail;
    }

    public void setRetail(String retail) {
        this.retail = retail;
    }

    public String getRetailPayment() {
        return retailPayment;
    }

    public void setRetailPayment(String retailPayment) {
        this.retailPayment = retailPayment;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPerChild() {
        return perChild;
    }

    public void setPerChild(String perChild) {
        this.perChild = perChild;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchoolPayment() {
        return schoolPayment;
    }

    public void setSchoolPayment(String schoolPayment) {
        this.schoolPayment = schoolPayment;
    }
}
