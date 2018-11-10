package schoolsystem;


import schoolsystem.Course;
import schoolsystem.CourseDB;
import schoolsystem.Assessment;

import java.io.IOException;
import java.util.*;

public class SchoolSystem {
	
	public static final String SEPARATOR = "|";
	
	public static void main(String args[]) {
		
		int choice = 0;
		
		System.out.println("Choose an option: ");
		System.out.println("1: Add a student");
		System.out.println("2: Add a course");
		System.out.println("3: Register a student into a course");
		System.out.println("4: Check slots for course or classes");
		System.out.println("5: Print the student list by lecture, tutorial or laboratory session for a course");
		System.out.println("6: Add an assessment for a course");
		System.out.println("7: Enter a student's marks");
		System.out.println("8: Print a course's performance");
		System.out.println("9: Print a student's transcript");
		
		Scanner sc = new Scanner(System.in);
		
		choice = sc.nextInt();
		
		switch(choice) {
		
		case 1: 
			StudentDB txtDB = new StudentDB();
	    	String filenamecase1 = "/Users/trifenacaroline/Downloads/student.txt";
			int AddIDNum;
			String studName;
	    	
			try {
				
				ArrayList allStudents1 = StudentDB.readStudents(filenamecase1) ;
				
				System.out.println("Enter New Student ID:");
				AddIDNum = sc.nextInt();
				System.out.println("Enter New Student Name:");
				studName = sc.next();
				Student student = new Student(AddIDNum, studName);
				
				//add student obj into list of all students
				allStudents1.add(student);
				//write student record(s) to file
				StudentDB.saveStudents(filenamecase1, allStudents1);
				
				for (int i = 0 ; i < allStudents1.size() ; i++) {
						Student studenttoprint = (Student)allStudents1.get(i);
						System.out.println("Student ID: " + studenttoprint.getStudentID());
						System.out.println("Student Name: " + studenttoprint.getStudentName() );
						System.out.println("---------------------------------");
						
				}
				
			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
			break;
			
			
		case 2:
			
			break;
			
		case 3:
			
			int studID;
			int courseIDToAdd;
			int targetindex = 0;
			
			try {
				String filenamecase2 = "/Users/trifenacaroline/Downloads/student.txt";
				ArrayList allStudents2 = StudentDB.readStudents(filenamecase2) ;
				
				System.out.println("Enter Student ID:");
				studID = sc.nextInt();
				System.out.println("Enter Course to Register:");
				courseIDToAdd = sc.nextInt();
				
				for (int j = 0; j<allStudents2.size(); j++) {
					Student studenttocheckID = (Student)allStudents2.get(j);
					if (studID == studenttocheckID.getStudentID()) {
						targetindex = j;
						break;
					}
				}
				
				Student studenttoRegisterCourse = (Student)allStudents2.get(targetindex);
				//import list of courses into an arraylist
				ArrayList courseList = new ArrayList(); // to store list of courses
				courseList = CourseDB.readCourse("/Users/trifenacaroline/Downloads/Course.txt");
				for (int j = 0; j < courseList.size(); j++) {
					Course courseToTest = (Course)courseList.get(j);
					int courseToTestID = courseToTest.getCourseID();
					if (courseToTestID == courseIDToAdd) {
						Course courseToAdd = (Course)courseList.get(j);
						studenttoRegisterCourse.addCourse(courseToAdd);
						break;
					}
				}
				
				
				
				//add student obj into list of all students
				
				//write student record(s) to file
				StudentDB.saveStudents(filenamecase2, allStudents2);
				
				for (int i = 0 ; i < allStudents2.size() ; i++) {
						Student studenttoprint = (Student)allStudents2.get(i);
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
			
		case 4:
			int chosen = 0;
			
			System.out.println("Enter 1 for course.");
			System.out.println("Enter 2 for classes ");
			
			chosen = sc.nextInt();
			
			
			break;
			
		case 5:
			
			System.out.println("Please enter a course code:");
    		int courseCode = sc.nextInt(); 
    		
    		try {
    		String courseFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Course.txt";
    		String studentFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\student.txt";
    		ArrayList arrayReadCourse = CourseDB.readCourse(courseFile);
    		ArrayList arrayReadStudent = StudentDB.readStudents(studentFile); 
       	
    		for (int i = 0 ; i < arrayReadStudent.size() ; i++) {
				Student student = (Student)arrayReadStudent.get(i); 
    			//Course course = (Course)arrayReadCourse.get(i);
							
				if(student.getCourseID(i) == courseCode)
				{
					System.out.println("List of Student: " + student.getStudentName());
				
					//System.out.println("Course Coordinator: " + course.getCourseCoordi());
					//System.out.println("Course Vacancy: " + course.getcourseVacancy());
	
				}
				
				else 
					System.out.println("Course Code not found!"); 
							break;
													
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
			
		case 6:
			int case6courseID;
			String case6assessmentName;
			int case6targetCourseIndex = 999;
			Course case6targetCourse;
			boolean case6madeBefore = false;
			int case6courseworkInput;
			int case6weightage;
			
			System.out.println("Enter the courseID of course to add assessment to: ");
			case6courseID = sc.nextInt();
			
			
			try {
			ArrayList courseList = new ArrayList(); // to store list of courses
			courseList = CourseDB.readCourse("/Users/trifenacaroline/Downloads/Course.txt");
			 
			for (int j = 0; j < courseList.size(); j++) {
				Course courseToTest = (Course)courseList.get(j);
				int courseToTestID = courseToTest.getCourseID();
				if (courseToTestID == case6courseID) {
					case6targetCourseIndex = j;
					break;
				}
			}
			
			if (case6targetCourseIndex == 999 ) {
				System.out.println("Course does not exist!");
				break;
			}
			
			case6targetCourse = (Course)courseList.get(case6targetCourseIndex);
			
			
			System.out.println("Enter the name of the assessment: ");
			
			case6assessmentName = sc.next();
			
			ArrayList assessmentList = new ArrayList(); // to store list of courses
			assessmentList = AssessmentDB.readAssessments("/Users/trifenacaroline/Downloads/Assessment.txt");
			
			for (int i=0; i< assessmentList.size(); i++) {
				Assessment assessmentToTest = (Assessment)assessmentList.get(i);
				if (case6courseID == assessmentToTest.courseID) {
					if (case6assessmentName == assessmentToTest.assessmentName) {
					case6madeBefore = true;
					break;
				    } 
				}
			}
			
			if(case6madeBefore == true) {
				String case6assessmentinvalid;
				case6assessmentinvalid = String.format("Assessment with the same assessment name has been made before for CourseID %d ", case6courseID);
				System.out.println(case6assessmentinvalid);
				break;
			}
			
			
			System.out.println("Is this assessment a Coursework sub-component? ");
			System.out.println("Enter 1 for Yes");
			System.out.println("Enter 0 for No");
			case6courseworkInput = sc.nextInt();
			
			System.out.println("Enter the weightage of the assessment(out of 100): ");
			case6weightage = sc.nextInt();
			
			Assessment case6newAssessment = new Assessment(case6courseID, case6assessmentName, case6weightage, case6courseworkInput);
			assessmentList.add(case6newAssessment);
			AssessmentDB.saveAssessments("/Users/trifenacaroline/Downloads/Assessment.txt", assessmentList);
			
			System.out.println("Assessment succesfully added!");
			
			} catch (Exception e) {
	            e.printStackTrace();
	        }
			break;			
		case 7:
			
			System.out.println("Enter the Students ID: ");
			System.out.println("Enter the course ID: ");
			
			break;
		case 8:
			
			
			break;
		case 9:
			
			break;
		
		
		
		
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
}