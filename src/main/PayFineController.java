package main;

import com.mysql.cj.util.TestUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PayFineController {

    @FXML
    private TextField username;

    @FXML
    private TextField amount;

    private boolean payFine() {

        try {
            Database.edit("UPDATE user_tbl SET balance = balance - " + Integer.parseInt(amount.getText()) + ";");
            Database.edit("INSERT INTO transaction_tbl (username, amount, date) VALUES (" + username.getText() + ", " + Integer.parseInt(amount.getText()) + ", NOW();");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void payFineAction() {
        if (payFine()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("The fine amount has been paid");
            alert.setHeaderText("The fine was paid.");
            alert.setContentText(username.getText() + "'s fine has successfully been paid as has been reduced on their account.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fine could not be paid");
            alert.setHeaderText("The fine was not paid.");
            alert.setContentText(username.getText() + "'s fine has failed been paid due to an error, please check username and the amount to make sure they are correct.");
        }

    }

}
