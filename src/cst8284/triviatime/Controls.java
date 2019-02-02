package cst8284.triviatime;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

//Filename: Controls.java
//Author:Gurkirat Singh Toor,#040909509
// Course:CST 8132-OOP
// Assignment:2
// Date:2018-04-18
//Professor: David Houtman
// Purpose: This file contains Controls class which has static methods to control the question being displayed and how user enters his/her answer.



/**
* This class provides various methods to generate the visuals that will be loaded into the scene and displayed to the user
* @author David Houtman
* @author Gurkirat Singh Toor
* @version 1.0
* @since 1.8
*
*/
public class Controls {
	
	/** mnuItm used to store various MenuItems to be added to the menu
	 * */
	private static MenuItem mnuItm;
	
	/** mnu  is used to store references to various menu objects that are added to the menu bar 
	 * */
	private static Menu mnu;
	
	/** qp  stores QApane  object generated based on qa objects stored in question ArrayList
	 * */
	private static QAPane qp;
	
	/** questions stores qa objects that we will read from the files
	 * */
	private static ArrayList<QA> questions;
	
	/** stage that can be used to access all it it's childs
	 * */
	private static Stage stage;
	
	/** nextQuestion Button users clicks when he wants to go to next question provided he has answered that current question
	 * */
	private static Button nextQuestion;
	
	private static File qaFile;
	

	/** currentQuestion tracks the current question being displayed
	 * */
	private static int currentQuestion = 0;

    /**
     * setter for currentQuestion
     * @param set the value of the currentQuestion to be set
     */
	public static void setCurrentQuestion(int set) {
	currentQuestion=set;
	}
	
	/** 
	 * increases the value of currentQuestion by 1
	 * */
	public static void incrementCurrentQuestion () {
		currentQuestion++;
	}
	
	/**
	 * getter for currentQuestion
	 * @return the value CurrentQuestion being displayed 
	 */
	public static int getCurrentQuestion() {
		return currentQuestion;
	}

 /**
  * 
  * @param primarystage The Stage which is saved by using set(Stage primaryStage)
  * @return menuBar with menu loaded into it
  */
	public static MenuBar getMenuBar(Stage primarystage) {
		setStage(primarystage);
	   MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(getMnuFile(),getMnuSettings(),getMnuHelp());
	
		return menuBar;
	}
	
	/**
	 * Generates the File menu
	 * @return File menu with newGame and Exit MenuItem added into it 
	 */
    public static Menu getMnuFile() {
    	mnu = new Menu("File");
    	mnu.setOnAction(e->mnu.show());
    	MenuItem ng =  getMnuItmNewGame();
    	MenuItem ex = new MenuItem("_Exit");
    	ex.setOnAction(e -> Platform.exit());
    	ex.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
    	mnu.getItems().addAll(ng,ex);
    	mnu.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
    	return mnu;
    	
    }
 
    /**
     * Generates the Settings Menu
     * @return Setting menu with RandomizeQuestions,IncreaasingDifficulty,ByTopic menuItems added to it
     */
    public static Menu getMnuSettings() {
    	mnu = new Menu("Settings");
    	mnu.setOnAction(e->mnu.show());
    	mnu.getItems().addAll(getRandomizeQuestions(),getByTopic(),getIncreasingDifficulty());
    	mnu.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
    	return mnu;	
    }
    
	/**
	 * Generates the Help menu
	 * @return Help menu with About menuItem added to it
	 */
    public static Menu getMnuHelp() {
    	mnu= new Menu("Help");
    	mnu.setOnAction(e->mnu.show());
    	MenuItem ab =  getMnuItmAbout();
    	mnu.getItems().add(ab);
    	mnu.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
    	return mnu;
    	
    }

 
    /**
     * Generates Randomize Questions mneuItm which shuffles questions randomly
     * @return returns Randomize Questions menuItem which randomizes questions
     */
    public static MenuItem getRandomizeQuestions() {
    	mnuItm = new MenuItem("Randomize Questions");
    	mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
    	mnuItm.setOnAction(e->{
    		if(getQaFile()==null) {
    			getMnuItmNewGame().fire();
    			Collections.shuffle(getQuestions());
    		}else{Collections.shuffle(getQuestions());}
    	setCurrentQuestion(0);
		Results.resetResult();
		qp = new QAPane (questions.get(getCurrentQuestion()));
		TriviaTimeLaunch.getRootPane().setCenter(qp.getQAPane());
		TriviaTimeLaunch.getRootPane().setBottom(getNextQuestionPane());	
    	});
    	
    	return mnuItm;
    }
    
    /**
     * Generates ByTopic mneuItm which sorts questions by category alphabetically
     * @return returns ByTopic MenuItem
     */
    public static MenuItem getByTopic() {
    	mnuItm = new MenuItem("By Topic");
    	mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));
    	mnuItm.setOnAction(e->{
    		if(getQaFile()==null) {
    			getMnuItmNewGame().fire();
    			Collections.sort(getQuestions(),new Comparator<QA>() {
    				@Override
    				public int compare(QA q1, QA q2) {
    					return q1.getCategory().compareTo(q2.getCategory());
    				}});
    		}else{Collections.sort(getQuestions(),new Comparator<QA>() {
				@Override
				public int compare(QA q1, QA q2) {
					return q1.getCategory().compareTo(q2.getCategory());
				}
    		});
			setCurrentQuestion(0);
			Results.resetResult();
			qp = new QAPane (questions.get(getCurrentQuestion()));
			TriviaTimeLaunch.getRootPane().setCenter(qp.getQAPane());
			TriviaTimeLaunch.getRootPane().setBottom(getNextQuestionPane());;
    		}});
    	return mnuItm;
    }
    
    /**
     * Generates Increasing Difficulty menuItem which sorts questions by increasing difficulty
     * @return returns IncreasingDifficulty menuItem
     */
    public static MenuItem getIncreasingDifficulty() {
    	mnuItm = new MenuItem("Increasing Difficulty");
    	mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
    	mnuItm.setOnAction(e->{
    		if(getQaFile()==null) {
    			getMnuItmNewGame().fire();
    			Collections.sort(getQuestions(),new Comparator<QA>() {
    				@Override
    				public int compare(QA q1, QA q2) {
    					return q1.getDifficulty()-q2.getDifficulty();
    				}});
    		}else{Collections.sort(getQuestions(),new Comparator<QA>() {
				@Override
				public int compare(QA q1, QA q2) {
					return q1.getDifficulty()-q2.getDifficulty();
				}
    		});
			setCurrentQuestion(0);
			Results.resetResult();
			qp = new QAPane (questions.get(getCurrentQuestion()));
			TriviaTimeLaunch.getRootPane().setCenter(qp.getQAPane());
			TriviaTimeLaunch.getRootPane().setBottom(getNextQuestionPane());;
    		}});
    	return mnuItm;
    }
    
    /**
     * Generates newGame MenuItem which starts a new Game and Loads a File Containing various questions
     * @return newGame menuItem
     */
    public static MenuItem getMnuItmNewGame() {
	mnuItm = new MenuItem("_NewGame");
	mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
	mnuItm.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Select the Trivia File to open");
			fc.getExtensionFilters().addAll(
			         new ExtensionFilter("Trivia Files", "*.trivia")
			         );
			fc.setInitialDirectory(	new File("C:\\TriviaTime"));
			setQaFile(fc.showOpenDialog(stage));
			if(getQaFile()!=null) {
				FileUtils.setQAArrayList(getQaFile().getAbsolutePath());
				questions = FileUtils.getQAArrayList();
				if(getCurrentQuestion()>0) {
				setCurrentQuestion(0);
				Results.resetResult();
				}
				qp = new QAPane (questions.get(getCurrentQuestion()));
				TriviaTimeLaunch.getRootPane().setCenter(qp.getQAPane());
				TriviaTimeLaunch.getRootPane().setBottom(getNextQuestionPane());
			}
			
		});
		
	return mnuItm;
}
	
	/**
	 * Displays an Alert box showing information about author of the application
	 * @return About MenuItem
	 */
	private static MenuItem getMnuItmAbout() {
		
		mnuItm = new MenuItem("_About");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("About Trivia Time");
			alert.setContentText("Author: Gurkirat Singh Toor");
			alert.showAndWait();
		});
		return mnuItm;
	}
	
	/**
	 * Setter for stage 
	 * @param stage Stage in which all the nodes are contained 
	 */
	private static void setStage(Stage stage) {stage= stage;}
	
	/**
	 * Getter for stage
	 * @return stage object which contains the Stage on which the scene and other nodes are being displayed
	 */
	private static Stage getStage() {return stage;}
	
	/**
	 * Generates a HBox containing the next question Button which if a user clicks takes him to next question
	 * @return HBox containing the next question Button
	 */
	public  static HBox getNextQuestionPane() {
		 HBox toor = new HBox();
		 setNextQuestionButton("Next Question");
		 disableNextQuestionButton();
		 getNextQuestionButton().setOnAction(e -> {
		 incrementCurrentQuestion();
		if (getCurrentQuestion()<FileUtils.getQAArrayList().size()) {
			setqp(questions.get(getCurrentQuestion()));
			disableNextQuestionButton();
			TriviaTimeLaunch.getRootPane().setCenter(getqp().getQAPane());
			}
				else {
					Results.setResult(getQuestions());
					TriviaTimeLaunch.getRootPane().setCenter(Results.VBoxresult(getQuestions()));	
				}
			});    
	     toor.getChildren().add(getNextQuestionButton());
		return toor;
	}
	
	/**
	 * disables the nextQuestion Button
	 */
	public static void disableNextQuestionButton() {
		getNextQuestionButton().setDisable(true);
	}
	
	/**
	 * Setter for nextQuestion -- creates a  new button with specified text
	 * @param text to be displayed on the button
	 */
	public static void setNextQuestionButton(String text) {
		nextQuestion = new Button(text);
	}
	
	/**
	 * getter for nextQuestion
	 * @return the nextQuestion button
	 */
	public static Button getNextQuestionButton() {
		return nextQuestion;
	}
	
	/**
	 * setter for qp-- the QAPane object
	 * @param obj The QA object whose properties will be used to create qp object
	 */
	public static void setqp(QA obj) {
		qp = new QAPane(obj);
	}  
	
	/**
	 * * getter for qp-- the QAPane object
	 * @return qp object which is displayed to the user
	 */
	public static QAPane getqp() {
		return qp;
	}
	
	/**
	 * getter for question the ArrayList containing all the QA objects
	 * @return The QA ArrayList questions
	 */
	public static ArrayList<QA> getQuestions() {
		return questions;
	}
	
	/**
	 * getter for qaFile Object which holds the QA objects
	 * @return qaFile File Object containing the file for the QA Objects 
	 */
	public static File getQaFile() {
		return qaFile;
	}

	/**
	 * setter for the qaFile which holds the QA Objects
	 * @param qaFile the File object to be set as The value qaFile 
	 */
	public static void setQaFile(File qaFile) {
		Controls.qaFile = qaFile;
	}
}

