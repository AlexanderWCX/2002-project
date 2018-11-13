package schoolsystem;

import schoolsystem.CourseDB;
import schoolsystem.Course;
import schoolsystem.Professor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProfessorDB {
	public static final String SEPARATOR = "|";

	
	
    //For reading in list of professors from the textfile
	public static ArrayList readProfessors(String directory) throws IOException {
		
		ArrayList courseList = new ArrayList(); // to store list of courses
		courseList = CourseDB.readCourses("/Users/trifenacaroline/Downloads/Course.txt");
		
		
		ArrayList stringArray = new ArrayList();// read String from text file
		ArrayList professors = new ArrayList() ;// to store Professors data
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
		//ArrayList students = new ArrayList() ;// to store Students data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				if(!star.hasMoreTokens()) {
					break;
				}
				int staffID = Integer.parseInt(star.nextToken().trim()); // first token
				String professorName = star.nextToken().trim();	// second token

				
				Professor professor = new Professor(staffID, professorName); // create Student Object from file data
				professors.add(professor); //add to Student List
				while(star.hasMoreTokens()) {
				int courseID = Integer.parseInt(star.nextToken().trim());
				for (int j = 0; j < courseList.size(); j++) {
					
					Course courseToTest = (Course)courseList.get(j);
					int courseToTestID = courseToTest.getCourseID();
					if (courseToTestID == courseID) {
						Course courseToAdd = (Course)courseList.get(j);
						professor.addCourse(courseToAdd);
						break;
					}
				}
				
				}
			
        }
			return professors;
	}
     
	
	//To read the list of professors in the text file without the list of courses each professor has
public static ArrayList readProffesorIDs(String directory) throws IOException {
		
		
		ArrayList stringArray = new ArrayList();// read String from text file
		ArrayList professors = new ArrayList() ;// to store Professors data
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
				int staffID = Integer.parseInt(star.nextToken().trim()); // first token
				String professorName = star.nextToken().trim();	// second token
				Student professor = new Student(staffID, professorName); // create Professor Object from file data
				professors.add(professor); //add to Professor List
        }
			return professors;
	}
	
	
	// For saving(writing) the list of professors to the text file
	public static void saveProfessors(String filename, List inputList) throws IOException {
			List professors = new ArrayList() ;// to store Professors data

	        for (int i = 0 ; i < inputList.size() ; i++) {
					Professor professor = (Professor)inputList.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(professor.getStaffID());
					st.append(SEPARATOR);
					st.append(professor.getProfessorName().trim());
					st.append(SEPARATOR);
					//add third token append here
					for (int j = 0; j < professor.getCourseListSize(); j++) {
						if (professor.getCourseID(j)!= 0) {
								st.append(professor.getCourseID(j));
								st.append(SEPARATOR);
					    }
					}
					professors.add(st.toString()) ;
				}
				write(filename,professors);
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
