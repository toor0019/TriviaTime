package cst8284.triviatime;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

//Filename: FileUtils.java
//Author:Gurkirat Singh Toor,#040909509
//Course:CST 8132-OOP
//Assignment:2
//Date:2018-04-18
//Professor: David Houtman
//Purpose: This file contains FileUtils class which has static methods that loads a file  containing QA objects 



/**
* This class provides various methods that loads a file  containing QA objects 
* @author David Houtman
* @author Gurkirat Singh Toor
* @version 1.0
* @since 1.8
*
*/
public class FileUtils {
	
	/**
	 * ArrayList that stores QA objects
	 */
	private static ArrayList<QA> qaAL;
	
	/**
	 * sets the array qaAl by reading objects from a file
	 * @param absPath the Absolute Path of the file
	 */
	public static void setQAArrayList(String absPath) {
		qaAL = new ArrayList<>();
		if (fileExists(absPath)) {
			try {
				FileInputStream fis = new FileInputStream(absPath);
				ObjectInputStream ois = new ObjectInputStream(fis);
				boolean jatt = true;
				while(jatt) {
				QA qa =(QA) (ois.readObject());
				if(qa !=null) {
					qaAL.add(qa);}else {jatt=false;}
				;}
				ois.close();
			} catch (IOException | ClassNotFoundException e) {} 
		}
		else 
			qaAL = null;
	}
	
	/**
	 * getter for qaAl ArrayList that stores various QA Objects
	 * 
	 * @return qaAl the ArrayList containing QA objects
	 */
	public static ArrayList<QA> getQAArrayList() {return qaAL;}

	/**
	 * checks if file exists or not
	 * @param  f id the file to be checked
	 * @return true if file exists
	 */
	public static boolean fileExists(File f) {
		return (f != null && f.exists() && f.isFile() && f.canRead() && (f.length() > 2));
	}

	/**
	 * calls file filExists(File f) method for specified String
	 * @param s is the String representation of the path of the file
	 * @return true if file exists
	 */
	public static boolean fileExists(String s) {
		return (fileExists(new File(s)));
	}
	
	/**
	 * gets absolute path of the file
	 * @param f File whose absolute path is to be known
	 * @return absolute path of the file
	 */
	public static String getAbsPath(File f) {
		return f.getAbsolutePath();
	}

}
