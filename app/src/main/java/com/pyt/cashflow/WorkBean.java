package com.pyt.cashflow;

import com.pyt.cashflow.bean.ProfessionBean;

import java.util.List;

/**
 * Created by pengyutao on 2017/7/27.
 */

public class WorkBean {


    /**
     * xmlData : {"profession":[{"title":"Airline Pilot","salary":"9500","taxes":"2350","mortgage":"143000","mortgagePayment":"1330","car":"15000","carPayment":"300","credit":"22000","creditPayment":"660","retail":"1000","retailPayment":"50","other":"2210","perChild":"480","savings":"400","v1":"1","v2":"1","school":"12000","schoolPayment":"60"},{"title":"Airline Pilot","salary":"9500","taxes":"2000","mortgage":"90000","mortgagePayment":"1000","car":"15000","carPayment":"300","credit":"22000","creditPayment":"700","other":"2000","perChild":"400","savings":"2500","v2":"1"},{"title":"Business Manager","salary":"4600","taxes":"910","mortgage":"75000","mortgagePayment":"700","school":"12000","schoolPayment":"60","car":"6000","carPayment":"120","credit":"3000","creditPayment":"90","retail":"1000","retailPayment":"50","other":"1000","perChild":"240","savings":"400","v1":"1"},{"title":"Business Manager","salary":"4600","taxes":"900","mortgage":"75000","mortgagePayment":"700","school":"12000","schoolPayment":"100","car":"6000","carPayment":"100","credit":"4000","creditPayment":"200","other":"1000","perChild":"300","savings":"400","v2":"1"},{"title":"Doctor (MD)","salary":"13200","taxes":"3420","mortgage":"202000","mortgagePayment":"1900","school":"150000","schoolPayment":"750","car":"19000","carPayment":"380","credit":"9000","creditPayment":"270","retail":"1000","retailPayment":"50","other":"2880","perChild":"640","savings":"400","v1":"1"},{"title":"Doctor (MD)","salary":"13200","taxes":"3200","mortgage":"202000","mortgagePayment":"1900","school":"150000","schoolPayment":"700","car":"19000","carPayment":"300","credit":"10000","creditPayment":"200","other":"2000","perChild":"700","savings":"3500","v2":"1"},{"title":"Engineer","salary":"4900","taxes":"1050","mortgage":"75000","mortgagePayment":"700","school":"12000","schoolPayment":"60","car":"7000","carPayment":"140","credit":"4000","creditPayment":"120","retail":"1000","retailPayment":"50","other":"1090","perChild":"250","savings":"400","v1":"1"},{"title":"Engineer","salary":"4900","taxes":"1000","mortgage":"75000","mortgagePayment":"700","school":"12000","schoolPayment":"100","car":"7000","carPayment":"200","credit":"5000","creditPayment":"200","other":"1000","perChild":"200","savings":"400","v2":"1"},{"title":"Janitor","salary":"1600","taxes":"280","mortgage":"20000","mortgagePayment":"200","car":"4000","carPayment":"60","credit":"2000","creditPayment":"60","retail":"1000","retailPayment":"50","other":"300","perChild":"70","savings":"560","v1":"1"},{"title":"Janitor","salary":"1600","taxes":"300","mortgage":"20000","mortgagePayment":"200","car":"4000","carPayment":"100","credit":"3000","creditPayment":"100","other":"300","perChild":"100","savings":"600","v2":"1"},{"title":"Lawyer","salary":"7500","taxes":"1830","mortgage":"115000","mortgagePayment":"1100","school":"78000","schoolPayment":"390","car":"11000","carPayment":"220","credit":"6000","creditPayment":"180","retail":"1000","retailPayment":"50","other":"1650","perChild":"380","savings":"400","v1":"1"},{"title":"Lawyer","salary":"7500","taxes":"1800","mortgage":"115000","mortgagePayment":"1100","school":"78000","schoolPayment":"300","car":"11000","carPayment":"200","credit":"7000","creditPayment":"200","other":"1500","perChild":"400","savings":"2000","v2":"1"},{"title":"Mechanic","salary":"2000","taxes":"360","mortgage":"31000","mortgagePayment":"300","car":"3000","carPayment":"60","credit":"2000","creditPayment":"60","retail":"1000","retailPayment":"50","other":"450","perChild":"110","savings":"670","v1":"1"},{"title":"Mechanic","salary":"2000","taxes":"400","mortgage":"31000","mortgagePayment":"300","car":"3000","carPayment":"100","credit":"3000","creditPayment":"100","other":"400","perChild":"100","savings":"700","v2":"1"},{"title":"Nurse","salary":"3100","taxes":"600","mortgage":"47000","mortgagePayment":"400","school":"6000","schoolPayment":"30","car":"5000","carPayment":"100","credit":"3000","creditPayment":"90","retail":"1000","retailPayment":"50","other":"710","perChild":"170","savings":"480","v1":"1"},{"title":"Nurse","salary":"3100","taxes":"600","mortgage":"47000","mortgagePayment":"400","school":"6000","schoolPayment":"100","car":"5000","carPayment":"100","credit":"4000","creditPayment":"200","other":"600","perChild":"200","savings":"500","v2":"1"},{"title":"Police Officer","salary":"3000","taxes":"580","mortgage":"46000","mortgagePayment":"400","car":"5000","carPayment":"100","credit":"2000","creditPayment":"60","retail":"1000","retailPayment":"50","other":"690","perChild":"160","savings":"520","v1":"1"},{"title":"Police Officer","salary":"3000","taxes":"600","mortgage":"46000","mortgagePayment":"400","car":"5000","carPayment":"100","credit":"3000","creditPayment":"100","other":"700","perChild":"200","savings":"500","v2":"1"},{"title":"Secretary","salary":"2500","taxes":"460","mortgage":"38000","mortgagePayment":"400","car":"4000","carPayment":"80","credit":"2000","creditPayment":"60","retail":"1000","retailPayment":"50","other":"570","perChild":"140","savings":"710","v1":"1"},{"title":"Secretary","salary":"2500","taxes":"500","mortgage":"38000","mortgagePayment":"400","car":"4000","carPayment":"100","credit":"3000","creditPayment":"100","other":"600","perChild":"100","savings":"700","v2":"1"},{"title":"Teacher (K-12)","salary":"3300","taxes":"630","mortgage":"50000","mortgagePayment":"500","school":"12000","schoolPayment":"60","car":"5000","carPayment":"100","credit":"3000","creditPayment":"90","retail":"1000","retailPayment":"50","other":"760","perChild":"180","savings":"400","v1":"1"},{"title":"Teacher (K-12)","salary":"3300","taxes":"500","mortgage":"50000","mortgagePayment":"500","school":"12000","schoolPayment":"100","car":"5000","carPayment":"100","credit":"4000","creditPayment":"200","other":"700","perChild":"200","savings":"400","v2":"1"},{"title":"Truck Driver","salary":"2500","taxes":"460","mortgage":"38000","mortgagePayment":"400","car":"4000","carPayment":"80","credit":"2000","creditPayment":"60","retail":"1000","retailPayment":"50","other":"570","perChild":"140","savings":"750","v1":"1"},{"title":"Truck Driver","salary":"2500","taxes":"500","mortgage":"38000","mortgagePayment":"400","car":"4000","carPayment":"100","credit":"3000","creditPayment":"100","other":"600","perChild":"200","savings":"800","v2":"1"}],"dream":[{"title":"Buy a Forest","price":"250000","v1":"1"},{"title":"Pro Team Box Seats","price":"200000","v1":"1"},{"title":"Ancient Asian Cities","price":"150000","v1":"1"},{"title":"Stock Market for Kids","price":"125000","v1":"1"},{"title":"Yacht Racing","price":"150000","v1":"1"},{"title":"Cannes Film Festival","price":"125000","v1":"1"},{"title":"Private Fishing Cabin on a Montana Lake","price":"100000","v1":"1"},{"title":"Park Named After You","price":"225000","v1":"1"},{"title":"Run for Mayor","price":"125000","v1":"1"},{"title":"Gift of Faith","price":"175000","v1":"1"},{"title":"Heli-Ski the Swiss Alps","price":"150000","v1":"1"},{"title":"Dinner with the President","price":"100000","v1":"1"},{"title":"Research Center for Cancer and AIDS","price":"225000","v1":"1"},{"title":"Seven Wonders of the World","price":"200000","v1":"1"},{"title":"Save the Ocean Mammals","price":"125000","v1":"1"},{"title":"Be a \"Jet-Setter\"","price":"250000","v1":"1"},{"title":"Golf Around the World","price":"150000","v1":"1"},{"title":"Kid's Library","price":"175000","v1":"1"},{"title":"South Sea Island Fantasy","price":"100000","v1":"1"},{"title":"Capitalists' Peace Corps","price":"200000","v1":"1"},{"title":"Cruise the Mediterranean on a Private Yacht","price":"100000","v1":"1"},{"title":"Mini-Farm in the City","price":"150000","v1":"1"},{"title":"African Photo Safari","price":"100000","v1":"1"}],"stock":[{"title":"2BIG","dividend":"1","v1":"1","interest":"1","v2":"1"},{"title":"CD","interest":"1","v1":"1"},{"title":"GRO4US","v1":"1","v2":"1"},{"title":"OK4U","v1":"1","v2":"1"},{"title":"ON2U","v1":"1","v2":"1"},{"title":"MYT4U","v1":"1","v2":"1"}],"realEstate":[{"title":"Condo 2Br/1Ba","order":"1","repair":"1","v1":"1","v2":"1","inflation":"1","units":"2"},{"title":"House 2Br/1Ba","order":"2","repair":"1","v2":"1"},{"title":"House 3Br/2Ba","order":"3","inflation":"1","repair":"1","v1":"1"},{"title":"House 3Br/2Ba","order":"3","repair":"1","v2":"1"},{"title":"Duplex","order":"4","repair":"1","units":"2","v1":"1","v2":"1"},{"title":"4-plex","order":"5","repair":"1","units":"4","v1":"1","v2":"1"},{"title":"8-plex","order":"6","repair":"1","units":"8","v1":"1","v2":"1"},{"title":"Apartment Complex","order":"7","repair":"1","units":"-1","v1":"1"},{"title":"Apartment Home","order":"7","repair":"1","units":"-1","v2":"1"},{"title":"Land (10 acres)","order":"8","repair":"0","v1":"1"},{"title":"Land (20 acres)","order":"9","repair":"0","v1":"1"}],"business":[{"title":"Auto Dealer","limited":"1","v1":"1","v2":"1"},{"title":"Auto Wash","v1":"1","v2":"1"},{"title":"Bed and Breakfast","v1":"1"},{"title":"Car Wash","v1":"1","v2":"1"},{"title":"Coin Telephone","v1":"1"},{"title":"Doctor Office","limited":"1","v1":"1","v2":"1"},{"title":"Laundromat","v1":"1","v2":"1"},{"title":"Pinball Machines","v1":"1","v2":"1"},{"title":"Pizza Chain","limited":"1","v1":"1","v2":"1"},{"title":"Pizza Franchise","v1":"1","v2":"1"},{"title":"Sandwich Shop","limited":"1","v1":"1","v2":"1"},{"title":"Shopping Mall","v1":"1"},{"title":"Software Company","v1":"1"},{"title":"Widget Company","v1":"1"}],"gold":[{"title":"Spanish Gold","cost":"500","coins":"1","v1":"1","v2":"1"},{"title":"Krugerrands","cost":"3000","coins":"10","v1":"1"},{"title":"Coin Collector Liquidates","cost":"1000","coins":"5","v2":"1"},{"title":"Friend Needs Fast Cash","cost":"3000","coins":"10","v2":"1"}],"doodad":[{"title":"Big Screen TV","loan":"0","loanPayment":"0","down":"4000","v1":"1"},{"title":"Big Screen TV with Loan","loan":"4000","loanPayment":"120","down":"0","v1":"1"},{"title":"Boat","loan":"0","loanPayment":"0","down":"17000","v1":"1"},{"title":"Boat with Loan","loan":"17000","loanPayment":"340","down":"1000","v1":"1"}],"fastBusiness":[{"title":"60 Unit Apartment Building","price":"300000","cashflow":"8000","v1":"1","v2":"1"},{"title":"200 Unit Mini Storage","price":"200000","cashflow":"6000","v1":"1","v2":"1"},{"title":"400 Unit Apartment Building","price":"300000","cashflow":"8000","v2":"1"},{"title":"App Development Company","price":"150000","cashflow":"5000","v2":"1"},{"title":"Assisted Living Center","price":"400000","cashflow":"8000","v2":"1"},{"title":"Auto Repair Shop","price":"150000","cashflow":"6000","v1":"1","v2":"1"},{"title":"Beauty Salon","price":"250000","cashflow":"10000","v1":"1","v2":"1"},{"title":"Burger Franchise","price":"300000","cashflow":"9500","v1":"1"},{"title":"Burger Shop","price":"300000","cashflow":"9500","v2":"1"},{"title":"Chicken Franchise","price":"300000","cashflow":"10000","v1":"1"},{"title":"Coffee Shop","price":"120000","cashflow":"5000","v2":"1"},{"title":"Collectibles Store","price":"100000","cashflow":"3000","v2":"1"},{"title":"Dry Cleaner","price":"100000","cashflow":"3000","v1":"1"},{"title":"Dry Cleaning Business","price":"100000","cashflow":"3000","v2":"1"},{"title":"Dry Dock Storage","price":"100000","cashflow":"3000","v2":"1"},{"title":"Extreme Sports Equipment Rental","price":"150000","cashflow":"5000","v2":"1"},{"title":"Family Restaurant","price":"300000","cashflow":"14000","v1":"1","v2":"1"},{"title":"Fried Chicken Restaurant","price":"300000","cashflow":"10000","v2":"1"},{"title":"Frozen Yogurt Store","price":"120000","cashflow":"5000","v2":"1"},{"title":"Heat and A/C Service","price":"200000","cashflow":"10000","v1":"1","v2":"1"},{"title":"Hobby Supply Store","price":"100000","cashflow":"3000","v2":"1"},{"title":"Island Vacation Rental","price":"100000","cashflow":"3000","v2":"1"},{"title":"Mobile Home Trailer Park","price":"400000","cashflow":"9000","v2":"1"},{"title":"Movie Theater","price":"150000","cashflow":"6000","v2":"1"},{"title":"Pizza Franchise","price":"225000","cashflow":"7000","v1":"1"},{"title":"Pizza Shop","price":"225000","cashflow":"7000","v2":"1"},{"title":"Private Wildlife Preserve","price":"120000","cashflow":"5000","v2":"1"},{"title":"Quick Food Marts","price":"120000","cashflow":"5000","v1":"1"},{"title":"Quick Food Market","price":"120000","cashflow":"5000","v2":"1"},{"title":"Research Diseases","price":"300000","cashflow":"8000","v2":"1"},{"title":"Ticket Sales Company","price":"150000","cashflow":"5000","v2":"1"},{"title":"T-Shirt Stores","price":"200000","cashflow":"8000","v1":"1"},{"title":"Truck Parts Maker","price":"150000","cashflow":"5000","v1":"1"},{"title":"Build Pro Golf Course","price":"150000","cashflow":"6000","v2":"1"}],"fastInvestment":[{"title":"Bio-Tech Co. IPO","price":"50000","cash":"500000","cashflow":"0","v1":"1","v2":"1"},{"title":"Buy A Gold Mine","price":"150000","cashflow":"25000","cash":"0","v1":"1"},{"title":"Cookware Infomercial","price":"225000","cashflow":"50000","cash":"0","v1":"1"},{"title":"Russian Oil Deal","price":"300000","cashflow":"75000","cash":"0","v1":"1"},{"title":"Foreign Oil Deal","price":"300000","cashflow":"75000","cash":"0","v2":"1"},{"title":"Software Company IPO","price":"25000","cashflow":"0","cash":"500000","v1":"1","v2":"1"}]}
     */

    private List<ProfessionBean> profession;
    private List<DreamBean> dream;
    private List<StockBean> stock;
    private List<RealEstateBean> realEstate;
    private List<BusinessBean> business;
    private List<GoldBean> gold;
    private List<DoodadBean> doodad;
    private List<FastBusinessBean> fastBusiness;
    private List<FastInvestmentBean> fastInvestment;

    public List<ProfessionBean> getProfession() {
        return profession;
    }

    public void setProfession(List<ProfessionBean> profession) {
        this.profession = profession;
    }

    public List<DreamBean> getDream() {
        return dream;
    }

    public void setDream(List<DreamBean> dream) {
        this.dream = dream;
    }

    public List<StockBean> getStock() {
        return stock;
    }

    public void setStock(List<StockBean> stock) {
        this.stock = stock;
    }

    public List<RealEstateBean> getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(List<RealEstateBean> realEstate) {
        this.realEstate = realEstate;
    }

    public List<BusinessBean> getBusiness() {
        return business;
    }

    public void setBusiness(List<BusinessBean> business) {
        this.business = business;
    }

    public List<GoldBean> getGold() {
        return gold;
    }

    public void setGold(List<GoldBean> gold) {
        this.gold = gold;
    }

    public List<DoodadBean> getDoodad() {
        return doodad;
    }

    public void setDoodad(List<DoodadBean> doodad) {
        this.doodad = doodad;
    }

    public List<FastBusinessBean> getFastBusiness() {
        return fastBusiness;
    }

    public void setFastBusiness(List<FastBusinessBean> fastBusiness) {
        this.fastBusiness = fastBusiness;
    }

    public List<FastInvestmentBean> getFastInvestment() {
        return fastInvestment;
    }

    public void setFastInvestment(List<FastInvestmentBean> fastInvestment) {
        this.fastInvestment = fastInvestment;
    }

    public static class DreamBean {
        /**
         * title : Buy a Forest
         * price : 250000
         * v1 : 1
         */

        private String title;
        private String price;
        private String v1;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getV1() {
            return v1;
        }

        public void setV1(String v1) {
            this.v1 = v1;
        }
    }

    public static class StockBean {
        /**
         * title : 2BIG
         * dividend : 1
         * v1 : 1
         * interest : 1
         * v2 : 1
         */

        private String title;
        private String dividend;
        private String v1;
        private String interest;
        private String v2;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDividend() {
            return dividend;
        }

        public void setDividend(String dividend) {
            this.dividend = dividend;
        }

        public String getV1() {
            return v1;
        }

        public void setV1(String v1) {
            this.v1 = v1;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getV2() {
            return v2;
        }

        public void setV2(String v2) {
            this.v2 = v2;
        }
    }

    public static class RealEstateBean {
        /**
         * title : Condo 2Br/1Ba
         * order : 1
         * repair : 1
         * v1 : 1
         * v2 : 1
         * inflation : 1
         * units : 2
         */

        private String title;
        private String order;
        private String repair;
        private String v1;
        private String v2;
        private String inflation;
        private String units;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getRepair() {
            return repair;
        }

        public void setRepair(String repair) {
            this.repair = repair;
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

        public String getInflation() {
            return inflation;
        }

        public void setInflation(String inflation) {
            this.inflation = inflation;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }
    }

    public static class BusinessBean {
        /**
         * title : Auto Dealer
         * limited : 1
         * v1 : 1
         * v2 : 1
         */

        private String title;
        private String limited;
        private String v1;
        private String v2;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLimited() {
            return limited;
        }

        public void setLimited(String limited) {
            this.limited = limited;
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
    }

    public static class GoldBean {
        /**
         * title : Spanish Gold
         * cost : 500
         * coins : 1
         * v1 : 1
         * v2 : 1
         */

        private String title;
        private String cost;
        private String coins;
        private String v1;
        private String v2;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getCoins() {
            return coins;
        }

        public void setCoins(String coins) {
            this.coins = coins;
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
    }

    public static class DoodadBean {
        /**
         * title : Big Screen TV
         * loan : 0
         * loanPayment : 0
         * down : 4000
         * v1 : 1
         */

        private String title;
        private String loan;
        private String loanPayment;
        private String down;
        private String v1;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLoan() {
            return loan;
        }

        public void setLoan(String loan) {
            this.loan = loan;
        }

        public String getLoanPayment() {
            return loanPayment;
        }

        public void setLoanPayment(String loanPayment) {
            this.loanPayment = loanPayment;
        }

        public String getDown() {
            return down;
        }

        public void setDown(String down) {
            this.down = down;
        }

        public String getV1() {
            return v1;
        }

        public void setV1(String v1) {
            this.v1 = v1;
        }
    }

    public static class FastBusinessBean {
        /**
         * title : 60 Unit Apartment Building
         * price : 300000
         * cashflow : 8000
         * v1 : 1
         * v2 : 1
         */

        private String title;
        private String price;
        private String cashflow;
        private String v1;
        private String v2;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCashflow() {
            return cashflow;
        }

        public void setCashflow(String cashflow) {
            this.cashflow = cashflow;
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
    }

    public static class FastInvestmentBean {
        /**
         * title : Bio-Tech Co. IPO
         * price : 50000
         * cash : 500000
         * cashflow : 0
         * v1 : 1
         * v2 : 1
         */

        private String title;
        private String price;
        private String cash;
        private String cashflow;
        private String v1;
        private String v2;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCash() {
            return cash;
        }

        public void setCash(String cash) {
            this.cash = cash;
        }

        public String getCashflow() {
            return cashflow;
        }

        public void setCashflow(String cashflow) {
            this.cashflow = cashflow;
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
    }
}
