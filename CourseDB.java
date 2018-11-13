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
public static final String STUDENTSEPARATOR = "/";
public static final String ASSESSMENTSEPARATOR = ",";
public static final String CLASSSEPARATOR = "!";

	public static ArrayList readCourses(String courseFile) throws IOException {
				
		// Read String from Text File
		ArrayList courseArray = new ArrayList();
		
		//For storing of Course Data
		ArrayList courses = new ArrayList();
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
				int courseType = Integer.parseInt(star.nextToken().trim());
				//Fourth Token 
				String courseProfName = star.nextToken().trim(); 
				//Fifth Token 
				int courseFreeSlot = Integer.parseInt(star.nextToken().trim());
				//Sixth Token 
				int courseTotalSlot = Integer.parseInt(star.nextToken().trim()); 
						
				//Create Course object from file data
				 Course course = new Course (courseID, courseName, courseType, courseProfName, courseFreeSlot, courseTotalSlot);
				
				ArrayList studentList = new ArrayList(); 
				studentList = StudentDB.readStudentIDs("C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Course.txt"); 
				
				StringTokenizer studentStar = new StringTokenizer(st , STUDENTSEPARATOR);	
				while(studentStar.hasMoreTokens())
				{
					int studentID = Integer.parseInt(studentStar.nextToken().trim()); 
					
					for(int j = 0; j<studentList.size(); j++)
					{
						Student student = (Student)studentList.get(j); 
						int checkStudentID = student.getStudentID(); 
						
						if(checkStudentID == studentID)
						{
							Student studentToAdd = (Student)studentList.get(j); 
							course.addStudent(studentToAdd);
							break; 
							
						}
					}
				}
				
				ArrayList assessmentList = new ArrayList(); 
				assessmentList = AssessmentDB.readAssessments("C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Assessment.txt"); 
				
				StringTokenizer assessmentStar = new StringTokenizer(st , ASSESSMENTSEPARATOR);	
				while(assessmentStar.hasMoreTokens())
				{
					String assessmentName = assessmentStar.nextToken().trim();  
					
					for(int j = 0; j<assessmentList.size(); j++)
					{
						Assessment assessment = (Assessment)assessmentList.get(j); 
						String checkAssessmentName = assessment.getAssessmentName(); 
						
						if(checkAssessmentName == assessmentName)
						{
							Assessment assessmentToAdd = (Assessment)assessmentList.get(j); 
							course.addAssessment(assessmentToAdd);
							break; 
							
						}
					}
				}
				
				ArrayList classList = new ArrayList(); 
				classList = StudentDB.readStudentIDs("C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Assessment.txt"); 
				
				StringTokenizer classStar = new StringTokenizer(st , CLASSSEPARATOR);	
				while(classStar.hasMoreTokens())
				{
					String classCode = classStar.nextToken().trim();  
					
					for(int j = 0; j<classList.size(); j++)
					{
						Class newClass = (Class)classList.get(j); 
						String checkClassCode = newClass.getClassCode(); 
						
						if(checkClassCode == classCode)
						{
							Class classToAdd = (Class)classList.get(j); 
							course.addClass(classToAdd);
							break; 
							
						}
					}
				}
				
				//Add to Course list
				courses.add(course);
				
			}
			return courses;
	}
	


	//To save a Course 
	public static void saveCourses(String filename, List inputList) throws IOException {
		//To store Course Data	
		List courses = new ArrayList();

	        for (int i = 0 ; i < inputList.size() ; i++) {
					Course course = (Course)inputList.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(course.getCourseID());
					st.append(SEPARATOR);
					st.append(course.getCourseName().trim());
					st.append(SEPARATOR);
					st.append(course.getCourseType());
					st.append(SEPARATOR);
					st.append(course.getCourseCoordinator().trim());
					st.append(SEPARATOR);
					st.append(course.getCourseFreeSlot());
					st.append(SEPARATOR);
					st.append(course.getCourseTotalSlot());
					st.append(SEPARATOR);
					
					for(int j=0; j<course.getStudentListSize(); j++)
					{
						if(course.getStudentID(j) !=0)
						{
							st.append(course.getStudentID(j)); 
							st.append(STUDENTSEPARATOR); 
						}
					}
					
					for(int k=0; k<course.getAssessmentListSize(); k++)
					{
						
							st.append(course.getAssessmentName(k).trim()); 
							st.append(ASSESSMENTSEPARATOR); 
						
					}
					
					for(int l=0; l<course.getClassListSize(); l++)
					{
						
							st.append(course.getClassCode(l).trim()); 
							st.append(CLASSSEPARATOR); 
						
					}
					
					courses.add(st.toString());
				}
	        
				write(filename,courses);
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
