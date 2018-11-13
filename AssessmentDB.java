package schoolsystem;

import schoolsystem.CourseDB;
import schoolsystem.Course;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

	public class AssessmentDB {
		public static final String SEPARATOR = "|";
		
	    // an example of reading
		public static ArrayList readAssessments(String directory) throws IOException {
			
			// read String from text file
			ArrayList stringArray = new ArrayList();
			ArrayList assessments = new ArrayList() ;
	    	Scanner input;
	    	
	        try {
	            File file = new File(directory);
	            input = new Scanner(file);
	            int i = 0;

	            while (input.hasNextLine()) {
	                String line = input.nextLine();
	                stringArray.add(line);
	                
	            }
	            input.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println("in read assessments");
	       
	        for (int i = 0 ; i < stringArray.size() ; i++) {
					String st = (String)stringArray.get(i);
					
					// get individual 'fields' of the string separated by SEPARATOR
					StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
					int courseID = Integer.parseInt(star.nextToken().trim()); //first token
					String assessmentName = star.nextToken().trim(); //second token
					int weightage = Integer.parseInt(star.nextToken().trim());	//third token
					int coursework = Integer.parseInt(star.nextToken().trim()); //fourth token 
			 		
					Assessment assessment = new Assessment(courseID, assessmentName, weightage,coursework); // create Assessment Object from file data
					
					
					ArrayList scoreList = new ArrayList(); // to store list of scores
					scoreList = ScoreDB.readScores("C:\\Users\\xanwo\\eclipse-workspace\\java2002project\\src\\schoolsystem\\Score.txt");
					//finding a matching score object from scoreList to be added to the newly made Assessment object
					for (int j=0;j<scoreList.size(); j++) {
							Score scoreToTest = (Score) scoreList.get(j);
							if (scoreToTest.getAssessmentName().equals(assessmentName)) {
								for(int k=0; k<scoreToTest.getMarksListSize(); k++) {
									assessment.getScore().addMarks(scoreToTest.getMarks(k));
								}
								for(int k=0; k<scoreToTest.getStudentListSize(); k++) {
									assessment.getScore().addStudent(scoreToTest.getStudent(k));
								}
								//Debugging : print matching Score obj is found!
							}
						}
						assessments.add(assessment); //add to assessmentList
					
	        }
	        System.out.println("in assessments end of assessments");
	      
				return assessments;
		}
	        
		
		// an example of saving
		public static void saveAssessments(String filename, List inputList) throws IOException {
				List assessments = new ArrayList() ;
				System.out.println(inputList.size());

		        for (int i = 0 ; i < inputList.size() ; i++) {
						Assessment assessment = (Assessment)inputList.get(i);
						StringBuilder st =  new StringBuilder();
						st.append(assessment.getCourseID());
						st.append(SEPARATOR);
						st.append(assessment.getAssessmentName().trim());
						st.append(SEPARATOR);
						st.append(assessment.getWeightage());
						st.append(SEPARATOR);
						st.append(assessment.getCoursework());
						st.append(SEPARATOR);
						
						assessments.add(st.toString());
					}
					write(filename,assessments);
			}
		/** Write fixed content to the given file. */
		  public static void write(String fileName, List data) throws IOException  {
		    PrintWriter out = new PrintWriter(new FileWriter(fileName));

		    try {
				for (int i =0; i < data.size() ; i++) {
		      		out.println((String)data.get(i));
				}
		    }
		    finally {
		      out.close();
		    }
		  }
		  
		  /** Read the contents of the given file. */
		  public static List read(String fileName) throws IOException {
			List data = new ArrayList() ;
		    Scanner scanner = new Scanner(new FileInputStream(fileName));
		    try {
		      while (scanner.hasNextLine()){
		        data.add(scanner.nextLine());
		      }
		    }
		    finally{
		      scanner.close();
		    }
		    return data;
		  }
	}

