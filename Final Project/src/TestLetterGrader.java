import java.io.*;
import java.util.*;

/**
 * This is program allow users to read in grade from a file and output it as a letter grade 
 * Besides, it will also calculates the average, minimum and maximum of the score of the class or each exam
 * and will quit the program if finally the user press ENTER
 * @author Yun-Yung Wang
 * Date created: July 1
 * Last Date Modified: June 7
 */
public class TestLetterGrader {

	public static void main (String args[]) throws IOException	{
		//test if there	are	two	valid arguments	then, create the	object
	    //if not give right	message	and	exit
		System.out.println(args[0]);
		LetterGrader letterGrader = new LetterGrader(args[0], args[1]);
																																																																																			
		//LetterGrader is your main class,																																																																																//args[0]	has	input	file	name,	and
		//args[1] has output file name
		letterGrader.readScore();	 //reads score and stores the data in member variables
		letterGrader.calcLetterGrade();	//determines letter	grade and stores information
		letterGrader.printGrade();		//writes the grade in output file
		letterGrader.displayAverages();	//displays	the	averages in	console
		letterGrader.pressAnyKeyToContinue(); //prompt user for 
		letterGrader.doCleanup();	   //use it	to close files and other resources
		
		
	}

}
