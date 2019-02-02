package cst8284.triviatime;

import javafx.scene.text.Font;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

 // Filename: TriviaTimeLaunch.java
 //Author:Gurkirat Singh Toor,#040909509
 // Course:CST 8132-OOP
 // Assignment:2
 // Date:2018-04-18
 //Professor: David Houtman
 // Purpose: This file contains  TriviaTimeLaunch class whic has various methods to manage the intial screen presented to the user and also has the main method of the application.
 


/**
 * This class sets intial text to be displayed to the user when he starts the class and it provides methods to set the animation on that text. It is also the starting point of our application
 * @author David Houtman
 * @author Gurkirat Singh Toor
 * @version 1.0
 * @since 1.8
 *
 */
public class TriviaTimeLaunch extends Application {
	/**  @Copyright Dave Houtman 2018.  For use in Winter 2018 - CST8284 classes only */
	
	/** rootPane stores a borderpane object that is used to format the various panes and nodes to be displayed to the user which are then passed to the scene object */
	private static BorderPane rootPane;
	
	/** 
	 * This method is a implementation for the abstract start method of javafx.application.Application class 's start(Stage ss) method as we have extended from that class
	 * @param primaryStage is a Stage object which would be used by Application.launch(args) method to display our application to the user
	 * */
	@Override
	public void start(Stage primaryStage){	
	   // Display Splash Screen
	   setRootPane("Welcome to Trivial Time");//set our start screen with this text
	   getRootPane().setTop(Controls.getMenuBar(primaryStage));
	   Scene scene =  new Scene(getRootPane(), 1024, 512);// intiating a new scene object
	   primaryStage.setTitle("Trivia Time");
	   primaryStage.setScene(scene);
	   primaryStage.show();	
	}
	
	
	/** 
	 * Main method where the execution of the code begins as Application.launch() is executed here
	 * @param args are the command line arguments
	 * */
	public static void main(String[] args){
		Application.launch(args);// starts our application
	}
	
	/**
	 * This method creates a borderpane object and adds a Text object in its center
	 * @param center  String to be displayed to the user on the beginning of the application
	 */
	public void setRootPane(String center) {//various animation efects are put on the text
	rootPane = new BorderPane();
	Text toor = new Text(center);
	 rootPane.setCenter(toor);
	toor.setFont(Font.font ("Bold", 56));
	toor.setFill(Color.BLUE);
	Reflection r = new Reflection();
	r.setFraction(0.9);
	toor.setEffect(r);
    Boolean das =new Random().nextBoolean();
    	if(das){getSequentialTransition(toor);}
    	else {getParallelTransition(toor);}
    	 rootPane.setCenter(toor);
    
	}
	
	/** getter for rootPane 
	 * @return returns the rootpane variable which contains a borderpane object
	 * @see javafx.scene.layout.BorderPane
	 * */
	public static BorderPane getRootPane() {
		return rootPane;
	}
	
	
	/** applies a FadeTransition on a text object based on some random values
	 * @param  toor is the text object on which the FadeTransition is to be applied
	 * @return a FadeTransition with different values every time making use of java.util.Random and Math Class
	 * @see javafx.animation.FadeTransition
	 * */
	public static FadeTransition getFadeTransition(Text toor) {
		 FadeTransition fadeTransition = new FadeTransition(Duration.millis(new Random().nextInt(10000)), toor);
	        fadeTransition.setFromValue(1.0f);
	        fadeTransition.setToValue(Math.random());
	        fadeTransition.setCycleCount(new Random().nextInt(5));
	        fadeTransition.setAutoReverse(true);
		return fadeTransition;
	}
	
	/** applies a TranslateTransition on a text object based on some random values
	 * @param toor is the text object on which the TranslateTransition is to be applied
	 * @return a TranslateTransition with different values every time making use of java.util.Random and Math Class
	 * @see javafx.animation.TranslateTransition
	 */
	public static TranslateTransition getTranslateTransition(Text toor) {
		  TranslateTransition translateTransition = new TranslateTransition(Duration.millis(new Random().nextInt(10000)), toor);
	        translateTransition.setFromX(50);
	        translateTransition.setToX(new Random().nextInt(600));
	        translateTransition.setCycleCount(new Random().nextInt(5));
	        translateTransition.setAutoReverse(true);
	        return translateTransition;
	}
	
	/** applies a RotateTransition on a text object based on some random values
	 * @param toor is the text object on which the RotateTransition is to be applied
	 * @return a RotateTransition with different values every time making use of java.util.Random and java.lang.Math Class
	 * @see javafx.animation.RotateTransition
	 */
	public static RotateTransition getRotateTransition(Text toor) {
		  RotateTransition rotateTransition =  new RotateTransition(Duration.millis(new Random().nextInt(10000)), toor);
	        rotateTransition.setByAngle(new Random().nextInt(270));
	        rotateTransition.setCycleCount(new Random().nextInt(5));
	        rotateTransition.setAutoReverse(true);
	        return rotateTransition;
	}
	
	/** applies a ScaleTransition on a text object based on some random values
	 * @param toor is the text object on which the ScaleTransition is to be applied
	 * @return a ScaleTransition with different values every time making use of java.util.Random and java.lang.Math Class
	 * @see javafx.animation.ScaleTransition
	 */
	public static ScaleTransition getScaleTransition(Text toor) {
		  ScaleTransition scaleTransition =    new ScaleTransition(Duration.millis(2000), toor);
	        scaleTransition.setToX(new Random().nextInt(4));
	        scaleTransition.setToY(new Random().nextInt(4));
	        scaleTransition.setCycleCount(new Random().nextInt(5));
	        scaleTransition.setAutoReverse(true);
	        return scaleTransition;
	}
	
	/** plays a series of Transition in parallel on a text object 
	 * @param toor is the text object on which the ParallelTransition is to be applied
	 * @return a PrallelTransition object with different types of transition in it
	 * @see javafx.animation.ParallelTransition
	 */
	public static ParallelTransition getParallelTransition(Text toor) {
		// uses other transtion methods to play them all together
		ParallelTransition   parallelTransition = new ParallelTransition();//Initiating new parallelTransition object 
        parallelTransition.getChildren().addAll(
                getFadeTransition(toor),
                getTranslateTransition(toor),
                getRotateTransition(toor),
                getScaleTransition(toor)
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();
        return parallelTransition;
	}
	
	/** plays a series of Transition  on a text object one after another 
	 * @param toor is the text object on which the ParallelTransition is to be applied
	 * @return a SequentialTransition object with different types of transition in it
	 * @see javafx.animation.SequentialTransition
	 */
	public static SequentialTransition getSequentialTransition(Text toor) {
		SequentialTransition sequentialTransition = new SequentialTransition();//Initiating new SequentialTransition object 
		sequentialTransition.getChildren().addAll(
		        getFadeTransition(toor),
		        getTranslateTransition(toor),
		        getRotateTransition(toor),
		        getScaleTransition(toor));// adding all other transitions into the observerable list 
		sequentialTransition.setCycleCount(Timeline.INDEFINITE);
		sequentialTransition.setAutoReverse(true);
		sequentialTransition.play();
		return sequentialTransition;
	}
	
	
	
	
	
	
	
	
	
	
}