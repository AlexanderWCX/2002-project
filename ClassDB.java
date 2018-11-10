package schoolsystem;

import schoolsystem.Class;
import schoolsystem.Student;

import java.io.*;
import java.util.*;

public class ClassDB {
	public static final String SEPARATOR = "|";

	
	
    // an example of reading
	public static ArrayList readClasses(String directory) throws IOException {
		
		ArrayList studentList = new ArrayList(); // to store list of courses
		studentList = StudentDB.readStudents("C:\\Users\\xanwo\\eclipse-workspace\\java2002project\\src\\schoolsystem\\student.txt");
		
		// read String from text file
		ArrayList stringArray = new ArrayList();
		ArrayList classes = new ArrayList() ;// to store Class data
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

				int courseID = Integer.parseInt(star.nextToken().trim()); // first token
				String classType = star.nextToken().trim();	// second token
				String classCode = star.nextToken().trim();	// third token
				
				Class class1 = new Class(courseID, classType, classCode); // create Class Object from file data
				classes.add(class1); //add to Class List
				while(star.hasMoreTokens()) {
				int studentID = Integer.parseInt(star.nextToken().trim());
				for (int j = 0; j < studentList.size(); j++) {
					Student studentToTest = (Student)studentList.get(j);
					int studentToTestID = studentToTest.getStudentID();
					if (studentToTestID == studentID) {
						Student studentToAdd = (Student)studentList.get(j);
						class1.addStudent(studentToAdd);
						break;
					}
				}
				
				}
			
        }
			return classes;
	}
        
	
	// an example of saving
	public static void saveClasses(String filename, List inputList) throws IOException {
			List classes = new ArrayList() ;// to store Professors data

	        for (int i = 0 ; i < inputList.size() ; i++) {
					Class class1 = (Class)inputList.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(class1.getCourseID());
					st.append(SEPARATOR);
					st.append(class1.getClassType().trim());
					st.append(SEPARATOR);
					st.append(class1.getClassCode().trim());
					st.append(SEPARATOR);
					
					//add third token append here
					for (int j = 0; j < class1.getStudentListSize(); j++) {
						if (class1.getStudentID(j)!= 0) {
								st.append(class1.getStudentID(j));
								st.append(SEPARATOR);
					    }
					}
					classes.add(st.toString()) ;
				}
				write(filename,classes);
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