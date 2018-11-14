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
				sc.nextLine();
				System.out.println("Enter New Student Name:");
				String studName = sc.nextLine();
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
	    	String classFile = "/Users/trifenacaroline/Downloads/Class.txt";
	    	String courseFile = "/Users/trifenacaroline/Downloads/Course.txt";
	    	String profFile = "/Users/trifenacaroline/Downloads/Professor.txt";
			
	    	int courseID;
			String courseName;
			int courseType;
			String courseProfName;
			int courseFreeSlot; 
			int courseTotalSlot = 0; 
			String classCode;  
			int classSize; 
			int numOfClass; 
			boolean classCodeExists = false;
			boolean courseIDExists = false;
			boolean profNameExists = false;
			
	   	
			try {
				
				ArrayList courseList = CourseDB.readCourses(courseFile);
				ArrayList classList = ClassDB.readClasses(classFile); 
				ArrayList profList = ProfessorDB.readProfessors(profFile); 
					
				System.out.print("Please enter Course Code: "); 
				courseID = sc.nextInt(); 
				
				ArrayList<Class> classAccumulation = new ArrayList<Class>();
				
				for (int i = 0 ; i < courseList.size() ; i++) {
					Course courseToCheck = (Course)courseList.get(i);
					if (courseID == courseToCheck.getCourseID()) {
						courseIDExists = true;
						break;
					}
				}
				
				
				while(courseIDExists == true) {
					System.out.print("Course ID already exist! Please enter a new Course ID: ");
					courseIDExists = false;
					courseID = sc.nextInt();
					
					for (int i = 0 ; i < courseList.size() ; i++) {
						Course courseToCheck = (Course)courseList.get(i);
						if (courseID == courseToCheck.getCourseID()) {
							courseIDExists = true;
							break;
						}
					}
				
				}
				
				String empty = sc.nextLine(); 
			    System.out.print("Please enter Course Name: ");
				courseName = sc.nextLine();
				System.out.print("Please enter Course Professor Name: ");
				courseProfName = sc.nextLine();
				
				for (int i = 0 ; i < profList.size() ; i++) {
					Professor profToCheck = (Professor)profList.get(i);
					if (courseProfName.equals(profToCheck.getProfessorName())) {
						profNameExists = true;
						break;
					}
				}
				
				
				while(profNameExists == false) {
					System.out.print("Invalid Professor Name! Please try again!: ");
					profNameExists = false;
					courseProfName = sc.next();
					
					for (int i = 0 ; i < profList.size() ; i++) {
						Professor profToCheck = (Professor)profList.get(i);
						if (courseProfName.equals(profToCheck.getProfessorName())) {
							profNameExists = true;
							break;
						}
					}
				
				}
				
				
			    System.out.print("Please enter Course Type: (1 - Lecture only, 2 - Lecture/Tutorial only, 3 - Lecture/Tutorial/Lab): ");
				courseType = sc.nextInt();
				 
				if(courseType == 1)
				{
					System.out.print("Please enter Lecture Group Code:");
					classCode = sc.next(); 
					
												
					System.out.print("Please enter Lecture Size Group:");
					classSize = sc.nextInt(); 
					
					Class case2NewClass = new Class(courseID, "Lecture", classCode, classSize); 
					
					courseTotalSlot = classSize; 
					
					courseFreeSlot = courseTotalSlot; 
					
					
					classList.add(case2NewClass); 
					ClassDB.saveClasses(classFile, classList);
					
					System.out.println(courseName + "(" + courseID + ") " + " Lecture Code " + classCode + " is created" );
					
					Course case2NewCCourse = new Course(courseID, courseName, courseType, courseProfName, courseFreeSlot, courseTotalSlot);  
					
					case2NewCCourse.addClass(case2NewClass);
					
					courseList.add(case2NewCCourse); 
					CourseDB.saveCourses(courseFile, courseList); 
					
					System.out.println("Course " + courseName + "(" + courseID + ") " + " taught by " + courseProfName + " is created " );
					printCourses(); 
				
				}
				else {
				if(courseType == 2)
				{
					//Prompt user to enter the number of Tutorial classes (E.g 4 Tutorial Classes for 2002)
					System.out.print("Please enter the number of Tutorial Classes for " + courseName + "(" + courseID + "): ");
					numOfClass = sc.nextInt(); 
					Class case2NewClass;
					for(int i = 0; i<numOfClass; i++) //For each of the classes, enter the class codes and number of slots 
					{
						System.out.print("Please enter Tutorial Class Code for " + courseName + "(" + courseID + "): ");
						classCode = sc.next(); 
						
						for (int j = 0 ; j < classList.size() ; j++) {
							Class classToCheck = (Class)classList.get(j);
							if (classCode.equals(classToCheck.getClassCode())) {
								classCodeExists = true;
								break;
							}
						}
												
						while(classCodeExists == true) {
							System.out.print("Class Code already exist! Please enter a new Class Code: ");
							classCodeExists = false;
							classCode = sc.next();
							
							for (int k = 0 ; k < classList.size() ; k++) {
								Class classToCheck = (Class)classList.get(k);
								if (classCode.equals(classToCheck.getClassCode())) {
									classCodeExists = true;
									break;
								}
							}
						
						}
												
						System.out.print("Please enter Tutorial Class Size for " + classCode + ": ");
						classSize = sc.nextInt(); 
						
						//courseTotalSlot = classSize; 
						courseTotalSlot += classSize; 
						//courseFreeSlot = courseTotalSlot; 
														
						case2NewClass = new Class(courseID, "Tutorial", classCode, classSize); 
						classAccumulation.add(case2NewClass);
						classList.add(case2NewClass); 
						ClassDB.saveClasses(classFile, classList);
						
						System.out.println("Tutorial Class " + classCode + " for " + courseName + "(" + courseID + ") is created" );
					}
						System.out.println("Please enter the Lecture Group Code for " + courseName + "(" + courseID + "): ");
						classCode = sc.next(); 
									
						for (int j = 0 ; j < classList.size() ; j++) {
							Class classToCheck = (Class)classList.get(j);
							if (classCode.equals(classToCheck.getClassCode())) {
								classCodeExists = true;
								break;
							}
						}
												
						while(classCodeExists == true) {
							System.out.print("Class Code already exist! Please enter a new Class Code: ");
							classCodeExists = false;
							classCode = sc.next();
							
							for (int k = 0 ; k < classList.size() ; k++) {
								Class classToCheck = (Class)classList.get(k);
								if (classCode.equals(classToCheck.getClassCode())) {
									classCodeExists = true;
									break;
								}
							}
						
						}
						
						courseFreeSlot = courseTotalSlot; 
						
						Class case2NewClassType2 = new Class(courseID, "Lecture", classCode, courseTotalSlot); 
						
						classList.add(case2NewClassType2); 
						ClassDB.saveClasses(classFile, classList);
						
						Course case2NewCCourse = new Course(courseID, courseName, courseType, courseProfName, courseTotalSlot, courseTotalSlot);  
						
						courseList.add(case2NewCCourse); 
						for(int i = 0 ; i<classAccumulation.size(); i++) {
							Class classToAdd = (Class)classAccumulation.get(i);
							case2NewCCourse.addClass(classToAdd);
						}
						
						case2NewCCourse.addClass(case2NewClassType2);
						CourseDB.saveCourses(courseFile, courseList);
						
						System.out.print("Course " + courseName + "(" + courseID + ") " + " taught by " + courseProfName + " is created " );
						printCourses(); 
					
				}
					else 
					if(courseType == 3)
					{
						//Prompt user to enter the number Lab classes (E.g 4 Lab Classes for 2002)
						System.out.print("Please enter the number of Tut Classes for " + courseName + "(" + courseID + "): ");
						numOfClass = sc.nextInt(); 
						
						for(int i = 0; i<numOfClass; i++) //For each of the classes, enter the class codes and number of slots 
						{
							System.out.print("Please enter Class Code for: "  + courseName + "(" + courseID + "): ");
							classCode = sc.next(); 
							
							for (int j = 0 ; j < classList.size() ; j++) {
								Class classToCheck = (Class)classList.get(j);
								if (classCode.equals(classToCheck.getClassCode())) {
									classCodeExists = true;
									break;
								}
							}
													
							while(classCodeExists == true) {
								System.out.print("Class Code already exist! Please enter a new Class Code: ");
								classCodeExists = false;
								classCode = sc.next();
								
								for (int k = 0 ; k < classList.size() ; k++) {
									Class classToCheck = (Class)classList.get(k);
									if (classCode.equals(classToCheck.getClassCode())) {
										classCodeExists = true;
										break;
									}
								}
							
							}
														
							System.out.print("Please enter the Class Size for: " + classCode + ": ");
							classSize = sc.nextInt(); 
							
							//courseTotalSlot = classSize; 
							courseTotalSlot += classSize; 
													
							Class case2NewTutClass = new Class(courseID, "Tutorial", classCode, classSize); 
							classList.add(case2NewTutClass); 
							Class case2NewLabClass = new Class(courseID, "Lab", classCode, classSize);
							classList.add(case2NewLabClass);
							
							classAccumulation.add(case2NewTutClass);
							classAccumulation.add(case2NewLabClass);
							System.out.println("Tutorial Class Code " + classCode + " for " + courseName + "(" + courseID + ") is created" );
							System.out.println("Lab Class Code " + classCode + " for " + courseName + "(" + courseID + ") is created" );
							
						}
							System.out.println("Please enter the Lecture Group Code for " + courseName + "(" + courseID + "): ");
							classCode = sc.next(); 
										
							for (int j = 0 ; j < classList.size() ; j++) {
								Class classToCheck = (Class)classList.get(j);
								if (classCode.equals(classToCheck.getClassCode())) {
									classCodeExists = true;
									break;
								}
							}
													
							while(classCodeExists == true) {
								System.out.print("Class Code already exist! Please enter a new Class Code: ");
								classCodeExists = false;
								classCode = sc.next();
								
								for (int k = 0 ; k < classList.size() ; k++) {
									Class classToCheck = (Class)classList.get(k);
									if (classCode.equals(classToCheck.getClassCode())) {
										classCodeExists = true;
										break;
									}
								}
							
							}
							
							courseFreeSlot = courseTotalSlot; 
							
							Class case2NewClass = new Class(courseID, "Lecture", classCode, courseTotalSlot); 
							
							classList.add(case2NewClass); 
							ClassDB.saveClasses(classFile, classList);
							
							Course case2NewCCourse = new Course(courseID, courseName, courseType, courseProfName, courseTotalSlot, courseTotalSlot);  
							
							courseList.add(case2NewCCourse);
							courseList.add(case2NewCCourse); 
							for(int i = 0 ; i<classAccumulation.size(); i++) {
								Class classToAdd = (Class)classAccumulation.get(i);
								case2NewCCourse.addClass(classToAdd);
							}
							case2NewCCourse.addClass(case2NewClass);
							
							CourseDB.saveCourses(courseFile, courseList);
							
							System.out.print("Course " + courseName + "(" + courseID + ") " + " taught by " + courseProfName + " is created " );
							printCourses(); 			
						}
						}
					
					
					
					
	
				
				
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
			Class targetClass;
			
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
				
				ArrayList classList = new ArrayList(); // to store list of courses
				classList = ClassDB.readClasses("/Users/trifenacaroline/Downloads/Class.txt");
				
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
				
				int courseType = targetCourse.getCourseType();
				int targetClassIndexInClassList =999;
				int targetLectIndexInClassList = 999;
				if (courseType == 0) {
					targetClass = targetCourse.getClassObject(0);
					String targetclassCode = targetClass.getClassCode();
					for (int i = 0; i < classList.size(); i++) {
						Class checkClass = (Class)classList.get(i);
						
						if (checkClass.getClassCode().equals(targetclassCode))
							targetClassIndexInClassList = i;
					}
				}
				else {
				System.out.println("Enter which class code to register in:");
				System.out.println("List of available class codes");
				System.out.println("Class Code | Available Slots");
				for (int i = 0; i < targetCourse.getClassListSize(); i++) {
					Class printClass = (Class)targetCourse.getClassObject(i);
					String classType = printClass.getClassType();
					if (!classType.equals("Lecture")) {
					System.out.println(printClass.getClassCode() + "       | " + printClass.getFreeSlots());
					}
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
						String classType = printClass.getClassType();
						if (!classType.equals("Lecture")) {
						System.out.println(printClass.getClassCode() + "       | " + printClass.getFreeSlots());
						}
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
					
				targetClass = (Class)targetCourse.getClassObject(targetClassIndex);
				String targetClassClassCode = targetClass.getClassCode();
				
		
				for(int i=0; i<classList.size(); i++) {
					Class testClass = (Class)classList.get(i);
					String testClassClassCode = testClass.getClassCode();
					if (testClassClassCode.equals(targetClassClassCode)) {
						targetClassIndexInClassList = i;
						break;
					}
				}
				
				String lectClassCode = "";
				for(int i=0; i<targetCourse.getClassListSize();i++) {
					Class testClass = targetCourse.getClassObject(i);
					if (testClass.getClassType().equals("Lecture")) {
						lectClassCode = testClass.getClassCode();
						break;
					}
				}
				
				for(int i=0; i<classList.size(); i++) {
					Class testClass = (Class)classList.get(i);
					String testClassClassCode = testClass.getClassCode();
					if (testClassClassCode.equals(lectClassCode)) {
						targetLectIndexInClassList = i;
						break;
					}
				}
				
				}//end of else
				
				int newCourseFreeSlot = 0;
				for(int i = 0; i < targetCourse.getClassListSize(); i++) {
					Class testClass = (Class)targetCourse.getClassObject(i);
					newCourseFreeSlot = newCourseFreeSlot + testClass.getFreeSlots();
					
				}
				targetCourse.setCourseFreeSlot(newCourseFreeSlot);
				
				if (courseType != 0) {
					Class targetLectInClassList = (Class)classList.get(targetLectIndexInClassList);
					targetLectInClassList.addStudent(studenttoRegisterCourse);
				}
				
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
			try {
			ArrayList<Course> courseList = new ArrayList<Course>(); // to store list of courses
			courseList = CourseDB.readCourses("/Users/trifenacaroline/Downloads/Course.txt");
			
			int courseID;
			boolean courseAdded = false;
			int targetCourseIndex = 999;
			
			System.out.println("Please enter Course ID:");
			courseID = sc.nextInt();
			
			//checking if courseID exists in courseList
			for (int j = 0; j < courseList.size(); j++) {
				Course courseToTest = (Course)courseList.get(j);
				int courseToTestID = courseToTest.getCourseID();
				if (courseToTestID == courseID) {
					courseAdded = true;
					targetCourseIndex = j;
					break;
				} 
			}
			
			while (courseAdded == false) {
				System.out.println("Course ID does not exist!");
				System.out.println("Enter Course to Register:");
				courseID = sc.nextInt();
				
				for (int j = 0; j < courseList.size(); j++) {
					Course courseToTest = (Course)courseList.get(j);
					int courseToTestID = courseToTest.getCourseID();
					if (courseToTestID == courseID) {
						courseAdded = true;
						targetCourseIndex = j;
						break;
					} 
				}
				
			}
			
			Course targetCourse = courseList.get(targetCourseIndex);
			
			System.out.println("List of slots for " + courseID + " :");
			System.out.println("Class Type | Class Code | Free Slots / Total Slots");
			
			for(int i=0; i<targetCourse.getClassListSize(); i++) {
				Class classToPrint = (Class)targetCourse.getClassObject(i);
				String classType = classToPrint.getClassType();
				String classCode = classToPrint.getClassCode();
				int freeSlots = classToPrint.getFreeSlots();
				int totalSlots = classToPrint.getClassSize();
				if (classType.equals("Lecture")) {
				System.out.println(classType+ "    | " + classCode + "       | " + freeSlots +" / " + totalSlots);
				} else if (classType.equals("Tutorial")) {
					System.out.println(classType+ "   | " + classCode + "       | " + freeSlots +" / " + totalSlots);
				} else if (classType.equals("Lab")) {
					System.out.println(classType+ "        | " + classCode + "       | " + freeSlots +" / " + totalSlots);
				}
				
			}
			
			System.out.println("All slot information printed!");
			
			
			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
			
		}		
		
		public void printCourseStudentList(){
			
			boolean courseAdded = false;
			int targetCourseIndex = 999;
			int courseType;
			
			System.out.println("Please enter a course ID:");
    		int courseID = sc.nextInt(); 
    		
    		try {
    		String courseFile = "/Users/trifenacaroline/Downloads/Course.txt";
    		String studentFile = "/Users/trifenacaroline/Downloads/student.txt";
    		
    		ArrayList<Course> courseList = new ArrayList<Course>();
    		ArrayList<Student> studentList = new ArrayList<Student>();
    		
    		courseList = CourseDB.readCourses(courseFile);
    		studentList = StudentDB.readStudents(studentFile); 
    		
    		//checking if courseID exists in courseList
			for (int j = 0; j < courseList.size(); j++) {
				Course courseToTest = (Course)courseList.get(j);
				int courseToTestID = courseToTest.getCourseID();
				if (courseToTestID == courseID) {
					courseAdded = true;
					targetCourseIndex = j;
					break;
				} 
			}
			
			while (courseAdded == false) {
				System.out.println("Course ID does not exist!");
				System.out.println("Enter Course to Register:");
				courseID = sc.nextInt();
				
				for (int j = 0; j < courseList.size(); j++) {
					Course courseToTest = (Course)courseList.get(j);
					int courseToTestID = courseToTest.getCourseID();
					if (courseToTestID == courseID) {
						courseAdded = true;
						targetCourseIndex = j;
						break;
					} 
				}
				
			}
			
			Course targetCourse = (Course)courseList.get(targetCourseIndex);
			
			
			for (int i = 0 ; i < targetCourse.getClassListSize(); i++) {
				Class targetClass = (Class)targetCourse.getClassObject(i);
				String classType = targetClass.getClassType();
				String classCode = targetClass.getClassCode();
				
				System.out.println("Class Type: " + classType + " | Class Code: " + classCode );
				System.out.println("List of students:");
				for (int j = 0; j < targetClass.getStudentListSize(); j++) {
					Student studentToPrint = (Student)targetClass.getStudentObject(j);
					int studentID = studentToPrint.getStudentID();
					String studentName = studentToPrint.getStudentName();
					
					System.out.println(studentID + " " + studentName);
				}
				System.out.println("---------------------------------------");
			}
			
			System.out.println("All student lists printed!");
    		
    		} 
    		catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
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
			courseList = CourseDB.readCourses("/Users/trifenacaroline/Downloads/Course.txt");
			
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
			assessmentList = AssessmentDB.readAssessments("/Users/trifenacaroline/Downloads/Assessment.txt");
			
			for(int i =0; i<assessmentList.size(); i++) {
				System.out.println(assessmentList.get(i).getAssessmentName());
			}
			
			
			while (madeBefore) {
				
				
				if (targetCourse.getAssessmentListSize()==0) {
					madeBefore = false;
				}
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
			CourseDB.saveCourses("/Users/trifenacaroline/Downloads/Course.txt", courseList);
			AssessmentDB.saveAssessments("/Users/trifenacaroline/Downloads/Assessment.txt", assessmentList);
			
			System.out.println("Assessment succesfully added!");
			
			} catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
		
		
		
public void enterAssessmentMark(){
			
			
			try {
				
				int studentID;
				int courseID;
				int marks;
				String assessmentName;
				int targetStudentIndex=0;
				int targetAssessmentIndex=0;
				boolean studentExist = false;
				boolean courseExist = false;
				boolean assessmentExist = false;
				Assessment targetAssessment;
				Score targetScore;
				boolean duplicate = false;
				
				ArrayList<Student> studentList = new ArrayList();
				ArrayList<Course> courseList = new ArrayList();
				ArrayList<Assessment> assessmentList = new ArrayList();
				ArrayList<Score> scoreList = new ArrayList();
				//reading in the student list, course list and assessment list from the text files
				String studentFile = "/Users/trifenacaroline/Downloads/student.txt";
				String courseFile = "/Users/trifenacaroline/Downloads/Course.txt";
				String assessmentFile = "/Users/trifenacaroline/Downloads/Assessment.txt";
				String scoreFile = "/Users/trifenacaroline/Downloads/Score.txt";
				studentList = StudentDB.readStudents(studentFile);
				courseList = CourseDB.readCourses(courseFile);
				assessmentList = AssessmentDB.readAssessments(assessmentFile);
				scoreList = ScoreDB.readScores(scoreFile);
				
				
				System.out.println("Enter Student ID: ");
				studentID = sc.nextInt();
				
				
				while(studentExist == false){
					
					for(int i=0; i < studentList.size(); i++) {
						if(studentID == studentList.get(i).getStudentID()) {
							studentExist = true;
							targetStudentIndex = i;
							break;
						}
					
					}
					
					if(studentExist == false) {
						System.out.println("StudentID not found please try again");
						System.out.println("Enter the Students ID: ");
						studentID = sc.nextInt();
					}
				};
				
				//the chosen student
				Student targetStudent = studentList.get(targetStudentIndex);
				
				//the courses the chosen student takes
				ArrayList<Course> studentcourseList = targetStudent.getCourseList(); 
				
				System.out.println("Enter Course ID: ");
				courseID = sc.nextInt();
				
				while(courseExist == false){
					
					for(int k=0; k < studentcourseList.size(); k++) {
						if(courseID == studentcourseList.get(k).getCourseID()) {
							courseExist = true;
							break;
						}
					
					}
					
					if(courseExist == false){
						System.out.println("CourseID not found in students list of courses, please try again");
						System.out.println("Enter Course ID: ");
						studentID = sc.nextInt();
					}
				};
				
				String empty = sc.nextLine();
				System.out.println("Enter the assessment name: ");
				assessmentName = sc.nextLine();
				
				while(assessmentExist == false){
					
					for(int p=0; p < assessmentList.size(); p++) {
						if(assessmentName.equals(assessmentList.get(p).getAssessmentName()) 
								&& courseID==assessmentList.get(p).getCourseID()) {
							assessmentExist = true;
							targetAssessmentIndex = p;
							break;
						}
					
					}
					
					if(assessmentExist == false){
						System.out.println("Assessment name not found in the list of assessments, please try again");
						System.out.println("Enter the assessment name: ");
						studentID = sc.nextInt();
					}
				};
				
				//chosen assessment
				targetAssessment = assessmentList.get(targetAssessmentIndex);
				
				
				//chosen score
				targetScore = targetAssessment.getScore();
				for(int u=0; u<scoreList.size();u++) {
				if(targetScore.getCourseID()==scoreList.get(u).getCourseID()
						&& targetScore.getAssessmentName().equals(scoreList.get(u).getAssessmentName())) {
					targetScore=scoreList.get(u);
					duplicate = true;
					
				};
				};
				
				System.out.println("Enter the Score(out of 100): ");
				marks = sc.nextInt();
				
				while(marks>100 || marks<0) {
					System.out.println("Marks does not fall within range of 0 to 100 ");
					System.out.println("Enter the Score(out of 100): ");
					marks = sc.nextInt();
				}
				
				targetScore.addMarks(marks);
				targetScore.addStudent(targetStudent);
				
				if (duplicate == false) {
					scoreList.add(targetScore);
				}
				
				ScoreDB.saveScores(scoreFile, scoreList);
				
				System.out.println("Score added!");
				
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
			
		public void	printCourseStats() {
			
			System.out.println("Please enter a course ID:");
    		int courseID = sc.nextInt(); 
    		
    		try {
    		String courseFile = "/Users/trifenacaroline/Downloads/Course.txt";
    		//String studentFile = "/Users/trifenacaroline/Downloads/student.txt";
    		String scoreFile = "/Users/trifenacaroline/Downloads/Score.txt";
    		
    		ArrayList<Course> courseList = new ArrayList<Course>();
    		//ArrayList<Student> studentList = new ArrayList<Student>();
    		ArrayList<Score> scoreList = new ArrayList<Score>();
    		
    		courseList = CourseDB.readCourses(courseFile);
    		//studentList = StudentDB.readStudents(studentFile); 
    		boolean courseAdded = false;
    		int targetCourseIndex = 999;
    		
    		//checking if courseID exists in courseList
			for (int j = 0; j < courseList.size(); j++) {
				Course courseToTest = (Course)courseList.get(j);
				int courseToTestID = courseToTest.getCourseID();
				if (courseToTestID == courseID) {
					courseAdded = true;
					targetCourseIndex = j;
					break;
				} 
			}
			
			while (courseAdded == false) {
				System.out.println("Course ID does not exist!");
				System.out.println("Enter Course to Register:");
				courseID = sc.nextInt();
				
				for (int j = 0; j < courseList.size(); j++) {
					Course courseToTest = (Course)courseList.get(j);
					int courseToTestID = courseToTest.getCourseID();
					if (courseToTestID == courseID) {
						courseAdded = true;
						targetCourseIndex = j;
						break;
					} 
				}
				
			}
			
			Course targetCourse = (Course)courseList.get(targetCourseIndex);
			
			System.out.println("Overall Performance (Exam + Coursework)");
			float allStudentTotalGrade =0;
			
			for (int i = 0; i<targetCourse.getStudentListSize(); i++) {
				int studentID = targetCourse.getStudentID(i);
				float totalMarksForThisStudent = 0;
				int targetStudentIndex = 999;
				
				for (int j = 0; j<targetCourse.getAssessmentListSize(); j++) {
				Assessment assessmentToCalc = (Assessment) targetCourse.getAssessment(j);
				Score score = assessmentToCalc.getScore();
				
				
					for (int k = 0; k<score.getStudentListSize(); k++) {
					int testStudentID = score.getStudentID(k);
					if (testStudentID == studentID) {
						targetStudentIndex = k;
					}
				    }
					
				int studentScore = score.getMarks(targetStudentIndex);
				float calcScore = (float) studentScore;
				int weightage = assessmentToCalc.getWeightage();
				float calcWeightage = (float) weightage;
				totalMarksForThisStudent = totalMarksForThisStudent + ((100 *(calcScore / 100) *  (calcWeightage / 100)));
				
				}
				
				allStudentTotalGrade = allStudentTotalGrade + totalMarksForThisStudent;
				
			}
			int noOfStudent = targetCourse.getStudentListSize();
			float overallgrade = allStudentTotalGrade / noOfStudent;
			System.out.println("Overall Grade:" + overallgrade);
			System.out.println("--------------------------------------");
			
			int examWeightage = 0;
			System.out.println("Exam Performance");
			float allStudentExamGrade = 0;
			
			for (int i = 0; i<targetCourse.getStudentListSize(); i++) {
				int studentID = targetCourse.getStudentID(i);
				float totalMarksForThisStudent = 0;
				int targetStudentIndex = 999;
				
				for (int j = 0; j<targetCourse.getAssessmentListSize(); j++) {
				
				Assessment assessmentToCalc = (Assessment) targetCourse.getAssessment(j);
				int assessmentType = assessmentToCalc.getCoursework();
				if (assessmentType == 0) {
				Score score = assessmentToCalc.getScore();
				examWeightage = assessmentToCalc.getWeightage();
				
					for (int k = 0; k<score.getStudentListSize(); k++) {
					int testStudentID = score.getStudentID(k);
					if (testStudentID == studentID) {
						targetStudentIndex = k;
					}else {
						
					}
				    }
					
				int studentScore = score.getMarks(targetStudentIndex);
				float calcScore = (float)studentScore;
				totalMarksForThisStudent = totalMarksForThisStudent + calcScore;
				
				} else {
					break;
				}
				
				}
				System.out.println("allstudentexamgrade here is "+ allStudentExamGrade);
				allStudentExamGrade = allStudentExamGrade + totalMarksForThisStudent;
				
			}
			
			
			float overallExamGrade = allStudentExamGrade / noOfStudent;
			System.out.println("Overall Exam Grade:" + overallExamGrade);
			System.out.println("--------------------------------------");
			
			
			System.out.println("Coursework Performance");
			
			float allStudentCourseworkGrade = 0;
			int totalCourseWorkWeightage = 100 - examWeightage;
					
			for (int i = 0; i<targetCourse.getStudentListSize(); i++) {
				int studentID = targetCourse.getStudentID(i);
				float totalMarksForThisStudent = 0;
				int targetStudentIndex = 999;
				
				for (int j = 0; j<targetCourse.getAssessmentListSize(); j++) {
				
				Assessment assessmentToCalc = (Assessment) targetCourse.getAssessment(j);
				int assessmentType = assessmentToCalc.getCoursework();
				int weightage = assessmentToCalc.getWeightage();
				if (assessmentType == 1) {
					
				Score score = assessmentToCalc.getScore();
				
				
					for (int k = 0; k<score.getStudentListSize(); k++) {
					int testStudentID = score.getStudentID(k);
					if (testStudentID == studentID) {
						targetStudentIndex = k;
					}else {
						
					}
				    }
				
				int studentScore = score.getMarks(targetStudentIndex);
				float calcScore = (float)studentScore;
				float calcWeightage = (float)weightage;
				float calcTotalCourseWeightage = (float) totalCourseWorkWeightage;
				totalMarksForThisStudent = totalMarksForThisStudent + (100 *(calcScore / 100) *  (calcWeightage / calcTotalCourseWeightage));
				
				
				} else {
					break;
				}
				
				}
				
				allStudentCourseworkGrade = allStudentCourseworkGrade + totalMarksForThisStudent;
				
			}
			
			
			float overallCourseworkGrade = allStudentExamGrade / noOfStudent;
			System.out.println("Overall Coursework Grade:" + overallCourseworkGrade);
			
			
			
			
			
    		} 
    		catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
		
		}
				
		
		public void printStudentTranscript() {
			
			boolean studentIDExist = false; 
			int targetStudentIndex = 999;
			float overallGrade = 0;
			String studentFile = "/Users/trifenacaroline/Downloads/student.txt";
	    	String classFile = "/Users/trifenacaroline/Downloads/Class.txt";
	    	String courseFile = "/Users/trifenacaroline/Downloads/Course.txt";
	    	String assessmentFile = "/Users/trifenacaroline/Downloads/Assessment.txt";
	    	
			try {
				
				ArrayList courseList = CourseDB.readCourses(courseFile);
				ArrayList classList = ClassDB.readClasses(classFile); 
				ArrayList studentList = StudentDB.readStudents(studentFile) ;
				
				System.out.println("Please enter a Student ID: ");
				int studentID = sc.nextInt(); 
							
				for (int i = 0 ; i < studentList.size() ; i++) {
					Student studentToCheck = (Student)studentList.get(i);
					if (studentID == studentToCheck.getStudentID()) {
						studentIDExist = true;
						targetStudentIndex = i;
						break;
					}
					
				}
				
				//prompt if studentID does not exist
				while(studentIDExist == false) {
					System.out.println("Student ID does not exist!");
					System.out.println("Enter Student ID:");
					studentID = sc.nextInt();
					
					for (int i = 0 ; i < studentList.size() ; i++) {
						Student studentToCheck = (Student)studentList.get(i);
						if (studentID == studentToCheck.getStudentID()) {
							studentIDExist = true;
							targetStudentIndex = i;
							break;
						}
						
					}
				
				}
				
				Student targetStudent = (Student)studentList.get(targetStudentIndex);
				System.out.println("Student " + studentID + "'s Transcript: ");
				int targetScoreIndex = 999;
				for(int i = 0; i < targetStudent.getCourseListSize(); i++) {
					Course courseToPrint = (Course)targetStudent.getCourseObject(i);
					int courseID = courseToPrint.getCourseID();
					String courseName = courseToPrint.getCourseName();
					
					System.out.println(courseID + " " + courseName + " ");
					
					
					for (int j = 0; j < courseToPrint.getAssessmentListSize(); j++) {
						Assessment assessmentToPrint = (Assessment)courseToPrint.getAssessment(j);
						String assessmentName = assessmentToPrint.getAssessmentName();
						int weightage = assessmentToPrint.getWeightage();
						int coursework = assessmentToPrint.getCoursework();
						String assessmentType = "";
							if (coursework == 1) {
							assessmentType = "Coursework ";
							} else if (coursework == 0){
							assessmentType = "Non-Coursework ";
							}
						Score scoreObject = assessmentToPrint.getScore();
						
						for (int k = 0; k<scoreObject.getStudentListSize(); k++) {
							if (studentID == scoreObject.getStudentID(j)) {
								targetScoreIndex = k;
								break;
							}
						}
						int marks = scoreObject.getMarks(targetScoreIndex);
						float calcmarks =(float)marks;
						float calcweightage = (float)weightage;
					System.out.println(assessmentType + " - " + assessmentName + "(" + weightage + "%) = " + marks);
						overallGrade = overallGrade + 100*((calcmarks / 100) * (calcweightage / 100));
					}
					System.out.println("Overall Score : " + overallGrade );
					
					
				}
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
			
		
		
		}			
		public static void printCourses()
		{
			String courseFile = "/Users/trifenacaroline/Downloads/Course.txt";
			
			try {
				ArrayList courseList = CourseDB.readCourses(courseFile);
				
				System.out.println("---------------------------------");
				System.out.println("List of Courses:");
				
				for (int i = 0 ; i < courseList.size() ; i++) {
					
					Course courseToPrint = (Course)courseList.get(i);
					int typeCourse = courseToPrint.getCourseType(); 
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
				
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
}
	
	
	
