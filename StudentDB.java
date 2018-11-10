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

public class StudentDB {
	public static final String SEPARATOR = "|";

	System.out.println("hello world");
	
    // an example of reading
	public static ArrayList readStudents(String directory) throws IOException {
		
		ArrayList courseList = new ArrayList(); // to store list of courses
		courseList = CourseDB.readCourse("/Users/trifenacaroline/Downloads/Course.txt");
		
		// read String from text file
		ArrayList stringArray = new ArrayList();
		ArrayList alr = new ArrayList() ;// to store Students data
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
		//ArrayList alr = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				int studentID = Integer.parseInt(star.nextToken().trim()); // first token
				String studentName = star.nextToken().trim();	// second token

				
				Student student = new Student(studentID, studentName); // create Student Object from file data
				alr.add(student); //add to Student List
				while(star.hasMoreTokens()) {
				int courseID = Integer.parseInt(star.nextToken().trim());
				for (int j = 0; j < courseList.size(); j++) {
					Course courseToTest = (Course)courseList.get(j);
					int courseToTestID = courseToTest.getCourseID();
					if (courseToTestID == courseID) {
						Course courseToAdd = (Course)courseList.get(j);
						student.addCourse(courseToAdd);
						break;
					}
				}
				
				}
			
        }
			return alr;
	}
        
	
	// an example of saving
	public static void saveStudents(String filename, List al) throws IOException {
			List alw = new ArrayList() ;// to store Professors data

	        for (int i = 0 ; i < al.size() ; i++) {
					Student student = (Student)al.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(student.getStudentID());
					st.append(SEPARATOR);
					st.append(student.getStudentName().trim());
					st.append(SEPARATOR);
					//add third token append here
					for (int j = 0; j < student.getCourseListSize(); j++) {
						if (student.getCourseID(j)!= 0) {
								st.append(student.getCourseID(j));
								st.append(SEPARATOR);
					    }
					}
					alw.add(st.toString()) ;
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