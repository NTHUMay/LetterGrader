import java.io.*;
import java.util.*; 

/**
 * This constructor stores the variable that are going to be used 
 * @param finalScore stores the weighted score of a student
 * @param arg stores input of the command line 
 * @param grade stores letter grade of the student 
 * @param out is the printer to the output file
 * @param stores the total score of a student 
*/ 
public class LetterGrader {
	double [] finalScore;
	String [] studentName;
	String [] args = new String[2];
	char [] grade;
	PrintWriter out;
	ArrayList<ArrayList<Object>> scoreTotal;
	public LetterGrader(String string, String string2) {
		this.args[0] = string;
		this.args[1] = string2;
	}
	
	
	
	InputStream in = null;
	BufferedReader reader = null;
	/**
	   * if the command args contains less than two strings, it displays the message like this:
	   * "Usage: java DiskIO inputFile outputFile"
	   * @param reader read in the inputstream
	   * @param inputFileName stores the name of the input file 
	   * @throws throws IOException 
	*/ 
	public void readScore() throws IOException {
			
		if (this.args.length < 2) {

			System.out.println("Usage: java TestLetterGrader.java inputFile outputFile");
		}
		else {

			String inputFileName = this.args[0];

			try {
				
				in = new FileInputStream(inputFileName);
				reader = new BufferedReader(new InputStreamReader(in));
				
				this.scoreTotal = new ArrayList<ArrayList<Object>>();					 
				
				//if there is lines in the file
				while(reader.ready()) {
					
					ArrayList<Object> score = new ArrayList<Object>(); 	

				     //read the input file
					 String line = reader.readLine();
					 //split the line by , 
					 String fWord[] = line.split(",");
					 
					 for (int i = 0; i < fWord.length; i++) {
						 
						 score.add(fWord[i]);
					 }
					 
					 this.scoreTotal.add(score);
				     //write to output file
				     //out.println("Student #" + count + " is: \""+ fWord[0] + "\" whose raw scores are: " + fWord[1] + ":");
				}
				//System.out.println(this.scoreTotal);

				//sort the names in order
				Collections.sort(this.scoreTotal, new Comparator<ArrayList<Object>>() {    
			        @Override
			        public int compare(ArrayList<Object> o1, ArrayList<Object> o2) {
			            return ((String) o1.get(0)).compareTo((String)o2.get(0));
			        }               
				}
				);
				this.finalScore = new double[this.scoreTotal.size()];
				
				//store the student name
				this.studentName = new String[this.scoreTotal.size()];
				
				this.grade = new char[this.scoreTotal.size()];
				
				//calculate the weighted score of each student
				for(int i = 0; i < this.scoreTotal.size(); i++) {
					this.finalScore[i] = Float.valueOf((String) this.scoreTotal.get(i).get(1))* 0.1
							+ Float.valueOf((String) this.scoreTotal.get(i).get(2))* 0.1
							+ Float.valueOf((String) this.scoreTotal.get(i).get(3))* 0.1
							+ Float.valueOf((String) this.scoreTotal.get(i).get(4))* 0.1
							+ Float.valueOf((String) this.scoreTotal.get(i).get(5))* 0.2
							+ Float.valueOf((String) this.scoreTotal.get(i).get(6))* 0.15
							+ Float.valueOf((String) this.scoreTotal.get(i).get(7))* 0.25; 
					//store the student names in the array
					this.studentName[i] = (String) this.scoreTotal.get(i).get(0);		
					
				}
			} 
			catch (FileNotFoundException e) {
					
				System.out.println("File does not exist.");
			}
		}
		
	}
	
	/**
	 * This method turn the score into letter grade 
	 * @param finalScore stores the weighted score of a student
	 * @param arg stores input of the command line 
	 * @param grade stores letter grade of the student 
	*/ 
	public void calcLetterGrade() {
		double score = 0;
		for(int i = 0; i < this.finalScore.length; i++) {
			score = this.finalScore[i];
			if(score >= 90)
				this.grade[i] = 'A';
			else if(score < 90 && score > 79)
				this.grade[i] = 'B';
			else if(score < 80 && score > 69)
				this.grade[i] = 'C';
			else if(score < 70 && score > 59)
				this.grade[i] = 'D';
			else
				this.grade[i] = 'F'; 
		}		
		
	}
	
	/**
	 * This method output the letter grade 
	 * @param out print the context to file
	*/ 
	public void printGrade() throws FileNotFoundException {
		this.out = new PrintWriter(this.args[1]);
		out.println("Letter grade for 8 students given in input_data.txt file is:");

		for(int i = 0; i < this.finalScore.length; i++) {
			out.println(this.studentName[i] + ":\t" + this.grade[i]);
		}
		out.close();
	}
	
	/**
	 * This method display the average, min and the max of each exam 
	 * @param sum stores the total score of the class for each exam
	 * @param max stores the max grade of the exam of the class
	 * @param min stores the min grade of the exam of the class
	*/ 
	public void displayAverages() {
		System.out.println();
		System.out.println("Letter grade has been calculated for students listed in input file input_final.txt "
				+ "and written to output file output.txt\n");
		System.out.println("Here is the class average:\n");

		float [] sum = new float [this.finalScore.length];
		float max = 0;
		float min = 0;
		
		System.out.println("\t Q1\tQ2\tQ3\tQ4\tMid I\tMid II\tFinal");

		
		System.out.print("Average: ");
		//iterate over the 2 dimension array list to get the score 
		for(int j = 0; j < 7; j++) {
			for (int i = 0; i < this.finalScore.length; i++) {
			
				sum[j] += Float.valueOf((String) this.scoreTotal.get(i).get(j + 1));
				
			}
			System.out.printf("%.2f\t", sum[j]/this.finalScore.length);	
		}
		
		System.out.print("\n");
		System.out.print("Minimum: ");
				
		//iterate over the 2 dimension array list to get the score 
		for(int j = 0; j < 7; j++) {
			min = Float.valueOf((String) this.scoreTotal.get(1).get(j + 1));
			for (int i = 1; i < this.finalScore.length; i++) {
						
				if(Float.valueOf((String) this.scoreTotal.get(i).get(j + 1)) < min){
					//replace min if a smaller value is found
					min = Float.valueOf((String) this.scoreTotal.get(i).get(j + 1));
				}
		
			}
			System.out.print((int)min + "\t");	
		}

		System.out.print("\n");

		System.out.print("Maximum: ");

		//iterate over the 2 dimension array list to get the score 
		for(int j = 0; j < 7; j++) {
			max = Float.valueOf((String) this.scoreTotal.get(1).get(j + 1));
			for (int i = 0; i < this.finalScore.length; i++) {
				
				if(Float.valueOf((String) this.scoreTotal.get(i).get(j + 1)) > max){
					//replace max if a bigger value is found
					max = Float.valueOf((String) this.scoreTotal.get(i).get(j + 1));
				}

			}
			System.out.print((int)max + "\t");	
		}
		
		System.out.print("\n");

	}
	
	/**
	   * Prompt user to press enter key in order to exit the program
	   * @param bufferedreader to do buffering    
	   */ 
	public void pressAnyKeyToContinue() {
		String toContinue = null;
		System.out.println();
		System.out.println("Press ENTER to continue...");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//catch error
		try {
			toContinue = br.readLine();
		} 
		catch(IOException e) { // catch and print out error
			e.printStackTrace();
			System.exit(1);
		}
		System.exit(1);
		
	}
	
	/**
	   * close up the readers/ writers 
	   */ 
	public void doCleanup() throws IOException {
		reader.close();	
		in.close();	
	}

}
