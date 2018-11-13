package schoolsystem;


import schoolsystem.Course;
import schoolsystem.CourseDB;
import schoolsystem.Assessment;
import schoolsystem.Class;

import java.io.IOException;
import java.util.*;

public class SchoolSystem {
	
	public static final String SEPARATOR = "|";	
	static Scanner sc = new Scanner(System.in);
		
		public void addStudent () {
			boolean studentIDExists = false;
			
	    	String studentFile = "/Users/trifenacaroline/Downloads/student.txt";
	    	try {
				
				ArrayList studentList = StudentDB.readStudents(studentFile) ;
				
				System.out.println("Enter New Student ID:");
				int studentID = sc.nextInt();
				
				
				for (int i = 0 ; i < studentList.size() ; i++) {
					Student studentToCheck = (Student)studentList.get(i);
					if (studentID == studentToCheck.getStudentID()) {
						studentIDExists = true;
						break;
					}
				}
				
				//prompt if studentID does not exist
				while(studentIDExists == true) {
					System.out.println("Student ID already exists!");
					studentIDExists = false;
					System.out.println("Enter New Student ID:");
					studentID = sc.nextInt();
					
					for (int i = 0 ; i < studentList.size() ; i++) {
						Student studentToCheck = (Student)studentList.get(i);
						if (studentID == studentToCheck.getStudentID()) {
							studentIDExists = true;
							break;
						}
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
					CourseDB.saveCourses(courseFile, courseList); 
					
					System.out.print("Course " + courseName + "(" + courseID + ") " + " taught by " + courseProfName + " is created " );
					
					for (int i = 0 ; i < courseList.size() ; i++) {
						
						Course courseToPrint = (Course)courseList.get(i);
						int typeCourse = courseToPrint.getCourseType(); 
						System.out.println("List of Courses:");
						System.out.println("Course ID: " + courseToPrint.getCourseID());
						System.out.println("Course Name: " + courseToPrint.getCourseName());
						System.out.println("Course Coordinator: " + courseToPrint.getCourseCoordinator());
						
						
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
			
			int studentID;
			int courseIDToAdd = 999;
			int targetStudentIndex = 999;
			boolean studentIDExists = false;
			boolean courseAdded = false;
			String classCode;
			int targetCourseIndex = 999;
			boolean validClassCode = false;
			int targetClassIndex = 999;
			boolean studentAlreadyInCourse = true;
			
			
			try {
				String studentFile = "/Users/trifenacaroline/Downloads/student.txt";
				ArrayList studentList = StudentDB.readStudents(studentFile); 
				
				System.out.println("Enter Student ID:");
				studentID = sc.nextInt();
				
				for (int i = 0 ; i < studentList.size() ; i++) {
					Student studentToCheck = (Student)studentList.get(i);
					if (studentID == studentToCheck.getStudentID()) {
						studentIDExists = true;
						targetStudentIndex = i;
						break;
					}
					
				}
				
				//prompt if studentID does not exist
				while(studentIDExists == false) {
					System.out.println("Student ID does not exist!");
					System.out.println("Enter Student ID:");
					studentID = sc.nextInt();
					
					for (int i = 0 ; i < studentList.size() ; i++) {
						Student studentToCheck = (Student)studentList.get(i);
						if (studentID == studentToCheck.getStudentID()) {
							studentIDExists = true;
							targetStudentIndex = i;
							break;
						}
						
					}
					
				
				}
				
				//create student object for target student
				Student studenttoRegisterCourse = (Student)studentList.get(targetStudentIndex);
				
				
				System.out.println("Enter Course to Register:");
				courseIDToAdd = sc.nextInt();
				
				
				//import list of courses into an arraylist
				ArrayList courseList = new ArrayList(); // to store list of courses
				courseList = CourseDB.readCourses("/Users/trifenacaroline/Downloads/Course.txt");
				
				//checking if courseID exists in courseList
				for (int j = 0; j < courseList.size(); j++) {
					Course courseToTest = (Course)courseList.get(j);
					int courseToTestID = courseToTest.getCourseID();
					if (courseToTestID == courseIDToAdd) {
						courseAdded = true;
						targetCourseIndex = j;
						break;
					} 
				}
				
				while (courseAdded == false) {
					System.out.println("Course ID does not exist!");
					System.out.println("Enter Course to Register:");
					courseIDToAdd = sc.nextInt();
					
					for (int j = 0; j < courseList.size(); j++) {
						Course courseToTest = (Course)courseList.get(j);
						int courseToTestID = courseToTest.getCourseID();
						if (courseToTestID == courseIDToAdd) {
							courseAdded = true;
							targetCourseIndex = j;
							break;
						} 
					}
					
				}
				
				
				
				System.out.println("courselistsize is:" + studenttoRegisterCourse.getCourseListSize());
				
				for (int i=0; i < studenttoRegisterCourse.getCourseListSize(); i++) {
					if (studenttoRegisterCourse.getCourseID(i) == courseIDToAdd) {
						System.out.println("this student has course :" + studenttoRegisterCourse.getCourseID(i));
						System.out.println("Student already in this course!");
						break;
					}
					else if ((i == (studenttoRegisterCourse.getCourseListSize()-1)) && studenttoRegisterCourse.getCourseID(i) != courseIDToAdd )  {
						studentAlreadyInCourse = false;
					}
				}
				
				if (studenttoRegisterCourse.getCourseListSize()==0) {
					studentAlreadyInCourse = false;
				}
				
				while(studentAlreadyInCourse) {
					System.out.println("Enter Course to Register:");
					courseIDToAdd = sc.nextInt();
					
					for (int i=0; i < studenttoRegisterCourse.getCourseListSize(); i++) {
						if (studenttoRegisterCourse.getCourseID(i) == courseIDToAdd) {
							System.out.println("Student already in this course!");
							break;
						}
						if ((i == (studenttoRegisterCourse.getCourseListSize()-1)) && studenttoRegisterCourse.getCourseID(i) != courseIDToAdd ) {
							studentAlreadyInCourse = false;
						}
					}
				}
				
				
				Course targetCourse = (Course)courseList.get(targetCourseIndex);
				studenttoRegisterCourse.addCourse(targetCourse);
				
				System.out.println("Enter which class code to register in:");
				System.out.println("List of available class codes");
				System.out.println("Class Code | Available Slots");
				for (int i = 0; i < targetCourse.getClassListSize(); i++) {
					Class printClass = (Class)targetCourse.getClassObject(i);
					System.out.println(printClass.getClassCode() + "       | " + printClass.getFreeSlots());
				}
				
				classCode = sc.next();
				
				for (int i = 0; i <targetCourse.getClassListSize(); i++) {
					Class testClass = (Class)targetCourse.getClassObject(i);
					if (testClass.getClassCode().equals(classCode)) {
						if (testClass.getFreeSlots() != 0) {
							validClassCode = true;
							targetClassIndex = i;
							break;
						}
						else {
							System.out.println("This class code has no vacancy. Please pick a class code with vacancy.");
							break;
						}
					}
					if (i == (targetCourse.getClassListSize()-1)) {
						System.out.println("Class code not found. Please enter a valid class code.");
						break;
					}
				}
		
				while (validClassCode == false) {
					System.out.println("Enter which class code to register in:");
					System.out.println("List of available class codes");
					System.out.println("Class Code | Available Slots");
					for (int i = 0; i < targetCourse.getClassListSize(); i++) {
						Class printClass = (Class)targetCourse.getClassObject(i);
						System.out.println(printClass.getClassCode() + "       | " + printClass.getFreeSlots());
					}
					
					for (int i = 0; i < targetCourse.getClassListSize(); i++) {
						Class printClass = (Class)targetCourse.getClassObject(i);
						//String printout = "%s | %d", printClass.getClassCode(), printClass.getFreeSlots());
					}
					
					classCode = sc.next();
					
					for (int i = 0; i <targetCourse.getClassListSize(); i++) {
						Class testClass = (Class)targetCourse.getClassObject(i);
						if (testClass.getClassCode().equals(classCode)) {
							if (testClass.getFreeSlots() != 0) {
								validClassCode = true;
								targetClassIndex = i;
								break;
							}
							else {
								System.out.println("This class code has no vacancy. Please pick a class code with vacancy.");
								break;
							}
						}
						if (i == targetCourse.getClassListSize()-1) {
							System.out.println("Class code not found. Please enter a valid class code.");
							break;
						}
					}
				}
					
				Class targetClass = (Class)targetCourse.getClassObject(targetClassIndex);
				String targetClassClassCode = targetClass.getClassCode();
				
				
				ArrayList classList = new ArrayList(); // to store list of courses
				classList = ClassDB.readClasses("/Users/trifenacaroline/Downloads/Class.txt");
				
				int targetClassIndexInClassList = 999;
				for(int i=0; i<classList.size(); i++) {
					Class testClass = (Class)classList.get(i);
					String testClassClassCode = testClass.getClassCode();
					if (testClassClassCode.equals(targetClassClassCode)) {
						targetClassIndexInClassList = i;
						break;
					}
				}
				
				int newCourseFreeSlot = 0;
				for(int i = 0; i < targetCourse.getClassListSize(); i++) {
					Class testClass = (Class)targetCourse.getClassObject(i);
					newCourseFreeSlot = newCourseFreeSlot + testClass.getFreeSlots();
					
				}
				targetCourse.setCourseFreeSlot(newCourseFreeSlot);
				
				
				Class targetClassInClassList = (Class)classList.get(targetClassIndexInClassList);
				
				
				targetClassInClassList.addStudent(studenttoRegisterCourse);
				targetClass.addStudent(studenttoRegisterCourse);
				targetCourse.addStudent(studenttoRegisterCourse);
				
				
				//write new student added to Class.txt and Course.txt
				CourseDB.saveCourses("/Users/trifenacaroline/Downloads/Course.txt", courseList);
				ClassDB.saveClasses("/Users/trifenacaroline/Downloads/Class.txt", classList);
				
				//write student record(s) to file after addition of course
				StudentDB.saveStudents(studentFile, studentList); 
				
				for (int i = 0 ; i < studentList.size() ; i++) {
						Student studenttoprint = (Student)studentList.get(i);
						System.out.println("Student ID: " + studenttoprint.getStudentID());
						System.out.println("Student Name: " + studenttoprint.getStudentName() );
						System.out.println("Courses Registered are: ");
						studenttoprint.printCourses();
						System.out.println("---------------------------------");
				}
				
			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
			
			
			
		}	
		
			
		public void checkClassAvailability() {
			
			int case4CourseCode;
			int case4ClassCode; 
						
			System.out.println("Please enter Course Code:");
			case4CourseCode = sc.nextInt();
			System.out.println("Please enter Class Code for " + case4CourseCode);
			case4ClassCode = sc.nextInt();
			
			
		}
		
		
		public void printCourseStudentList(){
			
			System.out.println("Please enter a course ID:");
    		int courseCode = sc.nextInt(); 
    		
    		try {
    		String courseFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Course.txt";
    		String studentFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\student.txt";
    		
    		ArrayList arrayReadCourse = CourseDB.readCourses(courseFile);
    		
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
			 
		}
			
			
		
		
		public void addAssessment () {
			int courseID;
			String assessmentName;
			int targetCourseIndex = 0;
			boolean courseExist = false;
			Course targetCourse;
			boolean falsebefore=false;
			boolean madeBefore = true;
			int courseworkInput;
			int weightage;
			
			System.out.println("Enter the courseID of the course: ");
			courseID = sc.nextInt();
			sc.nextLine();
			
			try {
			ArrayList<Course> courseList = new ArrayList(); // to store list of courses
			courseList = CourseDB.readCourses("C:\\Users\\xanwo\\eclipse-workspace\\java2002project\\src\\schoolsystem\\Course.txt");
			
			while (courseExist == false ) {
				for (int j = 0; j < courseList.size(); j++) {
					Course courseToTest = (Course)courseList.get(j);
					int courseToTestID = courseToTest.getCourseID();
					if (courseToTestID == courseID) {
						
						targetCourseIndex = j;
						
						courseExist = true;
					}
					else {
						courseExist = false;
					}
				}
				
				if(courseExist == false) {
				falsebefore=true;
				System.out.println("Course does not exist!");
				System.out.println("Enter the courseID of course to add assessment to: ");
				courseID = sc.nextInt();
				}
			
			}
			
			/*
			for (int j = 0; j < courseList.size(); j++) {
				Course courseToTest = (Course)courseList.get(j);
				int courseToTestID = courseToTest.getCourseID();
				if (courseToTestID == courseID) {
					targetCourseIndex = j;
					courseExist = true;
				}
				else {
					courseExist = false;
				}
			}
			
			if(courseExist == false) {
				System.out.println("Course does not exist");
			}
			
			*/
			targetCourse = (Course)courseList.get(targetCourseIndex);
			System.out.println(targetCourse.getCourseID());
			System.out.println(targetCourse.getAssessmentListSize());
			for (int p = 0; p < targetCourse.getAssessmentListSize() ; p++) {
				System.out.println(targetCourse.getAssessmentName(p));
			}
			if(falsebefore) {
			String empty =sc.nextLine();
			}
			System.out.println("Enter the name of the assessment: ");
			
			assessmentName = sc.nextLine();
			
			ArrayList<Assessment> assessmentList = new ArrayList(); // to store list of courses
			assessmentList = AssessmentDB.readAssessments("C:\\Users\\xanwo\\eclipse-workspace\\java2002project\\src\\schoolsystem\\Assessment.txt");
			
			for(int i =0; i<assessmentList.size(); i++) {
				System.out.println(assessmentList.get(i).getAssessmentName());
			}
			
			
			while (madeBefore) {
				
				System.out.println("assessment list size is " + targetCourse.getAssessmentListSize());
				for (int i=0; i< targetCourse.getAssessmentListSize(); i++) {
				
				Assessment assessmentToTest = targetCourse.getAssessment(i);
				if (assessmentName.equals(assessmentToTest.getAssessmentName())) {
						
					madeBefore = true;
							
				}
				else {
					
					madeBefore = false;
				}
			}
		
			if(madeBefore == true) {
				String assessmentinvalid;
				assessmentinvalid = String.format("Assessment with the same assessment name has been made before for CourseID %d ", courseID);
				System.out.println(assessmentinvalid);
				System.out.println("Enter the name of the assessment: ");
				
				assessmentName = sc.nextLine();
			}
			
			}
			
			/*
			for (int i=0; i< targetCourse.getAssessmentListSize(); i++) {
				Assessment assessmentToTest = targetCourse.getAssessment(i);
				if (assessmentName == assessmentToTest.getAssessmentName()) {
							madeBefore = true;
				}
				else {
					madeBefore = false;
				}
			}
			*/
			System.out.println("Is this assessment a Coursework sub-component? ");
			System.out.println("Enter 1 for Yes");
			System.out.println("Enter 0 for No");
			courseworkInput = sc.nextInt();
			
			System.out.println("Enter the weightage of the assessment(out of 100): ");
			weightage = sc.nextInt();
			
			for(int i =0; i<assessmentList.size(); i++) {
				System.out.println(assessmentList.get(i).getAssessmentName());
			}
			
			Assessment newAssessment = new Assessment(courseID, assessmentName, weightage, courseworkInput);
			assessmentList.add(newAssessment);
			targetCourse.addAssessment(newAssessment);
			CourseDB.saveCourses("C:\\Users\\xanwo\\eclipse-workspace\\java2002project\\src\\schoolsystem\\Course.txt", courseList);
			AssessmentDB.saveAssessments("C:\\Users\\xanwo\\eclipse-workspace\\java2002project\\src\\schoolsystem\\Assessment.txt", assessmentList);
			
			System.out.println("Assessment succesfully added!");
			
			} catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
		
		
		
		public void enterAssessmentMark(){
			
			System.out.println("Enter the Students ID: ");
			System.out.println("Enter the course ID: ");
			
		}
		
			
		public void	printCourseStats() {
			
		}
		
		
		public void printStudentTranscript() {
			
			
		
		
		}	
		
		
}
	
	
	
