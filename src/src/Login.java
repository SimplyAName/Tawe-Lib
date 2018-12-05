import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Login window, Stores the logged in user
 * @author Chris
 *
 */
public class Login extends Application {
	private static final int WINDOW_HEIGHT = 700;
	private static final int WINDOW_WIDTH = 700;
	private static final int LARGE_FONT_SIZE = 50;
	private static final int SMALL_FONT_SIZE = 20;
	
	private static final int PREF_X_SIZE = 70;
	private static final int PREF_Y_SIZE = 30;
	private static final Insets BUTTON_PADDING = new Insets(15,15,15,15);
	private static final Insets AROUND_BUTTON_PADDING = new Insets(25,25,25,25);
	
	private static String username; 
	private static Stage primaryStage;
	
	private static User user;
	
	/**
	 * Starts the program on the primary stage
	 */
	@Override
	public void start(Stage pStage) throws Exception {
		try {
			primaryStage = pStage;
			VBox root = new VBox();
			root = setRootPane(root);
			root.setAlignment(Pos.CENTER);
			root.setPadding(AROUND_BUTTON_PADDING);
			Scene scene = new Scene(root,WINDOW_HEIGHT,WINDOW_WIDTH);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Sets up the main root pane
	 * @param root the root pane
	 * @return the root pane
	 */
	private VBox setRootPane(VBox root){
		HBox buttonBox = new HBox();
		HBox inputRow = new HBox();
		
		
		Label title = new Label("Login");
		title.setAlignment(Pos.CENTER);
		title.setFont(new Font(LARGE_FONT_SIZE));
		
		Label usernameLabel = new Label("Username: ");
		usernameLabel.setAlignment(Pos.CENTER);
		usernameLabel.setFont(new Font(SMALL_FONT_SIZE));
		
		TextField inputBox = new TextField();
		inputBox.setFont(new Font(SMALL_FONT_SIZE));
		inputBox.autosize();
		inputBox.setAlignment(Pos.CENTER);
		inputBox.setPadding(AROUND_BUTTON_PADDING);
		
		inputRow.getChildren().addAll(usernameLabel, inputBox);
		inputRow.setAlignment(Pos.CENTER);
		inputRow.setPadding(AROUND_BUTTON_PADDING);
		
		
		Label infoLabel = new Label();
		infoLabel.setAlignment(Pos.CENTER);
		infoLabel.setFont(new Font(SMALL_FONT_SIZE));
		
		Button loginButton = new Button();
		loginButton.setText("Login");
		loginButton.setFont(new Font(SMALL_FONT_SIZE));
		loginButton.setPadding(BUTTON_PADDING);
		loginButton.autosize();
		loginButton.setAlignment(Pos.CENTER);
		loginButton.setOnAction(a -> {
			String tmpUsername = inputBox.getCharacters().toString();
			try{
				ResultSet set = Database.query("SELECT * FROM user_tbl WHERE username = '"
			+ tmpUsername +"';");
				
				if(set.next()){
					user = new User(set.getString("username"), set.getString("firstnames"), set.getString("lastname"), 
							set.getString("addrline1"),set.getString("postcode"), set.getString("phone"), (new Image("test.png")),
							set.getInt("balance"));
					infoLabel.setText("Welcome "+ user.getFirstName()+" "+ user.getLastName());
					
				}else{
					infoLabel.setText("Please enter a correct username");
				}
			}catch (Exception e){
				System.out.println(e);
				infoLabel.setText("Failed to Login in. Please try again");
			}
			
		});
		
		Button cancelButton = new Button();
		cancelButton.setText("Cancel");
		cancelButton.setFont(new Font(SMALL_FONT_SIZE));
		cancelButton.setPadding(BUTTON_PADDING);
		cancelButton.autosize();
		cancelButton.setAlignment(Pos.CENTER);
		cancelButton.setOnAction(a -> {
			System.exit(10);
		});
		
		buttonBox.getChildren().addAll(cancelButton, loginButton);
		buttonBox.setAlignment(Pos.CENTER);
	
		buttonBox.setPadding(AROUND_BUTTON_PADDING);
	
		root.getChildren().addAll(title, inputRow, buttonBox, infoLabel);
		
		return root;
	}
	/**
	 * returns the currently logged in user
	 * @return who is logged in
	 */
	public static User getUser(){
		return user;
	}
	/**
	 * Lauches the login
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
