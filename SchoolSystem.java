package schoolsystem;


import schoolsystem.Course;
import schoolsystem.CourseDB;
import schoolsystem.Assessment;
import schoolsystem.Class;

import java.io.IOException;
import java.util.*;

public class SchoolSystem {
	
	public static final String SEPARATOR = "|";	
		Scanner sc = new Scanner(System.in);
		
		public void addStudent () {
			boolean studentIDExists = true;
			
	    	String studentFile = "/Users/trifenacaroline/Downloads/student.txt";
	    	try {
				
				ArrayList studentList = StudentDB.readStudents(studentFile) ;
				
				System.out.println("Enter New Student ID:");
				int studentID = sc.nextInt();
				
				while(studentIDExists == true) {
				for (int i = 0 ; i < studentList.size() ; i++) {
					Student studentToCheck = (Student)studentList.get(i);
					if (studentID == studentToCheck.getStudentID())
						studentIDExists = true;
					else 
						studentIDExists = false;
				}
				
				//prompt if studentID does not exist
				if(studentIDExists == true) {
					System.out.println("Student ID already exists!");
					System.out.println("Enter New Student ID:");
					studentID = sc.nextInt();
					
				}
				}
				
				System.out.println("Enter New Student Name:");
				String studName = sc.next();
				Student student = new Student(studentID, studName);
				
				//add student obj into list of all students
				studentList.add(student);
				//write student record(s) to file
				StudentDB.saveStudents(studentFile, studentList);
				
				for (int i = 0 ; i < studentList.size() ; i++) {
						Student studenttoprint = (Student)studentList.get(i);
						System.out.println("Student ID: " + studenttoprint.getStudentID());
						System.out.println("Student Name: " + studenttoprint.getStudentName() );
						System.out.println("---------------------------------");
						
				}
				
			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
		}
		
		
		public static void addCourse()
		{
			ClassDB ClassDB = new ClassDB();
	    	String classFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Class.txt";
	    	String courseFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Course.txt";
			
	    	int courseID;
			String courseName;
			int courseType;
			String courseProfName;
			int courseFreeSlot; 
			int courseTotalSlot; 
			String classCode;  
			int classSize; 
			int numOfClass; 
			boolean classCodeExists = true;
			
	   	
			try {
				
				ArrayList courseList = CourseDB.readCourses(courseFile);
				ArrayList classList = ClassDB.readClasses(classFile); 
					
				System.out.print("Please enter Course Code: "); 
				courseID = sc.nextInt(); 
				String empty = sc.nextLine(); 
			    System.out.print("Please enter Course Name: ");
				courseName = sc.nextLine();
				System.out.print("Please enter Course Professor Name: ");
				courseProfName = sc.nextLine();
			    System.out.print("Please enter Course Type: (1 - Lecture only, 2 - Lecture/Tutorial only, 3 - Lecture/Tutorial/Lab): ");
				courseType = sc.nextInt();
				 
				if(courseType == 1)
				{
					System.out.print("Please enter Lecture Group Code:");
					classCode = sc.next(); 
					//checkClass(classCode); 	
					
					while(classCodeExists == true) {
						for(int i=0; i<classList.size(); i++ )
						{
							Class classToCheck = (Class) classList.get(i); 
							if(classToCheck.getClassCode() == classCode)
							{
								classCodeExists = true; 
							}
							
							else 
							{
								classCodeExists = false;
							}
						}
						
						if(classCodeExists = true)
						{
						System.out.print("Course Code existed! Please enter a new Class Code: "); 
						classCode = sc.next(); 
						}
						}
					
					System.out.print("Please enter Lecture Size Group:");
					classSize = sc.nextInt(); 
					
					Class case2NewClass = new Class(courseID, "Lecture", classCode, classSize); 
					
					courseTotalSlot = classSize; 
					courseFreeSlot = courseTotalSlot; 
					
					classList.add(case2NewClass); 
					ClassDB.saveClasses(classFile, classList);
					
					System.out.println(courseName + "(" + courseID + ") " + " Lecture Code: " + classCode + " is created" );
					
					Course case2NewCCourse = new Course(courseID, courseName, courseType, courseProfName, courseFreeSlot, courseTotalSlot);  
					
					courseList.add(case2NewCCourse); 
					CourseDB.saveCourse(courseFile, courseList); 
					
					System.out.print("Course " + courseName + "(" + courseID + ") " + " taught by " + courseProfName + " is created " );
					
					for (int i = 0 ; i < courseList.size() ; i++) {
						
						Course courseToPrint = (Course)courseList.get(i);
						int typeCourse = courseToPrint.getCourseType(); 
						System.out.println("List of Courses:");
						System.out.println("Course ID: " + courseToPrint.getCourseID());
						System.out.println("Course Name: " + courseToPrint.getCourseName());
						System.out.println("Course Coordinator: " + courseToPrint.getCourseProfName());
						
						
						if(typeCourse == 1)
						{
							System.out.println("Course Type: Lecture");
						}
						
						else if(typeCourse == 2)
						{
							System.out.println("Course Type: Lecture/Tutorial");
						}
						
						else if(typeCourse == 3)
						{
							System.out.println("Course Type: Lecture/Tutorial/Lab");
						}
						
						System.out.println("---------------------------------");
						
				}
				}
				/*
				else if(courseType == 2 || courseType == 3)
				{
					//Prompt user to enter the number of Tutorial classes (E.g 4 Tutorial Classes for 2002)
					System.out.print("Please enter the number of Tutorial Classes for " + courseName + "(" + courseID + "): ");
					numOfClass = sc.nextInt(); 
					
					for(int i = 0; i<numOfClass; i++) //For each of the classes, enter the class codes and number of slots 
					{
						System.out.print("Please enter Tutorial Class Code for: " + courseName + "(" + courseID + "): ");
						classCode = sc.next(); 
						
						while(classCodeExists == true) {
							for(int j=0; i<classList.size(); i++ )
							{
								Class classToCheck = (Class) classList.get(i); 
								if(classCode == classToCheck.getClassCode())
								{
									classCodeExists = true; 
								}
								
								else 
								{
									classCodeExists = false;
								}
							}
							
							if(classCodeExists = true)
							{
							System.out.print("Course Code existed! Please enter a new Class Code: "); 
							classCode = sc.next(); 
							}
							}
						
						System.out.print("Please enter Tutorial Class Size for: " + classCode);
						classSize = sc.nextInt(); 
						
						courseTotalSlot = classSize; 
						courseTotalSlot += classSize; 
														
						Class case2NewClass = new Class(courseID, "Tutorial", classCode, classSize); 
						
						classList.add(case2NewClass); 
						ClassDB.saveClasses(classFile, classList);
						
						System.out.println("Tutorial Class Code " + classCode + " for " + courseName + "(" + courseID + ") is created" );
												
					}
					
					if(courseType == 3)
					{
						//Prompt user to enter the number Lab classes (E.g 4 Lab Classes for 2002)
						System.out.print("Please enter the number of Lab Classes for " + courseName + "(" + courseID + "): ");
						numOfClass = sc.nextInt(); 
						
						for(int i = 0; i<numOfClass; i++) //For each of the classes, enter the class codes and number of slots 
						{
							System.out.print("Please enter Class Code for: "  + courseName + "(" + courseID + "): ");
							classCode = sc.next(); 
							
							while(classCodeExists == true) {
								for(int j=0; i<classList.size(); i++ )
								{
									Class classToCheck = (Class) classList.get(i); 
									if(classCode == classToCheck.getClassCode())
									{
										classCodeExists = true; 
									}
									
									else 
									{
										classCodeExists = false;
									}
								}
								
								if(classCodeExists = true)
								{
								System.out.print("Course Code existed! Please enter a new Class Code: "); 
								classCode = sc.next(); 
								}
								}
							
							System.out.print("Please enter the Class Size for: " + classCode);
							classSize = sc.nextInt(); 
							
							courseTotalSlot = classSize; 
							courseTotalSlot += classSize; 
													
							Class case2NewClass = new Class(courseID, "Lab", classCode, classSize); 
							
							classList.add(case2NewClass); 
							
							System.out.println("Lab Class Code " + classCode + " for " + courseName + "(" + courseID + ") is created" );
						
						}
					
					}
					
					System.out.println("Please enter the Lecture Group Code for" + courseName + "( " + courseID + "):  ");
					classCode = sc.next(); 
					
					while(classCodeExists == true) {
						for(int i=0; i<classList.size(); i++ )
						{
							Class classToCheck = (Class) classList.get(i); 
							if(classCode == classToCheck.getClassCode())
							{
								classCodeExists = true; 
							}
							
							else 
							{
								classCodeExists = false;
							}
						}
						
						if(classCodeExists = true)
						{
						System.out.print("Course Code existed! Please enter a new Class Code: "); 
						classCode = sc.next(); 
						}
						}
					
					courseTotalSlot = classSize; 
					courseFreeSlot = courseTotalSlot; 
					
					Class case2NewClass = new Class(courseID, "Lecture", classCode, courseTotalSlot); 
					
					classList.add(case2NewClass); 
					ClassDB.saveClasses(classFile, classList);
					
					Course case2NewCCourse = new Course(courseID, courseName, courseType, courseProfName, courseFreeSlot, courseTotalSlot);  
					
					courseList.add(case2NewCCourse); 
					CourseDB.saveCourse(courseFile, courseList); 
					
					System.out.print("Course " + courseName + "(" + courseID + ") " + " taught by " + courseProfName + " is created " );
								
				}
				
	*/
				
				
			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
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
	
	
	
