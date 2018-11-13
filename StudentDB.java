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
		
	public static ArrayList readStudents(String directory) throws IOException {
		ArrayList courseList = new ArrayList(); //To store list of courses
		courseList = CourseDB.readCourse("/Users/trifenacaroline/Downloads/Course.txt");
		
		
		ArrayList stringArray = new ArrayList();//To read String from text file
		
		ArrayList students = new ArrayList() ;//To store Students data
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
        
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				if(!star.hasMoreTokens()) {
					break;
				}
				int studentID = Integer.parseInt(star.nextToken().trim()); // first token
				String studentName = star.nextToken().trim();	// second token

				
				Student student = new Student(studentID, studentName); // create Student Object from file data
				students.add(student); //add to Student List
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
			return students;
	}
        
public static ArrayList readStudentIDs(String directory) throws IOException {
		
														
		ArrayList stringArray = new ArrayList();// read String from text file
		ArrayList students = new ArrayList() ;// to store Students data
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

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				if (!star.hasMoreTokens()) {
					break;
				}
				int studentID = Integer.parseInt(star.nextToken().trim()); // first token
				String studentName = star.nextToken().trim();	// second token
				Student student = new Student(studentID, studentName); // create Student Object from file data
				students.add(student); //add to Student List
        }
			return students;
	}
	
	
	// For saving(writing) back to the text file
	public static void saveStudents(String filename, List inputList) throws IOException {
			List students = new ArrayList() ;// to store students data

	        for (int i = 0 ; i < inputList.size() ; i++) {
					Student student = (Student)inputList.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(student.getStudentID());
					st.append(SEPARATOR);
					st.append(student.getStudentName().trim());
					st.append(SEPARATOR);
					
					for (int j = 0; j < student.getCourseListSize(); j++) {
						if (student.getCourseID(j)!= 0) {
								st.append(student.getCourseID(j));
								st.append(SEPARATOR);
					    }
					}
					students.add(st.toString()) ;
				}
				write(filename,students);
		}
	/** Write fixed content to the given file. */
	  public static void write(String fileName, List data) throws IOException  {
	    PrintWriter out = new PrintWriter(new FileWriter(fileName));

	    try {
			for (int i = 0; i < data.size() ; i++) {
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
