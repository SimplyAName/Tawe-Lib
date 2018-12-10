package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Controller for transaction history of the user.
 */
public class TransactionHistoryController implements Initializable {

    @FXML
    private TableView<Transaction> transactionsTable;

    private User user;

    public TransactionHistoryController(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTable();
    }


    private void fillTable() {

        ArrayList<Transaction> transactions = new ArrayList<>();

        transactions = getFineHistory(transactions);

        Collections.sort(transactions);

        transactionsTable.getItems().addAll(transactions);

    }

    private ArrayList<Transaction> getFineHistory(ArrayList<Transaction> transactions) {

        try {

            ResultSet resourceHistory = Database.query("SELECT historic_tbl.datefrom, historic_tbl.datetil, resource_tbl.title, resource_tbl.type, fine_tbl.amount, fine_tbl.daysoverdue" +
                    " FROM historic_tbl, copy_tbl, resource_tbl, fine_tbl WHERE historic_tbl.username = '" + user.getUsername() + "' AND copy_tbl.copyid = historic_tbl.copyid AND" +
                    " copy_tbl.resourceid = resource_tbl.resourceid AND historic_tbl.histid = fine_tbl.histid ORDER BY historic_tbl.datetil DESC");

            while (resourceHistory.next()) {
                Transaction transaction = new Transaction();

                transaction.setResourceTitle(resourceHistory.getString("title"));
                transaction.setResourceType(resourceHistory.getString("type"));
                transaction.setDateBorrowed(resourceHistory.getString("datefrom"));
                transaction.setDateReturned(resourceHistory.getString("datetil"));
                transaction.setDaysOverdue(resourceHistory.getString("daysoverdue"));
                transaction.setPaymentAmount(resourceHistory.getString("amount"));

                transactions.add(transaction);
            }

            ResultSet paidHistory = Database.query("SELECT amount, CONVERT(date, DATE) FROM transaction_tbl WHERE username = '" + user.getUsername() + "';");

            while (paidHistory.next()) {
                transactions.add(new Transaction(paidHistory.getString("amount"), paidHistory.getString("CONVERT(date, DATE)")));
            }

            return transactions;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
