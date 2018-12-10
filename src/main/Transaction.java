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

    /**
     * The transaction history fines and date for the user
     * @param paymentAmount The amount the user needs to pay
     * @param datePaidBack  The date in which they paid said amount
     */
    public Transaction(String paymentAmount, String datePaidBack) {
        this.paymentAmount = paymentAmount;
        this.datePaidBack = datePaidBack;
    }

    /**
     * 
     * @param resourceTitle  The title of the resource
     * @param resourceType   The type of resource
     * @param dateBorrowed   The date in which the resource was borrowed
     * @param dateReturned   The date the resource was returned
     * @param daysOverdue    How many days the resource is overdue
     * @param paymentAmount  THe amount the user needs to pay
     */
    public Transaction(String resourceTitle, String resourceType, String dateBorrowed, String dateReturned, String daysOverdue, String paymentAmount) {
        this.resourceTitle = resourceTitle;
        this.resourceType = resourceType;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
        this.daysOverdue = daysOverdue;
        this.paymentAmount = paymentAmount;
    }

    /**
     * Gets the resource title
     * @return Resource title
     */
    public String getResourceTitle() {
        return resourceTitle;
    }

    /**
     * Sets the resource title
     * @param resourceTitle
     */
    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    /**
     * Gets the resource type 
     * @return The type of resource
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Sets the resource being borrowed
     * @param resourceType
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Gets the date the resource was borrowed
     * @return The date 
     */
    public String getDateBorrowed() {
        return dateBorrowed;
    }

    /**
     * Sets the date the resource is borrowed
     * @param dateBorrowed The date resource was borrowed
     */
    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    /**
     * Get the date the resource was borrowed
     * @return The borrow date
     */
    public String getDateReturned() {
        return dateReturned;
    }

    /**
     * Sets the date the resource was returned
     * @param dateReturned The return date
     */
    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    /**
     * Gets how many days overdue a resource is
     * @return Days overdue 
     */
    public String getDaysOverdue() {
        return daysOverdue;
    }

    /**
     * Sets how many days overdue a resource is
     * @param daysOverdue
     */
    public void setDaysOverdue(String daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    /**
     * Gets the fine payment account
     * @return Payment amount
     */
    public String getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the payments amount
     * @param paymentAmount
     */
    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Gets the date in which the payment amount was paid
     * @return Day paid back
     */
    public String getDatePaidBack() {
        return datePaidBack;
    }

    /**
     * Sets the day the payment was paid
     * @param datePaidBack
     */
    public void setDatePaidBack(String datePaidBack) {
        this.datePaidBack = datePaidBack;
    }

    /*
     *Method to have a set format for the date returned
     */
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
