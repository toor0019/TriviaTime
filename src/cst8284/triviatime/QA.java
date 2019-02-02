package cst8284.triviatime;

//Filename: QA.java
//Author:Gurkirat Singh Toor,#040909509
//Course:CST 8132-OOP
//Assignment:2
//Date:2018-04-18
//Professor: David Houtman
//Purpose: This file contains QA class which has properties ,  getters and setters for those properties.



/**
* This class Represents a QA(Question Answer) object 
* @author David Houtman
* @author Gurkirat Singh Toor
* @version 1.0
* @since 1.8
*
*/

public class QA extends QARequirements {
	
	
	/**
	 * question contains the String representing  the Question*/
   private String question;
   /**
    * explanation contains the explanation for the right Answer
    */
   private String explanation;
   /**
    * category represents topic of java the question is related to
    */
   private String category;
   /**
    * points represents points given for correct answer
    */
   private int points;
   /**
    * difficulty represents the difficulty level of the question 
    */
   private int difficulty;
   /**
    * correctAnswer represents the number of correct answer
    */
   private int correctAnswer;
   /**
    *  answers Array contains the various available options for the question
    */
   private String[] answers;
   /**
    * result represents if the user entered correct or wrong answer for that question
    */
   private boolean result;
   
   /**
    * getter for question
    * @return the String question for the QA Object
    */
	@Override
	public String getQuestion() {
		// TODO Auto-generated method stub
		return question;
	}
 
	/**
	 * setter for question
	 * @param question String to be set as the question for the QA Object
	 */
	@Override
	public void setQuestion(String question) {
		this.question=question;
		
	}

	/**
	 * setter for answers Array
	    * @return the Array answers containing all the answers  for the QA Object
	    */
	@Override
	public String[] getAnswers() {
		// TODO Auto-generated method stub
		return answers;
	}

	/**
	 * getter for answers Array
	 * @param answers Array to be set as all the available answers for the QA Object
	 */
	@Override
	public void setAnswers(String[] answers) {
		this.answers=new String[answers.length];
		for(int i=0;i<answers.length;i++) {
			this.answers[i]= answers[i];
		}
		
	}

	/**
	 * getter for explanation
	 * @return the explanation for the correct answer
	 */
	@Override
	public String getExplanation() {
		// TODO Auto-generated method stub
		return explanation;
	}

	/**
	 * setter for explanation
	 * @param explanation to be set as explanation for the correct answer of the QA Object
	 */
	@Override
	public void setExplanation(String explanation) {
		this.explanation=explanation;
		
	}

	/**
	 * getter for the category of QA Object on which they can be sorted
	 * @return the category of the 	QA Object 
	 */
	@Override
	public String getCategory() {
		return category;
	}

	/**
	 * setter for category of the QA Object on which they can be sorted
	 * @param category of the 	QA Object
	 */
	@Override
	public void setCategory(String category) {
		// TODO Auto-generated method stub
		this.category=category;
	}

	/**
	 * getter for the difficulty of the QA Object on which they can be sorted
	 * @return the difficulty of the QA Object
	 */
	@Override
	public int getDifficulty() {
		// TODO Auto-generated method stub
		return difficulty;
	}

	/**
	 * getter for the difficulty of the QA Object on which they can be sorted
	 * @param difficulty to be set for the QA Object
	 */
	@Override
	public void setDifficulty(int difficulty) {
		// TODO Auto-generated method stub
		this.difficulty=difficulty;
	}

	/**
	 * getter for the number of points awarded for correct annswer
	 * @return points for the QA Object
	 */
	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return points;
	}

	/**
	 * setter for the number of points to be awarded for the correct answer
	 * @param points to be set to be awarded for correct asnwer
	 */
	@Override
	public void setPoints(int points) {
		this.points=points;
		
	}

	/**
	 * getter for correctAnswer
	 * @return the correct Answer number
	 */
	@Override
	public int getCorrectAnswerNumber() {
		return correctAnswer;
	}

	/**
	 * setter for the correctAnswer
	 * @param correctAnswer number of the correct answer in answer Array
	 */
	@Override
	public void setCorrectAnswerNumber(int correctAnswer) {
		this.correctAnswer=correctAnswer;
		
	}

	/**
	 * getter for the result based on the answer provided by the user
	 * @return true if the user provides correct Answer
	 */
	@Override
	public boolean isCorrect() {
		// TODO Auto-generated method stub
		return result;
	}

	/**
	 * set's result true if user enters correct Answer and False if the user enters wrong answer
	 * @param b boolean true or false based on user's answer
	 */
	@Override
	public void setResult(boolean b) {
	this.result=b;
		
	}
	
	/**
	 * Constructor to create QA objects by using the specified question,answers Array,category,difficulty,points,correctAnswer,explanation,
	 * @param q the question of the QA Object
	 * @param a the answers array containing the all the possible answers
	 * @param c the category the QA object belongs to
	 * @param e the explanation for the correct Answer
	 * @param diff the difficulty level of question
	 * @param point points to be awarded for correct answer
	 * @param an the correctAnswer Number
	 */
  public QA(String q,String[]a,String c,String e,int diff,int point,int an) {
	  setQuestion(q);
	  setAnswers(a);
	  setCategory(c);
	  setDifficulty(diff);
	  setPoints(point);
	  setCorrectAnswerNumber(an);
	  setExplanation(e);
  }
}
