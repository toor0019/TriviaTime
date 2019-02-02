package cst8284.triviatime;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
//Filename: Results.java
//Author:Gurkirat Singh Toor,#040909509
//Course:CST 8132-OOP
//Assignment:2
//Date:2018-04-18
//Professor: David Houtman
//Purpose: This file contains Results class which has static methods that GENERATE result



/**
* This class provides various methods that generate and display result
* @author David Houtman
* @author Gurkirat Singh Toor
* @version 1.0
* @since 1.8
*
*/
public class Results {
	/**
	 * stores the score
	 */
private static double result ;

/**
 * Increases result which stores the score  by point 
 * @param point by which the result is to be increased
 * @return the result
 */
	public static double increaseresult(int point) {
		return result +=point;
	}
	/**
	 * getter for result which stores the score of the quiz
	 * @return the result
	 */
	public static double getresult() {
		return result;
	}
	/**
	 *sets the result according to QA object's  isCorrect() method and getPoints method
	 *@param obj the ArrayList containing the QA objects
	 */
	public static void setResult(ArrayList<QA> obj) {//for loop is used to record which question user entered correctly and which one wrong
		for(QA jatt: obj) {
			if(jatt.isCorrect()) {
				increaseresult(jatt.getPoints());
			}
		}
	}
	
	/**
	 * 
	 * @param obj ArrayList containing the qa objects
	 * @return VBox displaying result
	 */
	public static VBox VBoxresult(ArrayList<QA> obj) {
		
		//vbox that is used to display the result
		VBox resultvb = new VBox();
		int ctr=1;
		// for loop to print right or wrong for evry answer based on user answers
		for(QA jj:obj) {
			if(jj.isCorrect()) {
				resultvb.getChildren().add(new Text(ctr+") Right"));
			}
			else {
				resultvb.getChildren().add(new Text(ctr+") Wrong"));
			}
			ctr++;
		}
		double total =  getresult()/FileUtils.getQAArrayList().size()*100;
		Text tt = new Text("You Scored "+total+"%");
		resultvb.getChildren().add(tt);
		resultvb.setAlignment(Pos.CENTER);// all are aligned on centre
		return resultvb;
	}
	
	/**
	 * resets the Result to zero if game is restarted
	 */
	public static void resetResult() {
		result=0;// resets the result to zero;
	}
}
