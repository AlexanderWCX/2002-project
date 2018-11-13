package schoolsystem;

import java.util.ArrayList; 
import java.io.*; 
import java.util.Scanner;
import java.util.List; 
import java.util.StringTokenizer; 
import schoolsystem.Student;
import schoolsystem.Assessment;
import schoolsystem.Class;

public class Course {
	
	private int courseID; 
	private String courseName;
	private int courseType; 
	private String courseCoordinator; 
	private int courseFreeSlot;
	private int courseTotalSlot; 
	
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private ArrayList<Assessment> assessmentList = new ArrayList<Assessment>();
	private ArrayList<Class> classList = new ArrayList<Class>(); 
	
	static Scanner sc = new Scanner(System.in);
	
	public Course (int courseID, String courseName, int courseType, String professorName, int courseFreeSlot, int courseTotalSlot)
	{
		this.courseID = courseID; 
		this.courseName = courseName; 
		this.courseType = courseType; 
		this.courseCoordinator = professorName; 
		this.courseFreeSlot = courseFreeSlot;
		this.courseFreeSlot = courseTotalSlot;
		
	}
	
	//For Course ID 
	public int getCourseID()
	{
		return courseID; 
	}
	
	public void setCourseID(int courseID)
	{
		this.courseID = courseID; 
	}
		
	
	//For Course Name 
	public String getCourseName()
	{
		return courseName; 
	}
			
	public void setCourseName(String courseName)
	{
		this.courseName = courseName; 
	}
	
	
	//For Course Type 
	public int getCourseType()
	{
		return courseType; 
	}
	
	public void setCourseType(int courseType)
	{
		this.courseType = courseType; 
	}
	
	
	//For Course Coordinator 
	public String getCourseCoordinator()
	{
		return courseCoordinator; 
	}
	
	public void setCourseCoodinator (String professorName)
	{
		this.courseCoordinator = professorName; 
	}
	
		 
	public int getCourseFreeSlot()
	{
		return courseFreeSlot; 
	}
	
	public void setCourseFreeSlot(int courseFreeSlot)
	{
		this.courseFreeSlot = courseFreeSlot; 
	}
	

	public int getCourseTotalSlot()
	{
		return courseTotalSlot; 
	}
	
	public void setCourseTotalSlot(int courseTotalSlot)
	{
		this.courseTotalSlot = courseTotalSlot; 
	}
	
	
	public int getStudentListSize()
	{
		return studentList.size(); 
	}
	
	public int getStudentID(int index)
	{		
		return studentList.get(index).getStudentID(); 
	}
	
	public void addStudent(Student student)
	{
		studentList.add(student); 
	}
	
	public int getAssessmentListSize()
	{
		return assessmentList.size(); 
	}
	
	public String getAssessmentName(int index)
	{		
		return assessmentList.get(index).getAssessmentName(); 
	}
	
	public void addAssessment(Assessment assessment) {
		
		assessmentList.add(assessment); 
	
	}
	
	public int getClassListSize()
	{
		return classList.size(); 
	}
	
	public String getClassCode(int index)
	{		
		return classList.get(index).getClassCode(); 
	}
	
	public void addClass(Class newClass) {
		
		classList.add(newClass); 
	
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
	
	public static void checkCourse(int courseID)
	{
		
	}
	
	public static void checkClass(String classCode)
	{
		boolean classCodeExists = true;
				
			try {
			String classFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Class.txt";
			ArrayList classList = ClassDB.readClasses(classFile); 
			
			
			while(classCodeExists == true) {
				for (int i = 0 ; i < classList.size() ; i++) {
					Class classToCheck = (Class)classList.get(i);
					if (classCode == classToCheck.getClassCode())
						classCodeExists = true;
					else 
						classCodeExists = false;
				}
				
				//prompt if studentID does not exist
				if(classCodeExists == true) {
					System.out.println("Student ID already exists!");
					System.out.println("Enter New Student ID:");
					classCode = sc.next();
					
				}
				}
			
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
}


