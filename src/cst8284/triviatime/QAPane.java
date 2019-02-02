package cst8284.triviatime;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

//Filename: QAPane.java
//Author:Gurkirat Singh Toor,#040909509
//Course:CST 8132-OOP
//Assignment:2
//Date:2018-04-18
//Professor: David Houtman
//Purpose: This file contains QAPane class which has properties, getters and setters for those properties.



/**
* This class Represents a QAPane object that is visual representation of a QA object 
* @author David Houtman
* @author Gurkirat Singh Toor
* @version 1.0
* @since 1.8
*
*/


public class QAPane {
	/**
	 * rbAr is the RadioButton Array that stores radioButtons generated for all the answers of a QA object
	 */
   private RadioButton[] rbAr;
   
   /**
     * qaPane is the VBox that contains all the nodes representing properties of a QA object
    */
   private VBox qaPane;
   
   /**
    * thatsMyAnswer Button that when clicked takes user to next Question
    */
   private Button thatsMyAnswer;
   
   /**
    * question is the Text object that renders the question property of QA Object on screen
    */
   private Text question;

   
   /**
    * The constructor that takes a QA object as parameter and and converts it's properties to nodes to be added to VBox qaPane
    * @param obj the QA object whose properties are to be added to VBox as nodes
    */
   public QAPane(QA obj) {//sets all of the nodes that are to be displayed to the user
	   setThatsMyAnswer(obj);
	   setQuestion(obj);
	   setAnswers(obj.getAnswers());
	   getAnswerPane(obj.getAnswers()); 
   }
   
   /**
    * setter for the rbAr array it puts every element in a togglegroup so that at any given time only one can be selected
    * @param ss the string which is to be used to create a radioButton array
    */
   public void setAnswers(String[] ss) {// for loop to get the radiobutton the for all the answers
	   rbAr = new RadioButton[ss.length];
	   ToggleGroup kg = new ToggleGroup();
	   for(int i=0;i<ss.length;i++) {
			rbAr[i] = new RadioButton(ss[i]);
			rbAr[i].setToggleGroup(kg); 
		   }
   }
   
   /**
    * getter for rbAr array which has every answer for a QA object represented as a RadioButton
    * @return rbAr array which has every answer as a RadioButton
    */
  public RadioButton[] getAnswers() {
	  return rbAr;
  } 
  
  /**
   * disables every RadioButton in the rbAr array using a for loop
   */
  public void disableRadioButtons() {//disables the radiobuttons contained in the array
		  for(RadioButton rb:getAnswers()) {
				rb.setDisable(true);
			}
  } 

  /**
   * 
   * @param ss String array to be added to the VBox returned by this method
   * @return VBox containing every answer for a QA object as a RadioButton and question as a Label 
   */
   public VBox getAnswerPane(String[] ss) {
	   // vbox containing all the radiobutton and buttons 
	 VBox jatt = new VBox();
	 jatt.setStyle("-fx-background-color: #F08085;");
	 jatt.setPadding(new Insets(20,40,80,20));
	 jatt.setSpacing(10);
	 jatt.getChildren().add(getQuestion());
	   for(int i=0;i<getAnswers().length;i++) {
		 jatt.getChildren().add(rbAr[i]); 
	   }
	   jatt.getChildren().add(getThatsMyAnswer());
	   setQAPane(jatt);
	   return jatt;
   }
  
   /**
    * calculates which radiobutton was selected by the user
    * @return index number of radiobutton selected in rbAr array plus 1 and if not radiobutton was selected returns 1;
    */
   public int getRadioButtonSelected(){
	   //for loop to get the radiobutton user selected
	   for(int i=0;i<rbAr.length;i++) {
		   if(rbAr[i].isSelected()) {
		return i+1;}
	   }
		   return 0; 
   }
   
	
	
	
	/**
	 * getter for Button thatsMyAnswer
	 * @return thatsMyAnswer the button which user clicks to confirm his/her answer 
	 */
public Button getThatsMyAnswer() {
	return thatsMyAnswer;
}	
		
/**
 * setter for  Button thatsMyAnswer which checks user's answer and then displays explanation for that QA object and right or wrong based on user's answer. Also it add's to score if user's answer is correct using Result.increaseresult() mehtod 
 * @param obj QA object for whom the button thatsMyAnswer  checks the user's answer
 */
 public void setThatsMyAnswer(QA obj) {//when user wanst to go to the next question
	 thatsMyAnswer = new Button("That's my Answer");
	  int answer = obj.getCorrectAnswerNumber();
	  thatsMyAnswer.setOnAction(e -> {//lamda expression to handle user mouse click
		  if(getRadioButtonSelected()==0) {
		    Alert jj = new Alert(AlertType.INFORMATION);
			jj.setContentText("Please Select an Answer");
			jj.showAndWait();
	  }
	    else if(answer==getRadioButtonSelected()) {
			obj.setResult(false);
			disableThatsMyAnswer();
			disableRadioButtons();
			getQAPane().getChildren().add(new Text("Right"));
			getQAPane().getChildren().add(new Text(obj.getExplanation()));
			Controls.getNextQuestionButton().setDisable(false);
			
		} else {
			disableThatsMyAnswer();
			obj.setResult(true);
			getQAPane().getChildren().add(new Text("Wrong"));
			getQAPane().getChildren().add(new Text(obj.getExplanation()));
			disableRadioButtons();
			Controls.getNextQuestionButton().setDisable(false);
		}
	  }
	);
	
 }
	
 
 
 /**
  * getter for question the Text object that displays QA Object's question property
  * @return question the text containing QA object's question property
  */
 public Text getQuestion() {
		return question;
	}
 /**
  * creates a new Text containing the question property of QA object which is passed as a parameter and sets question equal to it 
  * @param obj QA object whose question property is to displayed
  * @return question the Text object containing QA object's question property
  */
 public Text setQuestion(QA obj)
	{
		question =  new Text(obj.getQuestion());
		return question;
	}  
	 
 /**
  * disables thatsMyAnswer button so that user cannot click it
  */
 public void disableThatsMyAnswer() {// disable the thatsmyasnwer button
	 thatsMyAnswer.setDisable(true);
 }
 /**
  * setter for qaPane which is the VBox containing all the properties of a QA object
  * @param vb qaPane is set equal to this VBox
  */
 private void setQAPane(VBox vb) {this.qaPane = vb;}
 
 /**
  * getter for qaPane which is the VBox containing all the properties of a QA object
  * @return qaPane the VBox containing all the properties of a QA object 
  */
 public VBox getQAPane() {return qaPane;}
}
