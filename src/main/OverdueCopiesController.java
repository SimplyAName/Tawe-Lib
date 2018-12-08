package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OverdueCopiesController implements Initializable {

    @FXML
    private TableView<OverdueCopy> overdueCopiesTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ResultSet overdueCopiesResults = Database.query("SELECT * FROM out_tbl WHERE duedate < DATE(NOW())");

            ArrayList<OverdueCopy> overdueCopies = new ArrayList<>();

            while (overdueCopiesResults.next()) {

                OverdueCopy rowResult = new OverdueCopy();

                rowResult.setCopyid(overdueCopiesResults.getInt("copyid"));
                rowResult.setDateTakenOut(overdueCopiesResults.getString("datefrom"));
                rowResult.setDateDue(overdueCopiesResults.getString("duedate"));
                rowResult.setUsername(overdueCopiesResults.getString("username"));

                overdueCopies.add(rowResult);
            }

            for (OverdueCopy copy : overdueCopies) {

                ResultSet copyInformation = Database.query("SELECT resource_tbl.title, resource_tbl.type FROM resource_tbl INNER JOIN copy_tbl ON copy_tbl.copyid = " + copy.getCopyid() + " WHERE resource_tbl.resourceid = copy_tbl.resourceid;");

                copyInformation.next();

                copy.setResourceName(copyInformation.getString("resource_tbl.title"));

                copy.setResourceType(copyInformation.getString("resource_tbl.type"));
            }

            overdueCopiesTable.getItems().addAll(overdueCopies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
