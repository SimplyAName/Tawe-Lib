package src;
	
import java.awt.image.BufferedImage;
import java.io.File;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * A paint like application for creating or editing an image
 * @author Chris
 *
 */
public class CustomDrawing extends Application {
	private static final int WINDOW_HEIGHT = 700;
	private static final int WINDOW_WIDTH = 700;
	private static final int LARGE_FONT_SIZE = 25;
	private static final int SMALL_FONT_SIZE = 15;
	private static final int CANVAS_SIZE = 500;
	private static final int CANVAS_BORDER_WIDTH = 5;
	
	private static final int PREF_X_SIZE = 70;
	private static final int PREF_Y_SIZE = 30;
	private static final Insets BUTTON_PADDING = new Insets(5,5,5,5);
	private static final Insets AROUND_BUTTON_PADDING = new Insets(5,5,5,5);
		
	private Stage stage = null;
	private Canvas canvas;
	private String loadedImageLocation;
	private String saveImageLocation;
	private WritableImage savedImage = new WritableImage(CANVAS_SIZE,CANVAS_SIZE);
	private SnapshotParameters imageParameters = new SnapshotParameters();
	
	private String penType = "Free Draw";
	private double drawStartX;
	private double drawStartY;
	private double drawWidth;
	private double drawHeight;
	
	/**
	 * updates the stage to be the custom drawing window
	 */
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root = setRootPane(root);
			Scene scene = new Scene(root,WINDOW_HEIGHT,WINDOW_WIDTH);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void launchInNewWindow() {
		try { 
			this.stage = new Stage();
			BorderPane root = new BorderPane();
			root = setRootPane(root);
			Scene scene = new Scene(root,WINDOW_HEIGHT,WINDOW_WIDTH);
			
			this.stage.setScene(scene);
			this.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * For launching the application in a new window
	 * @param imageToLoad image location to load from, can be null 
	 * @param imageToSaveTo image location to save to, CANNOT be null
	 */
	public void launchInNewWindow(String imageToLoad, String imageToSaveTo) {
		try { 
			this.stage = new Stage();
			BorderPane root = new BorderPane();
			root = setRootPane(root);
			try{
				Image image = new Image(imageToLoad);
				this.canvas = loadImageIntoCanvas(image);
				root.setCenter(this.canvas);
				loadedImageLocation = imageToLoad;
			}catch(Exception e){
				this.canvas = createNewCanvas();
				root.setCenter(this.canvas);
			}
			saveImageLocation = imageToSaveTo;
			
			Scene scene = new Scene(root,WINDOW_HEIGHT,WINDOW_WIDTH);
			this.stage.setScene(scene);
			this.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private BorderPane setRootPane(BorderPane root) {
		
		this.canvas = createNewCanvas();
		root.setCenter(this.canvas);
		root = setSideButtons(root);
		root = setTopButtons(root);
		return root;
	}
	/**
	 * Loads image into canvas
	 * @param imageToLoad
	 * @return
	 */
	private Canvas loadImageIntoCanvas(Image imageToLoad){
		Canvas newCanvas = createNewCanvas();
		newCanvas.getGraphicsContext2D().drawImage(imageToLoad, 0, 0);
		return newCanvas;
	}
	/**
	 * Creates a empty canvas
	 * @return an empty canvas
	 */
	private Canvas createNewCanvas() {
		Canvas canvas = new Canvas(CANVAS_SIZE,CANVAS_SIZE);
		GraphicsContext drawable = canvas.getGraphicsContext2D();
		//creates border around canvas(will be part of image)
		drawable.setLineWidth(CANVAS_BORDER_WIDTH);
		drawable.strokeLine(0, 0, 0, CANVAS_SIZE);
		drawable.strokeLine(0, CANVAS_SIZE, CANVAS_SIZE, CANVAS_SIZE);
		drawable.strokeLine(CANVAS_SIZE, CANVAS_SIZE, CANVAS_SIZE, 0);
		drawable.strokeLine(CANVAS_SIZE, 0, 0, 0);

		//Works by drawing line following the mouse while held
		canvas.setOnMousePressed(e -> {
			drawable.beginPath();
			drawable.lineTo(e.getX(), e.getY());
			if (this.penType.equals("Circle Tool")){
				this.drawStartX = e.getX();
				this.drawStartY = e.getY();
			}else if(this.penType.equals("Particle Trace")){
				drawable.strokeOval(e.getX(), e.getY(), 1, 1);
			}
		});
		
		canvas.setOnMouseDragged(e ->{
			if(this.penType.equals("Free Form")){
				drawable.lineTo(e.getX(), e.getY());
				drawable.stroke();
			}else if(this.penType.equals("Particle Trace")){
				drawable.strokeOval(e.getX(), e.getY(), 1, 1);
			}
		});
		canvas.setOnMouseReleased(e -> {
			drawable.lineTo(e.getX(), e.getY());
			if(this.penType.equals("Circle Tool")){
				double width = this.drawStartX - e.getX();
				double height = this.drawStartY - e.getY();
				
				//System.out.println(this.drawStartX + ", "+ this.drawStartY + ", "+ e.getX() + ", " + e.getY());
				if ((width > 0) && (height > 0)){
					drawable.strokeOval(e.getX(), e.getY(), Math.abs(width), Math.abs(height));
				}else if(width > 0){
					drawable.strokeOval(e.getX(), this.drawStartY, Math.abs(width), Math.abs(height));
				}else if(height > 0){
					drawable.strokeOval(this.drawStartX, e.getY(), Math.abs(width), Math.abs(height));
				}
				else{
					drawable.strokeOval(this.drawStartX, this.drawStartY, Math.abs(width), Math.abs(height));
				}
				
			}else if((penType.equals("Straight Line")) || (penType.equals("Free Form"))){
				
				//drawable.strokeLine(drawStartX, drawStartY, e.getX(), e.getY());
				drawable.stroke();
			}
			drawable.closePath();
			this.canvas = canvas;
		});
		return canvas;
	}
	
	/**
	 * Creates the side buttons and adds them to the root pane
	 * @param root the pan to add to
	 * @return the root pane with the added side buttons
	 */
	private BorderPane setSideButtons(BorderPane root) {
		VBox sideButtonPane = new VBox();
		//Creating clear button
		Button clearCanvasButton = new Button();
		clearCanvasButton.setText("Clear");
		clearCanvasButton.setPadding(BUTTON_PADDING);
		clearCanvasButton.setPrefSize(PREF_X_SIZE, PREF_Y_SIZE);
		//works by replacing the current canvas with an empty one
		clearCanvasButton.setOnAction(a -> {
			this.canvas = createNewCanvas();
			root.setCenter(this.canvas);
		});
		//Creating save button
		Button saveCanvasButton = new Button();
		saveCanvasButton.setText("Save");
		saveCanvasButton.setPadding(BUTTON_PADDING);
		saveCanvasButton.setPrefSize(PREF_X_SIZE, PREF_Y_SIZE);
		//Works by snapshoting, and the buffering image to update file, or create if it does not currently exist
		saveCanvasButton.setOnAction(a -> {
			try{
				File fileBin = new File("bin/"+saveImageLocation);				
				if(!fileBin.exists()){
					fileBin.createNewFile();
				}
				File fileSrc = new File("src/"+saveImageLocation);				
				if(!fileSrc.exists()){
					fileSrc.createNewFile();
				}
				fileBin.setWritable(true);
				fileSrc.setWritable(true);
				
				savedImage = this.canvas.snapshot(imageParameters, savedImage);			
				BufferedImage image = SwingFXUtils.fromFXImage(savedImage, null);
				ImageIO.write(image, "png", fileBin);
				ImageIO.write(image, "png", fileSrc);
				loadSavedPopup();
				
			}catch (Exception e){
				System.out.println("could not save file");
				System.out.println(e);
			}
		});
		//Create close button
		Button closeCanvasButton = new Button();
		closeCanvasButton.setText("Close");
		closeCanvasButton.setPadding(BUTTON_PADDING);
		closeCanvasButton.setPrefSize(PREF_X_SIZE, PREF_Y_SIZE);
		//works by replacing the current canvas with an empty one
		closeCanvasButton.setOnAction(a -> {
			if(stage != null){
				this.stage.close();				
			}
		});
		sideButtonPane.setPadding(AROUND_BUTTON_PADDING);
		sideButtonPane.setAlignment(Pos.CENTER);
		sideButtonPane.getChildren().addAll(clearCanvasButton, saveCanvasButton, closeCanvasButton);
		root.setLeft(sideButtonPane);
		return root;
	}
	/**
	 * Loads and shows the saved popup window. will only happen if the save was a success
	 */
	private void loadSavedPopup(){
		final double POPUP_WINDOW_SIZE = 150;
		
		Stage savedPopup = new Stage();
		savedPopup.setAlwaysOnTop(true);
		VBox rootPane = new VBox();
		Label saved = new Label("Saved");
		saved.setAlignment(Pos.CENTER);
		saved.setFont(new Font(LARGE_FONT_SIZE));
		
		Button close = new Button("Close");
		close.setPrefSize(PREF_X_SIZE, PREF_Y_SIZE);
		close.setOnAction(e -> {
			savedPopup.close();
		});
		rootPane.getChildren().addAll(saved, close);
		rootPane.setAlignment(Pos.CENTER);
		savedPopup.setScene(new Scene(rootPane, POPUP_WINDOW_SIZE, POPUP_WINDOW_SIZE));
		savedPopup.show();
	}
	
	private BorderPane setTopButtons(BorderPane root) {
		VBox vbox = new VBox(); 
		HBox topButtonPane = new HBox();
		
		Button black = new Button("Black");
		black.setTextFill(Color.BLACK);
		black.setPadding(BUTTON_PADDING);
		black.setPrefSize(PREF_X_SIZE, PREF_Y_SIZE);
		black.setOnAction(e -> {
			this.canvas.getGraphicsContext2D().setStroke(Color.BLACK);
		});
		
		Button blue = new Button("Blue");
		blue.setTextFill(Color.BLUE);
		blue.setPadding(BUTTON_PADDING);
		blue.setPrefSize(PREF_X_SIZE, PREF_Y_SIZE);
		blue.setOnAction(e -> {
			this.canvas.getGraphicsContext2D().setStroke(Color.BLUE);
		});
		
		Button red = new Button("Red");
		red.setTextFill(Color.RED);
		red.setPadding(BUTTON_PADDING);
		red.setPrefSize(PREF_X_SIZE, PREF_Y_SIZE);
		red.setOnAction(e -> {
			this.canvas.getGraphicsContext2D().setStroke(Color.RED);
		});
		
		Button green = new Button("Green");
		green.setTextFill(Color.GREEN);
		green.setPadding(BUTTON_PADDING);
		green.setPrefSize(PREF_X_SIZE, PREF_Y_SIZE);
		green.setOnAction(e -> {
			this.canvas.getGraphicsContext2D().setStroke(Color.GREEN);
		});
		
		topButtonPane.getChildren().addAll(black, blue, red, green);
		topButtonPane.setPadding(AROUND_BUTTON_PADDING);
		topButtonPane.setAlignment(Pos.CENTER);
		
		Label chooseColor = new Label("Choose pen colour");
		chooseColor.setAlignment(Pos.CENTER);
		chooseColor.setFont(new Font(LARGE_FONT_SIZE));
		
		//Creating the pen types and toggle groups
		ToggleGroup penTypeGroup =  new ToggleGroup();
		RadioButton penFreeForm = new RadioButton("Free Form");
		penFreeForm.setToggleGroup(penTypeGroup);
		penFreeForm.setOnAction(a ->{
			this.penType = "Free Form";
			
		});
		
		RadioButton penLine = new RadioButton("Straight Line");
		penLine.setToggleGroup(penTypeGroup);
		penLine.setOnAction(a ->{
			this.penType = "Straight Line";
			
		});
		
		RadioButton penCircle = new RadioButton("Circle Tool");
		penCircle.setToggleGroup(penTypeGroup);
		penCircle.setOnAction(a ->{
			this.penType = "Circle Tool";
			
		});
		RadioButton penParticle = new RadioButton("Particle Trace");
		penParticle.setToggleGroup(penTypeGroup);
		penParticle.setOnAction(a ->{
			this.penType = "Particle Trace";
		});
		HBox penTypeBox = new HBox();
		penTypeBox.getChildren().addAll(penFreeForm, penLine, penCircle, penParticle);
		penTypeBox.setAlignment(Pos.CENTER);
		penTypeBox.setPadding(AROUND_BUTTON_PADDING);
		vbox.getChildren().addAll(penTypeBox, chooseColor, topButtonPane);
		vbox.setAlignment(Pos.CENTER);
		
		root.setTop(vbox);
		return root;
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
