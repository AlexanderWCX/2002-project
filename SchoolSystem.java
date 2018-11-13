package schoolsystem;


import schoolsystem.Course;
import schoolsystem.CourseDB;
import schoolsystem.Assessment;

import java.io.IOException;
import java.util.*;

public class SchoolSystem {
	
	public static final String SEPARATOR = "|";	
		Scanner sc = new Scanner(System.in);
		
		public void addStudent() {
				Student.addStudent();
						
			break;
			
		}
		
		
		public void addCourse() {
			
			Course.addCourse();
				
					
		break;
		}
		
		public void registerStudent() {
			
			int case3studID;
			int case3courseIDToAdd = 999;
			int case3targetIndex = 999;
			boolean case3studentIDExists = false;
			boolean case3courseAdded = false;
			
			try {
				String case3studentFile = "/Users/trifenacaroline/Downloads/student.txt";
				ArrayList case3studentList = StudentDB.readStudents(case3studentFile); 
				
				System.out.println("Enter Student ID:");
				case3studID = sc.nextInt();
				
				//checking if student ID exists
				for (int i = 0 ; i < case3studentList.size() ; i++) {
					Student case1studentToCheck = (Student)case3studentList.get(i);
					if (case3studID == case1studentToCheck.getStudentID())
						case3studentIDExists = true;
				}
				
				//prompt if student ID does not exist
				if(case3studentIDExists == false ) {
					System.out.println("Student ID does not exist!");
					break;
				}
				
				//get student ID's index in studentList
				for (int j = 0; j<case3studentList.size(); j++) {
					Student studenttocheckID = (Student)case3studentList.get(j);
					if (case3studID == studenttocheckID.getStudentID()) {
						case3targetIndex = j;
						break;
					}
				}
				
				//create student object for target student
				Student studenttoRegisterCourse = (Student)case3studentList.get(case3targetIndex);
				
				
				System.out.println("Enter Course to Register:");
				case3courseIDToAdd = sc.nextInt();
				
				
				//import list of courses into an arraylist
				ArrayList courseList = new ArrayList(); // to store list of courses
				courseList = CourseDB.readCourse("/Users/trifenacaroline/Downloads/Course.txt");
				
				//checking if courseID exists in courseList
				for (int j = 0; j < courseList.size(); j++) {
					Course courseToTest = (Course)courseList.get(j);
					int courseToTestID = courseToTest.getCourseID();
					if (courseToTestID == case3courseIDToAdd) {
						studenttoRegisterCourse.addCourse(courseToTest);
						case3courseAdded = true;
						break;
					} 
				}
				
				
				if(case3studentIDExists == false ) {
					System.out.println("Course ID does not exist!");
					break;
				}
				
				
				//write student record(s) to file
				StudentDB.saveStudents(case3studentFile, case3studentList); 
				
				for (int i = 0 ; i < case3studentList.size() ; i++) {
						Student studenttoprint = (Student)case3studentList.get(i);
						System.out.println("Student ID: " + studenttoprint.getStudentID());
						System.out.println("Student Name: " + studenttoprint.getStudentName() );
						System.out.println("Courses Registered are: ");
						studenttoprint.printCourses();
						System.out.println("---------------------------------");
				}
				
			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
			
			break;
			
		}	
		
			
		public void checkClassAvailability() {
			
			int case4CourseCode;
			int case4ClassCode; 
						
			System.out.println("Please enter Course Code:");
			case4CourseCode = sc.nextInt();
			System.out.println("Please enter Class Code for " + case4CourseCode);
			case4ClassCode = sc.nextInt();
			
			
			
			break;
		}
		
		
		public void printCourseStudentList(){
			
			System.out.println("Please enter a course ID:");
    		int courseCode = sc.nextInt(); 
    		
    		try {
    		String courseFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Course.txt";
    		String studentFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\student.txt";
    		
    		ArrayList arrayReadCourse = CourseDB.readCourse(courseFile);
    		
    		ArrayList arrayReadStudent = StudentDB.readStudents(studentFile); 
    		
    		System.out.println("List of Students: ");
    		
    		for (int i = 0 ; i < arrayReadStudent.size() ; i++) {
				Student student = (Student)arrayReadStudent.get(i); 
    			//Course course = (Course)arrayReadCourse.get(i);
				int sizeOfStudentCourseList = student.getCourseListSize();
				for (int j = 0; j < sizeOfStudentCourseList; j++) {
				if(student.getCourseID(j) == courseCode)
				{
					System.out.println(student.getStudentName());
				}
				/*else 
					System.out.println("Course Code not found!"); 
							break;
				*/									
    		}
    		}
    		} 
    		catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
    	
    		
	
			
			//int case5Choice = 0;
			//System.out.println("Please enter Course Code");
			
		//case5Choice = sc.nextInt(); 
			
		
		/*
		int choose = 0;
		System.out.println("Enter 1 for printing by lecture. ");
		System.out.println("Enter 2 for printing by tutorial. ");
		System.out.println("Enter 1 for printing by laboratory session. ");
		
		choose = sc.nextInt();
		*/
			break; 
		}
			
			
		public void enterAssessmentWeightage() {
			
			Assessment.addAssessment();
			break;	
			
		}
		
		
		public void enterAssessmentMark(){
			
			System.out.println("Enter the Students ID: ");
			System.out.println("Enter the course ID: ");
			
			break;
		}
		
			
		public void	printCourseStats() {
			break;
		}
		
		
		public void printStudentTranscript() {
			
			
			break;
		
		
		}	
		
		
}
	
	
	
