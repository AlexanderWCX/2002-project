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
	
	private int noOfStudents = 0;
	private int noOfAssessments = 0;
	private int noOfClasses = 0;
	
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
	
	public int getNoOfClasses()
	{
		return noOfClasses; 
	}
	
	public void setNoOfClasses(int noOfClasses)
	{
		this.noOfClasses = noOfClasses; 
	}
	
	public int getNoOfStudents()
	{
		return noOfStudents; 
	}
	
	public void setNoOfStudents(int noOfStudents)
	{
		this.noOfStudents = noOfStudents; 
	}
	
	public Assessment getAssessment(int index) {
		return assessmentList.get(index);
	}
	
	public int getNoOfAssessments()
	{
		return noOfAssessments; 
	}
	
	public void setNoOfAssessments(int noOfAssessments)
	{
		this.noOfAssessments = noOfAssessments; 
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
		noOfStudents++;
		courseFreeSlot--;
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
		noOfAssessments++;
		
	
	}
	
	public int getClassListSize()
	{
		return classList.size(); 
	}

	public Class getClassObject(int index)
	{
		return classList.get(index);
	}
	

	public String getClassCode(int index)
	{		
		return classList.get(index).getClassCode(); 
	}
	
	public void addClass(Class newClass) {
		
		classList.add(newClass); 
		noOfClasses++;
	
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


