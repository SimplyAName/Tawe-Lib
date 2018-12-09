package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Michael Loney
 * <p>
 * This is the controller for 'LoanResource'. It will loan a copy the resource to the user if avaible if not it will reserve it.
 */
public class LoanResourseController implements Initializable {

    @FXML
    private Label resourceTitleLabel;
    @FXML
    private Label usernameLabel;

    /**
     * Resource to be loaned out.
     */
    private Resource resource;

    /**
     * Username of the user the resource is being loaned too.
     */
    private String username;

    /**
     * @param resource The resource that will be loaned out.
     * @param username The username of the user that the resource will loaned out to.
     */
    public LoanResourseController(Resource resource, String username) {
        this.resource = resource;
        this.username = username;
    }

    /**
     * Uses ResourceManagment to loan a copy to the user and if a copy isn't avaliable reserves it.
     */
    @FXML
    private void loanResourceAction() {
        try {
            //TODO: Add karls class and use appropriate methods.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs when the scene is first loaded and sets the resource and username label to show the name of the resource and the username of the person it is being loaned.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceTitleLabel.setText(resource.getTitle());
        usernameLabel.setText(username);
    }
}
