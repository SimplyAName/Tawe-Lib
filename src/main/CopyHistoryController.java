package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CopyHistoryController implements Initializable {

    @FXML
    private TableView<CopyHistory> copyHistoryTable;

    private int copyid;

    public CopyHistoryController(int copyid) {
        this.copyid = copyid;
    }

    private void fillData() {

        ArrayList<CopyHistory> copyHistories = new ArrayList<>();

        try {
            ResultSet copyHistory = Database.query("SELECT resource_tbl.title, resource_tbl.type, historic_tbl.datefrom, historic_tbl.datetil, historic_tbl.username " +
                    "FROM historic_tbl, resource_tbl, copy_tbl " +
                    "WHERE historic_tbl.copyid = copy_tbl.copyid AND copy_tbl.resourceid = resource_tbl.resourceid AND historic_tbl.copyid = " + copyid + " ORDER BY historic_tbl.datetil DESC");

            while (copyHistory.next()) {
                copyHistories.add(new CopyHistory(copyHistory.getString("resource_tbl.title"), copyHistory.getString("historic_tbl.datefrom"),
                        copyHistory.getString("historic_tbl.datetil"), copyHistory.getString("historic_tbl.username")));
            }

            copyHistoryTable.getItems().addAll(copyHistories);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillData();
    }
}
