package schoolsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class CourseDB {
	
public static final String SEPARATOR = "|";

	public static ArrayList readCourse(String courseFile) throws IOException {
		// Read String from Text File
		ArrayList courseArray = new ArrayList();
		
		//For storing of Course Data
		ArrayList alr = new ArrayList();
    	Scanner input;
    	
        try {
            File file = new File(courseFile);
            input = new Scanner(file);
            int i = 0;

            while (input.hasNextLine()) {
                String line = input.nextLine();
                courseArray.add(line);
            }
            
            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        for (int i = 0 ; i < courseArray.size() ; i++) {
				String st = (String)courseArray.get(i);
				
				//To get individual 'fields' of the string separated by SEPARATOR
				//Passing in the string to the string tokenizer using delimiter ","
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	 
			
				//First Token
				int courseID = Integer.parseInt(star.nextToken().trim());
				//Second Token 
				String courseName = star.nextToken().trim();
				//Third Token 
				String courseType = star.nextToken().trim();
				//Fourth Token 
				String courseCoordi = star.nextToken().trim(); 
				//Fifth Token 
				int courseVacancy = Integer.parseInt(star.nextToken().trim());
								
				//Create Course object from file data
				Course course = new Course(courseID, courseName, courseType, courseCoordi, courseVacancy);
				
				//Add to Course list
				alr.add(course);
				
			}
			return alr;
	}
	
	//To save a Course 
	public static void saveCourse(String filename, List al) throws IOException {
		//To store Course Data	
		List alw = new ArrayList();

	        for (int i = 0 ; i < al.size() ; i++) {
					Course course = (Course)al.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(course.getCourseID());
					st.append(SEPARATOR);
					st.append(course.getCourseName().trim());
					st.append(SEPARATOR);
					st.append(course.getCourseType().trim());
					st.append(SEPARATOR);
					st.append(course.getCourseCoordi().trim());
					st.append(SEPARATOR);
					st.append(course.getcourseVacancy());
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
	  
	  //Read the contents of the given file. 
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
