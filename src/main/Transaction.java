package main;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * This stores a single transaction made on a users account .
 */
public class Transaction implements Comparable<Transaction> {

    private String resourceTitle = "";
    private String resourceType = "";
    private String dateBorrowed = "";
    private String dateReturned = "";
    private String daysOverdue = "";
    private String paymentAmount = "";
    private String datePaidBack = "";

    public Transaction() {

    }

    public Transaction(String paymentAmount, String datePaidBack) {
        this.paymentAmount = paymentAmount;
        this.datePaidBack = datePaidBack;
    }

    public Transaction(String resourceTitle, String resourceType, String dateBorrowed, String dateReturned, String daysOverdue, String paymentAmount) {
        this.resourceTitle = resourceTitle;
        this.resourceType = resourceType;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
        this.daysOverdue = daysOverdue;
        this.paymentAmount = paymentAmount;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    public String getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(String daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getDatePaidBack() {
        return datePaidBack;
    }

    public void setDatePaidBack(String datePaidBack) {
        this.datePaidBack = datePaidBack;
    }

    @Override
    public int compareTo(Transaction o) {

        Date compareDate = null;
        Date oCompareDate = null;

        if (getDateReturned().equals("")) {
            try {
                compareDate = new SimpleDateFormat("yyyy-MM-dd").parse(getDatePaidBack());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                compareDate = new SimpleDateFormat("yyyy-MM-dd").parse(getDateReturned());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (o.getDateReturned().equals("")) {
            try {
                oCompareDate = new SimpleDateFormat("yyyy-MM-dd").parse(o.getDatePaidBack());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                oCompareDate = new SimpleDateFormat("yyyy-MM-dd").parse(o.getDateReturned());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return compareDate.compareTo(oCompareDate);
    }
}
