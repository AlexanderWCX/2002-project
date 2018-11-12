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
	private int courseFreeSlot;
	private int courseTotalSlot; 
	private int courseType; 
	String courseName;
	String courseProfName; 
	
	public ArrayList<Class> classList = new ArrayList<Class>(); 
	private ArrayList<Student> studentList = new ArrayList<Student>();
	public ArrayList<Assessment> assessmentList = new ArrayList<Assessment>();
	 
	static Scanner sc = new Scanner(System.in);
	
	public Course (int courseID, String courseName, int courseType, String courseProfName, int courseFreeSlot, int courseTotalSlot)
	{
		this.courseID = courseID; 
		this.courseName = courseName; 
		this.courseType = courseType; 
		this.courseProfName = courseProfName; 
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
	public String getCourseProfName()
	{
		return courseProfName; 
	}
	
	public void setCourseProfName(String courseProfName)
	{
		this.courseProfName = courseProfName; 
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
	
	
	public int getStudentList()
	{
		return studentList.size(); 
	}
	
	public int getStudentID(int index)
	{		
		return studentList.get(index).getStudentID(); 
	}
	
	public void addStudents(Student student)
	{
		studentList.add(student); 
	}
	
	public void addAssessment(Assessment assessment) {
		
		assessmentList.add(assessment); 
	
	}
	
	public static void addCourse(int courseTypes)
	{
		ClassDB case2ClassDB = new ClassDB();
    	String case2ClassFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Class.txt";
    	String case2CourseFile = "C:\\Users\\mock_\\Desktop\\OODP Software Codes\\Course.txt";
		
    	int courseID;
		String courseName;
		int courseType;
		String courseProfName;
		int courseFreeSlot; 
		int courseTotalSlot = 0; 
		String classType;
		String classCode;  
		int classSize; 
		int numOfClass; 
		int classSlots; 
    	
		try {
			
			ArrayList newCourse = CourseDB.readCourse(case2CourseFile);
			ArrayList newClass = ClassDB.readClasses(case2ClassFile); 
			
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
				System.out.print("Please enter Lecture Size Group:");
				classSize = sc.nextInt(); 
				
				Class case2NewClass = new Class(courseID, "Lecture", classCode, classSize); 
				
				courseTotalSlot = classSize; 
				courseFreeSlot = courseTotalSlot; 
				
				newClass.add(case2NewClass); 
				ClassDB.saveClasses(case2ClassFile, newClass);
				
				System.out.println(courseName + "(" + courseID + ") " + " Lecture Code: " + classCode + " is created" );
				
				Course case2NewCCourse = new Course(courseID, courseName, courseType, courseProfName, courseFreeSlot, courseTotalSlot);  
				
				newCourse.add(case2NewCCourse); 
				CourseDB.saveCourse(case2CourseFile, newCourse); 
				
				System.out.print("Course " + courseName + "(" + courseID + ") " + " taught by " + courseProfName + " is created " );
			}
			
			else if(courseType == 2 || courseType == 3)
			{
				//Prompt user to enter the number of Tutorial classes (E.g 4 Tutorial Classes for 2002)
				System.out.print("Please enter the number of Tutorial Classes for " + courseName + "(" + courseID + "): ");
				numOfClass = sc.nextInt(); 
				
				for(int i = 0; i<numOfClass; i++) //For each of the classes, enter the class codes and number of slots 
				{
					System.out.print("Please enter Tutorial Class Code for: " + courseName + "(" + courseID + "): ");
					classCode = sc.next(); 
					
					System.out.print("Please enter Tutorial Class Size for: " + classCode);
					classSize = sc.nextInt(); 
					
					courseTotalSlot += classSize; 
													
					Class case2NewClass = new Class(courseID, "Tutorial", classCode, classSize); 
					
					newClass.add(case2NewClass); 
					ClassDB.saveClasses(case2ClassFile, newClass);
					
					System.out.print("Tutorial Class Code " + classCode + " for " + courseName + "(" + courseID + ") is created" );
											
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
						
						System.out.print("Please enter the Class Size for: " + classCode);
						classSize = sc.nextInt(); 
						
						courseTotalSlot += classSize; 
												
						Class case2NewClass = new Class(courseID, "Lab", classCode, classSize); 
						
						newClass.add(case2NewClass); 
						
						System.out.print("Lab Class Code " + classCode + " for " + courseName + "(" + courseID + ") is created" );
					
					}
				
				}
							
			}
			
			System.out.println("Please enter the Lecture Group Code for" + courseName + "( " + courseID + "):  ");
			classCode = sc.next(); 
			
			courseFreeSlot = courseTotalSlot; 
			
			Class case2NewClass = new Class(courseID, "Lecture", classCode, courseTotalSlot); 
			
			newClass.add(case2NewClass); 
			ClassDB.saveClasses(case2ClassFile, newClass);
			
			Course case2NewCCourse = new Course(courseID, courseName, courseType, courseProfName, courseFreeSlot, courseTotalSlot);  
			
			newCourse.add(case2NewCCourse); 
			CourseDB.saveCourse(case2CourseFile, newCourse); 
			
			
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
	
}


