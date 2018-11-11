package schoolsystem;

import schoolsystem.Student;
import schoolsystem.Score;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ScoreDB {
	public static final String SEPARATOR = "|";

	
	
    // an example of reading
	public static ArrayList readScores(String directory) throws IOException {
		
		ArrayList studentList = new ArrayList(); // to store list of courses
		studentList = StudentDB.readStudents("/Users/trifenacaroline/Downloads/Student.txt");// Copying the student database into studentList
		
		ArrayList courseList = new ArrayList(); // to store list of courses
		courseList = CourseDB.readCourse("/Users/trifenacaroline/Downloads/Course.txt");// Copying the course database into courseList
		
		ArrayList assessmentList = new ArrayList();
		assessmentList = AssessmentDB.readAssessments("/Users/trifenacaroline/Downloads/Assessment.txt");// Copying the course database into assessmentList
		
		
		ArrayList stringArray = new ArrayList();// read String from text file
		ArrayList scores = new ArrayList();// to store Score data
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
		//ArrayList stringArray = (ArrayList)read(filename);
		//ArrayList scores = new ArrayList() ;// to store Scores data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				int courseID = Integer.parseInt(star.nextToken().trim()); // first token
				String assessmentName = star.nextToken().trim();	// second token
				
				Assessment assessmentToAdd;
				Course courseToAdd;
				
				for (int j = 0; j < courseList.size(); j++) {
					Course courseToTest = (Course)courseList.get(j);
					int courseToTestID = courseToTest.getCourseID();
					if (courseToTestID == courseID) {
						courseToAdd = (Course)courseList.get(j);
						break;
					}
				}
				
				for (int k = 0; k < assessmentList.size(); k++) {
					Assessment assessmentToTest = (Assessment)assessmentList.get(k);
					String assessmentToTestName = assessmentToTest.assessmentName;
					if (assessmentToTestName == assessmentName) {
						assessmentToAdd = (Assessment)assessmentList.get(k);
						break;
					}
				}
				
				
					
				/* use the name and id to compare to the course and and assessment list, if there are such courses and assessments then 
				 * pass those into the score constructor and add it into the score list
				 */
				Score score = new Score(courseToAdd, assessmentToAdd); // create Score Object from file data
				scores.add(score); //add to score List
				
				
				
				while(star.hasMoreTokens()) {
				int studentID = Integer.parseInt(star.nextToken().trim());
				for (int l = 0; l < studentList.size(); l++) {
					Student studentToTest = (Student)studentList.get(l);
					int studentToTestID = studentToTest.getStudentID();
					if (studentToTestID == studentID) {
						Student studentToAdd = (Student)studentList.get(l);
						score.addStudent(studentToAdd);
						break;
					}
				
				}
				int markToAdd = Integer.parseInt(star.nextToken().trim());
				score.addMarks(markToAdd);
				
				}
			
        }
			return scores;
		
	}
        
	
	// an example of saving
	public static void saveScores(String filename, List inputList) throws IOException {
			List scores = new ArrayList() ;// to store Scores data

	        for (int i = 0 ; i < inputList.size() ; i++) {
					Score score = (Score)inputList.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(score.getCourseID());
					st.append(SEPARATOR);
					st.append(score.getAssessmentName().trim());
					st.append(SEPARATOR);
					//add third token append here
					for (int j = 0; j < score.getStudentListSize(); j++) {
						if (score.getStudentID(j)!= 0) {
								st.append(score.getStudentID(j));
								st.append(SEPARATOR);
								st.append(score.getMarks(j));
								st.append(SEPARATOR);
					    }
					}
					scores.add(st.toString()) ;
				}
				write(filename,scores);
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