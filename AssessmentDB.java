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
			ArrayList assessments = new ArrayList() ;// to store Assessments data
	    	Scanner input;
	    	
	    	ArrayList courseList = new ArrayList(); // to store list of courses
			courseList = CourseDB.readCourse("/Users/trifenacaroline/Downloads/Course.txt");// Copying the course database into courseList
	    	
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
			
	        for (int i = 0 ; i < stringArray.size() ; i++) {
					String st = (String)stringArray.get(i);
					// get individual 'fields' of the string separated by SEPARATOR
					StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
					int courseID = Integer.parseInt(star.nextToken().trim());
					
					Course courseToAdd;
					
					for (int j = 0; j < courseList.size(); j++) {
						Course courseToTest = (Course)courseList.get(j);
						int courseToTestID = courseToTest.getCourseID();
						if (courseToTestID == courseID) {
							courseToAdd = (Course)courseList.get(j);
							break;
						}
					}
					
					String assessmentName = star.nextToken().trim();
					int weightage = Integer.parseInt(star.nextToken().trim());	
					int coursework = Integer.parseInt(star.nextToken().trim());  
			 		
					Assessment assessment = new Assessment(courseToAdd, assessmentName, weightage,coursework); // create Assessment Object from file data
					assessments.add(assessment); //add to Assessment
					
				
	        }
				return assessments;
		}
	        
		
		// an example of saving
		public static void saveAssessments(String filename, List al) throws IOException {
				List alw = new ArrayList() ;// to store Professors data

		        for (int i = 0 ; i < al.size() ; i++) {
						Assessment assessment = (Assessment)al.get(i);
						StringBuilder st =  new StringBuilder();
						st.append(assessment.course.courseID);
						st.append(SEPARATOR);
						st.append(assessment.assessmentName.trim());
						st.append(SEPARATOR);
						st.append(assessment.weightage);
						st.append(SEPARATOR);
						st.append(assessment.coursework);
						st.append(SEPARATOR);
						
						alw.add(st.toString());
					}
					write(filename,alw);
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

